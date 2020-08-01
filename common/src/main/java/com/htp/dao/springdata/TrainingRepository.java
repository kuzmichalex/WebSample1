package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainingRepository extends CrudRepository<HibernateTraining, Long>,
		JpaRepository<HibernateTraining, Long> {

	@Override
	List<HibernateTraining> findAll();

}
