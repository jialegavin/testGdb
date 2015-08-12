
datasearch = function(){
	    //默认先加载阿根廷国家
		var country = "中国";
		//页面选择的字段id
		var selectFieldId = "";
		//页面选择的字段名
		var selectFieldName = "";
		//查询条件参数
		var queryParam = "";
		//报表数据表格隐藏列的值
		var hideColumn = "";
		//存放明细绑定字段数组
		var detailArray = "";
		datasearch.prototype.getCountry = function()
		{
		        return country;	
		}
		datasearch.prototype.setCountry = function(_country){
			country = _country;
		}
		
		datasearch.prototype.getSelectFieldId = function()
		{
		        return selectFieldId;	
		}
		datasearch.prototype.setSelectFieldId = function(_selectFieldId)
		{
			selectFieldId = _selectFieldId;
		}
		
		datasearch.prototype.getSelectFieldName = function()
		{
		        return selectFieldName;	
		}
		datasearch.prototype.setSelectFieldName = function(_selectFieldName)
		{
			selectFieldName = _selectFieldName;
		}
		
		datasearch.prototype.getQueryParam = function()
		{
		        return queryParam;	
		}
		datasearch.prototype.setQueryParam = function(_queryParam)
		{
			queryParam = _queryParam;
		}
		
		datasearch.prototype.getHideColumn = function()
		{
		        return hideColumn;	
		}
		datasearch.prototype.setHideColumn = function(_hideColumn)
		{
			hideColumn = _hideColumn;
		}
		datasearch.prototype.getDetailArray = function()
		{
		        return detailArray;	
		}
		datasearch.prototype.setDetailArray = function(_detailArray)
		{
			detailArray = _detailArray;
		}
}

/**
 * js页面初始化方法入口
 * @auther 罟皓
 */
$(document).ready(function()
{
	datasearch = new datasearch();
	//默认先加载阿根廷国家
	generateQueryCondition(datasearch.getCountry());
	// 按回车键即可实现查询
	$("#rightSearchbar").keypress(function(e){
		 if(e.which == 13){
			 subAuto();
		 } 
	});
	//改表一级菜单上贸易情报模块的背景色
	document.getElementById("nav-item_2").style.backgroundColor = "#ffffff";
	document.getElementById("font_color6").style.color = "#0066cc";
});

/**
 * 动态生成个国家的查询条件
 * @auther 罟皓
 */
function generateQueryCondition(country)
{
	$.post('/gbdbas/generateQueryCondition',{"country" : country},function(data){
		var htmlData = data['htmlData'];
		$("#showQueryCondition").html(htmlData);
		//原产国下拉裂变填值
		$('#c_origin_country').combobox({    
		    url:'/gbdbas/showAllCountryField?countryName='+country, 
		    valueField:'fieldValue',    
		    textField:'fieldValue'
		}); 
	},"json");
}

/**
 * 动态生成各国家搜索结果选项卡
 * @auther 罟皓
 */
function generateTab()
{
	$.ajax({  
	    url: '/gbdbas/generateTab',
	    dataType: "json",  
	    type: "get",  
	    async: false,
	    traditional: true,  
	    success: function (data) {
	    	var htmlData = data['htmlData'];
	    	$("#menubox").html(htmlData);
	    	$.parser.parse($("#menubox").parent());
	    }  
	}); 
}

/**
 * 在对话框中选择某一个国家
 * @auther 罟皓
 */
function chooseCountry(country)
{
	datasearch.setCountry(country);
	//$("#c_country").val(country);
	art.dialog({id:'countrysel'}).close();
	//清空选择字段中的id和名称
	datasearch.setSelectFieldId("");
	datasearch.setSelectFieldName("");
	generateQueryCondition(datasearch.getCountry());
	//切换国家还原成查询刚开始的样式
	queryStartDisplay();
}

/**
 * 弹出选择国家对话框
 * @auther 罟皓
 */
function changeCountry()
{
	openDivArtDialog("Choose a country", "countrysel", "countrysel",500,430,true);
}

/**
 * 打开多海关编码框
 * @param hscode 页面查询条件中显示hscode的name
 * @param addHscodeDiv 页面添加海关编码弹出框div的id
 * @return
 * @author honghao
 */
function addhscode(hscode,addHscodeDialog){
	var hscodeText = $("input[name='"+hscode+"']").val();
	var array =hscodeText.split(",");
	var  ctf = document.getElementById("addHscodeTable");
	//删除原来的talbe下的tr
	for(var i=0;i<ctf.childNodes.length;i++){
		ctf.removeChild(ctf.childNodes[i]);
	}
	//重新增加新的tr
	if(hscodeText!=''){
		for(var i =0;i<array.length;i++){
			var tr = '<tr style="line-height: 38px;"><td width="186px" align="left" style="color:#6B6C72;"><input name="addHscodeTr" type="hidden" value="'+array[i]+'"/>'+array[i]+'</td><td width="46px" align="right"><img alt="删除海关编码" width="22px" height="22px" src="\/gbdbas\/static\/img\/datasearch\/deleHscode.png"onclick="deleteCustomecodeFlexBr(this)"onmouseover="this.style.cursor=\'pointer\'"></td></tr>';
 			$("#addHscodeTable").append(tr);
		}
	}
	openDivArtDialog("Add HsCode", addHscodeDialog, addHscodeDialog, 400, 300,true);
}

/**
 * 往框中添加海关编码
 * @param addCustomInput 添加海关编码弹出框中input标签的id
 * @return
 * @author zjb
 */
function addHscodeToTable(addHscodeInp){
	var  addHscodeInpValue = $("#"+addHscodeInp).val().trim();
	//海关编码6到10位正则表达式
	var test = /^\d{6,10}$/;
	if(!test.test(addHscodeInpValue)){
		$.messager.alert('Prompt','HsCode you entered must be 6-10 digits！','info');
		return false;
	}
	var tr = '<tr style="line-height: 38px;" ><td width="186px" align="left" style="color:#6B6C72;"><input name="addHscodeTr" type="hidden" value="'+addHscodeInpValue+'"/>'+addHscodeInpValue+'</td><td width="46px" align="right"><img alt="删除海关编码" width="22px" height="22px" src="\/gbdbas\/static\/img\/datasearch\/deleHscode.png"onclick="deleteCustomecodeFlexBr(this)"onmouseover="this.style.cursor=\'pointer\'"></td></tr>';
	if($("#addHscodeTable").find("tr").length<5)
	{
		var hscodeArray = document.getElementsByName('addHscodeTr');
		for(i=0;i<hscodeArray.length;i++)
		{
	        if(hscodeArray[i].value == addHscodeInpValue.trim())
    	    {
	    	    $.messager.alert('Prompt','This HsCode have been added!',"info");
	    	    return false;
    	    }
	    }
		$("#addHscodeTable").append(tr);
    	$("#"+addHscodeInp).val("");
	}else
	{
		$("#"+addHscodeInp).val("");
	//	$.messager.alert('Prompt','多海关编码查询最多可以查五个!',"info");
		$.messager.alert('Prompt','Five HsCodes can be added on each search!',"info");
	}
}

/**
 * 往查询条件海关编码文本框中添加组织好的海关编码
 * customerCodeNameFlexBr 添加海关编码append中input的name
 * @param hscode 页面查询条件中显示hscode的id.
 * @param addCustomeCode 页面添加海关编码弹出框div的id
 * @return
 * @author honghao
 */
function saveAddedHscode(hscode,addCustomeCode){
	var custlen = $("input[name='addHscodeTr']");
	var value = "";
	for(var i=0;i<custlen.length;i++){
		value = value+custlen[i].value+",";
	}
	if(custlen.length>5){
		$.messager.alert('Prompt','Five HsCodes can be added on each search!','info');
	}
	else if(custlen.length == 0)
	{
		$("input[name='"+hscode+"']").val("");
	}
	else{
		$("input[name='"+hscode+"']").val(value.substring(0,value.lastIndexOf(',')));
	}
	art.dialog({id:addCustomeCode}).close();
}

/**
 * 往框中删除海关编码
 * @param ipt 
 * @return
 * @author honghao
 */
