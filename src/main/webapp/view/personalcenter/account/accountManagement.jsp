<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">  
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${root }/static/css/easyui/easyui.css" rel="stylesheet" type="text/css" />
<link href="${root }/static/css/common/subMenu/subMenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${root }/static/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${root }/static/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${root }/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${root }/static/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${root }/static/js/common/common-path.js"></script>
<script type="text/javascript" src="${root }/view/personalcenter/account/accountManagement.js"></script>
<title>账户管理</title>
<style type="text/css">
html,body {
    margin: 0;
    padding: 0!important;
    height: 100%;
}
</style>
</head>
<body  style="background-color: #f0f3f5;">
  <div style="margin:0 auto;text-align:left;padding: 0;width: 100%; min-height: 100%; height: auto!important;height: 100%;position: relative;background-color: #f0f3f5;">
	    <!--头部页面  -->
		<jsp:include page="/view/common/head/head.jsp"></jsp:include>
		<div style="margin:0 auto;text-align:left;padding-top: 50px;width: 1210px;top:40px;padding-bottom: 60px;">
			<div style="width:974px;position: absolute;padding-left: 200px;padding-top: 20px;">
				<div style="padding-bottom: 10px;">
			    	<span style="color: #0066cc;font-family: 宋体;font-size: 14px;">
			    		<img alt="消费记录" style="vertical-align:middle;" src="${root }/static/img/personalcenter/queryRight.png"> <b>我的消费记录</b>
			    	</span>
			    </div>
				<div id="sonAccountList" style="width:100%;padding-top: 7px;">
		           <table id="showUserAccount" class="easyui-datagrid" style="width:974px;height:150px"
				           url="/gbdbas/userAccount/searchMyAccount"  pagination="true"
				           rownumbers="true" fitColumns=true singleSelect="true" loadMsg="正在查询...">
				       <thead>
				           <tr>
				               <th field="userId" width="10" align="center" hidden="true">主键</th>
				               <th field="totalmoney" width="100" align="center">消费金额</th>
				               <th field="createtime" width="100" align="center">创建日期</th>
				               <th field="remarks" formatter="remarksFormat" width="100"  align="center">备注</th>
				           </tr>
				       </thead>
					</table>
			    </div>
			    <div style="width: 100%;margin-top: 20px;height: 30px;">
	 		 		<span style="color: #0066cc;font-family: 宋体;font-size: 14px;">
		    			购买信息
		    		</span>
			 	</div>
				<div>
				<c:if test="${sessionScope.user.userDesc ne '按次用户' }">
					<table style="height:200px" id='buyRightTable'  fitColumns="true" 
				         pagination="true" singleSelect="false" loadMsg="正在查询...">						
						<thead>
							<tr>
								<th field="byHsCode" Width="100" align="center">
									<font style="font-size: 14px;">海关编码</font>
								</th>
								<th field="byProductDesc" Width="150" align="center">
									<font style="font-size: 14px;">产品描述</font>
								</th>
								<th field="byCountry" Width="100" align="center">
									<font style="font-size: 14px;">国家</font>
								</th>
								<th field="startTime" Width="101" align="center">
									<font style="font-size: 14px;">开始时间</font>
								</th>
								<th field="endTime" Width="101" align="center">
									<font style="font-size: 14px;">结束时间</font>
								</th>
								<th field="iExportType" Width="100" align="center">
									<font style="font-size: 14px;">进出口类型</font>
								</th>
								<th field="openHistoryData" hidden="true"  Width="120" align="center">
									<font style="font-size: 14px;">是否开放历史数据</font>
								</th>
								<th field="openData" formatter="openHistoryDataFormat" Width="120" align="center">
									<font style="font-size: 14px;">是否开放历史数据</font>
								</th>
								<th field="historyStartTime" Width="100" align="center">
									<font style="font-size: 14px;">开放开始时间</font>
								</th>
								<th field="historyEndTime" Width="100" align="center">
									<font style="font-size: 14px;">开放截止时间</font>
								</th>
							</tr>
						</thead>
					</table>
				</c:if>
				<c:if test="${sessionScope.user.userDesc eq '按次用户' }">
					<table id='buyCountRightTable'  fitColumns="true" 
				         pagination="true" singleSelect="false" loadMsg="正在查询...">						
						<thead>
							<tr>
								<th field="totalNum" Width="130" align="center">
									<font style="font-size: 14px;">总次数</font>
								</th>
								<th field="remainNum" Width="130" align="center">
									<font style="font-size: 14px;">查看次数</font>
								</th>
								<th field="residueNum" Width="130" align="center">
									<font style="font-size: 14px;">剩余次数</font>
								</th>
							</tr>
						</thead>
					</table>
				</c:if>
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
</html>