<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/role/add.shtml">
		<input type="hidden" id="id" name="id" value="${id}"/>
		<div class="panel-body">
			<div class="form-group">
					<label class="col-sm-3 control-label">用户组名称</label>
				<div class="col-sm-8">
					<input type="text" placeholder="角色名称不能为空（必填）" class="form-control"  name="name" id="name" maxlength="32">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label">所属厂商</label>
				<div class="col-sm-8">
					<select id="company_id" name="company_id" tabindex="-1" class="form-control">
					    <option value=''>请选择</option>
						<option value='10D5'>广东伯央的度家居科技有限公司</option>
					</select>
					</div>
			</div>
			
		</div>
		<div class="text-right col-sm-12">
		   <button type="button"  class="btn btn-primary btn-s-xs" onclick="doSubmit();"><spring:message code='font.framework.submit'/></button>
		</div>
	</form>
</div>
</div>
<script type="text/javascript">
$ScinanSelect2({id:'company_id',placeholder: "请选择所属厂商"});

function doSubmit(){
	
    var name=$('#name').val();
    var company_id=$('#company_id').val();
    
    if(name==""){
    	layer.alert_("<spring:message code='message'/>","用户组名称不能为空","<spring:message code='font.framework.confirm'/>");
    	return false;
    }
    
    if(company_id==""){
    	layer.alert_("<spring:message code='message'/>","所属厂商不能为空","<spring:message code='font.framework.confirm'/>");
    	return false;
    }
   
     layer.confirm('请检查数据是否正确?', function(index) {
	    var data = CommnUtil.ajax($("#addForm").attr("action"),$("#addForm").serialize(),"json");
		if(data.status == 200){
			parent.layer.msg("<spring:message code='add_success'/>");
			parent.pageTable.refreshPage();
			parent.layer.close(parent.pageii);
		}else if(data.status==-1){
			layer.msg("角色名称已存在");
			return false;
		}
		else{
			layer.msg("<spring:message code='add_fail'/>");
		}
     });
}

</script>	
