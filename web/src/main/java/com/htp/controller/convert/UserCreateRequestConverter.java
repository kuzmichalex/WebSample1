package com.htp.controller.convert;

import com.htp.controller.request.UserCreateRequest;
import com.htp.dao.springdata.RoleRepository;
import com.htp.domain.hibernate.HibernateRole;
import com.htp.domain.hibernate.HibernateUser;
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

		//HibernateRole hibernateRole = Hi
		//HashSet<HibernateUser> usersSet = new HashSet<>();
		//usersSet.add(user);
		final Optional<HibernateRole> role_user = roleRepository.findByRoleName("ROLE_USER");

		HashSet roles = new HashSet<>();
		roles.add(role_user.get());
		user.setRoles(roles);

		//user.getRoles().add(role_user.get());

		//hibernateRole.setUsers(usersSet);
		//user.setRoles(Collections.singleton(hibernateRole));
		return doConvert(user, request);
	}
}
