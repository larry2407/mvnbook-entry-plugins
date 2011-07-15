<%@ page language="java" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!-- 
	Page de gestion des exceptions inattendues.
	
	Si Javascript est activé, la log d'erreur n'est pas affichée par défaut.
	Il faut appuyer sur le lien pour l'afficher.
 -->
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>


<script language="Javascript">
/**
 *  Permet d'afficher ou de masque un élément de la page.
 */
function afficherMasquerStack(element)
{
	var etat = document.getElementById(element).style.display;
	if( etat == "none"){
		document.getElementById(element).style.display="block";
	}
	else{
		document.getElementById(element).style.display="none";
	}
}
</script>


<div id="exception-cir">
	<h2>Une exception inattendue s'est produite</h2>
		<h3>Informations sur l'erreur</h3>
		<%
			//Ne pas renseigner la date dans une action car l'exception peut arriver après.
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		%>
		<p>Date : <strong><%=sdf.format(new Date())%></strong></p>
		<p>Il est recommandé de remonter cette erreur à votre administrateur système
		ou à votre support technique.<br />
		Merci de votre coopération.</p>
	
		<hr/>
		<h3>Message d'erreur</h3>
			<p><s:property value="exception" /></p>
	
		<h3>Détail technique</h3> 
			<s:a href="javascript:afficherMasquerStack('detailException')">[ Afficher/Masquer le d�tail technique de l'erreur ]</s:a>
			<div name="detailException" id="detailException" style="display:none"> 
				<p><s:property value="exceptionCause" /></p><!-- Variable renseign�e dans CirBaseAction. -->
				<p><s:property value="exceptionStack" /></p>
			</div>
			<!-- Code à afficher dans le cas où le javascript n'est pas activé -->
			<noscript>
				<p><s:property value="exceptionCause" /></p><!-- Variable renseignée dans CirBaseAction. -->
				<p><s:property value="exceptionStack" /></p>
			</noscript>
</div>

