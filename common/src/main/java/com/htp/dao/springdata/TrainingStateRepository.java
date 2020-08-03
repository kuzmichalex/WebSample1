package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateTrainingState;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@CacheConfig(cacheNames = {"StateCache"})
public interface TrainingStateRepository extends CrudRepository<HibernateTrainingState, Long>,
		JpaRepository<HibernateTrainingState, Long> {

	@Cacheable
	@Query(value = "select s from HibernateTrainingState s where s.name = 'Planned' and s.isDeleted = false")
	Optional<HibernateTrainingState> findPlannedState();

}
