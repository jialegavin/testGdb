//初始化加载
$(document).ready(function()
{
   	 //改表一级菜单上贸易情报模块的背景色
  	 document.getElementById("nav-item_1").style.backgroundColor = "#ffffff";
   	 document.getElementById("font_color1").style.color = "#0066cc";
     //显示二级菜单栏
  	 showSubMenu(1)
  	 //改变二级菜单栏样式
  	 changePCSubMenuColor(5);
  	 findCountUserRight();
  	 // js 权限控制 /gbdbas20150721/src/main/resources/config/search/TryUserQueryCountry.json
 	if(authorityControl('')=="试用用户"){
 		//加载权限表数据
 		$('#buyRightTable').datagrid({
 			url:'/gbdbas/tryUserCountry',
 			pageNumber:1,
 			method:'get',
 			pageSize:50,
 			fitcolumns:false,
 			autoRowHeight:false,
 			sortorder: 'asc',
 			loadMsg:'正在查询...',
 			pageList:[50,80,100],
 			onLoadSuccess: function(data){
 			}  
 		});
 		
 	}else{
 		 findUserRight();
 	}
  	 
  	  
});


//购买权限
function findUserRight()
{
	// js 权限控制
	//加载权限表数据
	$('#buyRightTable').datagrid({
		url:'/gbdbas/permission/QueryUserRight',
		pageNumber:1,
		pageSize:50,
		fitcolumns:false,
		autoRowHeight:false,
		sortorder: 'asc',
		loadMsg:'正在查询...',
		pageList:[50,80,100],
		onLoadSuccess: function(data){
		}  
	});
}
//按次用户购买权限
function findCountUserRight()
{
	// js 权限控制
	//加载权限表数据
	$('#buyCountRightTable').datagrid({
		url:'/gbdbas/permission/queryUserCountRight',
		pageNumber:1,
		pageSize:50,
		fitcolumns:false,
		autoRowHeight:false,
		sortorder: 'asc',
		loadMsg:'正在查询...',
		pageList:[50,80,100],
		onLoadSuccess: function(data){
		}  
	});
}
/**
 * 格式化显示列
 */
function openHistoryDataFormat(value,rowData,rowIndex){
	var openHistory = rowData.openHistoryData;
	var country = rowData.byCountry;
	if(openHistory){
		if(country == '中国'){
			return "开放";
		}else{
			return "不开放";
		}
	}else{
		return "";
	}
	
}

function remarksFormat(value,rowData,rowIndex){
	if(value==''){
		return '暂无';
	}else{
		return value;
	}
}