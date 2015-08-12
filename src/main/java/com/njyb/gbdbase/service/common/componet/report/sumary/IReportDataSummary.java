package com.njyb.gbdbase.service.common.componet.report.sumary;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
/**
 * 文档模型对象转换成实际的javabena对象
 * @author 贾红平
 *
 */
public interface IReportDataSummary {
	/**
	 * 获取一个javabean对象集合
	 * @param fields 查询参数
	 * @param values 查询值
	 * @param country 查询国家
	 * @param map 存储配置文件中的信息
	 * @return
	 */
	public  List<DataReportSumModel> getSumListModel(String[]fields,String[]values,String country,@SuppressWarnings("rawtypes") Map map);
}
