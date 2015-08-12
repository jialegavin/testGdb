//翻译数组
var lang="";
var findArray = initParam();
var reportArray = initReport();
var promptArray=promptmessageReport();
/**
 * 根据后台返回的数据显示提示框
 * 
 * @param data
 * @return
 */
function showpromptmessage(data) {
	var bTrue = parseInt(data);
	if (bTrue <= 0) {
		$('#screenSelect').css("display",'none');
		alertMessage(findArray[6], findArray[8]);
		return false;
	}
	return true;
}

/**
 * 区间价格
 * 
 * @param pv
 *            页面id
 * @param pbeginpriceflex
 *            开始价格
 * @param pendpriceflex
 *            结束价格
 * @return 返回json数据格式 sectionprice(参数值 )['pbegin'] / ['pend'] 取值
 */
function sectionprice(pv, pbeginpriceflex, pendpriceflex) {
	if (pv.id == 'myQujian' || pv.id == 'fobQujian' || pv.id == 'cifQujian') {
		if (pv.value == 1) {
			pbeginpriceflex = pendpriceflex;
			pendpriceflex = '10000000000';
		} else if (pv.value == 2) {
			if (parseFloat(pbeginpriceflex) < 0) {
				$.messager.alert(findArray[6], reportArray[13]);
				return "false";
			} else {
				pendpriceflex = pbeginpriceflex;
				pbeginpriceflex = '0';
			}
		}
	}
	var price = {
		"pbegin" : pbeginpriceflex,
		"pend" : pendpriceflex
	};
	return price;
}

/**
 * honghao 区间价格
 * 
 * @param pv
 *            页面id
 * @param pbeginpriceflex
 *            开始价格
 * @param pendpriceflex
 *            结束价格
 * @param type
 *            单价类型
 * @return 返回json数据格式 sectionprice(参数值 )['pbegin'] / ['pend'] 取值
 */
function getprice(pv, pbeginpriceflex, pendpriceflex, type) {
	if (pv.id == 'myQujian' || pv.id == 'fobQujian' || pv.id == 'cifQujian') {
		if (pv.id == type) {
			if (pv.value == 1) {
				pbeginpriceflex = pendpriceflex;
				pendpriceflex = '10000000000';
			} else if (pv.value == 2) {
				if (parseFloat(pbeginpriceflex) < 0) {
					$.messager.alert(findArray[6], reportArray[13]);
					return "false";
				} else {
					pendpriceflex = pbeginpriceflex;
					pbeginpriceflex = '0';
				}
			}else if (pv.value == 3) {
			    pbeginpriceflex = '0';
			    pendpriceflex = '10000000000';
		    }
		} else {
			var index;
			if (type == 'myQujian') {
				index = $('#myQujian').val();
			} else if (type == 'fobQujian') {
				index = $('#fobQujian').val();
			} else if (type == 'cifQujian') {
				index = $('#cifQujian').val();
			}
			var cifSelectItem = document.getElementById(type);
			var value = cifSelectItem.options[index].value;
			if (value == 1) {
				pbeginpriceflex = pendpriceflex;
				pendpriceflex = '10000000000';
			} else if (value == 2) {
				if (parseFloat(pbeginpriceflex) < 0) {
					$.messager.alert(findArray[6], reportArray[13]);
					return "false";
				} else {
					pendpriceflex = pbeginpriceflex;
					pbeginpriceflex = '0';
				}
			}else if (value == 3) {
			    pbeginpriceflex = '0';
			    pendpriceflex = '10000000000';
		    }
		}
	}
	var price = {
		"pbegin" : pbeginpriceflex,
		"pend" : pendpriceflex
	};
	return price;
}

/**
 * 页面总体验证
 * 
 * @param phscodeflex
 *            海关编码
 * @param pgoods_descriptionflex
 *            产品描述
 * @return
 */
function validatesummary(phscodeflex, pgoods_descriptionflex) {
	
	// 对海关编码校验正则
    var test = /^\d{6,10}$/;
    var hscodeArray =phscodeflex.split(",");
	if (phscodeflex == 'undefined') {
		alertMessage(findArray[6], promptArray[0],"info");
		return false;
	}
	if (pgoods_descriptionflex == 'undefined') {
		alertMessage(findArray[6], promptArray[1],"info");
		return false;
	}
	if (phscodeflex == null || phscodeflex == "" || phscodeflex == "请单击右侧添加海关编码") {
		alertMessage(findArray[6], promptArray[0],"info");
		return false;
	}
	else
	{
		for(var i =0;i<hscodeArray.length;i++){
			if(!test.test(hscodeArray[i])){

				alertMessage(findArray[6],findArray[15],'info');

				return false;
			}
		}
	}
	// 对产品描述校验
	if (pgoods_descriptionflex.trim() != "" && pgoods_descriptionflex !=null && pgoods_descriptionflex.length < 2) {
		alertMessage(findArray[6], findArray[16], "info");
		return false;
	}
	return true;
}

/**
 * 页面总体验证
 * 
 * @param blnumber
 *            提单号
 * @param pgoods_descriptionflex
 *            产品描述
 * @return
 */
function validatesummaryblnumber(phscodeflex, pgoods_descriptionflex) {
	if (phscodeflex == 'undefined') {
		alertMessage(findArray[6],promptArray[2]);
		return false;
	}
	if (pgoods_descriptionflex == 'undefined') {
		alertMessage(findArray[6], promptArray[1]);
		return false;
	}
	if ((phscodeflex == null || phscodeflex == "")
			&& (pgoods_descriptionflex == null || pgoods_descriptionflex == "")) {
		alertMessage(findArray[6], promptArray[3]);
		return false;
	}
	if (pgoods_descriptionflex != null && pgoods_descriptionflex != "") {
		if (pgoods_descriptionflex.length < 2) {

			alertMessage(findArray[6], findArray[16], 'info');


			return false;
		}
	}
	return true;
}

/**
 * 页面总体验证
 * 
 * @param phscodeflex
 *            海关编码
 * @param pcompanyname
 *            公司名称
 * @return
 */
function validatesummarycompanyname(phscodeflex, pcompanyname) {
	if (phscodeflex == 'undefined') {
		alertMessage(findArray[6], promptArray[0],"info");
		return false;
	}
	if (pcompanyname == 'undefined') {
		alertMessage(findArray[6], promptArray[0],"info");
		return false;
	}
	if ((phscodeflex == null || phscodeflex == "")
			&& (pcompanyname == null || pcompanyname == "")) {
		alertMessage(findArray[6], promptArray[4],"info");
		return false;
	}
	return true;
}

/**
 * 文本框验证
 * 
 * @param pstart
 *            起始值
 * @param pend
 *            结束值
 * @param message
 *            提示信息
 * @param type
 *            验证类型
 * @param reporttype
 *            报表类型
 * @return
 */
function validateSize(pstart, pend, message, type, reporttype) {

	/**
	 * 验证重量
	 */
	if (type == 'weight') {
		var re = /^\d+(\.\d+){0,1}$/;
		if (pstart != null && pstart != '' && pstart != 'undefined') {
			if (!re.test(pstart)) {
				alertMessage(findArray[6], promptArray[5],"info");
				return false;
			}
		}

		if (pend != null && pend != '' && pend != 'undefined') {
			if (!re.test(pend)) {
				alertMessage(findArray[6], promptArray[6],"info");
				return false;
			}
		}

		if ((pstart != null || pstart != '' || pstart != 'undefined')
				&& (pend != null || pend != '' || pend != 'undefined')) {

			if (parseFloat(pstart) > parseFloat(pend)) {
				alertMessage(findArray[6], message,"info");
				return false;
			}
		}
	}
	// 验证金钱
	else if (type == 'money') {
		var re = /^\d+(\.\d+){0,1}$/;
		if (pstart != null && pstart != '' && pstart != 'undefined') {
			if (!re.test(pstart)) {
				alertMessage(findArray[6], promptArray[7],"info");
				return false;
			}
		}

		if (pend != null && pend != '' && pend != 'undefined') {
			if (!re.test(pend)) {
				alertMessage(findArray[6],promptArray[8],"info");
				return false;
			}
		}

		if ((pstart != null || pstart != '' || pstart != 'undefined')
				&& (pend != null || pend != '' || pend != 'undefined')) {
			if (parseFloat(pstart) > parseFloat(pend)) {
				alertMessage(findArray[6], message,"info");
				return false;
			}
		}
	}
	// 验证数量
	else if (type == 'quantity') {
		var re = /^\d+(\.\d+){0,1}$/;
		if (pstart != null && pstart != '' && pstart != 'undefined') {
			if (!re.test(pstart)) {
				alertMessage(findArray[6], promptArray[27],"info");
				return false;
			}
		}

		if (pend != null && pend != '' && pend != 'undefined') {
			if (!re.test(pend)) {
				alertMessage(findArray[6], promptArray[28],"info");
				return false;
			}
		}

		if ((pstart != null || pstart != '' || pstart != 'undefined')
				&& (pend != null || pend != '' || pend != 'undefined')) {

			if (parseFloat(pstart) > parseFloat(pend)) {
				alertMessage(findArray[6], message,"info");
				return false;
			}
		}
	}
	// 验证日期
	if (reporttype == 'ADDIMPORTER' || reporttype == 'REDURCEIMPORTER'
			|| reporttype == 'ADDEXPORTER' || reporttype == 'REDURCEEXPORTER'
			|| type == 'date') {
		if (pstart == null || pstart == '') {
			alertMessage(findArray[6], findArray[17],"info");
			return false;
		}
		if (pend == null || pend == '') {
			alertMessage(findArray[6], findArray[18],"info");
			return false;
		}
		var sDate = new Date(pstart);
		var eDate = new Date(pend);
		if (sDate > eDate) {
			alertMessage(findArray[6], message,"info");
			return false;
		}
	} 
	return true;
}

/**
 * 文本框时间验证
 * 
 * @param firstStart
 *            第一段日期起始值
 * @param firstEnd
 *            第一段日期结束值
 * @param secondStart
 *            第二段日期起始值
 * @param secondEnd
 *            第二段日期结束值
 * @param message
 *            提示信息
 * @param reporttype
 *            报表类型
 * @author honghao
 * @return
 */
function validateDate(firstStart, firstEnd, secondStart, secondEnd, message,
		reporttype) {
	// 验证第一段日期
	if (firstStart == null || firstStart == '') {
		alertMessage(findArray[6], findArray[17],"info");
		return false;
	}
	if (firstEnd == null || firstEnd == '') {
		alertMessage(findArray[6], findArray[18],"info");
		return false;
	}
	var fsDate = new Date(firstStart);
	var feDate = new Date(firstEnd);
	var btime = fsDate.getTime(firstStart);
	var etime = feDate.getTime(firstEnd);
	var time = parseInt(etime) - parseInt(btime);
	if (fsDate > feDate) {
		alertMessage(findArray[6], message,"info");
		return false;
	}
	// 验证时间段不超过一年
	if (time > 31536000000) {
		alertMessage(findArray[6], findArray[19],"info");
		return false;
	}
	// 判断当报告类型为新增，流失进口商，新增流失出口商时校验第二段日期
	if (reporttype == 'ADDIMPORTER' || reporttype == 'REDURCEIMPORTER'
			|| reporttype == 'ADDEXPORTER' || reporttype == 'REDURCEEXPORTER') {
		// 验证第二段日期
		if (secondStart == null || secondStart == '') {
			alertMessage(findArray[6], promptArray[9],"info");
			return false;
		}
		if (secondEnd == null || secondEnd == '') {
			alertMessage(findArray[6], promptArray[10],"info");
			return false;
		}
		var ssDate = new Date(secondStart);
		var seDate = new Date(secondEnd);
		var sbtime = ssDate.getTime(secondStart);
		var setime = seDate.getTime(secondEnd);
		var stime = parseInt(setime) - parseInt(sbtime);
		if (ssDate > seDate) {
			alertMessage(findArray[6], message,"info");
			return false;
		}
		// 验证新增时间不超过一年
		if (stime > 31536000000) {
			alertMessage(findArray[6], findArray[19],"info");
			return false;
		}
	}
	return true;
}

