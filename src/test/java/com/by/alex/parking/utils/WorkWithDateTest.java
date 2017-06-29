package com.by.alex.parking.utils;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkWithDateTest {

	private static final String START_TIME_VALID = "16:00";
	private static final String DURATION = "01:00";
	private static final String START_TIME_NEGATIV = "23:00";
	private static final String EXCPECTED_END_TIME = "17:00";
	
	
	//@Test(description = "Check that times plusing correct")
	public void positiveTest(){
		String actual = WorkWithDate.getEndTime(START_TIME_VALID, DURATION);
		assertEquals(actual, EXCPECTED_END_TIME, "Expected 18:15 here, actual : " + actual);
	}
	
	//@Test(expectedExceptions = RuntimeException.class)
	public void negativeTest(){
		String exeption = WorkWithDate.getEndTime(START_TIME_NEGATIV, DURATION);
	}
	
	@Test
	public void comporatorValidationTest(){
		List<String> expected = new LinkedList<String>(Arrays.asList(new String [] {"11:00","12:00","13:00","23:00"}));
		List<String> actual = new LinkedList<String>(Arrays.asList(new String [] {"23:00","12:00","11:00","13:00"}));
		WorkWithDate.sortTime(actual);
		Assert.assertTrue(expected.equals(actual));
	}
}
