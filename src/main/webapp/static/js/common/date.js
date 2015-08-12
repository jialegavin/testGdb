/**
 * 获取当前时间
 * @returns {String}
 */
function getCurrentTime()
{
	var  date=new Date();
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    //起始时间为当前时间
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);	
}

/**
 * 返回格式日期
 * @param date
 * @returns {String}
 */
function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'/'+(m<10?('0'+m):m)+'/'+(d<10?('0'+d):d);
}
/**
 * 获取上一个月
 *
 * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
 */
function getPreMonth(date) {
    var arr = date.split('/');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    var day = arr[2]; //获取当前日期的日
    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中月的天数
    var year2 = year;
    var month2 = parseInt(month) - 1;
    if (month2 == 0) {
        year2 = parseInt(year2) - 1;
        month2 = 12;
    }
    var day2 = day;
    var days2 = new Date(year2, month2, 0);
    days2 = days2.getDate();
    if (day2 > days2) {
        day2 = days2;
    }
    if (month2 < 10) {
        month2 = '0' + month2;
    }
    var t2 = year2 + '/' + month2 + '/' + day2;
    return t2;
}

/**
 * 获取下一个月
 *
 * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
 */        
function getNextMonth(date) {
    var arr = date.split('/');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    var day = arr[2]; //获取当前日期的日
    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中的月的天数
    var year2 = year;
    var month2 = parseInt(month) + 1;
    if (month2 == 13) {
        year2 = parseInt(year2) + 1;
        month2 = 1;
    }
    var day2 = day;
    var days2 = new Date(year2, month2, 0);
    days2 = days2.getDate();
    if (day2 > days2) {
        day2 = days2;
    }
    if (month2 < 10) {
        month2 = '0' + month2;
    }

    var t2 = year2 + '/' + month2 + '/' + day2;
    return t2;
}

/***
 * 校验时间是否为空，并且起始时间小于结束时间
 * @param startDate
 * @param endDate
 */
function checkData(startDate,endDate){
	if((startDate != "" && endDate == "")||(startDate == "" && endDate != "")||(startDate == "" && endDate == "")) {
		   $.messager.alert('提示',"时间不能够为空!");
			return false;
	}
	if(endDate != ""){
		   if(!(verifyDate(startDate)&&verifyDate(endDate))){
			   $.messager.alert('提示',"时间格式不正确!");
			   return false;
		   }
		   var sDate = new Date(startDate);
		   var eDate = new Date(endDate);
		   if(eDate < sDate){
			   $.messager.alert('提示',"您输入的查询结束时间不能够小于查询开始时间!");
			   return false;
		   }
		}
	return true;
}

/**
 * 验证日期格式
 * @author WangBo
 */
function verifyDate(d) {
	var datePattern = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
	return datePattern.test(d);
}
