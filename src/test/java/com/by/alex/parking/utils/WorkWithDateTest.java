package com.by.alex.parking.utils;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class WorkWithDateTest {

	private static final String START_TIME_VALID = "16:00";
	private static final String DURATION = "01:00";
	private static final String START_TIME_NEGATIV = "23:00";
	private static final String EXCPECTED_END_TIME = "17:00";
	
	
	@Test(description = "Check that times plusing correct")
	public void positiveTest(){
		String actual = WorkWithDate.getEndTime(START_TIME_VALID, DURATION);
		assertEquals(actual, EXCPECTED_END_TIME, "Expected 18:15 here, actual : " + actual);
	}
	
	@Test(expectedExceptions = RuntimeException.class)
	public void negativeTest(){
		String exeption = WorkWithDate.getEndTime(START_TIME_NEGATIV, DURATION);
	}
}
