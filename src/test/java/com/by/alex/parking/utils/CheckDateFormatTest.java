package com.by.alex.parking.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckDateFormatTest {
    private final static String validTestData = "12:05";
    private final static String invalidTestData = "23:66";

    @Test(description = "checkDateFormatValidationPositive")
    public void checkDateFormatValidationPositive() {
	Assert.assertTrue(CheckDateFormat.validate(validTestData),
	        "Check that time '" + validTestData + "' is correct");

    }

    @Test(description = "checkDateFormatValidationNegative")
    public void checkDateFormatValidationNegative() {
	Assert.assertFalse(CheckDateFormat.validate(invalidTestData),
	        "Check that time '" + invalidTestData + "' is incorrect");

    }
}
