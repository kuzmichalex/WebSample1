package com.htp.dao;


import com.htp.domain.User;

import java.util.List;


/**
 * interface to access m_users table
 * */
public interface UserDao extends GenericDao <User, Long> {

	int insertBatch(List<User> items);

	public List<User> search(long itemId);
}
