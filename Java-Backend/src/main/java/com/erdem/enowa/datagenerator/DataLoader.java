package com.erdem.enowa.datagenerator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.erdem.enowa.entity.Stadt;
import com.erdem.enowa.service.ISpeziesService;
import com.erdem.enowa.service.IStadtService;
import com.erdem.enowa.service.IStrasseService;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private ISpeziesService speziesService;
	
	@Autowired
	private IStadtService stadtService;
	
	@Autowired
	private IStrasseService strasseService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
			speziesService.saveFehlendeSpezies();
			List<Stadt>neueStadtliste=stadtService.saveFehlendeStaedte();
			
			for (Stadt stadt : neueStadtliste) {
				strasseService.saveStrassewithBaeumen(stadt);
				System.out.println(stadt.getId()+"stadt bitti");
			}
		
	}
}

