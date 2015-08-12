<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Report Comparison</title>
</head>
<body>
	 <!-- 对比弹出框 -->
	 <div id="selectCompareDataDialog" title="报表对比"  style="width:380px;height:430px;display: none;">
		<div class="" style="width: 380px; height: 350px;">
				<div class="">
					<div id="selectName" style="width: 350px; height: 50px;line-height:50px; margin: 0 auto;">
						<font class="fieldname2">Selected Data:</font>
						<input type="text" name="" id="inputName" class="inpText" style="width: 250px; height: 25px;" readonly="readonly"/>
					</div>
					<div id="selectDiv" style="width: 350px; height: 50px;line-height:50px;  margin: 0 auto; margin-top: 20px;">
						<font class="fieldname2">Select to compare:</font>
						<select id="selectValue" class="easyui-combobox" style="width: 250px; height: 35px;"></select>
						<div id="sp"></div>
					</div>
					<div id="customerbuttonFlexBr" style="padding-top: 240px;padding-left: 90px">
						<a class="confirmOrcancel_cls" title="确定" onclick="saveCompareData();">Confirm</a>&nbsp;&nbsp;&nbsp;
						<a class="confirmOrcancel_cls" title="取消" onclick="javascript:art.dialog({id:'selectCompareDataDialog'}).close();">Cancel</a>
					</div>
			</div>
		</div>
	</div>	  
	
	
	<!-- 对比显示的table和图表 -->
	<div id="dataContrastreShow" title="报表对比"  style=" width:1210px;height:800px;display: none;">
		<div class="" style="">
			<div class="">
				<div id="" style="width: 1180px; height: 300; ">
					<table id='datasearchContrastreDg' class="easyui-datagrid" title="报表对比" 
							 fitColumns="true" style="width: 1180px; height: 300px;"
							pagination="true" singleSelect="false" loadMsg="loading……">						
						 <thead>
							 <tr>
							 </tr>
						 </thead>
					 </table>
				</div>
				<div id="" style="width: 1180px; height: 100%;">
					<div id="showChartContrastreChart" style="width:1180px;height: 400px;"></div>
				</div>
				<div id="" style="width: 1180px; height: 100%;">
					<div id="showChartContrastrePie" style="width:1180px;height: 400px;"></div>
				</div>
		   </div>
		</div>
	</div>	  
</body>
</html>