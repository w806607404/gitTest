<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${ctx}/static"/>


<%
	String mode = request.getRequestURI();
	if (mode != null && mode.indexOf("index") != -1)
	{
%>
	<%@include file="/include/index_resources.jsp"%>
<%
	} else {
%>
	<%@include file="/include/login_resources.jsp"%>
<%
	}
%>

