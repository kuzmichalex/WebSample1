package com.htp.dao;



import com.htp.domain.User;

import java.util.List;
import java.util.Optional;


/**
 * interface to access m_users table
 * */
public interface UserDao extends GenericDao <User, Long> {

	Optional<User> findByLogin(String login);

	int insertBatch(List<User> items);
}
