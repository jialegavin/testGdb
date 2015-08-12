package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.ecuador.EcuadorImportModel;

import java.lang.Integer;

/**
 * 
 * EcuadorImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IEcuadorImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<EcuadorImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}