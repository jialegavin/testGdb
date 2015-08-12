package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.honduras.HondurasImportModel;

import java.lang.Integer;

/**
 * 
 * HondurasImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IHondurasImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<HondurasImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}