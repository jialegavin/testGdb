/**
 * 鼠标单击在一级菜单跳转不同的页面
 * @auther honghao
 */
function clickMenuForword(index) {
	var userDesc = $("input[name='userDesc']").val();
	if (userDesc == '按次用户') {
		userLocationHref(index);
	} else if (userDesc == '试用用户') {
		userLocationHref1(index);
	} else {
		locationHref(index);
	}
}

var userLocationHref1 = function(index) {
	// 个人中心
	if(index == 1)
	{
		window.location.href = "/gbdbas/view/personalcenter/homepage/homePage.jsp";
	}
	//查看权限
	else if(index==2)
	{
		window.location.href = "/gbdbas//view/personalcenter/viewright/findUserRight.jsp";
	}
	//我的收藏
	else if(index==3)
	{
		window.location.href = "/gbdbas/view/personalcenter/myfavorites/myFavorites.jsp";
	}	
	//登录日志
	else if(index==4)
	{
		window.location.href = "/gbdbas/view/personalcenter/loginlog/userLoginLog.jsp";
	}
	//子账号管理
	else if(index==5)
	{
		window.location.href = "/gbdbas/view/usermanagement/sonUser.jsp";
	}
	//账户管理
	else if(index==14)
	{
		window.location.href = "/gbdbas/view/personalcenter/account/accountManagement.jsp";
	}
	//贸易区情报
	else if(index==6)
	{
		window.location.href = "/gbdbas/view/datasearch/datasearch.jsp";
	}
	//我的客户
	else if(index==7)
	{
		window.location.href = "/gbdbas/view/alldb/customer/customer.jsp";
	}
	//我的对手
	else if(index==8)
	{
		window.location.href = "/gbdbas/view/alldb/competitor/competitor.jsp";
	}
	//产品标签
	else if(index==9)
	{
		window.location.href = "/gbdbas/view/alldb/projectanalyze/projectanalyze.jsp";
	}
	//对比分析
	else if(index==10)
	{
		window.location.href = "/gbdbas/view/contrastreport/jsp/reportContrastre.jsp";
	}
	//全库下载
	else if(index==11)
	{
		alertRightMessage();
		return;
		window.location.href = "/gbdbas/view/alldb/downloadDBData/downLoadAllData.jsp";
	}
	//产品定制
	else if(index==12)
	{
		window.location.href = "/gbdbas/view/sellcenter/sellIndex.jsp";
	}
	//客服中心
	else if(index==13)
	{
		window.location.href = "/gbdbas/view/servicecenter/jsp/customercenter.jsp";
	}
}

var locationHref = function(index) {
	// 个人中心
	if(index == 1)
	{
		window.location.href = "/gbdbas/view/personalcenter/homepage/homePage.jsp";
	}
	//查看权限
	else if(index==2)
	{
		window.location.href = "/gbdbas//view/personalcenter/viewright/findUserRight.jsp";
	}
	//我的收藏
	else if(index==3)
	{
		window.location.href = "/gbdbas/view/personalcenter/myfavorites/myFavorites.jsp";
	}	
	//登录日志
	else if(index==4)
	{
		window.location.href = "/gbdbas/view/personalcenter/loginlog/userLoginLog.jsp";
	}
	//子账号管理
	else if(index==5)
	{
		window.location.href = "/gbdbas/view/usermanagement/sonUser.jsp";
	}
	//账户管理
	else if(index==14)
	{
		window.location.href = "/gbdbas/view/personalcenter/account/accountManagement.jsp";
	}
	//贸易区情报
	else if(index==6)
	{
		window.location.href = "/gbdbas/view/datasearch/datasearch.jsp";
	}
	//我的客户
	else if(index==7)
	{
		window.location.href = "/gbdbas/view/alldb/customer/customer.jsp";
	}
	//我的对手
	else if(index==8)
	{
		window.location.href = "/gbdbas/view/alldb/competitor/competitor.jsp";
	}
	//产品标签
	else if(index==9)
	{
		window.location.href = "/gbdbas/view/alldb/projectanalyze/projectanalyze.jsp";
	}
	//对比分析
	else if(index==10)
	{
		window.location.href = "/gbdbas/view/contrastreport/jsp/reportContrastre.jsp";
	}
	//全库下载
	else if(index==11)
	{
		window.location.href = "/gbdbas/view/alldb/downloadDBData/downLoadAllData.jsp";
	}
	//产品定制
	else if(index==12)
	{
		window.location.href = "/gbdbas/view/sellcenter/sellIndex.jsp";
	}
	//客服中心
	else if(index==13)
	{
		window.location.href = "/gbdbas/view/servicecenter/jsp/customercenter.jsp";
	}
}

