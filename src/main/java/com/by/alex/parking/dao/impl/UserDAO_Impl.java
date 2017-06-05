package com.by.alex.parking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.by.alex.parking.dao.UserDAO;
import com.by.alex.parking.dao.pool.ConnectionPool;
import com.by.alex.parking.entity.place.ParkingPlace;

public class UserDAO_Impl implements UserDAO{
	
	private Connection con;
	private Statement st;
	private PreparedStatement prepare;
	private int result;
	private String takePlaceQUery = "INSERT INTO parking_place_holder(place_name, start_time, durtion, ent_time, weel_id) VALUES('?', '?', '?', '?', '?');";

	@Override
	public void takePlaceInParking(ParkingPlace parkinPlace) {
		try {
			con = ConnectionPool.getInstance().getConnection();
			prepare = con.prepareStatement(takePlaceQUery);
			prepare.setString(1, parkinPlace.getPlaceId());
			/*
			 * 
			 * 
			 * 
			 * 
			 */
			result = prepare.executeUpdate();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeWeels() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showFreeTime() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTimeToParkinPlace() {
		// TODO Auto-generated method stub
		
	}

}
