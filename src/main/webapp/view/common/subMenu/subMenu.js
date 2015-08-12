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