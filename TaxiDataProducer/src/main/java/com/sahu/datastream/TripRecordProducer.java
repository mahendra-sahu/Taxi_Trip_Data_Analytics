package com.sahu.datastream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class TripRecordProducer {
	
	  public static void main(String[] args) throws Exception {
		    if (args.length < 3) {
		      System.err.println("Usage: TripRecordProducer <brokers> <topics> <no. of msg to send> <file_path>\n" +
		          "  <brokers> is a list of one or more Kafka brokers\n" +
		          "  <topics> is a list of one or more kafka topics to consume from\n\n");
		      System.exit(1);
		    }
		   
		    String topicName = args[1].toString();
		    Properties props = new Properties();
		   
		    props.put("bootstrap.servers", args[0]);
		    props.put("acks", "all");
		    props.put("retries", 3);
		    props.put("batch.size", 16384);
		    //Reduce the no of requests less than 0   
		    props.put("linger.ms", 10);
		    //The buffer.memory controls the total amount of memory available to the producer for buffering.   
		    props.put("buffer.memory", 33554432);
		    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		    
		    Producer<String, String> producer = new KafkaProducer<String, String>(props);
		    int msgCount =0;
		    int numberOfMsg = Integer.valueOf(args[2]);
		    
		    try {
				BufferedReader buffer = new BufferedReader(new FileReader(args[3]));
				String line = null;
				while( (line = buffer.readLine()) != null  && msgCount < numberOfMsg){
					msgCount++;
					System.out.println(line);
					producer.send(new ProducerRecord<String, String>(topicName,Integer.toString(msgCount), line));
				}
				buffer.close();
			} catch (Exception e) {
				System.out.println("Error while reading Test Input File ");
				e.printStackTrace();
			}
		    System.out.println("Number of Message sent successfully:"+msgCount);
		    producer.close();
	  }
}








