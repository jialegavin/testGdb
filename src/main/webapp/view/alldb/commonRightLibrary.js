/**
 * -------------------------------------------权库共用JS------------------------------------------------
 */

/**
 * 查询对象 {权库}
 */
var queryParam = {};

var reportHtml = {};

/**
 * 区别进度条  立即查询 && 分页查询
 */
//var proFlag;

/**
 * 饼图全局变量
 */
var pieDataEcharts = {};

/**
 * 次数
 */
var pieCreateNum = 0;

/**
 * Echats 对象
 */
var echartsData = {};

/**
 * 加载所有国家
 * 
 * @param divId :
 *            divId
 * @param selectId :
 *            selectId
 * @param countryArray :
 *            国家集合
 * @param inputTitle :
 *            input头部
 * @param inputText :
 *            input除头部以外的input
 */
function LoadAllCountry(divId, selectId, countryArray, inputTitle, inputText) {
	divId.empty();
	divId.append(inputTitle);
	divId.append(inputText);
	selectId.combo({
		editable : false
	});
	divId.appendTo(selectId.combo('panel'));
}

/**
 * 创建 input ,提供给 初始化国家用
 * 
 * @param clickName :
 *            onclick_Nmae
 * @param name :
 *            input_Name
 * @param Id :
 *            input_Id
 * @param objArray :
 *            国家数组
 * @param queryObj :
 *            queryId
 * @returns {String}
 */
function createInput(clickName, name, id, objArray, queryObj) {
	queryObj.empty();
	if (objArray) {
		if ($.isArray(objArray)) {
			for (x in objArray) {
				queryObj.append("<input type=\"checkbox\" onClick=" + clickName
						+ " id=" + id + " name=" + name + "  value=\""
						+ objArray[x] + "\"><span>" + objArray[x]
						+ "</span><br/>");
			}
		} else {
			queryObj.append("<input type=\"checkbox\" onClick=" + clickName
					+ " id=" + id + " name=" + name + "><span>" + objArray
					+ "</span><br/>");
		}
	}
	return inputText;
}

/**
 * 
 * @param compId : 竞争对手Id
 * @param cusId : 客户Id
 */
function queryCusAndComCount(compId,cusId){
	// 请求对手
	if (compId) {
		$.ajax({
			url : getRootPath() + "/competitor/queryCompetitor",
			data : {companyName:'',countryName:''},
			dataType : "json",
			type : "post",
			success : function(data) {
				console.debug(data);
				compId.text(data.total);
			}
		});
	}
	// 请求客户
	if (cusId) {
		$.ajax({
			url : getRootPath() + "/customer/queryCustomer",
			data : {companyName:'',countryName:''},
			dataType : "json",
			type : "post",
			success : function(data) {
				console.debug(data);
				cusId.text(data.total);
			}
		});
	}
}

/**
 * 权库<br>
 * 全库我的竞争对手or我的客户信息 点击全选按钮让所有国家被选中
 */
function selectManayAllCountry(inpName, displayCountryDiv, all, countryId) {
	var lang = $("input[name='" + inpName + "']");
	var s = "";
	for (x in lang) {
		lang[x].checked = all.checked;
		if (lang[x].value) {
			s = s + lang[x].value + "、&nbsp;";
		}
	}
	if (all.checked) {
		$('#' + displayCountryDiv).html(
				"<font color='#0066CC' style='font-family: 微软雅黑,宋体;'>* 当前已选 &nbsp;:&nbsp;"
						+ s + "</font>");
	} else {
		$('#' + displayCountryDiv).html("");
	}
	$('#' + countryId).combo('setText', s.substring(0, s.lastIndexOf(',')))
			.combo('hidePanel');
}

/**
 * 点击单选按钮<br>
 * 点击国家被选中,显示在文本上
 */
function changeOnlyCountryNameAddText(inpName, displayCountryDiv, country) {
	var text = "";
	var name = inpName;
	var displayCountryText = displayCountryDiv;
	$("input[name='" + name + "']:checkbox").each(function() {
		if ($(this).attr("checked")) {
			text += $(this).val() + "、&nbsp;";
		}
	})
	if (text == "") {
		$('#' + displayCountryText).html("");
	} else {
		$('#' + displayCountryText).html(
				"<font color='#0066CC' style='font-family: 微软雅黑,宋体;'>*当前已选&nbsp;:&nbsp;" + text
						+ "</font>");
	}
	$('#' + country).combo('setText', text.substring(0, text.lastIndexOf(',')))
			.combo('hidePanel');
}

/**
 * 全库市场分析报告返回被选中的国家字符串
 * 
 * @returns {String} : 国家Str
 */
function subFlexSearchs() {
	var s = "";
	var lang = $("input[name='complang']");
	for (var i = 0; i < lang.length; i++) {
		if (lang[i].checked) {
			s = s + lang[i].value + ",";
		}
	}
	return s;
}

/**
 * 获取选中国家
 * 
 * @param name
 * @returns {String}
 */
