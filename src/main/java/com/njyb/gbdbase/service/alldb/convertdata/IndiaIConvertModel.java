package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.india.IndiaImportModel;
/**
 * 印度进口 数据
 * @author WangBo
 * 2015年6月4日
 * IndiaIConvertModel.java
 */
public class IndiaIConvertModel extends AllDBModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 洪都拉斯进口
	 * @param indiaImportModel
	 * @param country
	 * @return
	 */
	public static IndiaIConvertModel getIConvertModel(IndiaImportModel indiaImportModel,String country) {
		IndiaIConvertModel allDBModel = new IndiaIConvertModel();
		allDBModel.setId(indiaImportModel.getIndiaImportId());
		allDBModel.setWeight(0);
		allDBModel.setQuantity(indiaImportModel.getQuantity());
		allDBModel.setDate(indiaImportModel.getDate());
		allDBModel.setCountNum(indiaImportModel.getQuantity());
		allDBModel.setCountry(country);
		allDBModel.setGoodsdescription(indiaImportModel.getGoodsDesc());
		allDBModel.setExporter(indiaImportModel.getExporter());
		allDBModel.setHscode(indiaImportModel.getHscode());
		allDBModel.setImporter(indiaImportModel.getImporter());
		allDBModel.setTotalprice(indiaImportModel.getInrValue());
		allDBModel.setUnitprice(indiaImportModel.getUsdUnit());
		return allDBModel;
	}
}