package com.by.alex.parking.dao;

import java.util.List;

import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.utils.TimeHolder;

public interface UserDAO {

	public int takePlaceInParking(ParkingPlace parkingPlace, String end_time);
	public boolean removeWeels(String placeID, int weel_id);
	public List<TimeHolder> showFreeTime(String placeID);
	public void addTimeToParkinPlace();
	public String getPlaceInfoByID(int weelID);
}
