var addrInfo=null;//用户地址信息
var orderInfo=null;//用户订单信息
var userInfo=null; //获取当前用的email
var orderNo;
$(document).ready(function(){
	var theRequest = getRequest(); 
	 orderNo = theRequest['orderNo']; 
	 //查询当前sessio用户
	 userInfo=querySessionUser();
	 //加载用户和订单信息
	 initInfo(orderNo);
	 //加载明细信息
	initDetTable(orderNo);
	//将用户信息显示在页面上
	initJsp();
});
/**
 * 根据code和类型查询视图
 */
function  queryView(code,type){
	var value=null;
	//异步获取视图
	$.ajax({
		type:'post',
		url:'/gbdbas/sellCenter/queryCountryMenu?type='+type+'&code='+code,
		dataType:'json',
		async:false,
		success:function(data){
			if(data!=null&&data.length==1){
				value=data[0].value;
			}
		}
	});
	return value;
}

/**
 * 初始化订单信息
 */
function initInfo(orderNo){
	if(orderInfo==null){
		queryOrderInfo(orderNo);
	}
	//当orderInfo不是空时,查询用户地址信息
	if(addrInfo==null){
		queryaddrInfo();
	}
}
/**
 * 查询用户信息
 * @param infoId
 */
function queryaddrInfo(){
	$.ajax({
		type:'post',
		url:'/gbdbas/sellCenter/queryReceiverInfo',
		dataType:'json',
		async:false,
		success:function(data){
			addrInfo=data.recInfo
		}
	});
}
/**
 * 初始化明细表
 * @param orderNo
 */
function initDetTable(orderNo){
	//加载订单明细
	$('#detTable').datagrid({
		url:'/gbdbas/sellCenter/queryOrderDetByNo',
		queryParams:{orderNo:orderNo},
		width:847,
		 'pagination':false,
         'rownumbers':true,
		columns : [dataArra]
	});
}
/**
 * 根据订单号查询订单信息
 */
function queryOrderInfo(orderNo){
	$.ajax({
		type:'post',
		url:'/gbdbas/sellCenter/queryOrderInfoByNo',
		data:{orderNo:orderNo},
		dataType:'json',
		async:false,
		success:function(data){
			orderInfo=data.order
		}
	});
}
 /**
 * 初始化jsp页面的值
 */
function initJsp(){
	$("#consignee").val(addrInfo.consignee);
	$("#orderNo").val(orderInfo.orderNo);
	$("#orderName").val(orderInfo.orderName);
	var country=queryView(addrInfo.country,'nation');
	var province=queryView(addrInfo.province,'province');
	var city=queryView(addrInfo.city,'city');
	$("#country").val(country);
	$("#province").val(province);
	$("#city").val(city);
	$("#company").val(addrInfo.company);
	$("#address").val(addrInfo.address);
	$("#postalNum").val(addrInfo.postalNum);
	$("#phone").val(addrInfo.phone);
	$("#email").val(userInfo.email);
	$("#orderNum").val(orderInfo.orderNum);
	$("#invoiceType").val(orderInfo.invoiceType);
	$("#invoice").val(orderInfo.invoice);
	$("#orderSummary").val(orderInfo.orderSummary);
	if(orderInfo.orderStatus=="1"){
		$("#hasPay").hide();
		$("#noPay").show();
	}
	else{
		$("#noPay").hide();
		$("#hasPay").show();
	}
	if(orderInfo.invoiceType!="0"){
		$("#invoiceType").val("是")
		$("#showInvoice").show();
		if(orderInfo.invoiceType=="1"){
			$("#type").val("增值税发票");
		}else if(orderInfo.invoiceType=="2"){
			$("#type").val("普通发票");
		}
	}
	else{
		$("#invoiceType").val("否")
		$("#showInvoice").hide();
	}
}
//数据数组
var dataArra=[ 
	            {field:"byType", width:'100', align:"center",title:"定制类型"},
	            {field:"hscode", width:'100', align:"center",title:"海关编码"},
	            {field:"prodesc", width:'100', align:"center",title:"产品描述"},
	            {field:"country", width:'100', align:"center",title:"国家"},
	            {field:"ioType", width:'100', align:"center",title:"进出口类型"},
	            {field:"tlimit", width:'160', align:"center",title:"时间"},
	            {field:"times", width:'60', align:"center",title:"次数"},
	            {field:'price',width:'100', align:"center",title:"金额"}
          ]
/**
 * 获取url地址的参数
 * @returns {Object}
 */
function getRequest() { 
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object(); 
	if (url.indexOf("?") != -1) { 
	var str = url.substr(1); 
	strs = str.split("&"); 
	for(var i = 0; i < strs.length; i ++) { 
	theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
	} 
	} 
	return theRequest; 
} 
/**
 * 付款
 */
function payOrder(){
	//订单号
	$("#out_trade_no").val(orderInfo.orderNo);
	//总价
	$("#total_fee").val(orderInfo.orderSummary);
	//提交订单
	$("#payMoney").submit();  
}
