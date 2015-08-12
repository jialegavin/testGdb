package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.guatemala.GuatemalaImportModel;
/**
 * 危地马拉进口
 * @author WangBo
 * 2015年6月4日
 * GuatemalaIConvertModel.java
 */
public class GuatemalaIConvertModel extends AllDBModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 危地马拉数据
	 * @param guatemalaImportModel
	 * @param country
	 * @return
	 */
	public static GuatemalaIConvertModel getGuatemalaIConvertModel(GuatemalaImportModel guatemalaImportModel,String country) {
		GuatemalaIConvertModel allDBModel = new GuatemalaIConvertModel();
		allDBModel.setId(guatemalaImportModel.getGuatemalaImportId());
		allDBModel.setWeight(0);
		allDBModel.setQuantity(guatemalaImportModel.getQuantity());
		allDBModel.setDate(guatemalaImportModel.getDate());
		allDBModel.setCountNum(guatemalaImportModel.getQuantity());
		allDBModel.setCountry(country);
		allDBModel.setGoodsdescription(guatemalaImportModel.getGoodsDesc());
		allDBModel.setExporter(guatemalaImportModel.getExporter());
		allDBModel.setHscode("");
		allDBModel.setImporter(guatemalaImportModel.getImporter());
		allDBModel.setTotalprice(0);
		allDBModel.setUnitprice(0);
		return allDBModel;
	}
}