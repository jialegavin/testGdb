/**
 * author:hu


 * 功能：填充页面的统计数据{混合图（折线+柱状图）、饼状图}
 * 混合图：具有分页、放大、缩小展示页面的功能
 * 混合图的数据格式
 * 
 [
      {
        "data":["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],
        "name":"月份", //  该数据的名称 
        "type":"x",  //   该条数据的类型：line-折线图、bar-柱形图、x-轴坐标  如果是x轴则yIndex的值为空
        "yIndex":0 ,//   该数据对应的y轴坐标：0-左y轴、1-右y轴  
        "init":0   //默认时0  就是初始化显示   1 初始化图表不显示
       },
       {
         "data":[2,4.9,7,23.2,25.6,76.7,135.6,162.2,32.6,20,6.4,3.3],
         "name":"蒸发量",
         "type":"line",
         "yIndex":0 ,
         "init":0
        }...
]

 * 饼状图的数据格式
[
	{value:335, name:'直接访问'},
	{value:310, name:'邮件营销'},
	{value:234, name:'联盟广告'},
	{value:135, name:'视频广告'},
	{value:1548, name:'搜索引擎'}
]
 */
/**
 * 分页需要的变量
 */
var pageSize=10;//分页数量
var pageIndex=0;//分页游标
var pageNow=1;//当前页数
var pageTotal=0;//总页数
var showCount=0; //每页具体显示的数据数量
var dataCount=0;//数据的全部数量
var changeIndex=0;//放大缩小游标
var changeCount=0; //放大缩小页面数量

//存放页数显示DIV
var pagingSizeDiv;
//混合图分页数据
var mixDataPaging={}; 
//存储混合图所有数据
var mixObj=new Array();
//存储饼状图的全部数据
var pieObj=new Array();


var colors=['#ff9873', '#9fd7fb', '#e18cde', '#5bd7fb', '#83aaf0', 
            '#ff87c3', '#c777db', '#d77c7c','#ffb733',  '#66e6d9', 
            '#fcbe51', '#a9834f', '#48cfad', '#4fc1e9', '#ac92ec', 
            '#ec87c0', '#a0d468', '#ffce54', '#5d9cec', '#434a54'];


/**
 * 创建存放echartr容器
 * @param id
 * @param title
 * @param width
 * @param height
 */
function creatBodyHtml(id,title,subTitle,width,height,pageFlag){
	//用户想要放入的容器
	var outDiv=$("#"+id);
	outDiv.empty();
	//echart的主容器
	var mainDiv=$("<div></div>");
	mainDiv.attr('id',id+"_main");
	mainDiv.css({
		'border': "1px solid #red",
	    'width':width+"px",
	    'height':height+"px",
	    'color':"#FFFFFF",
	    'position':'relative'
	});
	mainDiv.appendTo(outDiv);
	//echart的标题
	var titleDiv=$("<div></div>");
	titleDiv.attr('id',id+"_title");
	titleDiv.removeClass();
	titleDiv.css({
		'margin':"0",
		'padding':"4px 0 0 10px",
		'border-bottom': "1px solid #1369C0",
		'background-color':"#1369C0",
		'font-size':"16px",
		'color':"#FFFFFF",
	    'width':width-10+"px",
	    'height':"28px",
	    'text-align':'left'
	});
	titleDiv.text(title);
	subtitleDiv=$("<span style='font-size:16px;color:#FFFFFF;'>"+subTitle+"</span>");
	subtitleDiv.appendTo(titleDiv);
	titleDiv.appendTo(mainDiv);
	//eachrt的
	echartId=id+"_chart";
	var echartDiv=$("<div></div>");
	echartDiv.attr('id',echartId);
	echartDiv.removeClass();
	echartDiv.css({
		'background-color':"#FFFFFF",
		'color':"#FFFFFF",
	    'width':width+"px",
	    'height':(height-28)+"px"
	});
	echartDiv.appendTo(mainDiv);
		//分页div
	if(pageFlag){
		/**
		 * 分页需要的图标按钮
		 */
		var pagingDiv=$("<div><span onclick=echartbigger('"+echartId+"') >" +
						"&nbsp;<img src='/gbdbas/static/js/eachart/img/big.png' title='Zoom In' width='16px' /></span>" +
						"<span onclick=echartsmaller('"+echartId+"') >" +
						"<img src='/gbdbas/static/js/eachart/img/small.png' title='Zoom Out' width='16px'/></span>" +
						"<span onclick=echartpreviousPage('"+echartId+"') >" +
						"<img src='/gbdbas/static/js/eachart/img/left.png' title='Page Up' width='16px'/></span>" +
						"<span onclick=echartnextPage('"+echartId+"') >" +
						"<img src='/gbdbas/static/js/eachart/img/right.png' title='Page Down' width='16px'/></span></div>");
		pagingDiv.attr('id',id+"_paging");
		pagingDiv.removeClass();
		pagingDiv.css({
			'width':'80px',
			'height':'20px',
			'left': (width-310)+"px",
			'top': '50px',
		    'position': 'absolute'
		});
		pagingDiv.appendTo(mainDiv);
		/**
		 * 页数的大小
		 */
		var pagingSize=$("<div><div>");
		pagingSizeDiv=id+"_pagingSize";
		pagingSize.attr('id',pagingSizeDiv);
		pagingSize.removeClass();
		pagingSize.css({
			'width':'80px',
			'height':'20px',
			'left': (width-150)+"px",
			'bottom': '5px',
		    'position': 'absolute',
		    'color':'#ED5564',
		    'font-weight':'normal',
		    'fill':'#ED5564',
		    'font-size':'12px'
		});
		pagingSize.text('Page: '+pageNow+'/'+pageTotal);
		pagingSize.appendTo(mainDiv);
	}
	return echartId;
	
}

