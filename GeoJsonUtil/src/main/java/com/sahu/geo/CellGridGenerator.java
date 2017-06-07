package com.sahu.geo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CellGridGenerator {

	//lat=x
	//lon=y
	
	//longitude values are considered the x-coordinate, 
	// latitude values are the y-coordinate.
	
	public static void main(String args[]) throws IOException {
		
		generateFile(args[0]);
	
	}
	
	public static void generateFile(String filePath)throws IOException{

		//41.474937, -74.913585  ==center point  [top point after subtracting 250m from north and 250m from ea]
		//getting top point of Rectangle from Given center 
		
		//top    [41.47718278821029,-74.91658239966345]
		Point toppoint = addDistance(41.474937, -74.913585, 250, -250);
		FileWriter fw = new FileWriter(new File(filePath+"/cell_grid.geojson"));
		fw.write("{\"type\": \"FeatureCollection\",\"features\": [  \n");
		ArrayList<Rectangle> firstRow = new ArrayList<Rectangle>();
		
		for(int col=1; col <=300; col++){
			Rectangle rect =  createRectange(toppoint);
			rect.setRow(1);
			rect.setColumn(col);
			firstRow.add(rect);
			toppoint = rect.getEast();
		}
		
		for(int row=1; row <=300;row++  ){
			for(int col=0; col <300; col++){
				Rectangle rect =  createRectange(firstRow.get(col).getSouth());
				rect.setRow(row);
				rect.setColumn(col+1);
				fw.write(rect+", \n");
				firstRow.set(col,rect);
			}
		}
		
		fw.write("]}");
		fw.close();
		
	
	}
	
	
	public static Rectangle createRectange(Point basepoint){
		Rectangle rect = new Rectangle();
		rect.setTop(basepoint);
		rect.setEast(addDistance(basepoint.getX(),basepoint.getY() ,0,500));
		rect.setSouthEast(addDistance(basepoint.getX(),basepoint.getY() ,-500,500));
		rect.setSouth(addDistance(basepoint.getX(),basepoint.getY() ,-500,0));
		return rect;
	}
	
	
	public static Point addDistance(double lat,double lon  ,double distanceNorth, double distanceEast){
		 //Earth radius, sphere
		 double R=6378137 ;
		 //Coordinate offsets in radians
		 double dLat = distanceNorth/R ;
		 double dLon = distanceEast/(R*Math.cos(Math.PI*lat/180));
		 //OffsetPosition, decimal degrees
		 double latO = lat + dLat * 180/Math.PI;
		 double lonO = lon + dLon * 180/Math.PI;
		 return new Point (latO,lonO);
	}
	
	
}
