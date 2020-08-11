package com.erdem.enowa.datagenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.erdem.enowa.constraints.Constraints;
import com.erdem.enowa.repo.SpeziesRepo;
import com.erdem.enowa.repo.StadtRepo;



@Component
public class DataLoader implements ApplicationRunner {
	
	private StadtRepo stadtRepo;
	
	private SpeziesRepo speziesRepo;
	
	private Datagenarator datagenerator;
	
	
	@Autowired
	public DataLoader(StadtRepo stadtRepo, SpeziesRepo speziesRepo, Datagenarator datagenerator) {

		this.stadtRepo = stadtRepo;
		this.speziesRepo = speziesRepo;
		this.datagenerator = datagenerator;
	}



	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		speziesRepo.saveAll(datagenerator.getAllRndmSpezies());
		for (int i = 0; i < Constraints.ZAHLDERSTAEDTE; i++) {
			stadtRepo.save(datagenerator.generateStadt());
			System.out.println(i+". Stadt speichert");
		}
		
	}
	/*
	 * TODO
	 * data kontrol
	 * data yukleme sekil
	 * sabitler
	 * dokumentasyon
	 */

}
