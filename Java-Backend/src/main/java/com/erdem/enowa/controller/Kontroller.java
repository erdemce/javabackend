package com.erdem.enowa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erdem.enowa.dto.BaumDetaillDTO;
import com.erdem.enowa.dto.BaumLocationResponse;
import com.erdem.enowa.dto.BefundDTO;
import com.erdem.enowa.service.IBaumService;
import com.erdem.enowa.service.IStrasseService;


@RestController
@RequestMapping(value="/enowa")
public class Kontroller {
	
	@Autowired
	IStrasseService strasseService;
	
	@Autowired
	IBaumService baumService;
	
	@GetMapping("/")
	public String main() {
		return "Hello";
	}
	
	//Die Frontend-Anwendung soll alle Bäume einer Straße als Punkte auf einer Karte zeigen
	//und benötigt daher die Geokoordinaten der Bäume
	@GetMapping("/strasse/{strasseId}/geokoordinatenderbaeume")
	public ResponseEntity<List<BaumLocationResponse>> getStrasseById(@PathVariable int strasseId) {
		
		return new ResponseEntity<List<BaumLocationResponse>>(strasseService.getAllBaumLocationInStrasse(strasseId),HttpStatus.OK);
	}
	
	//Das Frontend soll die Baume in der Karte je nach Befunddaten einfärben
	//und benötigt daher die Liste der Befunde
	
	@GetMapping("/baum/{baumId}/befunde")
	public ResponseEntity<List<BefundDTO>> getBefundById(@PathVariable int baumId) {
		 
		return new ResponseEntity<List<BefundDTO>>(baumService.getAllBefundeDesBaumes(baumId),HttpStatus.OK);
		
	}
	
	//Das Frontend soll per Pop-up die Details wie Spezies und Alter eines Baumes anzeigen
	//und benötigt daher Zugriff auf diese Informationen
	
	@GetMapping("/baum/{baumId}/details")
	public ResponseEntity<BaumDetaillDTO> getBaumDetailsById(@PathVariable int baumId) {
		
		return new ResponseEntity<BaumDetaillDTO>(baumService.getDetailsdesBaumes(baumId),HttpStatus.OK);
		
	}
}
