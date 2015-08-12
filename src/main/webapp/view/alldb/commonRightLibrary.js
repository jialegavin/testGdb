/**
 * -------------------------------------------权库共用JS------------------------------------------------
 */

/**
 * 查询对象 {权库}
 */
var queryParam = {};

var reportHtml = {};

var userModel = {};

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

//-------------------------------------------权限------------------------------------------------------------------------------------------------------------------------------------------------------------



/**
 * 判断用户的权限
 * @return  true : 正式用户  false : 超级用户,试用用户 
 */
var checkUserRightDesc = function(){
	var result = false;
	var userRight = userRightCfg.user.userDesc;
	if (userRight == '正式用户') {
		result = true;
	}
	return result;
}

/**
 * 初始化用户国家和时间<br>
 * 赠送时间根据用户有效时间得出<br>
 * 用户所购买的国家如果为中国,2种情况: 1. 开放了历史数据 2. 没有开放历史数据<br>
 * 并且赠送3个月国外数据.为全部国家,3个月过期之后,不再赠送国外数据<br>
 * 中国 : 数据检索时间 , 国外 : 账户时间
 * @param userRightModel : 用户权限model
 * @param queryBeginTime : 查询开始时间
 * @param queryEndTime : 查询结束时间
 * @returns userRightModel : 处理过的用户权限Model
 */
var initUserRightMethod = function(userRightModel,queryBeginTime,queryEndTime) {
	try {
		if (userRightModel) {
			var startTime = userRightCfg.user.beginTime;		//用户有效开始时间
			var endTime = userRightCfg.user.endTime;			//用户结束时间
			var temp_countryArray = [];
			var serviceTime =userRightCfg.nowDate;				//服务器当前时间截
			var isDispark = getChinaMonth(serviceTime,startTime);		// 赠送时间
			for (x in userRightModel) {
	//			var startTime = userRightModel[x].startTime;	// 开始时间
				var endTime = userRightModel[x].endTime;		// 结束时间
				// 是否含有中国
				if (userRightModel[x].byCountry == "中国") {
					// 是否开放了历史数据
					if (userRightModel[x].openHistoryData) {
						// 开放历史数据
						if (isDispark) {		// 在赠送时间范围内
							// 设置为全部国家
							userRightCfg.countryArray = reportArray.ALL;
							// 对输入的时间进行国家筛选
							userRightCfg.countryArray = checkChinaBuyCountry(queryBeginTime,queryEndTime);
						} else {										// 不再赠送时间范围内
							// 获取当前用户所购买的国家数组
							userRightCfg.countryArray = queryCountryArray(queryBeginTime,queryEndTime);
						}
					} else {
						// 未开放历史数据
						// 判断在赠送时间内
						if (isDispark) {		// 在赠送时间范围内
							// 设置为全部国家
							userRightCfg.countryArray = reportArray.ALL;
							// 对输入的时间进行国家筛选
							checkChinaBuyCountry(queryBeginTime,queryEndTime);
						} else {
							// 未在赠送时间内
							// 获取当前用户所购买的国家数组
							userRightCfg.countryArray = queryCountryArray(queryBeginTime,queryEndTime);
						}
					}
				} else {
					// 获取当前用户所购买的国家数组
					userRightCfg.countryArray = queryCountryArray(queryBeginTime,queryEndTime);
				}
			}
		}
	} catch (e) {
		console.debug("commonRightLibrary.js 出现异常 : " + e);
	}
}

/**
 * 是否是试用用户<br>
 * 当前行对象是否是不可操作的数据<br>
 * 模块 : 我的客户,我的对手,产品标签
 * @param rowObj : 当前行的数据
 * @return true , false 
 */
var isOperate = function(rowObj){
	var result = false;
	if (userRightCfg.user.userDesc == '试用用户') {
		if (rowObj) {
			if (1 == rowObj.isOperate) {
				result = true;
			}
		}
	}
	return result;
}

