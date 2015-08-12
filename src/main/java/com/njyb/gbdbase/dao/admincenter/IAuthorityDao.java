package com.njyb.gbdbase.dao.admincenter;

import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.admincenter.AuthorityModel;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;


/**
 * 初始化权限信息DAO
 * @author 章华才
 */
@MyBatisReposity
public interface IAuthorityDao {

	/**
	 * 获取权限基本信息
	 * @param userid 用户Id
	 * @return
	 */
	public abstract List<ConditionRightModel> queryJurisdictInfo(Integer userid);
	
}
