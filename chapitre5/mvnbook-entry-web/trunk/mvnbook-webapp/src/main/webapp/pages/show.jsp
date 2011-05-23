<%@ page language="java" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1>MVBOOK ENTRY PLUGINS</h1>
<h2>Gestion des plugins Apache Maven</h2>
<h3>Plugin ajouté !</h3>
<div id="menu">
<s:url id="linkUpload" action="uploadPlugin"  />
<s:url id="linkHome" action="home"  />
<ul>
<li><s:a href="%{linkUpload}">Ajouter un plugin</s:a></li>
<li><s:a href="%{linkHome}">Accueil</s:a></li>
</ul>
</div>
<ul>
   <li>Nom du fichier envoyé : <s:property value="fileUploadFileName"/></li> 
   <li>Content Type : <s:property value="fileUploadContentType"/></li> 
   <li>File : <s:property value="fileUpload"/></li> 
   <li>artifactId : <s:property value="plugin.artifactId"/></li>
   <li>groupId : <s:property value="plugin.groupId"/></li>
</ul>
