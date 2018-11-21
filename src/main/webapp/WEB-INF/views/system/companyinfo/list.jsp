<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body m-t-n" style="padding-bottom:0px">

 <div class="modal-body margin0">
    <form class="form-horizontal m-t" action="${ctx}/user/updateUserBasicInfo.shtml" id="changeform">
        	<input id="id" value="${userBasicInfo.user_id}" name="id" type="hidden">
        	<input id="logo_url" name="logo_url" type="hidden" value="${userBasicInfo.logo_url}">
        	<input id="wx_qr_code" name="wx_qr_code" type="hidden" value="${userBasicInfo.wx_qr_code}">
        	<c:if test="${not empty my_current_money}">
		        <div class="form-group">
		              <label class="col-sm-2 control-label" for="name" style="text-align:center">&nbsp;</label>
				      <div class="col-sm-10">
						  <font color="red"></font><STRONG>当前经销商的分成比例为<font color="red">${current_ratio_str}%</font>，如果订单总金额100元，那么自留分成金额为<font color="red">${my_current_money}</font>元。</STRONG>
					  </div>
			    </div>
	    </c:if>
	    <div class="form-group">
		      <label class="col-sm-2 control-label" for="name" style="text-align:center">公司名称</label>
		      <div class="col-sm-10">
				  <input type="text" class="form-control" id="name" name="name" value="${userBasicInfo.name}" placeholder="公司名称不能为空，<spring:message code='companyinfo.name.zh.style'/>" maxlength="20">
			  </div>
	    </div>
	    <div class="hr-line-dashed"></div>
	    <div class="form-group">
			  <label class="col-sm-2 control-label" for="company_info" style="text-align:center"><spring:message code='companyinfo.desc'/></label>
		      <div class="col-sm-10">
				  <textarea class="form-control" id="company_info" name="company_info" placeholder="<spring:message code='companyinfo.desc.style'/>" maxlength="400">${userBasicInfo.company_info}</textarea>
			  </div>
		</div>
		<div class="hr-line-dashed"></div>
		<div class="form-group">
		      <label class="col-sm-2 control-label" for="img" style="text-align:center"><spring:message code='companyinfo.up.logo'/></label>
		      <div class="col-sm-10">
			      <input type="file" id="img" name="img">
			      <p class="help-block"><spring:message code='companyinfo.logo.style'/></p>
		      </div>
		</div>
		<div class="hr-line-dashed"></div>
		<c:choose>
       		<c:when test="${empty userBasicInfo}">
	       		<div style="display:none" id="hidden_img">
				 <div class="form-group">
				  <label class="col-sm-2 control-label" for="img-icon" style="text-align:center">logo图片</label>
			      <div class="col-sm-10">
		       	      <img src="" id="img-icon" style="height:120px;"/>
	     		  </div>
				 </div>
				 <div class="hr-line-dashed"></div>
				</div>
	       	</c:when>
       		<c:otherwise>
	       		 <div class="form-group">
				  <label class="col-sm-2 control-label" for="img-icon" style="text-align:center">logo图片</label>
			      <div class="col-sm-10">
		       	      <img src="${userBasicInfo.logo_url}" id="img-icon" style="height:120px;"/>
	     		  </div>
				 </div>
				 <div class="hr-line-dashed"></div>
       		</c:otherwise>
       	</c:choose>
		<div class="form-group">
			  <label class="col-sm-2 control-label" for="company_website" style="text-align:center"><spring:message code='companyinfo.web.address'/></label>
		      <div class="col-sm-10">
				  <input type="text" class="form-control" id="company_website" name="company_website" value="${userBasicInfo.company_website}" placeholder="<spring:message code='companyinfo.web.address.style'/>" maxlength="80">
			  </div>
		</div>
		<div class="hr-line-dashed"></div>
		<div class="form-group">
			  <label class="col-sm-2 control-label" for="wx_account" style="text-align:center">联系人</label>
		      <div class="col-sm-10">
				  <input type="text" class="form-control" id="link_name" name="link_name" value="${userBasicInfo.link_name}" placeholder="请输入联系人" maxlength="32">
			  </div>
		</div>
		<div class="hr-line-dashed"></div>
		<div class="form-group">
			  <label class="col-sm-2 control-label" for="customer_phone" style="text-align:center">联系电话</label>
		      <div class="col-sm-10">
				  <input type="text" class="form-control"  id="phone" name="phone" value="${userBasicInfo.phone}" placeholder="请输入联系电话" maxlength="16">
			  </div>
		</div>
		
		<div class="modal-footer">
            <button type="button" class="btn btn-primary" id="changebutton"><spring:message code='font.framework.save'/></button>
        </div>
    </form>
  </div>
