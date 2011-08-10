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
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.maven.pom._4_0.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mgreau.mvnbook.persistence.dao.JpaPluginDao;
import com.mgreau.mvnbook.persistence.model.Category;
import com.mgreau.mvnbook.persistence.model.IssueManagement;
import com.mgreau.mvnbook.persistence.model.Organization;
import com.mgreau.mvnbook.persistence.model.Plugin;
import com.mgreau.mvnbook.persistence.model.PluginVersion;
import com.mgreau.mvnbook.persistence.model.Repository;
import com.mgreau.mvnbook.persistence.model.Type;

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

	/** Logger */
	private final static Logger LOGGER = LoggerFactory
			.getLogger(ServiceLoadPom.class);

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
			@NotNull Category category, @NotNull Type type) throws Exception {

		return loadByPomFile(new File(this.getClass().getClassLoader()
				.getResource(file).getFile()), category, type);
	}

	/**
	 * 
	 * @param file
	 * @param category
	 * @param persist
	 * @return
	 * @throws Exception
	 */
	public Plugin loadByPomFile(@NotNull String file) throws Exception {
		return loadByPomFile(new File(this.getClass().getClassLoader()
				.getResource(file).getFile()));
	}

	public Plugin loadByPomFile(@NotNull File file) throws Exception {
		return loadByPomFile(file, new Category("default",
				"Categorie par défaut"), Type.BUILD);
	}

	public Plugin loadByPomFile(@NotNull File file, @NotNull Category category,
			@NotNull Type type) throws Exception {
		Model model = null;
		Plugin p = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(PACKAGE_MODEL);
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			model = getModel(unmarshaller, new FileInputStream(file));
			p = pomModelToPlugin(model, category, type);
			manageVersions(model, p);
		} catch (JAXBException e) {
			throw new Exception("Erreur de création du ", e);
		}

		if (p != null && p.getId() == null) {
			pluginDao.persist(p);
		} else {
			pluginDao.merge(p);
		}

		return p;
	}

	/**
	 * Valide extension, et contenu du fichier
	 * 
	 * @param pom
	 * @throws Exception
	 */
	public void validateFile(File pom) throws Exception {

		if (pom == null) {
			throw new ConstraintViolationException("fichier null", null);
		}

		// extension
		if (!pom.getAbsolutePath().endsWith(".xml")
				&& !pom.getAbsolutePath().endsWith(".pom")) {
			LOGGER.error("fichier incorrect, extensions pom ou xml requises.");
			throw new ConstraintViolationException(
					"extension de fichier incorrect", null);
		}

		// schema
		SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File(this.getClass().getClassLoader()
				.getResource("maven-4.0.0.xsd").getFile()));

		Validator validator = schema.newValidator();
		// validator.set
		JAXBContext jc = JAXBContext.newInstance(String.class);
		JAXBSource source = new JAXBSource(jc, new StreamSource(pom));
		// validator.validate(source);

	}

	/**
	 * Gestion des versions d'un plugin.
	 * 
	 * @param model
	 * @param p
	 */
	protected PluginVersion manageVersions(Model model, Plugin p) {
		// TODO gérer le prerequis
		// new Prerequisite("maven", model.getPrerequisites().getMaven());
		PluginVersion pv = new PluginVersion(p, model.getVersion(), null, null,
				model.getScm().getUrl());
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
	protected Plugin pomModelToPlugin(Model model, Category category, Type type)
			throws JAXBException {

		Plugin p = new Plugin(model.getName(),
				model.getGroupId() != null ? model.getGroupId() : model
						.getParent().getGroupId(), model.getArtifactId(),
				model.getDescription());
		p.setUrl(model.getUrl());
		p.setCategory(category);
		p.setType(type);
		try {
			if (model.getInceptionYear() != null)
				p.setInceptionYear(sdf.parse(model.getInceptionYear()));
		} catch (ParseException e) {
			LOGGER.error("Erreur pour le format de la date de création", e);
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
