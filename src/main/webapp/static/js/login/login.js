/*
 * js页面初始化方法入口
 * @auther honghao
 */
$(document).ready(function()
{
	//刷新验证码
	myRandReload();
	var userName=$("#userName").text();
	if(userName!="null"){
		$("#user_name").val(userName);
		$("[name='rPassword']").attr("checked",'true');
	}
});

/*
 * 实现回车键登入事件
 * @auther honghao
 */
document.onkeydown = function (e) {
	var theEvent = window.event || e;
	var code = theEvent.keyCode || theEvent.which;
	if(code == 13) {
		$("#loginInput").click();
	}
}

/*
 * 检查浏览器
 * @auther honghao
 */
function checkBrower(){
	openDivArtDialog("浏览器检测结果提示", 'dlgNew', 'dlgNew', 390,168, true);
}

function is360 () {
    if (window.external && window.external.twGetRunPath) {
        var r = external.twGetRunPath();
        return  (r && r.toLowerCase().indexOf("360") > -1);
    }
    return false;
}

/*
 * 根据用户当前系统的详细信息 下载对应的IE浏览器版本
 * @auther honghao
 */
function downLoadIeBrower(){
	var info=checkSystemWeiShu();
	if(info=="64位"){
			window.location.href="http://download.microsoft.com/download/5/6/F/56FD6253-CB53-4E38-94C6-74367DA2AB34/IE11-Windows6.1-x64-zh-cn.exe";
			art.dialog({id:'dlgNew'}).close();
	}
	if(info=="32位"){
		window.location.href="http://download.microsoft.com/download/F/2/8/F2871AC4-E82B-4636-BB37-A5F2B14C8616/IE11-Windows6.1-x86-zh-cn.exe";
		art.dialog({id:'dlgNew'}).close();
	}
 }
 
/*
 * 检查操作系统位数 64位还是32位
 * @auther honghao
 */
function checkSystemWeiShu(){
    var cname=checkSystemName();
	var _bit;
	var _pf = navigator.platform;
	var appVer = navigator.userAgent;

	if(_pf == "Win32" || _pf == "Windows")
	{
		if(appVer.indexOf("WOW64")>-1){
		return "64位";
		}
		else{
		return "32位";
		}
		
	}
}

/*
 * 检查用户操作系统名称
 * @auther honghao
 */
function checkSystemName(){
	var appVer = navigator.userAgent;
	alert("app:"+appVer);
		if(appVer.indexOf("Windows NT 6.0") > -1 || appVer.indexOf("Windows Vista") > -1)
			{
				if(appVer.indexOf("Windows Vista") > -1){
					return  'Windows_vista';
				}else{
					return "Unknow1";
				}
			}
		else if(appVer.indexOf("Windows NT 6.1") > -1 || appVer.indexOf("Windows 7") > -1) {
			if(appVer.indexOf("Windows NT 6.1") > -1){
				return  'Windows 7';
			}else{
				return "Unknow";
			}
		}
}

/*
 * 切换页面语言
 * @param v 选择的语言种类
 * @auther honghao
 */
function onChangeLanguage(v)
{
 	$.ajax({
		type:'post',
        url : "/gbdbas/language/changelanguage?language="+v.value,
     	success : function(data)
     	{
            window.location.reload();
	    }
    });
}

/*
 * 忘记密码鼠标移除事件
 * @auther honghao
 */
function out(){
	$("#wjmm").css("color","#1b66c7");
}

/*
 * 忘记密码鼠标悬浮事件
 * @auther honghao
 */
function over(){
	$("#wjmm").css("color","red");
}

/*
 * 忘记密码提交表单
 * @auther honghao
 */
function forgetPass(){
	//跳转到找回密码页面
	window.open('/gbdbas/view/login/findpassword/findpwd.jsp')
}

/*
 * 免费试	用登录
 * @param v 登录用户名
 * @auther honghao
 */
function testLogin(v){
	$.post("/gbdbas/userLogin",{loginName:v},
		function(data){
			//登录成功
			if("1"==data)
			{
				location.href = "${ctx}/goToIndex";
			}
			//密码不正确！
			else if("2" == data)
			{
    			$.messager.alert('提示','密码输入不正确!','info');
			}
			//用户名不存在！
			else if("3" == data)
			{
				$.messager.alert('提示','用户名不存在！','info');
			}
			//同一个账号同时登陆的地方不超过3个
			else if("5"  == data)
			{
				$.messager.alert('提示','对不起,同一个账号同时登陆的地方不超过3个,请您采用其他账号登录！请联系<a class="kefuMenu1" href="http://wpa.qq.com/msgrd?v=3&uin=1665953128&site=qq&menu=yes" target="_blank">在线客服</a>','info');
			}
			//对不起!您的账户已经被锁定!
			else if("6"==data)
			{
				$.messager.alert('提示','对不起，您的账户暂时被锁定,请您稍后登录!','info');
			}
			//对不起!您的账户没有激活 ！
			else if("7"==data)
			{
				$.messager.alert('提示','对不起!您的账户没有激活 ！','info');
			}
			//对不起!您的账户已被禁用!
			else if("8"==data)
			{
				$.messager.alert('提示','对不起!您的账户已被禁用!请联系<a class="kefuMenu1" href="http://wpa.qq.com/msgrd?v=3&uin=1665953128&site=qq&menu=yes" target="_blank">在线客服</a>','info');
			}
		}
	);
}