/**
 * 根据输入的国家返回当前的国家元素索引
 */
var getCountryIndex = function(countryArray,countryName){
	for (x in countryArray) {
		if (countryArray[x] == countryName) {
			return x;
		}
	}
	return "";
}

/**
 * 
 */
var checkChinaBuyCountry = function(queryBeginTime,queryEndTime){
	var countryArray = [];
	var rightModel = userRightCfg.key;
	var temp_queryBeginTime = formatDate(queryBeginTime,"");			// 查询开始时间
	var temp_queryEndTime = formatDate(queryEndTime,"");				// 查询结束时间
	var temp_userEndTime = formatDate(userRightCfg.user.endTime);		// 用户结束时间 
	for (var x = 0; x < rightModel.length ; x++) {
		var startTime = formatDate(rightModel[x].startTime);	// 开始时间
		var endTime = formatDate(rightModel[x].endTime);		// 结束时间
		countryArray = removal_Country(countryArray);			// 去重复
		// 如果为中国,按照数据检索时间
		if (rightModel[x].byCountry == '中国') {
			if ((temp_queryBeginTime >= startTime && startTime <= temp_queryEndTime)
					|| (temp_queryBeginTime <= endTime && endTime <= temp_queryEndTime)) {
				// 设置为全部国家
				countryArray = reportArray.ALL;
				countryArray.unshift('中国');
				countryArray = removal_Country(countryArray);
			} else {
				countryArray = reportArray.ALL;
				var temp_index = getCountryIndex(countryArray,'中国');
				if (temp_index) {
					countryArray.splice(temp_index,1);
				}
				countryArray = removal_Country(countryArray);
			}
		}
	}
	return removal_Country(countryArray);
}

/**
 * 获取当前用户所购买的国家数组
 * @param queryBeginTime : 查询开始时间	
 * @param queryEndTime : 查询结束时间
 * @param countryArray : 经过查询时间处理过的国家数组
 */
var queryCountryArray = function(queryBeginTime,queryEndTime){
	var countryArray = [];
	var rightModel = userRightCfg.key;
	var temp_queryBeginTime = formatDate(queryBeginTime,"");			// 查询开始时间
	var temp_queryEndTime = formatDate(queryEndTime,"");				// 查询结束时间
	var temp_userEndTime = formatDate(userRightCfg.user.endTime);		// 用户结束时间 
	for (x in rightModel) {
		var startTime = formatDate(rightModel[x].startTime);	// 开始时间
		var endTime = formatDate(rightModel[x].endTime);		// 结束时间
		// 如果为中国,按照数据检索时间
		if (rightModel[x].byCountry == '中国') {
			if ((temp_queryBeginTime >= startTime && startTime <= temp_queryEndTime)
					|| (temp_queryBeginTime <= endTime && endTime <= temp_queryEndTime)) {
				countryArray[x] = rightModel[x].byCountry;
			}
		} else {
			// 如果国外,按照账户有效时间
			// 只计算 购买数据结束时间 于 账户结束时间
			if (temp_userEndTime >= endTime) {
				countryArray[x] = rightModel[x].byCountry;
			}
		}
	}
	return removal_Country(countryArray);
}

/**
 * 去重复数组
 */
var removal_Country = function(countryArray){
	var temp_CountryArray = [],temp_hash = [];
	for (x in countryArray) {
		if (!temp_hash[countryArray[x]]) {
			temp_hash[countryArray[x]] = true;
			temp_CountryArray.push(countryArray[x]);
		}
	}
	return temp_CountryArray;
}

/**
 * 是否在中国赠送时间范围内
 * @param ServiceTime : 服务器当前时间
 * @param startTime : 用户有效开始时间
 * @returns false : 失效  true : 生效
 */
