<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/page_resources.jsp"%>

<script>
//标记  0 新增菜单  1 更新菜单
var save_type = '0';
//父菜单名称
var parent_text = '';
//父菜单id
var parent_id = '';
//菜单类型 0.目录 1.菜单 2.按钮
var type = '';
//菜单链接
var href = '';
//子菜单id
var c_id = '';
//菜单排序
var sort = '';
//子菜单名称
var text = '';
//菜单描述
var desc = '';
//菜单图标
var icon = '';
//菜单状态
var state = '';
//菜单英文名称
var name_en = '';
//按钮ID
var name_ext1 = '';

$(function() {
	//菜单json
	var e = JSON.parse('${json}');
    
	var $searchableTree = $("#treeview").treeview({
        color: "#428bca",
        levels: 1,
        data: e,
        onNodeSelected: function(e, o) {//选中某个菜单触发事件
        	save_type = '1';
        	$('#saveInfo').text("更新内容");
        	
            parent_id = o.parent_id;
            type = o.res_type;
            href = o.href;
            c_id = o.id;
            sort = o.sort_num;
            text = o.text;
            desc = o.description;
            icon = o.icon;
            state = o.menu_status;
            name_en = o.name_en;
            name_ext1 = o.name_ext1;
            res_key = o.res_key;
            
            //表单赋值
            $('#menu_name').val(text);
            
            $('#menu_name_en').val(name_en);
            
            $('#description').val(desc);

            $('#sort_num').val((sort == '' || sort == 'undefined' || typeof(sort) == undefined) ?0:sort);
            
            $('#res_url').val(href);
            
            $('#icon').val(icon);
            
           	$('#res_type').val(type);
           	
           	$('#state').val(state);
           	
           	$('#name_ext1').val(name_ext1);
           	
           	$('#res_key').val(res_key);
           	
        	$('#res_url').attr('disabled',false);
           	
           	$('#res_type').attr('disabled',true);
           	
           	
           	if(type == '2'){//按钮
           		$('#h3_title').text(text + "  按钮-编辑：");
        		$('#label_name').text("中文按钮名称：");
        		$('#label_name_en').text("按钮Id：");
        		$('#label_parent_menu').text("所属菜单");
        		$('#div_menu_state').show();
        		$('#btn_id_div').show();
        		$('#label_menu_state').text("是否模态");
        		$('#form-group_menu_icon').hide();
        	}else{ // 0 目录    1 菜单
        		$('#div_menu_state').hide();
        		$('#label_name').text("中文菜单名称：");
        		$('#label_name_en').text("按钮Id：");
        		$('#label_parent_menu').text("父菜单：");
        		if(type == '0'){  //目录
        			$('#h3_title').text(text + "  目录-编辑：");
        			$('#form-group_menu_icon').show();
        			$('#res_url').attr('disabled',true);
        			$('#form-group_res_url').hide();
        			$('#form-group_parent_menu').hide();
        		}else{//菜单
        			$('#h3_title').text(text + "  菜单-编辑：");
        			$('#form-group_menu_icon').hide();
        			$('#form-group_res_url').show();
        			$('#form-group_parent_menu').show();
        		}
        	}
            
           	//获取父菜单json数据
            var results = $searchableTree.treeview('getParent', o);
            //是否是顶级菜单 String:不是顶级菜单  func是顶级菜单
            if(typeof(results.text) == 'string'){
            	parent_text = results.text;
            	$('#p_menu_name').attr('disabled',true);
            	$('#p_menu_name').val(parent_text);
            }else{
            	parent_text = text;
            	$('#p_menu_name').val("");
            	$('#p_menu_name').attr('disabled',true);
            }
        }
    });
    
    //添加菜单
    $('#btn_add').click(function(){
    	//设置标记为新增状态
    	save_type = '0';
    	$('#saveInfo').text("保存内容");
		if(type == ''){//type为空新增顶级目录
			$('#div_menu_state').hide();
			$('#h3_title').text("新增-目录");
			$('#res_type').val(0);
			$('#res_type').attr('disabled',true);
			$('#p_menu_name').attr('disabled',true);
			$('#res_url').attr('disabled',true);
			$('#form-group_parent_menu').hide();
		}else{
			$('#menu_name').val("");
			$('#menu_name_en').val("");
			$('#sort_num').val("");
			$('#description').val("");
			$('#state').val(1);
			$('#res_url').val("");
			$('#res_key').val("");
			$('#form-group_menu_icon').hide();

			if(type == '0'){//新增菜单
				$('#div_menu_state').hide();
				$('#h3_title').text(parent_text + "-新增下级菜单");
				$('#res_type').val(1);
				$('#p_menu_name').val(parent_text);
				
				$('#res_type').attr('disabled',true);
				$('#p_menu_name').attr('disabled',true);
				$('#res_url').attr('disabled',false);
				
				$('#form-group_parent_menu').show();
				$('#form-group_res_url').show();
				
			}else if(type == '1'){//新增按钮
				$('#div_menu_state').show();
				$('#h3_title').text(text + "菜单-新增按钮");
				$('#p_menu_name').val(text);
				$('#res_type').val(2);
				//$('#label_name').text("中文按钮名称：");
        		//$('#label_name_en').text("英文按钮名称：");
				$('#label_parent_menu').text("所属菜单");
				$('#label_menu_state').text("是否模态");
				$('#btn_id_div').show();
				
			}else if(type == '2'){
				alert("按钮不可以创建下级菜单");
				return;
			}
		}
    });
    
    //删除菜单
    $('#btn_delete').click(function(){
    	if(c_id == ''){
    		alert("请先选中树状菜单");
    		return;
    	}
    	
    	//确认框
		$ScinanSwal.confirm({
			title: "<spring:message code='confirm_delete'/>",
	        text: "您所选中的目录及该目录下所有子目录将全部被删除，并且删除后将无法恢复，请谨慎操作！",
	        confirmButtonText: "<spring:message code='font.framework.delete'/>"
		},function(){
			var obj = CommnUtil.ajax("${ctx}/resources/delete.shtml",{id:c_id},"json");
    		if(obj.status == 200){
           	 	$ScinanSwal.alert("<spring:message code='delete_success'/>", "<spring:message code='delete_success_prompt'/>", "success");
           	 	$('#addResourcesform')[0].reset();
				location.reload();
           	}else{
          	 	layer.msg("<spring:message code='delete_fail'/>");
           	}	
		});
    	
    	
    });
    
    //刷新菜单
    $('#btn_refresh').click(function(){
    	$('#addResourcesform')[0].reset();
		location.reload();
    });
    
	//保存或更新菜单信息    
    $('#saveInfo').click(function(){
    	
    	var json = {};
		json.name_cn = $('#menu_name').val();
		json.name_en = $('#menu_name_en').val();
		json.sort_num = $('#sort_num').val();
		json.icon = $('#icon').val();
		json.res_url = $('#res_url').val();
		json.state = $('#state').val();
		json.description = $('#description').val();
		json.name_ext1=$('#name_ext1').val();
		json.res_key=$('#res_key').val();
		json.res_type = $('#res_type').val();
		
		if(json.name_cn==""){
			layer.alert_("<spring:message code='message'/>","中文菜单名称不能为空!","<spring:message code='font.framework.confirm'/>");
			return false;
		}
		
		if(json.res_key==""){
			layer.alert_("<spring:message code='message'/>","国际化Key不能为空!","<spring:message code='font.framework.confirm'/>");
			return false;
		}
		
		//按钮状态下，校验按钮ID是法合法
		if(json.res_type=="2"){
			
			if(json.name_ext1==""){
		    	layer.alert_("<spring:message code='message'/>","按钮ID不能为空!", "<spring:message code='font.framework.confirm'/>");
		    	return false;
		    }else{
		    	var r = /^[a-zA-Z_]{4,16}$/; //字母和下线画
		        if(!r.test(json.name_ext1)){
		        	layer.alert_("<spring:message code='message'/>","按钮Id只能由字母和下线画组成!", "<spring:message code='font.framework.confirm'/>");
		        	return false;
		        }
		    }
		}
		
		
	    if(json.sort_num==""){
	    	layer.alert_("<spring:message code='message'/>","序号不能为空!", "<spring:message code='font.framework.confirm'/>");
	    	return false;
	    }else{
	    	var r = /^\+?[0-9][0-9]*$/; //正整数
	        if(!r.test(json.sort_num)){
	        	layer.alert_("<spring:message code='message'/>","序号必须为数字!", "<spring:message code='font.framework.confirm'/>");
	        	return false;
	        }
	    }
	    
	    
	    //增加菜单时，请求URL不能为空
	    if(json.res_type=="1"){
	    	if(json.res_url==""){
	    		layer.alert_("<spring:message code='message'/>","URL请求不能为空!", "<spring:message code='font.framework.confirm'/>");
		    	return false;
	    	}
	    }
		
    	if(save_type == '0'){
    		json.res_type = $('#res_type').val();
   			json.parent_id = c_id;
   			var obj = CommnUtil.ajax("${ctx}/resources/add.shtml",json,"json");
    	}else{
			json.id = c_id;
			var obj = CommnUtil.ajax("${ctx}/resources/update.shtml",json,"json");
    	}
    	
    	if(obj.status == 200){
			alert("操作成功");
			$('#addResourcesform')[0].reset();
			location.reload();
		}
    });
    
});

