//对比类
var contrastre = null;
//验证类
var validate = null;
var divOjb = null;
var isAdd = null;

$(document).ready(function(){
	//改表一级菜单上产品标签模块的背景色
	$("#nav-item_5").css('backgroundColor','#ffffff');
	$("#nav-item_5").find("a").find("span").css('color','#0066cc');
});
function locahost(){
	location.href="/gbdbas/contrastre/transitDrillJsp";
}

/**
 * 设置参数并初始化信息
 */
function setParameters()
{
	//获取国家
	var countryName = $("#countryName").val();
	//获取海关编码
	var hscode = $("#hscode_contrast").val();
	//获取产品描述
	var goodsDesc = $("#goodsDesc").val();
	//获取公司名称
	var company = $("#company").val();
	//获取第一段开始时间
	var beginDate = $("#beginDate").val();
	//获取第一段结束时间
	var endDate = $("#endDate").val();
	//获取第二段开始时间
	var beginAddDate = $("#beginAddDate").val();
	//获取第二段结束时间
	var endAddDate = $("#endAddDate").val();
	//获取排序字段
	var sortKey = $("#sortKey").val();
	//进出口商类型
	var imexType = $("#selectType").val();
	//获取下拉框选择text文本值
	var imexText = $("#selectType").find("option:selected").text();
	
	var tradeType = '';
	if(countryName == '中国' || countryName == '韩国')
	{
		if(imexType == 'import')
		{
			tradeType = 'I';
		}
		else
		{
			tradeType = 'E';
		}	
	}
	//new校验对象
	validate = new contrastreValidate(countryName, hscode, goodsDesc, beginDate, endDate, beginAddDate, endAddDate, company, sortKey,imexType);
	contrastre = new contrastreQuery(countryName, hscode, goodsDesc, beginDate, endDate, beginAddDate, endAddDate, company, sortKey,imexType,tradeType,imexText);
	//飞空验证
	if(validate.validation())
	{
		//初始化tab页
		$("#tab1_content").css("display","block");
		$("#tab2_content").css("display","none");
		$("#tab3_content").css("display","none");
		//初始化tab页
		$("#tab_report1").css({"backgroundImage":"url('/gbdbas/static/img/datasearch/tabout.png')","color":"#1369c0","font-weight":"bold","font-size":"14px"});
		$("#tab_report2").css({"backgroundImage":"url('/gbdbas/static/img/datasearch/tabbbefore.png')","color":"#ffffff","font-size":"14px"});
		$("#tab_report3").css({"backgroundImage":"url('/gbdbas/static/img/datasearch/tabbbefore.png')","color":"#ffffff","font-size":"14px"});
		
		divOjb = "contrastreAdd";
		isAdd = "second";
		//显示tab页
		$("#contrastreCenter").css("display","block");
		//显示导出execl图片
		$("#execl_div").css("display","block");
		contrastre.queryReport("/gbdbas/contrastre/contrastreAddOutController", "contrastreAdd", "add", "showChartAdd","a","tab_report1",imexText);
		
	}
}


//**************************以下是切换tab触发的事件 js  *********************************
/**
 * 点击tab也触发事件
 */
var myclick = function(v) {  
    var llis = document.getElementById("tab_barCenter").getElementsByTagName("li");  
    for(var i = 0; i < llis.length; i++) {  
        var lli = llis[i];  
        if(lli == document.getElementById("tab_report" + v)) {  
            lli.style.backgroundImage = "url('/gbdbas/static/img/datasearch/tabout.png')";  
            lli.style.color = "#1369c0";  
            lli.style.fontWeight = "bold";  
        } else {  
            lli.style.backgroundImage = "url('/gbdbas/static/img/datasearch/tabbbefore.png')";  
            lli.style.color = "#ffffff"; 
            lli.style.fontWeight = "normal"; 
        }  
     }  
    var divs = document.getElementsByClassName("tab_css");  
    for(var i = 0; i < divs.length; i++) {  
        var divv = divs[i];  
        if(divv == document.getElementById("tab" + v + "_content")) {  
        	 divv.style.display = "block";  
        	//验证
    		if(validate.validation())
    		{
    			//新增
    			if(v == 1){
    				//显示导出execl图片
    				$("#execl_div").css("display","block");
    				isAdd = "second";
            		divOjb = "contrastreAdd";
            		contrastre.queryReport("/gbdbas/contrastre/contrastreAddOutController", "contrastreAdd", "add", "showChartAdd","a","tab_report1",contrastre.imexText);
            	}
    			//流失
    			else if(v == 2){
    				//显示导出execl图片
    				$("#execl_div").css("display","block");
    				isAdd = "first";
            		divOjb = "contrastreOut";
	    			contrastre.queryReport("/gbdbas/contrastre/contrastreAddOutController", "contrastreOut", "out", "showChartOut","a","tab_report2",contrastre.imexText);
            	}
    			//保持
    			else if(v == 3){
    				//显示导出execl图片
    				$("#execl_div").css("display","none");
            		contrastre.queryReportKeep("/gbdbas/contrastre/contrastreKeepController", "keepDiv","keep");	
            	}
    		}
        } else {  
            divv.style.display = "none";  
        }  
    }  
 }  
