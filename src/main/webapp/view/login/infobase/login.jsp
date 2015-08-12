<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!-- 国际化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String language = (String)request.getSession().getAttribute("language");
	if(language == null || "".equals(language) || "pleaseSelect".equals(language))
	{
		language = "message_zh_CN";
	    request.getSession().setAttribute("language","message_zh_CN");
	}
	String userName=(String)request.getAttribute("userName");
%>
<fmt:setBundle basename="<%=language%>" var="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User-Login</title>
    <meta name="renderer" content="webkit">  
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" type="text/css"	href="${root }/static/css/easyui/easyui.css">
    <link rel="stylesheet" href="${root }/static/css/login/login.css"/>
    <link rel="stylesheet" href="${root }/static/css/login/reset.css">
    <link rel="stylesheet" href="${root }/static/css/login/supersized.css">
    <script type="text/javascript" 	src="${root }/static/js/jquery/jquery.js"></script>
	<script type="text/javascript" 	src="${root }/static/js/jquery/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"	src="${root }/static/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${root }/static/js/login/login.js" ></script>
    <script type="text/javascript" src="${root }/static/js/login/supersized.3.2.7.min.js" ></script>
    <script type="text/javascript" src="${root }/static/js/login/supersized-init.js" ></script>

</head>
<body> 
    <div style="display: none" id="userName"><%=userName %></div>
    <div id="top" style="margin:0px auto; height:65px; background-color:#ebf3f6">
        <div style="width:1195px; height:64px; margin:0px auto">
            <div style="width:300px; height:63px; background-image:url(${root }/static/img/login/logo.png); background-repeat:no-repeat"></div>
        </div>
    </div>

    <div style="width:1195px;height:540px; margin:20px auto">
        <div style="width:401px; height:495px; background-image:url(${root }/static/img/login/login_main_bg.png); background-repeat:no-repeat; margin-left:793px">
            <table style="width:100%; font-family:Arial,微软雅黑;">
                <tr style="height:40px;">
                    <td style="width:200px; text-align:center; vertical-align:middle; font-size:16px; font-weight:600; color:#00a1be">Login</td>
                    <td id="shiyong" style="width:201px; text-align:center; vertical-align:middle; font-size:16px; font-weight:600; color:#ffffff"><a href="#">Trial version</a></td>
                </tr>
                <tr style="height:70px;">
                    <td colspan="2">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="text" id="user_name"  placeholder="用户名" class="login_input" style=" background:url(${root }/static/img/login/username_bg.png); background-repeat:no-repeat"/>
                        <br />
                        <input type="password" id="user_pass" placeholder="密码" class="login_input"  style=" background:url(${root }/static/img/login/userpwd_bg.png); background-repeat:no-repeat"/>
                        <br />
                        <input type="text" id="checkcode"  placeholder="验证码" class="login_input" style="width:155px"/>
                    	
                    	<a href="#" onClick="javascript:myRandReload()" alt="重新生成验证吧" title="重新生成验证吧" class="img" style="padding-top: 25px;padding-left: 5px;z-index: 100;position: absolute;"> 
							<img align="bottom" id="createcheckcode" style="width:130px;height:45px;" border="0">
						</a>
						<a id="bjc" href="javascript:myRandReload()" style="text-decoration: none;color:#4a97e3;padding:2px 2px;padding-top: 33px;padding-left: 140px;z-index: 100;position: absolute;">
							<img title="刷新" src="${root }/static/img/login/refresh.png" width="25px" height="25px"/>
						</a>
                    </td>
                </tr>
                <tr>
                    <td id="fp" style="font-size:14px; vertical-align:top;">
                        <input type="checkbox" style="margin-top:17px; margin-left:30px" id="rPassword" name="rPassword" value='y'/><span style="color:#677176">Remember login</span>
                        <br />
                        <br />
                        <br />
                        <a href="javascript:forgetPass();"; style="margin-left:30px; font-size:16px; color:#27afc8">Forgot Password？</a>
                    </td>
                    <td>
                        <input type="button" onclick="usersub();" id ="loginInput" class="login_btn" value="登录" onmouseover="overlogin(this)" onmouseout="outlogin(this)"/>
                    </td>
                </tr>
                <tr style="height:60px">
                    <td id="reg" colspan="2" style="font-size:20px; vertical-align:middle; text-align:center; color:#ffffff; padding-top:30px">
                        <!--<a href="#">立即注册平台账号</a>-->
                        <input id="regnow" type="button" value="立即注册平台账号"  onmouseover="overreg(this)" onmouseout="outreg(this)"/>
                        <!-- onClick="window.open('/gbdbas/view/login/register/register.jsp')" -->
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <script type="text/javascript">
        function overlogin(obj) {
            obj.style.backgroundColor = "#1683c7";
        }

        function outlogin(obj) {
            obj.style.backgroundColor = "#27afc8";
        }
        function overreg(obj) {
            obj.style.backgroundColor = "#1683c7";
        }

        function outreg(obj) {
            obj.style.backgroundColor = "#4fACA8";
        }
    </script>
</body>
</html>
