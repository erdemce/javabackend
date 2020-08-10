package com.erdem.enowa.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StadtDTO {
	
	private int id;
	
	private String plz;
	
	private String name;
	
	private List<StrasseDTO> strassen;

}
