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
<title>Market Analysis</title>
<head>
</head>
<body>
	<div id="quankuDiv" style="margin: 0 auto; text-align: left; padding: 0; width: 100%; height: 590px; background-color: #ffffff;display: none;overflow: scroll;">
		<div id="report" style="clear: both; width: 100%;">
			<div class="alldb_advancesearch">
				<div style="padding-top: 14px; background-color: #F5FAF9; width: 950px; height: 30px;">
					<b><font color="#478DE3"
						style="font-size: 18px; font-family: 微软雅黑, 宋体; padding-left: 36px"><fmt:message
								key="allCountry.productTagMutiMAReport" bundle="${messages}" /></font></b>
				</div>
				<div align="left" width="950px" style="border: 0px solid red;">
					<div class="alldb_firstcols">
						<font class="alldb_fieldname">Custom Code&nbsp;:&nbsp;</font> <input
							id="quankuhscode" class="reportinpText" disabled="disabled"
							name="quankuhscode"
							placeholder="<fmt:message key="allCountry.addHscode" bundle="${messages}"/>" />&nbsp;&nbsp;
					</div>
					<div class="alldb_secondcols">
						<font class="alldb_fieldname">Trade Type&nbsp;:&nbsp;</font>
						<input type="radio" name="quankuiexportFlex" value="进口" onclick="iexportFlexClick('I')"
							checked="checked" /> <font class="alldb_fieldname"><fmt:message
								key="allCountry.import" bundle="${messages}" /></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="quankuiexportFlex" value="出口" onclick="iexportFlexClick('E')"/> <font
							class="alldb_fieldname"><fmt:message key="allCountry.export"
								bundle="${messages}" /></font>
					</div>
					<div class="alldb_firstcols">
						<font class="alldb_fieldname">Product Description&nbsp;:&nbsp;</font>
						<input id="quankugoodsdesc" class="reportinpText"
							disabled="disabled" name="quankugoodsdesc"
							placeholder="<fmt:message key="allCountry.completeWord" bundle="${messages}"/>" />
					</div>
					<div class="alldb_secondcols">
						<font class="alldb_fieldname">Select country&nbsp;:&nbsp;</font>
						<select id="equirementSelects9" name="bqopponentcountry"
							style="width: 190px"></select>
						<!-- 国家显示列表 -->
						<div class="choosecountry">
							<div id="quankuselectcountry" style="display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;"></div>
						</div>
					</div>
					<div class="alldb_firstcols">
						<div style="float: left;">
							<font class="alldb_fieldname">Time Span&nbsp;:&nbsp;</font>
							<input id="quankubegindate" value="2014-01-01"
								style="width: 88px; height: 20px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;"
								name="quankubegindate"
								onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})" />
							<font class="henggang">-</font> 
							<input name="quankuenddate" style="width: 88px; height: 20px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;"
								id="quankuenddate" value="2014-12-31" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-12-31','2011-12-31','2012-12-31','2013-12-31','2014-12-31'],readOnly:true})" />&nbsp;&nbsp;
						</div>
					</div>
					<!-- 进度条 -->
					<div id="clientProgress" name="clientProgress" style="display:none">
						<span id="dbshowLoadStatus" name="dbshowLoadStatus" style="position: absolute;top: 280px; left: 350px; color: #0066CC;font-size: 14px;font-family:微软雅黑;z-index: 100"></span>
						<div id="clientProgressBar" name="clientProgressBar" class="easyui-progressbar"  data-options="text:''"
							style="position:absolute; width: 300px; height:13px;top: 300px; left: 350px; z-index: 100" />
						</div>
					</div>
					<div id="sps9"></div>
					<div class="alldb_firstcols" style="height: 40px;">
						<div id="qkqueryDownReportGrey"
							style="float: left; margin-left: 650px; margin-top: -10px; display: block;">
							<a class="btn_cls" href="javascript:quankusent(this);" title="立即查询">
								Immediate Search
							</a>
						</div>
					</div>
				</div>
			</div>
			<div id="right_library_mebubox">
				<ul>
					<li id="tab1" name="tab1" onclick="alldb_setTab('tab',1,7);reportTypeClick('PORTOFSHIPMENT');"
						style="background-image: url('/gbdbas/static/img/datasearch/tabout.png');" class="hover">
						<!-- 隐藏报告类型 -->
						<span style="display: none;">PORTOFSHIPMENT</span>
						<a id="tab_a1" style="color: #1369c0">POL Report</a>
					</li>
					<li id="tab2" name="tab2" onclick="alldb_setTab('tab',2,7);reportTypeClick('PORTOFARRIVAL');"
						style="background-image: url('/gbdbas/static/img/datasearch/tabbbefore.png');">
						<span style="display: none;">PORTOFARRIVAL</span>
						<a id="tab_a2">POD Report</a>
					</li>
					<li id="tab3" name="tab3" onclick="alldb_setTab('tab',3,7);reportTypeClick('IMPORTERSUMMARY');"
						style="background-image: url('/gbdbas/static/img/datasearch/tabbbefore.png');">
						<span style="display: none;">IMPORTERSUMMARY</span>
						<a id="tab_a3">Global Buyers Report</a>
					</li>
					<li id="tab4" name="tab4" onclick="alldb_setTab('tab',4,7);reportTypeClick('EXPORTERSUMMARY');"
						style="background-image: url('/gbdbas/static/img/datasearch/tabbbefore.png');">
						<span style="display: none;">EXPORTERSUMMARY</span>
						<a id="tab_a4">Global Suppliers Report</a>
					</li>
					<li id="tab5" name="tab5" onclick="alldb_setTab('tab',5,7);reportTypeClick('TRANSACTIONTREND');"
						style="background-image: url('/gbdbas/static/img/datasearch/tabbbefore.png');">
						<span style="display: none;">TRANSACTIONTREND</span>
						<a id="tab_a5">Market Trend Report</a>
					</li>
					<li id="tab6" name="tab6" onclick="alldb_setTab('tab',6,7);reportTypeClick('NATIVE');"
						style="background-image: url('/gbdbas/static/img/datasearch/tabbbefore.png');">
						<span style="display: none;">NATIVE</span>
						<a id="tab_a6">Country Of Origin Report</a>
					</li>
				</ul>
			</div>
			<div id="conten">
				<div class="" id="con_tab_1">
					<!-- 列表 -->
					<div id="resultFieldDiv" style="padding-top: 20px;">
						<table name="marketplaceDataGrid" style="width: 950px;">
						</table>
					</div>
					<div style="position: relative;">
						<div id="echartsDiv" style="padding-top: 0px;">
						</div>
