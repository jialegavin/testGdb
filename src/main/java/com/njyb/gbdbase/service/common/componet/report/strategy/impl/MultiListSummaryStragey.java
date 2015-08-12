package com.njyb.gbdbase.service.common.componet.report.strategy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.OrderModel;
import com.njyb.gbdbase.service.common.componet.report.strategy.AbstractSummaryStrategy;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
/**
 * 多个集合赋值
 * @author nb
 *
 */
public class MultiListSummaryStragey extends  AbstractSummaryStrategy {
	@Override
	public OrderModel getDataReportSumModel(List<DataReportSumModel>ls,String country,@SuppressWarnings("rawtypes") Map map,String type){
		@SuppressWarnings("unused")
		Map<String, String>nmp=new HashMap<String, String>();
		if (ls.size()>1) {
			weight=0.0; money=0.0; quantity=0.0; size=0.0; packages=0.0; count=0;
			for(DataReportSumModel data:ls){
				//赋值顺序 金额 重量 数量 次数
				if (map.get("field_mwq").toString().contains(country)) {
					 money=money+data.getTradeMoney();
					 weight=weight+data.getTradeWeight();
					 quantity=quantity+data.getTradeQuantity();
				}
				//赋值顺序 金额 数量 次数
				if (map.get("field_mq").toString().contains(country)) {
					 money=money+data.getTradeMoney();
					 quantity=quantity+data.getTradeQuantity();
				}
				//赋值顺序 金额 重量 次数
				if (map.get("field_mw").toString().contains(country)) {
					 money=money+data.getTradeMoney();
					 weight=weight+data.getTradeWeight();
				}
				//赋值顺序 数量 次数
				if (map.get("field_quantity").toString().contains(country)) {
					 quantity=quantity+data.getTradeQuantity();
				}
				//美国: 重量 件数 尺寸 次数
				if (map.get("field_wps").toString().contains(country)) {
					weight=weight+data.getTradeWeight();
					packages=packages+data.getTradePackage();
					size=size+data.getTradeSize();
				}
				//巴基斯坦: 重量 件数 次数
				if (map.get("field_wp").toString().contains(country)) {
					weight=weight+data.getTradeWeight();
					packages=packages+data.getTradePackage();
				}
				//韩国 英国 次数
				if(country.equals(LuceneConstant.KOREA_STRING)|| country.equals(LuceneConstant.UK_IMPORT_STRING)){
				}
				//所有国家 最后都需要累加次数
				count=count+data.getTradeCount();
			}
			//组装一个排序对象
			return new OrderModel(money, weight, quantity, packages, count, size);
		}
		return null;
	}

}
