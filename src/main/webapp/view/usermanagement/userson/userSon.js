//当前用户全局变量
var currentUser;
var usercenterArray=userCenterParam();
var urladdrss;
//当前子用户的ID
var userSonId;
var operFlag;
//初始化加载
$(document).ready(function()
{
     $('#addExcleDlg').hide();
     loadUserInfo();
   	 //改表一级菜单上贸易情报模块的背景色
  	 document.getElementById("nav-item_1").style.backgroundColor = "#ffffff";
   	 document.getElementById("font_color1").style.color = "#0066cc";
     //显示二级菜单栏
  	 showSubMenu(1)
  	 //改变二级菜单栏样式
  	 changePCSubMenuColor(4);
  	  
});

/**
 * 加载当前用户的全部信息
 */
function loadUserInfo()
{
	var uid=$("#currentId").text();
	//根据用户ID获取用户的全部信息
 	$.ajax({
		type:'post',
		url:'/gbdbas/userManager/queryUserById',
		data:{"userId":uid},
	    dataType:"json",
		async:false,
		success:function(data){
			//赋值给用户全局变量
			currentUser=data;
			 loadSonNum();
			 queryUserSons();
		}
	});
}
/**
 * 查询用户的子用户列表
 */
function queryUserSons()
{
	var userId=currentUser.userId;
	//加载子用户列表
	$('#sonsTable').datagrid({
            url:'/gbdbas/userSon/queryUserSons',
	 		queryParams:{"userId":userId},
	 		pageNumber:1,
	        pageSize:25,
	        pagination:true,
	        fitcolumns:false,
	        autoRowHeight:false,
	        sortorder: 'asc',
	        loadMsg:'Loading...',
	        pageList:[10,25,50,80,100],
	 		columns : [sonsArr]
	});
}
var sonsArr=[
             {field:'ck',checkbox:true},
             {field:"userId", hidden:"true", align:"center",title:"用户编号"},
             {field:"userDesc", hidden:"true", align:"center",title:"用户描述"},
             {field:"isDisable", hidden:"true", align:"center",title:"是否禁用"},
             {field:"tel", hidden:"true", align:"center",title:"电话"},
             {field:"qq", hidden:"true", align:"center",title:"QQ"},
             {field:"phone", hidden:"true", align:"center",title:"手机"},
             {field:"beginTime", hidden:"true", align:"center",title:"开始时间"},
             {field:"endTime", hidden:"true", align:"center",title:"结束时间"},
             {field:"loginPassword", hidden:"true", align:"center",title:"密码"},
             {field:"loginName", width:"100" ,align:"center",title:"用户名"},
             {field:"email", width:"100", align:"center",title:"邮箱"},
             {field:"sonAccountNum", width:"120", align:"center",title:"已添加子账户数量"},
             {field:"userName", width:"100" ,align:"center",title:"联系人"},
             {field:"accountopt", width:"525" ,align:"center" ,formatter:addsonUserOp,title:"操作"}
          ]

/**
 * 添加新的用户
 */
function operUserSon()
{
	var userId= $("#userId").val().trim();
	if(userId == "" || userId == "undefined" || userId ==null)
	{
		userId = 0;
	}
	var userDesc = $("#userDesc").val().trim();
	var loginName = $("#inputLoginName").val().trim();
	var loginPassword = $("#inputPassword").val().trim();
	var email = $("#inputEmail").val().trim();
	var phone=$("#inputPhone").val().trim();
	var qq =$("#inputQQ").val().trim();
	var tel=$("#inputTel").val().trim();
	var beginTime= $("#beginTime").val();
	var endTime= $("#endTime").val();
	if(checkQQ()&&checkEmail()&&checkTel()&&checkPhone()&&checkAccount()&&checkPassword()&&checkData(beginTime,endTime)){
		$.ajax({
			type:'post',
			url:urladdrss,
			data:{"loginName": loginName,"loginPassword": loginPassword,
			      "email": email,"phone": phone, "qq":qq,"beginTime":beginTime,
			      "endTime":endTime,"tel":tel,"userId":userId,"userDesc":userDesc},
		    dataType:"json",
			async:false,
			success:function(data){
				if(data.flag){
		    		 art.dialog({id:'addUserdlg'}).close();
		    		 loadUserInfo();
		    		 $.messager.alert(usercenterArray[2], "操作成功", 'info');
		    	}else{
		    		 art.dialog({id:'addUserdlg'}).close();
		    		 $.messager.alert(usercenterArray[2], "操作失败", 'info');
		    	}
			}
		});
	}
}
/**
 * 修改用户
 */
