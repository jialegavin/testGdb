//清空form表单
function clearForm(){            
	document.getElementById('userInfoForm').reset();  
}
//验证表单
function checkSubmit(){
	if(isCheckPhone()&&isCheckTel()&&isCheckEmail()&&
			isCheckLoginName()&&isCheckPwd()&&isCheckAccountDate()&&
			isCheckMoney()&&isCheckDoMain()){
		return true;
	}else{
		return false;
	}
}
//提交form
function submitForm(){
	if(checkSubmit()){
		var item =$(':radio[name="RadioGroup1"]:checked').val();
		if(item == 'a'){
			$("#addUserRightType").val("a");
		} else {
			$("#addUserRightType").val("b");
		}
	 	$('#userInfoForm').form('submit', {   
		      success:function(data){   
 		    	 var json=JSON.parse(data);
		    	  if(json.userIsExist){
			    	  if(json.flag){ 
							$("#showLoginName").val(json.loginName);
							$("#buyMoney").val(json.buyMoney);
							document.getElementById("addRightUserName").innerHTML = json.loginName;
							$("#showRight").show();
 				      }else{
				  			$.messager.alert('提示','新增用户记录失败！','info');
					  }
		    	  }else{
		    			$.messager.alert('提示','新增用户失败！','info');
			      }
		     }   
		});   
	}
}  

/**
 * 显示授权方式
 */
function showRightInfo(){
	$("#countRightInfo").hide();
 	document.getElementById('rightInfoForm').reset();  
	var auth_type = $(':radio[name="RadioGroup2"]:checked').val();
	$.post("/gbdbas/userManager/showCountryInfo",{"auth_type":auth_type}); 
	$("#showRight_div").load("showRightInfo.jsp #RightInfo");
}

//全选
allselect=function(){
	var r=document.getElementsByName("checkid");  
    for(var i=0;i<r.length;i++){
    	r[i].checked=true;
    }      
}
//全不选
allNot=function(){
	var r=document.getElementsByName("checkid");  
    for(var i=0;i<r.length;i++){
    	r[i].checked=false;
    }      
}

//验证电话号码
function isCheckPhone(){
	var item =$(':radio[name="RadioGroup1"]:checked').val();
	if(item == 'a'){
		var mobile = $("#phone").val();
	    if(mobile.length!=0) 
	    { 
	   	      var myreg = /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/; 
	    	  if(mobile.length!=11) 
	    	  { 
	    		$.messager.alert('提示','请输入有效的手机号码！','info');
	   	        return false; 
	    	  } else if (!myreg.test(mobile)) 
	   	      { 
	   	    	$.messager.alert('提示','请输入有效的手机号码！','info');
	   	        return false; 
	   	      } else {
				return true;
		   	  }
	    }else{
			return true;
		}   
	}else{
		return true;
	}
}

//验证手机号
function isCheckTel(){
	var item =$(':radio[name="RadioGroup1"]:checked').val();
	if(item == 'a'){
		var tel = $("#tel").val();
	    if(tel.length!=0) 
	    { 
	   	      var myreg = /^([0-9]{3,4}-)?[0-9]{7,8}$/; 
	   	      if(!myreg.test(tel)) 
	   	      { 
	   	    	$.messager.alert('提示','请输入有效的座机号码！','info');
	   	        return false; 
	   	      }else{
	   	    	return true;
	   	      }
	    }else{
	    	return true;
		}     
	}else{
		return true;
	}
}

//验证email
function isCheckEmail(){
	var item =$(':radio[name="RadioGroup1"]:checked').val();
	if(item == 'a'){
		var email = $("#email").val();
		if(email == ''){
			$.messager.alert('提示','请输入电子邮箱！','info');
		    return false; 
		} else {
			 var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/; 
			 if(!myreg.test(email)) 
	  	      { 
	  	    	$.messager.alert('提示','请输入有效的电子邮箱！','info');
	  	        return false; 
	  	      } else {
	  	    	return true;
		  	  }
		}
	}else{
		return true;
	}
}

