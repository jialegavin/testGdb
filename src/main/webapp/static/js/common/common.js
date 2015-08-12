
/**
 * js去除空格的方法
 * @returns
 */
String.prototype.trim = function()
{
    // 用正则表达式将前后空格
    // 用空字符串替代。
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
/**
 * 获取当前session 里的用户
 */
function querySessionUser(){
	var user=null;
	//异步获取session里的用户填充到用户全局变量
	 $.ajax({
		 type:'post',
		 url:'/gbdbas/userManager/querySessionUser',
		 dataType:'json',
		 async:false,
		 success:function(data){
			 //如果为空跳转到登录页面
			 user=data;
		 } 
	 });
	 return user;
}
/**
 * 修改session里的用户
 */
function upSessionUser(userId){
	//异步修改session里的用户填充到用户全局变量
	 $.ajax({
		 type:'post',
		 url:'/gbdbas/userManager/upSessionUser',
		 data:{userId:userId},
		 dataType:'json',
		 async:false,
		 success:function(data){
			 
		 } 
	 });
}