var getChinaMonth = function(ServiceTime,startTime) {
	var result = true;
	var serviceTime = formatDate(ServiceTime,"");
	var buyStartTime = formatDate(startTime,3);	//中国数据开始时间+3个月
	if (serviceTime > buyStartTime) {			//服务器时间大于用户购买时间
		result = false;
	}
	return result;
}

/**
 * 验证当前用户所购买的权限
 * @param input_hsCode : 用户输入的hsCode
 * @param input_hsCode : 用户输入的产品描述
 * @returns  1 : hsCode 不存在 2 : goodsDesc 不存在
 */
var checkUserBuyParam = function(input_hsCode,input_goodsDesc) {
	result = 0;
	userList = userRightCfg.key;
	for (x in userList) {
		if (userList[x].byHsCode != input_hsCode) {
			result = 0;
		}
		if (userList[x].byProductDesc != input_goodsDesc) {
			result = 1;
		}
	}
	return result;
}

/**
 * 是否有中国这个国家并且没有开放了历史数据
 * @return true : 开放  false : 未开放
 */
var getChinaAndHistry = function() {
	var result = false;
	// 是否含有中国
	if (userRightModel[x].byCountry == "中国"){
		// 是否开放了历史数据
		if (userRightModel[x].openHistoryData) {
			result = true;
		}
	}
	return result;
}

/**
 * 是否购买了中国
 * @return true : 开放  false : 未开放
 */
var getChinaAndHistry = function() {
	var rightModel = userRightCfg.key;
	for (x in rightModel) {
		if (rightModel[x].byCountry == "中国") {
			return true;
		}
	}
	return false;
}

/**
 * 获取对应的饼图的显示中文值
 * @param param : 对象的字段
 * @return result : 返回对应的中文名称
 */
var getPieChatsValue = function(param){
	var result = "";
	if (param) {
		if (param == 'tradeWeight') {
			result = "重量";
		} else if (param == 'tradeMoney') {
			result = "金额";
		} else if (param == 'tradeQuantity') {
			result = "数量";
		} else if (param == 'tradeCount') {
			result = "次数";
		} 
	} else {
		// 如果为空,默认为 重量
		result = "重量";
	}
	return result;
}

/**
 * 对中国的时间进行转换<br>
 * 获取时间截
 * @param date : 日期
 * @param afterTime : 追加时间 可以设置为""
 * @return 时间截
 */
var formatDate = function(date,afterTime) {
	if (date) {
		if (date.length > 10) {
			date = date.substring(0,date.indexOf(" "));
		}
		var dateArray = date.split("-");
		var temp_date = new Date(dateArray[0],dateArray[1],dateArray[2]);
		if (afterTime) {
			temp_date.setMonth(temp_date.getMonth() +  parseInt(afterTime - 1));
			return temp_date.getTime();	// 加上3个月
		}
		temp_date.setMonth(temp_date.getMonth() -1);
		return temp_date.getTime();
	}
}

/**
 * 获得当前用户所购买海关编码的总数量
 * @param userRightModel : 用户权限集合
 * @return hsCodeArray : 海关编码数量数组
 */
var gethsCodeCount = function(userRightModel){
	var hsCodeArray = [];
	var rightList = userRightModel.key;
	for (x in rightList) {
		if (rightList[x].byHsCode) {
			hsCodeArray.push(rightList[x].byHsCode);
		}
	}
	return hsCodeArray;
}

/**
 * 试用用户 查询时间效验
 * @param queryBeginDate : 查询开始时间
 * @param queryEndDate : 查询结束时间
 * @return true : 有效  false : 无效
 */
var probationUser = function(queryBeginDate,queryEndDate) {
	var result = false;
	// 试用用户限制时间
	var beginDate = formatDate("2012-01-01", "");
	var endDate = formatDate("2013-12-31", "");
	var temp_queryBeginDate = formatDate(queryBeginDate, "");
	var temp_queryEndDate = formatDate(queryEndDate, "");
	if (temp_queryBeginDate > beginDate && temp_queryEndDate > endDate
			|| temp_queryBeginDate < beginDate && temp_queryEndDate < endDate) {
		result = true;
	}
	return result;
}

