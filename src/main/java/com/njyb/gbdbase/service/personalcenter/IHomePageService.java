
package com.njyb.gbdbase.service.personalcenter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.njyb.gbdbase.model.personalcenter.CountryUpdateTimeModel;

/**
 * 查询各个国家数据的数据更新时间
 * @author honghao 需求变更,重写 wangB
 */
@Service
public interface IHomePageService {
	
	/**
	 * 查询数据更新时间
	 * @return CountryUpdateTimeModel
	 */
	List<CountryUpdateTimeModel> getCountryUpdateTimeModel();

}
