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
	 <!-- 显示该用户的所有权限 -->
   <div id="seeRight"  style="display: none;width: 840px; height: 600px;">
		<!--查询按国家定制的权限 -->
		<div id="goldenrightDiv">
			<div  id="goldenrightTable" style="width: 819px; " title="按国家定制权限如下">
			</div>
		</div>
		
		<!--查询按产品描述或者海关编码定制的权限  -->
		<div id="viprightDiv">
			<div  id="viprightTable" style="width: 819px; " title="按海关编码或者产品描述定制如下">
			</div>
		</div>
	</div>
  </body>
</html>
