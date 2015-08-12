/**
 * -----------------------------------------------市场分析------------------------------------------------------
 */

var datagridlength1 = 0;

var countryArray; 	// 国家集合
var reportType;		//报告类型

var queryParams = {};	//查询参数
$(function() {
//	// 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
//	var pp = $("div[name='reportValueCompFlexComeFlexBr']").tabs('getSelected');
//	var tab = pp.panel('options').tab; // 相应的标签页（tab）对象
//	var reportTypeNew = tab.text();
//	if (checkIsNotNull(reportTypeNew)) {
//		reportType = getCountryByChinaTitle(reportTypeNew);
//	}
	$("#conten").hide();
	$("[name='marketplaceDataGrid']").hide();
	countryArray = reportArray.ALL;
	// 展示所有国家下拉列表
	allCountryOptionShow36();
});

/**
 * 展示所有国家下拉列表 --WangBo
 */
function allCountryOptionShow36() {
	$("#sps36").append("<input type=\"checkbox\" onClick=\"selectManayAllCountry('complang','alreadyselectcountry',this,'equirementSelects36')\" id=\"quankuallCompCountry\" name=\"quankuallCompCountry\"><span>Select All</span><br/>")
	for(var i=0; i < countryArray.length; i++){
		if (countryArray[i]) {
			$("#sps36").append("<input type=\"checkbox\" onClick=\"changeOnlyCountryNameAddText('complang','alreadyselectcountry','equirementSelects36')\" name=\"complang\"  value=\""+countryArray[i]+"\"><span>"+countryArray[i]+"</span><br/>")
		}
	}
	$('#equirementSelects36').combo({
		editable:false
	});
	$('#sps36').appendTo($('#equirementSelects36').combo('panel'));
}

/**
 * 初始化查询model
 */
var initQueryParams = function(){
	queryParams.hscode = $("input[name='hscodeFlexBr']").val();
	queryParams.companyName = $("input[name='jzexporter']").val();
	queryParams.beginDateFlex = $("input[name='compbegindateFlexBr']").val();
	queryParams.endDateFlex = $("input[name='compenddateFlexBr']").val();
	queryParams.countrySelect = subFlexSearchs();
	return queryParams;
}

/**
 * 点击查询
 * 
 * @param v
 */
function quankuCompsent(v) {
	var flagIndex;
	//显示图表和dg
	$("#dg_echart").show();	
	//tab页
	$("#right_library_mebubox").show();		// 显示列表
	initQueryParams();						// 初始化参数
	// 验证合法性
	if (!checkSibmitHtml(queryParams)){
		return;
	}
	//权库 客户信息 和 竞争对手 的 标示符 1 : 竞争对手 2 : 客户信息 
	if (flagPage == 1){	// 竞争对手
		// 出口商
		queryParams.tradeType = "E";
		//报告类型   第一次加载 默认为页面显示的第一个报告类型  注意{每点击一次查询都默认}
		// TODO queryParams.flexType = "IMPORTERSUMMARY";
		// 请求url
		queryParams.url = getRootPath() + "/competitor/queryCompetitorMarketData";
		// {市场分析}保存为共享对象 提供给报告类型切换查询使用
		queryParam.marketplace = queryParams;
		// 设置为进口商,中国,韩国,俄罗斯 和其他国家字段区别.
//		queryParams.importer = $("input[name='jzexporter']").val();
		queryParams.exporter = $("input[name='jzexporter']").val();
		// url 保存在 全局对象中
		$("[name='marketplaceDataGrid']").datagrid({
			url : getRootPath() + "/competitor/queryCompetitorMarketData",
			queryParams : queryParams,
			columns : [judgeEquirementquanku(queryParams.flexType)],
			pageNumber : 1,
			pageSize : 10,
			pagination:true,
			singleSelect:false,
			loadMsg:"正在查询...",
			showFooter:true,
			autoRowHeight:false,
			onBeforeLoad : function(param){
				flagIndex = param.page;
				if (1 == flagIndex) {
					$('div[name="clientProgress"]').show();
					createProgress('clientProgressBar','dbshowLoadStatus', param.countrySelect);
				}
			},
			onLoadSuccess:function(data){
				// 暂停定时器
				if (1 == flagIndex) {
					CheckDataOver($('div[name="clientProgress"]'));
				}
//				$("div[name='dataTables']").show();		// 显示列表X
				if (data.total == 0){
					$.messager.alert("提示", "未查询到数据");
					//显示图表和dg
					$("#echartsDiv").hide();	
					return;
				}
				$("#conten").show();
				data = data.rows;
				var echartData = createEcharts(data);
				if (!echartData) {
					$("#echartsDiv").hide();
					return;
				}
				$("#echartsDiv").show();	
				showMixEchart('echartsDiv',echartData , getTitle(queryParams.flexType), '', 950, 500, '123', 'xxxdd');
			}
		}); 
	} else if (flagPage == 2) {		//客户信息 
		// 进口商
		queryParams.tradeType = "I";
		initQueryParams();					// 初始化查询参数
		// 请求url
		queryParams.url = getRootPath() + "/customer/queryCustomerMarketData";
		// {市场分析}保存为共享对象 提供给报告类型切换查询使用
		queryParam.marketplace = queryParams;
		// 设置为进口商,中国,韩国,俄罗斯 和其他国家字段区别.
//		queryParams.exporter = $("input[name='jzexporter']").val();
		queryParams.importer = $("input[name='jzexporter']").val();
		$("[name='marketplaceDataGrid']").datagrid({
			url : getRootPath() + "/customer/queryCustomerMarketData",
			queryParams : queryParams,
			columns : [judgeEquirementquanku(queryParams.flexType)],
			autoRowHeight:false,
			pagination:true,
			singleSelect:false,
			loadMsg:"正在查询...",
			showFooter:true,
			pageNumber : 1,
			pageSize : 10,
			onBeforeLoad : function(param){
				if (1 == flagIndex) {
					$('div[name="clientProgress"]').show();
					createProgress('clientProgressBar','dbshowLoadStatus', param.countrySelect);
				}
			},
			onLoadSuccess:function(data){
				//暂停定时器
				if (1 == flagIndex) {
					CheckDataOver($('div[name="clientProgress"]'));
				}
				if (data.total == 0){
					$.messager.alert("提示", "未查询到数据");
					//显示图表和dg
					$("#echartsDiv").hide();	
					return;
				}
				data = data.rows;
				var echartData = createEcharts(data);
				if (!echartData) {
					$("#echartsDiv").hide();
					return;
				}
				$("#echartsDiv").show();
				showMixEchart('echartsDiv',echartData , getTitle(queryParams.flexType), '', 950, 500, '123', 'xxxdd');
			}
		});
	}
}