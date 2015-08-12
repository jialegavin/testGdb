/**
 * js页面初始化方法入口
 * @auther 洪皓
 */
$(document).ready(function()
{
	//改表一级菜单上贸易情报模块的背景色
	document.getElementById("nav-item_1").style.backgroundColor = "#ffffff";
	document.getElementById("font_color1").style.color = "#0066cc";
	//显示二级菜单栏
	showSubMenu(1)
	//改变二级菜单栏样式
	changePCSubMenuColor(1);
	//查询用户的权限
	queryUserRight();
});

/**
 * 查看用户权限
 * @auther 洪皓
 */
function queryUserRight()
{
	//加载权限表数据
	$('#rightTable').datagrid({
		url:'/gbdbas/permission/QueryUserRight',
		pageNumber:1,
		pageSize:50,
		fitcolumns:false,
		autoRowHeight:false,
		sortorder: 'asc',
		loadMsg:'Loading...',
		pageList:[50,80,100],
		onLoadSuccess: function(data){
			if(data.rows.length ==0)
			{
				$('#rightDiv').animate({opacity:"0"},0);
				alertMessage('Tips','Sorry, no permission for checking','info');
			}else
			{
				$('#rightDiv').animate({opacity:"1"},0);
			}
		}  
	});
}

/**
 * @param tip 提示标题
 * @param text 提示内容
 * @param iconType  提示图标类型
 * @author honghao
 */
function  alertMessage(tip,text,iconType){
        $.messager.alert(tip,text,iconType);
}