// ///////详情翻译下拉列表的方法////////////////
$(function() {
	var navTimeoutID, menu, navs;
	menu = $('.dropdown').children('ul');
	navs = $('.dropdown').has('ul').children('a');
	navs.mouseenter(function() {
		$(this).addClass("hover");
		menu.hide();
		var submenu = $(this).next();
		submenu.show();
		submenu.mouseleave(function() {
			$(this).hide();
		});
		submenu.mouseenter(function() {
			window.clearTimeout(navTimeoutID);
		});
	});
	navs.mouseleave(function() {
		var target = $(this);
		navTimeoutID = window.setTimeout((function(target) {
			return function() {
				target.removeClass("hover");
				target.next().hide();
			}
		}(target)), 250);
	});
});

// ///////详情的翻译方法//////////////////
function changeLanguage(id, value) {
	var ones = document.getElementById(id);
	if (ones) {
		ones.innerHTML = value;
	}
}

function changeTitle(id, value) {
	var tiltle = document.getElementById(id);
	if (tiltle) {
		$("#" + id).panel( {
			title : "<div style='height:38px;margin-left:-15px;padding-left:15px;font-size:16px;font-weight:normal;border-bottom: 2px solid #e5e9ed;'>"+value+"</div>"
		});
	}
}
function changeButton(id, value) {
	var button = document.getElementById(id);
	var butId = id;
	if (button) {
		$("#" + butId).linkbutton( {
			text : value
		});
	}
}

function readText(fileName) {
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
// alert(localhostPaht);
	var id = [];
	var filePath = localhostPaht+"/customer_search/view/chinese/search/lanuage/" + fileName + ".txt";
	$.ajax( {
		type : "POST",
		url : localhostPaht+"/customer_search/view/chinese/search/lanuage/id.txt",
		dataType : 'text',
		success : function(data) {
			id = data.split("\r");
			// alert(id);
		$.ajax( {
			type : "POST",
			url : filePath,
			dataType : 'text',
			success : function(data) {
				var language = data.split("\r");
				// alert(language);
			for ( var i = 0; i < id.length; i++) {
				if (id[i].indexOf("title") > 0) {
					changeTitle(id[i].trim(), language[i].trim());
				} 
// else if (id[i].indexOf("button") > 0) {
// changeButton(id[i].trim(), language[i].trim());
// }
				else {
					changeLanguage(id[i].trim(), language[i].trim());
				}
			}
		}
		});
	}
	});
}
// ///去空
String.prototype.trim = function() {
	// 用正则表达式将前后空格
	// 用空字符串替代。
	return this.replace(/(^\s*)|(\s*$)/g, "");
}



// XL 修改密码
function updatePassword(){
	openDivArtDialog(promptArray[11], 'pwdDlg', 'pwdDlg', 600, 300,true);
}
function saveUserPassword(){
	var url="/userPassword/updatePwd";
	var userPassword = $("#userPassword").val();
	var newPassword = $("#newPassword").val();
	var surePassword = $("#surePassword").val();
	if(userPassword==""||newPassword==""||surePassword==""){

		 $.messager.alert(findArray[6],promptArray[12]);


		 return;
	}
	if(newPassword!=surePassword){

		$.messager.alert(findArray[6],promptArray[13]);


		$("#surePassword").val("");
		return;
	}
	$('#pwdfm').form('submit',{
      url: url,
      onSubmit: function(){
          return $(this).form('validate');
      },
      success:function(data){
      	 var dataObj=eval("("+data+")");
      	if(dataObj.result==1){

     		 $.messager.alert(findArray[6],promptArray[14]);


     		 if(dataObj.domainname=='qy'){
     			 window.location.href="/customer_search/webInfo/trade_easy_login/login.jsp";
         	 }
     		 else{
     			 window.location.href="/customer_search/webInfo/infobase_login/login.jsp";
         		 }
     		 closePwd();
     	 }
     	 if(dataObj.result==2){

     		 $.messager.alert(findArray[6],promptArray[15]);


          	 $("#surePassword").val("");
     	 }
      }
     
	});
}

function calcelUpdatePwd(){
	$('#pwdfm').form('clear');
	$('#pwdDlg').dialog('close');
}


function onblurEvent(v)
{
	if(v.value.length<1)
	{
		v.value=v.defaultValue;
		v.style.color='gray';
	}
	else
	{
		v.style.color='black';
	}
}
function onfocusEvent(v)
{
	if(v.value==v.defaultValue)
	{
		v.value=''
	}
}

function changeFieldValue(searchParam)
{
	 if(searchParam == "请输入至少一个完整的单词"){
	    	searchParam = "";
	   }
	 return searchParam;
}

/*
 * 验证提单号为必填项 honghao
 */
function validateBillNumber(billNumber)
{
	 if(billNumber == null || billNumber == '')
	 {

		 alertMessage(findArray[6],promptArray[16]);


		 return false;
	  }
	 return true;
}
function initFindGrid(id){
	$(window).resize(function(){ 
		$("#"+id).width(document.documentElement.clientWidth);
		$('#mytable').datagrid('resize',{width:document.body.clientWidth});
	});
}

function initRepGrid(id){
	$(window).resize(function(){ 
		$("#"+id).width(document.documentElement.clientWidth);
		$('#dgFlexBr').datagrid('resize',{width:document.body.clientWidth});
		$('#showDataDiv').datagrid('resize',{width:document.body.clientWidth});
	});
}

function initHsGrid(id){
	$(window).resize(function(){ 
		$("#"+id).width(document.documentElement.clientWidth);
		$('#hisTable').datagrid('resize',{width:document.body.clientWidth});
		$('#hisResult').datagrid('resize',{width:document.body.clientWidth});
	});	
}

/**
 * 构造页面显示的参数,显示真实有值的参数,比如：hscode:230910 arrayValueObject:所有的查询条件对应的值
 * arrayNameObject:所有的查询条件对应的名称
 * 
 * @return
 */
function getDesplay(arrayValueObject,arrayNameObject,fieldName,linkColomValue)
{
	var aString = "<li>"+"&nbsp&nbsp"+"<font style='font-size: 16px;color: #EC5565;'>"+promptArray[17]+":</font></li></br>";
	aString += "<hr style='border: 1px solid #eb5465;width: 950px'/></br>";
	for(var i =0;i<arrayValueObject.length;i++){
		if (arrayValueObject[i] == null || arrayValueObject[i] == "" 
			|| arrayValueObject[i] == "请输入至少一个完整的单词" 
				|| arrayValueObject[i] == "==请选择=="
					|| arrayValueObject[i] == "请输入完整的提单号")
		{
			continue;
		}
		aString +="<li>"+"&nbsp&nbsp&nbsp&nbsp"+"<font color='#EC5565'>"+arrayNameObject[i]+":&nbsp&nbsp</font>"+arrayValueObject[i]+"</li></br>";
	}
	aString += "<li>"+"&nbsp&nbsp&nbsp&nbsp"+"<font color='#EC5565'>"+promptArray[18]+fieldName+promptArray[19]+":&nbsp&nbsp</font>"+linkColomValue+"</li>";
	return aString;
}

/**
 * 生成报告类型排除当前的报告类型 reportValue:待生成的报告英文名 reportValue:待生成的报告中文名
 * reportValueFlexComeFlex:当前的报告类型
 * 
 * @return
 */
function generateReportType(reportValueArray,reportNameArray,reportValueFlexComeFlex)
{
	var aString = "<select name='reportNameList' onchange='changeReportType()' id='reportNameList' style='width:204px;height:25px;' editable='false'>";
	var reportNowName;
	for(var i =0;i<reportValueArray.length;i++){
		if(reportValueArray[i] == reportValueFlexComeFlex)
		{
			continue;
		}else
		{
			if(reportValueFlexComeFlex == "TRANSACTIONTREND"
				|| reportValueFlexComeFlex =="IMPORTERTREND" 
				|| reportValueFlexComeFlex == "EXPORTERTREND"){

				reportNowName = reportArray[3] + reportNameArray[i];

				reportNowName = json.top + reportNameArray[i];
			}else{
				reportNowName =  reportNameArray[i];
			}
			aString += "<option value="+reportValueArray[i]+">"+reportNowName+"</option>";
		}
	}
	aString+="</select>";
	return aString
}

String.prototype.startWith = function(str) {
	if (str == null || str == "" || this.length == 0 || str.length > this.length)
		return false;
	if (this.substr(0, str.length) == str)
		return true;
	else
		return false;
	return true;
};

String.prototype.endWith = function(str) {
	if (str == null || str == "" || this.length == 0 || str.length > this.length)
		return false;
	if (this.substring(this.length - str.length) == str)
		return true;
	else
		return false;
	return true;
};
// 清空查询结果
function init(gid){
	var item = $('#'+gid+'').datagrid('getRows');
	if (item) {
		for (var i = item.length - 1; i >= 0; i--) {
			var index = $('#'+gid+'').datagrid('getRowIndex', item[i]);
			$('#'+gid+'').datagrid('deleteRow', index);
		}
	}  
}
// 获取选中的数据
function getSelectedValue(){
	var arr=new Array();
	var lang = $("input[name='lang']");
	for(var i=0;i<lang.length;i++){
		if(lang[i].checked){
			arr.push(lang[i].value);
		}
	}
	return arr;
	
}
// 判断数组长度是否大于0
function judgeArrayLen(){
	var arr= getSelectedValue();
	if(arr.length>0){
		return true;
	}
	else{
		return false;
	}
}


// 判断数组中是否包含此元素
function judgeArrayIsContainParam(val){
	var arr= getSelectedValue();
	if(arr.length==1){
		if(arr[0]==val){
			return false;
		}
		else{
			return true;
		}
	}
}
// 数据对比
// currentSelectedValue==当前选中的值
// opentTitle==窗口标题
// openDialogId=打开窗口的编号
// openDialogAsId==打开窗口的别名编号
// openWidth==宽度
// openHeight==高度
// falg==是否是模式化窗口
function chinaDataCompare(param,opentTitle,openDialogId,closeWindowId,openDialogAsId){
	var arr= getSelectedValue();
	// 数组长度等于1
	if(arr.length==1){
		if(arr[0]==param){
			$.messager.alert(findArray[6], reportArray[18]);
			return;
		}
		else{
			art.dialog({id:closeWindowId}).close();
			openCompareDataDialog(opentTitle+promptArray[21], openDialogId, openDialogAsId, 1050, 635,true);  
        	subFlexSearch();
		}
	}
	// 数组长度大于0
	if(arr.length>0){
		art.dialog({id:closeWindowId}).close();
		openCompareDataDialog(opentTitle+promptArray[21], openDialogId, openDialogAsId, 1050, 635,true);  
    	subFlexSearch();
	}
	else{
		$.messager.alert(findArray[6], reportArray[22]);
		return;
	}
}