function updateUser(){
	 clearMessage();
	 
	 var rows=$('#sonsTable').datagrid('getChecked');
		//判断用户选择几行
		if (rows.length != 1) {
			$.messager.alert(usercenterArray[2], "请选择一行", 'info');
			return;
		}
		 var row = $('#sonsTable').datagrid('getSelected');
		 if (row){
			 operFlag=true;
			 $("#inputLoginName").attr('readonly',true);
				$("#inputLoginName").css({
					'border':'none',
				    'background-color':'#ffffff',
				    'color':'#488fe8'
				});
			 openDivArtDialog(usercenterArray[24], 'addUserdlg', 'addUserdlg', 700, 500,true); 
		    $("#userfm").find('#userId').attr("value",row.userId)
		    $("#userfm").find('#inputLoginName').attr("value",row.loginName)
		    $("#userfm").find('#inputEmail').attr("value",row.email)
		    $("#userfm").find('#userDesc').attr("value",row.userDesc)
		    $("#userfm").find('#inputPhone').attr("value",row.phone)
		    $("#userfm").find('#inputTel').attr("value",row.tel)
		    $("#userfm").find('#inputQQ').attr("value",row.qq)
		    $("#userfm").find('#beginTime').val(getCurrentTime())
		    $("#userfm").find('#endTime').val(row.endTime.substring(0,10))
		    urladdrss = '/gbdbas/userManager/upUser';
		 }else{
			$.messager.alert(usercenterArray[2], usercenterArray[25], 'info');
		 }
}
/**
 * 根据用户ID删除用户
 */
function delUser(userId)
{
	 $.messager.confirm(usercenterArray[2], usercenterArray[27],function(r){
		   if (r){
		      $.post('/gbdbas/userSon/deleteUserSon',{"userId":userId},
		    		  function(data){
                      //提示删除结果
		    	      if(true==data.flag)
		    	      {
		    	    	  $.messager.alert(usercenterArray[2],"删除成功");
			    	  	    //重新加载用户信息
			    	  		loadUserInfo();
		    	       }
		    	  		
		      		  },'json');
		   }
		});
}
/**
 *删除用户
 */
function deleteUsers()
{
		var rows=$('#sonsTable').datagrid('getChecked');
		var names = new Array();
		$.each(rows, function(index, item) {
			names[index] = item.userId;
		});
		if (names.length == 0) {
			$.messager.alert(usercenterArray[2], "请至少选择一行", 'info');
			return;
		}
		 $.messager.confirm(usercenterArray[2], usercenterArray[27],function(r){
			   if (r){
			      $.post('/gbdbas/userSon/deleteUserSons',{ids:names},
			    		  function(data){
                            //提示删除结果
					    	  if(true==data.flag)
				    	      {
				    	    	  $.messager.alert(usercenterArray[2],"删除成功");
					    	  	    //重新加载用户信息
					    	  		loadUserInfo();
				    	       }
			      		  },'json');
			   }
			});
		
}

/**
 * 验证是否可以添加用
 */
function addUser(){
	if(currentUser!=undefined&&currentUser.userId!="")
	{
		urladdrss="/gbdbas/userSon/addUserSon";
		var flag=chechUserSonRight(currentUser.loginName);
		if(flag==1)
		{
			//根据当前的用户ID查询该用户判断该用户是否有新增子帐号的权限
			clearMessage();
			operFlag=false;
			 $("#inputLoginName").attr('readonly',false);
				$("#inputLoginName").css({
					"width":"184px",
			    	"height":"28px",
			    	"border": "1px solid #e5e5e5",
					"border-radius":"4px 4px 4px 4px",
					"margin-left":"8px"
				});
			openDivArtDialog('新增用户', 'addUserdlg', 'addUserdlg', 600, 500,true); 	
			$('#userfm').form('clear');
			setSonEffectDate();
		}
		else if(flag==2)
		{
   		 $.messager.alert(usercenterArray[2], "子帐号已经用完", 'info');
		}
		else if(flag==3)
		{
			 $.messager.alert(usercenterArray[2], "未开通子帐号的权限", 'info');
		}
		else if(flag==4)
		{
			 $.messager.alert(usercenterArray[2], "已超出3层不允许开通子帐号", 'info');
		}
	}
}
/**
 * 检测该用户是否具有开通子用户的权限
 */
