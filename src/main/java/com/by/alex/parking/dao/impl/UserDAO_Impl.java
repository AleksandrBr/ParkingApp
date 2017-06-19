package com.by.alex.parking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.by.alex.parking.dao.UserDAO;
import com.by.alex.parking.dao.pool.ConnectionPool;
import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.utils.TimeHolder;

public class UserDAO_Impl implements UserDAO{
	
	private ResultSet resultSet;
	private Connection con;
	private Statement st;
	private PreparedStatement prepare;
	private int result;
	private final String takePlaceQUery = "INSERT INTO parking_place_holder(place_name, start_time, durtion, ent_time, weel_id) VALUES(?, ?, ?, ?, ?);";

	@Override
	public int takePlaceInParking(ParkingPlace parkingPlace, String end_time) {
		try {
			con = ConnectionPool.getInstance().getConnection();
			prepare = con.prepareStatement(takePlaceQUery);
			prepare.setString(1, parkingPlace.getPlaceId());
			prepare.setString(2, parkingPlace.getWeelType().getStartTime());
			prepare.setString(3, parkingPlace.getWeelType().getDuration());
			prepare.setString(4, end_time);
			prepare.setInt(5, parkingPlace.getWeelType().getId());
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
		finally{
			try {
				ConnectionPool.getInstance().returnConnection(con);
				con.close();
			} catch (SQLException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
		
	}

	@Override
	public void removeWeels() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TimeHolder> showFreeTime(String placeID) {
		List<TimeHolder> timeHolderList = new ArrayList<TimeHolder>();
		TimeHolder timeHolder;
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			resultSet = st.executeQuery("Select start_time , ent_time FROM parkingappdb.parking_place_holder where place_name = '"+placeID+"'");
			while(resultSet.next()){
				timeHolder = new TimeHolder();
				timeHolder.setStartTime(resultSet.getString("start_time"));
				timeHolder.setEndTime(resultSet.getString("ent_time"));
				timeHolderList.add(timeHolder);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeHolderList;
	}

	@Override
	public void addTimeToParkinPlace() {
		// TODO Auto-generated method stub
		
	}

}
