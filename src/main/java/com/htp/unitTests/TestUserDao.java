package com.htp.unitTests;

import com.htp.dao.UserDao;
import com.htp.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TestUserDao {
	private static ApplicationContext context;
	static {
		context = new AnnotationConfigApplicationContext("com.htp");
	}

	@Test
	public void userDaoSaveAndFinOne() {
		UserDao userDaoImpl = context.getBean(UserDao.class);
		User testUser = getRandomUser();
		User savedUser = userDaoImpl.save(testUser);
		assertEquals("Incorrect ", testUser.getName(), savedUser.getName());
		assertEquals("Incorrect ", testUser.getLogin(), savedUser.getLogin());
		assertEquals("Incorrect ", testUser.getBirthDate(), savedUser.getBirthDate());
		assertEquals("Incorrect ", testUser.getPassword(), savedUser.getPassword());
		userDaoImpl.delete(savedUser);
	}

	@Test
	public void userDaoBatchInsertAndSearch(){
		final int testSize = 33;
		UserDao userDao = context.getBean(UserDao.class);
		List<User> testUsers = new ArrayList<>();
		User firstSavedUser = userDao.save(getRandomUser());
		for (int i = 0; i <testSize ; i++) {
			testUsers.add(getRandomUser());
		}
		final int countSaved = userDao.insertBatch(testUsers);
		assertEquals("incorrect number of saved records", testSize, countSaved);
/*
		попробуем прибрать за собой, удалив пользователей с ID firstSavedUser;
		Да уж, тест себе такой. Если что-то сломается, то в базе может остаться мусор.
*/
		List<User> search = userDao.search(Long.toString(firstSavedUser.getId()));
		assertEquals("incorrect number of records found", search.size(), testSize);
		for (User user : search) {
			userDao.delete(user);
		}
		userDao.delete(firstSavedUser);
	}



	private User getRandomUser(){
		Random random = new Random();
		random.nextDouble();
		int randomValue = random.nextInt();

		User retUser = new User();
		retUser.setName("test_name_" + randomValue);
		retUser.setLogin("test_login_" + randomValue);
		retUser.setPassword("test_pass_" + randomValue);
		retUser.setBirthDate(Date.valueOf("2000-01-01"));
		return retUser;
	}

}
