<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"　"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/servicecenter/customerCenter.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/common/button/button.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/easyui/icon.css">
<script type="text/javascript"	src="${pageContext.request.contextPath }/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/static/js/artdialog/iframeTools.source.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/static/js/artdialog/artdialogopen.js"></script>
<script type="text/javascript" 	src="${pageContext.request.contextPath }/static/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" 	src="${pageContext.request.contextPath }/view/servicecenter/js/customercenter.js"></script>
<title>Customer Service</title>
</head>
<body style="background-color: #f0f3f5;" >
	<div style="min-height: 100%;height: auto!important;height: 100%;position: relative;">
	    <!--头部页面  -->
		<jsp:include page="/view/common/head/head.jsp"></jsp:include>
		<div style="width:1020px;margin: 0 auto; padding-bottom: 60px;text-align: right;padding-top: 50px;">
			  <div class="advancesearchCenter">
			  	<div style="width: 930px;margin: 0 auto ;">
					<div style="width: 930px;text-align: left;">
						<div style="font-size: 14px; font-family: 宋体; color: #1369c0;width: 200px;height: 36px;line-height: 36px;font-weight: bold;"><img alt="客服中心" style="vertical-align: middle;" src="${pageContext.request.contextPath}/static/img/servicecenter/center.png">Customer Service</div>
						<div style="width: 900px; margin: 0 auto;height:160px;text-align: center;">
							<div style="border-bottom:1px dashed #333333;height: 2px;margin:0px auto 10px auto;"></div>
							<img alt="图片" src="/gbdbas/static/img/contrastreport/img/yb.png" style="width: 900px; height: 140px;">
						</div>
						<div style="margin-top: 20px;width:900px; height:inherit;text-align: left; line-height:27px; margin: 0 auto;">
							<font style="font-size: 14px; font-family: 宋体;color: #333333;">&nbsp;&nbsp;Through a unified service, Inforvellor provide customers a professional, honest and comprehensive services, to fully meet customer demand for differentiated services trade data. With a senior industry background and many years' expereince in information technology services, we are a professional data specialized team  to provide comprehensive, thoughtful, and personalized services. Customers can achieve a higher efficiency, faster decision -support system with a lower cost. The estabilishment of trade risk management system is to experience the information age with a happy trade as a new one.</font> 
						</div>
						<div style="width:900px; height:auto !important;text-align: left; margin: 0 auto;  line-height: 25px!important;padding-top: 10px;">
							<div>
								<font style="font-size: 14px; font-family: 宋体; color: #1369c0;">Plase call us</font>
							</div>
							<div style="margin-left: 20px;">
								<font style="font-size: 14px; font-family: 宋体; font-weight: 900;color: #e3000b;">301-982-1900</font><br/>
							</div>
							<div style="margin-left: 20px;">
								<font style="font-size: 14px; font-family: 宋体; font-weight: 900;color: #e3000b;">Provide international first-class, fast and transparent data service</font>
							</div>
							<ul style="list-style:square;margin-left: 40px;color:#333333;">
								<li style="font-family: 宋体;font-size: 14px;"> 7*8 hours of customer support services</li>
								<li style="font-family: 宋体;font-size: 14px;"> one to one online consulting and services guidance platform</li>
								<li style="font-family: 宋体;font-size: 14px;"> To provide enterprises with multi-faceted industry analysis report</li>
								<li style="font-family: 宋体;font-size: 14px;"> Regular visits Services</li>
							</ul>
						</div>
						<div style="width:900px; height:auto !important;text-align: left; margin: 0 auto;  line-height: 25px!important; border: 0px solid red;padding-top: 10px;">
							<div style="width: 100%; height:auto !important; line-height: 28px;">
								<font style="font-size: 14px; font-family: 宋体;color: #1369c0;">Leave a message to us</font>
							</div>
							<div style="width: 100%; height:auto !important; margin-left: 20px;color:#333333;">
								<ul id="" style="list-style:square;margin-left: 20px;">
									<li style="font-family: 宋体;font-size: 14px;">You have suggested as proposed. Please describe your encounter questions in the box below the table</li>
									<li style="font-family: 宋体;font-size: 14px;">Please inform  the error message in the platform. we will make a more accurate answer</li>
									<li style="font-family: 宋体;font-size: 14px;">We will respond to your letter within 48 hours. please fill in your contact details</li>
								</ul>
							</div>
						</div>
						<div id="companyInfo" style="width: 900px; margin:0 auto; text-align:center;margin-left: 40px;padding-bottom: 50px;">
							<table width="700px" height="180px;" cellpadding="0" cellspacing="0">
								<tr>
									<td style="width:45px; height:auto !important;color:#333333;font-size: 14px;">Name：</td>
									<td><input id="name" type="text" class="call_inp_cls"/></td>
								</tr>
								<tr>
									<td style="height:auto !important;color:#333333;font-size: 14px;">Tel：</td>
									<td><input id="tel" type="text" class="call_inp_cls"/></td>
								</tr>
								<tr style="width: 100%; height: height:auto !important;">
									<td style="height:auto !important;color:#333333;font-size: 14px;">Mesg：</td>
									<td><textarea id="centent" class="call_area_cls"></textarea> </td>
								</tr>
								<tr>
									<td></td>	
									<td align="left" style="padding-left: 240px;"><a class="sub_cls" title="提交" onclick="btnClick(this)">Submit</a></td>	
								</tr>
							</table>
						</div>
					</div>
				  </div>	
			    </div>	
			</div>
		<!-- xl 添加页尾 -->
		<jsp:include page="/view/common/footer/footer.jsp"></jsp:include>
	</div>
</body>
</html>