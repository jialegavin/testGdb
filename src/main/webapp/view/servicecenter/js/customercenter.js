
$(document).ready(function(){
	//改表一级菜单上产品标签模块的背景色
	$("#nav-item_8").css('backgroundColor','#ffffff');
	$("#nav-item_8").find("a").find("span").css('color','#0066cc');
});

/**
 * 点击查询所有留言信息
 */ 
function query(){
	$("#downCls").css("display","block");
	$("#tab").css("display","block");
	$('#mytable').datagrid({
		url:'/gbdbas/customer/queryCentent',
		pageNumber:1,
	     pageSize:20,
	     rownumbers:false,
	     onLoadSuccess: function(data){
				if(data.rows.length == 0){
					$.messager.alert("提示", "没有查询到数据!");
			    } 
				else{
				}
		    }  
	});
 }	


/**
 * 点击确认按钮切换图片
 * 报表中心模块
 * @param obj
 */
function btnClick(obj){
	$("#btn").css({
		  "background-image":"url(/gbdbas/static/img/contrastreport/img/ok2.png)"
    });
	var name = $("#name").val();
	var tel = $("#tel").val();
	var centent = $("#centent").val();
	if(name == '' || tel == '' || centent == ''){
		$.messager.alert("提示","输入信息不完整,请输入完整!");
	}else if(isPhone(tel)){
		$.messager.alert("提示","电话号码无效,请输入正确的电话号码!");
	}else{
		$.post(
				"/gbdbas/customer/addCustomer", 
				{ 
					"name": name,
					"tel": tel,
					"centent":centent
				},   
				function (data, textStatus){
				 var href = window.location.href;
				 if(contains(href,'trade-easy',true)){
					 alert('十分感谢您联系趣易，您反馈的问题已提交，我们会在48小时内进行处理，并根据您的电话进行回复！');
				 }else if(contains(href,'www.ybdb.net',true)){
					 alert('十分感谢您联系英蓓，您反馈的问题已提交，我们会在48小时内进行处理，并根据您的电话进行回复！');
				 }	
				$("#name").val("");
				$("#tel").val("");
				$("#centent").val("");
		});
	}
}


/**
 * 字符串包含
 * @param string
 * @param substr
 * @param isIgnoreCase
 * @returns {Boolean}
 */
function contains(string,substr,isIgnoreCase){
	if(isIgnoreCase){ 
		string=string.toLowerCase();
		substr=substr.toLowerCase();
		var startChar=substr.substring(0,1); 
		var strLen=substr.length; 
		
		 for(var j=0;j<string.length-strLen+1;j++) {
			 if(string.charAt(j)==startChar){//如果匹配起始字符,开始查找              
		
				 if(string.substring(j,j+strLen)==substr) {
					 return true;
				}
			 } 
		 }
		return false; 
	}
}


/**
 * 检查字符串是否为合法手机号码
 * @param {String} 字符串
 * @return {bool} 是否为合法手机号码
 */
 function isPhone(aPhone) {
     var bValidate = RegExp(/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)|(13\d{9}$)|(15[0135-9]\d{8}$)|(18[267]\d{8}$)/).test(aPhone);
     
     if (!bValidate) {
         return true;
     }
     else
         return false;
 }





/**
 * 删除表格中数据汇总的所有的列
 * @param tableName:easyUI table表格的名称
 * @author honghao
 */
function deleteDataColumn(tableName){
	var item = $('#'+tableName+'').datagrid('getRows');
	if (item) {
		for (var i = item.length - 1; i >= 0; i--) {
			var index = $('#'+tableName+'').datagrid('getRowIndex', item[i]);
			$('#'+tableName+'').datagrid('deleteRow', index);
		}
	}
}
/**
 * 判断用户点击导出PDF或者是excel按钮的时候 有没有选中要导出的数据
 * @param country--国家的简称
 * @param json--保存国际化信息
 * @author jiahp
 * @return 
 */
function judgeSelectedData(names){
	var checkedItems = $('#mytable').datagrid('getChecked');
	$.each(checkedItems, function(index, item) {
		names[index] = item.id;
	});
	if (names.length == 0) {
		$.messager.alert("提示","请勾选留言信息!");
		return false;
	}
	return names;
}
/**
 * 导出留言信息
 */
function exportPDForEXCELPub(type){
	//先判断用户有没有选中要导出的数据
	var names=new Array();
	var flag=judgeSelectedData(names);
	if(flag==false){
		return;
	}
	var param=getJsonParam(names);
	window.location.href = '/gbdbas/customer/queryCententById?id='+param;
}

/**
 * 删除指定的留言信息
 */
function deleteCentent(){
	//先判断用户有没有选中要导出的数据
	var names=new Array();
	var flag=judgeSelectedData(names);
	if(flag==false){
		return;
	}
	var param=getJsonParam(names);
	$.post("/gbdbas/customer/deleteCententById",{"id":param},
			function(){
		$('#mytable').datagrid('reload');
	});
	
}

/**
 * 根据国家不同 返回对应的json格式参数
 * @param names
 * @param country
 * @param exportType
 * @return
 */
function getJsonParam(names){
	var str = '';
	for (var i = 0; i < names.length; i++) {
		str = str + names[i]+",";
	}
	return str;
}
