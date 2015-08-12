
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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><fmt:message key="allCountry.addConpetitorTips" bundle="${messages}" /></title>
<head>
<script type="text/javascript" src="${root }/view/alldb/competitor/js/addAndUpdateCompetitor.js"></script>
<style type="text/css">
	.characterStyle{font-size: 14px;color: #0;}
	.longInputField{width: 174px;height:24px;border: 1px solid #95b8e7;border-color: #e5e5e5;border-radius:4px 4px 4px 4px;}
	.shortInputField{width:174px;height:24px;border: 1px solid #95b8e7;	border-color: #e5e5e5;border-radius:4px 4px 4px 4px;}
	.textAreaStyle{width:510px;height:120px;overflow-x:hidden;overflow-y:scroll;resize:none;border: 1px solid #95b8e7;border-color: #e5e5e5;border-radius:4px 4px 4px 4px;}
</style>
</head>

<body style="font-family:宋体;">
	<!--用户中心 添加竞争对手信息 -->
	<div id="showEcuadorCompetitorDlg" style="width: 800px; height: 540px;display: none;">
		<div align="center">
			<span id="titleSpan" style="color: #478DE3;font-size: 14px;font-family: 宋体;font-weight: bold;"></span>
		</div>
		<form id="competitorfmForAllDB" method="post">
			<div>
				<div style="padding-top: 2px;padding-left: 85px;">
					<p>
						<font style="font-size: 14px; font-weight: border; color: #000000;padding-right:25px;">
							Company name
						</font> 
						<input name="companyName" style="width: 274px; height: 26px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;"/>
						<span id='showSpanOfPage' style="display: none;color: red;">Please enter English</span>
					</p>
				</div>
				
				<div style="padding-left: 80px;">
					<table width="100%" cellpadding="2" cellspacing="2">
						<tr>
							<td>
								<font class="characterStyle">
									Alternative Name
								</font>
							</td>
							<td style="width: 248px;">
								<input name="alternativeName" type="text" id="alternativeName" class="longInputField" />
							</td>
							<td>
								<font class="characterStyle">
									Contact person
								</font>
							</td>
							<td>
								<input name="contact" type="text" id="contact" class="shortInputField" />
							</td>
						</tr>
						<tr>
							<td>
								<font class="characterStyle">
									Country
								</font>
							</td>
							<td>
								<select id="ecuador_companyCountry" style="width:174px; height: 28px;border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;" name="country">
								</select> 
							</td>
							
							<td>
								<font class="characterStyle">
									Tel
								</font>
							</td>
							<td>
								<input name="tel" type="text" id="tel" class="shortInputField" />
							</td>
						</tr>
						<tr>
							<td>
								<font class="characterStyle">
									Fax
								</font>
							</td>
							<td>
								<input name="fax" type="text" id="fax" class="shortInputField" />
							</td>
							
							<td>
								<font class="characterStyle">
									Zip code
								</font>
							</td>
							<td>
								<input name="zip" type="text" id="zip" value="" class="shortInputField" />
							</td>
						</tr>
						<tr>
							<td>
								<font class="characterStyle">
									Email
								</font>
							</td>
							<td>
								<input type="text" class="shortInputField" id="mailBox" name="mailBox" />
							</td>
							<td>
								<font class="characterStyle">
									Alternative Email
								</font>
							</td>
							<td>
								<input type="text" class="shortInputField" id="alternativeEmail" name="alternativeEmail" />
							</td>
						</tr>
						<tr>
							<td>
								<font class="characterStyle">
									Company Website
								</font>
							</td>
							<td>
								<input type="text" class="longInputField" id="website" name="website" />
							</td>
							<td>
								<font class="characterStyle">
									Contact Address
								</font>
							</td>
							<td>
								<input name="address" type="text" id="mCustomerAddr" class="shortInputField" />
							</td>
						</tr>
						<tr>
							<td>
								<font class="characterStyle"  style="font-weight: lighter; color: #000000;">
									Customer Value
								</font>
							</td>
							<td>
								<input class="shortInputField" id="customerValue" name="customerValue"> 
								<font class="characterStyle">
									10k
								</font>
							</td>
							<td>
								<font class="characterStyle">
									Data Source
								</font>
							</td>
							<td>
								<select style="width:174px; height: 28px; border: 1px solid #95b8e7; border-color: #e5e5e5; border-radius: 4px 4px 4px 4px;"
									name="datasource" id="datasource">
										<option value="来自第三方的数据">
											Data from a third party
										</option>
										<option value="朋友介绍的">
											Friend's Recommendation
										</option>
										<option value="网站">
											Website
										</option>
										<option value="广告">
											Advertizement
										</option>
										<option value="代理渠道">
											Distribution Channel
										</option>
										<option value="其他">
										Other
										</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<font class="characterStyle">
									Remarks
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
					<a class="confirmOrcancel_cls" title="保存" onclick="saveCompetitorForAllDB()">Save</a>
				</span>
				<span id="updateSpan" style="padding-right: 13px;">
					<a class="confirmOrcancel_cls" title="修改" onclick="updateCompetitorForAllDB()">Refine</a>
				</span>
				<span>
					<a class="confirmOrcancel_cls" title="取消" onclick="closeCompetitorForAllDB()">Delete</a>
				</span>
			</c:if>
		</div>
	</div>
</body>
</html>
