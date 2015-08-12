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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><fmt:message key="allCountry.competitorSAnalysis"
		bundle="${messages}" /></title>
<head>
<script type="text/javascript"
	src="${root }/view/alldb/competitor/js/report.js"></script>
</head>
<body>
	<div id="qkCompeterDiv" style="margin: 0 auto; text-align: left; padding: 0; width: 970px; height: 590px; background-color: #ffffff;display: none;overflow: scroll;">
		<div id="report" style="clear: both; width: 100%;">
			<div class="alldb_advancesearch">
				<div style="padding-top: 14px; background-color: #F5FAF9; width:100%; height: 30px; line-height: 20px;">
					<b><font color="#478DE3" id="fontText" style="font-size: 18px; font-family: 微软雅黑, 宋体; padding-left: 16px">
							Market analysis of my customers
						</font>
					</b>
				</div>
				<div align="left" width="920px">
					<div class="alldb_firstcols">
						<font class="alldb_fieldname">
							Company name
							&nbsp;:&nbsp;
						</font>
						<input type="text" id="jzcompanytext" name="jzexporter"
							class="reportinpText" readonly="readonly">&nbsp;&nbsp;
					</div>
					<div class="alldb_secondcols">
						<font class="alldb_fieldname">
							Select country&nbsp;:&nbsp;
						</font>
						<select id="equirementSelects36" style="width: 190px"></select>
						<div id="sps36"></div>
					</div>
					<div class="alldb_firstcols">
						<div style="float: left;">
							<font class="alldb_fieldname">
								Time Span&nbsp;:&nbsp;
							</font>
							<input id="compbegindateFlexBr" value="2012-01-01"
								style="width: 88px; height: 20px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;"
								name="compbegindateFlexBr"
								onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})" />
							<font class="henggang">-</font> <input name="compenddateFlexBr"
								style="width: 88px; height: 20px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;"
								id="compenddateFlexBr" value="2012-12-31"
								onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-12-31','2011-12-31','2012-12-31','2013-12-31','2014-12-31'],readOnly:true})" />&nbsp;&nbsp;
						</div>
					</div>
					<div class="alldb_choosecountry">
						<div id="alreadyselectcountry" style="display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">
						</div>
					</div>
					<div class="alldb_firstcols" style="height: 40px">
						<div id="" style="float: left; margin-left: 550px; margin-top: -5px; padding-bottom: 20px;">
							<a class="btn_cls" title="立即查询" href="javascript:quankuCompsent(this);">Immediate Search</a>
					</div>
				</div>
			</div>
			<!-- 进度条 -->
			<div id="clientProgress" name="clientProgress" style="display:none">
				<span id="dbshowLoadStatus" name="dbshowLoadStatus" style="position: absolute;top: 160px; left: 250px; color: #0066CC;font-size: 14px;font-family:微软雅黑;"></span>
				<div id="clientProgressBar" name="clientProgressBar" class="easyui-progressbar"  data-options="text:''"
					style="position:absolute; width: 300px; height:13px;top: 180px; left: 250px; " />	
			</div>
		</div>
	</div>	
	<div>
		<!-- 报告类型 -->
		<div id="right_library_mebubox">
			<ul>
				<li id="tab1" name="tab1" onclick="alldb_setTab('tab',1,3);reportTypeClick('EXPORTERSUMMARY');"
					style="background-image: url('/gbdbas/static/img/datasearch/tabout.png');" class="hover">
					<span style="display: none;">EXPORTERSUMMARY</span>
					<a id="tab_a1" style="color: #1369c0">Supplier's channel analysis report</a>
				</li>
				<li id="tab2" name="tab2" onclick="alldb_setTab('tab',2,3);reportTypeClick('GOODSSUM');"
					style="background-image: url('/gbdbas/static/img/datasearch/tabbbefore.png');">
					<span style="display: none;">GOODSSUM</span>
					<a id="tab_a2">Product procurement analysis report</a>
				</li>
				<li id="tab3" name="tab3" onclick="alldb_setTab('tab',3,3);reportTypeClick('TRANSACTIONTREND');"
					style="background-image: url('/gbdbas/static/img/datasearch/tabbbefore.png');">
					<span style="display: none;">TRANSACTIONTREND</span>
					<a id="tab_a3">Procurement trend analysis report</a>
				</li>
			</ul>
		</div>
		<div id="dg_echart" style="width: 100%;hei1ht: 100%;border: 0px solid red;margin-top: 20px;">
			<!-- 列表 -->
			<table name="marketplaceDataGrid" style="width: 950px;"></table>
			<div id="echartsDiv"></div>
		</div>
	</div>
 </div>	
</body>
</html>