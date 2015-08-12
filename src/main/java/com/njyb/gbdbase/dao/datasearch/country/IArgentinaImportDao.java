package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.argentina.ArgentinaImportModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;

import java.lang.Integer;

/**
 * 
 * ArgentinaImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IArgentinaImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<ArgentinaImportModel>  queryByPrimaryAll(SqlModel sqlModel);

}