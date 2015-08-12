/**
 * 作者：陈虎
 */
var userId='123';

var countries=new Array();
var ioTypes=new Array();
//初始化
$(document).ready(function(){
	//初始化显示hs购买页面
	initCountryData();
	showBox('hs');
	$('#car_table').datagrid({
		height:260,
		columns : [dataArra]
	});
});
var dataArra=[ 
                {field:'ck',checkbox:true},
	            {field:"byType", width:'100', align:"center",title:"定制类型"},
	            {field:"hscode", width:'100', align:"center",title:"海关编码"},
	            {field:"desc",   width:'122', align:"center",title:"产品描述"},
	            {field:"country", width:'100', align:"center",title:"国家"},
	            {field:"ioType", width:'80', align:"center",title:"进出口类型"},
	            {field:"tlimit", width:'140', align:"center",title:"时间"},
	            {field:"times", width:'40', align:"center",title:"次数"},
	            {field:'price',width:'100', align:"center",title:"金额"}
          ]

/**
 * 初始化视图数据
 * @param sId(select标签的ID)
 * @param dId(div标签的Id)
 */
function initCountryData(){
	//防止重复读取数据
	if(countries.length==0){
		//获取国家视图列表
		$.ajax({
			type:'post',
			url:'/gbdbas/sellCenter/queryViewByName?viewName=counData',
			dataType:'json',
			async:false,
			success:function(data){
				data.unshift({code:'',value:'--请选择--'});  
				if(data!=null&&data.length>0){
					countries=data;
				}
			}
		});
	}
	//防止重复读取数据
	if(ioTypes.length==0){
		//获取进出口视图列表
		$.ajax({
			type:'post',
			url:'/gbdbas/sellCenter/queryViewByName?viewName=ioType',
			dataType:'json',
			async:false,
			success:function(data){
				data.unshift({code:'',value:'--请选择--'});  
				if(data!=null&&data.length>0){
					ioTypes=data;
				}
			}
		});
	}
}
/**
 * 根据Id显示页面
 * @param id
 */
function showBox(id){
	//隐藏所有
	hideAll();
	//显示单个Div
	initDiv(id)
}
/**
 * 初始化一个页面
 */
function initDiv(id){
	$("#"+id).show();
	initCountry(id);
	initIoType(id);
}
/**
 * 展示国家下拉框选则
 * @param id 主Div Id
 */
function initCountry(id){
	$("#"+id+"_country").combobox({
		valueField:'code',    
	    textField:'value',
	    data:countries,
	    editable:false
	});    
}
/**
 * 展示进出口下拉框
 * @param id
 */
function initIoType(id){
	$("#"+id+"_io").combobox({
		valueField:'code',    
	    textField:'value',
	    data:ioTypes,
	    editable:false
	});  
}

/**
 * 添加一行
 */
function addCar(id){
	var flag=true;
   //获取商品描述的值
	var de="-";
	var hs="-";
	var cy='-';
	var tlimit='-';
	var times='-';
	var byType='-';
    var ioType='-';
	//当不是按次购买时
	if(id!='nm'){
		//购买的国家
		cy= $('#'+id+'_country').combobox('getText');
		if((cy=='')||(cy =='--请选择--')){
			 $.messager.alert('提示',"国家不能为空!");
				return ;
		}
		//进出口类型
		ioType=$("#"+id+"_io").combobox('getText');
		if((ioType=='')||(ioType=='--请选择--')){
			 $.messager.alert('提示',"进出口类型不能为空!");
			    return ;
		}
	    //开始时间
	    var beginTime = $('#'+id+'_beginTime').val().trim();
	    //结束时间
	    var endTime = $("#"+id+"_endTime").val().trim();
	  //生成按条件购买表格
		if(id=='hs'){
			hs=$("#hs_code").val();
			if(hs==''){
				 $.messager.alert('提示',"海关编码不能为空");
				return ;
			}
			byType="按HSCODE定制";
		}
		if(id=='de'){
			de=$("#de_desc").val();
			if(de==''){
				 $.messager.alert('提示',"产品描述不能为空");
				return ;
			}
			byType="按产品描述定制"
		}
		if(id=='cy'){
			byType="按国家定制";
		}
		//判断时间是否为空
	    flag=checkData(beginTime,endTime);
		tlimit=beginTime+'/'+endTime;
	}
	else{
		byType="按次数定制";
		times=$("#times").val();
		if(times==''){
			 $.messager.alert('提示',"购买次数不能为空");
			return ;
		}
	}
	 //商品价格计算
	  var price=payRules();
	  //按次添加
	  if(flag){
		  $("#car_table").datagrid('appendRow',
				  {
			  'byType':byType,
			  'hscode':hs,
			  'desc':de,
			  'country':cy,
			  'tlimit':tlimit,
			  'times':times,
			  'ioType':ioType,
			  'price':price
				  }	 
		  ); 
		  var formId=id+"_Form";
			 $("#"+formId).form('clear');
	  }
}

/**
 * 清理表格内容
 */
function cancle(id){
	$("#"+id).form('clear');
}
/**
 * 计算出按次购买的总价钱
 */
function checkNum(){
	var test=/^[0-9]*$/;
	var txt=$("#times").val();
	if(!test.test(txt.trim()))
		{
		  alert('请输入0-9的数字');
		  $("#times").val("");
           return;
		}
}
/**
 * 购买
 * @param tid
 */
function buy(tid){
}
/**
 * 产品价格计算规则
 * @param id(定制方式)
 */
function payRules(id){
	//当为HS规则时
	if(id=='hs'){
		
	}
	return 10.92;
}
/**
 * 隐藏所有div
 */
function hideAll(){
	$("#hs").hide();
	$("#de").hide();
	$("#cy").hide();
	$("#nm").hide();
}
/**
 * 购买产品
 */
function buyPro(id){
	var orderNo="";
  //将订单明细存库
	var selectRows=new Array();
    //获取所有选中的表格的ID
   var rows=$('#'+id).datagrid('getChecked');
   if(rows.length>0){
	   for(var j=0;j<rows.length;j++){
		   selectRows.push(rows[j]);
	   }
	}else{
		 $.messager.alert('提示',"购物车为空不能提交订单");
		return;
	}
	var selectRigth={"dets":selectRows};
	  $.ajax({
		  type:'post',
		   url:'/gbdbas/sellCenter/addNewOrder',
		   dataType:'json',
		   data:{data:JSON.stringify(selectRigth)},
		   async:false,
		   success:function(data){
			  //返回订单号，在打开一个新的窗口时 将订单号带入
			   if(data!=""){
				   orderNo=data.orderNo;
				   //跳转到填写订单页面 
					  window.open('addOrder.jsp?orderNo='+orderNo);
			   }
			   else{
				   $.messager.alert('提示',"订单提交");
			   }
			   deletePros(); 
		   }
	  });
}
function deletePros(){
	var rows=$('#car_table').datagrid('getChecked');
	if (rows) {
             for(var i=0;i<rows.length;i++){
            	 var rowIndex = $('#car_table').datagrid('getRowIndex', rows[i]);
    	         $('#car_table').datagrid('deleteRow', rowIndex);  
             }		
	 }
}
