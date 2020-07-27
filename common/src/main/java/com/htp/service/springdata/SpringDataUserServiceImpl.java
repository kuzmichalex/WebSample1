package com.htp.service.springdata;

import com.htp.dao.springdata.UserRepository;
import com.htp.domain.hibernate.HibernateUser;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SpringDataUserServiceImpl implements SpringDataUserService {
	private final UserRepository userRepository;

	public SpringDataUserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Optional<HibernateUser> findByLogin(String login) {
		try {
			return Optional.ofNullable(userRepository.findByLoginEquals(login));
		} catch (NoSuchElementException e) {
			return Optional.empty();
		}
	}
}
