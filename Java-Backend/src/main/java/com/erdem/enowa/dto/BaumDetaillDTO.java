package com.erdem.enowa.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaumDetaillDTO {
	
	private long nummer;
	
	private Date pflanzdatum;
	
	private SpeziesDTO spezies;
	
}