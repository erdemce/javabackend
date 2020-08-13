package com.erdem.enowa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erdem.enowa.constraints.Constraints;
import com.erdem.enowa.datagenerator.SpeziesGenerator;
import com.erdem.enowa.repo.SpeziesRepo;
import com.erdem.enowa.service.ISpeziesService;

@Service
public class SpeziesServiceImpl implements ISpeziesService{
	
	@Autowired
	SpeziesRepo speziesRepo;
	
	@Autowired
	SpeziesGenerator speziesGenerator;
	
	@Override
	public void saveFehlendeSpezies() {

		long fehlendeAnzahl=Constraints.ANZAHLDERSPEZIES-speziesRepo.count();
		
		if(fehlendeAnzahl>0) {
			speziesRepo.saveAll(speziesGenerator.generateSpezies(((int)fehlendeAnzahl)));
		}
	}

}
