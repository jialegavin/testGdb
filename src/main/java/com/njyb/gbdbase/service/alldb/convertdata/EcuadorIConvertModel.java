package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.ecuador.EcuadorImportModel;
/**
 *  厄瓜多尔  出口
 * @author WangBo
 * 2015年6月1日
 * EcuadorIConvertModel.java
 */
public class EcuadorIConvertModel extends AllDBModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private EcuadorIConvertModel(){
	}
	
	private static EcuadorIConvertModel allDBModel = null;
	
	/**
	 * 厄瓜多尔  出口
	 * @param ecuadorImportModel
	 * @param country
	 * @return
	 */
	public static EcuadorIConvertModel getEcuadorIConvertModel(EcuadorImportModel ecuadorImportModel,String country){
		allDBModel = new EcuadorIConvertModel();
		allDBModel.setWeight(0);
		allDBModel.setId(ecuadorImportModel.getEcuadorImportId());
		allDBModel.setDate(ecuadorImportModel.getDate());
		allDBModel.setCountNum(ecuadorImportModel.getQuantity());
		allDBModel.setOrgincountry(ecuadorImportModel.getOriginCountry());
		allDBModel.setCountry(country);
		allDBModel.setGoodsdescription(ecuadorImportModel.getGoodsDesc());
		allDBModel.setHscode(ecuadorImportModel.getHscode());
		allDBModel.setImporter(ecuadorImportModel.getImporter());
		allDBModel.setTotalprice(0);
		allDBModel.setUnitprice(ecuadorImportModel.getFobValue());
		return allDBModel;
	}
}