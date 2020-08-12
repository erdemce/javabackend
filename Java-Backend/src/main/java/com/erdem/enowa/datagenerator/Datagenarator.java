package com.erdem.enowa.datagenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erdem.enowa.constraints.Constraints;
import com.erdem.enowa.entity.Baum;
import com.erdem.enowa.entity.Befund;
import com.erdem.enowa.entity.GeoLocation;
import com.erdem.enowa.entity.Spezies;
import com.erdem.enowa.entity.Stadt;
import com.erdem.enowa.entity.Strasse;
import com.erdem.enowa.repo.SpeziesRepo;
import com.erdem.enowa.repo.StadtRepo;
import com.erdem.enowa.repo.StrasseRepo;

@Component
public class Datagenarator {

	@Autowired
	private StadtRepo stadtRepo;

	@Autowired
	private SpeziesRepo speziesRepo;

	@Autowired
	private StrasseRepo strasseRepo;

	private List<Spezies> allRndmSpezies;

	private List<Stadt> Staedteliste;
	
	public boolean ueberpruefenDieAnzahlderStaedte() {
		boolean gibtRichtigeData=true;
		
		if(stadtRepo.count()!=Constraints.ANZAHLDERSTAEDTE) {
			gibtRichtigeData= false;
		}
		else if(speziesRepo.count()!=Constraints.ANZAHLDERSPEZIES) {
			gibtRichtigeData= false;
		}
		else if(strasseRepo.count()< Constraints.MINANZAHLDERSTRASSEINSTADT*Constraints.ANZAHLDERSTAEDTE) {
			gibtRichtigeData= false;
		}
		else if(strasseRepo.count()> Constraints.MAXANZAHLDERSTRASSEINSTADT*Constraints.ANZAHLDERSTAEDTE) {
			gibtRichtigeData= false;
		}
		if(!gibtRichtigeData) {
			stadtRepo.deleteAll();
			speziesRepo.deleteAll();
		}
		
		return gibtRichtigeData;
	}
	
