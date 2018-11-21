<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/include/page_resources.jsp"%>
<link href="${ctxStatic}/css/permissions.css" rel="stylesheet">
</head>
<script type="text/javascript">
function smenu(obj,id){  
	  $("input[_key='menu_1_"+id+"']").each(function(){
	   $(this).prop("checked",obj.checked);
	  });
	  $("input[_key='menu_1_1_"+id+"']").each(function(){
		   $(this).prop("checked",obj.checked);
		  });
};
function menu_1(obj,id,pid){  
	  $("input[_key_2='menu_1_1_"+id+"']").each(function(){
		   $(this).prop("checked",obj.checked);
	});
	  if(obj.checked==true){
		  $("input[_key='menu_"+pid+"']").each(function(){
			   $(this).prop("checked",obj.checked);
		});
	  }
};
function menu_1_1(obj,id,pid){  
	if(obj.checked==true){
		  $("input[_key_1='menu_1_1_"+id+"']").each(function(){
			   $(this).prop("checked",obj.checked);
		});
		  $("input[_key='menu_"+pid+"']").each(function(){
			   $(this).prop("checked",obj.checked);
		});
	}
}
function closeWin(){
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	parent.layer.close(index);
}
function sub(){
	var path = "";
	var userId = $('#userId').val();
// 	if(userId == "" || userId == null){
		path = "${ctx}/resources/addRoleRes.shtml";
// 	}else{
// 		path = "/resources/addUserRes.shtml";
// 	}
		sn.ajax({
			async : false, //请勿改成异步，下面有些程序依赖此请数据
			type : "POST",
			data : $("#from").serializeJson(),
			url : path,
			dataType : 'json',
			success : function(json) {
				layer.closeAll('loading');
				if (json.status == 200) {
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
					layer.msg("<spring:message code='success_operat'/>");
				} else {
					layer.msg("<spring:message code='operat_fail'/>");
				}
				;
			}
		});
}
</script>
<body>
<form method="post" id="from" name="form">
<input id='userId' name="userId" type="hidden" value="${param.userId}">
<input id='roleId' name="roleId" type="hidden" value="${role_id}">
<table id="mytable" class="mytable" cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series">
 <tr>
    <th scope="row" abbr="L2 Cache" class="specalt"><spring:message code='rolelist.a.menu'/></th>
    <th scope="row" abbr="L2 Cache" class="specalt">
	    <span><spring:message code='rolelist.sec.menu'/></span>
	    <span style="float: right;margin-right: 48%;"><spring:message code='rolelist.btn'/></span>
    </th>
  </tr>
  <c:forEach items="${permissions}" var="k">
  <tr>
    <th scope="row" abbr="L2 Cache" class="specalt">
    <input type="checkbox" name="resId" id="menu" _key="menu_${k.res_id}" onclick="smenu(this,'${k.res_id}')" value="${k.res_id}">
    ${k.res_name}
    </th>
    <th scope="row" abbr="L2 Cache" class="specalt">
    <table id="mytable" cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series" style="width: 100%;height: 100%;">
    <c:forEach items="${k.nodes}" var="kc">
    <tr>
    <th scope="row" abbr="L2 Cache" class="specalt sec-labal">
    <input type="checkbox"  name="resId" id="menu" _key="menu_1_${k.res_id}" _key_1="menu_1_1_${kc.res_id}" onclick="menu_1(this,'${kc.res_id}','${k.res_id}')"  value="${kc.res_id}">
    ${kc.res_name}
    </th>
     <th>
    <c:if test="${not empty kc.nodes}">
   
    <table id="mytable" cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series" style="width: 100%;height: 100%;">
    <c:forEach items="${kc.nodes}" var="kcc">
    <tr>
    <th scope="row" abbr="L2 Cache" class="specalt">
    <input type="checkbox"  name="resId" id="menu" _key="menu_1_1_${k.res_id}" _key_2="menu_1_1_${kc.res_id}" onclick="menu_1_1(this,'${kc.res_id}','${k.res_id}')" value="${kcc.res_id}">
    ${kcc.res_name}
    </th>
     </tr>
    </c:forEach>
   
    </table>
    
    </c:if>
    </th>
     </tr>
    </c:forEach>
   
    </table>
    </th>
  </tr>
</c:forEach>
</table>
<br>
<div class="doc-buttons" style="text-align: center;">
	<a href="#" class="btn btn-s-md btn-primary" onclick="sub()"><spring:message code='font.framework.save'/></a>
	<a href="#" class="btn btn-s-md btn-default" onclick="closeWin();"><spring:message code='font.framework.cancel'/></a>
</div>
	<br>
	</form>
	<script type="text/javascript">
	$(function(){
		$.ajax({
			type : "POST",
			data : {roleId : $('#roleId').val()},
			url : '${ctx}/resources/findRes.shtml',
			dataType : 'json',
			success : function(json) {
				for (index in json) {
					$("input[name='resId']:checkbox[value='" + json[index].res_id + "']").prop('checked', 'true');
				}
			}
		});
	});
		
	</script>
</body>
</html>