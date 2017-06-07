package com.sahu.geo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONException;

import com.esri.core.geometry.GeoJsonImportFlags;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.MapGeometry;
import com.esri.core.geometry.OperatorImportFromGeoJson;
import com.esri.core.geometry.Point;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CellProcessor implements Serializable{
	

	private static final long serialVersionUID = 1L;
	static ArrayList<Feature> features = new ArrayList<Feature>();
	String cellId="";
	public static String file="";
	public static CellProcessor geo=null ;

	public static CellProcessor getInstance(){
      try {
		  if (geo == null) {
			  geo = new CellProcessor();
		  }
	  }catch(JSONException e){
      	e.printStackTrace();
	  }catch(IOException ie){
	  	ie.printStackTrace();
	  }
		return geo;
	}
	public static void setFilePath(String p){
		file = p;
	}
	
	
   public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException, JSONException{
		CellProcessor geo = new CellProcessor();
		//medallion,hack_license,pickup_datetime,dropoff_datetime,trip_time_in_secs,trip_distance,pickup_longitude,pickup_latitude,dropoff_longitude,dropoff_latitude,payment_type,fare_amount,surcharge,mta_tax,tip_amount,tolls_amount,total_amount
		BufferedReader  br = new BufferedReader(new FileReader("C:\\HardDisk\\Teradata\\DBS\\sorted_data\\sorted_data.csv"));
		String line ="";
		int count=0;
		
		while((line = br.readLine())!= null){
			String[] token = line.split(",");
            count++;
			double pickup_longitude = Double.valueOf(token[6]);
			double pickup_latitude = Double.valueOf(token[7]);
			double dropoff_longitude = Double.valueOf(token[8]);
			double dropoff_latitude = Double.valueOf(token[9]);
		
			if(geo.contain(   pickup_longitude,pickup_latitude)){
				System.out.println(count+" :Pick_up_location:"+geo.getCellId());
			}else{
				System.out.println("pick up is out:"+count);
			}
			
			if(geo.contain( dropoff_longitude ,dropoff_latitude )){
				System.out.println(count+" :drop_off_location:"+geo.getCellId());
			}
			else{
				System.out.println("dropp off is out:"+count);
			}
			
		}
		
		br.close();
		
	}

	
	public CellProcessor() throws JsonParseException, JsonMappingException, IOException, JSONException{
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("cell_grid.geojson");
		java.util.LinkedHashMap geoJsonMap =  new ObjectMapper().readValue(inputStream, java.util.LinkedHashMap.class);
		
		if("FeatureCollection".equalsIgnoreCase((String)geoJsonMap.get("type"))){
			java.util.ArrayList<java.util.LinkedHashMap> featureCollection = (ArrayList) geoJsonMap.get("features");
			for(java.util.LinkedHashMap feature: featureCollection){
				Feature f = new Feature();
				f.setId(feature.get("id").toString());
				f.setProperties((Map<String, String> )feature.get("properties"));
				String geoJsonString = new ObjectMapper().writeValueAsString(feature.get("geometry"));
				//System.out.println(geoJsonString);
			    MapGeometry g = OperatorImportFromGeoJson.local().execute(GeoJsonImportFlags.geoJsonImportDefaults, Geometry.Type.Polygon, geoJsonString, null);
				f.setGeometry(new RichGeometry(g.getGeometry()));
				features.add(f);
			 }
		}
		else{
			System.out.println("Invalie Geo Json format !!!");
		}
	}
	public String getCellId() {
		return cellId;
	}

	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	public boolean contain(double x, double y){
		boolean found = false;
		Point pt1 = new Point( x , y);
		 for(Feature feature: features){
			 if(feature.getGeometry().contains(pt1)){
				 found = true;
				 setCellId(feature.getId());
				 return found;
			 }
		 }
		return found; 
	}
	
	






}
