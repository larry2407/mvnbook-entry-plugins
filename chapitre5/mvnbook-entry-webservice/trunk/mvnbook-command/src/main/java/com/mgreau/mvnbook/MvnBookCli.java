/**
 * 
 */
package com.mgreau.mvnbook;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mgreau.mvnbook.webservice.client.ClientWS;

/**
 * Client pour faire un appel de WS avec une librairie autonome.
 * 
 * @author Maxime Gréau - SRE
 *
 */
public class MvnBookCli {

	/** Logger */
	public static final Logger log = LoggerFactory.getLogger(MvnBookCli.class);

	/** URL du serveur où se trouve le webservice */
	private String url = "http://localhost:8080";

	/** WebService à appeler */
	private String ws;

	/** Path du POM à ajouter */
	private String file = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("------- Execution du CLI en mode commande -------");
		MvnBookCli cli = new MvnBookCli();
		if (null == args || args.length == 0)
			log.error(cli.getArgsAvailable());
		else {
			if (cli.manageArgs(args)) {
				ClientWS cliWS = new ClientWS();
				try {
					log.info("- appel du WS addPlugin...");
					String response = cliWS.addPlugin(new File(cli.file));
					boolean resultat = (response != null && response.startsWith("SUCCESS")?true:false);
					log.info("");
					log.info("-------------------");
					log.info("AJOUT DU PLUGIN " + (resultat?"OK":"KO"));
					if (resultat)
						log.info("Plugin : " + response.split(":")[1] + ":" + response.split(":")[2]);
					log.info("-------------------");
				} catch (Exception e) {
					log.error("erreur d'appel WS", e);
				}
			}
		}
		log.info("----------- Fin du processus -----------");

	}

	/**
	 * Gestion des arguments.
	 * 
	 * @param args
	 * @return
	 */
	public boolean manageArgs(String[] args) {
		boolean argsOk = true;
		for (String arg : args) {
			if (!arg.startsWith("url=") && !arg.startsWith("file=") && !arg.startsWith("ws=")) {
				if (!arg.startsWith("-D")) {
					log.error("L'argument " + arg + "  n'est pas autorisé.");
					argsOk = false;
					break;
				}
			} else {
				if (arg.startsWith("url")) {
					url = arg.split("=")[1];
				} else if (arg.startsWith("file")) {
					file = arg.split("=")[1];
				} else if (arg.startsWith("ws")) {
					ws = arg.split("=")[1];
				}
			}
		}
		return argsOk;
	}

	/**
	 * Affiche la liste des arguments diponibles.
	 * @return la liste des args autorisés
	 */
	public String getArgsAvailable() {
		StringBuffer args = new StringBuffer();
		args.append(
				"url=http://<ip>:<port> : adresse du serveur où est déployé le Web Service")
				.append('\n');
		args.append("ws=<name> : web service à appeler").append('\n');
		args.append("file=<path> : chemin d'acces au fichier POM").append('\n');
		return args.toString();
	}
}
