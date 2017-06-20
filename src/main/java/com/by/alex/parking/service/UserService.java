package com.by.alex.parking.service;

import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.service.exception.ServiceException;

public interface UserService {

	
	public ParkingPlace searchPlaceNow(String weelType, String startTime, String duration) throws ServiceException;
	public boolean takeLeftPlaces(String startTime, String endTime);
	public ParkingPlace takeWeelsBack(int placeID) throws ServiceException;
}
