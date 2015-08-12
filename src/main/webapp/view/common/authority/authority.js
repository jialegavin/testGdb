//js 模块控制
 function subModuleControl(url,country){
	 var isTrue = true;
		$.ajax({
			url:'/gbdbas/ConditRightController',
			dataType: "json",
		    type: "post",
		    async: false,
		    data:{"url":url,"country":country},
		    success: function (data) {
		    	// js 权限
		    	if(data.error != undefined){
					$.messager.alert("提示",data.error);
					isTrue = false;
				}
		    }
		});
		if(isTrue){
			return true;
		}
		return false;
 }
 
 function authorityControl(url){
	 var obj = null;
	 var isTrue = true;
		$.ajax({
			url:'/gbdbas/ConditRightController',
			dataType: "json",
		    type: "post",
		    async: false,
		    data:{"url":url},
		    success: function (data) {
		    	obj = data.user_desc;
		    }
		});
	return obj;	
 }