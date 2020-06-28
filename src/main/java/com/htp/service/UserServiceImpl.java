package com.htp.service;

import com.htp.dao.UserDao;
import com.htp.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	//@Qualifier("userRepositoryJdbcTemplate" нужен, чтобы заавтовайрилось.
	public UserServiceImpl(@Qualifier("userRepositoryJdbcTemplate") UserDao userDao) {
		this.userDao = userDao;
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
		return userDao.save(user);
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
		return userDao.findById(itemId);
	}

	public int insertBatch(List<User> items) {
		return userDao.insertBatch(items);
	}

}
