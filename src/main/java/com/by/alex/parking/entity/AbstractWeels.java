package com.by.alex.parking.entity;

import java.util.Arrays;

public abstract class AbstractWeels {

    private int id;
    private String startTime;
    private String duration;
    private String color;
    private String[] random = { "Red", "Blue", "Black", "White", "Green" };

    public AbstractWeels(String startTime, String duration) {
	super();
	this.color = random[(int) (Math.random() * random.length)];
	this.startTime = startTime;
	this.duration = duration;
	this.id = hashCode();
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

    public String getDuration() {
	return duration;
    }

    public void setDuration(String duration) {
	this.duration = duration;
    }

    @Override
    public String toString() {
	return "id=" + id + ", startTime=" + startTime + ", duration=" + duration + ", color=" + color;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((color == null) ? 0 : color.hashCode());
	result = prime * result + ((duration == null) ? 0 : duration.hashCode());
	result = prime * result + id;
	result = prime * result + Arrays.hashCode(random);
	result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AbstractWeels other = (AbstractWeels) obj;
	if (color == null) {
	    if (other.color != null)
		return false;
	} else if (!color.equals(other.color))
	    return false;
	if (duration == null) {
	    if (other.duration != null)
		return false;
	} else if (!duration.equals(other.duration))
	    return false;
	if (id != other.id)
	    return false;
	if (!Arrays.equals(random, other.random))
	    return false;
	if (startTime == null) {
	    if (other.startTime != null)
		return false;
	} else if (!startTime.equals(other.startTime))
	    return false;
	return true;
    }

}
