/**
 * author:陈虎
 * 动态生产国家table列表当点击国家，查看该用户权限
 */
//系统拥有那些国家列表
var countris=[];
//用户选中那些国家权限表格
var selectT;
//当前用户选中的具体国家权限的表格
var tId="";
//整个权限对象
var right={};
//
var  editIndex=[];
/**
 * 初始化
 */
$(document).ready(function(){
	//设置当前选中的国家的tan内的tableId
	$('#codesDiv').tabs({ 
		onSelect:function(title){ 
		  var p = $(this).tabs('getTab', title); 
		  tId= getTableId(p);
		} 
		});
	
});
  
/**
 * 1.加载国家试图：
 * 查看该系统拥有的可查询国家信息
 */
function loadCountyView()
{
	//获取所有国家返回的是一个国家数组
	$.ajax({
		type:"post",
		url:'/gbdbas/userSonRight/queryAllCountries',
		dataType:"json",
	    async:false,
	    success:function(data){
	    	 createTable("countrysDiv",data);
	    }
	});
}
/**
 * 1.1动态生产国家table列表
 */
function createTable(id,data)
{
	//在国家div中动态加载需要展示的国家列表
	countris=data.country;
	var changeTable="<table cellPadding='6px;'>";
	if(countris.length>0)
		{ 
		  var j=1;
		  var i=0;
		  while(i<countris.length)
			  {
				  if(j%11==0)
		    	  {
					  changeTable+="</tr>";
			      }
				  else
				  {
					  if(i%10==0)
				      {
				        changeTable+="<tr>";
				      }
				      changeTable+="<td><span style='display:none;' id='sY_"+countris[i].code+"' onclick='loadCountyDivs(\"CD_"
				    	    +countris[i].code+"\",\""+countris[i].value+"\",\""+countris[i].value+"\")" +
				    	    		"'>" +"<a href=\"#\" title=\""+countris[i].value+"\"><img style='cursor:hand' src='/gbdbas/static/img/usermanagement/ableCounties/"+countris[i].code+".png'/></a>" +
				    	    				"</span><span id='sN_"+countris[i].code+"' >" +
				    	    						"<img src='/gbdbas/static/img/usermanagement/disableCountries/" 
				    	    				          +countris[i].code+".png'  title=\""+countris[i].value+"\"/></span></td>"			   
				      i++;
				  }
				  if((i==countris.length-1)&&j%5!=0)
				  {
					    changeTable+="</tr>"; 
					    break;
				  }
			      j++;
			  }
		}
	 changeTable+="</table>";
	 $("#"+id).html(changeTable);
}
/**
 * 1.2 动态加载用户拥有那些国家权限
 */
function loadUserRight()
{
	//获取所有国家返回的是一个国家数组
	$.ajax({
		type:"post",
		url:'/gbdbas/userSonRight/queryCountriesByUid',
		data:{"userId":userId},
		dataType:"json",
	    async:false,
	    success:function(data){
	    	//在国家div中动态加载需要展示的国家列表
	    	countris=data.country;
	    	if(countris.length>0)
			{ 
			  for(var i=0;i<countris.length;i++)
		      {
				  $("#sY_"+countris[i]).css("display","block");
				  $("#sN_"+countris[i]).css("display","none");
		      }
			}
	    }
	
	});
}
/**
 * 2.加载国家的DIV
 * 当用户点击国家Table中每个可选国家时弹出每个国家的Div
 */
function loadCountyDivs(countyId,countyName,codeId)
{
	//加载国家的Div
	var countryDiv="<div id='"+countyId+"'></div>";
	addTab(countyName,countryDiv,countyId,codeId);
}
/**
 * 2.1动态加载tabs
 * @param title
 * @param html
 */
function addTab(title,html,countyId,codeId){
	if ($('#codesDiv').tabs('exists', title)){
		 $('#codesDiv').tabs('select', title);
	} else {
		$('#codesDiv').tabs('add',{
			title:title,
			content:html,
			closable:true
		});
		//加载国家权限列表
		loadCountryDiv(countyId,codeId);
	}
}
/**
 * 3 为每个国家DIV填充内容
 */
function loadCountryDiv(countyId,codeId)
{    
	loadCountryTable(countyId,codeId);
}
/**
 * 3.1填充单个国家的权限列表
 */
function loadCountryTable(id,codeId){
	//判断一个用户是否具有国家权限
	//查询一个用户的国家权限
	var rightTable="<table id='"+tId+"' style='width:auto;height:400px;'></table>"
	  //动态加载权限列表
	  $("#"+id).html(rightTable);
	 loadTableData(rightsArr,codeId);
}
/**
 * 3.2加载权限表的数据
 * @param id
 * @param colums
 */
function loadTableData(colums,codeId)
{
	//根据用户ID和国家代码查询用户权限列表
	$('#'+tId).datagrid({
        url:'/gbdbas/userSonRight/queryRightsByUidCN',
 		queryParams:{"userId":userId,"countryName":codeId},
 		pageNumber:1,
        pageSize:25,
        fitcolumns:false,
        autoRowHeight:false,
        sortorder: 'asc',
        loadMsg:'Loading...',
        pageList:[10,25,50,80,100],
 		columns : [colums],
 		iconCls:'icon-edit',
 		onDblClickRow:function(rowIndex, rowData){
 		      $('#'+tId).datagrid('beginEdit', rowIndex);
 		 }
    });
}
/**
 * 权限表的列
 */
