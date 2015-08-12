package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.uruguay.UruguayExportModel;

import java.lang.Integer;

/**
 * 
 * UruguayExportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IUruguayExportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<UruguayExportModel>  queryByPrimaryAll( SqlModel sqlModel);

}