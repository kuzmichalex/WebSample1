package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends CrudRepository<HibernateActivity, Long>,
		JpaRepository<HibernateActivity, Long> {

	@Query(value = "select ac from HibernateActivity ac" +
			" where ac.userId = :user and ac.isDeleted = false")
	List<HibernateActivity> findAllByUser(@Param("user") Long userId);

}
