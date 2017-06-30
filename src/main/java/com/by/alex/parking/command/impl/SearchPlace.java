package com.by.alex.parking.command.impl;

import com.by.alex.parking.bean.Request;
import com.by.alex.parking.bean.Response;
import com.by.alex.parking.bean.request.SearchPlaceNowRequest;
import com.by.alex.parking.command.Command;
import com.by.alex.parking.command.exeption.CommandException;
import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.service.ServiceFactory;
import com.by.alex.parking.service.exception.ServiceException;

public class SearchPlace implements Command {

    String weelType;
    int id;
    String startTime;
    String duration;
    Response resp;
    ParkingPlace parkingPlace;

    @Override
    public Response execute(Request request) throws CommandException {

	SearchPlaceNowRequest req;

	if (request instanceof SearchPlaceNowRequest) {
	    req = (SearchPlaceNowRequest) request;
	} else {
	    throw new CommandException("Wrong request");
	}
	resp = new Response();
	weelType = req.getWeelsType();
	startTime = req.getStartTime();
	duration = req.getDoubleDuration();
	try {
	    parkingPlace = ServiceFactory.getInstance().getUserService().searchPlaceNow(weelType, startTime, duration);
	} catch (ServiceException e) {
	    throw new CommandException(e.getMessage());
	}
	if (parkingPlace == null) {
	    resp.setErrorStatus(true);
	    resp.setErrorMessage("Service Not Working! Sorry For This trouble\n");
	    return resp;
	}

	resp.setErrorStatus(false);
	resp.setResultMessage("You can locate your " + weelType.toUpperCase() + " on place with ID:'"
	        + parkingPlace.getPlaceId() + ". Your UnicNumber is: " + parkingPlace.getWeelType().getId()
	        + "' .\nPls take it back after " + duration + " hours. \nYour Price is : "
	        + Integer.parseInt(duration.split(":")[0]) * 2 + "$ . Hope You Enjoyed Over Service\n");
	return resp;
    }

}
