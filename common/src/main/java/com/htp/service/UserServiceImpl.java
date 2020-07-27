package com.htp.service;

import com.htp.dao.jdbctemplate.RoleDao;
import com.htp.dao.jdbctemplate.UserDao;
import com.htp.domain.Role;
import com.htp.domain.User;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.exceptions.InvalidUserRegistrationDataException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements userService {

	private final UserDao userDao;
	private final RoleDao roleDao;

	//@Qualifier("userRepositoryJdbcTemplate" нужен, чтобы заавтовайрилось.
	public UserServiceImpl(@Qualifier("userRepositoryJdbcTemplate") UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public Optional<User> findByLogin(String login) {
		return userDao.findByLogin(login);
	}

	public Optional<User> findById(Long userId) {
		return userDao.findById(userId);
	}

	@Override
	public User findOne(Long userId) {
		return userDao.findOne(userId);
	}

	@Override
	public User save(User user) {
		final User savedUser;
		try {
			savedUser = userDao.save(user);
		} catch (DuplicateKeyException e) {
			throw new InvalidUserRegistrationDataException("The user login '" + user.getLogin() + "' already exists");
		}
		return savedUser;
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	public int delete(User user) {
		return userDao.delete(user);
	}

	public Optional<User> findById(long itemId) {
		final Optional<User> byId = userDao.findById(itemId);
		if (byId.isEmpty())
			throw new EntityNotFoundException("User ID = '" + itemId + "' not found");
		return byId;
	}

	@Override
	public List<Role> getUserRoles(Long userId) {
		return roleDao.findRolesByUser(userId);
	}

	public int insertBatch(List<User> items) {
		return userDao.insertBatch(items);
	}

}
