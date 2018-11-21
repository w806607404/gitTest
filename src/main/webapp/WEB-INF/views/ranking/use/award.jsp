<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/ranking/use/addAward.shtml">
		<input type="hidden" id="id" name="id" value="${id}"/>
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">当前拥有红包金额</label>
				<div class="col-sm-3">
					<input type="text" readonly="true" class="form-control" value="${redEnvelopeAmount}"   maxlength="32">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">奖励金额</label>
				<div class="col-sm-3">
					<input type="text" placeholder="请输入奖励金额（必填）" class="form-control"  name="redEnvelopeAmount" id="redEnvelopeAmount" maxlength="16">
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
    var redEnvelopeAmount=$('#redEnvelopeAmount').val();  
    
    if(redEnvelopeAmount==""){
    	layer.msg("奖励金额不能为空");
    	return false;
    }
       
    if(redEnvelopeAmount<=0){
    	layer.msg("奖励金额必须大于0");
    	return false;
    }
	
     layer.confirm('请检查数据是否正确?', function(index) {
	    var data = CommnUtil.ajax($("#addForm").attr("action"),$("#addForm").serialize(),"json");
		if(data.status == 200){
			parent.layer.msg("<spring:message code='add_success'/>");
			parent.pageTable.refreshPage();
			parent.layer.close(parent.pageii);
		}else{
			layer.msg("<spring:message code='add_fail'/>");
		}
     });
}



</script>	
