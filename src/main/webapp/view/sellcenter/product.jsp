<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String language = (String)request.getSession().getAttribute("language");
	if(language == null || "".equals(language) || "pleaseSelect".equals(language)){
	    language = "message_zh_CN";
	}
	String dateLanguage = "zh-cn";
	if(language.equals("message_en_US")){
		dateLanguage ="en";
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<%=basePath%>static/css/sellcenter/myeasyui.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>static/css/sellcenter/product.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" src="<%=basePath%>static/js/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>view/sellcenter/js/json2.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"	src="<%=basePath%>static/js/My97DataPicker/WdatePicker.js"></script>
    
    <script type="text/javascript" src="<%=basePath%>view/sellcenter/js/product.js"></script>
    <script type="text/javascript"	src="<%=basePath%>static/js/common/date.js"></script>
     <script type="text/javascript"	src="<%=basePath%>static/js/common/common-path.js"></script>
<title>Product purchase center</title>
</head>
<body >
       <div class="mainDiv">
          <div class='title'>Customization</div>
          <div id='menuDiv'>
            <span onclick="showBox('hs')">HSCODE</span>
            <span onclick="showBox('de')">Discription</span>
            <span onclick="showBox('cy')">Country</span>
            <span onclick="showBox('nm')">Times</span>
          </div>
          <!-- 存放查询条件 -->
          <div class='conditionDiv'>
             <!-- hsCode -->
	           <div id="hs">
	              <form id="hs_Form">
                     <div class='formCondition'>
	                      <table width="750px">
	                         <tr>
	                            <td class='tag'>HS CODE:</td>
	                            <td><input class='inputNomarl' type="text" id="hs_code"/></td>
	                            <td class='tag'>Country：</td>
                                <td> <input type="text" id='hs_country' ></td>
	                            <td class='tag'>In/output：</td>
	                            <td><input type="text" id='hs_io'></td>
	                         </tr>
	                         <tr>
	                              <td  class='tag'>Time range：</td>
	                              <td colspan="2"><input id="hs_beginTime" name="hs_beginTime"  class="doubleinpText" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})"/> -
	                              <input id="hs_endTime" name="hs_endTime"  class="doubleinpText" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})"/> 
	                              </td>
	                              <td></td>
	                              <td></td>
	                              <td style='padding-left: 30px;'><span class='buttonSpan' onclick="addCar('hs')">OK</span>
	                              <span class='buttonSpan' onclick="cancle('hs')">Clear</span></td>
	                         </tr>
	                      </table>
                      </div>
	                 </form>
	              </div>
	              <!-- 产品描述 -->
	            <div id='de'>
                  <form id="de_Form">
                     <div class='formCondition'>
                        <table>
                        <tr>
	                            <td class='tag'>Product key word:</td>
	                            <td><input class='inputNomarl' type="text" id="de_desc"/></td>
	                            <td class='tag'>Country：</td>
                                <td> <input type="text" id='de_country' ></td>
	                            <td class='tag'>In/output：</td>
	                            <td><input type="text" id='de_io'></td>
	                         </tr>
	                         <tr>
	                              <td  class='tag'>Time range：</td>
	                              <td colspan="2"><input id="de_beginTime" name="de_beginTime"  class="doubleinpText" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})"/> -
	                              <input id="de_endTime" name="de_endTime"  class="doubleinpText" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})"/> 
	                              </td>
	                              <td></td>
	                              <td></td>
	                              <td style='padding-left: 30px;'><span class='buttonSpan' onclick="addCar('de')">OK</span>
	                              <span class='buttonSpan' onclick="cancle('de')">Clear</span></td>
	                         </tr>
                        </table>
                     </div>
                  </form>
                </div>
                <!-- 国家 -->
                <div id='cy'>
                  <form id="cy_Form">
                     <div class='formCondition'>
                        <table>
                        
                         <tr>
	                            <td class='tag'>Country：</td>
                                <td> <input type="text" id='cy_country' ></td>
                                <td class='tag'></td>
	                            <td class='tag'>In/output：</td>
	                            <td><input type="text" id='cy_io'></td>
	                         </tr>
	                         <tr>
	                              <td  class='tag'>Time range：</td>
	                              <td colspan="2"><input id="cy_beginTime" name="cy_beginTime"  class="doubleinpText" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})"/> -
	                              <input id="cy_endTime" name="cy_endTime"  class="doubleinpText" onclick="WdatePicker({lang:'<%=dateLanguage%>',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})"/> 
	                              </td>
	                              <td></td>
	                              <td></td>
	                              <td style='padding-left: 30px;'>
		                              <span class='buttonSpan' onclick="addCar('cy')">OK</span>
		                              <span class='buttonSpan' onclick="cancle('cy')">Clear</span>
	                              </td>
	                         </tr>
                        </table>
                     </div>
                  </form>
                </div>
                <!-- 按次 -->
                <div id='nm'>
                 <form id="nm_Form">
                     <div class='formCondition'>
                        <table >
                           <tr>
                              <td ><span  class='tag'> Please input times:</span><input type="text"  class='inputNomarl' id="times" onblur="checkNum()"></td>
                           </tr>
                           <tr>
                             <td style="padding-left: 400px;">
                                <span class='buttonSpan' onclick="addCar('nm')">OK</span>
                                <span  class='buttonSpan' onclick="cancle('nm')">Clear</span>
                             </td>
                           </tr>
                        </table>
                     </div>
                  </form>
                </div>    
          </div>
          <!-- 存放购物车 -->
          <div id='car'  class='car'>
               <div id="tool_bar">
                 	<a	href="javascript:void(0)"	class="easyui-linkbutton"	plain="true" onmouseover="this.style.background='#DA4653';" onmouseout="this.style.background='#EC5565';" onclick="deletePros()">
							<font style="font-size:16px;color: #ffffff;font-family: Microsoft YaHei;">Delete</font>
						</a>
               </div>
               <!-- 海关编码或者国家或者产品描述 -->
               <div class='carDet'>
		              <table id="car_table" toolbar="#tool_bar" >
		              </table>
               </div>
               <div class='formButtom' >
                 <span class='buttonSpan' onclick='buyPro("car_table")'>Buy now</span>
               </div>
          </div>
       </div>
       
</body>
</html>