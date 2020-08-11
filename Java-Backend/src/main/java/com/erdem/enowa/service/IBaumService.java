package com.erdem.enowa.service;

import java.util.List;

import com.erdem.enowa.dto.BaumDetaillDTO;
import com.erdem.enowa.dto.BefundDTO;

public interface IBaumService {

	
	public BaumDetaillDTO getDetailsdesBaumes(int baumId);
	
	public List<BefundDTO> getAllBefundeDesBaumes(int baumId);
}


