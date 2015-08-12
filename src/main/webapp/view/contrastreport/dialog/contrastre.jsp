<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">  
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Report Comparison</title>
</head>
<body>
	 <div id="reportDuibi" title="报表对比"  style=" width:380px;height:400px;display: none;">
		<div class="" style="width: 380px; height: 350px;">
				<div class="">
					<div id="selectName" style="width: 350px; height: 50px;line-height:50px; margin: 0 auto;">
						<font class="fieldname2">Selected Data:</font>
						<input type="text" name="" id="inputName" class="inpText2" style="width: 250px; height: 25px;" readonly="readonly"/>
					</div>
					<div id="selectDiv" style="width: 350px; height: 50px;line-height:50px;  margin: 0 auto; margin-top: 20px;">
						<font class="fieldname2">Select to compare:</font>
						<select id="selectValue" style="width: 250px; height: 35px;"></select>
						<div id="sp"></div>
					</div>
					<div id="customerbuttonFlexBr" style="padding-top: 240px;padding-left: 100px">
						<a class="confirmOrcancel_cls"  onclick="saveDb();">OK</a>&nbsp;&nbsp;&nbsp;
						<a class="confirmOrcancel_cls"  onclick="javascript:art.dialog({id:'duibi'}).close();">Cancel</a>
					</div>
			</div>
		</div>
	</div>	  
	
	
	<div id="reportDuibiInfo" title="报表对比"  style=" width:1210px;height:600px;display: none;overflow: scroll;">
		<div class="" style="">
			<div class="">
				<div id="" style="width: 1180px; height: 300px; ">
					<div id="duiBiTable"></div>
				</div>
				<div id="" style="width: 1180px; height: 100%;">
					<div id="showChartDuibiChart" style="width:1180px;height: 400px;"></div>
				</div>
				<div id="" style="width: 1180px; height: 100%;display: none;">
					<div id="showChartDuibiPie" style="width:1180px;height: 400px;"></div>
				</div>
		   </div>
		</div>
	</div>	  
</body>
</html>