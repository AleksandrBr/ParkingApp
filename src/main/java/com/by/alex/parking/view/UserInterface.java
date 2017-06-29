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
			System.out.println("Hi take your choise: \n1. Search for place\n2. Get Your Weels Back\n3. GetSchedule For Today\n0. Exit");
			choiseType = in.nextInt();

			switch (choiseType) {

			case 1:
				req = new SearchPlaceNowRequest();
				command = new SearchPlace();
				System.out.println("OK! Take Your Weel Type:\n1.Car\n2.Moto");
				choiseType = in.nextInt();
				switch (choiseType) {
					case 1: req.setWeelsType("CAR"); break;
					case 2: req.setWeelsType("MOTOCYCLE"); break;
					default: System.out.println("Hi take your choise: \n1. Search for place\n0. Exit");
					break;
				}
				req.setStartTime("16:00");
				req.setDoubleDuration("01:00");
				try {
					resp = command.execute(req);
					if(resp.isErrorStatus()){
						System.out.println(resp.getErrorMessage());
					}
					else{
						System.out.println(resp.getResultMessage());
					}
				} catch (CommandException e) {
					System.out.println(e.getLocalizedMessage());
				}
				break;
			case 2:
				RemoveWeelRequest request = new RemoveWeelRequest();
				System.out.println("OK! Pls Write your UnicNuber place here :");
				un = in.nextInt();
				request.setPlaceID(un);
				command = new RemoveWeel();
				try {
					resp = command.execute(request);
					if(resp.isErrorStatus()){
						System.out.println(resp.getErrorMessage());
						break;
					}
					System.out.println(resp.getResultMessage());
					break;
				} catch (CommandException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				}
				
			case 3:
				
				req = new TodayScheduleRequest();
				command = new TodaySchedule();
				System.out.println("Ok!\nPls Wait Your Request is on Progress....");
				try{
					resp = command.execute(req);
					if(resp.isErrorStatus()){
						System.out.println(resp.getErrorMessage());
						break;
					}
					System.out.println(resp.getResultMessage());
					break;
				}
				catch (CommandException e) {
					System.out.println(e.getLocalizedMessage());
					break;
				}				
			default:
				ConnectionPool.getInstance().closePool();
				in.close();
				power = false;
				break;
			}

		}
		in.close();

	}

}