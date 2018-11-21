<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addFormShipping" name="form" class="form-horizontal" method="post" action="${ctx}/notifySend/setShipping.shtml">
		<input type="hidden" id="id" name="id" value="${notifySend.id}"/>
		<input type="hidden" id="status" name="status" value="${notifySend.status}"/>
		<input type="hidden" id="send_userId" name="send_userId" value="${notifySend.send_userId}"/>
		<input type="hidden" id="receive_userId" name="receive_userId" value="${notifySend.receive_userId}"/>
		<input type="hidden" id="amount" name="amount" value="${notifySend.amount}"/>
		<input type="hidden" id="notify_type" name="notify_type" value="${notifySend.notify_type}"/>		
		
		<div class="panel-body">
			<div class="form-group">
					<label class="col-sm-3 control-label">物流单号</label>
				<div class="col-sm-8">
					<input type="text"  placeholder="请填写物流信息 例：（顺丰：8888FFFF）或（商家自送）"  class="form-control" value="${notifySend.shipping}"  name="shipping" id="shipping" maxlength="100">
				</div>
			</div>
						
		<div class="text-right col-sm-12">
		   <button type="button"  class="btn btn-primary btn-s-xs" onclick="doSubmit();"><spring:message code='font.framework.submit'/></button>
		</div>
	</form>
</div>
</div>
<script type="text/javascript">
$(document).ready(function(e) {
	
});


function doSubmit(){
	var shipping = $('#shipping').val(); 
    if(shipping==""){
	    	layer.msg("物流单号不能为空");
	    	return false;
	    }
	  	 
     	 
    layer.confirm('请检查数据是否正确?', function(index) {
	    var data = CommnUtil.ajax($("#addFormShipping").attr("action"),$("#addFormShipping").serialize(),"json");
		if(data.status == 200){
			parent.layer.msg("<spring:message code='success_operat'/>");
			parent.pageTable.refreshPage();
			parent.layer.close(parent.pageii);
		}else if (data.status == -101) {			
			layer.msg("该记录状态不是待发货或是您自己发送的发货通知,操作失败");
			return false;
		}else if(data.status==-102){
			layer.msg("厂商(代理商)还未发货，无法确认收货");
			return false;
		}else{
			layer.msg("<spring:message code='operat_fail'/>");
		}
	});
}
</script>	
