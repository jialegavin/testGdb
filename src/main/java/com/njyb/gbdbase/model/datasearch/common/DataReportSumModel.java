package com.njyb.gbdbase.model.datasearch.common;

import java.io.Serializable;

/**
 * 报表数据汇总模型
 * @author 贾红平
 *
 */
public class DataReportSumModel implements Serializable{
	/**
	 * 保证序列化版本一致性
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 基本信息
	 */
	//进口商
	private String importer;
	//企业单位
	private String company_name;
	//收发货地
	private String product_place;
	//出口关区
	private String port;
	//出口商
	private String exporter;
	//原产国
	private String origin_country;
	//目的国
	private String dest_country;
	//起运港
	private String start_port;
	//抵达港
	private String end_port;
	//日期
	private String date;
	//通知人
	private String notifier;
	//制造商
	private String manufacture;
	//海关编码
	private String hscode;
	//产品描述
	private String goodsDesc;
	
	//海关城市
	private String customs;
	/**
	 * 辅助信息
	 */
	//采购商数量
	private int importerCount=1;
	//供应商数量
	private int exporterCount=1;
	/**
	 * 汇总字段
	 */
	//金额
	private double tradeMoney=0.0;
	//重量
	private double tradeWeight=0.0;
	//数量
	private double tradeQuantity=0.0;
	//包装件数
	private double tradePackage=0.0;
	//尺寸
	private double tradeSize=0.0;
	//次数
	private int tradeCount=1;
	//金额比率
	private double moneyPercentage;
	//重量比率
	private double weightPercentage;
	//数量比率
	private double quantityPercentage;
	//贸易次数比率
	private double countPercentage;
	//件数比率
	private double packagePercentage;
	//尺寸比率
	private double sizePercentage;
	//数据表格隐藏的列名(值包含选项卡模块（cp_trade）,
	//报告类型(qs),当前汇总的字段(importer列的值)) 通过洪分隔
	//如:cp_trade洪qs洪aaaaaaa
	private String hideColumn;
	
	private String country;
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImporter() {
		return importer;
	}
	public void setImporter(String importer) {
		this.importer = importer;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getProduct_place() {
		return product_place;
	}
	public void setProduct_place(String product_place) {
		this.product_place = product_place;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getExporter() {
		return exporter;
	}
	public void setExporter(String exporter) {
		this.exporter = exporter;
	}
	public String getOrigin_country() {
		return origin_country;
	}
	public void setOrigin_country(String origin_country) {
		this.origin_country = origin_country;
	}
	public String getDest_country() {
		return dest_country;
	}
	public void setDest_country(String dest_country) {
		this.dest_country = dest_country;
	}
	public String getStart_port() {
		return start_port;
	}
	public void setStart_port(String start_port) {
		this.start_port = start_port;
	}
	public String getEnd_port() {
		return end_port;
	}
	public void setEnd_port(String end_port) {
		this.end_port = end_port;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNotifier() {
		return notifier;
	}
	public void setNotifier(String notifier) {
		this.notifier = notifier;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getHscode() {
		return hscode;
	}
	public void setHscode(String hscode) {
		this.hscode = hscode;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getCustoms() {
		return customs;
	}
	public void setCustoms(String customs) {
		this.customs = customs;
	}
	public int getImporterCount() {
		return importerCount;
	}
	public void setImporterCount(int importerCount) {
		this.importerCount = importerCount;
	}
	public int getExporterCount() {
		return exporterCount;
	}
	public void setExporterCount(int exporterCount) {
		this.exporterCount = exporterCount;
	}
	public double getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public double getTradeWeight() {
		return tradeWeight;
	}
	public void setTradeWeight(double tradeWeight) {
		this.tradeWeight = tradeWeight;
	}
	public double getTradeQuantity() {
		return tradeQuantity;
	}
	public void setTradeQuantity(double tradeQuantity) {
		this.tradeQuantity = tradeQuantity;
	}
	public double getTradePackage() {
		return tradePackage;
	}
	public void setTradePackage(double tradePackage) {
		this.tradePackage = tradePackage;
	}
	public double getTradeSize() {
		return tradeSize;
	}
	public void setTradeSize(double tradeSize) {
		this.tradeSize = tradeSize;
	}
	public int getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}
	public DataReportSumModel() {
		super();
	}
	public double getMoneyPercentage() {
		return moneyPercentage;
	}
	public void setMoneyPercentage(double moneyPercentage) {
		this.moneyPercentage = moneyPercentage;
	}
	public double getWeightPercentage() {
		return weightPercentage;
	}
	public void setWeightPercentage(double weightPercentage) {
		this.weightPercentage = weightPercentage;
	}
	public double getQuantityPercentage() {
		return quantityPercentage;
	}
	public void setQuantityPercentage(double quantityPercentage) {
		this.quantityPercentage = quantityPercentage;
	}
	public String getHideColumn() {
		return hideColumn;
	}
	public void setHideColumn(String hideColumn) {
		this.hideColumn = hideColumn;
	}
	public double getCountPercentage() {
		return countPercentage;
	}
	public void setCountPercentage(double countPercentage) {
		this.countPercentage = countPercentage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public double getPackagePercentage() {
		return packagePercentage;
	}
	public void setPackagePercentage(double packagePercentage) {
		this.packagePercentage = packagePercentage;
	}
	public double getSizePercentage() {
		return sizePercentage;
	}
	public void setSizePercentage(double sizePercentage) {
		this.sizePercentage = sizePercentage;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
