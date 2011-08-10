package com.mgreau.mvnbook.persistence.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Organisme qui gère le plugin.
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 * 
 */
@Entity
@SuppressWarnings("serial")
public class Organization implements Serializable {

	/** Identifiant */
	private Long id;

	/** Nom de l'organisme */
	private String name;

	/** URL du site de l'organisme */
	private String url;

	/** Liste des plugins gérés par l'organisme */
	private List<Plugin> plugins;

	public Organization() {

	}

	/**
	 * @param name
	 * @param url
	 */
	public Organization(String name, String url) {
		super();
		this.name = name;
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
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@NotBlank
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
	 * @return the url
	 */
	@Pattern(message = "{com.mgreau.mvnbook.persistence.model.URL}", regexp = "^(http|https|ftp)\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\\-\\._\\?\\,\\'/\\\\\\+&amp;%\\$#\\=~])*$")
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
	 * @return the plugins
	 */
	@OneToMany(mappedBy = "organization")
	public List<Plugin> getPlugins() {
		return plugins;
	}

	/**
	 * @param plugins
	 *            the plugins to set
	 */
	public void setPlugins(List<Plugin> plugins) {
		this.plugins = plugins;
	}

}
