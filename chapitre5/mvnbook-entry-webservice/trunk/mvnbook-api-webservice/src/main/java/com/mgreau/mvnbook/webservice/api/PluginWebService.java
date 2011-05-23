package com.mgreau.mvnbook.webservice.api;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.io.*;
 
/**
 * Web Service pour les plugins
 * 
 * @author Maxime Gréau (dev@mgreau.com)
 *
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface PluginWebService{
 
	@WebMethod 
	public String addPlugin(File pom) throws WSException;
 
}