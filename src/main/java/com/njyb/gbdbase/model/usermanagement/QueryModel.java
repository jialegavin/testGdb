package com.njyb.gbdbase.model.usermanagement;

/**
 * 查询模板
 * @author chenhu
 * 2015年3月26日
 */
public class QueryModel {
	/**
	 * 起始页
	 */
	private Integer startnum;
	/**
	 * 结束页
	 */
	private Integer endNum;
    /**
     * 每页显示数量
     */
	private Integer pageSize;
    /**
     * 当前页码
     */
	private Integer curPage;
	/**
	 * 用户登录名称
	 */
	private String loginName;
	/**
	 * 用户的ID
	 */
	private int    userId;
	/**
	 * 用户关联id
	 */
	private int relaId;
	/**
	 * 系统登录日志
	 */
	private int logId;
	/**
	 * 用户类型
	 * 1、普通用户；2、管理员用户
	 */
	private String userDesc;
	/**
	 * 查询sql
	 */
	private String whereSql;
	/**
	 * 更新sql
	 */
	private String updateSql;
	/**
	 * 更新字段名称
	 */
	private String param;
	/**
	 * 国家名称
	 */
	private String countryName;
	/**
	 * 贸易类型
	 */
	private String ioType;
	/**
	 * Id数组
	 */
	private String[] ids;
	/**
	 * 订单号
	 */
	private String orderNo;
	
	public Integer getStartnum() {
		return (this.curPage-1)*this.pageSize;
	}
	public void setStartnum(Integer startnum) {
		this.startnum =startnum ;
	}

	public Integer getEndNum() {
		return endNum;
	}

	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getWhereSql() {
		return whereSql;
	}

	public void setWhereSql(String whereSql) {
		this.whereSql = whereSql;
	}

	public String getUpdateSql() {
		return updateSql;
	}

	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	public String getIoType() {
		return ioType;
	}
	public void setIoType(String ioType) {
		this.ioType = ioType;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getRelaId() {
		return relaId;
	}
	public void setRelaId(int relaId) {
		this.relaId = relaId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


}
