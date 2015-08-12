/**
 * 打开修改密码对话框
 * @author XL
 */
function openUpdatePwdDlg(){
	openDivArtDialog("修改密码", "updatePwdDlg", "updatePwdDlg",450,250,true);
}

/**
 * 保存用户
 */
function saveUserPassword() {
	var userPassword = $("#userPassword").val();
	var newPassword = $("#newPassword").val();
	var surePassword = $("#surePassword").val();
	// 非空验证
	if (userPassword && newPassword && surePassword) {
		// 新密码和老密码效验
		if (newPassword != surePassword) {
			$.messager.alert("提示", "新密码和确认密码必须是一致,请确认您的操作!");
			$("#surePassword").val("");
			return;
		}
	} else {
		$.messager.alert("提示", "您填写的信息不够完整,请确认您的操作!");
		return;
	}
	var url = "/gbdbas/UserPassword/updateUserByPassword";
	$('#pwdfm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			data = eval('(' + data + ')');
			//0 : 原密码,输入密码一致  1 : 原密码,输入密码不一致  2 : 密码为空
			//3 : 修改密码成功 4 : 修改密码失败
			if (data.resultNum == 1) {
				$.messager.alert("提示", "原密码与输入密码不一致!");
				return;
			} else if (data.resultNum == 2) {
				$.messager.alert("提示", "密码为空!");
				return;
			} else if (data.resultNum == 4) {
				$.messager.alert("提示", "修改密码失败!");
				return;
			} else if (data.resultNum == 3) {
				$.messager.alert("提示", "修改密码成功!");
				//返回登录页面
				location.reload();
//				window.location.href = getRootPath() + "/src/main/webapp/view/login/infobase/login.jsp";
			} else if (data.resultNum == 0 ) {
				$.messager.alert("提示", "修改密码成功!");
				//返回登录页面
				location.reload();
//				window.location.href = getRootPath() + "/src/main/webapp/view/login/infobase/login.jsp";
			}
		}
	});
}

/**
 * 清空text文本
 */
function calcelUpdatemyPwd() {
	$('#pwdfm').form('clear');
}