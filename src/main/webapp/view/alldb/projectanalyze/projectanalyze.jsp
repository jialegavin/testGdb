<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set var="root" value="${pageContext.request.contextPath }" />
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
<fmt:setBundle basename="<%=language%>" var="messages" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>All Library Download</title>
<link href="${root }/static/css/alldb/alldb.css" rel="stylesheet" type="text/css" />
<link href="${root }/static/css/easyui/easyui.css" rel="stylesheet" type="text/css" />
<link href="/gbdbas/static/css/common/button/button.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${root }/static/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${root }/static/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${root }/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${root }/static/js/easyui/locale/easyui-lang-en.js"></script>
<script type="text/javascript" src="${root }/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript" src="${root }/static/js/artdialog/iframeTools.source.js"></script>
<script type="text/javascript" src="${root }/static/js/artdialog/artdialogopen.js"></script>
<script type="text/javascript" src="${root }/static/js/My97DataPicker/WdatePicker.js"></script>
<!-- echarts -->
<script type="text/javascript" src="${root }/static/js/eachart/echarts-all.js"></script>
<script type="text/javascript" src="${root }/static/js/eachart/echarts.js"></script>
<script type="text/javascript" src="${root }/static/js/eachart/reportEcharts.js"></script>
<script type="text/javascript" src="${root }/static/js/common/common-path.js"></script>
<!-- 权库 共用js -->	
<script type="text/javascript" src="${root }/view/alldb/commonRightLibrary.js"></script>
<script type="text/javascript" src="${root }/view/alldb/projectanalyze/js/projectAnalyze.js"></script>
</head>
<body class="body_style">
		<!--头部页面  -->
	<div style="width: 100%; height: 130px;">
		<jsp:include page="/view/common/head/head.jsp"></jsp:include>
	</div>
	
	<div style="width: 80%; margin: 0 auto;border: 0px solid red;">
		<div style="padding-top: 10px;background-color: #F5FAF9;width: 100%;height: 32px;text-align: left;">
			<div style="width: 27px; height: 30px;float: left;"><img src="${pageContext.request.contextPath }/static/img/alldb/buyer.png"/></div>
			<div style="width: 150px; height: 30px;float: left; line-height: 30px;">
				<b><font color="#1369c0" style="font-size:18px;padding-left: 5px;">My Product Label</font></b>
			</div>
		</div>
		<div id="center" style="width:100%; height: 400px;margin-top: 5px;border: 0px solid red;">
			 <div id="customerDg" style="height:30px;line-height:30px;display:none; font-size: 16px;font-family:Microsoft YaHei;background-color:#E7F1FB;">
			   	<div style="width:100%; height: 30px;line-height: 26px;">
					<a href="javascript:addProduct();" title="立即查询" class="btn_alldb_add"><span style="margin-left: 15px;">New</span></a>
					<a href="javascript:updateproduct();" title="立即查询" class="btn_alldb_eit"><span style="margin-left: 15px;">Refise</span></a>
					<a href="javascript:delproduct();" title="立即查询" class="btn_alldb_remove"><span style="margin-left: 15px;">Delete</span></a>
			   		<span style="float: right; margin-top: 5px;">
						<input class="easyui-searchbox" id="queryHscode" style="width: 200px;">
					</span>
			   	</div>
			  </div>
			<div id="table">
				<table id="productdg" class="easyui-datagrid"
						style="height: 380px;" pagination="true" singleSelect="false"
						toolbar="#customerDg" autoRowHeight="false"
						url="${root}/projectAnalyze/queryProjectAnalyzeByParam"
						loadMsg="Loading.....">
						<thead>
							<tr>
								<th data-options="field:'ck',checkbox:true"></th>
								<th field="verifycode" align="center" width="200"
									style="color: #656565;"><font style="font-size: 14px">Label Name</font></th>
								<th field="hscode" width="200" align="center"
									style="color: #656565;"><font style="font-size: 14px">HS code
								</font></th>
								<th field="productName" width="200" align="center"
									style="color: #656565;"><font style="font-size: 14px">Key Word</font></th>
								<th field="opt" width="510" align="center" formatter="addOps"><font
									style="font-size: 14px">Other Functions</font></th>
							</tr>
						</thead>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/view/common/footer/footer.jsp"></jsp:include>
</body>
<!-- 竞争对手 -->
<jsp:include page="/view/alldb/projectanalyze/competitor.jsp"></jsp:include>
<!-- 市场分析 -->
<jsp:include page="/view/alldb/projectanalyze/marketAnalyzeReport.jsp"></jsp:include>
<!-- 买家 -->
<jsp:include page="/view/alldb/buyer/buyerInfo.jsp"></jsp:include>
<!-- 添加产品 -->
<jsp:include page="/view/alldb/projectanalyze/addProductTag.jsp"></jsp:include>
<!-- 查看详情页面 -->
<jsp:include page="/view/datasearch/dialog/viewdetail.jsp"></jsp:include>
</html>