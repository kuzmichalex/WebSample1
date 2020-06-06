package com.htp.unitTests;

import com.htp.dao.RoleDao;
import com.htp.domain.Role;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TestRoleDao {
	private static ApplicationContext context;
	static {
		context = new AnnotationConfigApplicationContext("com.htp");
	}
	@Test
	public void roleDaoSaveAndFinOne() {
		RoleDao roleDao = context.getBean(RoleDao.class);
		Role testRole = getRandomRole();
		Role savedRole = roleDao.save(testRole);
		assertEquals("Incorrect ", testRole.getRoleName(), savedRole.getRoleName());
		roleDao.Delete(savedRole);
	}

	@Test
	public void roleDaoBatchInsertAndSearch(){
		final int testSize = 33;
		RoleDao roleDao = context.getBean(RoleDao.class);
		List<Role> testroles = new ArrayList<>();
		Role firstSavedrole = roleDao.save(getRandomRole());
		for (int i = 0; i <testSize ; i++) {
			testroles.add(getRandomRole());
		}
		final int countSaved = roleDao.insertBatch(testroles);
		assertEquals("incorrect number of saved records", testSize, countSaved);
/*
		попробуем прибрать за собой, удалив пользователей с ID firstSavedrole;
		Да уж, тест себе такой. Если что-то сломается, то в базе может остаться мусор.
*/
		List<Role> search = roleDao.search(Long.toString(firstSavedrole.getId()));
		assertEquals("incorrect number of records found", search.size(), testSize);
		for (Role role : search) {
			roleDao.Delete(role);
		}
		roleDao.Delete(firstSavedrole);
	}

	private Role getRandomRole() {
		Random random = new Random();
		random.nextDouble();
		int randomValue = random.nextInt();
		Role retRole = new Role();
		retRole.setRoleName("test_role_" + randomValue);
		return retRole;
	}
}
