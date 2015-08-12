<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 国际化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String language = (String)request.getSession().getAttribute("language");
	if(language == null || "".equals(language) || "pleaseSelect".equals(language))
	   language = "message_zh_CN";
%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<fmt:setBundle basename="<%=language%>" var="messages" />
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Change Password</title>
    <link rel="stylesheet" type="text/css" href="${root}/static/css/common/button/button.css">
    <link rel="stylesheet" type="text/css" href="${root }/static/css/personalcenter/password/tabLogin.css">
    <script type="text/javascript"  src="${root }/view/common/head/updatepasswd/updatePassword.js"></script>
  </head>
  <body>
	<!--用户中心 修改密码 -->
	<div id="updatePwdDlg" style="margin:0 auto;padding: 0;display: none;">
		<div style="margin:0 auto;width: 400px;height:200px;">
			<form id="pwdfm" method="post" style="padding-left: 50px;">
				<div class="pwditem">
					<label style="font-size: 12px; font-family: 'microsoft yahei'">
						<fmt:message key="userCenter.oldPwd" bundle="${messages}"/>
					</label>
					<input id="userPassword" class = "passwordfield" name="userPassword" type="password" />
				</div>
				<div class="pwditem">
					<label style="font-size: 12px; font-family: 'microsoft yahei'">
						<fmt:message key="userCenter.newPwd" bundle="${messages}"/>
					</label>
					<input id="newPassword" class = "passwordfield" name="newPassword" type="password" />
				</div>
				<div class="pwditem">
					<label style="font-size: 12px; font-family: 'microsoft yahei'">
						<fmt:message key="userCenter.sureNewPwd" bundle="${messages}"/>
					</label>
					<input id="surePassword" class = "passwordfield" name=surePassword type="password" />
				</div>
			</form>
			<div style="padding-left: 120px;padding-top: 10px;">
				<a class="sub_cls" title="<fmt:message key="userCenter.sureUpdate" bundle="${messages}"/>" onclick="saveUserPassword();"><fmt:message key="userCenter.sureUpdate" bundle="${messages}"/></a>
				&nbsp;&nbsp;
				<a class="sub_cls" title="<fmt:message key="userCenter.sureCancel" bundle="${messages}"/>" onclick="calcelUpdatemyPwd();"><fmt:message key="userCenter.sureCancel" bundle="${messages}"/></a>
			</div>
		</div>
	</div>
  </body>
</html>