function getSelectedCountry(name) {
	var s = "";
	var lang = $("input[name='" + name + "']");
	for (var i = 0; i < lang.length; i++) {
		if (lang[i].checked) {
			s = s + lang[i].value + ",";
		}
	}
	return s;
}

/**
 * 全库市场分析报告正在查询时数据的样式
 * 
 * @param v
 * @return
 */
function qkreportQueryingDisplay() {
	// 报表正在查询时按钮样式
	$('#qkqueryDownReport').css('display', 'none');
	$('#qkqueryDownReportGrey').css('display', 'none');
	$('#qkquerygreyDownReportGrey').css('display', 'block');
	// 报表正在查询时数据展示的列样式
	$('#quankudatashow').animate({
		opacity : "0"
	}, 0);
}

/**
 * 改变选项卡的样式
 * 
 * @param name
 * @param cursel
 * @param n
 * @author WangBo
 */
function alldb_setTab(name, cursel, n) {
	for (i = 1; i <= n; i++) {
		var menu=document.getElementById(name+i);/* two1 */
		//var con=document.getElementById("con_"+name+"_"+i);/* con_two_1 */
		var a = document.getElementById(name+"_a"+i);
		var jqMenu = $(menu);
		//var jqCon = $(con);
		var jqA = $(a);
		jqMenu.attr('class',cursel?"hover":"");	/*三目运算  等号优先*/
		jqMenu.css('backgroundImage',i == cursel?"url('/gbdbas/static/img/datasearch/tabout.png')":i==1?"url('/gbdbas/static/img/datasearch/tabbbefore.png')":"url('/gbdbas/static/img/datasearch/tabbbefore.png')");
		//jqCon.css('display',i == cursel?"block":"none");
		jqA.css('color',i == cursel?"#1369c0":"#ffffff");
	}
}

/**
 * 格式化列表字段大小
 * 
 * @param str :
 *            格式字符
 * @return
 */
function getFormatStr(str) {
	return "<font style='font-size:14px;'>" + str + "</font>";
}

/**
 * 根据报告类型,查询结果集
 * 
 * @param reportName :
 *            报表类型
 */
function reportTypeClick(reportName) {
	if (reportName) { // 如果不为空
		param.reportType = reportName;
		if ('TRADE' == reportName) {		//这里为特殊情况,新增了一个 交易记录
			// 请求交易记录
			requestTreadReport();
			return;
		}
		if (getCountryByReoportName(reportName)) {
			param.countryArray = getCountryByReoportName(reportName);
			// 加载国家combo
			checkParamCreateCountryArray();
			// 点击一次,请求一次后台
		}
		sendLoadData();
	}
}

/**
 * 同环比按钮隐藏与现实<br>
 * 交易记录隐藏 | 其他的报告类型 显示
 * @param flag : 标记
 */
function tongHuanBi(flag) {
	if (flag) {
		$("#withRadio").hide();
	} else {
		$("#withRadio").show();
	}
}

/**
 * 请求后台数据,得到最新的查询对象<br>
 * 本页面只允许出现一个 dataGrid table 和 echats<br>
 * 多个页面合在一起 jsp:incode<br>
 * 权库的市场分析 3个页面 {产品标签,竞争对手,客户信息} 共用此方法
 */
function sendLoadData(){
	var flagPro = false;
	queryParam.marketplace.flexType = param.reportType;
	var arr = judgeEquirementquanku(queryParam.marketplace.flexType);
	tongHuanBi(false);
	$("[name='marketplaceDataGrid']").datagrid({
		url : queryParam.marketplace.url,
		queryParams : queryParam.marketplace,
		title : getTitle(param.reportType),
		height : 'auto',
		columns : [arr],
		pageNumber : 1,
		pageSize : 10,
		showFooter:true,
		onBeforeLoad : function(param){
			flagPro = param.page;
			if (1 == flagPro) {
				$('div[name="clientProgress"]').show();
				createProgress('clientProgressBar','dbshowLoadStatus', param.countrySelect);
			}
		},
		onLoadSuccess:function(data) {
			// 结束进度条
				CheckDataOver($('div[name="clientProgress"]'));
			data = data.rows;
			$("#conten").show();
			$("div[name='dataTables']").show();		//显示列表
			if (data.length > 0) {
				//起运港 和 抵达港 为饼图	param.reportType 报告类型
				if ('PORTOFSHIPMENT' == param.reportType || 'PORTOFARRIVAL' == param.reportType) {
					var pieData = pieCreateEcharts(data);
					if (pieData) {
						$("#echartsDiv").show();
						showPieChart('echartsDiv', pieData, getTitle(param.reportType), '',950, 500,'重量');
					} else {
						$("#echartsDiv").hide();
					}
				} else {
					// 生成折线图 和 柱状图
					var echartsData = createEcharts(data);
					if (echartsData) {
						$("#echartsDiv").show();
						showMixEchart('echartsDiv',echartsData , getTitle(param.reportType), '', 950, 500, '123', 'xxxdd');
					} else {
						$("#echartsDiv").hide();
					}
				}
			} else {
				$.messager.alert("提示","未查询到数据!",'info');
				$("#echartsDiv").html('');
				$("#conten").hide();
			}
		}
	});
}

