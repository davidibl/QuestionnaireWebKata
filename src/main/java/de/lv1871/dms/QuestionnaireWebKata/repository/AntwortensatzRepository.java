package de.lv1871.dms.QuestionnaireWebKata.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import de.lv1871.dms.QuestionnaireWebKata.entity.AntwortensatzEntity;

public interface AntwortensatzRepository extends Repository<AntwortensatzEntity, Long> {

	List<AntwortensatzEntity> findAll();

	AntwortensatzEntity findById(Long id);

	AntwortensatzEntity save(AntwortensatzEntity entity);

}
