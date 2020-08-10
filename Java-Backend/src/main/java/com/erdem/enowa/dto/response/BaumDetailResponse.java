package com.erdem.enowa.dto.response;

import java.sql.Date;

import com.erdem.enowa.dto.SpeziesDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaumDetailResponse {
	
	private long nummer;
	
	private Date pflanzdatum;
	
	private SpeziesDTO spezies;
	
}