	/*
	 *  um alle Spezies zu erstellen und in Datenbank zu speichern
	 */
	public void saveAllSpezies() {
		List<Spezies> allRndmSpezies = new ArrayList<>();

		for (int i = 0; i < 250; i++) {
			allRndmSpezies.add(generateSpezies());
		}
		this.allRndmSpezies = allRndmSpezies;
		speziesRepo.saveAll(allRndmSpezies);
	}
	/*
	 * Um die Staedte und die Strassen dieser Staedte erstellen und
	 * in Datenbank speichern.
	 * 
	 */
	public void saveStaedtewithStrassen() {

		Staedteliste = new ArrayList<>();

		for (int i = 0; i < Constraints.ANZAHLDERSTAEDTE; i++) {
			Stadt savedStadt = stadtRepo.saveAndFlush(generateStadtwithStrassen());
			Staedteliste.add(savedStadt);
			System.out.println(i + ". Stadt speichert");
		}
	}
	/*
	 * um die Baeume in allen Strassen in allen Staedten zu erstellen
	 */
	public void saveBaeumeInAllenStaedten() {

		for (Stadt stadt : Staedteliste) {
			saveBaeumeInAllenStrassenInEinerStadt(stadt);
		}
	}
	/*
	 * um eine zufaellige Stadt und dazugehörige Strasse zu erstellen
	 */
	private Stadt generateStadtwithStrassen() {

		Stadt stadt = new Stadt();
		String nameStadt = RandomStringUtils.randomAlphabetic(6, 15).toUpperCase();
		stadt.setName("STADT " + nameStadt);

		// Als PLZ brauchen wir einen zufaelligen Zahl.
		stadt.setPlz(
				String.valueOf(RandomUtils.nextInt(Constraints.MINPLZVONDEUTSHLAND, Constraints.MAXPLZVONDEUTSHLAND)));

		// Als Anzahl der Strassen brauchen wir einen zufaelligen Zahl
		int rndmAnzahlStrasse = RandomUtils.nextInt(Constraints.MINANZAHLDERSTRASSEINSTADT,
				Constraints.MAXANZAHLDERSTRASSEINSTADT);
		List<Strasse> listeDerStrassen = new ArrayList<>();
		for (int i = 0; i < rndmAnzahlStrasse; i++) {
			Strasse strasse = generateStrasse();
			listeDerStrassen.add(strasse);
		}

		stadt.setStrassen(listeDerStrassen);

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
	/*
	 * um die Baeume in allen Strassen in einer Stadt zu erstellen 
	 * und in Datenbank zu speichern
	 */
	private void saveBaeumeInAllenStrassenInEinerStadt(Stadt stadt) {


		// ich esrstelle eine zufaellige Geolocation als StadtZentrum
		// Mein Ziel ist logische punkte für Strassen und Baeumen zu generieren

		System.out.println(stadt.getId() + ". stadt yazılmaya basladı");

		double latitude = (Math.random() * 180.0) - 90.0;
		double longitude = (Math.random() * 360.0) - 180.0;
		GeoLocation stadtZentrum = new GeoLocation();
		stadtZentrum.setLatitude(latitude);
		stadtZentrum.setLongitude(longitude);

		for (Strasse strasse : stadt.getStrassen()) {

			// Es werden 250 verschiedene Spezies unterschieden. In einer Straße
			//stehen aber im allgemeinen Bäume maximal 10 verschiedener Spezies
			// Deshalb soll ich eine zufaellige Id-list von Spezies erstellen
			int zahlSpezies = RandomUtils.nextInt(1, Constraints.MAXANZAHLDERSPEZIESINEINERSTRASSE+1);
			List<Integer> speziesIndexlistefuerStrasse = new ArrayList<>();

			while (speziesIndexlistefuerStrasse.size() < zahlSpezies) {
				int indexRndmSpezies = RandomUtils.nextInt(0, Constraints.ANZAHLDERSPEZIES);
				if (speziesIndexlistefuerStrasse.contains(indexRndmSpezies) == false) {
					speziesIndexlistefuerStrasse.add(indexRndmSpezies);
				}
			}

			// ich möchte einen punkt um herum Stadtzentrum herum zu generieren als
			// Beginpunkt für strasse
			// ich benutze diesen Punkt beim Generieren der GeoLocation Baeumen

			GeoLocation beginPunktDerStrasse = generateEinenNaehenKoordinatenpunkt(stadtZentrum, (Math.random() - 1) * 2,(Math.random() - 1) * 2);

			// An einer Straße stehen bis zu 800 Bäume
			// Deshalb brauchen wir einen zufaelligen Zahl von 1 bis 800
			int AnZahlDerBaeume = RandomUtils
					.nextInt(Constraints.MINANZAHLDERBAEUMEINSTRASSE, Constraints.MAXANZAHLDERBAEUMEINSTRASSE+1);

			System.out.println("baum: " + AnZahlDerBaeume);

			List<Baum> bauemeliste = new ArrayList<>();

			double abstandLatitude = (Math.random() - 1) * 0.02;
			double abstandLongitude = (Math.random() - 1) * 0.02;
			for (int i = 0; i < AnZahlDerBaeume; i++) {
				beginPunktDerStrasse = generateEinenNaehenKoordinatenpunkt(beginPunktDerStrasse, abstandLatitude,abstandLongitude);
				Baum baum = generateBaum(speziesIndexlistefuerStrasse, beginPunktDerStrasse);
				bauemeliste.add(baum);
			}
			strasse.setBaeume(bauemeliste);
			strasseRepo.save(strasse);
		}
	}
	/*
	 * um einen Baum und dazugehörige Befunde zu erstellen
	 */
	private Baum generateBaum(List<Integer> probelSpeziesIds, GeoLocation baumPunkt) {

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

		int indexSpeziesId = RandomUtils.nextInt(0, probelSpeziesIds.size());
		baum.setSpezies(allRndmSpezies.get(indexSpeziesId));

		// Ein Baum hat im Mittel 5 Befunde
		// Deshalb soll ich einen zufaellige Zahl(von 1 bis 10) der Befunde erstellen.
		int AnzahlDerBefunde = RandomUtils.nextInt(1, 2*Constraints.DURCHSCHINITLICHERZAHLDERBEFUNDE);

		List<Befund> befundliste = new ArrayList<>();

		for (int i = 0; i < AnzahlDerBefunde; i++) {
			befundliste.add(generateBefund());

		}
		baum.setBefunde(befundliste);

		return baum;
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
	 * um einen naehen Koordinatenpunkt zu erstellen
	 */
	private GeoLocation generateEinenNaehenKoordinatenpunkt(GeoLocation abgegebenePunkt, double abstandLongitude, double abstandLatitude ) {

		GeoLocation location = new GeoLocation();
		double longitudeVonBeginStrasse = abgegebenePunkt.getLongitude() + abstandLongitude;
		double latitudeVonBeginStrasse = abgegebenePunkt.getLatitude() + abstandLatitude;
		location.setLongitude(longitudeVonBeginStrasse);
		location.setLatitude(latitudeVonBeginStrasse);
		return location;
	}
}