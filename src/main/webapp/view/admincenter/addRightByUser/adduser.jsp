<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${root }/static/css/easyui/easyui.css" rel="stylesheet" type="text/css" />
<link href="${root }/static/css/admincenter/adduser.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" 	src="${root }/static/js/jquery/jquery.js"></script>
<script type="text/javascript" 	src="${root }/static/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript"	src="${root }/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="${root }/static/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"	src="${root }/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript"	src="${root }/static/js/artdialog/iframeTools.source.js"></script>
<script type="text/javascript"	src="${root }/static/js/artdialog/artdialogopen.js"></script>
<script type="text/javascript"	src="${root }/static/js/My97DataPicker/WdatePicker.js"></script>
<script type="text/javascript"	src="${root }/view/admincenter/addRightByUser/addUserAndRight.js"></script>
<title>账户授权</title>
<script type="text/javascript">
$(function(){
	$(".a").click(function(){
		$("#info_id").show();
		$("#pwd_info_id").show();
		$("#showRight").hide();
		});
	
	$(".b").click(function(){
		$("#info_id").hide();
		$("#pwd_info_id").hide();
		$("#showRight").hide();
		});
		showRightInfo();
});
</script>
</head>
<body style="background-color: #F0F0F0;">
	<div style="width:550px;height:650px;border:5px solid white;margin: 0 auto;">
		<p style="width:90%; border:1px solid #666666; margin-left:25px; border-radius:5px;">
			<span style=" vertical-align:top; margin-top:20px;">请选择：</span><br />&nbsp;&nbsp;
	    	<label title="未注册用户" >
	      		<input name="RadioGroup1" type="radio" class="a" value="a" checked="checked"  onclick="userInfoForm.reset()"/>未注册用户
	      	</label>
	        <label title="已注册用户">
	        	<!-- reset() 方法可把表单中的元素重置为它们的默认值 -->
	      	    <input name="RadioGroup1" type="radio" class="b"  value="b" onclick="userInfoForm.reset()"/>已注册用户
	       </label>
	  	</p>	
		<div style="float:left;border:1px solid #E5E5E5;margin-left: 25px;background-color: #FFFFFF;">
			<form name="userInfoForm" id="userInfoForm" action="/gbdbas/userManager/rightUserOper" method="post"  onSubmit="return checkSubmit();">
				<input type="hidden" value="" id="addUserRightType" name="addUserRightType"/>
				<table class="tab_info"  border="0" cellspacing="0" cellpadding="0">
					<tr id="info_id">
						<td class="tab_info_td" style="width: 100px;text-align: center;">公司信息</td>
						<td class="tab_info_td">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="tab2_td_cls">公司名称</td>
									<td class="tab2_td2_cls"><input id="companyname" name="companyname" type="text"></td>
								</tr>
								<tr>
									<td class="tab2_td_cls">联系人</td>
									<td class="tab2_td2_cls"><input id="userName" name="userName" type="text"></td>
								</tr>
								<tr>
									<td class="tab2_td_cls">手机号</td>
									<td class="tab2_td2_cls"><input id="phone" name="phone" type="text"></td>
								</tr>
								<tr>
									<td class="tab2_td_cls">座机号</td>
									<td class="tab2_td2_cls"><input id="tel" name="tel" type="text"></td>
								</tr>
								<tr>
									<td class="tab2_td_cls">联系地址</td>
									<td class="tab2_td2_cls"><input id="address" name="address" type="text"></td>
								</tr>
								<tr>
									<td class="td_none tab2_td_cls">电子邮箱</td>
									<td class="td_none tab2_td2_cls"><input id="email" name="email" type="text"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td  class="tab_info_td" style="text-align: center;width: 100px;">平台账号</td>
						<td class="tab_info_td">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="tab2_td_cls">用户名</td>
									<td class="tab2_td2_cls"><input id="loginName" name="loginName" type="text"></td>
								</tr>
								<tr id="pwd_info_id">
									<td class="td_none tab2_td_cls">密码</td>
									<td class="td_none tab2_td2_cls"><input id="loginPassword" name="loginPassword" type="password"></td>
								</tr>
							</table>
						</td>
					</tr>
			
					<tr>
						<td class="tab_info_td" style="text-align: center;">账号有效期</td>
						<td class="tab_info_td tab_info_td2">
						<input id="beginTime" class="dateinpText" name="beginTime" onclick="WdatePicker({lang:'auto',readOnly:true})"/>
						<font class="henggang">-</font>
						<input id="endTime" class="dateinpText" name="endTime" onclick="WdatePicker({lang:'auto',readOnly:true})"/>
						</td>
					</tr>
					<tr>
						<td class="tab_info_td" style="text-align: center;">购买金额</td>
						<td class="tab_info_td tab_info_td2"><input id="totalmoney" name="totalmoney" type="text"></td>
					</tr>
					<tr>
						<td class="tab_info_td" style="text-align: center;">属性</td>
						<td class="tab_info_td tab_info_td2">
							<select id="property" name="property">
								<option value="南京">南京</option>
								<option value="南通">南通</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="tab_info_td" style="text-align: center;">域名</td>
						<td class="tab_info_td tab_info_td2">
							<input id="domain" name="domain" type="text">
						</td>
					</tr>
					<tr>
						<td class="tab_info_td" style="text-align: center;">备注</td>
						<td class="tab_info_td tab_info_td2" style="height: 150px;">
						 	<textarea id="remarks" name="remarks" class="call_area_cls"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="border: 1px solid #666666;height: 40px;text-align: center;">
							<span><a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">保存</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a></span>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="showRight" style="width: 1150px;margin: 0 auto;display: none;">
		<p style="width:97%; border:1px solid #666666; margin-left:25px; border-radius:5px;padding:5px 5px;">
			<span style=" vertical-align:top; margin-top:20px;">用户<span id="addRightUserName" style="font-weight:bold;color:red;"></span>账户授权：</span><br />&nbsp;&nbsp;
	    	<label title="按Hscode授权" >
	      		<input name="RadioGroup2" type="radio" class="c" value="auth_hscode" checked="checked"  onclick="showRightInfo()"/>Hscode
	      	</label>
	        <label title="按产品描述授权">
	        	<!-- reset() 方法可把表单中的元素重置为它们的默认值 -->
	      	    <input name="RadioGroup2" type="radio" class="d"  value="auth_desc" onclick="showRightInfo()"/>产品描述
	       </label>
	        <label title="按国家授权">
	        	<!-- reset() 方法可把表单中的元素重置为它们的默认值 -->
	      	    <input name="RadioGroup2" type="radio" class="e"  value="auth_country" onclick="showRightInfo()"/>国家
	       </label>
	        <label title="按次授权">
	        	<!-- reset() 方法可把表单中的元素重置为它们的默认值 -->
	      	    <input name="RadioGroup2" type="radio" class="f"  value="auth_count" onclick="showCountRightInfo()"/>按次
	       </label>
	  	</p>
	  	<input type="hidden" id="showLoginName" name="showLoginName"/>
	  	<input type="hidden" id="buyMoney" name="buyMoney"/>
	  	<div id="showRight_div">
	  		<jsp:include page="/view/admincenter/addRightByUser/showRightInfo.jsp"></jsp:include>
	  	</div>
	  	<div id="countRightInfo" style="display: none;float:left;margin-left: 25px;">
	  		<button type="button" onclick="addCountRight()">按次授权</button>
	  		<button type="button" onclick="closeRightForm()">关闭授权窗口</button>
	  	</div>
	</div>
</body>
</html>