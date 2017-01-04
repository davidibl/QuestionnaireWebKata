package de.lv1871.dms.QuestionnaireWebKata.domain;

import static de.lv1871.dms.QuestionnaireWebKata.util.LambdaExtensions.equal;

import java.util.List;

public class FrageAuswerter {

	private final Frage frage;
	private List<Antwort> antworten;

	private FrageAuswerter(Frage frage) {
		this.frage = frage;
	}

	public static FrageAuswerter create(Frage frage) {
		return new FrageAuswerter(frage);
	}

	public FrageAuswerter withAntworten(List<Antwort> antworten) {
		this.antworten = antworten;
		return this;
	}

	public boolean isRichtigBeantwortet() {
		// @formatter:off
		return antworten
				.stream()
				.filter(equal(Antwort::getFrageId, frage.getId()))
				.filter(equal(Antwort::getAntwort, getRichtigeAntwort()))
				.count() > 0;
		// @formatter:on
	}

	public boolean isBeantwortet() {
		// @formatter:off
		return antworten
				.stream()
				.filter(equal(Antwort::getFrageId, frage.getId()))
				.findFirst()
				.isPresent();
		// @formatter:on
	}

	private String getRichtigeAntwort() {
		// @formatter:off
		return frage.getMoeglicheAntworten()
				.stream()
				.filter(AntwortMoeglichkeit::isRichtig)
				.map(AntwortMoeglichkeit::getAntwort)
				.findFirst()
				.get();
		// @formatter:on
	}

}
