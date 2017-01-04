package de.lv1871.dms.QuestionnaireWebKata.service;

import static de.lv1871.dms.QuestionnaireWebKata.util.LambdaExtensions.toStream;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.lv1871.dms.QuestionnaireWebKata.domain.Antwortensatz;
import de.lv1871.dms.QuestionnaireWebKata.domain.Ergebnis;
import de.lv1871.dms.QuestionnaireWebKata.domain.Frage;
import de.lv1871.dms.QuestionnaireWebKata.domain.FrageAuswerter;
import de.lv1871.dms.QuestionnaireWebKata.domain.Fragensatz;

@Service
public class ErgebnisService {

	@Autowired
	private AntwortService antwortService;

	@Autowired
	private FrageService frageService;

	public Ergebnis getErgebnis(Long antwortensatzId) {
		Antwortensatz antwortensatz = antwortService.getAntwortensatz(antwortensatzId);

		// @formatter:off
		List<Frage> alleFragen = frageService
				.getFrageSaetze()
				.stream()
				.flatMap(toStream(Fragensatz::getFragen))
				.collect(Collectors.toList());

		List<FrageAuswerter> fragenAuswerter = alleFragen
				.stream()
				.map(FrageAuswerter::create)
				.peek(addAntwortensatz(antwortensatz))
				.collect(Collectors.toList());
		// @formatter:on

		int anzahlRichtigeFragen = (int) fragenAuswerter.stream().filter(FrageAuswerter::isRichtigBeantwortet).count();

		int anzahlBeantwortet = (int) fragenAuswerter.stream().filter(FrageAuswerter::isBeantwortet).count();

		int anzahlFragenFalsch = alleFragen.size() - anzahlRichtigeFragen;

		int score = (anzahlRichtigeFragen != 0) ? alleFragen.size() - (alleFragen.size() - anzahlRichtigeFragen) : 0;

		return new Ergebnis(alleFragen.size(), anzahlBeantwortet, anzahlRichtigeFragen, anzahlFragenFalsch, score);

	}

	private Consumer<FrageAuswerter> addAntwortensatz(Antwortensatz antwortensatz) {
		return (frageAuswerter) -> frageAuswerter.withAntworten(antwortensatz.getAntworten());
	}

}
