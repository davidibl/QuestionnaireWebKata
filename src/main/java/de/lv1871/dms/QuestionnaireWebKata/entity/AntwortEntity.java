package de.lv1871.dms.QuestionnaireWebKata.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "antwort")
public class AntwortEntity {

	private Long id;
	private String antwort;
	private AntwortensatzEntity antwortsatz;

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "antwortsatzId", referencedColumnName = "id")
	public AntwortensatzEntity getAntwortsatz() {
		return antwortsatz;
	}

	public void setAntwortsatz(AntwortensatzEntity antwortsatz) {
		this.antwortsatz = antwortsatz;
	}

	public String getAntwort() {
		return antwort;
	}

	public void setAntwort(String antwort) {
		this.antwort = antwort;
	}

}
