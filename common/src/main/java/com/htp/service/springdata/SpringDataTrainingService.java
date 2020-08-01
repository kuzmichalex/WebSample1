package com.htp.service.springdata;

import com.htp.domain.hibernate.HibernateTraining;

import java.util.List;

public interface SpringDataTrainingService {
	List<HibernateTraining> findAll();
}
