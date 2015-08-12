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
<title>Orders</title>
<link href="<%=basePath%>static/css/sellcenter/myeasyui.css" rel="stylesheet" type="text/css" />
<link style="text/css" rel="stylesheet" href='<%=basePath%>/static/css/sellcenter/addOrder.css'>

<script type="text/javascript" 	src="<%=basePath%>static/js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/easyui/jquery.easyui.min.js"></script>

<script type="text/javascript" src="<%=basePath%>/view/sellcenter/js/addOrder.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/common/easyuiCascadeMenu.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/common/check.js"></script>
</head>
<body>
  <div class='mainDiv'>
         <!-- 订单form -->
		      <!-- 用户地址信息 -->
		      <div class='userInfo'>
		       <div class='myT'><span>Fill in order information  </span></div>
		       <div class="showInfo">
		         <div id='mr'><span  onclick="upAddr()">Set Default Address</span></div>
		          <!-- 用户地址表单 -->
		           <form id="addrForm">
		               <table cellspacing="5px">
		                    <tr>
		                        <td><span  class='tag'>Company Name:</span></td>
		                        <td><input id="company"  class='inputNomarl' class="easyui-validatebox" validtype="realname"
		                         name='company' type="text"></td>
		                    </tr>
			                <tr>
			                    <td><span  class='tag'>Consignee:</span></td>
			                    <td><input id="consignee" class='inputNomarl' name='consignee' type="text"></td>
			                </tr>
			                <tr>
			                    <td><span  class='tag'>Location</span></td>
			                    <td><input id="country"  name='country'>
		                         <input id="province" name='province' >  </td>
		                    </tr>
			                <tr>
			                       <td></td><td><input id="city" name='city'></td>
			                </tr>
			                <tr>
			                       <td><span  class='tag'>Address:</span></td>
			                      <td><textarea id="address" name='address'  ></textarea></td>
			                </tr>
			                <tr>
			                   <td><span  class='tag'>Zip code:</span></td>
			                   <td><input id="postalNum" type="text" class='inputNomarl'></td>
			                </tr>
			                <tr>
			                    <td><span  class='tag'>Cell phone number:</span></td>
			                    <td><input id="phone" type="text" class='inputNomarl'></td>
			               </tr>
		               </table>
		           </form>
	           </div>
		      <form id="odForm">
		      </div>
		      <!-- 支付方式 :暂时只提供支付宝-->
		      <div class='myT'>
		          <span>Payment And Invoice Information</span>
		      </div>
		      <div class='showInfo'>
		         <input type="hidden" id='rid' name='rid'>
		          <table cellspacing="5px" height="100px" align="top">
		            <tr><td><span class='tag' style='font-weight: bolder;'>Payment Method：</span></td>
		                <td>
		                    <input type="radio"  id='payType'  name='payType' checked="checked"/><span class='tag'>支付宝</span>
		                </td>
		            </tr>
                     <tr>
                        <td><span class='tag' style='font-weight: bolder;'>Invoice Information：</span></td>
                        <td>
                          <input type="radio"  id='invoiceType' name='invoiceType' checked="checked" value='0'/><span class='tag'>Not Invoiced</span>
                        </td>
                        <td>
                           <input type="radio"  id='invoiceType' name='invoiceType' value='1' /><span class='tag'>增值税专用发票</span>
                        </td>
                    </tr>
                    <tr style="display: none" id='invoiceInfo'>
                       <td><span class='tag' style='font-weight: bolder;'>发票名头:</span></td>
                       <td colspan="2"><input type='text' class='inputNomarl' style='width:300px;' id='invoice' name='invoice'></td>
                    </tr>
		          </table>
		       </div>
		        <div style="width:400px;margin-left: 600px;padding-left: 200px;">
		          <span class='tag'>Amount payable：</span>
		           <span >
		           <input type='text' id='sumMoney' name='sumMoney' readonly="readonly"  align="right" 
		           style="border: none;background-color: #f8f8f8; font-size: 24px; color:red; width: 100px;"/>
		            </span>
		        </div>
		        <div style="width:300px;margin-left: 600px;padding-left: 240px; margin-top: 10px;">
		          <span class='buttonSpan' onclick="subOrder()">Submit order</span>
		        </div>
		    </form>
   </div>
</body>
</html>