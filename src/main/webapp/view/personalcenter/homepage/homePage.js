/**
 * js页面初始化方法入口
 * @auther 洪皓
 */
$(document).ready(function()
{
	//显示二级菜单栏
	showSubMenu(1)
	//改表一级菜单上贸易情报模块的背景色
	document.getElementById("nav-item_1").style.backgroundColor = "#ffffff";
	document.getElementById("font_color1").style.color = "#0066cc";
	//在首页面显示各国家最新数据更新
	generateQueryCondition();
});

/**
 * 在首页面显示各国家最新数据更新
 * @auther 洪皓
 */
function generateQueryCondition()
{
	$.post('/gbdbas/queryCountryUpdateTime',function(data){
		var htmlData = data['htmlData'];
		//显示最新数据更新时间
		$("#updateTimeMarquee").html(htmlData);
	},"json");
}