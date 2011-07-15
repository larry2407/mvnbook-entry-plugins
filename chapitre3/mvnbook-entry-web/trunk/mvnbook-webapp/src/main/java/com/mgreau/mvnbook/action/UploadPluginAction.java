package com.mgreau.mvnbook.action;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.google.inject.Injector;
import com.mgreau.mvnbook.persistence.dao.Dao;
import com.mgreau.mvnbook.persistence.model.Category;
import com.mgreau.mvnbook.persistence.model.Plugin;
import com.mgreau.mvnbook.service.ServiceLoadPom;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author Maxime Gréau <dev@mgreau.com>
 *
 */
public class UploadPluginAction extends ActionSupport  implements ServletContextAware {
	
	/**	 */
	private static final long serialVersionUID = 1L;

	private ServletContext context;
	
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	
	/** Plugin à sauvegarder */
	private Plugin plugin;

    public String execute() throws Exception {
    	Injector inj = (Injector)context.getAttribute(Injector.class.getName());
    	
    	ServiceLoadPom service = inj.getInstance(ServiceLoadPom.class);
    	plugin = service.loadByPomFile(fileUpload, new Category("test", ""), false);
    	
    	if (plugin == null){
    		return ERROR;
    	}
    	
    	Dao dao = inj.getInstance(Dao.class);
    	dao.persist(plugin);
    	
        return SUCCESS;
    }
    
	
	public String display() {
		return NONE;
	}
	
    
	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}


	/**
	 * @return the context
	 */
	public ServletContext getContext() {
		return context;
	}


	/**
	 * @param context the context to set
	 */
	public void setContext(ServletContext context) {
		this.context = context;
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
