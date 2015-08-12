package com.njyb.gbdbase.dao.personalcenter.password;

import java.util.Map;

import com.njyb.gbdbas.mybatis.annotation.MyBatisReposity;
import com.njyb.gbdbase.model.personalcenter.password.PasswordCommon;

/**
 * 用户密码 修改 与 找回
 * @author WangBo
 */
@MyBatisReposity
public interface IUserPasswordDao {
	
	/**
	 * 根据名称查找是否存在对象
	 * @param loginName
	 * @return Is not null Or  Is null {EmailModel}
	 */
	public PasswordCommon queryEmailModelByUserId(int userId);
	
	/**
	 * 更改邮件根据邮箱Id
	 * @param EmailModel : 邮件模型
	 * @return 1 : 更改成功 2 : 更改失败
	 */
	public int updateEmailModelByEmailId(PasswordCommon EmailModel);
	
	/**
	 * 用于用户插入到邮件表中
	 * @param EmailModel
	 */
	public void addEmailModelByModel(PasswordCommon EmailModel);
	
	/**
	 * 判断邮件的连接地址的sId是否在半个小时之内,如果在:生效,如果没有:失效
	 * @param param : Map参数
	 * @return true : 生效 , false : 失效
	 */
	public PasswordCommon queryEmailByParam(Map<String,Object> paramMap);
	/**
	 * 根据sid删除发送的邮件
	 * @param sid
	 */
	void deleteEmailBySid(String sid);
}
