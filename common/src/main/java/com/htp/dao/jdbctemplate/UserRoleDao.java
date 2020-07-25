package com.htp.dao.jdbctemplate;


import com.htp.domain.UserRole;

/**
 * interface to access l_user_roles table
 * */
public interface UserRoleDao extends GenericDao <UserRole, Long>{

	interface UserRoles extends GenericDao {
	}
}
