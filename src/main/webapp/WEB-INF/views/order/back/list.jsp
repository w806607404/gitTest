<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body" style="padding-bottom:0px;">
	<input type="hidden" value = "${accountInfo.id}" id="user_id" />
	<input type="hidden" value = "${accountInfo.role_type}" id="role_type" />
	<!-- 查询条件begin -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message code='font.framework.search.conditions' />
			</div>
			<div class="panel-body">
				<form id="formSearchAccountBack" class="form-horizontal">
					<div class="form-group">

						<label class="control-label col-sm-1" for="search_account">产品编号</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="device_id"
								name="device_id">
						</div>
						
						<label class="control-label col-sm-1" for="search_account">物流状态</label>
						<div class="col-sm-2">
							<select id="status" name="status" tabindex="-1" class="form-control" ">
						    	<option value=''>-请选择-</option>							
								<option value='0'>申请中</option>
								<option value='1'>代发货</option>
								<option value='2'>已发货</option>
								<option value='3'>已完成</option>
							</select>
						</div>
							
					</div>

					<div class="form-group">												
						<div class="col-sm-1 btn_query">
						<button type="button" id="btn_query_accountback"
							class="btn btn-primary">
							<spring:message code='font.framework.search' />
						</button>
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
	
	<table id="accountBackList"></table>
	
</div>
</div>


