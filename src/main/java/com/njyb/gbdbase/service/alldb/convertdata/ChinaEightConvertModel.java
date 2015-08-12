package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.chinaEight.ChinaEightModel;
/**
 * 
 * @author WangBo
 * 2015年5月27日
 * ChinaEightModel.java
 */
public class ChinaEightConvertModel extends AllDBModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static ChinaEightConvertModel chinaEightConvert = null;
	
	private ChinaEightConvertModel() {
	}
	
	/**
	 * 转换中国数据<br>
	 * 单例
	 * @param chinaEightModel
	 * @param country
	 * @return
	 */
	public static AllDBModel getConvertModel(ChinaEightModel chinaEightModel,String country){
		chinaEightConvert = new ChinaEightConvertModel();
		if (null != chinaEightConvert) {
			chinaEightConvert.setId(chinaEightModel.getChinaEightId());
			chinaEightConvert.setCountNum(0);
			chinaEightConvert.setQuantity(chinaEightModel.getQuantity());
			chinaEightConvert.setCountry(country);
			chinaEightConvert.setDate(chinaEightModel.getDate());
			chinaEightConvert.setExporter(chinaEightModel.getCompanyName());
			chinaEightConvert.setImporter(chinaEightModel.getCompanyName());
			chinaEightConvert.setGoodsdescription(chinaEightModel.getGoodsDesc());
			chinaEightConvert.setHscode(chinaEightModel.getHscode());
			chinaEightConvert.setStartport(chinaEightModel.getStartPort());
			chinaEightConvert.setOrgincountry(chinaEightModel.getOriginCountry());
			chinaEightConvert.setTotalprice(chinaEightModel.getCifValue());
			chinaEightConvert.setIexport(chinaEightModel.getTradeType());
			chinaEightConvert.setUnitprice(chinaEightModel.getCifUnit());
		}
		return chinaEightConvert;
	}
}