//验证用户名
function isCheckLoginName(){
	var item =$(':radio[name="RadioGroup1"]:checked').val();
	var loginName = $("#loginName").val();
	if(loginName == ''){
		$.messager.alert('提示','请输入用户名！','info');
	    return false; 
	}else{
		var flag=$.ajax({url:"/gbdbas/userSon/checkUserSonName",dataType:"json",data:{loginName:loginName},async:false,cache:false,type:"post"}).responseText;
		if(item == 'a'){
   	     	if(flag=="true"){
	   	     	if ((loginName.length < 4)|| (loginName.length >12)) {
	        		$.messager.alert('提示','账号长度在4-12个字符之间！','info');
	                return false;
	           } else {
	                if (!/^[\w]+$/.test(loginName)) {
						$.messager.alert('提示','账号必须由英文字母、数字(0-9)、汉字组成!','info');
	                    return false;
	                }else{
	                    return true;
	                }
	          }
   	   	    }else{
	   	   		$.messager.alert('提示','用户名已存在！','info');
				return false;
   	   	   	}
		}else{
			if(flag=="true"){
				$.messager.alert('提示','用户名不存在！','info');
				return false;
			}else{
				return true;
			}
		}
	}
}

//验证账户有效日期
function isCheckAccountDate(){
	var item =$(':radio[name="RadioGroup1"]:checked').val();
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	if(beginTime == '' && endTime == ''){
		$.messager.alert('提示','请输入账户有效期！','info');
        return false;
	}else{
		if(beginTime == '' && endTime != ''){
			$.messager.alert('提示','请输入账户有效期开始日期！','info');
	        return false;
		}else if(beginTime != '' && endTime == ''){
			$.messager.alert('提示','请输入账户有效期截止日期！','info');
	        return false;
		}else{
			var startDate = new Date(beginTime);
			var endDate = new Date(endTime);
			if (startDate > endDate) {
				$.messager.alert('提示', '第二段日期不能小于第一段日期','info');
				return false;
			}else{
				return true;
			}
		}
	}
}

//验证金额
function isCheckMoney(){
	var item =$(':radio[name="RadioGroup1"]:checked').val();
	var totalmoney = $("#totalmoney").val();
	if(totalmoney == ''){
		$.messager.alert('提示','请输入购买金额！','info');
        return false;
	}else{
		 if (!/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/.test(totalmoney)) {
			 $.messager.alert('提示','请输入有效的金额!','info');
             return false;
         } else {
        	 return true;
         }
	}
}

//验证域名
function isCheckDoMain(){
	var item =$(':radio[name="RadioGroup1"]:checked').val();
	var domain = $("#domain").val();
	if(domain == ''){
		$.messager.alert('提示','请输入域名！','info');
        return false;
	}else{
		return true;
	}
}