<!-- 					<div id="withRadio" style="text-align:right;height: 20px;border:0px solid red;position: absolute;left:800px;top:5px;"> -->
<!-- 						<input type="radio" name="reportRadio" value="myoy" /><span style="font-size: 12px;color: white">同比</span> -->
<!-- 						<input type="radio" name="reportRadio" value="mom" /><span style="font-size: 12px;color: white">环比</span> -->
<!-- 					</div> -->
					</div>
				</div>
				<div class="alldb_tab_show" id="con_tab_2">
					<p style="padding-top: 20px">The Value Of Tab 2</p>
				</div>
				<div class="alldb_tab_show" id="con_tab_3">
					<p style="padding-top: 20px">The Value Of Tab 3</p>
				</div>
				<div class="alldb_tab_show" id="con_tab_4">
					<p style="padding-top: 20px">The Value Of Tab 4</p>
				</div>
				<div class="alldb_tab_show" id="con_tab_5">
					<p style="padding-top: 20px">The Value Of Tab 5</p>
				</div>
				<div class="alldb_tab_show" id="con_tab_6">
					<p style="padding-top: 20px">The Value Of Tab 6</p>
				</div>
				<div class="alldb_tab_show" id="con_tab_7">
					<p style="padding-top: 20px">The Value Of Tab 7</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>