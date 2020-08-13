package com.erdem.enowa.service;

import java.util.List;

import com.erdem.enowa.dto.BaumLocationResponse;
import com.erdem.enowa.entity.Stadt;
import com.erdem.enowa.entity.Strasse;

public interface IStrasseService {
	
	public List<BaumLocationResponse> getAllBaumLocationInStrasse (int strasseId);
	
	public void saveStrassewithBaeumen(Stadt stadt);
	
}

