<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/user/doModifyArea.shtml">
		<input type="hidden" id="user_id" name="user_id" value="${accountInfo.id}"/>
		<input type="hidden" id="province_name" name="province_name" value=""/>
		<input type="hidden" id="city_name" name="city_name" value=""/>
		<input type="hidden" id="district_name" name="district_name" value=""/>
		<div class="panel-body">
		<div class="form-group">
				<label class="col-sm-7 control-label" style="color: red;">提示：请谨慎操作，一旦执行变更数据便不可恢复!</label>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">用户昵称</label>
				<div class="col-sm-8">
					<input type="text" value="${accountInfo.user_nickname}"  class="form-control" readonly="readonly" >
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">帐号名称</label>
				<div class="col-sm-8">
					<input type="text" value="${accountInfo.user_name}" class="form-control" readonly="readonly" >
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label" >用户类型</label>
				<div class="col-sm-8">
					<input type="text" value="${accountInfo.role_name}"  class="form-control" readonly="readonly">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label" >当前所属区域</label>
				<div class="col-sm-8">
					<input type="text" value="${accountInfo.province_name}${accountInfo.city_name}${accountInfo.district_name}"  class="form-control" readonly="readonly">
				</div>
			</div>
		<!--伯央的度用户区域选择start -->
		<div class="form-group">
				<label class="col-sm-3 control-label">请选择要变更到的区域</label>
				<div class="col-sm-2">
					<select id="province_id" name="province_id" tabindex="-1" class="form-control" onchange="searchprovince(this.value);">
					    <option value=''>请选择</option>
						<c:forEach items="${provinces}" var="key">
							<option value='${key.id}'>${key.area_name}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="col-sm-2">
					<select id="city_id" name="city_id" tabindex="-1" class="form-control" onchange="searchcity(this.value);">
					    <option value=''>请选择</option>
					</select>
				</div>
				
				<div class="col-sm-2">
					<select id="district_id" name="district_id" tabindex="-1" class="form-control">
					    <option value=''>请选择</option>
					</select>
				</div>
			</div>
		<!--伯央的度用户区域选择end -->
		
		<div class="text-right col-sm-12">
		   <button type="button"  class="btn btn-primary btn-s-xs" onclick="doSubmit();"><spring:message code='font.framework.submit'/></button>
		</div>
	</form>
</div>
</div>
<script type="text/javascript">
$ScinanSelect2({id:'role_id',placeholder: "请选择所属用户组"});

function doSubmit(){
    var province_id = $('#province_id').val();
    var city_id = $('#city_id').val();
    var district_id = $('#district_id').val();
    var province_name = $('#province_id').find('option:selected').text();
    var city_name = $('#city_id').find('option:selected').text();
    var district_name = $('#district_id').find('option:selected').text();
    
    
    if(province_id==""){
    	layer.msg("请选择省份");
    	return false;
    }
    if(city_id==""){
    	layer.msg("请选择城市");
		return false;    	
    }
    if(district_id==""){
    	layer.msg("请选择区县");
    	return false;
    }
    
    //省市区赋值名称
    $('#province_name').val(province_name);
    $('#city_name').val(city_name);
    $('#district_name').val(district_name);
    
	
     layer.confirm('请检查数据是否正确?', function(index) {
	    var data = CommnUtil.ajax($("#addForm").attr("action"),$("#addForm").serialize(),"json");
		if(data.status == 200){
			parent.layer.msg("<spring:message code='add_success'/>");
			parent.pageTable.refreshPage();
			parent.layer.close(parent.pageii);
		}else if(data.status==-101){
			layer.msg("该用区域已有代理商存在");
			return false;
		}else if(data.status==101){
			layer.msg('更改账号或者被更改账号的下级仍有退货账单未完成！');
			return;
		}else if(data.status==102){
			layer.msg('更改账号或者被更改账号的下级仍有购货账单未完成！');
			return;
		}else if(data.status==103){
			layer.msg("更改账号或者被更改账号的下级仍有购货账单未完成！");
			return false;
		}else if (data.status == 104) {
			layer.msg('更改账号或者被更改账号仍有结算账单未完成！');
			return;
		} else{
			layer.msg("更改失败，请重试");
		}
     });
}

function getDis(){
	var roleType = $('#roleType').val(); 
	var role_id = $('#role_id').val();
	var data = {
			role_id:role_id
	    };
	var url ="${ctx}/user/getRoleType.shtml";
	var value = CommnUtil.ajax(url, data,"json");
	var role_type = value.role_type;
	
	if(parseInt(roleType)==parseInt(role_type)){
		$('#div11').hide();
	}else{
		$('#div11').show();
	}
}


function toGetMoney(){
	var current_ratio_str = $('#current_ratio_str').val();
	
	var current_ratio = $('#current_ratio').val();
	if(current_ratio==""){
		 layer.msg("请输入分成比例值");
	     return false;
	}
	else
	{
    	 var _ratio = Math.round(current_ratio);
    	 if(_ratio>100){
    		 layer.msg("分成比例值不能大于100");
    	     return false;
    	 }
    	 
    	 //var reg = /([0-9]+\.[0-9]{2})[0-9]*/;
    	 //var reg = /^\d+(\.\d{1,2})?$/;
    	 //var reg = new RegExp("^[0-9]*$");  
    	 var reg = /^\d+(\.\d{1,2})?$/;
    	 if(!reg.test(current_ratio)){
    		 layer.msg("分成比例值不合法,只允许是数字且最多保留2位小数");
    	     return false;
    	 }
    }
	
	$('#moneyStr').html(changeTwoDecimal(current_ratio_str*current_ratio));
}

function changeTwoDecimal(v) {
    if (isNaN(v)) {//参数为非数字
        return 0;
    }
    var fv = parseFloat(v);
    fv = Math.round(fv * 100) / 100 /100; //四舍五入，保留两位小数
    var fs = fv.toString();
    var fp = fs.indexOf('.');
    if (fp < 0) {
        fp = fs.length;
        fs += '.';
    }
    while (fs.length <= fp + 2) { //小数位小于两位，则补0
        fs += '0';
    }
    return fs;
}

function searchprovince(parent_id){
	var citys_html = "<option value =''>--<spring:message code='font.framework.please.select'/>--</option>";
	if(parent_id == ''){
		$("#city_id").empty();
		$("#city_id").append(citys_html);
		$("#district_id").empty();
		$("#district_id").append(citys_html);
	}else{
		var url ="${ctx}/user/fetchAreasByParentId.shtml";
		var areas = CommnUtil.ajax(url, {parent_id:parent_id},"json");
		
		for ( var i = 0; i < areas.length; i++) {
			citys_html +="<option value='" + areas[i].id + "'>"+ areas[i].area_name + "</option>";
		}
		$("#city_id").empty();
		$("#city_id").append(citys_html);
	}
}

function searchcity(parent_id){
	var citys_html = "<option value =''>--<spring:message code='font.framework.please.select'/>--</option>";
	if(parent_id == ''){
		$("#district_id").empty();
		$("#district_id").append(citys_html);
	}else{
		var url ="${ctx}/user/fetchAreasByParentId.shtml";
		var areas = CommnUtil.ajax(url, {parent_id:parent_id},"json");
		
		for ( var i = 0; i < areas.length; i++) {
			citys_html +="<option value='" + areas[i].id + "'>"+ areas[i].area_name + "</option>";
		}
		$("#district_id").empty();
		$("#district_id").append(citys_html);
	}
}


</script>	
