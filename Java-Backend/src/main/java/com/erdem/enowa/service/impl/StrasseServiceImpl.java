package com.erdem.enowa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erdem.enowa.customexception.StrasseNotFoundException;
import com.erdem.enowa.datagenerator.BaumGenerator;
import com.erdem.enowa.dto.BaumLocationResponse;
import com.erdem.enowa.entity.Stadt;
import com.erdem.enowa.entity.Strasse;
import com.erdem.enowa.repo.SpeziesRepo;
import com.erdem.enowa.repo.StrasseRepo;
import com.erdem.enowa.service.IStrasseService;
import com.erdem.enowa.util.Konverter;

@Service
public class StrasseServiceImpl implements IStrasseService{
	
	@Autowired
	StrasseRepo strasseRepo;
	
	@Autowired
	SpeziesRepo speziesRepo;
	
	@Autowired
	Konverter konverter;
	
	@Autowired
	BaumGenerator baumgenerator;
	
	
	@Override
	public List<BaumLocationResponse> getAllBaumLocationInStrasse(int strasseId) {
		
		Strasse strasse=strasseRepo.findById(strasseId).orElseThrow(()->new StrasseNotFoundException());
		
		return konverter.strasseZuBaumLocationListKonverter(strasse);
	}
	
	
	@Override
	public void saveStrassewithBaeumen(Stadt stadt) {
		
		baumgenerator.generateBaeumefuerStadt(stadt, speziesRepo.findAll());
		
		for (Strasse strasse : stadt.getStrassen()) {
			strasseRepo.save(strasse);
		}
		
	}


}
