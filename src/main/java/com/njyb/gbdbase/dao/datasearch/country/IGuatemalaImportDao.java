package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.guatemala.GuatemalaImportModel;

import java.lang.Integer;

/**
 * 
 * GuatemalaImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IGuatemalaImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<GuatemalaImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}