/**
 * 是否购买过hscode
 * @return true : 购买过  false : 未购买
 */
var checkHsCodeOfBuy = function(inputHscode){
	var result = false;
	var userRight = userRightCfg.key;
	if (userRight) {
		for (x in userRight) {
			var temp_inputHscode = inputHscode;
			if (userRight[x].byHsCode) {
				// 以用户购买hscode的长度为准
				var temp_userBuyHscode = userRight[x].byHsCode;
				var buy_HscodeLength = temp_userBuyHscode.length;
				var temp_inputHscode = temp_inputHscode.substring(0,buy_HscodeLength);
				if (temp_inputHscode.trim() == temp_userBuyHscode.trim()) {
					result = true;
				}
			}
		}
	} else {
		result = true;
	}
	return result;
}

/**
 * 获取当前用户所购买的所有国家列表
 */
var getUserCountry = function(userCountrys) {
	var userCountry = [];
	if (getUserBuyChina(userRightCfg.key) == 1 || getUserBuyChina(userRightCfg.key) == 3) {
		console.debug("赠送");
		userCountry = reportArray.ALL;
	} else if (getUserBuyChina(userRightCfg.key) == 2) {
		console.debug("不赠送");
		for (x in userCountrys) {
			userCountry[x] = userCountrys[x].bycountry;
		}
	}
	return userCountry;
}

/**
 * 获取用户购买的国家集合
 */
var getUserBuyCountry = function(userCountrys){
	var userCountry = [];
	for (x in userCountrys) {
		userCountry[x] = userCountrys[x].byCountry;
	}
	return userCountry;
}

/**
 * 循环对象,是否含有中国的国家,是否开放了历史数据
 * @returns 0 : 没有中国  1 : 开了历史数据,还在赠送时间内 2 : 未开历史数据,不再赠送时间 3 : 未开中国历史数据,还在赠送时间内
 */
var getUserBuyChina = function(userRightList){
	var result = 0;
	for (x in userRightList) {
		// 购买的国家 是 中国
		if (userRightList[x].bycountry == '中国') {
			// 中国数据是否开放历史数据
			if (userRightList[x].openHistoryData == 0) {		// 未开中国历史数据,还在赠送时间内
				if (proBationUser(userRightList[x].starttime)) {
					result = 3;
				}
			} else if (userRightList[x].openHistoryData == 1){	// 开了历史数据
				if (proBationUser(userRightList[x].starttime)) {
					result = 1;
				} else {
					result = 2;
				}
			}
		}
	}
	return result;
}

/**
 * 对于中国,判断赠送国家的日期之内
 */
var proBationUser = function(beginDate) {
	var result = false;
	var nowDate = userRightCfg.nowDate;			// 服务器时间
	if (checkUserDate(nowDate,beginDate)) {
		result = true;
	}
	return result;
}

/**
 * 数据格式统一
 */
var checkDate = function(date){
	if (date && date != 'undefined') {
		var dateArray = date.split("-");
		if (dateArray.length == 2) {
			date += "-01";
		}
	}
	return date;
}

/**
 * 用户权限验证方法
 * @returns true : SUCCESS false : ERROR
 */
var checkUserRole = function(){
	var result = false;
	var userModel = userRightCfg.user;
	if (userModel.userDesc == '正式用户') {
		result = true;
	} else if (userModel.userDesc == '超级用户') {
		result = true;
	} else if (userModel.userDesc == '试用用户') {
		probationUser();
		result = true;
	} else if (userModel.userDesc == '按次用户') {
		alertRightMessage();
	}
	return result;
}

/**
 * 判断用户有效日期是否超过3个月<br>
 * 对于中国来说<br>
 * 如果超过3个月,false  否则 true
 * @param serviceNowDate : 服务开始时间
 * @param userBuyBeginDate : 购买时间
 */
