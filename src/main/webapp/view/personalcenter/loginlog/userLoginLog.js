/**
 * js页面初始化方法入口
 * @auther 洪皓
 */
$(document).ready(function()
{
	reload(getRootPath() + "/userLog/QueryUserLogByUserModel",arr);
	//查询当前账号的所有子账号
	$('#loginName').combobox({
		url:getRootPath() + "/userLog/queryAllUserModel",
		valueField:'userId',
	    textField:'loginName',
	    //加载完成后,设置选中第一项
	    onLoadSuccess: function () { 
            var val = $(this).combobox("getData");
            for (var item in val[0]) {
                if (item == "userId") {
                    $(this).combobox("select", val[0][item]);
                }
            }
        }
	});
	//改表一级菜单上贸易情报模块的背景色
	document.getElementById("nav-item_1").style.backgroundColor = "#ffffff";
	document.getElementById("font_color1").style.color = "#0066cc";
	//显示二级菜单栏
	showSubMenu(1)
	//改变二级菜单栏样式
	changePCSubMenuColor(3);
});

/**
 * 配置选项
 */
var arr = [ {
	field : 'loginName',
	title : "User Name",
	width : 224,
	align:'center'
}, {
	field : 'loginTime',
	title : "Time",
	width : 224,
	align:'center'
}, {
	field : 'ipAddress',
	title : "IP Address",
	width : 224,
	align:'center'
}, {
	field : 'ipAttribution',
	title : "IP Location",
	width : 226,
	align:'center'
} ];

/**
 * 加载数据
 */
function reload(url,queryParams){
	$('#userlogin').datagrid({
		url : url,
		queryParams : queryParams,
		pagination : true,
		singleSelect : true,
		autoRowHeight : false,
		pageSize :15,
		pageList : [ 15, 30, 60, 80, 100 ],
		// queryParams:{},
		loadMsg : "Loading Data……",
		columns : [arr],
		toolbar : "#toolbar",
		onLoadSuccess: function(data){
			console.debug(data);
		}
	});
}

/**
 * 按照登录名称和时间查询登录日志
 */
function selectByLogin(){
	var userId = $('#loginName').combobox('getValue');
	var beginDate = "";
	if ($("#beginDate")) {
		beginDate = $("#beginDate").val().trim();
	}
	var endDate = "";
	if ($("#endDate")) {
		endDate = $("#endDate").val().trim();
	}
	var queryParams = {
		userId : userId,
		beginDate : beginDate,
		endDate : endDate
	}
	var url = getRootPath() + "/userLog/queryUserLogByUserName";
	reload(url,queryParams);
}

function mouseoverDo(num){
	if (num == 1) {
		$("#submitDo").css("background-image", "url("+getRootPath()+"/static/img/personalcenter/login_sm.png)");
	} else if (num == 2) {
		$("#submitDo").css("background-image", "url("+getRootPath()+"/static/img/personalcenter/login_voer.png)");
	}
}
