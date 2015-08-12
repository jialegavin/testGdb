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
		<!-- 查询收藏夹 -->
		<div id="addFavoritesDiv" style="height: 320px; width: 466px;display: none;">
			<form id="usercollection" class="x-panel-body x-panel-body-noheader x-form" method="POST">
				<div class="x-form-item " tabindex="-1" style="padding-bottom: 8px;padding-top: 10px;outline: none 0;">
					<label class="x-form-item-label" style="width: 60px;color: #777F7F;font-size: 16px;" for="favoritesNameId">
						<fmt:message key="commonAll.name" bundle="${messages}"/>:	
					</label>
					<div id="x-form-el-favoritesNameId" class="x-form-element" style="padding-left: 60px;">
						<input id="favoritesNameId" name="favoritesNameId" class=" x-form-text x-form-field  x-form-invalid" type="text" name="name" autocomplete="off" size="20" style="width: 356px;height:32px;border-radius:3px;border:1px solid #777F7F;"></input>
					</div>
					<div class="x-form-clear-left"></div>
				</div>
				<div class="x-form-item " tabindex="-1" style="padding-bottom: 26px;outline: none 0;">
					<label class="x-form-item-label" style="width: 60px;color: #777F7F;font-size: 16px;" for="favoritesDescId">
						<fmt:message key="common.remark" bundle="${messages}"/>:
					</label>
					<div id="x-form-el-favoritesDescId" class="x-form-element" style="padding-left: 60px">
						<textarea id="favoritesDescId" name='favoritesDescId' class=" x-form-textarea x-form-field " name="favoritesDescId" autocomplete="off" style="width: 352px; height: 210px;border-radius:3px;border:1px solid #777F7F;" readonly="">
     							</textarea>
					</div>
					<div class="x-form-clear-left">
					</div>
				</div>
			</form>
			<div class="x-form-item " tabindex="-1" style="outline: none 0;">
				<div id="x-form-el-favoritesDescId" class="x-form-element" style="padding-left: 257px">
				    <a class="confirmOrcancel_cls"  onclick="saveQueryCon();" style="margin-right: 9px;"><fmt:message key="common.confirm" bundle="${messages}"/></a>
					<a class="confirmOrcancel_cls"  onclick="javascript:art.dialog({id:'addFavoritesDiv'}).close();"><fmt:message key="common.cancel" bundle="${messages}"/></a>
				</div>
			</div>
		</div>
	</body>
</html>