/**
 * ------------------------------------------产品分析----------------------------------------------
 */

var dataGrid;
var flag = 1; // 1 : 保存产品 2 : 编辑产品
$(function() {
	dataGrid = $("#productdg");
	// 添加产品
	$("#addproduct").click(addProduct);
	// 搜索海关编码
	$("#queryHscode").searchbox(queryHscodeMethod);
	// 修改产品信息
	$("#updateproduct").click(updateproduct);
	// 删除产品信息
	$("#delproduct").click(delproduct);
	// 默认报告类型
	var reportName = 'PORTOFSHIPMENT';
	param.reportType = reportName;
	// 获取国家列表,填充combo
	// checkParamCreateCountryArray();
	getAppendCountry(reportName);
	//改表一级菜单上产品标签模块的背景色
	$("#nav-item_4").css('backgroundColor','#ffffff');
	$("#nav-item_4").find("a").find("span").css('color','#0066cc');
});

/**
 * 添加产品
 */
function addProduct() {
	// addProductTag
	flag = 1;
	checkInputByNum('');
	openDivArtDialog('Add Product', 'addProductTag', 'addProduct', 650, 400, true);
}

/**
 * 删除产品信息
 */
function delproduct() {
	var rows = dataGrid.datagrid("getSelections");
	if (rows.length > 0) {
		var productId = "";
		for (var i = 0; i < rows.length; i++) {
			productId += rows[i].productId + "#";
		}
		if (productId.lastIndexOf(productId, ",") != -1) {
			productId = productId.substring(0, productId.length - 1);
		}
		// var searchParam = $("#companyName").val().trim();
		var hsCode = $("#queryHscode").val();
		$.messager.confirm("Prompt", "Delete this product?",
				function(r) {
					if (r) {
						easyUiAjaxDel(dataGrid, getRootPath()
								+ '/projectAnalyze/deleteProjectAnalyze', {
							'deleteId' : productId,
							'hscode' : hsCode
						}, delFromTable);
					}
				});
	} else {
		$.messager.alert("Prompt", "Please Choose!", "info");
	}
}

/**
 * 删除回调函数
 */
function delFromTable(data) {
	var result = "";
	if (data.result == 1) {
		result = "Delete Successfully!";
	} else {
		result = "Delete Failed!";
	}
	$.messager.alert("Prompt", result, "info");
}

/**
 * 处理为空的数据显示N/A
 */
function isNullFormat(value, rowData, rowIndex){
	if(value == ''){
		return 'N/A';
	}
	return value;
}

/**
 * 如果值为空,则显示--
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns
 */
function checkValueSmall(value, rowData, rowIndex) {
	console.debug(value);
	if (!value) {
		return "--";
	}
	return value;
}

/**
 * 生成图片
 */
function addOps(value, rowData, rowIndex) {
	var fx = '<img src="'
			+ getRootPath()
			+ '/static/img/alldb/scfx.png" style="vertical-align:middle;padding-right:5px;"><a onmouseover=\'this.style.textDecoration="underline";\' onMouseOut=\'this.style.textDecoration="none";\' href="#" mce_href="#" style="font-size:12px;color:#656565;" onclick="fenxi(\''
			+ rowData.hscode + "','" + rowData.productName
			+ '\')">Market Analysis</a> &nbsp;&nbsp;&nbsp;&nbsp;';
	// 买家
	var mj = '<img src="'
			+ getRootPath()
			+ '/static/img/alldb/buyers.png" style="vertical-align:middle;padding-right:5px;"><a onmouseover=\'this.style.textDecoration="underline";\' onMouseOut=\'this.style.textDecoration="none";\' href="#" mce_href="#" style="font-size:12px;color:#656565;" onclick="buyer(\''
			+ rowData.hscode
			+ '\',\''
			+ rowData.productName
			+ '\')">Buyer</a> &nbsp;&nbsp;&nbsp;&nbsp;';
	var jz = '<img src="'
			+ getRootPath()
			+ '/static/img/alldb/jz.png" style="vertical-align:middle;padding-right:5px;"><a onmouseover=\'this.style.textDecoration="underline";\' onMouseOut=\'this.style.textDecoration="none";\' href="#" mce_href="#" style="font-size:12px;color:#656565;" onclick="jingzheng(\''
			+ rowData.hscode + "','" + rowData.productName
			+ '\')">Competitor</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return fx + mj + jz;
}

/**
 * 取消保存产品信息
 */
function closeArtDlg() {
	art.dialog({
		id : 'addProduct'
	}).close();
}

/**
 * 修改产品
 */
function updateproduct() {
	flag = 2;
	var rows = dataGrid.datagrid("getSelections");
	if (rows.length == 1) {
		checkInputByNum(rows[0]);
		openDivArtDialog('Add Product', 'addProductTag', 'addProduct', 650, 400, true);
	} else {
		$.messager.alert("Prompt", "请选择要编辑的产品,只能选择一个", 'info');
	}
}

/**
 * 搜索 hsCode
 */
