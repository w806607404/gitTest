<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${ctx}/static"/>

<link href="${ctxStatic}/css/bootstrap.min.css-v=3.3.5.css" rel="stylesheet">
<link href="${ctxStatic}/css/page.style.min.css-v=4.0.0.css" rel="stylesheet">
<link href="${ctxStatic}/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="${ctxStatic}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<link href="${ctxStatic}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="${ctxStatic}/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">
<link href="${ctxStatic}/css/animate.min.css" rel="stylesheet">
<link href="${ctxStatic}/css/plugins/bootstrap_select/css/bootstrap-select.css" rel="stylesheet"/>
<link href="${ctxStatic}/css/plugins/webuploader/webuploader.css" rel="stylesheet"/>
<link href="${ctxStatic}/css/style.css" rel="stylesheet"/>

<link href="${ctxStatic}/css/plugins/bootstrap_datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/css/plugins/select2/select2.min.css" rel="stylesheet" type="text/css" />



<script src="${ctxStatic}/js/jquery.min.js-v=2.1.4.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/bootstrap.min.js-v=3.3.5.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/plugins/layer/layer.min.js"  type="text/javascript"></script>
<script src="${ctxStatic}/js/plugins/bootstrap-table/bootstrap-table.min.js"  type="text/javascript" ></script>
<script src="${ctxStatic}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"  type="text/javascript" ></script>
<script src="${ctxStatic}/js/plugins/validate/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/plugins/validate/messages_zh.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/plugins/sweetalert/sweetalert.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/plugins/toastr/toastr.min.js"  type="text/javascript"></script>
<script src="${ctxStatic}/js/plugins/toastr/toastr-common.js"  type="text/javascript"></script>
<script src="${ctxStatic}/js/plugins/treeview/bootstrap-treeview.js"  type="text/javascript"></script>
<script src="${ctxStatic}/js/plugins/select2/select2.js" type="text/javascript"></script>

<script src="${ctxStatic}/js/plugins/bootstrap_select/js/bootstrap-select.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/plugins/bootstrap_datetimepicker/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/plugins/webuploader/webuploader.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/plugins/bootstrap_datetimepicker/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/common.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/plugins/resource/ajaxfileupload.js" type="text/javascript" ></script>
<script src="${ctxStatic}/js/cookieutil.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/laydate/laydate.js" type="text/javascript" ></script>
<script src="${ctxStatic}/js/plugins/jquery/jquery.form.js" type="text/javascript"></script>

<script type="text/javascript">
 $.fn.modal.Constructor.prototype.enforceFocus = function () {};
 </script>