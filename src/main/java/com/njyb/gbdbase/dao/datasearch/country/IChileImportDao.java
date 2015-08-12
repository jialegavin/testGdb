package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.chile.ChileImportModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;

import java.lang.Integer;

/**
 * 
 * ChileImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IChileImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<ChileImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}