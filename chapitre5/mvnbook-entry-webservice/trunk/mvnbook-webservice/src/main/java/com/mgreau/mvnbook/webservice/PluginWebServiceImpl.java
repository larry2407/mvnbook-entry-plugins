package com.mgreau.mvnbook.webservice;

import java.io.File;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.mgreau.mvnbook.persistence.dao.Dao;
import com.mgreau.mvnbook.persistence.dao.JpaDao;
import com.mgreau.mvnbook.persistence.model.Plugin;
import com.mgreau.mvnbook.service.ServiceLoadPom;
import com.mgreau.mvnbook.webservice.api.PluginWebService;
import com.mgreau.mvnbook.webservice.api.WSException;
import com.sun.xml.ws.transport.http.servlet.ServletModule;

/**
 * Implémentation du Web Service pour les plugins
 * 
 * @author Maxime Gréau (dev@mgreau.com)
 * 
 */
@WebService(endpointInterface = "com.mgreau.mvnbook.webservice.api.PluginWebService")
public class PluginWebServiceImpl implements PluginWebService {

	private Logger logger = LoggerFactory.getLogger(PluginWebServiceImpl.class);

	private Injector inj;

	public PluginWebServiceImpl() {
		inj = getInjector();
	}

	@Override
	public String addPlugin(File pom) throws WSException {

		ServiceLoadPom servicePom = inj.getInstance(ServiceLoadPom.class);
		Plugin plugin = null;
		try {
			servicePom.validateFile(pom);
			plugin = servicePom.loadByPomFile(pom);
		} catch (Exception e) {
			logger.error("Le fichier POM est incorrect", e);
			throw new WSException("La validation du fichier POM a échoué", e);
		}
		if (plugin != null) {
			return "SUCCESS :" + plugin.getGroupId() + ":"
					+ plugin.getArtifactId() + " ajouté.";
		}

		return "ERROR";
	}

	protected Injector getInjector() {
		inj = Guice.createInjector(new JpaPersistModule("myFirstJpaUnit"));
		inj.getInstance(PersistService.class).start();
		inj.getInstance(UnitOfWork.class).begin();
		return inj;
	}
}