package com.by.alex.parking.bean;

public class Request {

	private int id;
	private double startTime;
	private double duration;
	private String weelsType;
	
	public String getWeelsType() {
		return weelsType;
	}
	public void setWeelsType(String weelsType) {
		this.weelsType = weelsType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getStartTime() {
		return startTime;
	}
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	public double getDoubleDuration() {
		return duration;
	}
	public void setDoubleDuration(double duration) {
		this.duration = duration;
	}
	
	
}
