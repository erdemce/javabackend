package com.erdem.enowa.customexception;

import org.hibernate.mapping.Constraint;

import com.erdem.enowa.constraints.Constraints;

public class BaumNotFoundException extends RuntimeException {

	public BaumNotFoundException() {
		super(Constraints.BAUMNOTFOUND);
	
	
	}
	
	
}
