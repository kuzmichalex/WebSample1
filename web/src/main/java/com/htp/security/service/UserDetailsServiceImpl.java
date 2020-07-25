//security

package com.htp.security.service;

import com.htp.dao.jdbctemplate.RoleDao;
import com.htp.dao.jdbctemplate.UserDao;
import com.htp.domain.Role;
import com.htp.domain.User;
import com.htp.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*Реазлизация Authentication provider
 * его задача - вытянуть по определёненым данным информацию о пользователе из базы
 *
 * */

@Service
@Qualifier("userDetailServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserDao userRepository;
	private final RoleDao roleRepository;

	public UserDetailsServiceImpl(UserDao userRepository, RoleDao roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userLogin)  {

		final Optional<User> byLogin = userRepository.findByLogin(userLogin);
		if (byLogin.isEmpty()) throw new EntityNotFoundException("User witch login " + userLogin + " not found");

		final User user = byLogin.get();
		final List<Role> rolesByUser = roleRepository.findRolesByUser(user.getId());
		final String authorities = rolesByUser.stream().map(Role::getRoleName).collect(Collectors.joining(","));
		return new org.springframework.security.core.userdetails.User(
				user.getLogin(),
				user.getPassword(),
				AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
		);
	}
}
