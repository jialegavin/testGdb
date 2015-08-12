package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.korea.KoreaModel;
/**
 * 韩国
 * @author WangBo
 * 2015年6月4日
 * KoreaConvertModel.java
 */
public class KoreaConvertModel extends AllDBModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 韩国数据 转换
	 * @param koreaModel
	 * @param country
	 * @return
	 */
	public static KoreaConvertModel getKoreaConvertModel(KoreaModel koreaModel,String country) {
		KoreaConvertModel allDBModel = new KoreaConvertModel();
		allDBModel.setId(koreaModel.getKoreaId());
		allDBModel.setWeight(0);
		allDBModel.setDate(koreaModel.getDate());
		allDBModel.setCountNum(koreaModel.getNum());
		allDBModel.setCountry(country);
		allDBModel.setGoodsdescription(koreaModel.getGoodsDesc());
		allDBModel.setExporter("");
		allDBModel.setHscode(koreaModel.getHscode());
		allDBModel.setImporter("");
		allDBModel.setTotalprice(0);
		allDBModel.setUnitprice(0);
		return allDBModel;
	}
}