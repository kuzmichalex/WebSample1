package com.htp.controller.convert;


import com.htp.controller.request.UserCreateRequest;
import com.htp.domain.hibernate.HibernateUser;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class UserRequestConverter<S, T> extends EntityConverter<S, T> {

	protected HibernateUser doConvert(HibernateUser user, UserCreateRequest request) {

		user.setLogin(request.getLogin());
		user.setName(request.getName());
		user.setBirthDate(request.getBirthDate());
		user.setPassword(request.getPassword());
		return user;
	}
}