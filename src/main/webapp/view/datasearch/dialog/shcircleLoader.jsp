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
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>转圈</title>
<head>
	<style type="text/css">
		#shclKeyframes{width:50px; height:30px; margin:10px auto;}
	</style>
</head>
 <body>
	<div id="shclKeyframesDiv" style="width: 150px; height: 80px;font-size: 12px;overFlow-x: hidden;position: relative;display: none; ">
		<p>正在查询中,请稍等……</p>
		<div id="shclKeyframes"></div>
	</div>
	<script type="text/javascript">
		$('#shclKeyframes').shCircleLoader({keyframes:"0%{background:blue}40%{background:white}60%{background:blue}100%{background:blue}"});
	</script>
 </body>
</html>
