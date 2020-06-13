package com.htp.unittests;

import com.htp.dao.TrainingDao;
import com.htp.domain.Training;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class TestTrainingDao {
	private static final ApplicationContext context;
	static {
		context = new AnnotationConfigApplicationContext("com.htp");
	}
	@Test
	public void testTrainingFindAll(){
		TrainingDao traningDao = context.getBean(TrainingDao.class);
		List<Training> all = traningDao.findAll();
		assertFalse("training count too small" + all.size(), all.isEmpty());
	}
	@Test
	public void testTrainingSaveDelete(){
		TrainingDao trainingDao = context.getBean(TrainingDao.class);
		Training training = new Training();
		training.setName("Running");
		training.setDescription("Run, Run, Run!");
		training.setUserAuthorId(1);
		final Training saved = trainingDao.save(training);
		assertEquals("Incorrect saved name", training.getName(), saved.getName());
		assertEquals("Incorrect saved description", training.getDescription(), saved.getDescription());
		assertEquals("Incorrect saved author id", training.getUserAuthorId(), saved.getUserAuthorId());
		trainingDao.delete(saved);
	}
}
