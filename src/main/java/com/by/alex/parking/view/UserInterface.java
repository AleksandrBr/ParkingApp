package com.by.alex.parking.view;

import java.util.Scanner;

import com.by.alex.parking.bean.Request;
import com.by.alex.parking.bean.Response;
import com.by.alex.parking.bean.request.RemoveWeelRequest;
import com.by.alex.parking.bean.request.SearchPlaceNowRequest;
import com.by.alex.parking.bean.request.TodayScheduleRequest;
import com.by.alex.parking.command.Command;
import com.by.alex.parking.command.exeption.CommandException;
import com.by.alex.parking.command.impl.RemoveWeel;
import com.by.alex.parking.command.impl.SearchPlace;
import com.by.alex.parking.command.impl.TodaySchedule;
import com.by.alex.parking.dao.pool.ConnectionPool;

public class UserInterface {

    private static final String menu = "Hi take your choise: \n1. Search for place\n2. Get Your Weels Back\n3. GetSchedule For Today\n0. Exit";
    private boolean power = true;
    private int choiseType;
    private int un;
    private String stringChoise;
    private Scanner in = new Scanner(System.in);
    private Request req;
    private Response resp;
    private Command command;

    public void run() {

	while (power) {
	    System.out.println(menu);
	    choiseType = in.nextInt();
	    switch (choiseType) {

	    case 1:
		choiseForSearchPlaceNow();
		break;
	    case 2:
		choiseForGetWeelBack();
		break;
	    case 3:
		choiseForGetSchedule();
		break;
	    default:
		closeApp();
		break;
	    }
	}
    }

    private void choiseForSearchPlaceNow() {
	boolean tempCycle = true;
	req = new SearchPlaceNowRequest();
	command = new SearchPlace();
	System.out.println("OK! Take Your Weel Type:\n1.Car\n2.Moto");
	while (tempCycle) {
	    System.out.println("OK! Take Your Weel Type:\n1.Car\n2.Moto\n0.Back to Main Menu");
	    choiseType = in.nextInt();
	    switch (choiseType) {
	    case 1:
		req.setWeelsType("CAR");
		tempCycle = false;
		break;
	    case 2:
		req.setWeelsType("MOTOCYCLE");
		tempCycle = false;
		break;
	    case 0:
		System.out.println("Go To Main Menu");
		tempCycle = false;
		break;
	    default:
		System.out.println(
		        "You make a wrong choise! Pls Try Again...\ntake your choise: \n1. Search for place\n0. Exit");
		break;
	    }
	}
	System.out.println("Now Set Your start Time in 'hh' format(from 00 to 24)");
	while (!tempCycle) {
	    stringChoise = in.next();
	    if (0 <= Integer.parseInt(stringChoise) && Integer.parseInt(stringChoise) <= 24) {
		req.setStartTime(stringChoise + ":00");
		tempCycle = true;
	    } else {
		System.out.println("Wrong Ttpe Of Time!!!!!");
		System.out.println("Pls Set Your start Time in 'hh' format(from 00 to 24)");
	    }
	}
	System.out.println("Now Set the duration Time in 'hh' format(from 00 to 24)");
	while (tempCycle) {
	    stringChoise = in.next();
	    if (0 <= Integer.parseInt(stringChoise) && Integer.parseInt(stringChoise) <= 24) {
		req.setDoubleDuration(stringChoise + ":00");
		tempCycle = false;
	    } else {
		System.out.println("Wrong Ttpe Of Time!!!!!");
		System.out.println("Pls Set Your duration Time in 'hh' format(from 00 to 24)");
	    }
	}
	try {
	    resp = command.execute(req);
	    if (resp.isErrorStatus()) {
		System.out.println(resp.getErrorMessage());
	    } else {
		System.out.println(resp.getResultMessage());
	    }
	} catch (CommandException e) {
	    System.out.println(e.getLocalizedMessage());
	}
    }

    private void choiseForGetWeelBack() {
	RemoveWeelRequest request = new RemoveWeelRequest();
	System.out.println("OK! Pls Write your UnicNuber place here :");
	un = in.nextInt();
	request.setPlaceID(un);
	command = new RemoveWeel();
	try {
	    resp = command.execute(request);
	    if (resp.isErrorStatus()) {
		System.out.println(resp.getErrorMessage());
		return;
	    }
	    System.out.println(resp.getResultMessage());
	    return;
	} catch (CommandException e) {
	    System.out.println(e.getLocalizedMessage());
	    return;
	}
    }

    private void choiseForGetSchedule() {
	req = new TodayScheduleRequest();
	command = new TodaySchedule();
	System.out.println("Ok!\nPls Wait Your Request is on Progress....");
	try {
	    resp = command.execute(req);
	    if (resp.isErrorStatus()) {
		System.out.println(resp.getErrorMessage());
		return;
	    }
	    System.out.println(resp.getResultMessage());
	    return;
	} catch (CommandException e) {
	    System.out.println(e.getLocalizedMessage());
	    return;
	}
    }

    private void closeApp() {
	in.close();
	ConnectionPool.getInstance().closePool();
	in.close();
	power = false;
    }
}