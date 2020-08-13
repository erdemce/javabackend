package com.erdem.enowa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erdem.enowa.customexception.BaumNotFoundException;
import com.erdem.enowa.dto.BaumDetaillDTO;
import com.erdem.enowa.dto.BefundDTO;
import com.erdem.enowa.entity.Baum;
import com.erdem.enowa.repo.BaumRepo;
import com.erdem.enowa.service.IBaumService;
import com.erdem.enowa.util.Konverter;

@Service
public class BaumServiceImpl implements IBaumService {
	
	@Autowired
	BaumRepo baumRepo;
	
	@Autowired
	Konverter konverter;
	
	@Override
	public List<BefundDTO> getAllBefundeDesBaumes(int baumId) {
		Baum baum=baumRepo.findById(baumId).orElseThrow(()->new BaumNotFoundException());
		
		return konverter.baumZuBefundDTOListKonverter(baum);
		
	}
	
	@Override
	public BaumDetaillDTO getDetailsdesBaumes(int baumId) {
		Baum baum=baumRepo.findById(baumId).orElseThrow(()->new BaumNotFoundException());
		
		return konverter.baumZubaumDetailsKonverter(baum);
		
	}
}

