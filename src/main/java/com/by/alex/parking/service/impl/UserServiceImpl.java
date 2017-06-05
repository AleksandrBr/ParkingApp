package com.by.alex.parking.service.impl;

import java.util.List;

import com.by.alex.parking.entity.AbstractWeels;
import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.entity.place.PlaceHolder;
import com.by.alex.parking.entity.weels.Car;
import com.by.alex.parking.entity.weels.Motocycle;
import com.by.alex.parking.factory.FactoryWeels;
import com.by.alex.parking.service.UserService;
import com.by.alex.parking.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
	private FactoryWeels weelsFactory = new FactoryWeels();
	private AbstractWeels weelType;
	private ParkingPlace parkingPlace;
	private int parkingPlaceID;
	private List<ParkingPlace> carList;
	List<ParkingPlace> motoList;
	private int maxPlace = 10;

	@Override
	public ParkingPlace searchPlaceNow(String weelTypeName, double startTime, double duration)
			throws ServiceException {
		takeParkingPlaceID();
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
		return parkingPlace;
	}

	@Override
	public double takeLeftPlaces() {
		takeParkingPlaceID();
		if (maxPlace == parkingPlaceID && motoList.size() % 2 == 1) {
			return 0.5;
		}
		if (motoList.size() % 2 == 1) {
			return (maxPlace - parkingPlaceID) + 0.5;
		}
		return maxPlace - parkingPlaceID;
	}

	@Override
	public ParkingPlace takeWeelsBack(String placeID) throws ServiceException {
		takeParkingPlaceID();
		if (placeID.contains("C")) {
			for (ParkingPlace find : carList) {
				if (find.getPlaceId().equals(placeID)) {
					parkingPlace = find;
					PlaceHolder.getInstance().goAwayCar(find);
					break;
				} else {
					throw new ServiceException("THERE NO CAR ON PLACE WITH ID : '" + placeID + "'");
				}
			}
		} else {
			for (ParkingPlace find : motoList) {
				if (find.getPlaceId().equals(placeID)) {
					parkingPlace = find;
					PlaceHolder.getInstance().goAwayMoto(find);
					break;
				} else {
					throw new ServiceException("THERE NO MOTO ON PLACE WITH ID : '" + placeID + "'");
				}

			}
		}
		return parkingPlace;

	}

	private void takeParkingPlaceID() {
		carList = PlaceHolder.getInstance().getCurrentPlaceInformation().get(0);
		motoList = PlaceHolder.getInstance().getCurrentPlaceInformation().get(1);
		parkingPlaceID = (carList.size() + ((motoList.size() / 2) + (motoList.size() % 2)));
	}

}
