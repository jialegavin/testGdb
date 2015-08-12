package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.uk.UkImportModel;

import java.lang.Integer;

/**
 * 
 * UkImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IUkImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<UkImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}