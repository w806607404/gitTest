<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/bill/addBill.shtml">
		<input type="hidden" id="user_id" name="user_id" value="${user.id}"/>
		<input type="hidden" id="parent_id" name="parent_id" value="${user.parent_user_id}"/>		
		<input type="hidden" id="status" name="status" value="0"/>
		<input type="hidden" id="ratio_amount" name="ratio_amount" value="${user.ratio_amount}"/>
		<input type="hidden" id="subsidy_amount" name="subsidy_amount" value="${user.subsidy_amount}"/>
			
				
		<div class="panel-body">
			<div class="form-group" id="fecthByAmount">
				<label class="col-sm-3 control-label">分红余额 ：${user.ratio_amount}</label>
				<%-- <div class="col-sm-8">
				<input type="text" id="" name="" value="${user.ratio_amount}"/>
				</div> --%>
				<label class="col-sm-3 control-label">补贴余额 ：${user.subsidy_amount}</label>
				<%-- <div class="col-sm-8">
				<input type="text" id="" name="" value="${user.subsidy_amount}"/>
				</div> --%>
			</div>
							
			<div class="form-group">
					<label class="col-sm-3 control-label">结算金额种类</label>
				<div class="col-sm-8">
					<select id="close_type" name="close_type"  tabindex="-1" class="form-control" >					    						
						<option value='0' >分红金额</option>
						<option value='1' >补贴金额</option>						
					</select>					
				</div>
			</div>
			
			<div class="form-group">
					<label class="col-sm-3 control-label">提现金额</label>
				<div class="col-sm-8">
					<input type="text" placeholder="请填写提现金额（必填）" class="form-control"   name="amount" id="amount" maxlength="16">
					<span style="color:#F00" id="result" >输入金额超过实际余额</span>
				</div>
			</div>
			
			
			<div class="form-group">
					<label class="col-sm-3 control-label">提现方式</label>
				<div class="col-sm-8">
					<select id="target" name="target"  tabindex="-1" class="form-control" >
					    <option value=''>请选择</option>						
						<option value='0' >支付宝</option>
						<option value='1' >微信</option>
						<option value='2' >银行卡</option>				
					</select>					
				</div>
			</div>
			<div class="form-group">
				<div class="text-right col-sm-12">
		   			<button type="button" id="button_id" class="btn btn-primary btn-s-xs" onclick="doSubmit();"><spring:message code='font.framework.submit'/></button>
				</div>
			</div>
	</form>
</div>
</div>
<script type="text/javascript">
	$("#fecthByAmount").hide();
	$("#result").hide();
	function doSubmit() {
		var amount = $('#amount').val();
		var close_type = $('#close_type').val();
		var target = $('#target').val();
		
	
		if (amount == "") {
			layer.msg("提现金额不能为空");
			return false;
		}
		
		if (close_type == "") {
			layer.msg("结算金额种类不能为空");
			return false;
		}

		if (target == "") {
			layer.msg("提现方式不能为空");
			return false;
		}
		
		if(close_type == 0){
			if(amount < 100){
				layer.msg("提现分红金额必须大于等于100元");
				return false;
			}
		}
		
		if(close_type == 1){
			if(amount < 1000){
				layer.msg("提现补贴金额必须大于等于1000元");
				return false;
			}
		}
		
		
		
		layer.confirm('请检查数据是否正确?', function(index) {
			var data = CommnUtil.ajax($("#addForm").attr("action"), $(
					"#addForm").serialize(), "json");
			if (data.status == 200) {
				parent.layer.msg("<spring:message code='add_success'/>");
				parent.pageTable.refreshPage();
				parent.layer.close(parent.pageii);
			} else if (data.status == -101) {
				layer.msg("输入金额大于实际余额");
				return false;
			} else {
				layer.msg("<spring:message code='add_fail'/>");
			}
		});
	}

		
	 $('#amount').focus(function(event) {		 
		 var close_type = $('#close_type').val();
		 $("#fecthByAmount").show();
     })	
     
     $('#amount').blur(function(event) {   
		 var amount = $('#amount').val();
		 var close_type = $('#close_type').val();
		 var ratio_amount = $('#ratio_amount').val();
		 var subsidy_amount = $('#subsidy_amount').val();
		 var reg = /^[0-9]*$/;
		if (!reg.test(amount)) {
			layer.msg("提现金额不合法,只允许是数字");
			$('#amount').val('');
			return false;
			}
		 if(close_type == 0){
			 if(parseInt(amount) > parseInt(ratio_amount)){
				 $("#result").show();
				 $("#button_id").attr('disabled',true);
				 return false;
			 }else{
				 $("#result").hide();
				 $("#button_id").attr('disabled',false);
			 }
		 }else if(close_type == 1){
			 if(parseInt(amount) > parseInt(subsidy_amount)){
				 $("#result").show();
				 $("#button_id").attr('disabled',true);
				 return false;
			 }else{
				 $("#result").hide();
				 $("#button_id").attr('disabled',false);
			 }
		 }
		 
	 })
</script>	
