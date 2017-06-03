package com.by.alex.parking.service;

import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.service.exception.ServiceException;

public interface UserService {

	
	public ParkingPlace searchPlaceNow(String weelType, int id, double startTime, double duration) throws ServiceException;
	public double takeLeftPlaces();
	public ParkingPlace takeWeelsBack(String placeID) throws ServiceException;
}