/**************结束切换tab触发的事件 js  *****************************/


//********************选择国家弹出对话框 js*******************************
/**
 * 弹出选择国家对话框
 * @auther zhc
 */
function changeCountryRepost()
{
	//没有进口商字段的国家不可用
	$("#mxg_click").css('display','none');
	$("#hdls_click").css('display','none');
	$("#agt_export_click").css('display','none');
	//打开对话框
	openDivArtDialog("Choose a country", "countrysel", "country",500,430,true);
}

/**
 * 选择国家
 * @param countryEnName  国家英文名称
 * @param countryNameCh  国家中文名称
 */
function chooseCountry(countryEnName){
	$("#countryName").val(countryEnName);
	art.dialog({id:'country'}).close();
	$.post("/gbdbas/contrastre/judgeCountryIsHaveImportOrExport",{"countryName":countryEnName},function(data){
		//判断是否存在海关编码
		if(data.hscode){
			$("#hscode_contrast").removeAttr("disabled");//要变成Enable，JQuery只能这么写  
			$("#hscode_contrast").val("");
		}else{
			$("#hscode_contrast").attr("disabled","disabled");//再改成disabled  
			$("#hscode_contrast").val("This country do not use Hs Code！");
		}
		
		//判断是否存在产品描述
		if(data.goodsDesc){
			$("#goodsDesc").removeAttr("disabled");//要变成Enable，JQuery只能这么写  
			$("#goodsDesc").val("");
		}else{
			$("#goodsDesc").attr("disabled","disabled");//再改成disabled 
			$("#goodsDesc").val("");
		}
		
		$("#selectType").html(data.ie);
		//隐藏元素
		hideElement(null,"contrastreCenter");
	},"json");
}

/*********************结束选择国家弹出对话框 js *****************************/

//********************导出新增流失进出口execl js******************************
 /**
  * 判断用户点击导出PDF或者是excel按钮的时候 有没有选中要导出的数据
  * @author zhc
  * @return 
  */
 function judgeSelectedDataDuibi(names,divOjb,imexType,country){
 	var checkedItems = $('#'+divOjb).datagrid('getChecked');
 	$.each(checkedItems, function(index, item) {
 		if(country == '英国进口' || country == '英国出口' || country == '中国' || country == '韩国'){
 			names[index] = item.company_name;
 		}else{
 			if(imexType == 'export'){
 				names[index] = item.exporter;
 			}else{
 				names[index] = item.importer;
 			}
 		}
 	});
 	if (names.length == 0) {
 		$.messager.alert("Prompt","Please choose the information want to be export!");
 		return false;
 	}
 }
 /**
  * 导出进出口商报表信息
  */
 function exportPDForEXCELPubDuibi(){
 	//获取国家
 	var countryName = $("#countryName").val();
 	//进出口商类型
 	var imexType = $("#selectType").val();
 	//先判断用户有没有选中要导出的数据
 	var names=new Array();
 	//获取导出的数据
 	var flag=judgeSelectedDataDuibi(names,divOjb,imexType,countryName);
 	if(flag==false){
 		return;
 	}
 	
 	//获取选择的值
 	var param=getJsonParam(names);
 	
 	window.location.href = '/gbdbas/contrastre/exportExcel?paman='+encodeURI(encodeURI(param))+'&countryName='+encodeURI(encodeURI(countryName))+'&imexType='+imexType+'';
 }
 
 /**
  * 根据国家不同 返回对应的json格式参数
  * @param names
  * @param country
  * @param exportType
  * @return
  */
 function getJsonParam(names){
 	var str = '';
 	for (var i = 0; i < names.length; i++) {
 		str = str + names[i]+",";
 	}
 	return str;
 }
 
/***********************结束新增流失进出口商导出execl js *************/

