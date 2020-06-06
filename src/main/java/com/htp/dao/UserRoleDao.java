package com.htp.dao;

import com.htp.domain.UserRole;

import java.util.List;
import java.util.Optional;

/**
 * interface to access l_user_roles table
 * */
public interface UserRoleDao {
	List<UserRole> findAll();

	List<UserRole> search(String paramSearch);

	Optional<UserRole> findById(long roleID);

	UserRole findOne(long roleID);

	UserRole save(UserRole role);

	UserRole update(UserRole role);

	int Delete(UserRole role);

	int insertBatch(List<UserRole> UserRoless);
}
