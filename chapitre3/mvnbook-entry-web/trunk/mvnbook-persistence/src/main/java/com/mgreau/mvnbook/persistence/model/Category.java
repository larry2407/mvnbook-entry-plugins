package com.mgreau.mvnbook.persistence.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * La catégorie associée à un plugin (packaging, outils...)
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 * 
 */
@Entity
@SuppressWarnings("serial")
public class Category implements Serializable {

	/** Identifiant */
	private Long id;

	/** Intitulé de la catégorie */
	private String title;

	/** Description de la catégorie */
	private String description;
	
	/** Liste des plugins de la catégorie */
	private List<Plugin> plugins;

	/**
	 * @param title
	 * @param description
	 */
	public Category(String title, String description) {
		super();
		this.title = title;
		this.description = description;
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
	 * @return the title
	 */
	@NotBlank
	@Size(max = 200)
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	@Lob
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
	 * @return the plugins
	 */
	@OneToMany(mappedBy="category")
	public List<Plugin> getPlugins() {
		return plugins;
	}

	/**
	 * @param plugins the plugins to set
	 */
	public void setPlugins(List<Plugin> plugins) {
		this.plugins = plugins;
	}
}