</div>
</div>
<script>
$(function(){
	   var eee;
	   var v;
	   $('#img').uploadify({
	   	'swf' : "${ctxStatic}/js/plugins/uploadify/uploadify.swf",
	   	'uploader' :  "${ctx}/upload/file/2.shtml",
	   	'buttonText' : '<spring:message code='upload'/>',
	   	'width' : 100,
	   	'height' : 35,
	   	'method' : 'post',
		'fileTypeExts' : '*.png;*.jpg;*.jpeg;*.gif',
	   	'fileObjName'   : 'file',
	   	'sizeLimit': '1MB',  // 注意这里，这是文件大小。。。  
	   	
	   	'onUploadStart' : function(file) {
	   	alert(1);
	   		
	    },
	    'onDialogOpen': function(){
		},
	   	'onUploadSuccess' : function(file, data, response) {
	   		if(data.status == 200){
	   			var url = data.linkUrl;
	   			$('#logo_url').val(url);
	   			$('#img-icon').attr('src',url); 
	   			$('#hidden_img').show();
	   			layer.msg("<spring:message code='upload_success'/>");
	   		}else{
	   			layer.msg("<spring:message code='upload_faild'/>");
	   		}
	   	}
	   	});
	   
	   
	   $('#wx_qr_url').uploadify({
		   	'swf' : "${ctxStatic}/js/plugins/uploadify/uploadify.swf",
		   	'uploader' :  "${ctx}/upload/file/2.shtml",
		   	'buttonText' : '<spring:message code='upload'/>',
		   	'width' : 100,
		   	'height' : 35,
		   	'method' : 'post',
			'fileTypeExts' : '*.png;*.jpg;*.jpeg;*.gif',
		   	'fileObjName'   : 'file',
		   	'sizeLimit': '1MB',  // 注意这里，这是文件大小。。。  
		   	
		   	'onUploadStart' : function(file) {
		   		
		    },
		    'onDialogOpen': function(){
			},
		   	'onUploadSuccess' : function(file, data, response) {
		   		if(data.status == 200){
		   			var url = data.linkUrl;
		   			$('#wx_qr_code').val(url);
		   			$('#img-icon1').attr('src',url); 
		   			$('#hidden_img1').show();
		   			layer.msg("<spring:message code='upload_success'/>");
		   		}else{
		   			layer.msg("<spring:message code='upload_faild'/>");
		   		}
		   	}
		   	});
	   
	   $('#changebutton').click(function(){
		   var name = $("#name").val();
		   if(name == ''){
			   layer.alert_("<spring:message code='message'/>","公司名称不能为空","<spring:message code='font.framework.confirm'/>");
			   return;
		   }
		   var company_info = $("#company_info").val();
		   if(company_info == ''){
			   layer.alert_("<spring:message code='message'/>","<spring:message code='companyinfo.desc.empty'/>","<spring:message code='font.framework.confirm'/>");
			   return;
		   }
		   var logo_img = $('#logo_url').val();
		   if(logo_img == ''){
			   layer.alert_("<spring:message code='message'/>","<spring:message code='companyinfo.logo.empty'/>","<spring:message code='font.framework.confirm'/>");
			   return;
		   }
		   var reg = /(http|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;
		   var company_website = $.trim($("#company_website").val());
		   if(company_website == ''){
			   layer.alert_("<spring:message code='message'/>","<spring:message code='companyinfo.web.empty'/>","<spring:message code='font.framework.confirm'/>");
			   return;
		   }
		   if(!reg.test(company_website)){
			   layer.alert_("<spring:message code='message'/>","<spring:message code='companyinfo.web.err'/>","<spring:message code='font.framework.confirm'/>");
				return;
			}
		   
		   var link_name = $('#link_name').val();
		   if(link_name == ''){
			   layer.alert_("<spring:message code='message'/>","联系人不能为空","<spring:message code='font.framework.confirm'/>");
			   return;
		   }
		   
		   var phone = $('#phone').val();
		   if(phone == ''){
			   layer.alert_("<spring:message code='message'/>","联系号码或坐机号码不能为空","<spring:message code='font.framework.confirm'/>");
			   return;
		   }else{
			   var r = /^\+?[0-9][0-9]*$/; //正整数
			   var f = /^\d[\d\-]*$/;
		        if(!f.test(phone)){
		        	layer.msg('联系号码或坐机号码必须合法！');
		        	return false;
		        }
		   }
		   
		  
		   var id = $("#id").val();
		   layer.confirm('请检查数据是否正确?', function(index) {
			   var obj = CommnUtil.ajax($("#changeform").attr("action"),$("#changeform").serialize(),"json");
			   if(obj.status == 200){
					layer.msg("设置成功!");
					top.location.href = "${ctx}/index.shtml";
				}else{
					layer.msg("设置失败!");
				}
		   });
	   });
	  
});
   
</script>