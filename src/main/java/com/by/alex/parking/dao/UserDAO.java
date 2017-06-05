package com.by.alex.parking.dao;

import com.by.alex.parking.entity.place.ParkingPlace;

public interface UserDAO {

	public void takePlaceInParking(ParkingPlace parkinPlace);
	public void removeWeels();
	public void showFreeTime();
	public void addTimeToParkinPlace();
}
