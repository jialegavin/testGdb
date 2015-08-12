package com.njyb.gbdbase.service.personalcenter.email;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.njyb.gbdbase.model.personalcenter.email.MailModel;


/**
 * 邮件发送实现类
 * @author WangBo
 */
@Service
public class EmailService implements IEmailService {
	
	//log记录日志
	private static final Logger log = Logger.getLogger(IEmailService.class);
	
	/**
	 * 提供发送邮件的功能
	 */
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private MimeMessage mimeMessage;
	@Autowired
	private VelocityEngine velocityEngine;
	
	/**
	 * 发送HTML格式，带附件的邮件
	 * mail:邮件model
	 * model:模板填充内容
	 * templateLocation:模板位置
	 * @throws UnsupportedEncodingException 
	 */
	@Override
	public boolean sendAttachMail(MailModel mail,Map<String,Object>model,String templateLocation){
		try {
			//构建MimeMessage对象
			MimeMessageHelper mailMessage = new MimeMessageHelper(this.mimeMessage, true, "UTF-8");
			//设置邮件消息的发送者
			mailMessage.setFrom(mail.getFromAddress());
			//设置邮件的发送主题
			mailMessage.setSubject(mail.getSubject());
			//设置邮件的发送时间
			mailMessage.setSentDate(new Date());
			//获取模板文件内容 并且制定为html格式
			//设置发送的模板文件
			String text = VelocityEngineUtils.mergeTemplateIntoString(
		               velocityEngine, templateLocation,"UTF-8", model);
			// 设置邮件的发送内容 true:是否可以发送HTML形式的文本
			mailMessage.setText(text, true);
			//得到要发送的地址数组
			String[] toAddress = mail.getToAddreress().split(";");
			for(int i = 0;i<toAddress.length;i++){
				//设置要发送的邮件地址
				mailMessage.setTo(toAddress[i]);
				if(mail.getAttachFileNames() != null){
					for(String fileName:mail.getAttachFileNames()){
						File file = new File(fileName);
						//循环添加发送的附件 MimeUtility.encodeWord:解决乱码问题
						mailMessage.addAttachment(MimeUtility.encodeWord(file.getName()),file);
					}
				}
				//发送邮件
				this.javaMailSender.send(mimeMessage);
				log.info("发送成功！");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
