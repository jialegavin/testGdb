<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<link rel="stylesheet" type='text/css' href="<%=basePath%>/static/css/login/findpwd.css">
<link rel="stylesheet" type='text/css' href="<%=basePath%>/static/css/easyui/icon.css">
<link rel="stylesheet" type='text/css' href="<%=basePath%>/static/css/easyui/easyui.css">
<script type="text/javascript" src="<%=basePath%>/static/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/login/findpwd.js"></script>
<title>Inforvellor-reset password</title>
<script type="text/javascript">
//初始化页面
$(document).ready(function(){
	$('#password').focus(function(){ 
		$(this).removeClass('red_cls'); 
		//添加文本框样式
		$(this).addClass('blue_cls'); 
		//提示
		$("#pwd-msg").empty();
		$("#pwd-msg").append("It can only contain letters, numbers and punctuation marks, within a length of 6 to 12");
		$("#pwd-msg").css("display","block");
	});
	$('#password').blur(function(){ 
		var password = $("#password").val();
		if(""==password)
		{
			$(this).removeClass('blue_cls'); 
			$("#password").addClass('red_cls'); 
			$("#pwd-msg").empty();
			$("#pwd-msg").append("Please enter password");
			$("#pwd-msg").css("display","block");
		}
		else
		{
			$(this).removeClass('red_cls'); 
			$(this).removeClass('blue_cls'); 
			$("#pwd-msg").css("display","none");
		}
	});
	$('#verifypwd').focus(function(){
		$(this).removeClass('red_cls');  
		//添加文本框样式
		$(this).addClass('blue_cls'); 
		$("#verifypwd-msg").css("display","none");
	});
	$('#verifypwd').blur(function(){ 
		var verifypwd = $("#verifypwd").val();
		if(""==verifypwd)
		{
			$(this).removeClass('blue_cls'); 
			$("#verifypwd").addClass('red_cls'); 
			$("#verifypwd-msg").empty();
			$("#verifypwd-msg").append("Please enter confirmed password");
			$("#verifypwd-msg").css("display","block");
		}
		else
		{
			$(this).removeClass('red_cls'); 
			$(this).removeClass('blue_cls'); 
			$("#verifypwd-msg").css("display","none");
		}
	});
});
</script>
</head>
<body>
   <!--主DIV-->
   <div class="all">
	   <!-- head -->
	   <div class="header">
	   </div>
	   <!-- body -->
	   <div class="sub">
	    <div class='titleLeft'>To Retrieve Password</div>
	    <div class='titleRight'>
	    </div>
	    <div class="find_div">
	       <ul class="mod-sub-nav">
	         <li class="mod-sub-list1" style="font-size: 16px;">Confirm Account  </li>
	         <li class="mod-sub-list2" style="font-size: 16px;">Safety verification  </li>
	         <li class="mod-sub-list3 list3-active" style="font-size: 16px;">Reset password </li>
	       </ul>
	       <div class="mod-step-detail"> 
		       <p class="step-form-tip m_l80">The account you are searching for is：${sessionScope.user.loginName }</p> 
		       <form action="/gbdbas/UserPassword/updatePwd" method="post" id="resetpwd-form" onsubmit="return validateUserPwd()"> 
		       	<div class="pass-input-container clearfix">
	       	        <label class="pass-input-title" for="password">New password</label> 
	       	        <input type="password" class="pass-input left pass-input-error" name="password" id="password" value="" autocomplete="off"> 
	       	        <span class="pass-input-msg" id="pwd-msg" style="display: none;">Please type your password</span> 
		       	</div> 
		       	<div class="pass-input-container clearfix"> 
		       		<label class="pass-input-title" for="repassword">Confirm new password</label> 
		       		<input type="password" class="pass-input left pass-input-error" name="verifypwd" id="verifypwd" value="" autocomplete="off"> 
		       		<span class="pass-input-msg" id="verifypwd-msg" style="display: none;">Please type your Confirmed password</span> 
		       	</div> 
		       	<div class="m_l80"> 
		       		<input type="submit" name="" value="下一步" class="pass-button-submit" id="submit">  
		       	</div> 
		      </form> 
		   </div>
	    </div>
	   </div>
   </div>
</body>
</html>