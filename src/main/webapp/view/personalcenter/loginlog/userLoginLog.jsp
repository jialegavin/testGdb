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
	<link rel="stylesheet" type="text/css" href="${root }/static/css/easyui/easyui.css">
	<link rel="stylesheet" type="text/css" href="${root }/static/css/easyui/icon.css">
	<link href="${root }/static/css/common/button/button.css" rel="stylesheet" type="text/css" />
	<link href="${root }/static/css/common/subMenu/subMenu.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${root }/static/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${root }/static/js/jquery/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${root }/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"	src="${root }/static/js/easyui/locale/easyui-lang-en.js"></script>
	<script type="text/javascript" src="${root }/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="${root }/static/js/artdialog/iframeTools.source.js"></script>
	<script type="text/javascript" src="${root }/static/js/My97DataPicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${root }/static/js/artdialog/artdialogopen.js"></script>
	<script type="text/javascript" src="${root }/static/js/common/common-path.js"></script>
	<script type="text/javascript" src="${root }/view/personalcenter/loginlog/userLoginLog.js"></script>
	<style type="text/css">
		html,body {
		    margin: 0;
		    padding: 0!important;
		    height: 100%;
		}
	</style>
</head>
<body style="background-color: #f0f3f5;"> 
	<div style="style="margin:0 auto;text-align:left;padding: 0;width: 100%; min-height: 100%; height: auto!important;height: 100%;position: relative;background-color: #f0f3f5"">
	    <!--头部页面  -->
		<jsp:include page="/view/common/head/head.jsp"></jsp:include>
		<div style="margin:0 auto;text-align:left;padding-top: 50px;width: 1210px;top:40px;padding-bottom: 60px;">
			<div style="width:1000px;position: absolute;padding-left: 200px;padding-top: 20px;">
				<div style="padding-bottom: 10px;">
			    	<span style="color: #0066cc;font-family: 宋体;font-size: 14px;">
			    		<img alt="账号登录日志" style="vertical-align:middle;" src="${root }/static/img/personalcenter/loginLog.png"> Log-in History
			    	</span>
			    </div>
				<!-- 查询条件 -->
				<div style="font-size: 12px;padding-bottom: 5px;">
					 <font style="font-size: 14px">Username：</font>
					 <select class="easyui-combobox" name="c_origin_country" id="loginName" style="width:180px;height:28px;"></select>&nbsp;
				     <font style="font-size: 14px">Login time：</font>
				     <input id="beginDate"  style="height: 22px;border-radius: 4px 4px 4px 4px;border: 1px solid #e5e5e5;" id="beginDate" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})" />
				     <font>-</font> 
				     <input name="endDate"style="height: 22px;border-radius: 4px 4px 4px 4px;border: 1px solid #e5e5e5;" id="endDate" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-12-31','2011-12-31','2012-12-31','2013-12-31','2014-12-31'],readOnly:true})" />&nbsp;&nbsp;
				     <a class="sub_cls" title="查询" onclick="selectByLogin()">Query</a>
				</div>
				<div id="rightDiv" style="width:900px;">
				     <table id='userlogin'   style="height: 530px;width:900px;"  singleSelect="false"  loadMsg="<fmt:message key="common.querying" bundle="${messages}"/>">
						   <thead>
						   </thead>
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
</body>
</html>