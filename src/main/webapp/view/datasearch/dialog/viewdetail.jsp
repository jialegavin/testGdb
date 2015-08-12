<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 国际化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String language = (String)request.getSession().getAttribute("language");
if(language == null || "".equals(language) || "pleaseSelect".equals(language))
    language = "message_zh_CN";
%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<fmt:setBundle basename="<%=language%>" var="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Inforvellor——View detail</title>
    <head>
		<link href="${root }/static/css/datasearch/viewdetail/viewdetail.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet/css" href="${root }/static/css/datasearch/viewdetail/print.css" media="print">
    </head>
  <body class="body_style">
  		<div id="detailmessageDIV"  title=" 查看详情" style="overFlow-x: hidden;position: relative; width:900px;height:600px;display: none;">
	  		<div id="showArea"></div>
		</div>
  </body>
</html>
