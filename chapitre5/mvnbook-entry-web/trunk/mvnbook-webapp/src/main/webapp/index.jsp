<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<h1 class="page_title">Gestion des plugins Apache Maven</h1>
<div class="bredcrum"><a href="#">Online Store</a> &gt; <a href="#">Bathroom</a> &gt; <a href="#">Cabinets, shelves &amp; sink cabinets </a></div>
<div id="menu">
<s:url id="linkUpload" action="uploadPlugin"  />
<s:url id="linkHome" action="home"  />
<ul>
<li><s:a href="%{linkUpload}">Ajouter un plugin</s:a></li>
<li><s:a href="%{linkHome}">Accueil</s:a></li>
</ul>
</div>

      
      <div class="products_box featured">
        <h2 class="box_title">Liste des plugins</h2>
        <div class="products_content">
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
        </div>
      </div>
      <div class="pagination"> 
      <ul class="page_nav">
        <li class="prev"><a href="#"><img src="images/pag_nav-arrow-left.gif" alt="" width="5" height="8">Prev</a></li>
        <li class="current_page"><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li class="skip_pages">...</li>
        <li><a href="#">14</a></li>
        <li><a href="#">15</a></li>
        <li class="next"><a href="#">Next<img src="images/pag_nav-arrow-right.gif" alt="" width="5" height="8"></a></li>
        </ul>
        <form id="form2" name="form2" method="post" action="" class="jqtransform jqtransformdone">
          <div class="sortby">Order by:</div> <label>
            <div style="z-index: 10; width: 87px;" class="jqTransformSelectWrapper"><div><span style="width: 67px;">Date</span><a href="#" class="jqTransformSelectOpen"></a></div><ul style="width: 85px; display: none; visibility: visible; height: 80px; overflow: hidden;"><li><a class="selected" href="#" index="0">Date</a></li><li><a href="#" index="1">Title</a></li><li><a href="#" index="2">Rating</a></li><li><a href="#" index="3">Price</a></li></ul><select style="" class="jqTransformHidden" name="select2" id="select2">
              <option value="1">Date</option>
              <option value="2">Title</option>
              <option value="3">Rating</option>
              <option value="4">Price</option>
            </select></div>
          </label>
        </form>
      </div>
      