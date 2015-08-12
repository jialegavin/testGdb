/**
 * -------------------------------------------------动态日期控件接口----------------------------------------------------------
 */

// 日期对象
var dateObj = {};

/**
 * 根据用户权限日期控件
 */
var userTypeFactory = function(){
	if (userRightCfg.user.userDesc == '试用用户') {
		// 试用用户 时间设定
		changeDatetrial();
	} else {
		// 其他用户权限时间设定
		changeDateOfficial();
	}
}

/**
 * 根据用户权限,分配不同的日期<br>
 * 全局方法.适用 : 客户,对手,产品标签
 */
var changeDateValue = function(){
	if (userRightCfg.user.userDesc == '试用用户') {
		// 试用用户 时间设定
		$("input[name='c_startdate']").val('2012-01-01');
		$("input[name='c_enddate']").val('2013-12-31');
	} else {
		// 其他用户权限时间设定
		$("input[name='c_startdate']").val('2014-01-01');
		$("input[name='c_enddate']").val('2015-12-31');
	}
}

/**
 * 试用用户时间设定<br>
 * 2013-01-01    2014-12-31
 */
var changeDatetrial = function() {
	dateObj.minDate = '2012-01-01';
	dateObj.maxDate = '2013-12-31';
}

/**
 * 正式用户时间设定<br>
 * 数据库中的时间  maxDate : beignDate, minDate : minDate
 */
var changeDateOfficial = function() {
	var resultObj = getObjByKey();			// 当前对应国家的对象 (一个)
	if (resultObj) {
		dateObj.minDate = resultObj.startTime;
		dateObj.maxDate = resultObj.endTime;
	} else {
		dateObj.minDate = '2012-01-01';
		dateObj.maxDate = '2013-12-31';
	}
}

/**
 * 日期控件控制器入口
 * @param countryName : 国家名称
 */
var createDate = function(countryName){
	initDateObj();								// 初始化日期对象
	try {
		if (countryName) {
			dateObj.countryName = countryName;
			// 动态生成日期,日期格式根据国家不同日期控件YYYY-MM-DD || YYYY-MM 不同
			userTypeFactory();						// 调用用户权限工厂,根据数据生成时间
			dateObj.dateFmt = dateFmt();			// 日期格式  yyyy-MM-dd  ||  yyyy-MM
			deleteFun();							// 移除2个input所有事件
			initNameDate();							// 创建日期控件并且绑定事件
		} else {
			console.debug('dateutil.js createDate(countryName) 传入的参数为: 违法变量,请检查您的代码.....');
		}
	} catch (e) {
		console.debug("dateutil.js 出现异常 " + e);
	}
}

/**
 * 移除所有事件
 */
var deleteFun = function() {
	$(dateObj.startDate).off('focus');
	$(dateObj.endDate).off('focus');
}

/**
 * 初始化日期对象
 */
var initDateObj = function() {
//	$("input[name='c_startdate']").removeAttr('readonly');
	// two date names
	dateObj.startDate = $("input[name='c_startdate']");
	dateObj.endDate = $("input[name='c_enddate']");
	dateObj.lang = 'zh-cn';
}

/**
 * 返回日期格式
 */
var dateFmt = function(){
	if (dateObj.countryName && reportArray.YEARMONTHCOUNTRY) {
		countryName = dateObj.countryName;
		var countryArray = reportArray.YEARMONTHCOUNTRY;
		if (countryName.indexOf(',') > -1) {		// 全库
			var index = 0;
			countryName = removerNull(countryName.split(','));
			for (x in countryName) {
				if (countryArray.indexOf(countryName[x]) > -1) {
					index++;
				}
			}
			if (countryName.length == index) {		// 如果全库选择的国家和日期格式
				return "yyyy-MM";
			}
		} else {									// 贸易情报,对比分析
			for (x in countryArray) {
				if (countryArray[x] == countryName) {
					return "yyyy-MM";
				}
			}
		}
	} else {
		console.debug("dateFmt方法变量异常!,请检查和 dateUtil.js!");
	}
	return "yyyy-MM-dd";
}

/**
 * 数组去空
 */
var removerNull = function(array) {
	var temp_array = [];
	if (array) {
		for (var i =0,temp = array.length; i < temp; i++) {
			if (array[i]) {
				temp_array[i] = array[i];
			}
		}
	}
	return temp_array;
}

/**
 * 当前字符串的所含有特殊符号,返回长度
 * @param symbol : 特殊符号
 * @param str : 字符串
 * @return result
 */
var getSymbolByStr = function(symbol,str){
	var result = "";
	if (symbol && str) {
		result = str.split(symbol);
	}
	return result;
}

/**
 * 生成日期控件,并且绑定这个事件
 */
var initNameDate = function() {
	$(dateObj.startDate).on('focus',dateBeginFocusFun);
	$(dateObj.endDate).on('focus',dateEndFocusFun);
}

/**
 * focus fun <br>
 * 开始时间
 */
var dateBeginFocusFun = function(){
	console.info("开始时间:" + dateObj.dateFmt + " " + dateObj.minDate +  "  "  + dateObj.maxDate + '  ' + createDateContrl(dateObj));
	createDateContrl(dateObj);
//	$(this).val(dateObj.minDate);
}

/**
 * focus fun <br>
 * 结束时间
 */
var dateEndFocusFun = function(){
	console.info("结束时间:" + dateObj.dateFmt + " " + dateObj.minDate +  "  "  + dateObj.maxDate + '  ' + createDateContrl(dateObj));
	createDateContrl(dateObj);
//	$(this).val(dateObj.maxDate);
}

/**
 * 日期控件
 */
var createDateContrl = function(dateObj) {
	var wdatePickers = WdatePicker(
		{
			lang : dateObj.lang,
			dateFmt : dateObj.dateFmt,
			minDate : dateObj.minDate,
			maxDate : dateObj.maxDate,
			readOnly:true
		}
	);
	return wdatePickers;
}

/**
 * 根据key获取相应的对象
 */
var getObjByKey = function(){
	var countryName;
	var resultObj = null;
	if (dateObj.countryName.indexOf(",") > -1) {		// 针对全库模块  客户,对手,下载,产品
		countryName = removerNull(dateObj.countryName.split(','));
	} else {											// 只有一个国家 贸易情报,对比分析
		countryName = dateObj.countryName;
		for (x in allCountryObj) {
			if (x == countryName) {
				resultObj = allCountryObj[x];
			}
		}
	}
	return resultObj;
}

/**
 * 对于日期格式不同,转换不同的结果
 */
var toDate = function(flag,value){
	try {
		var subDate = "";
		var resultStr = "";
		if (flag == 1) {
			subDate = "01";
		} else if (flag == 2) {
			subDate = "31";
		} else {
			// TODO 还有其他情况
		}
		if (value) {
			var lenArray = value.split('-');
			if (lenArray.length == 2) {
				resultStr += value + "-" + subDate;
			} else {
				resultStr = value;
			}
		} else {
			console.debug('此字符串为空!');
		}
	} catch (e) {
		console.debug('dateutil.js toDate() 传入的参数为空!');
	}
	return resultStr;
}

/**
 * 日期控件初始化<br>
 * 除了正式用户  离开时间
 */
var initDateFormat = function() {
	if (userRightCfg.user.userDesc != '试用用户') {
		return WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',minDate:'2011-01-01',maxDate:'2015-12-31',readOnly:true});
	} else {
		return WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',minDate:'2012-01-01',maxDate:'2013-12-31',readOnly:true});
	}
}