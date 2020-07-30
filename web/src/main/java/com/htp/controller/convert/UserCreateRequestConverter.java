package com.htp.controller.convert;

import com.htp.controller.request.UserCreateRequest;
import com.htp.dao.springdata.RoleRepository;
import com.htp.domain.hibernate.HibernateRole;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;

@Component
public class UserCreateRequestConverter extends UserRequestConverter<UserCreateRequest, HibernateUser> {
	private final RoleRepository roleRepository;

	public UserCreateRequestConverter(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public HibernateUser convert(UserCreateRequest request) {

		HibernateUser user = new HibernateUser();
		final Optional<HibernateRole> role = roleRepository.findByRoleName("ROLE_USER");
		if (role.isPresent()) {
			HashSet<HibernateRole> roles = new HashSet<>();
			roles.add(role.get());
			user.setRoles(roles);
			return doConvert(user, request);
		}
		;
		throw new EntityNotFoundException("Can't create user :( No role");
	}

}
