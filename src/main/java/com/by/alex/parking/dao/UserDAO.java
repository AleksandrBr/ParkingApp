package com.by.alex.parking.dao;

import java.util.List;

import com.by.alex.parking.dao.exception.DAOException;
import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.utils.TimeHolder;

public interface UserDAO {

    public int takePlaceInParking(ParkingPlace parkingPlace, String end_time) throws DAOException;

    public boolean removeWeels(String placeID, int weel_id) throws DAOException;

    public List<TimeHolder> showFreeTime(String placeID) throws DAOException;

    public void addTimeToParkinPlace() throws DAOException;

    public String getPlaceInfoByID(int weelID) throws DAOException;

    public List<String> getSchedule() throws DAOException;
}
