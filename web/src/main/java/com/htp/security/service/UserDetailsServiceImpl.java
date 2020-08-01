//security

package com.htp.security.service;

import com.htp.dao.springdata.UserRepository;
import com.htp.domain.hibernate.HibernateRole;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

/*Реазлизация Authentication provider
 * его задача - вытянуть по определёненым данным информацию о пользователе из базы
 *
 * */

@Service
@Qualifier("userDetailServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userLogin) {

		final Optional<HibernateUser> hibernateUser = userRepository.findByLoginEquals(userLogin);
		if (hibernateUser.isEmpty()) throw new EntityNotFoundException("User witch login " + userLogin + " not found");
		HibernateUser user = hibernateUser.get();
		final String authorities = user.getRoles().stream().map(HibernateRole::getRoleName).collect(Collectors.joining(","));

		return new org.springframework.security.core.userdetails.User(
				user.getLogin(),
				user.getPassword(),
				AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
		);
	}
}
