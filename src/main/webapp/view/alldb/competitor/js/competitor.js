/**
 * -----------------------------------------------竞争对手------------------------------------------
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
	$("#delCompetitor").click(del);
	// 添加
	$("#addCompetitor").click(addCompetitor);
	// 修改
	$("#updateCompetitor").click(updateCompetitor);
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
	changeImgOfCom();			//图片改变
	//我的客户点击事件
	$("#customerId").click(function(){
		window.location.href="/gbdbas/view/alldb/customer/customer.jsp";
	});
	
	$("#competorId").click(function(){
		window.location.href="/gbdbas/view/alldb/competitor/competitor.jsp";
	});
});

/**
 * 搜索竞争对手
 */
var queryCountry = {
	searcher : function(value, name) {
		easyUiAjaxLoad(dataGrid, getRootPath() + "/competitor/queryCompetitor", {'companyName' : value},
				loadMethod);
	},
	menu : '#mm',
	prompt : 'My Competitor'
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
 * 添加竞争对手
 */
function addCompetitor() {
	flag = false;
	updateJsp(flag);
	setHtmlParam(null);
	openDivArtDialog("Add Competitor", 'showEcuadorCompetitorDlg',
			'closeCompetitorDialogId', 810, 500, true);
}

/**
 * 修改竞争对手
 */
function updateCompetitor() {
	flag = true;
	updateJsp(flag);		//隐藏或者显示 添加按钮
	var rows = dataGrid.datagrid("getSelections");
	//只能修改一条数据
	if (rows.length == 1) {
		setHtmlParam(rows);
		openDivArtDialog("Market Analysis", 'showEcuadorCompetitorDlg',
		'closeCompetitorDialogId', 810, 550, true);
	} else {
		$.messager.alert("Prompt", "One Line Data Only!", 'info');
	}
}

/**
 * 隐藏添加和修改页面的标签
 * @param flag : true : 修改 , false : 添加
 */
function updateJsp(flag){
	if (flag) {
		$("#titleSpan").html("Refise Competitor Information");
		$("#sureSpan").hide();
		$("#updateSpan").show();
	} else {
		$("#titleSpan").html("Add Competitor Information");
		$("#sureSpan").show();
		$("#updateSpan").hide();
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
		$("input[name='alternativeName']").val(obj.alternativeName);
		$("input[name='contact']").val(obj.contact);
		$("input[name='country']").val(obj.country);
		$("input[name='tel']").val(obj.tel);
		$("input[name='fax']").val(obj.fax);
		$("input[name='address']").val(obj.address);
		$("input[name='zip']").val(obj.zip);
		$("input[name='mailBox']").val(obj.mailBox);
		$("input[name='alternativeEmail']").val(obj.alternativeEmail);
		$("input[name='website']").val(obj.website);
		$("input[name='customerValue']").val(obj.customerValue);
		$("input[name='datasource']").val(obj.datasource);
		$("input[name='remark']").val(obj.remark);
		$("#collectionId").val(obj.collectionId);
	} else {
		$("input[name='companyName']").val("");
		$("input[name='alternativeName']").val("");
		$("input[name='contact']").val("");
		$("input[name='country']").val("");
		$("input[name='tel']").val("");
		$("input[name='fax']").val("");
		$("input[name='address']").val("");
		$("input[name='zip']").val("");
		$("input[name='mailBox']").val("");
		$("input[name='alternativeEmail']").val("");
		$("input[name='website']").val("");
		$("input[name='customerValue']").val("");
		$("input[name='datasource']").val("");
		$("input[name='remark']").val("");
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
 * 我的竞争对手操作栏
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
	return jy + "&nbsp;&nbsp;&nbsp;&nbsp;";
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
 * 查询竞争对手
 * 
 * @param data :
 *            数据
 */
function loadMethod(data) {
	// 查询个数
	queryCusAndComCount($("#competorSpan"),$("#customerSpan"));
	if (data.rows.length == 0) {
		$.messager.alert("Prompt", "未查询到竞争对手的数据!", 'info');
	} else {
		flagPage = data.flag;
		console.debug(flagPage);
	}
}

/**
 * 删除竞争对手
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
		$.messager.confirm("Prompt", "Delete?",
			function(r) {
				if (r) {
					ajaxLoadJava(getRootPath() + '/competitor/delCompany', {
						'id' : collectionId,
						'countryName' : countryname
					}, "post", "json");
				}
			});
	} else {
		$.messager.alert("Prompt", "Please Choose!", "info");
	}
}

/**
 * 删除的回调函数
 * @param data
 */
function returnMethod(data){
	var result = "";
	if (data.result == 1) {
		result = "Delete Successfully!";
	} else {
		result = "Delete Failed!";
	}
	$.messager.alert("Prompt", result, "info");
	// 查询
	queryDataGrid();
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
	alldb_setTab('tab',1,2);
	qkreportQueryEndDisplay();// 查询按钮还原样式
	$("#rivalRateProgress").hide();
	$("#rivalRateProgressBar").progressbar('setValue', 0);
	// 初始化报告类型
	queryParams = {
			flexType : $("#right_library_mebubox").find("ul li:first span:first").html()
	};
	openDivArtDialog("Market Analysis", 'qkCompeterDiv',
			'qkCompeterDiv', 950,600, true);
	$("#hcomp").val(v);
	$("#jzcompanytext").val(v);
	// 修改 小窗口的字体
	updateObj($("#fontText"));
	// 报表刚开始查询时数据展示的列样式
	qkreportQueryStartDisplay();
	// 隐藏报表和图表
	$("#dg_echart").hide();		//隐藏报告类型
	$("#conten").hide();
	$('div[name="clientProgress"]').hide(); // 隐藏进度条
	$("#right_library_mebubox").hide();	// 隐藏报告
	// 清空 国家下拉框的选择状态
	var quankuall = $("#quankuallCompCountry");
	quankuall.attr("checked",false);
	selectManayAllCountry('complang', 'alreadyselectcountry', quankuall,
			'equirementSelects36');
}

/**
 * 交易记录
 */
function competitorDeal(v){
	openDivArtDialog("外贸交易情报", 'compiterorTradeW','compiterorTradeW',1102, 550, true);
	$("div[name='queryDataTable']").hide();		// 隐藏列表
	$("#companyNameByTradingRecord").val(v);
	$("#companyNameByTradingRecord").attr('readonly',"readonly");
}

/**
 * 加载所有的国家
 */
function allCountryOptionShow() {
	countryArray = reportArray.ALL;
	$("#sps").empty();
	$("#sps").append("<input type=\"checkbox\" onClick=\"selectCompAllChangeClick(this,'equirementSelects')\" name=\"all\"><span>Select All</span><br/>")
	for (var i = 0; i < countryArray.length; i++) {
	$("#sps").append(
					"<input type=\"checkbox\" onClick=\"selectCompCountryNameAddText('equirementSelects')\" name=\"lang\"  value=\""
							+ countryArray[i]
							+ "\"><span>"
							+ countryArray[i] + "</span><br/>")
	}
	$('#equirementSelects').combo({
		editable:true
	});
	$('#sps').appendTo($('#equirementSelects').combo('panel'));
	$('#equirementSelects').combo('setText', '').combo('hidePanel');
}

/**
 * 预加载dataGrid的数据
 */
function queryDataGrid() {
	easyUiAjaxLoad(dataGrid, getRootPath() + "/competitor/queryCompetitor", {},
			loadMethod);
}