<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>MvnBook Entry-Plugins - Point d'entrée des plugins Maven</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/nav-menus.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/stuHover.js"></script>
<script type="text/javascript" src="js/jquery.flow.1.2.auto.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#myController").jFlow({
		slides: "#slides",
		controller: ".jFlowControl", // must be class, use . sign
		slideWrapper : "#jFlowSlide", // must be id, use # sign
		selectedWrapper: "jFlowSelected",  // just pure text, no sign
		auto: true,		//auto change slide, default true
		width: "554px",
		height: "228px",
		duration: 600,
		prev: ".jFlowPrev", // must be class, use . sign
		next: ".jFlowNext" // must be class, use . sign
	});
});
</script>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
</head>
<body>
	<div class="wrap" id="three_columns">
		<tiles:insertAttribute name="header" />
		<div class="content clearfix">
			<tiles:insertAttribute name="sidebar-left" />
			<div class="main_content">
					<s:url id="linkUpload" action="uploadPlugin"  />
					<s:url id="linkHome" action="home"  />
				 	<h1 class="page_title">Gestion des plugins Apache Maven</h1>
					<div class="bredcrum"><a href="./home.action">Accueil</a> &gt; <a href="./home.action">Liste des plugins</a> &gt;</div>
					<div id="menu">
					<ul>
					<li><s:a href="%{linkUpload}">Ajouter un plugin</s:a></li>
					</ul>
					</div>
				<tiles:insertAttribute name="content" />
			</div>
			<tiles:insertAttribute name="sidebar-right" />
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
