package com.njyb.gbdbase.service.datasearch;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 报表同环比分析业务接口
 * @author 贾红平
 *
 */
public interface IReportTrendService {
	/**
     * 查询所有国家同环比结果集
     * @param request
     * @param singleReport 单个报告类型如mom,myoy,yoy
     * @param reportType 当前报告类型
     * @param value 当前列的值
     * @return
     * @throws Exception
     */
    public <T> List<T>  getThbReportList (HttpServletRequest request,String singleReport,String reportType,String value) throws Exception;

}
