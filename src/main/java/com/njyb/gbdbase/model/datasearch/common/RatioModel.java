package com.njyb.gbdbase.model.datasearch.common;

import java.io.Serializable;

public class RatioModel implements Serializable {
	/**
	 * 保证序列化版本一致性
	 */
	private static final long serialVersionUID =1l;
	/**
	 * 金额
	 */
	private double money;
	/**
	 * 金额比率
	 */
	private double moneyRatio;
	/**
	 * 重量
	 */
	private double weight;
	/**
	 * 重量比率
	 */
	private double weightRatio;
	/**
	 * 数量
	 */
	private double quantity;
	/**
	 * 数量比率
	 */
	private double quantityRatio;
	/**
	 * 件数
	 */
	private double packages;
	/**
	 * 件数比率
	 */
	private double packagesRatio;
	/**
	 * 尺寸
	 */
	private double size;
	/**
	 * 尺寸比率
	 */
	private double sizeRatio;
	/**
	 * 交易记录次数
	 */
	private int count;
	/**
	 * 次数比率
	 */
	private double countRatio;
	/**
	 * 日期
	 * @return
	 */
	private String date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getMoneyRatio() {
		return moneyRatio;
	}
	public void setMoneyRatio(double moneyRatio) {
		this.moneyRatio = moneyRatio;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getWeightRatio() {
		return weightRatio;
	}
	public void setWeightRatio(double weightRatio) {
		this.weightRatio = weightRatio;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getQuantityRatio() {
		return quantityRatio;
	}
	public void setQuantityRatio(double quantityRatio) {
		this.quantityRatio = quantityRatio;
	}
	public double getPackages() {
		return packages;
	}
	public void setPackages(double packages) {
		this.packages = packages;
	}
	public double getPackagesRatio() {
		return packagesRatio;
	}
	public void setPackagesRatio(double packagesRatio) {
		this.packagesRatio = packagesRatio;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public double getSizeRatio() {
		return sizeRatio;
	}
	public void setSizeRatio(double sizeRatio) {
		this.sizeRatio = sizeRatio;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getCountRatio() {
		return countRatio;
	}
	public void setCountRatio(double countRatio) {
		this.countRatio = countRatio;
	}
	
}