/******************************************混合图模块***********************************/
/**
 * 展示混合图形（折线图、柱状图）（入口）
 * @param id     容器ID
 * @param data   图形数据(数据格式参考 混合图的数据格式)
 * @param title  图形标题
 * @param width  图形的宽度
 * @param height 图形的高度
 * @param rName  右坐标的名称 如:(温度(℃))最好带上单位
 * @param lName  左坐标的名称 如:(温度(℃))最好带上单位
 */
function showMixEchart(id,data,title,subTitle,width,height,rName,lName){
//	  if(tradFlag){
					 pageSize=10;//分页数量
					 pageIndex=0;//分页游标
					 pageNow=1;//当前页数
					 pageTotal=0;//总页数
					 showCount=0; //每页具体显示的数据数量
					 dataCount=0;//数据的全部数量
					 changeIndex=0;//放大缩小游标
					 changeCount=0; //放大缩小页面数量
			
					//将数据复制给全局变量 保存起来
					mixObj=data;
					mixDataPaging.rName=rName;
					mixDataPaging.lName=lName;
					mixDataPaging.width=width;
					mixDataPaging.height=height;
					//初始化分页变量
					initPagingVar();
					mixDataPaging.id=creatBodyHtml(id,title,subTitle,width,height,true);
					//获取分页数据
					forPaging(showCount,pageIndex);
					//设置游标
					pageIndex=pageIndex+showCount;
//	  }
				
					//显示Echart
					createMixEchart();
}
/**
 * 初始化分页变量  第一页显示多少数据
 * @param data
 */
function initPagingVar(){
		var xs = mixObj[0].data;
		if(xs.length<=0){
			return;
		}
		//数据的总数量
		dataCount=xs.length;
		//设置总页数
		if(dataCount % pageSize == 0){
			pageTotal = Math.floor(dataCount/pageSize);
		}else if(dataCount % pageSize != 0){
			pageTotal = Math.floor(dataCount/pageSize) + 1;
		}
		//如果当前页数是为1 则显示数量=最大数量
		if(pageTotal==1){
			showCount=dataCount;
		}
		//否则显示数量=分页数量
		else{
			showCount=pageSize;
    	}
}
/**
 * 作用：从全部混合数据中提取分页数据
 * @param showCount   要显示的数据数量
 * @param pageIndex   起始游标
 * @return
 */
function forPaging(showCount,pageIndex){
	//将混合数据集赋给变量
	var mixDatas=mixObj;
    var newDatas=new Array(); //用来保存分页数据集
    var xObj={};  //x轴
    var legendData=new Array(); //图例
    //如果混合数据集的大小为0则返回
	if(mixDatas.length==0)
		return;
	for(var j=0;j<mixDatas.length;j++)
	{
		//获取截取数据
		var newData=mixDatas[j].data.slice(pageIndex,pageIndex+showCount);
		//保存x轴数据
		if(mixDatas[j].type=="x")
		{
			xObj.data=newData;
			xObj.name=mixDatas[j].name;
		}	
		//保存混合图数据
		else{
			var newObj={};
			newObj.data=newData;
			newObj.name=mixDatas[j].name;
			newObj.type=mixDatas[j].type;
			newObj.yIndex=mixDatas[j].yIndex
			newDatas.push(newObj);
			var legObj={};
			legObj.name=mixDatas[j].name
			legObj.init=mixDatas[j].init
			legendData.push(legObj);
		}
		
	}
	mixDataPaging.x=xObj;
	mixDataPaging.legendData=legendData;
	mixDataPaging.datas=newDatas;
}
/**
 * 创建Echart图
 * @param id
 * @param title 
 * @param width
 * @param height
 * @param rName
 * @param lName
 */
