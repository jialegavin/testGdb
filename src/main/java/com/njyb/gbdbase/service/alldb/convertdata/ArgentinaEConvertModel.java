package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.argentina.ArgentinaExportModel;
/**
 * 阿根廷出口
 * @author WangBo
 * 2015年5月27日
 * ArgentinaEConvertModel.java
 */
public class ArgentinaEConvertModel extends AllDBModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArgentinaEConvertModel() {
	}
	
	private static ArgentinaEConvertModel argentina = null;
	
	/**
	 * 转换阿根廷数据
	 * @param argentinaExport
	 * @param country
	 * @return
	 */
	public static ArgentinaEConvertModel getArgentinaEConvertModel(ArgentinaExportModel argentinaExport,String country){
		argentina = new ArgentinaEConvertModel();
		if (null != argentina) {
			argentina.setId(argentinaExport.getArgentinaExportId());
			argentina.setQuantity(argentinaExport.getQuantity());
			argentina.setWeight(argentinaExport.getGrossWeight());
			argentina.setDate(argentinaExport.getDate());
			argentina.setCountry(country);
			argentina.setCountNum(argentinaExport.getQuantity());
			argentina.setGoodsdescription(argentinaExport.getGoodsDesc());
			argentina.setHscode(argentinaExport.getHscode());
			argentina.setCustomscity(argentinaExport.getCustoms());
			argentina.setTotalprice(argentinaExport.getCifValue());
			argentina.setCustomscity(argentinaExport.getCustoms());
		}
		return argentina;
	}
}