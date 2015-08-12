package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.paraguay.ParaguayExportModel;

import java.lang.Integer;

/**
 * 
 * ParaguayExportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IParaguayExportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<ParaguayExportModel>  queryByPrimaryAll( SqlModel sqlModel);

}