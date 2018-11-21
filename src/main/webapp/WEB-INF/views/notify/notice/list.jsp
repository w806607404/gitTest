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
					<label class="control-label col-sm-1" for="search_state">公告类型:</label>
					<div class="col-sm-2">
						<select id="article_type" name="article_type" class="article_type" tabindex="-1"  >
							<option value=''>-<spring:message code='choose'/>-</option>
							<option value='4'>公司介绍</option>
							<option value='3'>首页广告</option>
							<option value='6'>通知公告</option>
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
  <table id="mytab"></table>
</div>
</div>
<script>
$ScinanSelect2({id:'article_type',placeholder: "请选择公告类型"});
var type = 'article';
var pageTable;
$(function() {
	pageTable = $ScinanPageTable_({
		$id:$("#mytab"),
        url: "${ctx}/notify/fetchNoticeByPage.shtml?type="+ type,//数据源
        searchForm:$('#formSearch'),//查询条件form表单
        locale : "<spring:message code='bootstraptable.local'/>",//国际化
        columns: [
            {
                title: "",
                field: "select",
                checkbox: true,
                width: 20,//宽度
                align: "center",
                valign: "middle"
            }
            ,
            {
                field: "app_name",
                title: "<spring:message code='title.app.name'/>",
                align: "center",
                width: 150
            },
            {
                field: "title",
                title: "<spring:message code='title.title'/>",
                align: "center",
                width: 100
            },
            {
                field: "thumb_url",
                title: "<spring:message code='title.img'/>",
                align: "center",
                formatter:function(value,row,index){
	   				return "<img src=" + value + " style='width:60px'></img>";
	   		    }
            },
            {
                field: "article_type",
                title: "<spring:message code='title.article.type'/>",
                align: "center",
                width: 60,
                formatter:function(value,row,index){
	   				if(value == "3"){
	   					return "首页广告";
	   				}else if(value == "4"){
	   					return "公司介绍";
	   				}else if(value == "6"){
	   					return "通知公告";
	   				}
	   				else{
	   					return '-';
	   				}
	   		}
            },
            {
                field: "detail_url",
                title: "<spring:message code='title.url'/>",
                align: "center",
                formatter:function(value,row,index){
	   				return "<a href=" + value + " target='_blank'>" + value + "</a>";
	   		    }
            },
            {
                field: "create_time",
                title: "<spring:message code='create_time'/>",
                sortable: true,
                align: "center",
                formatter:function(value, row, index){
                	if(value == null || value == 'null') return '-';
               		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                }
            },
            {
                field: "update_time",
                title: "<spring:message code='update_time'/>",
                sortable: true,
                align: "center",
                formatter:function(value, row, index){
                	if(value == null || value == 'null') return '-';
               		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                }
            }
		]
	});
	/*查询*/
	$('#btn_query').click(function(){
		pageTable.refreshPage();  
	});
	/*添加*/
	$("#btn_1007003001").click("click", function() {
		var page_type = 2;
		setCookie("page_type",page_type,1);
		pageii = layer.open({
			title : "<spring:message code='title.add.a'/>",
			type : 2,
			fix: true, //固定
		    maxmin: true,
		    offset: 'auto',
			area : [ "90%", "95%" ],
			content :'${ctx}/notify/notice/addUI.shtml'
		});
	});
	/*修改*/
	$('#btn_1007003002').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0) {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		var page_type = 2;
		setCookie("page_type",page_type,1);
		pageii = layer.open({
			title : "<spring:message code='title.update.a'/>",
			type : 2,
			fix: true, //固定
		    maxmin: true,
		    offset: 'auto',
			area : [ "90%", "95%" ],
			content : '${ctx}/notify/notice/editUI.shtml?id=' + data[0].id
		});
	});
	$("#btn_audit").click("click", function() {
		var data = pageTable.getSelections();
		if (data.length == 0) {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		
		var page_type = 2;
		setCookie("page_type",page_type,1);
		pageii = layer.open({
			title : "<spring:message code='title.check.a'/>",
			type : 2,
			fix: true, //固定
		    maxmin: true,
		    offset: 'auto',
			area : [ "90%", "95%" ],
			content : '${ctx}/notify/notice/approvalUI.shtml?id=' + data[0].id
		});
	});
	
	$('#btn_1007003003').click(function(){
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
			var obj = CommnUtil.ajax("${ctx}/notify/notice/delete.shtml",{ids:id_arr + ""},"json");
			if (obj.status == 200) {
				$ScinanSwal.alert("<spring:message code='delete_success'/>","<spring:message code='delete_success_prompt'/>", "success");
				pageTable.refreshPage();
			}else{
				layer.msg("<spring:message code='delete_fail'/>");
			}
		});
		});
});
</script>