var checkUserDate = function(serviceNowDate,userBuyBeginDate){
	var result = true;
	var temp_serviceNowDate = serviceNowDate.split("-");
	var temp_nowDate = new Date(temp_serviceNowDate[0],temp_serviceNowDate[1],temp_serviceNowDate[2]);
	var temp_serviceDate = temp_nowDate.getTime();
	userBuyBeginDate = userBuyBeginDate.split(" ");
	var temp_beginDate = userBuyBeginDate[0].split("-");
	var temp_begin = new Date(temp_beginDate[0],Number(temp_beginDate[1]) + 2,temp_beginDate[2]);
	var temp_buyDate = temp_begin.getTime();
	if(temp_serviceDate > temp_buyDate) {
		result = false;
	}
	return result;
}

/**
 * 判断用户有效日期是否超过3个月<br>
 * 对于中国来说<br>
 * @param serviceDate : 服务开始时间
 * @param buyBeginDate : 购买时间
 * @return true : 有效   false : 无效
 */
var checkServiceOrBuyTime = function(serviceDate,buyBeginDate) {
	var result = false;
	if (serviceDate > buyBeginDate) {
		result = true;
	}
	return result;
}

/**
 * 点击国家下拉按钮时触发<br>
 * 开始时间和结束时间 和 用户所购买的国家所对应
 */
var checkBeginAndEndDate = function(beginDate,endDate){
	var temp_countryArray = [];
	var temp_all_country = reportArray.ALL;		// 存放要过滤的国家
	beginDate = getDateSub(beginDate);			// 查询开始时间
	endDate = getDateSub(endDate);				// 查询结束时间
	if (beginDate && endDate) {
		var userRightData = userRightCfg.key;
		for (x in userRightData) {
			var buyBeginDate = getDateSub(userRightData[x].starttime);		// 购买开始时间
			var buyEndDate = getDateSub(userRightData[x].endtime);			// 购买结束时间
			if (userRightData[x].bycountry = '中国') {
				// 是否开放了历史数据
				if (checkUserRightHisty(userRightData[x])) {
					// 在赠送时间 生效
					if (checkServiceOrBuyTime(getDateSub(userRightCfg.nowDate),buyBeginDate)) {
						// 判断时间
						if (beginDate > buyBeginDate || buyBeginDate < endDate &&
								endDate > buyBeginDate || buyEndDate < endDate) {
							temp_countryArray = reportArray.ALL;
						} else {
							// 不再查询之内
							var index = getArrayIndex(userRightData[x].bycountry,temp_all_country);
							temp_all_country.splice(index,1);
							temp_countryArray = temp_all_country;
						}
					} else {
						// 不再赠送时间之内的
						// 判断购买时间是否在查询时间之内
						if (beginDate > buyBeginDate || buyBeginDate < endDate &&
								endDate > buyBeginDate || buyEndDate < endDate) {
							temp_countryArray[x] = userRightData[x].bycountry;
						}
					}
				} else {
					// 不开放历史数据
					// 在赠送时间 生效
					if (checkServiceOrBuyTime(getDateSub(userRightCfg.nowDate),buyBeginDate)) {
						// 查询时间和购买时间对比
						if (beginDate > buyBeginDate || buyBeginDate < endDate &&
								endDate > buyBeginDate || buyEndDate < endDate) {
							temp_countryArray = reportArray.ALL;
						} else {
							// 不再查询之内
							var index = getArrayIndex(userRightData[x].bycountry,temp_all_country);
							temp_all_country.splice(index,1);
							temp_countryArray = temp_all_country;
						}
					} else {
						// 不再赠送时间之内的
						// 判断购买时间是否在查询时间之内
						if (beginDate > buyBeginDate || buyBeginDate < endDate &&
								endDate > buyBeginDate || buyEndDate < endDate) {
							temp_countryArray[x] = userRightData[x].bycountry;
						}
					}
				}
			}
		}
	}
	return temp_countryArray;
}