// 数据对比===贾红平
// currentSelectedValue==当前选中的值
// opentTitle==窗口标题
// openDialogId=打开窗口的编号
// openDialogAsId==打开窗口的别名编号
// openWidth==宽度
// openHeight==高度
// falg==是否是模式化窗口
function changeDataCompare(opentTitle,openDialogId,closeWindowId,openDialogAsId){
	var param=$("input[name='val']").val();
 	var arr_falg=judgeArrayLen();
 	var bh_falg=judgeArrayIsContainParam(param);
 	// 数组长度大于0
 	if(arr_falg==true){
 		// 数组长度等于1 且包含当前选中的项
 		if(bh_falg==true){
 			subFlexSearch();
 		    art.dialog({id:closeWindowId}).close();
 		    openCompareDataDialog(opentTitle+promptArray[21], openDialogId, openDialogAsId,  1050, 635,true);
 			return;
 		}
 		if(bh_falg==false){
 			$.messager.alert(findArray[6], reportArray[18]);
			return;
 		}
 		else{
 			subFlexSearch();
 			art.dialog({id:closeWindowId}).close();
 		    openCompareDataDialog(opentTitle+promptArray[21], openDialogId, openDialogAsId,1050, 635,true);
 			return;
 		}
 	}
 	else{
 		$.messager.alert(findArray[6], reportArray[22]);
		return;
 	}
}

/**
 * 鼠标移动上去显示对应的title
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @return
 */
function formatTitle(value,rowData,rowIndex){
    return "<font title='"+value+"'>"+value+"</font>";
}

function over_country(param){
	$("#"+param).css("color","red");
}

function out_country(param){
	$("#"+param).css("color","black");
}
/**
 * 鼠标移动上显示超链接 根据国家名称跳转到对应国家的查询页面
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @return
 */
function formaterCountry(value,rowData,rowIndex){
	var param="usa";
	if (value=='巴拿马进出口(Panama iexport)') {
		return "<a id='a' href='/customer_search/view/chinese/search/panama/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('a');\" onmouseout=\"out_country('a');\">"+value+"</a>";
	}
	else if(value=='智利进口(Chile import)'){
		return "<a id='b' href='/customer_search/view/chinese/search/chile/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('b');\" onmouseout=\"out_country('b');\">"+value+"</a>";
	}
	else if(value=='阿根廷进口(Argentina import)'){
		return "<a id='c' href='/customer_search/view/chinese/search/argentina/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('c');\" onmouseout=\"out_country('c');\">"+value+"</a>";
	}
	else if (value=='哥伦比亚进口(Colombia import)') {
		return "<a id='d' href='/customer_search/view/chinese/search/glby/import/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('d');\" onmouseout=\"out_country('d');\">"+value+"</a>";
	}
	else if(value=='哥伦比亚出口(Colombia export)'){
		return "<a id='e' href='/customer_search/view/chinese/search/glby/export/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('e');\" onmouseout=\"out_country('e');\">"+value+"</a>";
	}
	else if (value=='哥斯达黎加进口(Costarica import)') {
		return "<a id='f' href='/customer_search/view/chinese/search/gsdnj/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('f');\" onmouseout=\"out_country('f');\">"+value+"</a>";
	}
	else if(value=='中国进出口(China iexport)'){
		return "<a id='g' href='/customer_search/view/chinese/search/eight/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('g');\" onmouseout=\"out_country('g');\">"+value+"</a>";

	} 
	else if (value=='巴拉圭进口(Paraguay import)') {
		return "<a id='h' href='/customer_search/view/chinese/search/paraguay/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('h');\" onmouseout=\"out_country('h');\">"+value+"</a>";
	}
	else if(value=='俄罗斯进出口(Russia iexport)'){
		return "<a id='i' href='/customer_search/view/chinese/search/russia/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('i');\" onmouseout=\"out_country('i');\">"+value+"</a>";

	} 
	else if (value=='厄瓜多尔进口(Ecuador import)') {
		return "<a id='j' href='/customer_search/view/chinese/search/ecuador/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('j');\" onmouseout=\"out_country('j');\">"+value+"</a>";
	}
	else if(value=='印度进口(India import)'){
		return "<a id='k' href='/customer_search/view/chinese/search/india/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('k');\" onmouseout=\"out_country('k');\">"+value+"</a>";

	}
	else if(value=='韩国进出口(Korea iexport)'){
		return "<a id='l' href='/customer_search/view/chinese/search/korea/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('l');\" onmouseout=\"out_country('l');\">"+value+"</a>";

	} 
	else if (value=='秘鲁进口(Peru import)') {
		return "<a id='m' href='/customer_search/view/chinese/search/peru/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('m');\" onmouseout=\"out_country('m');\">"+value+"</a>";
	}
	else if(value=='委内瑞拉进口(Venezuela import)'){
		return "<a id='n' href='/customer_search/view/chinese/search/weineiruila/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('n');\" onmouseout=\"out_country('n');\">"+value+"</a>";

	} 
	else if (value=='乌克兰进出口(Ukraine iexport)') {
		return "<a id='o' href='/customer_search/view/chinese/search/ukraine/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('o');\" onmouseout=\"out_country('o');\">"+value+"</a>";
	}
	else if(value=='乌拉圭进口(Uruguay import)'){
		return "<a id='p' href='/customer_search/view/chinese/search/uruguay/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('p');\" onmouseout=\"out_country('p');\">"+value+"</a>";

	} 
	else if (value=='英国进出口(Uk iexport)') {
		return "<a id='q' href='/customer_search/view/chinese/search/uk/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('q');\" onmouseout=\"out_country('q');\">"+value+"</a>";
	}
	else if(value=='巴基斯坦进出口(Pakistan iexport)'){
		return "<a id='r' href='/customer_search/view/chinese/search/pakistan/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('r');\" onmouseout=\"out_country('r');\">"+value+"</a>";

	}
	else if (value=='美国进口(Usa import)') {
		return "<a id='s' href='/customer_search/view/chinese/search/usa/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('s');\" onmouseout=\"out_country('s');\">"+value+"</a>";
	}
	else if(value=='巴西进口(Brazail import)'){
		return "<a id='t' href='/customer_search/view/chinese/search/brazil/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('t');\" onmouseout=\"out_country('t');\">"+value+"</a>";

	}
	else if (value=='洪都拉斯进口(Honduras import)') {
		return "<a id='v' href='/customer_search/view/chinese/search/honduras/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('v');\" onmouseout=\"out_country('v');\">"+value+"</a>";
	}
	else if(value=='墨西哥进口(Mexico import)'){
		return "<a id='u' href='/customer_search/view/chinese/search/mexicon/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('u');\" onmouseout=\"out_country('u');\">"+value+"</a>";

	}
	else if (value=='危地马拉进出口(Guatemala iexport）') {
		return "<a id='w' href='/customer_search/view/chinese/search/guatemala/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('w');\" onmouseout=\"out_country('w');\">"+value+"</a>";
	}
	else if(value=='尼加拉瓜进出口(Nicaragua iexport）'){
		return "<a id='x' href='/customer_search/view/chinese/search/nicargua/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('x');\" onmouseout=\"out_country('x');\">"+value+"</a>";

	}
	else if (value=='萨尔瓦多进出口(Salvatore iexport)') {
		return "<a id='y' href='/customer_search/view/chinese/search/salvatore/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('y');\" onmouseout=\"out_country('y');\">"+value+"</a>";
	}
	else if(value=='越南进口(Vietnam import)'){
		return "<a id='z' href='/customer_search/view/chinese/search/vietnam/searchAll.jsp' style='color:black;text-decoration:none;' onmousemove=\"over_country('z');\" onmouseout=\"out_country('z');\">"+value+"</a>";
	}
	
}
/**
 * @param tip
 *            提示标题
 * @param text
 *            提示内容
 * @param iconType
 *            提示图标类型
 * @param id
 *            需要修改状态的元素id
 * @param opType
 *            元素的类型：text 或者 button（失去焦点 或者 不可点击）
 * @param funName
 *            button执行的方法
 * @return
 */
function  alertMessage(tip,text,iconType){
        $.messager.alert(tip,text,iconType);
        reportQueryStartDisplay();
        advanceQueryStartDisplay();
        // 全库市场分析报告页面样式控制
        qkreportQueryStartDisplay();
}

/**
 * 报表模块刚开始查询时数据的样式
 * 
 * @param v
 * @return
 */
function reportQueryStartDisplay()
{
	// 报表刚开始查询时按钮样式
	$('#queryDownReport').css('display','none');
	$('#queryDownReportGrey').css('display','block');
	$('#querygreyDownReportGrey').css('display','none');
	// 报表刚开始查询时数据展示的列样式
	$('#datashow').animate({opacity:"0"},0);
	$(".picReport").hide();
}

/**
 * 报表模块有结果时数据的样式
 * 
 * @param v
 * @return
 */
function reportQueryEndDisplay()
{
	// 报表查询有结果时按钮样式
	$('#queryDownReport').css('display','block');
	$('#queryDownReportGrey').css('display','none');
	$('#querygreyDownReportGrey').css('display','none');
	// 报表查询有结果时数据展示的列样式
	$('#datashow').animate({opacity:"1"},0);
	$(".picReport").show();
}

/**
 * 报表模块正在查询时数据的样式
 * 
 * @param v
 * @return
 */
function reportQueryingDisplay()
{
	// 报表正在查询时按钮样式
	$('#queryDownReport').css('display','none');
	$('#queryDownReportGrey').css('display','none');
	$('#querygreyDownReportGrey').css('display','block');
	// 报表正在查询时数据展示的列样式
	$('#datashow').animate({opacity:"1"},0);
	$(".picReport").hide();
}

/**
 * 高级查询模块刚开始查询时数据的样式
 * 
 * @param v
 * @return
 */
function advanceQueryStartDisplay()
{
	// 查询模块刚开始查询时按钮样式
	$("#querygreyCollectionGrey").css("display",'none');
	$("#queryCollectionGrey").css("display",'block');
	$("#queryCollection").css("display",'none');
	// 查询模块刚开始查询时数据展示的列样式
	$('#addColor').animate({opacity:"0"},0);
}

/**
 * 高级查询模块有结果时数据的样式
 * 
 * @param v
 * @return
 */
function advanceQueryEndDisplay()
{
	// 查询模块有结果时按钮样式
	$("#querygreyCollectionGrey").css("display",'none');
	$("#queryCollectionGrey").css("display",'none');
	$("#queryCollection").css("display",'block');
	// 查询模块有结果时数据展示的列样式
	$('#addColor').animate({opacity:"1"},0);
}

/**
 * 高级查询模块正在查询时数据的样式
 * 
 * @param v
 * @return
 */
function advanceQueryingDisplay()
{
	// 查询模块正在查询时按钮样式
	$("#querygreyCollectionGrey").css("display",'block');
	$("#queryCollectionGrey").css("display",'none');
	$("#queryCollection").css("display",'none');
	// 查询模块正在查询时数据展示的列样式
	$('#addColor').animate({opacity:"1"},0);
}


