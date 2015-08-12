package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.argentina.ArgentinaExportModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;

import java.lang.Integer;

/**
 * 
 * ArgentinaExportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IArgentinaExportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<ArgentinaExportModel>  queryByPrimaryAll( SqlModel sqlModel);

}