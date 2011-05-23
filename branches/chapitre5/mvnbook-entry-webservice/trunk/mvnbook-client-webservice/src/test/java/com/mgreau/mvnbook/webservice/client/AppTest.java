package com.mgreau.mvnbook.webservice.client;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.mgreau.mvnbook.webservice.api.PluginWebService;

public class AppTest {

	@Test
	public void testWS() {
		try {
			PluginWebService port = new ProxyWSPlugin()
					.getPluginWebServiceImplPort();

			File pom = new File(this.getClass().getClassLoader()
					.getResource("maven-jar-plugin-2.3.1.pom").getFile());

			String response = port.addPlugin(pom);
			assertTrue(response != null);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
