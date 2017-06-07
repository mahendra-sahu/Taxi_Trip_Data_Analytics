
export APP_PATH=$BASE_DIR/GeoJsonUtil/bin
java -cp  .:$APP_PATH/../target/GeoUtil-1.0.jar::$APP_PATH/../lib/esri-geometry-api-1.2.1.jar:$APP_PATH/../lib/geojson-jackson-1.8.jar:$APP_PATH/../lib/jackson-annotations-2.7.0.jar:$APP_PATH/../lib/jackson-core-2.7.3.jar:$APP_PATH/../lib/jackson-core-asl-1.9.12.jar:$APP_PATH/../lib/jackson-databind-2.7.3.jar:$APP_PATH/../lib/json-20090211.jar:$APP_PATH/../lib/xercesImpl-2.8.0.jar:$APP_PATH/../lib/xml-apis-1.3.03.jar com.sahu.geo.CellGridGenerator $BASE_DIR/GeoJsonUtil

