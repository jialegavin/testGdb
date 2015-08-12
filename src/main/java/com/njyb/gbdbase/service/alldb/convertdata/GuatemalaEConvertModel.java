package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.guatemala.GuatemalaExportModel;

/**
 * 危地马拉出口
 * @author WangBo
 * 2015年6月4日
 * GuatemalaEConvertModel.java
 */
public class GuatemalaEConvertModel extends AllDBModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 获取危地马拉出口
	 * @param guatemalaExportModel
	 * @param country
	 * @return
	 */
	public static GuatemalaEConvertModel getGuatemalaEConvertModel(GuatemalaExportModel guatemalaExportModel,String country){
		GuatemalaEConvertModel allDBModel = new GuatemalaEConvertModel();
		allDBModel.setWeight(0);
		allDBModel.setQuantity(guatemalaExportModel.getQuantity());
		allDBModel.setId(guatemalaExportModel.getGuatemalaExportId());
		allDBModel.setDate(guatemalaExportModel.getDate());
		allDBModel.setCountNum(guatemalaExportModel.getQuantity());
		allDBModel.setOrgincountry(guatemalaExportModel.getOriginCountry());
		allDBModel.setCountry(country);
		allDBModel.setGoodsdescription(guatemalaExportModel.getGoodsDesc());
		allDBModel.setHscode("");
		allDBModel.setExporter(guatemalaExportModel.getExporter());
		allDBModel.setImporter(guatemalaExportModel.getImporter());
		allDBModel.setTotalprice(0);
		allDBModel.setUnitprice(0);
		return allDBModel;
	}
}
