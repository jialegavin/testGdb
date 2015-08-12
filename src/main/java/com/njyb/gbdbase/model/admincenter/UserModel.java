package com.njyb.gbdbase.model.admincenter;

import java.io.Serializable;


/**
 * 用户表的实体类
 * @author honghao
 * 2015-03-06
 */
public class UserModel  implements Serializable{

	private static final long serialVersionUID = 1L;
	//主账号用户id
	private int userId;
	//登录的用户名
	private String loginName;
	// 用户登录密码
	private String loginPassword;
	// 用户注册邮箱
	private String email;
	// 用户联系号码
	private String phone;
	// 用户qq号
	private String qq;
	// 用户真实姓名
	private String userName;
	// 公司所在地址
	private String address;
	//单位名
	private String unitName;
	//国家
	private String country;
	//省份
	private String province;
	//城市
	private String city;
	//邮编
	private String postalNum;
	// 用户注册时间
	private String registertime;
	// 用户最后登录的时间
	private String endLoginTime;
	// 用户的购买时间
	private String beginTime;
	// 用户的到期时间
	private String endTime;
	//用户身份描述
	private String userDesc;
	//允许子账户授权的次数
	private int sonAccountTotal;
	//是否激活 默认是0未激活
	private boolean isActivated;
	//已经添加的子账号的数量
	private int sonAccountNum;
	//开启子账号服务 1代表已经开启 0代表开启
	private boolean openService;
	//账号是否已经被占时锁定1代表锁定,0代表未锁定
	private boolean isLocked;
	//用户登录密码错误的次数
	private int loginCount;
	//账号是否已经被禁用1代表禁用,0代表未禁用
	private boolean isDisable;
	//sql语句
	private String sql;
    //指向上一级
	private int relaId;
	//主账户
	private int mainId;
	//电话
	private String tel;
	//自动登录
	private String autoLogin;
	//firstName
	private String firstName;
	//lastName
	private String lastName;
	public UserModel() 
	{
		
	}

	public UserModel(int userId, String loginName, String loginPassword,
			String email, String phone, String qq, String userName,
			String address, String registertime, String endLoginTime,
			String beginTime, String endTime, String userDesc,
			int sonAccountTotal, boolean isActivated, int sonAccountNum,
			boolean openService, boolean isLocked, int loginCount,
			boolean isDisable, String sql,int relaId,int mainId, String firstName,String lastName) 
	{
		super();
		this.userId = userId;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.email = email;
		this.phone = phone;
		this.qq = qq;
		this.userName = userName;
		this.address = address;
		this.registertime = registertime;
		this.endLoginTime = endLoginTime;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.userDesc = userDesc;
		this.sonAccountTotal = sonAccountTotal;
		this.isActivated = isActivated;
		this.sonAccountNum = sonAccountNum;
		this.openService = openService;
		this.isLocked = isLocked;
		this.loginCount = loginCount;
		this.isDisable = isDisable;
		this.sql = sql;
		this.relaId=relaId;
		this.mainId=mainId;
		this.firstName=firstName;
		this.lastName=lastName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}

	public String getEndLoginTime() {
		return endLoginTime;
	}

	public void setEndLoginTime(String endLoginTime) {
		this.endLoginTime = endLoginTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalNum() {
		return postalNum;
	}

	public void setPostalNum(String postalNum) {
		this.postalNum = postalNum;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public int getSonAccountTotal() {
		return sonAccountTotal;
	}

	public void setSonAccountTotal(int sonAccountTotal) {
		this.sonAccountTotal = sonAccountTotal;
	}

	public boolean getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public int getSonAccountNum() {
		return sonAccountNum;
	}

	public void setSonAccountNum(int sonAccountNum) {
		this.sonAccountNum = sonAccountNum;
	}

	public boolean getOpenService() {
		return openService;
	}

	public void setOpenService(boolean openService) {
		this.openService = openService;
	}

	public boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public boolean getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public int getRelaId() {
		return relaId;
	}

	public void setRelaId(int relaId) {
		this.relaId = relaId;
	}

	public int getMainId() {
		return mainId;
	}

	public void setMainId(int mainId) {
		this.mainId = mainId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName=lastName;
	}
}
