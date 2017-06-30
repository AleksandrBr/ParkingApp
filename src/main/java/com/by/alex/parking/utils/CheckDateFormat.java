package com.by.alex.parking.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckDateFormat {
    private static Pattern pattern;
    private static Matcher matcher;
    private final static String TIME_FORMAT_PATTERN = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

    public static boolean validate(String time) {
	pattern = Pattern.compile(TIME_FORMAT_PATTERN);
	matcher = pattern.matcher(time);
	return matcher.matches() || time.equals("24:00");
    }
}
