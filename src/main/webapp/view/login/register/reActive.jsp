<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<title>Re activate user</title>
<link rel="stylesheet" type='text/css' href="<%=basePath%>static/css/easyui/easyui.css">
<link rel="stylesheet" type='text/css' href="<%=basePath%>static/css/login/register.css">

<script type="text/javascript" src="<%=basePath%>/static/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="<%=basePath%>/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/artdialog/artdialogopen.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/login/regest.js"></script>
<style type="text/css">
 .main{
   margin: 200px auto auto auto;
   width:400px;
   height:400px;
 }
</style>
<script type="text/javascript">
</script>
</head>
<body>
<!-- 重新激活页面 -->
   <div class="main"> 
        <div>Sorry. The email account has expired. Please reactivate</div>
        <div>
           <button id="reActive" onclick="reActive()">Send email again</button>
        </div>
   </div>
  <!-- 激活邮箱 -->
   <div  id="activeDiv" closed="true" class="easyui-dialog" style="border-width:1px; width:400px;height:400px;padding:10px 20px;overflow-x:hidden;">
       <div class="toMail">
       <font  >Congratulations! <span id="name"></span></font><br/>
       <font >We have sent an activation email to your email. Please click the link in the email to complete your account activation</font><br/>
                Go to <a id='emailId' href="#" style='font-size:16px;'></a> Activate  <br/>
       </div>
        <!--  <button id="redoButton" onclick="sendAgin()">重新发送</button>-->
   </div>
</body>
</html>