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
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Inforvellor</title>
    <head>
		<link href="${root }/static/css/datasearch/datasearch.css" rel="stylesheet" type="text/css" />
		<link href="${root }/static/css/common/button/button.css" rel="stylesheet" type="text/css" />
		<link href="${root }/static/css/common/country/country.css" rel="stylesheet" type="text/css" />
		<link href="${root }/static/css/easyui/easyui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" 	src="${root }/static/js/jquery/jquery.js"></script>
		<script type="text/javascript" 	src="${root }/static/js/jquery/jquery-1.8.2.min.js"></script>
		<script type="text/javascript"	src="${root }/static/js/easyui/jquery.easyui.min.js"></script>
		
		<!-- <script type="text/javascript"	src="${root }/static/js/easyui/locale/easyui-lang-zh_CN.js"></script>
		 -->
		 <script type="text/javascript"	src="${root }/static/js/easyui/locale/easyui-lang-en.js"></script> 
		<script type="text/javascript"  src="${root }/static/js/jquery/jquery.PrintArea.js"></script>
		<script type="text/javascript"	src="${root }/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
		<script type="text/javascript"	src="${root }/static/js/artdialog/iframeTools.source.js"></script>
		<script type="text/javascript"	src="${root }/static/js/artdialog/artdialogopen.js"></script>
		<script type="text/javascript"	src="${root }/static/js/common/common.js"></script>
		<script type="text/javascript"	src="${root }/view/datasearch/datasearch.js"></script>
		<script type="text/javascript"	src="${root }/static/js/My97DataPicker/WdatePicker.js"></script>
		<script type="text/javascript"	src="${root }/static/js/common/common-path.js"></script>
		<script type="text/javascript"	src="${root }/static/js/eachart/reportEcharts.js"></script>
		<script type="text/javascript" src="${root }/static/js/eachart/echarts.js"></script>
		<script type="text/javascript" src="${root }/static/js/eachart/echarts-all.js"></script>
		<script type="text/javascript" src="${root}/view/alldb/ProgressBarFun.js"></script>
