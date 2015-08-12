package com.njyb.gbdbase.model.authcenter;

import java.io.Serializable;
/**
 * 按次收费权限实体类
 * @author 洪皓
 * 2015年3月27日
 */
public class CountRightModel  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//权限主键
	private int rid;
	
	//用户ID
	private int userId;
	 
	// 总数量
	private int totalNum;
	
	// 剩余数量
	private int remainnum;

	public CountRightModel(){
		
	}
	
	public CountRightModel(int rid, int userId, int totalNum,
			int remainnum) {
		super();
		this.rid = rid;
		this.userId = userId;
		this.totalNum = totalNum;
		this.remainnum = remainnum;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getRemainnum() {
		return remainnum;
	}

	public void setRemainnum(int remainnum) {
		this.remainnum = remainnum;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CountRightModel [rid=" + rid + ", userId=" + userId
				+ ", totalNum=" + totalNum + ", remainnum=" + remainnum + "]";
	}
}
