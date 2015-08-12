<%@page import="com.njyb.gbdbase.model.admincenter.UserModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 国际化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String language = (String)request.getSession().getAttribute("language");
	if(language == null || "".equals(language) || "pleaseSelect".equals(language)){
	    language = "message_zh_CN";
	}
    String dateLanguage = "zh-cn";
	if(language.equals("message_en_US")){
		dateLanguage ="en";
	}
	UserModel user=(UserModel)request.getSession().getAttribute("user");
%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<fmt:setBundle basename="<%=language%>" var="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Inforvellor</title>
<head>
	<meta name="renderer" content="webkit">  
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" type="text/css" href="${root }/static/css/easyui/easyui.css">
    <link href="${root }/static/css/common/button/button.css" rel="stylesheet" type="text/css" />
	<link href="${root }/static/css/datasearch/datasearch.css" rel="stylesheet" type="text/css" />
	<link href="${root }/static/css/common/subMenu/subMenu.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${root }/static/css/usermanagement/son.css" />
    <script type="text/javascript" 	src="${root }/static/js/jquery/jquery.js"></script>
	<script type="text/javascript" 	src="${root }/static/js/jquery/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"	src="${root }/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"	src="${root }/static/js/easyui/locale/easyui-lang-en.js"></script>
	<script type="text/javascript"	src="${root }/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript"	src="${root }/static/js/artdialog/iframeTools.source.js"></script>
	<script type="text/javascript"	src="${root }/static/js/artdialog/artdialogopen.js"></script>
	<script type="text/javascript"	src="${root }/static/js/common/common.js"></script>
    <script type="text/javascript" src="${root }/view/admincenter/usermanager/js/comm.js"></script>
    <script type="text/javascript" src="${root }/view/usermanagement/userson/userSon.js"></script>
    <script type="text/javascript" src="${root }/static/js/common/date.js"></script>   
    <script type="text/javascript" src="${root }/view/usermanagement/userson/right.js"></script>
     <script type="text/javascript"	src="${root }/static/js/My97DataPicker/WdatePicker.js"></script>
</head>
<body style="background-color: #f0f3f5">
  <!--保存当前用户ID-->
  <div id="currentId"  hidden><%=user.getUserId() %></div>
  <div style="style="margin:0 auto;text-align:left;padding: 0;width: 100%; min-height: 100%; height: auto!important;height: 100%;position: relative;background-color: #f0f3f5"">
	    <!--头部页面  -->
		<jsp:include page="/view/common/head/head.jsp"></jsp:include>
		<div style="margin:0 auto;text-align:left;padding-top: 50px;width: 1210px;top:40px;padding-bottom: 60px;">
			<div style="width:974px;position: absolute;padding-left: 200px;padding-top: 20px;">
				<div style="padding-bottom: 10px;">
			    	<span style="color: #0066cc;font-family: 宋体;font-size: 14px;">
			    		<img alt="账号登录日志" style="vertical-align:middle;" src="${root }/static/img/usermanagement/sonAccount.png"> <b>Sub-Account Management</b>
			    	</span>
			    </div>
				<div id="buttonDiv">
		   	 	    <!-- 新增用户 -->
					<a class="btn_alldb_add" onclick="addUser()" title="新增用户"  href="javascript:void(0);">&nbsp;&nbsp;Add</a>
					<!-- 修改用户 -->
					<a class="btn_alldb_eit"  onclick="updateUser()" title="编辑用户"  href="javascript:void(0);">&nbsp;&nbsp;Refise</a>
					<!-- 删除用户 -->
					<a class="btn_alldb_remove" onclick="deleteUsers()" title="删除用户" href="javascript:void(0);">&nbsp;&nbsp;Delete</a>
					<div style="float: right;">
				    	<img src="/gbdbas/static/img/datasearch/tips.png" style="width:25px;vertical-align: middle;">
						<span class="showfontCls" id="subSumCount"></span>
					</div>
				</div>
				<div id="sonAccountList" style="width:100%;padding-top: 7px;">
		           <table id="sonsTable" singleSelect="false" style="width:974px;height:408px;">
			       </table>
			    </div>
			 </div>
			 <div style="margin:0 auto;text-align:left;padding: 0;width: 100%;background-color: #f0f3f5;padding-top: 10px">
				<jsp:include page="/view/common/subMenu/subMenu.jsp"></jsp:include>
			</div>
		</div>
		<!-- xl 添加页尾 -->
		<jsp:include page="/view/common/footer/footer.jsp"></jsp:include>
	</div>
</body>
<!-- 添加用户 -->
<jsp:include page="/view/usermanagement/dialog/addUser.jsp"></jsp:include>
<!-- 授权 -->
<jsp:include page="/view/usermanagement/dialog/grantRight.jsp"></jsp:include>
<!--海关编码产品描述输入对话框 -->
<jsp:include page="/view/usermanagement/dialog/inputCondition.jsp"></jsp:include>
<!-- 查看权限 -->
<jsp:include page="/view/usermanagement/dialog/seeRight.jsp"></jsp:include>
<!-- 设置下载数量 -->
<jsp:include page="/view/usermanagement/dialog/setDownloadNum.jsp"></jsp:include>
</html>