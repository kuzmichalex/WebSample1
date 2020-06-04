package com.htp.dao;

import com.htp.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * interface to access m_users table
 * */
public interface UserDao {
	List<User> findAll();

	List<User> search(String paramSearch);

	Optional<User> findById(long userID);

	User findOne(long userID);

	User save(User user);

	User update(User user);

	int Delete(User user);
}
