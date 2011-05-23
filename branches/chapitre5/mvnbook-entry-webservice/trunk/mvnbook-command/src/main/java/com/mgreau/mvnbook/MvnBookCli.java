/**
 * 
 */
package com.mgreau.mvnbook;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mgreau.mvnbook.webservice.client.ClientWS;

/**
 * @author mgreau
 * 
 */
public class MvnBookCli {

	public static final Logger log = LoggerFactory.getLogger(MvnBookCli.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Execution du CLI en mode commande -------");
		MvnBookCli cli = new MvnBookCli();
		if (null == args || args.length == 0)
			log.error(cli.getArgsAvailable());
		else {
			cli.showArgs(args);
			ClientWS cliWS = new ClientWS();
			try {
				String res = cliWS.addPlugin(new File("test"));
				log.info("ok call done." + res);
			} catch (Exception e) {
				log.error("erreur d'appel WS", e);
			}
		}

		log.info("-----------Fin du processus.");

	}

	public void showArgs(String[] args) {
		for (String arg : args) {
			if (!arg.startsWith("-D")) {
				log.error("Les arguments doivent être préciser par la syntaxe -Darg=valeur");
				break;
			}
			log.info(arg.substring(1));

		}
	}

	public String getArgsAvailable() {
		StringBuffer args = new StringBuffer();
		args.append(
				"-Durl=http://<ip>:<port> : adresse du serveur où est déployé le Web Service")
				.append('\n');
		args.append("-Dws=<name> : web service à appeler").append('\n');
		args.append("-Dfile=<path> : chemin d'acces au fichier POM").append(
				'\n');
		return args.toString();
	}

}
