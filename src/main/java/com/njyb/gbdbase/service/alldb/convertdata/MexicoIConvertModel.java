package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.mexico.MexicoImportModel;
/**
 * 墨西哥进口
 * @author WangBo
 * 2015年6月4日
 * MexicoIConvertModel.java
 */
public class MexicoIConvertModel extends AllDBModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 墨西哥进口
	 * @param mexicoImportModel
	 * @param country
	 * @return
	 */
	public static MexicoIConvertModel getMexicoIConvertModel(MexicoImportModel mexicoImportModel,String country) {
		MexicoIConvertModel allDBModel = new MexicoIConvertModel();
		allDBModel.setQuantity(mexicoImportModel.getQuantity());
		allDBModel.setId(mexicoImportModel.getMexicoImportId());
		allDBModel.setWeight(0);
		allDBModel.setDate(mexicoImportModel.getDate());
		allDBModel.setCountNum(mexicoImportModel.getQuantity());
		allDBModel.setOrgincountry(mexicoImportModel.getOriginCountry());
		allDBModel.setCountry(country);
		allDBModel.setGoodsdescription(mexicoImportModel.getGoodsDesc());
		allDBModel.setExporter("");
		allDBModel.setHscode(mexicoImportModel.getHscode());
		allDBModel.setImporter("");
		allDBModel.setTotalprice(mexicoImportModel.getFobValue());
		allDBModel.setUnitprice(mexicoImportModel.getFobUnit());
		return allDBModel;
	}
	
}