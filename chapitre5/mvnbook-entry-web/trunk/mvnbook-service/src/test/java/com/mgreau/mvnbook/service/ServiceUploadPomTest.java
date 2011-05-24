package com.mgreau.mvnbook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.apache.maven.pom._4_0.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mgreau.mvnbook.persistence.model.Category;
import com.mgreau.mvnbook.persistence.model.Plugin;
import com.mgreau.mvnbook.persistence.model.Type;

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
	public void LoadPom() {
		//@TODO Utiliser mockito pour mock de injection
		ServiceLoadPom service = new ServiceLoadPom(null);
		Category category = new Category("Packaging", "Package un projet");
		Plugin pHelp = new Plugin("", "", "maven-help-plugin", "");
		try {
			pHelp = service.loadByPomFile("maven-help-plugin-2.1.1.pom", category, Type.BUILD);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
		assertNotNull("plugin HELP non chargé." , pHelp);
		assertEquals("maven-help-plugin", pHelp.getArtifactId());
		//assertEquals("2.1.1", pHelp.getVersions().get(0).getVersion());
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
			
			Plugin plugin = service.pomModelToPlugin(model, category, Type.BUILD_AND_REPORTING);
			assertEquals("maven-help-plugin", plugin.getArtifactId());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
