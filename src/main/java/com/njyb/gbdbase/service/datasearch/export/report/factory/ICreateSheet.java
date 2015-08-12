package com.njyb.gbdbase.service.datasearch.export.report.factory;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.write.WritableWorkbook;

/**
 * 创建工作表接口层
 * @author XL
 * @date 2015-04-02
 * @version 标准版
 *
 */
public interface ICreateSheet {
	
	/**
	 * 创建非钻取工作表
	 * @param wb 工作簿
	 * @param map 存放数据信息的集合
	 * @param readOnly 判断工作表是否是只读状态，是：true,否:false
	 * @param sheetIndex 工作表索引
	 * @param sumSheetNum 报告类型个数
	 * @param request
	 */
	 <T> void createNoDrillSheet(WritableWorkbook wb,Map map, boolean readOnly,int sheetIndex,int sumSheetNum,HttpServletRequest request)throws Exception ;
	 
	 
	 /**
	  * 创建钻取工作表
	  * @param wb 工作簿
	  * @param map 存放数据信息的集合
	  * @param readOnly 判断工作表是否是只读状态，是：true,否:false
	  * @param sheetIndex 工作表索引
	  * @param sumSheetNum 报告类型个数
	  * @param request
	  */
	 <T> void createDrillSheet(WritableWorkbook wb,Map map, boolean readOnly,int sheetIndex,int sumSheetNum,HttpServletRequest request)throws Exception ;
}
