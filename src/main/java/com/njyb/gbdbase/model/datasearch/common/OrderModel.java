package com.njyb.gbdbase.model.datasearch.common;

import java.io.Serializable;

public class OrderModel implements Serializable{
	/**
	 * 保证序列化版本一致性
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 金额
	 */
	public double money;
	/**
	 * 重量:毛重 净重
	 */
	private double weight;
	/**
	 * 数量
	 */
	private double quantity;
	/**
	 * 件数
	 */
	private double packages;
	/**
	 * 日期字段
	 */
	private String date;
	
	public OrderModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderModel(double money, double weight, double quantity,
			double packages, int count, double size,String date) {
		super();
		this.money = money;
		this.weight = weight;
		this.quantity = quantity;
		this.packages = packages;
		this.count = count;
		this.size = size;
		this.date=date;
	}
	public OrderModel(double money, double weight, double quantity,
			double packages, int count, double size) {
		super();
		this.money = money;
		this.weight = weight;
		this.quantity = quantity;
		this.packages = packages;
		this.count = count;
		this.size = size;
	}
	/**
	 * 交易次数
	 */
	private int count;
	/**
	 * 尺寸
	 */
	private double size;
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getPackages() {
		return packages;
	}
	public void setPackages(double packages) {
		this.packages = packages;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
