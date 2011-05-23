package com.mgreau.mvnbook.persistence.dao;

/**
 * Interface des DAO pour la persistance des objets métiers.
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 *
 */
public interface Dao {
	
      <E> void persist(E entity);
      <E> void remove(E entity);
      <E> E find(Class<E> clazz, Object id);
}