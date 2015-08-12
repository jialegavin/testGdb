package com.njyb.gbdbase.service.datasearch;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 生成各种图表的业务接口
 * @author 贾红平
 *
 */
public interface IBuildChartService {
	 /**
     * 生成混搭图(折线图和柱状图),饼图需要的数据
     * @param request
     * @param listAll
     * @param columField
     * @param map
     * @param reportType
     * @param functionType 1代表报表汇总 2代表深度挖取 3代表数据对比模块 4 月度同比，月度环比模块 5年度同比模块
     * @return
     * @throws Exception
     */
      public <T>  Map  getPieAndMixChartData (HttpServletRequest request,List <T> listAll,String columField,Map map,String reportType,int functionType,String DynamicSortValue) throws Exception;

    
}
