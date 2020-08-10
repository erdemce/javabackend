package com.erdem.enowa.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StrasseDTO {
	
	private int id;
	
	private String verwaltungsKuerzel;
	
	private String name;

	private List<BaumDTO> baeume;

}
