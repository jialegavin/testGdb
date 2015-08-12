package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.salvatore.SalvatoreExportModel;

import java.lang.Integer;

/**
 * 
 * SalvatoreExportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface ISalvatoreExportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<SalvatoreExportModel>  queryByPrimaryAll( SqlModel sqlModel);

}