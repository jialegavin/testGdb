var flag=false;
var currentUserId="";
$(document).ready(function(){ 
	queryUserTree();
	onclickTree();
	hideSomeDiv();
	//设置开始时间格式
	  $('#beginTime').datebox({
		    formatter: function(date){ return myformatter(date);},
		});
	 //设置结束时间时间格式
	  $('#endTime').datebox({
		    formatter: function(date){ return myformatter(date);},
		});
}); 
//初始化隐藏一些div
function hideSomeDiv(){
	$("#addUser").hide();
	$("#upUser").hide();
	$("#loginInfo").hide();
	$("#searchInfo").hide();
	$("#userInfo").hide();
}
//查询用户树
function queryUserTree()
{
	$.ajax({
	    type:'post',
	    url:'/gbdbas/userManager/queryUserTree',
	    async: false, 
	    success:function(data){
	    	 var treeData=eval(data);
	    	 $('#usersTree').tree({    
	    	     	data:treeData
	    	     });
	    }
	});
}
//用户树上的节点单击事件
function onclickTree(){
	  $("#usersTree").tree({
		  onClick:function(node){
			  var nodeId=node.id;
			  var userDesc=node.text;
			  //判断节点的ID是否为空如果不是空则为用户ID，获取后台用户模型
			  if(nodeId!=undefined)
				  {
				    //判读用用点击的节点：如果是树干 则显示新增用户，如果是树叶则显示修改用户
				    if(nodeId=="userType")
				    {
				        //隐藏修改用户
				    	$("#upUser").hide();
				    	//隐藏登录日志
				        $("#loginInfo").hide();
				        //隐藏查询信息
				    	$("#searchInfo").hide();
				    	//隐藏用户基本信息
				    	$("#userInfo").hide();
				        //清除添加form的残留信息
				    	$("#userOperForm").form('clear');
				        //设置新增或要修改的用户的身份
				    	$("#userDesc").val(userDesc)
				        //显示新增按钮
				    	$("#addUser").show();
				   	}
				    else
				    {
				    	//隐藏新增按钮
				    	$("#addUser").hide();
				    	currentUserId=nodeId;
				    	//显示用户基本信息
				    	fillUserInfo(nodeId);
			    		//显示用户登录信息
				    	 queryLoginLog(nodeId);
				    	$("#upUser").show();
				    }
				  
				  }
		  }
	  });
}
//填充用户信息
function fillUserInfo(uId)
{
	//根据用户ID获取用户模型
	$.ajax({
	    type:'post',
	    url:'/gbdbas/userManager/queryUserById',
	    data:{userId:uId},
	    dataType:"json",
	    async: false, 
	    success:function(data){
	    	 var user=eval(data);
	    	 //设置基本信息
	    	 $("#loginNameS").html(user.loginName);
	    	 $("#emailS").html(user.email);
	    	 $("#phoneS").html(user.phone);
	    	 $("#qqS").html(user.qq);
	    	 $("#userNameS").html(user.userName);
	    	 $("#sonAccountNumS").html(user.sonAccountNum);
	    	 $("#userDescS").html(user.userDesc);
	    	 $("#isActivatedS").html(reSpanJpg(user.isActivated));
	    	 $("#sonAccountTotalS").html(user.sonAccountTotal);
	    	 $("#openServiceS").html(reSpanJpg(user.openService));
	    	 $("#isLockedS").html(reSpanJpg(user.isLocked));
	    	 $("#isDisableS").html(reSpanJpg(user.isDisable));
	    	 //填充要修改的信息
	    	 $("#userId").val(user.userId);
	    	 $("#loginName").val(user.loginName);
	    	 $("#email").val(user.email);
	    	 $("#phone").val(user.phone);
	    	 $("#qq").val(user.qq);
	    	 $("#userName").val(user.userName);
	    	 $("#address").val(user.address);
	    	 $("#beginTime").datebox("setValue",user.beginTime.substring(0,10)); 
	    	 $("#endTime").datebox("setValue",user.endTime.substring(0,10));
	    	 $("#userDesc").val(user.userDesc);
	    	//判断是该用户是否是管理员用户，如果是则不显示查询日志
	    	 if(user.userDesc!="管理员用户")
	    	 {
	    		 querySearchLog(uId);
	    	 }
	    	 else
	         {
	    			$("#searchInfo").hide();
	         }
	    }
	});
	//显示用户基本信息
	$("#userInfo").show();
	$("#var1").hide();
	$("#var1").attr("value","");
	$("#sonAccountTotalS").show();
}

