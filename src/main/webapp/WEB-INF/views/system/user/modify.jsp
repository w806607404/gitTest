<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/user/update.shtml">
		<input type="hidden" id="id" name="id" value="${accountInfo.id}" />
		<input type="hidden" id="display" name="display" value="${display}" />
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">用户昵称</label>
				<div class="col-sm-8">
					<input type="text" placeholder="用户昵称不能为空（必填）" class="form-control"  name="user_nickname" id="user_nickname" value="${accountInfo.user_nickname}" maxlength="32" style="cursor:hand">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">帐号名称</label>
				<div class="col-sm-8">
					<input type="text" placeholder="账号名称不能为空，且11位数字组成的手机号码（必填）" class="form-control"  name="user_name" value="${accountInfo.user_name}" id="user_name" maxlength="11" readonly="true" style="cursor:hand">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">用户类型</label>
				<div class="col-sm-8">
					<input type="text" placeholder="账号名称不能为空，且8~16位，字母开头（必填）" class="form-control"  name="role_name" value="${accountInfo.role_name}" id="role_name" maxlength="16" readonly="true" style="cursor:hand">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">联系人</label>
				<div class="col-sm-8">
					<input type="text" placeholder="联系人不能为空（必填）" class="form-control"  name="agent_name" id="agent_name" maxlength="32" value="${accountInfo.agent_name}"  style="cursor:hand">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">联系电话</label>
				<div class="col-sm-8">
					<input type="text" placeholder="联系电话不能为空（必填）" class="form-control"  name="agent_phone" id="agent_phone" maxlength="16" value="${accountInfo.agent_phone}" style="cursor:hand">
				</div>
			</div>
			
		</div>
		<div class="text-right col-sm-12">
		   <button type="button"  class="btn btn-primary btn-s-xs" onclick="doSubmit();"><spring:message code='font.framework.submit'/></button>
		</div>
	</form>
</div>
</div>
<script type="text/javascript">
$ScinanSelect2({id:'role_id',placeholder: "请选择所属用户组"});

function doSubmit(){

    var user_nickname=$('#user_nickname').val();  
    var user_name = $('#user_name').val();
    var role_id = $('#role_id').val();
    var agent_name = $('#agent_name').val();
    var agent_phone = $('#agent_phone').val();

  	if(user_nickname==""){
    	layer.msg("用户昵称不能为空");
    	return false;
    }

	if(agent_name==""){
    	layer.msg("联系人不能为空");
    	return false;
    }
    
	if(agent_phone==""){
    	layer.msg("联系电话不能为空");
    	return false;
    }else{
	   var account_reg = /^[1][3,4,5,7,8][0-9]{9}$/; 
		if(!account_reg.test(agent_phone)){
			layer.msg("联系电话格式不合法,长度为11位且由数字组成的手机号码");
			return;
		}
    }
	


     layer.confirm('请检查数据是否正确?', function(index) {
	    var data = CommnUtil.ajax($("#addForm").attr("action"),$("#addForm").serialize(),"json");
		if(data.status == 200){
			parent.layer.msg("修改成功");
			parent.pageTable.refreshPage();
			parent.layer.close(parent.pageii);
		}
		else if(data.status==-101){
			layer.msg("该用户昵称已存在");
			return false;
		}
		else{
			layer.msg("修改失败");
		}
     });
}

</script>	
