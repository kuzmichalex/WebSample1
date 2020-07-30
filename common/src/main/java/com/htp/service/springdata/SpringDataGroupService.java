package com.htp.service.springdata;

import com.htp.domain.hibernate.HibernateGroup;

import java.util.List;

public interface SpringDataGroupService {
	List<HibernateGroup> findAll();

	HibernateGroup save(HibernateGroup group);
}