/**
 * 全库市场分析报告刚开始查询时数据的样式
 * 
 * @param v
 * @return
 */
function qkreportQueryStartDisplay()
{
	// 报表刚开始查询时按钮样式
	$('#qkqueryDownReport').css('display','none');
	$('#qkqueryDownReportGrey').css('display','block');
	$('#qkquerygreyDownReportGrey').css('display','none');
	// 报表刚开始查询时数据展示的列样式
	$('#quankudatashow').animate({opacity:"0"},0);
}

/**
 * 全库市场分析报告有结果时数据的样式
 * 
 * @param v
 * @return
 */
function qkreportQueryEndDisplay()
{
	// 报表查询有结果时按钮样式
	$('#qkqueryDownReport').css('display','block');
	$('#qkqueryDownReportGrey').css('display','none');
	$('#qkquerygreyDownReportGrey').css('display','none');
	// 报表查询有结果时数据展示的列样式
	//$('#quankudatashow').animate({opacity:"1"},0);
}

/**
 * 全库市场分析报告正在查询时数据的样式
 * 
 * @param v
 * @return
 */
function qkreportQueryingDisplay()
{
	// 报表正在查询时按钮样式
	$('#qkqueryDownReport').css('display','none');
	$('#qkqueryDownReportGrey').css('display','none');
	$('#qkquerygreyDownReportGrey').css('display','block');
	// 报表正在查询时数据展示的列样式
	$('#quankudatashow').animate({opacity:"0"},0);
}



// 展开收起搜索条件
function openMoreField(type){
	// 如果隐藏时的处理方法
	if($('#'+type+'moreField').is(':hidden')){
		$('#'+type+'moreField').slideDown();
		document.getElementById(type+"openclosetextdisplay").innerHTML=promptArray[22];
		document.getElementById(type+"sanjiao").src="/customer_search/static/img/zhengsanjiao.png";
	}else
	{
		$('#'+type+'moreField').slideUp();
		document.getElementById(type+"openclosetextdisplay").innerHTML=promptArray[23];
		document.getElementById(type+"sanjiao").src="/customer_search/static/img/daosanjiao.png";
	}
}
/**
 * 条件的显示和隐藏 chenhao
 * 
 * @return
 */
// report.jsp使用
function showConditions_report(){
	$(".conditions_report").slideDown();
	$("#ohtherConditions_report").html('<div style="padding-left: 100px;margin-top: -20px"><a href="#" onclick="showConditions_report();"><font class="fontStyle">&nbsp;&nbsp;'+promptArray[22]+'</font></a></div>');
	$("#ohtherConditions_report a").attr("onclick","hideConditions_report()");
}
function hideConditions_report(){
	$(".conditions_report").slideUp();
	$("#ohtherConditions_report").html('<div style="padding-left: 100px;margin-top: -20px"><a href="#" onclick="showConditions_report();"><font class="fontStyle">&nbsp;&nbsp;'+promptArray[23]+'</font></a></div>');
	$("#ohtherConditions_report a").attr("onclick","showConditions_report()");
}
// find.jsp使用
function showConditions_find(){
	$(".conditions_find").slideDown();
	$("#ohtherConditions_find").html('<div style="padding-left: 100px;margin-top: -20px"><a href="#" onclick="showConditions_find();"><font class="fontStyle">&nbsp;&nbsp;'+promptArray[22]+'</font></a></div>');
	$("#ohtherConditions_find a").attr("onclick","hideConditions_find()");
}
function hideConditions_find(){
	$(".conditions_find").slideUp();
	$("#ohtherConditions_find").html('<div style="padding-left: 100px;margin-top: -20px"><a href="#" onclick="showConditions_find();"><font class="fontStyle">&nbsp;&nbsp;'+promptArray[23]+'</font></a></div>');
	$("#ohtherConditions_find a").attr("onclick","showConditions_find()");
}





/**
 * 格式化列表字段大小
 * 
 * @param str
 * @return
 */
function getStr(str){
	return "<font style='font-size:14px;'>"+str+"</font>";
}

/**
 * 交易趋势报告时补全时间跨度到一整月
 * 
 * @return
 */
function autoCompleteMonth(beginDate,endDate)
{
	// 处理开始日期变成该月的第一天
	beginDate = beginDate.substr(0,8)+"01";
    
	// 处理结束日期变成该月的最后一天
	var eDate=new Date(endDate);
	if(eDate.getMonth()==11 || eDate.getMonth()==10 || eDate.getMonth()==9)
	{
		if(eDate.getMonth()==11 || eDate.getMonth()==9)
		{
			endDate = eDate.getFullYear()+"-"+(eDate.getMonth()+1)+"-"+31;
		}else
		{
			eDate.setMonth(eDate.getMonth()+1);
			eDate.setDate(0);
			endDate = eDate.getFullYear()+"-"+(eDate.getMonth()+1)+"-"+eDate.getDate();
		}
	}else
	{
		if(eDate.getMonth()==0 || eDate.getMonth()==2 || eDate.getMonth()==4
				|| eDate.getMonth()==6 || eDate.getMonth()==7)
		{
			endDate = eDate.getFullYear()+"-0"+(eDate.getMonth()+1)+"-"+31;
		}else
		{
			eDate.setMonth(eDate.getMonth()+1);
			eDate.setDate(0);
			endDate = eDate.getFullYear()+"-0"+(eDate.getMonth()+1)+"-"+eDate.getDate();
		}
	}
    var date = {"beginTime" : beginDate,"endTime" : endDate};
    return date;
}
/**
 * highchart加载数据控制进度条的4个方法 开始方法 结束方法 清除方法 间隔方法
 */
// 进度条start
var pid;
var point;// 目标进度
var isBack=false;// 请求有无返回
function startTo(gap){// gap间隔
	if(!isBack){// 请求未返回，进度继续，否则清除pid,走end()
	var value = $('#rateProgressBar').progressbar('getValue');
	if(value==100){
		return;
	}
	if (value <point){
	value += Math.floor(Math.random() * 10);
	if(value>=point){
	$('#rateProgressBar').progressbar('setValue', point);
	clearTimeout(pid);
	return;
	}else{
		$('#rateProgressBar').progressbar('setValue', value);
		pid = setTimeout('startTo('+gap+')',gap);	
		}
	}
	}else{
		clearTimeout(pid);
	}
}
// 请求返回进度条执行此函数
function end(){
	clearTimeout(pid);
	var value = $('#rateProgressBar').progressbar('getValue');
	if(value>=100){
		showThb();
	}else	if (value < 100){
		finalShowThb();
	}	
}
// 数据返回成功后进度条平稳加载(进度值<100时调用)
function finalShowThb(){
	pid = setInterval('showThb()',200);	
}
// 判断是否可以显示图形
function showThb(){
	var value = $('#rateProgressBar').progressbar('getValue');
	value += Math.floor(Math.random() * 10);
	if(value<100){
		$('#rateProgressBar').progressbar('setValue', value);
	}
	if(value>=100){
		clearInterval(pid);
		$('#rateProgressBar').progressbar('setValue', 100);
		setTimeout(function(){
			$("#rateProgress").css("display","none");
			$("#itemSon").animate({opacity:"1"},800);
			$('#rateProgressBar').progressbar('setValue', 0);
			isBack = false;
		},200);	
	}
}
/**
 * 将数字以每3位逗号分割
 * 
 * @param s
 * @param n
 * @return
 */
function fmoney(s, n)   
{   
  n = n > 0 && n <= 20 ? n : 2;   
  s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
  var l = s.split(".")[0].split("").reverse(),   
  r = s.split(".")[1];   
  t = "";   
  for(i = 0; i < l.length; i ++ )   
  {   
     t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
  }   
  return t.split("").reverse().join("") + "." + r;   
}

/**
 * 使用字面量封装进度条的4个方法 开始 结束 数据成功加载 @ author: jiahp
 */
var pid;// 进度条start
var point;// 目标进度
var isBack=false;// 请求有无返回
var chartLoading={
		// 进度条开始加载
		startLoad:function(gap){// gap间隔
 			if(!isBack){// 请求未返回，进度继续，否则清除pid,走end()
				var value = $('#rateProgressBar').progressbar('getValue'); 
				if(value==100){
					return;
				}
				if (value <point){
				value += Math.floor(Math.random() * 10);
				if(value>=point){
				$('#rateProgressBar').progressbar('setValue', point);
				clearTimeout(pid);
				return;
				}else{
					$('#rateProgressBar').progressbar('setValue', value);
					pid = setTimeout('startLoad('+gap+')',gap);	
					}
				}
				}else{
					clearTimeout(pid);
				}
			},
		// 结束加载
		endLoad:function (){
				clearTimeout(pid);
				var value = $('#rateProgressBar').progressbar('getValue');
				if(value>=100){
					showThb();
				}else	if (value < 100){
					pid = setInterval(function (){
						var value = $('#rateProgressBar').progressbar('getValue');
						value += Math.floor(Math.random() * 10);
						if(value<100){
							$('#rateProgressBar').progressbar('setValue', value);
						}
						if(value>=100){
							clearInterval(pid);
							$('#rateProgressBar').progressbar('setValue', 100);
							setTimeout(function(){
								$("#rateProgress").css("display","none");
								$("#itemSon").animate({opacity:"1"},800);
								$('#rateProgressBar').progressbar('setValue', 0);
								isBack = false;
							},200);	
						}
					},200);	
				}	
			}
		
}


/**
 * 封装多海关编码的几个方法 添加 保存 删除 author jiahp
 */
