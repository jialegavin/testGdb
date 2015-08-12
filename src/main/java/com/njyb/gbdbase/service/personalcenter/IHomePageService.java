
package com.njyb.gbdbase.service.personalcenter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.njyb.gbdbase.model.personalcenter.CountryUpdateTimeModel;
import com.njyb.gbdbase.dao.personalcenter.IHomePageDao;

/**
 * 查询各个国家数据的数据更新时间
 * 
 * @author honghao
 * 
 */
@Service
public interface IHomePageService {
	
	/**
	 * 查询数据更新时间
	 * @return CountryUpdateTimeModel
	 */
	List<CountryUpdateTimeModel> getCountryUpdateTimeModel();

}
