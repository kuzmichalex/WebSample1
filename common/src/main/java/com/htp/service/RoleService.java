package com.htp.service;

import com.htp.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
	List<Role> findAll();

	Optional<Role> findById(Long roleId);

	Optional<Role> findByRoleName(String name);

	Optional<Role> findOne(Long roleId);

	List<Role> findRolesByUser(long userId);

	Role save(Role role);

	Role update(Role role);

	int delete(Role role);
}
