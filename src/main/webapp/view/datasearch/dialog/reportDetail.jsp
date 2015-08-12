<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<fmt:setBundle basename="<%=language%>" var="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Report Details</title>
</head>
<body>
	<input type="text" id="detailParam" style="display: none;"/>
	<input type="text" id="detailCountry" style="display: none;"/>
	<div id="datasearchDetailDiv" title="报表明细"  style="width:1000px;height:440px;	width: 100%!important;display: none;">
		<div style="padding:5px 0px 10px 0px;z-index: 100;float: right;">
			<a class="exportPdf_cls" onclick="exportDetailData()" title="<fmt:message key="common.exportPDF" bundle="${messages}"/>">
				<font style="padding-left: 30px;">导 出</font>
			</a>
		</div>
		<div style="width: 100%!important; height: 420px;">
			<table id='datasearchDetail'  style="width: 1000px; height: 390px;" pagination="true" singleSelect="false" loadMsg="loading……">						
				 <thead>
					 <tr>
					 </tr>
				 </thead>
			 </table>
		</div>
	</div>	  
</body>
</html>