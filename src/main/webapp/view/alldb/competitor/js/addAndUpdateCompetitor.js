/**
 * -----------------------------------------------添加竞争对手------------------------------------------
 */

var proOrComp = 2;		// 1 : 产品标签 2 : 竞争对手 

/**
 * 预处理
 */
$(function() {
	// 获取添加竞争对手的所有的国家
	getAllCountry();
	// 提示用户
	hintCompanyName();
	// 对公司名称的颜色处理 和 只允许输入英文
	objClick($("input[name='companyName']"));
	// 离开判断
	$("input[name=companyName]").bind('blur',objBurl($("input[name='companyName']")));
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
 * 提示用语
 */
function hintCompanyName(){
	var companyName = $("input[name='companyName']");
	companyName.val("请输入英文");
	companyName.css("color","#FF0000");
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
	}
}

/**
 * 取消
 */
function closeCompetitorForAllDB() {
//	art.dialog.list['showEcuadorCompetitorDlg'].close();
	art.dialog({
		id : 'closeCompetitorDialogId'
	}).close();
}

/**
 * 保存竞争对手
 */
function saveCompetitorForAllDB() {
	// 客户名称必填项校验控制
	var companyName = $("input[name='companyName']").val().trim();
	var customerValue = $("input[name='customerValue']").val().trim();
	// 验证公司名称是否为包含中文 /+/
	var companyNameReg = /[\u4E00-\u9FA5]/i;
	if (checkObjIsRegular(companyNameReg, companyName)){
		$.messager.alert('提示', '请输入客户名称为英文或者数字', 'info');
		$("#companyName").focus();
		return false;
	}
	// 验证客户价值是否为10位以下的数字
	var patter = /^\d{0,10}$/;
	if (!companyName) {
		$.messager.alert('提示', '请输入客户的名称！',
				'info');
		$("input[name='companyName']").focus();
		return;
	}
	// 客户价值必填项校验控制
	if (!customerValue) {
		$.messager.alert('提示', '请输入客户价值！', 'info');
		$("#customerValue").focus();
		return;
	} else if (!patter.test(customerValue)) {
		$.messager
				.alert('提示', '客户价值必须为数字', 'info');
		$("#customerValue").focus();
		return;
	}
	$('#competitorfmForAllDB').form('submit', {
		url : getRootPath() + "/competitor/addCompetitorInfo",
		onSubmit : function() {
			var bol = $(this).form('validate');
			return bol;
		},
		success : function(data) {
			var dataObj = eval("(" + data + ")");
			var result = "";
			if (dataObj.result == 1) {
				result = "添加竞争对手成功!";
			} else {
				result = "添加竞争对手失败!";
			}
			$.messager.alert("提示", result);
			// 调用共用页面,查询最新的数据
			try {
				queryDataGrid();
			} catch (error) {
				console.debug(error);
			} finally {
				art.dialog({
					id : 'closeCompetitorDialogId'
				}).close();
			}
		}
	});
}

/**
 * 修改竞争对手
 */
function updateCompetitorForAllDB(){
	// 客户名称必填项校验控制
	var companyName = $("input[name='companyName']").val().trim();
	var customerValue = $("input[name='customerValue']").val().trim();
	// 验证公司名称是否为英文
	var companyNameReg = /[\u4E00-\u9FA5]/i;
	if (checkObjIsRegular(companyNameReg, companyName)){
		$("#CompanyNamesForAllDBs").focus();
		$.messager.alert('提示', '公司名称必须为英文或者数字！',
		'info');
		return false;
	}
	// 验证客户价值是否为10位以下的数字
	var patter = /^\d{0,10}$/;
	if (companyName == "" || companyName == null || companyName == "undefined") {
		$.messager.alert('提示', '请输入公司名称！',
				'info');
		$("input[name='companyName']").focus();
		return false;
	}
	// 客户价值必填项校验控制
	if (customerValue == "" || customerValue == null
			|| customerValue == "undefined") {
		$.messager.alert('提示', '请输入客户价值！', 'info');
		$("#customerValue").focus();
		return false;
	} else if (!patter.test(customerValue)) {
		$.messager
				.alert('提示', '客户价值必须为数字', 'info');
		$("#customerValue").focus();
		return false;
	}
	$('#competitorfmForAllDB').form('submit', {
		url : getRootPath() + "/competitor/updateCompetitorInfo",
		onSubmit : function() {
			var bol = $(this).form('validate');
			return bol;
		},
		success : function(data) {
			var dataObj = eval("(" + data + ")");
			var result = dataObj.result == 1 ? '修改竞争对手成功!' : "修改竞争对手失败!";
			$.messager.alert('提示', result);
			art.dialog({
				id : 'showCompetitorDlg'
			}).close();
			// 调用共用页面,查询最新的数据
			try {
				queryDataGrid();
			} catch (error) {
				console.debug(error);
			} finally {
				art.dialog({
					id : 'closeCompetitorDialogId'
				}).close();
			}
		}
	});
}
