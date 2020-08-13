package com.erdem.enowa.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erdem.enowa.constraints.Constraints;
import com.erdem.enowa.datagenerator.StadtGenerator;
import com.erdem.enowa.entity.Stadt;
import com.erdem.enowa.repo.StadtRepo;
import com.erdem.enowa.service.IStadtService;

@Service
public class StadtServiceImpl implements IStadtService{
	
	@Autowired
	StadtRepo stadtRepo;
	
	@Autowired
	StadtGenerator stadtGenerator;
	
	@Override
	public List<Stadt> saveFehlendeStaedte() {
		System.out.println(stadtRepo.count());

		long fehlendeAnzahl=Constraints.ANZAHLDERSTAEDTE-stadtRepo.count();
		List<Stadt> stadtliste=new ArrayList<>();
				
		
		if(fehlendeAnzahl>0) {
			stadtliste=stadtRepo.saveAll(stadtGenerator.generateStaedtewithStrassen(((int)fehlendeAnzahl)));
		}
		return stadtliste;
	}

}
