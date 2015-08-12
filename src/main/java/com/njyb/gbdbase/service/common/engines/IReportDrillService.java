package com.njyb.gbdbase.service.common.engines;

import java.util.List;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
/**
 * 报表深度挖取业务接口
 * @author jiahp
 *
 */
public interface IReportDrillService {
	 /**
	  * 
	  * @param value 当前对象的值
	  * @param orginalType 第一次汇总的报告类型
	  * @param country  当前查询的国家
	  * @param drillType 要钻取的报告类型
	  * @return
	  */
	public abstract  List<DataReportSumModel>drillReportSumList(String value,String orginalType,String country,String drillType);

}
