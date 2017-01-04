package de.lv1871.dms.QuestionnaireWebKata.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AntwortMoeglichkeit {

	private final String antwort;
	private final boolean richtig;
	private Long antwortAnzahl;

	public AntwortMoeglichkeit(String antwort, Boolean richtig) {
		this.antwort = antwort;
		this.richtig = richtig;
	}

	public String getAntwort() {
		return antwort;
	}

	@JsonIgnore
	public boolean isRichtig() {
		return richtig;
	}

	public Long getAntwortAnzahl() {
		return antwortAnzahl;
	}

	public void setAntwortAnzahl(Long antwortAnzahl) {
		this.antwortAnzahl = antwortAnzahl;
	}

}