var getArrayIndex = function (value,array) {
	var index = -1;
	for (var i = 0; i < array.length; i++) {
		if (array[i] == value) {
			index = i;
			break;
		}
	}
	return index;
}

/**
 * 判断当前国家是否开放了历史数据<br>
 * 对于中国来说
 */
var checkUserRightHisty = function(RightModel){
	var result = false;
	if (RightModel.openHistoryData == 1) {
		result = true;
	}
	return result;
}

/**
 * 获取时间的时间截
 */
var getDateSub = function(date) {
	date = checkDate(date);
	var temp_date = date.split("-");
	var temp_nowDate = new Date(temp_date[0],temp_date[1],temp_date[2]);
	var temp_serviceDate = temp_nowDate.getTime();
	return temp_serviceDate;
}

// -------------------------------------权限END---------------------------------------------------------------
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
	var countryArray = "";
	for (x in lang) {
		lang[x].checked = all.checked;
		if (lang[x].value) {
			countryArray = countryArray + lang[x].value + "、&nbsp;";
		}
	}
	if (all.checked) {
		$('#' + displayCountryDiv).html(
				"<font color='#0066CC' style='font-family: 微软雅黑,宋体;'>* 当前已选 &nbsp;:&nbsp;"
						+ countryArray + "</font>");
	} else {
		$('#' + displayCountryDiv).html("");
	}
	$('#' + countryId).combo('setText', countryArray.substring(0, countryArray.lastIndexOf(',')))
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
			queryParam.marketplace.sort = param.sort;		// 排序字段
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
					if (checkObjectNull(pieData)) {
						$("#echartsDiv").show();
						showPieChart('echartsDiv', pieData, getTitle(param.reportType), '',950, 500,getPieChatsValue(queryParam.marketplace.sort));
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
 * 判断是否为空<br>
 * 针对饼状数据
 * @param obj : 数据
 * @return result : true | false
 */
var checkObjectNull = function(obj){
	var result = true;
	var count = obj.length;
	var num = 0;
	for (x in obj) {
		if (obj[x].value == '') {
			num++;
		}
	}
	if (count == num) {
		result = false;
	}
	return result;
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
		$.messager.alert("提示", "开始日期不能为空!", "info");
		return false;
	}
	if (firstEnd == null || firstEnd == '') {
		$.messager.alert("提示", "结束日期不能为空!", "info");
		return false;
	}
	var fsDate = new Date(firstStart);
	var feDate = new Date(firstEnd);
	var btime = fsDate.getTime(firstStart);
	var etime = feDate.getTime(firstEnd);
	var time = parseInt(etime) - parseInt(btime);
	if (fsDate > feDate) {
		$.messager.alert("提示", message, "info");
		return false;
	}
	// 验证时间段不超过一年
	if (time > 31536000000) {
		$.messager.alert("提示", "查询最大的时间差不能超过一年!", "info");
		return false;
	}
	// 判断当报告类型为新增，流失进口商，新增流失出口商时校验第二段日期
	if (reporttype == 'ADDIMPORTER' || reporttype == 'REDURCEIMPORTER'
			|| reporttype == 'ADDEXPORTER' || reporttype == 'REDURCEEXPORTER') {
		// 验证第二段日期
		if (secondStart == null || secondStart == '') {
			$.messager.alert("提示", "新增时间段开始日期不能为空!", "info");
			return false;
		}
		if (secondEnd == null || secondEnd == '') {
			$.messager.alert("提示", "新增时间段结束日期不能为空!", "info");
			return false;
		}
		var ssDate = new Date(secondStart);
		var seDate = new Date(secondEnd);
		var sbtime = ssDate.getTime(secondStart);
		var setime = seDate.getTime(secondEnd);
		var stime = parseInt(setime) - parseInt(sbtime);
		if (ssDate > seDate) {
			$.messager.alert("提示", message, "info");
			return false;
		}
		// 验证新增时间不超过一年
		if (stime > 31536000000) {
			$.messager.alert("提示", "查询最大的时间差不能超过一年!", "info");
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
		if (checkUserRightDesc()) {
			array = unique1(userRightCfg.countryArray);
		} else {
			array = reportArray.ALL;
		}
	}
	if (inputType && countryArray) { // 不为空
 		for (x in countryArray) {
			// 如果字符中存在 inputType {进口,出口}
			if (countryArray[x].indexOf(inputType) != -1) {
				array.push(countryArray[x]);
			}
		}
 		if (userRightCfg.user.userDesc != '正式用户') {
			for (x in noFilterArray) {
				// 如果不存在不用过滤的国家,则加进数组中
//				if (countryArray.indexOf(noFilterArray[x]) == -1) {
					array.unshift(noFilterArray[x]);
//				}
			}
		} else {
			for (x in noFilterArray) {
				if (getChinaMonth(userRightCfg.nowDate,userRightCfg.user.beginTime)) {
					array.unshift(noFilterArray[x]);
				}
			}
		}
	}
	return unique1(array);
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
				// 权限国家在当前报告类型中存在的国家
				if (userRightCfg.user.userDesc == "正式用户") {
					param.countryArray = getCountryArrayOfTwoArray(param.countryArray,userRightCfg.countryArray);
				}
				getAppendCountryByParam();
				$("#quankuselectcountry").html("");
			}
		}
	}
}

