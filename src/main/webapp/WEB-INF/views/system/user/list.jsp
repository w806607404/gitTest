<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body" style="padding-bottom:0px;">
	
	<!-- 查询条件begin -->
	<div class="panel panel-default">
		<div class="panel-heading"><spring:message code='font.framework.search.conditions'/></div>
		<div class="panel-body">
			<form id="formSearch" class="form-horizontal">
			    <input type="hidden" name="com_role_id" id="com_role_id" value="${role_id}" />
			    <input type="hidden" name="pk_user_id" id="pk_user_id"  />
				<div class="form-group">
				
					<label class="control-label col-sm-1" for="search_nickname"><spring:message code='userlist.nickname'/></label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="search_nickname" name="search_nickname">
					</div>
				
					<label class="control-label col-sm-1" for="search_account"><spring:message code='userlist.account.name'/></label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="search_username" name="search_username">
					</div>	
				</div>
					<!-- 区域查询 -->
				<div class="form-group">
					<label class="control-label col-sm-1" for="search_account">省份</label>
					<div class="col-sm-2">
						<select id="province_id" name="province_id" tabindex="-1" class="form-control" onchange="searchprovince(this.value);">
						    <option value=''>-请选择-</option>
							<c:forEach items="${provinces}" var="key">
								<option value='${key.id}'>${key.area_name}</option>
							</c:forEach>
						</select>
					</div>	
					<label class="control-label col-sm-1" for="search_account">城市</label>
					<div class="col-sm-2">
						<select id="city_id" name="city_id" tabindex="-1" class="form-control" onchange="searchcity(this.value);">
								<option value=''></option>
						</select>
					</div>	
					<label class="control-label col-sm-1" for="search_account">区县</label>
					<div class="col-sm-2">
						<select id="district_id" name="district_id" tabindex="-1" class="form-control">
								<option value=''></option>
						</select>
					</div>	
					<!-- 区域查询 -->
					<div class="col-sm-1 btn_query">
						<button type="button" id="btn_query" class="btn btn-primary"><spring:message code='font.framework.search'/></button>
					</div>
				</div>
			</form>
		</div>
	</div>  
 	<!-- 查询条件end -->
 
 	<!-- 增删查改 begin -->
	<div id="toolbar" class="btn-group">
		<c:forEach items="${systemResourcesBeans}" var="systemResourcesBean" varStatus="idxStatus" >
			<button id="btn_${systemResourcesBean.res_id}" type="button" class="btn btn-default" data-toggle="modal">
		 		<span class="${systemResourcesBean.description}" aria-hidden="true"></span> <spring:message code='${systemResourcesBean.res_name}'/>
			</button>
		</c:forEach>
	</div>
	<!-- 增删查改 end -->
	
	<table id="mytab"></table>
	
</div>
</div>
<!-- 重置账号 begin -->
	<div class="modal fade" id="resetAccountModal" tabindex="-1" role="dialog" aria-hidden="true">
	
	   <div class="modal-dialog">
	   
	        <div class="modal-content">
	        
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                    &times;
	                </button>
	                <h4 class="modal-title" id="myModalLabel"><spring:message code='userlist.reset.password'/></h4>
	            </div>
	            
	            <div class="modal-body">
                         <p>您确定要重置用户昵称为：<font color="red"></font><strong id="strong_account_name"></strong></font> 的密码为<font color="red"><STRONG>123456</STRONG></font>吗？</p>
	            </div>
	            
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='font.framework.cancel'/></button>
	                <button type="button" class="btn btn-primary" id="resetAccountButton"><spring:message code='font.framework.confirm'/></button>
	            </div>
	            
	        </div>
	        
	    </div>
	</div>
	<!-- 重置账号 end -->
	
