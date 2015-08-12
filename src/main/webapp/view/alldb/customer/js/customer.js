
/**
 * -----------------------------------------------客户信息------------------------------------------
 */

var dataGrid;

//标记 修改 && 添加
var flag = false;
/**
 * 预加载
 */
$(function() {
	// 预加载 dataGrid 的数据
	dataGrid = $("#datagrid");
	// 查询竞争对手
	$('#queryCountry').searchbox(queryCountry);
	// 删除
//	$("#delCustomer").click(del);
	// 添加
//	$("#addCustomer").click(addCompetitor);
	// 修改
//	$("#updateCustomer").click(updateCompetitor);
	// 预加载所有数据
	queryDataGrid();
	//改表一级菜单上客户/对手模块的背景色
	$("#nav-item_3").css('backgroundColor','#ffffff');
	$("#nav-item_3").find("a").find("span").css('color','#0066cc');
	//显示二级菜单栏
	showSubMenu(2);
	//改变二级菜单栏样式
	changePCSubMenuColor(2);
	// 调用此方法,为客户和竞争对手个数赋值  
	queryCusAndComCount($("#competorSpan"),$("#customerSpan"));
	changeImgOfCus();			//图片改变
	//我的客户点击事件
	$("#customerId").bind("click",function(){
		window.location.href="/gbdbas/view/alldb/customer/customer.jsp";
	});
	
	$("#competorId").bind("click",function(){
		window.location.href="/gbdbas/view/alldb/competitor/competitor.jsp";
	});
});

/**
 * 删除客户信息 回调方法
 * @param data
 */
function returnMethod(data){
	var result = "";
	if (data.result == 1) {
		result = "Delete Successfully!";
	} else {
		result = "Delete failed!";
	}
	$.messager.alert("Prompt", result, "info");
	// 查询
	queryDataGrid();
};

/**
 * 搜索竞争对手
 */
var queryCountry = {
	searcher : function(value, name) {
		easyUiAjaxLoad(dataGrid, getRootPath() + "/customer/queryCustomer", {'companyName' : value},
				loadMethod);
	},
	menu : '#mm',
	prompt : 'My Customer'
}

/**
 * 根据公司名称和国家查询
 */
function queryCompany() {
	var searchParam = $("#companyName").val().trim();
	var countryname = checkCountryName($("#opponentcountry").val());
	easyUiAjaxLoad($('#myCompetitorsdg'), getRootPath()
			+ "/competitor/queryCompetitor", {
		'companyName' : searchParam,
		'countryName' : countryname
	}, loadMethod);
}

/**
 * 添加客户信息
 */
function addCompetitor() {
	flag = false;
	updateJspForCustomer(flag);
	setHtmlParam(null);
	openDivArtDialog("Add Customer Information", 'showCustomerDlg',
			'showCustomerDlg', 750, 500, true);
}


/**
 * 添加竞争对手
 * @param exporter 出口商
 * @author XL
 */
function showCompanyName(exporter)
{
	$('#competitorfmForAllDB').form('reset');
	$("input[name='companyName']").val(exporter);
	updateJsp(false);
	//打开弹出框
	openDivArtDialog('Add Competitor Information', 'showEcuadorCompetitorDlg', 'closeCompetitorDialogId',810, 500,true);
}


/**
 * 修改客户信息
 */
function updateCompetitor() {
	flag = true;
	updateJspForCustomer(flag);
	var rows = dataGrid.datagrid("getSelections");
	//只能修改一条数据
	if (rows.length == 1) {
		setHtmlParam(rows);
		openDivArtDialog("Change Customer Information", 'showCustomerDlg',
		'showCustomerDlg',750, 500, true);
	} else {
		$.messager.alert("Prompt", "Only one line of data can be chosen!", 'info');
	}
}

/**
 * 赋值给小窗口
 * @param params : 参数
 */