var userLocationHref = function(index){
	// 个人中心
	if(index == 1)
	{
		window.location.href = "/gbdbas/view/personalcenter/homepage/homePage.jsp";
	}
	//查看权限
	else if(index==2)
	{
		alertRightMessage();
		return;
	}
	//我的收藏
	else if(index==3)
	{
		window.location.href = "/gbdbas/view/personalcenter/myfavorites/myFavorites.jsp";
	}	
	//登录日志
	else if(index==4)
	{
		window.location.href = "/gbdbas/view/personalcenter/loginlog/userLoginLog.jsp";
	}
	//子账号管理
	else if(index==5)
	{
		alertRightMessage();
		return;
	}
	//账户管理
	else if(index==14)
	{
		window.location.href = "/gbdbas/view/personalcenter/account/accountManagement.jsp";
	}
	//贸易区情报
	else if(index==6)
	{
		window.location.href = "/gbdbas/view/datasearch/datasearch.jsp";
	}
	//我的客户
	else if(index==7)
	{
		alertRightMessage();
		return;
		window.location.href = "/gbdbas/view/alldb/customer/customer.jsp";
	}
	//我的对手
	else if(index==8)
	{
		alertRightMessage();
		return;
		window.location.href = "/gbdbas/view/alldb/competitor/competitor.jsp";
	}
	//产品标签
	else if(index==9)
	{
		alertRightMessage();
		return;
		window.location.href = "/gbdbas/view/alldb/projectanalyze/projectanalyze.jsp";
	}
	//对比分析
	else if(index==10)
	{
		alertRightMessage();
		return;
		window.location.href = "/gbdbas/view/contrastreport/jsp/reportContrastre.jsp";
	}
	//全库下载
	else if(index==11)
	{
		alertRightMessage();
		return;
		window.location.href = "/gbdbas/view/alldb/downloadDBData/downLoadAllData.jsp";
	}
	//产品定制
	else if(index==12)
	{
		alertRightMessage();
		return;
		window.location.href = "/gbdbas/view/sellcenter/sellIndex.jsp";
	}
	//客服中心
	else if(index==13)
	{
		window.location.href = "/gbdbas/view/servicecenter/jsp/customercenter.jsp";
	}
}

/**
 * 弹出框
 */
var alertRightMessage = function(){
	$.messager.alert('提示','您好,您无权限访问此功能！<br>请联系客服人员：' + tell,'info');
}


//-------------------------------------------------------------------------------------------------------
//  所有国家更新数据
//-------------------------------------------------------------------------------------------------------
var allCountryObj = {};		// 国家数据对象

$.ajax({
	url : "/gbdbas/queryDBDateMessage",
	data : {},
	type : "post",
	dateType : "json",
	success : function(data) {
		try {
			if (data) {
				allCountryObj = eval('(' + data + ')');
			}
		} catch (e) {
			console.debug("head.js 用于请求个个国家数据更新时间时,导致报错 ,报错信息: " + e);
		}
	},
	error : function(data) {
		console.info("head.js 用于请求个个国家数据更新时间时,导致报错 ,报错信息: " + data);
	}
});

//-------------------------------------------------------------------------------------------------------
//  用户权限对象
//-------------------------------------------------------------------------------------------------------
/**
 * 用户权限对象
 * @param key : 权限集合
 * @param user : 用户Model对象
 * @param nowDate : 服务器当前时间
 * @param hsHsCode : 是否存在海关编码
 * @param hsGoodsDesc : 是否存在产品描述
 * @return 用户权限对象
 */
var userRightCfg = {};

/**
* 初始化 用户权限信息
*/
$.ajax({
	url: getRootPath() + "/permission/queryUserRightMessage",
	data : [],
	type : "post",
	dataType : "json",
	success : function(data) {
		console.debug("初始化用户权限信息 !");
		console.debug(data);
		userRightCfg = data;
		userRightCfg.hsHsCode = queryHsCode(userRightCfg.key);		//判断集合中是否存在hscode
		userRightCfg.hsGoodsDesc = queryHsGoodsDesc(userRightCfg.key);	//判断集合中是否存在产品描述
	}
});

/**
 * 判断集合中是否存在hscode
 * @param userRightModel : 用户权限集合
 * @return true : 存在 false : 不存在
 */
var queryHsCode = function(userRightModel){
	var result = false;
	for (i in userRightModel) {
		if (userRightModel[i].byHsCode) {
			result = true;
		}
	}
	return result;
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
 * 判断集合中是否存在产品描述
 * @param userRightModel : 用户权限集合
 * @return true : 存在 false : 不存在
 */
var queryHsGoodsDesc = function(userRightModel){
	var result = false;
	if (userRightModel) {
		for (i in userRightModel) {
			if (userRightModel[i].byProductDesc) {
				result = true;
			}
		}
	} else {
		result = true;
	}
	return result;
}