</head>
<body style="background-color: #f0f3f5;"> 
	<div style="margin:0 auto;text-align:left;padding: 0;width: 100%; min-height: 100%; height: auto!important;height: 100%;position: relative;background-color: #f0f3f5">
	    <!--头部页面  -->
		<jsp:include page="/view/common/head/head.jsp"></jsp:include>
		<div style="margin:0 auto;text-align:left;padding: 0;width: 1210px;top:40px;padding-bottom: 60px;">
			<div style="margin:0 auto;text-align:left;padding: 0;width: 100%;background-color: #f0f3f5;padding-top: 26px">
				<div id="find" style="clear: both;width:1210px;height:auto;">
					<div class="advancesearch">
						<div style="padding-top: 10px;background-color: #F5FAF9;width: 1210px;height: 30px;"><b><font color="#1369c0" style="font-size:18px;padding-left: 36px">Search Conditions</font></b></div>
						<div align="right" width="1100px" >
							<!-- 动态生成的前台查询条件 -->
							<div id="showQueryCondition"></div>
							<hr style="border: 1px solid #e5e5e5;width: 1030px;margin-top:20px;left:310px"/>
						</div>
						<div class = "tips" style="line-height: 20px;">
						 	<img src="/gbdbas/static/img/datasearch/tips.png" style="width:25px;"></img>
							<font size="2">Custom Report Data Directory: It is recommended that you choose HS code as a search reference. Each country has its own code but the first six digits are unified. So, we recommend that you choose the six digit HS code to search. We also recommend that you use the local language as the key words of the product in your search. Records Guide: It is recommended that you choose product key words in your search.</font>
						</div>
						<!-- 查询按钮 -->
						<div id="queryCollection" style="margin-left:930px;margin-top:-30px;height:60px;display: none;">
							<a class="btn_cls" title="<fmt:message key="commonAll.queryImmediately" bundle="${messages}"/>" href="javascript:query();">Immediate Search</a>
							<a id="downloadReport" class="btn_cls" title="<fmt:message key="commonAll.downloadReport" bundle="${messages}"/>" href="javascript:downloadReport()">Download Report</a>
						</div>
						<div id="queryCollectionGrey" style="margin-left:930px;margin-top:-30px;height:60px;display: block;">
							<a class="btn_cls" title="<fmt:message key="commonAll.queryImmediately" bundle="${messages}"/>" href="javascript:query();">Immediate Search</a>
							<a id="downloadReportgrey" class="btnGrey_cls" title="<fmt:message key="commonAll.downloadReport" bundle="${messages}"/>" href="javascript:void(0)">Download Report</a>
						</div>
						<div id="querygreyCollectionGrey" style="margin-left:930px;margin-top:-30px;height:60px;display: none;">
							<a class="btnGrey_cls" title="<fmt:message key="commonAll.queryImmediately" bundle="${messages}"/>" href="javascript:void(0);">Immediate Search</a>
							<a id="downloadReportgrey" class="btnGrey_cls" title="<fmt:message key="commonAll.downloadReport" bundle="${messages}"/>" href="javascript:void(0)">Download Report</a>
						</div>
					</div>
				</div>
			</div>
			<div id="resultDiv" style="display: none;">
				<div id="menubox">
				</div>
				<div id="conten">
					<div class="tab_show" id="con_tab_1" >
						<!-- 导出PDF及EXCEL弹出框 -->
					    <div id="exportPDForExcelDlg" style="display:none;width:363px;height:192px;">
							<table>
								<tr>
									<td>
										<div class="smallerStyle" style="color:#6B6D73;">
											<fmt:message key="common.Hello" bundle="${messages}"/>${sessionScope.user.loginName}</br>
											<span id="downloadNum"  class="smallerStyle" style="color:#EC5565;"></span>
										</div>
									</td>
								</tr>
								<tr>
									<td style="padding-top:15px;">
										<input type="radio" value="下载到本地" checked="checked" />
										<span class="commonStyle" style="color:#6B6D73;"><fmt:message key="common.downloadDataToLocal" bundle="${messages}"/></span>
									</td>
								</tr>
								<tr>
									<td style="position:absolute;bottom:23px;right:30px;z-index:100;">
										<a class="confirmOrcancel_cls" title="<fmt:message key="common.download" bundle="${messages}"/>"  id="dowonload"><fmt:message key="common.download" bundle="${messages}"/></a>
										<a class="confirmOrcancel_cls" title="<fmt:message key="common.cancel" bundle="${messages}"/>" onclick="art.dialog({id:'exportPDForExcelDlg'}).close();"><fmt:message key="common.cancel" bundle="${messages}"/></a>
									</td>
								</tr>
							</table>
						</div>
						<!-- 导出PDF和导出EXCEL -->
						<div id="exportPDForExcel"  style="margin-top: 20px;z-index: 100;float: right;">
							<a class="addFavorites_cls" onclick="addFavorites()" title="<fmt:message key="commonAll.favoriteQueryConditions" bundle="${messages}"/>">
								<font style="padding-left: 30px;">Save</font>
							</a>&nbsp;
							<a class="exportPdf_cls" onclick="exportFile('2')" title="<fmt:message key="common.exportPDF" bundle="${messages}"/>">
								<font style="padding-left: 30px;">Export</font>
							</a>&nbsp;
							<a class="exportExcl_cls" onclick="exportFile('1')" title="<fmt:message key="common.exportExcel" bundle="${messages}"/>">
								<font style="padding-left: 30px;">Export</font>
							</a>&nbsp;
						</div>
						<div id="resultFieldDiv" style="padding-top: 56px">
						</div>
						<div id="selectFieldDialog" style="display:none;z-index: 100;position: absolute;margin-top:-860px;border: 1px solid #D4D4D4;width:300px;height:auto;background-color: #FFFFFF;" >
							<div style="padding-top: 10px;background-color: #F5FAF9;width: 300px;height: 30px;"><b><font color="#1369c0" style="font-size:16px;padding-left: 36px">please select the shown letter</font></b></div>
							<div id="selectFieldDiv" style="width:300px;font-size: 12px;">
							</div>
							<div id="selectFieldButton" style="padding:40px 0px 30px 60px ;z-index: 110;">
								<a class="confirmOrcancel_cls" title="保存" onclick="selectField();">Save</a>
								<a class="confirmOrcancel_cls" title="取消" onclick="javascript:$('#selectFieldDialog').css('display','none');">Cancel</a>
							</div>
						</div>
					</div>
					<div class="tab_show" id="con_tab_2" ></div>
					<div class="tab_show" id="con_tab_3" ></div>
					<div class="tab_show" id="con_tab_4" ></div>
				</div>
			</div>
		</div>
		<!-- xl 添加页尾 -->
		<jsp:include page="/view/common/footer/footer.jsp"></jsp:include>
	</div>
	<!-- 导出汇总明细 -->
	<div id="postdata" style="display: none"></div>
	<!-- 点击查看更多时显示数据弹出框 -->
	<div id="showLoadDataDialog" style="display: none;"></div>
</body>
    <!-- 选择国家对话框 -->
	<jsp:include page="/view/datasearch/dialog/choosecountry.jsp"></jsp:include>
	<!-- 查看详情页面 -->
	<jsp:include page="/view/datasearch/dialog/viewdetail.jsp"></jsp:include>
	<!-- 添加多海关编码对话框 -->
	<jsp:include page="/view/datasearch/dialog/addhscode.jsp"></jsp:include>
	<!-- 添加搜藏夹对话框 -->
	<jsp:include page="/view/datasearch/dialog/addFavorites.jsp"></jsp:include>
	<!-- 添加我的客户对话框 -->
	<jsp:include page="/view/alldb/customer/addAndUpdateCustomer.jsp"></jsp:include>
	<!-- 添加竞争对手对话框 -->
	<jsp:include page="/view/alldb/competitor/addAndUpdateCompetitor.jsp"></jsp:include>
	<!-- 选择对比数据对话框 -->
	<jsp:include page="/view/datasearch/dialog/selectConpareData.jsp"></jsp:include>
	<!-- 数据对比对话框 -->
	<jsp:include page="/view/datasearch/dialog/dataCompare.jsp"></jsp:include>
	<!-- 报表明细对话框 -->
	<jsp:include page="/view/datasearch/dialog/reportDetail.jsp"></jsp:include>
	<!-- 深度挖取对话框 -->
	<jsp:include page="/view/datasearch/dialog/depthdigging.jsp"></jsp:include>
	<!-- 同环比对话框 -->
	<jsp:include page="/view/datasearch/dialog/thbReport.jsp"></jsp:include>
	<!-- 多海关编码对话框 -->
	<jsp:include page="/view/datasearch/dialog/mutiHscodeMark.jsp"></jsp:include>
</html>