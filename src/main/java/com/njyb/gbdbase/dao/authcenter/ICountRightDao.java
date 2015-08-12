package com.njyb.gbdbase.dao.authcenter;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.admincenter.UserModel;
import com.njyb.gbdbase.model.authcenter.CountRightModel;
/**
 * 按次用户的按次权限Dao
 * @author chenhu
 * 2015年4月27日
 */
@MyBatisReposity
public interface ICountRightDao {
	/**
	 * 根据用户Id获取当前的用户次数记录
	 * @param user
	 * @return
	 */
	CountRightModel queryCountRightModelById(UserModel user);
    /**
     * 更新按次用户的使用次数
     * @param countRight
     */
	void updateCountRight(CountRightModel countRight);
	/**
	 * 新增用户下载权限
	 * @param countRight
	 */
	void addNewCountRight(CountRightModel countRight);
}
