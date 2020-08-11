package com.erdem.enowa.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erdem.enowa.dto.BaumDetaillDTO;
import com.erdem.enowa.dto.BaumLocationResponse;
import com.erdem.enowa.dto.BefundDTO;
import com.erdem.enowa.dto.GeoLocationDTO;
import com.erdem.enowa.dto.SpeziesDTO;
import com.erdem.enowa.entity.Baum;
import com.erdem.enowa.entity.Befund;
import com.erdem.enowa.entity.Strasse;
import com.erdem.enowa.repo.StrasseRepo;

@Component
public class Konverter {
	
	@Autowired
	StrasseRepo strasseRepo;
	
	public List<BaumLocationResponse> strasseZuBaumLocationListKonverter(Strasse strasse){
		
		List<BaumLocationResponse> baumLocationList=new ArrayList<>();
		for (Baum baum : strasse.getBaeume()) {
			
			BaumLocationResponse baumLocation=new BaumLocationResponse();
			baumLocation.setBaumId(baum.getId());
			GeoLocationDTO geoLocationDTO=new GeoLocationDTO();
			geoLocationDTO.setLatidude(baum.getLocation().getLatidude());
			geoLocationDTO.setLongitude(baum.getLocation().getLongitude());
			baumLocation.setLocation(geoLocationDTO);
			baumLocationList.add(baumLocation);
		}
		return baumLocationList;
	}

	public BaumDetaillDTO baumZubaumDetailsKonverter(Baum baum) {
		
		BaumDetaillDTO baumdetails=new BaumDetaillDTO();
		baumdetails.setNummer(baum.getNummer());
		baumdetails.setPflanzdatum(baum.getPflanzdatum());
		SpeziesDTO spezies=new SpeziesDTO();
		spezies.setBotanischerName(baum.getSpezies().getBotanischerName());
		spezies.setTrivialName(baum.getSpezies().getTrivialName());
		baumdetails.setSpezies(spezies);
		
		return baumdetails;
	}

	public List<BefundDTO> baumZuBefundDTOListKonverter(Baum baum){
		
		List<Befund> befundeDesBaumes=baum.getBefunde();
		List<BefundDTO>befundeDTODesBaumes=new ArrayList<>();
			
		for (Befund befund : befundeDesBaumes) {
			
			BefundDTO befundDTO=new BefundDTO();
			
			befundDTO.setErhobenAm(befund.getErhobenAm());
			befundDTO.setBeschreibung(befund.getBeschreibung());
			befundeDTODesBaumes.add(befundDTO);
		}
		return befundeDTODesBaumes;
		
	}
}
