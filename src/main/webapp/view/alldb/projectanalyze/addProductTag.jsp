<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 国际化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String language = (String)request.getSession().getAttribute("language");
if(language == null || "".equals(language) || "pleaseSelect".equals(language))
    language = "message_zh_CN";
%>
<fmt:setBundle basename="<%=language%>" var="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body style="font-family: 微软雅黑;">
<div id="addProductTag" style="display:none;width: 681px;height: 340px;">
    <form id="addProdg" method="post">		
	<table title="添加产品标签"  style="width:680px;height:300px;">
         <thead>
          <tr>
            <td align="center" colspan="2" style="font-size: 18px;color:#0066ff;border-bottom:2px solid #0066ff;">Add product tag</td>
          </tr>
        </thead>
         <tr>
           <td style="font-size: 16px;color:#777f7f;padding-left:154px;padding-top: 17px;">Tag Name:</td>
           <td style="padding-top: 17px;">&nbsp;&nbsp;<input name="verifycode" id="verifycode" style="border: 1px solid #e2e5e7;margin-right:8px; border-radius: 4px; width: 190px; height: 30px;"/>
           	<span style="color: #0066ff;font-size: 12px;text-align: center;">*Required Field</span> 
           </td>
         </tr>
         <tr>
           <td style="text-align: right;color:#777f7f;font-size: 16px;height:40px;">HS code:</td>
           <td>&nbsp;&nbsp;<input  name="hscode" id="hscode2" style="border: 1px solid #e2e5e7;margin-right:8px; border-radius: 4px;width: 190px; height: 30px;"/>
          	 <span style="color: #0066ff;font-size: 12px;text-align: center;">*Required Field</span>
           </td>
         </tr>
         <tr>
            <td style="text-align: right;color:#777f7f;font-size: 16px;">Product Description:</td>
           <td>&nbsp;&nbsp;<input name="productName" id="productName" style="border: 1px solid #e2e5e7;margin-right:8px; border-radius: 4px;width: 190px; height: 30px;"/>
           	<span style="color: #0066ff;font-size: 12px;text-align: center;">*Required Field (You can add more by using spaces to separate)</span>
           </td>
         </tr>
         <tr style="display: none">
            <td style="text-align: right;color:#777f7f;font-size: 16px;">Product Description:</td>
           <td>&nbsp;&nbsp;<input name="productId" id="productId" style="border: 1px solid #e2e5e7;margin-right:8px; border-radius: 4px;width: 190px; height: 30px;"/>
           	<span style="color: #0066ff;font-size: 12px;text-align: center;">*Required Field (You can add more by using spaces to separate)</span>
           </td>
         </tr>
         <tr>
            <td colspan="2" style="padding-left:200px;padding-top: 30px;">
            <div style="width: 350px; height: 100px;border: 0px solid red;">
            	<a class="btn_cls" href="#" title="保存" style="padding: 5px 31.5px 5px; margin-right: 9px;" onclick="addProInfo();">Save</a>
               	<a class="btn_cls" href="#" title="取消" style="padding: 5px 31.5px 5px;" onclick="closeArtDlg();">Cancel</a>
	        </div>
            </td>
         </tr>
    </table>
    </form>
	</div>
</body>
</html>