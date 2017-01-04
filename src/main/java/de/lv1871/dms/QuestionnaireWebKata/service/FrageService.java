package de.lv1871.dms.QuestionnaireWebKata.service;

import static de.lv1871.dms.QuestionnaireWebKata.util.LambdaExtensions.equal;
import static de.lv1871.dms.QuestionnaireWebKata.util.LambdaExtensions.exception;
import static de.lv1871.dms.QuestionnaireWebKata.util.LambdaExtensions.toStream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.lv1871.dms.QuestionnaireWebKata.domain.AntwortMoeglichkeit;
import de.lv1871.dms.QuestionnaireWebKata.domain.Frage;
import de.lv1871.dms.QuestionnaireWebKata.domain.Fragensatz;
import de.lv1871.dms.QuestionnaireWebKata.exception.ResourceNotFoundException;

@Service
public class FrageService {

	private static final String MSG_FRAGENSATZ_NOT_FOUND = "Der angegebene Fragensatz konnte nicht gefunden werden!";
	private static final String MSG_FRAGE_NOT_FOUND = "Die angegebene Frage konnte nicht gefunden werden!";

	private List<Fragensatz> fragensaetze;

	@Autowired
	private AntwortService antwortService;

	@PostConstruct
	public void initFragensaetze() {
		// @formatter:off
		
		Frage a = new Frage(1L, "Was ist eine Lebensversicherung?",
					Arrays.asList(
						new AntwortMoeglichkeit("Ein Schutz vor Zombieattacken", false),
						new AntwortMoeglichkeit("Eine Art Kontaktbörse", true),
						new AntwortMoeglichkeit("Ein Blatt Papier das im Wert stetig steigt", true)));

		Frage b = new Frage(2L, "Ist eine Lebensversicherung gewinnbringend?",
					Arrays.asList(
						new AntwortMoeglichkeit("Nur bei Vollmond", true),
						new AntwortMoeglichkeit("Nur bei Halbmond", false),
						new AntwortMoeglichkeit("Nur bei Sonnenfinsternis", false)));

		Frage c = new Frage(3L, "Kann man eine Lebensversicherung auch für ein Haustier abschließen?",
					Arrays.asList(
						new AntwortMoeglichkeit("Sobald der TSV 1860 München Meister ist", true),
						new AntwortMoeglichkeit("Nein", false),
						new AntwortMoeglichkeit("Hängt von der Tierart ab", false)));
		
		Frage d = new Frage(4L, "Welches Pokemon ist das Stärkste?",
					Arrays.asList(
						new AntwortMoeglichkeit("Evoli", false), 
						new AntwortMoeglichkeit("Mauzi", true),
						new AntwortMoeglichkeit("Rattikarl", false)));

		Frage e = new Frage(5L, "Welche Mannschaft wurde 2016 Europameister?",
					Arrays.asList(
						new AntwortMoeglichkeit("Portugal", true),
						new AntwortMoeglichkeit("Griechenland", false),
						new AntwortMoeglichkeit("Herbert und Peter", false)));
		
		Frage f = new Frage(6L, "Wer hält die Jahresendansprache",
				Arrays.asList(
					new AntwortMoeglichkeit("Angela", false),
					new AntwortMoeglichkeit("Robert", true)));

		fragensaetze = Arrays.asList(
				new Fragensatz(1, "Lebensversicherungen", Arrays.asList(a, b, c)),
				new Fragensatz(2, "Allgemeinwissen", Arrays.asList(f, d, e)));
		
		// @formatter:on
	}

	private Consumer<AntwortMoeglichkeit> addAntwortAnzahl() {
		// @formatter:off
		return (antwortMoeglichkeit) -> antwortMoeglichkeit
				.setAntwortAnzahl(antwortService.getAntwortAnzahl(antwortMoeglichkeit.getAntwort()));
		// @formatter:on
	}

	public List<Fragensatz> getFrageSaetze() {
		// @formatter:off
		fragensaetze.stream()
			.flatMap(toStream(Fragensatz::getFragen))
			.flatMap(toStream(Frage::getMoeglicheAntworten))
			.forEach(addAntwortAnzahl());
		// @formatter:on

		return fragensaetze;
	}

	public Fragensatz getFrageSatz(Integer fragensatzId) {
		// @formatter:off
		return fragensaetze
				.stream()
				.filter(equal(Fragensatz::getNumber, fragensatzId))
				.findFirst()
				.orElseThrow(exception(ResourceNotFoundException.class, MSG_FRAGENSATZ_NOT_FOUND));
		// @formatter:on
	}

	public List<Frage> getAllFrage() {
		// @formatter:off
		return getFrageSaetze()
				.stream()
				.flatMap(toStream(Fragensatz::getFragen))
				.collect(Collectors.toList());
		// @formatter:on
	}

	public Frage getFrage(Long frageId) {
		// @formatter:off
		return getFrageSaetze()
				.stream()
				.flatMap(toStream(Fragensatz::getFragen))
				.filter(equal(Frage::getId, frageId))
				.findFirst()
				.orElseThrow(exception(ResourceNotFoundException.class, MSG_FRAGE_NOT_FOUND));
		// @formatter:on
	}

}
