package com.erdem.enowa.datagenerator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import com.erdem.enowa.constraints.Constraints;
import com.erdem.enowa.entity.Stadt;
import com.erdem.enowa.entity.Strasse;

@Component
public class StadtGenerator {
	/*
	 * Um die Staedte und dazugehörige Strassen erstellen 
	 * 
	 */
	public List<Stadt> generateStaedtewithStrassen(int anzahl) {

		List<Stadt> staedteliste = new ArrayList<>();

		for (int i = 0; i < anzahl; i++) {
			Stadt stadt = generateStadtwithStrassen();
			staedteliste.add(stadt);
		}
		
		return staedteliste;
	}
	/*
	 * um eine zufaellige Stadt und dazugehörige Strasse zu erstellen
	 */
	private Stadt generateStadtwithStrassen() {

		Stadt stadt = new Stadt();
		String nameStadt = RandomStringUtils.randomAlphabetic(6, 15).toUpperCase();
		stadt.setName("STADT " + nameStadt);

		// Als PLZ brauchen wir einen zufaelligen Zahl.
		stadt.setPlz(String.valueOf(RandomUtils.nextInt()));

		// Als Anzahl der Strassen brauchen wir einen zufaelligen Zahl
		int rndmAnzahlStrasse = RandomUtils.
				nextInt(Constraints.MINANZAHLDERSTRASSEINSTADT,Constraints.MAXANZAHLDERSTRASSEINSTADT);
		
		List<Strasse> strassenliste = new ArrayList<>();
		for (int i = 0; i < rndmAnzahlStrasse; i++) {
			Strasse strasse = generateStrasse();
			strassenliste.add(strasse);
		}

		stadt.setStrassen(strassenliste);

		return stadt;

	}
	
	/*
	 * um eine Strasse zu erstellen
	 */
	private Strasse generateStrasse() {

		Strasse strasse = new Strasse();

		strasse.setName(RandomStringUtils.randomAlphabetic(6, 15).toUpperCase() + " Strasse");
		strasse.setVerwaltungsKuerzel(strasse.getName().substring(0, 5));
		return strasse;
	}	


}
