package com.manuv.persistence.dao;

import java.util.List;

public interface BaseDao<T> {

	T get(Long id);

	List<T> getAll();

	void save(T obj);

	void remove(T obj);
	
	void merge(T obj);

}
