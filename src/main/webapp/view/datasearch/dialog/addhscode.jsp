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
%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<fmt:setBundle basename="<%=language%>" var="messages" />
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Insert title here</title>
    <head>
    </head>
  <body>
		<div id="addHscodeDialog" style="width: 450px; height: 280px;display: none;">
			<div id="" style="margin:14px 0 0 50px;">
				<div style="float: left;font-size: 16px;color: #777f7f;height: 32px;line-height: 32px;">Custom code:</div>
				<div style="float: left;height: 32px;line-height: 32px;margin-left: 10px;"><input name="addHscodeInp" id="addHscodeInp" class="inpText" style="width: 184px;"/></div>
				<div style="float: left;margin-left: 24px;">
					<img  alt="<fmt:message key="commonAll.add" bundle="${messages}"/>" src="${root }/static/img/datasearch/addHscode.png"
					onclick="addHscodeToTable('addHscodeInp')" onmouseover="this.style.cursor='pointer'">
				</div>
			</div>
			<div style="clear: both; margin-left:130px;padding-top:9px;height: 114px;">
				<table  cellpadding="0" cellspacing="0" id="addHscodeTable">
				</table>
			</div>
			<div id="addHscodeButton" style="float: right;margin:100px 20px 0 0 ;">
				<a class="confirmOrcancel_cls" title="保存"  onclick="saveAddedHscode('c_hscode','addHscodeDialog');">Save</a>
				<a class="confirmOrcancel_cls" title="取消" onclick="javascript:art.dialog({id:'addHscodeDialog'}).close();">Cancel</a>
			</div>
		</div>
  </body>
</html>
