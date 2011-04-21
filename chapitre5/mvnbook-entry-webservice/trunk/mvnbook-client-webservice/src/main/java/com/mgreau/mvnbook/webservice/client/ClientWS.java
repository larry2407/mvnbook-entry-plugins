package com.mgreau.mvnbook.webservice.client;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mgreau.mvnbook.webservice.api.PluginWebService;
import com.mgreau.mvnbook.webservice.api.WSException;

public class ClientWS {

	Logger log = LoggerFactory.getLogger(ClientWS.class);
	PluginWebService wsPlugin;

	public ClientWS() {
		wsPlugin = new ProxyWSPlugin().getPluginWebServiceImplPort();
	}

	public ClientWS(PluginWebService ws) {
		wsPlugin = ws;
	}

	public String addPlugin(File pom) throws WSException {
		if (null == pom){
			throw new WSException("Le fichier POM ne doit pas être null.");
		}
		try {
			return wsPlugin.addPlugin(pom);
		} catch (WSException e) {
			log.error("Pb à l'appel du  Web Service", e);
		}
		return "ERROR";
	}

}