//菜单类型选择
function changeSelect(obj){
	var res_type = $(obj).val();
	if(res_type == '2'){
		$('#label_name').text("中文按钮名称：");
		$('#label_name_en').text("英文按钮名称：");
		$('#label_parent_menu').text("所属菜单");
		$('#form-group_menu_icon').hide();
		$('#btn_id_div').show();
	}else{
		$('#label_name').text("中文菜单名称：");
		$('#label_name_en').text("英文菜单名称：");
		$('#label_parent_menu').text("父菜单：");
		$('#form-group_menu_icon').show();
	}
}

</script>
<div class="row wrapper wrapper-content animated fadeInRight">
<div class="panel-body" style="padding-bottom:0px;">
	<!-- 增删 begin -->
	<div style="padding-left:15px;">
	
		<button type="button" class="btn btn-default" data-toggle="modal" id="btn_refresh">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> <spring:message code='refrush.menu'/>
		</button>
	
		<button id="btn_add" type="button" class="btn btn-default">
		 	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span><spring:message code='font.framework.add'/>
		</button>

		<button type="button" class="btn btn-default" data-toggle="modal" id="btn_delete">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span><spring:message code='font.framework.delete'/>
		</button>
	
	</div>
	<!-- 增删 end -->

	<div style="padding-top:10px;">
        <div class="col-sm-6">
            <div id="treeview" class="test"></div>
        </div>
        <div class="col-sm-6">
        
            <h3 id="h3_title">新增-目录：</h3>
            <hr>
            
            <form class="form-horizontal m-t" id="addResourcesform" action="${ctx}/resources/add.shtml">
                <div class="form-group">
                    <label class="col-sm-3 control-label">类型：</label>
                    <div class="col-sm-8">
                       		<select id="res_type" name="res_type" class="form-control" onchange="changeSelect(this);" disabled="disabled"> 
                         	  	<option value="0">目录</option> 
								<option value="1">菜单</option> 
							 	<option value="2">按钮</option> 
							</select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" id="label_name"> 中文菜单名称：</label>
                    <div class="col-sm-8">
                        <input id="menu_name" type="text" class="form-control" name="menu_name">
                    </div>
                </div>
                
                <!--<div class="form-group">
                    <label class="col-sm-3 control-label" id="label_name_en"> 英文菜单名称：</label>
                    <div class="col-sm-8">
                        <input id="menu_name_en" type="text" class="form-control" name="menu_name_en">
                    </div>
                </div>-->
                
                <div class="form-group">
                    <label class="col-sm-3 control-label" id="label_res_key"> 国际化Key：</label>
                    <div class="col-sm-8">
                        <input id="res_key" type="text" class="form-control" name="res_key" maxlength="64">
                    </div>
                </div>
                
                <div class="form-group" style="display:none" id="btn_id_div">
                    <label class="col-sm-3 control-label" id="label_name_en"> 按钮id：</label>
                    <div class="col-sm-8">
                        <input id="name_ext1" type="text" class="form-control" name="name_ext1" maxlength="32">
                    </div>
                </div>
                
                <div class="form-group" style="display: none;" id="form-group_parent_menu">
                    <label class="col-sm-3 control-label" id="label_parent_menu">父菜单：</label>
                    <div class="col-sm-8">
                        <input id="p_menu_name" type="text" class="form-control" name="p_menu_name" disabled="disabled">
                    </div>
                </div>
                
                 <div class="form-group">
                    <label class="col-sm-3 control-label">序号：</label>
                    <div class="col-sm-8">
                        <input id="sort_num" type="text" class="form-control" name="sort_num" maxlength="8">
                    </div>
                </div>
                
                <div class="form-group" style="display:none;" id="form-group_res_url">
                          <label class="col-sm-3 control-label">URL请求：</label>
                          <div class="col-sm-8">
                             <input id="res_url" type="text" class="form-control" name="res_url"  maxlength="250">
                          </div>
                      </div>
                      
                       <div class="form-group" id="form-group_menu_icon">
                          <label class="col-sm-3 control-label">菜单图标：</label>
                          <div class="col-sm-8">
                             <input id="icon" type="text" class="form-control" name="icon"  maxlength="100">
                          </div>
                      </div>
                      
                      
                       <div class="form-group" id="div_menu_state">
                          <label id="label_menu_state" class="col-sm-3 control-label">状态：</label>
                          <div class="col-sm-8">
                             <select id="state" name="state" class="form-control"> 
								      <option value="1">否</option> 
								      <option value="2">是</option> 
							   </select>
                          </div>
                      </div>
                      
                          <div class="form-group">
                          <label class="col-sm-3 control-label">备注：</label>
                          <div class="col-sm-8">
                             <textarea id="description" class="form-control" name="description" maxlength="254"></textarea>
                          </div>
                      </div>
            </form>
            <div class="col-sm-6 col-sm-offset-9">
					<button id="saveInfo" class="btn btn-primary" type="button">保存内容</button>
			</div>
              </div>
              <div class="clearfix"></div>
          </div>
</div>
</div>