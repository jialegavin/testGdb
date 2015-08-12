package com.njyb.gbdbase.service.common.engines;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;

/**
 * 显示多海关编码备注的详细信息
 * @author 贾红平
 * @ 标准版
 *
 */
public interface IMultiHscodeMarkService {
	/**
	 * 获取多海关编码备注信息
	 * @param value 当前对象的值
	 * @param country 当前查询国家
	 * @param reportType 当前对象对应的报告类型
	 * @return map
	 */
	public List<DataReportSumModel>getMultiHscodeMarkInfo(String value,String country,String reportType);
}
