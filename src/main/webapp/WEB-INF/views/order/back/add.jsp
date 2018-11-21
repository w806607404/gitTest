<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/back/addBack.shtml">
		<input type="hidden" id="user_id" name="user_id" value="${user.id}"/>
		<input type="hidden" id="parent_id" name="parent_id" value="${user.parent_user_id}"/>
		<input type="hidden" id="status" name="status" value="0"/>	
				
		<div class="panel-body">
			<div class="form-group">
					<label class="col-sm-3 control-label">产品id</label>
				<div class="col-sm-8">
					<input type="text" placeholder="请填写产品id（必填）" class="form-control"   name="device_id" id="device_id" maxlength="32">
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">退货类型</label>
				<div class="col-sm-8">					
					<select id="back_type" name="back_type" tabindex="-1" class="form-control" ">
								<option value=' '>请选择</option>						    							
								<option value='0'>退货</option>
								<option value='1'>换货</option>								
							</select>
				</div>
			</div>
			<div class="form-group">
					<label class="col-sm-3 control-label">退换货原因</label>
				<div class="col-sm-8">
					<input type="text"  class="form-control"  name="cause" id="cause" maxlength="200">
				</div>
			</div>
						
						
		<div class="text-right col-sm-12">
		   <button type="button"  class="btn btn-primary btn-s-xs" onclick="doSubmit();"><spring:message code='font.framework.submit'/></button>
		</div>
	</form>
</div>
</div>
<script type="text/javascript"> 
	function doSubmit() {
		var device_id = $('#device_id').val();
		var back_type = $('#back_type').val();		
		
		if (device_id == "") {
			layer.msg("产品ID不能为空");
			return false;
		}
		
		if (device_id.length != 16) {
			layer.msg("产品id格式错误，请输入16位产品id");
			return false;
		}
		
		if (back_type == "") {
			layer.msg("退货类型不能为空");
			return false;
		}


		layer.confirm('请检查数据是否正确?', function(index) {
			var data = CommnUtil.ajax($("#addForm").attr("action"), $(
					"#addForm").serialize(), "json");
			if (data.status == 200) {
				parent.layer.msg("<spring:message code='success_operat'/>");
				parent.pageTable.refreshPage();
				parent.layer.close(parent.pageii);
			}else if (data.status == -101) {
				layer.msg("该产品尚未绑定不能退换");
				return false;
			}else if (data.status == -102) {
				layer.msg("该产品下级已绑定不能退换");
				return false;
			}else if (data.status == -103) {
				layer.msg("该产品已售出不能退换");
				return false;
			} else if (data.status == -104) {
				layer.msg("该产品已退货");
				return false;
			} else if (data.status == 500) {
				layer.msg("退换货操作异常");
				return false;
			} else {
				layer.msg("<spring:message code='operat_fail'/>");
			}
		});
	}
		
</script>	
