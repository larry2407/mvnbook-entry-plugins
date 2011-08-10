package com.mgreau.mvnbook.action;

import java.io.File;

import javax.servlet.ServletContext;

import com.google.inject.Injector;
import com.mgreau.mvnbook.persistence.dao.Dao;
import com.mgreau.mvnbook.persistence.model.Category;
import com.mgreau.mvnbook.persistence.model.Plugin;
import com.mgreau.mvnbook.service.ServiceLoadPom;

/**
 * Ajout d'un plugin via son POM.
 * 
 * @author Maxime Gréau (dev@mgreau.com)
 *
 */
@SuppressWarnings("serial")
public class UploadPluginAction extends AbstractMvnBookAction {
	
	protected File fileUpload;
	protected String fileUploadContentType;
	protected String fileUploadFileName;
	
	/** Plugin à sauvegarder */
	private Plugin plugin;

    public String executeMetier() throws Exception {
    	
    	ServiceLoadPom service = inj.getInstance(ServiceLoadPom.class);
    	plugin = service.loadByPomFile(fileUpload);
    	
    	if (plugin == null){
    		logger.error("Le plugin est null");
    		return ERROR;
    	}
    	
        return SUCCESS;
    }
    
	/**
	 * Affiche le formulaire
	 * @return
	 */
	public String display() {
		return NONE;
	}
	
    
	/**
	 * @return the fileUpload
	 */
	public File getFileUpload() {
		return fileUpload;
	}


	/**
	 * @param fileUpload the fileUpload to set
	 */
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}


	/**
	 * @return the fileUploadContentType
	 */
	public String getFileUploadContentType() {
		return fileUploadContentType;
	}


	/**
	 * @param fileUploadContentType the fileUploadContentType to set
	 */
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}


	/**
	 * @return the fileUploadFileName
	 */
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}


	/**
	 * @param fileUploadFileName the fileUploadFileName to set
	 */
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
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
}
