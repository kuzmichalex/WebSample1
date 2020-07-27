package com.htp.service;

import com.htp.domain.Role;
import com.htp.domain.User;

import java.util.List;
import java.util.Optional;

public interface userService {
	List<User> findAll();

	Optional<User> findById(Long userId);

	Optional<User> findByLogin(String login);

	User findOne(Long userId);

	User save(User user);

	User update(User user);

	int delete(User user);

	List<Role> getUserRoles(Long userId);

}
