<%@ page language="java" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

 <div id="head">
   <h1 class="logo"><a href="#"><span>MNVBOOK-ENTRY-PLUGINS</span></a></h1>
   <div class="basket">
   	<a href="#">Apache Maven</a> | <a href="#">Doc</a> | <a href="#">Sonatype</a> | <a href="#">Codehaus</a>
   </div>
   <div class="top_banner"></div>
   <div id="search">
     <form id="searchform" name="searchform" action="02-Orange-01-Home-Page.html">
       <input name="search" type="text" tabindex="1" onblur="if (this.value==''){this.value='Rechercher un plugin'};" onfocus="if(this.value=='Rechercher un plugin'){this.value=''};" value="Rechercher un plugin" />
       <input name="search" type="submit" value="" class="button" />
     </form>
   </div>
 </div>

