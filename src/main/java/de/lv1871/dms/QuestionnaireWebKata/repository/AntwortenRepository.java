package de.lv1871.dms.QuestionnaireWebKata.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import de.lv1871.dms.QuestionnaireWebKata.entity.AntwortEntity;

public interface AntwortenRepository extends Repository<AntwortEntity, Long> {

	List<AntwortEntity> findAll();

	AntwortEntity findById(Long id);

	List<AntwortEntity> findByAntwort(String antwort);

	AntwortEntity save(AntwortEntity entity);

}
