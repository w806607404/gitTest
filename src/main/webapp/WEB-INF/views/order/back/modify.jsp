<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/back/updateBack.shtml">
		<input type="hidden" id="id" name="id" value="${back.id}"/>
		<input type="hidden" id="user_id" name="user_id" value="${back.user_id}"/>
		<input type="hidden" id="parent_id" name="parent_id" value="${back.parent_id}"/>		
		<input type="hidden" id="status" name="status" value="${back.status}"/>
		<div class="panel-body">
		<div style="display: ${back.status == 0 ? 'inline' : 'none'};">
			<div class="form-group">
					<label class="col-sm-3 control-label">产品id</label>
				<div class="col-sm-8">
					<input type="text" value="${back.device_id}" class="form-control"   name="device_id" id="device_id" maxlength="32">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">退货类型</label>
				<div class="col-sm-8">					
					<select id="back_type" name="back_type" tabindex="-1" class="form-control" ">
						 	<option value=' '>请选择</option>						    							
							<option value='0' <c:if test="${back.back_type == 0}">selected</c:if>>退货</option>
							<option value='1' <c:if test="${back.back_type == 1}">selected</c:if>>换货</option>								
					</select>
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">退换货原因</label>
				<div class="col-sm-8">
					<input type="text"  class="form-control" value="${back.cause}" name="cause" id="cause" maxlength="200">
				</div>
			</div>
			</div>			
			<div class="form-group" style="display: ${back.status == 1 ? 'inline' : 'none'};">
					<label class="col-sm-3 control-label">物流信息</label>
				<div class="col-sm-8">
					<input type="text"  class="form-control" value="${back.shipping}" name="shipping" id="shipping" maxlength="100">
				</div>
			</div>
			
		<div class="text-right col-sm-12">
		   <button type="button"  class="btn btn-primary btn-s-xs" onclick="doSubmit();"><spring:message code='font.framework.submit'/></button>
		</div>
	</form>
</div>
</div>
<script type="text/javascript">

function doSubmit(){
   
	var device_id = $('#device_id').val();
	var back_type = $('#back_type').val();
	var cause = $('#cause').val();
	var status = $('#status').val();	
	var shipping = $('#shipping').val();		
	if(status == 0){
		if (device_id == "") {
			layer.msg("产品ID不能为空");
			return false;
		}
	
		if (back_type == "") {
			layer.msg("退货类型不能为空");
			return false;
		}
		if (cause == "") {
			layer.msg("退货原因不能为空");
			return false;
		}
	}
	
	if(status == 1){
		$('#status').val(2);
		if (shipping == "") {
			layer.msg("物流信息不能为空");
			return false;
		}
	}
	  	 
	  	
    layer.confirm('请检查数据是否正确?', function(index) {
	    var data = CommnUtil.ajax($("#addForm").attr("action"),$("#addForm").serialize(),"json");
		if(data.status == 200){
			parent.layer.msg("<spring:message code='modify_success'/>");
			parent.pageTable.refreshPage();
			parent.layer.close(parent.pageii);
		}else if(data.status==500){
			layer.msg("修改失败");
			return false;
		}else{
			layer.msg("<spring:message code='modify_fail'/>");
		}
	});
}
</script>	