//**********************以下是报表明细js*****************************
 /**
  * 报表明细
  * @param value  选择的进出口商名称
  * @param type   进口商/出口商 importer/exporter/company_name
  */
 function detailed(value,type){
	 //获取国家
	 var country = contrastre.countryName;
	 //调用获取明细方法
	 detailShowField(country,'/gbdbas/contrastre/showDetailField',value,type);
 }
 /**
  * 请求后台拿到明细字段并显示
  * @param countryName
  */
 function detailShowField(countryName,url,value,type){
	//报表明细中文显示字段
	 var detailName = new Array();
	 //报表明细英文显示字段
	 var detailId = new Array();
 	//情况原始数组
 	detailName = [];
 	detailId = [];
 	$.ajax({
 		url:url,
 		type:'post',
 		dataType:'json',  
 		data:{"countryName":countryName},
 		success:function(data) {
 			for(var i=0;i<data.name.length;i++){
 				detailName[i] = data.name[i];
 			}
 			for(var i=0;i<data.id.length;i++){
 				detailId[i] = data.id[i];
 			}
 			 var detailArrayList = new Array();
 			 detailArrayList = getDetailField(detailName,detailArrayList,detailId,150,100);
 			 //区分是钻取明细和新增流失明细
 			 if(type.split("&")[0] == 'drill'){
 				 openDatagrid("detailTable", {"ie":value,"isAdd":type}, detailArrayList, "/gbdbas/contrastre/reportDetailInfo",false,true,1000,400,null);
 			 }else{
 				 openDatagrid("detailTable", {"ie":value,"isAdd":isAdd}, detailArrayList, "/gbdbas/contrastre/reportDetailInfo",false,true,1000,400,null);
 			 }
 		}
 	});
 }
/***************************结束报表明细**********************************/
 
 
//*************************以下是数据对比js*******************************
 
 /**
  * 获取对比的进出口商
  * @param value 选择的进出口商名称
  * @param type 进口商/出口商 importer/exporter/company_name
  */
 function getDuibi(value,type){
 	$("#inputName").val(value);
 	var bool = true;
 	$.post("/gbdbas/contrastre/selectValues",function(data){
 		if(data){
			if (data.selectValue.length<=1) {
				$.messager.alert("Prompt","No comparison data!");
				bool = false;
			}
		}
 		fillingCompareSelect(data.selectValue,type,$("#inputName").val());
 		//打开对话框
 		if(bool){
 	 		openCompareDataDialog("Comparison Report","reportDuibi","duibi",500, 300,true);
 	 	}
 	},"json");
 }
 /**
  * 选择对比下拉框动态填充数据
  * json：后台传来的值用于填充数据
  * @author zhc
  */
 function fillingCompareSelect(json,type,val)
 {
 	//当查询的数据为空时，提示用户更换查询条件，并且隐藏选择对比下拉列表和排序字段
 	$("#sp").empty();
 	for(var i=0;i<json.length;i++){
 		//判断类型
 		var optionText = null;
 		var value = null;
 		if(type == 'importer'){
 		    optionText = json[i].importer;
 		    value = json[i].importer;
 		}else if(type == 'exporter'){
 			 optionText = json[i].exporter;
 			 value = json[i].exporter;
 		}else if(type == 'company_name'){
 			 optionText = json[i].company_name;
 			 value = json[i].company_name;
 		}
 		
 		if(optionText.length>18)
 		{
 			optionText=optionText.substring(0,18)+"……";
 		}
 		//过滤掉当前选中的input值
 		if(value != val){
 			$("#sp").append("<label><input type=\"checkbox\" name=\"langDuiBi\" title=\""+value+"\"  value=\""+value+"\"><span title=\""+value+"\">"+optionText+"</span></label><br/>")
 		}
 	}
 	$('#selectValue').combo({
 		editable:false
 	});
 	//向select下拉列表填充option值
 	$('#sp').appendTo($('#selectValue').combo('panel'));
 }
 
 /**
  * 进行报表对比
  */
 function saveDb(){
	 var array = contrastre.fields;
	 art.dialog({id:'duibi'}).close();
	 //拿到input框中的值
	 var val = $("#inputName").val();
	 //拿到下拉框选中的值
	 var arr_falg=judgeArrayLen();
	//进出口商类型
	 var imexType = $("#selectType").val();
	 //判断数组是否大于0
	 if(arr_falg == true){
		 var arr = getSelectedValue();
		 var selectValue = $("#selectType").find("option:selected").text();
		 console.debug(selectValue);
		 openDatagrid("duiBiTable",{"val":val,"arr":arr,"imexType":imexType},array,"/gbdbas/contrastre/contrastByImportOrExport",true,false,1180,300,selectValue);
	 }else{
		 $.messager.alert("Prompt","Please choose the data for comparison!");
	 }
 }
 
 
 /**
 * 获取选中的数据
 * @returns {Array}
 */
 function getSelectedValue(){
 	var arr=new Array();
 	var lang = $("input[name='langDuiBi']");
 	for(var i=0;i<lang.length;i++){
 		if(lang[i].checked){
 			arr.push(lang[i].value);
 		}
 	}
 	return arr;
 }
 /**
  * 判断数组长度是否大于0
  */
 function judgeArrayLen(){
 	var arr= getSelectedValue();
 	if(arr.length>0){
 		return true;
 	}
 	else{
 		return false;
 	}
 }
/**************************结束数据对比js********************************************/

 
 
