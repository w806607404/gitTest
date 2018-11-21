<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/user/add.shtml">
		<input type="hidden" id="id" name="id" value="${id}"/>
		<input type="hidden" id="roleType" name="roleType" value="${roleType}"/>
		<input type="hidden" id="province_name" name="province_name" value=""/>
		<input type="hidden" id="city_name" name="city_name" value=""/>
		<input type="hidden" id="district_name" name="district_name" value=""/>
		
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">用户昵称</label>
				<div class="col-sm-8">
					<input type="text" placeholder="用户昵称不能为空（必填）" class="form-control"  name="user_nickname" id="user_nickname" maxlength="32">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">帐号名称</label>
				<div class="col-sm-8">
					<input type="text" placeholder="账号名称不能为空，且11位，手机号码（必填）" class="form-control"  name="user_name" id="user_name" maxlength="16">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">所属用户组</label>
				<div class="col-sm-8">
					<select id="role_id" name="role_id" tabindex="-1" class="form-control" onchange="getDis();">
					    <option value=''>请选择</option>
						<c:forEach items="${vtlRoleList}" var="key">
							<option value='${key.id}'>${key.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
		<!--伯央的度用户区域选择start -->
		<div class="form-group">
				<label class="col-sm-3 control-label">省</label>
				<div class="col-sm-8">
					<select id="province_id" name="province_id" tabindex="-1" class="form-control" onchange="searchprovince(this.value);">
					    <option value=''>请选择</option>
						<c:forEach items="${provinces}" var="key">
							<option value='${key.id}'>${key.area_name}</option>
						</c:forEach>
					</select>
				</div>
				
				<label class="col-sm-3 control-label">市</label>
				<div class="col-sm-8">
					<select id="city_id" name="city_id" tabindex="-1" class="form-control" onchange="searchcity(this.value);">
					    <option value=''>请选择</option>
					</select>
				</div>
				
				<label class="col-sm-3 control-label">区</label>
				<div class="col-sm-8">
					<select id="district_id" name="district_id" tabindex="-1" class="form-control">
					    <option value=''>请选择</option>
					</select>
				</div>
			</div>
		<!--伯央的度用户区域选择end -->
		
			<div class="form-group">
					<label class="col-sm-3 control-label">联系人</label>
				<div class="col-sm-8">
					<input type="text" placeholder="联系人不能为空（必填）" class="form-control"  name="agent_name" id="agent_name" maxlength="32">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">联系电话</label>
				<div class="col-sm-8">
					<input type="text" placeholder="联系电话不能为空（必填）" class="form-control"  name="agent_phone" id="agent_phone" maxlength="16">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">状态</label>
				<div class="col-sm-8">
					<nobr><input name="status" type="radio" value="1" checked />开启  &nbsp;&nbsp;   <input name="status" type="radio" value="0" />关闭  </nobr>
				</div>
			</div>
			 <div class="form-group">
                  <label class="col-sm-3 control-label">备注</label>
                  <div class="col-sm-8">
                      <textarea id="note" name="note" wrap="off" rows="3" style="white-space:nowrap; overflow-x:scroll; overflow-y:hidden;" class="form-control" placeholder="长度256个字符内" maxlength="256"></textarea>
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
$ScinanSelect2({id:'role_id',placeholder: "请选择所属用户组"});

function doSubmit(){
    var user_nickname=$('#user_nickname').val();  
    var user_name = $('#user_name').val();
    var role_id = $('#role_id').val();
    var agent_name = $('#agent_name').val();
    var agent_phone = $('#agent_phone').val();
    var province_id = $('#province_id').val();
    var city_id = $('#city_id').val();
    var district_id = $('#district_id').val();
    var province_name = $('#province_id').find('option:selected').text();
    var city_name = $('#city_id').find('option:selected').text();
    var district_name = $('#district_id').find('option:selected').text();
    
    
    if(user_nickname==""){
    	layer.msg("用户昵称不能为空");
    	return false;
    }
       
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
    
    if(user_name == ""){
    	layer.msg("帐号名称不能为空");
		return;
	}else{
		var account_reg = /^[1][3,4,5,7,8][0-9]{9}$/; 
		if(!account_reg.test(user_name)){
			layer.msg("帐号名称格式不合法,长度为11位且由数字组成的手机号码");
			return;
		}
	}
    
    if(role_id==""){
    	layer.msg("所属用户组不能为空");
    	return false;
    }
    
    var roleType = $('#roleType').val(); 
	var data = {
			role_id:role_id
	    };
	var url ="${ctx}/user/getRoleType.shtml";
	var value = CommnUtil.ajax(url, data,"json");
	var role_type = value.role_type;
	
	
	if(agent_name==""){
    	layer.msg("联系人不能为空");
    	return false;
    }
    
	if(agent_phone==""){
    	layer.msg("联系电话不能为空");
    	return false;
    }else{
	   var account_reg = /^[1][3,4,5,7,8][0-9]{9}$/; 
		if(!account_reg.test(agent_phone)){
			layer.msg("帐号名称格式不合法,长度为11位且由数字组成的手机号码");
			return;
		}
    }
	
     layer.confirm('请检查数据是否正确?', function(index) {
	    var data = CommnUtil.ajax($("#addForm").attr("action"),$("#addForm").serialize(),"json");
		if(data.status == 200){
			parent.layer.msg("<spring:message code='add_success'/>");
			parent.pageTable.refreshPage();
			parent.layer.close(parent.pageii);
		}
		else if(data.status==-101){
			layer.msg("该用户账户已存在");
			return false;
		}else if(data.status==-102){
			layer.msg("该用区域已有代理商存在");
			return false;
		}else{
			layer.msg("<spring:message code='add_fail'/>");
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
