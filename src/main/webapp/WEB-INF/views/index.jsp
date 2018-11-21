<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/initTag.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title><spring:message code='font.framework.title'/></title>
	<script src="${ctxStatic}/js/plugins/pace/pace.min.js" type="text/javascript"></script>
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
</head>
<style type="text/css">
    #winpop {
        width:240px; height:120px;
        position:absolute; right:0; bottom:0;
        border:1px solid #999999; margin:0; padding:1px;
        overflow:hidden; display:none; background:#FFFFFF
    }
    #winpop .title {
        width:100%; height:20px;
        line-height:20px; background: orangered;
        font-weight:bold; text-align:center;
        font-size:12px;
    }
    #winpop .con {
        width:100%; height:600px;
        line-height:80px; font-weight:bold;
        font-size:12px; color:#000;
        text-decoration:underline; text-align:center;
        word-wrap:break-word;
        word-break:break-all;
    }
    #winpop .button {
        width:100%;
        height: 20px;
    }
    #silu {
        font-size:13px; color:#999999;
        position:absolute; right:0;
        text-align:right; text-decoration:underline;
        line-height:22px;
    }
    .close {
        position:absolute; right:4px; top:-1px;
        color:#FFFFFF; cursor:pointer
    }
</style>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                        	<span class="logo">
                        	<c:choose>
                        		<c:when test="${companyInfoBean.logo_url==''}">
                        			<img class="logo_img" src="${ctxStatic}/img/vitalong.png" id="logo-img">
                        		</c:when>
                        		<c:otherwise>
                        			<img class="logo_img" src="${companyInfoBean.logo_url}" id="logo-img">
                        		</c:otherwise>
                        	</c:choose>
                        	</span>
                        </div>
                        <div class="logo-element">
							<img class="yun-logo" src="${ctxStatic}/img/yun-logo.png">
                        </div>
                    </li>
                    
                    <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label"><spring:message code='font.framework.homepage'/></span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <span class="sec-label glyphicon glyphicon-chevron-right "></span>
                                <a class="J_menuItem" href="javascript:;"  data-res="${ctx}/indexWelcome.shtml" data-index="0" ><spring:message code='menu.manage.map.distribution'/></a>
                            </li>
                        </ul>

                    </li>
                    
                    <c:choose>
                    	<c:when test="${language == 'en'}">
                    		
                    		<c:forEach items="${lists}" var="menu" varStatus="idxStatus">
			                    <li>
			                        <a href="javascript:;">
			                            <i class="${menu.icon}"></i>
			                            <span class="nav-label">${menu.res_name}</span>
			                            <span class="fa arrow"></span>
			                        </a>
			                        <ul class="nav nav-second-level">
			                        	<c:forEach items="${menu.nodes}" var="menu2" varStatus="idxStatus">
			                            <li>
			                                <a class="J_menuItem" id="${menu2.res_id}" href="javascript:;" data-res="${ctx}${menu2.res_url}?id=${menu2.res_id}">
			                                <span class="sec-label glyphicon glyphicon-chevron-right "></span>
			                                ${menu2.res_name}
			                                </a>
			                            </li>
			                            </c:forEach>	
			                        </ul>
			                    </li>
		                    </c:forEach>
                    	</c:when>
                    	<c:otherwise>
                    		<c:forEach items="${lists}" var="menu" varStatus="idxStatus">
			                    <li>
			                        <a href="javascript:;">
			                            <i class="${menu.icon}"></i>
			                            <span class="nav-label">${menu.res_name}</span>
			                            <span class="fa arrow"></span>
			                        </a>
			                        <ul class="nav nav-second-level">
			                        	<c:forEach items="${menu.nodes}" var="menu2" varStatus="idxStatus">
			                            <li>
			                                <a class="J_menuItem" id="${menu2.res_id}" href="javascript:;" data-res="${ctx}${menu2.res_url}?id=${menu2.res_id}">
			                                 <span class="sec-label glyphicon glyphicon-chevron-right "></span>
			                                 ${menu2.res_name}
			                                </a>
			                            </li>
			                            </c:forEach>	
			                        </ul>
			                    </li>
		                    </c:forEach>
                    	</c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
       

	   <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
           
		   
			<!--头部开始-->
			<div class="row border-bottom">
                <nav class=" navbar-static-top" role="navigation" style="margin-bottom: 0;min-height:40px;max-height:40px;">
                    <div class="navbar-header">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-default " href="#"><i class="fa fa-bars"></i> </a>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                       <li class="dropdown user_todo">
	                    <span class="user-do">
	                    <a data-toggle="dropdown" class="dropdown-toggle" href="#" style="background-color:#f3f3f4;">${account.user_nickname}<b class="caret"></b></a>
		                    <ul class="dropdown-menu animated fadeInRight">
			                  <li style="border-bottom:1px solid #CCD2DB"><a data-toggle="modal" data-target="#pwdModal"><spring:message code='font.framework.password'/></a>
			                  </li>
			                  <li><a href="${ctx}/logout.shtml"><spring:message code='font.framework.logout'/></a>
			                  </li>
			                </ul>
	                    </span>
	                   </li>                       
	                   <li class="hidden-xs">
                            <!--  <span id="i18n_cn" style="cursor: pointer;">中文</span> | <span id="i18n_en" style="cursor: pointer;">English</span> -->
                       </li>
                    </ul>
                    <ul class="nav navbar-nav" hidden="true">
                      
                    </ul>
                </nav>
            </div>
			<!--头部结束-->
			
			
			<!--多选项卡操作、退出等功能开始-->
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">								
                        <a href="javascript:;" class="active J_menuTab" data-id="${ctx}/indexWelcome.shtml"><spring:message code='menu.manage.map.distribution'/></a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown"><spring:message code='font.framework.closetabs'/><span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right animated fadeInRight m-t-xxs">
                        <li class="J_tabShowActive"><a><spring:message code='font.framework.locationcurrenttab'/></a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a><spring:message code='font.framework.closealltabs'/></a>
                        </li>
                        <li class="J_tabCloseOther"><a><spring:message code='font.framework.closeothertab'/></a>
                        </li>
                    </ul>
                </div>
                <a href="${ctx}/logout.shtml"  class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i><spring:message code='font.framework.logout'/></a>
            </div>
			<!--多选项卡操作、退出等功能结束-->
			
			
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx}/indexWelcome.shtml"  frameborder="0" data-id="${ctx}/indexWelcome.shtml" seamless></iframe>
            </div>
			
			<!--底部开始-->
            <div class="footer" style="margin-bottom:-8px">
                <div class="pull-right"><span id="cur_year"></span> © DUYDDUS Data Center
                </div>
            </div>
			<!--底部结束-->
			
        </div>
        <!--右侧部分结束-->
		
		
		
		<!--mini聊天最小化图标开始-->
        <div id="small-chat" style="display: none;">
            <span class="badge badge-warning pull-right">5</span>
            <a class="open-small-chat">
                <i class="fa fa-comments"></i>
            </a>
        </div>
		<!--mini聊天最小化图标结束-->
		
		
		
		
		
		
		
		<!-- 修改密码 begin -->
	<div class="modal fade" id="pwdModal" tabindex="-1" role="dialog" aria-hidden="true">
	
	   <div class="modal-dialog">
	   
	        <div class="modal-content">
	        
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                    &times;
	                </button>
	                <h4 class="modal-title" id="myModalLabel"><spring:message code='font.framework.password'/></h4>
	            </div>
	            
	            <div class="modal-body">
                        <form class="form-horizontal m-t" id="pwdform" action="${ctx}/user/changePwd.shtml">
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><spring:message code='font.framework.original.password'/>：</label>
                                <div class="col-sm-8">
                                    <input id="password" name="password" type="password" class="form-control" maxlength="16" placeholder="原始密码（必填 ）">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><spring:message code='font.framework.new.password'/>：</label>
                                <div class="col-sm-8">
                                    <input id="password_1" type="password" class="form-control" name="password_1" maxlength="16" placeholder="<spring:message code='font.framework.password.style'/>">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><spring:message code='font.framework.confirm.password'/>：</label>
                                
                                <div class="col-sm-8">
                                    <input id="password_2" type="password" class="form-control"  maxlength="16" value="" name="password_2" placeholder="<spring:message code='font.framework.password.style'/>">
                                </div>
                            </div>
                            
                        </form>
	            </div>
	            
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='font.framework.cancel'/></button>
	                <button type="button" class="btn btn-primary" id="pwdbutton"><spring:message code='font.framework.save'/></button>
	            </div>
	            
	        </div>
	        
	    </div>
	</div>
	<!-- 新增用户 end -->
    </div>

    <script>
    	$(function(){
    		$('#i18n_cn').click(function(){
    			location.href = "${ctx}/changeI18n/cn.shtml";
    		});
    		
			$('#i18n_en').click(function(){
				location.href = "${ctx}/changeI18n/us.shtml";
    		});

			$('#pwdbutton').click(function(){
				var pwd = $('#password').val();
				var pwd_1 = $('#password_1').val();
				var pwd_2 = $('#password_2').val();
				if(pwd == ''){
					layer.msg("<spring:message code='font.framework.original.empty'/>");
					return;
				}

				if(pwd_1 == ''){
					layer.msg("<spring:message code='font.framework.new.empty'/>");
					return;
				}

				if(pwd_1.length>16 || pwd_1.length<8){
					layer.msg("<spring:message code='font.framework.pw.n.error'/>");
					return;
				}else{
					//密码强度
					var StrengthValue = passwordLevel(pwd_1);
					if(StrengthValue<3){
						layer.msg("<spring:message code='font.framework.pw.n.format.error'/>");
						return;
					}
				}

				if(pwd_2== ''){
					layer.msg("<spring:message code='font.framework.confirm.empty'/>");
					return;
				}

				if(pwd_1 != pwd_2){
					layer.msg("<spring:message code='font.framework.pw.double'/>");
					return;
				}

				var obj = CommnUtil.ajax($('#pwdform').attr("action"),{pwd:pwd,pwd1:pwd_1},"json");
				if(obj.status == 200){
					$("#pwdModal").modal('hide');
					layer.msg("<spring:message code='modify_success'/>");
					location.href="${ctx}/logout.shtml";
				}else if(obj.status == -301){
					layer.msg("该用户不存在");
					return false;
				}else if(obj.status ==-303){
					layer.msg("原始密码错误");
					return false;
				}
				else{
					layer.msg("<spring:message code='modify_fail'/>");
				}
			});
			curtime();

    	});

    	//校验密码级别
    	function passwordLevel(password) {
    		 var Modes = 0;
    		 for (i = 0; i < password.length; i++) {
    		 Modes |= CharMode(password.charCodeAt(i));
    		 }
    		 return bitTotal(Modes);
    		 //CharMode函数
    		 function CharMode(iN) {
    		 if (iN >= 48 && iN <= 57)//数字
    		  return 1;
    		 if (iN >= 65 && iN <= 90) //大写字母
    		  return 2;
    		 if ((iN >= 97 && iN <= 122) || (iN >= 65 && iN <= 90))
    		 //大小写
    		  return 4;
    		 else
    		  return 8; //特殊字符
    		 }
    		 //bitTotal函数
    		 function bitTotal(num) {
    		 modes = 0;
    		 for (i = 0; i < 4; i++) {
    		  if (num & 1) modes++;
    		  num >>>= 1;
    		 }
    		 return modes;
    		 }
    	}
    	
    	function curtime(){
        var a = new Date();
        var y = a.getFullYear();
        document.getElementById("cur_year").innerHTML =y;
     }
    </script> 
</body>
<script type="text/javascript">
 
	
    window.onload=function(){
//        setInterval("searchAlarm()",5000);
    }

</script>
</html>