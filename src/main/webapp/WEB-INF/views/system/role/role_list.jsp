<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body" style="padding-bottom:0px;">
	<!-- 查询条件begin -->
	<div class="panel panel-default">
		<div class="panel-heading"><spring:message code='font.framework.search.conditions'/></div>
		<div class="panel-body">
			<form id="formSearch" class="form-horizontal">
			    <input name="com_role_id" id="com_role_id" type="hidden" value="${role_id}"/>
				<div class="form-group">
				
					<label class="control-label col-sm-2" for="search_role_name">用户组名称</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="search_role_name" name="search_role_name">
					</div>
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

<script>

var ly;
var pageTable;
$(function() {
// 	$ScinanDateTimePicker.date({id:'search_date',language:'zh-CN'});
	
	pageTable = $ScinanPageTable_({
		$id:$('#mytab'),
        url: "${ctx}/role/fetchByPage.shtml",//数据源
        searchForm:$('#formSearch'),//查询条件form表单
        locale : "<spring:message code='bootstraptable.local'/>",//国际化
        columns: [
            {
                title: "",
                field: "select",
                checkbox: true,
                width: 20,//宽度
                align: "center",
                valign: "middle"
            },
            {
                field: "id",
                title: "ID",
                align: "center",
                visible:false
            },
            {
                field: "name",
                title: "用户组名称",
                width: 250,//宽度
                align: "center"
            },
            {
                field: "role_type",
                title: "角色类型",
                width: 250,//宽度
                sortable: true,
                align: "center",
                formatter:function(value, row, index){
                	if(value == null && value == 'null') return '-';
                	else if(value==1){
                		return "厂商角色";
                	}else if(value==2){
                		return "代理商角色";
                	}else{
                		return "经销商角色";
                	}
                }
            },
            {
                field: "create_time",
                title: "<spring:message code='create_time'/>",
                width: 250,//宽度
                sortable: true,
                align: "center",
                formatter:function(value, row, index){
                	if(value == null || value == 'null') return '-';
               		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                }
            },
            {
                field: "update_time",
                title: "<spring:message code='update_time'/>",
                width: 250,//宽度
                sortable: true,
                align: "center",
                formatter:function(value, row, index){
                	if(value == null || value == 'null') return '-';
               		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                }
            }
		]
	});
	
	
	//权限分配
	$('#btn_1004002004').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0) {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		
		ly = parent.layer.open({
			title : "<spring:message code='rolelist.assign'/>",
			type : 2,
			fix: true, //固定
		    maxmin: true,
		    offset: 'auto',
			area : [ "80%", "80%" ],
			content : '${ctx}/resources/permissions.shtml?id=' + data[0].id+'&role_type='+data[0].role_type
		});
		
	});
	
	
	//点击删除角色按钮
	$('#btn_1004002003').click(function(){
		//获取勾选id数组
		var ids_arr = pageTable.getSelectionIds();
		if (ids_arr.length == 0) {
			layer.msg("<spring:message code='select_last_one'/>");
			return;
		}
		
		var data = pageTable.getSelections();
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		
		if(data[0].id==1){
			layer.msg("不允许删除系统用户组");
			return;
		}
		
		//确认框
		$ScinanSwal.confirm({
			title: "<spring:message code='confirm_delete'/>",
	        text: "<spring:message code='delete_prompt'/>",
	        confirmButtonText: "<spring:message code='font.framework.delete'/>",
	        cancelButtonText: "取消"
		},function(){
			var obj = CommnUtil.ajax("${ctx}/role/delete.shtml",{ids:ids_arr + ""},"json");
    		if(obj.status == 200){
           	 	$ScinanSwal.alert("<spring:message code='delete_success'/>", "<spring:message code='delete_success_prompt'/>", "success");
           	 	pageTable.refreshPage();  
           	}else if(obj.status == 600){
           		$ScinanSwal.alert("删除失败", "不允许删除，该用户组下还存在绑定的用户", "error");
           	 	pageTable.refreshPage();  
           	}
    		else{
          	 	layer.msg("<spring:message code='delete_fail'/>");
           	}	
		});
	});
	
	//查询按钮
	$('#btn_query').click(function(){
		pageTable.refreshPage();  
	});
});





//角色添加
$('#btn_1004002001').click(function(){
	pageii = layer.open({
		title : "新增用户组",
		type : 2,
		area : [ "60%", "60%" ],
		fix: true, //不固定
	    maxmin: true,
		content : '${ctx}/role/addRoleInit.shtml'
	});
});


//角色修改
$('#btn_1004002002').click(function(){
	var data = pageTable.getSelections();
	if (data.length == 0 || data == "") {
		layer.msg("<spring:message code='select_one'/>");
		return;
	}
	if (data.length > 1) {
		layer.msg("<spring:message code='select_only_one'/>");
		return;
	}
	
	if(data[0].id==1){
		layer.msg("不允许修改系统用户组");
		return;
	}
	
	pageii = layer.open({
		title : "修改用户组",
		type : 2,
		area : [ "60%", "60%" ],
		fix: true, //不固定
	    maxmin: true,
		content : '${ctx}/role/modifyRoleInit.shtml?id='+data[0].id
	});
});



</script>