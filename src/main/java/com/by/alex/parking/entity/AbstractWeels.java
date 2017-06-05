package com.by.alex.parking.entity;

import java.util.Arrays;

public abstract class AbstractWeels {

	private int id;
	private double startTime;
	private double duration;
	private String color;
	private String[] random = { "Red", "Blue", "Black", "White", "Green" };
	
	public AbstractWeels(double startTime, double duration) {
		super();
		this.color = random[(int) (Math.random() * random.length)];
		this.startTime = startTime;
		this.duration = duration;
		this.id = hashCode();
	}

	@Override
	public String toString() {
		return " id=" + id + ", startTime=" + startTime + ", duration=" + duration + ", color=" + color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		long temp;
		temp = Double.doubleToLongBits(duration);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + Arrays.hashCode(random);
		temp = Double.doubleToLongBits(startTime);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (Double.doubleToLongBits(duration) != Double.doubleToLongBits(other.duration))
			return false;
		if (id != other.id)
			return false;
		if (!Arrays.equals(random, other.random))
			return false;
		if (Double.doubleToLongBits(startTime) != Double.doubleToLongBits(other.startTime))
			return false;
		return true;
	}


}