function  createMixEchart(){
	//创建div用了装载echart
	var echartBody=getEchartBody(mixDataPaging.rName,mixDataPaging.lName);
	var myChart = echarts.init(document.getElementById(mixDataPaging.id)); 
	var ecConfig = echarts.config;
    var option={
    		    backgroundColor:"#FFFFFF",
			     tooltip : {
								trigger: 'axis',
								axisPointer : {            
									type : 'shadow'        
								}
							},
				 //设置工具箱
			     toolbox: {
								show : true,
								feature : {
											dataZoom : {show: true, title : 'Scale'},
										    magicType: {
								                show : true,
								                title : {
								                    line : 'Line',
								                    bar : 'Bar'
								                },
								                type : ['line', 'bar']
								            },
											restore : {show: true,title:'Default'},
											saveAsImage  : {
														    show: true,
													        title : 'Save as Picture',
													        type : 'jpg',
													        lang : ['Click to Save'] 
											}
								},
								orient : 'horizontal',
								x:mixDataPaging.width-230+"",
								y:"17"
			     		 },
				//是否启动值域漫游  暂时没发现用处
				calculable : true,
				//滚动轴
		        dataZoom : {		
								show : false,
								orient : 'horizontal',
								realtime : true,
								handleSize : 20,
								handleColor : '#0000FF',
								backgroundColor : '#ccccce',
								dataBackgroundColor : '#5d5d5d',
								start : 0,
								end : 100
							},
				 //纵坐标
			    yAxis : echartBody.yAxis,
			    //图例
				legend:echartBody.legend ,
				//横坐标
	            xAxis:echartBody.xAxis,
			    //图形实例
				series:echartBody.series
			}
		   //设置柱子的圆角
		  for ( var i = 0; i < option.series.length; i++) {
			
			  option.series[i].itemStyle.normal.barBorderRadius = [5,5,0,0];
			  option.series[i].itemStyle.emphasis.barBorderRadius = [5,5,0,0];
		  }
		   function eConsole(param)
		   {
		   }
		   myChart.on(ecConfig.EVENT.CLICK, eConsole);
		   myChart.on(ecConfig.EVENT.DBLCLICK, eConsole);
		   myChart.setOption(option); 
}
/**
 * 创建echartBody
 * @param rName
 * @param lName
 */
function getEchartBody(rName,lName){
	var legend=createLegend(mixDataPaging.legendData);//创建图例
	var yAxis=creatyAxis(rName,lName);//创建y轴坐标
	var series=createSeries(mixDataPaging.datas);//创建混合图形
	var xAxis=createxAxis(mixDataPaging.x.name,mixDataPaging.x.data) ;//创建x轴坐标
	var echartBody={};
	echartBody.legend=legend;
	echartBody.yAxis=yAxis;
	echartBody.series=series;
	echartBody.xAxis=xAxis;
	return echartBody;
}
/**
 * 创建图例
 * @param legendDatas
 * @return
 */
function createLegend(legendDatas)
{   
	//图例列表
	var datas=new Array();
	//图例显示列表
	var seleced={};
   for(var i in legendDatas){
	   datas.push(legendDatas[i].name);
	   if(legendDatas[i].init==0){
		   seleced[legendDatas[i].name]=true;
	   }else{
		   seleced[legendDatas[i].name]=false;
	 }
   }	
   var legend= {
					data:datas,
					orient : 'horizontal',
					x:'150',
					y:'20',
					selected:seleced,
					selectedMode:'single'
				};
       return legend;
}
/**
 * 创建图形系列
 */
function createSeries(datas)
{
	var series=new Array()
	//判断图形系列是否有值
	 if(datas!=undefined)
	{
       for (var i=0;i<datas.length ; i++)
       {
			var serie=new Array();
           if(datas[i].type=="bar")
			{
				//如果为柱状图
        	   serie=createBar(datas[i].name,datas[i].data,datas[i].yIndex,i);
			}
			else if(datas[i].type=="line")
			{ 
               //如果为折线图
	        	   serie=createLine(datas[i].name,datas[i].data,datas[i].yIndex,i);
			}
			series.push(serie);
       }
	}
	 return series;
}
/**
 * 展示柱状图
 */
