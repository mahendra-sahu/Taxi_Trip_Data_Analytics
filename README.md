##Create Kafka Topic
/usr/hdp/current/kafka-broker/bin/kafka-topics.sh --create  --zookeeper sandbox.hortonworks.com:2181 -replication-factor 1 --partitions 1 --topic test

##Verify is topic "test" has been created 
/usr/hdp/current/kafka-broker/bin/kafka-topics.sh --list --zookeeper sandbox.hortonworks.com:2181

##Test the topic 
/usr/hdp/current/kafka-broker/bin/kafka-console-producer.sh --broker-list sandbox.hortonworks.com:6667 --topic test
/usr/hdp/current/kafka-broker/bin/kafka-console-consumer.sh --zookeeper sandbox.hortonworks.com:2181 --topic test --from-beginning

#Download the code and put into any dir example /tmp/mahendraAssignment 
export BASE_DIR=/tmp/mahendraAssignment 

##on first console Start the Spark Streaming 
export APP_PATH=$BASE_DIR/TaxiDataStream/bin
java -cp  .:$APP_PATH:$APP_PATH/../lib/antlr4-runtime-4.5.3.jar:$APP_PATH/../lib/aopalliance-1.0.jar:$APP_PATH/../lib/aopalliance-repackaged-2.4.0-b34.jar:$APP_PATH/../lib/avro-1.7.7.jar:$APP_PATH/../lib/avro-ipc-1.7.7-tests.jar:$APP_PATH/../lib/avro-ipc-1.7.7.jar:$APP_PATH/../lib/avro-mapred-1.7.7-hadoop2.jar:$APP_PATH/../lib/chill-java-0.8.0.jar:$APP_PATH/../lib/chill_2.11-0.8.0.jar:$APP_PATH/../lib/commons-beanutils-1.7.0.jar:$APP_PATH/../lib/commons-beanutils-core-1.8.0.jar:$APP_PATH/../lib/commons-cli-1.2.jar:$APP_PATH/../lib/commons-codec-1.3.jar:$APP_PATH/../lib/commons-collections-3.2.1.jar:$APP_PATH/../lib/commons-compiler-3.0.0.jar:$APP_PATH/../lib/commons-compress-1.4.1.jar:$APP_PATH/../lib/commons-configuration-1.6.jar:$APP_PATH/../lib/commons-crypto-1.0.0.jar:$APP_PATH/../lib/commons-digester-1.8.jar:$APP_PATH/../lib/commons-httpclient-3.1.jar:$APP_PATH/../lib/commons-io-2.1.jar:$APP_PATH/../lib/commons-lang-2.5.jar:$APP_PATH/../lib/commons-lang3-3.5.jar:$APP_PATH/../lib/commons-math-2.1.jar:$APP_PATH/../lib/commons-math3-3.4.1.jar:$APP_PATH/../lib/commons-net-2.2.jar:$APP_PATH/../lib/compress-lzf-1.0.3.jar:$APP_PATH/../lib/curator-client-2.4.0.jar:$APP_PATH/../lib/curator-framework-2.4.0.jar:$APP_PATH/../lib/curator-recipes-2.4.0.jar:$APP_PATH/../lib/esri-geometry-api-1.2.1.jar:$APP_PATH/../lib/geojson-jackson-1.8.jar:$APP_PATH/../lib/GeoUtil-1.0.jar:$APP_PATH/../lib/guava-14.0.1.jar:$APP_PATH/../lib/guice-3.0.jar:$APP_PATH/../lib/hadoop-annotations-2.2.0.jar:$APP_PATH/../lib/hadoop-auth-2.2.0.jar:$APP_PATH/../lib/hadoop-client-2.2.0.jar:$APP_PATH/../lib/hadoop-common-2.2.0.jar:$APP_PATH/../lib/hadoop-hdfs-2.2.0.jar:$APP_PATH/../lib/hadoop-mapreduce-client-app-2.2.0.jar:$APP_PATH/../lib/hadoop-mapreduce-client-common-2.2.0.jar:$APP_PATH/../lib/hadoop-mapreduce-client-core-2.2.0.jar:$APP_PATH/../lib/hadoop-mapreduce-client-jobclient-2.2.0.jar:$APP_PATH/../lib/hadoop-mapreduce-client-shuffle-2.2.0.jar:$APP_PATH/../lib/hadoop-yarn-api-2.2.0.jar:$APP_PATH/../lib/hadoop-yarn-client-2.2.0.jar:$APP_PATH/../lib/hadoop-yarn-common-2.2.0.jar:$APP_PATH/../lib/hadoop-yarn-server-common-2.2.0.jar:$APP_PATH/../lib/hk2-api-2.4.0-b34.jar:$APP_PATH/../lib/hk2-locator-2.4.0-b34.jar:$APP_PATH/../lib/hk2-utils-2.4.0-b34.jar:$APP_PATH/../lib/ivy-2.4.0.jar:$APP_PATH/../lib/jackson-annotations-2.6.5.jar:$APP_PATH/../lib/jackson-core-2.7.3.jar:$APP_PATH/../lib/jackson-core-asl-1.9.12.jar:$APP_PATH/../lib/jackson-databind-2.6.5.jar:$APP_PATH/../lib/jackson-mapper-asl-1.9.13.jar:$APP_PATH/../lib/jackson-module-paranamer-2.6.5.jar:$APP_PATH/../lib/jackson-module-scala_2.11-2.6.5.jar:$APP_PATH/../lib/janino-3.0.0.jar:$APP_PATH/../lib/javassist-3.18.1-GA.jar:$APP_PATH/../lib/javax.annotation-api-1.2.jar:$APP_PATH/../lib/javax.inject-1.jar:$APP_PATH/../lib/javax.inject-2.4.0-b34.jar:$APP_PATH/../lib/javax.servlet-api-3.1.0.jar:$APP_PATH/../lib/javax.ws.rs-api-2.0.1.jar:$APP_PATH/../lib/jcl-over-slf4j-1.7.16.jar:$APP_PATH/../lib/jersey-client-2.22.2.jar:$APP_PATH/../lib/jersey-common-2.22.2.jar:$APP_PATH/../lib/jersey-container-servlet-2.22.2.jar:$APP_PATH/../lib/jersey-container-servlet-core-2.22.2.jar:$APP_PATH/../lib/jersey-guava-2.22.2.jar:$APP_PATH/../lib/jersey-media-jaxb-2.22.2.jar:$APP_PATH/../lib/jersey-server-2.22.2.jar:$APP_PATH/../lib/jets3t-0.7.1.jar:$APP_PATH/../lib/jetty-util-6.1.26.jar:$APP_PATH/../lib/json-20090211.jar:$APP_PATH/../lib/json4s-ast_2.11-3.2.11.jar:$APP_PATH/../lib/json4s-core_2.11-3.2.11.jar:$APP_PATH/../lib/json4s-jackson_2.11-3.2.11.jar:$APP_PATH/../lib/jsr305-1.3.9.jar:$APP_PATH/../lib/jul-to-slf4j-1.7.16.jar:$APP_PATH/../lib/kafka-clients-0.8.2.1.jar:$APP_PATH/../lib/kafka_2.11-0.8.2.1.jar:$APP_PATH/../lib/kryo-shaded-3.0.3.jar:$APP_PATH/../lib/leveldbjni-all-1.8.jar:$APP_PATH/../lib/log4j-1.2.17.jar:$APP_PATH/../lib/lz4-1.3.0.jar:$APP_PATH/../lib/metrics-core-2.2.0.jar:$APP_PATH/../lib/metrics-core-3.1.2.jar:$APP_PATH/../lib/metrics-graphite-3.1.2.jar:$APP_PATH/../lib/metrics-json-3.1.2.jar:$APP_PATH/../lib/metrics-jvm-3.1.2.jar:$APP_PATH/../lib/minlog-1.3.0.jar:$APP_PATH/../lib/netty-3.8.0.Final.jar:$APP_PATH/../lib/netty-all-4.0.42.Final.jar:$APP_PATH/../lib/objenesis-2.1.jar:$APP_PATH/../lib/oro-2.0.8.jar:$APP_PATH/../lib/osgi-resource-locator-1.0.1.jar:$APP_PATH/../lib/paranamer-2.6.jar:$APP_PATH/../lib/parquet-column-1.8.1.jar:$APP_PATH/../lib/parquet-common-1.8.1.jar:$APP_PATH/../lib/parquet-encoding-1.8.1.jar:$APP_PATH/../lib/parquet-format-2.3.0-incubating.jar:$APP_PATH/../lib/parquet-hadoop-1.8.1.jar:$APP_PATH/../lib/parquet-jackson-1.8.1.jar:$APP_PATH/../lib/protobuf-java-2.5.0.jar:$APP_PATH/../lib/py4j-0.10.4.jar:$APP_PATH/../lib/pyrolite-4.13.jar:$APP_PATH/../lib/RoaringBitmap-0.5.11.jar:$APP_PATH/../lib/scala-compiler-2.11.0.jar:$APP_PATH/../lib/scala-library-2.11.8.jar:$APP_PATH/../lib/scala-parser-combinators_2.11-1.0.2.jar:$APP_PATH/../lib/scala-reflect-2.11.7.jar:$APP_PATH/../lib/scala-xml_2.11-1.0.2.jar:$APP_PATH/../lib/scalap-2.11.0.jar:$APP_PATH/../lib/scopt_2.11-3.3.0.jar:$APP_PATH/../lib/slf4j-api-1.7.16.jar:$APP_PATH/../lib/slf4j-log4j12-1.7.16.jar:$APP_PATH/../lib/snappy-java-1.1.2.6.jar:$APP_PATH/../lib/spark-catalyst_2.11-2.1.1.jar:$APP_PATH/../lib/spark-core_2.11-2.1.1.jar:$APP_PATH/../lib/spark-launcher_2.11-2.1.1.jar:$APP_PATH/../lib/spark-network-common_2.11-2.1.1.jar:$APP_PATH/../lib/spark-network-shuffle_2.11-2.1.1.jar:$APP_PATH/../lib/spark-sketch_2.11-2.1.1.jar:$APP_PATH/../lib/spark-sql_2.11-2.1.1.jar:$APP_PATH/../lib/spark-streaming-kafka-0-8_2.11-2.1.1.jar:$APP_PATH/../lib/spark-streaming-twitter_2.11-1.6.0.jar:$APP_PATH/../lib/spark-streaming_2.11-2.1.1.jar:$APP_PATH/../lib/spark-tags_2.11-2.1.1.jar:$APP_PATH/../lib/spark-unsafe_2.11-2.1.1.jar:$APP_PATH/../lib/stream-2.7.0.jar:$APP_PATH/../lib/twitter4j-core-3.0.3.jar:$APP_PATH/../lib/twitter4j-stream-3.0.3.jar:$APP_PATH/../lib/univocity-parsers-2.2.1.jar:$APP_PATH/../lib/unused-1.0.0.jar:$APP_PATH/../lib/validation-api-1.1.0.Final.jar:$APP_PATH/../lib/xbean-asm5-shaded-4.4.jar:$APP_PATH/../lib/xercesImpl-2.8.0.jar:$APP_PATH/../lib/xml-apis-1.3.03.jar:$APP_PATH/../lib/xmlenc-0.52.jar:$APP_PATH/../lib/xz-1.0.jar:$APP_PATH/../lib/zkclient-0.3.jar:$APP_PATH/../lib/zookeeper-3.4.5.jar:$APP_PATH/../target/original-TaxiDataStream-2.1.0.jar:$APP_PATH/../lib/cell_grid.geojson: com.sahu.javadatastream.TaxiDStreamDataFrame sandbox.hortonworks.com:6667  test


