package com.htp.unitTests;

import com.htp.dao.TrainingDao;
import com.htp.domain.Training;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class TestTrainingDao {
	private static ApplicationContext context;
	static {
		context = new AnnotationConfigApplicationContext("com.htp");
	}
	@Test
	public void testTrainingFindAll(){
		return;
//		TrainingDao traningDao = context.getBean(TrainingDao.class);
//		List<Training> all = traningDao.findAll();
//		for (Training training1 : all) {
//			System.out.println(training1);
//		}
	}
	@Test
	public void testTrainingSave(){
		/*TrainingDao trainingDao = context.getBean(TrainingDao.class);
		Training training = new Training();
		training.setName("Running");
		training.setDescription("Run, Run, Run!");
		training.setUserAuthorId(1);

		trainingDao.save(training);*/

	}
}
