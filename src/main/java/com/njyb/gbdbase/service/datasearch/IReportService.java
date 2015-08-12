package com.njyb.gbdbase.service.datasearch;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;

/**
 * 报表相关业务接口
 * @author 贾红平
 *
 */
public interface IReportService {
	 /**
	    * 获取某一报告选项卡下某一个数据表格的报表汇总集合
	    * @param request
	    * @param tableId
	    * @param type
	    * @param queryKey 查询条件key
	    * @param queryValue 查询条件value
	    * @param loadType 加载类型 1代表懒加载只加载前10条数据，2代表全部加载
	    * @return List<DataReportSumModel>
	    * @throws Exception
	    */
		public List<DataReportSumModel> getReportList(HttpServletRequest request,String tableId,String type,String queryKey,String queryValue,int loadType)throws Exception;
	    
		
		/**
		 * 获取报告钻取选项卡下某一个数据表格的报表汇总集合
		 * @param request
		 * @param value
		 * @param orginalType
		 * @param drillType
		 * @return
		 * @throws Exception
		 */
		public List<DataReportSumModel> getDeepReportList(HttpServletRequest request,String value,String orginalType,String drillType)throws Exception;
		
		
		/**
		 * 获取数据对比某一选项卡下某一个数据表格的报表汇总集合
		 * @param request
		 * @param hideColumn 通过"洪"分隔 例如 cp_trade洪qs洪aaaaaaa洪date
		 * @param selectedValue 选中待对比的数据通过"洪"分隔 例如 aaaaaaa洪bbbbbb洪cccccccc....
		 * @return
		 * @throws Exception
		 */
		public List<DataReportSumModel> getDataCompareReportList(HttpServletRequest request,String hideColumn,String selectedValue)throws Exception;
		
}