##on second console Produce some msg to Kafka Topic
export APP_PATH=$BASE_DIR/TaxiDataProducer/bin
java -cp  .:$APP_PATH/../target/original-TaxiDataProducer-1.0.jar:$APP_PATH/../lib/commons-math3-3.4.1.jar:$APP_PATH/../lib/kafka-clients-0.8.2.1.jar:$APP_PATH/../lib/kafka_2.11-0.8.2.1.jar:$APP_PATH/../lib/log4j-1.2.14.jar:$APP_PATH/../lib/lz4-1.2.0.jar:$APP_PATH/../lib/metrics-core-2.2.0.jar:$APP_PATH/../lib/scala-library-2.11.8.jar:$APP_PATH/../lib/scala-parser-combinators_2.11-1.0.2.jar:$APP_PATH/../lib/scala-xml_2.11-1.0.2.jar:$APP_PATH/../lib/slf4j-api-1.7.2.jar:$APP_PATH/../lib/snappy-java-1.1.1.6.jar:$APP_PATH/../lib/spark-streaming-kafka-0-8_2.11-2.1.1.jar:$APP_PATH/../lib/spark-tags_2.11-2.1.1.jar:$APP_PATH/../lib/unused-1.0.0.jar:$APP_PATH/../lib/zkclient-0.3.jar  com.sahu.datastream.TripRecordProducer sandbox.hortonworks.com:6667  test  10 $BASE_DIR/TaxiDataProducer/testData/sorted_data.csv

