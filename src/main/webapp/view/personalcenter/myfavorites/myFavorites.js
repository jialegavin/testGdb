myFavorites = function(){
		 //默认先加载阿根廷国家
		var country = "";
		//页面选择的字段id
		var selectFieldId = "";
		//页面选择的字段名
		var selectFieldName = "";
		//查询条件参数
		var queryParam = "";
		//查询条件
		var queryValue = "";
		//表示是收藏页面
		var intoType = "myFavorites";
		
		myFavorites.prototype.getCountry = function()
		{
		        return country;	
		}
		myFavorites.prototype.setCountry = function(_country){
			country = _country;
		}
		
		myFavorites.prototype.getSelectFieldId = function()
		{
		        return selectFieldId;	
		}
		
		myFavorites.prototype.setSelectFieldId = function(_selectFieldId)
		{
			selectFieldId = _selectFieldId;
		}
		
		myFavorites.prototype.getSelectFieldName = function()
		{
		        return selectFieldName;	
		}
		
		myFavorites.prototype.setSelectFieldName = function(_selectFieldName)
		{
			selectFieldName = _selectFieldName;
		}
		
		myFavorites.prototype.getQueryParam = function()
		{
		        return queryParam;	
		}
		
		myFavorites.prototype.setQueryParam = function(_queryParam)
		{
			queryParam = _queryParam;
		}
		
		myFavorites.prototype.getQueryValue = function()
		{
		        return queryValue;	
		}
		
		myFavorites.prototype.setQueryValue = function(_queryValue){
			queryValue = _queryValue;
		}
		
		myFavorites.prototype.getIntoType = function()
		{
			return intoType;	
		}
		
		myFavorites.prototype.setIntoType= function(_intoType){
			intoType = _intoType;
		}
}

var tempQueryValue = "";

/**
 * js页面初始化方法入口
 * @auther 洪皓
 */
$(document).ready(function()
{
	//用户收藏记录
	$('#userCollectionDiv').animate({opacity:"0"},0);
	// 填充国家
	fillCountry();
	myFavorites = new myFavorites();
	//改表一级菜单上贸易情报模块的背景色
//	document.getElementById("nav-item_1").style.backgroundColor = "#ffffff";
//	document.getElementById("font_color1").style.color = "#0066cc";
	//显示二级菜单栏
	showSubMenu(1)
	//改变二级菜单栏样式
	changePCSubMenuColor(2);
	//清空选择字段中的id和名称
	myFavorites.setSelectFieldId("");
	myFavorites.setSelectFieldName("");
	//查询用户收藏记录
	setTimeout(function(){
		queryUserCollection();
	},200);
});

/**
 * 填充国家下拉框
 */
var fillCountry = function(){
	var country = $("#faCountry");
	for (x in reportArray.ALL) {
		country.append("<option value='"+reportArray.ALL[x]+"'>"+reportArray.ALL[x]+"</option>");
	}
}

/**
 * 根据条件查询收藏夹
 */
function queryAllFav(){
	if ((queryFun().beginDate && queryFun().endDate) || (!queryFun().beginDate && !queryFun().endDate)) {
		// 判断收藏时间是否为空
		$('#collectionTable').datagrid({
			url : getRootPath() + '/myFavorites/queryMyFavorItesByParams',
			pageNumber:1,
			pageSize:50,
			queryParams : queryFun(),
			fitcolumns:false,
			autoRowHeight:false,
			sortorder: 'asc',
			loadMsg:'Loading...',
			pageList:[50,80,100],
			onLoadSuccess: function(data){
				if(data.rows.length == 0) {
					//没有收藏记录的话隐藏收藏记录表格显示没有收藏记录提示
					alertMessage('rompt','No recorded information found','info');
				}else {
					//用户收藏记录
					$('#userCollectionDiv').animate({opacity:"1"},0);
				}
			}  
		});
	} else {
		$.messager.alert("Prompt", "Start Date or End Date can NOT be empty!");
	}
}

/**
 * 收藏夹查询参数初始化
 */
