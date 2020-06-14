package com.htp.service;

import com.htp.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
	List<Role> findAll();

	Optional<Role> findById(Long RoleId);

	Role findOne(Long RoleId);

	Role save(Role Role);

	Role update(Role Role);

	int delete(Role Role);
}