function setHtmlParam(params){
	if (params){
		var obj = params[0];
		$("input[name='companyName']").val(obj.companyName);
//		$("#companyName").val(obj.companyName);
		$("#ecuador_companyCountry").val(obj.country);
		$("#alternativeName").val(obj.alternativeName);
		$("#contact_id").val(obj.contact);
		$("#fax").val(obj.fax);
		$("#argentina_tel_id").val(obj.tel);
		$("#mailBox_id").val(obj.mailBox);
		$("#alternativeEmail").val(obj.alternativeEmail);
		$("#website").val(obj.website);
		$("#customerValue").val(obj.customerValue);
		$("#address").val(obj.address);
		$("#companyName").val(obj.companyName);
		$("#argentina_CustomerAddr_id").val(obj.address);
		$("#zip").val(obj.zip);
		$("#contactNum").val(obj.contactNum);
		$("#finallyContact").val(obj.finallyContact);
		$("#collectionId").val(obj.collectionId);
	} else {
		$("input[name='companyName']").val("");
		$("#ecuador_companyCountry").val("");
		$("#alternativeName").val("");
		$("#contact_id").val("");
		$("#fax").val("");
		$("#argentina_tel_id").val("");
		$("#mailBox_id").val("");
		$("#alternativeEmail").val("");
		$("#website").val("");
		$("#customerValue").val("");
		$("#address").val("");
		$("#companyName").val("");
		$("#argentina_CustomerAddr_id").val("");
		$("#zip").val("");
		$("#contactNum").val("");
		$("#finallyContact").val("");
		$("#collectionId").val("");
	}
}

/**
 * 加载国家名称
 */
function mycompetitorcountrys() {
	$.post(getRootPath() + '/country/getAllCountry', {
		'countryValue' : null
	}, function(data, v) {
		var s = data.join("");
		var d = s.split('、');
		$(d).each(function(e, item) {
			if (e == 0) {
				var option = document.createElement("option");
				option.width = "190px"
				option.value = "Select All";
				option.innerHTML = allDataJs.all_selectAll;
				document.getElementById("opponentcountry").appendChild(option);
			}
			var option = document.createElement("option");
			option.width = "190"
			option.value = item;
			option.innerHTML = item;
			document.getElementById("opponentcountry").appendChild(option);
		});
	}, "json");
}

/**
 * 我的客户信息操作栏
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @return
 */
function addOpss(value, rowData, rowIndex) {
	var fx = '<img src="'
			+ getRootPath()
			+ '/static/img/alldb/scfx.png" style="vertical-align:middle;padding-right:5px;"><a onmouseover=\'this.style.textDecoration="underline";\' onMouseOut=\'this.style.textDecoration="none";\' href="#" mce_href="#" style="font-size:12px;color:#656565;" onclick="sfenxi(\''
			+ rowData.companyName + '\')"> 市场分析' + '</a>';
	var jy = '<img src="'
			+ getRootPath()
			+ '/static/img/alldb/traed.png" style="vertical-align:middle;padding-right:5px;"><a onmouseover=\'this.style.textDecoration="underline";\' onMouseOut=\'this.style.textDecoration="none";\' href="#" mce_href="#" style="font-size:12px;color:#656565;" onclick="competitorDeal(\''
			+ rowData.companyName + '\')"> 交易记录' + '</a>';
	return  jy + "&nbsp;&nbsp;&nbsp;&nbsp;";
	//fx + "&nbsp;&nbsp;&nbsp;&nbsp;" +
}

/**
 * 根据国家查询
 * 
 * @param name
 * @returns
 */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

/**
 * 处理国家<br>
 * 选择全部
 * 
 * @param countryName
 * @returns
 */
function checkCountryName(countryName) {
	if (countryName) {
		countryName = countryName.val();
		if (countryName == 'Select All') {
			countryName = null;
		}
	}
	return countryName;
}

/**
 * 查询客户信息
 * 
 * @param data :
 *            数据
 */
function loadMethod(data) {
	// 查询个数
	queryCusAndComCount($("#competorSpan"),$("#customerSpan"));
	if (data.rows.length == 0) {
		$.messager.alert("Prompt", "No Customer record founded!", 'info');
	} else {
		flagPage = data.flag;
		console.debug(flagPage);
	}
}

/**
 * 删除客户信息
 */
function del() {
	var rows = dataGrid.datagrid("getSelections");
	if (rows.length > 0) {
		var collectionId = "";
		for (var i = 0; i < rows.length; i++) {
			collectionId += rows[i].collectionId + "#";
		}
		if (collectionId.lastIndexOf(collectionId, ",") != -1) {
			collectionId = collectionId.substring(0, collectionId.length - 1);
		}
		// var searchParam = $("#companyName").val().trim();
		var countryname = checkCountryName($("#opponentcountry").val());
		$.messager.confirm("Prompt","Delete?",
			function(r) {
				if (r) {
					ajaxLoadJava(getRootPath() + '/customer/delCustomer', {
						'id' : collectionId,
						'countryName' : countryname
					}, "post", "json");
				}
			});
	} else {
		$.messager.alert("Prompt", "Please choose!", "info");
	}
}

/**
 * 全库市场分析报告有结果时数据的样式
 * 
 * @param v
 * @return
 */