<script>
var pageTable;
$(function() {
    var user_id = '';
	pageTable = $ScinanPageTable_({
		$id:$('#accountBackList'),
    	url: "${ctx}/back/fetchBackByPage.shtml",//请求url
    	searchForm:$('#formSearchAccountBack'),//查询条件表单
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
               field: "device_id",
               title: "产品编号",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "device_name",
               title: "产品名称",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "user_nickname",
               title: "退换货人名称",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "user_name",
               title: "退换货人账号",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "user_type",
               title: "退换货人类型",
               width: 100,//宽度
               align: "center"
           },                               
		   {
				field : "back_type",
				title : "类型",
				width : 100,//宽度
				align : "center",
				formatter : function(value, row, index) {
					if (value == null || value == 'null')
						return '-';
					if (value == 0 || value == '0')
						return '退货';
					if (value == 1 || value == '1')
						return '换货';				
				}
			}, 
			{
				field : "cause",
				title : "退换货原因",
				width : 100,//宽度
				align : "center"
			}, 
			{
	               field: "shipping",
	               title: "物流信息",
	               width: 100,//宽度
	               align: "center"
	           },	
			{
				field : "status",
				title : "状态",
				width : 100,//宽度
				align : "center",
				formatter : function(value, row, index) {
					if (value == null || value == 'null')
						return '-';
					if (value == 0 || value == '0')
						return '待确认';
					if (value == 1 || value == '1')
						return '待发货';
					if (value == 2 || value == '2')
						return '已发货';
					if (value == 3 || value == '3')
						return '已完成';
				}
			}, 		
			{
				field : "create_time",
				title : "申请时间",
				sortable : true,
				width : 100,//宽度
				align : "center",
				formatter : function(value, row, index) {
					if (value == null || value == 'null')
						return '-';
					return new Date(value).format("yyyy-MM-dd hh:mm:ss");
				}
			}

			]
		});

		//查询按钮
		$('#btn_query_accountback').click(function() {
			pageTable.refreshPage();
		});

		//删除退换货记录
		$('#btn_1005005003').click(function(){
		var data = pageTable.getSelections();
		if (data[0].status != 0) {
			layer.msg("抱歉！记录状态为“申请中”时才可以删除该记录");
			return;
		}
		
		if (data.length == 0) {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		
		if (data[0].user_id != $("#user_id").val()) {
						layer.msg("抱歉！只允许删除自己提交的记录");
						return;
			}

		//确认框
		$ScinanSwal.confirm({
			title: "<spring:message code='confirm_delete'/>",
	        text: "<spring:message code='delete_prompt'/>",
	        confirmButtonText: "<spring:message code='font.framework.delete'/>",
	        cancelButtonText: "取消"
		},function(){
			var obj = CommnUtil.ajax("${ctx}/back/delBack.shtml",{id:data[0].id},"json");
    		if(obj.status == 200){
           	 	$ScinanSwal.alert("<spring:message code='success_operat'/>","<spring:message code='delete_success_prompt'/>", "success");
           	 	pageTable.refreshPage(); 
           	}else if(obj.status==500){
           		$ScinanSwal.alert("删除失败", "error");
           	 	pageTable.refreshPage();
           	}
    		else{
          	 	layer.msg("<spring:message code='delete_fail'/>");
           	}	
		});
	});

		//修改
		$('#btn_1005005002').click(
				function() {
					var data = pageTable.getSelections();
					if (data[0].status != 0) {
						layer.msg("抱歉！记录状态为“申请中”时才可以修改该记录");
						return;
					}
					
					if (data.length == 0 || data == "") {
						layer.msg("<spring:message code='select_one'/>");
						return;
					}

					if (data.length > 1) {
						layer.msg("<spring:message code='select_only_one'/>");
						return;
					}
					
					if (data[0].user_id != $("#user_id").val()) {
						layer.msg("抱歉！不允许修改下级提交的记录");
						return;
					}

					pageii = layer.open({
						title : "修改退换货信息",
						type : 2,
						area : [ "70%", "95%" ],
						fix : true, //不固定
						maxmin : true,
						content : '${ctx}/back/modifyBackInit.shtml?id='
								+ data[0].id
					});

				});
	});

	
	
	

	//修改退换货状态（确认）
	$('#btn_1005005004').click(function() {
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
			layer.msg("记录状态不是待确认，操作失败！");
			return;
		}
		if (data[0].user_id == $("#user_id").val()) {
			layer.msg("不允许确认自己提交的记录！");
			return;
		}

		var obj = CommnUtil.ajax("${ctx}/back/updateBackStatus.shtml",{id:data[0].id},"json");
		layer.msg("obj.status="+obj.status);
		if(obj.status == 200){
			layer.msg("<spring:message code='success_operat'/>");
       	 	pageTable.refreshPage(); 
       	}else if(obj.status == 500){
       		layer.msg("<spring:message code='operat_fail'/>", "error");
       	 	pageTable.refreshPage();
       	}else if(obj.status == -101){
       		layer.msg("抱歉！没有权限确认该条记录状态");      	 	
       	}
       	else if(obj.status == -102){
       		layer.msg("抱歉！尚未绑定改产品！");      	 	
       	}
		else{
      	 	layer.msg("<spring:message code='operat_fail'/>");
       	}	

	});
	
	//修改退换货状态（发货）
	$('#btn_1005005005').click(function() {
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
			layer.msg("记录状态不是待发货，操作失败！");
			return;
		}
		if (data[0].user_id != $("#user_id").val()) {
			layer.msg("只能由申请人发货");
			return;
		}

	pageii = layer.open({
			title : "填写物流信息",
			type : 2,
			area : [ "70%", "95%" ],
			fix : true, //不固定
			maxmin : true,
			content : '${ctx}/back/modifyBackInit.shtml?id='
					+ data[0].id
		});

	});
	
	//修改退换货状态（完成）
	$('#btn_1005005006').click(function() {
		var data = pageTable.getSelections();
		
		if (data.length == 0 || data == "") {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}

		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		if (data[0].status != 2) {
			layer.msg("记录状态已完成不需要再确认");
			return;
		}
		if (data[0].user_id == $("#user_id").val()) {
			layer.msg("不允许确认完成自己提交的记录");
			return;
		}

		var obj = CommnUtil.ajax("${ctx}/back/updateBackStatus.shtml",{id:data[0].id},"json");
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
	
	//退货信息
	$('#btn_1005005001').click(function() {
	if(1>=$('#role_type').val()){
		layer.msg("抱歉！无权操作");    
		return;
	}
		pageii = layer.open({
			title : "添加退换货信息",
			type : 2,
			area : [ "70%", "95%" ],
			fix : true, //不固定
			maxmin : true,
			content : '${ctx}/back/accountBackInit.shtml'
		});
	});

	//设置单价
	function setValue(userId) {
		pageii = layer.open({
			title : "单价设置",
			type : 2,
			area : [ "70%", "65%" ],
			fix : true, //不固定
			maxmin : true,
			content : '${ctx}/user/setUnitPrice.shtml?userId=' + userId
		});
	}
</script>