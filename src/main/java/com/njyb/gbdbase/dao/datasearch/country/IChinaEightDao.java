package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.chinaEight.ChinaEightModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;

import java.lang.Integer;

/**
 * 
 * ChinaEightMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IChinaEightDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<ChinaEightModel>  queryByPrimaryAll( SqlModel sqlModel);

}