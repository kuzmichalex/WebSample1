package com.htp.dao.hibernate;

import com.htp.domain.hibernate.HibernateTraining;

import java.util.List;

public interface HibernateTrainingRepository {
	public List<HibernateTraining> Find();

	public List<HibernateTraining> criteriaFind();


	public List<HibernateTraining> criteriaFind2();


}
