package com.njyb.gbdbase.dao.datasearch.country;


import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
import com.njyb.gbdbase.model.datasearch.pakistan.PakistanImportModel;

import java.lang.Integer;

/**
 * 
 * PakistanImportMapper数据库操作接口类
 * 
 **/
@MyBatisReposity
public interface IPakistanImportDao{


	/**
	 * 
	 * 查询所有返回List（）
	 * 
	 **/
	List<PakistanImportModel>  queryByPrimaryAll( SqlModel sqlModel);

}