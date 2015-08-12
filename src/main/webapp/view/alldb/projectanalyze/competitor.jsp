<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 国际化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String language = (String) request.getSession().getAttribute(
			"language");
	if (language == null || "".equals(language)
			|| "pleaseSelect".equals(language))
		language = "message_zh_CN";
%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<fmt:setBundle basename="<%=language%>" var="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Product tag/ competitor's information</title>
<style type="text/css">
.body_style {
	font-family: "Microsoft Yahei", Heiti, arial, helvetica,
		sans-serif !important;
}

#bqjzsearchtools span {
	font-size: 16px;
	color: #77807f;
}
</style>
</head>
<body>
	<div id="bqCompetitors" style="width: 100%; height: 550px; display: none;border: 0px solid red;overflow: scroll;">
		<div id="bqjzsearchtools" style="padding: 15px 0px 5px 0px; width:80%; height: 58px; background-color: #ffffff; border-bottom-color: #ffffff; border-bottom-width: 1px;">
			<span>Country name:</span> 
				<select id="competorCountry" name="competorCountry" style="width: 198px; height: 24px;">
				</select>
			<div id="bqspanel"></div>
			<span style="margin-left: 36px;">
				Company name:</span> 
				<input type="text" id="bqcompanyName" value="" style="width: 198px; 
				height: 24px; border: 1px solid #e5e5e5; border-radius: 4px; margin-right: 36px;" />
			<a class="btn_cls" href="#" onclick="queryCompany();" title="立即查询">Immediate Search</a>
			<input type="hidden" id="competitorHsCode" name="competitorHsCode"/>
			<input type="hidden" id="competitorGoodsDesp" name="competitorGoodsDesp"/>
		</div>
		<!-- 竞争对手 -->
		<table id="bqCompetitorsdg" title="竞争对手信息"
			style="width: 990px; height: 380px"
			pagination="true" pageList = '[200,300,400,500]'
			singleSelect="true" autoRowHeight="false"
			loadMsg="正在查询">
			<thead>
				<tr>
					<th field="country" width="160" align="center" formatter="isNullFormat">
						<span style="font-size: 14px;">
							Country name
						</span>
					</th>
					<th field="exporter" width="140" align="center" formatter="isNullFormat">
						<span style="font-size: 14px;">
							Exporter
						</span>
					</th>
					<th field="tradeCount" width="100" align="center" sortable="true">
						<span style="font-size: 14px;">
							Number of trades
						</span>
					</th>
					<th field="tradeMoney" width="100" align="center" sortable="true" formatter="checkValueSmall">
						<span style="font-size: 14px;">
							Amount of money
						</span>
					</th>
					<th field="tradeWeight" width="100" align="center" sortable="true" formatter="checkValueSmall">
						<span style="font-size: 14px;">
							Weight
						</span>
					</th>
					<th field="tradeQuantity" width="100" align="center" sortable="true" formatter="checkValueSmall">
						<span style="font-size: 14px;">
							Amount
						</span>
					</th>
					<th field="opt" width="280" formatter="bqaddOpss" align="center">
						<span style="font-size: 14px;">
							Other Functions
						</span>
					</th>
				</tr>
			</thead>
		</table>
		<div style="padding-top: 14px;"></div>
		<!-- 采购商 -->
		<div id="procurementDiv" style="display: none;width: 990px; height: 380px;">
			<table id="bquyerCompetitorsdg" title="竞争对手采购商信息" style="width: 990px; height: 400px;"
				pagination="true" singleSelect="true" autoRowHeight="false"
				loadMsg="正在查询...">
			  <thead>
				<tr>
					<th field="id" width="40" align="center" hidden="true" formatter="isNullFormat">
						<span style="font-size: 14px;">
							Number
						</span>
					</th>
					<th field="importer" width="180" align="center" formatter="isNullFormat">
						<span style="font-size: 14px;">
							Importer
						</span>
					</th>
					<th field="tradeQuantity" width="120" align="center" sortable="true" formatter="checkValueSmall">
						<span style="font-size: 14px;">
							Amount
						</span>
					</th>
					<th field="tradeWeight" width="120" align="center" sortable="true" formatter="checkValueSmall">
						<span style="font-size: 14px;">
							Weight
						</span>
					</th>
					<th field="tradeMoney" width="120" align="center" sortable="true" formatter="checkValueSmall">
						<span style="font-size: 14px;">
							Amount of money
						</span>
					</th>
					<th field="country" width="160" align="center">
						<span style="font-size: 14px;">
							Country
						</span>
					</th>
					<th field="opt" width="280" formatter="bqaddOpssbuyer" align="center">
						<span style="font-size: 14px;">
							Other Functions
						</span>
					</th>
				</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
	<!-- 添加竞争对手 -->
	<jsp:include page="/view/alldb/competitor/addAndUpdateCompetitor.jsp"></jsp:include>
	<!-- 添加采购商 -->
	<jsp:include page="/view/alldb/customer/addAndUpdateCustomer.jsp"></jsp:include>
</html>