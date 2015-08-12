<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 国际化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String language = (String)request.getSession().getAttribute("language");
if(language == null || "".equals(language) || "pleaseSelect".equals(language))
    language = "message_zh_CN";
 String dateLanguage = "zh-cn";
	if(language.equals("message_en_US")){
		dateLanguage ="en";
	}
%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<fmt:setBundle basename="<%=language%>" var="messages" />
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<head>
<meta name="renderer" content="webkit">  
<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
	<div id="addUserdlg"  style="border-width:1px; width:620px;padding:10px 20px;overflow-x:hidden;display: none;">     
        <form id="userfm" method="post" enctype="multipart/form-data">
           <div class="fitem"  style="display:none">
               <font>userId：</font>
                 <input name="userId" id="userId" class="inputTextcss">
         	 </div>
    	  <div class="fitem"  style="display:none">
	           <font>用户身份：</font>
	           <input name="userDesc" id="userDesc" class="inputTextcss">
    	   </div>
           <div class="fitem">
               <font>User status：</font>
               <input id="inputLoginName"  name="loginName" class="inputTextcss"  onfocus="fouceAccount()" onblur="checkAccount()">
               <span class="help-inline"  id="userNameMessage" style="width:200px;padding-left: 8px;"></span>
           </div>
           <div class="fitem">
               <font>Password：</font>
               <input id="inputPassword" name="loginPassword" class="inputTextcss" onblur="checkPassword()" onfocus="foucsPassword()"> 
                        <span class="help-inline" id="passMessage" style="width:200px;padding-left: 8px;"></span>
           </div>
           <div class="fitem">
               <font>E-mail：</font>
               <input name="email" id="inputEmail" class="inputTextcss" onblur="checkEmail()">
            	<span class="help-inline" id="emailMessage" style="width:200px;padding-left: 8px;"></span>
           </div>
        <div class="fitem">
               <font>Tel:</font>
               <input name="tel" id="inputTel" class="inputTextcss" onblur="checkTel()">
            	<span class="help-inline" id="telMessage" style="width:200px;padding-left: 8px;"></span>
           </div>
            <div class="fitem">
               <font>Cell phone:</font>
               <input name="phone" id="inputPhone" class="inputTextcss" onblur="checkPhone()">
            	<span class="help-inline" id="phoneMessage" style="width:200px;padding-left: 8px;"></span>
           </div>
           <div class="fitem">
               <font>QQ:</font>
               <input name="qq" id="inputQQ" class="inputTextcss" onblur="checkQQ()">
            	<span class="help-inline" id="qqMessage" style="width:200px;padding-left: 8px;"></span>
           </div>
           <div class="fitem">
          	 	<font>Expiry date:</font>
          	 	<input id="beginTime" name="beginTime"  class="doubleinpText" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})"/> 
	            <span style="line-height:30px;">-</span>
	            <input id="endTime" name="endTime"  class="doubleinpText" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})"/> 
           </div>
          <div style="padding-left: 392px;padding-top: 30px">
              <a class="confirmOrcancel_cls" title="保存" onclick="operUserSon();">Save</a>
              <a class="confirmOrcancel_cls" title="取消" onclick="javascript:art.dialog({id:'addUserdlg'}).close();">Cancel</a>
           </div>
       </form>
  	</div>
  </body>
</html>
