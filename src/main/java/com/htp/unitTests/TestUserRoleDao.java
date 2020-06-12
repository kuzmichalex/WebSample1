package com.htp.unitTests;

import com.htp.dao.UserRoleDao;
import com.htp.domain.UserRole;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class TestUserRoleDao {
	private static ApplicationContext context;
	static {
		context = new AnnotationConfigApplicationContext("com.htp");
	}

	@Test
	public void userDaoSaveAndFindOne() {
		UserRoleDao userRoleDaoImpl = context.getBean(UserRoleDao.class);
		UserRole testUserRole = new UserRole();
		testUserRole.setUserId(1);
		testUserRole.setRoleId(2);

		UserRole savedUserRole = userRoleDaoImpl.save(testUserRole);
		assertEquals("Incorrect UserRole save", testUserRole.getUserId(), savedUserRole.getUserId());
		assertEquals("Incorrect UserRole save", testUserRole.getRoleId(), savedUserRole.getRoleId());

		//А теперь должна быть ошибка UserRole Error, потому что 2 раза одну роль тому же юзеру запрещает ключ.
		UserRole savedUserRole2 = userRoleDaoImpl.save(testUserRole);
		assertEquals("Incorrect UserRole primary key", savedUserRole2 == null, true);
		userRoleDaoImpl.delete(savedUserRole);

	}
}
