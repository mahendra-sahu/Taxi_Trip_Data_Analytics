package com.sahu.geo;

import java.io.Serializable;

import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.SpatialReference;



public class RichGeometry implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 5900423307764191923L;
	Geometry geometry;
	 SpatialReference   spatialReference  = SpatialReference.create(4326); 
		    
	 public RichGeometry(Geometry g){
		 this.geometry = g;
	 }
	 public boolean  contains( Geometry other){
			return  GeometryEngine.contains(this.geometry, other, spatialReference);
	  }
}

