package com.by.alex.parking.bean;

public class Response {

	private boolean errorStatus;
	private String resultMessage;
	private String errorMessage;
	private double restOfPlaces;

	public double getRestOfPlaces() {
		return restOfPlaces;
	}

	public void setRestOfPlaces(double restOfPlaces) {
		this.restOfPlaces = restOfPlaces;
	}

	public boolean isErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