function createBar(nameBar,dataBar,yIndex,index)
{
	var serieBar = {
					name:nameBar,
					barMaxWidth:40,
					type:'bar',
					yAxisIndex:yIndex,
					itemStyle: {
						normal: {
							color: function(params) {
	                         var colorList = colors;
	                         return colorList[params.dataIndex]
	                       },
						barBorderRadius : [],
						 label : {
	                        show: true, 
	                        textStyle:{
	                        	color:function(params) {
	                             var colorList = colors;
	                            return colorList[params.dataIndex]
	                          }
	                        },
		                     formatter: function(value){
		    						return new Number(value.data);
		    					}
	                    }
						} ,
						emphasis: {
							barBorderRadius : []
						}
					},
					data:dataBar
				};
				return serieBar;
}
/**
 * 创建折线图
 */
function createLine(nameLine,dataLine,yIndex,index)
{
	var serieLine = {
			name:nameLine,
			type:'line',
			yAxisIndex:yIndex,
			itemStyle: {
				normal: {
//					color:function(params) {
//                        var colorList = colors;
//                       return colorList[index]
//                     },
					barBorderRadius : [],
					label:{
						show:true,
						textStyle:{
//									color:function(params) {
//									                          var colorList = colors;
//									                         return colorList[index]
//									                       }
		                       
								},
					 formatter: function(value){
    						return new Number(value.data);
    					}
					}
				} ,
				emphasis: {
					barBorderRadius : []
				}
			},   
			yAxisIndex: yIndex,
			barMaxWidth:40,
			data:dataLine
		};
				return serieLine;
}
/**
 * 创建y轴坐标
 */
function creatyAxis(yNamel,yNamer){
	var yAxis=new Array();
	if (yNamel!=null)
	{
		yAxis[0]={
				type : 'value',
				name : yNamel,
				position:'left',
				splitLine:{
				 show:false
			  	},
				scale: true,
				axisLabel : {
					formatter: function(value){
						return detaily(value);
					}
				}
			}
		}
	
	if(yNamer!=null)
	{
		  yAxis[1]={
				type : 'value',
				name : yNamer,
				position:'right',
				splitLine:{
				 show:true
			  	},
				scale: true,
				axisLabel : {
					formatter: function(value){
						return detaily(value);
					}
				}
			}
	}
	return yAxis;
}
/**
 * 创建X轴坐标
 */
function createxAxis(xName,xData)
{
	     var xAxis=[
					{
						type : 'category',
//						name:xName,
						color: '#FFFFFF',
						show:true,
						splitLine:{
							 show:true
						  	},
						axisLabel:{
							formatter:function (value){
								var num=value.length;
								if(num<=8){
									return value
								}else{
								   return	value.substr(0,5)+"...";
								}
								
								},
							textStyle:{
								color:'#ED5564'
							}
					 },
				  data : xData
				}
			]
			return xAxis;
}

/**
 * 作用：下一页
 * @param id
 * @return
 */
function echartnextPage(id){
	//判断是否是最后一页
	if(pageNow==pageTotal)
	{
		  $.messager.alert("Prompt","This is the last page");
	  return ;
	}
	changeCount=0;
	//如果剩余数据不够每页标准的数量，则每页展示的数量为剩余数量
	showCount=((dataCount-pageIndex)>=pageSize)?pageSize:(dataCount-pageIndex);
	forPaging(showCount,pageIndex);
	pageIndex=pageIndex+showCount;
	pageNow+=1;
	$("#"+pagingSizeDiv).text('Page: '+pageNow+'/'+pageTotal);
	createMixEchart(id);
}
/**
 * 作用：上一页
 * @param id
 * @return
 */
function echartpreviousPage(id){
	//判断是否是第一页
	if(pageNow==1)
	{
	  $.messager.alert("Prompt","This is the first page");
	  return ;
	}
	pageNow-=1;
	//如果剩余数据不够每页标准的数量，则每页展示的数量为剩余数量
	pageIndex=((pageIndex-showCount)>=pageSize)?(pageIndex-pageSize-showCount):0;
	showCount=pageSize;
	changeCount=0;
	forPaging(showCount,pageIndex);
	pageIndex=pageIndex+showCount;
	$("#"+pagingSizeDiv).text('Page: '+pageNow+'/'+pageTotal);
	createMixEchart(id);
}
/**
 * 作用：放大（当页的初始数据不动，每次减少两条数据）
 * @param id
 * @return
 */
