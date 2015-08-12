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
<fmt:setBundle basename="<%=language%>" var="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title><fmt:message key="allCountry.buyerRepositoryTable" bundle="${messages}"/></title>
</head>
<body>
	<div id="mybuyerDlg"  style="width:100%;height:500px;display: none;border: 0px solid red;">
		
		<div id="searchbuyer" style="padding:0px 0px 0px 0px;width:80%;height: 58px;background-color: #ffffff;border-bottom-color: #ffffff;border-bottom-width: 1px;">
		    <span style="font-size: 16px;color: #77807f"><fmt:message key="allCountry.country" bundle="${messages}"/>:</span>
		    <input class="easyui-combobox" id="Selcountry" name="Selcountry" style="width: 198px;" data-options="valueField:'id', textField:'text', panelHeight:'auto'">&nbsp;&nbsp;
<!-- 		    <font class="fieldname">时间跨度&nbsp;:&nbsp;</font> -->
<!-- 			<input id="buyerBeginDate" value="2012-01-01" -->
<!-- 				style="width: 88px; height: 20px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;" -->
<%-- 				onclick="WdatePicker({lang:'<%=dateLanguage %>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})" /> --%>
<!-- 			<font class="henggang">-</font>  -->
<!-- 			<input style="width: 88px; height: 20px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;" -->
<%-- 				id="buyerEndDate" value="2012-12-31" onclick="WdatePicker({lang:'<%=dateLanguage %>',quickSel:['2010-12-31','2011-12-31','2012-12-31','2013-12-31','2014-12-31'],readOnly:true})" /> --%>
				&nbsp;&nbsp;
			<a class="btn_cls" onclick="queryBuyer(this)" title="立即查询">Immediate Search</a>
		</div>
		
		<!-- 进度条 -->
<!-- 		<div id="clientProgress" name="clientProgress" style="display:none"> -->
<!-- 			<span id="dbshowLoadStatus" name="dbshowLoadStatus" style="position: absolute;top: 230px; left: 350px; color: red;font-size: 14px;font-family:微软雅黑;"></span> -->
<!-- 			<div id="clientProgressBar" name="clientProgressBar" class="easyui-progressbar" style="position:absolute; width: 300px; height:13px;top: 250px; left: 350px; " /> -->
<!-- 		</div> -->
	
	
		<table id="buyerdg" title="买家资源库" class="easyui-datagrid" style="width:900px;height:400px"
	           pagination="true" fitColumns="true" singleSelect="true" 
	            loadMsg="正在加载中……">	
	       <thead>
	           <tr>
	               <th field="country" width="150" align="center" formatter="isNullFormat"><span style="font-size: 14px;"><fmt:message key="allCountry.homeCountry" bundle="${messages}"/></span></th>
	               <th field="companyName" width="150" align="center" formatter="isNullFormat"><span style="font-size: 14px;"><fmt:message key="allCountry.companyName" bundle="${messages}"/></span></th>
	               <th field="hscode" width="150" align="center" formatter="isNullFormat"><span style="font-size: 14px;"><fmt:message key="allCountry.hscode" bundle="${messages}"/></span></th>
	               <th field="goodsDescription" width="150" align="center" formatter="isNullFormat"><span style="font-size: 14px;"><fmt:message key="allCountry.goodsdesp" bundle="${messages}"/></span></th>
	               <th field="opt" width="180" align="center" formatter="addDetials"><span style="font-size: 14px;">Add Customer</span></th> 
	           </tr>
	       </thead>
		</table>
		
	</div>
</body>
</html>