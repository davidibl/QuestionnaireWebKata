package de.lv1871.dms.QuestionnaireWebKata.service;

import static de.lv1871.dms.QuestionnaireWebKata.util.LambdaExtensions.equal;
import static de.lv1871.dms.QuestionnaireWebKata.util.LambdaExtensions.exception;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.lv1871.dms.QuestionnaireWebKata.domain.Antwort;
import de.lv1871.dms.QuestionnaireWebKata.domain.AntwortMoeglichkeit;
import de.lv1871.dms.QuestionnaireWebKata.domain.Antwortensatz;
import de.lv1871.dms.QuestionnaireWebKata.domain.Frage;
import de.lv1871.dms.QuestionnaireWebKata.entity.AntwortEntity;
import de.lv1871.dms.QuestionnaireWebKata.entity.AntwortensatzEntity;
import de.lv1871.dms.QuestionnaireWebKata.exception.BadRequestException;
import de.lv1871.dms.QuestionnaireWebKata.exception.ResourceNotFoundException;
import de.lv1871.dms.QuestionnaireWebKata.repository.AntwortenRepository;
import de.lv1871.dms.QuestionnaireWebKata.repository.AntwortensatzRepository;

@Service
public class AntwortService {

	private static final String MSG_KEIN_ANTWORTENSATZ_VORHANDEN = "Um zu antworten müssen Sie zunächst einen gültigen Antwortensatz anlegen.";
	private static final String MSG_FRAGE_NICHT_GEFUNDEN = "Die angegebene Frage kann nicht gefunden werden.";
	private static final String MSG_ANTWORT_NICHT_MOEGLICH = "Die gegebene Antwort stand nicht zur Auswahl.";
	private static final String MSG_ANTWORT_NOT_FOUND = "Die Antwort konnte nicht gefunden werden.";
	private static final String MSG_ANTWORTENSATZ_NOT_FOUND = "Der angegebene Antwortensatz konnte nicht gefunden werden!";

	@Autowired
	private AntwortensatzRepository antwortensatzRepository;

	@Autowired
	private AntwortenRepository antwortenRepository;

	@Autowired
	private FrageService frageService;

	public Long createAntwortenSatz() {
		AntwortensatzEntity entity = new AntwortensatzEntity();
		AntwortensatzEntity antwortensatzEntity = antwortensatzRepository.save(entity);

		return antwortensatzEntity.getId();
	}

	public Antwortensatz getAntwortensatz(Long antwortensatzId) {
		AntwortensatzEntity antwortensatzEntity = antwortensatzRepository.findById(antwortensatzId);

		if (antwortensatzEntity == null) {
			throw new ResourceNotFoundException(MSG_ANTWORTENSATZ_NOT_FOUND);
		}

		// @formatter:off
		return new Antwortensatz(antwortensatzEntity.getId(),
				antwortensatzEntity
						.getAntworten()
						.stream()
						.map(entityToAntwort())
						.collect(Collectors.toList()));
		// @formatter:on

	}

	private Function<AntwortEntity, Antwort> entityToAntwort() {
		return (entity) -> new Antwort(entity.getId(), entity.getAntwort());
	}

	public Long addAntwort(Long antwortensatzId, Antwort antwort) {

		// @formatter:off
		Frage frage = Optional.ofNullable(antwort)
			.map(a -> frageService.getFrage(a.getFrageId()))
			.orElseThrow(exception(BadRequestException.class, MSG_FRAGE_NICHT_GEFUNDEN));
		// @formatter:on

		if (isAntwortNichtMoeglich(antwort).test(frage)) {
			throw new BadRequestException(MSG_ANTWORT_NICHT_MOEGLICH);
		}

		AntwortensatzEntity antwortensatzEntity = antwortensatzRepository.findById(antwortensatzId);
		if (antwortensatzEntity == null) {
			throw new BadRequestException(MSG_KEIN_ANTWORTENSATZ_VORHANDEN);
		}

		AntwortEntity newAntwortentity = new AntwortEntity();

		newAntwortentity.setAntwort(antwort.getAntwort());
		newAntwortentity.setId(antwort.getFrageId());
		newAntwortentity.setAntwortsatz(antwortensatzEntity);

		return antwortenRepository.save(newAntwortentity).getId();
	}

	private Predicate<Frage> isAntwortNichtMoeglich(Antwort antwort) {
		// @formatter:off
		return (frage) -> frage
					.getMoeglicheAntworten()
					.stream()
					.filter(equal(AntwortMoeglichkeit::getAntwort, antwort.getAntwort()))
					.count() < 1;
		// @formatter:on
	}

	public Antwort getAntwort(Long antwortensatzId, Long antwortId) {
		// @formatter:off
		return getAntwortensatz(antwortensatzId)
				.getAntworten()
				.stream()
				.filter(equal(Antwort::getFrageId, antwortId))
				.findFirst()
				.orElseThrow(exception(ResourceNotFoundException.class, MSG_ANTWORT_NOT_FOUND));
		// @formatter:on
	}

	public Long getAntwortAnzahl(String antwort) {
		return new Long(antwortenRepository.findByAntwort(antwort).size());
	}

}
