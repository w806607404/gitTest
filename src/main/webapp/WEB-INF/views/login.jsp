<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/initTag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title><spring:message code="login.title"/></title>
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
</head>
<body class="margin0">

    <div id="login-warning" class="margin0 animated bounceInDown">
      <div id="warning-text">
		<p>
		 <spring:message code="login.new.version.message"/>
		</p>
      	<button id="login-warning-confirm" class="btn btn-primary btn-block"><spring:message code="i.know"/></button>
      </div>
    </div>
    
    <div class="margin0 login-page">
	<div class="language_a">
	    <!--<a href="${ctx}/changeI18n/cn.shtml">中文</a> | <a href="${ctx}/changeI18n/us.shtml">English</a>-->
	</div>
    <div class="row">
        <div class="signinpanel" id="login-form">
            <form id="formlogin" class="m-t" role="form"  method="post" onsubmit="return false;">
                <!-- <h4 class="no-margins"><spring:message code="login"/>：</h4> -->
                <p class="m-t-sm "><spring:message code='font.framework.title'/></p>
                
                <input id="loginname" name="loginname" type="text" class="form-control uname" placeholder="账号"  maxlength="32"/>
                <input id="loginpwd" name="loginpwd" type="password" class="form-control pword m-b" placeholder="<spring:message code="password"/>" maxlength="16"/>
	
				<input id="verifycode" name="verifycode" type="text" class="form-control" placeholder="<spring:message code="codes"/>" maxlength="4"/>
				<img id="codeImg" alt="<spring:message code="click.change"/>" title="<spring:message code="click.change"/>" src=""/>&nbsp;
                
                <button class="btn btn-primary btn-block" id="loginsubmit"><spring:message code="login"/></button>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function(){
	if('${is_show}' == '0'){
		$('#login-warning').hide();
	}else{
		if('${login_state}' == '1'){
			//$('#login-form').hide();
			$('#login-warning').hide();
		}else{
			$('#login-warning').hide();
		}
	}
	
	
	changeCode();
	$("#codeImg").bind("click", changeCode);
	 var maxPoint = 0;
	 var setinterval_login;
    if(window.top!==window.self){window.top.location=window.location};
	var $LOGIN = {
			//检查输入
			checkInput:function() {
				if ($("#loginname").val() == "") {
					Toastr.warning("<spring:message code='login.user.name.not.empty'/>",4000,false);
					$("#loginname").focus();
					return false;
				}
				if ($("#loginpwd").val() == "") {
					Toastr.warning("<spring:message code='login.user.pwd.not.emppty'/>",4000,false);
					$("#loginpwd").focus();
					return false;
				}
				
				if($("#verifycode").val() == ""){
					Toastr.warning("<spring:message code='login.input.verify.code'/>",4000,false);
					$("#verifycode").focus();
					return false;
				}

				return true;
			},
			doLogin:function() {
				$.post("${ctx}/login.shtml", $("#formlogin").serialize(),function(data){
	                if(data.status != 200){
	                	dologining();
	                }
					switch (data.status) {
	                case 200:
	                	Toastr.success("<spring:message code='login.success'/>",2000,false);
						location.href = "${ctx}/index.shtml";
						break;
	                case 10010:
	                	layer.alert_("<spring:message code='message'/>","验证码不能为空","<spring:message code='font.framework.button'/>");
	                	break;
	                case 10011:
	                	layer.alert_("<spring:message code='message'/>","验证码错误","<spring:message code='font.framework.button'/>");
	                	break;
	                case 10012:
	                	layer.alert_("<spring:message code='message'/>","该用户不存在","<spring:message code='font.framework.button'/>");
	                	break;
	                case 10013:
	                	break;
	                case 10014:
	                	Toastr.error("用户无效",4000,false);
						$("#loginname").select();
	                	break;
	                case 10015:
	                	layer.alert_("<spring:message code='message'/>","用户密码错误","<spring:message code='font.framework.button'/>");
	                	break;
	                case 10016:
	                	layer.alert_("<spring:message code='message'/>","账户审核中","<spring:message code='font.framework.button'/>");
	                	break;
					default:
						Toastr.error("<spring:message code='system.error'/>",4000,false);
						break;
					}
				});
			},
			login:function() {
				if (this.checkInput()) {
					this.doLogin();
					 $("#loginsubmit").attr("disabled", true);
					 setinterval_login = setInterval(function(){
					     var count = "";
					     for (var i = 0; i < maxPoint; i++) {
					    	 count += ".";
					     }
					     $("#loginsubmit").text("<spring:message code="logging.in"/>" + count);
					     maxPoint++;
					     if (maxPoint > 4) maxPoint = 0;
						 
					 }, 500);
				}
			}
	};
	
	$("#loginsubmit").click(function(){
		$LOGIN.login();
	});
	
	function changeCode() {
		$("#codeImg").attr("src", "${ctx}/code?t=" + new Date().getTime());
	}
	
	function dologining(){
		 clearInterval(setinterval_login);
         $("#loginsubmit").text("<spring:message code='login'/>");
         $("#loginsubmit").attr("disabled", false);
	}
}); 
	$('#login-warning-confirm').click(function(){
		$('#login-warning').hide();
		$('#login-form').fadeIn();
	});
</script>   
</body>
</html>