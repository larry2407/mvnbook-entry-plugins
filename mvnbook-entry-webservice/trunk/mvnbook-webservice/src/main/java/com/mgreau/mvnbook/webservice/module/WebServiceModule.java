package com.mgreau.mvnbook.webservice.module;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.mgreau.mvnbook.persistence.dao.Dao;
import com.mgreau.mvnbook.persistence.dao.JpaDao;

public class WebServiceModule extends ServletModule{
	
	@Override
    protected void configureServlets() {      
    	install(new JpaPersistModule("mvnbookJpa")); 
        filter("/*").through(PersistFilter.class);
        bind(Dao.class).to(JpaDao.class);
  }

}
