var user=null;  //保存用户信息
$(document).ready(function(){
	//加载用户
	if(user==null){
		user=querySessionUser();
	}
	showUserInfo();
	document.getElementById("nav-item_7").style.backgroundColor = "#ffffff";
	document.getElementById("font_color7").style.color = "#0066cc";
});
/**
 * 显示iFrame
 * @param id
 */
function showFrame(id){
	if(id=='1'){
		$("#mainFrame").attr("src","/gbdbas/view/sellcenter/product.jsp");
	}
	if(id=='2'){
		$("#mainFrame").attr("src","/gbdbas/view/sellcenter/orderList.jsp");
	}
}
/**
 * 显示用户信息
 */
function showUserInfo(){
	if(user!=null){
		$("#myInfo input").attr('readonly',true);
		$("#myInfo input").css({
			'border':'none',
		    'background-color':'#DBEAF7'
		});
		$("#myLoginName").text(user.loginName);
		$("#registertime").text(user.registertime);
		$("#endlogintime").text(user.endLoginTime);
		$("#myEmial").text(user.email);
	}
}
/**
 * 填充个人信息
 */
function fillInfo(){
	//为了防止session用户的数据的修改，导致的数据不一致
    //每次填充用户信息时都根据用户id进行重新查询一次
	if(user!=null){
		queryUserById(user.userId);
		$("#userId").val(user.userId);
		$("#loginName").val(user.loginName);
		$("#userName").val(user.userName);
		$("#email").val(user.email);
		$("#unitName").val(user.unitName);
		$("#phone").val(user.phone);
		$("#qq").val(user.qq);
		$("#qq").val(user.qq);
		$("#address").text(user.address);
		$("#postalNum").val(user.postalNum);
		$("#tel").val(user.tel);
		//填充地址信息
	    $('#country').val(user.country);	     
	    $('#province').val(user.province);
	    $('#city').val(user.city);
		//出现化 地址三级级联下拉框
		initMenu('country','province','city');
		openDivArtDialog('完善个人资料', 'recInfo', 'recInfo', 800, 600,true);
	}else{
		location.href="/gbdbas/view/login/infobase/login.jsp"
	}
	
}
/**
 * 修改用户信息
 */
function subRecForm(){
	 $('#upUserForm').form('submit', {  
	       onSubmit: function(){ 
	         return $(this).form('validate'); 
	      } ,  
	      success:function(data){  
				 if(data){
					 queryUserById(user.userId);
					 upSessionUser(user.userId);
					 showUserInfo();
					 art.dialog({id:'recInfo'}).close();
				 }
			 } 
	}); 
}
/**
 * 清理form表单
 */
function clearRecForm(){
	art.dialog({id:'recInfo'}).close();
}
/**
 * 获取当前登录用户的信息
 */
function queryUserById(userId){
	//异步获取session里的用户填充到用户全局变量
		 $.ajax({
			 type:'post',
			 url:'/gbdbas/userManager/queryUserById',
			 data:{userId:userId},
			 dataType:'json',
			 async:false,
			 success:function(data){
				 user=data;
			 } 
		 });
}

