<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body" style="padding-bottom:0px;">
			<input type="hidden" id="cur_id" name="cur_id" value="${ai.id}">
	<!-- 查询条件begin -->
	<div class="panel panel-default">
		<div class="panel-heading"><spring:message code='font.framework.search.conditions'/></div>
		<div class="panel-body">
			<form id="formSearchPurchase" class="form-horizontal">
				<div class="form-group">
				
					<label class="control-label col-sm-1" for="search_account">购货账号</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="user_name" name="user_name">
					</div>
					
					<label class="control-label col-sm-1" for="search_account">物流状态</label>
					<div class="col-sm-2">
						<select id="status" name="status" tabindex="-1" class="form-control" ">
						    <option value=''>-请选择-</option>							
							<option value='0'>申请中</option>
							<option value='1'>已发货</option>
							<option value='2'>已完成</option>
						</select>
					</div>	
						
					<div class="col-sm-1 btn_query">
						<button type="button" id="btn_query_purchase" class="btn btn-primary"><spring:message code='font.framework.search'/></button>
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
	
	<table id="purchaseList"></table>
	
</div>
</div>

	
<script>
var pageTable;
$ScinanSelect2({id:'search_organization',placeholder: "请选择归属单位"});
$(function() {
    var user_id = '';
	pageTable = $ScinanPageTable_({
		$id:$('#purchaseList'),
    	url: "${ctx}/purchase/fetchPurchaseByPage.shtml",//请求url
    	searchForm:$('#formSearchPurchase'),//查询条件表单
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
               title: "购货人名称",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "user_name",
               title: "购货人账号",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "pay_type",
               title: "支付方式",
               width: 100,//宽度
               align: "center",
               formatter:function(value, row, index){
                   if(value == null || value == 'null') return '-';
                   if(value == 0 || value == '0') 
                   	return '线下转账';
                   if(value == 1 || value == '1') 
                   	return '分红余额';
                   if(value == 2 || value == '2') 
                    return '补贴余额';
                    if(value == 3 || value == '3') 
                    return '支付宝';
                    if(value == 4 || value == '4') 
                    return '微信';
                   }
           
               
           },
           {
               field: "device_name",
               title: "进货产品名称",
               width: 100,//宽度
               align: "center"              
           },
           {
               field: "count",
               title: "数量",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "amount",
               title: "订单总金额",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "invoice",
               title: "是否开具发票",              
               width: 100,//宽度
               align: "center",
               formatter:function(value, row, index){
                   if(value == null || value == 'null') return '-';
                   if(value == 0 || value == '0') 
                   	return '否';
                   if(value == 1 || value == '1') 
                   	return '是';
                   }
           },
           {
               field: "shipping",
               title: "物流单号",
               width: 100,//宽度
               align: "center"
           },         
           {
               field: "status",
               title: "状态",               
               width: 100,//宽度
               align: "center",
               formatter:function(value, row, index){
                   if(value == null || value == 'null') return '-';
                   if(value == 0 || value == '0') 
                   	return '申请中';
                   if(value == 1 || value == '1') 
                   	return '已发货';
                   if(value == 2 || value == '2') 
                    return '已完成';
                   }
           },
           {
               field: "create_time",
               title: "购买时间",
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
               title: "发货时间",               
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
    $('#btn_query_purchase').click(function(){
    	pageTable.refreshPage();
	});
	
	
	//修改物流状态信息
	$('#').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0) {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		
		if (data[0].status == 2) {
			layer.msg("该记录已完成不允许在操作");
			return;
		}

		//确认框
		$ScinanSwal.confirm({
			title: "<spring:message code='confirm_delete'/>",
	        text: "<spring:message code='delete_prompt'/>",
	        confirmButtonText: "<spring:message code='font.framework.delete'/>",
	        cancelButtonText: "取消"
		},function(){
			var obj = CommnUtil.ajax("${ctx}/purchase/updateStatus.shtml",{id:data[0].id},"json");
    		if(obj.status == 200){
           	 	$ScinanSwal.alert("<spring:message code='success_operat'/>", "success");
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
	
	
	//发货
	$('#btn_1005002001').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0 || data == "") {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		
		if (data[0].status != 0) {
			layer.msg("该记录不是待发货不允许在操作");
			return;
		}
		var user_id = $('#cur_id').val();
		if (data[0].user_id == user_id) {
			layer.msg("只能由上级发货");
			return;
		}
		pageii = layer.open({
			title : "设置物流单号信息",
			type : 2,
			area : [ "70%", "95%" ],
			fix: true, //不固定
		    maxmin: true,
			content : '${ctx}/purchase/modifyShipping.shtml?id='+data[0].id
		});
		
	});
	
	//完成
	$('#btn_1005002002').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0 || data == "") {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		
		if (data[0].status != 1) {
			layer.msg("该记录不是已发货不允许在操作");	
			return;
		}
		
		var user_id = $('#cur_id').val();
		if (data[0].user_id != user_id) {
			layer.msg("只能购货人完成");
			return;
		}
		
		var obj = CommnUtil.ajax("${ctx}/purchase/updateStatus2.shtml",{id:data[0].id},"json");
		if(obj.status == 200){
			layer.msg("<spring:message code='success_operat'/>");
       	 	pageTable.refreshPage(); 
       	}else if(obj.status == 500){
       		layer.msg("<spring:message code='operat_fail'/>", "error");
       	 	pageTable.refreshPage();
       	}else if(obj.status == -101){
       		layer.msg("抱歉！没有权限确认该条记录状态");      	 	
       	}
		else{
      	 	layer.msg("<spring:message code='operat_fail'/>");
       	}	
		
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
$('#btn_1005001001').click(function(){
	pageii = layer.open({
		title : "新增销售记录信息",
		type : 2,
		area : [ "70%", "95%" ],
		fix: true, //不固定
	    maxmin: true,
		content : '${ctx}/sold/addSoldInit.shtml'
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