var rightsArr=[
             {field:'ck',checkbox:true},
             {field:"byHsCode", hidden:false, align:"center",width:'98',title:"Custom Number"},
             {field:"byProductDesc", hidden:false, align:"center",width:'99',title:"Product Description"},
             {field:"byCountry", hidden:false, align:"center",width:'98',title:"Country"},
             {field:"startTime", hidden:false, align:"center", width:'99', title:"Starting Date",editor:'datebox'},
             {field:"endTime", hidden:false, align:"center",width:'99',title:"Finishing Date",editor:'datebox'},
             {field:"iExportType", hidden:false, align:"center",width:'98',title:"Import/Export Category"},
             {field:"rightType", hidden:false, align:"center",width:'99',title:"Whether opens to history data",
            	 formatter:function(value,row,index){
            		    if(value=="国家权限")
            		   {
            		    	var startTime=row.startTime;
            		    	var endTime=row.endTime;
            		    	var byCountry=row.byCountry;
            		    	var iExportType=row.iExportType;
            		    	var rightType="单一权限";
                            var s = '<a href="#" style="color:deepskyblue;" onclick="addrow(\''+startTime+'\',\''+endTime+'\',\''+byCountry+'\',\''+rightType+'\',\''
                            +iExportType+'\')">国家权限</a> ';
                            return s;
            		   } else{
            		    	  return '单一权限';
            		    	}
                 }
             }
          ]

/**
 *新加一条权限表
 * @param startTime
 * @param endTime
 * @param byCountry
 * @param rightType
 * @param iExportType
 */
function addrow(startTime,endTime,byCountry,rightType,iExportType){
    addCodeOrDesc();
    right={startTime:startTime,
         	endTime:endTime,
         	byCountry:byCountry,
         	rightType:rightType,
         	iExportType:iExportType}
}
/**
 * 打开输入框
 */
function addCodeOrDesc(){
	$("#hs").val("");
	$("#desc").val("");
	openDivArtDialog('Add HSCODE or Product Discription', 'inputConditionDiv', 'inputConditionDiv', 300, 260,true);
}
/**
 * 判读用户输入的内容是否为空
 * @returns {Boolean}
 */
function cofirmIn(id){
	if($(id).val()=="")
		{
		  return true;
		}
	return false;
}
/**
 *添加HS
 */
function addDesc(){
	if(cofirmIn("#desc")){
	   	  $.messager.alert('Prompt','HS and Product Discription can not be both empty');
	   	return;
	   }
	//讲产品描述和HScode添加到新的一行上
	right.byHsCode="";
	right.byProductDesc=$("#desc").val();
	$("#desc").val("");
	//添加新的一行
    $('#'+tId).datagrid('appendRow',right);
}
/**
 *添加DESC
 */
function addHS(){
	if(cofirmIn("#hs")){
	   	  $.messager.alert('Prompt','HS and Product Discription can not be both empt');
	   	return;
	   }
	//讲产品描述和HScode添加到新的一行上
	right.byProductDesc="";
	right.byHsCode=$("#hs" ).val();
	$("#hs").val("");
	//添加新的一行
    $('#'+tId).datagrid('appendRow',right);
}


/**
 * 获取所有出现的国家权限列表
 */
function  getRigthTables(){
	selectT=new Array();
    var id = "#codesDiv";//Tab所在的层的ID  
    var tabs = $(id).tabs("tabs");//获得所有小Tab  
    var tCount = tabs.length;  
	if(tCount>0){  
	        //收集所有Tables的ID 
		for(var i=0;i<tCount;i++){  
			var tid=getTableId(tabs[i]);
			selectT.push(tid); 
		}  
    }
}

/**
 * 用户点击授权的时候
 */
function grantRight()
{
    
	var selectRows=new Array();
    //获取所有选中的表格的ID
	getRigthTables();
	if(selectT.length>0)
	{
		for(var i=0;i<selectT.length;i++){
		  //获取所有选中的行添加到一行里
		   var rows=$('#'+selectT[i]).datagrid('getChecked');
		   if(rows.length>0){
			   for(var j=0;j<rows.length;j++){
				   selectRows.push(rows[j]);
			   }
		   }
		}
	}
	if(selectRows.length==0){
		  $.messager.alert('Prompt','Please at least choose one line');
			return;
	}
	var selectRigth={"rights":selectRows,'uid':userSonId};
	  $.ajax({
		  type:'post',
		   url:'/gbdbas/userSonRight/addRights',
		   dataType:'json',
		   data:{data:JSON.stringify(selectRigth)},
		   async:false,
		   success:function(data){
			   var flag=data.flag;
			   var result="";
			   if(flag){
				   result="授权成功";
			   }
			   else{
				   result="授权失败"
			   }
				   loadUserInfo();
					  $.messager.alert('Prompt','授权成功');
				   art.dialog({id:'rightDiv'}).close();
		   }
	  });
}
/**
 * 获取tab里的tableID
 * @param tab
 * @returns {String}
 */
function getTableId(tab){
	var tid="T"+tab[0].children[0].id;
	return tid;
}


