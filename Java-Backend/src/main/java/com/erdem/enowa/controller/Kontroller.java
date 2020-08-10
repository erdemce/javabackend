package com.erdem.enowa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erdem.enowa.dto.response.BaumDetailResponse;
import com.erdem.enowa.dto.response.BaumLocationResponse;
import com.erdem.enowa.dto.response.BefundResponse;
import com.erdem.enowa.repo.BefundRepo;
import com.erdem.enowa.service.MeinServiceInterface;


@RestController
@RequestMapping(value="/enowa")
public class Kontroller {
	
	@Autowired
	MeinServiceInterface meinService;
	
	@GetMapping("/")
	public String main() {
		return "Hello";
	}
	
	//Die Frontend-Anwendung soll alle Bäume einer Straße als Punkte auf einer Karte zeigen
	//und benötigt daher die Geokoordinaten der Bäume
	@GetMapping("/strasse/{strasseId}/geokoordinatenderbaeume")
	public List<BaumLocationResponse> getStrasseById(@PathVariable int strasseId) {
		
		return meinService.getAllBaumLocationInStrasse(strasseId);
	}
	
	//Das Frontend soll die Baume in der Karte je nach Befunddaten einfärben
	//und benötigt daher die Liste der Befunde
	@GetMapping("/baum/{baumId}/befunde")
	public List<BefundResponse> getBefundById(@PathVariable int baumId) {
		 return meinService.getAllBefundeDesBaumes(baumId);
		
	}
	
	//Das Frontend soll per Pop-up die Details wie Spezies und Alter eines Baumes anzeigen
	//und benötigt daher Zugriff auf diese Informationen
	@GetMapping("/baum/{baumId}/details")
	public BaumDetailResponse getBaumDetailsById(@PathVariable int baumId) {
		 return meinService.getDetailsdesBaumes(baumId);
		
	}
}
