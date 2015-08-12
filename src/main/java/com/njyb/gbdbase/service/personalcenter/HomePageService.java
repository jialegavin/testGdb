package com.njyb.gbdbase.service.personalcenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njyb.gbdbas.util.ds.spring.DBContextUtil;
import com.njyb.gbdbase.model.personalcenter.CountryUpdateTimeModel;
import com.njyb.gbdbase.dao.personalcenter.IHomePageDao;

/**
 * 查询各个国家数据的数据更新时间
 * @author honghao
 * 
 */
@Service
public class HomePageService implements IHomePageService{
	
	@Autowired
	private IHomePageDao homePageDao;
	
	/**
	 * 查询数据更新时间
	 * @return CountryUpdateTimeModel
	 */
	public List<CountryUpdateTimeModel> getCountryUpdateTimeModel()
	{
		//切换数据库
		DBContextUtil.setDbTypeName(DBContextUtil.DATA_SOURCE_USER);
		return homePageDao.getCountryUpdateTimeModel();
	}

}
