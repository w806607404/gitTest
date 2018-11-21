<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body" style="padding-bottom:0px;">
	<input type="hidden" id="ratio_amount" name="ratio_amount" value="${user.ratio_amount}"/>
	<input type="hidden" id="subsidy_amount" name="subsidy_amount" value="${user.subsidy_amount}"/>
	<input type="hidden" id="role_type"  value="${user.role_type}"/>
	<input type="hidden" id="cur_id"  value="${user.id}"/>
	<!-- 查询条件begin -->
	<div class="panel panel-default">
		<div class="panel-heading"><spring:message code='font.framework.search.conditions'/></div>
		<div class="panel-body">
			<form id="formSearchBill" class="form-horizontal">
				<div class="form-group">
				
					<label class="control-label col-sm-1" for="search_account">申请人账号</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="user_name" name="user_name">
					</div>
					
					<label class="control-label col-sm-1" for="search_account">状态</label>
					<div class="col-sm-2">
						<select id="status" name="status" tabindex="-1" class="form-control" ">
						    <option value=''>-请选择-</option>							
							<option value='0'>申请中</option>
							<option value='1'>已结算</option>
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
	
	<table id="billList"></table>
	
</div>
</div>

	
<script>
var pageTable;
$ScinanSelect2({id:'search_organization',placeholder: "请选择归属单位"});
$(function() {
    var user_id = '';
	pageTable = $ScinanPageTable_({
		$id:$('#billList'),
    	url: "${ctx}/bill/fetchBillCloseByPage.shtml",//请求url
    	searchForm:$('#formSearchBill'),//查询条件表单
    	locale : "<spring:message code='bootstraptable.local'/>",
    	columns: [
           {
               title: "",
               field: "select",
               checkbox: true,
               width: 100,//宽度
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
               title: "申请人名称",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "user_name",
               title: "申请人账号",
               width: 100,//宽度
               align: "center"
           },
           
           {
               field: "amount",
               title: "申请结算金额",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "close_type",
               title: "结算金额种类",
               width: 100,//宽度
               align: "center",
               formatter:function(value, row, index){
                   if(value == null || value == 'null') return '-';
                   if(value == 0 || value == '0') 
                   	return '分红余额';                  
                   if(value == 1 || value == '1') 
                    return '补贴余额';
                   }
           
               
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
                   	return '已结算';
                   if(value == 2 || value == '2') 
                    return '已完成';
                   }
           },
           {
               field: "target",
               title: "提现方式",               
               width: 100,//宽度
               align: "center",
               formatter:function(value, row, index){
                   if(value == null || value == 'null') return '-';
                   if(value == 0 || value == '0') 
                   	return '支付宝';
                   if(value == 1 || value == '1') 
                   	return '微信';
                   if(value == 2 || value == '2') 
                    return '银行卡';
                   }
           },
           {
               field: "create_time",
               title: "申请时间",
               sortable: true,
               width: 100,//宽度
               align: "center",
               formatter:function(value, row, index){
               	if(value == null || value == 'null') return '-';
              		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
               }
           },
           {
               field: "close_time",
               title: "结算时间",               
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
	
	
	//修改
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
		
		pageii = layer.open({
			title : "设置物流单号信息",
			type : 2,
			area : [ "70%", "95%" ],
			fix: true, //不固定
		    maxmin: true,
			content : '${ctx}/purchase/modifyShipping.shtml?id='+data[0].id
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



//申请提现信息
$('#btn_1006006001').click(function(){
	if(1>=$('#role_type').val()){
		layer.msg("抱歉！无权操作");    
		return;
	}
	var ratio_amount = $('#ratio_amount').val();
	var subsidy_amount = $('#subsidy_amount').val();	
	
	if(ratio_amount <=100 && subsidy_amount <=1000){
		layer.msg("不满足提现条件，无法申请提现！");
		return false;
	}
	pageii = layer.open({
		title : "申请提现信息",
		type : 2,
		area : [ "70%", "95%" ],
		fix: true, //不固定
	    maxmin: true,
		content : '${ctx}/bill/billCloseInit.shtml'
	});
});

//申请购货信息
$('#btn_1006006002').click(function(){	
	if(1>=$('#role_type').val()){
		layer.msg("抱歉！无权操作");    
		return;
	}
	pageii = layer.open({
		title : "申请购货信息",
		type : 2,
		area : [ "70%", "95%" ],
		fix: true, //不固定
	    maxmin: true,
		content : '${ctx}/purchase/addPurchaseInit.shtml'
	});
});

//结算
$('#btn_1006006003').click(function(){
	var data = pageTable.getSelections();
	if (data.length == 0 || data == "") {
		layer.msg("<spring:message code='select_one'/>");
		return;
	}
	
	if (data.length > 1) {
		layer.msg("<spring:message code='select_only_one'/>");
		return;
	}
	
	if(0 != data[0].status){
		layer.msg("该状态不是待结算");
		return;
	}
	
	var user_id = $('#cur_id').val();
	if(user_id == data[0].user_id){
	layer.msg("自己申请的只能由上级来结算");
		return;
	}
	
	layer.confirm('您确认要结算吗?', function(index) {
		var obj = CommnUtil.ajax("${ctx}/bill/settlement.shtml",{id:data[0].id},"json");
				
		if (obj.status == 200) {
			layer.msg("<spring:message code='success_operat'/>");
			pageTable.refreshPage();
			layer.close(pageii);
		} else if (obj.status == -101) {
			layer.msg("该记录状态不是申请中或是您自己申请的提现，无法结算");
			return false;
		} else {
			layer.msg("<spring:message code='operat_fail'/>");
		}
	});
});

//已完成
$('#btn_1006006004').click(function(){
	var data = pageTable.getSelections();
	if (data.length == 0 || data == "") {
		layer.msg("<spring:message code='select_one'/>");
		return;
	}
	
	if (data.length > 1) {
		layer.msg("<spring:message code='select_only_one'/>");
		return;
	}
	
	if(1 != data[0].status){
		layer.msg("该状态不是已结算");
		return;
	}
	var user_id = $('#cur_id').val();
	if(user_id != data[0].user_id){
	layer.msg("只能由申请人来完成");
		return;
	}
	layer.confirm('您确认已收到结算款项吗?', function(index) {
		var obj = CommnUtil.ajax("${ctx}/bill/completed.shtml",{id:data[0].id},"json");
				
		if (obj.status == 200) {
			layer.msg("<spring:message code='success_operat'/>");
			pageTable.refreshPage();
			layer.close(pageii);
		} else if (obj.status == -101) {
			layer.msg("该记录状态不是已结算或不是您自己申请的提现，无法已完成");
			return false;
		} else {
			layer.msg("<spring:message code='operat_fail'/>");
		}
	});
});

</script>