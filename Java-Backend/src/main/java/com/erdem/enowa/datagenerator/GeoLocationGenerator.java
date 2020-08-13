package com.erdem.enowa.datagenerator;

import org.springframework.stereotype.Component;

import com.erdem.enowa.entity.GeoLocation;

@Component
public class GeoLocationGenerator {
	
	
	/*
	 * um eine GeoLocation in der Welt zu erstellen
	 */
	public GeoLocation generateLocation() {
		
		double latitude = (Math.random() * 180.0) - 90.0;
		double longitude = (Math.random() * 360.0) - 180.0;
		GeoLocation location = new GeoLocation();
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		return location;
		
	}
	
	/*
	 * um einen naehen Koordinatenpunkt zu erstellen
	 */
	public GeoLocation generateLocation(GeoLocation startPunkt, double abstandLongitude, double abstandLatitude ) {

		GeoLocation location = new GeoLocation();
		double longitudeVonBeginStrasse = startPunkt.getLongitude() + abstandLongitude;
		double latitudeVonBeginStrasse = startPunkt.getLatitude() + abstandLatitude;
		location.setLongitude(longitudeVonBeginStrasse);
		location.setLatitude(latitudeVonBeginStrasse);
		return location;
	}
	
	

	

				

}
