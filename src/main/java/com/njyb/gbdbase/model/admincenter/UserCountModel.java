package com.njyb.gbdbase.model.admincenter;

import java.io.Serializable;
/**
 * 按次用户model
 * @author WangBo
 * 2015年6月24日
 * UserCountModel.java
 */
public class UserCountModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userId;			// userId
	
	private String loginName;		// 用户名
	
	private int totalMoney;		// 总金额
	
	private String doMain;			// 域名
	
	private String email;			// email
	
	private int totalNum;			// 总条数
	
	private int remainNum;			// 查看次数
	
	private int residueNum;			// 剩余次数
	
	private int residueMoney;	// 剩余金额
	
	private int consumptionMoney;	// 消费金额

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getTotalNum() {
		return totalNum;
	}


	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}


	public int getRemainNum() {
		return remainNum;
	}


	public void setRemainNum(int remainNum) {
		this.remainNum = remainNum;
	}


	public int getResidueNum() {
		return residueNum;
	}


	public void setResidueNum(int residueNum) {
		this.residueNum = residueNum;
	}


	public int getResidueMoney() {
		return residueMoney;
	}


	public void setResidueMoney(int residueMoney) {
		this.residueMoney = residueMoney;
	}

	public int getConsumptionMoney() {
		return consumptionMoney;
	}

	public void setConsumptionMoney(int consumptionMoney) {
		this.consumptionMoney = consumptionMoney;
	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getDoMain() {
		return doMain;
	}

	public void setDoMain(String doMain) {
		this.doMain = doMain;
	}

	@Override
	public String toString() {
		return "UserCountModel [userId=" + userId + ", loginName=" + loginName
				+ ", totalMoney=" + totalMoney + ", email=" + email
				+ ", totalNum=" + totalNum + ", remainNum=" + remainNum
				+ ", residueNum=" + residueNum + ", residueMoney="
				+ residueMoney + ", consumptionMoney=" + consumptionMoney + "]";
	}
}