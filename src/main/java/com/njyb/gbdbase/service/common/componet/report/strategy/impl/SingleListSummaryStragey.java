package com.njyb.gbdbase.service.common.componet.report.strategy.impl;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.OrderModel;
import com.njyb.gbdbase.service.common.componet.report.strategy.AbstractSummaryStrategy;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
import com.njyb.gbdbase.service.common.engine.util.ParamEnumUtil;
/**
 * 单个集合赋值
 * @author jiahp
 *
 */
public class SingleListSummaryStragey extends AbstractSummaryStrategy {
	@Override
	public OrderModel getDataReportSumModel(List<DataReportSumModel>ls,String country,Map map,String type){
		if (ls.size()>0) {
			weight=0.0; money=0.0; quantity=0.0; size=0.0; packages=0.0; count=0;
			//赋值顺序 金额 重量 数量 次数
			if (map.get("field_mwq").toString().contains(country)) {
				 money=ls.get(0).getTradeMoney();
				 weight=ls.get(0).getTradeWeight();
				 quantity=ls.get(0).getTradeQuantity();
			}
			//赋值顺序 金额 数量 次数
			if (map.get("field_mq").toString().contains(country)) {
				 money=ls.get(0).getTradeMoney();
				 quantity=ls.get(0).getTradeQuantity();
			}
			//赋值顺序 金额 重量 次数
			if (map.get("field_mw").toString().contains(country)) {
				 money=ls.get(0).getTradeMoney();
				 weight=ls.get(0).getTradeWeight();
			}
			//赋值顺序 数量 次数
			if (map.get("field_quantity").toString().contains(country)) {
				 quantity=ls.get(0).getTradeQuantity();
			}
			//美国: 重量 件数 尺寸 次数
			if (map.get("field_wps").toString().contains(country)) {
				weight=ls.get(0).getTradeWeight();
				packages=ls.get(0).getTradePackage();
				size=ls.get(0).getTradeSize();
			}
			//巴基斯坦: 重量 件数 次数
			if (map.get("field_wp").toString().contains(country)) {
				weight=ls.get(0).getTradeWeight();
				packages=ls.get(0).getTradePackage();
			}
			//韩国 英国 次数
			if(country.equals(LuceneConstant.KOREA_STRING)|| country.equals(LuceneConstant.UK_IMPORT_STRING)){
				
			}
			if (type.equals(ParamEnumUtil.jkscompare_first.name()) || type.equals(ParamEnumUtil.jkscompare_second.name())
					||type.equals(ParamEnumUtil.companycompare_first.name()) || type.equals(ParamEnumUtil.companycompare_second.name())) {
				date=ls.get(0).getDate();
			}
			//所有国家 最后都需要累加次数
			count=ls.get(0).getTradeCount();
			 //组装一个排序对象
			return new OrderModel(money, weight, quantity, packages, count, size);
		}
		return null;
	}

}
