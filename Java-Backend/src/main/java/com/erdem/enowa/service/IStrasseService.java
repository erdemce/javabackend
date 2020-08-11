package com.erdem.enowa.service;

import java.util.List;

import com.erdem.enowa.dto.BaumLocationResponse;

public interface IStrasseService {
	
	public List<BaumLocationResponse> getAllBaumLocationInStrasse (int strasseId);
	
}

