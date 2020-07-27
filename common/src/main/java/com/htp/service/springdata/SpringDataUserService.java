package com.htp.service.springdata;

import com.htp.domain.hibernate.HibernateUser;

import java.util.Optional;

public interface SpringDataUserService {
	Optional<HibernateUser> findByLogin(String login);

}
