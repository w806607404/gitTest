<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="/user/transfer.shtml">
		
		<div class="panel-body">
			<div class="form-group" style="color: red;width:70%;margin-left: 20%;">
					<p style="color: red;">提示：只有关闭状态的账号，并且迁移账号和被迁移账号的退货、通知发货、购货账单都是完成状态，方可进行迁移
					</br>请谨慎操作，一旦迁移数据便不可恢复
					</p>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">迁移用户</label>
				<div class="col-sm-8">
					<select name="p_user_id" data-placeholder="请选择需要迁移的用户"  id="p_user_id" style="height: 40px; width: 570px; background: #EEEEEE;" onchange="getSon(this.value);">  
					    <option value ="">--<spring:message code='choose'/>--</option>
					    <c:forEach items="${list}" var="list" varStatus="roleList" >
                            <option value ="${list.id}">${list.user_name}</option>
                        </c:forEach>
 				     </select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">迁移到</label>
				<div class="col-sm-8">
					 <select name="n_user_id" data-placeholder="请选择要迁移到哪个用户"  id="n_user_id" style="height: 40px; width: 570px; background: #EEEEEE;">  
					    <option value ="">--<spring:message code='choose'/>--</option>
 				     </select>
				</div>
			</div>
		</div>
		<div class="text-right col-sm-12" align="center">
		   <input type="button" id="importButton"name="importButton" value="一键迁移" class="btn btn-primary marR10" onclick="transferSubmit();" /> &nbsp;&nbsp;
		</div>
	</form>
</div>
</div>
<script type="text/javascript">
$ScinanSelect2({id:'p_user_id',placeholder: "请选择需要迁移的用户"});
$ScinanSelect2({id:'n_user_id',placeholder: "请选择要迁移到哪个用户"});


function transferSubmit() {
	var p_user_id = $("#p_user_id").val();
	var n_user_id = $("#n_user_id").val();
	
	if (null == p_user_id || p_user_id == "") {
		layer.alert('迁移用户不能为空!', 3);
		return;
	}
	
	if (null == n_user_id || n_user_id == "") {
		layer.alert('当前用户不能为空!', 3);
		return;
	}
	
	if (p_user_id == n_user_id) {
		layer.alert('迁移用户不能与当前用户相同!', 3);
		return;
	}

	layer.confirm('请检查迁移的用户是否正确?', function(index) {
		var url = '${ctx}/user/transfer.shtml';
		var s = CommnUtil.ajax(url, {p_user_id:p_user_id,n_user_id:n_user_id}, "json");
		if (s.status == 200) {
			layer.msg('一键迁移成功,2分钟后生效!');
			return;
		} else if (s.status == 101) {
			layer.msg('迁移账号或者被迁移账号仍有退货账单未完成！');
			return;
		} else if (s.status == 102) {
			layer.msg('迁移账号或者被迁移账号仍有通知发货账单未完成！');
			return;
		} else if (s.status == 103) {
			layer.msg('迁移账号或者被迁移账号仍有购货账单未完成！');
			return;
		} else if (s.status == 104) {
			layer.msg('迁移账号或者被迁移账号仍有结算账单未完成！');
			return;
		} else{
			layer.msg('一键迁移失败!');
			return;
		}
     });
}

function getSon (val){
	var son_html = "<option value =''>--<spring:message code='font.framework.please.select'/>--</option>";
	if(val == "" || val == null){
		$('#n_user_id').empty();
		$('#n_user_id').append(son_html);
	}else{
	var json = CommnUtil.ajax("${ctx}/user/getSonList.shtml",{parent_id:val},"json");
	console.log(json);
	if(json != null){
		for(var i = 0 ;i<json.length ; i++){
			son_html += "<option value='"+json[i].id+"'>"+json[i].user_name+"</option>";	
			console.log(son_html);
		}
	}
	$('#n_user_id').empty();
	$('#n_user_id').append(son_html);
  }
	
}
</script>	
