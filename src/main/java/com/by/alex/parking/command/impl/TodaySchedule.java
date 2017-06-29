package com.by.alex.parking.command.impl;

import com.by.alex.parking.bean.Request;
import com.by.alex.parking.bean.Response;
import com.by.alex.parking.bean.request.TodayScheduleRequest;
import com.by.alex.parking.command.Command;
import com.by.alex.parking.command.exeption.CommandException;
import com.by.alex.parking.service.ServiceFactory;
import com.by.alex.parking.service.exception.ServiceException;

public class TodaySchedule implements Command{

	@Override
	public Response execute(Request request) throws CommandException {

		TodayScheduleRequest req;
		Response resp;
		
		if (request instanceof TodayScheduleRequest) {
			req = (TodayScheduleRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}
		resp = new Response();
		try {
			String informMsg = ServiceFactory.getInstance().getUserService().getSchedule();
			resp.setErrorStatus(false);
			resp.setResultMessage(informMsg);
		} catch (ServiceException e) {
			resp.setErrorStatus(true);
			resp.setErrorMessage(e.getLocalizedMessage());
		}
		
		return resp;
	}

}
