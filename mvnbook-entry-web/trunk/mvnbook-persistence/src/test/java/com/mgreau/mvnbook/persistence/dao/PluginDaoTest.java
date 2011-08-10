package com.mgreau.mvnbook.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.mgreau.mvnbook.persistence.model.Category;
import com.mgreau.mvnbook.persistence.model.IssueManagement;
import com.mgreau.mvnbook.persistence.model.Organization;
import com.mgreau.mvnbook.persistence.model.Plugin;
import com.mgreau.mvnbook.persistence.model.PluginVersion;
import com.mgreau.mvnbook.persistence.model.Prerequisite;
import com.mgreau.mvnbook.persistence.model.Repository;
import com.mgreau.mvnbook.persistence.model.Type;

/**
 * Test du DAO des plugins.
 * 
 * @author Maxime Gréau (dev@mgreau.com)
 * @author Dhanji R. Prasanna (dhanji@gmail.com)
 */
public class PluginDaoTest {
	private Injector injector;
	
	private JpaDao dao;

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
	
	private Plugin getPlugin(){
		String artifactId = "maven-war-plugin";
		String name = "maven war plugin";
		return getPlugin(name, artifactId);
	}
	
	private Plugin getPlugin(String name, String artifactId){
		Plugin plugin = new Plugin(name,
				"org.apache.maven.plugins", artifactId,
				"Création d'une archive WAR");

		plugin.setInceptionYear(new Date());
		plugin.setIssueManagement(getIssueManagement());
		plugin.setCategory(getCategory());
		plugin.setOrganization(getOrganization());
		List<Repository> repo = new ArrayList<Repository>();
		repo.add(getReleaseRepo());
		repo.add(getSnaphostRepo());
		plugin.setRepositories(repo);
		plugin.setType(Type.BUILD);
		plugin.setUrl("http://maven.apache.org/plugins/maven-war-plugin");
		return plugin;
	}
	
	private Repository getReleaseRepo(){
		Repository r;
		r = dao.find(Repository.class, new Long(1));
		if (r == null)
			r = new Repository("http://repo1.com", true);
		return r;
	}
	
	private Repository getSnaphostRepo(){
		Repository r;
		r = dao.find(Repository.class, new Long(2));
		if (r == null)
			r = new Repository("http://repo2.com", false);
		return r;
	}
	
	private Category getCategory(){
		Category c;
		c = dao.find(Category.class, new Long(1));
		if (c == null)
			c = new Category("Packaging", "Package un projet");
		return c;
	}
	
	private IssueManagement getIssueManagement(){
		IssueManagement im;
		im = dao.find(IssueManagement.class, new Long(1));
		if (im == null)
			im = new IssueManagement("JIRA", "http://jira.com");
		return im;
	}
	
	private Organization getOrganization(){
		Organization org ;
		org = dao.find(Organization.class, new Long(1));
		if (org == null)
			org = new Organization("Apache", "http://apache.org");
		return org;
	}
	

	/**
	 * Création d'un plugin
	 */
	@Test
	public void testCreatePlugin() {
		dao = injector.getInstance(JpaDao.class);

		dao.persist(getPlugin());
		Plugin p = dao.find(Plugin.class, new Long(1));

		assertTrue("plugin non recupére", p != null);
		assertEquals("bad name", p.getName(), "maven war plugin");
	}
	
	/**
	 * Liste de tous les plugins
	 */
	@Test
	public void testFindAllPlugin() {
		dao = injector.getInstance(JpaDao.class);

		dao.persist(getPlugin());
		dao.persist(getPlugin("jar plugin", "maven-jar-plugin"));
		dao.persist(getPlugin("ear plugin", "maven-ear-plugin"));

		Collection<Plugin> plugins = dao.findAll(Plugin.class);
		assertTrue("aucun plugin trouvé", plugins != null);
		assertTrue("", plugins.size() == 3);
	}
	
	/**
	 * Suppression d'un plugin
	 */
	@Test
	public void testRemovePlugin() {
		dao = injector.getInstance(JpaDao.class);

		dao.persist(getPlugin());
		Plugin p = dao.find(Plugin.class, new Long(1));
		assertTrue("plugin non trouvé", p != null);
		
		dao.remove(p);
		Plugin p2 = dao.find(Plugin.class, new Long(1));
		assertTrue("plugin trouvé.", p2 == null);
	}
	
	/**
	 * Recherche par type de plugins.
	 */
	@Test
	public void testFindByType() {
		dao = injector.getInstance(JpaDao.class);
		JpaPluginDao daoPlugin = injector.getInstance(JpaPluginDao.class);
		
		Plugin p1 = getPlugin();
		p1.setType(Type.BUILD_AND_REPORTING);
		p1.setArtifactId("maven-war1-plugin");
		daoPlugin.persist(p1);
		
		Plugin p2 = getPlugin();
		p2.setType(Type.BUILD_AND_REPORTING);
		p2.setArtifactId("maven-war2-plugin");
		daoPlugin.persist(p2);
		
		Plugin p3 = getPlugin();
		daoPlugin.persist(p3);

		List<Plugin> l1 = daoPlugin.findByType(Type.BUILD_AND_REPORTING);
		assertTrue("plugin non trouvé", l1 != null && l1.size() == 2);
		
		List<Plugin> l2 = daoPlugin.findByType(Type.BUILD);
		assertTrue("plugin non trouvé", l2 != null && l2.size() == 1);
		
		List<Plugin> l3 = daoPlugin.findByType(Type.REPORTING);
		assertTrue("plugin non trouvé", l3 != null && l3.isEmpty());
	}
	
	/**
	 * Création d'une version pour un plugin
	 */
	@Test
	public void testCreatePluginVersion(){
		dao = injector.getInstance(JpaDao.class);
		
		PluginVersion plVersion = new PluginVersion(getPlugin(), "2.0.0", new Prerequisite("maven", "2.2.1"), "war", "http://svn.com/tag/2.0.0");
		dao.persist(plVersion);
		assertTrue(dao.find(PluginVersion.class, new Long(1)) != null);
	}

}