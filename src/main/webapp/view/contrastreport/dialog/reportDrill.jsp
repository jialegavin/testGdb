<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">   
<meta http-equiv="X-UA-Compatible" content="IE=edge">  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Advanced Search</title>
</head>
<body style="width: 99%; background-color: white">
	<div id="drillReportDiv" style="width: 1100px;height:600px;margin: 0  auto; margin-top: 0px;display: none;overflow-x:hidden;">
		<div id="secodeDiv" style="width: 100%; height: auto !important; border:0px solid blue; margin: 0 auto; margin-top: 0px;">
			
			<div style="width: 100%;height: 30px;  line-height: 30px; margin-top: 0px; background-color: #0070C2;display: none;">
				<span style="color: white; font-family: 宋体; font-weight: 800; font-size: 12px;margin-left: 10px;">Advanced Search</span>
			</div>
			
			<div style="width: 100%;height: 50px;  line-height: 30px; margin-top: 0px;">
				<span style="color: #0070C2; font-family: 宋体; font-weight: 800; font-size: 12px;"><font id="fontId"></font>：<font id="value"></font></span>
				<hr style="border: 1px solid #0070C2;"/>
			</div>
			
			
			
			<!-- 产品描述汇总和海关编码汇总 -->
			<div id="goodsDesc" title="产品汇总" style="width: 100%; height: 100%; border: 0px solid red; text-align:center; margin-top: 10px;display:none;">
				<div id="" title="产品描述报表" style="width: 100%;height: 320px; border: 0px solid red; margin-top: 10px;">
					<div id="goodsDesc_chartShow"></div>
				</div>
				
				<div id="" style="width: 100%; height: 100%; border: 0px solid green;">
					<div id="goodsDesc_dg"></div>
				</div>
			</div>
			
			<!-- 进出口商汇总 -->
			<div id="trande" title="进出口商汇总" style="width: 100%; height: 100%; border: 0px solid red; margin-top: 10px;display:none;">
				<div id="" title="进口商报表" style="width: 100%;height: 320px; border: 0px solid red; margin-top: 10px;">
					<div id="trande_chartShow"></div>
				</div>
				<div id="trande_dg"></div>
			</div>
			
			<!-- 原产国汇总 -->
			<div id="country" title="原产国汇总" style="width: 100%; height: 100%;text-align:center; border: 0px solid red; margin-top: 10px;display:none">
				<div id="" title="原产国图表" style="width: 100%;height: 320px; border: 0px solid red; margin-top: 10px;">
					<div id="country_chartShow"></div>
				</div>
				 
				 <div id="" style="width: 100%; height: 100%; border: 0px solid green;">
				 	<div id="country_dg"></div>
				</div>
			</div>
			
			
			<!-- 针对(美国)件数汇总 -->
			<div id="package" title="件数汇总" style="width: 100%; height: 300px;text-align:center; border: 0px solid red; margin-top: 10px;display:none">
				<div id="" title="原产国图表" style="width: 100%;height: 320px; border: 0px solid red; margin-top: 10px;">
					<div id="package_chartShow"></div>
				</div>
				 
				 <div id="" style="width: 100%; height: 240px; border: 0px solid green;">
				 	<div id="package_dg"></div>
				</div>
			</div>
			
			<!-- 交易趋势汇总(日期) -->
			<div id="city" title="海关城市汇总" style="width: 100%; height: 300px; border: 1px solid red; margin-top: 10px;display:none;">
				<div id="toolbaraAdd" style="height:30px;line-height:30px;font-size: 16px;font-family:Microsoft YaHei;background-color:#0070C2;">
			   		<div style="width: 120px; height: 30px; line-height: 30px; margin-left: 10px;">
						<span style="font-size:14px;color: white;font-family: 微软雅黑;">Trading Trend Summary Information</span>
			   		</div>
				 </div>
			</div>
			
		</div>
	</div>
</body>
</html>