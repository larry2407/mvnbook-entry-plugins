package com.mgreau.mvnbook.webservice.it;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mgreau.mvnbook.webservice.PluginWebServiceImpl;

/**
 * Tests du service d'Upload d'un POM.
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 *
 */
public class PluginWebServiceImplIT {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PluginWebServiceImplIT.class);

	
	@Before
	public void setUp() {
	}

	@After
	public final void tearDown() {
	}
	
	/**
	 * Données nécessaires pour le test.
	 */
	private void initDatas(){
	}
	
	@Test
	public void testCallWS() {
		File pom = new File(this.getClass().getClassLoader()
				.getResource("maven-jar-plugin-2.3.1.pom").getFile());
		PluginWebServiceImpl ws = new PluginWebServiceImpl();
		try {
			String result = ws.addPlugin(pom);
			assertNotNull("Le résultat est null", result);
			assertTrue("Pas de SUCCESS dans le result", result.contains("SUCCESS"));
			LOGGER.info("Resultat WS : " + result);
		} catch (Exception e) {
			fail("erreur");
			LOGGER.error("Appel WS KO", e);
		}
	}
}
