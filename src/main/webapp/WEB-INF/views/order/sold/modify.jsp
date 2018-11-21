<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/sold/updateSold.shtml">
		<input type="hidden" id="id" name="id" value="${sold.id}"/>
		<input type="hidden" id="user_id" name="user_id" value="${sold.user_id}"/>
		<input type="hidden" id="company_id" name="company_id" value="${sold.company_id}"/>		
		
		<div class="panel-body">
			<div class="form-group">
					<label class="col-sm-3 control-label">产品ID</label>
				<div class="col-sm-8">
					<input type="text"  readOnly="true"  class="form-control" value="${sold.device_id}"  name="device_id" id="device_id" maxlength="32">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">售出人名称</label>
				<div class="col-sm-8">
					<input type="text" readOnly="true" class="form-control" value="${sold.user_nickname}"  name="user_nickname" id="user_nickname" maxlength="32">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">售出人账号</label>
				<div class="col-sm-8">
					<input type="text" readOnly="true" class="form-control" value="${sold.user_name}"  name="user_name" id="user_name" maxlength="16">
				</div>
			</div>		
			<div class="form-group">
					<label class="col-sm-3 control-label">收货人</label>
				<div class="col-sm-8">
					<input type="text" placeholder="请填写收货人（必填）" class="form-control" value="${sold.receive_name}"  name="receive_name" id="receive_name" maxlength="16">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">收货人联系方式</label>
				<div class="col-sm-8">
					<input type="text" placeholder="请填写收货人联系方式（必填）" class="form-control" value="${sold.receive_contact}"  name="receive_contact" id="receive_contact" maxlength="16">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">省</label>
				<div class="col-sm-8">
					<select id="province_id" onchange="provinceChange(this.value)" name="province_id"  tabindex="-1" class="form-control" >
					    <option value=''>请选择省</option>
					    <c:forEach items="${provinceList}" var="key">
							<option value='${key.id}' <c:if test="${sold.province_id == key.id }">selected</c:if> >${key.area_name}</option>
						</c:forEach>																		
					</select>					
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">市</label>
				<div class="col-sm-8">
					<select id="city_id" onchange="cityChange(this.value)" name="city_id"  tabindex="-1" class="form-control" >					    						
						<c:if test="${sold.city_id == null }"><option value=''>请选择市</option></c:if>	
						<c:if test="${sold.city_id != null }"><option value='${sold.city_id}'>${sold.city_name}</option></c:if>					
					</select>					
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">区</label>
				<div class="col-sm-8">
					<select id="district_id"  name="district_id"  tabindex="-1" class="form-control" >
					    <c:if test="${sold.district_id == null }"><option value=''>请选择区</option></c:if>	
						<c:if test="${sold.district_id != null }"><option value='${sold.district_id}'>${sold.district_name}</option></c:if>		
					</select>					
				</div>
			</div>
		<div class="text-right col-sm-12">
		   <button type="button"  class="btn btn-primary btn-s-xs" onclick="doSubmit();"><spring:message code='font.framework.submit'/></button>
		</div>
	</form>
</div>
</div>
<script type="text/javascript">
function provinceChange(code){
	$.ajax({
	    url: "cityList.shtml",         //请求的url地址
	    dataType: "json",   //返回格式类型为json
	    async: false,        //请求是否异步，默认为true:异步，这也是ajax重要特性
	    data: { "parent_id": code }, //传参的的参数	    
	    type: "POST",   //请求方式类型
	   
	    success: function(data) { //此处data为返回值	    		    	
	        //请求成功时处理操作	         
	    	 var str = "<option value=''>请选择市</option>";
             for(var i=0;i<data.length;i++)
             {                                       	
                 str += "<option value='"+data[i].id+"' <c:if test="${sold.city_id == data[i].id }">selected</c:if> >"+data[i].area_name+"</option>";    
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
	
	function cityChange(code){
		$.ajax({
		    url: "districtList.shtml",         //请求的url地址
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
	
	//点击事件区县
	$('#district_id').click(function() {
		var code = $('#city_id').val();
		if(code == ""){
			layer.msg("请先选择市");
			return false;
		}
		$.ajax({
		    url: "districtList.shtml",         //请求的url地址
		    dataType: "json",   //返回格式类型为json
		    async: false,        //请求是否异步，默认为true:异步，这也是ajax重要特性
		    data: { "parent_id": code }, //传参的的参数	    
		    type: "POST",   //请求方式类型
		   
		    success: function(data) { //此处data为返回值	    			    			    	
		        //请求成功时处理操作		   
		    	 var str = "";
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
	});

function doSubmit(){
   
	
	var receive_name = $('#receive_name').val();
		var receive_contact = $('#receive_contact').val();
		var province_id = $('#province_id').val();
		var city_id = $('#city_id').val();
		var district_id = $('#district_id').val();
		var user_type = $('#user_type').val();

		if (receive_name == "") {
			layer.msg("收货人不能为空");
			return false;
		}

		if (user_type == "") {
			layer.msg("售出人类型不能为空");
			return false;
		}

		if (receive_contact == "") {
			layer.msg("收货人联系方式不能为空");
			return false;
		}
		if (province_id == "") {
			layer.msg("省级不能为空");
			return false;
		}
		if (city_id == "") {
			layer.msg("市级不能为空");
			return false;
		}
		if (district_id == "") {
			layer.msg("区级不能为空");
			return false;
		}

		var reg = /^[0-9]*$/;
		if (!reg.test(receive_contact)) {
			layer.msg("收货人联系方式不合法,只允许是数字");
			return false;
		}

		layer.confirm('请检查数据是否正确?', function(index) {
			var data = CommnUtil.ajax($("#addForm").attr("action"), $(
					"#addForm").serialize(), "json");
			if (data.status == 200) {
				parent.layer.msg("<spring:message code='modify_success'/>");
				parent.pageTable.refreshPage();
				parent.layer.close(parent.pageii);
			} else if (data.status == -101) {
				layer.msg("修改失败");
				return false;
			} else {
				layer.msg("<spring:message code='modify_fail'/>");
			}
		});
	}
</script>	
