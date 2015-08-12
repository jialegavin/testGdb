/**
 * 刷新验证码
 */
function myRandReload(){
	var codeStr = "/gbdbas/getCheckCode?nocache="+new Date().getTime();
	$("#createcheckcode")[0].src= codeStr;
}

/**
 * 验证账户
 * @author XL
 */
function getVeryfiedCode(){
	//获取登录名称
	var loginValue = $("#loginEmail").val();
	$("#myModal").modal('show');
	 if(loginValue.trim().length==0)
	 {
		
		promptMessage('Please enter your Login Email!')	;
		
		 return false;
	 }
	
	 $.post("/gbdbas/UserPassword/isUserExit",{"loginName":loginValue},
		function(data){
			if(data=="0")
			{
				promptMessage('Login Email does NOT exist, please try another one!')	;
				return;
			}
//			else if(data == "1")
//			{
//				$("#veritycode").addClass('red_cls'); 
//				$("#veritycode_msg").empty();
//				$("#veritycode_msg").append("验证码错误");
//				$("#veritycode_msg").css("display","block");
//			}
			else 
			{
				
				
				sendEmail();
				sendValidate(60);
				$('#timer_id').addClass('disabled');
				 return;
			}
			
	
	 }
		,"text"); 
	}

function sendValidate(num){
	var tt;
	num -=1;
	$("#timer_id").empty();
	$("#timer_id").addClass('gray_cls'); 
	$("#timer_id").append("重新发送("+num+")");
	if(num == 0)
	{
		window.clearTimeout(tt);
		$("#timer_id").empty();
		$("#timer_id").removeClass('gray_cls'); 
		$("#timer_id").append("重新发送");
		$('#timer_id').removeClass('disabled');
		//$("#timer_id").one("click", function(){
		//	$('#timer_id').removeClass('disabled');
			//发送邮件
			//sendEmail();
			//触发定时器，定时器回调函数里再使用one绑定div的点击事件
		//	sendValidate(60);
	//	})
		return;
	}
	//每秒执行一次,showTime()  
   tt=setTimeout('sendValidate('+num+')',1000);
}

/**
 * 发送邮件
 */
function sendEmail(){
	 $.post("/gbdbas/UserPassword/sendEmail",
		function(data){
			if(data == "0")
			{
				promptMessage('Verify code sent failed!')	;
			//	$("#forgot-emailVcode-success").empty();
			//	$("#forgot-emailVcode-success").append("验证码发送失败");
			}
			else if(data == "1")
			{
				promptMessage('Verify code sent successfully!')	;
			//	$("#forgot-emailVcode-success").empty();
			//	$("#forgot-emailVcode-success").append("验证码已发送");
			}
		}
		,"text"); 
}

/**
 * 验证邮箱验证码是否填写成功
 */
function validateEmailCode(){
	//获取发送到邮箱的验证码
	var verityEmailcode = $("#pass-input-emailVcode").val();
	if(verityEmailcode.trim().length==0){
		$("#pass-input-emailVcode").addClass('red_cls'); 
		$("#forgot-emailVcode-tip").css("display","block");
		return false;
	 }else{
		 $("#pass-input-emailVcode").removeClass('red_cls'); 
		 $("#pass-input-emailVcode").removeClass('blue_cls'); 
		 $("#forgot-emailVcode-tip").css("display","none");
		return true;
	 }
}

/**
 * 验证用户输入的密码是否正确
 */
function validateUserPwd()
{
	//获取密码
	var password = $("#password").val();
	//获取确认密码
	var verifypwd = $("#verifypwd").val();
	 if(password.trim().length==0&&verifypwd.trim().length==0)
	 {
		 $("#password").addClass('red_cls'); 
		 $("#pwd-msg").css("display","block");
		 $("#verifypwd").addClass('red_cls'); 
		 $("#verifypwd-msg").css("display","block");
		 return false;
	 }
	 else
	 {
		if(password.trim().length==0){
			$("#password").addClass('red_cls'); 
			$("#pwd-msg").css("display","block");
			return false;
		 }else{
		    if (password.trim().length < 4 || password.trim().length > 12) {
				$("#password").addClass('red_cls'); 
	        	$("#pwd-msg").empty();
				$("#pwd-msg").append("密码长度必须大于4");
				$("#pwd-msg").css("display","block");
				return false;
	        } else {
	        	var test = /^([A-Za-z0-9-+=|,!@#$%^&*?_.~+/\\(){}\[\]<>]){6,12}$/;
	            if (!test.test(password.trim())) {
	            	$("#password").addClass('red_cls'); 
	            	$("#pwd-msg").empty();
	    			$("#pwd-msg").append("密码不符合格式");
	    			$("#pwd-msg").css("display","block");
	    			return false;
	            }
	            else
	            {
		   			 $("#password").removeClass('red_cls'); 
					 $("#password").removeClass('blue_cls'); 
					 $("#pwd-msg").css("display","none");
	            }
	        }
		 }
		 if(verifypwd.trim().length==0){
				$("#verifypwd").addClass('red_cls'); 
				$("#verifypwd-msg").css("display","block");
				return false;
		 }else{
				 $("#verifypwd").removeClass('red_cls'); 
				 $("#verifypwd").removeClass('blue_cls'); 
				 $("#verifypwd-msg").css("display","none");
		 }
		 if(password.trim().length!=0&&verifypwd.trim().length!=0)
		 {
			 if(password.trim()==verifypwd.trim()){
				 $("#verifypwd").removeClass('red_cls'); 
				 $("#verifypwd").removeClass('blue_cls'); 
				 $("#verifypwd-msg").css("display","none");
				 return true;
			 }
			 else
			{
				$("#verifypwd").addClass('red_cls'); 
				$("#verifypwd-msg").empty();
				$("#verifypwd-msg").append("您输入的密码与确认密码不一致");
				$("#verifypwd-msg").css("display","block");
				return false;
			}
		 }
	 }
}
function subEmail(){
	var loginValue = $("#loginEmail").val();
	$("#myModal").modal('show');
	if(loginValue.trim().length==0){
//	alert('<fmt:message key="loss.message12" bundle="${messages}"/>');
	promptMessage('Email address cannot be empty. Please type in your email for account!')	
	return;
	 }
	$.post("${pageContext.request.contextPath }/isUserExit",
		{loginName : loginValue},
		function(flag){
			if(flag=="0"){
			//	alert('<fmt:message key="loss.message8" bundle="${messages}"/>');
				promptMessage('<fmt:message key="loss.message8" bundle="${messages}"/>')	
				return;
			}else if(flag=="1"){
				//用户存在
				
			
			$("#show").empty();
			$.post("${pageContext.request.contextPath }/sendEmail",
					{loginName : loginValue},
					function(data){
						if(data == 1){
					//	alert('<fmt:message key="loss.message10" bundle="${messages}"/>');
						promptMessage('<fmt:message key="loss.message10" bundle="${messages}"/>')		
						}else if(data==2){
					//	alert('<fmt:message key="loss.message11" bundle="${messages}"/>');
						promptMessage('<fmt:message key="loss.message11" bundle="${messages}"/>')		
						 }else if(data==3){
					//	 alert('<fmt:message key="loss.message12" bundle="${messages}"/>');
						promptMessage('<fmt:message key="loss.message12" bundle="${messages}"/>')		
						 }else if(data==4){
					//	 alert('<fmt:message key="loss.message13" bundle="${messages}"/>');
						promptMessage('<fmt:message key="loss.message13" bundle="${messages}"/>')		
						}

					},"json");
			}
		},"text");
}



