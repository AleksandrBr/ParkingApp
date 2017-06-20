package com.by.alex.parking.utils;

import static org.testng.Assert.*;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.by.alex.parking.dao.UserDAO;
import com.by.alex.parking.dao.factory.DAOFactory;
import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.factory.FactoryWeels;

public class DataBaseTest {

	private ParkingPlace pPlace;
	private UserDAO userDAO;
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		pPlace = new ParkingPlace(FactoryWeels.getWeels("CAR", "12:00", "02:00"), "1");
		userDAO = DAOFactory.getInstance().getUserDAO();
	}
	
	@AfterClass(alwaysRun = true)
	public void teerDown(){
		pPlace = null;
		userDAO = null;
	}
	
	@Test
	public void test(){
		assertTrue(userDAO.takePlaceInParking(pPlace, "11:00")!=0, "Verify That count of Changed rows more than 0");
	}
	
	@Test(dependsOnMethods = "test")
	public void test2(){
		List<TimeHolder> timeHList = userDAO.showFreeTime("1");
		for(TimeHolder tHolder : timeHList){
			System.out.println(tHolder);
		}
		assertTrue(WorkWithDate.isFreeTime("08:00", "10:00", timeHList));
		assertFalse(WorkWithDate.isFreeTime("08:00", "12:00", timeHList));
	}
	
	@Test(dependsOnMethods = "test2")
	public void test_DeleteWeelsFromParking(){
		assertTrue(userDAO.removeWeels(pPlace.getPlaceId(), pPlace.getWeelType().getId()), "Verify Deletion");
	}
}
