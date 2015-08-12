package com.njyb.gbdbase.service.common.componet.report.sumary.cmp.father.build;

import java.util.Map;

import org.apache.lucene.document.Document;
import org.springframework.stereotype.Component;

import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
/**
 * 查询字段赋值
 * @author 贾红平
 *
 */
@Component
public class SearchConditionSetValueBuilder {
	//
	public DataReportSumModel  setSearchConditionValue(@SuppressWarnings("rawtypes") Map map,Document doc,String country,DataReportSumModel data){
		// 进口商赋值 importer
		if (map.get("jks_field").toString().contains(country)) {
			data.setImporter(setDocValue(doc, "importer"));
		}
		//企业名称赋值 company_name
		if (map.get("company_field").toString().contains(country)) {
			data.setCompany_name(setDocValue(doc, "company_name"));
		}
		//出口口岸 start_port
		if (map.get("port_field").toString().contains(country)) {
			data.setPort(setDocValue(doc, "start_port"));
		}
		// 出口商赋值 exporter
		if (map.get("cks_field").toString().contains(country)) {
			data.setExporter(setDocValue(doc, "exporter"));
		}
		// 起运港赋值 start_port
		if (map.get("qyg_field").toString().contains(country)) {
			data.setStart_port(setDocValue(doc, "start_port"));
		}
		// 抵达港赋值 end_port
		if (map.get("ddg_field").toString().contains(country)) {
			data.setEnd_port(setDocValue(doc, "end_port"));
		}
		// 海关城市赋值 customs
		if (map.get("hgcs_field").toString().contains(country)) {
			data.setCustoms(setDocValue(doc, "customs"));
		}
		// 原产国赋值 origin_country
		if (map.get("ycg_field").toString().contains(country)) {
			data.setOrigin_country(setDocValue(doc, "origin_country"));
		}
		// 目的国赋值 dest_country
		if (map.get("mdg_field").toString().contains(country)) {
			data.setDest_country(setDocValue(doc, "dest_country"));
		}
		// 制造商赋值 manufacture
		if (map.get("zzs_field").toString().contains(country)) {
			data.setManufacture(setDocValue(doc, "zzs_field"));
		}
		// 通知人赋值 notifier
		if (map.get("tzr_field").toString().contains(country)) {
			data.setNotifier(setDocValue(doc, "notifier"));
		}
		// 设置产品描述 goods_desc
		if (map.get("nohscode_field").toString().contains(country)) {
			data.setGoodsDesc(doc.get("goods_desc"));
		}
		// 产品描述+海关编码
		if (!map.get("nohscode_field").toString().contains(country)) {
			data.setGoodsDesc(doc.get("goods_desc"));
			data.setHscode(doc.get("hscode"));
		}
		// 收发货地
		if (map.get("place_field").toString().contains(country)) {
			data.setProduct_place(setDocValue(doc, "product_place"));
		}
//		// 收发货地port_field
//		if (map.get("port_field").toString().contains(country)) {
//			data.setPort(port);(setDocValue(doc, "start_port"));
//		}
				
		return data;
	}
	/**
	 * 设置对象属性的值 通过索引
	 * @param doc
	 * @param key
	 * @return
	 */
	private static  String setDocValue(Document doc,String key){
		String value=null;
		if (doc!=null) {
			value=doc.get(key);
			if (value==null || value.equals("") || value.equals(" ") || value.equals("null")) {
				value="N/A";
			}
		}
		return value;
	}
}
