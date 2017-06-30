package com.by.alex.parking.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class WorkWithDate {

    public static String getEndTime(String startTime, String duration) {
	String endTime;
	endTime = (Integer.parseInt(startTime.split(":")[0]) + Integer.parseInt(duration.split(":")[0])) + ":"
	        + startTime.split(":")[1];
	if (CheckDateFormat.validate(endTime))
	    return endTime;
	throw new RuntimeException("Our Service working only with today time");
    }

    public static String getCurrentTime() {
	String fullTime = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
	return fullTime.split(":")[0] + ":00";
    }

    public static boolean isFreeTime(String startTime, String endTime, List<TimeHolder> tHolderList) {
	startTime = startTime.split(":")[0];
	int startT = Integer.parseInt(startTime);
	int endT = Integer.parseInt(endTime.split(":")[0]);
	for (TimeHolder tHolder : tHolderList) {
	    int startSQL = Integer.parseInt(tHolder.getStartTime().split(":")[0]);
	    int endSQL = Integer.parseInt(tHolder.getEndTime().split(":")[0]);

	    if (startT >= startSQL && startT < endSQL) {
		return false;
	    } else if (startT < startSQL && endT > startSQL) {
		return false;
	    }
	}
	return true;
    }

    public static List<String> sortTime(List<String> startTimeInSlotList) {
	startTimeInSlotList.sort(new Comparator<String>() {

	    @Override
	    public int compare(String o1, String o2) {
		int start = Integer.parseInt(o1.substring(0, 2));
		int end = Integer.parseInt(o2.substring(0, 2));
		return start - end;
	    }
	});
	return startTimeInSlotList;
    }

}
