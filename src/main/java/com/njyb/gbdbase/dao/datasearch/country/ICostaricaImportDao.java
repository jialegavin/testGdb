package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.costarica.CostaricaImportModel;

import java.lang.Integer;

/**
 * 
 * CostaricaImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface ICostaricaImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<CostaricaImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}