var hsCode={
		// 打开添加多海关编码的对话框
		open:function(){
		    var hscodeText = $("input[name='hscodeFlex']").val();
			if(hscodeText == "请单击右侧添加海关编码")
			{
				hscodeText = "";
			}
			var array =hscodeText.split(",");
			var ctf = document.getElementById("customeTableFlexBr");
			// 删除原来的talbe下的tr
			for(var i=0;i<ctf.childNodes.length;i++){
				ctf.removeChild(ctf.childNodes[i]);
			}
			// 重新增加新的tr
			if(hscodeText!=''){
				for(var i =0;i<array.length;i++){
					var tr = '<tr style="line-height: 38px;"><td width="186px" align="left" style="color:#6B6C72;"><input name="customerCodeNameFlexBr" type="hidden" value="'+array[i]+'"/>'+array[i]+'</td><td width="46px" align="right"><img alt="'+findArray[11]+'" width="22px" height="22px" src="\/customer_search\/static\/img\/button\/deleHscode.png"onclick="deleteCustomecodeFlexBr(this)"onmouseover="this.src=\'\/customer_search\/static\/img\/button\/deleHscodeHover.png\';this.style.cursor=\'pointer\'" onmouseout="this.src=\'\/customer_search\/static\/img\/button\/deleHscode.png\'"></td></tr>';
		 			$("#customeTableFlexBr").append(tr);
				}
			}
			openDivArtDialog(findArray[9], 'addcustomecodeFlexBr', 'addcustomecodeFlexBr', 400, 300,true);
		},
		// 保存海关编码
		add:function(){
			var custlen = $("input[name='customerCodeNameFlexBr']");
			var value = "";
			
			for(var i=0;i<custlen.length;i++){
				value = value+custlen[i].value+",";
			}
			if(custlen.length>3){

				$.messager.alert(findArray[6],findArray[12],'info');


				 art.dialog({id:'addcustomecodeFlexBr'}).close();
			}
			else if(custlen.length == 0)
			{
				$("input[name='hscodeFlex']").val("");
				art.dialog({id:'addcustomecodeFlexBr'}).close();
			}
			else{
				$("#customscode2").val(value.substring(0,value.lastIndexOf(',')));
				art.dialog({id:'addcustomecodeFlexBr'}).close();
			}
		},
		// 动态插入一个hscode
		insert:function(){
			var  addCustomeCodeFlexBr = $("#addCustomeCodeFlexBr").val().trim();
			var test = /^\d{6,10}$/;
			if(!test.test(addCustomeCodeFlexBr)){
				$.messager.alert(findArray[6],findArray[15],'info');
				return false;
			}
			if($("input[name='customerCodeNameFlexBr']").length<3){
			var tr = '<tr style="line-height: 38px;" ><td width="186px" align="left" style="color:#6B6C72;"><input name="customerCodeNameFlexBr" type="hidden" value="'+addCustomeCodeFlexBr+'"/>'+addCustomeCodeFlexBr+'</td><td width="46px" align="right"><img alt="'+findArray[11]+'" width="22px" height="22px" src="\/customer_search\/static\/img\/button\/deleHscode.png"onclick="deleteCustomecodeFlexBr(this)"onmouseover="this.src=\'\/customer_search\/static\/img\/button\/deleHscodeHover.png\';this.style.cursor=\'pointer\'" onmouseout="this.src=\'\/customer_search\/static\/img\/button\/deleHscode.png\'"></td></tr>';
			$("#customeTableFlexBr").append(tr);
			$("#addCustomeCodeFlexBr").val("");
			}else{
				$.messager.alert('tip',findArray[12],'info');
				$("#addCustomeCodeFlexBr").val("");
			}
		},
		// 删除一个hscode
		remove:function(ipt){
			var del = $(ipt).parent().parent();
			del.remove();
		}
		
}



/**
 * 封装所有js常用的方法:国际化
 * @author 贾红平
 */
var JsModule={
		
		
}


/**
 * 下载图表通用方法--jhp url--服务器端的url pid--提交的id formId--表单提交的id
 */

function downLoadChart(url,pid,formId){
	 var myform = "<form method='post' action='"+url+"' id='"+formId+"'>";
	 myform += "<input name='columnKey' value='a'/>";
	 myform += "<input name='columnValue' value='b'/>";
	 myform += "</form>";
	 document.getElementById(""+pid+"").innerHTML = myform;
	 document.getElementById(""+formId+"").submit();
}
/**
 * 多海关编码备注查看通用方法--jhp gridId--datagrid的id url--服务器端的url
 */
function searchMultiHsCodeMark(gridId,url,v){
	 $('#'+gridId+'').datagrid({
	 		url:''+url+'',
	 		queryParams:{
	 			'dataMessage':v
	 			},
	 		pageNumber:1,
	 		onLoadSuccess:function (data){
	 		}
	 	});
 	openDivArtDialog(reportArray[5], 'itemPanlNew', 'searchbz', 700, 400,true);
}
/**
 * highcharts处理数据<0.01
 * 
 * @param data
 * @return
 */
function dealPercent(data){
	var resultPercent = "";
	if(data>=0.01){
		resultPercent = Highcharts.numberFormat(data, 2);
	}else{
		resultPercent = "&lt;0.01";
	}
	return resultPercent;
}