var queryFun = function(){
	var queryFaParams = {
		hscode : $("#faHscode").val(),
		country : $("#faCountry").val(),
		beginDate : $('input[name="beginDate"]').val(),
		endDate : $("#endDate").val()
	}
	return queryFaParams;
}

/**
 * 查看用户收藏
 * @auther 洪皓
 */
function queryUserCollection()
{
	//加载权限表数据
	$('#collectionTable').datagrid({
		url:'/gbdbas/myFavorites/queryMyFavorites',
		pageNumber:1,
		pageSize:50,
		fitcolumns:false,
		autoRowHeight:false,
		sortorder: 'asc',
		loadMsg:'Loading...',
		pageList:[50,80,100],
		onLoadSuccess: function(data){
			if(data.rows.length == 0)
			{
				//没有收藏记录的话隐藏收藏记录表格显示没有收藏记录提示
				alertMessage('Prompt','No recorded information found','info');
			}else
			{
				//用户收藏记录
				$('#userCollectionDiv').animate({opacity:"1"},0);
			}
			
		}  
	});
}

/**
 * 删除
 */
function delFaving(){
	var productId = '';
	var rows = $("#collectionTable").datagrid("getSelections");
	if (rows.length > 0) {
		for (var i = 0; i < rows.length; i++) {
			productId += rows[i].id + ",";
		}
		$.messager.confirm("Prompt", "Delete this product?",
			function(r) {
				if (r) {
					$.ajax({
						url : getRootPath() + '/myFavorites/delMyFavorites',
						data : {'id' : productId},
						type : "post",
						dataType: "json",
						success : function(data){
							var result = "";
							if (data.result == 1) {
								result = "Delete Successfully!";
								queryUserCollection();
							} else {
								result = "Delete Failed!";
							}
							$.messager.alert("Prompt", result, "info");
						}
					});
				}
			}
		);
	} else {
		$.messager.alert("Prompt", "Please choose one!", "info");
	}
}

/**
 * 鼠标移动上去显示对应的title
 * @param value
 * @param rowData
 * @param rowIndex
 * @return
 * @auther 洪皓
 */
function formatTitle(value,rowData,rowIndex)
{
	 return "<font title='"+value+"'>"+value+"</font>";
}

/**
 * 打印查看详情
 * @author XL
 */
function detailPrintData()
{
	$("#detailmessageDIV").printArea();
}


/**
 * 鼠标移动上去显示对应的百度搜索
 * @param value 包含了<font></font>需要截取后显示
 * @param rowData
 * @param rowIndex
 * @return
 * @auther 洪皓
 */
function searchData(value,rowData,rowIndex)
{
	myFavorites.setQueryValue(rowData.queryValue);
	return '<a href = \'javascript:generateResultField(\"' + rowData.queryValue + '\",\"' + rowData.enCountry + '\",\"/gbdbas/getResultFieldList\");\' title=\"查看数据"\ style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'">查看数据</a>'
}

/**
 * 动态生成收藏夹各国家搜索结果字段列的数据表格
 * @auther 洪皓
 */
function generateResultField(queryValue,country,url)
{
	if(country.trim()!="")
	{
		myFavorites.setCountry(country);
	}else
	{
		
	}
	$.ajax({
	    url: '/gbdbas/generateResultField?selectFieldId	='+ encodeURI(encodeURI(myFavorites.getSelectFieldId()))+'&selectFieldName='+encodeURI(encodeURI(myFavorites.getSelectFieldName()))+''+'&country='+encodeURI(encodeURI(myFavorites.getCountry()))+''+'&intoType='+encodeURI(encodeURI(myFavorites.getIntoType()))+'',
	    dataType: "json",
	    type: "get",
	    async: false,
	    traditional: true,
	    success: function (data) {
	    	var htmlData = data['htmlData'];
	    	$("#resultFieldDiv").html(htmlData);
	    	$.parser.parse($("#resultFieldDiv").parent());
	    	//延迟0.1秒请求后台不然会请求后台两次
	    	setTimeout(function(){
	    		loadResultFieldTable(url,queryValue);
  			},100);
	    }  
	}); 
}

/**
 * 请求后台查询lucene生成查询结果展示在数据表格中
 */
