package com.erdem.enowa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erdem.enowa.customexception.BaumNotFoundException;
import com.erdem.enowa.customexception.StrasseNotFoundException;
import com.erdem.enowa.dto.BefundDTO;
import com.erdem.enowa.dto.GeoLocationDTO;
import com.erdem.enowa.dto.SpeziesDTO;
import com.erdem.enowa.dto.response.BaumDetailResponse;
import com.erdem.enowa.dto.response.BaumLocationResponse;
import com.erdem.enowa.dto.response.BefundResponse;
import com.erdem.enowa.entity.Baum;
import com.erdem.enowa.entity.Befund;
import com.erdem.enowa.entity.Strasse;
import com.erdem.enowa.repo.BaumRepo;
import com.erdem.enowa.repo.BefundRepo;
import com.erdem.enowa.repo.StrasseRepo;

@Service
public class MeinService implements MeinServiceInterface{
	
	@Autowired
	StrasseRepo strasseRepo;
	
	@Autowired
	BaumRepo baumRepo;
	
	@Autowired
	BefundRepo befundRepo;
	

	@Override
	public List<BaumLocationResponse> getAllBaumLocationInStrasse(int strasseId) {
		
		//TODO burada exceptionhandlung yapmalımıyız
		//TODO SOR servisler ayrı ayrı mı yazılmalı
		Strasse selectedStrasse=strasseRepo.findById(strasseId).orElseThrow(()->new StrasseNotFoundException());
		
		List<BaumLocationResponse> baumLocationList=new ArrayList<>();
		
		for (Baum baum : selectedStrasse.getBaeume()) {
			
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

	@Override
	public List<BefundResponse> getAllBefundeDesBaumes(int baumId) {
		Baum selectedBaum=baumRepo.findById(baumId).orElseThrow(()->new BaumNotFoundException());
		
		
		List<Befund> befundeDesBaumes=selectedBaum.getBefunde();
		List<BefundResponse>befundeDTODesBaumes=new ArrayList<>();
		
		
		for (int i = 0; i < befundeDesBaumes.size(); i++) {
			
			BefundResponse convertedBefundDTO=new BefundResponse();
			
			convertedBefundDTO.setErhobenAm(befundeDesBaumes.get(i).getErhobenAm());
			convertedBefundDTO.setBeschreibung(befundeDesBaumes.get(i).getBeschreibung());
			befundeDTODesBaumes.add(convertedBefundDTO);
		}
		
		
		
		return befundeDTODesBaumes;
		
	}
	
	@Override
	public BaumDetailResponse getDetailsdesBaumes(int baumId) {
		Baum selectedBaum=baumRepo.findById(baumId).orElse(null);
		
		BaumDetailResponse baumdetails=new BaumDetailResponse();
		baumdetails.setNummer(selectedBaum.getNummer());
		baumdetails.setPflanzdatum(selectedBaum.getPflanzdatum());
		SpeziesDTO spezies=new SpeziesDTO();
		spezies.setBotanischerName(selectedBaum.getSpezies().getBotanischerName());
		spezies.setTrivialName(selectedBaum.getSpezies().getTrivialName());
		baumdetails.setSpezies(spezies);
		
		return baumdetails;
		
	}
}