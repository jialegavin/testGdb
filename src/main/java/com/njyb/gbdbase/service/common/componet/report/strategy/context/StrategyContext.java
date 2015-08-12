package com.njyb.gbdbase.service.common.componet.report.strategy.context;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.OrderModel;
import com.njyb.gbdbase.service.common.componet.report.strategy.AbstractSummaryStrategy;
import com.njyb.gbdbase.service.common.componet.report.strategy.impl.MultiListSummaryStragey;
import com.njyb.gbdbase.service.common.componet.report.strategy.impl.SingleListSummaryStragey;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;

/**
 * 封装策略上下文对象
 * @author 贾红平
 *
 */
public class StrategyContext {
	/**
	 * 策略汇总结果
	 * @param ls
	 * @param country
	 * @param map
	 * @return
	 */
	public static DataReportSumModel getOrderModel(List<DataReportSumModel>ls,String country,Map map,String type){
		OrderModel order=getSummaryStrategy(ls.size()).getDataReportSumModel(ls,country,map,type);
		return getReportSumModels(order,country, map);
	}
	/**
	 * 获取一个具体的汇总对象
	 * @param order
	 * @param country
	 * @param map
	 * @return
	 */
	private static DataReportSumModel getReportSumModels(OrderModel order,String country,Map map){
		DataReportSumModel data=new DataReportSumModel();
		//赋值顺序 金额 重量 数量 次数
		if (map.get("field_mwq").toString().contains(country)) {
			 data.setTradeMoney(order.getMoney());
			 data.setTradeWeight(order.getWeight());
			 data.setTradeQuantity(order.getQuantity());
		}
		//赋值顺序 金额 数量 次数
		if (map.get("field_mq").toString().contains(country)) {
			 data.setTradeMoney(order.getMoney());
			 data.setTradeQuantity(order.getQuantity());
		}
		//赋值顺序 金额 重量 次数
		if (map.get("field_mw").toString().contains(country)) {
			 data.setTradeMoney(order.getMoney());
			 data.setTradeWeight(order.getWeight());
		}
		//赋值顺序 数量 次数
		if (map.get("field_quantity").toString().contains(country)) {
			 data.setTradeQuantity(order.getQuantity());
		}
		//美国: 重量 件数 尺寸 次数
		if (map.get("field_wps").toString().contains(country)) {
			 data.setTradeWeight(order.getWeight());
			 data.setTradePackage(order.getPackages());
			 data.setTradeSize(order.getSize());
		}
		//巴基斯坦: 重量 件数 次数
		if (map.get("field_wp").toString().contains(country)) {
			 data.setTradeWeight(order.getWeight());
			 data.setTradePackage(order.getPackages());
		}
		//韩国 英国 次数
		if(country.equals(LuceneConstant.KOREA_STRING)|| country.equals(LuceneConstant.UK_IMPORT_STRING)){
			 
		}
		data.setTradeCount(order.getCount());
		
		return data;
	}
	/**
	 * 获取算法实际策略
	 * @param num
	 * @return
	 */
	private static AbstractSummaryStrategy getSummaryStrategy(int num){
		AbstractSummaryStrategy strategy=null;
		if (num>1) {
			strategy=new MultiListSummaryStragey();
		}
		else{
			strategy=new SingleListSummaryStragey();
		}
		return strategy;
	}
}
