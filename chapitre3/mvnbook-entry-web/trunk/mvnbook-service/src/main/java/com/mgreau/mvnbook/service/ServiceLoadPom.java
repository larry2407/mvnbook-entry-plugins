package com.mgreau.mvnbook.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.maven.pom._4_0.Model;

import com.google.inject.Inject;
import com.mgreau.mvnbook.persistence.dao.JpaPluginDao;
import com.mgreau.mvnbook.persistence.model.Category;
import com.mgreau.mvnbook.persistence.model.IssueManagement;
import com.mgreau.mvnbook.persistence.model.Organization;
import com.mgreau.mvnbook.persistence.model.Plugin;
import com.mgreau.mvnbook.persistence.model.PluginVersion;
import com.mgreau.mvnbook.persistence.model.Repository;

/**
 * Service pour charger le contenu du fichier POM d'un plugin dans le système du
 * projet mvnbook-entry-plugins.
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 * 
 */
public class ServiceLoadPom {

	/** nom du package pour les objets du model généré */
	private static final String PACKAGE_MODEL = "org.apache.maven.pom._4_0";

	/** Format de la date pour la valeur inceptionYear */
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

	/** DAO pour le plugin */
	private JpaPluginDao pluginDao;

	/**
	 * Constructeur
	 * 
	 * @param dao
	 */
	@Inject
	public ServiceLoadPom(JpaPluginDao dao) {
		pluginDao = dao;
	}
	
	/**
	 * 
	 * @param file
	 * @param category
	 * @param persist
	 * @return
	 * @throws Exception
	 */
	public Plugin loadByPomFile(@NotNull String file,
			@NotNull Category category, boolean persist) throws Exception {
		if (!file.endsWith(".xml") && !file.endsWith(".pom")) {
			throw new ConstraintViolationException(
					"extension de fichier incorrect", null);
		}
		return loadByPomFile(new File(this.getClass().getClassLoader()
					.getResource(file).getFile()), category, persist);
	}

	/**
	 * 
	 * @param in
	 * @param category
	 * @param persist
	 * @return
	 * @throws Exception
	 */
	public Plugin loadByPomFile(@NotNull File file,
			@NotNull Category category, boolean persist) throws Exception {
		
		Model model = null;
		Plugin p = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(PACKAGE_MODEL);
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			model = getModel(unmarshaller, new FileInputStream(file));
			p = pomModelToPlugin(model, category);
			handleVersions(model, p);
		} catch (JAXBException e) {
			throw new Exception("Erreur de création du ", e);
		}

		if (persist) {
			pluginDao.persist(p);
		}
		return p;
	}

	/**
	 * Gestion des versions d'un plugin.
	 * 
	 * @param model
	 * @param p
	 */
	protected PluginVersion handleVersions(Model model, Plugin p) {
		//TODO gérer le prerequis 
		//new Prerequisite("maven", model.getPrerequisites().getMaven());
		PluginVersion pv = new PluginVersion(p, model.getVersion(),
				null,
				null, model.getScm().getUrl());
		p.addVersion(pv);
		
		return pv;
	}

	/**
	 * Conversion des informations l'objet {@link Model} vers l'object
	 * {@link Plugin}
	 * 
	 * @param model
	 *            l'objet {@link Model} à convertir
	 * @param category
	 *            la {@link Category} du plugin
	 * @return le {@link Plugin} valorisé
	 * @throws JAXBException
	 */
	protected Plugin pomModelToPlugin(Model model, Category category)
			throws JAXBException {

		Plugin p = new Plugin(model.getName(),
				model.getGroupId() != null ? model.getGroupId() : model
						.getParent().getGroupId(), model.getArtifactId(),
				model.getDescription());
		p.setUrl(model.getUrl());
		p.setCategory(category);
		try {
			if (model.getInceptionYear() != null)
				p.setInceptionYear(sdf.parse(model.getInceptionYear()));
		} catch (ParseException e) {
			// générer un log erreur
		}
		p.setIssueManagement(new IssueManagement(model.getIssueManagement()
				.getSystem(), model.getIssueManagement().getUrl()));
		if (null != model.getOrganization())
			p.setOrganization(new Organization(model.getOrganization()
					.getName(), model.getOrganization().getUrl()));
		if (null != model.getDistributionManagement()) {
			List<Repository> repos = new ArrayList<Repository>();
			repos.add(new Repository(model.getDistributionManagement()
					.getRepository().getUrl(), true));
			p.setRepositories(repos);
		}

		return p;
	}

	/**
	 * Chargement du flux XMl en objet {@link Model}
	 * 
	 * @param unmarshaller
	 * @param in
	 * @return
	 * @throws JAXBException
	 */
	private Model getModel(Unmarshaller unmarshaller, InputStream in)
			throws JAXBException {
		return (Model) ((JAXBElement<Model>) unmarshaller.unmarshal(in))
				.getValue();
	}

}
