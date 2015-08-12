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
<link href="<%=basePath%>static/css/sellcenter/myeasyui.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/sellcenter/sellIndex.css">

<script type="text/javascript" 	src="<%=basePath%>static/js/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/artdialog/artdialogopen.js"></script>
<script type="text/javascript"	src="<%=basePath%>static/js/artdialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript" 	src="<%=basePath%>static/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/easyui/jquery.easyui.min.js"></script>
    
<script type="text/javascript" src="<%=basePath%>static/js/common/common.js"></script>    
<script type="text/javascript" src="<%=basePath%>static/js/common/check.js"></script>
<script type="text/javascript" src="<%=basePath%>view/sellcenter/js/sellcenter.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/common/easyuiCascadeMenu.js"></script>
<title>Subscription center</title>
</head>
<body style="background-color: #f0f3f5">
<div style="margin:0 auto;text-align:left;padding: 0;width: 100%;background-color: #f0f3f5">
  <!--头部页面  -->
    <jsp:include page="/view/common/head/head.jsp"></jsp:include>
	<!-- 主div -->
	 <div class='mainDiv'> 
	   		 <!-- 左标签栏 -->
		    <div class='leftDiv'>
		    <div style='width:150px;margin:10px auto auto auto;'>
		      <ul id='treeFloor1'>
		        <li>
		          <span>
		               <img src="<%=basePath%>static/img/sellcenter/tree_add.png">
		              <a href="javascript:void(0)" onclick="showFrame(1)">Product</a>
		          </span>
		          
		        </li>
		        <li >
		         <span ><img src="<%=basePath%>static/img/sellcenter/tree_add.png">
		                <a href="javascript:void(0)" onclick="showFrame(2)">My order</a>
		          </span>
		        </li>
		        <li >
		          <span ><img src="<%=basePath%>static/img/sellcenter/tree_minus.gif">
		                <a href="javascript:void(0)" onclick="showFrame(3)">My points</a>
		          </span>
		            <ul id='treeFloor2'>
		              <li >
		                 <span><a href="javascript:void(0)" onclick="showFrame(4)">Redeem</a></span>
		              </li>
		              <li >
		                 <span><a href="javascript:void(0)" onclick="showFrame(5)">Points rule</a></span>
		              </li>
		              <li >
		                 <span><a href="javascript:void(0)" onclick="showFrame(6)">Points details</a></span>
		              </li>
		            </ul>
		        </li>
		      </ul>
		     </div>
		  </div>
		 <!-- 右边1内容 --> 
		  <div class="rightDiv">
		    <div id="userMessege">
		        <div id="myPictrue">
		             <div><img  src="<%=basePath %>/static/img/sellcenter/head.jpg" style="width: 120px;height: 120px;"></div>
		             <div style="margin-left: 20px;margin-top: 10px;height: 20px" >
		                      <a href="#" onclick='fillInfo()' >Information</a>
		             </div>
		         </div>
		        <div id="myInfo">
		              <table>
		                  <tr><td>User name:<span  id="myLoginName"></span></td></tr>
		                  <tr><td>Registration Date：<span  id="registertime"></span></td></tr>
		                  <tr><td>Last time login：<span  id="endlogintime"></span></td></tr>
		                  <tr><td>email：<span  id="myEmial"></span></td></tr>
		               </table>
		        </div>
		    </div>
		    <div id='product'>
		       <iframe id="mainFrame" height="660px" width="840px" frameborder="0" scrolling="no" src="<%=basePath %>/view/sellcenter/product.jsp" ></iframe>
		    </div>
		  </div>  
		 <!-- 右边2内容 -->
	 </div>
	 <!-- 用户信息修 -->
	 <div id='recInfo' closed="true" class="easyui-dialog" style="border-width:1px; width:700px;height:550px;padding:10px 20px;overflow-x:hidden;">
	       <form id='upUserForm' action='/gbdbas/userManager/upUserInfo' method="post">
	         <input type="hidden" id='userId' name=userId>
	         <div class='upUserForm' align="center">
	            <table>
		           <tr>
			           <td class='tag'>User name：</td>
			           <td><input type='text' readonly='true' style='border:none' id='loginName' name='loginName'></td>
			       </tr>
		           <tr>
		                 <td class='tag'>Real name：</td>
		               <td><input type='text' id='userName' name='userName' class='inputNomarl'  class="easyui-validatebox" validtype="realname"></td>
		           </tr>
		           <tr>
		               <td class='tag'>Register e-mail：</td>
		               <td><input type='text' id='email' validtype="email" class='inputNomarl'  name='email' invalidMessage="邮箱格式不对" missingMessage='邮箱不能为空'
		               class="easyui-validatebox" data-options="required:true"></td>
		           </tr>
		           <tr><td class='tag'>Company name：</td><td><input type='text' class='inputNomarl'  id='unitName' name='unitName'></td></tr>
		           <tr><td class='tag'>Cell phone：</td><td><input type='text' class='inputNomarl' class="easyui-validatebox" id='phone' name='phone' validtype="mobile"></td></tr>
		           <tr><td class='tag'>QQ：</td><td><input type='text' class='inputNomarl' id='qq' class="easyui-validatebox"  name='qq' validtype="qq" ></td></tr>
		           <tr><td class='tag'>Location：</td>
		                <td>
		                   <input id="country" name='country'>
		                   <input id="province" name='province' >  
		                </td>
		           </tr>
		           <tr>
		             <td></td><td><input id="city" name='city'></td>
		           </tr>
		           <tr>
		                 <td class='tag' > Address：</td>
		                 <td><textarea id='address'  name='address'></textarea></td>
		           </tr>
		           <tr>
		                 <td class='tag'>Zip code：</td><td><input type='text'  class='inputNomarl' name='postalNum'   class="easyui-validatebox"  id='postalNum'  class="easyui-validatebox" validtype='zip' ></td>
		         </tr>
		           <tr><td class='tag'>Tel：</td><td><input type='text' name='tel' class='inputNomarl'   class="easyui-validatebox"  id='tel'></td></tr>
		        </table>
	         </div>
         	<div class='formButtom'>
	            <span class='buttonSpan' onclick="subRecForm()">OK</span>
	            <span class='buttonSpan' onclick="clearRecForm()">Cancel</span>
	        </div>
	       </form> 
	 </div>
	</div>
</body>
</html>