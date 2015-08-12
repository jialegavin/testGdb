package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.colombia.ColombiaExportModel;
/**
 * 转换克罗姆出口数据
 * @author WangBo
 * 2015年5月27日
 * ColombiaEConvoertModel.java
 */
public class ColombiaEConvoertModel extends AllDBModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private ColombiaEConvoertModel() {
	}

	private static ColombiaEConvoertModel allDBModel = null;

	/**
	 * 转换克罗姆出口数据
	 * @param argentinaExport
	 * @param country
	 * @return
	 */
	public static ColombiaEConvoertModel getColombiaEConvoertModel(
			ColombiaExportModel colombiaExportModel, String country) {
		allDBModel = new ColombiaEConvoertModel();
		if (null != allDBModel) {
			allDBModel.setId(colombiaExportModel.getColombiaExportId());
			allDBModel.setWeight(colombiaExportModel.getNetWeight());
			allDBModel.setDate(colombiaExportModel.getDate());
			allDBModel.setCountry(country);
			allDBModel.setCountNum(colombiaExportModel.getQuantity());
			allDBModel.setGoodsdescription(colombiaExportModel.getGoodsDesc());
			allDBModel.setHscode(colombiaExportModel.getHscode());
			allDBModel.setImporter(colombiaExportModel.getImporter());
			allDBModel.setExporter(colombiaExportModel.getExporter());
			allDBModel.setTotalprice(colombiaExportModel.getFobValue());
		}
		return allDBModel;
	}
}
