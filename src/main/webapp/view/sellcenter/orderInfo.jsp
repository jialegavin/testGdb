<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<%=basePath%>/static/css/easyui/easyui.css" rel="stylesheet" type="text/css" />
    <link style="text/css" rel="stylesheet" href='<%=basePath%>/static/css/sellcenter/addOrder.css'>
    
    
    <script type="text/javascript" src="<%=basePath%>/static/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/js/easyui/jquery.easyui.min.js"></script>
    
    
    <script type="text/javascript"	src="<%=basePath%>/static/js/common/common.js"></script>
    <script type="text/javascript"	src="<%=basePath%>/static/js/common/date.js"></script>
    <script type="text/javascript" src="<%=basePath%>/view/sellcenter/js/orderInfo.js"></script>
<title>Order details</title>
</head>
<body>
   <div class="mainDiv">
     <!-- 订单详情 -->
      <div>
         <div class='myT'><span> Order info</span></div>
          <div class="showInfo">
            <table cellspacing="5px;">
              <tr><td><span class='tag' >Order number：</span></td><td><input readonly="readonly" class='showText' type='text' id='orderNo'></td></tr>
              <tr><td><span class='tag' >Product name：</span></td><td><input readonly="readonly"  class='showText' type='text' id='orderName'></td></tr>
              <tr><td><span class='tag' >Receiver：</span></td><td><input readonly="readonly"  class='showText' type='text' id='consignee'></td></tr>
              <tr><td><span class='tag' >Location：</span></td>
                  <td>
                     <input type='text' class='showText'  style='width:50px;' readonly="readonly" id='country'>
                     <input type='text' class='showText' style='width:50px;' readonly="readonly" id='province'>
                     <input type='text' class='showText' style='width:50px;' readonly="readonly" id='city'>
                  </td>
              </tr>
              <tr><td><span class='tag' >Address:</span></td><td><input type='text' class='showText' readonly="readonly" id='address'></td></tr>
              <tr><td><span class='tag' >Company name：</span></td><td><input type='text' class='showText' readonly="readonly" id='company'></td></tr>
              <tr><td><span class='tag' >Cell phone number：</span></td><td><input type='text' class='showText' readonly="readonly" id='phone'></td></tr>
              <tr><td><span class='tag' >Email：</span></td><td><input type='text' class='showText' readonly="readonly" id='email'></td></tr>
              <tr><td><span class='tag' >Zip code：</span></td><td><input type='text' class='showText' readonly="readonly" id='postalNum'></td></tr>
              <tr><td><span class='tag' >是否开据发票：</span></td><td><input type='text' class='showText' readonly="readonly" id='invoiceType'></td></tr>
              </table>
              <div style='padding-left: 5px;' id='showInvoice'>
	              <span class='tag' >发票类型：</span><input type='text' class='showText'  readonly="readonly" id='type'><br>
	              <span class='tag' >所需发票名头：</span><input type='text' class='showText' readonly="readonly" id='invoice'  style='width:250px;'>
              </div>
	     </div>
      </div>
      <!-- 订单明细 -->
      <div>
         <div class='myT'><span>Order Details</span></div>
           <div class="showInfo">
             <table id='detTable'>
             </table>
           </div>
      </div>
      <div style="width:300px;margin-left: 600px;padding-left: 230px;">
           <span class='tag' >Total price：</span>
            <span >
		           <input type='text' id='orderSummary' name='orderSummary' readonly="readonly"  align="right" 
		           style="border: none;background-color: #f8f8f8; font-size: 24px; color:red; width: 100px;"/>
		            </span>
      </div>
      <div>
	         <div id='noPay' style="width:300px;margin-left: 600px;padding-left: 240px; margin-top: 10px;">
	          <form id='payMoney' action='/gbdbas/alipay/playForm'>
	             <input type="hidden" name='out_trade_no' id='out_trade_no'>
	             <input type="hidden" name='total_fee' id='total_fee'>
	             <span class='buttonSpan' onclick="payOrder()">Payment</span>
		      </form>
	        </div>
           <div id='hasPay'></div>
      </div>
  </div>
</body>
</html>