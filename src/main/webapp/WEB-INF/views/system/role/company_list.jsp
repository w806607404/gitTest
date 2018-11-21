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
				
					<label class="control-label col-sm-1" for="search_role_name"><spring:message code='rolelist.role.name'/></label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="search_role_name" name="search_role_name">
					</div>
				
					<label class="control-label col-sm-1" for="search_role_key"><spring:message code='rolelist.role.key'/></label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="search_role_key" name="search_role_key">
					</div>
					
					<label class="control-label col-sm-1" for="search_role_state"><spring:message code='rolelist.rolestatus'/></label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="search_role_state" name="search_role_state">
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
			<button id="${systemResourcesBean.name_ext1}" type="button" class="btn btn-default" data-toggle="modal" <c:if test="${systemResourcesBean.menu_status == 2}"> data-target='#addModal'</c:if>>
		 		<span class="${systemResourcesBean.description}" aria-hidden="true"></span><spring:message code='${systemResourcesBean.name}'/>
			</button>
		</c:forEach>
	</div>
	<!-- 增删查改 end -->
	
	<table id="mytab"></table>
	
</div>
</div>

	<!-- 新增角色 begin -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-hidden="true">
	
	   <div class="modal-dialog">
	   
	        <div class="modal-content">
	        
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                    &times;
	                </button>
	                <h4 class="modal-title" id="myModalLabel"><spring:message code='rolelist.addrole'/></h4>
	            </div>
	            
	            <div class="modal-body">
                        <form class="form-horizontal m-t" id="addroleform" action="${ctx}/role/add.shtml">
                        	<input type="hidden" id="role_type" name="role_type" value="4"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><spring:message code='rolelist.role.name'/>：</label>
                                <div class="col-sm-8">
                                    <input id="role_name" name="role_name" type="text" class="form-control" placeholder="<spring:message code='rolelist.name.long'/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><spring:message code='rolelist.rolekeywords'/>：</label>
                                <div class="col-sm-8">
                                    <input id="role_key" type="text" class="form-control" name="role_key" placeholder="<spring:message code='rolelist.key.long'/>">
                                </div>
                            </div>
                            
					        <div class="form-group"  id="add_div_company">
								<label class="col-sm-3 control-label">厂商名称：</label>
								<div class="col-sm-8">
						          <select id="company_id" name="company_id" class="selectpicker form-control" multiple data-live-search="true" data-live-search-placeholder="Search" data-actions-box="true">
				                         <c:forEach items="${factorys}" var="factory" varStatus="idxStatus" >
						                     <option value ="${factory.id}">${factory.name}</option>
					                     </c:forEach>
									</select>
						        </div>
					        </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><spring:message code='rolelist.rolestatus'/>：</label>
                                <div class="col-sm-8">
	                                <select class="form-control" id="state" name="state">
	                                    <option value="1">有效</option>
	                                    <option value="2">无效</option>
	                                </select>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><spring:message code='rolelist.description'/>：</label>
                                <div class="col-sm-8">
                                    <textarea id="description" name="description" class="form-control" placeholder="<spring:message code='rolelist.des.long'/>"></textarea>
                                </div>
                            </div>
                            
                        </form>
	            </div>
	            
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='font.framework.cancel'/></button>
	                <button type="button" class="btn btn-primary" id="addrolebutton"><spring:message code='font.framework.save'/></button>
	            </div>
	            
	        </div>
	        
	    </div>
	</div>
	<!-- 新增角色 end -->
	
	
	
	<!-- 修改角色 begin -->
	<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-hidden="true">
	
	   <div class="modal-dialog">
	   
	        <div class="modal-content">
	        
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                    &times;
	                </button>
	                <h4 class="modal-title" id="myModalLabel"><spring:message code='rolelist.modifyrole'/></h4>
	            </div>
	            
	            <div class="modal-body">
                        <form class="form-horizontal m-t" id="modifyroleform" action="${ctx}/role/update.shtml">
                            <input name="role_type" id="role_type" type="hidden" />
                            <input name="modify_id" id="modify_id" type="hidden"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><spring:message code='rolelist.role.name'/>：</label>
                                <div class="col-sm-8">
                                    <input id="role_name" name="role_name" type="text" class="form-control" placeholder="<spring:message code='rolelist.name.long'/>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><spring:message code='rolelist.rolekeywords'/>：</label>
                                <div class="col-sm-8">
                                    <input id="role_key" type="text" class="form-control" name="role_key" placeholder="<spring:message code='rolelist.key.long'/>">
                                </div>
                            </div>
                            
                            <div class="form-group">
								<label class="col-sm-3 control-label">厂商名称：</label>
								<div class="col-sm-8">
						          <select id="company_id" name="company_id" class="selectpicker form-control" multiple data-live-search="true" data-live-search-placeholder="Search" data-actions-box="true">
				                         <c:forEach items="${factorys}" var="factory" varStatus="idxStatus" >
						                     <option value ="${factory.id}">${factory.name}</option>
					                     </c:forEach>
									</select>
						        </div>
					        </div>
                            
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><spring:message code='rolelist.rolestatus'/>：</label>
                                <div class="col-sm-8">
	                                <select class="form-control" id="state" name="state">
	                                    <option value="1">是</option>
	                                    <option value="2">否</option>
	                                </select>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><spring:message code='rolelist.description'/>：</label>
                                <div class="col-sm-8">
                                    <textarea id="description" name="description" class="form-control" placeholder="<spring:message code='rolelist.des.long'/>"></textarea>
                                </div>
                            </div>
                            
                        </form>
	            </div>
	            
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='font.framework.cancel'/></button>
	                <button type="button" class="btn btn-primary" id="modifyrolebutton"><spring:message code='font.framework.modify'/></button>
	            </div>
	            
	        </div>
	        
	    </div>
	</div>
	<!-- 修改角色 end -->
	
