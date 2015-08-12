/**
 * ----------------------------------------下载全库-----------------------------------------
 */

var countryArray = "";

var isFlag = "";

var downPro;

var timeQuery;

/**
 * 初始化
 */
$(function(){
	// 点击单选按钮,修改input 
//	$('input[name="searchGroupRadio"]').click(searchGroupClick);
	// 加载国家
	initCountrySelect();
	//改表一级菜单上产品标签模块的背景色
	$("#nav-item_6").css('backgroundColor','#ffffff');
	$("#nav-item_6").find("a").find("span").css('color','#0066cc');
});

/**
 * 绑定单选按钮<br>
 * 已屏蔽
 */
function searchGroupClick(){
	var selectValuke = $('input[name="searchGroupRadio"]:checked').val().trim();
	initRedioValue();	//初始化单选框和输入框
	if (selectValuke) {
		if ('hscode' == selectValuke) {			//hscode
			$("#hscodeDiv").show();
		} else if ('product' == selectValuke) {	//产品描述
			$("#productDiv").show();
		} else if ('国家权限' == selectValuke) {	//国家权限
		}
	}
}

/**
 * 进度条初始化
 */
function downLoadDialog(){
	InitCueBox("全库下载", "Data Downloading……", "mydlog", 400, 120, true, 0.3, true);
//	art.dialog({
//		title:"全库下载",
//	    content: "<div style='text-align: center; color:#0066CC'>数据正在下载中……</div><br/><div id='downLoadPro' style='width:300px;'></div>",  
//	    id:'mydlog',
//	    width:400,
//	    height:120,
//	    lock:true,  //背景是否灰化
//	    opacity: 0.3,//透明度
//	    fixed:true //开启静止定位。它静止在浏览器某个地方不动，也不受滚动条拖动影响
//	});
}

/**
 * 初始化单选框和输入框
 */
function initRedioValue(){
	$("#hscodeDiv").hide();
	$("#productDiv").hide();
	$("#dowloadHscode").val('');
	$("#dowloadProductDesc").val('');
}

/**
 * 请求后台填充国家列表
 */
function requestCountrys(queryParams){
	$.ajax({
		url : getRootPath() + "/downloadDB/",
		data : "",
		dataType : "json",
		type : "post",
		success : function(data) {
			console.debug(data);
		}
	});
}

/**
 * 初始化国家
 */
function initCountrySelect() {
	var countryArray = reportArray.ALLDBDOWN;
	var countrySelect = $("#dowloadSpanel");
	countrySelect.empty();
	countrySelect.append("<input type=\"checkbox\" onClick=\"selAllCountryChange(this)\" name=\"allDowloadNames\"><span>Select All</span><br/>")
	for(var i=0;i<countryArray.length;i++){
		countrySelect.append("<input type=\"checkbox\" onClick=\"selOnlyCountryAddText(this)\" name=\"dowloadName\"  value=\""+countryArray[i]+"\"><span>"+countryArray[i]+"</span><br/>")
	}
	$('#dowloadCountry').combo({
		editable:false
	});
	$('#dowloadSpanel').appendTo($('#dowloadCountry').combo('panel'));
	$('#dowloadCountry').combo('setText', '').combo('hidePanel');
}

/**
 * 单击国家选中,显示在text
 * @param country
 */
function selOnlyCountryAddText(country){
	var text = "";
	$("input[name='dowloadName']:checkbox").each(function(){ 
        if($(this).attr("checked")){
        	text += $(this).val()+","; 
        }
    })
    $('#dowloadCountry').combo('setText', text.substring(0,text.lastIndexOf(','))).combo('hidePanel');
//	validateDowloadIexport(text);
}

/**
 * 防止页面刷新重复提交
 * @returns {Boolean}
 */
function checkSubmit(){
	var resubmit = $("#flagSubmit").val();
	if(resubmit) {
		return false;   
    } else {
    	resubmit = true;   
        return true;
    }
}

/**
 * 下载execl数据
 * @param v
 */
