/**
 * ----------------------------客户信息 添加 && 编辑
 * 页面-------------------------------------
 */

$(function() {
	// 获取所有的国家
	getAllCountry();
	var companyName = $("input[name='companyName']");
	// 提示语
	hintCompanyName();
	// 对公司名称的颜色处理 和 只允许输入英文
	objClick(companyName);
	// 离开判断
	$("input[name=companyName]").bind('blur',objBurl(companyName));
//	objBurl(companyName);
});


/**
 * 对公司名称的颜色处理 和 只允许输入英文
 * @param id
 */
function objClick(id){
	id.click(function(){
		$(this).css("color","#000000");
		if ("请输入英文"==$(this).val()){
			$(this).val("");
		}
	});
}

/**
 * 提示用语
 */
function hintCompanyName(){
	var companyName = $("input[name='companyName']");
	companyName.val("请输入英文");
	companyName.css("color","#FF0000");
}

/**
 * 离开事件
 * @param id
 */
function objBurl(id){
	id.blur(function(){
		var reg = /^[a-zA-Z0-9\s.-]+$/;
		if (!reg.test($(this).val())) {
			$("#showSpanOfPage").css("display","inline");
		} else {
			$("#showSpanOfPage").css("display","none");
		}
	});
}

/**
 * 获取所有的国家
 */
function getAllCountry() {
	var countryArray = reportArray.ALL;
	for (x in countryArray) {
		var option = document.createElement("option");
		option.width = "190";
		option.value = countryArray[x];
		option.innerHTML = countryArray[x];
		$("select[name='country']").append(option);
//		document.getElementById("ecuador_companyCountry")
//		.appendChild(option);
	}
}

/**
 * 保存客户
 * @returns {Boolean}
 */
function saveCustomer() {
	// 客户名称必填项校验控制
	var companyName = $("input[name='companyName']").val().trim();
	var customerValue = $("#customerValue").val().trim();
	// 验证公司名称是否为英文
	var companyNameReg = /[\u4E00-\u9FA5]/i;
	if (checkObjIsRegular(companyNameReg, companyName)){
		$.messager.alert('Prompt', 'Name of customer can ONLY be digits or letters', 'info');
		$("#companyName").focus();
		return false;
	}
	// 验证客户价值是否为10位以下的数字
	var patter = /^\d{0,10}$/;
	if (!companyName) {
		$.messager.alert('Prompt', '请输入客户的名称', 'info');
		$("#agt_companyName").focus();
		return false;
	}
	// 客户价值必填项校验控制
	if (!customerValue) {
		$.messager.alert('Prompt','请输入客户价值', 'info');
		$("#customerValue").focus();
		return false;
	} else if (!patter.test(customerValue)) {
		$.messager.alert('Prompt', '客户价值必须是数字', 'info');
		$("#customerValue").focus();
		return false;
	}

	$('#argentinaCustomerfm').form('submit', {
		url : getRootPath() + "/customer/addCustomerInfo",
		onSubmit : function() {
			var bol = $(this).form('validate');
			return bol;
		},
		success : function(data) {
			var dataObj = eval("(" + data + ")");
			result = dataObj.result == 1 ? "Save Successfully!" : "Save Failed";
			$.messager.alert('Prompt', result);
			art.dialog({
				id : 'showCustomerDlg'
			}).close();
			queryDataGrid();
			//强制刷新页面 给客户和对手重新赋值
			location.reload(false);   
		}
	});
}

/**
 * 修改页面
 * @param flag : true : 修改 , false : 添加
 */
function updateJspForCustomer(flag){
	var titleSpan = $("#titleSpan");
	var titalText = "";
	if (flag) {
		titalText = "Change Customer Information";
		$("#sureSpan").hide();
		$("#updateSpan").show();
	} else {
		titalText = "Add Customer Information";
		$("#sureSpan").show();
		$("#updateSpan").hide();
	}
	titleSpan.html(titalText);
}

/**
 * 修改客户信息
 * 
 * @returns {Boolean}
 */
function updateCustomer() {
	// 客户名称必填项校验控制
	var companyName = $("input[name='companyName']").val().trim();
	var customerValue = $("#customerValue").val().trim();
	// 验证公司名称是否为英文
	var companyNameReg = /[\u4E00-\u9FA5]/i;
	if (checkObjIsRegular(companyNameReg, companyName)){
		$.messager.alert('Prompt', '请输入客户名称为英文或者数字', 'info');
		$("#companyName").focus();
		return false;
	}
	// 验证客户价值是否为10位以下的数字
	var patter = /^\d{0,10}$/;
	if (companyName == "" || companyName == null || companyName == "undefined") {
		$.messager.alert('Prompt', '请输入客户的名称', 'info');
		$("#agt_companyName").focus();
		return false;
	}
	// 客户价值必填项校验控制
	if (customerValue == "" || customerValue == null
			|| customerValue == "undefined") {
		$.messager.alert('Prompt','请输入客户价值', 'info');
		$("#customerValue").focus();
		return false;
	} else if (!patter.test(customerValue)) {
		$.messager.alert('Prompt', '客户价值必须是数字', 'info');
		$("#customerValue").focus();
		return false;
	}

	$('#argentinaCustomerfm').form('submit', {
		url : getRootPath() + "/customer/updateCustomer",
		onSubmit : function() {
			var bol = $(this).form('validate');
			return bol;
		},
		success : function(data) {
			var dataObj = eval("(" + data + ")");
			result = dataObj.result == 1 ? "Change Successfully!" : "Change Failed";
			$.messager.alert('Prompt', result);
			art.dialog({
				id : 'showCustomerDlg'
			}).close();
			queryDataGrid();
		}
	});
}

/**
 * 关闭小窗口
 */
function closeCustomer() {
	art.dialog.list['showCustomerDlg'].close();
}
