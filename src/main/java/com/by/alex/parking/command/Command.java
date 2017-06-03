package com.by.alex.parking.command;

import com.by.alex.parking.bean.Request;
import com.by.alex.parking.bean.Response;
import com.by.alex.parking.command.exeption.CommandException;

public interface Command {
	
	Response execute(Request req) throws CommandException;
}
