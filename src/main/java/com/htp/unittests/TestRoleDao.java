package com.htp.unittests;

import com.htp.dao.RoleDao;
import com.htp.domain.Role;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TestRoleDao {
	private static final ApplicationContext context;
	Random random = new Random();
	static {
		context = new AnnotationConfigApplicationContext("com.htp");
	}

	@Test
	public void saveFindOneDelete() {
		RoleDao roleDao = context.getBean(RoleDao.class);
		Role testRole = getRandomRole();
		Role savedRole = roleDao.save(testRole);
		assertEquals("Incorrect ", testRole.getRoleName(), savedRole.getRoleName());
		roleDao.delete(savedRole);
	}

	private Role getRandomRole() {
		random.nextDouble();
		int randomValue = random.nextInt();
		Role retRole = new Role();
		retRole.setRoleName("test_role_" + randomValue);
		return retRole;
	}
}
