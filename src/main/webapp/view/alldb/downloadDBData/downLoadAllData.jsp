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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${root }/static/css/datasearch/datasearch.css" rel="stylesheet" type="text/css" />
<link href="${root }/static/css/alldb/alldb.css" rel="stylesheet" type="text/css" />
<link href="${root }/static/css/common/subMenu/subMenu.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${root }/static/css/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${root }/static/css/easyui/icon.css">
<link href="/gbdbas/static/css/common/button/button.css" rel="stylesheet" type="text/css" />
<%-- <link href="${root }/static/css/usermanagement/test.css" rel="stylesheet" type="text/css" /> --%>
<script type="text/javascript" src="${root }/static/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${root }/static/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${root }/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${root }/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript" src="${root }/static/js/artdialog/iframeTools.source.js"></script>
<script type="text/javascript" src="${root }/static/js/artdialog/artdialogopen.js"></script>
<script type="text/javascript" src="${root }/static/js/My97DataPicker/WdatePicker.js"></script>
<script type="text/javascript" src="${root }/static/js/common/common-path.js"></script>
<!-- 权库共用的Js -->
<script type="text/javascript" src="${root }/view/alldb/commonRightLibrary.js"></script>
<!-- 下载全库 -->
<script type="text/javascript" src="${root}/view/alldb/downloadDBData/js/downLoadDBData.js"></script>
<!-- 进度条框架 -->
<script type="text/javascript" src="${root}/view/alldb/ProgressBarFun.js"></script>
</head>
<body class="body_style">
  <div id="dowloadAllDataDlg"  style="margin:0 auto;text-align:left;padding: 0;width: 100%;min-height: 100%; height: auto!important;height: 100%;position:absolute;background-color: #f0f3f5;border: 0px solid red;">
		<!--头部页面  -->
		<jsp:include page="/view/common/head/head.jsp"></jsp:include>
	  <div style="width: 80%;height: 200px; border: 0px solid red;margin: 0 auto;">
		<div class="alldb_down_advancesearch">
			<div title="全库下载" style="width:100%;height:200px;padding-top: 30px;">
				<div style="padding-top: 10px;background-color: #F5FAF9;width: 100%;height: 30px;"><b><font color="#1369c0" style="font-size:18px;padding-left: 36px">All Library Download</font></b></div>
<!-- 				<div style="border:0px solid #E5E5E5;width:80%; margin-top:10px;margin-left: 25px; border-radius:5px;font-size: 14px;color:#777f7f;height: 35px;"> -->
<!-- 					<span style=" vertical-align:top; margin-top:20px;">请选择适合你的查询下载条件：</span> -->
<!-- 					<label title="按hscode查询"> -->
<!-- 					<input class="hsc" type="radio" checked="checked" value="hscode" name="searchGroupRadio"> -->
<!-- 						hscode -->
<!-- 					</label> -->
<!-- 					<label title="按产品描述查询"> -->
<!-- 						<input class="pro" type="radio" value="product" name="searchGroupRadio"> -->
<!-- 						产品描述 -->
<!-- 					</label> -->
<!-- 					<label title="按产品描述查询"> -->
<!-- 						<input class="pro" type="radio" value="国家权限" name="searchGroupRadio"> -->
<!-- 						国家 -->
<!-- 					</label> -->
<!-- 				</div> -->
				<!-- 日期 -->
				<div style="height: 50px;width:20%;border: 0px solid red;line-height: 50px;margin-left: 20px;float: left;">
					<font class="fieldname">Date:</font>
					<input id="dowloadStartDate" name="dowloadStartDate"  class="doubleinpText" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})"/> -
					<input id="dowloadEndDate" name="dowloadEndDate"  class="doubleinpText" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-12-31','2011-12-31','2012-12-31','2013-12-31','2014-12-31'],readOnly:true})"/>
				</div>
				<!-- 海关编码 -->
				<div id="hscodeDiv" style="height: 45px;width: 26%;border: 0px solid red;line-height: 50px; float: left;">
					<font class="fieldname">Custom number:</font>
					<input id="dowloadHscode" name="dowloadHscode" class="inpText"/>
				</div>
				<!-- 产品描述 -->
				<div id="productDiv" style="height: 50px; width: 25%;line-height: 50px; float: left; border: 0px solid red;">
					<font class="fieldname">Product Description:</font>
					<input id="dowloadProductDesc" name="dowloadProductDesc" class="inpText"/> 
				</div>
				<!-- 国家 -->
				<div style="height: 65px;width: 25%;border: 0px solid red;line-height: 50px;margin-left: 20px;float: left;">
					<font class="fieldname">Country:</font>
					<select id="dowloadCountry" name="dowloadCountryName" style="width:170%;height:22px;border-color: #e2e5e7"></select>
					<div id="dowloadSpanel"></div>
				</div>
				<hr style="border: 1px solid #e5e5e5;width: 95%;"/>
				<div style="border: 0px solid red; width: 90%;height: 50px;margin-left: 27px;margin-top: 20px;">
					<div style="float: left;width: 3%;border: 0px solid blue;height: 25px;">
						<img src="/gbdbas/static/img/datasearch/tips.png" style="width:25px;"></img>
					</div>
					<div style="float: left;width: 30%;height: 25px; border: 0px solid green;margin-top: 2px;">
						<font size="2" style="line-height: 20px;">Query time of download data is up to 31 days.</font>
					</div>
					<div style="float: right;">
						<a class="btn_cls" title="立即下载" href="javascript:dowloadAllDataClick(this);" style="float: right;margin-top: 10px;">
						Immediate Download
						</a>
					</div>
				</div>
				<div id="提示" style="width: 50%; height: 30px; border:0px solid red;margin-left: 20px;">
				</div>
			</div>
		</div>
	  </div>
		<!-- 进度条 -->
<!-- 		<div id="clientProgress" name="clientProgress" style="display: none;"> -->
<!-- 			<span id="dbshowLoadStatus" name="dbshowLoadStatus" style="position: absolute;top: 230px; left: 350px; color: red;font-size: 14px;font-family:微软雅黑;"></span> -->
<!-- 			<div id="clientProgressBar" name="clientProgressBar" class="easyui-progressbar"  data-options="text:''" -->
<!-- 				style="position:absolute; width: 300px; height:13px;top: 250px; left: 350px; " /> -->
<!-- 		</div> -->
	</div>
	<jsp:include page="/view/common/footer/footer.jsp"></jsp:include>
</body>
</html>