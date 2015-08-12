package com.njyb.gbdbase.service.common.componet.report.strategy;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.OrderModel;

/**
 * 汇总策略接口
 * @author 贾红平
 *
 */
public abstract class AbstractSummaryStrategy {
	/**
	 * 重量
	 */
	protected double weight;
	/**
	 * 金额
	 */
	protected double money;
	/**
	 * 数量
	 */
	protected double quantity;
	/**
	 * 尺寸大小
	 */
	protected double size;
	/**
	 * 件数
	 */
	protected double packages;
	/**
	 * 交易记录数
	 */
	protected int count;
	/**
	 * 日期格式
	 */
	protected String date=null;
	
	/**
	 * 汇总之后的一个数据对象
	 * @param ls 数据汇总集合
	 * @param country 查询国家简称
	 * @param map 配置文件中的信息
	 * @return
	 */
	public abstract OrderModel getDataReportSumModel(List<DataReportSumModel>ls,String country,Map map,String type);
}
