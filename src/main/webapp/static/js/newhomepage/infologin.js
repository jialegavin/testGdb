//章华才国际化
/**
 * userCenterParam()方法在commontrend.js里头
 */
var usercenterArray=userCenterParam();
	function myRandReload(o){  
	    o.src= "/customer_search/getCheckCode?nocache="+new Date().getTime();  
		}  
	 function checkAccount(){
			var test = /^([A-Za-z0-9 ^\u4e00-\u9fa5]){4,12}$/;
			var txt = $("#user_name").val();
			if(txt.trim() == null||"" == txt.trim()){
				 alert(usercenterArray[164]);
				return false;
			}
			
			return true;
	 }
	 function checkPassword(){
		
			var test =/^([A-Za-z0-9-+=|,!@#$%^&*?_.~+/\\(){}\[\]<>]){6,12}$/;
			var txt = $("#user_pass").val();
			if(txt.trim() == null||"" == txt.trim()){
				alert(usercenterArray[160]);
				return false;
			}
			if(!test.test(txt.trim())){
			alert(usercenterArray[165]);
				return false;
			}
			return true;
		}
	 
	
		String.prototype.trim = function()
		{
		    // 用正则表达式将前后空格
		    // 用空字符串替代。
		    return this.replace(/(^\s*)|(\s*$)/g, "");
		}
		function showBg(e) { 
		    var bh = $("body").height(); 
		    var bw = $("body").width(); 
		    $("#fullbg").css({ 
		        height:bh, 
		        width:bw, 
		        display:"block" 
		    }); 
		    $(e).show(); 
		} 
		//关闭灰色 jQuery 遮罩
		$(document).ready(function(){
			var nameValue = rePassword("userName");
			var passValue = rePassword("userPassword");
			if(nameValue != null){
				document.getElementById("user_name").value = decodeURIComponent(nameValue);
			}
			if(passValue != null && passValue != ""){
				document.getElementById("rPassword").checked = true;
				document.getElementById("user_pass").value = passValue; 
			}else{
				document.getElementById("rPassword").checked = false;
			}
			
//			$(".close").click(function(){
//				$("#fullbg").hide(); 
//				$(this).parent().hide();
//			});
		});
		function rePassword(name){
			var name = name+"=";
			var allcookies = document.cookie;
			var pos = allcookies.indexOf(name);
			if (pos != -1) { // 如果pos值为-1则说明搜索"version="失败
			var start = pos + name.length; // cookie值开始的位置
			var end = allcookies.indexOf(";", start); // 从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置
			if (end == -1)
			end = allcookies.length; // 如果end值为-1说明cookie列表里只有一个cookie
			var value = allcookies.substring(start, end); // 提取cookie的值
			//var pasa = unescape(value);
			return value;
		} else
			return null;
		}