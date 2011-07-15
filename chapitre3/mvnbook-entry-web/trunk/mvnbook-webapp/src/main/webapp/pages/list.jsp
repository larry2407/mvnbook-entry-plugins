<%@ page language="java" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="hasFieldErrors() || hasActionErrors() || erreur != null">
    <div id="message">
        <div class="erreur erreur_contenu">
            <s:fielderror/>
            <s:actionerror/>
            <s:property value="erreur"/>
        </div>
    </div>
</s:if>