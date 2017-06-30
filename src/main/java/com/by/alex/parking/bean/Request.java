package com.by.alex.parking.bean;

public class Request {

    private int id;
    private String startTime;
    private String duration;
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

    public String getStartTime() {
	return startTime;
    }

    public void setStartTime(String startTime) {
	this.startTime = startTime;
    }

    public String getDoubleDuration() {
	return duration;
    }

    public void setDoubleDuration(String duration) {
	this.duration = duration;
    }

}
