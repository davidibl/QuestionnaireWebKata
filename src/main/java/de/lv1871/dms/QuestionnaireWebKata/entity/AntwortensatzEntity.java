package de.lv1871.dms.QuestionnaireWebKata.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "antwortsatz")
public class AntwortensatzEntity {

	private Long id;
	private Set<AntwortEntity> antworten;

	@OneToMany(mappedBy = "antwortsatz", targetEntity = AntwortEntity.class, cascade = CascadeType.ALL)
	public Set<AntwortEntity> getAntworten() {
		return antworten;
	}

	public void setAntworten(Set<AntwortEntity> antworten) {
		this.antworten = antworten;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
