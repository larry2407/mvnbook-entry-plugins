<%@ page language="java" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1>MVBOOK ENTRY PLUGINS</h1>
<h2>Gestion des plugins Apache Maven</h2>
<h3>Accueil</h3>
<div id="menu">
<s:url id="linkUpload" action="uploadPlugin"  />
<s:url id="linkHome" action="home"  />
<ul>
<li><s:a href="%{linkUpload}">Ajouter un plugin</s:a></li>
<li><s:a href="%{linkHome}">Accueil</s:a></li>
</ul>
</div>

<table>
	<thead>
		<tr>
			<th>groupId</th>
			<th>artifactId</th>
			<th>version</th>
		</tr>
	</thead>
	<tbody>
	   <s:iterator value="plugins">
		<tr>
			<td><s:property value="groupId"/></td>
			<td><s:property value="artifactId"/></td>
			<td><s:property value="versions[0].version"/></td>
		</tr>
		</s:iterator>
	</tbody>
</table>