function deleteCustomecodeFlexBr(ipt){
	var del = $(ipt).parent().parent();
	del.remove();
}

/**
 * 翻译
 * @param id
 */
function translateValue(id){
	var countryValue = $("#"+id).val().trim();
	if(countryValue==""){
		$.messager.alert('Prompt','Please enter search value','info');
		return;
	}
	$("#"+id).val('Translating......');
	$.post('/gbdbas/translate/translateCountry',{"countryValue":countryValue},function(data){
		$("#"+id).val(data).css({"color":"#EE2C2C"});
	});
}

/**
 * 改变选项卡的样式
 * @param name
 * @param cursel
 * @param n
 * @auther 罟皓
 */
function setTab(name,cursel,n){
	for(i=1;i<=n;i++){
		var menu=document.getElementById(name+i);/* two1 */
		var con=document.getElementById("con_"+name+"_"+i);/* con_two_1 */
		var a = document.getElementById(name+"_a"+i);
		menu.className=i==cursel?"hover":"";/*三目运算  等号优先*/
		menu.style.backgroundImage = i==cursel?"url('/gbdbas/static/img/datasearch/tabout.png')":i==1?"url('/gbdbas/static/img/datasearch/tabbbefore.png')":"url('/gbdbas/static/img/datasearch/tabbbefore.png')";
		con.style.display=i==cursel?"block":"none";
		a.style.color=i==cursel?"#1369c0":"#ffffff";
	}
}

/**
 * 动态生成各国家搜索结果字段列的数据表格
 * @auther 罟皓
 */
function generateResultField(selectFieldId,selectFieldName,url)
{
	$.ajax({  
	    url: '/gbdbas/generateResultField?selectFieldId='+ encodeURI(encodeURI(selectFieldId))+'&selectFieldName='+encodeURI(encodeURI(selectFieldName))+'',
	    dataType: "json",  
	    type: "get",  
	    async: false,
	    traditional: true,  
	    success: function (data) {
	    	//更换国家对应选项卡
	    	generateTab();
	    	//返回搜索结果标签页
	    	setTab("tab",1,document.getElementsByName("resultTab").length);
	    	var htmlData = data['htmlData'];
	    	$("#resultFieldDiv").html("");
	    	$("#resultFieldDiv").html(htmlData);
	    	$.parser.parse($("#resultFieldDiv").parent());
	    	//延迟0.2秒请求后台不然会请求后台两次
	    	setTimeout(function(){
	    		loadResultFieldTable(url);
  			},200);
	    }  
	});
}

/**
 * 请求后台查询lucene生成查询结果展示在数据表格中
 */
function loadResultFieldTable(url)
{
	//构造查询条件
	var queryParam = getQueryParam();
	//文本框校验未通过
	if(!queryParam)
	{
		return;
	}
	datasearch.setQueryParam(queryParam);
	//请求后台查询lucene生成查询结果
	$('#resultFieldTable').datagrid({
	    url:url,
        queryParams:{'queryKey':queryParam['key'],'queryValue':queryParam['value']},
		pageNumber:1,
        pageSize:25,
        pagination:true,
        fitcolumns:false,
        autoRowHeight:false,
        sortorder: 'asc',
        loadMsg:'Loading...',
        pageList:[10,25,50,80,100],
        onLoadSuccess: function(data){
			if(data.rows.length == 0){
				alertMessage('Prompt','对不起，你输入的查询条件未检索到结果，请尝试更换条件值!','info');
		    } else {
				queryEndDisplay();
			}
        }  
  	});
}

/**
 * 构造查询条件参数传入后台查询
 * @auther 罟皓
 * @return queryParam 拼接的查询条件
 */
function getQueryParam()
{
	var queryParam = "";
	//海关编码
	var hscode = $("input[name='c_hscode']").val();
	//cif开始价格
	var cifstartprice = $("input[name='c_cifstartprice']").val();
	//cif结束价格
	var cifendprice = $("input[name='c_cifendprice']").val();
	//开始毛重
	var startweight = $("input[name='c_startweight']").val();
	//结束毛重
	var endweight = $("input[name='c_endweight']").val();
	//产品描述
	var goods_desc = $("input[name='c_goodsdesc']").val();
	//开始日期
	var startdate = $("input[name='c_startdate']").val();
	//结束日期
	var enddate = $("input[name='c_enddate']").val();
	//fob开始价格
	var fobstartprice = $("input[name='c_fobstartprice']").val();
	//fob结束价格
	var fobendprice = $("input[name='c_fobendprice']").val();
	//开始数量
	var startquantity = $("input[name='c_startquantity']").val();
	//结束数量
	var endquantity = $("input[name='c_endquantity']").val();
	//进口商
	var importer = $("input[name='c_importer']").val();
	//原产国
	var origin_country = $("input[name='c_origin_country']").val();
	//出口商
	var exporter = $("input[name='c_exporter']").val();
	//起运港
	var start_port = $("input[name='c_start_port']").val();
	//抵达港
	var end_port = $("input[name='c_end_port']").val();
	//目的国
	var dest_country = $("input[name='c_dest_country']").val();
	//开始净重
	var startnetweight = $("input[name='c_startnetweight']").val();
	//结束毛重
	var endnetweight = $("input[name='c_endnetweight']").val();
	//提单号
	var bl_number = $("input[name='c_bl_number']").val();
	//开始件数
	var startpackages = $("input[name='c_startpackages']").val();
	//结束件数
	var endpackages = $("input[name='c_endpackages']").val();
	//通知人
	var notifier = $("input[name='c_notifier']").val();
	//贸易类型
	var trade_type = $("input[name='c_trade_type']:checked").val();
	//公司名称
	var compan_name = $("input[name='c_companyname']").val();
	//收发货地
	var product_place = $("input[name='c_product_place']").val();
	
	
	//字段名组成的数组
	var keyArray= new Array("hscode","cif_value","g_weight","goods_desc","date","fob_value","quantity","importer","origin_country",
			                         "exporter","start_port","end_port","dest_country","n_weight","bl_number" ,"packages","notifier","trade_type"
			                         ,"company_name","product_place");
	//文本框值组成的数组和keyArray一一对应
	var valueArray= new Array(hscode,cifstartprice+","+cifendprice,startweight+","+endweight,goods_desc,startdate+","+enddate,
					   fobstartprice+","+fobendprice,startquantity+","+endquantity,importer,origin_country,exporter,start_port,end_port,
			           dest_country,startnetweight+","+endnetweight,bl_number, startpackages+","+endpackages,notifier,trade_type,compan_name,product_place);
	
	//构造查询条件
	var key = "";
	var value = "";
	
	for(var i=0;i<valueArray.length;i++)
	{
		if(valueArray[i] !="" && valueArray[i] !="undefined" && valueArray[i] !=null)
		{
			//如果是区间文本框字段
			if(valueArray[i].indexOf(",") != -1 && keyArray[i] !="hscode" 
				&& keyArray[i] == "cif_value" || keyArray[i] == "g_weight" || keyArray[i] == "date" || 
				keyArray[i] == "fob_value" || keyArray[i] == "quantity" || keyArray[i] == "n_weight"
				|| keyArray[i] == "packages")
			{
				var fieldValue = valueArray[i].split(",");
				//日期文本框
				if(keyArray[i] == "date")
				{
					//验证日期区间文本框输入是否合法
					if(!validateDateType(valueArray[i]))
					{
					    return false;
					}
					key+=keyArray[i]+";";
					value+=valueArray[i]+";";
				}
				//cif价格，fob价格，毛重，净重，数量，件数，数值区间文本框
				else
				{
					//验证这些区间输入是否合法
					if(!validateNumericType(keyArray[i],valueArray[i]))
					{
						return false;
					}
					//用户只输入第一个文本框,补全第二个文本框为无穷大
					if(fieldValue[0].trim() !="" && fieldValue[0] !="undefined" && fieldValue[0] !=null
							&& (fieldValue[1].trim() =="" || fieldValue[1] =="undefined" || fieldValue[1] ==null))
					{
						key+=keyArray[i]+";";
						value+=fieldValue[0]+",99999999999"+";";
					}
					//用户只输入第二个文本框,补全第一个文本框为0
					else if(fieldValue[1].trim() !="" && fieldValue[1] !="undefined" && fieldValue[1] !=null
							       && (fieldValue[0].trim() =="" || fieldValue[0] =="undefined" || fieldValue[0] ==null))
					{
						key+=keyArray[i]+";";
						value+="0,"+fieldValue[1]+";";
					}//用户只输入第二个文本框,补全第一个文本框为0
					else if(fieldValue[1].trim() !="" && fieldValue[1] !="undefined" && fieldValue[1] !=null
						       && (fieldValue[0].trim() !="" || fieldValue[0] !="undefined" || fieldValue[0] !=null))
					{
						key+=keyArray[i]+";";
						value+=valueArray[i]+";";
					}
				}
			}
			//海关编码，产品描述，进口商，出口商，原产国，等单文本框
			else
			{
				key+=keyArray[i]+";";
				value+=valueArray[i]+";";
			}
		}
	}
	
	//查询条件中至少包含以下条件之一方可查询
	if(!validateRequireCon(key))
	{
		if(datasearch.getCountry() == "中国")                                                                                                                                                                                                                                                                                             
		{
			alertMessage('Prompt','please enter HsCode！','info');
		}else
		{
			alertMessage('Prompt','Please enter at least one search value！','info');
		}
		return false;
	}
	
	//贸易情报模块正在查询时数据的样式 
	queryingDisplay();
	var queryParam = {"key" : key,"value" : value};
	return queryParam
}

