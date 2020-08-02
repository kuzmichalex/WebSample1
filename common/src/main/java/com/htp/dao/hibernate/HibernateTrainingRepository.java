package com.htp.dao.hibernate;

import com.htp.domain.hibernate.HibernateTraining;

import java.util.List;
import java.util.Optional;

public interface HibernateTrainingRepository {

	List<HibernateTraining> criteriaFind(Optional<String> name, Optional<String[]> feature);

}
