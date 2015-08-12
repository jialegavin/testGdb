package com.njyb.gbdbase.model.personalcenter.email;

import java.io.Serializable;
import java.util.List;
/**
 * 邮件发送
 * @author WangBo
 */
public class MailModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 发送人地址
	 */
	private String fromAddress;
	/**
	 * 接收人地址，可以有多个，每个地址以";"隔开
	 */
	private String toAddreress;
	/**
	 * 邮件主题
	 */
	private String subject;
	/**
	 * 附件
	 */
	private List<String> attachFileNames;

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddreress() {
		return toAddreress;
	}

	public void setToAddreress(String toAddreress) {
		this.toAddreress = toAddreress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<String> getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(List<String> attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
}