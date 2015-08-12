
/**
 * 作者：陈虎
 * 订单列表
 */
$(document).ready(function(){
	initODTabel();
});

function initODTabel(){
	$('#odTable').datagrid({
            url:'/gbdbas/sellCenter/queryOrders',
             width: 843,  
             height:400,
            'pagination':true,
            'rownumbers':true,
            'pageList':[10,20,30,40],
            'pageSize':10,
            'pageNumber':1,
	 		columns : [orArr]
	});
}
var orArr=[
           {field:"orderId", hidden:true, width:100, align:"center",title:"订单编号"},
           {field:"userId", hidden:true, width:100,align:"center",title:"用户编号"},
           {field:"orderNo", hidden:false,width:100, align:"center",title:"订单编号"},
           {field:"orderTime", hidden:false, width:205,align:"center",title:"订单时间"},
           {field:"orderSummary", hidden:false,width:100, align:"center",title:"订单金额"},
           {field:"consignee", hidden:false,width:100, align:"center",title:"收货人"},
           {field:"orderDet", hidden:false, width:100,align:"center",formatter:forOrderDet,title:"订单信息"},
           {field:"orderStatus", hidden:false, width:100,align:"center",title:"付款状态"       },
           {field:"accountopt", width:100,align:"center" ,formatter:operOrder,title:"操作"}
           ];
/**
 * 订单操作
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {String}
 */
function operOrder(value,rowData,rowIndex){
	return  "&nbsp;<a title=\""+"删除订单"+"\" href=\"#\" mce_href=\"#\" style=\"color: #939a9d;font-family: Microsoft YaHei;\" onmouseover=\"this.style.textDecoration='underline';this.style.color='#086fac'\" onMouseOut=\"this.style.textDecoration='none';this.style.color='#939a9d'\" onclick=\"delOrder('"+rowData.orderNo+"')\">"+"删除订单"+"</a>&nbsp;";
}
/**
 * 为跳到订单详情页面
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {String}
 */
function forOrderDet(value,rowData,rowIndex){
	return  "&nbsp;<a title=\""+"查看订单"+"\" href=\"#\" mce_href=\"#\" style=\"color: #939a9d;font-family: Microsoft YaHei;\" onmouseover=\"this.style.textDecoration='underline';this.style.color='#086fac'\" onMouseOut=\"this.style.textDecoration='none';this.style.color='#939a9d'\" onclick=\"toOrderDet('"+rowData.orderNo+"')\">"+"订单详情"+"</a>&nbsp;";
}
/**
 * 删除订单
 * @param orderId
 */
function delOrder(orderNo){
	$.ajax({
		type:'post',
		url:'/gbdbas/sellCenter/deleteOrderByNo',
		data:{orderNo:orderNo},
		dataType:'json',
		async:false,
		success:function(data){
			
		}
	});
	$('#odTable').datagrid('reload');
}
/**
 * 订单详情页面
 * @param orderNo
 */
function toOrderDet(orderNo){
   //跳转到订单详情页面+orderNo
   window.open('orderInfo.jsp?orderNo='+orderNo);
}
/**
 * 查询订单
 */
function searchOrder(){
	//订单号
	var orderNo=$("#orderNo").val();
	$('#odTable').datagrid({
        url:'/gbdbas/sellCenter/queryOrderByBlurNo?orderNo='+orderNo,
        'pagination':true,
        'rownumbers':true,
        'pageList':[10,20,30,40],
        'pageSize':10,
        'pageNumber':1,
 		columns : [orArr]
});
	
}