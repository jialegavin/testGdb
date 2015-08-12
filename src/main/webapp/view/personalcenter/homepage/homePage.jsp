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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Inforvellor</title>
    <head>
    	<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link href="${root }/static/css/easyui/easyui.css" rel="stylesheet" type="text/css" />
		<link href="${root }/static/css/common/subMenu/subMenu.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" 	src="${root }/static/js/jquery/jquery.js"></script>
		<script type="text/javascript" 	src="${root }/static/js/jquery/jquery-1.8.2.min.js"></script>
		<script type="text/javascript"	src="${root }/static/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript"	src="${root }/static/js/easyui/locale/easyui-lang-en.js"></script>
		<script type="text/javascript"	src="${root }/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
		<script type="text/javascript"	src="${root }/static/js/artdialog/iframeTools.source.js"></script>
		<script type="text/javascript"	src="${root }/static/js/artdialog/artdialogopen.js"></script>
		<script type="text/javascript"	src="${root }/static/js/common/common.js"></script>
		<script type="text/javascript"	src="${root }/view/personalcenter/homepage/homePage.js"></script>
</head>
<body style="background-color: #f0f3f5"> 
	<div style="margin:0 auto;text-align:left;padding: 0;width: 100%;background-color: #f0f3f5">
	    <!--头部页面  -->
		<jsp:include page="/view/common/head/head.jsp"></jsp:include>
		<div style="margin:0 auto;text-align:left;padding-top: 50px;width: 1210px;top:40px;">
				<div id="updateTimeDiv" style="padding-left: 203px;clear: both;padding-top:10px;width:900px;height:auto;z-index: 100px;position: absolute;">
					<img src="${root }/static/img/personalcenter/tips.png"></img>
				</div>
				<div style="padding-left: 250px;padding-top:20px;width:100px;height:auto;z-index: 200px;position: absolute;">
					<font>News</font>
				</div>
				<div style="padding-left: 340px;padding-top:20px;width:700px;height:auto;z-index: 200px;position: absolute;">
					<marquee id="updateTimeMarquee" style="font-size: 18px">
					</marquee>
				</div>
			<div style="margin:0 auto;text-align:left;padding: 0;width: 100%;background-color: #f0f3f5;padding-top: 10px">
				<jsp:include page="/view/common/subMenu/subMenu.jsp"></jsp:include>
				<table id ="introduction" >
					<tr>
						<td style="padding-left: 40px;padding-top: 30px;"><a href=""><img src="${root }/static/img/personalcenter/CustomerIntro.png"></img></a></td>
						<td style="padding-left: 40px;padding-top: 30px;"><a href=""><img src="${root }/static/img/personalcenter/CompetitorIntro.png"></img></a></td>
					</tr>
					<tr>
						<td style="padding-left: 40px;padding-top: 30px;"><a href="/gbdbas/view/datasearch/datasearch.jsp"><img src="${root }/static/img/personalcenter/dataSearchIntro.png"></img></a></td>
						<td style="padding-left: 40px;padding-top: 30px;"><a href=""><img src="${root }/static/img/personalcenter/reportCompareIntro.png"></img></a></td>
					</tr>
				</table>
			</div>
		</div>
		<!-- xl 添加页尾 -->
		<jsp:include page="/view/common/footer/footer.jsp"></jsp:include>
	</div>
</body>
</html>