queryHscodeMethod = {
	searcher : function(value, name) {
		dataGrid.datagrid({
			url : getRootPath() + "/projectAnalyze/queryProjectAnalyzeByParam",
			queryParams : {'hscode' : value},
			onLoadSuccess : function(data) {
				console.debug(data);
			},
			onLoadError : function(data) {
				console.debug(data);
			}
		});
	},
	menu : '#mm',
	prompt : 'HS Search'
}


/**
 * 新增 && 编辑 产品标签
 * 
 * @returns {Boolean}
 */
function addProInfo() {
	var url = '';
	// 判断 [提交url || 编辑url]
	var dateGridUrl = "";
	if (flag == 1) {
		url = getRootPath() + '/projectAnalyze/addProjectAnalyze';
	} else if (flag == 2) {
		url = getRootPath() + "/projectAnalyze/updateProjectAnalyzeByParam"
	}
	// 验证
	if (!checkProjuctFromValue()) {
		return;
	}
	$('#addProdg').form(
			'submit',
			{
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(data) {
					var dataObj = eval("(" + data + ")");
					var result = "";
					if (flag == 1) {
						if (dataObj.result == 1) {
							result = "Add Successfully!";
						} else {
							result = "Add Failed!";
						}
					} else {
						if (dataObj.result == 1) {
							result = "Edit Successfully!";
						} else {
							result = "Edit Failed!";
						}
					}
					easyUiAjaxLoad(dataGrid, getRootPath()
							+ "/projectAnalyze/queryProjectAnalyzeByParam", {},
							loadMethod);
					$.messager.alert("Prompt", result);
					art.dialog({
						id : 'addProduct'
					}).close();
				}
			});
}

/**
 * 检查{产品} 添加 && 编辑 页面<br>
 * 是否为空值
 * 
 * @returns {Boolean}
 */
function checkProjuctFromValue() {
	var verifycode = $('#verifycode').val();
	var hscode2 = $("#hscode2").val();
	var productName = $('#productName').val();
	if (verifycode.trim() == "") {
		$.messager.alert("Prompt", "标签名称不能为空", 'info');
		return false;
	}
	if (hscode2.trim() == "") {
		$.messager.alert("Prompt", "HS编码不能为空", 'info');
		return false;
	} else {
		var test = /^\d{6,8}$/;
		if (!test.test(hscode2)) {
			$.messager.alert("Prompt", "HS Code must be 6 or 8 digits number!", 'info');
			return false;
		}
	}
	if (productName.trim() == "") {
		$.messager.alert("Prompt", "Product description CAN NOT be empty！", 'info');
		return false;
	} 
//	else {
//		var parent = /^([A-Za-z]+\s?)*[A-Za-z]$/;
//		if (!parent.test(productName)) {
//			$.messager.alert("Prompt", "对不起，请输入英文，字符串开头不能有空格，可添加多个关键字，必须以单个空格分隔！",
//					'info');
//			return false;
//		}
//	}
	return true;
}

/**
 * 回调函数
 */
function loadMethod(data) {
	if (data.rows.length == 0) {
		$.messager.alert("Prompt", "对不起!没有查询到符合您条件的记录数...", 'info');
	} else {
		flagPage = data.flag;
	}
}

function isValue(value, row, index) {
	var realValue = fmoney(value, 2);
	return realValue;
}

/**
 * 检查 保存 && 修改<br>
 * 产品信息
 * 
 * @param params :
 *            参数对象
 */
function checkInputByNum(params) {
	if (flag == 1) {
		// 保存
		$("#verifycode").val('');
		$("#hscode2").val('');
		$("#productName").val('');
	} else if (flag == 2) {
		// 编辑
		if (params) {
			$("#verifycode").val(params.verifycode);
			$("#hscode2").val(params.hscode);
			$("#productName").val(params.productName);
			$("#productId").val(params.productId);
		}
	}
}

/**
 * 获取国家列表,<br>
 * 填充combox
 */
function getAppendCountry(reportName) {
	// 竞争对手 和 市场分析 共同的name
	var country = $("select[name=bqopponentcountry]");
	// 根据报告类型获取国家
	var reportCountry = getCountryByReoportName(reportName);
	// 存放入国家集合
	param.countryArray = reportCountry;
	$("#sps9").empty();
	$("#sps9")
			.append(
					"<input type=\"checkbox\" onClick=\"qkscfxselectAllCountry(this,'equirementSelects9')\" id=\"quankuall\" name=\"quankuall\"><span>全选</span><br/>")
	for (var i = 0; i < reportCountry.length; i++) {
		$("#sps9")
				.append(
						"<input type=\"checkbox\" onClick=\"qkscfxchangeCountryNameAddText('equirementSelects9')\" name=\"quankulang\"  value=\""
								+ reportCountry[i]
								+ "\"><span>"
								+ reportCountry[i] + "</span><br/>")
	}
	$('#equirementSelects9').combo({
		editable : false
	});
	$('#sps9').appendTo($('#equirementSelects9').combo('panel'));
}

