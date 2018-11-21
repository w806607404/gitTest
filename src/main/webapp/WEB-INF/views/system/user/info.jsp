<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="addForm" name="form" class="form-horizontal" method="post" action="${ctx}/user/update.shtml">
		<input type="hidden" id="id" name="id" value="${info.id}" />
		<input type="hidden" id="display" name="display" value="${display}" />
		<div class="panel-body" >
			<div class="form-group">
				<label class="col-sm-2 control-label">用户昵称</label>
				<div class="col-sm-3">
					<input type="text"  class="form-control" readonly="true" value="${info.user_nickname}" >
				</div>
				<label class="col-sm-3 control-label">帐号名称</label>
				<div class="col-sm-3">
					<input type="text" value="${info.user_name}"  class="form-control"  readonly="true" style="cursor:hand">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">角色类型</label>
				<div class="col-sm-3">
					<input type="text"  class="form-control"  name="role_name" value="${info.role_name}"  readonly="true" style="cursor:hand">
				</div>
				<label class="col-sm-3 control-label">真实姓名</label>
				<div class="col-sm-3">
					<input type="text"  class="form-control"  value="${info.agent_name}" readonly="true"  style="cursor:hand">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">联系电话</label>
				<div class="col-sm-3">
					<input type="text"  class="form-control" value="${info.agent_phone}" readonly="true" style="cursor:hand">
				</div>
				<label class="col-sm-3 control-label">用户类型</label>
				<div class="col-sm-3">
					<input type="text"  class="form-control"  value="${info.user_type == 1 ? '企业' : '个人'}" readonly="true"  style="cursor:hand">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">银行名称</label>
				<div class="col-sm-3">
					<input type="text" value="${info.bank_name}"  class="form-control" readonly="true" >
				</div>
				<label class="col-sm-3 control-label">银行卡号</label>
				<div class="col-sm-4">
					<input type="text" value="${info.bank_card}"  class="form-control"  readonly="true" style="cursor:hand">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">支付宝姓名</label>
				<div class="col-sm-3">
					<input type="text" value="${info.alipay_name}"  class="form-control" readonly="true" >
				</div>
				<label class="col-sm-3 control-label">支付宝账号</label>
				<div class="col-sm-3">
					<input type="text" value="${info.alipay_card}"  class="form-control"  readonly="true" style="cursor:hand">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">微信号</label>
				<div class="col-sm-3">
					<input type="text" value="${info.user_wechat}"  class="form-control" readonly="true" >
				</div>
				<label class="col-sm-3 control-label">邮箱</label>
				<div class="col-sm-3">
					<input type="text" value="${info.user_email}"  class="form-control"  readonly="true" style="cursor:hand">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">其他联系人姓名</label>
				<div class="col-sm-3">
					<input type="text" value="${info.other_user_name}"  class="form-control" readonly="true" >
				</div>
				<label class="col-sm-3 control-label">其他联系人联系方式</label>
				<div class="col-sm-3">
					<input type="text" value="${info.other_user_contact}"  class="form-control"  readonly="true" style="cursor:hand">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">分红金额</label>
				<div class="col-sm-3">
					<input type="text" value="${info.ratio_amount}"  class="form-control" readonly="true" >
				</div>
				<label class="col-sm-3 control-label">补贴余额</label>
				<div class="col-sm-3">
					<input type="text" value="${info.subsidy_amount}"  class="form-control"  readonly="true" style="cursor:hand">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">总设备数</label>
				<div class="col-sm-3">
					<input type="text" value="${info.totalDeviceNum}"  class="form-control" readonly="true" >
				</div>
				<label class="col-sm-3 control-label">总库存量</label>
				<div class="col-sm-3">
					<input type="text" value="${info.inventoryNum}"  class="form-control"  readonly="true" style="cursor:hand">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">总激活量</label>
				<div class="col-sm-3">
					<input type="text" value="${info.activedNum}"  class="form-control" readonly="true" >
				</div>
				<label class="col-sm-3 control-label">日激活量</label>
				<div class="col-sm-3">
					<input type="text" value="${info.activedNumDay}"  class="form-control"  readonly="true" style="cursor:hand">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">总销售量</label>
				<div class="col-sm-3">
					<input type="text" value="${info.soldNum}"  class="form-control" readonly="true" >
				</div>
				<label class="col-sm-3 control-label">日销售量</label>
				<div class="col-sm-3">
					<input type="text" value="${info.soldNumDay}"  class="form-control"  readonly="true" style="cursor:hand">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">所属区域</label>
				<div class="col-sm-5">
					<input type="text" value="${info.province_name}${info.city_name}${info.district_name}"  class="form-control" readonly="true" >
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">详细地址</label>
				<div class="col-sm-5">
					<input type="text" value="${info.user_address}"  class="form-control"  readonly="true" style="cursor:hand">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">证件照片</label>
				<div class="col-sm-8">
					<img id="img" style= "width:70%;hight:70%" alt="" src="${info.identity_img}">
				</div>
			</div>
			
		</div>
	</form>
</div>
</div>
<script type="text/javascript">
var flag = true,//状态true为正常的状态,false为放大的状态
           imgH,//图片的高度
           imgW,//图片的宽度
           img = document.getElementById("img");//图片元素
  img.onclick =  function(){
      //图片点击事件
       imgH = img.height; //获取图片的高度
       imgW = img.width; //获取图片的宽度
       if(flag){
           //图片为正常状态,设置图片宽高为现在宽高的2倍
           flag = false;//把状态设为放大状态
           img.height = imgH*2;
           img.width = imgW*2;
       }else{
           //图片为放大状态,设置图片宽高为现在宽高的二分之一
           flag = true;//把状态设为正常状态
           img.height = imgH/2;
           img.width = imgW/2;
       }
 
   }

</script>	
