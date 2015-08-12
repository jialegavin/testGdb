package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.russia.RussiaImportModel;

import java.lang.Integer;

/**
 * 
 * RussiaImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IRussiaImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<RussiaImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}