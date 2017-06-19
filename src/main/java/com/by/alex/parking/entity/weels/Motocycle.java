package com.by.alex.parking.entity.weels;

import com.by.alex.parking.entity.AbstractWeels;

public class Motocycle extends AbstractWeels{
	private String brand;
	private String[] random = { "BMW", "HONDA", "SUZZUKI", "URAL", "YAMAHA" };

	public Motocycle(String startTime, String duration){//, String color, String brand) {
		super(startTime, duration);
		this.brand = random[(int) (Math.random() * random.length)];
	}

	@Override
	public String toString() {
		return "Motocycle [ " + super.toString() + ", brand = " + brand + " ]";
	}
	

	
}
