package com.htp.dao;


import com.htp.domain.Role;

import java.util.List;
import java.util.Optional;

/**
 * interface to access m_users table
 * */
public interface RoleDao {
	List<Role> findAll();

	List<Role> search(String paramSearch);

	Optional<Role> findById(long roleID);

	Role findOne(long roleID);

	Role save(Role role);

	Role update(Role role);

	int Delete(Role role);

	int insertBatch(List<Role> Roles);
}
