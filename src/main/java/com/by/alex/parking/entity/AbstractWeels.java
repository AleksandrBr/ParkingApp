package com.by.alex.parking.entity;

public abstract class AbstractWeels {

	private int id;
	private double startTime;
	private double duration;
	private String color;
	private String[] random = { "Red", "Blue", "Black", "White", "Green" };
	
	public AbstractWeels(int id, double startTime, double duration) {
		super();
		this.color = random[(int) (Math.random() * random.length)];
		this.id = id;
		this.startTime = startTime;
		this.duration = duration;
	}

	@Override
	public String toString() {
		return " id=" + id + ", startTime=" + startTime + ", duration=" + duration + ", color=" + color;
	}


}
