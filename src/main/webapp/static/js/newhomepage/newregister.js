//章华才国际化

/**
 * userCenterParam()方法在commontrend.js里头
 * it likely in the promptmessage.js
 */
var usercenterArray=userCenterParam();
var check=0;
var _time = 75;

function timedown(t){
        if(_time == 0 ){
         t.removeAttribute("disabled");
         t.value="Send mail again...";
         _time = 75;
       }else {
      t.setAttribute("disabled", true);
      if(_time==74){
    	  $.ajax({
			    dataType:"json",
			    async:false,
			    url:'/customer_search/sendActivated',
			    success:function(data){
						 if(data== "1"){
							 hide("sendgif");
							 show("sendsuccess");
							}
			    }
		});
      }
      t.value=""+_time+"later resend";
      _time--;
     setTimeout(function(){
        timedown(t)
        },1000);
     }
}

//显示重发按钮
function displaytimedown(){
	$.ajax({
	    dataType:"json",
	    async:false,
	    url:'/customer_search/checkIsActivated',
	    success:function(data){
		if(data=="1"){
		  show("againActivedMail");
		 }
	    }
});
}
//隐藏和显示切�?
function show(v){
	document.getElementById(v).style.display="block";
}
function hide(v){
	document.getElementById(v).style.display="none";
}
//显示弹出�?
function displaymail(){
	openDivArtDialog('Sending Mail�?', 'Sending the activation mail, please wait...', 'displayMail', 390,168, true);
}
function changeAgree(o){
	if(o.checked){
		document.getElementById("submintButton").disabled=null;
	}else{
		document.getElementById("submintButton").disabled="true";
	}
}
String.prototype.trim = function()
{
    // 用正则表达式将前后空�?
    // 用空字符串替代�?
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
function fouceAccount(){
	$("#J_UserNameTip").hide();
	if(checkRegisterAccount()==false){
	//  $("#userNameMessage").empty();
	//	$("#userNameMessage").append("账号必须由英文字母、数�?0-9)、汉字组成，长度�?-12个字符之间�?);
	}
}



function checkRegisterAccountFormat(){
	var test = /^([A-Za-z0-9 ^\u4e00-\u9fa5]){4,12}$/;
	var txt = $("#inputUserName").val();
	if(!test.test(txt.trim())){
		
		//	$("#userNameMessage").empty();
			//$("#userNameMessage").append("<img class=\"imgTextTop\" src=\"/customer_search/static/img/no.png\"/>&nbsp;&nbsp;"+usercenterArray[42]);
			//alert(usercenterArray[42]);
			alert('The format of username is not correct!');
			return false;
	}else return true;
}

//验证用户名是否重�?
function checkRegisterAccount(){
	
	
	var txt = $("#inputUserName").val();
	$.ajaxSetup({ 
		  async: false 
		  });
	
		$.post("/customer_search/ajaxCheckLoginName",{loginName:txt},
				
				function(data){
			
					if(data>0){
					//	$("#userNameMessage").empty();
					//	$("#userNameMessage").append("<img class=\"imgTextTop\" src=\"/customer_search/static/img/no.png\"/>&nbsp;&nbsp;"+usercenterArray[43]);
					//	alert(usercenterArray[43]);
						alert('The username has been existed. Please change another one!');
						return false;
					}else {	
						return true;					
					//	$("#userNameMessage").empty();
					//	$("#userNameMessage").append("<img class=\"imgTextTop\" src=\"/customer_search/static/img/ok.png\"/>");
						
					}
					
		});
	
	return true;
	

}
//验证邮箱是否重复
function checkEmail(){
	var test = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var txt = $("#inputUserName").val();
//	$("#emailMessage").empty();
	if(!test.test(txt.trim())){
	//	$("#emailMessage").append("<img class=\"imgTextTop\" src=\"/customer_search/static/img/no.png\"/>&nbsp;&nbsp;"+usercenterArray[46]);
		//alert(usercenterArray[46]);
		alert('The format of email is not correct!');
		return false;
	}
	return true;
}
function foucsPassword(){
	if(checkRegisterPassword()==false){3
		$("#J_PwdTip").show();
		//$("#passMessage").empty();
		//$("#passMessage").append("由字母、数�?_ ! ? - = + @组成，长度为6�?2�?);
	}
}
function checkRegisterPassword(){
	$('#J_PwdTip').hide();
	var test = /^([A-Za-z0-9-+=|,!@#$%^&*?_.~+/\\(){}\[\]<>]){6,12}$/;
	var txt = $("#inputPassword").val();
	if(!test.test(txt.trim())){
	//	$("#passMessage").empty();
	//	$("#passMessage").append("<img class=\"imgTextTop\" src=\"/customer_search/static/img/no.png\"/>&nbsp;&nbsp;"+usercenterArray[45]);
		//alert(usercenterArray[45]);
		alert('The format of password is not correct! The length of the password must be 6-12 digits combining with characters and numbers!');
		return false;
	}else{
	//	$("#passMessage").empty();
	//	$("#passMessage").append("<img class=\"imgTextTop\" src=\"/customer_search/static/img/ok.png\"/>");
		
		return true;
	}
}

function checkrepassword(){
	var txt = $("#inputPassword").val();
	var txt1 = $("#reinputPassword").val();

	if(txt != txt1){
	//	$("#rePassMessage").empty();
	//	$("#rePassMessage").append("<img class=\"imgTextTop\" src=\"/customer_search/static/img/no.png\"/>&nbsp;&nbsp;"+usercenterArray[163]);
		//alert(usercenterArray[163]);
		alert('The confirmed password is different from password. Please check again!');
		return false;
	}else{
	//	$("#rePassMessage").empty();
	//	$("#rePassMessage").append("<img class=\"imgTextTop\" src=\"/customer_search/static/img/ok.png\"/>");
		return true;
	}
}
function getPasswordStrengthVerdict()	{
	var passwd = $("#inputPassword").val();
	if(passwd.length==0){
		$("div.pw-strength-bar").removeClass("pw-strength-bar-1");
		$("div.pw-strength-bar").removeClass("pw-strength-bar-2");
		$("div.pw-strength-bar").removeClass("pw-strength-bar-3");
		return;
	}
	var test = /^([a-z A-Z])+$/;
	var test1 = /^[0-9]+$/;
	var test2 =  /^[-+=|,!@#$%^&*?_.~+/\\(){}\[\]<>]+$/;
	var tchar = passwd.match(/[_\!\?\-\=\+\@]/);
	var num = passwd.match(/\d/);
	var char = passwd.match(/[a-z A-Z]/);
	
	if(test.test(passwd)||test1.test(passwd)||test2.test(passwd
			||passwd.length<6)){
		$("div.pw-strength-bar").removeClass("pw-strength-bar-2");
		$("div.pw-strength-bar").removeClass("pw-strength-bar-3");
		if(!($("div.pw-strength-bar").hasClass('pw-strength-bar-1'))){
		$("div.pw-strength-bar").addClass("pw-strength-bar-1");
		}
	}
	else if ((passwd.length>=6&&tchar!=null&&num!=null&&char==null)
			||(passwd.length>=6&&tchar!=null&&char!=null&&num==null)
			||(passwd.length>=6&&char!=null&&num!=null&&tchar==null)
			)
	{
		$("div.pw-strength-bar").removeClass("pw-strength-bar-1");
		$("div.pw-strength-bar").removeClass("pw-strength-bar-3");
		if(!($("div.pw-strength-bar").hasClass('pw-strength-bar-1'))){
			$("div.pw-strength-bar").addClass("pw-strength-bar-2");
		}
	}
	else if(passwd.length>=6&&tchar!=null&&num!=null&&char!=null)
	{
		$("div.pw-strength-bar").removeClass("pw-strength-bar-1");
		$("div.pw-strength-bar").removeClass("pw-strength-bar-2");
		if(!($("div.pw-strength-bar").hasClass('pw-strength-bar-3'))){
		$("div.pw-strength-bar").addClass("pw-strength-bar-3");
		}
	}
}
function userSubmit(){
	$("#myModal").modal('show');
	if(checkRegisterPassword()&&checkrepassword()&&checkEmail()){

	return true;
		
		

	}else 	return false;
	
}
function checkregCode(){
	var regCode = $("#regCode").val();
	var bol = true;
	if(regCode.trim() == null || regCode.trim() == ""){
		$("#regCodeMsg").empty();
		$("#regCodeMsg").append("<img class=\"imgTextTop\" src=\"/customer_search/static/img/no.png\"/>&nbsp;&nbsp;"+usercenterArray[161]);
		return false;
	}else{
		 $.ajax({
			    dataType:"json",
			    async:false,
			    url:'/customer_search/getRandCodeStr',
			    success:function(msg){
						 if(regCode.trim() != msg){
								$("#").val("");
								$("#regCodeMsg").empty();
								$("#regCodeMsg").append("<img class=\"imgTextTop\" src=\"/customer_search/static/img/no.png\"/>&nbsp;&nbsp;"+usercenterArray[162]);
								myRandReload();
								bol = false;
							}else{
								$("#regCodeMsg").empty();
							}
			    }
		});
	}
	return bol;
}