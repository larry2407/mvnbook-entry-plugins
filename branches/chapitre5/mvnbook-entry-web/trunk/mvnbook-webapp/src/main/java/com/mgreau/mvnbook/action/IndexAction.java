package com.mgreau.mvnbook.action;

import java.util.List;

import org.apache.struts2.util.ServletContextAware;

import com.mgreau.mvnbook.persistence.dao.JpaPluginDao;
import com.mgreau.mvnbook.persistence.model.Category;
import com.mgreau.mvnbook.persistence.model.Plugin;

/**
 * 
 * @author mgreau
 *
 */
@SuppressWarnings("serial")
public class IndexAction extends AbstractMvnBookAction  implements ServletContextAware {
	

	public List<Plugin> plugins;
	
    public String executeMetier() throws Exception {
    	
    	JpaPluginDao dao = inj.getInstance(JpaPluginDao.class);
    	Category c = new Category("test","");
    	c.setId(1L);
    	plugins = dao.findByCategory(c);
    	
        return SUCCESS;
    }
	
}
