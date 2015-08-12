package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.panama.PanamaExportModel;

import java.lang.Integer;

/**
 * 
 * PanamaExportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IPanamaExportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<PanamaExportModel>  queryByPrimaryAll( SqlModel sqlModel);

}