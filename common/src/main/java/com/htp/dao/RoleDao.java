package com.htp.dao;


import com.htp.domain.Role;

import java.util.List;
import java.util.Optional;

/**
 * interface to access m_users table
 * */
public interface RoleDao extends GenericDao <Role, Long>{

	Optional<Role> findByRoleName(String name);

	List<Role> findRolesByUser(long userId);
}