/*
 * 刷新验证码
 * @auther honghao
 */
function myRandReload(){
	var codeStr = "/gbdbas/getCheckCode?nocache="+new Date().getTime();
	$("#createcheckcode")[0].src= codeStr;
}

/*
 * 登录总方法
 * @auther honghao
 */
function usersub(){
	if(loginSubmit()){
		//用户名
		var loginName = $("#user_name").val();
		//密码
		var loginPassword = $("#user_pass").val();
		//检验码
		var regCode = $("#checkcode").val();
		//下次自动登录
		var check  = document.getElementById("rPassword");
		var rPassword;
		if(check.checked)
		{
			rPassword = check.value;
		}else
		{
			rPassword = "";
		}
		
		$.post("/gbdbas/userLogin",{loginName:loginName,loginPassword :loginPassword,regCode:regCode,rPassword:rPassword},
			function(data){
				//登录成功
				if(data == "1")
				{
					location.href = "/gbdbas/pageJump";
				}
				//密码不正确！
				else if(data == "2")
				{
	    			$.messager.alert('提示','密码输入不正确！','info');
	    			$("#user_pass").val("");
	    			myRandReload();
				}
				//用户名不存在！
				else if(data == "3")
				{
					$.messager.alert('提示','用户名不存在！','info');
					$("#user_pass").val("");
					myRandReload();
				}
				//验证码不正确！
				else if(data == "4")
				{
					$.messager.alert('提示','验证码不正确！','info');
					myRandReload();
				}
				//该用户已经在其他地方登录
				else if(data == "5")
				{
					$.messager.alert('提示','对不起,同一个账号同时登陆的地方不超过3个,请您采用其他账号登录！请联系<a class="kefuMenu1" href="http://wpa.qq.com/msgrd?v=3&uin=1665953128&site=qq&menu=yes" target="_blank">在线客服</a>','info');
					myRandReload();
				}
				//对不起!您的账户已被禁用!
				else if(data == "6")
				{
					$.messager.alert('提示','对不起，您密码输入错误次数超过5次暂时被锁定，请您于您的账号暂时已经被锁定请您于15分钟后登录','info');
					myRandReload();
				}
				//对不起!您的账户没有激活 ！
				else if(data == "7")
				{
					$.messager.alert('提示','对不起!您的账户没有激活 ！','info');
					myRandReload();
				}
				//对不起!您的账户已被禁用!
				else if(data == "8")
				{
					$.messager.alert('提示','对不起!您的账户已被禁用!请联系<a class="kefuMenu1" href="http://wpa.qq.com/msgrd?v=3&uin=1665953128&site=qq&menu=yes" target="_blank">在线客服</a>','info');
				}
			}
		);
	}
}

/*
 * 集中校验用户名,密码,验证码
 * @auther honghao
 */
function loginSubmit(){
	 if(checkAccount()&&checkPassword()&&checkCode())
	 {
		 return true;
	 }
	 return false;
}

/*
 * 校验验证码输入是否正确
 * @auther honghao
 */
function checkCode(){
	var txt = $("#checkcode").val();
	if(txt.trim() == null || "" == txt.trim())
	{
		$.messager.alert('提示','验证码不能为空','info');
		return false;
	}
	return true;
}

/*
 * 校验用户名输入是否正确
 * @auther honghao
 */
function checkAccount(){
	//包含大小写字母数字汉字,长度为4到12位的正则校验规则
	var test = /^([A-Za-z0-9 ^\u4e00-\u9fa5]){4,12}$/;
	var txt = $("#user_name").val();
	if(txt.trim() == null || "" == txt.trim()){
		$.messager.alert('提示','用户名不能够为空','info');
		return false;
	}
	if(!test.test(txt.trim())){
		 $.messager.alert('提示','账号不正确!','info');
		return false;
	}
	return true;
}

/*
 * 校验用户登录密码
 * @auther honghao
 */
function checkPassword(){
	//包含大小写字母数字特殊字符,长度为6到12位的正则校验规则
	var test =/^([A-Za-z0-9-+=|,!@#$%^&*?_.~+/\\(){}\[\]<>]){6,12}$/;
	var txt = $("#user_pass").val();
	if(txt.trim() == null || "" == txt.trim()){
		$.messager.alert('提示','确认密码不能够为空','info');
		return false;
	}
	if(!test.test(txt.trim())){
		$.messager.alert('提示','密码格式不正确','info');
		return false;
	}
	return true;
}