package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.paraguay.ParaguayImportModel;

import java.lang.Integer;

/**
 * 
 * ParaguayImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IParaguayImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<ParaguayImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}