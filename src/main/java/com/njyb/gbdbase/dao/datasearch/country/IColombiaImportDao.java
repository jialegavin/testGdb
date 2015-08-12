package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.colombia.ColombiaImportModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;

import java.lang.Integer;

/**
 * 
 * ColombiaImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IColombiaImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<ColombiaImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}