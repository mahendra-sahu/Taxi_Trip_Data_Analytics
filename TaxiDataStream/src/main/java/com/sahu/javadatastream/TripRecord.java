package com.sahu.javadatastream;


import java.sql.Date;

public class TripRecord implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5602916557131644328L;
	public Integer msgSequence;
	public Date pickup_datetime ;
	public Date dropoff_datetime ;
	public Long trip_time_in_secs ;
	public Double trip_distance ;
	public Double pickup_longitude ;
	public Double pickup_latitude ; 
	public Double dropoff_longitude ;
	public Double dropoff_latitude  ;
	public String payment_type      ;
	public Double fare_amount       ;
	public Double surcharge         ;
	public Double mta_tax           ;
	public Double tip_amount        ;
	public Double tolls_amount      ;
	public Double total_amount      ;
	public String pickUpCell        ;
	public String dropoffCell       ;
	
	
	
	public TripRecord(Integer msgSequence,Date pickup_datetime, Date dropoff_datetime,
			Long trip_time_in_secs, Double trip_distance,
			Double pickup_longitude, Double pickup_latitude,
			Double dropoff_longitude, Double dropoff_latitude,
			String payment_type, Double fare_amount, Double surcharge,
			Double mta_tax, Double tip_amount, Double tolls_amount,
			Double total_amount, String pickUpCell, String dropoffCell) {
		this.msgSequence = msgSequence;
		this.pickup_datetime = pickup_datetime;
		this.dropoff_datetime = dropoff_datetime;
		this.trip_time_in_secs = trip_time_in_secs;
		this.trip_distance = trip_distance;
		this.pickup_longitude = pickup_longitude;
		this.pickup_latitude = pickup_latitude;
		this.dropoff_longitude = dropoff_longitude;
		this.dropoff_latitude = dropoff_latitude;
		this.payment_type = payment_type;
		this.fare_amount = fare_amount;
		this.surcharge = surcharge;
		this.mta_tax = mta_tax;
		this.tip_amount = tip_amount;
		this.tolls_amount = tolls_amount;
		this.total_amount = total_amount;
		this.pickUpCell = pickUpCell;
		this.dropoffCell = dropoffCell;
	}
	
	
	public Integer getMsgSequence() {
		return msgSequence;
	}


	public void setMsgSequence(Integer msgSequence) {
		this.msgSequence = msgSequence;
	}


	public Date getPickup_datetime() {
		return pickup_datetime;
	}
	public void setPickup_datetime(Date pickup_datetime) {
		this.pickup_datetime = pickup_datetime;
	}
	public Date getDropoff_datetime() {
		return dropoff_datetime;
	}
	public void setDropoff_datetime(Date dropoff_datetime) {
		this.dropoff_datetime = dropoff_datetime;
	}
	public Long getTrip_time_in_secs() {
		return trip_time_in_secs;
	}
	public void setTrip_time_in_secs(Long trip_time_in_secs) {
		this.trip_time_in_secs = trip_time_in_secs;
	}
	public Double getTrip_distance() {
		return trip_distance;
	}
	public void setTrip_distance(Double trip_distance) {
		this.trip_distance = trip_distance;
	}
	public Double getPickup_longitude() {
		return pickup_longitude;
	}
	public void setPickup_longitude(Double pickup_longitude) {
		this.pickup_longitude = pickup_longitude;
	}
	public Double getPickup_latitude() {
		return pickup_latitude;
	}
	public void setPickup_latitude(Double pickup_latitude) {
		this.pickup_latitude = pickup_latitude;
	}
	public Double getDropoff_longitude() {
		return dropoff_longitude;
	}
	public void setDropoff_longitude(Double dropoff_longitude) {
		this.dropoff_longitude = dropoff_longitude;
	}
	public Double getDropoff_latitude() {
		return dropoff_latitude;
	}
	public void setDropoff_latitude(Double dropoff_latitude) {
		this.dropoff_latitude = dropoff_latitude;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public Double getFare_amount() {
		return fare_amount;
	}
	public void setFare_amount(Double fare_amount) {
		this.fare_amount = fare_amount;
	}
	public Double getSurcharge() {
		return surcharge;
	}
	public void setSurcharge(Double surcharge) {
		this.surcharge = surcharge;
	}
	public Double getMta_tax() {
		return mta_tax;
	}
	public void setMta_tax(Double mta_tax) {
		this.mta_tax = mta_tax;
	}
	public Double getTip_amount() {
		return tip_amount;
	}
	public void setTip_amount(Double tip_amount) {
		this.tip_amount = tip_amount;
	}
	public Double getTolls_amount() {
		return tolls_amount;
	}
	public void setTolls_amount(Double tolls_amount) {
		this.tolls_amount = tolls_amount;
	}
	public Double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}
	public String getPickUpCell() {
		return pickUpCell;
	}
	public void setPickUpCell(String pickUpCell) {
		this.pickUpCell = pickUpCell;
	}
	public String getDropoffCell() {
		return dropoffCell;
	}
	public void setDropoffCell(String dropoffCell) {
		this.dropoffCell = dropoffCell;
	}
    
    
    
	}