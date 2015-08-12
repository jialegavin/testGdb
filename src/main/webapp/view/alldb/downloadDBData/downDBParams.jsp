<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请选择国家</title>
<style type="text/css">
#showTable tr {
}
#showTable tr td{
	float: left;
	margin-left: 5px;
	margin-right: 5px;
}
</style>
</head>
<body>
	<div id="downDBParams">
		<div id="showDiv">
			<table id="showTable" cellpadding="5" cellspacing="5">
			</table>
		</div>
		<div align="center">
			<a class="btn_cls" href="#" title="保存" style="padding: 5px 31.5px 5px; margin-right: 9px;" onclick="addParamInfo();">保存</a>
            <a class="btn_cls" href="#" title="取消" style="padding: 5px 31.5px 5px;" onclick="closeThisDiv();">取消</a>
		</div>	
	</div>
</body>
</html>