package com.by.alex.parking.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.by.alex.parking.dao.UserDAO;
import com.by.alex.parking.dao.exception.DAOException;
import com.by.alex.parking.dao.pool.ConnectionPool;
import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.utils.TimeHolder;
import com.by.alex.parking.utils.WorkWithDate;

public class UserDAO_Impl implements UserDAO {

    private ResultSet resultSet;
    private Connection con;
    private Statement st;
    private PreparedStatement prepare;
    private int result;
    private final String takePlaceQUery = "INSERT INTO parking_place_holder(place_name, start_time, durtion, end_time, weel_id) VALUES(?, ?, ?, ?, ?);";

    @Override
    public int takePlaceInParking(ParkingPlace parkingPlace, String end_time) throws DAOException {
	try {
	    con = ConnectionPool.getInstance().getConnection();
	    prepare = con.prepareStatement(takePlaceQUery);
	    prepare.setString(1, parkingPlace.getPlaceId());
	    prepare.setString(2, parkingPlace.getWeelType().getStartTime());
	    prepare.setString(3, parkingPlace.getWeelType().getDuration());
	    prepare.setString(4, end_time);
	    prepare.setInt(5, parkingPlace.getWeelType().getId());
	    result = prepare.executeUpdate();
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    ConnectionPool.getInstance().returnConnection(con);
	}
	return result;

    }

    @Override
    public boolean removeWeels(String placeID, int weel_id) throws DAOException {
	String deleteQuery = String.format("DELETE FROM parking_place_holder WHERE place_name = '%s' and weel_id='%s'",
	        placeID, weel_id);
	try {
	    con = ConnectionPool.getInstance().getConnection();
	    st = con.createStatement();
	    return st.executeUpdate(deleteQuery) != 0;
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    ConnectionPool.getInstance().returnConnection(con);
	}
	return false;
    }

    @Override
    public List<TimeHolder> showFreeTime(String placeID) throws DAOException {
	List<TimeHolder> timeHolderList = new ArrayList<TimeHolder>();
	TimeHolder timeHolder;
	try {
	    con = ConnectionPool.getInstance().getConnection();
	    st = con.createStatement();
	    resultSet = st.executeQuery(
	            "Select start_time , end_time FROM parkingappdb.parking_place_holder where place_name = '" + placeID
	                    + "'");
	    while (resultSet.next()) {
		timeHolder = new TimeHolder();
		timeHolder.setStartTime(resultSet.getString("start_time"));
		timeHolder.setEndTime(resultSet.getString("end_time"));
		timeHolderList.add(timeHolder);
	    }

	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    ConnectionPool.getInstance().returnConnection(con);
	}
	return timeHolderList;
    }

    @Override
    public void addTimeToParkinPlace() throws DAOException {
	// TODO Auto-generated method stub

    }

    @Override
    public String getPlaceInfoByID(int weelID) throws DAOException {
	String placeIDList = null;
	try {
	    con = ConnectionPool.getInstance().getConnection();
	    st = con.createStatement();
	    resultSet = st
	            .executeQuery(String.format("SELECT * FROM parking_place_holder where weel_id = '%s'", weelID));
	    resultSet.next();
	    placeIDList = resultSet.getString("place_name");

	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return placeIDList;
    }

    @Override
    public List<String> getSchedule() throws DAOException {
	Map<String, String> placeScheduleMap = new HashMap<String, String>();
	List<String> startTimeInSlotList = new LinkedList<String>();
	List<String> freeTime = new LinkedList<String>();
	try {
	    con = ConnectionPool.getInstance().getConnection();
	    st = con.createStatement();
	    resultSet = st.executeQuery("SELECT start_time , end_time FROM parking_place_holder");
	    if (!resultSet.next()) {
		return freeTime;
	    }
	    do {
		String key = resultSet.getString("start_time");
		String value = resultSet.getString("end_time");
		startTimeInSlotList.add(key);
		placeScheduleMap.put(key, value);
	    } while (resultSet.next());
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	startTimeInSlotList = WorkWithDate.sortTime(startTimeInSlotList);
	if (!"00:00".equals(startTimeInSlotList.get(0))) {
	    freeTime.add("00:00");
	    freeTime.add(startTimeInSlotList.get(0));
	}
	for (int i = 0; i < startTimeInSlotList.size() - 1; i++) {
	    String key = placeScheduleMap.get(startTimeInSlotList.get(i));
	    String nextValue = startTimeInSlotList.get(i + 1);
	    if (key.equals(nextValue)) {
		continue;
	    }
	    freeTime.add(key);
	    freeTime.add(nextValue);
	}
	if (!"24:00".equals(placeScheduleMap.get(startTimeInSlotList.get(startTimeInSlotList.size() - 1)))) {
	    freeTime.add(placeScheduleMap.get(startTimeInSlotList.get(startTimeInSlotList.size() - 1)));
	    freeTime.add("24:00");
	}
	if (freeTime.isEmpty()) {
	    throw new DAOException("Sorry but All of the places Are Host. Come back later");
	}
	return freeTime;
    }

}
