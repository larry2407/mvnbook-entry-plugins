<%@ page language="java" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1>MVBOOK ENTRY PLUGINS</h1>
<h2>Gestion des plugins Apache Maven</h2>
<h3>Ajouter un plugin</h3>
<div id="menu">
<s:url id="linkUpload" action="uploadPlugin"  />
<s:url id="linkHome" action="home"  />
<ul>
<li><s:a href="%{linkUpload}">Ajouter un plugin</s:a></li>
<li><s:a href="%{linkHome}">Accueil</s:a></li>
</ul>
</div>

<s:if test="hasFieldErrors() || hasActionErrors()">
    <div id="msg">
        <div class="errors">
            <s:fielderror/>
            <s:actionerror/>
        </div>
    </div>
</s:if>

<s:form action="showPlugin" namespace="/" method="post" enctype="multipart/form-data">
	<s:file name="fileUpload" label="Choissisez un fichier (.pom ou .xml)"  size="40" />
	<s:submit value="Ajouter le plugin" name="submit" />
</s:form>