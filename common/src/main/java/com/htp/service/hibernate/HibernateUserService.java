package com.htp.service.hibernate;


import com.htp.domain.hibernate.HibernateUser;

import java.util.List;
import java.util.Optional;

public interface HibernateUserService {
	List<HibernateUser> findAll(int pageNum, int pageSize);

	Optional<HibernateUser> findByLogin(String login);

	HibernateUser findById(Long userId);

	HibernateUser findOne(Long userId);

	HibernateUser save(HibernateUser user);

	HibernateUser update(HibernateUser user);

	int delete(HibernateUser user);

}