function qkreportQueryEndDisplay() {
	// 报表查询有结果时按钮样式
	$('#qkqueryDownReport').css('display', 'block');
	$('#qkqueryDownReportGrey').css('display', 'none');
	$('#qkquerygreyDownReportGrey').css('display', 'none');
	// 报表查询有结果时数据展示的列样式
	// $('#quankudatashow').animate({opacity:"1"},0);
}

/**
 * 全库市场分析报告刚开始查询时数据的样式
 * 
 * @param v
 * @return
 */
function qkreportQueryStartDisplay() {
	// 报表刚开始查询时按钮样式
	$('#qkqueryDownReport').css('display', 'none');
	$('#qkqueryDownReportGrey').css('display', 'block');
	$('#qkquerygreyDownReportGrey').css('display', 'none');
	// 报表刚开始查询时数据展示的列样式
	$('#quankudatashow').animate({
		opacity : "0"
	}, 0);
}

/**
 * 市场分析
 * 
 * @param v
 */
function sfenxi(v) {
	alldb_setTab('tab',1,3);
	qkreportQueryEndDisplay();// 查询按钮还原样式
	$("#rivalRateProgress").hide();
	$("#rivalRateProgressBar").progressbar('setValue', 0);
	openDivArtDialog("Market Analyse", 'qkCompeterDiv', 'qkCompeterDiv', 950, 600, true);
	$("#hcomp").val(v);
	$("#jzcompanytext").val(v);
	// 修改样式
	updateObj($("#fontText"));
	// 报表刚开始查询时数据展示的列样式
	qkreportQueryStartDisplay();
	// 初始化报告类型
	queryParams = {
			flexType : $("#right_library_mebubox").find("ul li:first span:first").html()
	};
	var quankuall = $("#quankuallCompCountry");
	quankuall.attr('checked',false);
	// 隐藏图标和报表
	$("#right_library_mebubox").hide();		//隐藏报告类型dg_echart、
	$("#dg_echart").hide();					//隐藏报告类型、
	$('div[name="clientProgress"]').hide(); // 隐藏进度条
	selectManayAllCountry('complang', 'alreadyselectcountry', quankuall,
	'equirementSelects36');
}

/**
 * 预加载dataGrid的数据
 */
function queryDataGrid(flagNum) {
	// 查询产品标签
	if (flagNum == 1) {
		easyUiAjaxLoad(dataGrid, getRootPath() + "/projectAnalyze/queryProjectAnalyzeByParam", {},
				loadMethod);
	} else {
		// 查询我的客户信息.
		easyUiAjaxLoad(dataGrid, getRootPath() + "/customer/queryCustomer", {},
				loadMethod);
	}
}

/**
 * 交易记录
 */
function competitorDeal(v){
	if(!v){
		$.messager.alert("Prompt","No competitor trade data founded！");
		return;
	}
	// 展示国家列表
	showCountryCombo();
	// 初始化
	$("#compTradestartDate").val("2012-01-01");
	$("#compTradeendDate").val("2012-12-31");
	$("#companyNameByTradingRecord").val(v.trim());
	$("#companyNameByTradingRecord").attr("readonly","readonly");
	$("#compHscode").val("");
	$("#compProductDesc").val("");
	var tradeType = $('#competitorIexportType').combobox('getData');
	$("#competitorIexportType").combobox('select',tradeType[0].value);
	queryParams = {'exporter':v.trim()};
	$("div[name='queryDataTable']").hide();		// 隐藏列表
	// 清空 dataGrid 清除上一次遗留的交易记录数据
	//emptyDateGrid($("#tradingDataGrid"));
	// 打开窗口
	openDivArtDialog("外贸交易情报", 'compiterorTradeW', 'compTradeWindowasd', 1102, 550,true);
}

/**
 * 展示国家列表
 */
function showCountryCombo(){
	var countryArray = reportArray.ALL;
	$("#sps1").empty();
	$("#sps1").append("<input type=\"checkbox\" onClick=\"selectManayAllCountry('customerlang','custselectedcountry',this,'equirementSelects1')\" id=\"customerAllCountry\" name=\"customerAllCountry\"><span>Select All</span><br/>")
	for(var i=0;i < countryArray.length; i++){
		$("#sps1").append("<input type=\"checkbox\" onClick=\"changeOnlyCountryNameAddText('customerlang','custselectedcountry','equirementSelects1')\" name=\"customerlang\"  value=\""+countryArray[i]+"\"><span>"+countryArray[i]+"</span><br/>")
	}
	$('#equirementSelects1').combo({
			editable:false
	});
	$('#sps1').appendTo($('#equirementSelects1').combo('hidePanel'));
}