/**
 * 文本框时间验证
 * 
 * @param firstStart
 *            第一段日期起始值
 * @param firstEnd
 *            第一段日期结束值
 * @param secondStart
 *            第二段日期起始值
 * @param secondEnd
 *            第二段日期结束值
 * @param message
 *            提示信息
 * @param reporttype
 *            报表类型
 * @author honghao
 * @return
 */
function validateDate(firstStart, firstEnd, secondStart, secondEnd, message,
		reporttype) {
	// 验证第一段日期
	if (firstStart == null || firstStart == '') {
		alertMessage("提示", "开始日期不能为空!", "info");
		return false;
	}
	if (firstEnd == null || firstEnd == '') {
		alertMessage("提示", "结束日期不能为空!", "info");
		return false;
	}
	var fsDate = new Date(firstStart);
	var feDate = new Date(firstEnd);
	var btime = fsDate.getTime(firstStart);
	var etime = feDate.getTime(firstEnd);
	var time = parseInt(etime) - parseInt(btime);
	if (fsDate > feDate) {
		alertMessage("提示", message, "info");
		return false;
	}
	// 验证时间段不超过一年
	if (time > 31536000000) {
		alertMessage("提示", "查询最大的时间差不能超过一年!", "info");
		return false;
	}
	// 判断当报告类型为新增，流失进口商，新增流失出口商时校验第二段日期
	if (reporttype == 'ADDIMPORTER' || reporttype == 'REDURCEIMPORTER'
			|| reporttype == 'ADDEXPORTER' || reporttype == 'REDURCEEXPORTER') {
		// 验证第二段日期
		if (secondStart == null || secondStart == '') {
			alertMessage("提示", "新增时间段开始日期不能为空!", "info");
			return false;
		}
		if (secondEnd == null || secondEnd == '') {
			alertMessage("提示", "新增时间段结束日期不能为空!", "info");
			return false;
		}
		var ssDate = new Date(secondStart);
		var seDate = new Date(secondEnd);
		var sbtime = ssDate.getTime(secondStart);
		var setime = seDate.getTime(secondEnd);
		var stime = parseInt(setime) - parseInt(sbtime);
		if (ssDate > seDate) {
			alertMessage("提示", message, "info");
			return false;
		}
		// 验证新增时间不超过一年
		if (stime > 31536000000) {
			alertMessage("提示", "查询最大的时间差不能超过一年!", "info");
			return false;
		}
	}
	return true;
}

/**
 * 验证参数合法性<br>
 * 1 : 国家<br>
 * 2 : 开始时间<br>
 * 3 : 结束时间<br>
 * params{ countrySelect : 国家, beginDateFlex : 开始时间 endDateFlex : 结束时间
 * companyName : 公司名称 hscode : 海关编码 tradeType : 贸易类型 goodsdescription : 产品描述
 * importer : 进口商 }
 */
function checkSibmitHtml(params) {
	if (params) {
		if (!params.countrySelect) {
			$.messager.alert("提示", "国家不能为空!");
			return false;
		}
		if ((params.beginDateFlex.trim() != "" && params.endDateFlex.trim() == "")
				|| (params.beginDateFlex.trim() == "" && params.endDateFlex
						.trim() != "")) {
			$.messager.alert("提示", "时间不能为空");
			return false;
		}
		if (params.endDateFlex.trim() != "") {
			if (!(verifyDate(params.beginDateFlex) && verifyDate(params.endDateFlex))) {
				$.messager.alert("提示", "时间格式不正确!");
				return false;
			}
			var sDate = new Date(params.beginDateFlex);
			var eDate = new Date(params.endDateFlex);
			if (eDate < sDate) {
				$.messager.alert("提示", "您输入的查询结束时间不能够小于查询开始时间!");
				return false;
			}
			var date1 = new Date(params.beginDateFlex);
			var date2 = new Date(params.endDateFlex);
			var btime = date1.getTime(params.beginDateFlex);
			var etime = date2.getTime(params.endDateFlex);
			var time = parseInt(etime) - parseInt(btime);
			if (time > 31536000000) {
				$.messager.alert("提示", "查询最大的时间差不能超过一年!");
				return false;
			}
		}
	}
	return true;
}

function isEmpty(gradId) {
	var item = gradId.datagrid('getRows'); 
	if (item) { 
	for (var i = item.length - 1; i >= 0; i--) { 
		var index = gradId.datagrid('getRowIndex', item[i]); 
			gradId.datagrid('deleteRow', index); 
		}
	} 
}

/**
 * 根据进出口类型<br>
 * 过滤国家集合
 * @param inputType : 进出口类型
 * @param countryArray : 国家集合
 * @returns
 */
function checkTradeType(inputType, countryArray) {
	var array = [];
	if ('全部' == inputType) {
		array = reportArray.ALL;
	}
	if (inputType && countryArray) { // 不为空
		for (x in noFilterArray) {
			// 如果不存在不用过滤的国家,则加进数组中
			if (countryArray.indexOf(noFilterArray[x]) == -1) {
				array.push(noFilterArray[x]);
			}
		}
		for (x in countryArray) {
			// 如果字符中存在 inputType {进口,出口}
			if (countryArray[x].indexOf(inputType) != -1) {
				array.push(countryArray[x]);
			}
		}
	}
	return array;
}