/**
 * 各个国家贸易情报模块数据查询
 * @auther 罟皓
 */
function query()
{
	emptyDiv();
	//动态生成各国家搜索结果字段列的数据表格
	generateResultField(datasearch.getSelectFieldId(),datasearch.getSelectFieldName(),'/gbdbas/getResultFieldList');
}

/**
 * 初始化查询结果div
 */
var emptyDiv = function(){
	$("#con_tab_2").html("");
	$("#con_tab_3").html("");
	$("#con_tab_4").html("");
	$("#con_tab_2").css("display",'none');
	$("#con_tab_3").css("display",'none');
	$("#con_tab_4").css("display",'none');
}

/**
 * 下载excel报表汇总
 * @author XL
 */
function downloadReport()
{
	//导出路径
	window.location.href = '/gbdbas/export/downloadReport';
	//启动进度条
	downLoadDialog();		// 初始化进度条
	queryProBySeconds(downLoadPar,1500);
	
}

/**
 * 进度条初始化
 */
function downLoadDialog(){
	InitCueBox("Data Download", "Data is downloading……", "mydlog", 400, 120, true, 0.3, true);
}

/**
 * 进度条 业务处理
 */
var downLoadPar = function(){
	$.ajax({
		url : getRootPath() + "/rightLibraryData/queryDataSeachPro",
		data : {},
		type : "post",
		dataType : "json",
		success : function(data){
			data = data.key;
			if (100 == data) {
				$('#downLoadPro').progressbar({value: 100});
				stopOfSeconds('mydlog',1000);
			} else {
				$('#downLoadPro').progressbar({value: data});
			}
		},error:function(data){
			console.debug("error");
			console.debug(data);
		}
	});
}

/**
 * 查看详情数据
 * @auther 罟皓
 */
function viewdetail(value, row, index) {
	var id = "";
	if(datasearch.getCountry() =="阿根廷进口")
	{
		id = row.argentinaImportId;
	}else if(datasearch.getCountry() =="阿根廷出口")
	{
		id = row.argentinaExportId;
	}else if(datasearch.getCountry() =="智利进口")
	{
		id = row.chileImportId;
	}else if(datasearch.getCountry() =="智利出口")
	{
		id = row.chileExportId;
	}else if(datasearch.getCountry() =="乌拉圭进口")
	{
		id = row.uruguayImportId;
	}else if(datasearch.getCountry() =="乌拉圭出口")
	{
		id = row.uruguayExportId;
	}else if(datasearch.getCountry() =="巴拉圭进口")
	{
		id = row.paraguayImportId;
	}else if(datasearch.getCountry() =="巴拉圭出口")
	{
		id = row.paraguayExportId;
	}else if(datasearch.getCountry() =="秘鲁进口")
	{
		id = row.peruImportId;
	}else if(datasearch.getCountry() =="秘鲁出口")
	{
		id = row.peruExportId;
	}else if(datasearch.getCountry() =="厄瓜多尔进口")
	{
		id = row.ecuadorImportId;
	}else if(datasearch.getCountry() =="厄瓜多尔出口")
	{
		id = row.ecuadorExportId;
	}else if(datasearch.getCountry() =="哥伦比亚进口")
	{
		id = row.colombiaImportId;
	}else if(datasearch.getCountry() =="哥伦比亚出口")
	{
		id = row.colombiaExportId;
	}else if(datasearch.getCountry() =="巴西进口")
	{
		id = row.brazilImportId;
	}else if(datasearch.getCountry() =="委内瑞拉进口")
	{
		id = row.venezuelaImportId;
	}else if(datasearch.getCountry() =="委内瑞拉出口")
	{
		id = row.venezuelaExportId;
	}else if(datasearch.getCountry() =="巴拿马进口")
	{
		id = row.panamaImportId;
	}else if(datasearch.getCountry() =="巴拿马出口")
	{
		id = row.panamaExportId;
	}else if(datasearch.getCountry() =="美国进口")
	{
		id = row.usaImportId;
	}else if(datasearch.getCountry() =="墨西哥进口")
	{
		id = row.mexicoImportId;
	}else if(datasearch.getCountry() =="洪都拉斯进口")
	{
		id = row.hondurasImportId;
	}else if(datasearch.getCountry() =="洪都拉斯出口")
	{
		id = row.hondurasExportId;
	}else if(datasearch.getCountry() =="哥斯达黎加进口")
	{
		id = row.costaricaImportId;
	}else if(datasearch.getCountry() =="哥斯达黎加出口")
	{
		id = row.costaricaExportId;
	}else if(datasearch.getCountry() =="危地马拉进口")
	{
		id = row.guatemalaImportId;
	}else if(datasearch.getCountry() =="危地马拉出口")
	{
		id = row.guatemalaExportId;
	}else if(datasearch.getCountry() =="尼加拉瓜进口")
	{
		id = row.nicaraguaImportId;
	}else if(datasearch.getCountry() =="尼加拉瓜出口")
	{
		id = row.nicaraguaExportId;
	}else if(datasearch.getCountry() =="萨尔瓦多进口")
	{
		id = row.salvatoreImportId;
	}else if(datasearch.getCountry() =="萨尔瓦多出口")
	{
		id = row.salvatoreExportId;
	}else if(datasearch.getCountry() =="乌克兰进口")
	{
		id = row.ukraineImportId;
	}else if(datasearch.getCountry() =="英国进口")
	{
		id = row.ukImportId;
	}else if(datasearch.getCountry() =="中国")
	{
		id = row.chinaEightId;
	}else if(datasearch.getCountry() =="韩国")
	{
		id = row.koreaId;
	}else if(datasearch.getCountry() =="巴基斯坦进口")
	{
		id = row.pakistanImportId;
	}else if(datasearch.getCountry() =="越南进口")
	{
		id = row.vietnamImportId;
	}else if(datasearch.getCountry() =="越南出口")
	{
		id = row.vietnamExportId;
	}else if(datasearch.getCountry() =="印度进口")
	{
		id = row.indiaImportId;
	}else if(datasearch.getCountry() =="俄罗斯进口")
	{
		id = row.russiaImportId;
	}else if(datasearch.getCountry() =="俄罗斯出口")
	{
		id = row.russiaExportId;
	}
	else if(datasearch.getCountry() =="玻利维亚进口")
	{
		id = row.boliviaImportId;
	}
	return '<a href = \'javascript:getRowData(\"' + id + '\");\' style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'">More details</a>';
}
function testMe(){
	window.location.href="/gbdbas/";
}
/**
 * 生成查看详情对话框内容
 * @auther 罟皓
 */
