package com.njyb.gbdbase.service.personalcenter;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.njyb.gbdbase.dao.personalcenter.IHomePageDao;
import com.njyb.gbdbase.model.personalcenter.CountryUpdateTimeModel;

/**
 * 查询各个国家数据的数据更新时间
 * @author honghao  业务需求变更,重写 wangB
 * 
 */
@Service
public class HomePageService implements IHomePageService{
	
	private static Logger log4j = Logger.getLogger(HomePageService.class); 
	
	@Autowired
	private IHomePageDao homePageDao;
	
	/**
	 * 查询个个国家更新数据时间
	 * @return CountryUpdateTimeModel
	 */
	public List<CountryUpdateTimeModel> getCountryUpdateTimeModel() {
		List<CountryUpdateTimeModel> resultList = Lists.newArrayList();
		try {
			resultList = homePageDao.getCountryUpdateTimeModel();
		} catch (Exception e) {
			log4j.debug(e);
		}
		return resultList;
	}
}