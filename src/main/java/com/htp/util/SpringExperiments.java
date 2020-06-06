package com.htp.util;

import com.htp.dao.UserDao;
import com.htp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SpringExperiments {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext("com.htp");

		//Получаем экземпляр из контекста по имени
		UserDao userDaoImpl = (UserDao) context.getBean("userDaoImpl");
		//Получаем экземпляр из контекста по классу
		UserDao userDaoImplByClassName = context.getBean(UserDao.class);

		System.out.println("--------------findAll");
		List<User> allUsers = userDaoImpl.findAll();
		for (User user : allUsers) {
			System.out.printf(" %5s : %20s\n", user.getId(), user.getName());
		}

		System.out.println("--------------findOne");
		User user = userDaoImpl.findOne(4);
		System.out.println(user);



	}
}
