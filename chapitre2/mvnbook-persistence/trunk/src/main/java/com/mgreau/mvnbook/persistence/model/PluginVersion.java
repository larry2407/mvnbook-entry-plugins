package com.mgreau.mvnbook.persistence.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Association d'un plugin dans une version
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 */
@SuppressWarnings("serial")
public class PluginVersion implements Serializable {
	
	/** Identifiant */
	private Long id;
	
	/** Le plugin en question */
	private Plugin plugin;
	
	/** La version du plugin */
	private String version;
	
	/** La version de Maven prérequise */
	private Prerequisite prerequisite;
	
	/** Reccourci pour lancer les goals */
	private String prefix;
	
	/** URL du SCM pour la version du plugin */
	private String scmUrl;
	
	/** Liste des goals disponibles pour cette version du plugin */
	private List<Goal> goals;
	

	/**
	 * 
	 */
	public PluginVersion() {
		super();
	}

	/**
	 * @param plugin
	 * @param version
	 * @param prerequiste
	 * @param prefix
	 * @param scmUrl
	 */
	public PluginVersion(Plugin plugin, String version,
			Prerequisite prerequiste, String prefix, String scmUrl) {
		super();
		this.plugin = plugin;
		this.version = version;
		this.prerequisite = prerequiste;
		this.prefix = prefix;
		this.scmUrl = scmUrl;
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
	 * @return the plugin
	 */
	public Plugin getPlugin() {
		return plugin;
	}

	/**
	 * @param plugin the plugin to set
	 */
	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * @return the version
	 */
	@NotBlank
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the prerequisite
	 */
	public Prerequisite getPrerequisite() {
		return prerequisite;
	}

	/**
	 * @param prerequiste the prerequisite to set
	 */
	public void setPrerequisite(Prerequisite prerequisite) {
		this.prerequisite = prerequisite;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the scmUrl
	 */
	public String getScmUrl() {
		return scmUrl;
	}

	/**
	 * @param scmUrl the scmUrl to set
	 */
	public void setScmUrl(String scmUrl) {
		this.scmUrl = scmUrl;
	}

	/**
	 * @return the goals
	 */
	public List<Goal> getGoals() {
		return goals;
	}

	/**
	 * @param goals the goals to set
	 */
	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}

}
