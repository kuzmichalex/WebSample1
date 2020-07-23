package com.htp.controller.convert;

import com.htp.controller.request.UserUpdateRequestC;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class UserUpdateRequestConverter extends UserRequestConverter<UserUpdateRequestC, HibernateUser> {

	@Override
	public HibernateUser convert(UserUpdateRequestC request) {
		HibernateUser hibernateUser = ofNullable(entityManager.find(HibernateUser.class, request.getUserId())).
				orElseThrow(ResourceNotFoundException::new);
		return doConvert(hibernateUser, request);
	}
}

