package com.mgreau.mvnbook.persistence.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Un référentiel qui met à disposition des plugins.
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "url" }) })
@SuppressWarnings("serial")
public class Repository implements Serializable {
	
	/** Identifiant */
	private Long id;
	
	/** URL d'accès au référentiel */
	private String url;
	
	/**Identifie si le référentiel est destiné aux référentiels releases ou snapshots */
	private Boolean isRelease;
	
	/** Les plugins du repository */
	private List<Plugin> plugins;
	
	/** URL publique qui pointe vers URL */
	private String publicUrl;

	/**
	 * @param url
	 * @param isRelease
	 */
	public Repository(String url, Boolean isRelease) {
		super();
		this.url = url;
		this.isRelease = isRelease;
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
	 * @return the url
	 */
	@Pattern(message = "{com.mgreau.mvnbook.persistence.model.URL}", 
			regexp = "^(http|https|ftp)\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\\-\\._\\?\\,\\'/\\\\\\+&amp;%\\$#\\=~])*$")
	@NotNull
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the isRelease
	 */
	public Boolean getIsRelease() {
		return isRelease;
	}

	/**
	 * @param isRelease the isRelease to set
	 */
	public void setIsRelease(Boolean isRelease) {
		this.isRelease = isRelease;
	}

	/**
	 * @return the plugins
	 */
	@ManyToMany(fetch=FetchType.LAZY)
	public List<Plugin> getPlugins() {
		return plugins;
	}

	/**
	 * @param plugins the plugins to set
	 */
	public void setPlugins(List<Plugin> plugins) {
		this.plugins = plugins;
	}

	/**
	 * @return the publicUrl
	 */
	@Pattern(message = "{com.mgreau.mvnbook.persistence.model.URL}", 
			regexp = "^(http|https|ftp)\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\\-\\._\\?\\,\\'/\\\\\\+&amp;%\\$#\\=~])*$")
	public String getPublicUrl() {
		return publicUrl;
	}

	/**
	 * @param publicUrl the publicUrl to set
	 */
	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}
}