<script>
var pageTable;
$ScinanSelect2({id:'search_organization',placeholder: "请选择归属单位"});
$(function() {
    var user_id = '';
	pageTable = $ScinanPageTable_({
		$id:$('#mytab'),
    	url: "${ctx}/user/fetchByPage.shtml",//请求url
    	searchForm:$('#formSearch'),//查询条件表单
    	locale : "<spring:message code='bootstraptable.local'/>",
    	columns: [
    	 {
               title: "",
               field: "select",
               checkbox: true,
               width: 10,//宽度
               align: "center",
               valign: "middle"
           },
           {
               field: "id",
               title: "ID",
               visible:false,
               width: 20,//宽度
               align: "center"
           },
           {
               field: "user_nickname",
               title: "用户昵称",
               width: 200,//宽度
               align: "center"
           },
           {
               field: "user_name",
               title: "用户账号",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "role_name",
               title: "用户类型",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "agent_name",
               title: "联系人",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "agent_phone",
               title: "联系电话",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "status",
               title: "状态",
               width: 100,//宽度
               align: "center",
               formatter:function(value, row, index){
              		return value == 0 ? "<font color=\"blue\"><a href=\"#\" onclick=\"setStatus("+row.id+");\">关闭</a></font>" : "<font color=\"blue\"><a href=\"#\" onclick=\"setStatus("+row.id+");\">开启</a></font>";
               }
           },
           {
           	   field:"province_name",
           	   title:"省份",
           	   width:50,
           	   align:"center",
           },
           {
           	   field:"city_name",
           	   title:"城市",
           	   width:50,
           	   align:"center",
           },
           {
           	   field:"district_name",
           	   title:"区县",
           	   width:50,
           	   align:"center",
           },
           {
               field: "create_time",
               title: "<spring:message code='create_time'/>",
               sortable: true,
               width: 100,//宽度
               align: "center",
               formatter:function(value, row, index){
               	if(value == null || value == 'null') return '-';
              		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
               }
           } 
		]
	});
	
	
	//查询按钮
    $('#btn_query').click(function(){
    	pageTable.refreshPage();
	});
	
	//重置app端账号
	$('#btn_1004001004').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0 || data == "") {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		
		if(data[0].id != 2){
			layer.msg("只允许重置管理员账号");
			return;
		}
		
		if(data[0].role_type != 1){
			layer.msg("只允许修改系统用户信息");
			return;
		}
		
		pageii = layer.open({
			title : "重置app端账号",
			type : 2,
			area : [ "70%", "95%" ],
			fix: true, //不固定
		    maxmin: true,
			content : '${ctx}/user/setStatus.shtml?userId='+data[0].id+'&type=3'
		});
		
	});
	
	
	//删除账号
	$('#btn_1004001003').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0) {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		
		if(data[0].id<=2){
			layer.msg("不允许删除系统用户");
			return;
		}

		//确认框
		$ScinanSwal.confirm({
			title: "<spring:message code='confirm_delete'/>",
	        text: "<spring:message code='delete_prompt'/>",
	        confirmButtonText: "<spring:message code='font.framework.delete'/>",
	        cancelButtonText: "取消"
		},function(){
			var obj = CommnUtil.ajax("${ctx}/user/deleteAccount.shtml",{id:data[0].id},"json");
    		if(obj.status == 200){
           	 	$ScinanSwal.alert("<spring:message code='delete_success'/>", "<spring:message code='delete_success_prompt'/>", "success");
           	 	pageTable.refreshPage(); 
           	}else if(obj.status==-1){
           		$ScinanSwal.alert("删除失败", "该用户还存在对应的下级经销商!", "error");
           	 	pageTable.refreshPage();
           	}else if (obj.status == 101) {
				layer.msg('该账号或者该账号的下级仍有退货账单未完成！');
				return;
			} else if (obj.status == 102) {
				layer.msg('该账号或者该账号的下级仍有通知发货账单未完成！');
				return;
			} else if (obj.status == 103) {
				layer.msg('该账号或者该账号的下级仍有购货账单未完成！');
				return;
			}else if (obj.status == 104) {
				layer.msg('该账号或者该账号的下级仍有结算账单未完成！');
				return;
			}else{
          	 	layer.msg("<spring:message code='delete_fail'/>");
           	}	
		});
	});
	
	
	//修改
	$('#btn_1004001002').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0 || data == "") {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		
		
		if(data[0].role_type>1){
			layer.msg("只允许修改系统用户信息");
			return;
		}
	
		pageii = layer.open({
			title : "修改用户信息",
			type : 2,
			area : [ "70%", "95%" ],
			fix: true, //不固定
		    maxmin: true,
			content : '${ctx}/user/modifyUserInit.shtml?id='+data[0].id
		});
		
	});
});

//更改代理/经销区域
	$('#btn_1004001006').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0 || data == "") {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		
		
		if(data[0].role_type == 1){
			layer.msg("只允许变更非系统用户");
			return;
		}
		
		pageii = layer.open({
			title : "更改代理/经销区域",
			type : 2,
			area : [ "70%", "95%" ],
			fix: true, //不固定
		    maxmin: true,
			content : '${ctx}/user/modifyArea.shtml?id='+data[0].id
		});
		
	});


function changeSelect(obj){
	var type = $(obj).val();
	if(type == 1){//厂商
		$('#div_company_id').show();
	}else if(type == 0){//司南
		$('#div_company_id').hide();
	}
}

function lockAccount(val,user_id){
	
	var obj = CommnUtil.ajax("${ctx}/user/lockAccount.shtml",{lockValue:val,userId:user_id},"json");
	if(obj.status == 200){
		pageTable.refreshPage();
		if(val == 1){
			layer.msg("<spring:message code='userlist.lock.success'/>");
		}else{
			layer.msg("<spring:message code='userlist.unlock.success'/>");
		}
	}else if(obj.status == 201){//暂无
		layer.alert_("<spring:message code='message'/>","<spring:message code='userlist.undo'/>","<spring:message code='font.framework.confirm'/>");
	}else{
		if(val == 1){
			layer.alert_("<spring:message code='message'/>","<spring:message code='userlist.lock.fail'/>","<spring:message code='font.framework.confirm'/>");
		}else{
			layer.alert_("<spring:message code='message'/>","<spring:message code='userlist.unlock.fail'/>","<spring:message code='font.framework.confirm'/>");
		}
	}
}



//添加用户
$('#btn_1004001001').click(function(){
	pageii = layer.open({
		title : "新增用户信息",
		type : 2,
		area : [ "70%", "95%" ],
		fix: true, //不固定
	    maxmin: true,
		content : '${ctx}/user/addUserInit.shtml'
	});
});

//查看个人信息详情
$('#btn_1004001005').click(function(){
	var data = pageTable.getSelections();
	if (data.length == 0 || data == "") {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
	pageii = layer.open({
		title : "用户信息详情",
		type : 2,
		area : [ "70%", "95%" ],
		fix: true, //不固定
	    maxmin: true,
		content : '${ctx}/user/fetchById.shtml?id='+data[0].id
	});
});


//设置状态
function setStatus(userId){
		if(userId<=2){
			layer.msg("不允许操作管理员信息");
			return;
		}
	var type = 1;
	pageii = layer.open({
		title : "状态设置",
		type : 2,
		area : [ "70%", "65%" ],
		fix: true, //不固定
	    maxmin: true,
		content : '${ctx}/user/setStatus.shtml?userId='+userId+'&type='+type
	});
}

//区域选择
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