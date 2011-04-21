<%@ page language="java" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1>MVBOOK ENTRY PLUGINS</h1>
<h2>Gestion des plugins Apache Maven</h2>
<div id="menu">
<s:url id="linkUpload" action="uploadPlugin"  />
<s:url id="linkHome" action="home"  />
<ul>
<li><s:a href="%{linkUpload}">Ajouter un plugin</s:a></li>
<li><s:a href="%{linkHome}">Accueil</s:a></li>
</ul>
</div>
<h3>Erreurs</h3>
<s:actionerror/>

<h3>Exception</h3>
<s:if test="exceptionCause != null">
<pre><s:property value="exceptionCause" /></pre>
</s:if>
<pre><s:property value="exceptionStack" /></pre>

