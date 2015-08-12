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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>heading of the page</title>
<head>
	<link href="${root }/static/css/common/head/head.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript"  src="${root }/view/common/head/head.js"></script>
</head>
<body id="bodys"  style="text-align:center;width:100%;padding: 0;margin: 0;font-family: 微软雅黑;">
<div id="zzhao" style="background-color:white;">
	<div style="margin:0 auto;text-align:left;padding: 0;width: 1210px;height: 79px;background-color: #FFFFFF;">
		 <!--页面头部内容  -->
		<div style="position:absolute;float: left;margin-left:850px;margin-top: -60px;z-index:1000;">
	 	    <!-- 添加客服 
			<c:set var="infoEmail" value="${sessionScope.commonInfo.companyEmail}"></c:set>
			<c:if test="${fn:contains(infoEmail, 'njinfobase')}">
			    <jsp:include page="/webInfo/infobase_login/login_kefu/kefu.jsp"></jsp:include>
			</c:if>
			<c:if test="${fn:contains(infoEmail, 'trade_easy')}">
			    <jsp:include page="/webInfo/trade_easy_login/trade_kefu.jsp"></jsp:include>
			</c:if>-->
		</div>
	  	<div id="head" style="background-color: #ffffff;top: 23px;margin:0 auto;align:left;padding: 0;width: 1210px;height:86px;position:absolute;">
		  	<div style="float:left; width:100px; solid #0000FF;padding-left: 20px;">
				<img alt="logo" src="${root }/static/img/login/INFORVELLOR-LOGO-EMAIL.png">
		  	</div>
		  	<div style="float:right;padding-top:6px;right:0px;">
			  	<div id="nickname" class="loginsms" style="margin-top:0px;padding-right:5px;width:220px">
					<span style="font-size:14px;font-family:微软雅黑;color:#777F7F">${sessionScope.user.loginName} </span>
					<a target="_self" style="font-size:14px;font-family:微软雅黑;color:#777F7F" href="${pageContext.request.contextPath }/userOff">[<fmt:message key="common.logOut" bundle="${messages}"/>]</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
		  	</div>
		  	<div style="float:right;padding-top:7px;padding-right:15px;">
				<a href="javascript:void(0)" style="font-family:微软雅黑;font-size:14px;color:#777F7F;" onmouseover="this.style.textDecoration='underline'" onMouseOut="this.style.textDecoration='none'" onclick="openUpdatePwdDlg()">Change Password</a>
		  	</div>
	 	</div>
	</div>
	<!--一级菜单栏 -->
	<div id="nav-strip">
          <ul>
               <li class="menu_line"></li>
               
               <li class="nav-item " id="nav-item_1"><a onclick="clickMenuForword(1);"><span class="primary-link"  id="font_color1">Personal Center</span></a>
               		<div class="subnav" id="subnav_1">                       
	                     <div class="subnav-inner">
		                     <ul>
		                          <li class=""><a href="javascript:void(0)" onclick="clickMenuForword(2)">Subscription Permissions</a></li>
		                          <li class=""><a href="/gbdbas/view/personalcenter/myfavorites/myFavorites.jsp" onclick="clickMenuForword(3)">Saved Bookmarks</a></li>
		                          <li class=""><a href="/gbdbas/view/personalcenter/loginlog/userLoginLog.jsp" onclick="clickMenuForword(4)">Log-in History</a></li>
		                          <li class=""><a href="javascript:void(0)" onclick="clickMenuForword(5)">Sub-Account Management</a></li>   
		                    </ul>
	                    </div>
	               </div>
	           </li>
	             
	           <li class="menu_line"></li>
               <li class="nav-item no-subnav active" id="nav-item_2"><a onclick="clickMenuForword(6)" ><span class="primary-link" id="font_color6">Trading Info</span></a></li>
               <li class="menu_line"></li>
               <li class="nav-item no-subnav active" id="nav-item_3"><a onclick="clickMenuForword(7)" ><span class="primary-link">Clients/Rivals</span></a>
               	  <div class="subnav" id="subnav_3">                       
	                     <div class="subnav-inner">
		                     <ul>
		                          <li class=""><a href="javascript:void(0)" onclick="clickMenuForword(7)">My Customers</a></li>
		                          <li class=""><a href="javascript:void(0)" onclick="clickMenuForword(8)">My Competitors</a></li>
		                    </ul>
	                    </div>
	               </div>
               </li>
               <li class="menu_line"></li>
       	       <li class="nav-item" id="nav-item_4"><a href="javascript:void(0)" onclick="clickMenuForword(9)"><span class="primary-link" >Product Label</span></a></li> 
               <li class="menu_line"></li>
               <li class="nav-item no-subnav active" id="nav-item_5"><a onclick="clickMenuForword(10)" ><span class="primary-link">Comparison</span></a></li>
               <li class="menu_line"></li>
               <li class="nav-item no-subnav active" id="nav-item_6"><a onclick="clickMenuForword(11)" ><span class="primary-link">All Library Download</span></a></li>
               <li class="menu_line"></li>
                 
               <li class="nav-item no-subnav active" id="nav-item_7"><a onclick="clickMenuForword(12)" ><span class="primary-link" id="font_color7">Product customization</span></a></li>
               <li class="menu_line"></li>
               
               <li class="nav-item no-subnav active" id="nav-item_8"><a onclick="clickMenuForword(13)" ><span class="primary-link">Service Center</span></a></li>
               <li class="menu_line"></li>
          </ul>
      </div>
      <script src="${root }/static/js/common/rcom-nav.js" type="text/javascript"></script>
      <!--xl 添加修改密码 -->
	  <jsp:include page="/view/common/head/updatepasswd/updatePassword.jsp"></jsp:include>
 </div>
 
</body>
</html>