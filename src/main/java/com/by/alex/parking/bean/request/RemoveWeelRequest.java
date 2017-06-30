package com.by.alex.parking.bean.request;

import com.by.alex.parking.bean.Request;

public class RemoveWeelRequest extends Request {

    private int placeID;

    public int getPlaceID() {
	return placeID;
    }

    public void setPlaceID(int placeID) {
	this.placeID = placeID;
    }

}
