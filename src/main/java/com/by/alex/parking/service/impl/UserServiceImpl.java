package com.by.alex.parking.service.impl;

import java.util.List;

import com.by.alex.parking.dao.UserDAO;
import com.by.alex.parking.dao.factory.DAOFactory;
import com.by.alex.parking.entity.AbstractWeels;
import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.entity.place.PlaceHolder;
import com.by.alex.parking.entity.weels.Car;
import com.by.alex.parking.entity.weels.Motocycle;
import com.by.alex.parking.factory.FactoryWeels;
import com.by.alex.parking.service.UserService;
import com.by.alex.parking.service.exception.ServiceException;
import com.by.alex.parking.utils.TimeHolder;
import com.by.alex.parking.utils.WorkWithDate;

public class UserServiceImpl implements UserService {
	private AbstractWeels weelType;
	private ParkingPlace parkingPlace;
	private int parkingPlaceID;
	private List<ParkingPlace> carList;
	List<ParkingPlace> motoList;
	private int maxPlace = 10;

	@Override
	public ParkingPlace searchPlaceNow(String weelTypeName, String startTime, String duration)
			throws ServiceException {
		String endTime = WorkWithDate.getEndTime(startTime, duration);
		if(takeLeftPlaces(startTime, endTime)){
		String end_time = WorkWithDate.getEndTime(startTime, duration);
		weelType = FactoryWeels.getWeels(weelTypeName, startTime, duration);
		parkingPlace = new ParkingPlace(weelType, "Slot1");
		DAOFactory.getInstance().getUserDAO().takePlaceInParking(parkingPlace, end_time);
		return parkingPlace;}
		else{
			throw new ServiceException(String.format("Sorry But At This Time: '%s - %s' we have no empty places", startTime, endTime));
		}
		/*takeParkingPlaceID();
		if (parkingPlaceID == maxPlace) {
			if (motoList.size() % 2 == 1) {
				weelType = weelsFactory.getWeels(weelTypeName, startTime, duration);
				if (weelType instanceof Motocycle) {
					parkingPlace = new ParkingPlace(weelType, (parkingPlaceID + "-B"));
					PlaceHolder.getInstance().takeMotoPlace(parkingPlace);
				} else {
					throw new ServiceException(
							"Sorry but our parking is full at this moment(Left only 1 place for MOTO)\n");
				}
			} else {
				throw new ServiceException("Sorry but our parking is full at this moment\n");
			}
		} else {
			weelType = weelsFactory.getWeels(weelTypeName, startTime, duration);
			if (weelType instanceof Car) {
				parkingPlace = new ParkingPlace(weelType, (parkingPlaceID + "-C"));
				PlaceHolder.getInstance().takeCarPlace(parkingPlace);
			} else if (weelType instanceof Motocycle) {
				if (motoList.size() % 2 == 1)
					parkingPlace = new ParkingPlace(weelType, ((parkingPlaceID - 1) + "-B"));
				else
					parkingPlace = new ParkingPlace(weelType, (parkingPlaceID + "-A"));
				PlaceHolder.getInstance().takeMotoPlace(parkingPlace);
			}
		}
		String end_time = WorkWithDate.getEndTime(startTime, duration);
		DAOFactory.getInstance().getUserDAO().takePlaceInParking(parkingPlace, end_time);
		return parkingPlace;*/
	}

	@Override
	public boolean takeLeftPlaces(String startTime, String endTime) {
		List<TimeHolder> timeHolderList;
		timeHolderList = DAOFactory.getInstance().getUserDAO().showFreeTime("Slot1");
		WorkWithDate.isFreeTime(startTime, endTime, timeHolderList);
		
		return WorkWithDate.isFreeTime(startTime, endTime, timeHolderList);
//		takeParkingPlaceID();
//		if (maxPlace == parkingPlaceID && motoList.size() % 2 == 1) {
//			return 0.5;
//		}
//		if (motoList.size() % 2 == 1) {
//			return (maxPlace - parkingPlaceID) + 0.5;
//		}
//		return maxPlace - parkingPlaceID;
	}

	@Override
	public ParkingPlace takeWeelsBack(int placeID) throws ServiceException {
		takeParkingPlaceID(placeID);
		if(DAOFactory.getInstance().getUserDAO().removeWeels("Slot1", placeID)){
			System.out.println("Deleted success");
		}
		return parkingPlace;

	}

	private void takeParkingPlaceID(int placeID) {
		DAOFactory.getInstance().getUserDAO().getPlaceInfoByID(placeID);
	}

}
