<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body" style="padding-bottom:0px;">
	
	<!-- 查询条件begin -->
	<div class="panel panel-default">
		<div class="panel-heading"><spring:message code='font.framework.search.conditions'/></div>
		<div class="panel-body">
			<form id="formSearch" class="form-horizontal">
				<div class="form-group">
				
					<label class="control-label col-sm-1" for="search_account">产品名称</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="device_name" name="device_name">
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
var pageTable;
$ScinanSelect2({id:'search_organization',placeholder: "请选择归属单位"});
$(function() {
    var user_id = '';
	pageTable = $ScinanPageTable_({
		$id:$('#mytab'),
    	url: "${ctx}/finance/fetchRatioByPage.shtml",//请求url
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
               field: "device_name",
               title: "产品名称",
               width: 200,//宽度
               align: "center"
           },
           {
               field: "product_price",
               title: "产品单价",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "agent_ratio",
               title: "代理商分成比例(%)",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "dealer_ratio",
               title: "经销商分成比例(%)",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "primage_ratio",
               title: "运费补贴比例(%)",
               width: 100,//宽度
               align: "center"
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
           },
           {
               field: "update_time",
               title: "<spring:message code='update_time'/>",
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
	
	
	//删除比例信息
	$('#btn_1006001003').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0) {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}

		//确认框
		$ScinanSwal.confirm({
			title: "<spring:message code='confirm_delete'/>",
	        text: "<spring:message code='delete_prompt'/>",
	        confirmButtonText: "<spring:message code='font.framework.delete'/>",
	        cancelButtonText: "取消"
		},function(){
			var obj = CommnUtil.ajax("${ctx}/finance/delRatio.shtml",{id:data[0].id},"json");
    		if(obj.status == 200){
           	 	$ScinanSwal.alert("<spring:message code='delete_success'/>", "<spring:message code='delete_success_prompt'/>", "success");
           	 	pageTable.refreshPage(); 
           	}else if(obj.status==-1){
           		$ScinanSwal.alert("删除失败", "该用户还存在对应的分成比例!", "error");
           	 	pageTable.refreshPage();
           	}
    		else{
          	 	layer.msg("<spring:message code='delete_fail'/>");
           	}	
		});
	});
	
	
	//修改
	$('#btn_1006001002').click(function(){
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
			title : "修改用户分成比例信息",
			type : 2,
			area : [ "70%", "95%" ],
			fix: true, //不固定
		    maxmin: true,
			content : '${ctx}/finance/modifyRatioInit.shtml?id='+data[0].id
		});
		
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



//添加用户比例
$('#btn_1006001001').click(function(){
	pageii = layer.open({
		title : "新增用户比例信息",
		type : 2,
		area : [ "70%", "95%" ],
		fix: true, //不固定
	    maxmin: true,
		content : '${ctx}/finance/addRatioInit.shtml'
	});
});


//设置单价
function setValue(userId){
	pageii = layer.open({
		title : "单价设置",
		type : 2,
		area : [ "70%", "65%" ],
		fix: true, //不固定
	    maxmin: true,
		content : '${ctx}/user/setUnitPrice.shtml?userId='+userId
	});
}

</script>