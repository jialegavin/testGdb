<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户授权</title>
</head>
<body style="background-color: #F0F0F0;">
  	<div id="RightInfo" style="float:left;margin-left: 25px;">
  		<div style="margin-bottom:10px;">
	  		<button type="button" onclick="allselect()">全选</button>
			<button type="button" onclick="allNot()">全不选</button>
			<button type="button" onclick="submitRightForm()">提交权限</button>
			<button type="button" onclick="closeRightForm()">关闭授权窗口</button>
		</div>
	  	<form name="rightInfoForm" id="rightInfoForm" action="" method="post"  onSubmit="return checkRightSubmit();">
			<table>
				<c:forEach items="${sessionScope.rightMap}" var="m">
					<tr id="title_id">
						<c:if test="${m.key eq 'auth_hscode' }">
							<th></th>
							<th>国家</th>
							<th>贸易类型</th>
							<th>Hscode</th>
							<th>开始日期</th>
							<th>截止日期</th>
							<th>历史数据开关</th>
							<th>历史数据开始日期</th>
							<th>历史数据截止日期</th>
							<th>赠国外开放开始日期</th>
							<th>赠国外开放截止日期</th>
						</c:if>
						<c:if test="${m.key eq 'auth_desc' }">
							<th></th>
							<th>国家</th>
							<th>贸易类型</th>
							<th>产品描述</th>
							<th>开始日期</th>
							<th>截止日期</th>
						</c:if>
						<c:if test="${m.key eq 'auth_country' }">
							<th></th>
							<th>国家</th>
							<th>贸易类型</th>
							<th>开始日期</th>
							<th>截止日期</th>
							<th>历史数据开关</th>
							<th>历史数据开始日期</th>
							<th>历史数据截止日期</th>
							<th>赠国外开放开始日期</th>
							<th>赠国外开放截止日期</th>
						</c:if>
					</tr>
					<c:forEach items="${m.value}" var="item" varStatus="status">
		  				<tr>
		  					<td><input type="checkbox" id="checkid${status.count }" name="checkid"/></td>
		  					<td><input style="width: 100px;" type="text" id="byCountry${status.count }" name="byCountry" value="${item.byCountry }" readonly="readonly"></td>
		  					<td><input style="width: 100px;" type="text" id="iExportType${status.count }" name="iExportType" value="${item.iExportType }" readonly="readonly"></td>
		  					<c:if test="${m.key eq 'auth_hscode' }">
		  						<td>
		  							<input style="width: 100px;" type="text" id="byHsCode${status.count }" name="byHsCode" >
		  						</td>
		  					</c:if>
		  					<c:if test="${m.key eq 'auth_desc' }">
		  						<td>
		  							<input type="text" id="byProductDesc${status.count }" name="byProductDesc">
		  						</td>
		  					</c:if>
		  					<td>
		  						<c:if test="${fn:length(item.startTime)==10}">
		  							<input style="width: 100px;" id="startTime${status.count }" name="startTime" class="dateinpText" value="${item.startTime }" onclick="WdatePicker({minDate:'${item.startTime }',dateFmt:'yyyy-MM-dd',lang:'auto',readOnly:true})">
		  						</c:if>
		  						<c:if test="${fn:length(item.startTime)==7}">
		  							<input style="width: 100px;" id="startTime${status.count }" name="startTime" class="dateinpText" value="${item.startTime }" onclick="WdatePicker({minDate:'${item.startTime }',dateFmt:'yyyy-MM',lang:'auto',readOnly:true})">
		  						</c:if>
		  					</td>
		  					<td>
		  						<c:if test="${fn:length(item.startTime)==10}">
		  							<input style="width: 100px;" id="endTime${status.count }" name="endTime" class="dateinpText" onclick="WdatePicker({lang:'auto',readOnly:true,dateFmt:'yyyy-MM-dd'})">
		  						</c:if>
		  						<c:if test="${fn:length(item.startTime)==7}">
		  							<input style="width: 100px;" id="endTime${status.count }" name="endTime" class="dateinpText" onclick="WdatePicker({lang:'auto',readOnly:true,dateFmt:'yyyy-MM'})">
		  						</c:if>
		  					</td>
		  					<c:if test="${item.byCountry  eq '中国'}">
		  						<c:if test="${item.iExportType  eq '进口'}">
			  						<td style="width: 100px;">
			  						<input type="radio" checked="checked" id="openHistoryData${status.count }" name="openHistoryData_import" value="true">YES
			  						<input type="radio"  id="openHistoryData${status.count }" name="openHistoryData_import" value="false">NO
			  						</td>
		  						</c:if>
		  						<c:if test="${item.iExportType  eq '出口'}">
			  						<td style="width: 100px;">
			  						<input type="radio" checked="checked" id="openHistoryData${status.count }" name="openHistoryData_export" value="true">YES
			  						<input type="radio"  id="openHistoryData${status.count }" name="openHistoryData_export" value="false">NO
			  						</td>
		  						</c:if>
		  						<td>
			  						<c:if test="${fn:length(item.startTime)==10}">
			  							<input style="width: 100px;" id="historyStartTime${status.count }" name="historyStartTime" class="dateinpText" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'auto',readOnly:true})">
			  						</c:if>
			  						<c:if test="${fn:length(item.startTime)==7}">
			  							<input style="width: 100px;" id="historyStartTime${status.count }" name="historyStartTime" class="dateinpText" onclick="WdatePicker({dateFmt:'yyyy-MM',lang:'auto',readOnly:true})">
			  						</c:if>
		  						</td>
		  						<td>
			  						<c:if test="${fn:length(item.startTime)==10}">
			  							<input style="width: 100px;" id="historyEndTime${status.count }" name="historyEndTime" class="dateinpText" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'auto',readOnly:true})">
			  						</c:if>
			  						<c:if test="${fn:length(item.startTime)==7}">
			  							<input style="width: 100px;" id="historyEndTime${status.count }" name="historyEndTime" class="dateinpText" onclick="WdatePicker({dateFmt:'yyyy-MM',lang:'auto',readOnly:true})">
			  						</c:if>
		  						</td>
		  						<td>
			  						<c:if test="${fn:length(item.startTime)==10}">
			  							<input style="width: 100px;" id="treeStartTime${status.count }" name="treeStartTime" class="dateinpText" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'auto',readOnly:true})">
			  						</c:if>
			  						<c:if test="${fn:length(item.startTime)==7}">
			  							<input style="width: 100px;" id="treeStartTime${status.count }" name="treeStartTime" class="dateinpText" onclick="WdatePicker({dateFmt:'yyyy-MM',lang:'auto',readOnly:true})">
			  						</c:if>
		  						</td>
		  						<td>
			  						<c:if test="${fn:length(item.startTime)==10}">
			  							<input style="width: 100px;" id="treeEndTime${status.count }" name="treeEndTime" class="dateinpText" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'auto',readOnly:true})">
			  						</c:if>
			  						<c:if test="${fn:length(item.startTime)==7}">
			  							<input style="width: 100px;" id="treeEndTime${status.count }" name="treeEndTime" class="dateinpText" onclick="WdatePicker({dateFmt:'yyyy-MM',lang:'auto',readOnly:true})">
			  						</c:if>
		  						</td>
		  					</c:if>		  				
		  				</tr>
		  			</c:forEach>					
				</c:forEach>
			</table>
	  	</form>
  	</div>
</body>
</html>