function addCustomer(){
		getLanuageType();
		var addCustomerArray = new Array();
		if(lang=='c'){
			//提示
			addCustomerArray[0]="提示"; 
			//请输入客户名称！
			addCustomerArray[1]="请输入客户名称！";
			//请输入客户价值
			addCustomerArray[2]="请输入客户价值！"; 
			//客户价值必须为数字
			addCustomerArray[3]="客户价值必须为数字！"; 
			//请输入公司名称
			addCustomerArray[4]="请输入公司名称！"; 
		}else{
			//提示
			addCustomerArray[0]="Prompt"; 
			//请输入客户名称！
			addCustomerArray[1]="Please enter the name of the customer!";
			//请输入客户价值
			addCustomerArray[2]="Please enter the customer value!"; 
			//客户价值必须为数字
			addCustomerArray[3]="Customer value must be numeric!";
			//请输入公司名称
			addCustomerArray[4]="Please name of the Entering Firm!"; 
		}
		return addCustomerArray;
	}

	function initParam(){
		//获取语言
		getLanuageType();
		var findArray = new Array();
		if(lang=='c'){
			findArray[0]="==请选择==";
			findArray[1]="第二段产品重量不能小于第一段产品重量";
			findArray[2]="第二段CIF单价不能小于第一段CIF单价";
			findArray[3]="第二段FOB单价不能小于第一段FOB单价";
			findArray[4]="第二段产品数量不能小于第一段产品数量";
			findArray[5]="对不起,第二段日期不能小于第一段日期!";
			findArray[6]="提示";
			findArray[7]="对不起您权限已到期，请重新购买!";
			findArray[8]="对不起，你输入的查询条件未检索到结果，请尝试更换条件值!";
			findArray[9]="添加海关编码";
			findArray[10]="您好,请输入海关编码必须是大于4位的数字!";
			findArray[11]="删除";
			findArray[12]="多海关编码查询最多可以查三个!";
			findArray[13]="详情提单";
			findArray[14]="对不起,海关编码必须输入!";
			findArray[15]="您好,请输入海关编码必须是大于6位小于10位的数字！";
			findArray[16]="产品描述长度不得小于两位!";
			findArray[17]="开始日期不能为空!";
			findArray[18]="结束日期不能为空!";
			findArray[19]="查询最大的时间差不能超过一年!";
			findArray[20]="第二段产品净重不能小于第一段产品净重";
			findArray[21]="第二段产品单价不能小于第一段产品单价";
			findArray[22]="请输入海关编码!";
			findArray[23]="海关编码和产品描述必须输入一项！";
			findArray[24]="==全部==";
			findArray[25]="第二段价格不能小于第一段价格";
			findArray[26]="多海关编码查询最多可以查五个!";
			findArray[27]="我的权限查看";
			findArray[28]="购买的海关编码";
			findArray[29]="购买的商品名称";
			findArray[30]="所属国家";
			findArray[31]="进出口类型";
			findArray[32]="数据检索开始时间";
			findArray[33]="数据检索结束时间";
			findArray[34]="购买总次数";
			findArray[35]="已查看次数";
			findArray[36]="剩余查看次数";
			findArray[37]="所购买的国家";
			findArray[38]="第二段产品毛重不能小于第一段产品毛重";
			findArray[39]="第二段卢布单价不能小于第一段卢布单价";
			findArray[40]="第二段美元单价不能小于第一段美元单价";
			findArray[41]="请输入产品描述！";
			findArray[42]="第二段发票金额不能小于第一段发票金额";
			findArray[43]="请至少输入一个查询必要条件！";
			findArray[44]="您好,请输入海关编码必须是大于6位小于10位的数字,多海关编码要以逗号分隔！";
		}else{
			findArray[0]="==Please Select==";
			findArray[1]="The second section weight cannot weigh less than the first paragraph weight.";
			findArray[2]="The second segment CIF price cannot be less than the first section CIF unit.";
			findArray[3]="The second segment FOB price cannot be less than the first section of FOB unit.";
			findArray[4]="The second section number cannot be less than the number of the first paragraph.";
			findArray[5]="Sorry, the second date cannot be less than the first date.";
			findArray[6]="Prompt";
			findArray[7]="Sorry, your access has expired. Please purchase a new subscription.";
			findArray[8]="Sorry, the search you entered did not retrieve results. Try replacing the condition value.";
			findArray[9]="Add HS-code";
			findArray[10]="Hello, please enter the customs code. Codes must be greater than 4 digits.";
			findArray[11]="Delete";
			findArray[12]="Multiple Customs Code. (Maximum of 3 searches.)";
			findArray[13]="Bill of Lading Details";
			findArray[14]="The customs code must be entered.";
			findArray[15]="Hello. Please enter the customs code. (Must be greater than 6 and less than 10 digits.)";
			findArray[16]="Product description length must be more than 2 words.";
			findArray[17]="Start date cannot be empty.";
			findArray[18]="End date cannot be empty.";
			findArray[19]="Searches cannot have a time difference of more than one year.";
			findArray[20]="The second section of the first paragraph net weight net weight of not less than";
			findArray[21]="The second section of the unit price cannot be less than the first period price.";
			findArray[22]="Please enter the HS-code.";
			findArray[23]="The HS-Code and product description must enter A.";
			findArray[24]="==all==";
			findArray[25]="The second section cannot be less than the first price.";
			findArray[26]="Multiple Customs Code. (Maximum of 5 searches.)";
			findArray[27]="Permission to View";
			findArray[28]="Buy the HS-Code.";
			findArray[29]="Buy the product name.";
			findArray[30]="Country";
			findArray[31]="Trade Type";
			findArray[32]="Search start time.";
			findArray[33]="Search end time.";
			findArray[34]="Total";
			findArray[35]="Has the view";
			findArray[36]="The remaining number.";
			findArray[37]="The purchase of state.";
			findArray[38]="The second section of the first paragraph gross weight gross weight not less than.";
			findArray[39]="The second section unit price cannot be less than the first period of the rouble rouble unit.";
			findArray[40]="The second section cannot be less than the first paragraph USD USD.";
			findArray[41]="Please enter a description of the product.";
			findArray[42]="The second section of the invoice amount not less than the first period of the invoice amount.";
			findArray[43]="Please enter at least one required condition for the search.";
			findArray[44]="Hello. Please enter the customs code. (Must be greater than 6 and less than 10 digits. Separate by commas.)";
		}
		return findArray;
	}
	
	 function initReport (){
		getLanuageType();
		var reportArray = new Array();
		if(lang=='c'){
			reportArray[0]="请单击右侧添加海关编码";
			reportArray[1]="新增流失报告不能使用多海关编码，请选择单个海关编码";
			reportArray[2]="对不起您的权限不能查看收费的报表,如需查看请点击定制中心进行定制!谢谢";
			reportArray[3]="前十大";
			reportArray[4]="查看汇总详情";
			reportArray[5]="查看备注";
			reportArray[6]="所有页面统计";
			reportArray[7]="当前页面统计";
			reportArray[8]="进口商不能为空！";
			reportArray[9]="查看同比";
			reportArray[10]="输入必须是纯数字";
			reportArray[11]="对不起,开始的价格不能为空!";
			reportArray[12]="对不起,结束的价格不能为空!";
			reportArray[13]="对不起，没有更低的价格了!";
			reportArray[14]="抱歉，请填写完整公司名称!";
			reportArray[15]="对不起,您没有可查看的明细记录!";
			reportArray[16]="出口商不能为空!";
			reportArray[17]="对不起,该报告没有对应的图表展示!";
			reportArray[18]="对不起,不能和自身进行对比,请确认您的操作!";
			reportArray[19]="买卖方交易汇总报告不生成图表";
			reportArray[20]="乌克兰数据明细";
			reportArray[21]="印度数据对比分析";
			reportArray[22]="对不起,对比数据不能为空,请您重新选择数据!";
			reportArray[23]="危地马拉出口";
			reportArray[24]="危地马拉进口";
			reportArray[25]="尼加拉瓜进口";
			reportArray[26]="尼加拉瓜出口";
		    reportArray[27]="海关编码数据如下";
	        reportArray[28]="重量";
		    reportArray[29]="金额";
		    reportArray[30]="次数";	
	        reportArray[31]="委内瑞拉";
	        reportArray[32]="越南";
	        reportArray[33]="请减小起始年份或增加当前年份";
	        reportArray[34]="请增加终止年份或减小当前年份";
	        reportArray[35]="结束月份不得小于起始月份！";
	        reportArray[36]="当前时间间隔";
	        reportArray[37]="月";
			reportArray[38]="隐藏时间段";
			reportArray[39]="显示时间段";
			reportArray[40]="时间不能够为空!"
			reportArray[41]="时间格式不正确!"
			reportArray[42]="请补充完整第一时间段起始年份！"
			reportArray[43]="请补充完整第一时间段起始月份！"
			reportArray[44]="请补充完整第一时间段结束年份！"
			reportArray[45]="请补充完整第一时间段结束月份！"
			reportArray[46]="请单击按钮展开并补充第二时间段起始年份！"
			reportArray[47]="请单击按钮展开并补充第二时间段起始月份！"
			reportArray[48]="请单击按钮展开并补充第二时间段结束年份！"
			reportArray[49]="请单击按钮展开并补充第二时间段结束月份！"
			reportArray[50]="柱状图(查看数值)"
			reportArray[51]="美国报表详情查看"
			reportArray[52]="美国"
			reportArray[53]="阿根廷"
			reportArray[54]="巴基斯坦"
			reportArray[55]="巴西"
			reportArray[56]="智利"
			reportArray[57]="乌拉圭进口";	
			reportArray[58]="中国";	
			reportArray[59]="厄瓜多尔";	
			reportArray[60]="哥伦比亚出口";	
			reportArray[61]="哥伦比亚进口";	
			reportArray[62]="哥斯达尼加";	
			reportArray[63]="英国";
			reportArray[64]="全选";
			reportArray[65]="洪都拉斯";
			reportArray[66]="韩国";
			reportArray[67]="墨西哥";
			reportArray[68]="尼加拉瓜进口";
			reportArray[69]="萨尔瓦多出口";
			reportArray[70]="乌克兰";
			reportArray[71]="尼加拉瓜出口";
			reportArray[72]="巴拿马出口";
			reportArray[73]="巴拿马进口";
			reportArray[74]="巴拉圭进口";
			reportArray[75]="秘鲁";
			reportArray[76]="俄罗斯";
			reportArray[77]="萨尔瓦多进口";
			reportArray[78]="请选择国家！";
		}else{
				reportArray[0]="Click to the right to add the Customs Code.";
			reportArray[1]="New reports cannot be used more than the loss of customs codes, please select a single customs code.";
			reportArray[2]="Sorry, you cannot view the report privilege fees. Please click Custom Shop To view customization. Thank you.";
			reportArray[3]="Top 10";
			reportArray[4]="View summary details.";
			reportArray[5]="See Remarks";
			reportArray[6]="All Page Summary";
			reportArray[7]="Current Page";
			reportArray[8]="Importer field cannot be empty.";
			reportArray[9]="Search year month-to-month.";
			reportArray[10]="The input must be a pure digital.";
			reportArray[11]="Sorry, the beginning of the price cannot be empty.";
			reportArray[12]="Sorry, the end of the price cannot be empty.";
			reportArray[13]="Sorry, not lower prices.";
			reportArray[14]="Sorry, please complete and return the name of the company.";
			reportArray[15]="Sorry, you cannot see the detail records.";
			reportArray[16]="Exporter field cannot be empty.";
			reportArray[17]="Sorry, the report does not correspond to the chart.";
			reportArray[18]="Sorry, cannot compare itself. Please confirm your action.";
			reportArray[19]="Buyers and sellers transaction summary report does not generate charts.";
			reportArray[20]="Ukraine Data Details";
			reportArray[21]="Data analysis comparing India.";
			reportArray[22]="Sorry, comparative data cannot be empty. Please re-select your data.";
			reportArray[23]="Guatemala exports";
			reportArray[24]="Guatemala imports";
			reportArray[25]="Nicaragua imports";
			reportArray[26]="Nicaraguan exports";
			reportArray[27]="The customs code data are as follows";
	        reportArray[28]="weight";
		    reportArray[29]="money";
		    reportArray[30]="count";	
	        reportArray[31]="Venezuela";
	        reportArray[32]="Vietnam";
	        reportArray[33]="Please reduce the starting year or increase the end year.";
	        reportArray[34]="Please increase or decrease the end year.";
	        reportArray[35]="The final month must not be less than the starting month.";
	        reportArray[36]="The current time interval.";
	        reportArray[37]="Month";
			reportArray[38]="Hidden time";
			reportArray[39]="Display time";
			reportArray[40]="Time cannot be empty."
			reportArray[41]="Time is not in the correct format."
			reportArray[42]="Please complete the first time starting year."
			reportArray[43]="Please complete the first time starting month."
			reportArray[44]="Please complete the first time period end date."
			reportArray[45]="Please complete the first time period end date."
			reportArray[46]="Click the start button and add a second time period starting year."
			reportArray[47]="Click the start button and add a second time period starting month."
			reportArray[48]="Click the start button and add a second time period end date."
			reportArray[49]="Click the start button and add a second time period end date."
			reportArray[50]="Histogram (see number)"
			reportArray[51]="American report details view"
			reportArray[52]="USA"
			reportArray[53]="Argentina"
			reportArray[54]="Pakistan"
			reportArray[55]="Brazil"
			reportArray[56]="Chile"
			reportArray[57]="Uruguay imports";	
			reportArray[58]="China";	
			reportArray[59]="Ecuador";	
			reportArray[60]="Colombian exports";	
			reportArray[61]="Colombian imports";	
			reportArray[62]="Costa Rica";	
			reportArray[63]="United Kingdom";
			reportArray[64]="Select all";
			reportArray[65]="Honduras";
			reportArray[66]="Korea";
			reportArray[67]="Mexico";
			reportArray[68]="Nicaragua imports";
			reportArray[69]="Salvadoran exports";
			reportArray[70]="Ukraine";
			reportArray[71]="Nicaraguan exports";
			reportArray[72]="Panama exports";
			reportArray[73]="Panama imports";
			reportArray[74]="Paraguay imports";
			reportArray[75]="Peru";
			reportArray[76]="Russia";
			reportArray[77]="El Salvador imports";
			reportArray[78]="Please select a country.";
		}
		return reportArray;
	}

	 
	 /**
	  * 章华才
	  * commontrend.js公共文件翻译
	  * @return
	  */
	 function commessageReport(){
	 	getLanuageType();
	 	var commArray = new Array();
	 	if(lang=='c'){
	 		commArray[0]="未查看数据需要收费，你确定要查看吗?";
	 		commArray[1]="添加产品信息!";
	 		commArray[2]="来自网页的消息";
	 		commArray[3]="立即充值";
	 		commArray[4]="添加客户信息";
	 		commArray[5]="添加竞争对手";
	 		commArray[6]="您好,横向打印的效果更佳！";
	 		commArray[7]="确认删除吗?";
	 		commArray[8]="添加到收藏夹";
	 		commArray[9]="保存成功，您可以在收藏中心内的查询收藏夹中找到它";
	 		commArray[10]="我的查询收藏'";
	 		commArray[11]="查询耗时";
	 		commArray[12]="提示";
	 		commArray[13]="对不起，你输入的查询条件未检索到结果，请尝试更换条件值!";
	 		commArray[14]="查看详情";
	 		commArray[15]="最少要输入一个查询条件";
	 		commArray[16]="导出成功,路径为";

	 	}else{
	 		commArray[0]="Did not need to see the data charges, are Are you sure you want to view it?";
	 		commArray[1]="Add Product Information";
	 		commArray[2]="Message from webpage";
	 		commArray[3]="Immediately recharge";
	 		commArray[4]="Adding Customer Information";
	 		commArray[5]="Add competitors";
	 		commArray[6]="Hello, the effect of the transverse print better.";
	 		commArray[7]="Confirm delete?";
	 		commArray[8]="Add to Favorites";
	 		commArray[9]="Saved successfully, you can search the collection in the Favorites Center folder to find it";
	 		commArray[10]="My search collections";
	 		commArray[11]="Consuming searches";
	 		commArray[12]="Prompt";
	 		commArray[13]="Sorry, the search you entered did not retrieve results, try replacing the condition value.";
	 		commArray[14]="View details";
	 		commArray[15]="To enter a search condition at least";
	 		commArray[16]="Export is successful path:";
	 	}
	 	return commArray;
	 }


	 /**
	  * 章华才
	  * 用户中心模块翻译
	  * @return
	  */
	 function userCenterParam(){
			//获取语言
			getLanuageType();
			var usercenterArray = new Array();
			if(lang=='c'){
				usercenterArray[0]="修改成功!";
				usercenterArray[1]="请确认您的原密码正确!";
				usercenterArray[2]="提示";
				usercenterArray[3]="我的密码修改!";
				usercenterArray[4]="您填写的信息不够完整,请确认您的操作!";
				usercenterArray[5]="新密码和确认密码必须是一致,请确认您的操作!";
				usercenterArray[6]="请输入所定制的";
				usercenterArray[7]="请选择所定制的国家!";
				usercenterArray[8]="请选择所定制的贸易类型!";
				usercenterArray[9]="请填写海关编码!";
				usercenterArray[10]="您好！请输入海关编码长度为6位的数字!";
				usercenterArray[11]="您好！请输入海关编码长度为8位的数字!";
				usercenterArray[12]="请选择数据定制开始日期!";
				usercenterArray[13]="开始日期应选择当月的1日!";
				usercenterArray[14]="请选择数据定制结束日期!";
				usercenterArray[15]="截止日期应选择当月的最后1日!";
				usercenterArray[16]="您订购的数据开始日期不能够小于截止日期!";
				usercenterArray[17]="我的产品订单";
				usercenterArray[18]="请填写定制的产品名称!";
				usercenterArray[19]="请选择产销国!";
				usercenterArray[20]="请输入您要查看的次数!";
				usercenterArray[21]="请输入正确的次数!";
				usercenterArray[22]="定制中心";
				usercenterArray[23]="新增用户";
				usercenterArray[24]="编辑用户";
				usercenterArray[25]="请选择要修改的数据!";
				usercenterArray[26]="请选择需要删除的用户!";
				usercenterArray[27]="确定要删除所选数据吗?";
				usercenterArray[28]="确定要删除此用户及他相关联的的子用户吗?";
				usercenterArray[29]="账户管理";
				usercenterArray[30]="您好！请输入数字!";
				usercenterArray[31]="您好！请输入大于0的数字!";
				usercenterArray[32]="您好！您输入的下载条数已经大于您权限内的下载条数!";
				usercenterArray[33]="确定要激活用户吗？";
				usercenterArray[34]="确定要禁用用户吗？";
				usercenterArray[35]="删除用户";
				usercenterArray[36]="授权";
				usercenterArray[37]="激活用户";
				usercenterArray[38]="禁用用户";
				usercenterArray[39]="查看子账户权限";
				usercenterArray[40]="我的权限查看";
				usercenterArray[41]="*账号必须由英文字母、数字(0-9)、汉字组成，长度在4-12个字符之间。";
				usercenterArray[42]="账号不正确!";
				usercenterArray[43]="用户名已经存在!";
				usercenterArray[44]="*只能包含字母、数字以及标点符号（除空格），长度为6～12。";
				usercenterArray[45]="密码输入不正确!";
				usercenterArray[46]="邮箱格式不正确！请重新填写。";
				usercenterArray[47]="请填写用户权限的有效期!";
				usercenterArray[48]="第二段日期不能小于第一段日期!";
				usercenterArray[49]="获取有效时间范围...";
				usercenterArray[50]="有效期";
				usercenterArray[51]="请上传图片!";
				usercenterArray[52]="文件类型";
				usercenterArray[53]="或";
				usercenterArray[54]="区号为3-4位的数字，区号之后用“-”与电话号码连接，电话号码为7-8位的数字。";
				usercenterArray[55]="域名格式不正确！请重新填写。";
				usercenterArray[56]="*域名由各国文字的特定字符集、英文字母、数字及“-”任意组合而成, 但开头及结尾均不能含有“-”";
				usercenterArray[57]="对不起，您当前无可添加的产品信息";
				usercenterArray[58]="对不起，您当前无可添加的客户信息";
				usercenterArray[59]="对不起，您当前无可添加的竞争对手信息";
				usercenterArray[60]="市场分析";
				usercenterArray[61]="买家";
				usercenterArray[62]="竞争";
				usercenterArray[63]="交易";
				usercenterArray[64]="标签匹配";
				usercenterArray[65]="删除";
				usercenterArray[66]="修改";
				usercenterArray[67]="添加产品标签";
				usercenterArray[68]="标签名称不能为空！";
				usercenterArray[69]="编码不能为空！";
				usercenterArray[70]="编码必须是6位的数字!";
				usercenterArray[71]="产品描述不能为空!";
				usercenterArray[72]="对不起，请输入英文，字符串开头不能有空格，可添加多个关键字，必须以单个空格分隔！";
				usercenterArray[73]="修改产品标签";
				usercenterArray[74]="对不起!没有查询到符合您条件的记录数...";
				usercenterArray[75]="买家资源库表";
				usercenterArray[76]="全选";
				usercenterArray[77]="添加竞争对手信息";
				usercenterArray[78]="添加客户信息";
				usercenterArray[79]="竞争对手信息";
				usercenterArray[80]="外贸交易情报";
				usercenterArray[81]="时间不能够为空!";
				usercenterArray[82]="时间格式不正确!";
				usercenterArray[83]="您输入的查询结束时间不能够小于查询开始时间!";
				usercenterArray[84]="查询最大的时间差不能超过一年!";
				usercenterArray[85]="查看详情";
				usercenterArray[86]="产品标签市场分析报告";
				usercenterArray[87]="*当前已选";
				usercenterArray[88]="对不起,您的权限暂不开放此功能!如需查看请到";
				usercenterArray[89]="快速定制";
				usercenterArray[90]="栏目进行定制";
				usercenterArray[91]="数据更新";
				usercenterArray[92]="对不起您的权限不够，请购买更多的国家权限!";
				usercenterArray[93]="登录记录查询";
				usercenterArray[94]="数据加载中请稍后……";
				usercenterArray[95]="查询";
				usercenterArray[96]="请输入登录名";
				usercenterArray[97]="账号名称";
				usercenterArray[98]="真实姓名";
				usercenterArray[99]="查询国家";
				usercenterArray[100]="查询时间";
				usercenterArray[101]="查询参数";
				usercenterArray[102]="地址";
				usercenterArray[103]="所属地";
				usercenterArray[104]="草稿箱";
				usercenterArray[105]="邮件编号";
				usercenterArray[106]="收件人";
				usercenterArray[107]="主题";
				usercenterArray[108]="内容";
				usercenterArray[109]="时间";
				usercenterArray[110]="操作";
				usercenterArray[111]="查看邮件";
				usercenterArray[112]="删除成功!";
				usercenterArray[113]="操作异常!";
				usercenterArray[114]="你没有选中目标!";
				usercenterArray[115]="差！功能太少了，槽糕极了";
				usercenterArray[116]="一般！功能很少，没有好的印象";
				usercenterArray[117]="不错！功能不够多，继续努力";
				usercenterArray[118]="功能比较很多,还是很棒的！";
				usercenterArray[119]="太棒了！功能超多超赞的，好评！！";
				usercenterArray[120]="差！槽糕极了，再也不来了";
				usercenterArray[121]="一般！没啥好的体验，没有好的印象";
				usercenterArray[122]="还行！人性化不强，和其它网站无差别";
				usercenterArray[123]="很棒！体验很好，蛮喜欢的！！";
				usercenterArray[124]="太棒了！体验超级棒！终生难忘啊";
				usercenterArray[125]="请选出评价系统功能！";
				usercenterArray[126]="请写评价和建议";
				usercenterArray[127]="谢谢！你的评价已保存！！";
				usercenterArray[128]="评价有误！！";
				usercenterArray[129]="请输入充值金额!";
				usercenterArray[130]="请输入正确的金额!";
				usercenterArray[131]="购买的海关编码";
				usercenterArray[132]="购买的商品名称";
				usercenterArray[133]="所属国家";
				usercenterArray[134]="进出口类型";
				usercenterArray[135]="数据检索开始时间";
				usercenterArray[136]="数据检索结束时间";
				usercenterArray[137]="购买总次数";
				usercenterArray[138]="已查看次数";
				usercenterArray[139]="剩余查看次数";
				usercenterArray[140]="对不起，您输入的查询结束时间间隔不应超过二年!";
				usercenterArray[141]="请输入查询框的值！";
				usercenterArray[142]="正在翻译,请等待......";
				usercenterArray[143]="添加邮箱";
				usercenterArray[144]="对不起,邮箱已存在!";
				usercenterArray[145]="数据异常，请重新请求，或者提供的参数不正确，无法获取邮件!";
				usercenterArray[146]="登录成功";
				usercenterArray[147]="请选择需要删除的邮件！";
				usercenterArray[148]="请选择需要标记为已读的邮件！";
				usercenterArray[149]="请选择需要标记为未读的邮件！";
				usercenterArray[150]="恢复成功";
				usercenterArray[151]="请选择邮件";
				usercenterArray[152]="对不起!最多可以同时添加5个邮箱地址";
				usercenterArray[153]="对不起!暂时没有邮件";
				usercenterArray[154]="确定要删除此邮件吗?";
				usercenterArray[155]="垃圾箱";
				usercenterArray[156]="发件人";
				usercenterArray[157]="对不起!请选择需要查询的国家...";
				usercenterArray[158]="请输入查询条件";
				usercenterArray[159]="请输入大于等于四位的HS编码或提单号 ！";
				usercenterArray[160]="确认密码不能够为空";
				usercenterArray[161]="验证码不能够为空";
				usercenterArray[162]="验证码不正确";
				usercenterArray[163]="密码输入不一致";
				usercenterArray[164]="用户名不能够为空";
				usercenterArray[165]="密码格式不正确";
				usercenterArray[166]="浏览器检测结果提示";
				usercenterArray[167]="对不起！没有查询到与竞争对手相关的采购商！";
				usercenterArray[168]="对不起没有查询到此竞争对手的交易记录！";
				usercenterArray[169]="深度挖取报表";
				
			}else{
				usercenterArray[0]="Modify success.";
				usercenterArray[1]="Please make sure your original password is correct.";
				usercenterArray[2]="Prompt";
				usercenterArray[3]="My password change.";
				usercenterArray[4]="Information incomplete. Please confirm your entry.";
				usercenterArray[5]="New Password and Confirm password must be the same. Please confirm your action.";
				usercenterArray[6]="Please enter customized";
				usercenterArray[7]="Please select customized country.";
				usercenterArray[8]="Please select the type of customized trade.";
				usercenterArray[9]="Please fill in the Customs Code.";
				usercenterArray[10]="Hello. Please enter the customs code. (6 digits)";
				usercenterArray[11]="Hello. Please enter the customs code. (8 digits)";
				usercenterArray[12]="Please select the data to customize the start date.";
				usercenterArray[13]="Start date should be from the 1st of the month.";
				usercenterArray[14]="Please select the data to customize the end date.";
				usercenterArray[15]="End date should be the last day of the month.";
				usercenterArray[16]="End date cannot be earlier than the start date.";
				usercenterArray[17]="My product orders.";
				usercenterArray[18]="Please fill in the customized product name.";
				usercenterArray[19]="Please choose the country sales.";
				usercenterArray[20]="Please enter the number you want to see.";
				usercenterArray[21]="Please enter the correct number.";
				usercenterArray[22]="Custom Shop";
				usercenterArray[23]="New users";
				usercenterArray[24]="Edit User";
				usercenterArray[25]="Please select the data to be modified.";
				usercenterArray[26]="Please select the user you want to delete.";
				usercenterArray[27]="Are you sure you want to delete the selected data?";
				usercenterArray[28]="Are you sure you want to delete the user and secondary users associated with it?";
				usercenterArray[29]="Account Management";
				usercenterArray[30]="Hello. Please enter a number.";
				usercenterArray[31]="Hello. Please enter a number greater than 0.";
				usercenterArray[32]="Hello. The number you entered is already greater than the number of pieces you downloaded.";
				usercenterArray[33]=" Are you sure you want to activate this user?";
				usercenterArray[34]="Are you sure you want to disable this user?";
				usercenterArray[35]="Delete User";
				usercenterArray[36]="Authorize";
				usercenterArray[37]="Active users";
				usercenterArray[38]="Disable User";
				usercenterArray[39]="View sub-account privileges";
				usercenterArray[40]="I have permission to view";
				usercenterArray[41]="* Account must be in English letters, numbers (0-9), the composition of Chinese characters in length between 4-12 characters.";
				usercenterArray[42]="Incorrect account.";
				usercenterArray[43]="Username already exists.";
				usercenterArray[44]="*Can only contain letters, numbers, and punctuation (except space), a length of 6 to 12.";
				usercenterArray[45]="Password is not correct.";
				usercenterArray[46]="E-mail format is incorrect. Please re-enter.";
				usercenterArray[47]="Please fill out the validity of user privileges.";
				usercenterArray[48]="Date cannot be less than the first paragraph of the second paragraph of date.";
				usercenterArray[49]="Obtain a valid time range ...";
				usercenterArray[50]="Validity";
				usercenterArray[51]="Please upload pictures.";
				usercenterArray[52]="File Type";
				usercenterArray[53]="Or";
				usercenterArray[54]="Code 3-4 digits after the area code with the - and a telephone number to connect, phone numbers 7-8 digits.";
				usercenterArray[55]="Domain name format is incorrect. Please re-enter.";
				usercenterArray[56]="* name for English alphanumeric and - arbitrary combination, but the opening and the ending are allowed to contain -";
				usercenterArray[57]="Sorry, your current product information was not added.";
				usercenterArray[58]="Sorry, your current customer information was not added.";
				usercenterArray[59]="Sorry, there is no competitor information.";
				usercenterArray[60]="Market analysis";
				usercenterArray[61]="Buyers";
				usercenterArray[62]="Competition";
				usercenterArray[63]="Transaction";
				usercenterArray[64]="Tag match";
				usercenterArray[65]="Delete";
				usercenterArray[66]="Update";
				usercenterArray[67]="Add the product label";
				usercenterArray[68]="Label name cannot be empty.";
				usercenterArray[69]="Coding cannot be empty.";
				usercenterArray[70]="The encoding must be 6 figures.";
				usercenterArray[71]="Product description cannot be empty.";
				usercenterArray[72]="Sorry, please enter the English. The beginning of the string cannot contain spaces. You can add multiple keywords which must be separated by a single space.";
				usercenterArray[73]="Modify the product label";
				usercenterArray[74]="Sorry. No inquiry into the number of records that meet your criteria ...";
				usercenterArray[75]="Buyers repository tables";
				usercenterArray[76]="Select";
				usercenterArray[77]="Add competitor information";
				usercenterArray[78]="Adding Customer Information";
				usercenterArray[79]="Competitor information";
				usercenterArray[80]="Foreign trade transactions intelligence";
				usercenterArray[81]="Time cannot be empty.";
				usercenterArray[82]="The time format is not correct.";
				usercenterArray[83]="Search end time cannot be less than the start time.";
				usercenterArray[84]="The largest time difference cannot exceed one year.";
				usercenterArray[85]="View details";
				usercenterArray[86]="Product label market analysis report";
				usercenterArray[87]="* Currently selected";
				usercenterArray[88]="Sorry, your permission to temporarily open this feature. To see go";
				usercenterArray[89]="Rapid customization";
				usercenterArray[90]="Columns customization";
				usercenterArray[91]="Data Update";
				usercenterArray[92]="I'm sorry, your subscription does not have access to this information. Please buy more country permissions.";
				usercenterArray[93]="Log records check";
				usercenterArray[94]="Loading Please wait ......";
				usercenterArray[95]="Inquiry";
				usercenterArray[96]="Please enter your login name";
				usercenterArray[97]="Account name";
				usercenterArray[98]="Real name";
				usercenterArray[99]="National inquiry";
				usercenterArray[100]="Search time";
				usercenterArray[101]="Search parameters";
				usercenterArray[102]="Address";
				usercenterArray[103]="Belongs to";
				usercenterArray[104]="Drafts";
				usercenterArray[105]="Mail ID";
				usercenterArray[106]="Addressee";
				usercenterArray[107]="Theme";
				usercenterArray[108]="Content";
				usercenterArray[109]="Time";
				usercenterArray[110]="Operating";
				usercenterArray[111]="Check Mail";
				usercenterArray[112]="Deleted successfully..";
				usercenterArray[113]="Abnormal operation..";
				usercenterArray[114]="You did not select the target..";
				usercenterArray[115]="Difference. Features too small, extremely slot cake";
				usercenterArray[116]="General. Function and there are no good impression";
				usercenterArray[117]="Not bad. Functional enough to continue their efforts";
				usercenterArray[118]="Compare a lot of features, or great.";
				usercenterArray[119]="Great. Features super multi awesome, praise. .";
				usercenterArray[120]="Difference. Slot cake was great, never came";
				usercenterArray[121]="General. Nothing good experience, not a good impression";
				usercenterArray[122]="Okay. Humanity is not strong, and other sites undifferentiated";
				usercenterArray[123]="Great. Experience is very good, quite like it. .";
				usercenterArray[124]="Great. Experience superb. Unforgettable ah";
				usercenterArray[125]="Please select evaluation system functions.";
				usercenterArray[126]="Please write reviews and advice";
				usercenterArray[127]="Thank you. Your rating has been saved. .";
				usercenterArray[128]="Evaluation is wrong. .";
				usercenterArray[129]="Please enter the recharge amount.";
				usercenterArray[130]="Please enter the correct amount.";
				usercenterArray[131]="Purchase of the Customs Code";
				usercenterArray[132]="Buy trade names";
				usercenterArray[133]="Country";
				usercenterArray[134]="Import and Export Type";
				usercenterArray[135]="Start Time";
				usercenterArray[136]="End Time";
				usercenterArray[137]="The total number of purchase";
				usercenterArray[138]="Been Views";
				usercenterArray[139]="Remaining Views";
				usercenterArray[140]="Sorry, the end time of the search interval should not exceed two years.";
				usercenterArray[141]="Please enter a value check box.";
				usercenterArray[142]="Being translated, please wait ......";
				usercenterArray[143]="Add Mailbox";
				usercenterArray[144]="Sorry, the mailbox already exists.";
				usercenterArray[145]="Data anomalies, re-request parameter is incorrect or cannot get mail.";
				usercenterArray[146]="Login successful.";
				usercenterArray[147]="Please select messages to be deleted.";
				usercenterArray[148]="Please select messages marked as read.";
				usercenterArray[149]="Please select Mark as unread mail.";
				usercenterArray[150]="Successful recovery";
				usercenterArray[151]="Please select mail";
				usercenterArray[152]="Sorry. You can only add up to five e-mail address at the same time.";
				usercenterArray[153]="Sorry. No mail";
				usercenterArray[154]="Are you sure you want to delete this message?";
				usercenterArray[155]="Trash";
				usercenterArray[156]="Sender";
				usercenterArray[157]="Sorry. Please select a country need to check ...";
				usercenterArray[158]="Please enter a search condition";
				usercenterArray[159]="Please enter the four-digit HS codes greater than or equal to the bill of lading number.";
				usercenterArray[160]="Confirm password cannot be empty";
				usercenterArray[161]="The code cannot be empty";
				usercenterArray[162]="Incorrect verification code";
				usercenterArray[163]="Password inconsistent";
				usercenterArray[164]="User name cannot be empty";
				usercenterArray[165]="Password is incorrect format";
				usercenterArray[166]="Browser Detection results suggest";
				usercenterArray[167]="I'm sorry. No search to relevant competitors and purchaser.";
				usercenterArray[168]="Sorry no search to rival the transaction record.";
				usercenterArray[169]="Digging depth reports";
			}
			return usercenterArray;
		}
		
		

	 /**
	  * 章华才
	  * proptmessage.js翻译
	  * @return
	  */
	 function promptmessageReport(){
	 	getLanuageType();
	 	var promptArray = new Array();
	 	if(lang=='c'){
	 		promptArray[0]="海关编码未定义!";
	 		promptArray[1]="产品描述未定义!";
	 		promptArray[2]="提单号未定义!";	
	 		promptArray[3]="提单号或者产品描述必须要输入一项!";	
	 		promptArray[4]="海关编码或者公司名称必须要输入一项!";	
	 		promptArray[5]="开始重量不是数字!";	
	 		promptArray[6]="结束重量不是数字!";	
	 		promptArray[7]="开始价格不是数字!";	
	 		promptArray[8]="结束价格不是数字!";	
	 		promptArray[9]="新增时间段开始日期不能为空!";
	 		promptArray[10]="新增时间段结束日期不能为空!";
	 		promptArray[11]="我的密码修改";
	 		promptArray[12]="您填写的信息不够完整,请确认您的操作!";
	 		promptArray[13]="新密码和确认密码必须是一致,请确认您的操作!";
	 		promptArray[14]="恭喜您,已经成功修改了登陆密码,请重新登陆系统!";
	 		promptArray[15]="您输入的原始密码是错误,请输入正确的!";
	 		promptArray[16]="您好！提单编号为必填项!";
	 		promptArray[17]="基本查询条件如下";
	 		promptArray[18]="当前选中的";
	 		promptArray[19]="值为";
	 		promptArray[20]="对不起,不能和自身进行对比,请确认您的操作!";
	 		promptArray[21]="海关数据对比分析";
	 		promptArray[22]="收起搜索条件";
	 		promptArray[23]="展开搜索条件";
	 		promptArray[24]="对不起，没有更低的价格了!";
	 		promptArray[25]="您好,请至少输入海关编码前6位进行查询!";
	 		promptArray[26]="请输入至少两位字符的产品描述!";
	 		promptArray[27]="开始数量不是数字!";
	 		promptArray[28]="结束数量不是数字!";
	 		promptArray[29]="开始日期不能为空！";
	 		promptArray[30]="结束日期不能为空!";
	 		promptArray[31]="查询最大的时间差不能超过一年!";
	 		promptArray[32]="对不起,对比数据不能为空,请您重新选择数据!";
	 		promptArray[33]="添加海关编码";
	 		promptArray[34]="多海关编码查询最多可以查三个!";
	 		promptArray[35]="多海关编码备注详情";
	 	}else{
	 		promptArray[0]="Customs Code undefined.";
	 		promptArray[1]="Product Description Undefined.";
	 		promptArray[2]="Bill of lading number undefined.";
	 		promptArray[3]="One bill of lading number or product description must be entered.";	
	 		promptArray[4]="One customs code or company name must be entered.";
	 		promptArray[5]="Start weight is not a number.";
	 		promptArray[6]="Weight is not the end of the digital.";
	 		promptArray[7]="Prices start is not a number.";
	 		promptArray[8]="Price is not the end of the digital.";	
	 		promptArray[9]="Added time period start date cannot be empty.";
	 		promptArray[10]="New period ending date cannot be empty.";
	 		promptArray[11]="My password change";
	 		promptArray[12]="Information incomplete. Please confirm your entry.";
	 		promptArray[13]="New Password and Confirm Password must be the same. Please confirm your action.";
	 		promptArray[14]="Congratulations, you have successfully changed the login password. Please re-visit system.";
	 		promptArray[15]="The original password you entered is incorrect. Please enter the correct one.";
	 		promptArray[16]="Hello. Bill of lading number is required.";
	 		promptArray[17]="Basic search conditions are as follows";
	 		promptArray[18]="Currently selected ";
	 		promptArray[19]="Value";
	 		promptArray[20]="Sorry, cannot compare itself. Please confirm your action.";
	 		promptArray[21]="Customs data comparison analysis";
	 		promptArray[22]="Hidden search";
	 		promptArray[23]="Show Search";
	 		promptArray[24]="Sorry, there is no lower prices of.";
	 		promptArray[25]="Hello, please enter at least six former customs code search.";
	 		promptArray[26]="Please enter at least two characters in the product description.";
	 		promptArray[27]="Start number is not a number.";
	 		promptArray[28]="End number is not a number.";
	 		promptArray[29]="Start Date cannot be empty.";
	 		promptArray[30]="End date cannot be empty.";
	 		promptArray[31]="The largest time difference cannot exceed one year.";
	 		promptArray[32]="Sorry, comparative data cannot be empty. Please re-select the data.";
	 		promptArray[33]="Add Customs Code";
	 		promptArray[34]="Customs Code searches can check up to three.";
	 		promptArray[35]="More details Customs Code Remarks";
	 	}
	 	return promptArray;
	 }


		
		
		function getLanuageType(){
			$.ajax({
					type:"POST",
					url:"/chooseLanguage/getLanguage",
					dataType:"json",
					async: false,
					error:function(data){
					},
					success:function(data){
						if(data.language=="zh"){
							lang='c';
						}else{
							lang='e';
						}
						return lang;
					}
			});
		}

