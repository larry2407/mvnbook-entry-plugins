<%@ page language="java" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<s:if test="hasFieldErrors() || hasActionErrors()">
    <div id="msg">
        <div class="errors">
            <s:fielderror/>
            <s:actionerror/>
        </div>
    </div>
</s:if>

<s:form action="showPlugin" namespace="/" method="post" enctype="multipart/form-data">
	<s:file name="fileUpload" label="Choissisez un fichier (.pom ou .xml)"  size="40" />
	<s:submit value="Ajouter le plugin" name="submit" />
</s:form>