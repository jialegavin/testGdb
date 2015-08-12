package com.njyb.gbdbase.dao.personalcenter;

import java.util.List;
import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.personalcenter.CountryUpdateTimeModel;

/**
 * 查询各个国家数据的数据更新时间
 * 
 * @author honghao
 * 
 */
@MyBatisReposity
public interface IHomePageDao {
	
	/**
	 * 查询数据更新时间
	 * @return CountryUpdateTimeModel
	 */
	List<CountryUpdateTimeModel> getCountryUpdateTimeModel();

}
