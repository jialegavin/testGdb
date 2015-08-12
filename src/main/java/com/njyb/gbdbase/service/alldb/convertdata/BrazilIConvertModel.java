package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.brazil.BrazilImportModel;

public class BrazilIConvertModel extends AllDBModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private BrazilIConvertModel(){
	}
	
	private static BrazilIConvertModel brazilI = null;
	
	/**
	 * 转换阿根廷数据
	 * @param argentinaExport
	 * @param country
	 * @return
	 */
	public static BrazilIConvertModel getBrazilIConvertModel(BrazilImportModel brazilImportModel,String country){
		brazilI = new BrazilIConvertModel();
		if (null != brazilI) {
			brazilI.setId(brazilImportModel.getBrazilImportId());
			brazilI.setWeight(brazilImportModel.getNetWeight());
			brazilI.setQuantity(brazilImportModel.getQuantity());
			brazilI.setDate(brazilImportModel.getDate());
			brazilI.setImporter(brazilImportModel.getImporter());
			brazilI.setCountry(country);
			brazilI.setCountNum(brazilImportModel.getQuantity());
			brazilI.setGoodsdescription(brazilImportModel.getGoodsDesc());
			brazilI.setHscode(brazilImportModel.getHscode());
			brazilI.setImporter(brazilImportModel.getImporter());
			brazilI.setTotalprice(brazilImportModel.getFobValue());
			brazilI.setUnitprice(brazilImportModel.getFobUnit());
		}
		return brazilI;
	}
}
