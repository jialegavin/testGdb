/**
 * 作者：陈虎
 */
//保存最新的用户地址
var addrInfo=null;
//订单号
var orderNo=null;
//订单
var order=null;

$(document).ready(function(){
	//初始化清空form
	$("#addrForm").form("clear");
	$("#odForm").form("clear");
	//初始化用户地址信息
	initAddrInfo();
	//出现化 地址三级级联下拉框
	initMenu('country','province','city');
	//初始化订单信息
	initOrderInfo();
});
/**
 * 初始化用户地址信息
 */
function initAddrInfo(){
      //查询用户的默认地址信息
	  queryAddrInfo();
	 //加载页面时如果用户存在默认地址信息,则将地址信息显示给用户看，并且不可编辑
	   if((addrInfo!=null)){
		   //填充地址信息
		   fillAddrInfo();
	   }
}
/**
 * 填充地址信息
 */
function fillAddrInfo(){
	//如果默认地址信息不为空
	if(addrInfo!=null&&addrInfo!=undefined){
		 $("#company").val(addrInfo.company);
		 $("#consignee").val(addrInfo.consignee);
		 $("#province").val(addrInfo.province);
		 $("#city").val(addrInfo.city);
		 $("#country").val(addrInfo.country);
		 $("#postalNum").val(addrInfo.postalNum);
		 $("#phone").val(addrInfo.phone);
		 $("#address").val(addrInfo.address);
		 $("#rid").val(addrInfo.rid)
	}
	return ;
}
/**
 *设置地址的全局变量
 */ 
function queryAddrInfo(){
		//1.根据登录用户ID 获取当前用户的地址 查看是否默认
		$.ajax({
			type:'post',
			url:'/gbdbas/sellCenter/queryReceiverInfo',
			dataType:'json',
			async:false,
			success:function(data){
				addrInfo=data.recInfo;
			}
		});
}
/**
 * 初始化订单信息
 */
function initOrderInfo(){
	var theRequest = getRequest(); 
	var orderNo = theRequest['orderNo']; 
	//根据订单获取该订单的全部信息
	queryOrderByNo(orderNo);
	initPayType();
}
/**
 *根据订单号查询订单
 * @param orderNo
 */
function queryOrderByNo(orderNo){
	if(orderNo!=null){
		$.ajax({
			type:"post",
			url:'/gbdbas/sellCenter/queryOrderInfoByNo',
			data:{orderNo:orderNo},
			dataType:'json',
			async:false,
			success:function(data){
				order=data.order;
         	$("#sumMoney").val(order.orderSummary);
			}
		});
	}else{
		 $.messager.alert('订单错误',"请重新定制");
	}
}
/**
 * 初始化支付方式
 */
function initPayType(){
	//是否要开发票
	 $("input[name=invoiceType]").click(function(){
		     if($(this).val()!='0'){
		    	 $('#invoiceInfo').show();
		     }else{
		    	 $('#invoiceInfo').hide();
		     }
		 });
}
/**
 * 提交订单
 */
function subOrder(){
     //再次获取地址信息 绑定到订单上
	queryAddrInfo();
	if(addrInfo==null){
		$.messager.alert('提示',"请设置默认地址");
		 return ;
	}
	order.rid=addrInfo.rid;
	//获取支付方式,并绑定到订单上
     var payType=$('input[name="payType"]:checked').val();
     order.payType=payType;
     //如果为线下，则添加汇款人名称
     if(payType=='unLine'){
    	 var paypepole=$("#paypepole").val();
    	 if(paypepole==""){
    		 $.messager.alert('提示',"务必填写汇款人姓名");
    		 return ;
    	 }
    	 order.payPepole=paypepole;
     }
	//发票方式
     var invoiceType=$('input[name="invoiceType"]:checked').val();
     order.invoiceType=invoiceType;
     if(invoiceType=='1'){
    	 var invoice=$("#invoice").val();
    	 if(invoice==""){
    		 $.messager.alert('提示',"务必填写汇款人姓名");
    		 return ;
    	 }
    	 order.invoice=invoice;
     }
     else{
    	 order.payType=false;
     }
	//提交订单
     $.ajax({
    	 type:'post',
    	 url:'/gbdbas/sellCenter/upOrderInfo',
    	 data:order,
    	 dataType:'json',
    	 async:false,
    	 success:function(data){
    		location.href='/gbdbas/view/sellcenter/orderInfo.jsp?orderNo='+order.orderNo;
    	 }
     });
}
/**
 * 修改
 * 判断用户第一次进入该页面是否具有默认地址，
 * 如果没有则插入一条新的用户地址信息并设置为默认
 * 如果有默认地址,则修改用的默认地址信息
 */
function upAddr(){
	//operflag 如果为true 则修改 如果为false 则新增
	var operFlag=true;
    //首先获取当前用的修改信息
	var company=$("#company").val();
	var consignee=$("#consignee").val();
	var country=$("#country").combobox("getValue");
	var province=$("#province").combobox("getValue");
	var city=$("#city").combobox("getValue");
	var address=$("#address").val();
	var postalNum=$("#postalNum").val();
	var phone=$("#phone").val();
	//如果用户地址信息为空
	if(addrInfo==null){
		addrInfo={};
		operFlag=false;
	}
	addrInfo.company=company;
	addrInfo.consignee=consignee;
	addrInfo.country=country;
	addrInfo.province=province;
	addrInfo.address=address;
	addrInfo.postalNum=postalNum;
	addrInfo.phone=phone;
	addrInfo.operFlag=operFlag;
	addrInfo.city=city;
	$.ajax({
		type:'post',
		url:'/gbdbas/sellCenter/operAddrInfo',
		data:addrInfo,
		dataType:'json',
		async:false,
		success:function(data){
			addrInfo=data.userInfo;
			 $('#forUp').hide();
			 $('#forShow').show();
			 $('#a1').hide();
			 $('#a2').show();
			 $.messager.alert('提示',"设置成功");
		}
	});
	initUserInfo();
}
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

