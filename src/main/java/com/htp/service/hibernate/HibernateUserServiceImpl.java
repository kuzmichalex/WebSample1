package com.htp.service.hibernate;

import com.htp.dao.hibernate.HibernateUserDao;
import com.htp.domain.hibernate.HibernateUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HibernateUserServiceImpl implements HibernateUserService {

	private final HibernateUserDao hibernateUserDao;

	public HibernateUserServiceImpl(HibernateUserDao hibernateUserDao) {
		this.hibernateUserDao = hibernateUserDao;
	}


	@Override
	public List<HibernateUser> findAll(int pageNum, int pageSize) {
		return hibernateUserDao.findAll(pageNum, pageSize);
	}

	@Override
	public Optional<HibernateUser> findById(Long userId) {
		return hibernateUserDao.findById(userId);
	}

	@Override
	public Optional<HibernateUser> findByLogin(String login) {
		return hibernateUserDao.findByLogin(login);
	}

	@Override
	public HibernateUser findOne(Long userId) {
		return hibernateUserDao.findOne(userId);
	}

	@Override
	public HibernateUser save(HibernateUser user) {
		return hibernateUserDao.save(user);
	}

	@Override
	public HibernateUser update(HibernateUser user) {
		return hibernateUserDao.save(user);
	}

	@Override
	public int delete(HibernateUser user) {
		return hibernateUserDao.delete(user);
	}
}
