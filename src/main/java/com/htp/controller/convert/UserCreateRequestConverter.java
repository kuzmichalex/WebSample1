package com.htp.controller.convert;

import com.htp.controller.request.UserCreateRequest;
import com.htp.domain.hibernate.HibernateUser;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserCreateRequestConverter extends UserRequestConverter<UserCreateRequest, HibernateUser> {

	@Override
	public HibernateUser convert(UserCreateRequest request) {

		HibernateUser user = new HibernateUser();

		//HibernateRole hibernateRole = new HibernateRole();
		HashSet<HibernateUser> usersSet = new HashSet<>();
		usersSet.add(user);
		//hibernateRole.setUsers(usersSet);
		//user.setRoles(Collections.singleton(hibernateRole));
		return doConvert(user, request);
	}
}