function echartbigger(id){
	//无论是放大 分页游标不变
	changeIndex=pageIndex-showCount
   //判断当前页面的显示数据是否是第一次加载
	if(changeCount==0)
	{
		//设置每次显示的数量 第一步判断初始化
		 if(showCount>2)
	     {
			 changeCount= (showCount>3)?showCount-2:2;
	     }
	     else{
	    	 $.messager.alert("Prompt","CAN NOT Zoom in more");
			 return;
	     }
	}else
	{
		if(changeCount>2)
	     {
			 changeCount= (changeCount>3)?changeCount-2:2;
	     }else{
			$.messager.alert("Prompt","CAN NOT Zoom in more");
			 return;
	     }
	}
    
	 forPaging(changeCount,changeIndex);
	 createMixEchart(id);
}
/**
 * 作用：缩小
 * @param id
 * @return
 */
function echartsmaller(id)
{
	//无论是放大 分页游标不变
	changeIndex=pageIndex-showCount
	//判断是否是初始化
	if(changeCount==0)
	{
		if((changeIndex+showCount)<dataCount)
		{
			changeCount=((changeIndex+showCount+2)<=dataCount)?(showCount+2):(showCount+1)
		}else
		{
			$.messager.alert("Prompt","CAN NOT Zoom out more");
			return;
		}
	}else
	{
		if(((changeIndex+changeCount)<dataCount)&&(changeCount<dataCount))
		{
			changeCount=((changeIndex+changeCount+2)<=dataCount)?(changeCount+2):(changeCount+1)
		}else
		{
			$.messager.alert("Prompt","CAN NOT Zoom out more");
			return;
		}
	}
	 forPaging(changeCount,changeIndex);
	 createMixEchart(id);

}
/**
 *处理y轴显示比例
 *
 */
function detaily(value){
	if(value<1000){
		return value;
	}else if(value<1000000){
		return value/1000+'k';
	}else if(value<1000000000){
		return value/1000000+'M';
	}
}  

/******************************************折线图模块***********************************/


/******************************************饼状图模块***********************************/
/**
 * id:饼状图的容器ID
 * data：饼状图的数据
 * title:标题
 * subTitle:副标题
 * width:宽度
 * height:高度
 * unitName:单位
 */
function showPieChart(id,data,title,subTitle,width,height,unitName){
	var pieId=creatBodyHtml(id,title,subTitle,width,height,false);
	var myChart = echarts.init(document.getElementById(pieId)); 
	var ecConfig = echarts.config;
	var pieLengen=getPieLegend(data);
	option = {
			backgroundColor:'#FFFFFF',
		    tooltip : {
		        trigger: 'item',
		        formatter: function(params,ticket,callback){
		        	 return params[1]+"<br/>"+unitName+":"+new Number(params[2])+" <br/>占比:("+params[3]+"%)";
		        }
		    },
		    toolbox: {
		        show : true,
		        feature : {
		        	restore : {
		        		show: true,
		        		title : 'Default'},
		        		
		            saveAsImage : {
		            	show: true,
				        title : 'Save as Picture',
				        type : 'jpg',
				        lang : ['Click to Save']
		            	
		            
		            
		            }
		        },
		        orient : 'horizontal',
				x:width-100+"",
				y:"35"
		    },
		    calculable : false,//是否重计算
		    series : [
				        {
				        	selectedOffset:'10',//扇区移动偏移量
				            type:'pie',
				            selectedMode:'single', //扇区是否移动
				            radius :'65%', //扇区半径
				            center: ['50%', '50%'],//饼图的原点位置
				            itemStyle : {
				                normal : {
				                    labelLine : {
				                        show : true//是否显示指示线
				                    },
				                    labelLine : {
				                    	 length : '10'//指示线的长度
				                    }
				                    
				                }
				            },
				            data:data
				        }
		    ]
		};
	myChart.setOption(option);                    
}
/**
 * 获取饼状图的图例
 * @param data
 */
function getPieLegend(data){
	var pieArr=new Array();
	//循环获取饼状图图例
	for(var i=0;i<data.length;i++){
		pieArr.push(data[i].name);
	}
	return pieArr;
}
/******************************************饼状图模块***********************************/
