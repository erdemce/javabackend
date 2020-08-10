package com.erdem.enowa.service;

import java.util.List;

import com.erdem.enowa.dto.BefundDTO;
import com.erdem.enowa.dto.response.BaumDetailResponse;
import com.erdem.enowa.dto.response.BaumLocationResponse;
import com.erdem.enowa.dto.response.BefundResponse;
import com.erdem.enowa.entity.Befund;

public interface MeinServiceInterface {
	
	public List<BaumLocationResponse> getAllBaumLocationInStrasse (int strasseId);
	
	public List<BefundResponse> getAllBefundeDesBaumes(int baumId);
	
	public BaumDetailResponse getDetailsdesBaumes(int baumId);
}
