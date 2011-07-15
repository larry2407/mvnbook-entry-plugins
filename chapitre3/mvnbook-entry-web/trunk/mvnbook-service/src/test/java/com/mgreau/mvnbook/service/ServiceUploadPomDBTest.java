package com.mgreau.mvnbook.service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.apache.maven.pom._4_0.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.mgreau.mvnbook.persistence.dao.JpaDao;
import com.mgreau.mvnbook.persistence.model.Category;
import com.mgreau.mvnbook.persistence.model.IssueManagement;
import com.mgreau.mvnbook.persistence.model.Organization;
import com.mgreau.mvnbook.persistence.model.Plugin;
import com.mgreau.mvnbook.persistence.model.PluginVersion;
import com.mgreau.mvnbook.persistence.model.Prerequisite;
import com.mgreau.mvnbook.persistence.model.Repository;

/**
 * Tests du service d'Upload d'un POM.
 * 
 * @author mgreau
 *
 */
public class ServiceUploadPomDBTest {

	private Injector injector;
	
	private Category category;
	
	/**
	 * Initialisation de Guice
	 */
	@Before
	public void setUp() {
		injector = Guice.createInjector(new JpaPersistModule("testUnit"));

		injector.getInstance(PersistService.class).start();
		injector.getInstance(UnitOfWork.class).begin();
		initDatas();
	}

	@After
	public final void tearDown() {
		injector.getInstance(UnitOfWork.class).end();
		injector.getInstance(EntityManagerFactory.class).close();
	}
	
	/**
	 * Données nécessaires pour le test.
	 */
	private void initDatas(){
		JpaDao dao = injector.getInstance(JpaDao.class);
		category = new Category("Packaging", "Package un projet");
		dao.persist(category);
	}
	
	//@Test
	public void testLoadPomAndSave() {
		ServiceLoadPom service = injector.getInstance(ServiceLoadPom.class);
		Plugin pHelp = null;
		Plugin pJar = null;
		try {
			pHelp = service.loadByPomFile("maven-help-plugin-2.1.1.pom", category, true);
			pJar = service.loadByPomFile("maven-jar-plugin-2.3.1.pom", category, true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
		assertTrue("plugin HELP non créé." , pHelp!=null && pHelp.getId() != null);
		assertTrue("plugin JAR non créé." , pJar!=null && pJar.getId() != null);
	}
}
