<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 国际化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String language = (String)request.getSession().getAttribute("language");
	if(language == null || "".equals(language) || "pleaseSelect".equals(language))
	    language = "message_zh_CN";
%>
<fmt:setBundle basename="<%=language%>" var="messages" />
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>footer of the page</title>
<head>
<link href="${root }/static/css/common/footer/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="footer_z_div footer_position">
	<div class="footer_div">Copyright © Inforvellor 2014-<script>document.write((new Date()).getFullYear());</script> 
	</div>
	</div>
</body>
</html>