package com.erdem.enowa.datagenerator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import com.erdem.enowa.constraints.Constraints;
import com.erdem.enowa.entity.Spezies;

@Component
public class SpeziesGenerator {
	
	/*
	 *  um die Spezies zu erstellen
	 */
	public List<Spezies> generateSpezies(int anzahl) {
		List<Spezies> speziesliste = new ArrayList<>();

		for (int i = 0; i < 250; i++) {
			speziesliste.add(generateSpezies());
		}
		return speziesliste;
	}
	
	/*
	 *  um einen Spezies zu erstellen
	 */
	private Spezies generateSpezies() {

		Spezies rndmSpezies = new Spezies();
		rndmSpezies.setBotanischerName(RandomStringUtils.randomAlphabetic(8, 15) + " Spezies");
		rndmSpezies.setTrivialName(RandomStringUtils.randomAlphabetic(5, 12) + " Spezies");

		return rndmSpezies;
	}
	
	
	/*
	 * Es werden 250 verschiedene Spezies unterschieden. In einer Straße
	 * stehen aber im allgemeinen Bäume maximal 10 verschiedener Spezies
	 * 
	 * Um eine zufaellige Id-list von Spezies in einer Strasse zu erstellen
	 */
	public List<Spezies> spezieslisteInEinerStrasse(List<Spezies> speziesliste){
		
		int zahlSpezies = RandomUtils.nextInt(1, Constraints.MAXANZAHLDERSPEZIESINEINERSTRASSE+1);
		List<Spezies> spezieslistefuerStrasse = new ArrayList<>();

		while (spezieslistefuerStrasse.size() < zahlSpezies) {
			int indexRndmSpezies = RandomUtils.nextInt(0, Constraints.ANZAHLDERSPEZIES);
			if (spezieslistefuerStrasse.contains(speziesliste.get(indexRndmSpezies)) == false) {
				spezieslistefuerStrasse.add(speziesliste.get(indexRndmSpezies));
			}
		}
		
		return spezieslistefuerStrasse;
	}

}
