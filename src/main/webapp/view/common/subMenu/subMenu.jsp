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
	<title>国际商业智能化数据信息平台</title>
    <head>
    	
    	<script type="text/javascript"	src="${root }/view/common/subMenu/subMenu.js"></script>
    </head>
    <body>
  		<!--主题内容div-->   
	    <div id="middleDiv" style="display: none;z-index: 10">
	    	<!--侧边栏-->
        	 <!--个人中心二级菜单标题-->
        	<div class="title1" id = "title1">
        		<img src="/gbdbas/static/img/common/subMenu/title_background.png" align="top" />
        		<div style="position:absolute;top:10px;left:34px;height:30px;line-height:30px;font-size: 14px;color: white;">Personal Center</div>
        	</div>
        	<!--客户/竞争二级菜单标题-->
        	<div class="title2" id = "title2">
        		<img src="/gbdbas/static/img/common/subMenu/title_background.png" align="top" />
        		<div style="position:absolute;top:10px;left:10px;height:30px;line-height:30px;font-size: 14px;color: white;">Customers/Competitors</div>
        	</div>
	        <div id="sidebar">
	        	 <!--个人中心子菜单-->
	        	<div class="menu1" id="menu1">
	            	<p><a href="/gbdbas/view/personalcenter/viewright/findUserRight.jsp" style="color:#000;font-size: 14px" id="user1" onclick="changePCSubMenuColor(1);">Subscription Permissions</a></p>
	                <p><a href="/gbdbas/view/personalcenter/myfavorites/myFavorites.jsp" style="color:#000;font-size: 14px" id="user2" onclick="changePCSubMenuColor(2);">Saved Bookmarks</a></p>
	                <p><a href="/gbdbas/view/personalcenter/loginlog/userLoginLog.jsp" style="color:#000;font-size: 14px" id="user3" onclick="changePCSubMenuColor(3);">Log-in History</a></p>
                	<p><a href="/gbdbas/view/usermanagement/sonUser.jsp" style="color:#000;font-size: 14px" id="user4" onclick="changePCSubMenuColor(4);">Sub-Account Management</a></p>
	            </div>
	            <!--客户/竞争子菜单-->
	        	<div class="menu2" id="menu2">
	        		<div id="customerId">
	        			<img id="customerImg" src="/gbdbas/static/img/alldb/customer.png" name="no"/><br/>
	        			<span>My Customers(<span id="customerSpan"></span>)</span>
	        		</div>
	        		<div id="competorId">
	        			<img id="competorImg" src="/gbdbas/static/img/alldb/competitor.png" name="no"/><br/>
	        			<span>My Competitors(<span id="competorSpan"></span>)</span>
	        		</div>
	            </div>
	        </div>
	    </div>
    </body>
</html>