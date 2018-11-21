<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/initTag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="platform"/> - 404 <spring:message code="page.404"/></title>
</head>

<body class="gray-bg">
    <div class="middle-box text-center animated fadeInDown">
        <h1>404</h1>
        <h3 class="font-bold"><spring:message code="no.page"/>！</h3>

        <div class="error-desc">
           <spring:message code="sorry.404"/>
        </div>
    </div>
</body>
</html>