/**
 * 获取国家列表,<br>
 * 填充combox
 */
function getAppendCountryByParam() {
	var countryArray = param.countryArray;
	if (countryArray) {
		// 竞争对手 和 市场分析 共同的name
		var country = $("select[name=bqopponentcountry]");
		$("#sps9").empty();
		$("#sps9")
				.append(
						"<input type=\"checkbox\" onClick=\"qkscfxselectAllCountry(this,'equirementSelects9')\" id=\"quankuall\" name=\"quankuall\"><span>全选</span><br/>")
		for (x in countryArray) {
			$("#sps9")
					.append(
							"<input type=\"checkbox\" onClick=\"qkscfxchangeCountryNameAddText('equirementSelects9')\" name=\"quankulang\"  value=\""
									+ countryArray[x]
									+ "\"><span>"
									+ countryArray[x] + "</span><br/>")
		}
		$('#equirementSelects9').combo({
			editable : false
		});
		$('#sps9').appendTo($('#equirementSelects9').combo('panel'));
	}
}

/**
 * 鼠标移动上去显示对应的title
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @return
 */
function formatTitle(value,rowData,rowIndex){
    return "<font title='"+value+"'>"+value+"</font>";
}

/**
 * 买家详细
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {String}
 */
function addDetials(value,rowData,rowIndex) {
	//交易数据不要删除，日后可能需要
	var mj = '<img src="'+getRootPath()+'/static/img/alldb/buyer.png" style="vertical-align:middle;padding-right:5px;"><a onmouseover=\'this.style.textDecoration="underline";\' onMouseOut=\'this.style.textDecoration="none";\' href="#" mce_href="#" style="font-size:12px;color:#656565;" onclick="showCustomerNameForAllDB(\''+ rowData.companyName + '\')">添加客户</a> &nbsp;&nbsp;&nbsp;&nbsp;';
	return mj;
}

/**
 * 买家资源库
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {String}
 */
function isNull(value,rowData,rowIndex) {
	//交易数据不要删除，日后可能需要
	if(value == ''){
		return 'N/A';
	}
	return value;
}

//**************************************************市场分析 交易趋势  同环比**********************************
$(function(){
	// 给同环比 单选按钮 注册 click
	$("#withRadio :radio").click(withClick);
});

/**
 * 市场分析 同环比 
 */
function withClick() {
	var widthType = $(this).val();			//获取同环比的按钮的值
	queryParam.marketplace.widthType = widthType;
	// 生成arr 显示参数{dataGrid}
	var arr = judgeEquirementquanku(queryParam.marketplace.flexType);
	$("table[name='marketplaceDataGrid']").datagrid({
		url : getRootPath() + '/WithSequential/queryWithSequential',
		queryParams : queryParam.marketplace,
		title : getTitle(param.reportType),
		columns : [ tHArr ],
		pageNumber : 1,
		pagination:true,
		singleSelect:false,
		loadMsg:'Loading...',
		showFooter : true,
		onBeforeLoad : function(param){
			$('div[name="clientProgress"]').show();
			createProgress('clientProgressBar','dbshowLoadStatus', param.countrySelect);
		},
		onLoadError : function(data) {
			// 查询按钮还原样式
			// qkreportQueryStartDisplay();
			$('#clientProgress').hide();
		},
		onLoadSuccess : function(data) {
			// 暂停定时器
			CheckDataOver($('div[name="clientProgress"]'));
			$("#conten").show();					//显示报表和图表
			$("#right_library_mebubox").show();		//显示报告类型
			if (data.rows.length > 0) {
				//生成 Echarts 报表 折线图 
				var echatsDate = getTHBarData(data.rows);
				showMixEchart('echartsDiv',echatsDate,'市场趋势报告', '', 930, 500, '', '');
			} else {
				$.messager.alert("Prompt","No data founded!",'info');
				$("#conten").hide();
				$("#echartsDiv").html('');
			}
		}
	});
}
	
/**
 * 生成echarts报表数据<br>
 * 同环比 设置日期为x轴数据
 */
function getTHBarData(data){
	var dataArray = [];
	if (data) {
		for (var i = 0; i < WithArray.length; i++) {
			var xDataArray = {};
			var xDate = [];
			for (var y = 0; y < data.length; y++) {
				if (WithArray[i] == 'date') {		// 如果为日期,则不处理
					var resultNum = data[y][WithArray[i]];
					xDate[y] = resultNum;
				} else {
					var resultNum = data[y][WithArray[i]];
					xDate[y] = cutOutNum(resultNum);
				}
			}
			xDataArray.data = xDate;
			xDataArray.name = WithEnArray[i];
			xDataArray.init = 0;
			if (WithArray[i] == 'date') {		//设置为x轴为日期
				xDataArray.type = 'x';
			} else {
				xDataArray.type = 'bar';
			}
			xDataArray.yIndex = 0;
			dataArray.push(xDataArray);
		}
	}
	return dataArray;
}

