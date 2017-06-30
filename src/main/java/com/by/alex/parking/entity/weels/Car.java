package com.by.alex.parking.entity.weels;

import com.by.alex.parking.entity.AbstractWeels;

public class Car extends AbstractWeels {
    private String brand;
    private String[] random = { "BMW", "HONDA", "TOYOTA", "VOLGA", "PORSCHE" };

    public Car(String startTime, String duration) {
	super(startTime, duration);
	this.brand = random[(int) (Math.random() * random.length)];
    }

    @Override
    public String toString() {
	return "Car [ " + super.toString() + ", brand = " + brand + " ]";
    }

}
