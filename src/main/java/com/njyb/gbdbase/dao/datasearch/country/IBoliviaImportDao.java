package com.njyb.gbdbase.dao.datasearch.country;

import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.bolivia.BoliviaImportModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;

/**
 * 
 * BoliviaImportModel数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IBoliviaImportDao {

	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<BoliviaImportModel>  queryByPrimaryAll(SqlModel sqlModel);
}