/**
 * 点击全选
 * @param all
 * @param countryId
 */
function qkscfxselectAllCountry(all, countryId) {
	var lang = $("input[name='quankulang']");
	var s = "";
	for (var i = 0; i < lang.length; i++) {
		lang[i].checked = all.checked;
		s = s + lang[i].value + "、&nbsp;";
	}
	if (all.checked) {
		$("#quankuselectcountry").html(
				"<font color='#0066CC' style='font-family: 微软雅黑,宋体;'>*You have chosen: &nbsp;:&nbsp;"
						+ s + "</font>");
	} else {
		$("#quankuselectcountry").html("");
	}
	$('#' + countryId).combo('setText', s.substring(0, s.lastIndexOf(',')))
			.combo('hidePanel');
}

/**
 * 市场分析
 * @param hscode
 * @param goodsdescription
 */
function fenxi(hscode, goodsdescription) {
	$("#quankuhscode").val(hscode);
	$("#quankugoodsdesc").val(goodsdescription);
	$("#right_library_mebubox").hide();	// 隐藏报告类型
	$("#con_tab_1").hide();  //隐藏datagrid
	var inputType = $('input[name="quankuiexportFlex"]:checked').val();
	// 进出口类型
	param.tradeType = inputType;
	// 过滤国家集合
	checkParamCreateCountryArray();
	// TODO 默认选择第一个报告类型 
	queryParam.marketplace = {
			flexType : $("#right_library_mebubox").find("ul li:first span:first").html()
	};
	// 隐藏进度条
	$("div[name='clientProgress']").hide();
	// 加载国家
	openDivArtDialog("Market Analysis", 'quankuDiv', 'quankuDivdsa', 950, 600, true);
}

/**
 * 竞争对手
 * 
 * @param hscode
 * @param goodsdescription
 */
function jingzheng(hscode, goodsdescription) {
	var competitorDataGrid = $('#bqCompetitorsdg');
	//隐藏datagrid
	$("#procurementDiv").hide();
//	emptyDateGrid(competitorDataGrid);
//	emptyDateGrid($('#bquyerCompetitorsdg'));
	// 加载国家
	callCountryOption();
	// 清空公司名称
	$("#bqcompanyName").val("");
	// 请求数据
	queryParams = {
			'hscode':hscode.trim(),
			'goodsdescription':goodsdescription.trim(),
			'beginDateFlex' : '2014-01-01',
			'endDateFlex' : '2014-12-31',
			'countrySelect' : splitCountry(reportArray.ALL),
			'flexType' : 'EXPORTERSUMMARY'
		};
	// 把hscode 和 goodsdescription 放入隐藏Input
	$("#competitorHsCode").val(hscode.trim());
	$("#competitorGoodsDesp").val(goodsdescription.trim());
	// 请求数据
	easyUiAjaxLoad(competitorDataGrid, getRootPath() + "/projectAnalyze/queryCompetitor", queryParams, function(data){
    	if(data.rows.length == 0){
    		$.messager.alert("Prompt","No data founded.....",'info');
	    }
 	});
	openDivArtDialog("Competitor", 'bqCompetitors', 'dbAllCompetitor', 1000,520,
			true);
}

/**
 * 截取Str
 * @param country
 * @returns {String}
 */
function splitCountry(country){
	var countrys = reportArray.ALL;
	var result = "";
	for (var i = 0; i < countrys.length; i++) {
		if (i == countrys.length-1) {
			result += countrys[i];
		} else {
			result += countrys[i] + ",";
		}
	}
	return result;
}

/**
 * 标签竞争操作栏<br>
 * 竞争对手
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @return
 */
function bqaddOpss(value, rowData, rowIndex) {
	// 海关编码
	var hscode = $("input[name='quankuhscode']").val();
	// 旅行时间(第一段)
	var desp = $("input[name='quankugoodsdesc']").val();
	var fx = '<img src="'
			+ getRootPath()
			+ '/static/img/alldb/jz.png" style="vertical-align:middle;padding-right:5px;"><a onmouseover=\'this.style.textDecoration="underline";\' onMouseOut=\'this.style.textDecoration="none";\' href="#" mce_href="#" style="font-size:12px;color:#656565;" onclick="showCompanyNameForAllDB(\''
			+ rowData.exporter + '\')">Add Competitor</a>';
	var jy = '<img src="'
			+ getRootPath()
			+ '/static/img/alldb/scfx.png" style="vertical-align:middle;padding-right:5px;"><a onmouseover=\'this.style.textDecoration="underline";\' onMouseOut=\'this.style.textDecoration="none";\' href="#" mce_href="#" style="font-size:12px;color:#656565;" onclick="queryProcurement(\''
			+ rowData.exporter + "','" + rowData.country + '\')">Check Supplier</a>';
	return fx + "&nbsp;&nbsp;&nbsp;&nbsp;" + jy;
}

/**
 * 全库市场分析报告点击单选按钮让国家被选中
 */
