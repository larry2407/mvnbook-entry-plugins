package com.mgreau.mvnbook.persistence.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * Goal disponible dans une ou plusieurs versions d'un plugin.
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 * 
 */
@SuppressWarnings("serial")
public class Goal implements Serializable {
	
	/** Identifiant */
	private Long id;

	/** Le nom du goal a lancer pour effectuer les traitements */
	private String name;

	/** La description des traitements réalisés par ce goal */
	private String description;

	/** La liste des plugins dans les versions qui proposent ce goal */
	private List<PluginVersion> plugins;

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param plugins
	 */
	public Goal(Long id, String name, String description,
			List<PluginVersion> plugins) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.plugins = plugins;
	}

	/**
	 * @return the id
	 */
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
	 * @return the name
	 */
	@NotBlank
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the plugins
	 */
	public List<PluginVersion> getPlugins() {
		return plugins;
	}

	/**
	 * @param plugins the plugins to set
	 */
	public void setPlugins(List<PluginVersion> plugins) {
		this.plugins = plugins;
	}

}
