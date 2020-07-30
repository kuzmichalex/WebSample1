package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateGroup;
import com.htp.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<HibernateGroup, Long>,
		JpaRepository<HibernateGroup, Long>,
		PagingAndSortingRepository<HibernateGroup, Long> {

	List<HibernateGroup> findAll();

	HibernateUser save(HibernateUser user);

}


