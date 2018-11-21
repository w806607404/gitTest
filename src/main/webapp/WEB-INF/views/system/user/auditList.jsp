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

	
<script>
var pageTable;
$ScinanSelect2({id:'search_organization',placeholder: "请选择归属单位"});
$(function() {
    var user_id = '';
	pageTable = $ScinanPageTable_({
		$id:$('#mytab'),
    	url: "${ctx}/user/fetchAuditPage.shtml",//请求url
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
               width: 10,//宽度
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
               field: "pay_proof",
               title: "付费凭证",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "status",
               title: "状态",
               width: 100,//宽度
               align: "center",
               formatter:function(value, row, index){
               var str = "";
               switch(value){
               			case 6:
                        str="注册用户";
                        break;
                        case 5:
                        str="审核通过";
                        break;
                        case 2:
                        str="待审核";
                        return "<font color=\"blue\"><a href=\"#\" onclick=\"setStatus("+row.id+");\">审核中</a></font>"
                        break;
                   		case 4:
                        str="预约用户";
                        return "<font color=\"blue\"><a href=\"#\" onclick=\"setStatus("+row.id+");\">预约用户</a></font>"
                        break;
                        case 3:
                        str="审核不通过";
                        return "<font color=\"red\">审核不通过</font>"
                        break;
               }
               return "<font color=\"blue\">"+str+"</font>";
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
               field: "update_time",
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
               field: "create_time",
               title: "注册时间",
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
});

//审核
function setStatus(userId){
	var type = 2;
	pageii = layer.open({
		title : "审核账户",
		type : 2,
		area : [ "70%", "65%" ],
		fix: true, //不固定
	    maxmin: true,
		content : '${ctx}/user/setStatus.shtml?userId='+userId+'&type='+type
	});
}

	//查询按钮
    $('#btn_query').click(function(){
    	pageTable.refreshPage();
	});
	
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

