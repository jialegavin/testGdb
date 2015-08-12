<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">  
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${root}/static/css/contrastreport/contrastreCenter.css">
<link href="${root }/static/css/common/country/country.css" rel="stylesheet" type="text/css" />
<link href="${root }/static/css/common/button/button.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${root}/static/css/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${root}/static/css/easyui/icon.css">
<script type="text/javascript" 	src="${root}/static/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript"	src="${root}/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="${root }/static/js/easyui/locale/easyui-lang-en.js"></script>
<script type="text/javascript"	src="${root}/static/js/artdialog/artDialog.source.js?skin=twitter"></script>
<script type="text/javascript"	src="${root}/static/js/artdialog/iframeTools.source.js"></script>
<script type="text/javascript"	src="${root}/static/js/artdialog/artdialogopen.js"></script>
<script type="text/javascript" src="${root}/static/js/eachart/echarts.js"></script>
<script type="text/javascript" src="${root}/static/js/eachart/echarts-all.js"></script>
<script type="text/javascript" src="${root}/static/js/eachart/reportEcharts.js"></script>
<script type="text/javascript" src="${root}/static/js/My97DataPicker/WdatePicker.js"></script>
<script type="text/javascript" src="${root}/view/contrastreport/js/reportCompare.js"></script>
<title>Inforvellor</title>
</head>

