function promptMessage(m){
		$("#myModalLabel").text('Prompt');
		document.getElementById("warn").style.display="block";
		document.getElementById("prog").style.display="none";
		document.getElementById("clossBtn").style.display="block";
		$("#warn").text(m);
	}
	function clearPrompt(){
		document.getElementById("warn").style.display="none";
		document.getElementById("prog").style.display="block";
		document.getElementById("clossBtn").style.display="none";
		$("#myModalLabel").text('Loading');
		$("#warn").text('');
	}
		  function usersub(){
// 		   var $btnmy=document.getElementById("signIn");
// 		   $btnmy.value="loading..."
		   //$(this).value="loading";
		
   		 // business logic...
   		 if(loginSubmit()){
     
			var loginName = $("#user_name").val();
			var loginPassword = $("#user_pass").val();
			var regCode = $("#checkcode").val();
			var check  = document.getElementById("rPassword");
			var rPassword;
			if(check.checked){
				rPassword = check.value;
			}else{
				rPassword = "";
			}
			$("#myModal").modal('show');
			$.post("${ctx}/userLogin",{loginName:loginName,loginPassword :loginPassword,regCode:regCode,rPassword:rPassword,language:'chinese'},
							function(data){
							if("2" == data){
								 promptMessage('<fmt:message key="common.passiscorr" bundle="${messages}"/>')	;							
							}
							else if("3" == data){
								promptMessage('<fmt:message key="common.Usernotexist" bundle="${messages}"/>')	;								
							}else if("4"  == data){
								promptMessage('The identity code is not correct!');	
							}else if("5"  == data){
								promptMessage('<fmt:message key="common.usenotplacelogin" bundle="${messages}"/>')	;
								
							}else if("1"==data){
								clearPrompt();
								location.href = "${ctx}/goToIndex";
							}else if("6"==data){
								promptMessage('<fmt:message key="common.addisnotlogin" bundle="${messages}"/>')	;
								
							}else if("7"==data){
								promptMessage('<fmt:message key="common.hasbeendisabled" bundle="${messages}"/>')	;
								
							}else if("8"==data){
								promptMessage('<fmt:message key="common.isnotactive" bundle="${messages}"/>');	
							}else{
								promptMessage('<fmt:message key="common.istemlocke" bundle="${messages}"/>');	
							}
						}
					);
		}
   			 
	
		
	
	}
		function subEmail(){
			var loginValue = $("#loginName").val();
			$("#myModal").modal('show');
			if(loginValue.trim().length==0){
		//	alert('<fmt:message key="loss.message12" bundle="${messages}"/>');
			promptMessage('<fmt:message key="common.istemlocke" bundle="${messages}"/>')	
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
						
					$("#autoInfo").css("background-image","url(${pageContext.request.contextPath }/static/img/button/infoSend.png)");
					$("#emailState").html("<fmt:message key="loss.message9" bundle="${messages}"></fmt:message>");
					$("#emailState").css("left",43);
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
								 $("#autoInfo").css("background-image","url(${pageContext.request.contextPath }/static/img/button/infoAuto.png)");
									 $("#emailState").css("left",57);
									 $("#emailState").html("<fmt:message key="loss.ok" bundle="${messages}"></fmt:message>");
							},"json");
					}
				},"text");
		}
		
		function loginSubmit(){	
			 if(checkAccount()&&checkPassword()){
				 $("#loginWait").empty();
				 
				 return true;
			 }
			 return false;
		}
		

		
		document.onkeydown = function (e) {
		var theEvent = window.event || e;
		var code = theEvent.keyCode || theEvent.which;
		if (code == 13) {
		$("#login").click();
		}
		}