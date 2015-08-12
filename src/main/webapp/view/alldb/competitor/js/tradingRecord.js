/**
 * ------------------------------------交易记录-------------------------------------------
 */
var tradingDataGrid;
var countryArray = "";

var userRightCfg;

//初始化用户权限数据
$(function(){
	/**
	 * 初始化 用户权限信息
	 */
	$.ajax({
		url: getRootPath() + "/permission/queryUserRightMessage",
		data : [],
		type : "post",
		dataType : "json",
		success : function(data) {
			userRightCfg = data;
			userRightCfg.hsHsCode = queryHsCode(userRightCfg.key);		//判断集合中是否存在hscode
			userRightCfg.hsGoodsDesc = queryHsGoodsDesc(userRightCfg.key);	//判断集合中是否存在产品描述
			// 对 交易记录的时间 初始化 赋值 2012-01-01 2012-12-31
			changeDateValue();				//根据用户权限初始化不同的日期
			// 绑定离开时间 日期控件初始化
			initDateFormat();
			$("#compTradestartDate").on('focus',initDateFormat);
			$("#compTradeendDate").on('focus',initDateFormat);
			// 初始化国家下拉框
			initUserRightMethod(userRightCfg.key,$("#compTradestartDate").val(),$("#compTradeendDate").val());
			if (userRightCfg.user.userDesc == '正式用户') {
				countryArray = userRightCfg.countryArray;
			} else {
				countryArray = reportArray.ALL;
			}
			allCountryOptionShow();		//显示国家
			tradingDataGrid = $("#tradingDataGrid");
			// 进出口类型 初始化  国家列表
			$("#competitorIexportType").combobox({
				onSelect : function(record){
					var tradeType = getTradeType(record.value);
					if (userRightCfg.user.userDesc == '正式用户') {
						initUserRightMethod(userRightCfg.key,$("#compTradestartDate").val(),$("#compTradeendDate").val());					// 初始化国家
						countryArray = checkTradeType(tradeType, userRightCfg.countryArray);
						// 购买时间在3个月内.赠送所有国家
						if (getChinaMonth(userRightCfg.nowDate,userRightCfg.user.beginTime)) {
							userRightCfg.countryArray.unshift('韩国');
							userRightCfg.countryArray.unshift('中国');
						}
					} else {
						countryArray = checkTradeType(tradeType, reportArray.ALL);
//						userRightCfg.countryArray.unshift('韩国');
//						userRightCfg.countryArray.unshift('中国');
					}
					allCountryOptionShow();
				}
			});
		}
	});
});

/**
 * 获取用户点击的时间,对国家数组从新赋值
 * @param dp
 */
function getDate(dp){
	if (userRightCfg.user.userDesc == '正式用户') {
		$("#" + this.id).val(dp.cal.getNewDateStr());		// 从新赋值用户点击最新的时间
		var queryBeginDate = $("#compTradestartDate").val();	// 查询开始时间
		var queryEndDate = $("#compTradeendDate").val();		// 查询结束时间
		initUserRightMethod(userRightCfg.key,queryBeginDate,queryEndDate);					// 初始化国家
		countryArray = userRightCfg.countryArray;
		allCountryOptionShow();
	}
}

/**
 * 国家填充
 * @param country
 */
function selectCompcountrySelectAddText(country) {
	var text = "";
	$("input[name='lang']:checkbox").each(function() {
		if ($(this).attr("checked")) {
			text += $(this).val() + ",";
		}
	});
	$('#' + country).combo('setText', text.substring(0, text.lastIndexOf(',')))
			.combo('hidePanel');
	validateCompIexport(text);
//	$("#compTradestartDate").off('focus');
//	$("#compTradeendDate").off('focus');
}

/**
 * 验证加载的数据是否符合规则
 * 
 * @param text
 */
function validateCompIexport(text) {
	var str = text.substring(0, text.lastIndexOf(','));
	createDate(text);		// 动态时间控件
	var data = $('#competitorIexportType').combobox('getData');
}

/**
 * 加载所有的国家
 */
function allCountryOptionShow() {
	countryArray = removal_Country(countryArray);
	$("#sps").empty();
	$("#sps").append(
					"<input type=\"checkbox\" onClick=\"selectCompAllChangeClick(this,'equirementSelects')\" name=\"all\"><span>选择全部</span><br/>")
	for (i in countryArray) {
		$("#sps")
				.append(
						"<input type=\"checkbox\" onClick=\"selectCompcountrySelectAddText('equirementSelects')\" name=\"lang\"  value=\""
								+ countryArray[i]
								+ "\"><span>"
								+ countryArray[i] + "</span><br/>");
	}
	$('#equirementSelects').combo({
		editable : true
	});
	$('#sps').appendTo($('#equirementSelects').combo('panel'));
	$('#equirementSelects').combo('setText', '').combo('hidePanel');
}

/**
 * 鼠标移动上去显示对应的title
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @return
 * @auther honghao
 */
