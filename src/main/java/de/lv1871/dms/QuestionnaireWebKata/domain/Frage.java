package de.lv1871.dms.QuestionnaireWebKata.domain;

import java.util.List;

public class Frage {

	private final Long id;
	private final String frage;
	private final List<AntwortMoeglichkeit> moeglicheAntworten;

	public Frage(Long id, String frage, List<AntwortMoeglichkeit> moeglicheAntworten) {
		this.id = id;
		this.frage = frage;
		this.moeglicheAntworten = moeglicheAntworten;
	}

	public Long getId() {
		return id;
	}

	public String getFrage() {
		return frage;
	}

	public List<AntwortMoeglichkeit> getMoeglicheAntworten() {
		return moeglicheAntworten;
	}

}
