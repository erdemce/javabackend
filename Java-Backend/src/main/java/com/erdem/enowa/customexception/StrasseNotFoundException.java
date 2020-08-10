package com.erdem.enowa.customexception;

import com.erdem.enowa.constraints.Constraints;

public class StrasseNotFoundException extends RuntimeException {

	public StrasseNotFoundException() {
		super(Constraints.STRASSENOTFOUND);
	
	
	}}