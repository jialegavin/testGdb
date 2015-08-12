package com.njyb.gbdbase.service.personalcenter.email;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.njyb.gbdbase.model.personalcenter.email.MailModel;

/**
 * 发送邮件接口
 * 
 * @author WangBo
 * 
 */
public interface IEmailService {
	
	/**
	 * 发送HTML格式，带附件的邮件
	 * mail:邮件model
	 * model:模板填充内容
	 * templateLocation:模板位置
	 * @throws UnsupportedEncodingException 
	 */
	public boolean sendAttachMail(MailModel mail, Map<String, Object> model,
			String templateLocation);
}