<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="m"%>

<c:url var="defContextPath" value="/" />


<c:url value="/css" var="cssPath" scope="request" />
<c:url value="/js" var="jsPath" scope="request" />

<!DOCTYPE html>
<html  >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="${cssPath}/toastr.min.css" />
<link rel="stylesheet" href="${cssPath}/custom.css" />
<script src="${jsPath}/jquery-2.2.4.min.js"></script>
<script src="${jsPath}/toastr.min.js"></script>
<script src="${jsPath}/bootstrap.min.js"></script>
<script src="${jsPath}/moment.js"></script>
<script src="${jsPath}/bootstrap-datetimepicker.min.js"></script>
<script src="${jsPath}/utils.js"></script>

<title><spring:message code="title" /></title>

</head>
<body >
    <header >
        <tiles:insertAttribute name="header" />
    </header>
    <main class="container">
        <tiles:insertAttribute name="content" />
    </main>
    <footer>
        <tiles:insertAttribute name="footer" />
    </footer>
</body>
</html>