function dowloadAllDataClick(v){
	//防止页面刷新重复提交
	checkSubmit();
	//hscode
  	var hscode = $("input[name='dowloadHscode']").val().trim();
  	//产品描述
    var productDesc = $("input[name='dowloadProductDesc']").val().trim();
    //进出口类型
//    var iexportType = $('#dowloadIexportType').combobox('getValue').trim();
    //开始时间
    var startDate = $("input[name='dowloadStartDate']").val().trim();
    //结束时间
    var endDate = $("input[name='dowloadEndDate']").val().trim();
    //存放用户选择的国家
	var countryName = "";
	var lang = $("input[name='dowloadName']");
	for(var i=0;i<lang.length;i++){
		if(lang[i].checked){
			countryName = countryName+lang[i].value+",";
		}
	}
	countryArray = countryName;			// 赋值国家,用于计算获取索引的位置
	//获取用户点击事件：hscode/prodcut
	var radioVal = $("input[name='searchGroupRadio']:checked").val();
	if("hscode" == radioVal){				//hscode
		if(!hscode){
			$.messager.alert('Prompt',"HS Code CAN NOT be Empty！");
			return;
		} else {
		    var test = /^\d{6,10}$/;
		    if(!test.test(hscode)){
		    	$.messager.alert('Prompt',"Hs Code must be 6-10 digits!");
				return;
		    }
		}
	}else if ('product' == radioVal){			//产品描述
		if(productDesc==""){
			$.messager.alert('Prompt',"Product Description CAN NOT be Empty！");
			return;
		}
	}
   if((startDate != "" && endDate == "")||(startDate == "" && endDate != "")||(startDate == "" && endDate == "")) {
	   $.messager.alert('Prompt',"Date CAN NOT be Empty!");
		return;
   }
   if(endDate != ""){
	   if(!(verifyDate(startDate)&&verifyDate(endDate))){
		   $.messager.alert('Prompt',"Date Format is not correct!");
		   return;
	   }
	   var sDate = new Date(startDate);
	   var eDate = new Date(endDate);
	   if(eDate < sDate){
		   $.messager.alert('Prompt',"Finishing Date CAN NOT earlier than Starting Date!");
		   return;
	   }
	   var date1 = new Date(startDate);
	   var date2 = new Date(endDate);
	   var btime = date1.getTime(startDate);
	   var etime = date2.getTime(endDate);	
	   var time = parseInt(etime)-parseInt(btime);
	   if(time>2678400000){
		   $.messager.alert('Prompt',"Time span for downloading CAN NOT exceed one month!");
		   return;
	   }
	}
   	if(countryName==null||countryName=='') {
   		$.messager.alert("Prompt","Please choose a country！",'info');
		return;
	}
   	window.location.href = getRootPath() + "/downloadDB/downLoadDBByParams?goodsdescription="+ encodeURI(encodeURI(productDesc))
   	+ "&hscode=" + encodeURI(encodeURI(hscode))
   	+ "&beginDateFlex=" + startDate
   	+ "&endDateFlex=" + endDate
   	+ "&countrySelect=" + encodeURI(encodeURI(countryName));
   	//启动进度条
	downLoadDialog();		// 初始化进度条
	queryProBySeconds(downLoadPar,1000);
//	timeQuery = setInterval(downLoadPar,1000);		// 启动定时器
	//初始化进度条
//	$('#downLoadPro').progressbar({value: 0});
}


function downLoadPar(){
	$.ajax({
		url : getRootPath() + "/rightLibraryData/queryDataSchedule",
		data : {},
		type : "post",
		dataType : "json",
		success : function(data){
			data = data.key;
			if ('end' == data) {
				$('#downLoadPro').progressbar({value: 100});
				stopOfSeconds('mydlog',1000);
//				setTimeout('clearintervalPar()', 1000);
			} else {
				var index = getCountryIndex(data);
				var proValue = $('#downLoadPro').progressbar('getValue');
				if (index > proValue) {
					$('#downLoadPro').progressbar({value: index});
				}
			}
		},error:function(data){
			console.debug("错误了");
			console.debug(data);
		}
	});
}

/**
 * 获取进度条的进度值
 * @param country
 * @returns {Number}
 */
function getCountryIndex(country){
	if (countryArray.charAt(countryArray.length - 1) == ','){
		countryArray = countryArray.substring(0, countryArray.length - 1);
	}
	var countryArrays = countryArray.split(",");
	var proLength = 100 / countryArrays.length;
	console.debug(countryArrays);
	for (var i = 0; i < countryArrays.length; i++) {
		if (countryArrays[i] == country) {
			return parseInt(i * proLength);
		}
	}
}

/**
 * 全选
 * @param all
 */
function selAllCountryChange(all){
	var lang = $("input[name='dowloadName']");
	for(var i=0;i<lang.length;i++){
		lang[i].checked = all.checked;
	}
	var text = "";
	var lang = $("input[name='dowloadName']");
	for(var i=0;i<lang.length;i++){
		if(lang[i].checked){
			text+=lang[i].value+",";
		}
	}
	$('#dowloadCountry').combo('setText', text.substring(0,text.lastIndexOf(','))).combo('hidePanel');
//	validateDowloadIexport(text);
}