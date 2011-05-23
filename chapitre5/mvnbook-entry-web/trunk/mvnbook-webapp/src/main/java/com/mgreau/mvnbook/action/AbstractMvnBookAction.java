package com.mgreau.mvnbook.action;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author mgreau
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractMvnBookAction extends ActionSupport  implements ServletContextAware {

	/** Logger */
	protected Logger log =  LoggerFactory.getLogger(this.getClass());
	
	protected Injector inj;
	
	/** Servlet Context */
	protected ServletContext context;
	
	/** Cause de l'exception dans l'IHM */
	private String exceptionCause;

	/**
	 * 
	 */
    public String execute() throws Exception {
    	try {
    		inj = (Injector)context.getAttribute(Injector.class.getName());
			return executeMetier();
		} catch (Exception e) {
			exceptionCause = e.getCause() != null ? e.getCause().toString() : null;
			log.error("Exception inattendue.", e);
			throw e;
		}
    }
    
    /**
	 * L'implementation par défaut ne fait rien d'autre que retourner "success". <p/> Les classes qui
	 * héritent de {@link AbstractCirBaseAction} doivent réimplémenter cette méthode. <p/>
	 * 
	 * @return returns {@link #SUCCESS}
	 * @throws Exception can be thrown by subclasses.
	 */
	public abstract String executeMetier() throws Exception;
    
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
	 * @return the exceptionCause
	 */
	public String getExceptionCause() {
		return exceptionCause;
	}

	/**
	 * @param exceptionCause the exceptionCause to set
	 */
	public void setExceptionCause(String exceptionCause) {
		this.exceptionCause = exceptionCause;
	}


	
}
