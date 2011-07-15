package com.mgreau.mvnbook.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Un plugin pour Apache Maven
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "groupId",
		"artifactId" }) })
@SuppressWarnings("serial")
public class Plugin implements Serializable {

	/** Identifiant */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** Nom du plugin */
	@NotBlank
	private String name;

	/** groupId du POM */
	@NotBlank
	private String groupId;

	/** artifactId du POM */
	@NotBlank
	private String artifactId;

	/** Date de création du plugin */
	@Temporal(TemporalType.DATE)
	private Date inceptionYear;

	/** Description du plugin */
	@Lob
	private String description;

	/** La catégorie du plugin */
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private Category category;

	/** Type de plugin */
	@Enumerated(EnumType.STRING)
	private Type type;

	/** Gestionnaire de bugs du plugin */
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private IssueManagement issueManagement;

	/** Référentiel des releases et snapshots du plugin */
	@ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private List<Repository> repositories;
	
	/** Adresse du site Web de documentation */
	@Pattern(message = "{com.mgreau.mvnbook.persistence.model.URL}", regexp = "^(http|https|ftp)\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\\-\\._\\?\\,\\'/\\\\\\+&amp;%\\$#\\=~])*$")
	private String url;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private Organization organization;
	
	/** Les versions disponibles pour ce plugin */
	@ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private List<PluginVersion> versions;

	/**
	 * @param name
	 * @param groupId
	 * @param artifactId
	 * @param description
	 */
	public Plugin(String name, String groupId, String artifactId,
			String description) {
		super();
		this.name = name;
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.description = description;
	}

	/**
	 * @param id
	 * @param name
	 * @param groupId
	 * @param artifactId
	 * @param inceptionYear
	 * @param description
	 * @param category
	 * @param type
	 * @param issueManagement
	 * @param url
	 */
	public Plugin(Long id, String name, String groupId, String artifactId,
			Date inceptionYear, String description, Category category,
			Type type, IssueManagement issueManagement, String url) {
		super();
		this.id = id;
		this.name = name;
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.inceptionYear = inceptionYear;
		this.description = description;
		this.category = category;
		this.type = type;
		this.issueManagement = issueManagement;
		this.url = url;
	}
	
	/**
	 * Ajoute une version au Plugin.
	 * @param pv la version à ajouter
	 */
	public void addVersion(PluginVersion pv){
		if (null == versions){
			versions = new ArrayList<PluginVersion>();
		}
		versions.add(pv);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the artifactId
	 */
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * @param artifactId
	 *            the artifactId to set
	 */
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	/**
	 * @return the inceptionYear
	 */
	public Date getInceptionYear() {
		return inceptionYear;
	}

	/**
	 * @param inceptionYear
	 *            the inceptionYear to set
	 */
	public void setInceptionYear(Date inceptionYear) {
		this.inceptionYear = inceptionYear;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the issueManagement
	 */
	public IssueManagement getIssueManagement() {
		return issueManagement;
	}

	/**
	 * @param issueManagement
	 *            the issueManagement to set
	 */
	public void setIssueManagement(IssueManagement issueManagement) {
		this.issueManagement = issueManagement;
	}

	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * @return the repositories
	 */
	public List<Repository> getRepositories() {
		return repositories;
	}

	/**
	 * @param repositories the repositories to set
	 */
	public void setRepositories(List<Repository> repositories) {
		this.repositories = repositories;
	}
	
	/**
	 * @return the versions
	 */
	public List<PluginVersion> getVersions() {
		return versions;
	}

	/**
	 * @param versions the versions to set
	 */
	public void setVersions(List<PluginVersion> versions) {
		this.versions = versions;
	}



}
