<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body" style="padding-bottom:0px;">
	
	<div class="panel panel-default">
		<div class="panel-heading">排名月份</div>
		<div class="panel-body">
		<h2 id="time1" style="color: blueviolet;" />
		</div>
	</div>  
 
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
    	url: "${ctx}/ranking/usePage.shtml",//请求url
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
               width: 20,//宽度
               align: "center"
           },
           {
               field: "deviceUsedTimesMouth",
               title: "使用次数",
               width: 30,//宽度
               align: "center"
           },
           {
               field: "user_name",
               title: "昵称",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "user_mobile",
               title: "账号",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "redEnvelopeAmount",
               title: "已收红包金额",
               width: 100,//宽度
               align: "center"               
           }
		]
	});
	
	curtime();
	
	//奖励红包金额
	$('#btn_1008002001').click(function(){
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
			title : "奖励红包金额",
			type : 2,
			area : [ "60%", "85%" ],
			fix: true, //不固定
		    maxmin: true,
			content : '${ctx}/ranking/use/award.shtml?id='+data[0].id+'&redEnvelopeAmount='+data[0].redEnvelopeAmount
		});
		
	});
});

	function curtime(){
        var a = new Date();
        var m = a.getMonth();
        document.getElementById("time1").innerHTML =m+'月份排名';
     }
</script>