function loadResultFieldTable(url,queryValue)
{
	//复制queryValue
	tempQueryValue = queryValue;
	var key = "";
	var value = "";
	var queryCondition = queryValue;
	var conditionArray = queryCondition.split(";");
    for(var i=0;i<conditionArray.length;i++)
	{
    	if (conditionArray[i].split(":")[0]) {
    		key += conditionArray[i].split(":")[0]+";";
    	}
    	if (conditionArray[i].split(":")[1]) {
    		value += conditionArray[i].split(":")[1]+";";	
    	}
	}
	//请求后台查询lucene生成查询结果
	$('#resultFieldTable').datagrid({
	    url:url,
	    queryParams:{'queryKey':key,'queryValue':value},
		pageNumber:1,
        pageSize:25,
        pagination:true,
        fitcolumns:false,
        autoRowHeight:false,
        sortorder: 'asc',
        loadMsg:'Loading...',
        pageList:[25,50,80,100],
        onLoadSuccess: function(data){
			if(data.rows.length == 0)
			{
				alertMessage('Prompt','Sorry, no data has been founded, please change your search conditions!','info');
		    }else
	    	{
		    	queryEndDisplay();
	    	}
        }  
  	});
}

/**
 * 立即查询结果处理 N/A
 * @param value
 * @param rowData
 * @param rowIndex
 * @return
 * @auther 罟皓
 */
function formatOtherSearch(value, row, index){
	
	if(value != null && value !="NULL" && value !="null" && value !="" && value !="undefined"){
		return value;
	}else{
		return "N/A";
	}
	
}

/**
 * 查看详情数据
 * @auther 洪皓
 */
function viewdetail(value, row, index) {
	var id = "";
	var country = myFavorites.getCountry();
	if(myFavorites.getCountry() =="argentina_import")
	{
		id = row.argentinaImportId;
	}else if(myFavorites.getCountry() =="argentina_export")
	{
		id = row.argentinaExportId;
	}else if(myFavorites.getCountry() =="chile_export")
	{
		id = row.chileExportId;
	}else if(myFavorites.getCountry() =="chile_import")
	{
		id = row.chileImportId;
	}else if(myFavorites.getCountry() =="uruguay_import")
	{
		id = row.uruguayImportId;
	}else if(myFavorites.getCountry() =="uruguay_export")
	{
		id = row.uruguayExportId;
	}else if(myFavorites.getCountry() =="paraguay_import")
	{
		id = row.paraguayImportId;
	}else if(myFavorites.getCountry() =="paraguay_export")
	{
		id = row.paraguayExportId;
	}else if(myFavorites.getCountry() =="peru_import")
	{
		id = row.peruImportId;
	}else if(myFavorites.getCountry() =="peru_export")
	{
		id = row.peruExportId;
	}else if(myFavorites.getCountry() =="ecuador_import")
	{
		id = row.ecuadorImportId;
	}else if(myFavorites.getCountry() =="ecuador_export")
	{
		id = row.ecuadorExportId;
	}else if(myFavorites.getCountry() =="colom_import")
	{
		id = row.colombiaImportId;
	}else if(myFavorites.getCountry() =="colom_export")
	{
		id = row.colombiaExportId;
	}else if(myFavorites.getCountry() =="brazil_import")
	{
		id = row.brazilImportId;
	}else if(myFavorites.getCountry() =="vnrl_import")
	{
		id = row.venezuelaImportId;
	}else if(myFavorites.getCountry() =="vnrl_export")
	{
		id = row.venezuelaExportId;
	}else if(myFavorites.getCountry() =="panama_import")
	{
		id = row.panamaImportId;
	}else if(myFavorites.getCountry() =="panama_export")
	{
		id = row.panamaExportId;
	}else if(myFavorites.getCountry() =="usa_import")
	{
		id = row.usaImportId;
	}else if(myFavorites.getCountry() =="mexicon_import")
	{
		id = row.mexicoImportId;
	}else if(myFavorites.getCountry() =="honduras_import")
	{
		id = row.hondurasImportId;
	}else if(myFavorites.getCountry() =="honduras_export")
	{
		id = row.hondurasExportId;
	}else if(myFavorites.getCountry() =="const_import")
	{
		id = row.costaricaImportId;
	}else if(myFavorites.getCountry() =="const_export")
	{
		id = row.costaricaExportId;
	}else if(myFavorites.getCountry() =="wdml_import")
	{
		id = row.guatemalaImportId;
	}else if(myFavorites.getCountry() =="wdml_export")
	{
		id = row.guatemalaExportId;
	}else if(myFavorites.getCountry() =="njlg_import")
	{
		id = row.nicaraguaImportId;
	}else if(myFavorites.getCountry() =="njlg_export")
	{
		id = row.nicaraguaExportId;
	}else if(myFavorites.getCountry() =="sewd_import")
	{
		id = row.salvatoreImportId;
	}else if(myFavorites.getCountry() =="sewd_export")
	{
		id = row.salvatoreExportId;
	}else if(myFavorites.getCountry() =="wkl_import")
	{
		id = row.ukraineImportId;
	}else if(myFavorites.getCountry() =="uk_import")
	{
		id = row.ukImportId;
	}else if(myFavorites.getCountry() =="china")
	{
		id = row.chinaEightId;
	}else if(myFavorites.getCountry() =="korea")
	{
		id = row.koreaId;
	}else if(myFavorites.getCountry() =="pakistan_import")
	{
		id = row.pakistanImportId;
	}else if(myFavorites.getCountry() =="yn_import")
	{
		id = row.vietnamImportId;
	}else if(myFavorites.getCountry() =="yn_export")
	{
		id = row.vietnamExportId;
	}else if(myFavorites.getCountry() =="india_import")
	{
		id = row.indiaImportId;
	} else if (myFavorites.getCountry() == 'russian_export') {
		id = row.russiaExportId;
	} else if (myFavorites.getCountry() == 'russian_import') {
		id = row.russiaImportId;
	}
	return '<a href = \'javascript:getRowData(\"' + id + '\");\' style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'">查看详情</a>';
}


