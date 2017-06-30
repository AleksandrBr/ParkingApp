package com.by.alex.parking.utils;

import static org.testng.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.alex.parking.dao.UserDAO;
import com.by.alex.parking.dao.exception.DAOException;
import com.by.alex.parking.dao.factory.DAOFactory;
import com.by.alex.parking.dao.pool.ConnectionPool;
import com.by.alex.parking.entity.place.ParkingPlace;
import com.by.alex.parking.factory.FactoryWeels;

public class DataBaseTest {

    private ParkingPlace pPlace;
    private UserDAO userDAO;

    @BeforeClass(alwaysRun = true)
    public void setup() {
	pPlace = new ParkingPlace(FactoryWeels.getWeels("CAR", "12:00", "02:00"), "1");
	userDAO = DAOFactory.getInstance().getUserDAO();
    }

    @AfterClass(alwaysRun = true)
    public void teerDown() {
	ConnectionPool.getInstance().closePool();
	pPlace = null;
	userDAO = null;
    }

    @Test
    public void test() throws DAOException {
	assertTrue(userDAO.takePlaceInParking(pPlace, "13:00") != 0, "Verify That count of Changed rows more than 0");
    }

    @Test(dependsOnMethods = "test")
    public void test2() throws DAOException {
	List<TimeHolder> timeHList = userDAO.showFreeTime("1");
	for (TimeHolder tHolder : timeHList) {
	    System.out.println(tHolder);
	}
	assertTrue(WorkWithDate.isFreeTime("08:00", "10:00", timeHList));
	assertFalse(WorkWithDate.isFreeTime("08:00", "13:00", timeHList));
    }

    @Test(dependsOnMethods = "test2")
    public void test_DeleteWeelsFromParking() throws DAOException {
	assertTrue(userDAO.removeWeels(pPlace.getPlaceId(), pPlace.getWeelType().getId()), "Verify Deletion");
    }

    // Run only with clean DB
    // @Test(groups = "Smoke")
    public void snokeTest() throws DAOException {
	Assert.assertTrue(userDAO.getSchedule().isEmpty());
    }
}