function getRowData(id)
{
	$.post("/gbdbas/getRowData",{"id":id},function(data){
			var htmlData = data['htmlData'];
		    var hscode = data['hscode'];
		    var country = data['country'];
		    $("#showArea").html(htmlData);
		    $("#id_hscode").html(hscode);
		    $("#country").html(country+"数据");
		    translation('english');
		   // testMe();
		    openDivArtDialog("More details", "detailmessageDIV", "detailmessageDIV",900,600,true);
		   
		//    var language=''english;
			
     	},"json");
	
	
}

/**
 * 鼠标移动上去显示对应的title
 * @param value
 * @param rowData
 * @param rowIndex
 * @return
 * @auther 罟皓
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
 * 鼠标移动上去显示对应的百度搜索
 * @param value 包含了<font></font>需要截取后显示
 * @param rowData
 * @param rowIndex
 * @return
 * @auther 罟皓
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
		return "<a href=\"http://www.google.com/#q="+titleValue+"\" target=\"_blank\"><img title=\"More details in Google\" alt=\"More details in Google\" style=\"width: 15px;height:15px;border: 0px;\" src=\"/gbdbas/static/img/datasearch/google-g-logo-vector.png\" ></a>"+"<font title='"+titleValue+"'>"+value+"</font>";
	}else
	{
		return "N/A";
	}
}

/**
 * 贸易情报模块刚开始查询时数据的样式
* @auther 罟皓
 */
function queryStartDisplay()
{
	// 贸易情报模块刚开始查询时立即查询、收藏查询条件按钮样式
	$("#querygreyCollectionGrey").css("display",'none');
	$("#queryCollectionGrey").css("display",'block');
	$("#queryCollection").css("display",'none');
	//隐藏结果展示区域
	$("#resultDiv").css("display",'none');
	//选择列表对话框处于关闭状态
	$("#selectFieldDialog").css("display",'none');
}

/**
 * 贸易情报模块正在查询时数据的样式
 * @auther 罟皓
 */
function queryingDisplay()
{
	 //贸易情报模块正在查询时立即查询、收藏查询条件按钮样式
	$("#querygreyCollectionGrey").css("display",'block');
	$("#queryCollectionGrey").css("display",'none');
	$("#queryCollection").css("display",'none');
	//显示结果展示区域
	$("#resultDiv").css("display",'block');
	//选择列表对话框处于关闭状态
	$("#selectFieldDialog").css("display",'none');
}

/**
 * 贸易情报模块有结果时数据的样式
 *  @auther 罟皓
 */
function queryEndDisplay()
{
	//贸易情报模块查询有结果时立即查询、收藏查询条件按钮样式
	$("#querygreyCollectionGrey").css("display",'none');
	$("#queryCollectionGrey").css("display",'none');
	$("#queryCollection").css("display",'block');
	//显示结果展示区域
	$("#resultDiv").css("display",'block');
}

/**
 * 添加到收藏夹对话框
 * @auther 罟皓
 */
function addFavorites(){
	openDivArtDialog('Add to favorite', 'addFavoritesDiv', 'addFavoritesDiv', 466,370,true);
	$('#favoritesNameId').val("");
    $.post("/gbdbas/getQueryCondition",function(data)
    {
	   $('#favoritesDescId').val(data);
    });
}

/**
 * 保存收藏记录
 * @auther 罟皓
 */
