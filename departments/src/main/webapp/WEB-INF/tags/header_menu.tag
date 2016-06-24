<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="m"%>

<c:url value="/rest_template.html" var="rest"/>
<c:url value="/list.html" var="jq"/>
<c:url value="/main.html" var="main"/>


<nav class="navbar navbar-inverse navbar-fixed-top">
    
    <div class="container-fluid">
        <div class="navbar-header">
            <div class="navbar-brand"><spring:message code="title"/></div>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${main}">main</a></li>
            <li><a href="${jq}">jQuery</a></li>
            <li><a href="${rest}">RestTemplate</a></li>
        </ul>
    </div>
</nav>