<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  class="panel-noscroll" xmlns="http://www.w3.org/1999/xhtml" style="height: 100%; overflow: hidden;">
<head id="Head1">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>南通趣易后台管理页面</title>
    <link href="<%=basePath%>/static/js/menu/css/default.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>/static/js/menu/css/easyui.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/js/menu/css/icon.css" />
    
    <script type="text/javascript" src="<%=basePath%>/static/js/menu/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/js/menu/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src='<%=basePath%>/static/js/menu/js/XiuCai.index.js'> </script>
    <script type="text/javascript">
    var aa = ${sessionScope.menusStr};
	var _menus = eval(aa);
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: 'Change Password',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }

        

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('System Hint', 'Please type password！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('System Hint', 'Please type password again！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('System Hint', 'The two password entries do not match! Please retype new password', 'warning');
                return false;
            }

            $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
                msgShow('System Hint', 'Congratulations! Your password has been successfully changed！<br>Your new password is：' + msg, 'info');
                $newpass.val('');
                $rePass.val('');
                close();
            })
            
        }

       
        $(function() {
            openPwd();

            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })

			$('#btnCancel').click(function(){closePwd();})

            $('#loginOut').click(function() {
                $.messager.confirm('System Hint', 'Are you sure you want to log out?', function(r) {
                    if (r) {
                        $.post("/dbas/userOff",function(){
               			 //window.location.href="/dbas/webInfo/infobase_login/login.jsp";
                            });
                    }
                });
            })
        });
		

    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  fit="true"   scroll="no">
<noscript>

<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="images/noscript.gif" alt='Sorry. Please enable script support!' />
</div></noscript>

<div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000">
<div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;"> 
    <img src="images/loading.gif" align="absmiddle" /> Loading…Please wait..</div>
</div>

    <div region="north" split="true" border="false" style="overflow: hidden; height: 40px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">Welcome ${sessionScope.user.loginName } <a href="#" id="editpass">Change password</a> <a href="#" id="loginOut">Exit safely.</a></span>
        <span style="padding-left:10px; font-size: 16px;"><img src="<%=basePath%>static/js/menu/images/logo.png"  align="absmiddle" /></span>
    </div>
      <div  region="south" split="true" style="height: 40px; background: #D2E0F2; ">
      </div>
    <div region="west" split="true"  title="导航菜单" style="width:180px;" id="west">
			<div id="nav">
			</div>
    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " >
				
			</div>
		</div>
    </div>
    
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                	<tr>
                        <td>Original password：</td>
                        <td><input id="txtRegPass" type="password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>New password：</td>
                        <td><input id="txtNewPass" type="password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>Confirm password：</td>
                        <td><input id="txtRePass" type="password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    Confirm</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">Cancel</a>
            </div>
        </div>
    </div>
   <div id="mm" class="easyui-menu" style="width:150px;">
		<div id="tabupdate">Refresh</div>
		<div class="menu-sep"></div>
		<div id="close">Clsoe</div>
		<div id="closeall">Close all</div>
		<div id="closeother">Close other tabs</div>
		<div class="menu-sep"></div>
		<div id="closeright">Close tabs to the right</div>
		<div id="closeleft">Close tabs to the left</div>
		<div class="menu-sep"></div>
		<div id="exit">Quit</div>
	</div>
</body>
</html>