function saveQueryCon(){
	var favoritesNameId = $('#favoritesNameId').val()
	if(favoritesNameId=='' || favoritesNameId==undefined || favoritesNameId==null)
	{
  	    $.messager.alert("Prompt", "Please Complete Your information and then Submit!","info");
	}
	else
	{
		var URL = '/gbdbas/myFavorites/addMyFavorites';
		$('#usercollection').form('submit',{
	        url: URL,
	        onSubmit: function(){
	            return $(this).form('validate');
	        }
		});
		art.dialog({id:'addFavoritesDiv'}).close();
		$.messager.alert("Prompt", "Added Successfully, You can find it at My Favorates","info");
		
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
 * @auther 罟皓
 */
function openSelectFieldDialog(){
	
	$.ajax({  
	    url: '/gbdbas/generateSelectField?selectFieldId='+ encodeURI(encodeURI(datasearch.getSelectFieldId()))+'&selectFieldName='+encodeURI(encodeURI(datasearch.getSelectFieldName() ))+'', 
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
	    	$("#autoExpandFav").height(tableHgt > digHgt ? tableHgt : digHgt);
	    }  
	}); 
	
}

/**
 * 选择字段保存
 * @auther 罟皓
 */
function selectField()
{
	var selectedValue = getSelectedFieldValue("selectField");
	var selectedId = getSelectedFieldId("selectField");
	datasearch.setSelectFieldName(selectedValue);
	datasearch.setSelectFieldId(selectedId);
	art.dialog({id:"selectfield"}).close();
	//重新展示查询列表
	generateResultField(selectedId,selectedValue,'/gbdbas/getSelectFieldList');
	$("#selectFieldDialog").css("display",'none');
}

/**
 * 返回一系列复选框中被选中的字段的value值串以逗号隔开
 * @auther 罟皓
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
 * @auther 罟皓
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
 * 导出搜索模块的数据excel或pdf
 * @param exportType 导出类型值1：excel 2:pdf
 * @author XL
 */
function exportFile(exportType)
{
	//导出excel或pdf路径
	var url= "/gbdbas/export/downloadData?type="+exportType;
	//在导出之前先进行条件判断
	$.post("/gbdbas/export/downloadService", {"type": exportType},
		function(data)
		{
			if(data.loadDataFlag)
			{
				$.messager.alert('Prompt',data.message);
			}
			else
			{
				if(data.loadNumFlag)
				{
					$.messager.alert('Prompt',data.message);
				}
				else
				{
					if(data.isLog)
					{
						$.messager.alert('Prompt',data.message);
					}
					else
					{
						//调用导出excel或pdf方法
						loadData(data,url);
					}
				}
			}
	   }, "json");
}

/**
 * 导出excel或pdf方法
 * @param data 后台获取的json数据
 * @param url 导出excel或pdf路径
 * @author XL
 */
function loadData(data,url)
{
	//页面显示提示下载
	document.getElementById("downloadNum").innerHTML=data.dowonlodMessage;
	//打开弹出框
	openDivArtDialog('Downloading', 'exportPDForExcelDlg', 'exportPDForExcelDlg',413, 232,true);
	//下载点击事件
	document.getElementById("dowonload").onclick=function(){
		//下载
		window.location.href = url;
		//关闭弹出框
		art.dialog({id:'exportPDForExcelDlg'}).close();
	}
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
 * 验证cif价格，fob价格，毛重，净重，数量，件数，数值区间文本框的值
 * @param fieldName 文本框的类型名称
 * @param fieldValue 文本框的值
 * @author honghao
 */
function validateNumericType(fieldName,fieldValue)
{
	var valueArray = fieldValue.split(",");
    //验证数值类型的正则表达式
	var regex = /^\d+(\.\d+){0,1}$/;
	if (valueArray[0] != null && valueArray[0] != '' && valueArray[0] != 'undefined') {
		if (!regex.test(valueArray[0])) {
			if(fieldName == "cif_value")
			{
				alertMessage('Prompt', 'CIF Value must be digits','info');
			}else if(fieldName == "fob_value")
			{
				alertMessage('Prompt', 'FOB Value must be digits','info');
			}else if(fieldName == "g_weight")
			{
				alertMessage('Prompt', '重量第一段文本框不是数字','info');
			}else if(fieldName == "n_weight")
			{
				alertMessage('Prompt', '净重第一段文本框不是数字','info');
			}else if(fieldName == "quantity")
			{
				alertMessage('Prompt', 'Amount must be digits','info');
			}else if(fieldName == "packages")
			{
				alertMessage('Prompt', '件数第一段文本框不是数字','info');
			}
			return false;
		}
	}
	if (valueArray[1] != null && valueArray[1] != '' && valueArray[1] != 'undefined') {
		if (!regex.test(valueArray[1])) {
			if(fieldName == "cif_value")
			{
				alertMessage('Prompt', 'CIF Value must be digits','info');
			}else if(fieldName == "fob_value")
			{
				alertMessage('Prompt', 'FOB Value must be digits','info');
			}else if(fieldName == "g_weight")
			{
				alertMessage('Prompt', '重量第二段文本框不是数字','info');
			}else if(fieldName == "n_weight")
			{
				alertMessage('Prompt', '净重第二段文本框不是数字','info');
			}else if(fieldName == "quantity")
			{
				alertMessage('Prompt', 'Amount must be digits','info');
			}else if(fieldName == "packages")
			{
				alertMessage('Prompt', '件数第二段文本框不是数字','info');
			}
			return false;
		}
	}
	
	if ((valueArray[0] != null && valueArray[0]  != 'undefined' && valueArray[0].trim() != "" )
			&& (valueArray[1] != null && valueArray[1] != 'undefined' && valueArray[1].trim() != "" ))
	{
		if (parseFloat(valueArray[0]) > parseFloat(valueArray[1])) 
		{
			if(fieldName == "cif_value")
			{
				alertMessage('Prompt', '第二段cif价格不能小于第一段cif价格','info');
			}else if(fieldName == "fob_value")
			{
				alertMessage('Prompt', '第二段fob价格不能小于第一段fob价格','info');
			}else if(fieldName == "g_weight")
			{
				alertMessage('Prompt', '第二段毛重不能小于第一段毛重','info');
			}else if(fieldName == "n_weight")
			{
				alertMessage('Prompt', '第二段净重不能小于第一段净重','info');
			}else if(fieldName == "quantity")
			{
				alertMessage('Prompt', '第二段数量不能小于第一段数量','info');
			}else if(fieldName == "packages")
			{
				alertMessage('Prompt', '第二段件数不能小于第一段件数','info');
			}
			return false;
		}
	}
	return true;
}

/**
 * 验证日期区间文本框的值
 * @param fieldValue 日期文本框的值
 * @author honghao
 */
function validateDateType(fieldValue)
{
	var valueArray = fieldValue.split(",");
	//验证第一段日期
	if (valueArray[0] == null || valueArray[0] == '') {
		alertMessage('Prompt', 'Starting Date Error','info');
		return false;
	}
	//验证第二段日期
	if (valueArray[1] == null || valueArray[1] == '') {
		alertMessage('Prompt', 'Finishing Date Error','info');
		return false;
	}
	
	if(datasearch.getCountry() == "中国" || datasearch.getCountry() == "韩国" || datasearch.getCountry() == "英国"
		|| datasearch.getCountry() == "委内瑞拉进口" || datasearch.getCountry() == "委内瑞拉出口")
	{
		valueArray[0] = formatDate(valueArray[0]);
		valueArray[1] = formatDate(valueArray[1]);
	}
	
	var startDate = new Date(valueArray[0]);
	var endDate = new Date(valueArray[1]);
	//计算两段日期的时间差
	var startTime = startDate.getTime(valueArray[0]);
	var endTime = endDate.getTime(valueArray[1]);
	var timeBetween = parseInt(endTime) - parseInt(startTime);
	if (startDate > endDate) {
		alertMessage('Prompt', 'Finishing Date must be later than Starting Date','info');
		return false;
	}
	
	// 验证时间段不超过三年
	if (timeBetween > 94608000000) {
		alertMessage('Prompt','Time Span can NOT be larger than 3 years!','info');
		return false;
	}
	return true;
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

/**
 * 验证查询条件中至少包含以下条件之一方可查询
 * @param queryKey 查询条件名称
 * @author honghao
 */
function  validateRequireCon(queryKey){
	var requireArray ="";
	var country = datasearch.getCountry();
	//中国特殊情况必须包含海关编码
	if(country =="中国")
	{
		//查询条件中至少包含以下条件之一方可查询
		requireArray =new Array("hscode");
	}else
	{
		//查询条件中至少包含以下条件之一方可查询
		requireArray =new Array("hscode","goods_desc","importer","origin_country","exporter","start_port"
				,"end_port","dest_country","bl_number","notifier");
		
	}
	
	
	for(var i=0;i<requireArray.length;i++)
	{
		if(queryKey.indexOf(requireArray[i]) != -1)
		{
			return true;
		}
	}
	return false;
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
	//打开弹出框
	openDivArtDialog('Add Customer', 'showCustomerDlg', 'showCustomerDlg',810, 500,true);
	updateJsp(false);
}

/**
 * 添加竞争对手
 * @param exporter 出口商
 * @author XL
 */
function showCompanyName(exporter) {
	$('#competitorfmForAllDB').form('reset');
	$("input[name='companyName']").val(exporter);
	//打开弹出框
	openDivArtDialog('Add Competitor', 'showEcuadorCompetitorDlg', 'closeCompetitorDialogId',810, 500,true);
	updateJsp(false);
}

/**
 * 处理中国，韩国，英国,委内瑞拉进出口等日期格式为MM/YYYY格式为YYYY-MM-DD的格式
 * @param srcDate
 * @author 罟皓
 */
function formatDate(srcDate)
{
	var array = srcDate.split("/");
	var decDate = array[1]+"-"+array[0]+"-01";
	return decDate;
}

/**
 * 鼠标点击产品搜索结果选项卡，展示页面内容
 * @author 罟皓
 */
function searchResultTab()
{
	query();
}

/**
 * 鼠标点击产品交易趋势报表选项卡，展示页面内容
 * @author 罟皓
 */
function cp_trade_report()
{
	$.ajax({  
	    url: '/gbdbas/generateSummaryField?type=cp_trade',
	    dataType: "json",  
	    type: "get",  
	    async: false,
	    traditional: true,  
	    success: function (data) {
	    	var htmlData = data['htmlData'];
			var tableId = data['tableId'];
			$("#con_tab_2").html(htmlData);
			$("#cp_trade_div").css("display",'none');
			$.parser.parse($("#con_tab_2").parent());
			var tableIdArray = tableId.split(",");
			//延迟0.2秒请求后台不然会请求后台两次
			loadReportDataAndChart('/gbdbas/getReportSummaryList',tableIdArray,"cp_trade","cp_trade_div");
	    }  
	}); 
	
}

/**
 * 鼠标点击国别分析报表选项卡，展示页面内容
 * @author 罟皓
 */
function cp_country_report()
{
	$.ajax({  
	    url: '/gbdbas/generateSummaryField?type=cp_country',
	    dataType: "json",  
	    type: "get",  
	    async: false,
	    traditional: true,  
	    success: function (data) {
	    	var htmlData = data['htmlData'];
			var tableId = data['tableId'];
			$("#con_tab_3").html(htmlData);
			$("#cp_country_div").css("display",'none');
			$.parser.parse($("#con_tab_3").parent());
			var tableIdArray = tableId.split(",");
			//延迟0.2秒请求后台不然会请求后台两次
			loadReportDataAndChart('/gbdbas/getReportSummaryList',tableIdArray,"cp_country","cp_country_div");
//			setTimeout(function(){
//				
//			},200);
	    }  
	}); 
}

/**
 * 鼠标点击港口分析报表选项卡，展示页面内容
 * @author 罟皓
 */
function cp_port_report()
{
	$.ajax({  
	    url: '/gbdbas/generateSummaryField?type=cp_port',
	    dataType: "json",  
	    type: "get",  
	    async: false,
	    traditional: true,  
	    success: function (data) {
	    	var htmlData = data['htmlData'];
			var tableId = data['tableId'];
			$("#con_tab_"+document.getElementsByName("resultTab").length).html(htmlData);
			$("#cp_port_div").css("display",'none');
			$.parser.parse($("#con_tab_"+document.getElementsByName("resultTab").length).parent());
			var tableIdArray = tableId.split(",");
			//延迟0.2秒请求后台不然会请求后台两次
			loadReportDataAndChart('/gbdbas/getReportSummaryList',tableIdArray,"cp_port","cp_port_div");
//			setTimeout(function(){
//				
//			},200);
	    }  
	}); 
}

/**
 * 加载报表汇总数据和图形展示
 */
function loadReportDataAndChart(url,tableIdArray,type,tableid)
{
	for(var i=0;i<tableIdArray.length;i++)
	{
		if(tableIdArray[i].trim()!="")
		{
			$('#'+tableid+'').css("display",'block');
			//请求后台查询lucene生成查询结果
			$('#'+tableIdArray[i]+'').datagrid({
				url:url,
				queryParams:{'tableId':tableIdArray[i],'type':type,'loadType':1},
				pageNumber:1,
				pageSize:10,
				pagination:false,
				fitcolumns:false,
				autoRowHeight:false,
				showFooter:true,
				sortorder: 'asc',
				loadMsg:'Loading...',
				pageList:[10,25,50,80,100],
				onLoadError: function(){
					//报表查询数据汇总鼠标悬浮样式以及鼠标离开样式
					queryStartDisplay();
				},
				onLoadSuccess: function(data){
					if(data.rows.length == 0){
						alertMessage('Prompt','对不起，你输入的查询条件未检索到结果，请尝试更换条件值!','info');
					} 
					else{
						if(data.total<10)
						{
							//去除加载更多div
							$("#"+data.reportType+"_loadMore").html("");
						}
						queryEndDisplay();
						//混合图形div的id
						var mixChartDivId = data.reportType+"_mixchart";
						//饼图形div的id
						var pieChartDivId = data.reportType+"_piechart";
						//清空
						$("#"+mixChartDivId).html("");
						$("#"+pieChartDivId).html("");
						if(data.mixChart !=null && data.mixChart !="" && data.mixChart != "undefined")
						{
							//展示混合图(柱状图和折线图结合)
							showMixEchart(mixChartDivId,data.mixChart,data.reportZhName,"Histogram",1210,500,"","Month");
						}
						if(data.pieChart !=null && data.pieChart !="" && data.pieChart != "undefined")
						{
							//展示饼图
							showPieChart(pieChartDivId,data.pieChart,data.reportZhName,"Pie",1210,500,data.pieChartFieldZhName);
						}
					}
				}  
			});
		}
	}
}

/**
 * 除交易趋势外其它报表功能大全(列表有同环比按钮)
 * @author 罟皓
 */
function functionDaQuan(value, row, index)
{
	if(row.hideColumn.indexOf("当前页面汇总") != -1)
	{
		return "";
	}else
	{
		return '<a href = \'javascript:reportDetails(\"' + row.hideColumn + '\");\' title=\"Detailed Statement"\ style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'"><img src=\"/gbdbas/static/img/datasearch/viewDetail.png\"></img></a>'
		+'&nbsp;'
		+'<a href = \'javascript:thbAnalysis(\"' + row.hideColumn + '\");\' title=\"MoM Analysis"\ style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'"><img src=\"/gbdbas/static/img/datasearch/THB.png\"></img></a>'
		+'&nbsp;'
		+'<a href = \'javascript:dataCompare(\"' + row.hideColumn + '\");\' title=\"Data Comparison"\ style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'"><img src=\"/gbdbas/static/img/datasearch/dataCompare.png\"></img></a>';
	}
}

/**
 * 交易趋势外报表功能大全(列表无同环比按钮)
 * @author 罟皓
 */
function functionQsDaQuan(value, row, index)
{
	if(row.hideColumn.indexOf("当前页面汇总") != -1)
	{
		return "";
	}else
	{
		return '<a href = \'javascript:reportDetails(\"' + row.hideColumn + '\");\' title=\"Detailed Statement"\ style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'"><img src=\"/gbdbas/static/img/datasearch/viewDetail.png\"></img></a>'
		+'&nbsp;'
		+'<a href = \'javascript:dataCompare(\"' + row.hideColumn + '\");\' title=\"Detailed Statement"\ style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'"><img src=\"/gbdbas/static/img/datasearch/dataCompare.png\"></img></a>';
	}
}





/**
 * 数据对比页面功能大全(列表无数据对比按钮)
 * @author 罟皓
 */
function compareFunctionDaQuan(value, row, index)
{
	if(row.hideColumn.indexOf("当前页面汇总") != -1)
	{
		return "";
	}else
	{
		var daQuan =  '<a href = \'javascript:reportDetails(\"' + row.hideColumn + '\");\' title=\"Detailed Statement"\ style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'"><img src=\"/gbdbas/static/img/datasearch/viewDetail.png\"></img></a>';
		if(row.hideColumn.split("罟")[1] != "qs")
		{
			daQuan+='&nbsp;'
			daQuan+='<a href = \'javascript:thbAnalysis(\"' + row.hideColumn + '\");\' title=\"MoM Analysis"\ style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'"><img src=\"/gbdbas/static/img/datasearch/THB.png\"></img></a>';
		}
		return daQuan;
	}
}

/**
 * 多海关编码报表功能大全(备注)
 * @author 罟皓
 */
function functionMutiHscodeDaQuan(value, row, index)
{
	if(row.hideColumn.indexOf("当前页面汇总") != -1)
	{
		return "";
	}else
	{
		return '<a href = \'javascript:reportDetails(\"' + row.hideColumn + '\");\' title=\"Detailed Statement"\ style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'"><img src=\"/gbdbas/static/img/datasearch/viewDetail.png\"></img></a>'
		+'&nbsp;'
		+ '<a href = \'javascript:mutiHscodeRemark(\"' + row.hideColumn + '\");\' title=\"Multi HsCode Comment"\ style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'"><img src=\"/gbdbas/static/img/datasearch/remark.gif\"></img></a>'
		+'&nbsp;'
		+'<a href = \'javascript:dataCompare(\"' + row.hideColumn + '\");\' title=\"Detailed Statement"\ style=\"color: #1369c0\" onmouseover="this.style.textDecoration=\'underline\'" onmouseout="this.style.textDecoration=\'none\'"><img src=\"/gbdbas/static/img/datasearch/dataCompare.png\"></img></a>';
	}
}

/**
 * 报表明细
 * @author 罟皓
 */
function reportDetails(hideColumn)
{
	//加载报表明细显示字段(章华才)
	dataSearchDetailShowField(datasearch.getCountry(),'/gbdbas/contrastre/showDetailField',hideColumn);
	
}

/**
 * 初始化请求后台拿到明细字段
 * @param countryName
 */
function dataSearchDetailShowField(countryName,url,hideColumn){
	//报表明细中文显示字段
	var detailName =new Array();
	//报表明细字段id显示字段
	var detailId =new Array();
	//存放明细绑定字段数组
	var detailArray =new Array();
	$.ajax({
		url:url,
		type:'post',
		dataType:'json',
		async: false,
		data:{"countryName":countryName},
		success:function(data) {
			if (data) {
				for(var i=0;i<data.name.length;i++){
					detailName[i] = data.name[i];
				}
				for(var i=0;i<data.id.length;i++){
					detailId[i] = data.id[i];
				}
				detailArray = getDataSearchDetailField(detailName,detailArray,detailId);
				datasearch.setDetailArray(detailArray);
				var array = hideColumn.split('罟');
				var columnKey = array[1];
				var columnValue = array[2];
				var countryName = datasearch.getCountry();
				openDetailDatagrid("datasearchDetail", {"columnKey":columnKey,"columnValue":columnValue},  datasearch.getDetailArray(), "/gbdbas/queryDataSearchDetail",false,true);
				//设置查询参数
				$("#detailParam").val(array);
				//设置国家
				$("#detailCountry").val(countryName);
		  }else{
			  $.messager.alert("Prompt","没有相关数据!")
		  }
		}
	});
}

/**
 * 获取明细绑定参数字段和名称
 * @param arrName 中文名称
 * @param arrId 字段
 * @author zhc
 * @return
 */
function getDataSearchDetailField(arrName,arr,arrId){
	
	var wd = 150;
	 //设置动态宽度
	if(arrId.length < 7){
		 wd = parseInt(1000/(arrId.length)-2);
	}
	for(var i=0; i < arrName.length;i++){
		if(arrName[i] == '重量' || arrName[i] == '数量' || arrName[i] == '金额'){
		    //sortable 排序
			arr.push({field:arrId[i],width:wd,title:arrName[i],align:'center',sortable:true,
				 formatter:function(value,row,index){
					 	if(value == '' || value == null ){
					 		return 0;
					 	}
//						var realValue = fomatDouble(value, 2);
					    return value;}});
	
		}else{
			arr.push({field:arrId[i],width:wd,title:arrName[i],align:'center',
				formatter:function(value,rows,index){
					if(value == ''){
						return 'N/A'
					}
					return value;
				}
			});
				
		}
	}
	return arr;
}

/**
 * 打开报表明细datagrid
 */
function openDetailDatagrid(id,queryParam,arr,url,isTrue,isDetail){
//	 alert("进入.....");
	 $('#'+id).datagrid({    
	    url:url, 
	    pageNumber:1,
	    queryParams:queryParam,
	    columns:[arr],    
	    onLoadSuccess: function(data){
			if(data.rows.length == 0){
				 $.messager.alert("Prompt","对不起，你输入的查询条件未检索到结果，请尝试更换条件值!");
		    } 
			else{
				openCompareDataDialog("Detailed Statement","datasearchDetailDiv","datasearchDetailId",1050, 480,true);
			}
	    } 
	}); 
}

/**
 * 导出明细数据
 * @author XL
 */
function exportDetailData(){
	//获取参数
	var param = $("#detailParam").val();
	//获取国家
	var country = $("#detailCountry").val();
	var array = param.split(',');
	var columnKey = array[1];
	var columnValue = array[2];
	var myform = "<form method='post' action='/gbdbas/export/exportSummaryDetail' id='exportPdfForm'>";
	myform += "<input name='columnKey' value='" + columnKey + "'/>";
	myform += "<input name='columnValue' value='" + columnValue + "'/>";
	myform += "<input name='country' value='" + country + "'/>";
	myform += "</form>";
	document.getElementById("postdata").innerHTML = myform;
	document.getElementById("exportPdfForm").submit();
}

/**
 * 选择数据进行对比
 * @author 罟皓
 */
function dataCompare(hideColumn)
{
	datasearch.setHideColumn(hideColumn);
	var hideValueArray = hideColumn.split("罟");
	$("#inputName").val(hideValueArray[2]);
	var bool = true;
	//请求后台拿到填充下拉框值
	$.post("/gbdbas/getSelectValue",{"hideColumn":hideColumn},function(data){
		if(data){
			if (data.selectValue.length<=1) {
				$.messager.alert("Prompt","No comparing data!");
				bool = false;
			}
		}
		datasearchCompareSelect(data.selectValue,hideValueArray[1],hideValueArray[2]);
		if(bool){
			openCompareDataDialog("Reposrt Comparison","selectCompareDataDialog","selectCompareDataDialog",500, 400,true);
		}
 	},"json");
	
}

/**
 * 获取选择对比选中下拉列表的数据
 * @returns selectedValue
 * @auther honghao
 */
 function getCompareSelectedValue(){
 	var selectedValue = "";
 	var lang = $("input[name='comparevalue']");
 	for(var i=0;i<lang.length;i++){
 		if(lang[i].checked){
 			selectedValue += "罟"+lang[i].value;
 		}
 	}
 	return selectedValue;
 }

/**
 * 选择对比下拉框动态填充数据
 * json：后台传来的值用于填充数据
 * @author zhc
 */
function datasearchCompareSelect(json,type,val)
{
	//当查询的数据为空时，提示用户更换查询条件，并且隐藏选择对比下拉列表和排序字段
	$("#sp").empty();
	for(var i=0;i<json.length;i++){
		//判断类型
		var optionText =  json[i].hideColumn;
		var value = json[i].hideColumn;
		if(optionText.length>32)
		{
			optionText=optionText.substring(0,32)+"……";
		}
		//过滤掉当前选中的input值
		if(value != val){
			$("#sp").append("<label><input type=\"checkbox\" name=\"comparevalue\" title=\""+value+"\"  value=\""+value+"\"><span title=\""+value+"\">"+optionText+"</span></label><br/>")
		}
	}
	$('#selectValue').combo({
		editable:false
	});
	//向select下拉列表填充option值
	$('#sp').appendTo($('#selectValue').combo('panel'));
}

/**
 * 根据选中的数据进行对比
 */
function saveCompareData()
{
	var selectedValue = getCompareSelectedValue();
	if(selectedValue !=null && selectedValue !="undefined" && selectedValue.trim() !="")
	{
		art.dialog({id:'selectCompareDataDialog'}).close();
		$.ajax({  
			url: '/gbdbas/generateDataCompareField?hideColumn='+ encodeURI(encodeURI(datasearch.getHideColumn()))+'',
			dataType: "json",  
			type: "get",  
			async: false,
			traditional: true,  
			success: function (data) {
				var htmlData = data['htmlData'];
				$("#dataCompareDiv").html(htmlData);
				$.parser.parse($("#dataCompareDiv").parent());
				//延迟0.1秒请求后台不然会请求后台两次
				setTimeout(function(){
					loadDataCompareData('/gbdbas/generateDataCompareData',datasearch.getHideColumn(),selectedValue);
				},100);
			}  
		});
	}else
	{
		$.messager.alert("Prompt","Please choose data for comparison!","info");
	}
}

/**
 * 加载数据对比所有数据和图形
 */
function loadDataCompareData(url,hideColumn,selectedValue)
{
	//请求后台查询lucene生成查询结果
	$('#'+hideColumn.split("罟")[1]+''+'_datacompare_table').datagrid({
		url:url,
		queryParams:{'hideColumn':hideColumn,'selectedValue':selectedValue},
		pageNumber:1,
		pageSize:25,
		pagination:true,
		fitcolumns:false,
		autoRowHeight:false,
		showFooter:true,
		sortorder: 'asc',
		loadMsg:'Loading...',
		pageList:[25,50,80,100],
		onLoadSuccess: function(data){
			if(data.rows.length == 0)
			{
				$("#dataCompareDiv").html("");
			} 
			openCompareDataDialog("Data Comparison Analyse", "dataCompareDialog", "dataCompareDialog",  900, 600,true);
			//混合图形div的id
			var mixChartDivId = data.reportType+"_datacompare_mixchart";
			//饼图形div的id
			var pieChartDivId =data.reportType+"_datacompare_piechart";
			//清空
			$("#"+mixChartDivId).html("");
			$("#"+pieChartDivId).html("");
			if(data.mixChart !=null && data.mixChart !="" && data.mixChart != "undefined")
			{
				//展示混合图(柱状图和折线图结合)
				showMixEchart(mixChartDivId,data.mixChart,data.reportZhName,"Histogram",866,350,"","Month");
			}
			if(data.pieChart !=null && data.pieChart !="" && data.pieChart != "undefined")
			{
				//展示饼图
				showPieChart(pieChartDivId,data.pieChart,data.reportZhName,"Pie",866,350,data.pieChartFieldZhName);
			}
		}  
	});
}


/**
 * 其他报告每列同环比报告分析
 * @author 罟皓
 */
function thbAnalysis(hideColumn)
{
	var hideColumnArray = hideColumn.split("罟");
	$.ajax({ 
	    url: '/gbdbas/generateTHBReportfield',
	    dataType: "json",
	    type: "get",
	    async: false,
	    traditional: true,  
	    success: function (data) {
	    	var htmlData = data['htmlData'];
			var thbReport = data['thbReport'];
			$("#thbDiv").html(htmlData);
			$.parser.parse($("#thbDiv").parent());
			//延迟0.2秒请求后台不然会请求后台两次
			setTimeout(function(){
				loadThbReportData('/gbdbas/generateTHBReportData',hideColumnArray[1],hideColumnArray[2],thbReport);
			},200);
	    }  
	});
}

/**
 * 交易趋势报告同环比报告分析
 * @author 罟皓
 */
function trendThbAnalysis(value)
{
	$.ajax({ 
	    url: '/gbdbas/generateTHBReportfield',
	    dataType: "json",
	    type: "get",
	    async: false,
	    traditional: true,  
	    success: function (data) {
	    	var htmlData = data['htmlData'];
			var thbReport = data['thbReport'];
			$("#thbDiv").html(htmlData);
			$.parser.parse($("#thbDiv").parent());
			//延迟0.2秒请求后台不然会请求后台两次
			setTimeout(function(){
				loadThbReportData('/gbdbas/generateTHBReportData',value,null,thbReport);
			},200);
	    }  
	});
}

/**
 * 加载同环比报表数据
 * @author 罟皓
 */
function loadThbReportData(url,reportType,value,thbReport)
{
	var thbReportArray = thbReport.split(",");
	for(var i=0;i<thbReportArray.length;i++)
	{
		//请求后台查询lucene生成查询结果
		$('#'+thbReportArray[i]+''+'_thb_table').datagrid({
			url:url,
			queryParams:{'singleReport':thbReportArray[i],'reportType':reportType,'value':value},
			pageNumber:1,
			pageSize:25,
			pagination:true,
			fitcolumns:false,
			autoRowHeight:false,
			sortorder: 'asc',
			loadMsg:'Loading...',
			pageList:[25,50,80,100],
			onLoadSuccess: function(data){
				var reportDiv = data.showType+"_thb_div";
				if(data.rows.length == 0){
					$("#"+reportDiv).html("");
				} 
				else
				{
					if(!data.showTHB)
					{
						$("#"+reportDiv).html("<div style=\"height:100px;width:98%;\"><div style=\"height:35px;background-color: #1369c0;\"><div style=\"font-size:16px;color:#FFFFFF;padding: 4px 0px 0px 10px;\">"+data.showTypeZhName+"报告</div></div><p style=\"color: #1369c0;text-align: center;\">操作提示：没有可查看的"+data.showTypeZhName+"报告</p></div>");
					}
				}
				//打开同环比对话框
				openCompareDataDialog("MoM Analysis","thbDialog","thbDialog",900, 600,true);
				//混合图形div的id
				var mixChartDivId = data.showType+"_thb_mixchart";
				//饼图形div的id
				var pieChartDivId = data.showType+"_thb_piechart";
				//清空
				$("#"+mixChartDivId).html("");
				$("#"+pieChartDivId).html("");
				if(data.mixChart !=null && data.mixChart !="" && data.mixChart != "undefined")
				{
					//展示混合图(柱状图和折线图结合)
					showMixEchart(mixChartDivId,data.mixChart,data.showTypeZhName,"",866,350,"","Month");
				}
				if(data.pieChart !=null && data.pieChart !="" && data.pieChart != "undefined")
				{
					//展示饼图
					showPieChart(pieChartDivId,data.pieChart,data.showTypeZhName,"Pie",866,350,data.pieChartFieldZhName);
				}
			}  
		});
	}
}

/**
 * 深度挖取
 * @author 罟皓
 */
function deepReport(value)
{
	$.ajax({  
	    url: '/gbdbas/generateDepthDiggingField?value='+ encodeURI(encodeURI(value))+'',
	    dataType: "json",  
	    type: "get",  
	    async: false,
	    traditional: true,  
	    success: function (data) {
	    	var htmlData = data['htmlData'];
			var allReportType = data['allReportType'];
			var reportTypeArray = allReportType.split(",");
			$("#depthDiggingDiv").html(htmlData);
			$.parser.parse($("#depthDiggingDiv").parent());
			//延迟0.2秒请求后台不然会请求后台两次
			setTimeout(function(){
				loadDepthDiggingData('/gbdbas/generateDepthDiggingData',value,reportTypeArray);
			},200);
	    }  
	});
}

/**
 * 加载深度挖取所有数据和图形
 */
function loadDepthDiggingData(url,value,reportTypeArray)
{
	for(var i=0;i<reportTypeArray.length;i++)
	{
		if(reportTypeArray[i].trim()!="")
		{
			//请求后台查询lucene生成查询结果
			$('#'+reportTypeArray[i]+''+'_depthdigging_table').datagrid({
				url:url,
				queryParams:{'value':value,'reportType':reportTypeArray[i]},
				pageNumber:1,
				pageSize:10,
				pagination:true,
				fitcolumns:false,
				autoRowHeight:false,
				sortorder: 'asc',
				loadMsg:'Loading...',
				pageList:[10,20,40,60],
				onLoadSuccess: function(data){
					if(data.rows.length == 0){
						var reportDiv = data.reportType+"_depthdigging_div";
						$("#"+reportDiv).html("");
					} 
					else{
						openCompareDataDialog("Advanced Search","depthDiggingDialog","depthDiggingDialog",900, 600,true);
						//混合图形div的id
						var mixChartDivId = data.reportType+"_depthdigging_mixchart";
						//饼图形div的id
						var pieChartDivId =data.reportType+"_depthdigging_piechart";
						//清空
						$("#"+mixChartDivId).html("");
						$("#"+pieChartDivId).html("");
						if(data.mixChart !=null && data.mixChart !="" && data.mixChart != "undefined")
						{
							//展示混合图(柱状图和折线图结合)
							showMixEchart(mixChartDivId,data.mixChart,data.reportZhName,"Histogram",866,350,"","Month");
						}
						if(data.pieChart !=null && data.pieChart !="" && data.pieChart != "undefined")
						{
							//展示饼图
							showPieChart(pieChartDivId,data.pieChart,data.reportZhName,"Pie",866,350,data.pieChartFieldZhName);
						}
					}
				}  
			});
		}
	}
}

/**
 * 加载更多数据
 * @param reportType
 * @param type
 * @param reportTypeZh 当前报告中文名如:进口商,出口商
 * @author Xl
 */
function queryMore(reportType,type,reportTypeZh)
{
	$.ajax({  
		url: '/gbdbas/loadSummaryField',
		data:{"reportType":reportType},
	    dataType: "json",  
	    type: "get",  
	    async: false,
	    traditional: true,  
	    success: function (data) {
	    	var htmlData = data['htmlData'];
	    	var tableId = data['tableId'];
			$("#showLoadDataDialog").html(htmlData);
			$.parser.parse($("#showLoadDataDialog").parent());
			//延迟0.2秒请求后台不然会请求后台两次
			setTimeout(function(){
				loadMoreReportData("/gbdbas/getReportSummaryList",tableId,type,reportType,reportTypeZh);
			},200);
	    }  
	}); 
}

/**
 * 点击查询更多加载报表汇总数据
 * @author XL
 */
function loadMoreReportData(url,tableId,type,reportType,reportTypeZh)
{
	var tableName =reportType + "_table";
	//请求后台查询lucene生成查询结果
	$('#'+tableId+'').datagrid({
		url:url,
		queryParams:{'tableId':tableName,'type':type,'loadType':2 },
		pageNumber:1,
		pageSize:10,
		pagination:true,
		fitcolumns:false,
		autoRowHeight:false,
		showFooter:false,
		sortorder: 'asc',
		loadMsg:'Loading...',
		pageList:[10,25,50,80,100]
	});
	openDivArtDialog('All'+reportTypeZh, 'showLoadDataDialog', 'showLoadDataDialog', 900,400,true);
}

/**
 * 多海关编码备注
 * @author 罟皓 
 */
function mutiHscodeRemark(hideColumn)
{
	$.ajax({ 
	    url: '/gbdbas/generateMutihscodefield?hideColumn='+ encodeURI(encodeURI(hideColumn))+'', 
	    dataType: "json",
	    type: "get",
	    async: false,
	    traditional: true,  
	    success: function (data) {
	    	var htmlData = data['htmlData'];
			$("#mutiHscodeRemarkDiv").html(htmlData);
			$.parser.parse($("#mutiHscodeRemarkDiv").parent());
			//延迟0.2秒请求后台不然会请求后台两次
			setTimeout(function(){
				loadmutiHscodeRemarkData('/gbdbas/getMultiHscodeRemarkData',hideColumn);
			},200);
	    }  
	});
}

/**
 *加载多海关编码数据
 * @author 罟皓
 */
function loadmutiHscodeRemarkData(url,hideColumn)
{
	var tableId=hideColumn.split("罟")[1]+"_mutihscode_table";
	//请求后台查询lucene生成查询结果
	$('#'+tableId+'').datagrid({
		url:url,
		queryParams:{'hideColumn':hideColumn},
		pageNumber:1,
		pageSize:10,
		pagination:true,
		fitcolumns:false,
		autoRowHeight:false,
		showFooter:false,
		sortorder: 'asc',
		loadMsg:'Loading...',
		pageList:[10,25,50,80,100]
	});
	openDivArtDialog('HsCode Commit', 'mutiHscodeRemarkDialog', 'mutiHscodeRemarkDialog', 870,220,true);

}