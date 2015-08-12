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
	 <!-- 设置用户下载条数 -->
	<div id="addExcleDlg"  style="display: none;width: 220px; height: 160px;padding:10px 20px;overflow-x:hidden;" >
		<div style="font-size:14px;font-weight:bold;margin-bottom: 5px;">The current maximum number of downloads：<span id="maxNum" style="font-size:12px;font-weight:bold;color:red;" /></span></div>
		<div style="font-size:14px;font-weight:bold;margin-bottom: 5px;padding-top: 10px;">Set the number of downloads：<input name="setNum"  style="font-size:12px;" id="setNum" type="text" size=10/></div>
		<div style="padding-left: 30px;padding-top: 60px;">
			<a class="confirmOrcancel_cls" title="保存" onclick="saveExcleNum();">Save</a>
			<a class="confirmOrcancel_cls" title="取消" onclick="javascript:art.dialog({id:'addExcleDlg'}).close();">Cancel</a>
		</div>
	</div>
</body>
</html>
