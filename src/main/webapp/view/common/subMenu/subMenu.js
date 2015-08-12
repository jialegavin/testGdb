/**
 * 显示二级菜单栏
 * @param v
 */
function showSubMenu(v){
	$("#middleDiv").css("display",'block');
	for(i=1;i<=2;i++){
		if(i==v){
			document.getElementById("menu"+i).style.display="block";
			document.getElementById("title"+i).style.display="block";
		}else{
			document.getElementById("menu"+i).style.display="none";
			document.getElementById("title"+i).style.display="none";
		}
	}
}


function subClick(url){
	// js 权限控制
//	if(!subModuleControl(url)){
//		return;
//	}
	window.location=url;
}

/**
 * 改变个人中心二级菜单按钮颜色
 * @auther honghao
 */
function changePCSubMenuColor(v){
	for(i=1;i<=4;i++){
		if(i==v){
			document.getElementById("user"+i).style.color="#0066cc";
		}else{
			document.getElementById("user"+i).style.color="#000";
		}
	}
}

/**
 * 按次用户 权限跳转控制
 * @param index
 */
function changeHref(index) {
	var userDesc = $("input[name='userDesc']").val();
	if (userDesc == '按次用户') {
		if (index == 1 || index == 4) {
			alertRightMessage();
			return;
		} else {
			locahHref(index);
		}
	} else {
		locahHref(index);
	}
}

/**
 * 根据不同的模块跳转不同的跳转路径
 */
var locahHref = function(index) {
	if (index == 1) {
		window.location.href = "/gbdbas/view/personalcenter/viewright/findUserRight.jsp";
	} else if (index == 2) {
		window.location.href = "/gbdbas/view/personalcenter/myfavorites/myFavorites.jsp";
	} else if (index == 3){
		window.location.href = "/gbdbas/view/personalcenter/loginlog/userLoginLog.jsp";
	} else if (index == 4){
		window.location.href = "/gbdbas/view/usermanagement/sonUser.jsp";
	}
}

/**
 * 改变客户/对手二级菜单按钮颜色
 * @auther honghao
 */
function changeCCSubMenuColor(v){
	for(i=1;i<=2;i++){
		if(i==v){
			document.getElementById("search"+i).style.color="#ED5564";
		}else{
			document.getElementById("search"+i).style.color="#666";
		}
	}
}