package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeatureRepository extends CrudRepository<HibernateFeature, Long>,
		JpaRepository<HibernateFeature, Long> {
	@Override
	List<HibernateFeature> findAll();
}
