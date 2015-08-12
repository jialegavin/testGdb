<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<title>订单</title>
    
    <link href="<%=basePath%>/static/css/easyui/easyui.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" src="<%=basePath%>/static/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/js/artdialog/artdialogopen.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
    
    <script type="text/javascript" src="<%=basePath%>/view/sellcenter/js/orderList.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/js/easyui/locale/easyui-lang-zh_CN.js"></script>
     <script type="text/javascript" src="<%=basePath%>/view/admincenter/usermanager/js/comm.js"></script>
</head>
<body>
   <!-- 订单列表 -->
   <div>
     <div id='odBar'>
       <input id='orderNo'  placeholder="Please enter your order number..." type='text'><input type='button' onclick='searchOrder()'  value='查询'>
     </div>
      <table id='odTable' toolbar='#odBar' title='我的订单'>
      </table>
   </div>
</body>
</html>