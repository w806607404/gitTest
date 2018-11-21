<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<style>
	.pdtop8{
		padding-top:8px;
	}
</style>

<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/user/update.shtml">
		<input type="hidden" id="id" name="id" value="${accountInfo.id}" />
		<input type="hidden" id="display" name="display" value="${display}" />
		<div class="panel-body">
			<div class="form-group">
					<label class="col-sm-3 control-label">用户帐号</label>
				<div class="col-sm-8">
					<input type="text" placeholder="账号名称不能为空，且8~16位，字母开头（必填）" class="form-control"  name="user_name" value="${accountInfo.user_name}" id="user_name" maxlength="16" readonly="true" style="cursor:hand">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">用户类型</label>
				<div class="col-sm-8">
					<input type="text" placeholder="账号名称不能为空，且8~16位，字母开头（必填）" class="form-control"  name="role_name" value="${accountInfo.role_name}" id="role_name" maxlength="16" readonly="true" style="cursor:hand">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">付费凭证</label>
				<div class="col-sm-8">
					<input type="text" class="form-control"  name="pay_proof" id="pay_proof" maxlength="16" value="${accountInfo.pay_proof}" readonly="true" style="cursor:hand">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">审核</label>
				<div class="col-sm-8 pdtop8" >
					<nobr><input name="status" type="radio" value="5" checked="checked" />&nbsp;通过审核  &nbsp;&nbsp;   <input name="status" type="radio" value="3" />&nbsp;审核不通过  </nobr>
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

function doSubmit(){
     layer.confirm('请检查数据是否正确?', function(index) {
	    var data = CommnUtil.ajax($("#addForm").attr("action"),$("#addForm").serialize(),"json");
		if(data.status == 200){
			parent.layer.msg("操作成功");
			parent.pageTable.refreshPage();
			parent.layer.close(parent.pageii);
		}
		else if(data.status==-101){
			layer.msg("该用户账户已存在");
			return false;
		}
		else if(data.status==-102){
			layer.msg("该区域已有代理商已存在");
			return false;
		}
		else if(data.status==-103){
			layer.msg("该区域代理商还在审核中或者预约状态，请先审核该区域代理商！");
			return false;
		}
        else if(data.status==-104){
            layer.msg("该账号可能存在异常，不允许通过审核，请重新提交审核凭证！");
            return false;
        }
		else{
			layer.msg("操作失败");
		}
     });
}

</script>	
