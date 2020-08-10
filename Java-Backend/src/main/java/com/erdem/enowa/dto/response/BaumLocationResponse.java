package com.erdem.enowa.dto.response;

import com.erdem.enowa.dto.GeoLocationDTO;
import com.erdem.enowa.entity.GeoLocation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaumLocationResponse {
	
	private int baumId;
	
	private GeoLocationDTO location;

}
