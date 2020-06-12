package com.htp.dao;


import com.htp.domain.Training;

import java.util.List;

public interface TrainingDao extends GenericDao<Training, Long> {

	public int insertBatch(List<Training> trainings);

}
