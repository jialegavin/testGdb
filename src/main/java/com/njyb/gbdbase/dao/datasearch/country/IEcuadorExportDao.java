package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.ecuador.EcuadorExportModel;

import java.lang.Integer;

/**
 * 
 * EcuadorExportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IEcuadorExportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<EcuadorExportModel>  queryByPrimaryAll( SqlModel sqlModel);

}