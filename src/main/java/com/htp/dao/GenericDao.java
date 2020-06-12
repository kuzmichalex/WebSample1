package com.htp.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {
	List<T> findAll();

	Optional<T> findById(long itemId);

	T findOne(K itemId);

	T save(T item);

	T update(T item);

	int delete(T item);

}
