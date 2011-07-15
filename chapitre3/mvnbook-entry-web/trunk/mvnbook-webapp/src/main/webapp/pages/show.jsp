<%@ page language="java" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1>Afficher le plugin</h1>
<h2>Nom du plugin : </h2>
<ul>
   <li>Nom du fichier envoyé : <s:property value="fileUploadFileName"/></li> 
   <li>Content Type : <s:property value="fileUploadContentType"/></li> 
   <li>File : <s:property value="fileUpload"/></li> 
   <li>artifactId : <s:property value="plugin.artifactId"/></li>
   <li>groupId : <s:property value="plugin.groupId"/></li>
</ul>