##Verify the result on first console
+---------+----------+-----------+
|tripCount|pickUpCell|dropoffCell|
+---------+----------+-----------+
|       50|       Out|        Out|
|       21|  168.153 |   167.153 |
|       15|  155.161 |   159.159 |
|       15|  161.158 |   159.159 |
|       15|  169.161 |   169.160 |
|       15|  161.154 |        Out|
|       15|  152.158 |   155.156 |
|       15|  154.156 |   154.157 |
|       15|       Out|   166.156 |
|       14|  160.164 |   160.164 |
|       12|  168.153 |   170.152 |
|       12|  157.156 |   152.158 |
|       12|  159.158 |   160.157 |
|        8|  165.153 |   169.151 |
|        8|  161.156 |   162.156 |
|        8|  161.157 |   161.155 |
|        8|  168.155 |        Out|
|        8|  151.158 |   147.160 |
|        8|  159.158 |   161.158 |
|        8|  151.163 |   151.164 |
+---------+----------+-----------+

##Verify the Cell Grid logic
##Cell Grid Generator ## $BASE_DIR/GeoJsonUtil/src/main/java/com/sahu/geo/CellGridGenerator.java
##This will generate the Cell Grid at path $BASE_DIR/GeoJsonUtil/cell_grid.geojson
export APP_PATH=$BASE_DIR/GeoJsonUtil/bin
java -cp  .:$APP_PATH/../target/GeoUtil-1.0.jar::$APP_PATH/../lib/esri-geometry-api-1.2.1.jar:$APP_PATH/../lib/geojson-jackson-1.8.jar:$APP_PATH/../lib/jackson-annotations-2.7.0.jar:$APP_PATH/../lib/jackson-core-2.7.3.jar:$APP_PATH/../lib/jackson-core-asl-1.9.12.jar:$APP_PATH/../lib/jackson-databind-2.7.3.jar:$APP_PATH/../lib/json-20090211.jar:$APP_PATH/../lib/xercesImpl-2.8.0.jar:$APP_PATH/../lib/xml-apis-1.3.03.jar com.sahu.geo.CellGridGenerator $BASE_DIR/GeoJsonUtil

## Cell Grid Generator step will generate the file $BASE_DIR/GeoJsonUtil/cell_grid.geojson
## Open the file and verify Cell ID and respective lon lat
more $BASE_DIR/GeoJsonUtil/cell_grid.geojson


##Verificatoin of Cell Grid by displaying Grid on google Map
## 
cd $BASE_DIR/GeoJsonDisplay
java -jar start.jar

http://localhost:7070/GeoJsonDisplay/CellView.html