function formatTitle(value, rowData, rowIndex) {
	return "<font title='" + value + "';style = \"color:'#EC5565'\">" + value
			+ "</font>";
}

/**
 * 点击查看交易详情
 * @param id
 * @param country
 */
function searchTradeDetail(id,country) {
	console.debug("id" + id + "     " + "country" + country);
	if (id && country) {
		$.ajax({
			url : getRootPath() + "/rightLibraryData/getRowData",
			data : {id:id,countryName:country},
			type : "post",
			dataType : "json",
			success : function(data){
				var htmlData = data['htmlData'];
//			    var hscode = data['hscode'];
//			    var country = data['country'];
			    $("#showArea").html(htmlData);
	/*		    $("#id_hscode").html(hscode);
			    $("#name_null_data").html(country+"数据");*/
			    openDivArtDialog("查看详情", "detailmessageDIV", "detailmessageDIV",900,600,true);
			}
		});
	}
}

/**
 * 交易记录中按条件查询
 */
function queriesCompiterorShow(v) {
	var flagIndex;		//标记进度条
	// 存放用户选择的国家
	var countrySelect = "";
	var lang = $("input[name='lang']");
	for (var i = 0; i < lang.length; i++) {
		if (lang[i].checked) {
			countrySelect = countrySelect + lang[i].value + ",";
		}
	}
//	var beginQueryDate = toDate($("input[name='c_startdate']").val().trim(),1);
//	var endQueryDate = toDate($("input[name='c_enddate']").val().trim(),2);
	var params = {
			'hscode' : $("input[name='compHscode']").val().trim(),
			'goodsdescription' : $("input[name='compProductDesc']").val().trim(),
			'tradeType' : $('#competitorIexportType').combobox('getValue').trim(),
			'beginDateFlex' : toDate(1,$("#compTradestartDate").val().trim()),
			'endDateFlex' : toDate(2,$("#compTradeendDate").val().trim()),
			'countrySelect' : countrySelect,
			'companyName' : $("input[name='companyNameByTradingRecord']").val().trim()
	};
	console.debug(params.beginDateFlex);
	console.debug(params.endDateFlex);
	// 试用用户 权限验证
	if (userRightCfg.user.userDesc == '试用用户') {
		if (probationUser(params.beginDateFlex, params.endDateFlex)) {
			$.messager.alert("提示", "试用户查询时间为2012-01-01 - 2013-12-30!");
			return;
		}
	} else if (userRightCfg.user.userDesc == '正式用户') {
		if (params.hscode) {
			if (!checkHsCodeOfBuy(params.hscode)) {		// 是否买过hasoce
				$.messager.alert("提示", "您没有购买此 "+params.hscode+" 编码!",'info');
				return;
			}
		} else {
			$.messager.alert("提示", "请输入海关编码!",'info');
			return;
		}
		if (params.goodsdescription) {
			if (!queryHsGoodsDesc(params.goodsdescription)) {	// 是否买过产品描述
				$.messager.alert("提示", "您没有购买此 "+params.goodsdescription+" 产品描述!",'info');
				return;
			}
		}
	}
	checkFromParams(params);
	// 验证合法性
	if (!checkSibmitHtml(params)) {
		return;
	}
	//权库 客户信息 和 竞争对手 的 标示符 1 : 竞争对手 2 : 客户信息 
	if (flagPage == 1) {
		params.exporter = $("input[name='companyNameByTradingRecord']").val().trim();
		params.importer = "";
		params.tradeType = 'E';
		$("#tradingDataGrid").datagrid({
			url: getRootPath() + "/competitor/queryCompetitorTradingData",
			queryParams : params,
			pagination:true,
			autoRowHeight : false,
			columns:[arr],
			pageNumber:1,
			pageSize:50,
			onBeforeLoad : function(param){
				flagIndex = param.page;
				if (1 == flagIndex) {
					$('div[name="clientProgress"]').show();
					createProgress('clientProgressBar','dbshowLoadStatus', param.countrySelect);
				}
			},
			onLoadSuccess : function(data) {
				// 暂停定时器
				if (1 == flagIndex) {
					CheckDataOver($('div[name="clientProgress"]'));
				}
				if (!data.total){
					//隐藏datagrid的div
					$("div[name='queryDataTable']").hide();
					$.messager.alert("提示", "未查询到数据");
				} else {
					//显示datagrid的div
					$("div[name='queryDataTable']").show();
				}
			},
			onLoadError : function(data){		// 如果发生了异常
				// 暂停定时器
				if (1 == flagIndex) {
					CheckDataOver($('div[name="clientProgress"]'));
				}
				console.debug(data);
				//隐藏datagrid的div
				$("div[name='queryDataTable']").hide();
				$.messager.alert("提示", "未查询到数据");
			}
		});
	} else if (flagPage == 2) {
		params.importer = $("input[name='companyNameByTradingRecord']").val().trim();
		params.exporter = "";
		params.tradeType = 'I';
		$("#tradingDataGrid").datagrid({
			url: getRootPath() + "/customer/queryCustomerTradingData",
			queryParams : params,
			pagination: true,
			autoRowHeight : false,
			columns:[arr],
			agination:true,
			pageNumber:1,
			pageSize:50,
			onBeforeLoad : function(param){
				flagIndex = param.page;
				if (1 == flagIndex) {
					$('div[name="clientProgress"]').show();
					createProgress('clientProgressBar','dbshowLoadStatus', param.countrySelect);
				}
			},
			onLoadSuccess : function(data) {
				// 暂停定时器
				if (1 == flagIndex) {
					CheckDataOver($('div[name="clientProgress"]'));
				}
				if (!data.total){
					$('div[name="queryDataTable"]').hide();
					$.messager.alert("提示", "未查询到数据");
				} else {
					$('div[name="queryDataTable"]').show();
				}
			},
			onLoadError : function(data){		// 如果发生了异常
				// 暂停定时器
				if (1 == flagIndex) {
					CheckDataOver($('div[name="clientProgress"]'));
				}
				console.debug(data);
				//隐藏datagrid的div
				$("div[name='queryDataTable']").hide();
				$.messager.alert("提示", "未查询到数据");
			}
		});
	}
}

