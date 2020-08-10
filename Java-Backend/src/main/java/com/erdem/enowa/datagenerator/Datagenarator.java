package com.erdem.enowa.datagenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erdem.enowa.entity.Baum;
import com.erdem.enowa.entity.Befund;
import com.erdem.enowa.entity.GeoLocation;
import com.erdem.enowa.entity.Spezies;
import com.erdem.enowa.entity.Stadt;
import com.erdem.enowa.entity.Strasse;
import com.erdem.enowa.repo.SpeziesRepo;
import com.erdem.enowa.repo.StadtRepo;


@Component
public class Datagenarator {
	
	List<Spezies> allRndmSpezies=generateAllSpezies();
	
	public Stadt  generateStadt() {
		
		
		Stadt rndmStadt=new Stadt();
		String nameStadt=RandomStringUtils.randomAlphabetic(6,15).toUpperCase();
		rndmStadt.setName("STADT "+nameStadt);
		
		//für PLZ in DE, brauchen wir einen zufaellige Zahl von 10001 bis 99999
		rndmStadt.setPlz(String.valueOf(RandomUtils.nextInt(10001, 100000)));
		
		//ich esrstelle eine zufaellige Geolocation als StadtZentrum
		//Mein Ziel ist  logische punkte für Strassen und Baeumen zu generieren
		
		double latitude = (Math.random() * 180.0) - 90.0;
	    double longitude = (Math.random() * 360.0) - 180.0;
	    GeoLocation stadtZentrum=new GeoLocation();
	    stadtZentrum.setLatidude(latitude);
		stadtZentrum.setLongitude(longitude);
		
		
		
		//Je Stadt zwischen 10 und 200 Straßen
		//Deshalb brauchen wir einen zufaellige Zahl zwischen 10 und 200;
		int rndmZahlStrasse=RandomUtils.nextInt(10, 200);
		List<Strasse> rndStrassen=new ArrayList<>();
		for (int i = 0; i < rndmZahlStrasse; i++) {
			Strasse generatedStrasse=generateStrasse(stadtZentrum);
			rndStrassen.add(generatedStrasse);
		}
		
		rndmStadt.setStrassen(rndStrassen);
		
		return rndmStadt;
		
	}
	
	public Strasse generateStrasse(GeoLocation stadtZentrum) {
		
		Strasse rndmStrasse=new Strasse();
		
		rndmStrasse.setName(RandomStringUtils.randomAlphabetic(6,15).toUpperCase()+" Strasse");
		rndmStrasse.setVerwaltungsKuerzel(rndmStrasse.getName().substring(0, 5));
		
	
		
		//Es werden 250 verschiedene Spezies unterschieden -
		//in einer Straße stehen aber im allgemeinen Bäume maximal 10 verschiedener Spezies
		//Deshalb soll ich eine zufaellige Id-list von Spezies erstellen
		
		int zahlSpezies=RandomUtils.nextInt(0, 11);
		List<Integer>rndmSpeziesIndexfuerStrasse=new ArrayList<>();
		
		while (rndmSpeziesIndexfuerStrasse.size()<zahlSpezies) {
			int indexRndmSpezies=RandomUtils.nextInt(0,250);
			if(rndmSpeziesIndexfuerStrasse.contains(indexRndmSpezies)==false) {
				rndmSpeziesIndexfuerStrasse.add(indexRndmSpezies);
			}
		}
		
		//ich möchte einen punkt um herum Stadtzentrum herum zu generieren als Beginpunkt für strasse
		//ich benutze diesen Punkt beim Generieren der GeoLocation Baeumen
		
		GeoLocation beginStrasse=generateNaehenPunkt(stadtZentrum,(Math.random()-1)*2);
		
		//An einer Straße stehen bis zu 800 Bäume
		//Deshalb brauchen wir einen zufaellige Zahl von 1 bis 800
		int rndmZahlBaeume=RandomUtils.nextInt(1, 801);
				
				
		List<Baum> rndBaeume=new ArrayList<>();
		
		double abstand=(Math.random()-1)*0.02;
		for (int i = 0; i < rndmZahlBaeume; i++) {
			beginStrasse=generateNaehenPunkt(beginStrasse,abstand);
			Baum generatedBaum=generateBaum(rndmSpeziesIndexfuerStrasse,beginStrasse);
			rndBaeume.add(generatedBaum);
		}
		
		rndmStrasse.setBaeume(rndBaeume);
		
		
		return rndmStrasse;
		
	}

