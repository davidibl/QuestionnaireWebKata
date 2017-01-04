package de.lv1871.dms.QuestionnaireWebKata.domain;

public class Antwort {

	private Long frageId;
	private String antwort;

	public Antwort() {

	}

	public Antwort(Long frageId, String antwort) {
		this.frageId = frageId;
		this.antwort = antwort;
	}

	public String getAntwort() {
		return antwort;
	}

	public Long getFrageId() {
		return frageId;
	}

}
