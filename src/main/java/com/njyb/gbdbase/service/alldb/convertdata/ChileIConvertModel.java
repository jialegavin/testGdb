package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.chile.ChileImportModel;

/**
 * 智利进口数据转换
 * 
 * @author WangBo 2015年5月27日 ChileIConvertModel.java
 */
public class ChileIConvertModel extends AllDBModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private ChileIConvertModel() {
	}

	private static ChileIConvertModel allDBModel = null;

	/**
	 * 转换智利进口数据
	 * @param argentinaExport
	 * @param country
	 * @return
	 */
	public static ChileIConvertModel getChileIConvertModel(
			ChileImportModel chileImportModel, String country) {
		allDBModel = new ChileIConvertModel();
		if (null != allDBModel) {
			allDBModel.setId(chileImportModel.getChileImportId());
			allDBModel.setWeight(chileImportModel.getGrossWeight());
			allDBModel.setDate(chileImportModel.getDate());
			allDBModel.setCountry(country);
			allDBModel.setCountNum(chileImportModel.getQuantity());
			allDBModel.setGoodsdescription(chileImportModel.getGoodsDesc());
			allDBModel.setHscode(chileImportModel.getHscode());
			allDBModel.setImporter(chileImportModel.getImporter());
			allDBModel.setTotalprice(chileImportModel.getFobValue());
			allDBModel.setUnitprice(chileImportModel.getFobUnit());
		}
		return allDBModel;
	}
}
