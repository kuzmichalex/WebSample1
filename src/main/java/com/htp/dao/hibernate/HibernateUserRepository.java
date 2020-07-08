//JD18 hibernate

package com.htp.dao.hibernate;

import com.htp.domain.hibernate.HibernateUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository("userHibernateRepository")
public class HibernateUserRepository implements HibernateUserDao {
	private SessionFactory sessionFactory;

	private EntityManagerFactory entityManagerFactory;

	public HibernateUserRepository(SessionFactory sessionFactory, EntityManagerFactory entityManagerFactory) {
		this.sessionFactory = sessionFactory;
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public List<HibernateUser> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.createQuery("select user from HibernateUser user order by user.id asc", HibernateUser.class).getResultList();
	}

	@Override
	public List<HibernateUser> search(String searchParam) {
		return null;
	}

	@Override
	public Optional<HibernateUser> findById(Long userId) {
		return Optional.empty();
	}

	@Override
	public HibernateUser findOne(Long userId) {
		try (Session session = sessionFactory.openSession()) {
			return session.find(HibernateUser.class, userId);
		}
	}

	@Override
	public HibernateUser save(HibernateUser user) {
		try (Session session = sessionFactory.openSession()) {
			session.saveOrUpdate(user);
			return user;
		}
	}

	@Override
	public HibernateUser update(HibernateUser user) {
		return save(user);
	}

	@Override
	public int delete(HibernateUser user) {
		return 0;
	}
}
