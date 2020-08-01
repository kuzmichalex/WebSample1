package com.htp.service.hibernate;

import com.htp.dao.hibernate.HibernateUserRepository;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HibernateUserServiceImpl implements HibernateUserService {

	private final HibernateUserRepository userRepository;

	public HibernateUserServiceImpl(HibernateUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<HibernateUser> findAll(int pageNum, int pageSize) {
		return userRepository.findAll(pageNum, pageSize);
	}

	@Override
	public HibernateUser findById(Long userId) {
		final Optional<HibernateUser> byId = userRepository.findById(userId);
		if (byId.isEmpty())
			throw new EntityNotFoundException("User witch id = " + userId + "not found");
		else return byId.get();
	}

	@Override
	public Optional<HibernateUser> findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	@Override
	public HibernateUser findOne(Long userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public HibernateUser save(HibernateUser user) {
		return userRepository.save(user);
	}

	@Override
	public HibernateUser update(HibernateUser user) {
		return userRepository.save(user);
	}

	@Override
	public int delete(HibernateUser user) {
		return userRepository.delete(user);
	}
}
