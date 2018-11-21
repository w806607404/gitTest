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
    <form class="form-horizontal m-t" action="${ctx}/notify/notice/add.shtml" id="addform">
        <input id="company_id" name="company_id" value="${company_id}" type="hidden"/>
	    <div class="form-group">
		      <label class="col-sm-2 control-label" for="app_key" style="text-align:center"><spring:message code='title.app.name'/></label>
		      <div class="col-sm-10">
		      	<select id="app_key" name="app_key" tabindex="-1"  class="form-control">
					<c:forEach items="${appInfoBeans}" var="appInfoBean">
						<option value='${appInfoBean.app_key}'>${appInfoBean.app_name}</option>
					</c:forEach>
				</select>
			  </div>
	    </div>
	    <div class="hr-line-dashed"></div>
	    <div id="titletype">
		    <div class="form-group">
			      <label class="col-sm-2 control-label" for="article_type" style="text-align:center"><spring:message code='title.article.type'/></label>
			      <div class="col-sm-10">
			      	<select id="article_type" name="article_type" tabindex="-1"  class="form-control"  onchange="count(this.value);">
			      	        <option value=''>-<spring:message code='choose'/>-</option>
							<option value='4'>公司介绍</option>
							<option value='3'>首页广告</option>
							<option value='6'>通知公告</option>
					</select>
				  </div>
		    </div>
		    <div class="hr-line-dashed"></div>
		    <div class="form-group">
				  <label class="col-sm-2 control-label" for="title" style="text-align:center"><spring:message code='title.title'/></label>
			      <div class="col-sm-10">
					  <input type="text" class="form-control" id="title" name="title" placeholder=""/>
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
					  <img src="" id="img_" style="height:120px;"/>
				  </div>
			</div>
			<div class="hr-line-dashed"></div>
			</div>
			<div class="form-group">
				  <label class="col-sm-2 control-label" for="thumb_url" style="text-align:center"><spring:message code='title.img.url'/></label>
			      <div class="col-sm-10">
					  <input type="text" class="form-control" id="thumb_url" name="thumb_url" placeholder="" readonly>
				  </div>
			</div>
			<div class="hr-line-dashed"></div>
		</div>
		  <label class="col-sm-2 control-label" for="content" style="text-align:center"><spring:message code='title.content'/></label>
	      <div class="col-sm-10" style="padding-left:0">
			  <textarea class="form-control" id="content" name="content" style="border:none"></textarea>
		  </div>
		<div class="modal-footer" style="margin-top:550px">
            <button type="button" class="btn btn-primary" id="addbutton"><spring:message code='font.framework.save'/></button>
        </div>
	</form>
	</div>
</div>
</div>
<script>

$ScinanSelect2({id:'article_type',placeholder: "请选择文章类型"});
$ScinanSelect2({id:'app_key',placeholder: "请选择APP_KEY"});

$(function(){
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
	
	   var eee;
	   var v;
	   $('#img').uploadify({
	   	'swf' : "${ctxStatic}/js/plugins/uploadify/uploadify.swf",
	   	'uploader' :  "${ctx}/upload/file/2.shtml",
	   	'buttonText' : '<spring:message code='upload'/>',
	   	'width' : 100,
	   	'height' : 35,
	   	'auto':true,
	   	'method' : 'post',
		'fileTypeExts' : '*.png;*.jpg;*.jpeg;*.gif',
	   	'fileObjName'   : 'file',
	   	'sizeLimit': 204800000,  // 注意这里，这是文件大小。。。  
	   	
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
	   
	   /*添加*/
	   $("#addbutton").click(function(){
		   
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
		  
		   var data = CommnUtil.ajax($("#addform").attr("action"),$("#addform").serialize(),"json");
			if(data.status == 200){
				parent.layer.msg("<spring:message code='add_success'/>");
				parent.pageTable.refreshPage();
				parent.layer.close(parent.pageii);
				clearCookie("page_type");
			}else{
				layer.msg("<spring:message code='add_fail'/>");
			}
	   });
});


function count(val) {
	if(val != 6){
		var app_key = $('#app_key').val();
		var obj = CommnUtil.ajax("${ctx}/notify/notice/lockCount.shtml", {
			type : val,
			app_key:app_key
		}, "json");
		console.log(obj);
		if (obj.status == -101) {
			layer.msg("公司介绍只允许添加一条");
			return;
		}
		if (obj.status == -102) {
			layer.msg("首页广告只允许添加三条");
			return;
		}
	}
}

</script>