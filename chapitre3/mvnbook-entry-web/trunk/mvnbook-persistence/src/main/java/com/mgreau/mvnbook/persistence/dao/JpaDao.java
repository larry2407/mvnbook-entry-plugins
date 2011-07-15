package com.mgreau.mvnbook.persistence.dao;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

/**
 * DAO por les opérations communes.
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 * 
 */
public class JpaDao implements Dao {

	private final Provider<EntityManager> em;
	protected EntityManager lastEm;

	@Inject
	public JpaDao(Provider<EntityManager> em) {
		this.em = em;
	}

	@Transactional
	public <T> void persist(T entity) {
		lastEm = em.get();
		lastEm.persist(entity);
	}

	@Transactional
	public <T> void merge(T entity) {
		lastEm = em.get();
		lastEm.merge(entity);
	}

	@Transactional
	public <T> T find(Class<T> clazz, Object key) {
		lastEm = em.get();
		return lastEm.find(clazz, key);
	}

	@Transactional
	public <T> boolean contains(T entity) {
		if (null == lastEm) {
			lastEm = em.get();
		}
		return lastEm.contains(entity);
	}

	@Transactional
	public <T> void remove(T entity) {
		lastEm = em.get();
		lastEm.remove(entity);
	}
}