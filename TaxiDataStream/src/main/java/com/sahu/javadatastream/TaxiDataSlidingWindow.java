package com.sahu.javadatastream;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kafka.serializer.StringDecoder;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import scala.Tuple2;
import com.sahu.geo.CellProcessor;

public final class TaxiDataSlidingWindow {

	

	  public static void main(String[] args) throws Exception {
	    if (args.length < 2) {
	      System.err.println("Usage: TaxiDataStream <brokers> <topics>\n" +
	          "  <brokers> is a list of one or more Kafka brokers\n" +
	          "  <topics> is a list of one or more kafka topics to consume from\n\n");
	      System.exit(1);
	    }

	    String brokers = args[0];
	    String topics = args[1];
	    final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    
	    // Create context with a 2 seconds batch interval
	    SparkConf sparkConf = new SparkConf().setAppName("JavaDirectKafkaWordCount").setMaster("local[2]");
	    JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(5));

	    Set<String> topicsSet = new HashSet<>(Arrays.asList(topics.split(",")));
	    Map<String, String> kafkaParams = new HashMap<>();
	    kafkaParams.put("metadata.broker.list", brokers);
	    kafkaParams.put("auto.offset.reset" , "smallest");
	    
	    
	    // Create direct kafka stream with brokers and topics
	    JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(
	        jssc,
	        String.class,
	        String.class,
	        StringDecoder.class,
	        StringDecoder.class,
	        kafkaParams,
	        topicsSet
	    );

	
	    CellProcessor.setFilePath(CellProcessor.class.getClassLoader().getResource("cell_grid.geojson").getFile());
	    final CellProcessor cellPro = CellProcessor.getInstance();
	    final Broadcast<CellProcessor> geo = jssc.sparkContext().broadcast(cellPro);
	    
	    
	    JavaDStream<TripRecord> records = messages.map(new Function<Tuple2<String, String>, TripRecord>() {
	      @Override
	      public TripRecord call(Tuple2<String, String> tuple2) {
	    	  TripRecord record = null;
	    	  try{
    		  Integer msgSequence =	Integer.valueOf(tuple2._1);
	    	  String[] recordTokens = tuple2._2().split(",");
	    	  if(recordTokens != null && recordTokens.length == 17){
		    	Date	  pickup_datetime   = new java.sql.Date(dateformat.parse(recordTokens[2]).getTime());
		    	Date      dropoff_datetime  = new java.sql.Date(dateformat.parse(recordTokens[3]).getTime());
	    		Long tripDuration = dropoff_datetime.getTime() - pickup_datetime.getTime();
	    		Long currentTime = System.currentTimeMillis();
	    		
	    		dropoff_datetime = new Date(currentTime);
	    		pickup_datetime =  new Date(currentTime - tripDuration);
	    		
	    		Long      trip_time_in_secs =Long.valueOf(recordTokens[4]);
	    		Double    trip_distance     =Double.valueOf(recordTokens[5]);
	    		Double    pickup_longitude   =Double.valueOf(recordTokens[6]);
	    		Double    pickup_latitude    =Double.valueOf(recordTokens[7]); 
	    		Double    dropoff_longitude  =Double.valueOf(recordTokens[8]);
	    		Double    dropoff_latitude   =Double.valueOf(recordTokens[9]);
	    		String    payment_type       =recordTokens[10];
	    		Double    fare_amount        =Double.valueOf(recordTokens[11]);
	    		Double    surcharge          =Double.valueOf(recordTokens[12]);
	    		Double    mta_tax            =Double.valueOf(recordTokens[13]);
	    		Double    tip_amount         =Double.valueOf(recordTokens[14]);
	    		Double    tolls_amount       =Double.valueOf(recordTokens[15]);
	    		Double    total_amount       =Double.valueOf(recordTokens[16]);
	    		String    pickUpCell         ="Out    ";
	    		String    dropoffCell        ="Out    ";
	    		if(geo.value().contain(pickup_longitude, pickup_latitude)){
	    			pickUpCell=geo.value().getCellId();
	    		}
	    		if(geo.value().contain(dropoff_longitude,dropoff_latitude)){
	    			dropoffCell=geo.value().getCellId();
	    		}
	    		record = new TripRecord(msgSequence,pickup_datetime, dropoff_datetime, trip_time_in_secs, trip_distance,
	    				pickup_longitude, pickup_latitude, dropoff_longitude, dropoff_latitude, payment_type, fare_amount, surcharge, mta_tax,
	    				tip_amount,	tolls_amount, total_amount, pickUpCell, dropoffCell);  
	    	  }
	    	  else{
	    		  System.out.println(recordTokens);
	    		  return null;
	    	  }
	    	  }
	    	  catch(Exception e){
	    		  e.printStackTrace();
	    		 return null;
	    	  }
	    	  return record;
	      }
	    });
	    
	    
	    JavaPairDStream<String, Integer> cellIdCountPair = records.mapToPair(
	  	      new PairFunction<TripRecord, String, Integer>() {
	  	        @Override
	  	        public Tuple2<String, Integer> call(TripRecord s) {
	  	        	if(s == null){
	  	        		return new Tuple2<>("-------Bad_Record-----", 1);
	  	        	}                        
	  	          return new Tuple2<>(s.getPickUpCell()+"-------"+s.getDropoffCell(), 1);
	  	        }
	  	      });
	    
	 // Reduce function adding two integers, defined separately for clarity
	    Function2<Integer, Integer, Integer> reduceFunc = new Function2<Integer, Integer, Integer>() {
	      @Override public Integer call(Integer i1, Integer i2) {
	        return i1 + i2;
	      }
	    };

	    // Reduce last 30 min of data, every 5 seconds
	    JavaPairDStream<String, Integer> last30MinData = cellIdCountPair.reduceByKeyAndWindow(reduceFunc, Durations.seconds(1800), Durations.seconds(5));

	    //last30MinData
	    JavaPairDStream<Integer,String> last30MinData1 = last30MinData.mapToPair(new PairFunction<Tuple2<String, Integer>,  Integer,String>() {
		      @Override
		      public Tuple2<Integer,String> call(Tuple2<String, Integer> tuple2) {
		    	  return new Tuple2<>(tuple2._2,tuple2._1);
		      }
		    });
	    
	    JavaPairDStream<Integer, String> sorted = last30MinData1.transformToPair(
       	     new Function<JavaPairRDD<Integer, String>, JavaPairRDD<Integer, String>>() {
       	         public JavaPairRDD<Integer, String> call(JavaPairRDD<Integer, String> in) throws Exception {
       	           return in.sortByKey(false);
       	         }
       	});
	    sorted.print();

	    // Start the computation
	    jssc.start();
	    jssc.awaitTermination();
	  }
}
