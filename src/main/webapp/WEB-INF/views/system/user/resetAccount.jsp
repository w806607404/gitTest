<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/user/resetAccount.shtml">
		<input type="hidden" id="id" name="id" value="${userInfo.id }"/>
		<input type="hidden" id="account_id" name="account_id" value="${accountInfo.id }"/>
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">APP端账号</label>
				<div class="col-sm-8">
					<input type="text" placeholder="用户账号不能为空（必填）" class="form-control"  name="user_nickname" id="user_nickname" readonly="true" value="${userInfo.user_mobile}" maxlength="32">
				</div>
			</div>
			 <div class="form-group">
                  <label class="col-sm-3 control-label">新APP端账号</label>
                  <div class="col-sm-8">
					<input type="text" placeholder="新账号不能为空，长度为11位且由数字组成的手机号码（必填）" class="form-control"  name="user_mobile" id="user_mobile"  maxlength="32">
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
    var user_mobile=$('#user_mobile').val();  
    
    if(user_mobile == ""){
    	layer.msg("新用户账号不能为空");
    	return false;
    }else{
    	var account_reg = /^[1][3,4,5,7,8][0-9]{9}$/; 
		if(!account_reg.test(user_mobile)){
			layer.msg("帐号名称格式不合法,长度为11位且由数字组成的手机号码");
			return;
		}
    }
    
   
   
     layer.confirm('请检查数据是否正确?', function(index) {
	    var data = CommnUtil.ajax($("#addForm").attr("action"),$("#addForm").serialize(),"json");
		if(data.status == 200){
			parent.layer.msg("修改成功");
			parent.pageTable.refreshPage();
			parent.layer.close(parent.pageii);
		}else if(data.status==-101){
			layer.msg("该用户账户已存在");
			return false;
		}else{
			layer.msg("修改失败");
		}
     });
}

</script>	
