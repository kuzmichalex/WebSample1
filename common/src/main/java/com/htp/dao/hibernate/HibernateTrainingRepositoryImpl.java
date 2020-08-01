package com.htp.dao.hibernate;

import com.htp.dao.springdata.FeatureRepository;
import com.htp.domain.hibernate.HibernateFeature;
import com.htp.domain.hibernate.HibernateFeature_;
import com.htp.domain.hibernate.HibernateTraining;
import com.htp.domain.hibernate.HibernateTraining_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository("hibernateTrainingRepository")
public class HibernateTrainingRepositoryImpl implements HibernateTrainingRepository {

	private final FeatureRepository featureRepository;

	private final SessionFactory sessionFactory;

	private final EntityManagerFactory entityManagerFactory;

	public HibernateTrainingRepositoryImpl(FeatureRepository featureRepository, SessionFactory sessionFactory, EntityManagerFactory entityManagerFactory) {
		this.featureRepository = featureRepository;
		this.sessionFactory = sessionFactory;
		this.entityManagerFactory = entityManagerFactory;
	}


	@Override
	public List<HibernateTraining> Find() {
		final String query = "select t from HibernateTraining as t inner join t.features as f" +
				" where f.name like :name ";


		try (Session session = sessionFactory.openSession()) {
			final Query findQuery = session.createQuery(query);
			findQuery.setParameter("name", "A%");
			return findQuery.list();
		}

	}

	@Override
	public List<HibernateTraining> criteriaFind() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder(); //create query and query parts
		CriteriaQuery<HibernateTraining> cq = cb.createQuery(HibernateTraining.class);

		final Root<HibernateTraining> root = cq.from(HibernateTraining.class);
		Join<HibernateTraining, HibernateFeature> join = root.join(HibernateTraining_.features);

		final Predicate trainingNameLike = cb.like(root.get(HibernateTraining_.name), "%Ice%");

		List<Predicate> orFeatures = new ArrayList<>();
		orFeatures.add(cb.or(cb.like(join.get(HibernateFeature_.name), "%%")));

		cq.select(root).
				distinct(true).
				where(trainingNameLike,
						cb.and(orFeatures.toArray(new Predicate[0]))
				);
		final TypedQuery<HibernateTraining> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public List<HibernateTraining> criteriaFind2() {
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final CriteriaQuery<HibernateTraining> cq = entityManager.getCriteriaBuilder().createQuery(HibernateTraining.class);
		Root<HibernateTraining> features = cq.from(HibernateTraining.class);
		Join<HibernateTraining, HibernateFeature> join = features.join(HibernateTraining_.features);
		cq.distinct(true);

		cq.where(
				entityManager
						.getCriteriaBuilder()
						.and(
								entityManager
										.getCriteriaBuilder()
										.like(features.get(HibernateTraining_.name), "%i%"),
								entityManager
										.getCriteriaBuilder()
										.like(join.get(HibernateFeature_.name), "%Ae%"))
		);

		javax.persistence.Query q = entityManager.createQuery(cq);

		return q.getResultList();

	}
}