function qkscfxchangeCountryNameAddText(country) {
	var text = "";
	$("input[name='quankulang']:checkbox").each(function() {
		if ($(this).attr("checked")) {
			text += $(this).val() + "、&nbsp;";
		}
	})
	if (text == "") {
		$("#quankuselectcountry").html("");
	} else {
		$("#quankuselectcountry").html(
				"<font color='#0066CC' style='font-family: 微软雅黑,宋体;'>*You have chosen&nbsp;:&nbsp;"
						+ text + "</font>");
	}
	$('#' + country).combo('setText', text.substring(0, text.lastIndexOf(',')))
			.combo('hidePanel');
}

/**
 * 标签竞争采购商操作栏
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @return
 */
function bqaddOpssbuyer(value, rowData, rowIndex) {
	var fx = '<img src="'
			+ getRootPath()
			+ '/static/img/alldb/scfx.png" style="vertical-align:middle;padding-right:5px;"><a onmouseover=\'this.style.textDecoration="underline";\' onMouseOut=\'this.style.textDecoration="none";\' href="#" mce_href="#" style="font-size:12px;color:#656565;" onclick="showCustomerNameForAllDB(\''
			+ rowData.importer + '\')">Add Customer</a>';
	var jy = '<img src="'
			+ getRootPath()
			+ '/static/img/alldb/serach.png" style="vertical-align:middle;padding-right:5px;"><a href="#" mce_href="#" onclick="searchTradeDetail(\''
			+ rowData.id + '\',\'' + rowData.country + '\')">More Details</a>';
	return "&nbsp;&nbsp;" + fx + "&nbsp;&nbsp;";
	//TODO 全库 屏蔽了查看详情 &nbsp;&nbsp;&nbsp;&nbsp;" + jy
}

/**
 * 添加采购商
 * @param importerName : 进口商
 */
function showCustomerNameForAllDB(importerName){
	if (importerName) {						// 进口商不为空
		$('#uCustomerfmForAllDB').form('reset');
		setHtmlParam(null);
		$("input[name='companyName']").val(importerName);	//赋值给公司名称
		openDivArtDialog("Add Customer Information", 'showCustomerDlg', 'showCustomerDlg', 800, 450,true);
		updateJspForCustomer(false);
	} else {
		$.messager.alert('Prompt','Company Name CAN NOT be empty','info');
	}
}

/**
 * 查看采购商详情
 */
function searchTradeDetail(id,country){
	if (id && country) {	//如果不为空
		$.ajax({
			url : getRootPath() + '/rightLibraryData/getRowData',
			cache: false,
			type : 'post',
			data : {'id':id,'countryName':country},
			contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(data){
				data = eval('('+data+')');
				var htmlData = data.htmlData;
			    var hscode = data.hscode;
			    var country = data.country;
			    $("#showArea").html(htmlData);
			    $("#id_hscode").html(hscode);
			    $("#name_null_data").html(country+"Data");
			    openDivArtDialog("More Details", "detailmessageDIV", "1detailmessageDIV",900,600,true);
			}
		});
	}
}

/**
 * 初始化参数
 * @param params : 对象
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

//----------------------------------------市场分析 查询---------------------------------------------------

/**
 * 初始化查询model
 */
var initQueryParams = function(){
	// 查询对象queryParam.marketplace
	queryParam.marketplace.goodsdescription = $("input[name='quankugoodsdesc']").val();		// 产品描述
	queryParam.marketplace.hscode = $("input[name='quankuhscode']").val();					// 海关编码
	queryParam.marketplace.beginDateFlex = $("input[name='quankubegindate']").val();		// 旅行时间(第一段)
	queryParam.marketplace.endDateFlex = $("input[name='quankuenddate']").val();			// 旅行时间(第二段)
	queryParam.marketplace.tradeType = getParseTradeType($('input[name="quankuiexportFlex"]:checked').val());			// 贸易类型
	queryParam.marketplace.countrySelect = getSelectedCountry('quankulang');				// 获取选中的国家
	queryParam.marketplace.oldName = "weight";												// 排序字段
	return queryParam.marketplace;
}
/**
 * 报表查询
 * 
 * @param v
 * @return
 */