/**
 * 实现 countryArray 的元素是否存在于 typeCountryArray,如果存在,则添加到临时数组中
 * @param typeCountryArray : 报告类型所需要的国家
 * @param countryArray : 用户权限所购买的国家
 * @return temp_array : 临时数组
 */
var getCountryArrayOfTwoArray = function(typeCountryArray,countryArray) {
	var temp_array = [];
	for (i in typeCountryArray) {
		for (y in countryArray) {
			if (typeCountryArray[i] == countryArray[y]) {
				temp_array[i] = typeCountryArray[i];
			}
		}
	}
	return temp_array;
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
 * 根据行号删除表格中的行数据
 */
var deleteDataGridByRow = function(row) {
	$('table[name="marketplaceDataGrid"]').datagrid('deleteRow', row);
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
					deleteDataGridByRow(row);
//					value = "N/A";
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
				deleteDataGridByRow(row);
//				value = "N/A";
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
//				value = "N/A";
				deleteDataGridByRow(row);
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
				deleteDataGridByRow(row);
//				value = "N/A";
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
		ass["title"] = getFormatStr('原产国');
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
	var result = 0;
	if (s) {
		n = n > 0 && n <= 20 ? n : 2;
		s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
		var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
		t = "";
		for (i = 0; i < l.length; i++) {
			t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
		}
		result = t.split("").reverse().join("") + "." + r;
	}
	return result;
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
		var resultValue = "";
		if (index) {
			resultValue = newData[echartsData[index].field];
		}
		totalWeight += resultValue;
		pieDatas.value = cutOutNum(resultValue);		// 格式化  2位数
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
		if (queryParam.marketplace.sort) {
			if (echartsData[i].field == queryParam.marketplace.sort) {
				return i;
			}
		} else {
			// 如果排序字段为空,默认为重量
			if (echartsData[i].field == 'tradeWeight') {
				return i;
			}
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
	if (numOrStr == 0) {
		return numOrStr;
	} else {
		var radixBefore = numOrStr.substring(0,numOrStr.lastIndexOf('.'));
		var radlxLater = numOrStr.substring(numOrStr.lastIndexOf('.'),numOrStr.lang);
		var result = replaceComma(radixBefore,",", /,/g, '');
	}
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