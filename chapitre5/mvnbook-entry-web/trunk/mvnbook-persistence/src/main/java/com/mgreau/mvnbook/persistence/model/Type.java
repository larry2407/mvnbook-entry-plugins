package com.mgreau.mvnbook.persistence.model;

/**
 * Le type de plugins, pour la construction du projet, pour la génération de
 * rapport ou les deux.
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 * 
 */
public enum Type {

	BUILD("B"), REPORTING("R"), BUILD_AND_REPORTING("B+R");

	private String shortcut;

	private Type(String shortcut) {
		this.shortcut = shortcut;
	}

	public String getShortcut() {
		return shortcut;
	}

}
