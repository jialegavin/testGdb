package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.venezuela.VenezuelaExportModel;

import java.lang.Integer;

/**
 * 
 * VenezuelaExportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IVenezuelaExportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<VenezuelaExportModel>  queryByPrimaryAll( SqlModel sqlModel);

}