/**
 * 根据进出口类型<br>
 * 获取国家集合
 * 
 * @param inputType
 */
function iexportFlexClick(inputType) {
	if (inputType == "I") {
		param.tradeType = "进口";
	} else if (inputType == "E") {
		param.tradeType = "出口";
	}
	// 加载国家combo
	checkParamCreateCountryArray();
}

/**
 * 贸易类型,传入中文,获取英文贸易类型
 * @param treadType : 贸易类型
 * @returns {String} 英文  贸易类型
 */
function getParseTradeType(treadType) {
	var result = "";
	if (treadType == "进口") {
		result = "I";
	} else if (treadType == "出口") {
		result = "E";
	}
	return result;
}

/**
 * 进出口类型{英文转中文}
 * @param treadType
 * @returns {String}
 */
function getTradeType(treadType) {
	var result = "";
	if (treadType == "I") {
		result = "进口";
	} else if (treadType == "E") {
		result = "出口";
	} else if (treadType == "I,E") {
		result = "全部";
	}
	return result;
}

/**
 * 根据条件对象生成国家集合<br>
 * 二级联动{}
 */
function checkParamCreateCountryArray() {
	var countryArray = [];
	if (param.reportType) {
		if (getCountryByReoportName(param.reportType)) {
			countryArray = getCountryByReoportName(param.reportType);
			if (param.tradeType) {
				param.countryArray = checkTradeType(param.tradeType,
						countryArray);
				getAppendCountryByParam();
				$("#quankuselectcountry").html("");
			}
		}
	}
}

/**
 * 清空 dataGrid
 * 
 * @param dataId
 */
function emptyDateGrid(dataId) {
	// dataId.datagrid('loadData', { total: 0, rows: [] });
	// 清空Datagrid
	if (dataId.data) {
		var item = dataId.datagrid('getRows');
		if (item) {
			for (var i = item.length - 1; i >= 0; i--) {
				var index = dataId.datagrid('getRowIndex', item[i]);
				dataId.datagrid('deleteRow', index);
			}
		}
	}
}

/**
 * 查看交易详情
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function searchAllDetail(value, row, index) {
	return '<a href="#" mce_href="#" onclick="searchTradeDetail(\''
			+ row.id
			+ '\',\''
			+ row.country
			+ '\')"><img style=\"border:0px;vertical-align: middle;\" src=\"'+getRootPath()+'/static/img/alldb/serach.png\" /></a>';
}

/**
 * 权库 客户信息 和 竞争对手 的 标示符 <br>
 * 1 : 竞争对手 2 : 客户信息 <br>
 * 
 * @param fontId :
 *            fontid 小窗口头部显示的文字
 */
function updateObj(fontId) {
	var titleValue = "";
	if (flagPage == 1) {
		titleValue = "关注的竞争对手供货分析";
	} else {
		titleValue = "我的客户的采购分析";
	}
	fontId.text(titleValue);
}

//*************************************************同环比用的arr*******************************
/**
 * 同环比 列表参数
 */
var tHArr = [
             {field:'date',title:'日期',width:80,sortable : true},
             {field:'money',title:'金额',width:80,sortable : true,formatter:function(value, row, index) {
				var realValue = fmoney(value, 2);
				return realValue;
			}},
             {field:'weight',title:'重量',width:80,sortable : true,formatter:function(value, row, index) {
				var realValue = fmoney(value, 2);
				return realValue;
			}},
             {field:'quantity',title:'数量',width:80,sortable : true,formatter:function(value, row, index) {
				var realValue = fmoney(value, 2);
				return realValue;
			}},
             {field:'packages',title:'件数',width:80,sortable : true,formatter:function(value, row, index) {
				var realValue = fmoney(value, 2);
				return realValue;
			}},
             {field:'count',title:'交易记录次数',width:80,sortable : true,formatter:function(value, row, index) {
				var realValue = fmoney(value, 2);
				return realValue;
			}}
             ];
//**************************************************同环比用的arr*******************************

/**
 * 获取echatsX轴的数据
 * @param field : 字段
 * @param title : 标题
 */
function collectEchatsX(field,title){
	var params = {
		'field' : field,
		'title' : title
	};
	return params;
}


/**
 * 汇总datagrid页面
 * 
 * @return
 */
function judgeEquirementquanku(tp) {
	var arr = new Array();
	if (tp == 'IMPORTERSUMMARY') {
		// 进口商
		arr.push({
			field : 'importer',
			title : getFormatStr("进口商"),
			titleValue : "进口商",
			sortable : true,
			align:'center',
			width : 206,
			formatter : function(value, row, index) {
				if (!value) {
					value = "N/A";
				}
				return value;
			}
		});

		arr.push({
			field : 'tradeWeight',
			title : getFormatStr('重量'),
			titleValue : '重量',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeMoney',
			title : getFormatStr('金额'),
			titleValue : '金额',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
				return realValue;
			}
		});
		arr.push({
			field : 'tradeQuantity',
			title : getFormatStr('数量'),
			titleValue : '数量',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
				return realValue;
			}
		});
		arr.push({
			field : 'tradeCount',
			title : getFormatStr('次数'),
			titleValue : '次数',
			align:'center',
			sortable : true,
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
				return realValue;
			}
		});
