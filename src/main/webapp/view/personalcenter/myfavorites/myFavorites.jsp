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
	if(language.equals("message_en_US"))
	{
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
		<link href="${root }/static/css/easyui/easyui.css" rel="stylesheet" type="text/css" />
		<link href="${root }/static/css/common/subMenu/subMenu.css" rel="stylesheet" type="text/css" />
		<link href="${root }/static/css/common/button/button.css" rel="stylesheet" type="text/css" />
		<link href="${root }/static/css/datasearch/datasearch.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"  src="${root }/static/js/common/common.js"></script>
		<script type="text/javascript"  src="${root }/static/js/jquery/jquery.js"></script>
		<script type="text/javascript"  src="${root }/static/js/jquery/jquery-1.8.2.min.js"></script>
		<script type="text/javascript"  src="${root }/static/js/jquery/jquery.PrintArea.js"></script>
		<script type="text/javascript"	 src="${root }/static/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript"	 src="${root }/static/js/easyui/locale/easyui-lang-en.js"></script>
		<script type="text/javascript"	 src="${root }/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
		<script type="text/javascript"	 src="${root }/static/js/artdialog/iframeTools.source.js"></script>
		<script type="text/javascript" src="${root }/static/js/My97DataPicker/WdatePicker.js"></script>
		<script type="text/javascript"	 src="${root }/static/js/artdialog/artdialogopen.js"></script>
		<script type="text/javascript"	 src="${root }/static/js/common/common-path.js"></script>
		<script type="text/javascript"	 src="${root }/view/personalcenter/myfavorites/myFavorites.js"></script>
</head>
<body style="background-color: #f0f3f5;"> 
	<div style="margin:0 auto;text-align:left;padding: 0;width: 100%; min-height: 100%; height: auto!important;height: 100%;position: relative;background-color: #f0f3f5">
	    <!--头部页面  -->
	    <div style="height:120px">
			<jsp:include page="/view/common/head/head.jsp"></jsp:include>
		</div>
		<div style="margin:0 auto;text-align:left;width: 1210px;top:40px;">
			<div style="background-color: #f0f3f5;width:190px;display:inline-block;vertical-align:top;padding-top: 20px;">
				<jsp:include page="/view/common/subMenu/subMenu.jsp"></jsp:include>
			</div>
			<div id="userCollectionDiv" style="width: 1000px; display:inline-block">
				<div style="padding-bottom: 10px;padding-top: 32px;">
			    	<span style="color: #0066cc;font-family: 宋体;font-size: 14px;">
			    		<img alt="我的收藏夹" style="vertical-align:middle;" src="${root }/static/img/personalcenter/favorites.png"> My Favorites
			    	</span>
			    </div>
				<!-- 查询条件 -->
				<div style="font-size: 12px;padding-bottom: 5px;">
					<!-- hscode -->
					HS code：
					<input id="faHscode" class="inpText"/>&nbsp;&nbsp;
					<!-- 国家 -->
					Country：
					<select id="faCountry" name="faCountry" style="width: 100px;border:1px solid #e5e5e5;border-radius: 4px 4px 4px 4px;height: 26px;">
						<option value="">Please Select a Country</option>
					</select>&nbsp;&nbsp;
					<!-- 时间 -->
					Collection data：
					<input name="beginDate"  class="doubleinpText" id="beginDate" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})" />
				    <font class="henggang">-</font> 
				    <input name="endDate" class="doubleinpText" id="endDate" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-12-31','2011-12-31','2012-12-31','2013-12-31','2014-12-31'],readOnly:true})" />&nbsp;&nbsp;
				    <!-- 立即查询按钮 -->
				    <a class="sub_cls" title="查询" onclick="queryAllFav()">Query</a>
				    <!-- 删除按钮 -->
				    <a class="btn_alldb_remove" onclick="delFaving()" title="删除" href="javascript:void(0);" style="float: right;">&nbsp;&nbsp;Delete</a>
				</div>
			    <!-- 用户收藏表 -->
			    <table id='collectionTable' fitColumns="false" pagination="true" singleSelect="false" loadMsg="正在查询..." checkbox="true" style="height: 300px;">		
					<thead>
						<tr>
							<th field="ck" checkbox="true"></th>
							<th field="name" Width="100" align="center">
								<font style="font-size: 14px;">User Name</font>
							</th>
							<th field="saveTime" Width="150" align="center">
								<font style="font-size: 14px;">Time Saved</font>
							</th>
							<th field="queryKey" Width="201" align="center" formatter="formatTitle">
								<font style="font-size: 14px;">Chinese Search Conditions</font>
							</th>
							<th field="queryValue" Width="201" align="center" formatter="formatTitle">
								<font style="font-size: 14px;">English Search Conditions</font>
							</th>
							<th field="country" Width="150" align="center">
								<font style="font-size: 14px;">Country</font>
							</th>
							<th data-options="field:'id',width:150,formatter:  searchData"  align="center">
								<font style="font-size: 14px;">Details</font>
							</th>
						</tr>
					</thead>
				</table>
			 	<div id="searchResult" style="display:none;width: 100%;margin-top: 20px;height: 30px;">
	 		 		<span style="color: #0066cc;font-family: 宋体;font-size: 14px;">
		    			View details
		    		</span>
			 	</div>
				<div id="autoExpandFav">
					<div id = "resultFieldDiv" style="display: none;margin:0 auto;">
					</div>
					<div id="selectFieldDialog" style="display: none; z-index: 999; position: relative; top: -270px; border: 1px solid rgb(212, 212, 212); width: 300px; height: auto; background-color: rgb(255, 255, 255);" >
						<div style="padding-top: 10px;background-color: #F5FAF9;width: 300px;height: 30px;"><b><font color="#1369c0" style="font-size:16px;padding-left: 36px">请选择显示字段</font></b></div>
						<div id="selectFieldDiv" style="width:300px;font-size: 12px;">
						</div>
						<div id="selectFieldButton" style="padding:40px 0px 30px 60px;z-index: 110;">
							<a class="btn_cls" title="保存" onclick="selectField();">Save</a>
							<a class="btn_cls" title="取消" onclick="javascript:$('#selectFieldDialog').css('display','none');">Cancel</a>
						</div>
					</div>	
				</div>
			</div>
		</div>
			<!-- xl 添加页尾 -->
			<div style="margin-top:0px;">
			 	<jsp:include page="/view/common/footer/footer.jsp"></jsp:include>
			</div>
	</div>
</body>
	<!-- 查看详情页面 -->
	<jsp:include page="/view/datasearch/dialog/viewdetail.jsp"></jsp:include>
	<!-- 添加我的客户对话框 -->
	<jsp:include page="/view/alldb/customer/addAndUpdateCustomer.jsp"></jsp:include>
	<!-- 添加竞争对手对话框 -->
	<jsp:include page="/view/alldb/competitor/addAndUpdateCompetitor.jsp"></jsp:include> 
</html>