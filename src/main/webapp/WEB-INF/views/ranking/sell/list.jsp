<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body" style="padding-bottom:0px;">
	
	<div class="panel panel-default">
		<div class="panel-heading">排名月份</div>
		<div class="panel-body">
		<h3 id= "role"/>
		<h2 id="time1" style="color: blueviolet;" />
		</div>
	</div>  
 	
 	<form id="formSearch" class="form-horizontal">
 		<input type="hidden" id="type" name="type" />
 	</form>
 	
 	<!-- 增删查改 begin -->
	<div id="toolbar" class="btn-group">
		<c:forEach items="${systemResourcesBeans}" var="systemResourcesBean" varStatus="idxStatus" >
			<button id="btn_${systemResourcesBean.res_id}" type="button" class="btn btn-default" data-toggle="modal">
		 		<span class="${systemResourcesBean.description}" aria-hidden="true"></span> <spring:message code='${systemResourcesBean.res_name}'/>
			</button>
		</c:forEach>
	</div>
	<!-- 增删查改 end -->
	
	<table id="soldList"></table>
	
</div>
</div>

	
<script>
var pageTable;
$ScinanSelect2({id:'search_organization',placeholder: "请选择归属单位"});
$(function() {
    var user_id = '';
	pageTable = $ScinanPageTable_({
		$id:$('#soldList'),
    	url: "${ctx}/ranking/sellPage.shtml",//请求url
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
               field: "cur_ranking",
               title: "名次",
               width: 30,//宽度
               align: "center"
           },
           {
               field: "soldNumMouth",
               title: "月销量",
               width: 50,//宽度
               align: "center"
           },
           {
               field: "user_nickname",
               title: "昵称",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "user_name",
               title: "账号",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "role_type",
               title: "类型",
               width: 100,//宽度
               align: "center",
                formatter:function(value, row, index){
                	var str = value == 2 ? "代理商" : "经销商";
                	document.getElementById("role").innerHTML = str;
              		return str;
              		
               }               
           }
		]
	});
	
	curtime();
});
	
	
	//代理商排名
	$('#btn_1008001001').click(function(){
	$('#type').val(2);
		pageTable.refreshPage();
	});
	
	//经销商排名
	$('#btn_1008001002').click(function(){
		$('#type').val(3);
		pageTable.refreshPage();
	});
	
	 function curtime(){
        var a = new Date();
        var m = a.getMonth();
        document.getElementById("time1").innerHTML =m+'月份排名';
     }
    
</script>