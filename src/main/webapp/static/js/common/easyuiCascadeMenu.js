//easyUi三级级联菜单  作者：chenhu
var menu1Data;
var menu2Data;
var menu3Data;
/**
 * 
 * @param m1(1级菜单)
 * @param m2(2级菜单)
 * @param m3(3级菜单)
 */
function initMenu(m1,m2,m3){
	var value1=$("#"+m1).val();
	if(value1==""||value1==undefined){
		value1='c1';
	}
	var srmCombx = $("#"+m1).combobox({  
		loader:function(param,success,error){  
		    $.ajax({  
						url: '/gbdbas/sellCenter/queryCountryMenu?type=nation&code='+value1,  
						dataType: 'json',  
						success: function(data){  
						data.unshift({code:'',value:'--请选择--'});  
						success(data);  
						},  
						error: function(){  
						   error.apply(this, arguments);  
						}  
				});  
		   },  
		   onSelect:function(record){  //onSelect 用户点击时触发的事件  在此的意义在于，用户点击一级后自动二级combobox  
		      piperowCombx.combobox({  
				loader:function(param,success,error){  
						    $.ajax({  
									url:'/gbdbas/sellCenter/queryCascdeMenu?type=province&rela='+$("#"+m1).combobox("getValue")+'',  
									dataType: 'json',  
									success: function(data){  
									data.unshift({code:'',value:'--请选择--'});  
									     success(data);  
									},  
								    error: function(){  
									  error.apply(this, arguments);  
									}  
								});  
				             },  
			   onSelect:function(record){ //这里也使用了onSelect事件，在二级combobox被用户点击时触发三级combobox  
											pipeCombx.combobox({  
											loader:function(param,success,error){  
											    $.ajax({  
											url: '/gbdbas/sellCenter/queryCascdeMenu?type=city&rela='+$("#"+m2).combobox("getValue")+'',  
											dataType: 'json',  
											success: function(data){  
											data.unshift({code:'',value:'--请选择--'});  
											success(data);  
											},  
											               error: function(){  
											error.apply(this, arguments);  
											}  
											});  
											   },  
											   valueField: 'code',     
											   textField: 'value',  
											   value:'',  
											   editable:false  
											});  
		                          },  
							      onLoadSuccess:function(){  //清空三级下拉框 就是成功加载完触发的事件 当一级combobox改变时，二级和三级就需要清空  
											    pipeCombx.combobox("clear");  
											    pipeCombx.combobox('setValue', '--请选择--'); //给combobox下拉框设置一个值，否则为空不好看  
											   },  
											   valueField: 'code',     
											   textField: 'value',  
											   value:'',  
											   editable:false  
											}).combobox("clear"); //清空二级下拉框  
											  
											   },  
			   valueField: 'code',     
			   textField: 'value',  
			   editable:false  
		});  
					/******************************************************************************************************/  
					//下面的俩个是组件，  
					  
					//  二层Combo  
var piperowCombx = $("#"+m2).combobox({  
    loader:function(param,success,error){  
					    $.ajax({  
								url: '/gbdbas/sellCenter/queryCountryMenu?type=province&code='+$("#"+m2).combobox("getValue")+'',    
								dataType: 'json',  
								success: function(data){ 
							   data.unshift({code:'',value:'--请选择--'});
								success(data);  
								},  
								               error: function(){  
								error.apply(this, arguments);  
								}  
				      	});  
		   },  
		   onSelect:function(record){ //这里也使用了onSelect事件，在二级combobox被用户点击时触发三级combobox  
				pipeCombx.combobox({  
				loader:function(param,success,error){  
				    $.ajax({  
				url: '/gbdbas/sellCenter/queryCascdeMenu?type=city&rela='+$("#"+m2).combobox("getValue")+'',  
				dataType: 'json',  
				success: function(data){  
				data.unshift({code:'',value:'--请选择--'});  
				success(data);  
				},  
				               error: function(){  
				error.apply(this, arguments);  
				}  
				});  
				   },  
				   valueField: 'code',     
				   textField: 'value',  
				   value:'',  
				   editable:false  
				});  
     },
    valueField: 'code',     
    textField: 'value',  
    editable:false  
		});  
		  
		//三层Combo  
var pipeCombx=$("#"+m3).combobox({  
		loader:function(param,success,error){  
		    $.ajax({  
					url: '/gbdbas/sellCenter/queryCountryMenu?type=city&code='+$("#"+m3).combobox("getValue")+'',  
					dataType: 'json',  
					success: function(data){  
				    data.unshift({code:'',value:'--请选择--'}); 
					success(data);  
					},  
					               error: function(){  
					error.apply(this, arguments);  
					}  
					});  
		   },  
		 
	   valueField: 'code',     
	   textField: 'value',  
	   editable:false  
	});  
}