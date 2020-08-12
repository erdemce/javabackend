package com.erdem.enowa.customexception;


import com.erdem.enowa.constraints.Constraints;

public class BaumNotFoundException extends RuntimeException {

	public BaumNotFoundException() {
		super(Constraints.BAUMNOTFOUND);
	
	
	}
	
	
}