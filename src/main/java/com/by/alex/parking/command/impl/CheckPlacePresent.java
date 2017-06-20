package com.by.alex.parking.command.impl;

import com.by.alex.parking.bean.Request;
import com.by.alex.parking.bean.Response;
import com.by.alex.parking.command.Command;
import com.by.alex.parking.command.exeption.CommandException;
import com.by.alex.parking.service.ServiceFactory;

public class CheckPlacePresent implements Command {
	private Response res;
	private double restOfPlace;

	@Override
	public Response execute(Request req) throws CommandException {
		res = new Response();
		//restOfPlace = ServiceFactory.getInstance().getUserService().takeLeftPlaces();
		res.setRestOfPlaces(restOfPlace);
		res.setErrorStatus(false);
		if(restOfPlace == 0.5)
			res.setResultMessage("Seems like we have only one place for Motocycle. Other Places are Hold yet\n");
		if(restOfPlace == 0.0){
			res.setResultMessage("Seems like we have no more places\n");	
			return res;}
		res.setResultMessage("We cal locate '" + restOfPlace + "' WeelTypes\n");
		if(res == null){
			res.setErrorStatus(true);
			res.setErrorMessage("Sorry seems like our service is offline. PLS try one more time!\n");
		}
		return res;
	}

}
