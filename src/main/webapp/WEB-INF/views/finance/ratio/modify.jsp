<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/finance/updateRatio.shtml">
		<input type="hidden" id="id" name="id" value="${ratio.id}"/>
		
		<div class="panel-body">
			<div class="form-group">
					<label class="col-sm-3 control-label">产品单价</label>
				<div class="col-sm-8">
					<input type="text"  placeholder="请填写产品单价（必填）" class="form-control" value="${ratio.product_price}"  name="product_price" id="product_price" maxlength="32">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">代理商分成比例</label>
				<div class="col-sm-8">
					<input type="text" placeholder="请填写代理商分成比例（必填）" class="form-control" value="${ratio.agent_ratio}"  name="agent_ratio" id="agent_ratio" maxlength="32">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">经销商分成比例</label>
				<div class="col-sm-8">
					<input type="text" placeholder="请填写经销商分成比例（必填）" class="form-control" value="${ratio.dealer_ratio}"  name="dealer_ratio" id="dealer_ratio" maxlength="16">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">运费补贴比例</label>
				<div class="col-sm-8">
					<input type="text" placeholder="请填写运费补贴比例（必填）" class="form-control" value="${ratio.primage_ratio}"  name="primage_ratio" id="primage_ratio" maxlength="16">
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
    var product_price = $('#product_price').val();
    var agent_ratio = $('#agent_ratio').val();
    var dealer_ratio = $('#dealer_ratio').val();
    var primage_ratio = $('#primage_ratio').val();
    
    
  
     if(agent_ratio==""){
	    	layer.msg("代理商分成比例值不能为空");
	    	return false;
	    }else{
	    	 var _ratio = Math.round(agent_ratio);
	    	 if(_ratio>100){
	    		 layer.msg("代理商分成比例值不能大于100");
	    	     return false;
	  		 }
	  	 }
	  	 
	  if(primage_ratio==""){
	    	layer.msg("运费补贴分成比例值不能为空");
	    	return false;
	    }else{
	    	 var _ratio = Math.round(primage_ratio);
	    	 if(_ratio>100){
	    		 layer.msg("运费补贴分成比例值不能大于100");
	    	     return false;
	  		 }
	  	 }
	 if(dealer_ratio==""){
	    	layer.msg("经销商分成比例值不能为空");
	    	return false;
	    }else{
	    	 var _ratio = Math.round(dealer_ratio);
	    	 if(_ratio>100){
	    		 layer.msg("经销商分成比例值不能大于100");
	    	     return false;
	  		 }
	  	 }
	  	 
	  	
	   var reg = /^\d+(\.\d{1,2})?$/;
    if(!reg.test(product_price)){
		 layer.msg("产品单价值不合法,只允许是数字且最多保留2位小数");
	     return false;
  	 }
	if(!reg.test(agent_ratio)){
		 layer.msg("代理商分成比例值不合法,只允许是数字且最多保留2位小数");
	     return false;
  	 }
  	 if(!reg.test(dealer_ratio)){
		 layer.msg("经销商分成比例值不合法,只允许是数字且最多保留2位小数");
	     return false;
  	 }
  	 if(!reg.test(primage_ratio)){
		 layer.msg("运费补贴分成比例值不合法,只允许是数字且最多保留2位小数");
	     return false;
  	 }
        	 
     layer.confirm('请检查数据是否正确?', function(index) {
	    var data = CommnUtil.ajax($("#addForm").attr("action"),$("#addForm").serialize(),"json");
		if(data.status == 200){
			parent.layer.msg("<spring:message code='modify_success'/>");
			parent.pageTable.refreshPage();
			parent.layer.close(parent.pageii);
		}else if(data.status==-101){
			layer.msg("该产品类型已设置");
			return false;
		}else{
			layer.msg("<spring:message code='modify_fail'/>");
		}
	});
}


</script>	
