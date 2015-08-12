package com.njyb.gbdbase.dao.authcenter;

import java.util.List;
import java.util.Map;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.usermanagement.ConditionRightModel;

/**
 * 按次用户的按次权限Dao
 * @author honghao
 * 2015年05月19日
 */
@MyBatisReposity
public interface IUserRightDao {
	
	/**
	 * 根据用户Id查询用户权限表<br>
	 * @param userId :　用户Id
	 * @return List<UserRightModel>
	 */
	List<ConditionRightModel> queryUserRightResultByUserIdAndPage(int userId);
	
	/**
	 * 添加用户权限<br>
	 * 用于在定制时调用此方法,添加用户的权限
	 * @param userRightModel
	 * @return
	 */
	int insertUserRigth(ConditionRightModel userRightModel);
	
	/**
	 * 根据用户Id查询用户权限表<br>
	 * @param userId :　用户Id
	 * @return List<UserRightModel>
	 */
	List<ConditionRightModel> queryUserRightResult(Map<String,Object> paramMap);
	
	/**
	 * 根据map的userId && time && hsCode && product<br>
	 * 查询用户时候拥有此权限
	 * @param paramMap
	 * @author WangBo
	 * @return List<UserRightModel>
	 */
	public List<ConditionRightModel> checkUserRightByParam(Map<String,Object> paramMap);
}
