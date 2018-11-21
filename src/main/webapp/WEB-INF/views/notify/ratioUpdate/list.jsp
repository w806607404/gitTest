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
    	url: "${ctx}/notify/fetchRatioUpdateByPage.shtml",//请求url
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
               title: "原产品单价",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "product_price_new",
               title: "现产品单价",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "agent_ratio",
               title: "原代理商分成比例(%)",
               width: 50,//宽度
               align: "center"
           },
           {
               field: "agent_ratio_new",
               title: "现代理商分成比例(%)",
               width: 50,//宽度
               align: "center"
           },
           {
               field: "dealer_ratio",
               title: "原经销商分成比例(%)",
               width: 50,//宽度
               align: "center"
           },
           {
               field: "dealer_ratio_new",
               title: "现经销商分成比例(%)",
               width: 50,//宽度
               align: "center"
           },
           {
               field: "primage_ratio",
               title: "原运费补贴比例(%)",
               width: 50,//宽度
               align: "center"
           },
           {
               field: "primage_ratio_new",
               title: "现运费补贴比例(%)",
               width: 50,//宽度
               align: "center"
           },
           {
               field: "create_time",
               title: "<spring:message code='update_time'/>",
               sortable: true,
               width: 100,//宽度
               align: "center",
               formatter:function(value, row, index){
               	if(value == null || value == 'null') return '-';
              		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
               }
           },
		]
	});
	
});
//查询按钮
    $('#btn_query').click(function(){
    	pageTable.refreshPage();
	});
	
	
	//删除比例信息
	$('#btn_1007001001').click(function(){
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
			var obj = CommnUtil.ajax("${ctx}/notify/delRatioUpdate.shtml",{id:data[0].id},"json");
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
	
</script>