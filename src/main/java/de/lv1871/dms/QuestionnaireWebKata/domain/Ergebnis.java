package de.lv1871.dms.QuestionnaireWebKata.domain;

public class Ergebnis {

	private final int anzahlFragenGesamt;
	private final int anzahlFragenBeantwortet;
	private final int anzahlFragenRichtig;
	private final int anzahlFragenFalsch;
	private final int score;

	public Ergebnis(int anzahlFragenGesamt, int anzahlFragenBeantwortet, int anzahlFragenRichtig,
			int anzahlFragenFalsch, int score) {
		this.anzahlFragenBeantwortet = anzahlFragenBeantwortet;
		this.anzahlFragenFalsch = anzahlFragenFalsch;
		this.anzahlFragenGesamt = anzahlFragenGesamt;
		this.anzahlFragenRichtig = anzahlFragenRichtig;
		this.score = score;
	}

	public int getAnzahlFragenGesamt() {
		return anzahlFragenGesamt;
	}

	public int getAnzahlFragenBeantwortet() {
		return anzahlFragenBeantwortet;
	}

	public int getAnzahlFragenRichtig() {
		return anzahlFragenRichtig;
	}

	public int getAnzahlFragenFalsch() {
		return anzahlFragenFalsch;
	}

	public int getScore() {
		return score;
	}

}
