package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.costarica.CostaricaImportModel;
/**
 * 哥斯达黎加进口出口数据
 * @author WangBo
 * 2015年5月27日
 * CostaricaIConvertModel.java
 */
public class CostaricaIConvertModel extends AllDBModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private CostaricaIConvertModel() {
	}

	private static CostaricaIConvertModel allDBModel = null;
	
	/**
	 * 哥斯达黎加进口数据转换数据
	 * @param argentinaExport
	 * @param country
	 * @return
	 */
	public static CostaricaIConvertModel getCostaricaIConvertModel(
			CostaricaImportModel costaricaImportModel, String country) {
		allDBModel = new CostaricaIConvertModel();
		if (null != allDBModel) {
			allDBModel.setId(costaricaImportModel.getCostaricaImportId());
			allDBModel.setWeight(costaricaImportModel.getNetWeight());
			allDBModel.setDate(costaricaImportModel.getDate());
			allDBModel.setCountry(country);
			allDBModel.setGoodsdescription(costaricaImportModel.getGoodsDesc());
			allDBModel.setHscode(costaricaImportModel.getHscode());
			allDBModel.setImporter(costaricaImportModel.getImporter());
			allDBModel.setTotalprice(costaricaImportModel.getTotalCif());
			allDBModel.setUnitprice(costaricaImportModel.getCifValue());
		}
		return allDBModel;
	}
}