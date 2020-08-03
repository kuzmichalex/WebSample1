package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateTrainingState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface TrainingStateRepository extends CrudRepository<HibernateTrainingState, Long>,
		JpaRepository<HibernateTrainingState, Long> {
	@Query(value = "select s from HibernateTrainingState s where s.name = 'Planned' and s.isDeleted = false")
	Optional<HibernateTrainingState> findPlannedState();

}
