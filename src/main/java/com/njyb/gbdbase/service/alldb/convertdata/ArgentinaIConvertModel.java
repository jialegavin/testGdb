package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.argentina.ArgentinaImportModel;
/**
 * 
 * @author WangBo
 * 2015年5月27日
 * ArgentinaConvertModel.java
 */
public class ArgentinaIConvertModel extends AllDBModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private static ArgentinaIConvertModel argentinaIConvertModel = null;
	
	private ArgentinaIConvertModel() {
	}
	
	/**
	 * 转换阿根廷进口数据<br>
	 * 单例
	 * @param chinaEightModel
	 * @param country
	 * @return
	 */
	public static AllDBModel getConvertModel(ArgentinaImportModel argentina,String country){
		argentinaIConvertModel = new ArgentinaIConvertModel();
		if (null != argentinaIConvertModel) {
			argentinaIConvertModel.setId(argentina.getArgentinaImportId());
			argentinaIConvertModel.setWeight(argentina.getGrossWeight());
			argentinaIConvertModel.setDate(argentina.getDate());
			// TODO 注意删除 allDBModel.setExporter(argentina.getOriginCountry());
			argentinaIConvertModel.setExporter(argentina.getOriginCountry());
			argentinaIConvertModel.setCountry(country);
			argentinaIConvertModel.setQuantity(argentina.getQuantity());
			argentinaIConvertModel.setStartport(argentina.getStartPort());
			argentinaIConvertModel.setGoodsdescription(argentina.getGoodsDesc());
			argentinaIConvertModel.setHscode(argentina.getHscode());
			argentinaIConvertModel.setCustomscity(argentina.getCustoms());
			argentinaIConvertModel.setTotalprice(argentina.getCifValue());
			argentinaIConvertModel.setImporter(argentina.getImporter());
			argentinaIConvertModel.setCustomscity(argentina.getCustoms());
			argentinaIConvertModel.setOrgincountry(argentina.getOriginCountry());
		}
		return argentinaIConvertModel;
	}
}