//		arr.push({
//			field : 'country',
//			title : getFormatStr(allDataReportJs.country),
//			width : 80,
//			formatter : function(value, row, index) {
//				if (!value) {
//					value = "N/A";
//				}
//				return value;
//			}
//		});
	} else if (tp == 'EXPORTERSUMMARY') {
		var ass = {};
		ass["field"] = "exporter";
		ass["title"] = getFormatStr("出口商");
		ass["titleValue"] = "出口商";	// 不作处理的文字
		ass["width"] = 206;
		ass["align"] = 'center';
		ass["formatter"] = function(value, row, index) {
			if (!value) {
				value = "N/A";
			}
			return value;
		}
		arr.push(ass);
		arr.push({
			field : 'tradeWeight',
			title : getFormatStr('重量'),
			titleValue : '重量',
			align:'center',
			sortable : true,
			width : 186,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeMoney',
			title : getFormatStr('金额'),
			titleValue : '金额',
			align:'center',
			sortable : true,
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeQuantity',
			title : getFormatStr('数量'),
			titleValue : '数量',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeCount',
			title : getFormatStr('次数'),
			titleValue : '次数',
			align:'center',
			sortable : true,
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
//		arr.push({
//			field : 'country',
//			title : getFormatStr(allDataReportJs.country),
//			width : 80,
//			formatter : function(value, row, index) {
//				return value;
//			}
//		});
	} else if (tp == 'PORTOFSHIPMENT') {
		var ass = {};
		ass["field"] = "start_port";
		ass["title"] = getFormatStr('起运港');
		ass["titleValue"] = '起运港';	// 不作处理的文字
		ass["width"] = 226;
		ass["align"] = 'center';
		arr.push(ass);
		arr.push({
			field : 'tradeWeight',
			title : getFormatStr('重量'),
			titleValue : '重量',
			sortable : true,
			width : 180,
			align:'center',
			formatter : function(value, row, index) {
				var realValue = fmoney(value, 2);
				return realValue;
			}
		});
		arr.push({
			field : 'tradeMoney',
			title : getFormatStr('金额'),
			titleValue : '金额',
			sortable : true,
			width : 180,
			align:'center',
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeQuantity',
			title : getFormatStr('数量'),
			titleValue : '数量',
			sortable : true,
			width : 180,
			align:'center',
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeCount',
			title : getFormatStr('次数'),
			titleValue : '次数',
			sortable : true,
			align:'center',
			width :180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
//		arr.push({
//			field : 'country',
//			title : getFormatStr(allDataReportJs.country),
//			width : 80,
//			formatter : function(value, row, index) {
//				return value;
//			}
//		});
	} else if (tp == 'PORTOFARRIVAL') {
		var ass = {};
		ass["field"] = "end_port";
		ass["title"] = getFormatStr('抵达港');
		ass["titleValue"] = '抵达港';	// 不作处理的文字
		ass["width"] = 226;
		ass["align"] = 'center';
		arr.push(ass);
		arr.push({
			field : 'tradeWeight',
			title : getFormatStr('重量'),
			titleValue : '重量',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = fmoney(value, 2);
				return realValue;
			}
		});
		arr.push({
			field : 'tradeMoney',
			title : getFormatStr('金额'),
			titleValue : '金额',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeQuantity',
			title : getFormatStr('数量'),
			titleValue : '数量',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeCount',
			title : getFormatStr('次数'),
			titleValue : '次数',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
//		arr.push({
//			field : 'country',
//			title : getFormatStr(allDataReportJs.country),
//			width : 80,
//			formatter : function(value, row, index) {
//				return value;
//			}
//		});
	} else if (tp == 'TRANSACTIONTREND') {
		var ass = {};
		ass["field"] = "date";
		ass["title"] = getFormatStr('日期');
		ass["titleValue"] = '日期';	// 不作处理的文字
		ass["width"] = 226;
		ass["align"] = 'center';
		ass["formatter"] = function (value, row, index) {
			if (!value) {
				value = "N/A";
			}
			return value;
		}
		arr.push(ass);
//		var ass1 = {};
//		ass1["field"] = "details";
//		ass1["title"] = getFormatStr("备注");
//		ass1["hidden"] = "true";
//		arr.push(ass1);
		arr.push({
			field : 'tradeWeight',
			title : getFormatStr('重量'),
			titleValue : '重量',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeMoney',
			title : getFormatStr('金额'),
			titleValue : '金额',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeQuantity',
			title : getFormatStr('数量'),
			titleValue : '数量',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeCount',
			title : getFormatStr('次数'),
			titleValue : '次数',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
	} else if (tp == 'GOODSSUM') {
		var ass = {};
		ass["field"] = "goodsDesc";
		ass["title"] = getFormatStr('产品描述');
		ass["titleValue"] = '产品描述';	// 不作处理的文字
		ass["width"] = 226;
		ass["align"] = 'center';
		ass["formatter"] = function (value, row, index) {
			if (!value) {
				value = "N/A";
			}
			return value;
		}
		arr.push(ass);
		arr.push({
			field : 'tradeWeight',
			title : getFormatStr('重量'),
			titleValue : '重量',
			align:'center',
			sortable : true,
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeMoney',
			title : getFormatStr('金额'),
			titleValue : '金额',
			align:'center',
			sortable : true,
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeQuantity',
			title : getFormatStr('次数'),
			titleValue : '次数',
			sortable : true,
			width : 180,
			align:'center',
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeCount',
			title : getFormatStr('数量'),
			titleValue : '数量',
			sortable : true,
			align:'center',
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
//		arr.push({
//			field : 'country',
//			title : getFormatStr(allDataReportJs.country),
//			width : 80,
//			formatter : function(value, row, index) {
//				return value;
//			}
//		});
	} else if (tp == 'NATIVE') {
		var ass = {};
		ass["field"] = "origin_country";
		ass["title"] = getFormatStr('产品描述');
		ass["titleValue"] = '产品描述';	// 不作处理的文字
		ass["width"] = 226;
		ass["align"] = 'center';
		arr.push(ass);
		arr.push({
			field : 'tradeWeight',
			title : getFormatStr('重量'),
			titleValue : '重量',
			align:'center',
			sortable : true,
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeMoney',
			title : getFormatStr('金额'),
			titleValue : '金额',
			align:'center',
			sortable : true,
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeQuantity',
			title : getFormatStr('数量'),
			titleValue : '数量',
			align:'center',
			sortable : true,
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
		arr.push({
			field : 'tradeCount',
			title : getFormatStr('次数'),
			titleValue : '次数',
			align:'center',
			sortable : true,
			width : 180,
			formatter : function(value, row, index) {
				var realValue = 0;
				if (value) {
					realValue = fmoney(value, 2);
				} else {
					realValue = '--';
				}
				return realValue;
			}
		});
	}
	echartsData = arr;
	return arr;
}

/**
 * 将数字以每3位逗号分割
 * 
 * @param s:处理前的数值
 * @param n:保留n位小数
 * @return 处理后的数字
 * @author honghao
 */
function fmoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}

//****************************************echarts******************************************
/**
 * 生成 Echarts 报表  折线图
 */
function createEcharts(data){
	var echartData = [];	//data数据
	var xDataArray = {};	//X轴
	var dataArray1 = {};	//数据
	var dataArray2 = {};	//数据
	var dataArray3 = {};	//数据
	var dataArray4 = {};	//数据
	var xData = [];
	var array1 = [];
	var array2 = [];
	var array3 = [];
	var array4 = [];
	for (x in data) {
		var newData = data[x];
		// 生成第一个data
		if (newData[echartsData[0].field]){
			xData[x] = newData[echartsData[0].field];
			xDataArray.data = xData;
			xDataArray.name = echartsData[0].titleValue;
			xDataArray.init = 0;
			xDataArray.type = 'x';
			xDataArray.yIndex = 0;
		} else {
			return null;		// 如果第一个字段为空,则直接返回null,不在显示图标s
			xData[x] = "N/A";
			xDataArray.data = xData;
			xDataArray.name = echartsData[0].titleValue;
			xDataArray.init = 0;
			xDataArray.type = 'x';
			xDataArray.yIndex = 0;
		}
		if (newData[echartsData[1].field] || newData[echartsData[1].field] == 0) {
			array1[x] = cutOutNum(newData[echartsData[1].field]);
			dataArray1.data = array1;
			dataArray1.name = echartsData[1].titleValue;
			dataArray1.init = 0;
			dataArray1.type = 'line';
			dataArray1.yIndex = 0;
		} 
		if (newData[echartsData[2].field] || newData[echartsData[2].field] == 0) {
			array2[x] = cutOutNum(newData[echartsData[2].field]);
			dataArray2.data = array2;
			dataArray2.name = echartsData[2].titleValue;
			dataArray2.init = 0;
			dataArray2.type = 'line';
			dataArray2.yIndex = 0;
		} 
		if (newData[echartsData[3].field] || newData[echartsData[3].field] == 0) {
			array3[x] = cutOutNum(newData[echartsData[3].field]);
			dataArray3.data = array3;
			dataArray3.name = echartsData[3].titleValue;
			dataArray3.init = 0;
			dataArray3.type = 'line';
			dataArray3.yIndex = 0;
		} 
		if (newData[echartsData[4].field] || newData[echartsData[4].field] == 0) {
			array4[x] = cutOutNum(newData[echartsData[4].field]);
			dataArray4.data = array4;
			dataArray4.name = echartsData[4].titleValue;
			dataArray4.init = 0;
			dataArray4.type = 'line';
			dataArray4.yIndex = 0;
		}
	}
	echartData.push(xDataArray);
	echartData.push(dataArray1);
	echartData.push(dataArray2);
	echartData.push(dataArray3);
	echartData.push(dataArray4);
	return echartData;
}

/**
 * 饼图数据
 */
function pieCreateEcharts(data){
	var pieData = [];	//data数据
	var index = getIndexByParams(echartsData);	// 获取重量下标
	var totalWeight = 0;
	for (x in data) {
		var pieDatas = {};
		var newData = data[x];
		// 生成第一个data
		if (newData[echartsData[0].field]){
			var resultName = newData[echartsData[0].field];
			pieDatas.name = resultName;
		} else {
			return null;		// 如果第一个字段为空,则直接返回null,不在显示图标
			xData[x] = "N/A";
			pieDatas.name = xData[x];
		}
		// 饼图只对重量做处理
		var resultValue = newData[echartsData[index].field];
		totalWeight += resultValue;
		pieDatas.value = resultValue;
		pieData.push(pieDatas);
	}
	return pieData;
}

/**
 * 获取重量的索引下标
 * @param echartsData
 */
function getIndexByParams(echartsData) {
	for (var i = 0; i < echartsData.length; i++) {
		if (echartsData[i].field == 'tradeWeight') {
			return i;
		}
	}
}

/**
 * 截取字符串,根据不同类型截取不同格式数据
 * @param numOrStr : 传入数字
 * @param type : 类型
 */
function cutOutNum(numOrStr){
	//如果是数字
//	if (!isNaN(numOrStr)) {
//		numOrStr = fmoney(numOrStr, 2);	//截取2位
//		var result = replaceComma(numOrStr,",", /,/g, '.');
//		return result;
//	}
//	return numOrStr;
	
	numOrStr = fmoney(numOrStr, 2);	//截取2位
	var radixBefore = numOrStr.substring(0,numOrStr.lastIndexOf('.'));
	var radlxLater = numOrStr.substring(numOrStr.lastIndexOf('.'),numOrStr.lang);
	var result = replaceComma(radixBefore,",", /,/g, '');
	return result + radlxLater;
}

/**
 * 替换
 * @param prams : 原字符串
 * @param typeParams : 目标字符
 * @param fromToParams : 元字符
 * @returns prams
 */
function replaceComma(prams,paramStr,typeParams,fromToParams) {
	if (prams.indexOf(paramStr) != -1) {
		prams = prams.replace(typeParams,fromToParams);
	}
	return prams;
}

/**
 * 交易记录Arr
 */
var treadArr = [
            	{field:'country',title:'国家',width:80},
            	{field:'goodsdescription',title:'产品描述',width:150},
            	{field:'hscode',title:'海关编码',width:80,align:'right'},
            	{field:'date',title:'日期',width:80,align:'right',sortable :true},
            	{field:'importer',title:'进口商 ',width:150,align:'right'},
            	{field:'exporter',title:'出口商 ',width:150,align:'right'},
            	{field:'quantity',title:'数量',width:80,align:'right',sortable :true},
            	{field:'weight',title:'重量',width:80,align:'right',sortable :true},
            	{field:'totalprice',title:'金额 ',width:80,align:'right',sortable :true},
            	{field:'orgincountry',title:'原产国  ',width:80,align:'right'},
            	{field:'id',title:'主键 ',width:80,align:'right',hidden:true},
            	{field:'detail',title:'查看详情 ',width:80,align:'right',formatter:searchAllDetail},
                ];

/**
 * 获取标题
 * @param params
 */
function getTitle(params){
	var resultTitle = "";
	if ('PORTOFSHIPMENT' == params){
		resultTitle = "全球起运港报告";
	} else if ('PORTOFARRIVAL' == params){
		resultTitle = "全球抵达港报告";
	} else if ('IMPORTERSUMMARY' == params){
		resultTitle = "全球采购商报告";
	} else if ('EXPORTERSUMMARY' == params){
		resultTitle = "全球供应商报告";
	} else if ('TRANSACTIONTREND' == params){
		resultTitle = "市场趋势报告";
	} else if ('NATIVE' == params){
		resultTitle = "原产国报告";
	} else if ('TRADE' == params){
		resultTitle = "交易记录";
	} else if ('GOODSSUM' == params) {
		resultTitle = '采购产品分析报告';
	} 
	return resultTitle;
}


//******************************************************************权库进度条********************************************************************

var timeInit;				//定时器

var countryArray = [];		//国家集合

var progressbars;			//进度条对象

var countryIndex = 0;		//国家集合下标

var index = 0;				//数字下标

var resultText = "";		//文本

var showText = "";			//显示文本对象

/**
 * 生成 进度条
 */
function createProgress(progressbarsId,showTextId,country) {
	progressbars = $('div[name='+progressbarsId+']');
	showText = $('span[name='+showTextId+']');
	if (country) {			//判断国家是否不为空
		countryArray = checkCountry(country.split(','));
	}
	timeInit = setInterval('countTotal()',50);		//定时器
}

/**
 * 进度条 处理函数
 */
function countTotal(){
	changeValue(progressbars, index)
	if (countryIndex == countryArray.length) {
		clearInterval(timeInit);		//结束定时
		changeValue(progressbars,99);	//国家已加载完,等待被销毁
		showText.text('正在查询' + resultText + "99%");
	} else {
		index = index + 1;
		resultText = countryArray[countryIndex];
		if (index == 100) {	
			index = 0;
			resultText = "";
			resultText += countryArray[countryIndex];
			countryIndex += 1;
		}
		showText.text('正在查询' + resultText + '  ' + index +"%");
	}
}

/**
 * 验证国家个个元素,如果为空,则删除
 * @param countryArrays
 * @returns {Array}
 */
function checkCountry(countryArrays) {
	var countrys = [];
	for (x in countryArrays) {
		if (countryArrays[x]) {
			countrys.push(countryArrays[x]);
		}
	}
	return countrys;
}

/**
 * 查询结束,调用
 * @param obj : 进度条div对象
 */
function CheckDataOver(obj){
	clearInterval(timeInit);				//结束定时
	countryIndex = 0;
	index = 0;
	changeValue(progressbars,100);
	showText.text('所有国家查询完毕!');
	setTimeout(function(){
		obj.hide(1000);
	},1000);
}

/**
 * 改变进度条的值
 * @param obj
 * @param value
 */
function changeValue(obj,value) {
	obj.progressbar('setValue',value);
}


//******************************************************************权库进度条********************************************************************

/**
 * 生成 下载进度条
 */
function createDownData(progressbarsId,showTextId,country,singleCountry) {
	progressbars = $('div[name='+progressbarsId+']');
	showText = $('span[name='+showTextId+']');
	if (country) {			//判断国家是否不为空
		countryArray = checkCountry(country.split(','));
	}
	downDataTotal(singleCountry);			//进度条
	//timeInit = setInterval('downDataTotal('+country+')',0.1);		//定时器
}

/**
 * 进度条
 */
function downDataTotal(singleCountry){
	var proTotal = parseInt(100 / countryArray.length);
	var index = parseInt(getCountryIndex(singleCountry));
	var ProIndex = proTotal * index;
	changeValue(progressbars, proIndex);
}

/**
 * 获取国家下标
 */
function getCountryIndex(country) {
	for (var i = 0; i < countryArray.length; i++) {
		if (country == countryArray[i]) {
			return i;
		}
	}
}

/**
 * 下载结束,调用
 * @param obj : 进度条div对象
 */
function closeDataDown(obj){
	clearInterval(timeInit);				//结束定时
	countryIndex = 0;
	index = 0;
	changeValue(progressbars,100);
	showText.text('所有国家下载完毕!');
	setTimeout(function(){
		obj.hide(1000);
	},1000);
}


/**************************************************客户与对手 的小窗口事件************************************************/

/**
 * 改变客户 小窗口图片
 */
function changeImgOfCus(){
	$("#customerImg").attr("src","/gbdbas/static/img/alldb/customer2.png");
	$("#competorImg").attr("src","/gbdbas/static/img/alldb/competitor.png");
}

/**
 * 改变对手 小窗口图片
 */
function changeImgOfCom(){
	$("#competorImg").attr("src","/gbdbas/static/img/alldb/competitor2.png");
	$("#customerImg").attr("src","/gbdbas/static/img/alldb/customer.png");
}



//****************************************查看详情  翻译****************************
/**
 * 查看详情翻译语言列表显示
 * @author honghao
 */
function show(v){
	document.getElementById(v).style.display="block";
}

/**
 * 查看详情翻译隐藏语言列表显示
 * @author honghao
 */
function hide(v){
	document.getElementById(v).style.display="none";
}
/**
 * 查看详情翻译语言列表显示鼠标悬浮样式
 * @author honghao
 */
function Hover(v){
	document.getElementById(v).style.backgroundColor="#383f48";
}

/**
 * 查看详情翻译语言列表显示鼠标离开样式
 * @author honghao
 */
function notHover(v){
	document.getElementById(v).style.backgroundColor="#434a53";
}

/**
 * 查看详情翻译
 * @param language 语言
 * @author XL
 * 
 */
function translation(language) 
{
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	var id = [];
	var filePath = localhostPaht+"/gbdbas/view/datasearch/language/" + language + ".txt";
	$.ajax( {
		type : "POST",
		url : localhostPaht+"/gbdbas/view/datasearch/language/id.txt",
		dataType : 'text',
		success : function(data) 
		{
			id = data.split("\n");
			$.ajax( {
				type : "POST",
				url : filePath,
				dataType : 'text',
				success : function(data)
				{
					var language = data.split("\n");
					for ( var i = 0; i < id.length; i++)
					{
						var oneid = "name_"+id[i];
						changeLanguage(oneid.trim(), language[i].trim());
					}
				}
			});
		}
	});
}

/**
 * 翻译内容
 * @param id 当前翻译值的id
 * @param value 翻译值
 */
function changeLanguage(id, value) 
{
	var translationId = document.getElementById(id);
	if (translationId) {
		translationId.innerHTML = value;
	}
}