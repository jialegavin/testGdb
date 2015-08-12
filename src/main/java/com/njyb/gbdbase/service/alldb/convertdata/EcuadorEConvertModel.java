package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.ecuador.EcuadorExportModel;
/**
 * 厄瓜多尔 出口
 * @author WangBo
 * 2015年6月1日
 * EcuadorEConvertModel.java
 */
public class EcuadorEConvertModel extends AllDBModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private EcuadorEConvertModel() {
	}
	
	private static EcuadorEConvertModel argentina = null;
	
	/**
	 * 转换厄瓜多尔数据
	 * @param argentinaExport
	 * @param country
	 * @return
	 */
	public static EcuadorEConvertModel getEcuadorEConvertModel(EcuadorExportModel ecuadorExportModel,String country){
		argentina = new EcuadorEConvertModel();
		if (null != ecuadorExportModel) {
			argentina.setWeight(0);
			argentina.setId(ecuadorExportModel.getEcuadorExportId());
			argentina.setDate(ecuadorExportModel.getDate());
			argentina.setCountNum(ecuadorExportModel.getQuantity());
			argentina.setCountry(country);
			argentina.setGoodsdescription(ecuadorExportModel.getGoodsDesc());
			argentina.setHscode(ecuadorExportModel.getHscode());
			argentina.setImporter(ecuadorExportModel.getImporter());
			argentina.setTotalprice(0);
			argentina.setUnitprice(ecuadorExportModel.getFobValue());
		}
		return argentina;
	}
}