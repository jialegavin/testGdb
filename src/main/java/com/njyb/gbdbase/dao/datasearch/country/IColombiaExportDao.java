package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.colombia.ColombiaExportModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;

import java.lang.Integer;

/**
 * 
 * ColombiaExportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IColombiaExportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<ColombiaExportModel>  queryByPrimaryAll( SqlModel sqlModel);

}