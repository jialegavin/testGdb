package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.chile.ChileExportModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;

import java.lang.Integer;

/**
 * 
 * ChileExportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IChileExportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<ChileExportModel>  queryByPrimaryAll( SqlModel sqlModel);

}