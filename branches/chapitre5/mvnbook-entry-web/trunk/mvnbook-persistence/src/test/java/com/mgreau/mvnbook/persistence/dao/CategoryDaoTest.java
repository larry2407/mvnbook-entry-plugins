package com.mgreau.mvnbook.persistence.dao;

import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.mgreau.mvnbook.persistence.model.Category;

/**
 * A test around providing sessions (starting, closing etc.)
 * 
 * @author Maxime Gréau
 */
public class CategoryDaoTest {
	private Injector injector;

	/**
	 * Initialisation de Guice
	 */
	@Before
	public void setUp() {

		injector = Guice.createInjector(new JpaPersistModule("testUnit"));

		// startup persistence
		injector.getInstance(PersistService.class).start();
		injector.getInstance(UnitOfWork.class).begin();
	}

	@After
	public final void tearDown() {
		injector.getInstance(UnitOfWork.class).end();
		injector.getInstance(EntityManagerFactory.class).close();
	}

	@Test
	public void testCreateCategory() {
		JpaDao dao = injector.getInstance(JpaDao.class);
		
		Category c = new Category("Packaging", "Créer une archive d'un projet");
		dao.persist(c);
		Category c1 = dao.find(Category.class, new Long(1));

		assertNotNull("Pas de categorie créée.", c1);
		assertEquals("Catégorie non créée", c1.getTitle(), "Packaging");
	}

}