/**
 * 处理进出口类型为全部,为参数添加相应的属性
 * @param params : 参数
 * @returns : 处理好的查询对象
 */
function checkFromParams(params){
	if (params) {
		// 如果进出口类型为 全部,添加进口和出口
		if (params.tradeType == 'I,E') {
			params.importer = params.companyName;
			params.exporter = params.companyName;
		}
	}
	return params;
}

/**
 * 选择国家动态加载input上
 * @param all
 * @param countryId
 */
function selectCompAllChangeClick(all, countryId) {
	var lang = $("input[name='lang']");
	for (var i = 0; i < lang.length; i++) {
		lang[i].checked = all.checked;
	}
	addCompCountryNameTexts(countryId);
}

/**
 * 将用户选择的国家放入到文本框
 */
function addCompCountryNameTexts(countryId){
	var country = countryId;
	var text = "";
	var lang = $("input[name='lang']");
	for(var i=0;i<lang.length;i++){
		if(lang[i].checked){
			text+=lang[i].value+",";
		}
	}
	$('#'+country).combo('setText', text.substring(0,text.lastIndexOf(','))).combo('hidePanel');
	validateCompIexport(text);
}

/**
 * 回调函数
 */
function callBack(data) {
	alert(data);
	if (data.size == 0) {
		$.messager.alert(allDataJs.all_del, allDataJs.all_queryTip);
	}
}

/**
 * 添加客户信息
 * @param importer 进口商
 */
function showCustomerName(importer) {
	$('#argentinaCustomerfm').form('reset');
	$("input[name='companyName']").val(importer);
	//打开弹出框
	openDivArtDialog('添加客户信息', 'showCustomerDlg', 'showCustomerDlg',810, 500,true);
	updateJsp(false);
}

/**
 * 添加竞争对手
 * @param exporter 出口商
 */
function showCompanyName(exporter) {
	$('#competitorfmForAllDB').form('reset');
	$("input[name='companyName']").val(exporter);
	updateJsp(false);
	//打开弹出框
	openDivArtDialog('添加竞争对手', 'showEcuadorCompetitorDlg', 'closeCompetitorDialogId',810, 500,true);
}

/**
 * 交易记录用的到<br>
 * 显示列
 */
var arr = [
		{field:'country',title:'国家',align:'center',width:80,formatter:formatValue},
		{field:'goodsdescription',align:'center',title:'产品描述',width:150,formatter:formatValue},
		{field:'hscode',title:'海关编码',width:80,align:'center',formatter:formatValue},
		{field:'date',title:'日期',width:80,align:'center',sortable :true,formatter:formatValue},
		{field:'importer',title:'进口商 ',width:150,align:'center',formatter:formatValue},
		{field:'exporter',title:'出口商 ',width:150,align:'center',formatter:formatValue},
		{field:'quantity',title:'数量',width:80,align:'center',sortable :true,formatter:formatNum},
		{field:'weight',title:'重量',width:80,align:'center',sortable :true,formatter:formatNum},
		{field:'totalprice',title:'金额 ',width:80,align:'center',sortable :true,formatter:formatNum},
		{field:'orgincountry',title:'原产国  ',width:80,align:'center',formatter:formatValue},
		{field:'id',title:'主键 ',width:80,align:'center',hidden:true,formatter:formatValue},
		{field:'detail',title:'查看详情 ',width:80,align:'center',formatter:searchAllDetail},
    ];

/**
 * 格式化数据
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatValue(value, row, index) {
	if (!value) {
		value = "N/A";
	}
	return value;
}

/**
 * 格式化数据
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatNum(value, row, index) {
	if (!value) {
		value = "--";
	}
	return value;
}