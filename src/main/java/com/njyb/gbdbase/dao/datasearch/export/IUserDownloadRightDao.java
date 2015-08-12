package com.njyb.gbdbase.dao.datasearch.export;

import org.apache.ibatis.annotations.Param;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.datasearch.export.UserDownloadRightModel;

/**
 * @Description 用户下载权限数据库访问层
 * @author XL
 * @date 2015-03-18
 * @version 标准版
 */
@MyBatisReposity
public interface IUserDownloadRightDao {
	
	/**
	 * 添加用户下载权限记录
	 * @param userDownloadRightModel 用户下载权限实体类
	 */
	void insertDownloadRecord(UserDownloadRightModel userDownloadRightModel);

	/**
	 * 根据用户id查询用户下载权限信息
	 * @param userId 用户id
	 * @return
	 */
	UserDownloadRightModel queryUserDownloadRight(Integer userId);
	
	/**
	 * 公用修改用户下载EXCEL/PDF记录
	 * @param sql SQL语句
	 */
	void updateDownloadRecord(@Param("sql")String sql);
}