//根据字段名称修改状态
function changeStatus(name,id)
{
	//根据字段名称修改字段的状态修改字段的状态
	$.ajax({
		method:'post',
		url:'/gbdbas/userManager/updateFiledStatus',
		data:{"param":name,"userId":currentUserId},
		dataType:"json",
		success:function(data){
			var result=data.result
			if(result!=null)
				{
				  if(result==1)
					  {
					    $("#"+id).html(reSpanJpg("true"));
					  }
				  else{
					  $("#"+id).html(reSpanJpg("false"));
				  }
				  
				}
		}
	});
}
//
function reSpanJpg(flag)
{
	if(flag=="true")
		{
		  return "<img src='/gbdbas/static/img/admincenter/tree_dnd_yes.png'>"
		}
	else
		{
		 return "<img src='/gbdbas/static/img/admincenter/tree_dnd_no.png'>"
		}
}
//登陆日志列表
var loginArr = [
           {field:'ipAddress',align:'center',title:'用户IP地址'},
           {field:'IpAttribution',align:'center',title:'用户地址'},
           {field:'loginTime',align:'center',title:'登陆时间'}
           ];
//查询登陆日志
function queryLoginLog(uid)
{          
	//显示用户登录日志
	$("#loginInfo").show();
	//加载登录日志表格
	$('#loginTable').datagrid({
		 		url:'/gbdbas/userLogInfo/queryUserLogById',
	   	 		queryParams:{'userId':uid},
	   	 		columns : [loginArr]
	    	  	});
}

var searchArr=[
            {field:'logId',align:'center',title:'日志编号'},
            {field:'logTime',align:'center',title:'日志时间'},
            {field:'country',align:'center',title:'国家'},
            {field:'userName',align:'center',title:'用户真实名称'},
            {field:'loginName',align:'center',title:'用户登陆名称'},
            {field:'parameter',align:'center',title:'查询参数'},
            {field:'logDesc',align:'center',title:'日志描述'},
            {field:'userId',align:'center',title:'用户的ID'}
            ]
//加载查询列表
function querySearchLog(uid)
{
	//显示查询日志信息
	$("#searchInfo").show();
	//加载用户查询日志
	$('#searchTable').datagrid({
	            url:'/gbdbas/userLogInfo/queryUserSearchLog',
	   	 		queryParams:{'userId':uid},
	   	 		columns : [searchArr]
	    	  	});
}

//新增加用户
function addUser(){
	  var url="/gbdbas/userManager/addNewUser";
	  userOperForm(url);
}
//修改用户
function upUser()
{
	 var url="/gbdbas/userManager/upUser";
	 userOperForm(url);
}
function userOperForm(url){
	var userId= $("#userId").val().trim();
	if(userId == "" || userId == "undefined" || userId ==null)
	{
		userId = "0";
	}
	var loginName= $("#loginName").val();
	  var email= $("#email").val();
	  var phone= $("#phone").val();
	  var qq= $("#qq").val();
	  var userName= $("#userName").val();
	  var address= $("#address").val();
	  var beginTime= $("#beginTime").datebox('getValue');
	  var endTime= $("#endTime").datebox('getValue');
	  var userDesc= $("#userDesc").val();
	//新增用户
	$.post(url,
			{"userId":userId,"loginName":loginName,"email":email,"phone":phone,"qq":qq,
		  "userName":userName,"address":address,"beginTime":beginTime,"endTime":endTime,"userDesc":userDesc}
			,function(data){
		   if(data.flag="1")
			{
			  //提示新增成功
		     alert("操作成功");
		     //刷新用户树
		     queryUserTree();
	        }
	},	
	"json"
	);
}
//修改用户的子用户的数量
function upSonNum(){
	//获取新的值
	var newValue=$("#var2").val();
	var uid=$("#userId").val();
	//获取老的值
	var oldValue=$("#sonAccountTotalS").text();
    //新值不为空则执行修改用户的自用数量
	if(newValue!=null&&newValue!=""&&newValue!=undefined)
	{
		//异步修改子用户的数量
		$.ajax({
			method:'post',
			url:'/gbdbas/userManager/updateUseSonCount',
			async:false,
			data:{"userId":uid,"sonAccountTotal":newValue},
			success:function(data)
			{
				if(data="1")
				{
					$("#sonAccountTotalS").text(newValue);
				}
			}
		});
	}
	
	$("#var1").hide();
	$("#sonAccountTotalS").show();
}
//动态修改子账户的数量
function changeSpan(){
	$("#var2").val("");
	$("#sonAccountTotalS").hide();
	$("#var1").show();
}
