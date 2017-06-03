package com.by.alex.parking.entity.place;

import com.by.alex.parking.entity.AbstractWeels;

public class ParkingPlace {
	
	private AbstractWeels weelType;
	private String placeId;
	
	

	public ParkingPlace(AbstractWeels weelType, String placeId) {
		super();
		this.weelType = weelType;
		this.placeId = placeId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public AbstractWeels getWeelType() {
		return weelType;
	}

	public void setWeelType(AbstractWeels weelType) {
		this.weelType = weelType;
	}
	
}
