package com.erdem.enowa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erdem.enowa.customexception.StrasseNotFoundException;
import com.erdem.enowa.dto.BaumLocationResponse;
import com.erdem.enowa.entity.Strasse;
import com.erdem.enowa.repo.StrasseRepo;
import com.erdem.enowa.util.Konverter;

@Service
public class StrasseServiceImpl implements IStrasseService{
	
	@Autowired
	StrasseRepo strasseRepo;
	
	@Autowired
	Konverter konverter;
	
	
	@Override
	public List<BaumLocationResponse> getAllBaumLocationInStrasse(int strasseId) {
		
		Strasse strasse=strasseRepo.findById(strasseId).orElseThrow(()->new StrasseNotFoundException());
		
		return konverter.strasseZuBaumLocationListKonverter(strasse);
	}


}
