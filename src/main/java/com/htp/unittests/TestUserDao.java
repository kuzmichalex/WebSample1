package com.htp.unittests;

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
	private static final ApplicationContext context;
	Random random = new Random();
	static {
		context = new AnnotationConfigApplicationContext("com.htp");
	}

	@Test
	public void saveFinOneUpdateDelete() {
		UserDao userDaoImpl = context.getBean(UserDao.class);
		User testUser = getRandomUser();
		User savedUser = userDaoImpl.save(testUser);
		assertEquals("Incorrect saved name", testUser.getName(), savedUser.getName());
		assertEquals("Incorrect saved login", testUser.getLogin(), savedUser.getLogin());
		assertEquals("Incorrect saved birthday", testUser.getBirthDate(), savedUser.getBirthDate());
		assertEquals("Incorrect saved password", testUser.getPassword(), savedUser.getPassword());

		savedUser.setName(savedUser.getName()+"_2");
		savedUser.setLogin(savedUser.getLogin()+"_2");
		savedUser.setPassword(savedUser.getPassword()+"_2");
		final User updatedUser = userDaoImpl.update(savedUser);
		assertEquals("Incorrect updated name", savedUser.getName(), updatedUser.getName());
		assertEquals("Incorrect updated login", savedUser.getLogin(), updatedUser.getLogin());
		assertEquals("Incorrect updated password", savedUser.getPassword(), updatedUser.getPassword());
		userDaoImpl.delete(savedUser);
	}

	@Test
	public void batchInsertSearch(){
//		final int testSize = 33;
//		UserDao userDao = context.getBean(UserDao.class);
//		List<User> testUsers = new ArrayList<>();
//		User firstSavedUser = userDao.save(getRandomUser());
//		for (int i = 0; i <testSize ; i++) {
//			testUsers.add(getRandomUser());
//		}
//		final int countSaved = userDao.insertBatch(testUsers);
//		assertEquals("incorrect number of saved records", testSize, countSaved);
//
//		List<User> search = userDao.search(firstSavedUser.getId());
//		assertEquals("incorrect number of records found", search.size(), testSize);
//		for (User user : search) {
//			userDao.delete(user);
//		}
//		userDao.delete(firstSavedUser);
	}

	private User getRandomUser(){
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
