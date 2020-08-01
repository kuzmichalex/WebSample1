package com.htp.service.springdata;

import com.htp.dao.springdata.TrainingRepository;
import com.htp.domain.hibernate.HibernateTraining;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringDataTrainingServiceImpl implements SpringDataTrainingService {
	private final TrainingRepository trainingRepository;

	public SpringDataTrainingServiceImpl(TrainingRepository trainingRepository) {
		this.trainingRepository = trainingRepository;
	}

	@Override
	public List<HibernateTraining> findAll() {
		return trainingRepository.findAll();
	}
}
