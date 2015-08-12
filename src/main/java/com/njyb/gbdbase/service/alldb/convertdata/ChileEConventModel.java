package com.njyb.gbdbase.service.alldb.convertdata;

import java.io.Serializable;

import com.njyb.gbdbase.model.alldb.commonrightlibrary.AllDBModel;
import com.njyb.gbdbase.model.datasearch.chile.ChileExportModel;
/**
 * 智利出口转换数据
 * @author WangBo
 * 2015年5月27日
 * ChileEConventModel.java
 */
public class ChileEConventModel extends AllDBModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ChileEConventModel(){
	}
	
	private static ChileEConventModel allDBModel = null;
	
	/**
	 * 转换智利进口数据
	 * @param argentinaExport
	 * @param country
	 * @return
	 */
	public static ChileEConventModel getChileEConventModel(ChileExportModel chileExportModel,String country){
		allDBModel = new ChileEConventModel();
		if (null != allDBModel) {
			allDBModel.setId(chileExportModel.getChileExportId());
			allDBModel.setQuantity(chileExportModel.getQuantity());
			allDBModel.setWeight(chileExportModel.getGrossWeight());
			allDBModel.setDate(chileExportModel.getDate());
			allDBModel.setCountry(country);
			allDBModel.setCountNum(chileExportModel.getQuantity());
			allDBModel.setGoodsdescription(chileExportModel.getGoodsDesc());
			allDBModel.setHscode(chileExportModel.getHscode());
			allDBModel.setExporter(chileExportModel.getExporter());
			allDBModel.setTotalprice(chileExportModel.getFobValue());
			allDBModel.setUnitprice(chileExportModel.getFobUnit());
		}
		return allDBModel;
	}
}
