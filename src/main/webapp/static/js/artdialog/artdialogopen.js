//所有国家打开模式化对话框
//XL artDialog插件定义
//打开独立页面
function ShowArtDlg(title, url, width, height, lock) 
{
	if (width == null || width == "") {
		width = '90%';
	}
	if (!width.indexOf('px') && !width.indexOf('%')) {
		width = width + 'px';
	}
	if (width.indexOf('px') < 0 && width.indexOf('%') < 0) {
		width = width + 'px';
	}
	
	if (height == null || height == "") {
		height = '90%'
	}
	if (height.indexOf('px') < 0 && height.indexOf('%') < 0) {
		height = height + 'px';
	}
	
	if (lock == null || lock == "") {
		lock = false;
	}
	art.dialog.open(url, { height: height, width: width, title: title, lock: lock, opacity: 0.3}, false); 
	return false;
}


/**
 * 打开div
 * @param title
 * div标题
 * @param divId
 * 页面div的id
 * @param dialogId
 * artdialog自定义的id(随便给，但是不能重名)
 * @return
 */

function openDivArtDialog(title,divId,dialogId,width, height,lock){
	var dlgTitle = title;
	var divid = divId;
	var dialogid = dialogId;
	if (lock == null || lock == "") {
		lock = false;
	}
	art.dialog({  
		title:dlgTitle,
	    content: document.getElementById(divid),  
	    id:dialogid,
	    width:width,
	    height:height,
	    lock:lock,  //背景是否灰化
	    opacity: 0.3,//透明度
	    fixed:true //开启静止定位。它静止在浏览器某个地方不动，也不受滚动条拖动影响
	});
}



/**
 * 打开深度挖取的div
 * @param title
 * div标题
 * @param divId
 * 页面div的id
 * @param dialogId
 * artdialog自定义的id(随便给，但是不能重名)
 * @return
 */

function openEasyUiDialog(title,divId,dialogId,width, height,lock){
	var dlgTitle = title;
	var divid = divId;
	var dialogid = dialogId;
	if (lock == null || lock == "") {
		lock = false;
	}
	deepDialog = art.dialog({  
		title:dlgTitle,
	    content: document.getElementById(divid),  
	    id:dialogid,
	    width:width,
	    height:height,
	    lock:lock,  //背景是否灰化
	    opacity: 0.3,//透明度
	    close:function(){
		reAssignmentVar(divId);
		}
	});
}

/**
 * 打开对比的div
 * @param title
 * div标题
 * @param divId
 * 页面div的id
 * @param dialogId
 * artdialog自定义的id(随便给，但是不能重名)
 * @return
 */

function openCompareDataDialog(title,divId,dialogId,width, height,lock){
	var dlgTitle = title;
	var divid = divId;
	var dialogid = dialogId;
	if (lock == null || lock == "") {
		lock = false;
	}
	art.dialog({  
		title:dlgTitle,
	    content: document.getElementById(divid),  
	    id:dialogid,
	    width:width,
	    height:height,
	    lock:lock,  //背景是否灰化
	    opacity: 0.3,//透明度
	    close:function(){
	    	
		}
	});
}

