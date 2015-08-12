package com.njyb.gbdbase.dao.admincenter;

import java.util.List;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.admincenter.SubModuleModel;

/**
 * 子模块的数据访问层
 * @author 章华才
 */
@MyBatisReposity
public interface ISubModuleDao {

	
	/**
	 * 获取子模块的访问方法
	 * @param loginName 用户登录名称
	 * @return
	 */
	public abstract List<SubModuleModel> querySubModule(String loginName);
}