function quankusent(v) {
	// qkreportQueryingDisplay();
	alldb_setTab('tab',1,7);
	initQueryParams();
	// 验证国家
	if (!queryParam.marketplace.countrySelect) {
		$.messager.alert("Prompt", "Please Choose a Country！", 'info');
		return;
	}
	// 验证时间段
	var pdate = validateDate(queryParam.marketplace.beginDateFlex, queryParam.marketplace.endDateFlex,
			"", "", "对不起,第二段日期不能小于第一段日期!", queryParam.marketplace.flexType);
	if (!pdate) {
		return;
	}
	$("#right_library_mebubox").show();	//显示报告类型
	$("#con_tab_1").show();   //显示datagrid
	// 生成arr 显示参数{dataGrid}
	var arr = judgeEquirementquanku(queryParam.marketplace.flexType);
	queryParam.marketplace.url = getRootPath() + '/projectAnalyze/queryProjectMarketData';
	$("table[name='marketplaceDataGrid']").datagrid({
		url : getRootPath() + '/projectAnalyze/queryProjectMarketData',
		queryParams : queryParam.marketplace,
		title : getTitle(param.reportType),
		height : 'auto',
		columns : [ arr ],
		pageNumber : 1,
		pagination:true,
		singleSelect:false,
		loadMsg:'Loading...',
		showFooter : true,
		onBeforeLoad : function(param){
			$('div[name="clientProgress"]').show();
			createProgress('clientProgressBar','dbshowLoadStatus', param.countrySelect);
		},
		onLoadError : function(data) {
			// 查询按钮还原样式
			// qkreportQueryStartDisplay();
			$('#clientProgress').hide();
		},
		onLoadSuccess : function(data) {
			// 暂停定时器
			CheckDataOver($('div[name="clientProgress"]'));
			$("#conten").show();				//显示报表和图表
			data = data.rows;
			if (data.length > 0) {
				//起运港 和 抵达港 为饼图	param.reportType 报告类型
				if ('PORTOFSHIPMENT' == param.reportType || 'PORTOFARRIVAL' == param.reportType) {
					var pieData = pieCreateEcharts(data);
					showPieChart('echartsDiv', pieData, getTitle(param.reportType), '',950, 500,'重量');
				} else {
					//生成 Echarts 报表 折线图 
					var echartData = createEcharts(data);
					showMixEchart('echartsDiv',echartData , getTitle(param.reportType), '', 930, 500, '', '');
				}
			} else {
				$.messager.alert("Prompt","No data founded!",'info');
				$("#right_library_mebubox").show();	//显示报告类型
				$("#con_tab_1").hide();   //显示datagrid
				$("#echartsDiv").html('');
			}
		}
	});
}

/**
 * 交易记录 {市场分析里的交易记录按钮 根据条件查询交易记录}
 */
function requestTreadReport(){
	queryParam.marketplace.flexType = "";		//交易记录没有报告类型
	queryParam.marketplace.tradeType = getFlexTypeByValue($('input[name="quankuiexportFlex"]:checked').val());		//获取进出口 {I/E}
	tongHuanBi(true);	//隐藏同环比按钮
	$("[name='marketplaceDataGrid']").datagrid({
		url : getRootPath() + '/projectAnalyze/queryProjectTradeData',
		queryParams : queryParam.marketplace,
		title : getTitle(param.reportType),
		columns : [treadArr],
		rownumbers : true,
		pageNumber : 1,
		pageSize : 10,
		showFooter:true,
		autoRowHeight:false,
		onBeforeLoad : function(param){
			$('div[name="clientProgress"]').show();
			createProgress('clientProgressBar','dbshowLoadStatus', param.countrySelect);
		},
		onLoadSuccess:function(data) {
			CheckDataOver($('div[name="clientProgress"]'));		//结束进度条
			data = data.rows;
			if (data || data.length > 0) {
//				var echartsData = createEcharts(data);	//交易记录柱状图
//				showMixEchart('echartsDiv',echartsData , '全球海关数据', 'ssss', 920, 500, '123', 'xxxdd');
			} else {
				$.messager.alert("Prompt","No data founded!",'info');
			}
			$("#echartsDiv").html('');
		}
	});
}
// **********************************************竞争对手******************************************

/**
 * 加载国家
 */
function callCountryOption(){
	countryArray = reportArray.ALL;
	$("#bqspanel").empty();
	$("#bqspanel").append("<input type=\"checkbox\" onClick=\"bqselAll(this)\" name=\"all\"><span>Select All</span><br/>")
	for(var i=0;i<countryArray.length;i++){
		$("#bqspanel").append("<input type=\"checkbox\" onClick=\"selCountry(this)\" name=\"lang\"  value=\""+countryArray[i]+"\"><span>"+countryArray[i]+"</span><br/>")
	}
	$('#competorCountry').combo({
		editable:false
	});
	$('#bqspanel').appendTo($('#competorCountry').combo('panel'));
	$('#competorCountry').combo('setText', '').combo('hidePanel');
}

/**
 * 点击全选
 * @param all
 */
function bqselAll(all){
	var lang = $("input[name='lang']");
	for (var i=0;i<lang.length;i++) {
		lang[i].checked = all.checked;
	}
	var s = "";
	var text = "";
	var lang = $("input[name='lang']");
	for(var i=0;i<lang.length;i++){
		if(lang[i].checked){
			s = s+lang[i].value+"@";
			text = text+lang[i].value+",";
		}
	}
	$('#competorCountry').combo('setText', text.substring(0,text.lastIndexOf(','))).combo('hidePanel');
}

/**
 * 选择国家查询竞争对手
 * @param country
 * @return
 */
function selCountry(country){
	var countrys="";
	var text = "";
	$("input[name='lang']:checkbox").each(function(){
        if($(this).attr("checked")){
        	countrys += $(this).val()+"@";
        	text += $(this).val()+",";
        }
    })
    $('#competorCountry').combo('setText', text.substring(0,text.lastIndexOf(','))).combo('hidePanel');
}