function chechUserSonRight(loginName){
  var flag=0;
	$.ajax({
		type:"post",
		async:false,
		url:"/gbdbas/userSon/chechUserSonRight",
		data:{"loginName":loginName},
		dataType:"json",
		success:function(data){
			flag=data.flag;
		}
	});
	return flag;
}
//子帐号的操作
function addsonUserOp(value,rowData,rowIndex){
	var b = "&nbsp;<a title=\""+usercenterArray[36]+"\" href=\"#\" mce_href=\"#\" style=\"color: #939a9d;font-family: Microsoft YaHei;\" onmouseover=\"this.style.textDecoration='underline';this.style.color='#086fac'\" onMouseOut=\"this.style.textDecoration='none';this.style.color='#939a9d'\" onclick=\"addRight('"+rowData.userId+"')\">"+usercenterArray[36]+"</a>&nbsp;";
	var c = "&nbsp;<a title=\""+usercenterArray[37]+"\" href=\"#\" mce_href=\"#\" style=\"color: #939a9d;font-family: Microsoft YaHei;\" onmouseover=\"this.style.textDecoration='underline';this.style.color='#086fac'\" onMouseOut=\"this.style.textDecoration='none';this.style.color='#939a9d'\" onclick=\"changeStatus('"+rowData.userId+"','isActivated')\">"+usercenterArray[37]+"</a>";
	var d = "&nbsp;<a title=\""+usercenterArray[38]+"\" href=\"#\" mce_href=\"#\" style=\"color: #939a9d;font-family: Microsoft YaHei;\" onmouseover=\"this.style.textDecoration='underline';this.style.color='#086fac'\" onMouseOut=\"this.style.textDecoration='none';this.style.color='#939a9d'\" onclick=\"changeStatus('"+rowData.userId+"','isActivated')\">"+usercenterArray[38]+"</a>";
	var e = "&nbsp;<a title=\""+usercenterArray[39]+"\" href=\"#\" mce_href=\"#\" style=\"color: #939a9d;font-family: Microsoft YaHei;\" onmouseover=\"this.style.textDecoration='underline';this.style.color='#086fac'\" onMouseOut=\"this.style.textDecoration='none';this.style.color='#939a9d'\" onclick=\"selectmyRight('"+rowData.userId+"')\">"+usercenterArray[39]+"</a>&nbsp;";
	var f = "&nbsp;<a title=\""+usercenterArray[186]+"\" href=\"#\" mce_href=\"#\" style=\"color: #939a9d;font-family: Microsoft YaHei;\" onmouseover=\"this.style.textDecoration='underline';this.style.color='#086fac'\" onMouseOut=\"this.style.textDecoration='none';this.style.color='#939a9d'\" onclick=\"changeStatus('"+rowData.userId+"','openService')\">"+usercenterArray[186]+"</a>&nbsp;";
	var g = "&nbsp;<a title=\""+usercenterArray[187]+"\" href=\"#\" mce_href=\"#\" style=\"color: #939a9d;font-family: Microsoft YaHei;\" onmouseover=\"this.style.textDecoration='underline';this.style.color='#086fac'\" onMouseOut=\"this.style.textDecoration='none';this.style.color='#939a9d'\" onclick=\"changeStatus('"+rowData.userId+"','openService')\">"+usercenterArray[187]+"</a>&nbsp;";
	var h = "&nbsp;<a title=\""+usercenterArray[188]+"\" href=\"#\" mce_href=\"#\" style=\"color: #939a9d;font-family: Microsoft YaHei;\" onmouseover=\"this.style.textDecoration='underline';this.style.color='#086fac'\" onMouseOut=\"this.style.textDecoration='none';this.style.color='#939a9d'\" onclick=\"openExcleNum('"+rowData.userId+"')\">"+usercenterArray[188]+"</a>&nbsp;";
	//刚新增的用户拥有删除用户,授权,是否激活,是否允许添加子账号等内容
	if(rowData.userDesc=='试用用户'){
		//判断账号是否激活了
		if(rowData.isActivated){
			//判断是否开启子账号
			if(rowData.openService){
				return b+"|"+d+"|"+f;
			}else{
				return b+"|"+d+"|"+g;
			}
		}else{
			//判断是否开启子账号
			if(rowData.openService){
				return b+"|"+c+"|"+f;
			}else{
				return b+"|"+c+"|"+g;
			}
		}
	}else{
		//判断账号是否激活了
		if(rowData.isActivated){
			//判断是否开启子账号
			if(rowData.openService){
				return b+"|"+e+"|"+d+"|"+f+"|"+h;
			}else{
				return b+"|"+e+"|"+d+"|"+g+"|"+h;
			}
		}else{
			//判断是否开启子账号
			if(rowData.openService){
				return b+"|"+e+"|"+c+"|"+f+"|"+h;
			}else{
				return b+"|"+e+"|"+c+"|"+g+"|"+h;
			}
		}
	}
}
/**
 * 查询子账户权限
 */
