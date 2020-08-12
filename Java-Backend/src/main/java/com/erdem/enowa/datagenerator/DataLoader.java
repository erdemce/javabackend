package com.erdem.enowa.datagenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private Datagenarator datagenerator;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		if(!datagenerator.ueberpruefenDieAnzahlderStaedte()) {
			
			datagenerator.saveAllSpezies();
			System.out.println("spezies bitti");
			datagenerator.saveStaedtewithStrassen();
			System.out.println("staedtewithstrasse  bitti");
			datagenerator.saveBaeumeInAllenStaedten();
			System.out.println("kayıt bitti");
			
		}
		
			
		}	
	}
	

