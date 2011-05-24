package com.mgreau.mvnbook.action;

import java.io.File;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.apache.struts2.tiles.StrutsTilesContainerFactory;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.mgreau.mvnbook.persistence.dao.Dao;
import com.mgreau.mvnbook.persistence.dao.JpaDao;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author Maxime Gréau (dev@mgreau.com)
 *
 */
public class UploadPluginActionTest extends StrutsTestCase {
	
	private Injector injector;
	
	/**
	 * Affichage de la page d'upload
	 * @throws Exception
	 */
	public void testShowUploadFilePage() throws Exception {
		ActionProxy proxy = getActionProxy("/uploadPlugin");
		TilesContainer container = new StrutsTilesContainerFactory().createContainer(servletContext);
		TilesAccess.setContainer(servletContext, container);
		String result = proxy.execute();
		assertTrue("Expected a none result!", ActionSupport.NONE.equals(result));
	}
	
	
	/**
	 * Fichier d'upload obligatoire
	 * @throws Exception
	 */
	public void FileUploadRequired() throws Exception {
		ServletModule mod = new ServletModule() {
	        @Override
	        protected void configureServlets() {      
	            bind(Dao.class).to(JpaDao.class);
	      }
	    };
		injector = Guice.createInjector(new JpaPersistModule("testUnit"), mod);
		injector.getInstance(PersistService.class).start();
		injector.getInstance(UnitOfWork.class).begin();
		servletContext.setAttribute(Injector.class.getName(), injector);
		
		TilesContainer container = new StrutsTilesContainerFactory().createContainer(servletContext);
		TilesAccess.setContainer(servletContext, container);
		
		ActionProxy proxy = getActionProxy("/showPlugin");
		String result = proxy.execute();
		assertTrue("redirection vers INPUT attendu car fichier manquant !", ActionSupport.INPUT.equals(result));
	}


	/**
	 * Test chargement d'un fichier POM
	 * @throws Exception
	 */
	public void testUploadFile() throws Exception {
		ServletModule mod = new ServletModule() {
	        @Override
	        protected void configureServlets() {      
	            bind(Dao.class).to(JpaDao.class);
	      }
	    };
		injector = Guice.createInjector(new JpaPersistModule("testUnit"), mod);
		injector.getInstance(PersistService.class).start();
		injector.getInstance(UnitOfWork.class).begin();
		servletContext.setAttribute(Injector.class.getName(), injector);
		
		TilesContainer container = new StrutsTilesContainerFactory().createContainer(servletContext);
		TilesAccess.setContainer(servletContext, container);
		
		ActionProxy proxy = getActionProxy("/showPlugin");
		UploadPluginAction upload = (UploadPluginAction) proxy.getAction();
		
		upload.setServletContext(servletContext);
		upload.setFileUpload(new File(Thread.currentThread().getContextClassLoader()
		.getResource("maven-help-plugin-2.1.1.pom").getFile()));
		upload.setFileUploadContentType("test");
		upload.setFileUploadFileName("pom.xml");
		
		String result = proxy.execute();
		assertTrue("Expected a none result!", ActionSupport.SUCCESS.equals(result));
		assertTrue("PLugin non créé", upload.getPlugin() != null && upload.getPlugin().getId() != null);
	}
}
