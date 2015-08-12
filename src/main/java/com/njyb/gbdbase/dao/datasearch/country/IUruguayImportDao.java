package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.uruguay.UruguayImportModel;

import java.lang.Integer;

/**
 * 
 * UruguayImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IUruguayImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<UruguayImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}