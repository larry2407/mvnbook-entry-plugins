<%@ page language="java" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1>Chargement d'un plugin</h1>

<s:form action="uploadPlugin" namespace="/" method="post" enctype="multipart/form-data">
	<s:file name="fileUpload" label="Choissisez un fichier (.pom ou .xml)" size="40" />
	<s:submit value="submit" name="submit" />
</s:form>