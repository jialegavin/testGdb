<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 国际化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String language = (String)request.getSession().getAttribute("language");
if(language == null || "".equals(language) || "pleaseSelect".equals(language))
    language = "message_zh_CN";
%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<fmt:setBundle basename="<%=language%>" var="messages" />
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<head>
<meta name="renderer" content="webkit">  
<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
	<div id="rightDiv"  style="display: none;width: 720px; height: 550px;padding:10px 20px;overflow-x:hidden;" >
	   <div style="font-size:5;font-weight:bold;  color:blue;">Country list:</div>
	   <!-- 展示所有国家以及该用户拥有的国家权限 -->
	   <div id="countrysDiv" style="width:auto;height:150px;"></div>
	   <!-- 展示单个国家权限列表 -->
	   <div style="font-size:5; font-weight:bold; color:blue;margin-bottom: 6px; margin-top: 10px;">Permissions list:</div>
	   <div style="width:auto;height:300px;">
		   <div id="codesDiv" class="easyui-tabs" style="width:auto;height:260px;border-bottom: 1px solid #95B8E7;" title="国家权限列表"></div>
	   </div>
	   <!-- 提交按钮 -->
	   <div style="width:auto;height:30px;padding-left: 260px;">
	       <a class="confirmOrcancel_cls" title="授权" onclick="grantRight();">Authorize</a>&nbsp;&nbsp;
	       <a class="confirmOrcancel_cls" title="关闭" onclick="javascript:art.dialog({id:'rightDiv'}).close();">Close</a>
	   </div>
	   
	   
    </div>
  </body>
</html>