/**
 * 生成查看详情对话框内容
 * @auther 洪皓
 */
function getRowData(id) {
	console.debug(myFavorites.getCountry());
	if (id != 'undefined' && id) {
		$.post("/gbdbas/getRowData",{"id":id,'myFavorites':1,'country':myFavorites.getCountry()},function(data){
			var htmlData = data['htmlData'];
		    var hscode = data['hscode'];
		    var country = data['country'];
		    var country_cn = data['country_cn'];
		    openDivArtDialog("More details", "detailmessageDIV", "detailmessageDIV",900,600,true);
		    $("#showArea").html(htmlData);
		    $("#id_hscode").html(hscode);
		    $("#name_"+country_cn+"_data").html(country+"Data");
     	},"json");
	} else {
		alertMessage('Prompt','No more details about it!','info');
	}
}

/**
 * 鼠标移动上去显示对应的title
 * @param value
 * @param rowData
 * @param rowIndex
 * @return
 * @auther 洪皓
 */
function formatTitle(value,rowData,rowIndex)
{
	if(value != null && value.trim() !="NULL" && value.trim() !="null" && value.trim() !="" && value !="undefined")
	{
		var titleValue = "";
		//处理查询必要条件红色展示的标签
		var font_head = "<font color='#1369c0'>";
		var font_end = "</font>"
		if (value.indexOf(font_head) != -1)
		{
			titleValue = value.replace(font_head, '');
	    }
		if (titleValue.indexOf(font_end) != -1)
		{
			titleValue = titleValue.replace(font_end, '');
	    }
		if(value.indexOf(font_head) == -1 && titleValue.indexOf(font_end) == -1)
		{
			titleValue = value;
		}
		return"<font title=\""+titleValue+"\">"+value+"</font>";
	}else
	{
		return "N/A";
	}
}

/**
 * 鼠标移动上去显示对应的百度搜索
 * @param value 包含了<font></font>需要截取后显示
 * @param rowData
 * @param rowIndex
 * @return
 * @auther 洪皓
 */
