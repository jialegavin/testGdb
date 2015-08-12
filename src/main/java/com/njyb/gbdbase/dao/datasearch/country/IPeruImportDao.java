package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.peru.PeruImportModel;

import java.lang.Integer;

/**
 * 
 * PeruImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IPeruImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<PeruImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}