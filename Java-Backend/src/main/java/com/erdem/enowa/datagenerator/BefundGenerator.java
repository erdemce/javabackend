package com.erdem.enowa.datagenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import com.erdem.enowa.constraints.Constraints;
import com.erdem.enowa.entity.Befund;

@Component
public class BefundGenerator {
	
	/*
	 * Ein Baum hat im Mittel 5 Befunde
	 * Deshalb soll ich einen zufaellige Zahl(von 1 bis 10) der Befunde erstellen.
	 */
	
	public List<Befund> generetaBefundliste(){
	
			int AnzahlDerBefunde = RandomUtils.nextInt(1, 2*Constraints.DURCHSCHINITLICHERZAHLDERBEFUNDE);

			List<Befund> befundliste = new ArrayList<>();

			for (int i = 0; i < AnzahlDerBefunde; i++) {
				befundliste.add(generateBefund());
			}
			
			return befundliste;
	}
	
	/*
	 *  um einen Befund zu erstellen
	 */
	private Befund generateBefund() {
		Befund generatedBefund = new Befund();

		generatedBefund.setBeschreibung("Befund: " + RandomStringUtils.randomAlphabetic(10, 30));

		// Ich nehme daran dass alteste Befund vor 20 Jahre erhoben wurde....
		int vorWieVielTag = RandomUtils.nextInt(0, 365 * 20);
		LocalDate rndmLocalDate = LocalDate.now().minusDays(vorWieVielTag);
		Date rndDate = Date.valueOf(rndmLocalDate);
		generatedBefund.setErhobenAm(rndDate);
		return generatedBefund;

	}

}
