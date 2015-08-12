var checkStatus;
var emailValue;
/**
 * 更新选择状态
 */
function changeSatus(){
    if(checkStatus){
    	$("#ug").css("display","block");
    	$("#ag").css("display","none");
    	checkStatus=false;
    }else{
    	$("#ug").css("display","none");
    	$("#ag").css("display","block");
    	checkStatus=true;
    	 $("#pactMessege").text("");
    }
}

/*
 * 实现回车键登入事件
 * @auther honghao
 */
document.onkeydown = function (e) {
	var theEvent = window.event || e;
	var code = theEvent.keyCode || theEvent.which;
	if(code == 13) {
		$("#regestButton").click();
	}
}
/**
 * 刷新验证码
 * @auther honghao
 */
function myRandReload(){
	var codeStr = "/gbdbas/getCheckCode?nocache="+new Date().getTime();
	$("#createcheckcode")[0].src= codeStr;
}
/**
 * 注册
 */

function regest(){
	 emailValue=$("#email").val();

	 firstName = $("#firstName").val();
	 lastName = $("#lastName").val();
	 password = $("#loginPassword").val();
	 confirmPassword = $("#confirmPassword").val();
	 
	 if (emailValue != "" & firstName != "" & lastName != "" & password != "" & confirmPassword != "" & document.getElementById('checkRead').checked & (password == confirmPassword)){
	   $.post("/gbdbas/userRegest", $("#regestForm").serialize(), 
	      function(data){   
	    	  if(data=='1'|| data == '2'){
	    			 openActiveDiv();
	    		 
	    	  }
	    	  else if(data == '3'){
	    		  $("#requiredField").text('*This user exists, please log in.');
	    	  }

	    	  else if (data == '4'){
	    		  $("#requiredField").text('*This user has not been activated, please check your email');
	    	  }


	     }    
	   );
	 }
	 else if(firstName == ""){
		 $("#requiredField").text('*First name is required.');
	 }
	 else if(lastName == ""){
		 $("#requiredField").text('*Last name is required.');
	 }
	 else if(emailValue == ""){
		 $("#requiredField").text('*Email is required.');
	 }
	 else if(password == ""){
		 $("#requiredField").text('*Password is required.');
	 }
	 else if(confirmPassword == ""){
		 $("#requiredField").text('*Please confirm your password.');
	 }
	 else if (password != confirmPassword){
		 $("#requiredField").text('*Passwords do not match.');
	 }
	 else if(!document.getElementById('checkRead').checked){
		 $("#requiredField").text('*Please agree to our Customer Agreement.');
	 }

		  }





/**
 * 清理验证码
 */






function clearMessage(){
	$('#checkCode').val('');
	$('#checkCode').css({
		'width': '85px',
		'margin-right': '10px',
		'height': '24px',
		'border': '1px solid #ddd',
		'line-height': '24px',
		'margin-right': '4px',
		'padding-left': '4px',
		'font-size': '18px',
		'color': '#666'	,
		 'background-color':'white'
	  });
}
/**
 * 打开激活窗口
 */
function openActiveDiv(){
	emailValue=$("#email").val();
	//根据用户输入的email 获取正确的email地址
	var url=getEmailUrl(emailValue);
	//清空页面上的标签地址
	$("#emailId").attr('href',url); 
	$("#emailId").text(emailValue); 
	 openDivArtDialog("激活邮箱", 'activeDiv', 'activeDiv', 400, 400,true); 
}
/**
 * 根据输入的email跳转到要激活邮箱的email
 */
function getEmailUrl(email) {
	if (email) {
		var emails = [ {
			suffix : "qq.com",
			url : "http://mail.qq.com"
		}, {
			suffix : "gmail.com",
			url : "http://mail.google.com"
		}, {
			suffix : "sina.com",
			url : "http://mail.sina.com.cn"
		}, {
			suffix : "163.com",
			url : "http://mail.163.com"
		}, {
			suffix : "126.com",
			url : "http://mail.126.com"
		}, {
			suffix : "yeah.net",
			url : "http://www.yeah.net/"
		}, {
			suffix : "sohu.com",
			url : "http://mail.sohu.com/"
		}, {
			suffix : "tom.com",
			url : "http://mail.tom.com/"
		}, {
			suffix : "sogou.com",
			url : "http://mail.sogou.com/"
		}, {
			suffix : "139.com",
			url : "http://mail.10086.cn/"
		}, {
			suffix : "hotmail.com",
			url : "http://www.hotmail.com"
		}, {
			suffix : "live.com",
			url : "http://login.live.com/"
		}, {
			suffix : "live.cn",
			url : "http://login.live.cn/"
		}, {
			suffix : "live.com.cn",
			url : "http://login.live.com.cn"
		}, {
			suffix : "189.com",
			url : "http://webmail16.189.cn/webmail/"
		}, {
			suffix : "yahoo.com.cn",
			url : "http://mail.cn.yahoo.com/"
		}, {
			suffix : "yahoo.cn",
			url : "http://mail.cn.yahoo.com/"
		}, {
			suffix : "eyou.com",
			url : "http://www.eyou.com/"
		}, {
			suffix : "21cn.com",
			url : "http://mail.21cn.com/"
		}, {
			suffix : "188.com",
			url : "http://www.188.com/"
		}, {
			suffix : "foxmail.com",
			url : "http://www.foxmail.com"
		} ];

		var index = email.indexOf('@');
		var suffix = email.substring(index + 1);
		for ( var i = 0; i < emails.length; i++) {
			if (emails[i].suffix == suffix) {
				return emails[i].url;
			}
		}
		return "http://mail." + suffix;
	}
	return null;
}
/**
* 重新激活邮件
*/
function reActive(){
$.ajax({
	  type:'post',
	  url:'/gbdbas/reSendEmail',
	  dataType:'json',
	  success:function(data){
		  if(data.flag==true){
			  emailValue=data.email;
			  openActiveDiv();
			  //激活按钮设置成不可用
			  $("#reActive").attr("disabled",true);
		  }
		  else{
			  $.messager.alert('提示','服务器正忙请稍后再激活.....','info');
		  }
	  }
});
}