function formatSearch(value,rowData,rowIndex)
{
	if(value != null && value.trim() !="NULL" && value.trim() !="null" && value.trim() !="" && value !="undefined")
	{
		var titleValue = "";
		//处理查询必要条件红色展示的标签
		var font_head = "<font color='#1369c0'>";
		var font_end = "</font>"
		if (value.indexOf(font_head) != -1)
		{
			titleValue = value.replace(font_head, '');
	    }
		if (titleValue.indexOf(font_end) != -1)
		{
			titleValue = titleValue.replace(font_end, '');
	    }
		if(value.indexOf(font_head) == -1 && titleValue.indexOf(font_end) == -1)
		{
			titleValue = value;
		}
		return "<a href=\"http://www.baidu.com/s?ie=utf-8&wd="+titleValue+"&cl=3\" target=\"_blank\"><img title=\"在百度上查看更多的信息\" alt=\"在百度上查看更多的信息\" style=\"width: 15px;height:15px;border: 0px;\" src=\"/gbdbas/static/img/datasearch/baidu_logo.png\" ></a>"+"<font title='"+titleValue+"'>"+value+"</font>";
	}else
	{
		return "N/A";
	}
}

/**
 * 查看详情翻译
 * @param language 语言
 * @author XL
 * 
 */
function translation(language) 
{
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	var id = [];
	var filePath = localhostPaht+"/gbdbas/view/datasearch/language/" + language + ".txt";
	$.ajax( {
		type : "POST",
		url : localhostPaht+"/gbdbas/view/datasearch/language/id.txt",
		dataType : 'text',
		success : function(data) 
		{
			id = data.split("\n");
			$.ajax( {
				type : "POST",
				url : filePath,
				dataType : 'text',
				success : function(data)
				{
					var language = data.split("\n");
					for ( var i = 0; i < id.length; i++)
					{
						var oneid = "name_"+id[i];
						changeLanguage(oneid.trim(), language[i].trim());
					}
				}
			});
		}
	});
}

/**
 * 翻译内容
 * @param id 当前翻译值的id
 * @param value 翻译值
 */
function changeLanguage(id, value) 
{
	var translationId = document.getElementById(id);
	if (translationId) {
		translationId.innerHTML = value;
	}
}

/**
 * 动态生成各国家搜索结果选择字段对话框内容并打开选择字段对话框
 * @auther 洪皓
 */
function openSelectFieldDialog(){
	$.ajax({  
	    url: '/gbdbas/generateSelectField?selectFieldId='+ encodeURI(encodeURI(myFavorites.getSelectFieldId()))+'&selectFieldName='+encodeURI(encodeURI(myFavorites.getSelectFieldName() ))+'', 
	    dataType: "json",  
	    type: "get",
	    async: false,
	    traditional: true,  
	    success: function (data) {  
	    	var htmlData = data['htmlData'];
	    	$("#selectFieldDiv").html(htmlData);
	    	$.parser.parse($("#selectFieldDiv").parent());
	    	$("#selectFieldDialog").css("display",'block');
	    	var tableHgt = $("#resultFieldDiv").height();
	    	var digHgt = $("#selectFieldDialog").height();
	    	$("#autoExpandFav").height((tableHgt > digHgt ? tableHgt : digHgt)+40);
	    }  
	}); 
	
}

/**
 * 选择字段保存
 * @auther 洪皓
 */
function selectField() {
	var selectedValue = getSelectedFieldValue("selectField");
	var selectedId = getSelectedFieldId("selectField");
	myFavorites.setSelectFieldName(selectedValue);
	myFavorites.setSelectFieldId(selectedId);
	art.dialog({id:"selectfield"}).close();
	//重新展示查询列表	myFavorites.getQueryValue()
	generateResultField(tempQueryValue,'','/gbdbas/getSelectFieldList');
	$("#selectFieldDialog").css("display",'none');
}

/**
 * 返回一系列复选框中被选中的字段的value值串以逗号隔开
 * @auther 洪皓
 */
function getSelectedFieldValue(name){
	var selectedValue = "";
	var lang = $("input[name='"+name+"']");
	for(var i=0;i<lang.length;i++){
		if(lang[i].checked){
			selectedValue = selectedValue+lang[i].value+",";
		}
	}
	return selectedValue;
}

/**
 * 返回一系列复选框中被选中的字段的id值串以逗号隔开
 * @auther 洪皓
 */
