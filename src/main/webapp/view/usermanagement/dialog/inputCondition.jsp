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
<title>Insert title here</title>
<head>
<meta name="renderer" content="webkit">  
<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
	<!-- 条件输入框 -->
	<div id="inputConditionDiv"  style="display: none;width: 300px; height: 270px;overflow-x:hidden;" >
		<div id="lib_Tab">
			<div class="lib_Menubox lib_tabborder">
			<ul>
			   <li id="one1" onclick="changeTab('one',1,2)" class="hover">Custom code</li>
			   <li id="one2" onclick="changeTab('one',2,2)" >Product Description</li>
			</ul>
			</div>
		</div>
		<div id="result_Tab">
				<div style="padding-top: 20px;">
				 	<div id="con_one_1">
				 		<font color="#1313FF" style="font-size: 16px">Please enter HScode:</font><br><input style="font-size:14px;" size=20 id='hs' type="text"/>
			 			<div style="padding-left:195px;padding-top:100px;">
			 				<a class="confirmOrcancel_cls" title="确定" onclick="addHS();">Confirm</a>
			 			</div>
				 	</div>
					<div id="con_one_2" style="display:none">
						<font color="#1313FF" style="font-size: 16px">Please enter the product description:</font><br><input style="font-size:14px;" size=20 id='desc' type="text"/>
						<div style="padding-left:195px;padding-top:100px;">
							<a class="confirmOrcancel_cls" title="确定" onclick="addDesc();">Confirm</a>
						</div>
						
					</div>
			 	</div>
		</div>
	</div>
  </body>
</html>
