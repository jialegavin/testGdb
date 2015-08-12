package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.brazil.BrazilImportModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;

import java.lang.Integer;

/**
 * 
 * BrazilImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IBrazilImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<BrazilImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}