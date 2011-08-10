package com.mgreau.mvnbook.action;

import java.util.List;

import org.apache.struts2.util.ServletContextAware;

import com.mgreau.mvnbook.persistence.dao.JpaPluginDao;
import com.mgreau.mvnbook.persistence.model.Plugin;

/**
 * Action pour la page d'index de l'application.
 * 
 * <p>Afficher la liste des plugins présents en base de données.</p>
 * 
 * @author Maxime Gréau (dev@mgreau.com)
 *
 */
@SuppressWarnings("serial")
public class IndexAction extends AbstractMvnBookAction  implements ServletContextAware {
	
	/** Liste des plugins */
	public List<Plugin> plugins;
	
	/**
	 * Récupère la liste des plugins.
	 */
    public String executeMetier() throws Exception {
    	
    	JpaPluginDao dao = inj.getInstance(JpaPluginDao.class);
    	plugins = (List<Plugin>)dao.findAll(Plugin.class);
    	
        return SUCCESS;
    }
	
}
