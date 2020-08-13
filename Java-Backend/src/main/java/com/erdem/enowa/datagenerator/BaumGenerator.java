package com.erdem.enowa.datagenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erdem.enowa.constraints.Constraints;
import com.erdem.enowa.dto.SpeziesDTO;
import com.erdem.enowa.entity.Baum;
import com.erdem.enowa.entity.Befund;
import com.erdem.enowa.entity.GeoLocation;
import com.erdem.enowa.entity.Spezies;
import com.erdem.enowa.entity.Stadt;
import com.erdem.enowa.entity.Strasse;

@Component
public class BaumGenerator {
	
	@Autowired
	GeoLocationGenerator locationGenerator;
	
	@Autowired
	SpeziesGenerator speziesGenerator;
	
	@Autowired
	BefundGenerator befundGenerator;
	
	/*
	 * um die Baeume in allen Strassen in einer Stadt zu erstellen 
	 */
	public Stadt generateBaeumefuerStadt(Stadt stadt, List<Spezies> speziesliste) {
		
			// ich esrstelle eine zufaellige Geolocation als StadtZentrum
			// Mein Ziel ist logische punkte für Strassen und Baeumen zu generieren		
			GeoLocation stadtZentrum=locationGenerator.generateLocation();

			for (Strasse strasse : stadt.getStrassen()) {
				/*
				 * Es werden 250 verschiedene Spezies unterschieden. In einer Straße
				 * stehen aber im allgemeinen Bäume maximal 10 verschiedener Spezies
				 */
				List<Spezies>spezieslistefuerStrasse =speziesGenerator.spezieslisteInEinerStrasse(speziesliste);

				// ich möchte einen punkt um herum Stadtzentrum herum zu generieren als
				// Beginpunkt für strasse
				// ich benutze diesen Punkt beim Generieren der GeoLocation Baeumen

				GeoLocation beginPunktDerStrasse = locationGenerator.generateLocation(stadtZentrum, (Math.random() - 1) * 2,(Math.random() - 1) * 2);

				strasse=generateBaeumeFuerStrasse(strasse,beginPunktDerStrasse,spezieslistefuerStrasse);
				
			}
			
			return stadt;
		}
	
	private Strasse generateBaeumeFuerStrasse(Strasse strasse,GeoLocation beginPunktDerStrasse, List<Spezies> spezieslistefuerStrasse ) {
		
		// An einer Straße stehen bis zu 800 Bäume
		// Deshalb brauchen wir einen zufaelligen Zahl von 1 bis 800
		int AnZahlDerBaeume = RandomUtils
				.nextInt(Constraints.MINANZAHLDERBAEUMEINSTRASSE, Constraints.MAXANZAHLDERBAEUMEINSTRASSE+1);
		
		List<Baum> bauemeliste = new ArrayList<>();

		double abstandLatitude = (Math.random() - 1) * 0.02;
		double abstandLongitude = (Math.random() - 1) * 0.02;
		for (int i = 0; i < AnZahlDerBaeume; i++) {
			beginPunktDerStrasse = locationGenerator.generateLocation(beginPunktDerStrasse, abstandLatitude,abstandLongitude);
			Baum baum = generateBaum(spezieslistefuerStrasse, beginPunktDerStrasse);
			bauemeliste.add(baum);
		}
		strasse.setBaeume(bauemeliste);
		return strasse;
		
		
	}
	
	/*
	 * um einen Baum und dazugehörige Befunde zu erstellen
	 */
	private Baum generateBaum(List<Spezies> spezieslistefuerStrasse, GeoLocation baumPunkt) {

		Baum baum = new Baum();

		// Ich nehme daran dass alteste Baum 100 Jahre alt ist... Alle Baeume sind von 0
		// bis 100 jahre alt.
		int alt = RandomUtils.nextInt(0, 365 * 100);
		LocalDate rndmLocalDate = LocalDate.now().minusDays(alt);
		Date rndDate = Date.valueOf(rndmLocalDate);
		baum.setPflanzdatum(rndDate);

		baum.setLocation(baumPunkt);
		Long minNummer = 1000000000L;
		Long maxNummer = 9999999999L;

		baum.setNummer(RandomUtils.nextLong(minNummer, maxNummer));

		int indexSpeziesId = RandomUtils.nextInt(0, spezieslistefuerStrasse.size());
		baum.setSpezies(spezieslistefuerStrasse.get(indexSpeziesId));
		
		baum.setBefunde(befundGenerator.generetaBefundliste());

		return baum;
	}
		
}