	private Baum generateBaum(List<Integer> probleSpeziesIds,GeoLocation baumPunkt) {
		
		Baum rndmBaum=new Baum();
		
		//Ich nehme daran dass alteste Baum 100 Jahre alt ist... Alle Baeume sind von 0 bis 100 jahre alt.
		int alt=RandomUtils.nextInt(0, 365*100);
		LocalDate rndmLocalDate=LocalDate.now().minusDays(alt);
		Date rndDate=Date.valueOf(rndmLocalDate);
		rndmBaum.setPflanzdatum(rndDate);
		
		rndmBaum.setLocation(baumPunkt);
		Long minNummer=1000000000L;
		Long maxNummer=9999999999L;
		
		rndmBaum.setNummer(RandomUtils.nextLong(minNummer,maxNummer));
		
		
		int indexSpeziesId=RandomUtils.nextInt(0, probleSpeziesIds.size());
		rndmBaum.setSpezies(allRndmSpezies.get(indexSpeziesId));
		
		
		//Ein Baum hat im Mittel 5 Befunde
		//Deshalb soll ich einen zufaellige Zahl(von 1 bis 10) der Befunde erstellen.
		int befundZahl=RandomUtils.nextInt(1, 10);
		List<Befund> rndmBefunde=new ArrayList<>();
		
		for (int i = 0; i < befundZahl; i++) {
			rndmBefunde.add(generateBefund());
		}
		rndmBaum.setBefunde(rndmBefunde);

		return rndmBaum;
	}
	
	private Befund generateBefund() {
		Befund generatedBefund=new Befund();
		
		generatedBefund.setBeschreibung("Befund: "+RandomStringUtils.randomAlphabetic(10, 30));
		
		//Ich nehme daran dass alteste Befund vor 20 Jahre erhoben wurde.... 
		
		int vorWieVielTag=RandomUtils.nextInt(0, 365*20);
		LocalDate rndmLocalDate=LocalDate.now().minusDays(vorWieVielTag);
		Date rndDate=Date.valueOf(rndmLocalDate);
		generatedBefund.setErhobenAm(rndDate);
		return generatedBefund;
		
		
	}
	
	private Spezies generateSpezies(){

		Spezies rndmSpezies=new Spezies();
		rndmSpezies.setBotanischerName(RandomStringUtils.randomAlphabetic(8, 15)+" Spezies");
		rndmSpezies.setTrivialName(RandomStringUtils.randomAlphabetic(5, 12)+" Spezies");
		
		return rndmSpezies;
		
	}
	
	private List<Spezies> generateAllSpezies(){
		List<Spezies> allRndmSpezies=new ArrayList<>();

		for (int i = 0; i <250;i++) {
			allRndmSpezies.add(generateSpezies());
		}
		
		return allRndmSpezies;
	}
	
	private GeoLocation generateNaehenPunkt(GeoLocation abgegebenePunkt,Double abstand) {
				
				GeoLocation generatedPunkt=new GeoLocation();
				double longitudeVonBeginStrasse=abgegebenePunkt.getLongitude()+abstand;
				double latitudeVonBeginStrasse=abgegebenePunkt.getLatidude()+abstand;
				generatedPunkt.setLongitude(longitudeVonBeginStrasse);
				generatedPunkt.setLatidude(latitudeVonBeginStrasse);
				return generatedPunkt;
	}
	
	public List<Spezies> getAllRndmSpezies(){
		return allRndmSpezies;
	}
}
