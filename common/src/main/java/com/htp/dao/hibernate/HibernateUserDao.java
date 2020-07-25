//JD18 hibernate

package com.htp.dao.hibernate;

import com.htp.domain.hibernate.HibernateUser;

import java.util.List;
import java.util.Optional;

public interface HibernateUserDao {
	List<HibernateUser> findAll(int pageNum, int pageSize);

	Optional<HibernateUser> findById(Long userId);

	Optional<HibernateUser> findByLogin(String login);

	HibernateUser findOne(Long userId);

	HibernateUser save(HibernateUser user);

	HibernateUser update(HibernateUser user);

	int delete(HibernateUser user);
}
