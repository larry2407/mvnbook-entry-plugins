package com.mgreau.mvnbook.listener;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.mgreau.mvnbook.persistence.dao.Dao;
import com.mgreau.mvnbook.persistence.dao.JpaDao;
import com.mgreau.mvnbook.persistence.dao.JpaPluginDao;

/**
 * Example application module.
 *
 * @author crazybob@google.com (Bob Lee)
 */
public class WebListener extends GuiceServletContextListener {

  public Injector getInjector() {
    return Guice.createInjector(
      new ServletModule() {
        @Override
        protected void configureServlets() {      
        	// Struts 2 setup
            bind(StrutsPrepareAndExecuteFilter.class).in(Singleton.class);
        	install(new JpaPersistModule("mvnbookJpaUnit")); 

            filter("/*").through(PersistFilter.class);
            filter("/*").through(StrutsPrepareAndExecuteFilter.class);
            
            bind(Dao.class).to(JpaDao.class);
      }
    });
  }

}