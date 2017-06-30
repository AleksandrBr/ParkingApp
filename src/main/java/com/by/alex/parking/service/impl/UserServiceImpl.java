package com.by.alex.parking.service.impl;

import java.util.List;

import com.by.alex.parking.dao.exception.DAOException;
import com.by.alex.parking.dao.factory.DAOFactory;
import com.by.alex.parking.entity.AbstractWeels;
import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.factory.FactoryWeels;
import com.by.alex.parking.service.UserService;
import com.by.alex.parking.service.exception.ServiceException;
import com.by.alex.parking.utils.TimeHolder;
import com.by.alex.parking.utils.WorkWithDate;

public class UserServiceImpl implements UserService {
    private AbstractWeels weelType;
    private ParkingPlace parkingPlace;

    @Override
    public ParkingPlace searchPlaceNow(String weelTypeName, String startTime, String duration) throws ServiceException {
	final String endTime = WorkWithDate.getEndTime(startTime, duration);

	if (takeLeftPlaces(startTime, endTime)) {
	    String end_time = WorkWithDate.getEndTime(startTime, duration);
	    weelType = FactoryWeels.getWeels(weelTypeName, startTime, duration);
	    parkingPlace = new ParkingPlace(weelType, "Slot1");
	    try {
		DAOFactory.getInstance().getUserDAO().takePlaceInParking(parkingPlace, end_time);
		return parkingPlace;
	    } catch (DAOException e) {
		throw new ServiceException("ERROR IN DB: " + e.getLocalizedMessage()
		        + "\nGet Lof File for More Information Or Connect With Administator");
		// TODO e.printStackTrace();
	    }
	} else {
	    throw new ServiceException(
	            String.format("Sorry But At This Time: '%s - %s' we have no empty places", startTime, endTime));
	}

    }

    @Override
    public boolean takeLeftPlaces(String startTime, String endTime) throws ServiceException {
	List<TimeHolder> timeHolderList;
	try {
	    timeHolderList = DAOFactory.getInstance().getUserDAO().showFreeTime("Slot1");
	    WorkWithDate.isFreeTime(startTime, endTime, timeHolderList);

	    return WorkWithDate.isFreeTime(startTime, endTime, timeHolderList);
	} catch (DAOException e) {
	    throw new ServiceException(e.getLocalizedMessage());
	    // TODO e.printStackTrace();

	}
    }

    @Override
    public ParkingPlace takeWeelsBack(int placeID) throws ServiceException {
	takeParkingPlaceID(placeID);
	try {
	    if (DAOFactory.getInstance().getUserDAO().removeWeels("Slot1", placeID)) {
		System.out.println("Deleted success");
	    }
	    return parkingPlace;
	} catch (DAOException e) {
	    throw new ServiceException("ERROR IN DB: " + e.getLocalizedMessage()
	            + "\nGet Lof File for More Information Or Connect With Administator");
	    // TODO: Work with exception e.printStackTrace();
	}
    }

    @Override
    public String getSchedule() throws ServiceException {
	StringBuilder sb = new StringBuilder();
	final String msg = "Thanks For Your Waiting.\nWe have empty parking places on time \nfrom %s to %s";
	final String addTimeMsg = "\nand from %s to %s";
	List<String> freeTimeList;
	try {
	    freeTimeList = DAOFactory.getInstance().getUserDAO().getSchedule();

	    if (freeTimeList.isEmpty()) {
		return String.format(msg, "00:00", "24:00");
	    }
	    for (int i = 0; i < freeTimeList.size() - 1; i = i + 2) {
		if (i == 0) {
		    sb.append(String.format(msg, freeTimeList.get(i), freeTimeList.get(i + 1)));
		    continue;
		}
		sb.append(String.format(addTimeMsg, freeTimeList.get(i), freeTimeList.get(i + 1)));
	    }
	    return sb.toString();
	} catch (DAOException e) {
	    throw new ServiceException(e.getLocalizedMessage());
	}
    }

    private void takeParkingPlaceID(int placeID) {
	try {
	    DAOFactory.getInstance().getUserDAO().getPlaceInfoByID(placeID);
	} catch (DAOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
