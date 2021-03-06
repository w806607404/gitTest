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
				
					<label class="control-label col-sm-1" for="search_account">产品ID</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="device_id" name="device_id">
					</div>
					
					<div class="col-sm-1 btn_query">
						<button type="button" id="btn_query_back_bill_detail" class="btn btn-primary"><spring:message code='font.framework.search'/></button>
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
	
	<table id="backBillDetailList"></table>
	
</div>
</div>

	
<script>
var pageTable;
$(function() {
    var user_id = '';
	pageTable = $ScinanPageTable_({
		$id:$('#backBillDetailList'),
    	url: "${ctx}/backBillDetail/fetchBackBillDetailByPage.shtml",//请求url
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
               field: "device_id",
               title: "产品ID",
               width: 200,//宽度
               align: "center"
           },
           {
               field: "benefit_user_nickname",
               title: "分成收益人名称",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "benefit_user_name",
               title: "分成收益人账号",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "benefit_user_type",
               title: "收益人类型",
               width: 100,//宽度
               align: "center"               
           },
           {
               field: "sale_user_nickname",
               title: "售出人名称",
               width: 100,//宽度
               align: "center"
           },
           
           {
               field: "sale_user_type",
               title: "售出人用户类型",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "product_price",
               title: "产品金额",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "amount",
               title: "扣除分成金额",
               width: 100,//宽度
               align: "center"
           },
           
           {
               field: "sale_province_name",
               title: "省份",
               width: 100,//宽度
               align: "center"
           },
           
           {
               field: "sale_city_name",
               title: "城市",               
               width: 100,//宽度
               align: "center"
           },          
           {
               field: "sale_district_name",
               title: "区县",               
               width: 100,//宽度
               align: "center"
           },
           {
               field: "create_time",
               title: "扣除时间",
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
    $('#btn_query_back_bill_detail').click(function(){
    	pageTable.refreshPage();
	});
	
	
	//删除记录信息
	$('#btn_1006003003').click(function(){
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
			var obj = CommnUtil.ajax("${ctx}/billDetail/delBillDetail.shtml",{id:data[0].id},"json");
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
	
	
	//修改
	$('#btn_1005001002').click(function(){
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
			title : "修改销售记录信息",
			type : 2,
			area : [ "70%", "95%" ],
			fix: true, //不固定
		    maxmin: true,
			content : '${ctx}/sold/modifySoldInit.shtml?id='+data[0].id
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

	//添加用户比例
	$('#btn_1005001001').click(function() {
		pageii = layer.open({
			title : "新增销售记录信息",
			type : 2,
			area : [ "70%", "95%" ],
			fix : true, //不固定
			maxmin : true,
			content : '${ctx}/sold/addSoldInit.shtml'
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