<body style="background-color: #f0f3f5;height: 100%;padding: 0!important;margin: 0px;">
	<div style="margin:0 auto;text-align:left;padding: 0;width: 100%;min-height: 100%; height: auto!important;height: 100%;position:absolute;background-color: #f0f3f5;">
	  <!--头部页面  -->
		<jsp:include page="/view/common/head/head.jsp"></jsp:include>
		<div style="margin:0 auto;text-align:left;padding: 0;width: 1210px;top:40px; padding-bottom: 30px;overflow: auto;">
			<div style="margin:0 auto;text-align:left;padding: 0;width: 100%;background-color: #f0f3f5;padding-top: 26px;">
				<div style="clear: both;width:1210px;height:auto;">
					<div class="advancesearch" >
						<div style="padding-top: 10px;background-color: #F5FAF9;width: 1210px;height: 30px;"><b><font color="#1369c0" style="font-size:18px;padding-left: 36px">对比报表</font></b></div>
						<div align="right" width="1100px" >
							<div style="padding-left: 80px;">
								<div class="queryCss">
									<font class="fontCss">Country：</font>
									<input id="countryName" readonly="readonly" value="请选择国家" onclick="changeCountryRepost()" class="inpText2"/>
								</div>
								<div class="queryCss">
									<font class="fontCss">Custom code：</font>
									<input id="hscode_contrast" class="inpText2"/>
								</div> 		
								<div class="queryCss">
									<div>
										<font class="fontCss">Chosen Time：</font>
										<input id="beginAddDate" class="dateinpText" value="2014-06-01"
											name="begindateFlexBr" onclick="WdatePicker({lang:'auto',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})"/>
										<font class="henggang">-</font>
										<input  name="enddateFlexBr" class="dateinpText" id="endAddDate" value="2014-12-31"
											 onclick="WdatePicker({lang:'auto',quickSel:['2010-12-31','2011-12-31','2012-12-31','2013-12-31','2014-12-31'],readOnly:true})"/>&nbsp;&nbsp;
									</div>
								</div>
								<div class="querySecondCss">
									<font class="fontCss">Type：</font>
									<select id="selectType" class="inpText2" onchange="setParameters();">
									</select>
								</div>
								<div class="querySecondCss">
									<font class="fontCss">Product description：</font>
									<input id="goodsDesc" class="inpText2" name="hscodeFlexBr"/>
								</div> 		
								<div class="querySecondCss">
									<div style="border: 0px solid red;width: 100%;">
										<font class="fontCss">History Time：</font>
										<input id="beginDate" class="dateinpText" value="2014-01-01"
											 onclick="WdatePicker({lang:'auto',quickSel:['2010-1-1','2011-1-1','2012-1-1','2013-1-1','2014-1-1'],readOnly:true})"/>
										<font class="henggang">-</font>
										<input class="dateinpText" id="endDate" value="2014-05-30"
											 onclick="WdatePicker({lang:'auto',quickSel:['2010-12-31','2011-12-31','2012-12-31','2013-12-31','2014-12-31'],readOnly:true})"/>&nbsp;&nbsp;
									</div>
								</div>
							</div>
							<hr style="border: 1px solid #e5e5e5;width: 1030px;margin-top:20px;left:310px"/>
						</div>
						<div class="tips">
						 	<img src="/gbdbas/static/img/datasearch/tips.png" style="width:25px;"></img>
							<font size="2" style="line-height: 20px;">Depending on your Chosen Time and Historical Time, you can compare the trade differences between the two periods. It can help you analyze the competitive market of the product which you are concerned about</font>
						</div>
						<!-- 查询按钮 -->
						<div style="margin-left:935px;margin-top:-30px;height:60px;">
							<a href="javascript:setParameters();" title="立即查询" class="btn_cls">Immediate Search</a>
						</div>
					</div>
				</div>
			</div>
			<div id="contrastreCenter" style="display: none;">
	            <div id="tab_barCenter">  
	                <ul>  
	                    <li id="tab_report1" onclick="myclick(1)" style="background-image: url(${root}/static/img/datasearch/tabout.png);color:#1369c0;">  
	                       	Add<span class="text_span"></span>Report  
	                    </li>  
	                    <li id="tab_report2" onclick="myclick(2)">  
	                                                      Turnover<span class="text_span"></span>Report  
	                    </li>  
	                    <li id="tab_report3" onclick="myclick(3)">  
	                       	 Hold<span class="text_span"></span>Report 
	                    </li>  
	                </ul>  
	                <div id="execl_div" style="width:100px; height:30px; float:right;display: none;">
		               	<a onclick="exportPDForEXCELPubDuibi()" title="导出execl" class="exportExcl_cls">
		               		<font style="padding-left: 30px;">Export</font>
						</a>
	            	</div>
	            </div> 
           		<div> 
            	    <!-- tab1 新增-->
		            <div class="tab_css" id="tab1_content">  
		            	<!-- 表格div -->
						 <div style="padding-top: 56px;">
		                	<div id="contrastreAdd"></div>
		                	 <!-- echart图表 -->
			                <div id="showChartAdd"></div>
			                <div id="pieshowChartAdd"></div>
						</div>
		            </div>  
		            <!-- tab2 流失-->
		            <div class="tab_css" id="tab2_content">  
		                <!-- 表格div -->
		                <div style="padding-top: 56px">
		                	<div id="contrastreOut" ></div>
			                <!-- echart图表 -->
			                <div id="showChartOut"></div>
			                <div id="pieshowChartOut"></div> 
		                </div>  
		            </div>  
		            <!-- tab3 保持-->
		            <div class="tab_css" id="tab3_content">  
		            	<div style="padding-top: 56px">
						  <!--toobar -->
			              <div id="toolbaraKeep" style="height:30px;line-height:30px;color:#ffffff;display:none; font-size: 16px;font-family:Microsoft YaHei;background-color:#478DE3;">
						   	<div style="width:100%; height: 30px;" align="center">
								<font style="font-size:16px;color: #ffffff;font-family: Microsoft YaHei;" id="dateTis2"></font>
						   	</div>
						  </div>
						  <!--toobar -->
					      <div id="toolbaraKeep1" style="height:30px;line-height:30px;text-align:center; color:#ffffff;display:none; font-size: 16px;font-family:Microsoft YaHei;background-color:#478DE3;">
					   		<div style="width: 100%; height: 30px; text-align: center;">
								<font style="font-size:16px;color: #ffffff;font-family: Microsoft YaHei;" id="dateTis1"></font>
					   		</div>
					      </div>
				          <div style="float: left;width: 49%;border-right: 1px solid #999999;">
					    	<div id="keepDiv1" toolbar="#toolbaraKeep1"></div>
					      </div>
					      <div style="float: right;width: 49%;border-right: 1px solid #999999;">
						    <div id="keepDiv2" toolbar="#toolbaraKeep"></div>
					      </div>
					    </div>
		           </div>  
            	</div>
			</div>
		</div>
			<!-- xl 添加页尾 -->
			<jsp:include page="/view/common/footer/footer.jsp"></jsp:include>
 	</div>
	<jsp:include page="/view/datasearch/dialog/choosecountry.jsp"></jsp:include>
	<jsp:include page="/view/contrastreport/dialog/contrastre.jsp"></jsp:include>
	<jsp:include page="/view/contrastreport/dialog/detail.jsp"></jsp:include>
	<jsp:include page="/view/contrastreport/dialog/reportDrill.jsp"></jsp:include>
</body>
</html>