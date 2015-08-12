package com.njyb.gbdbase.model.alldb.commonrightlibrary;

import java.io.Serializable;

import com.njyb.gbdbase.model.personalcenter.CommonTotalModel;

/**
 * 全库实体bean
 * @author WangBo
 *
 */
public class AllDBModel implements Serializable {
	private static final long serialVersionUID = 1L;
	// 日期
	private String date;
	// 重量
	private double weight;
	// 国家
	private String country;
	// 抵达港
	private String endport;
	// 单价
	private double unitprice;
	// 起运港
	private String startport;
	// 海关城市
	private String customscity;
	// 数量
	private double quantity;
	// 海关编码
	private String hscode;
	// 产品描述
	private String goodsdescription;
	// 进口
	private String importer;
	// 出口
	private String exporter;
	// 主键id
	private Integer id;
	// 进出口类型
	private String iexport;
	// 原产国
	private String orgincountry;
	// 金额
	private double totalprice;
	// 排序字段
	private String sortKey;

	// 第一列显示的字段
	private String columName;

	// 贸易次数
	private double countNum;

	public String getColumName() {
		return columName;
	}

	public void setColumName(String columName) {
		this.columName = columName;
	}

	public double getCountNum() {
		return countNum;
	}

	public void setCountNum(double countNum) {
		this.countNum = countNum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEndport() {
		return endport;
	}

	public void setEndport(String endport) {
		this.endport = endport;
	}

	public double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}

	public String getStartport() {
		return startport;
	}

	public void setStartport(String startport) {
		this.startport = startport;
	}

	public String getCustomscity() {
		return customscity;
	}

	public void setCustomscity(String customscity) {
		this.customscity = customscity;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getHscode() {
		return hscode;
	}

	public void setHscode(String hscode) {
		this.hscode = hscode;
	}

	public String getGoodsdescription() {
		return goodsdescription;
	}

	public void setGoodsdescription(String goodsdescription) {
		this.goodsdescription = goodsdescription;
	}

	public String getImporter() {
		return importer;
	}

	public void setImporter(String importer) {
		this.importer = importer;
	}

	public String getExporter() {
		return exporter;
	}

	public void setExporter(String exporter) {
		this.exporter = exporter;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIexport() {
		return iexport;
	}

	public void setIexport(String iexport) {
		this.iexport = iexport;
	}

	public String getOrgincountry() {
		return orgincountry;
	}

	public void setOrgincountry(String orgincountry) {
		this.orgincountry = orgincountry;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public String getSortKey() {
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public AllDBModel() {
		super();
	}

	public AllDBModel(String sortKey) {
		super();
		this.sortKey = sortKey;
	}

}
