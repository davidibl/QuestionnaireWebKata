package de.lv1871.dms.QuestionnaireWebKata.domain;

import java.util.List;

public class Fragensatz {

	private final int number;
	private final String titel;
	private final List<Frage> fragen;

	public Fragensatz(int number, String titel, List<Frage> fragen) {
		this.titel = titel;
		this.fragen = fragen;
		this.number = number;
	}

	public String getTitel() {
		return titel;
	}

	public List<Frage> getFragen() {
		return fragen;
	}

	public int getNumber() {
		return number;
	}

}
