<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/initTag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="platform"/> - 500 <spring:message code="error.500"/></title>
</head>

<body class="gray-bg">
    <div class="middle-box text-center animated fadeInDown">
        <h1>500</h1>
        <h3 class="font-bold"><spring:message code="server.error"/></h3>

        <div class="error-desc">
            <spring:message code="server.readly.error"/>...
        </div>
    </div>
</body>
</html>