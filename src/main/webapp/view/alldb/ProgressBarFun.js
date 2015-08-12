/**
 * --------------------------------------封装进度条-----------------------------------------
 */

var timeQuery;

/**
 * 初始化进度条进度条
 * @param title : 弹出框的标题
 * @param contentTitle : 弹出框文本信息
 * @param id : 弹出框ID  id不可以重复
 * @param width : 弹出框宽度
 * @param height : 弹出框高度
 * @param lock : 背景是否灰化  true ： 灰化 false : 不灰化
 * @param opacity : 透明度  {0.1 - 0.9}
 * @param fixed : 开启静止定位。它静止在浏览器某个地方不动，也不受滚动条拖动影响
 * @author WangBo
 */
var InitCueBox = function(title,contentTitle,id,width,height,lock,opacity,fixed) {
	art.dialog({
		title:title,
	    content: "<div style='text-align: center; color:#0066CC'>"+contentTitle+"</div><br/><div id='downLoadPro' style='width:300px;'></div>",  
	    id:id,
	    width:width,
	    height:height,
	    lock:lock,  //背景是否灰化
	    opacity: opacity,//透明度
	    fixed:fixed //开启静止定位。它静止在浏览器某个地方不动，也不受滚动条拖动影响
	});
}

/**
 * 执行进度条
 * @param businessFun : 业务方法
 * @param seconds : 定时器每次执行的时间
 */
var queryProBySeconds = function(businessFun,seconds) {
	this.timeQuery = setInterval(businessFun,seconds);
	$("#downLoadPro").progressbar({value: 0});		//初始化进度条
}

/**
 * 停止进度条<br>注销进度条
 * @param id : 弹出框ID
 */
var stopPro = function(id) {
	if (id) {
		clearInterval(timeQuery);
		art.dialog({
			id : id
		}).close();
	}
}

/**
 * 根据seconds停止进度条
 * @param id : id
 * @param seconds : 秒{单位}  比如 1000 为一秒
 */
var stopOfSeconds = function(id,seconds) {
	setTimeout(stopPro(id),seconds);
}