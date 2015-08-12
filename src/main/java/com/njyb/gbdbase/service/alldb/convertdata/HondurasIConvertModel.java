package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.honduras.HondurasImportModel;
/**
 * 洪都拉斯 进口
 * @author WangBo
 * 2015年6月4日
 * HondurasIConvertModel.java
 */
public class HondurasIConvertModel extends AllDBModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 获取洪都拉斯转换数据
	 * @param hondurasImportModel
	 * @param country
	 * @return
	 */
	public static HondurasIConvertModel getHondurasIConvertModel(HondurasImportModel hondurasImportModel,String country) {
		HondurasIConvertModel allDBModel = new HondurasIConvertModel();
		allDBModel.setId(hondurasImportModel.getHondurasImportId());
		allDBModel.setWeight(hondurasImportModel.getGrossWeight());
		allDBModel.setDate(hondurasImportModel.getDate());
		allDBModel.setCountNum(hondurasImportModel.getNum());
		allDBModel.setCountry(country);
		allDBModel.setGoodsdescription(hondurasImportModel.getGoodsDesc());
		allDBModel.setExporter("");
		allDBModel.setHscode("");
		allDBModel.setImporter("");
		allDBModel.setTotalprice(hondurasImportModel.getCifValue());
		allDBModel.setUnitprice(hondurasImportModel.getCifUnit());
		return allDBModel;
	}
}