//验证密码
function isCheckPwd(){
	var item =$(':radio[name="RadioGroup1"]:checked').val();
	if(item == 'a'){
		var loginPassword = $("#loginPassword").val();
		if(loginPassword==''){
			$.messager.alert('提示','请输入密码！','info');
	        return false;
		}else{
			//规则验证
	        if (loginPassword.length < 6 || loginPassword.length > 20) {
	        	$.messager.alert('提示','密码长度必须大于20！','info');
	            return false;
	        } else {
	            if (!/^([A-Za-z0-9-+=|,!@#$%^&?_.~+/\\(){}\[\]<>]){6,12}$/.test(loginPassword)) {
	            	$.messager.alert('提示','密码不符合格式！','info');
	                return false;
	            }else{
	            	return true;
		        }
	        }
		}
	}else{
		return true;
	}
}

//验证授权信息
function checkRightSubmit(){
	var flag = false;
	var isflag = false;
	var isTrue = false;
	var r=document.getElementsByName("checkid");  
    for(var i=1;i<=r.length;i++){
    	if(r[i-1].checked){
    		flag= true;
    		break;
        }
    }
	var item =$(':radio[name="RadioGroup2"]:checked').val();
    if(!flag){
    	$.messager.alert('提示','请选择！','info');
    	return false;
    }else{
    	  for(var i=1;i<=r.length;i++){
    	    	if(r[i-1].checked){
    	    		if(item == 'auth_hscode'){
						var byHsCode = $("#byHsCode"+i).val();
						if(byHsCode == ''){
							$.messager.alert('提示','选中项未填入海关编码！','info');
					    	return false;
						}else{
							var test = /^\d{6,10}(,\d{6,10})*$/;
							if(!test.test(byHsCode)){
								$.messager.alert('提示','您好,请输入海关编码必须是大于6位小于10位的数字!多个海关编码必须以英文逗号分隔！','info');
								return false;
							}else{
								isflag=true;
							}
						}
    	    		} else if(item == 'auth_desc'){
    	    			var byProductDesc = $("#byProductDesc"+i).val();
						if(byProductDesc == ''){
							$.messager.alert('提示','选中项未填入产品描述！','info');
					    	return false;
						}else{
							isflag=true;	
						}
        	    	} else{
        	    		isflag=true;
            	    }
        	    	if(isflag){
						var beginTime = $("#startTime"+i).val();
						var endTime = $("#endTime"+i).val();
						if(beginTime == '' && endTime == ''){
							$.messager.alert('提示','请输入数据检索日期！','info');
					        return false;
						}else{
							if(beginTime == '' && endTime != ''){
								$.messager.alert('提示','请输入数据检索日期开始日期！','info');
						        return false;
							}else if(beginTime != '' && endTime == ''){
								$.messager.alert('提示','请输入数据检索日期截止日期！','info');
						        return false;
							}else{
								var startDate = new Date(beginTime);
								var endDate = new Date(endTime);
								if (startDate > endDate) {
									$.messager.alert('提示', '第二段日期不能小于第一段日期','info');
									return false;
								}else{
									var isRight = false;
									var byCountry = $("#byCountry"+i).val();
									var iExportType = $("#iExportType"+i).val();
									if(byCountry == '中国'){
										if(iExportType == '进口'){
											openHistoryData = $(':radio[name="openHistoryData_import"]:checked').val();
										} else {
											openHistoryData = $(':radio[name="openHistoryData_export"]:checked').val();
										}
										if(openHistoryData == 'true'){
											var historyStartTime = $("#historyStartTime"+i).val();
											var historyEndTime = $("#historyEndTime"+i).val();
											if(historyStartTime == '' && historyEndTime == ''){
												$.messager.alert('提示','请输入历史数据检索日期！','info');
										        return false;
											}else{
												if(historyStartTime == '' && historyEndTime != ''){
													$.messager.alert('提示','请输入历史数据检索日期开始日期！','info');
											        return false;
												}else if(historyStartTime != '' && historyEndTime == ''){
													$.messager.alert('提示','请输入历史数据检索日期截止日期！','info');
											        return false;
												}else{
													var startDate = new Date(historyStartTime);
													var endDate = new Date(historyEndTime);
													if (startDate > endDate) {
														$.messager.alert('提示', '历史数据检索日期第二段日期不能小于第一段日期','info');
														return false;
													}else{
														isRight= true;
													}
												}
											}
										}else{
											isRight= true;
										}
										if(isRight){
											var treeStartTime = $("#treeStartTime"+i).val();
											var treeEndTime = $("#treeEndTime"+i).val();
											if(treeStartTime == '' && treeEndTime == ''){
												isTrue= true;
											}else{
												if(treeStartTime == '' && treeEndTime != ''){
													$.messager.alert('提示','请输入赠送国外检索日期开始日期！','info');
											        return false;
												}else if(treeStartTime != '' && treeEndTime == ''){
													$.messager.alert('提示','请输入赠送国外检索日期截止日期！','info');
											        return false;
												}else{
													var startDate = new Date(treeStartTime);
													var endDate = new Date(treeEndTime);
													if (startDate > endDate) {
														$.messager.alert('提示', '赠送国外检索日期第二段日期不能小于第一段日期','info');
														return false;
													}else{
														isTrue= true;
													}
												}
											}
											
										}
									}else{
										isTrue= true;
									}
								}
							}
						}
            	   	}
    	        }
    	  }
    }
    if(isTrue){
        return true;
    }
}
//提交权限
function submitRightForm(){
	if(checkRightSubmit()){
	  var r=document.getElementsByName("checkid");
	  var item =$(':radio[name="RadioGroup2"]:checked').val();
	  var jsonStr="[";
  	  for(var i=1;i<=r.length;i++){
	    	if(r[i-1].checked){
	    		var byCountry = $("#byCountry"+i).val();
	    		var iExportType = $("#iExportType"+i).val();
	    		jsonStr += "{";
	    		jsonStr += '"byCountry":"'+ byCountry+'",';
	    		jsonStr += '"iExportType":"'+ iExportType+'",';
	    		var byHsCode;
	    		var byProductDesc;
	    		if(item == 'auth_hscode'){
					byHsCode = $("#byHsCode"+i).val();
					jsonStr += '"byHsCode":"'+ byHsCode+'",';
	    		} else if(item == 'auth_desc'){
	    			byProductDesc = $("#byProductDesc"+i).val();
	    			jsonStr += '"byProductDesc":"'+ byProductDesc+'",';
	    		}
	    		var startTime = $("#startTime"+i).val();
				var endTime = $("#endTime"+i).val();
				var openHistoryData;
				if(byCountry == '中国'){
					if(iExportType == '进口'){
						openHistoryData = $(':radio[name="openHistoryData_import"]:checked').val();
						jsonStr += '"openHistoryData":"'+ openHistoryData+'",';
					} else {
						openHistoryData = $(':radio[name="openHistoryData_export"]:checked').val();
						jsonStr += '"openHistoryData":"'+ openHistoryData+'",';
					}
					if(openHistoryData == 'true'){
						var historyStartTime = $("#historyStartTime"+i).val();
						var historyEndTime = $("#historyEndTime"+i).val();
						jsonStr += '"historyStartTime":"'+ historyStartTime+'",';
						jsonStr += '"historyEndTime":"'+ historyEndTime+'",';
					}
					var treeStartTime = $("#treeStartTime"+i).val();
					var treeEndTime = $("#treeEndTime"+i).val();
					jsonStr += '"treeStartTime":"'+ treeStartTime+'",';
					jsonStr += '"treeEndTime":"'+ treeEndTime+'",';
				}
	    		jsonStr += '"startTime":"'+ startTime+'",';
				jsonStr += '"endTime":"'+ endTime+'"';
				jsonStr += "}";
				if(i!=r.length+1)
				{
					jsonStr+=(",");
				}
  	    	}
	  }
      var jsonNewStr = jsonStr.substring(0, jsonStr.lastIndexOf(','));
      jsonNewStr+="]";
      var showLoginName = $("#showLoginName").val();
      var buyMoney = $("#buyMoney").val();
      if(showLoginName != ''){
	  	$.post("/gbdbas/userManager/addRightForUser",{"jsonArray":jsonNewStr,"auth_type":item,"loginName":showLoginName,"buyMoney":buyMoney},
	  	function(data){
	  		$.messager.alert('提示',data.message,'info');
		}
		,"json");  
      }else{
    	  $.messager.alert('提示',"请先输入用户信息",'info');
      }
	}
}

//按次授权页
function showCountRightInfo(){
	$("#RightInfo").hide();
	$("#countRightInfo").show();
}

//按次授权
function addCountRight(){
	 var showLoginName = $("#showLoginName").val();
     var buyMoney = $("#buyMoney").val();
	 var item =$(':radio[name="RadioGroup2"]:checked').val();
     if(showLoginName != ''){
	  	$.post("/gbdbas/userManager/addRightForUser",{"jsonArray":'',"auth_type":item,"loginName":showLoginName,"buyMoney":buyMoney},
		  	function(data){
		  		$.messager.alert('提示',data.message,'info');
			}
		,"json");  
     }else{
    	 $.messager.alert('提示',"请先输入用户信息",'info');
     }
}

//关闭授权窗口
function closeRightForm(){
	$.messager.confirm('提示', '确定关闭授权窗口吗?', function(r){
		if (r){
			clearForm();
			$("#pwd_info_id").hide();
			$("#showRight").hide();
		}
	});
}