/**
 * 竞争对手<br>
 * 产品标签
 */
function queryCompany(){
	var text = "";
	$("input[name='lang']:checkbox").each(function(){ 
        if($(this).attr("checked")){
        	text += $(this).val()+","; 
        }
    })
	var country = text.substring(0,text.lastIndexOf(','));
	if (!country) {
		$.messager.alert("Prompt",'Please Choose a Country','info');
		return;
	}
	//获取公司
	var companyname=$('#bqcompanyName').val().trim();
	if(country==""&&companyname==""){
		var item = $('#bqCompetitorsdg').datagrid('getRows'); 
		if (item) { 
			for (var i = item.length - 1; i >= 0; i--) { 
				var index = $('#bqCompetitorsdg').datagrid('getRowIndex', item[i]); 
				$('#bqCompetitorsdg').datagrid('deleteRow', index); 
			} 
		} 
		$.messager.alert("Prompt",'No data founded......','info');
		return;
	}else{
		console.debug($("#competitorHsCode").val().trim());
		console.debug($("#competitorGoodsDesp").val().trim());
		$("#bqCompetitorsdg").datagrid({
			url: getRootPath() + '/projectAnalyze/queryCompetitor',
			queryParams:{
				'companyName':companyname,
				'countrySelect':country,
				'beginDateFlex' : '2014-01-01',
				'endDateFlex' : '2014-12-31',
				'exporter' : companyname,
				'hscode': $("#competitorHsCode").val().trim(),
				'goodsdescription' : $("#competitorGoodsDesp").val().trim(),
				'flexType' : 'EXPORTERSUMMARY'
			},
			pagination:true,
			pageNumber:1,
			onLoadSuccess: function(data){
			    if(data.rows.length == 0){
			    	// 隐藏 当前页面下面的采购商信息
			    	$("#procurementDiv").hide();
			    	$.messager.alert("Prompt","对不起!没有查询到符合您条件的记录数...",'info');
				}
		    } 
		});
	}
}

/**
 * 产品标签里的竞争对手<br>
 * 添加我的竞争对手
 * @param exportName : 出口商
 */
function showCompanyNameForAllDB(exportName){
	if (exportName) {				// 如果出口商不为空
		$('#competitorfmForAllDB').form('reset');
		updateJsp(false);
		setHtmlParam(null);
		$("input[name='companyName']").val(exportName);
//		showAllDbCountrySel("dbAll_CompetitorCountry");
		openDivArtDialog("Add Competitor Information", 'showEcuadorCompetitorDlg', 'closeCompetitorDialogId',800, 350,true);
	} else {
		$.messager.alert('Prompt','Company Name CAN NOT be empty','info');
	}
}

/**
 * 赋值给小窗口
 * @param params : 参数
 */
function setHtmlParam(params){
	if (params){
		var obj = params[0];
		$("#CompanyNamesForAllDBs").val(obj.companyName);
		$("#alternativeName").val(obj.alternativeName);
		$("#contact").val(obj.contact);
		$("#fax").val(obj.fax);
		$("#tel").val(obj.tel);
		$("#mailBox").val(obj.mailBox);
		$("#customerValue").val(obj.customerValue);
		$("#collectionId").val(obj.collectionId);
	} else {
		$("#CompanyNamesForAllDBs").val("");
		$("#alternativeName").val("");
		$("#contact").val("");
		$("#fax").val("");
		$("#tel").val("");
		$("#mailBox").val("");
		$("#customerValue").val("");
	}
}

/**
 * 根据出口商查询进口商
 * @param companyName : 公司名称
 * @param country : 国家
 */
function queryProcurement(companyName,country){
	if(!companyName){
 		$.messager.alert('Prompt','"对不起!公司名称为空无法查询对应的采购商！"','info');
 		return;
 	}
	// 查询采购商
	$("#bquyerCompetitorsdg").datagrid({
		url: getRootPath() + '/projectAnalyze/queryCompetitor',
		queryParams:{
				'companyName' : companyName.trim(),
				'exporter' : companyName.trim(),
				'countrySelect' : country.trim(),
				'beginDateFlex' : '2014-01-01',
				'endDateFlex' : '2014-12-31',
				'hscode': $("#competitorHsCode").val().trim(),
				'goodsdescription' : $("#competitorGoodsDesp").val().trim(),
				'flexType' : 'IMPORTERSUMMARY'
			},
    	pagination:true,
		    pageNumber:1,
		    onLoadSuccess: function(data){
		    	if(data.rows.length == 0){
		    		$("#procurementDiv").css('display','none');
		    		$.messager.alert("Prompt","对不起!没有查询到对应的采购商...",'info');
			    } else {
			    	$("#procurementDiv").css('display','block');
			    }
     	  } 
	});
}

//---------------------------------------------------------买家--------------------------------------

/**
 * 买家条件对象
 */
var buyerParams = {};

