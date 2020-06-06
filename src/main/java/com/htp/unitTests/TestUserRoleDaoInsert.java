package com.htp.unitTests;

import com.htp.dao.UserDao;
import com.htp.dao.UserRoleDao;
import com.htp.domain.User;
import com.htp.domain.UserRole;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class TestUserRoleDaoInsert {
	private static ApplicationContext context;
	static {
		context = new AnnotationConfigApplicationContext("com.htp");
	}

	@Test
	public void userDaoSaveAndFinOne() {
		UserRoleDao userRoleDaoImpl = context.getBean(UserRoleDao.class);
		UserRole testUserRole = new UserRole();
		testUserRole.setUserId(1);
		testUserRole.setRoleId(2);

		UserRole savedUser = userRoleDaoImpl.save(testUserRole);

//		assertEquals("Incorrect ", testUser.getName(), savedUser.getName());
//		assertEquals("Incorrect ", testUser.getLogin(), savedUser.getLogin());
//		assertEquals("Incorrect ", testUser.getBirthDate(), savedUser.getBirthDate());
//		assertEquals("Incorrect ", testUser.getPassword(), savedUser.getPassword());
//		userDaoImpl.Delete(savedUser);
	}
}
