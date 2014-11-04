package com.manuv.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Extender classes of this class have a free CRUD guaranteed!
 * 
 * @author emanuele.ivaldi@gmail.com
 *
 * @param <T>
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	public BaseDaoImpl(Class<T> type) {
		this.type = type;
	}

	@Override
	public T get(Long id) {
		if (id == null) {
			return null;
		} else {
			return this.entityManager.find(type, id);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return entityManager.createQuery("select o from " + type.getName() + " o").getResultList();
	}

	@Override
	public void save(T obj) {
		logger.debug("persisting object of type: " + type);
		entityManager.persist(obj);
	}
	
	@Override
	public void merge(T obj) {
	
		entityManager.merge(obj);
	}

	@Override
	public void remove(T obj) {
		entityManager.remove(obj);
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Class<T> type;
	protected EntityManager entityManager;
	private final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

}
