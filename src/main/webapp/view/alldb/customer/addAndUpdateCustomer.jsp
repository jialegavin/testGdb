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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Add customer information</title>
<script type="text/javascript" src="${root }/view/alldb/customer/js/addAndUpdateCustomer.js"></script>
<style type="text/css">
	.characterStyle{font-size: 14px;color: #000000;}
	.longInputField{width: 174px;height:24px;border: 1px solid #95b8e7;border-color: #e5e5e5;border-radius:4px 4px 4px 4px;}
	.shortInputField{width:174px;height:24px;border: 1px solid #95b8e7;	border-color: #e5e5e5;border-radius:4px 4px 4px 4px;}
	.textAreaStyle{width:510px;height:120px;overflow-x:hidden;overflow-y:scroll;resize:none;border: 1px solid #95b8e7;border-color: #e5e5e5;border-radius:4px 4px 4px 4px;}
</style>
</head>

<body style="font-family:宋体;">
	<!--用户中心 添加客户信息 -->
	<div id="showCustomerDlg" style="width: 800px; height: 540px;display: none;">
		<div align="center">
			<span id="titleSpan" style="color: #478DE3;font-size: 14px;font-family: 宋体;font-weight: bold;"></span>
		</div>
		<form id="argentinaCustomerfm" method="post">
			<div>
				<div style="padding-top: 2px;padding-left: 80px;">
					<p>
						<font style="font-size: 14px; font-weight: border; color: #000000;padding-right:12px;">
							<fmt:message key="common.customerName" bundle="${messages}" />
						</font>
						<input style="width: 274px; height: 26px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;" name="companyName">
						<span id='showSpanOfPage' style="display: none;color: red;">Please input in English</span>
					</p>
				</div>
				<div style="padding-left: 80px;">
					<table width="100%" cellpadding="2" cellspacing="2">
						<tr>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.alternativeName" bundle="${messages}" />
								</font>
							</td>
							<td>
								<input name="alternativeName" type="text" id="alternativeName" class="longInputField" />
							</td>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.mainContactName" bundle="${messages}" />
								</font>
							</td>
							<td>
								<input name="contact" type="text" id="contact_id" class="shortInputField" />
							</td>
						</tr>
						<tr>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.country" bundle="${messages}" />
								</font>
							</td>
							<td>
								<select id="ecuador_companyCountry" style="width:174px; height: 28px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;" name="country">
								</select>
							</td>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.tel" bundle="${messages}" />
								</font>
							</td>
							<td>
								<input name="tel" type="text" id="argentina_tel_id" class="shortInputField" />
							</td>
						</tr>
						<tr>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.fax" bundle="${messages}" />
								</font>
							</td>
							<td>
								<input name="fax" type="text" id="fax" class="shortInputField" />
							</td>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.zip" bundle="${messages}" />
								</font>
							</td>
							<td>
								<input name="zip" type="text" id="zip" value="" class="shortInputField" />
							</td>
						</tr>
						<tr>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.email" bundle="${messages}" />
								</font>
							</td>
							<td>
								<input type="text" class="shortInputField" id="mailBox_id" name="mailBox" />
							</td>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.alternativeMail" bundle="${messages}" />
								</font>
							</td>
							<td>
								<input type="text" class="shortInputField" id="alternativeEmail" name="alternativeEmail" />
							</td>
						</tr>
						<tr>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.site" bundle="${messages}" />
								</font>
							</td>
							<td>
								<input type="text" class="longInputField" id="website" name="website" />
							</td>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.address" bundle="${messages}" />
								</font>
							</td>
							<td>
								<input name="address" type="text" id="argentina_CustomerAddr_id" class="shortInputField" />
							</td> 
						</tr>
						<tr>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.customerGroup" bundle="${messages}" />
								</font>
							</td>
							<td>
								<select id="userGrade" style="width:174px; height: 28px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;" name="userGrade">
									<option value="大客户">
										<fmt:message key="common.largeCustomers" bundle="${messages}" />
									</option>
									<option value="普通客户">
										<fmt:message key="common.ordinaryCustomers" bundle="${messages}" />
									</option>
								</select>
							</td>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.dataSources" bundle="${messages}" />
								</font>
							</td>
							<td>
								<select style="width:174px; height: 28px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;" name="datasource" id="datasource">
									<option value="来自第三方的数据">
										<fmt:message key="common.dataFromThirdParty" bundle="${messages}" />
									</option>
									<option value="朋友介绍的">
										<fmt:message key="common.friendsIntroduced" bundle="${messages}" />
									</option>
									<option value="网站">
										<fmt:message key="common.webSite" bundle="${messages}" />
									</option>
									<option value="广告">
										<fmt:message key="common.advertising" bundle="${messages}" />
									</option>
									<option value="代理渠道">
										<fmt:message key="common.distributionChannels" bundle="${messages}" />
									</option>
									<option value="其他">
										<fmt:message key="common.other" bundle="${messages}" />
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.trackingStatus" bundle="${messages}" />
								</font>
							</td>
							<td>
								<select style="width:174px; height: 28px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;" name="trackingStatus" id="trackingStatus">
									<option value="未接触">
										<fmt:message key="common.noContact" bundle="${messages}" />
									</option>
									<option value="初次联系">
										<fmt:message key="common.firstContact" bundle="${messages}" />
									</option>
									<option value="紧密联系">
										<fmt:message key="common.closely" bundle="${messages}" />
									</option>
									<option value="成交客户">
										<fmt:message key="common.contractCustomers" bundle="${messages}" />
									</option>
								</select>
							</td>
							<td>
								<font class="characterStyle" style="font-weight: lighter; color: #000000;">
									<fmt:message key="common.customerValue" bundle="${messages}" />
								</font>
							</td>
							<td>
								<input class="shortInputField" id="customerValue" name="customerValue">
								<font class="characterStyle">
									<fmt:message key="common.tenThousandYuan" bundle="${messages}" />
								</font>
							</td>
						</tr>
						<%-- <tr>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.contactNumber" bundle="${messages}" />
								</font>
							</td>
							<td>
								<input type="text" class="shortInputField" id="contactNum" name="contactNum" />
								&nbsp;
								<fmt:message key="common.times" bundle="${messages}" />
							</td>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.finallyContact" bundle="${messages}" />
								</font>
							</td>
							<td>
								<input type="text" class="shortInputField" id="finallyContact" name="finallyContact"/>
							</td>
						</tr> --%>
						<tr style="height: 10px"></tr>
						<tr>
							<td>
								<font class="characterStyle">
									<fmt:message key="common.remark" bundle="${messages}" />
								</font>
							</td>
							<td colspan="3">
								<textarea class="textAreaStyle" cols="80" rows="10" id="remark" name="remark">
								</textarea>
							</td>
						</tr>
						<input type="hidden" name="collectionId" id="collectionId" />
					</table>
				</div>
			</div>
		</form>
		<div id="buttonImage" style="padding-left: 280px; padding-top: 20px">
			<c:if test="${language eq 'message_zh_CN'}">
				<span id="sureSpan" style="padding-right: 13px;">
					<a class="confirmOrcancel_cls" title="保存" onclick="saveCustomer()">Save</a>
				</span>
				<span id="updateSpan" style="padding-right: 13px;">
					<a class="confirmOrcancel_cls" title="修改" onclick="updateCustomer()">Revise</a>
				</span>
				<span>
					<a class="confirmOrcancel_cls" title="取消" onclick="closeCustomer()">Cancel</a>
				</span>
			</c:if>
		</div>
	</div>
</body>
</html>