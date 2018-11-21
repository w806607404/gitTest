<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body" style="padding-bottom:0px;">
<input type="hidden" value = "${user.id}" id="current_id"  name="current_id"/>
	
	<!-- 查询条件begin -->
	<div class="panel panel-default">
		<div class="panel-heading"><spring:message code='font.framework.search.conditions'/></div>
		<div class="panel-body">
			<form id="formSearch" class="form-horizontal">
					<div class="form-group">
				
					<label class="control-label col-sm-1" for="search_account">接收人</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="user_nickname" name="user_nickname">
					</div>
					
					<label class="control-label col-sm-1" for="search_account">状态</label>
					<div class="col-sm-2">
						<select id="status" name="status" tabindex="-1" class="form-control" ">
						    <option value=''>-请选择-</option>							
							<option value='0'>待发货</option>
							<option value='1'>已发货</option>
							<option value='2'>已完成</option>
							<option value='3'>待确认</option>
							<option value='4'>未确认</option>
							<option value='5'>缺货</option>							
						</select>
					</div>	
					
					<div class="col-sm-1 btn_query">
						<button type="button" id="btn_query_notify_send" class="btn btn-primary"><spring:message code='font.framework.search'/></button>
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
	
	<table id="notifySendList"></table>
	
</div>
</div>

	
<script>
var pageTable;
$(function() {
    var user_id = '';
	pageTable = $ScinanPageTable_({
		$id:$('#notifySendList'),
    	url: "${ctx}/notifySend/fetchNoticeSendByPage.shtml",//请求url
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
               field: "send_user_nickname",
               title: "通知人名称",
               width: 200,//宽度
               align: "center"
           },
           {
               field: "user_nickname",
               title: "接收人名称",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "device_name",
               title: "设备名称",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "count",
               title: "发货数量",
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
               field: "notify_type",
               title: "通知发货类型",
               width: 100,//宽度
               align: "center",
               formatter:function(value, row, index){
                   if(value == null || value == 'null') return '-';
                   if(value == 0 || value == '0') 
                   	return '线下转账';
                   if(value == 1 || value == '1') 
                   	return '正常通知';
                  
                   }
           },
           {
               field: "user_name",
               title: "收货人姓名",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "user_contact",
               title: "收货人联系方式",
               width: 100,//宽度
               align: "center"
           },
           
           {
               field: "user_address",
               title: "收货人详细地址",
               width: 100,//宽度
               align: "center"
           },
           
           {
               field: "shipping",
               title: "物流信息",               
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
                   	return '待发货';
                   if(value == 1 || value == '1') 
                   	return '已发货';
                   if(value == 2 || value == '2') 
                    return '已完成';
                   if(value == 3 || value == '3') 
                       return '待确认';
                   if(value == 4 || value == '4') 
                       return '未确认';
                   if(value == 5 || value == '5') 
                       return '缺货';
                   }
           },
           {
               field: "create_time",
               title: "通知时间",
               sortable: true,
               width: 100,//宽度
               align: "center",
               formatter:function(value, row, index){
               	if(value == null || value == 'null') return '-';
              		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
               }
           },
           {
               field: "receipt_time",
               title: "响应时间",
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
    $('#btn_query_notify_send').click(function(){
    	pageTable.refreshPage();
	});
	
  	//修改
	$('#btn_1007004002').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0 || data == "") {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		if (data[0].status == 3 && data[0].send_userId == $('#current_id').val()) {
			
		}else if(data[0].status == 1 && data[0].send_userId != $('#current_id').val()){
			
		}else{
			layer.msg("只允许在待确认或已发货状态下修改订单,或者不是您自己通知或发货的订单");
			return;
		}
		
		
		
		pageii = layer.open({
			title : "编辑发货通知信息",
			type : 2,
			area : [ "70%", "95%" ],
			fix: true, //不固定
		    maxmin: true,
			content : '${ctx}/notifySend/modifyNotifySendInit.shtml?id='+data[0].id
		});
		
	});
    
	//删除记录信息
	$('#btn_1007004003').click(function(){
		var data = pageTable.getSelections();
		if (data[0].status != 3) {
			layer.msg("只允许删除待确认订单");
			return;
		}
		if (data[0].send_userId != $('#current_id').val()) {
			layer.msg("只能由通知人通知人删除该订单");
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

		//确认框
		$ScinanSwal.confirm({
			title: "<spring:message code='confirm_delete'/>",
	        text: "<spring:message code='delete_prompt'/>",
	        confirmButtonText: "<spring:message code='font.framework.delete'/>",
	        cancelButtonText: "取消"
		},function(){
			var obj = CommnUtil.ajax("${ctx}/notifySend/delNotifySend.shtml",{id:data[0].id},"json");
    		if(obj.status == 200){
           	 	$ScinanSwal.alert("<spring:message code='delete_success'/>", "<spring:message code='delete_success_prompt'/>", "success");
           	 	pageTable.refreshPage(); 
           	}else if(obj.status==-1){
           		$ScinanSwal.alert("删除失败", "error");
           	 	pageTable.refreshPage();
           	}
    		else{
          	 	layer.msg("<spring:message code='delete_fail'/>");
           	}	
		});
	});
	
	
	//点击完成按钮
	$('#btn_1007004007').click(function(){
		var data = pageTable.getSelections();
		
		if (data[0].send_userId != $('#current_id').val()) {
			layer.msg("只能由通知人来完成该订单");
			return;
		}
		if (data[0].status != 1) {
			layer.msg("该记录状态不是已发货,操作失败");
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
		
		layer.confirm('是否确认完成该条记录?', function(index) {
			var obj = CommnUtil.ajax("${ctx}/notifySend/updateStatus.shtml",{id:data[0].id},"json");
					
			if (obj.status == 200) {
				layer.msg("<spring:message code='success_operat'/>");
				pageTable.refreshPage();
				layer.close(pageii);
			} else if (obj.status == -101) {
				layer.msg("该记录状态不是已发货状态,操作失败");
				return false;
			}  else if (obj.status == -102) {
				layer.msg("您补贴金额少于订单金额，操作失败");
				return false;
			}else {
				layer.msg("<spring:message code='operat_fail'/>");
			}
		});
		
	});
	
	//发货
	$('#btn_1007004005').click(function(){
		var data = pageTable.getSelections();
		
		if (data[0].status != 0) {
			layer.msg("该记录状态不是代发货,操作失败");
			return;
		}
		if (data[0].send_userId == $('#current_id').val()) {
			layer.msg("只能由接收人来发货");
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
		
		pageii = layer.open({
			title : "设置物流信息",
			type : 2,
			area : [ "70%", "95%" ],
			fix: true, //不固定
		    maxmin: true,
			content : '${ctx}/notifySend/setShippingInit.shtml?id='+data[0].id
		});
		
	});
	
	//确认
	$('#btn_1007004004').click(function(){
		var data = pageTable.getSelections();
		if (data[0].status != 3) {
			layer.msg("该记录状态不是待确认,操作失败");
			return;
		}
		if (data[0].send_userId == $('#current_id').val()) {
			layer.msg("不允许操作自己的通知订单");
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
		
		layer.confirm('是否确认该条记录?', function(index) {
			var obj = CommnUtil.ajax("${ctx}/notifySend/updateStatus.shtml",{id:data[0].id},"json");
					
			if (obj.status == 200) {
				layer.msg("<spring:message code='success_operat'/>");
				pageTable.refreshPage();
				layer.close(pageii);
			} else if (obj.status == -101) {
				layer.msg("该记录状态不是待确认或是您自己发送的发货通知,操作失败");
				return false;
			} else {
				layer.msg("<spring:message code='operat_fail'/>");
			}
		});
		
	});
	
	
	//缺货
	$('#btn_1007004006').click(function(){
		var data = pageTable.getSelections();
		if (data[0].status != 3 ) {
			layer.msg("该记录状态不是待确认,无法标记缺货");
			return;
		}
		if (data[0].send_userId == $('#current_id').val()) {
			layer.msg("不允许操作自己的通知订单");
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
		
		layer.confirm('是否确认缺货?', function(index) {
			var obj = CommnUtil.ajax("${ctx}/notifySend/stockout.shtml",{id:data[0].id},"json");
					
			if (obj.status == 200) {
				layer.msg("<spring:message code='success_operat'/>");
				pageTable.refreshPage();
				layer.close(pageii);
			} else if (obj.status == -101) {
				layer.msg("该记录状态不是待确认,无法标记缺货");
				return false;
			} else {
				layer.msg("<spring:message code='operat_fail'/>");
			}
		});
		
	});
});


	function provinceChange(code) {
		$.ajax({
			url : "${ctx}/sold/cityList.shtml", //请求的url地址
			dataType : "json", //返回格式类型为json
			async : false, //请求是否异步，默认为true:异步，这也是ajax重要特性
			data : {
				"parent_id" : code
			}, //传参的的参数	    
			type : "POST", //请求方式类型

			success : function(data) { //此处data为返回值	    		    	
				//请求成功时处理操作	         
				var str = "<option value=''>请选择市</option>";
				for (var i = 0; i < data.length; i++) {
					str += "<option value='"+data[i].id+"' >"
							+ data[i].area_name + "</option>";
				}
				$("#city_id").html(str);
				$("#district_id").html("<option value=''>请选择区</option>");
			},

			error : function() {
				//请求出错处理操作
				layer.msg("请求错误");
			}
		});
	}

	function cityChange(code) {
		$.ajax({
			url : "${ctx}/sold/districtList.shtml", //请求的url地址
			dataType : "json", //返回格式类型为json
			async : false, //请求是否异步，默认为true:异步，这也是ajax重要特性
			data : {
				"parent_id" : code
			}, //传参的的参数	    
			type : "POST", //请求方式类型

			success : function(data) { //此处data为返回值	    			    			    	
				//请求成功时处理操作
				$("select[name='district_id']").empty();
				var str = "<option value=''>请选择区</option>";
				for (var i = 0; i < data.length; i++) {
					str += "<option value='"+data[i].id+"'>"
							+ data[i].area_name + "</option>";
				}
				$("#district_id").html(str);
			},

			error : function() {
				//请求出错处理操作
				layer.msg("请求错误");
			}
		});
	}
	function changeSelect(obj) {
		var type = $(obj).val();
		if (type == 1) {//厂商
			$('#div_company_id').show();
		} else if (type == 0) {//司南
			$('#div_company_id').hide();
		}
	}

	function lockAccount(val, user_id) {

		var obj = CommnUtil.ajax("${ctx}/user/lockAccount.shtml", {
			lockValue : val,
			userId : user_id
		}, "json");
		if (obj.status == 200) {
			pageTable.refreshPage();
			if (val == 1) {
				layer.msg("<spring:message code='userlist.lock.success'/>");
			} else {
				layer.msg("<spring:message code='userlist.unlock.success'/>");
			}
		} else if (obj.status == 201) {//暂无
			layer.alert_("<spring:message code='message'/>",
					"<spring:message code='userlist.undo'/>",
					"<spring:message code='font.framework.confirm'/>");
		} else {
			if (val == 1) {
				layer.alert_("<spring:message code='message'/>",
						"<spring:message code='userlist.lock.fail'/>",
						"<spring:message code='font.framework.confirm'/>");
			} else {
				layer.alert_("<spring:message code='message'/>",
						"<spring:message code='userlist.unlock.fail'/>",
						"<spring:message code='font.framework.confirm'/>");
			}
		}
	}

	//通知发货
	$('#btn_1007004001').click(function() {
		pageii = layer.open({
			title : "通知发货订单信息",
			type : 2,
			area : [ "70%", "95%" ],
			fix : true, //不固定
			maxmin : true,
			content : '${ctx}/notifySend/addNotifySendInit.shtml'
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