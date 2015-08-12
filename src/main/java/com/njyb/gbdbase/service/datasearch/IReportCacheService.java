package com.njyb.gbdbase.service.datasearch;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;

/**
 * 报表缓存业务接口
 * @author 贾红平
 *
 */
public interface IReportCacheService {
	/**
	 * 获取缓存中存放的报表对比数据
	 * @param request
	 * @param data
	 * @return
	 * @throws Exception
	 */
    public List<DataReportSumModel> getCacheValue(HttpServletRequest request,String data) throws Exception;
    /**
  	 * 获取缓存中的数据汇总集合(供导出报表汇总使用)
  	 * @param request
  	 * @param reportFieldMap
  	 * @param country 国家
  	 * @param reportType 报表类型，如：(qs)
  	 * @return List<DataReportSumModel>
  	 * @author XL
  	 */
  	public List<DataReportSumModel> getDataSummaryCacheValue(HttpServletRequest request, Map reportFieldMap, String country, String reportType);

}
