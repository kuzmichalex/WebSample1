package com.htp.dao;


import com.htp.domain.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingDao {
	List<Training> findAll();

	List<Training> search(String paramSearch);

	Optional<Training> findById(long userID);

	Training findOne(long userID);

	Training save(Training training);

	Training update(Training training);

	int Delete(Training training);

	int insertBatch(List<Training> trainings);

}
