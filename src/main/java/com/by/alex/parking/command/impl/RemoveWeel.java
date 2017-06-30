package com.by.alex.parking.command.impl;

import java.util.Arrays;

import com.by.alex.parking.bean.Request;
import com.by.alex.parking.bean.Response;
import com.by.alex.parking.bean.request.RemoveWeelRequest;
import com.by.alex.parking.command.Command;
import com.by.alex.parking.command.exeption.CommandException;
import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.service.ServiceFactory;
import com.by.alex.parking.service.exception.ServiceException;

public class RemoveWeel implements Command {
    private int weelID;
    private RemoveWeelRequest request;
    private ParkingPlace parkingPlace;
    private Response resp;

    @Override
    public Response execute(Request req) throws CommandException {

	if (req instanceof RemoveWeelRequest) {
	    request = (RemoveWeelRequest) req;
	} else {
	    throw new CommandException("Wrong request");
	}
	weelID = request.getPlaceID();
	resp = new Response();

	try {
	    parkingPlace = ServiceFactory.getInstance().getUserService().takeWeelsBack(weelID);
	    if (parkingPlace.getPlaceId() == null) {
		resp.setErrorStatus(true);
		resp.setErrorMessage("INTERNAL-ERROR! Servise BROKEN. Pls contact with ADMIN");
		return resp;
	    } else {
		resp.setErrorStatus(false);
		resp.setResultMessage("ATTENTION!\nThe :' " + parkingPlace.getWeelType()
		        + "' leaving the place with ID: '" + weelID + "'\nThanks For Using Our Service\nGood Luck!\n");
	    }
	} catch (ServiceException e) {
	    resp.setErrorStatus(true);
	    resp.setErrorMessage("INTERNAL-ERROR! Servise BROKEN. Pls contact with ADMIN" + "\n"
	            + Arrays.toString(e.getStackTrace()));
	}

	return resp;
    }

}
