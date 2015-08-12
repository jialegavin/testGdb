package com.njyb.gbdbase.dao.datasearch.country;

import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.allCountrySelectField.AllCountrySelectFieldModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;

/**
 * 所有下拉框显示国家字段
 * @author 章华才
 */
@MyBatisReposity
public interface IAllCountrySelectFieldDao {

	/**
	 * 查询所有国家显示的字段
	 * @return list
	 */
	List<AllCountrySelectFieldModel> queryAllCountrySelectField(SqlModel sqlmodel);
	
}
