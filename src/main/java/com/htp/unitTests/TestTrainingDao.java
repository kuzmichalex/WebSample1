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
	public void testTrainingSave(){
		TrainingDao traningDao = context.getBean(TrainingDao.class);
		Training training = new Training();
		training.setName("Running");
		training.setName("Run, Run, Run!");
		List<Training> all = traningDao.findAll();
		for (Training training1 : all) {
			System.out.println(training1);
		}

		//traningDao.save(training);
		//training.setUserAuthorId(1);

//		for (User user : userRepositoryJdbcTemplate.findAll()) {
//			System.out.println(user);
//		}
//		traningDao.save(training);
	}
}
