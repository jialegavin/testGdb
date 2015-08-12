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
	}
	String dateLanguage = "zh-cn";
	if (language.equals("message_en_US")) {
		dateLanguage = "en";
	}
%>
<fmt:setBundle basename="<%=language%>" var="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="${root }/view/alldb/competitor/js/tradingRecord.js"></script>
</head>
<body>
	<!-- 交易记录 -->
	<div id="compiterorTradeW" style="display: none; width: 100%; height: 550px;margin: 0 auto;">
		<div id="searchCompiterortool" style="height: auto; margin: 0 auto;">
			<div align="left" width="95%" class="alldb_advancesearch">
				<div style="padding-top: 14px; background-color: #F5FAF9; width: 100%; height: 30px;">
					<b><font color="#478DE3" id="fontText" style="font-size: 18px; font-family: 微软雅黑, 宋体; padding-left: 16px">
							Data List
						</font>
					</b>
				</div>
				<div style="width: 100%;height: 90%;margin-top: 10px;">
					<div style="height: 60px; width: 30%;float: left;">
						<font class="alldb_fieldname">Company name&nbsp;:&nbsp;</font>
						<input id="companyNameByTradingRecord" name="companyNameByTradingRecord" class="reportinpText" />
					</div>
					<div style="height: 60px;  width: 32%;float: left;">
						<font class="alldb_fieldname">
							Product Description&nbsp;:&nbsp;
						</font>
						<input id="compProductDesc" name="compProductDesc" class="reportinpText" />
					</div>
					<div style="height: 60px;  width: 30%;float: left;">
						<font class="alldb_fieldname">Date&nbsp;:&nbsp;</font> 
							<input id="compTradestartDate" name="compTradestartDate" class="reportinpText" style="width: 85px;"
							onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})" />
							- 
							<input id="compTradeendDate" name="compTradeendDate" style="width: 85px;" class="reportinpText"
							onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-12-31','2011-12-31','2012-12-31','2013-12-31','2014-12-31'],readOnly:true})" />
					</div>
					<div style="height: 50px;  width: 30%;float: left;">
						<font class="alldb_fieldname">
							Custom Code&nbsp;:&nbsp;
						</font> 
						<input id="compHscode" name="compHscode" class="reportinpText"/>
					</div>
					<div style="height:50px; width: 32%;float: left;">
						<font class="alldb_fieldname">
							Trade Type&nbsp;:&nbsp;</font>
						<select class="easyui-combobox" panelHeight="auto"
							style="width: 202px; height: 22px; border-color: #e2e5e7"
							editable="false" id="competitorIexportType" name="custIexportType">
							<option value="I,E">All</option>
							<option value="I">Import</option>
							<option value="E">Export</option>
						</select>
					</div>
					<div style="height: 50px; width: 30%;float: left;">
						<font class="alldb_fieldname">
							Country of Origin&nbsp;:&nbsp;</font> 
							<select id="equirementSelects" name="equirementSelects" 
								style="width: 205px; height: 22px; border-color: #e2e5e7">
							</select>
						<div id="sps"></div>
					</div>
					<div id="" style="width: 87%;height: 100%;">
						<div style="float:right;margin-top: 5px; padding-bottom: 20px;">
							<a class="btn_cls" title="立即查询" href="javascript:queriesCompiterorShow(this);">Immediate Search</a>
						</div>
					</div>
				</div>
			</div>
			<!-- 进度条 -->
			<div id="clientProgress" name="clientProgress" style="display:none">
				<span id="dbshowLoadStatus" name="dbshowLoadStatus" style="position: absolute;top: 200px; left: 350px; color: #0066CC;font-size: 14px;font-family:微软雅黑;">
				</span>
				<div id="clientProgressBar" name="clientProgressBar" class="easyui-progressbar"  data-options="text:''" style="position:absolute; width: 300px; height:13px;top: 220px; left: 350px; "/>
			</div>
		</div>
		<div name="queryDataTable">
			<table id="tradingDataGrid" style="width: 1055px; height: 300px;">
		</table>
		</div>
	</div>
</body>
</html>