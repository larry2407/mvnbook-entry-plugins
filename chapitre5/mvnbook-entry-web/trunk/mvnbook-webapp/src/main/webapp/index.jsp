<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


      
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
      </div>
      