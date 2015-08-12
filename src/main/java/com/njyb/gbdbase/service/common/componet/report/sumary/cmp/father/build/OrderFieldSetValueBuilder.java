package com.njyb.gbdbase.service.common.componet.report.sumary.cmp.father.build;

import java.util.Map;

import org.apache.lucene.document.Document;
import org.springframework.stereotype.Component;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.service.common.engine.util.ReportHelpUtil;

/**
 * 排序字段赋值
 * @author 贾红平
 *
 */
@Component
public class OrderFieldSetValueBuilder {
	public DataReportSumModel setOrderFieldValue(String[] orders, Map map,
			Document doc, String country, DataReportSumModel data) {
		// 赋值顺序 金额 重量 数量
		if (map.get("field_mwq").toString().contains(country)) {
			data.setTradeMoney(ReportHelpUtil.newInstanceReportUtil().parase(
					doc.get(orders[0])));
			data.setTradeWeight(ReportHelpUtil.newInstanceReportUtil().parase(
					doc.get(orders[1])));
			data.setTradeQuantity(ReportHelpUtil.newInstanceReportUtil()
					.parase(doc.get(orders[2])));
		} else if (map.get("field_mq").toString().contains(country)) {
			data.setTradeMoney(ReportHelpUtil.newInstanceReportUtil().parase(
					doc.get(orders[0])));
			data.setTradeQuantity(ReportHelpUtil.newInstanceReportUtil()
					.parase(doc.get(orders[1])));
		}
		// 赋值顺序 金额 重量
		else if (map.get("field_mw").toString().contains(country)) {
			data.setTradeMoney(ReportHelpUtil.newInstanceReportUtil().parase(
					doc.get(orders[0])));
			data.setTradeWeight(ReportHelpUtil.newInstanceReportUtil().parase(
					doc.get(orders[1])));
		}
		// 赋值顺序 数量
		else if (map.get("field_quantity").toString().contains(country)) {
			data.setTradeQuantity(ReportHelpUtil.newInstanceReportUtil()
					.parase(doc.get(orders[0])));
		}
		// 美国 重量 件数 尺寸
		else if (map.get("field_wps").toString().contains(country)) {
			data.setTradeWeight(ReportHelpUtil.newInstanceReportUtil().parase(
					doc.get(orders[0])));
			data.setTradePackage(ReportHelpUtil.newInstanceReportUtil().parase(
					doc.get(orders[1])));
			data.setTradeSize(ReportHelpUtil.newInstanceReportUtil().parase(
					doc.get(orders[2])));
		}
		// 巴基斯坦 重量 件数
		else if (map.get("field_wp").toString().contains(country)) {
			data.setTradeWeight(ReportHelpUtil.newInstanceReportUtil().parase(
					doc.get(orders[0])));
			data.setTradePackage(ReportHelpUtil.newInstanceReportUtil().parase(
					doc.get(orders[1])));
		}
		return data;
	}
}
