package com.htp.service;

import com.htp.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
	@Override
	public List<User> findAll() {
		return null;
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
		return null;
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
}
