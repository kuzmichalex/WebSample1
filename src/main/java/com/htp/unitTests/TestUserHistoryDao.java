package com.htp.unitTests;

import com.htp.dao.UserHistoryDao;
import com.htp.domain.UserHistory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class TestUserHistoryDao {
	private static ApplicationContext context;
	static {
		context = new AnnotationConfigApplicationContext("com.htp");
	}

	@Test
	public void userDaoSaveAndFinOne() {
		UserHistoryDao userDaoImpl = context.getBean(UserHistoryDao.class);
		UserHistory testHistory = getRandomUserHistory();
		UserHistory savedUserHistory = userDaoImpl.save(testHistory);
		assertEquals("Incorrect user id", testHistory.getUserId(), savedUserHistory.getUserId());
		assertEquals("Incorrect date ", testHistory.getDate(), savedUserHistory.getDate());
		assertEquals("Incorrect height", testHistory.getHeight(), savedUserHistory.getHeight());
		assertEquals("Incorrect weight", testHistory.getWeight(), savedUserHistory.getWeight());

		userDaoImpl.Delete(savedUserHistory);
	}

	private UserHistory getRandomUserHistory() {
		UserHistory retValue = new UserHistory();
		retValue.setUserId(1);
		retValue.setDate(Timestamp.valueOf("2000-01-01 01:02:03"));
		retValue.setHeight(180);
		retValue.setWeight(77);
		return retValue;
	}
}