function selectmyRight(v){
	
	$("#viprightTable").datagrid({
		url:'/gbdbas/userSonRight/queryCodeRightsForPagingById',
		queryParams:{userId:v,param:"单一权限"},
		columns:[hsArr],
		pageNumber:1,
        pageSize:10,
        pagination:true,
        fitcolumns:false,
        autoRowHeight:false,
        sortorder: 'asc',
        loadMsg:'Loading...',
        pageList:[10,25,50,80,100],
		onLoadSuccess: function(data){ 
		    if(data.rows.length == 0)
		    {
		    	$('#viprightDiv').hide();
			}else
			{
				$('#viprightDiv').show();
			}
	     		 
	    } 
	});
	//展示按国家定制的内容
	$("#goldenrightTable").datagrid({
		url:'/gbdbas/userSonRight/queryCountryRightsForPagingById',
		queryParams:{userId:v,param:"国家权限"},
		columns:[countryArr],
		pageNumber:1,
        pageSize:10,
        pagination:true,
        fitcolumns:false,
        autoRowHeight:false,
        sortorder: 'asc',
        loadMsg:'Loading...',
        pageList:[10,25,50,80,100],
		onLoadSuccess: function(data){ 
		    if(data.rows.length == 0)
		    {
		    	$('#goldenrightDiv').hide();
			}else
			{
				$('#goldenrightDiv').show();
			}
	     		 
	    } 
	});
	openDivArtDialog('授权', 'seeRight', 'seeRight', 820, 600,true); 	
}