<script>

var ly;
var pageTable;
$(function() {
	
	pageTable = $ScinanPageTable_({
		$id:$('#mytab'),
        url: "${ctx}/role/fetchByPage.shtml",//数据源
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
            },
            {
                field: "id",
                title: "ID",
                align: "center"
            },
            {
                field: "role_name",
                title: "<spring:message code='rolelist.role.name'/>",
                align: "center"
            },
            {
                field: "role_key",
                title: "<spring:message code='rolelist.role.key'/>",
                align: "center"
            },
            {
                field: "state",
                title: "<spring:message code='rolelist.state'/>",
                align: "center",
                formatter:function(value, row, index){
               		return value == 2 ? "<spring:message code='no'/>" : "<spring:message code='yes'/>";
                }
            },
            {
                field: "description",
                title: "<spring:message code='rolelist.description'/>",
                align: "center"
            },
//             {
//                 field: "company_id",
//                 title: "厂商ids",
//                 align: "center"
//             },
            
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
	
	//保存角色信息
	$('#addrolebutton').click(function(){	

		var role_name = $('#addroleform #role_name').val();
		var company_id = $('#addroleform #company_id').val();
		
		if(role_name.length < 1){
			layer.tips('<spring:message code='rolelist.name.not.empty'/>','#addroleform #role_name',{tips:[3,'#293846']});
			return;
		}
		if(role_name.length >85){
			layer.tips('<spring:message code='rolelist.name.long'/>','#addroleform #role_name',{tips:[3,'#293846']});
			return;
		}
		
		var obj = CommnUtil.ajax("${ctx}/role/checkrolename.shtml",{role_name:role_name},"json");
		if(obj.status == 200){//角色名称没有重复
			var role_key = $('#addroleform #role_key').val();
		    if(role_key.length < 1){
				layer.tips('<spring:message code='rolelist.key.not.empty'/>','#addroleform #role_key',{tips:[3,'#293846']});
				return;
			}
		    if(role_key.length >85){
				layer.tips('<spring:message code='rolelist.key.long'/>','#addroleform #role_key',{tips:[3,'#293846']});
				return;
			}
		    
		    if(company_id=="" || company_id==null){
				layer.msg("厂商不能为空");
				return;
			}
		    
			var description = $('#addroleform #description').val();
			if(description.length >85){
				layer.tips('<spring:message code='rolelist.des.long'/>','#addroleform #description',{tips:[3,'#293846']});
				return;
			}
			
			//添加角色请求
			var obj = CommnUtil.ajax($('#addroleform').attr("action"),$("#addroleform").serialize(),"json");	
			 if(obj.status == 200){
	         	 $("#addModal").modal('hide'); 
	         	 pageTable.refreshPage();  
	         	 $('#addroleform')[0].reset();
         		$('#add_div_company').show();
	    		$('#add_div_company_device_type').hide();
	         	 layer.msg("<spring:message code='add_success'/>");
	          }else{
	         	 layer.msg("<spring:message code='add_fail'/>");
	          }
		}else{//重复
			layer.tips('<spring:message code='rolelist.name_repeat'/>','#addroleform #role_name',{tips:[3,'#293846']});
			return;
		}

	});
	
	//权限分配
	$('#roleSetMenu').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0) {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		
		var url = '${ctx}/resources/permissions.shtml?id=' + data[0].id;
		ly = layer.open({
			title : "分配权限",
			type : 2,
			area : [ "700px", "60%" ],
			fix: false, //不固定
		    maxmin: true,
			content : url
		});
		
	});
	
	//点击删除角色按钮
	$('#roleDelete').click(function(){
		//获取勾选id数组
		var ids_arr = pageTable.getSelectionIds();
		if (ids_arr.length == 0) {
			layer.msg("<spring:message code='select_last_one'/>");
			return;
		}
		
		//确认框
		$ScinanSwal.confirm({
			title: "<spring:message code='confirm_delete'/>",
	        text: "<spring:message code='delete_prompt'/>",
	        confirmButtonText: "<spring:message code='font.framework.delete'/>"
		},function(){
			var obj = CommnUtil.ajax("${ctx}/role/delete.shtml",{ids:ids_arr + ""},"json");
    		if(obj.status == 200){
           	 	$ScinanSwal.alert("<spring:message code='delete_success'/>", "<spring:message code='delete_success_prompt'/>", "success");
           	 	pageTable.refreshPage();  
           	}else{
          	 	layer.msg("<spring:message code='delete_fail'/>");
           	}	
		});
	    
	});
	
	//点击修改角色按钮
	$('#roleModify').click(function(){
		var data = pageTable.getSelections();
		if (data.length == 0 || data == "") {
			layer.msg("<spring:message code='select_one'/>");
			return;
		}
		
		if (data.length > 1) {
			layer.msg("<spring:message code='select_only_one'/>");
			return;
		}
		var companyIdArr = "";
		var companyIds = data[0].company_id;
		if(companyIds!="" && null!=companyIds){
	      companyIdArr = companyIds.split(",");
		}
		$('#modifyroleform #company_id').selectpicker('refresh');
		$('#modifyroleform #modify_id').val(data[0].id);
		$('#modifyroleform #role_name').val(data[0].role_name);
		$('#modifyroleform #role_key').val(data[0].role_key);
		$('#modifyroleform #state').val(data[0].state);
		$('#modifyroleform #description').val(data[0].description);
		$('#modifyroleform #company_id').val(companyIdArr);
		$('#modifyroleform #company_id').selectpicker('refresh');//赋值后要刷新select选择框
		$("#modifyModal").modal('show'); 
	});
	
	//修改提交
	$('#modifyrolebutton').click(function(){
		var role_name = $('#modifyroleform #role_name').val();
		var role_id = $('#modifyroleform #modify_id').val();
		var company_id = $('#modifyroleform #company_id').val();
		if(role_name.length < 1){
			layer.tips('<spring:message code='rolelist.name.not.empty'/>','#modifyroleform #role_name',{tips:[3,'#293846']});
			return;
		}
		
		if(role_name.length >255){
			layer.tips('<spring:message code='rolelist.name.long'/>','#modifyroleform #role_name',{tips:[3,'#293846']});
			return;
		}
		
		if(company_id=="" || company_id==null){
			layer.msg("厂商不能为空");
			return;
		}

		var obj = CommnUtil.ajax("${ctx}/role/checkrolename.shtml",{role_name:role_name,role_id:role_id},"json");
		if(obj.status == 200){//角色名称没有重复
			
		    var role_key = $('#modifyroleform #role_key').val();
		    if(role_key.length < 1){
				layer.tips('<spring:message code='rolelist.key.not.empty'/>','#modifyroleform #role_key',{tips:[3,'#293846']});
				return;
			}
		    
		    if(role_key.length >85){
				layer.tips('<spring:message code='rolelist.key.long'/>','#modifyroleform #role_key',{tips:[3,'#293846']});
				return;
			}
		    
			var description = $('#modifyroleform #description').val();
			if(description.length >85){
				layer.tips('<spring:message code='rolelist.des.long'/>','#modifyroleform #description',{tips:[3,'#293846']});
				return;
			}
			
			var obj = CommnUtil.ajax($('#modifyroleform').attr("action"),$("#modifyroleform").serialize(),"json");	
			if(obj.status == 200){
				$("#modifyModal").modal('hide'); 
				$('#modifyroleform')[0].reset();
				pageTable.refreshPage();  
				layer.msg("<spring:message code='modify_success'/>");
	        }else{
		       	layer.msg("<spring:message code='modify_fail'/>");
	      	}
		}else{//重复
			layer.tips('<spring:message code='rolelist.name_repeat'/>','modifyroleform #role_name',{tips:[3,'#293846']});
			return;			
		}
			
	});
	
	//查询按钮
	$('#btn_query').click(function(){
		pageTable.refreshPage();  
	});
});



function changeRoleType(obj){
	var role_type = $(obj).val();
	if(role_type == 3){//厂商
		$('#add_div_company').show();
		$('#add_div_company_device_type').hide();
	}else if(role_type == 1){//超级管理员
		$('#add_div_company').hide();
		$('#add_div_company_device_type').hide();
	}else if(role_type == 2){//普通管理员
		$('#add_div_company_device_type').show();
		$('#add_div_company').hide();
	}
}
	
</script>