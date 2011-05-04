<%@ page language="java" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

 <div id="head">
   <h1 class="logo"><a href="02-Orange-01-Home-Page.html"><span>Classic Online Store - good products, cheep prices</span></a></h1>
   <div class="basket"><a href="02-Orange-05-Customer-Basket.html"><span>Shopping Basket</span></a>
     <p><span>$260.00</span> Item(s): <strong>2</strong></p>
   </div>
   <div class="top_banner"><a href="02-Orange-03-Products-List.html"><img src="images/top_banner.png" width="218" height="44" alt="" /></a></div>
   <div id="search">
     <form id="searchform" name="searchform" action="02-Orange-01-Home-Page.html">
       <input name="search" type="text" tabindex="1" onblur="if (this.value==''){this.value='enter a keyword or an item #'};" onfocus="if(this.value=='enter a keyword or an item #'){this.value=''};" value="enter a keyword or an item #" />
       <input name="search" type="submit" value="" class="button" />
     </form>
   </div>
 </div>
