package com.htp.dao.hibernate;

import com.htp.domain.hibernate.HibernateFeature;
import com.htp.domain.hibernate.HibernateFeature_;
import com.htp.domain.hibernate.HibernateTraining;
import com.htp.domain.hibernate.HibernateTraining_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("hibernateTrainingRepository")
public class HibernateTrainingRepositoryImpl implements HibernateTrainingRepository {

	private final EntityManagerFactory entityManagerFactory;

	public HibernateTrainingRepositoryImpl(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}


	@Override
	public List<HibernateTraining> criteriaFind(Optional<String> name, Optional<String[]> feature) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder(); //create query and query parts
		CriteriaQuery<HibernateTraining> cq = cb.createQuery(HibernateTraining.class);

		//Нужен join c таблицей признаков тренировок.
		final Root<HibernateTraining> root = cq.from(HibernateTraining.class);
		Join<HibernateTraining, HibernateFeature> join = root.join(HibernateTraining_.features);

		//Предикат для поиска по наименованию тренинга
		final Predicate trainingNameLike = cb.like(root.get(HibernateTraining_.name),
				(name.map(s -> "%" + s + "%").orElse("%")));

		//Набор предикатов для поиска по признакам
		List<Predicate> orFeatures = new ArrayList<>();
		if (feature.isPresent() && feature.get().length > 0) {
			for (String s : feature.get()) {
				orFeatures.add(cb.or(cb.like(join.get(HibernateFeature_.name), "%" + s + "%")));
			}
		} else {
			orFeatures.add(cb.or(cb.like(join.get(HibernateFeature_.name), "%")));
		}

		cq.select(root).
				distinct(true).
				where(trainingNameLike,
						cb.or(orFeatures.toArray(new Predicate[0]))
				);
		final TypedQuery<HibernateTraining> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

}
