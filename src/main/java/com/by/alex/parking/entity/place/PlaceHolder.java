package com.by.alex.parking.entity.place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaceHolder {

    private List<ParkingPlace> carPlaceList = new ArrayList<ParkingPlace>();
    private List<ParkingPlace> motocyclePalceList = new ArrayList<ParkingPlace>();
    private static final PlaceHolder INSTANCE = new PlaceHolder();

    private PlaceHolder() {
    }

    public static PlaceHolder getInstance() {
	return INSTANCE;
    }

    public void takeCarPlace(ParkingPlace place) {
	carPlaceList.add(place);
    }

    public void goAwayCar(ParkingPlace place) {
	carPlaceList.remove(place);
    }

    public void takeMotoPlace(ParkingPlace place) {
	motocyclePalceList.add(place);
    }

    public void goAwayMoto(ParkingPlace place) {
	motocyclePalceList.remove(place);
    }

    public List<List<ParkingPlace>> getCurrentPlaceInformation() {
	return new ArrayList<List<ParkingPlace>>((Arrays.asList(carPlaceList, motocyclePalceList)));
    }

}
