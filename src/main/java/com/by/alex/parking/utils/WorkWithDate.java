package com.by.alex.parking.utils;

public class WorkWithDate {
	private static String endTime;

	public static String getEndTime(String startTime, String duration) {
		endTime = (Integer.parseInt(startTime.split(":")[0]) + Integer.parseInt(duration.split(":")[0])) + ":"
				+ (Integer.parseInt(startTime.split(":")[1]) + Integer.parseInt(duration.split(":")[1]));
		if (CheckDateFormat.validate(endTime))
		return endTime;
		throw new RuntimeException("Our Service working only with today time");
	}

}
