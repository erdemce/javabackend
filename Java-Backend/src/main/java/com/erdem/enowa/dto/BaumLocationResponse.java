package com.erdem.enowa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaumLocationResponse {
	
	private int baumId;
	
	private GeoLocationDTO location;

}
