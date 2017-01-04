package de.lv1871.dms.QuestionnaireWebKata.domain;

import java.util.List;

public class Antwortensatz {

	private final Long id;
	private final List<Antwort> antworten;

	public Antwortensatz(Long id, List<Antwort> antworten) {
		this.id = id;
		this.antworten = antworten;
	}

	public Long getId() {
		return id;
	}

	public List<Antwort> getAntworten() {
		return antworten;
	}

}
