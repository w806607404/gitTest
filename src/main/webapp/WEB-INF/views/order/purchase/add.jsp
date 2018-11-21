<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/purchase/addPurchase.shtml">
		<input type="hidden" id="user_id" name="user_id" value="${user.id}"/>
		<input type="hidden" id="role_type" name="role_type" value="${user.role_type}"/>
		<input type="hidden" id="parent_id" name="parent_id" value="${user.parent_user_id}"/>
		<input type="hidden" id="company_id" name="company_id" value="${user.company_id}"/>
		<input type="hidden" id="status" name="status" value="0"/>	
				
		<div class="panel-body">			
			<div class="form-group">
					<label class="col-sm-3 control-label">您的分红余额</label>
				<div class="col-sm-8">					
					<input type="text" readOnly="true" class="form-control" value="${user.ratio_amount}"  name="ratio_amount" id="ratio_amount" >					
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">您的补贴余额</label>
				<div class="col-sm-8">					
					<input type="text" readOnly="true" class="form-control" value="${user.subsidy_amount}" name="subsidy_amount" id="subsidy_amount" >					
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">产品名称</label>
				<div class="col-sm-8">
					<select id="device_type" name="device_type"  tabindex="-1" class="form-control" onchange="deviceChange(this.value)">
					    <option value=''>请选择产品名称</option>						
						<c:forEach items="${deviceList}" var="key">
							<option value='${key.id}'>${key.device_name}</option>
						</c:forEach>						
					</select>					
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">产品单价</label>
				<div class="col-sm-8">					
					<input type="text" readOnly="true" class="form-control"  name="product_price" id="product_price" >					
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">数量</label>
				<div class="col-sm-8">
					<input type="text"  class="form-control"  name="count" id="count" maxlength="32" onkeyup="sum(this.value)">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">总价格</label>
				<div class="col-sm-8">					
					<input type="text" readOnly="true" class="form-control"  name="amount" id="amount" >
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">是否开具发票</label>
				<div class="col-sm-8">
					<select id="invoice" name="invoice"  tabindex="-1" class="form-control" >					    						
						<option value='0' >否</option>
						<option value='1' >是</option>									
					</select>
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">支付方式</label>
				<div class="col-sm-8">
					<select id="pay_type" name="pay_type"  tabindex="-1" class="form-control" onchange="payChange(this.value)">					    						
						<option value='0' >线下转账</option>
						<option value='1' >分红余额</option>
						<option value='2' >补贴余额</option>			
					</select>
					<span style="color:#F00" id="payResult" >余额不足，请重新选择支付方式</span>					
				</div>
			</div>
			
		<div class="text-right col-sm-12">
		   <button id="button_id" type="button"  class="btn btn-primary btn-s-xs" onclick="doSubmit();"><spring:message code='font.framework.submit'/></button>
		</div>
	</form>
</div>
</div>
<script type="text/javascript">
	$("#payResult").hide();
	function doSubmit() {
		var device_type = $('#device_type').val();
		var count = $('#count').val();

		var role_type =$("#role_type").val();
		if(role_type == 2){
			if(count < 10){
				layer.msg("起购10个以上");
				return;
			}
		}
		if(role_type == 3){
			if(count < 5){
				layer.msg("起购5个以上");
				return;
			}
		}
		
		if (device_type == "") {
			layer.msg("请选择产品名称");
			return false;
		}
		
		if (count == "") {
			layer.msg("产品数量不能为空");
			return false;
		}

		

		layer.confirm('请检查数据是否正确?', function(index) {
			var data = CommnUtil.ajax($("#addForm").attr("action"), $(
					"#addForm").serialize(), "json");
			if (data.status == 200) {
				parent.layer.msg("申请购货成功");
				parent.pageTable.refreshPage();
				parent.layer.close(parent.pageii);
			} else if (data.status == -101) {
				layer.msg("申请购货失败");
				return false;
			} else {
				layer.msg("申请购货失败");
			}
		});
	}
	
	function deviceChange(code){
		if(code!='' && code!=null){
			var obj = CommnUtil.ajax("${ctx}/purchase/fetchProductPrice.shtml",{id:code},"json");			
			if(obj != null){
				$("#product_price").val(obj);
	       	}
		}else{
			$("#product_price").val("");
		}
		
	}
	
	function sum(value){
		var role_type =$("#role_type").val();
		if(role_type == 2){
			if(value < 10){
				layer.msg("起购10个以上");
			}
		}
		if(role_type == 3){
			if(value < 5){
				layer.msg("起购5个以上");
			}
		}
		var ratio_amount = $("#ratio_amount").val();
		var subsidy_amount = $("#subsidy_amount").val();
		var payType = $("#pay_type").val();
		var product_price =$("#product_price").val();
		var amount = product_price * value;	
		if(product_price!= null && product_price!=""){
			$("#amount").val(product_price * value);
		}
		if(value==null || value==""){
			$("#amount").val("");
		}
		if(payType==1){
			if(parseInt(amount) > parseInt(ratio_amount)){
				$("#payResult").show();
				$("#button_id").attr('disabled',true);
			}else{
				$("#payResult").hide();
				$("#button_id").attr('disabled',false);
			}
		}else if(payType==2){
			if(parseInt(amount) > parseInt(subsidy_amount)){
				$("#payResult").show();
				$("#button_id").attr('disabled',true);
			}else{
				$("#payResult").hide();
				$("#button_id").attr('disabled',false);
			}
		}else{
			$("#payResult").hide();
			$("#button_id").attr('disabled',false);
		}	
		/* layer.msg("value="+parseInt(value)); */
	}
	
	function payChange(code){
		var amount = $("#amount").val();
		var ratio_amount = $("#ratio_amount").val();
		var subsidy_amount = $("#subsidy_amount").val();
		if(code==1){
			if(parseInt(amount) > parseInt(ratio_amount)){
				$("#payResult").show();
				$("#button_id").attr('disabled',true);
			}else{
				$("#payResult").hide();
				$("#button_id").attr('disabled',false);
			}
		}else if(code==2){
			if(parseInt(amount) > parseInt(subsidy_amount)){
				$("#payResult").show();
				$("#button_id").attr('disabled',true);
			}else{
				$("#payResult").hide();
				$("#button_id").attr('disabled',false);
			}
		}else{
			$("#payResult").hide();
			$("#button_id").attr('disabled',false);
		}	
	}
</script>	
