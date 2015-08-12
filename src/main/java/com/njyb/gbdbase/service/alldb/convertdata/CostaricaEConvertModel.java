package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.costarica.CostaricaExportModel;
/**
 * 哥斯达黎加出口数据转换
 * @author WangBo
 * 2015年5月27日
 * CostaricaEConvertModel.java
 */
public class CostaricaEConvertModel extends AllDBModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private CostaricaEConvertModel() {
	}

	private static CostaricaEConvertModel allDBModel = null;

	/**
	 * 哥斯达黎加出口数据转换数据
	 * @param argentinaExport
	 * @param country
	 * @return
	 */
	public static CostaricaEConvertModel getColombiaEConvoertModel(
			CostaricaExportModel costaricaExportModel, String country) {
		allDBModel = new CostaricaEConvertModel();
		if (null != allDBModel) {
			allDBModel.setId(costaricaExportModel.getCostaricaExportId());
			allDBModel.setWeight(costaricaExportModel.getNetWeight());
			allDBModel.setDate(costaricaExportModel.getDate());
			allDBModel.setCountry(country);
			allDBModel.setGoodsdescription(costaricaExportModel.getGoodsDesc());
			allDBModel.setHscode(costaricaExportModel.getHscode());
			allDBModel.setExporter(costaricaExportModel.getExporter());
			allDBModel.setTotalprice(costaricaExportModel.getTotalCif());
			allDBModel.setUnitprice(costaricaExportModel.getCifValue());
		}
		return allDBModel;
	}
}