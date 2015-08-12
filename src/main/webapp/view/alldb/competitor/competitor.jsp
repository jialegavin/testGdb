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
	String language = (String) request.getSession().getAttribute(
			"language");
	if (language == null || "".equals(language)
			|| "pleaseSelect".equals(language)) {
		language = "message_zh_CN";
		request.getSession().setAttribute("language", "message_zh_CN");
	}
%>
<fmt:setBundle basename="<%=language%>" var="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>我的竞争对手</title>
	<style type="text/css">
	.body_style {
		font-family: "Microsoft Yahei", Heiti, arial, helvetica,
			sans-serif !important;
	}
	
	#mycomsearchtools span {
		font-size: 16px;
		color: #77807f;
	}
	</style>
	<link href="${root }/static/css/alldb/alldb.css" rel="stylesheet" type="text/css" />
	<link href="${root }/static/css/common/subMenu/subMenu.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${root }/static/css/easyui/easyui.css">
	<link rel="stylesheet" type="text/css" href="${root }/static/css/easyui/icon.css">
	<link href="/gbdbas/static/css/common/button/button.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${root }/static/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${root }/static/js/jquery/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${root }/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${root }/static/js/easyui/locale/easyui-lang-en.js"></script>	 
	<script type="text/javascript" src="${root }/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="${root }/static/js/artdialog/iframeTools.source.js"></script>
	<script type="text/javascript" src="${root }/static/js/artdialog/artdialogopen.js"></script>
	<script type="text/javascript" src="${root }/static/js/My97DataPicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${root}/static/js/eachart/reportEcharts.js"></script>  
	<script type="text/javascript" src="${root }/static/js/common/common-path.js"></script>
	<script type="text/javascript" src="${root}/static/js/common/dateutil.js"></script>
	<!-- echarts -->
	<script type="text/javascript" src="${root }/static/js/eachart/echarts-all.js"></script>
	<script type="text/javascript" src="${root }/static/js/eachart/echarts.js"></script>
	<script type="text/javascript" src="${root }/static/js/eachart/reportEcharts.js"></script>
	<!-- 权库共用的Js -->
	<script type="text/javascript" src="${root }/view/alldb/commonRightLibrary.js"></script>
	<script type="text/javascript" src="${root }/view/alldb/competitor/js/competitor.js"></script>
</head>
<body>
	<!--头部页面  -->
	<div style="width: 100%; height: 150px;">
		<jsp:include page="/view/common/head/head.jsp"></jsp:include>
	</div>
	
	<div style="width: 80%;height: 100%; margin: 0 auto;">
		<div id="menu" style="margin:0 auto;text-align:left;width: 15%; height: 250px;float: left;margin-top: 5px;position: absolute;z-index: 100">
			<jsp:include page="/view/common/subMenu/subMenu.jsp"></jsp:include>
		</div>
		<div id="center" style="width:67%; height: 500px;margin-top: 5px;z-index:100;position: absolute;margin-left: 180px;">
			<!-- 标题 -->
			<div style="padding-top: 10px;background-color: #F5FAF9;width: 100%;height: 32px;text-align: left;">
				<div style="width: 18%; height: 30px; border: 0px solid red;">
					<div style="width: 27px; height: 30px;float: left;"><img src="${pageContext.request.contextPath }/static/img/alldb/buyer.png"/></div>
					<div style="width: 150px; height: 30px;float: left; line-height: 30px;">
						<b><font color="#1369c0" style="font-size:18px;padding-left: 5px;">My Competitors</font></b>
					</div>
				</div>
			</div>
			 <div id="customerDg" style="height:30px;line-height:30px;display:none; font-size: 16px;font-family:Microsoft YaHei;background-color:#E7F1FB;">
			   	<div style="width:100%; height: 30px;line-height: 26px;">
					<a href="javascript:addCompetitor();" title="立即查询" class="btn_alldb_add"><span style="margin-left: 15px;">新增</span></a>
					<a href="javascript:updateCompetitor();" title="立即查询" class="btn_alldb_eit"><span style="margin-left: 15px;">修改</span></a>
					<a href="javascript:del();" title="立即查询" class="btn_alldb_remove"><span style="margin-left: 15px;">删除</span></a>
					<span style="float: right; margin-top: 5px;">
						<input class="easyui-searchbox" id="queryCountry" style="width: 200px;">
					</span>
			   	</div>
			  </div>
			<div id="table">
				<table id="datagrid" title="" style="height: 400px;" pagination="true" toolbar="#customerDg"
					singleSelect="false" loadMsg="loading……">
					<thead>
						<tr>
							<th data-options="field:'ck',checkbox:true"></th>
							<th field="collectionId" width="2" align="center" hidden="true">
								<span style="font-size: 14px;">
									Customer ID
								</span>
							</th>
							<th field="companyName" width="245" align="center">
								<span style="font-size: 14px;">
									Company name
								</span>
							</th>
							<th field="address" width="80" align="center">
								<span style="font-size: 14px;">
									Address
								</span>
							</th>
							<th field="mailBox" width="80" align="center">
								<span style="font-size: 14px;">
									Email
								</span>
							</th>
							<th field="contact" width="80" align="center">
								<span style="font-size: 14px;">
									Contact person
								</span>
							</th>
							<th field="country" width="80" align="center">
								<span style="font-size: 14px;">
									Country
								</span>
							</th>
							<th field="fax" width="80" align="center">
								<span style="font-size: 14px;">
									Fax
								</span>
							</th>
							<th field="userType" width="80" align="center" hidden="true">
								<span style="font-size: 14px;">
									Customer Type
								</span>
							</th>
							<th field="tel" width="80" align="center">
								<span style="font-size: 14px;">
									Tel
								</span>
							</th>
							<th field="alternativeName" width="80" align="center" hidden="true">
								<span style="font-size: 14px;">
									Tel
								</span>
							</th>
							<th field="customerValue" width="80" align="center" hidden="true">
								<span style="font-size: 14px;">
									Tel
								</span>
							</th>
							<th field="opt" width="210" formatter="addOpss" align="center">
								<span style="font-size: 14px;">
									Other Functions
								</span>
							</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="/view/common/footer/footer.jsp"></jsp:include>
	</body>
 	<!-- 全库我的竞争对手市场分析-->
	<jsp:include page="/view/alldb/competitor/competitorReport.jsp"></jsp:include>
	<!-- 全库我的竞争对手交易记录 -->
	<jsp:include page="/view/alldb/competitor/tradingRecord.jsp"></jsp:include>
	<!-- 添加&&修改我的竞争对手 -->
	<jsp:include page="/view/alldb/competitor/addAndUpdateCompetitor.jsp"></jsp:include>
	<!-- 查看详情页面 -->
	<jsp:include page="/view/datasearch/dialog/viewdetail.jsp"></jsp:include>
	<!-- 添加我的对手 -->
	<jsp:include page="/view/alldb/competitor/addAndUpdateCompetitor.jsp"></jsp:include>
	<!-- 添加我的客户 -->
	<jsp:include page="/view/alldb/customer/addAndUpdateCustomer.jsp"></jsp:include>
</html>