//JD18 hibernate

package com.htp.dao.hibernate;

import com.htp.domain.hibernate.HibernateUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository("hibernateUserRepository")
public class HibernateUserRepository implements HibernateUserDao {
	private final SessionFactory sessionFactory;

	private final EntityManagerFactory entityManagerFactory;

	public HibernateUserRepository(SessionFactory sessionFactory, EntityManagerFactory entityManagerFactory) {
		this.sessionFactory = sessionFactory;
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public List<HibernateUser> findAll(int pageNum, int pageSize) {
		final String query = "select user from HibernateUser user order by user.id asc";
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		final TypedQuery<HibernateUser> hibernateUserTypedQuery = entityManager.createQuery(query, HibernateUser.class)
				.setFirstResult(pageNum * pageSize).setMaxResults(pageSize);
		return hibernateUserTypedQuery.getResultList();
	}

	@Override
	public Optional<HibernateUser> findById(Long userId) {
		try (Session session = sessionFactory.openSession()) {
			final HibernateUser hibernateUser = session.find(HibernateUser.class, userId);
			return Optional.ofNullable(hibernateUser);
		}
	}

	@Override
	public Optional<HibernateUser> findByLogin(String login) {

		try (Session session = sessionFactory.openSession()) {
			final HibernateUser hibernateUser = session.find(HibernateUser.class, login);
			return Optional.ofNullable(hibernateUser);
		}
	}

	@Override
	public HibernateUser findOne(Long userId) {
		try (Session session = sessionFactory.openSession()) {
			return session.find(HibernateUser.class, userId);
		}
	}

	@Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public HibernateUser save(HibernateUser user) {
		try (Session session = sessionFactory.openSession()) {
			session.saveOrUpdate(user);
			return user;
		}
	}

	@Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public HibernateUser update(HibernateUser user) {
		return save(user);
	}

	@Override
	public int delete(HibernateUser user) {
		return 0;
	}
}
