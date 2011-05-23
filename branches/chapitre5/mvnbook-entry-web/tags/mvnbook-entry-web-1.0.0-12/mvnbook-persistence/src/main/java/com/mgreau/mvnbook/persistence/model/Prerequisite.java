package com.mgreau.mvnbook.persistence.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Prérequis pour l'execution du plugin.
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 *
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames={"name", "value"})})
@SuppressWarnings("serial")
public class Prerequisite implements Serializable {
	
	/** Identifiant */
	private Long id;

	/** Clé pour identifier le type de prérequis (maven,...) */
	private String name;
	
	/** Valeur du prérequis */
	private String value;
	
	/** Liste des plugins qui requierent cette valeur */
	private List<PluginVersion> plugins;

	public Prerequisite(){
		
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public Prerequisite(String name, String value) {
		super();
		this.name = name;
		this.value = value;
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
	 * @return the plugins
	 */
	@OneToMany(mappedBy="prerequisite")
	public List<PluginVersion> getPlugins() {
		return plugins;
	}

	/**
	 * @param plugins the plugins to set
	 */
	public void setPlugins(List<PluginVersion> plugins) {
		this.plugins = plugins;
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
	 * @return the value
	 */
	@NotBlank
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
