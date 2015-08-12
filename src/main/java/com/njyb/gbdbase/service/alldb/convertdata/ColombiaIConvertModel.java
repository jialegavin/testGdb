package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.colombia.ColombiaImportModel;

/**
 * 克罗姆进口数据
 * @author WangBo
 * 2015年5月27日
 * ColombiaIConvertModel.java
 */
public class ColombiaIConvertModel extends AllDBModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private ColombiaIConvertModel() {
	}

	private static ColombiaIConvertModel allDBModel = null;

	/**
	 * 转换克罗姆出口数据
	 * @param argentinaExport
	 * @param country
	 * @return
	 */
	public static ColombiaIConvertModel getColombiaEConvoertModel(
			ColombiaImportModel colombiaImportModel, String country) {
		allDBModel = new ColombiaIConvertModel();
		if (null != allDBModel) {
			allDBModel.setId(colombiaImportModel.getColombiaImportId());
			allDBModel.setWeight(colombiaImportModel.getNetWeight());
			allDBModel.setDate(colombiaImportModel.getDate());
			allDBModel.setCountry(country);
			allDBModel.setCountNum(colombiaImportModel.getQuantity());
			allDBModel.setGoodsdescription(colombiaImportModel.getGoodsDesc());
			allDBModel.setHscode(colombiaImportModel.getHscode());
			allDBModel.setImporter(colombiaImportModel.getImporter());
			allDBModel.setExporter(colombiaImportModel.getExporter());
			allDBModel.setTotalprice(colombiaImportModel.getFobValue());
		}
		return allDBModel;
	}

}