//************************以下是深度挖取js*********************************************
 /**
  * 深度挖取
  * @param param
  */
 function reportDrillContrastre(param,tradeType){
		//获取国家
		var countryName = $("#countryName").val();
		//进出口商类型
		var imexType = $("#selectType").val();
		var country = countryName;
		//特殊国家 中国-韩国，英国，美国
		if(country == "中国"){
			if(tradeType != ''){
				if(tradeType == "I"){
					$("#ie").val("import");
				}else{
					$("#ie").val("export");
				}
			}
		}else{
			$("#ie").val(imexType);
		}
		
	    //初始化报表明细字段
//	    detailShowField(country,'/gbdbas/contrastre/showDetailField'); 好像没有用到
		
		if(imexType == 'import'){
			$("#fontId").html("采购商");
		}else if(imexType == 'export'){
			$("#fontId").html("供应商");
		}
		$("#value").html(param);
		openCompareDataDialog("Advanced Search","drillReportDiv","drillId",1100, 600,true);
		//new汇总model
		var reportSum = new reportDrill(param,country,isAdd,imexType);
		//初始化深度挖取报告类型
		drillShowField(country,"/gbdbas/contrastre/getDrillField",reportSum,imexType);
 }
 //汇总总model
 var reportDrill = function(value,country,isAdd,imexType){
 	
	 //所以的深度挖取汇总方法
	 /**
	  * @param url 请求后台url地址
	  * @param type 要钻取的类型 如：ycg/mdg/jks/cks....
	  * @param name 要钻取的中文名称 如：原产国/目的国/进口商/出口商...
	  * @param drillName 要绑定datagrid参数字段的中文名称
	  * @param drilllId 要绑定datagrid的参数的Id名称
	  * @param dgId datagrid要渲染的div Id
	  * zhc
	  */
	 this.reportDrill = function (url,type,name,drillName,drilllId,typeId){
		 	var array_drill = new Array();
		 	var arr_drill = getDrillField(drilllId,array_drill,drillName,type,name,isAdd,322,190);
	 		//判断是否是国别报表
	 		var bool = false;
	 		if(typeId == 'country' || typeId == 'trande' || typeId == 'goodsDesc'){
	 			bool = true;
	 		}
	 		drillDatagrid(typeId+"_dg",{"country":country,"value":value,"isAdd":isAdd,"type":type,"imexType":imexType},arr_drill,url,bool,null,name+"汇总信息",typeId);
	 };
	 
 	
 };
 
 /**
  * 初始化请求后台拿到明细字段
  * @param countryName
  */
 function drillShowField(countryName,url,reportSum,imexType){
	 //深度挖取参数绑定显示中文字段
	 var drillName = new Array();
	 //深度挖取英文显示字段
	 var drilllId = new Array();
	 //深度挖取需要钻取的那些报告类型en
	 var drillType = new Array();
	 //深度挖取需要钻取的那些报告类型ch
	 var dirllTypeName = new Array();
	 //初始化隐藏(凑合用用：还是要改精简和稳定些的)
	 $("#country").css("display","none");
	 $("#trande").css("display","none");
	 $("#goodsDesc").css("display","none");
 	$.ajax({
 		url:url,
 		type:'post',
 		dataType:'json',  
 		data:{"countryName":countryName,"imexType":imexType},
 		success:function(data) {
 			//获取绑定的数据表格的字段名称
 			for(var i=0;i<data.sumField.length;i++){
 				drilllId[i] = data.sumField[i];
 			}
 			//获取绑定数据表格的中文名称
 			for(var i=0;i<data.sumName.length;i++){
 				drillName[i] = data.sumName[i];
 			}
 			//获取钻取报告类型字段
 			for(var i=0;i<data.drillType.length;i++){
 				drillType[i] = data.drillType[i];
 			}
 			//获取钻取报告类型的中文字段
 			for(var i=0;i<data.drillTypeName.length;i++){
 				dirllTypeName[i] = data.drillTypeName[i];
 				
 			}
 			
 			for(var i=0;i<drillType.length;i++){
 				//获取类型
 				var type = drillType[i];
 				//datagrid渲染的div
				reportSum.reportDrill("/gbdbas/contrastre/"+type,dirllTypeName[i].split("_")[0],dirllTypeName[i].split("_")[1],drillName,drilllId,type);
				$("#"+type).css("display","block");
 			}
 		}
 	});
 }
 /**
  * 打开datagrid
  */
 function drillDatagrid(id,queryParam,arr_drill,url,isTrue,isDetail,mulde,typeId){
  $('#'+id).datagrid({    
 	    url:url, 
 	    pageNumber:1,
 	    queryParams:queryParam,
 	    columns:[arr_drill],    
 	    loadMsg:'Loading……',
 	    width:1084,
 	    pagination:true,
 	    autoRowHeight:false,
 	    onLoadSuccess: function(data){
 			if(data.rows.length == 0){
 				 $.messager.alert("Prompt","对不起，你输入的查询条件未检索到结果，请尝试更换条件值!");
 		    } 
 			else{
 				//给相应的toolbar加上标题信息
 				$("#"+typeId+"_spanId").html(mulde);
 				//是否显示echart图表
 				if(isTrue){
 					showMixEchart(typeId+"_chartShow",data.showChart,mulde,"混搭图",1085,300,"","");
 				}
 			}
 	    } 
 	});  
 }
 /**********************结束深度挖取 js*****************************************************/
 
 //**********************提交客服中心的留言信息js***************************************
 
 /******************结束客服中心提交留言信息js*************************/
 
 /**
  * 打开datagrid
  */
 function openDatagrid(id,queryParam,arr,url,isTrue,isDetail,wd,ht,imexport){
	 $('#'+id).datagrid({    
	    url:url, 
	    pageNumber:1,
	    queryParams:queryParam,
	    columns:[arr],    
	    width:wd,
 	    height:ht,
 	    pagination:true,
 	    autoRowHeight:false,
	    onLoadSuccess: function(data){
			if(data.rows.length == 0){
				 $.messager.alert("Prompt","对不起，你输入的查询条件未检索到结果，请尝试更换条件值!");
		    } 
			else{
				
				var array = data.ch_name;
				//是否显示echart图表
				if(isTrue){
					//注意：这里还不够完善：array数组不一定有两个元素
						showMixEchart("showChartDuibiChart",data.showChart,imexport+"汇总报告","折线图",1180,400,"","");
//						showPieChart("showChartDuibiPie",data.showPie,"进口商汇总报告","饼图",1180,400);
				}
				//是否是明细
				if(isDetail){
					openCompareDataDialog("明细报表查询","detailDiv","detailId",1050, 400,true);
				}else{
					openCompareDataDialog("报表对比汇总信息","reportDuibiInfo","duibiInfo",450, 400,true);
				}
			}
	    } 
	});  
 }
 
 
 //***********************以前的contrastreport.js文件用来做datagrid查询绑定参数与后台交互和前台页面的一些条件验证************************************
 /** 
  * 对比查询封装方法
  * @param countryName 国家名称
  * @param hscode 海关编码
  * @param goodsDesc 产品描述
  * @param beginDate 第一段开始时间
  * @param endDate 第一段结束时间
  * @param beginAddDate 第二段开始时间
  * @param endAddDate 第二段结束时间
  * @param company 公司名称
  * @param sortKey 排序字段
  * @author 章华才
  * 2015-03-31
  */
 function contrastreQuery(countryName,hscode,goodsDesc,beginDate,endDate,beginAddDate,endAddDate,company,sortKey,imexType,tradeType,imexText)
 {
 	//国家
 	this.countryName = countryName;
 	//海关编码
 	this.hscode = hscode;
 	//产品描述
 	this.goodsDesc = goodsDesc;
 	//开始时间
 	this.beginDate = beginDate;
 	//结束时间
 	this.endDate = endDate;
 	//新增开始时间
 	this.beginAddDate = beginAddDate;
 	//新增结束时间
 	this.endAddDate = endAddDate;
 	//排序字段
 	this.sortKey = sortKey;
 	//公司名称
 	this.company = company;
 	//进出口商类型
 	this.imexType = imexType;
 	//获取下拉框选择文本值
 	this.imexText = imexText;
 	
 	//针对中国-英国-美国进出口商类型
 	this.tradeType = tradeType;
 	
 	var arr = '';
 	/**
 	 * 执行新增流失方法
 	 * 第一个参数：url
 	 * 第二个参数：表格渲染的div
 	 * 第三个参数：国家名称
 	 * 第四个参数：新增还是流失
 	 * 第五个参数：图表渲染的div
 	 */
 	this.queryReport = function(url,id,isAdd,showChart,isKeep,chartText,selectText){
// 		 if(countryName == 'china'){
// 			 imexType = tradeType;
// 		 }
 		 arr = judgeEquirementArgentina(isAdd,countryName,imexType,isKeep,"report",180,169,tradeType);
 		 this.fields = judgeEquirementArgentina(isAdd,countryName,imexType,isKeep,"duibi",150,150,tradeType);
// 		 //隐藏元素
 		showElement(id,"contrastreCenter");
 		showElement(id,showChart);
 		$('#'+id).datagrid({    
 		    url:url, 
 		    loadMsg:'Loading……',
 		    pagination:true,
 		    pageNumber:1,
 		    singleSelect:false, //取消单选
 		    checkbox:true,     //显示多选
 		    autoRowHeight:false,
 		    queryParams:{'hscode':hscode,'countryName':countryName,'goodDesc':goodsDesc,'tradeType':tradeType,
 				  'beginDate':beginDate,'endDate':endDate,'beginAddDate':beginAddDate
 				  ,'endAddDate':endAddDate,'sortKey':sortKey,'imexType':imexType,'addOrOut':isAdd},
 		    columns:[arr],
 		    onLoadSuccess: function(data){
 				if(data.rows.length == 0){
 					 $.messager.alert("Prompt","对不起，你输入的查询条件未检索到结果，请尝试更换条件值!");
 					 //隐藏元素
 					hideElement(id,"contrastreCenter");
 			    } 
 				else{
 					//显示元素
 					showElement(id,showChart);
// 					//清空
 					$("#"+showChart).html("");
 					//
 					$(".text_span").html(imexText);
 					$(".text_span").css("font-size","14px");
 					var text = null;
 					if(isAdd == 'add'){
 						text = '新增'+selectText;
 					}else if(isAdd == 'out'){
 						text = '流失'+selectText;
 					}
 					showMixEchart(showChart,data.barChart,$("#"+chartText).text(),"",1210,400,"月份","个数");
 					showPieChart("pie"+showChart,data.pieChart,$("#"+chartText).text(),"",1210,400,text+"个数");
 				}
 		    } 
 		});  
 	};
 	/**
 	 * @param  url 请求后台的url
 	 * @param id 数据显示渲染div
 	 */
 	this.queryReportKeep = function(url,id,isKeep){
 		arr = judgeEquirementArgentina("",countryName,imexType,isKeep,"report",150,110,tradeType);
 		var j = 0;
 		for(var i = 1; i <= 2; i++)
 		{
 			$('#'+id+i).datagrid({    
 			    url:url, 
 			    loadMsg:'Loading……',
 			    pagination:true,
 			    pageNumber:1,
 			    pageSize:20,
 			    autoRowHeight:false,
 			    queryParams:{'hscode':hscode,'countryName':countryName,'goodDesc':goodsDesc,'tradeType':tradeType,
 					  'beginDate':beginDate,'endDate':endDate,'beginAddDate':beginAddDate
 					  ,'endAddDate':endAddDate,'sortKey':sortKey,'imexType':imexType,'i':i},
 			    columns:[arr],
 			    onLoadSuccess: function(data){
 					if(data.rows.length == 0){
 						 $.messager.alert("Prompt","对不起，你输入的查询条件未检索到结果，请尝试更换条件值!");
 						 //隐藏元素
// 						 $("#tt").css("height","150px");
 				    } 
 					else
 					{	
 						 j++;
 						 $("#dateTis"+j).html(data.date);
 						//改变高度
// 				    	$("#tt").css("height","800px");
 					}
 			    } 
 			}); 
 		}
 	};
 }

 /**
  * 判断校验对比
  * @param countryName 国家名称
  * @param hscode 海关编码
  * @param goodsDesc 产品描述
  * @param beginDate 第一段开始时间
  * @param endDate 第一段结束时间
  * @param beginAddDate 第二段开始时间
  * @param endAddDate 第二段结束时间
  * @param company 公司名称
  * @param sortKey 排序字段
  * @author 章华才
  * 2015-03-31
  */
 function contrastreValidate(countryName,hscode,goodsDesc,beginDate,endDate,beginAddDate,endAddDate,company,sortKey,type)
 {
 	this.validation = function()
 	{
 		if(countryName == '请选择国家')
 		{
 			 $.messager.alert("Prompt",'Please choose a country!');
 			return false;
 		}else if(hscode == '' && goodsDesc == '')
 		{
 			 $.messager.alert("Prompt","Hs code or product description CAN NOT be empty!");
 			return false;
 		}else if(hscode == 'This country do not use Hs Code！' && goodsDesc == '')
 		{
 			 $.messager.alert("Prompt","Good description can NOT be empty!");
 			return false;
 		}else if(beginDate == '' || endDate == '')
 		{
 			 $.messager.alert("Prompt","Starting Date and Finishing Date CAN NOT be empty!");
 			return false;
 		}else if(beginAddDate == '' || endAddDate == '')
 		{
 			 $.messager.alert("Prompt","Starting Date and Finishing Date for history CAN NOT be empty!");
 			return false;
 		}else if(validateTime(beginDate, endDate, beginAddDate, endAddDate) == false)
 		{
 			return false;
 		}else
 		{
 			return true;
 		}
 	}
 }

 /**
  * 判断时间大小
  * @param firstStart
  * @param firstEnd
  * @param secondStart
  * @param secondEnd
  * @returns {Boolean}
  * @author 章华才
  * 2015-03-31
  */
 function validateTime(firstStart,firstEnd,secondStart,secondEnd)
 {
 	//第一段开始时间结束时间
 	var fsDate = new Date(firstStart);
 	var feDate = new Date(firstEnd);
 	//第二段开始时间结束时间
 	var fsAddDate = new Date(secondStart);
 	var feAddDate = new Date(secondEnd);
 	
 	if(feDate < fsDate)
 	{
 		 $.messager.alert("Prompt",'Finishing Date of histroy CAN NOT be earlier than Starting Date');
 		return false;
 	}
 	else if(feAddDate < fsAddDate)
 	{
 		 $.messager.alert("Prompt",'Finishing Date of Chosen Time CAN NOT be earlier than Starting Date');
 		return false;
 	}else if(fsAddDate < fsDate)
 	{
 		 $.messager.alert("Prompt",'Starting Date of Chosen Time CAN NOT be earlier than Starting Date of history time');
 		return false;
 	}
 	else 
 	{
 		return true;
 	}
 }

 /**
  * 隐藏元素
  * @param id
  */
 function hideElement(id,showChar){
// 	$('#'+id+"Remove").hide();
 	$('#'+showChar).hide();
 	$('#pie'+showChar).hide();
 }
 /**
  * 显示元素
  * @param id
  */
 function showElement(id,showChar){
// 	$('#'+id+"Remove").show();
 	$('#'+showChar).show();
 	$('#pie'+showChar).show();
 }

 /***************************以前的contrastreport.js文件用来做datagrid查询绑定参数与后台交互和前台页面的一些条件验证*****************************/
 
 
 
 //*************************以前的commom_fields.js文件用来做一些table字段的绑定***********************************
 /**
  * 根据国家名称返回对应的绑定字段
  * @param type 新增还是流失
  * @param countryName 国家名称
  * @author zhc
  */
 function judgeEquirementArgentina(type,countryName,isImEx,isKeep,isDbOrDetail,wd,otherwd,tradeType) {
 	var arr = new Array();
 	arr=getColumnName(countryName,arr,type,isImEx,isKeep,isDbOrDetail,wd,otherwd,tradeType);
 	return arr;
 }

 /**r
  * 格式化double类型
  * @param s
  * @param n
  * @returns {String}
  */
 function fomatDouble(s, n)  
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
  * 返回该报告类型对应的实际属性的name名称
  * @param country--国家
  * @param tp--报告类型
  * @author zhc
  * @return
  */
 function getColumnName(country,arr,type,isImEx,isKeep,isDbOrDetail,wd,otherwd,tradeType){
 	//请求后台拿到要绑定的datagrid字段
 	$.ajax({
 		url:"/gbdbas/contrastre/getDatagridField",
  		type:'post',
  		async: false,    //设置同步请求 不然return 结果为null
  		dataType:'json',  
  		data:{"country":country,"ieType":isImEx},
  		success:function(data) {
  			//字段Id
  			var field = data.field_id;
  			//字段Name
  			var name = data.field_name;
  			//动态设置宽度
  			var width = null;
  			if(isKeep == 'keep'){
  				width = parseInt(592/(field.length-1));
  			}else if(isDbOrDetail == 'duibi'){
  				width = parseInt((1180)/(field.length)-1);
  			}else{
  				width = parseInt((1210-27)/(field.length+1)-1);
  			}
  			var imOrex = null;
  			//不是对比或者明细
  			if(isDbOrDetail != 'duibi' && isDbOrDetail != "detail"){
  				if(isKeep != 'keep'){
  					arr.push({field:'ck',checkbox:true});
  				}
  			}
  			for(var i=0; i <field.length;i++){
  				if(name[i] == '重量' || name[i] == '数量' || name[i] == '次数' || name[i] == '金额' || name[i] == '尺寸' || name[i] == '件数'){
  					    var boolean = true;
  					    //如果等于keep的话就是不排序不显示默认的排序三角
  					    if(isKeep == 'keep'){
  					    	boolean = false;
  					    }
  						//sortable 排序
  						arr.push({field:field[i],title:name[i],align:'center',width:width,sortable:boolean,
  							formatter:function(value,row,index){
  								var realValue = fomatDouble(value, 2);
  								return realValue;}});
  			
  				}else if(field[i] == 'importer' || field[i] == 'exporter' || field[i] == 'company_name'){
  						imOrex = field[i];
  						arr.push({field:field[i],title:name[i],width:width,align:'center',
  							formatter:function(value,row,index){
  								var paman = row[imOrex];
  							    return "<a href=\"javascript:reportDrillContrastre('"+paman+"','"+tradeType+"');\" style='color:black;text-decoration: none;' onmouseover=\"this.style.color='blue';this.style.textDecoration='underline'\" onMouseOut=\"this.style.color='black';this.style.textDecoration='none'\" >"+value+"</a>";}});
  						
  				}else if(name[i] == '日期'){
  					//不是明细
  					if(isDbOrDetail != "detail"){
  						if(isKeep != 'keep'){
  							if(type == 'add'){
  								arr.push({field:field[i],title:'第一次交易时间',width:width,align:'center',
  									formatter:function(value,row,index){
  									var realValue = check(value);
  								    return realValue;}});
  							}else{
  								arr.push({field:field[i],title:'最后一次交易时间',width:width,align:'center',
  									formatter:function(value,row,index){
  									var realValue = check(value);
  								    return realValue;}});
  							}
  						}
  					}
  				}
  			}
  			//不是对比或者明细
  			if(isDbOrDetail != 'duibi' && isDbOrDetail != "detail"){
  				if(isKeep != 'keep'){
  					arr.push({field:'duibi',title:'功能编辑',align:'center',width:width,
  						formatter:function(value,row,index){
  							var paman = row[imOrex];
  							return "&nbsp;&nbsp;<a href=\"javascript:detailed('"+paman+"','"+imOrex+"');\"><img style=\"border:0px;width:20px;vertical-align:middle;\" src=\"/gbdbas/static/img/contrastreport/img/items.jpg\" /></a>&nbsp;&nbsp;&nbsp;&nbsp;" +
  									"<a href=\"javascript:getDuibi('"+paman+"','"+imOrex+"');\"><img style=\"border:0px;width:20px;vertical-align:middle;\" src=\"/gbdbas/static/img/contrastreport/img/duibi.png\" /></a>&nbsp;&nbsp;"}});
  				}
  			}
  			
  		}
 	});
 	return arr;
 	
 }


 /**
  * 获取明细绑定参数字段和名称
  * @param arrName 中文名称
  * @param arrId 字段
  * @author zhc
  * @return
  */
 function getDetailField(arrName,arr,arrId,wd,ht){
	 //设置动态宽度
	var wd = parseInt(1050/(arrId.length)-2);
 	for(var i=0; i <arrName.length;i++){
 		if(arrName[i] == '重量' || arrName[i] == '数量' || arrName[i] == '金额'){
 		    //sortable 排序
 			arr.push({field:arrId[i],title:arrName[i],align:'center',sortable:true,
 				 width:wd,
 				 styler : function(value, row, index) {
 					return 'border:1;';
 				 },
 				 formatter:function(value,row,index){
 						var realValue = fomatDouble(value, 2);
 					    return realValue;}});
 	
 		}else{
 			arr.push({field:arrId[i],title:arrName[i],align:'center',width:wd,
 				 styler : function(value, row, index) {
  					return 'border:1;';
  				 },
 				formatter:function(value,row,index){
 						//如果等于空的用N/A来表示	
 						if(value == ''){
 							 return 'N/A';
 						}
 						return value; 
 				}});
 				
 		}
 	}
 	return arr;
 }
 /**
  * 绑定深度挖取显示字段
  * @param arrName 中文名称
  * @param arrId 字段
  * @author zhc
  * @return
  */
 function getDrillField(drilllId,arr_drill,drillName,type,name,isAdd,no,no){
	 
	 //设置动态宽度
	var wd = null;
 	if(type == 'ycg'){
 		type = "origin_country";
 	}else if(type == 'mdg'){
 		type = "dest_country";
 	}else if(type == 'cks'){
 		type = "exporter";
 	}else if(type == 'jks'){
 		type = "importer";
 	}
 	//除产品描述之外
 	if(type == 'goodsDesc'){
 		wd = parseInt(1083/(drilllId.length+2));
 	}else{
 		wd = parseInt(1083/(drilllId.length+1));
 	}
 	
 	
 	arr_drill.push({field:type,title:name,align:'center',width:wd,
 		formatter:function(value,row,index){
			//如果等于空的用N/A来表示	
			if(value == ''){
				 return 'N/A';
			}
			return value; 
			 }});
 	for(var i=0; i <drillName.length;i++){
 		if(drillName[i] == '重量' || drillName[i] == '数量' || drillName[i] == '金额' || drillName[i] == '次数' || drillName[i] == '件数'){
 		    //sortable 排序
 			arr_drill.push({field:drilllId[i],title:drillName[i],align:'center',width:wd,
 				 styler : function(value, row, index) {
  					return 'border:1;';
  				 },
 				 formatter:function(value,row,index){
 						var realValue =  fomatDouble(value, 2);
 					    return realValue;}});
 	
 		}
 	}
 	//报表明细
 	if(type == "goodsDesc"){
 		arr_drill.push({field:'desc',title:'功能编辑',align:'center',width:wd,
 			 styler : function(value, row, index) {
					return 'border:1;';
				 },
 			formatter:function(value,row,index){
 				var paman = row[type];
 				return "&nbsp;&nbsp;<a href=\"javascript:detailed('"+paman+"','drill&"+type+"');\"><img style=\"border:0px;width:20px;vertical-align:middle;\" src=\"/gbdbas/static/img/contrastreport/img/items.jpg\" /></a>&nbsp;&nbsp;&nbsp;&nbsp;"}});
 	}
 	return arr_drill;
 }


 //判断日期格式
 function check(date){
 	var a = /^(\d{4})(\d{2})(\d{2})$/
 	if (!a.test(date)) { 
 		date = date.substr(0, 4) + "-" + date.substr(4, 2);
 	} 
 	else {
 		date = date.substr(0, 4) + "-" + date.substr(4, 2) + "-" + date.substr(6, 2);
 	}
 	return date;
 } 
 
 /****************************结束以前的commom_fields.js文件用来做一些table字段的绑定*************************************/

 