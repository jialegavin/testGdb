package com.njyb.gbdbase.model.alldb.projectAnalyze;

import java.io.Serializable;
import java.util.Date;

import com.njyb.gbdbase.model.personalcenter.CommonTotalModel;

/**
 * 产品标签{全库分析}
 * 
 * @author WangBo 2015年4月20日 ProjectAnalyzeModel.java
 */
public class ProjectAnalyzeModel extends CommonTotalModel implements Serializable {

	private static final long serialVersionUID = 1L;

	// 产品编号
	private Integer productId;
	// 用户编号
	private Integer userId;
	// 产品名称
	private String productName;
	// 海关编码
	private String hscode;
	// 提单号
	private String billNumber;
	// 附加码
	private String verifycode;
	// 删除Id
	private String deleteId;
	// 添加时间
	private Date addTime;
	// 不可操作 删除,修改
	private int isOperate;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getHscode() {
		return hscode;
	}

	public void setHscode(String hscode) {
		this.hscode = hscode;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(String deleteId) {
		this.deleteId = deleteId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public int getIsOperate() {
		return isOperate;
	}

	public void setIsOperate(int isOperate) {
		this.isOperate = isOperate;
	}
}