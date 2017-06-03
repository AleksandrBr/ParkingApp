package com.by.alex.parking.factory;

import javax.management.RuntimeErrorException;

import com.by.alex.parking.entity.AbstractWeels;
import com.by.alex.parking.entity.weels.Car;
import com.by.alex.parking.entity.weels.Motocycle;

public class FactoryWeels {
		
	public AbstractWeels getWeels(String weelType,int id,double startTime,double duration){
	//	int id = weelType.getId();
	//	double startTime = weelType.getStartTime();
	//	double duration = weelType.getDoubleDuration();	
		
		if(weelType.equalsIgnoreCase("MOTOCYCLE")){
			return new Motocycle(id, startTime, duration);
		}
		else if(weelType.equalsIgnoreCase("CAR")){
			return new Car(id, startTime, duration);
		}
		throw new RuntimeErrorException(null, "Sorry man.. But We don't keep " + weelType.toUpperCase() + " in our parking yet");
	}
}
