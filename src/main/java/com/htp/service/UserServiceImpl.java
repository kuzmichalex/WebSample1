package com.htp.service;

import com.htp.dao.UserDao;
import com.htp.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

	private UserDao userDao;

	//@Qualifier("userRepositoryJdbcTemplate" нужен, чтобы заавтовайрилось.
	public UserServiceImpl(@Qualifier("userRepositoryJdbcTemplate")UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public List<User> search(String searchParam) {
		return null;
	}

	@Override
	public Optional<User> findById(Long userId) {
		return Optional.empty();
	}

	@Override
	public User findOne(Long userId) {
		return userDao.findOne(userId);
	}

	@Override
	public User save(User user) {
		return null;
	}

	@Override
	public User update(User user) {
		return null;
	}

	@Override
	public int delete(User user) {
		return 0;
	}

	@Override
	public int insertBatch(List<User> items) {
		return 0;
	}

	@Override
	public List<User> search(long itemId) {
		return null;
	}
}