/**
 * 获取买家资源库<br>
 * 查询条件
 */
function getQueryParams(hsCode,productName){
	var beginDateFlex = "";		//开始时间
	if (checkIsNotNull($("#buyerBeginDate").val())) {
		beginDateFlex = $("#buyerBeginDate").val();
	}
	var endDateFlex = "";		//结束时间
	if (checkIsNotNull($("#buyerEndDate").val())) {
		endDateFlex = $("#buyerEndDate").val();
	}
	// 请求参数
	buyerParams = {
			hscode : hsCode,
			goodsDescription : productName,
			beginDateFlex : beginDateFlex,
			endDateFlex : endDateFlex,
			countrySelect : ''
		};
	return buyerParams;
}

/**
 * 点击买家按钮,弹出窗口
 */
function buyer(hsCode,productName){
	// 清空dataGrid
	var item = $('#buyerdg').datagrid('getRows');
	if (item) {
		for (var i = item.length - 1; i >= 0; i--) {
			var index = $('#buyerdg').datagrid('getRowIndex', item[i]);
			$('#buyerdg').datagrid('deleteRow', index);
		}
	}
	$("#buyerdg").datagrid({
		url: getRootPath() + '/projectAnalyze/queryBuyerResource',
		queryParams : getQueryParams(hsCode,productName),
		pageNumber:1,
	    pageSize:100,
	    onBeforeLoad : function(param){
//			$('div[name="clientProgress"]').show();
//			createProgress('clientProgressBar','dbshowLoadStatus', param.countrySelect);
		},
		onLoadSuccess: function(data){
//			CheckDataOver($('div[name="clientProgress"]'));
			if(data.rows.length == 0){
				$.messager.alert("Prompt","No data founded......",'info');
			}
		}
	});
	initCountry();		//初始化国家下拉框数据
	openDivArtDialog('Buyer Data', 'mybuyerDlg', 'mybuyer',850, 500, true);  
}

/**
 * 初始化国家下拉框数据
 */
function initCountry(){
	var countryArray = reportArray.BUYER;
	var countryJson="[{\"id\":\"全部\",\"text\":\"Select All\",\"selected\":true},";
	for (var i = 0; i < countryArray.length; i++) {
		countryJson += "{";
		countryJson +='"id":"'+ countryArray[i]+'",';
		countryJson +='"text":"'+ countryArray[i]+'"';
		countryJson += "}";
		if (i != countryArray.length -1) {
			countryJson += ",";
		}
	}
	countryJson+="]";
	var data = eval('(' + countryJson + ')');
    $("#Selcountry").combobox({editable:false});
	$("#Selcountry").combobox("loadData", data);
	//设置easyui combox 下拉框高度
	$("#Selcountry").combobox({panelHeight:200});
}


var buyerArr = [
	                {field:'country',title:'国家',width:150,align:'center'},
	                {field:'companyname',title:'国家名称',width:150,align:'center'},
	                {field:'hscode',title:'海关编码',width:150,align:'center'},
	                {field:'goodsdescription',title:'产品描述',width:150,align:'center'},
	                {field:'opt',title:'功能大全',width:180,align:'center'}
                ];

/**
 * 买家资源库<br>
 * 立即查询 {按钮}
 */
function queryBuyer(obj){
	//存放用户选择的国家
	var country = $('#Selcountry').combobox('getValue').trim();
	buyerParams.countrySelect = country;
	// 得到实践
	buyerParams.beginDateFlex =  $("#buyerBeginDate").val();
	buyerParams.endDateFlex =  $("#buyerEndDate").val();
	$("#buyerdg").datagrid({
		url: getRootPath() + '/projectAnalyze/queryBuyerResource',
		queryParams : buyerParams,
		pageNumber:1,
	    pageSize:100,
	    onBeforeLoad : function(param){
//			$('div[name="clientProgress"]').show();
//			createProgress('clientProgressBar','dbshowLoadStatus', param.countrySelect);
		},
		onLoadSuccess: function(data){
//			CheckDataOver($('div[name="clientProgress"]'));
			if(data.rows.length == 0){
				$("#buyerdg").hide();
				$.messager.alert('Prompt',"对不起，你输入的查询条件未检索到结果，请尝试更换条件值!",'info');
			}
		}
	});
}

/**
 * 验证国家为全部,则返回全部国家<br>
 * 买家使用
 * @param country
 * @returns
 */
function checkCountryStr(country){
	if ('全部' == country) {
		return reportArray.ALL;
	}
	return country.join();
}

/**
 * 添加客户信息
 * @param importer 进口商
 */
function showCustomerName(importer) {
	$('#argentinaCustomerfm').form('reset');
	$("input[name='companyName']").val(importer);
	//打开弹出框
	openDivArtDialog('Add Customer Information', 'showCustomerDlg', 'showCustomerDlg',810, 500,true);
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
	openDivArtDialog('Add Competitor Information', 'showEcuadorCompetitorDlg', 'closeCompetitorDialogId',810, 500,true);
}