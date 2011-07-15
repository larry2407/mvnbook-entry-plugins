package com.mgreau.mvnbook.webservice;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName="ws", name="ws")
public class ImportPlugin {
    private String message = new String("Hello, ");

    public ImportPlugin() {}

    @WebMethod(operationName="upload")
    public String uploadPom(String name) {
        return message + name + ".";
    }
}