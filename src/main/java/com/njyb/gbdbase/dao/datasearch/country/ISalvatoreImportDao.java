package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.salvatore.SalvatoreImportModel;

import java.lang.Integer;

/**
 * 
 * SalvatoreImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface ISalvatoreImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<SalvatoreImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}