function getSelectedFieldId(name){
	var selectedId = "";
	var lang = $("input[name='"+name+"']");
	for(var i=0;i<lang.length;i++){
		if(lang[i].checked){
			selectedId = selectedId+lang[i].id+",";
		}
	}
	return selectedId;
}

/**
 * 打印查看详情
 * @author XL
 */
function detailPrintData()
{
	$("#detailmessageDIV").printArea();
}

/**
 * 查看详情翻译语言列表显示
 * @author honghao
 */
function show(v){
	document.getElementById(v).style.display="block";
}
/**
 * 查看详情翻译隐藏语言列表显示
 * @author honghao
 */
function hide(v){
	document.getElementById(v).style.display="none";
}
/**
 * 查看详情翻译语言列表显示鼠标悬浮样式
 * @author honghao
 */
function Hover(v){
	document.getElementById(v).style.backgroundColor="#383f48";
}

/**
 * 查看详情翻译语言列表显示鼠标离开样式
 * @author honghao
 */
function notHover(v){
	document.getElementById(v).style.backgroundColor="#434a53";
}

/**
 * 查看详情导出pdf
 * @author honghao
 */
function detailExportPdf(v){
	//生成导出详情页面模板
	$.ajax({  
	    url: '/gbdbas/export/generatePdfTemplate',
	    dataType: "json",
	    type: "get",
	    async: false,
	    traditional: true,
	    success: function (data) {
	    	//生成导出详情页面模板
	    	window.location.href = '/gbdbas/export/exportPdf';
	    }
	}); 
}

/**
 * 添加客户信息
 * @param importer 进口商
 * @author XL
 */
function showCustomerName(importer)
{
	$('#argentinaCustomerfm').form('reset');
	$("input[name='companyName']").val(importer);
	updateJsp(false);
	//打开弹出框
	openDivArtDialog('Add Customer', 'showCustomerDlg', 'showCustomerDlg',950, 500,true);
}

/**
 * 添加竞争对手
 * @param exporter 出口商
 * @author XL
 */
function showCompanyName(exporter)
{
	$('#competitorfmForAllDB').form('reset');
	$("input[name='companyName']").val(exporter);
	openDivArtDialog('Add Competitor', 'showEcuadorCompetitorDlg', 'closeCompetitorDialogId',950, 500,true);
	updateJsp(false);
}

/**
 * 翻译
 * @param id
 */
function translateValue(id){
	var countryValue = $("#"+id).val().trim();
	if(countryValue==""){
		$.messager.alert('Prompt','Please enter search value！','info');
		return;
	}
	$("#"+id).val('Translating, please wait......');
	$.post('/gbdbas/translate/translateCountry',{"countryValue":countryValue},function(data){
		$("#"+id).val(data).css({"color":"#EE2C2C"});
	});
}

/**
 * 收藏夹模块刚开始查询时数据的样式
* @auther 洪皓
 */
function queryStartDisplay()
{
	//隐藏结果展示区域
	$("#resultFieldDiv").css("display",'none');
	//选择列表对话框处于关闭状态
	$("#selectFieldDialog").css("display",'none');
	//显示搜索结果样式
	$("#searchResult").css("display",'none');
	
}

/**
 * 收藏夹模块正在查询时数据的样式
 * @auther 洪皓
 */
function queryingDisplay()
{
	//显示结果展示区域
	$("#resultFieldDiv").css("display",'block');
	//选择列表对话框处于关闭状态
	$("#selectFieldDialog").css("display",'none');
	//显示搜索结果样式
	$("#searchResult").css("display",'none');
}

/**
 * 收藏夹模块有结果时数据的样式
 *  @auther 洪皓
 */
function queryEndDisplay()
{
	//显示结果展示区域
	$("#resultFieldDiv").css("display",'block');
	//显示搜索结果样式
	$("#searchResult").css("display",'block');
}

/**
 * @param tip 提示标题
 * @param text 提示内容
 * @param iconType  提示图标类型
 * @author honghao
 */
function  alertMessage(tip,text,iconType){
        $.messager.alert(tip,text,iconType);
        queryStartDisplay();
}
