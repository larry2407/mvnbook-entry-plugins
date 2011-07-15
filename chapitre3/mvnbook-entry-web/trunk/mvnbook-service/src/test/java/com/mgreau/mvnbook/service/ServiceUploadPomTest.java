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
public class ServiceUploadPomTest {

	@Before
	public void setUp() {
	}

	@After
	public final void tearDown() {
	}
	
	/**
	 * Test du service pour charger et sauvegarder un POM.
	 */
	@Test
	public void testLoadPom() {
		ServiceLoadPom service = new ServiceLoadPom(null);
		Category category = new Category("Packaging", "Package un projet");
		Plugin pHelp = null;
		try {
			pHelp = service.loadByPomFile("maven-help-plugin-2.1.1.pom", category, false);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
		assertNotNull("plugin HELP non chargé." , pHelp);
		assertEquals("maven-help-plugin", pHelp.getArtifactId());
		assertEquals("2.1.1", pHelp.getVersions().get(0).getVersion());
	}
	
	/**
	 * Test le chargement du fichier XML *.pom en POJO pour l'ajout en BDD.
	 */
	@Test
	public void testModelToPlugin() {
		ServiceLoadPom service = new ServiceLoadPom(null);
		Category category = new Category("Packaging", "Package un projet");

		try {
			JAXBContext jc = JAXBContext
					.newInstance("org.apache.maven.pom._4_0");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream("maven-help-plugin-2.1.1.pom");
			Model model = (Model) ((JAXBElement<Model>) unmarshaller
					.unmarshal(in)).getValue();
			assertTrue("Maven Help Plugin".equals(model.getName()));
			
			Plugin plugin = service.pomModelToPlugin(model, category);
			assertEquals("maven-help-plugin", plugin.getArtifactId());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
