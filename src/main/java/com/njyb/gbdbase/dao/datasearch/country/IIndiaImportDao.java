package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.india.IndiaImportModel;

import java.lang.Integer;

/**
 * 
 * IndiaImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IIndiaImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<IndiaImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}