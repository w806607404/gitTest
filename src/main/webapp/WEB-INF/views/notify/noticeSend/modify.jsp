<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/notifySend/updateNotifySend.shtml">
		<input type="hidden" id="id" name="id" value="${notifySend.id}"/>
		<input type="hidden" id="status" name="status" value="${notifySend.status}"/>
		<div class="panel-body">		
		<div style="display: ${notifySend.status == 3 ? 'inline' : 'none'};">	
			<div class="form-group">
					<label class="col-sm-3 control-label">收货人名称</label>
				<div class="col-sm-8">
					<input type="text"  class="form-control"  name="user_name" id="user_name" maxlength="32" value="${notifySend.user_name}">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">收货人联系方式</label>
				<div class="col-sm-8">
					<input type="text"  class="form-control"  name="user_contact" id="user_contact" maxlength="32" value="${notifySend.user_contact}">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">收货人详细地址</label>
				<div class="col-sm-8">
					<input type="text"  class="form-control"  name="user_address" id="user_address" maxlength="32" value="${notifySend.user_address}">
				</div>
			</div>
			</div>
			<div class="form-group" style="display: ${notifySend.status == 1 ? 'inline' : 'none'};">
					<label class="col-sm-3 control-label">物流信息</label>
				<div class="col-sm-8">
					<input type="text"  class="form-control" value="${notifySend.shipping}"  name="shipping" id="shipping" maxlength="300">
				</div>
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
	$("#district_result").hide();
	var device_type =$("#device_type").val();
	if(device_type!='' && device_type!=null){
		var obj = CommnUtil.ajax("${ctx}/purchase/fetchProductPrice.shtml",{id:device_type},"json");			
		if(obj != null){
			$("#product_price").val(obj);
       	}
	}else{
		$("#product_price").val("");
	}
	
	function doSubmit() {
		var status = $('#status').val();
		var user_name = $('#user_name').val();
		var user_contact = $('#user_contact').val();
		var user_address = $('#user_address').val();
		var shipping = $('#shipping').val(); 
		
		if(status == 1){
			if (shipping == "") {
				layer.msg("物流信息不能为空");
				return false;
			}	
		}
		
		if(status == 3){
			if (user_name == "") {
				layer.msg("收货人名称不能为空");
				return false;
			}
			if (user_contact == "") {
				layer.msg("收货人联系方式不能为空");
				return false;
			}
			
			if (user_address == "") {
				layer.msg("收货人详情地址不能为空");
				return false;
			}
		}
		
		

		

		layer.confirm('请检查数据是否正确?', function(index) {
			var data = CommnUtil.ajax($("#addForm").attr("action"), $(
					"#addForm").serialize(), "json");
			if (data.status == 200) {
				parent.layer.msg("修改成功");
				parent.pageTable.refreshPage();
				parent.layer.close(parent.pageii);
			} else if (data.status == -101) {
				layer.msg("操作失败");
				return false;
			} else {
				layer.msg("操作失败");
			}
		});
	}
	//选择设备获得设备价格
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
	
	function accMul(arg1,arg2){
		var m=0,s1=arg1.toString(),s2=arg2.toString();
		try{m+=s1.split(".")[1].length}catch(e){}
		try{m+=s2.split(".")[1].length}catch(e){}
		return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
	}
	//选择数量时计算总价格
	function sum(value){
		var product_price =$("#product_price").val();		
		if(product_price!= null && product_price!=""){
			var sum = accMul(value,product_price);
			$("#amount").val(sum);
		}
		if(value==null || value==""){
			$("#amount").val("");
		}
		/* layer.msg("value="+parseInt(value)); */
		var amount = $("#amount").val();
		if(amount!=null || amount!=""){
			var notify_type = $('input[name="notify_type"]:checked').val();
			var subsidy_amount = $('#subsidy_amount').val(); //补贴余额
			var receive_user_type = $('#receive_user_type').val();
			if(notify_type == 1 && parseInt(amount) > parseInt(subsidy_amount) && receive_user_type == 1){
				//layer.msg($("#payResult").text());
				$ScinanSwal.alert($("#payResult").text());
				$(":radio[name='notify_type']").eq(1).attr("checked","true");
				$(":radio[name='notify_type']").eq(0).attr("disabled",true);
				
			}
		}
	}
	//选择支付方式验证余额
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
	//选中省
	function provinceChange(code){
		$.ajax({
		    url: "${ctx}/sold/cityList.shtml",         //请求的url地址
		    dataType: "json",   //返回格式类型为json
		    async: false,        //请求是否异步，默认为true:异步，这也是ajax重要特性
		    data: { "parent_id": code }, //传参的的参数	    
		    type: "POST",   //请求方式类型
		   
		    success: function(data) { //此处data为返回值	    		    	
		        //请求成功时处理操作	         
		    	 var str = "<option value=''>请选择市</option>";
	             for(var i=0;i<data.length;i++)
	             {                                       	
	                 str += "<option value='"+data[i].id+"' >"+data[i].area_name+"</option>";    
	             }
	             $("#city_id").html(str);
	             $("#district_id").html("<option value=''>请选择区</option>");
		    },
		    
		    error: function() {
		        //请求出错处理操作
		    	layer.msg("请求错误");
		    }
		});
		}
		//市
		function cityChange(code){
			$.ajax({
			    url: "${ctx}/sold/districtList.shtml",         //请求的url地址
			    dataType: "json",   //返回格式类型为json
			    async: false,        //请求是否异步，默认为true:异步，这也是ajax重要特性
			    data: { "parent_id": code }, //传参的的参数	    
			    type: "POST",   //请求方式类型
			   
			    success: function(data) { //此处data为返回值	    			    			    	
			        //请求成功时处理操作
			         $("select[name='district_id']").empty();
			    	 var str = "<option value=''>请选择区</option>";
		             for(var i=0;i<data.length;i++)
		             {                            	            	 
		                 str += "<option value='"+data[i].id+"'>"+data[i].area_name+"</option>";    
		             }
		             $("#district_id").html(str); 
			    },
			    
			    error: function() {
			        //请求出错处理操作
			    	layer.msg("请求错误");
			    }
			});
		}
		//选择区级触发验证该地区有无代理人
		function districtChange(code){
			var province_id = $('#province_id').val();
			var city_id = $('#city_id').val();
			$.ajax({
			    url: "${ctx}/notifySend/fetchAccountInfoByAgent.shtml",         //请求的url地址
			    dataType: "json",   //返回格式类型为json
			    async: false,        //请求是否异步，默认为true:异步，这也是ajax重要特性
			    data: { "province_id": province_id,"city_id": city_id,"district_id": code }, //传参的的参数	    
			    type: "POST",   //请求方式类型
			   
			    success: function(data) { //此处data为返回值
			        //请求成功时处理操作
			         if(data!=1){
			        	 $("#district_result").hide();
			        	 $("#button_id").attr('disabled',false);
			         }else{			    
			        	 $("#district_result").show();
			        	 $("#button_id").attr('disabled',true);
			        	 
			         }
			    },
			    
			    error: function() {
			        //请求出错处理操作
			    	layer.msg("请求错误");
			    }
			});
		}
		
	
		function userTypeChange(code){
			$.ajax({
			    url: "${ctx}/notifySend/fetchAccountInfoList.shtml",         //请求的url地址
			    dataType: "json",   //返回格式类型为json
			    async: false,        //请求是否异步，默认为true:异步，这也是ajax重要特性
			    data: { "role_type": code }, //传参的的参数	    
			    type: "POST",   //请求方式类型
			   
			    success: function(data) { //此处data为返回值	    			    			    	
			        //请求成功时处理操作
			         $("select[name='receive_userId']").empty();
			    	 var str = "<option value=''>请选择接收人</option>";
		             for(var i=0;i<data.length;i++)
		             {                            	            	 
		                 str += "<option value='"+data[i].id+"'>"+data[i].user_nickname+"</option>";    
		             }
		             $("#receive_userId").html(str); 
			    },
			    
			    error: function() {
			        //请求出错处理操作
			    	layer.msg("请求错误");
			    }
			});
		}
</script>	
