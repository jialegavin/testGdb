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
	<title>国际商业智能化数据信息平台</title>
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
		<script type="text/javascript"	src="${root }/view/personalcenter/viewright/findUserRight.js"></script>
</head>
<body style="background-color: #f0f3f5"> 
	<div style="margin:0 auto;text-align:left;padding: 0;width: 100%;background-color: #f0f3f5">
	    <!--头部页面  -->
		<jsp:include page="/view/common/head/head.jsp"></jsp:include>
		<div style="margin:0 auto;text-align:left;padding-top: 50px;width: 1210px;top:40px;">
		    <div id="rightDiv" style="width: 902px;height: 60px;z-index: 100;position: absolute;padding-left: 200px;padding-top: 20px;">
			    <div style="padding-bottom: 15px;">
			    	<span style="color: #0066cc;font-family: 宋体;font-size: 14px;">
			    		<img alt="查看用户权限" style="vertical-align:middle;" src="${root }/static/img/personalcenter/queryRight.png"> 查看用户权限(${sessionScope.user.loginName})
			    	</span>
			    </div>
			    <table id='rightTable'  fitColumns="false" 
			         pagination="true" singleSelect="false" loadMsg="正在查询...">						
					<thead>
						<tr>
							<th field="byHsCode" Width="150" align="center">
								<font style="font-size: 14px;">Custom Code</font>
							</th>
							<th field="byProductDesc" Width="150" align="center">
								<font style="font-size: 14px;">Product Description</font>
							</th>
							<th field="byCountry" Width="150" align="center">
								<font style="font-size: 14px;">Country</font>
							</th>
							<th field="startTime" Width="150" align="center">
								<font style="font-size: 14px;">Start time</font>
							</th>
							<th field="endTime" Width="150" align="center">
								<font style="font-size: 14px;">End time</font>
							</th>
							<th field="iExportType" Width="150" align="center">
								<font style="font-size: 14px;">进出口类型</font>
							</th>
						</tr>
					</thead>
				</table>
			 </div>
			<div style="margin:0 auto;text-align:left;padding: 0;width: 100%;background-color: #f0f3f5;padding-top: 10px">
				<jsp:include page="/view/common/subMenu/subMenu.jsp"></jsp:include>
			</div>
		</div>
		<!-- xl 添加页尾 -->
		<jsp:include page="/view/common/footer/footer.jsp"></jsp:include>
	</div>
</body>
</html>