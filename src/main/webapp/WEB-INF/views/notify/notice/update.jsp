<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>
<link href="${ctxStatic}/js/plugins/uploadify/uploadify.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="${ctxStatic}/js/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${ctxStatic}/js/ueditor/ueditor.all.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${ctxStatic}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<!--  -->
<script type="text/javascript" src="${ctxStatic}/js/ueditor/ueditor.parse.js"></script>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="modal-body margin0">
    <form class="form-horizontal m-t" action="${ctx}/notify/notice/update.shtml" id="updateform">
        <input id = "id" name="id" value="${articleInfoBean.id}" type="hidden">
        <input id="content_content" value='${articleInfoBean.content}' type="hidden">
        <input id="state" name="state" value="${articleInfoBean.state}" type="hidden">
        <input id="detail_url" name="detail_url" value="${articleInfoBean.detail_url}" type="hidden">
	    <div class="form-group">
		      <label class="col-sm-2 control-label" for="app_key" style="text-align:center"><spring:message code='title.app.name'/></label>
		      <div class="col-sm-10">
		      	<select id="app_key" name="app_key" tabindex="-1"  class="form-control">
					<c:forEach items="${appInfoBeans}" var="appInfoBean">
						<option <c:if test="${appInfoBean.app_key eq articleInfoBean.app_key}">selected='selected' </c:if> value='${appInfoBean.app_key}'>${appInfoBean.app_name}</option>
					</c:forEach>
				</select>
			  </div>
	    </div>
	    <div class="hr-line-dashed"></div>
	    <div id="titletype">
		    <div class="form-group">
			      <label class="col-sm-2 control-label" for="article_type1" style="text-align:center"><spring:message code='title.article.type'/></label>
			      <div class="col-sm-10">
			      	<select id="article_type" name="article_type" tabindex="-1"  class="form-control">
			      	        <option value=''>-<spring:message code='choose'/>-</option>
							<option value='3' <c:if test="${articleInfoBean.article_type eq '3'}">selected='selected' </c:if>>产品介绍</option>
							<option value='4' <c:if test="${articleInfoBean.article_type eq '4'}">selected='selected' </c:if>>厂商介绍</option>
							<option value='6' <c:if test="${articleInfoBean.article_type eq '6'}">selected='selected' </c:if>>产品咨讯</option>
					</select>
				  </div>
		    </div>
		    <div class="hr-line-dashed"></div>
		    <div class="form-group">
				  <label class="col-sm-2 control-label" for="title" style="text-align:center"><spring:message code='title.title'/></label>
			      <div class="col-sm-10">
					  <input type="text" class="form-control" id="title" name="title" placeholder="" value="${articleInfoBean.title}"/>
				  </div>
		    </div>
		    <div class="hr-line-dashed"></div>
	    </div>
	    <div id = "input_img">
		    <div class="form-group">
			      <label class="col-sm-2 control-label" for="img" style="text-align:center"><spring:message code='title.img'/></label>
			      <div class="col-sm-10">
				      <input type="file" id="img" name="img">
			      </div>
			</div>
			<div id="img_img">
				<div class="form-group">
					  <label class="col-sm-2 control-label" for="img_" style="text-align:center"></label>
				      <div class="col-sm-10">
						  <img src="${articleInfoBean.thumb_url}" id="img_" style="height:120px;"/>
					  </div>
				</div>
				<div class="hr-line-dashed"></div>
			</div>
			<div class="form-group">
				  <label class="col-sm-2 control-label" for="thumb_url" style="text-align:center"><spring:message code='title.img.url'/></label>
			      <div class="col-sm-10">
					  <input type="text" class="form-control" id="thumb_url" name="thumb_url" placeholder="" value="${articleInfoBean.thumb_url}" readonly>
				  </div>
			</div>
			<div class="hr-line-dashed"></div>
		</div>
		  <label class="col-sm-2 control-label" for="content" style="text-align:center"><spring:message code='title.content'/></label>
	      <div class="col-sm-10" style="padding-left:0">
			  <textarea class="form-control" id="content" name="content" style="border:none"></textarea>
		  </div>
		<div class="modal-footer" style="margin-top:550px">
            <button type="button" class="btn btn-primary" id="updatebutton"><spring:message code='font.framework.save'/></button>
        </div>
	</form>
	</div>
</div>
</div>
<script>
    
	var ue = UE.getEditor('content', {
		toolbars : [ [  'bold', 'italic', 'underline', 'fontborder', '|', 
		                'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', '|',
			            'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
			            'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
			            'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
			            'simpleupload','|',
			            ] ],
		initialFrameHeight : 400,
		autoHeightEnabled : false,
		autoFloatEnabled : true,
		autoClearinitialContent : true
	});
	var content = $("#content_content").val();
	ue.addListener("ready", function (){
		ue.setContent(content);
	});
$(function(){
	$ScinanSelect2({id:'app_key',placeholder: "请选择APP_KEY"});
	
	$('#img').uploadify({
	   	'swf' : "${ctxStatic}/js/plugins/uploadify/uploadify.swf",
	   	'uploader' :  "${ctx}/upload/file/2.shtml",
	   	'buttonText' : '<spring:message code='upload'/>',
	   	'width' : 100,
	   	'height' : 35,
	   	'method' : 'post',
		'fileTypeExts' : '*.png;*.jpg;*.jpeg;*.gif',
	   	'fileObjName'   : 'file',
	   //	'sizeLimit': '1MB',  // 注意这里，这是文件大小。。。  
	   	
	   	'onUploadStart' : function(file) {
	   		
	    },
	    'onDialogOpen': function(){
		},
	   	'onUploadSuccess' : function(file, data, response) {
	   		if(data.status == 200){
	   			var url = data.linkUrl;
	   			$('#thumb_url').val(url);
	   			$('#img_').attr('src',url); 
	   			$('#img_img').show();
	   			layer.msg("<spring:message code='upload_success'/>");
	   		}else{
	   			layer.msg("<spring:message code='upload_faild'/>");
	   		}
	   	}
	   	});
	
	   
	   
	   /*修改*/
	   $("#updatebutton").click(function(){
		   var article_type = $('#article_type').val();
		   var title = $('#title').val();
		   var thumb_url = $('#thumb_url').val();
		   var content = UE.getEditor('content').getContent();
		   
		   if(article_type==""){
		    	layer.msg("请选文章类型");
		    	return false;
		   }
		   if(title==""){
		    	layer.msg("标题不能为空");
		    	return false;
		    }
		   if(thumb_url==""){
		    	layer.msg("缩略图图标地址不能为空");
		    	return false;
		    }
		   if(content==""){
		    	layer.msg("内容不能为空");
		    	return false;
		    }
		   
		   var data = CommnUtil.ajax($("#updateform").attr("action"),$("#updateform").serialize(),"json");
			if(data.status == 200){
				parent.layer.msg("<spring:message code='modify_success'/>");
				parent.pageTable.refreshPage();
				parent.layer.close(parent.pageii);
				clearCookie("page_type");
			}else{
				layer.msg("<spring:message code='modify_fail'/>");
			}
	   });
});

</script>