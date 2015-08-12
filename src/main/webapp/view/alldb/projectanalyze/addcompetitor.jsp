<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 国际化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String language = (String)request.getSession().getAttribute("language");
if(language == null || "".equals(language) || "pleaseSelect".equals(language))
    language = "message_zh_CN";
%>
<fmt:setBundle basename="<%=language%>" var="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title><fmt:message key="allCountry.addConpetitorTips" bundle="${messages}"/></title>
    <head>
    </head>
  <body style="font-family: 微软雅黑;">
    <!--用户中心 添加竞争对手信息 -->
	    <div id="CompetitorDlgForAllDB" title=" " class="easyui-dialog" style="width:950px;height:610px;padding:10px 20px" closed="true" modal="true">        
		 	<div class="formtitle" align="center"><fmt:message key="allCountry.addCompetitor" bundle="${messages}"/></div>        
		 	<form id="competitorfmForAllDB" method="post">
		 	<div>
				<div style="padding-top:2px">
					<p><font style="font-size: 18px;font-weight:lighter;color:#EC5565;"><fmt:message key="allCountry.companyNameColon" bundle="${messages}"/></font><input id="CompanyNamesForAllDB" name="companyName" style="width: 274px;height:26px;border: 1px solid #95b8e7;border-color: #e5e5e5;border-radius:4px 4px 4px 4px;"></p>
				</div>
		 		<div style="padding-left: 40px;padding-top:9px;">
			 		<table width="100%" cellpadding="2" cellspacing="2">
			 			 <tr>
				 			 <td><font class="characterStyle"><fmt:message key="allCountry.alternativeName" bundle="${messages}"/></font></td>
	 						 <td >
	     						<input name="alternativeName" type="text" id="alternativeName" class="longInputField"/> 
							 </td>
	 						 <td><font class="characterStyle"><fmt:message key="allCountry.competitorContacts" bundle="${messages}"/></font></td>
	 					     <td>
								 <input name="contact" type="text" id="contact" class="shortInputField" />
	    					</td>
	 					</tr>
			 			 <tr>
				 			 <td><font class="characterStyle"><fmt:message key="allCountry.country" bundle="${messages}"/></font></td>
	 						 <td width="345" colspan="3"> 
	 						  	<select id="dbAll_CompetitorCountry"  style="width: 200px;height:24px;border: 1px solid #95b8e7;border-color: #e5e5e5; border-radius:4px 4px 4px 4px;" name="country"></select>
							 </td>
	 					</tr>
	 					 <tr>
				 			 <td><font class="characterStyle"><fmt:message key="allCountry.phone" bundle="${messages}"/></font></td>
	 						 <td >
	     						<input name="tel" type="text" id="tel" class="shortInputField" /> 
							 </td>
	 						 <td><font class="characterStyle"><fmt:message key="allCountry.fax" bundle="${messages}"/></font></td>
	 					     <td>
								 <input name="fax" type="text" id="fax" class="shortInputField" />
	    					</td>
	 					</tr>
	 					 <tr>
				 			 <td><font class="characterStyle"><fmt:message key="allCountry.address" bundle="${messages}"/></font></td>
	 						 <td >
	     						<input name="address" type="text" id="mCustomerAddr" class="inputField"/> 
							 </td>
	 						 <td><font class="characterStyle"><fmt:message key="allCountry.zipCode" bundle="${messages}"/></font></td>
	 					     <td>
								 <input name="zip" type="text" id="zip" value="" class="shortInputField"/>
	    					</td>
	 					</tr>
	 					 <tr>
				 			 <td><font class="characterStyle"><fmt:message key="allCountry.email" bundle="${messages}"/></font></td>
	 						 <td width="345" colspan="3"> 
	     						<input type="text" class="shortInputField" id="mailBox" name="mailBox"/>
							 </td>
	 					</tr>
	 					 <tr>
				 			 <td><font class="characterStyle"><fmt:message key="allCountry.alternativeEmail" bundle="${messages}"/></font></td>
	 						 <td width="345" colspan="3"> 
	     						<input type="text" class="shortInputField" id="alternativeEmail" name="alternativeEmail"/>
							 </td>
	 					</tr>
	 					 <tr>
				 			 <td><font class="characterStyle"><fmt:message key="allCountry.website" bundle="${messages}"/></font></td>
	 						 <td width="345" colspan="3"> 
	     						<input type="text" class="longInputField" id="website" name="website"/>
							 </td>
	 					</tr>
	 					<tr>
	 					 	 <td><font class="characterStyle" style="font-weight: lighter;color: #EC5565"><fmt:message key="allCountry.customerValue" bundle="${messages}"/></font></td>
	 					     <td>
		 					   	<input style="width: 195px;height:24px; border-radius:4px;border: 1px solid #e5e5e5;" id="customerValue">&nbsp;<fmt:message key="allCountry.tenthousand" bundle="${messages}"/>
	    					</td>
	    				</tr>
	    				<tr>
	 						 <td><font class="characterStyle"><fmt:message key="allCountry.dataSource" bundle="${messages}"/></font></td>
	 					     <td>
		 					    <select style="width: 200px;height:24px;border: 1px solid #95b8e7;border-color: #e5e5e5; border-radius:4px 4px 4px 4px;" name="datasource" id="datasource">
		 					    	<option value="<fmt:message key="allCountry.fromThirdParty" bundle="${messages}"/>"><fmt:message key="allCountry.fromThirdParty" bundle="${messages}"/></option>
									<option value="<fmt:message key="allCountry.friendIntroduced" bundle="${messages}"/>"><fmt:message key="allCountry.friendIntroduced" bundle="${messages}"/></option>
									<option value="<fmt:message key="allCountry.website" bundle="${messages}"/>"><fmt:message key="allCountry.website" bundle="${messages}"/></option>
									<option value="<fmt:message key="allCountry.advertisement" bundle="${messages}"/>"><fmt:message key="allCountry.advertisement" bundle="${messages}"/></option>
									<option value="<fmt:message key="allCountry.ProxyWay" bundle="${messages}"/>"><fmt:message key="allCountry.ProxyWay" bundle="${messages}"/></option>
									<option value="<fmt:message key="allCountry.others" bundle="${messages}"/>"><fmt:message key="allCountry.others" bundle="${messages}"/></option>
								</select>
	    					</td>
	 					</tr>
	 					<tr>
				 			 <td><font class="characterStyle"><fmt:message key="allCountry.remark" bundle="${messages}"/></font></td>
	 						 <td width="345" colspan="3"> 
	     						<textarea  class ="textAreaStyle" style="width: 680px" cols="40" rows="10" id="remark" name="remark"></textarea>
							 </td>
	 					</tr>
			 		</table>
		 		</div>
		 	</div>
		    </form>   
			<div id="buttonImage" style="padding-left: 630px;padding-top: 20px">
			   <c:if test="${language eq 'message_zh_CN'}">
	     		   <input type="image" src="${root }/static/img/personalcenter/addok.png" onclick="saveCompetitorForAllDB();"/>&nbsp;&nbsp; 
			       <input type="image" src="${root }/static/img/personalcenter/addcancel.png" onclick="closeCompetitorForAllDB();"/>
	     	   </c:if>
	     	   <c:if test="${language eq 'message_en_US'}">
	     		  <input type="image" src="${root }/static/img/personalcenter/addok.png" onclick="saveCompetitorForAllDB();"/>&nbsp;&nbsp; 
			 	  <input type="image" src="${root }/static/img/personalcenter/addcancel_en.png" onclick="closeCompetitorForAllDB();"/>
	     	   </c:if>
		    </div>
		 </div> 
  </body>
</html>
