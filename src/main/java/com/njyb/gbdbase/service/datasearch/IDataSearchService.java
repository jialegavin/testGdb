package com.njyb.gbdbase.service.datasearch;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.componet.report.sumary.ReportDataSummary;

/**
 * 公用搜索业务接口层
 * @author honghao/贾红平
 * @date 2015-04-10
 * @version 标准版/修订版
 */
@SuppressWarnings("all")
public interface IDataSearchService extends IReportService,IReportTrendService,IBuildChartService,IReportCacheService {
	/**
	 * 
	 * 根据lucene查询出的国家主键id查询具体国家的详细数据，展示给用户
	 * @param country
	 * @param idList
	 * @auther honghao
	 * @return
	 */
	public <T> List<T> getDataById(String country,List<Integer> idList);
	/**
	 * 拼接每个国家的搜索模块sql
	 * @param idList
	 * @auther honghao
	 * @return
	 */
	public String getMyBatiesSql(List<Integer> idList);
	
	/**
	 * 获取查看详情去重后的id
	 * @param request
	 * @return Set<String>
	 */
	Set<String> querySearchDetailFields(HttpServletRequest request);
	
      /**
       * 除了交易趋势报告设置钻取的列超链接显示（深度挖取）
       * @param request
       * @param pageList
       * @param columField
       * @param map
       * @auther honghao
       * @return
       * @throws Exception
       */
      public  List<DataReportSumModel>  getHyperlinkList(HttpServletRequest request,List <DataReportSumModel> pageList,String columField,Map map,String reportType)throws Exception;
        
      /**
       * 设置隐藏的列值供报表分析功能大全使用()
       * 值包含选项卡模块（cp_trade）,
       * 报告类型(qs),当前汇总的字段(如importer列的值)) 通过"洪"分隔
       * 如:cp_trade洪qs洪aaaaaaa
       * @param request
       * @param pageList
       * @param columField
       * @param tableId
       * @param type
       * @return
       * @throws Exception
       */
        public  List<DataReportSumModel>  setHideColumnValue(HttpServletRequest request,List <DataReportSumModel> pageList,String columField,String tableId,String type)throws Exception;

            /**
         * 处理没有海关编码只有提单号的国家
         * @param <T>
         * @param model    公共汇总model
         * @param country  国家
         * @return
         */
        public <T> String solveCountryHscode(T model,String country);
}