var hsArr=[
			{field:"byHsCode", hidden:false,width: 100,align:"center",title:"购买的海关编码"},
			{field:"byProductDesc", hidden:false,width: 100, align:"center",title:"购买的商品名称"},
			{field:"byCountry", hidden:false, width: 108,align:"center",title:"所属国家"},
			{field:"iExportType", hidden:false,width: 100, align:"center",title:"进出口类型"},
			{field:"startTime", hidden:false, width: 205,align:"center",title:"数据检索开始时间"},
			{field:"endTime", hidden:false, width: 205,align:"center",title:"数据检索结束时间"}
]
var countryArr=[
		{field:"byCountry", hidden:false, width: 200,align:"center",title:"所属国家"},
		{field:"iExportType", hidden:false,width: 200, align:"center",title:"进出口类型"},
		{field:"startTime", hidden:false, width: 208,align:"center",title:"数据检索开始时间"},
		{field:"endTime", hidden:false, width: 210,align:"center",title:"数据检索结束时间"}
]
//设置默认子账户的有效日期
function setSonEffectDate()
{
	//开始时间为当前默认时间
	var  startTime=getCurrentTime();
	//默认子账户的结束时间为父账户的结束时间
	var endTime=currentUser.endTime.substring(0,10);
	$("#beginTime").val(startTime);
    $("#endTime").val(endTime);
}
//根据用户ID获取用户相关信息
function loadSonNum()
{
			var mainID=currentUser.mainId;
			var sonAccountNum=currentUser.sonAccountNum;
			var sonAccountTotal=currentUser.sonAccountTotal;
			var mainstr="您当前拥有开通子账户总数为："+sonAccountTotal+"个"+" "+"当前已添加子账户总数为："+sonAccountNum+"个";
			var subStr="当前已添加子账户总数为："+sonAccountNum+"个";
			//判断当前用户是否是主用户，如果是，则显示它拥有的全部子账户的数量和已经开通的数量，
			//如果不是则只显示该账户下的子账户数量
			if(0==mainID)
			{
	              	$("#subSumCount") .html(mainstr);
			}
			else
			{
				 $("#subSumCount") .html(subStr); 
			}
}
//清除提示信息
function clearMessage(){
	$("#userfm .help-inline").empty();
}
//用户命名规则提示
function showNameRule(){
	if(!checkLoginName()){
		$("#loginNameMessage").empty();
		$("#loginNameMessage").append("*账号必须由英文字母、数字(0-9)、汉字组成，长度在4-12个字符之间。");
	}
}
//检查用户的输入名是否符合规定
function checkLoginName(){
	var test=/^([A-Za-z0-9^\u4e00-\u9fa5]){4,12}$/;
	var txt=$("#logName").val();
	var flag=true;
	if(!test.test(txt.trim()))
		{
		  $("#loginNameMessage").empty();
		  $("#loginNameMessage").append("<span style='color:#086fac'>&nbsp;&nbsp;"+"账号不正确!"+"!</span>");
           flag=false;
		}
	else
		{
		  $.ajax({
			  type:"post",
			  dataType:"json",
			  url:"/gbdbas/userSon/checkUserSonName?loginName="+txt+"",
			  async:false,
			  success:function(data)
			  {
				  var count=data.count;
				  if(count>1)
					  {
						  $("#loginNameMessage").empty();
						  $("#loginNameMessage").append("账户已存在");
						  flag=false;
					  }
				  else 
					  {
						  $("#loginNameMessage").empty();
						  $("#loginNameMessage").append("成功");
						  flag=true;
					  }
			  }
		  });
		}
	return flag;
}
//显示密码设置规则
function showPasswordRule()
{
	if(!checkPassword())
		{
           $("#loginPasswordMessage").empty();
           $("#loginPasswordMessage").append("只能包含字母、数字以及标点符号（除空格），长度为6～12。");
		}
}
//检查用户设置的密码是否符合规定
function checkPassword()
{
	//js正则表达式判断是否符合密码规则
	var test=/^([A-Za-z0-9-+=|,!@#$%^&*?_.~+/\\(){}\[\]<>]){6,12}$/;
	var txt=$("#loginPassword").val();
	if(!test.test(txt.trim())){
		$("#loginPasswordMessage").empty();
		$("#loginPasswordMessage").append("密码不符合格式");
		return false;
	}else{
		$("#loginPasswordMessage").empty();
		$("#loginPasswordMessage").append("密码合格");
		return true;
	}
}
//检查用户的email
function checkEmail()
{
	var test = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var txt = $("#email").val();
	if(txt.trim()!=""){
		if(!test.test(txt.trim())){
			$("#emailMessage").empty();
			$("#emailMessage").append("email地址错误");
			return false;
		}else{
			$("#emailMessage").empty();
			$("#emailMessage").append("成功");
			return true;
		}
	}else{
		return true;
	}
}
//检查手机号码
function checkPhone()
{
	 var test = /^\d{3,4}-\d{7,8}(-\d{3,4})?$/;
	 var txt = $("#phone").val();
	 if(txt.trim()!=""){
		 if(!test.test(txt.trim())){
				$("#phoneMessage").empty();
				$("#phoneMessage").append("电话号码格式错误");
				return false;
		}else{
				$("#phoneMessage").empty();
				$("#phoneMessage").append("电话号码正确");
				return true;
		}
	 }else{
		 return true;
	 }
}

//验证登陆名
function fouceAccount(){
	if(checkAccount()==false){
		$("#userNameMessage").empty();
		$("#userNameMessage").append("*账号必须由英文字母、数字(0-9)、汉字组成，长度在4-12个字符之间。");
	}
}
//验证用户名
function checkAccount(){
	if(operFlag)
		return true;
	var test = /^([A-Za-z0-9 ^\u4e00-\u9fa5]){4,12}$/;
	var txt = $("#inputLoginName").val();
	var flag = true;
	if(!test.test(txt.trim())){
		$("#userNameMessage").empty();
		$("#userNameMessage").append("<span style='color:#086fac'><img src=\"/gbdbas/static/img/usermanagement/no.png\"/>&nbsp;&nbsp;"+"账号不正确!"+"!</span>");
	    flag = false;
	}else
	{
		$.ajax({
			type:'post',
			dataType:"json",
	    	url : "/gbdbas/userSon/checkUserSonName?loginName="+txt+"",
	    	async:false,
			error:function(data){},
			success : function(data)
			{
	        	if(data){
	        		$("#userNameMessage").empty();
					$("#userNameMessage").append("<img src=\"/gbdbas/static/img/usermanagement/ok.png\"/>");
					flag = true;
	        	}else{
	        		$("#userNameMessage").empty();
					$("#userNameMessage").append("<span style='color:#086fac'><img src=\"/gbdbas/static/img/usermanagement/no.png\"/>&nbsp;&nbsp;"+"该账户名已经存在"+"</span>");
					flag = false;
				
				} 
			}
        });
	}
	return flag;
}
//验证密码
function foucsPassword(){
	if(checkPassword()==false){
		$("#passMessage").empty();
		$("#passMessage").append("*只能包含字母、数字以及标点符号（除空格），长度为6～12。");
	}
}
//验证密码
function checkPassword(){
	var test = /^([A-Za-z0-9-+=|,!@#$%^&*?_.~+/\\(){}\[\]<>]){6,12}$/;
	var txt = $("#inputPassword").val();
	if(!test.test(txt.trim())){
		$("#passMessage").empty();
		$("#passMessage").append("<span id=\"pwdmsg\" style='color:#086fac'><img src=\"/gbdbas/static/img/usermanagement/no.png\"/>&nbsp;&nbsp;"+"密码输入不正确!"+"</span>");
		return false;
	}else{
		$("#passMessage").empty();
		$("#passMessage").append("<img src=\"/gbdbas/static/img/usermanagement/ok.png\"/>");
		return true;
	}
}
//验证邮箱格式
function checkEmail(){
	var test = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var txt = $("#inputEmail").val();
	if(txt.trim()!=""){
		if(!test.test(txt.trim())){
			$("#emailMessage").empty();
			$("#emailMessage").append("<span id=\"emailmsg\" style='color:#086fac'><img src=\"/gbdbas/static/img/usermanagement/no.png\"/>&nbsp;&nbsp;"+"邮箱格式不正确！请重新填写。"+"</span>");
			return false;
		}else{
			$("#emailMessage").empty();
			$("#emailMessage").append("<img src=\"/gbdbas/static/img/usermanagement/ok.png\"/>");
			return true;
		}
	}else{
		return true;
	}
}

/**
 * 电话检测
 * @return
 */
function checkTel(){
	 var test = /^\d{3,4}-\d{7,8}(-\d{3,4})?$/;
	 var txt = $("#inputTel").val();
	 if(txt.trim()!=""){
		 if(!test.test(txt.trim())){
				$("#telMessage").empty();
				$("#telMessage").append("<span id=\"telmsg\" style='color:#086fac'><img src=\"/gbdbas/static/img/usermanagement/no.png\"/>&nbsp;&nbsp;"+"区号为3-4位的数字，区号之后用“-”与电话号码连接，电话号码为7-8位的数字。"+"  </span>");
				return false;
		}else{
				$("#telMessage").empty();
				$("#telMessage").append("<img src=\"/gbdbas/static/img/usermanagement/ok.png\"/>");
				return true;
		}
	 }else{
		 return true;
	 }
}
/**
 * 手机检检测
 */
function checkPhone()
{
	 var test = /^(\d){11}$/;
	 var txt = $("#inputPhone").val();
	 if(txt.trim()!=""){
		 if(!test.test(txt.trim())){
				$("#phoneMessage").empty();
				$("#phoneMessage").append("<span id=\"telmsg\" style='color:#086fac'><img src=\"/gbdbas/static/img/usermanagement/no.png\"/>&nbsp;&nbsp;"+"请输入手机号码为：7-8位的数字"+"  </span>");
				return false;
		}else{
				$("#phoneMessage").empty();
				$("#phoneMessage").append("<img src=\"/gbdbas/static/img/usermanagement/ok.png\"/>");
				return true;
		}
	 }else{
		 return true;
	 }
}
/**
 * 验证QQ
 */
function checkQQ()
{
	var test = /^(\d){5,11}$/;
	var txt = $("#inputQQ").val();
	if(txt.trim()!=""){
		if(!test.test(txt.trim())){
			$("#qqMessage").empty();
			$("#qqMessage").append("<span id=\"pwdmsg\" style='color:#086fac'><img src=\"/gbdbas/static/img/usermanagement/no.png\"/>&nbsp;&nbsp;"+"请输入正确的QQ号码!"+"</span>");
			return false;
		}else{
			$("#qqMessage").empty();
			$("#qqMessage").append("<img src=\"/gbdbas/static/img/usermanagement/ok.png\"/>");
			return true;
		}
	}
	else
	{
		 return true;
	}
}
/**
 *修改用状态
 */
function changeStatus(userId,name){
	$.messager.confirm("提示", "确定修改",function(r){
		if (r){
				//根据字段名称修改字段的状态修改字段的状态
				$.ajax({
					method:'post',
					url:'/gbdbas/userManager/updateFiledStatus',
					data:{"param":name,"userId":userId},
					dataType:"json",
					success:function(data){
						var result=data.result
						if(result!=null)
						{
								    loadUserInfo();
						}
					}
				});
		}
	});
}
/**
 * 添加用户权限
 */
function addRight(sonId){
	userId=currentUser.userId;
	userSonId=sonId;
//	$('#rightDiv').show();
	openDivArtDialog('授权', 'rightDiv', 'rightDiv', 800, 550,true); 	
	//清空tabs
	closeAllTabs();
	 //加载系统拥有的权限列表
	loadCountyView();
	//加载加用户拥有的权限列表
	loadUserRight();
}
/**
 * 初始化清空tabs
 */
function closeAllTabs(){  
    var arrTitle = new Array();  
    var id = "#codesDiv";//Tab所在的层的ID  
    var tabs = $(id).tabs("tabs");//获得所有小Tab  
    var tCount = tabs.length;  
    if(tCount>0){  
                //收集所有Tab的title  
        for(var i=0;i<tCount;i++){  
            arrTitle.push(tabs[i].panel('options').title)  
        }  
                //根据收集的title一个一个删除=====清空Tab  
        for(var i=0;i<arrTitle.length;i++){  
            $(id).tabs("close",arrTitle[i]);  
        }  
    }  
} 
/**
 * 打开下载条数页面
 */
function openExcleNum(userid){
	//设置当前的子用户
	 userSonId=userid;
	 //异步查询当前用户拥有的下载数量
	$.ajax({
		type:"post",
		async:false,
		url:'/gbdbas/userSonRight/queryDownLoadNum',
		data:{userId:userid,relaId:currentUser.userId},
		dataType:"json",
		success:function(data){
			var downRight=data.downRight;
		       if(downRight!=null&&downRight.length>0){
		    	   for(var i=0;i<downRight.length;i++){
		    		   //如果为主账户
		    		   if(downRight[i].userId==userid){
                          $("#setNum").val(downRight[i].totalNum);
		    		   }
		    		   if(downRight[i].userId==currentUser.userId){
		    			   $("#maxNum").html(downRight[i].totalNum);
		    		   }
		    	   }
		    	   //显示下载条数div
		    		openDivArtDialog('设置下载条数', 'addExcleDlg', 'addExcleDlg', 220, 120,true); 	
		       }
		}
	});
}
/**
 * 更新下载条数
 */
function saveExcleNum(){
     //父账户的最大下载数量
	var maxNum=$("#maxNum").text().trim();
	//子账户的最大下载数量  	
	var setNum=$("#setNum").val().trim();
	if((Number(setNum)>Number(maxNum))){
		$.messager.alert("提示", "子账户的最大下载数量超限", 'info');
		return ;
	}
	$.ajax({
		type:'post',
		url:'/gbdbas/userSonRight/updateDownNum',
		data:{userId:userSonId,totalNum:setNum},
		dataType:'json',
		success:function(data){
			var flag=data.flag;
			if(flag){
				 art.dialog({id:'addExcleDlg'}).close();
				 $.messager.alert(usercenterArray[2], "操作成功", 'info');
			}
		}
	});
		
}

/**
 * 切换海关编码产品描述标签页
 * @auther洪皓
 */
function changeTab(name,cursel,n){
	 for(i=1;i<=n;i++){
	  var menu=document.getElementById(name+i);
	  var con=document.getElementById("con_"+name+"_"+i);
	  menu.className=i==cursel?"hover":"";
	  con.style.display=i==cursel?"block":"none";
	 }
}
