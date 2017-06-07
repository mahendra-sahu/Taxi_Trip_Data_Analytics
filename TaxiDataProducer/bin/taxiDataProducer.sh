



export APP_PATH=$BASE_DIR/TaxiDataProducer/bin
java -cp  .:$APP_PATH/../target/original-TaxiDataProducer-1.0.jar:$APP_PATH/../lib/commons-math3-3.4.1.jar:$APP_PATH/../lib/kafka-clients-0.8.2.1.jar:$APP_PATH/../lib/kafka_2.11-0.8.2.1.jar:$APP_PATH/../lib/log4j-1.2.14.jar:$APP_PATH/../lib/lz4-1.2.0.jar:$APP_PATH/../lib/metrics-core-2.2.0.jar:$APP_PATH/../lib/scala-library-2.11.8.jar:$APP_PATH/../lib/scala-parser-combinators_2.11-1.0.2.jar:$APP_PATH/../lib/scala-xml_2.11-1.0.2.jar:$APP_PATH/../lib/slf4j-api-1.7.2.jar:$APP_PATH/../lib/snappy-java-1.1.1.6.jar:$APP_PATH/../lib/spark-streaming-kafka-0-8_2.11-2.1.1.jar:$APP_PATH/../lib/spark-tags_2.11-2.1.1.jar:$APP_PATH/../lib/unused-1.0.0.jar:$APP_PATH/../lib/zkclient-0.3.jar  com.sahu.datastream.TripRecordProducer sandbox.hortonworks.com:6667  test  10 $BASE_DIR/TaxiDataProducer/testData/sorted_data.csv

