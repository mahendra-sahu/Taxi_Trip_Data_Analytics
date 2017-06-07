package com.sahu.datastream

import kafka.serializer.StringDecoder;
import org.apache.spark.SparkConf;
import org.apache.log4j.{Level, Logger}
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._

object DirectDataStream {
  
  def main(args: Array[String]){
    if (args.length < 2) {
      System.err.println("Usage: DirectDataStream <hostname> <topic>")
      System.exit(1)
    }
    val Array(brokers, topics) = args
    val sparkConf = new SparkConf().setAppName("DirectKafkaWordCount").setMaster("local[2]")
   // sparkConf.set("spark.serializer","org.apache.spark.serializer.kryoserializer")
    val ssc = new StreamingContext(sparkConf, Seconds(3))
    val topicsSet = topics.split(",").toSet
    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers, "auto.offset.reset" -> "smallest")
    val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicsSet)
   /* val topicAndOffsets: scala.collection.immutable.Map[TopicAndPartition,Long] = map(new TopicAndPartition("topic name","partition"),1)
   val messages:InputDStream[(String,String)] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder,(String,String)](ssc, kafkaParams, 
        topicAndOffsets,(mmd:MessageAndMetadata[String,String])=>(mmd.key,mmd.message))
     */
    messages.foreachRDD{ rdd =>
      val offsetRange = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      val nParts = rdd.partitions.size
      
      rdd.foreachPartition { partition =>
        val currid = TaskContext.get.partitionId
        val osr: OffsetRange = offsetRange(currid)
        val untilOffset = osr.untilOffset
        val id = osr.partition
        val topic = osr.topic
        if(!partition.isEmpty){
          partition.foreach{ arrr =>
             var key = arrr._1
             var msg = arrr._2  
             println("============================>"+msg)
          }
        }
        //commit offset
        //(id,topic,untiloffset)
      }
    }


    // Start the computation
    ssc.start()
    ssc.awaitTermination()
  }
  

  
}