package com.mgreau.mvnbook.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Informations sur le gestionnaire de bugs.
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 * 
 */
@Entity
@SuppressWarnings("serial")
public class IssueManagement implements Serializable {
	
	/** Identifiant */
	private Long id;

	/** Type de gestionnaire (JIRA, Bugzilla...) */
	private String system;

	/** URL d'accès au gestionnaire */
	private String url;
	
	public IssueManagement(){
		
	}

	/**
	 * Constructeur
	 * 
	 * @param system
	 * @param url
	 */
	public IssueManagement(String system, String url) {
		super();
		this.system = system;
		this.url = url;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the system
	 */
	@NotBlank
	public String getSystem() {
		return system;
	}

	/**
	 * @param system
	 *            the system to set
	 */
	public void setSystem(String system) {
		this.system = system;
	}

	/**
	 * @return the url
	 */
	@Pattern(message = "{com.mgreau.mvnbook.persistence.model.URL}", 
			regexp = "^(http|https|ftp)\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\\-\\._\\?\\,\\'/\\\\\\+&amp;%\\$#\\=~])*$")
	@NotNull
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
}
