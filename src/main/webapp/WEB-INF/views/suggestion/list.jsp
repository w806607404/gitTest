<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body" style="padding-bottom:0px;">

	 <!-- 查询条件begin -->
    <div class="panel panel-default">
		<div class="panel-heading"><spring:message code='font.framework.search.conditions'/></div>
		<div class="panel-body">
			<form id="formSearch" class="form-horizontal">			 
				<div class="form-group" style="margin-top:10px">			
					<label class="control-label col-sm-1" for="search_state">反馈类型:</label>
					<div class="col-sm-2">
						<select id="type_id" name="type_id" class="article_type" tabindex="-1"  >
							<option value=''>-<spring:message code='choose'/>-</option>
							<option value='1'>产品质量</option>
							<option value='2'>用户体验</option>
							<option value='3'>产品咨询</option>
							<option value='4'>意见建议</option>
						</select>
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
	
	<table id="suggestionList"></table>
	
</div>
</div>

	
<script>
$ScinanSelect2({id:'type_id',placeholder: "请选择反馈类型"});
var pageTable;
$(function() {
    var user_id = '';
	pageTable = $ScinanPageTable_({
		$id:$('#suggestionList'),
    	url: "${ctx}/suggestion/suggestionPage.shtml",//请求url
    	searchForm:$('#formSearch'),//查询条件表单
    	locale : "<spring:message code='bootstraptable.local'/>",
    	columns: [
	    	{
                title: "",
                field: "select",
                checkbox: true,
                width: 20,//宽度
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
               field: "contact",
               title: "反馈人联系方式",
               width: 100,//宽度
               align: "center"
           },
           {
               field: "type_id",
               title: "反馈类型",
               width: 100,//宽度
               align: "center",
                formatter:function(value, row, index){
                	var str = "";
                	switch(value){
                	case 1:
                	str="产品质量";
                	break;
                	case 2:
                	str="用户体验";
                	break;
                	case 3:
                	str="产品咨询";
                	break;
                	case 4:
                	str="意见建议";
                	break;
                	}
              		return str;
               }               
           },
           {
               field: "content",
               title: "反馈内容",
               width: 150,//宽度
               align: "center"
           },
            {
               field: "create_time",
               title: "反馈时间	",
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
	
	$('#btn_1009001003').click(function(){
		var id_arr = pageTable.getSelectionIds();
		if (id_arr.length == 0) {
			layer.msg("<spring:message code='select_last_one'/>");
			return;
		}
		if (id_arr.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		//确认删除
		$ScinanSwal.confirm({
			    title: "<spring:message code='confirm_delete'/>",
		        text: "<spring:message code='delete_prompt'/>",
		        confirmButtonText: "<spring:message code='font.framework.delete'/>",
            cancelButtonText:"<spring:message code='font.framework.cancel'/>"
		},function(){
			var obj = CommnUtil.ajax("${ctx}/suggestion/deleteSuggestion.shtml",{id:id_arr[0]},"json");
			if (obj.status === 200) {
				$ScinanSwal.alert("<spring:message code='delete_success'/>","<spring:message code='delete_success_prompt'/>", "success");
				pageTable.refreshPage();
			}else{
				layer.msg("<spring:message code='delete_fail'/>");
			}
		});
		});
	
	//查询按钮
    $('#btn_query').click(function(){
    	pageTable.refreshPage();
	});
    
</script>