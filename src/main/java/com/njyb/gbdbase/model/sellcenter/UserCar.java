package com.njyb.gbdbase.model.sellcenter;
/**
 * 购物车
 * @author chenhu
 * 2015年5月6日
 */
public class UserCar {
  //购物车ID
  private int carId;
  //购物车状态
  private boolean status;
  //产品类型
  private String type;
  //用户ID
  private int userId;
  //产品描述
  private String desc;
  //价格
  private double price;
public int getCarId() {
	return carId;
}
public void setCarId(int carId) {
	this.carId = carId;
}
public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
}
