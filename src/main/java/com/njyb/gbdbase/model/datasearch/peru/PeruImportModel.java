package com.njyb.gbdbase.model.datasearch.peru;
import java.io.Serializable;

@SuppressWarnings("serial")
public class PeruImportModel implements Serializable {

	/**
	 * 
	 */
	private Integer peruImportId;

	/**
	 * 日期
	 */
	private String date;

	/**
	 * 日
	 */
	private String day;

	/**
	 * 月
	 */
	private String month;

	/**
	 * 年
	 */
	private String year;

	/**
	 * 编号
	 */
	private String controlId;

	/**
	 * 
	 */
	private String serie;

	/**
	 * 海关城市
	 */
	private String customs;

	/**
	 * 进口商编号
	 */
	private String importerId;

	/**
	 * 进口商
	 */
	private String importer;

	/**
	 * 进口商地址
	 */
	private String importerAddress;

	/**
	 * 进口商所在城市
	 */
	private String importerCity;

	/**
	 * 进口商所在国家/州
	 */
	private String importerState;

	/**
	 * 进口商所在区(县)
	 */
	private String importerDistrict;

	/**
	 * 进口商电话
	 */
	private String importerTel;

	/**
	 * 进口商传真
	 */
	private String importerFax;

	/**
	 * 运输方式
	 */
	private String transType;

	/**
	 * 银行
	 */
	private String bank;

	/**
	 * 原产国
	 */
	private String originCountry;

	/**
	 * 销售国
	 */
	private String salesCountry;

	/**
	 * 起运港(ORIGIN_PORT)
	 */
	private String startPort;

	/**
	 * 海关编码(HS_CODE)
	 */
	private String hscode;

	/**
	 * HS章注（HS_CODE_DESC）
	 */
	private String hsDesc;

	/**
	 * 产品描述(PRODUCT_DESC)
	 */
	private String goodsDesc;

	/**
	 * 规格型号
	 */
	private String varietey;

	/**
	 * 
	 */
	private String desc1;

	/**
	 * 
	 */
	private String desc2;

	/**
	 * 
	 */
	private String desc3;

	/**
	 * 
	 */
	private String desc4;

	/**
	 * 生产年份
	 */
	private String manufactureYear;

	/**
	 * FOB金额(FOB)
	 */
	private Double fobValue;

	/**
	 * 运费
	 */
	private Double freight;

	/**
	 * 保险费
	 */
	private Double insurance;

	/**
	 * CIF金额(CIF)
	 */
	private Double cifValue;

	/**
	 * 从价税
	 */
	private Double adValorem;

	/**
	 * 地方税
	 */
	private Double localTax;

	/**
	 * 净重
	 */
	private Double netWeight;

	/**
	 * 毛重
	 */
	private Double grossWeight;

	/**
	 * 数量
	 */
	private Double quantity;

	/**
	 * 数量单位
	 */
	private String unitOfQuantity;

	/**
	 * CIF单价
	 */
	private Double cifUnit;

	/**
	 * 贸易量
	 */
	private Double commercialQuantity;

	/**
	 * 贸易量单位(UNIT_OF_COMMERCIAL_QUANTITY)
	 */
	private String commercialQuantityUnit;

	/**
	 * 包装类型(TYPE_OF_PACKAGE)
	 */
	private String packageType;

	/**
	 * 件数
	 */
	private String packages;

	/**
	 * 商品状态
	 */
	private String merchandiseState;

	/**
	 * 出口商，供应商(SUPPLIER_NAME)
	 */
	private String exporter;

	/**
	 * 海关代理
	 */
	private String customsAgent;

	/**
	 * 运输公司
	 */
	private String transCorp;

	/**
	 * 成交方式
	 */
	private String incoterm;

	private Double num = 1.0;

	public Integer getPeruImportId() {
		return peruImportId;
	}

	public void setPeruImportId(Integer peruImportId) {
		this.peruImportId = peruImportId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getControlId() {
		return controlId;
	}

	public void setControlId(String controlId) {
		this.controlId = controlId;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getCustoms() {
		return customs;
	}

	public void setCustoms(String customs) {
		this.customs = customs;
	}

	public String getImporterId() {
		return importerId;
	}

	public void setImporterId(String importerId) {
		this.importerId = importerId;
	}

	public String getImporter() {
		return importer;
	}

	public void setImporter(String importer) {
		this.importer = importer;
	}

	public String getImporterAddress() {
		return importerAddress;
	}

	public void setImporterAddress(String importerAddress) {
		this.importerAddress = importerAddress;
	}

	public String getImporterCity() {
		return importerCity;
	}

	public void setImporterCity(String importerCity) {
		this.importerCity = importerCity;
	}

	public String getImporterState() {
		return importerState;
	}

	public void setImporterState(String importerState) {
		this.importerState = importerState;
	}

	public String getImporterDistrict() {
		return importerDistrict;
	}

	public void setImporterDistrict(String importerDistrict) {
		this.importerDistrict = importerDistrict;
	}

	public String getImporterTel() {
		return importerTel;
	}

	public void setImporterTel(String importerTel) {
		this.importerTel = importerTel;
	}

	public String getImporterFax() {
		return importerFax;
	}

	public void setImporterFax(String importerFax) {
		this.importerFax = importerFax;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getSalesCountry() {
		return salesCountry;
	}

	public void setSalesCountry(String salesCountry) {
		this.salesCountry = salesCountry;
	}

	public String getStartPort() {
		return startPort;
	}

	public void setStartPort(String startPort) {
		this.startPort = startPort;
	}

	public String getHscode() {
		return hscode;
	}

	public void setHscode(String hscode) {
		this.hscode = hscode;
	}

	public String getHsDesc() {
		return hsDesc;
	}

	public void setHsDesc(String hsDesc) {
		this.hsDesc = hsDesc;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getVarietey() {
		return varietey;
	}

	public void setVarietey(String varietey) {
		this.varietey = varietey;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getDesc3() {
		return desc3;
	}

	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}

	public String getDesc4() {
		return desc4;
	}

	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}

	public String getManufactureYear() {
		return manufactureYear;
	}

	public void setManufactureYear(String manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public Double getFobValue() {
		return fobValue;
	}

	public void setFobValue(Double fobValue) {
		this.fobValue = fobValue;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getInsurance() {
		return insurance;
	}

	public void setInsurance(Double insurance) {
		this.insurance = insurance;
	}

	public Double getCifValue() {
		return cifValue;
	}

	public void setCifValue(Double cifValue) {
		this.cifValue = cifValue;
	}

	public Double getAdValorem() {
		return adValorem;
	}

	public void setAdValorem(Double adValorem) {
		this.adValorem = adValorem;
	}

	public Double getLocalTax() {
		return localTax;
	}

	public void setLocalTax(Double localTax) {
		this.localTax = localTax;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getUnitOfQuantity() {
		return unitOfQuantity;
	}

	public void setUnitOfQuantity(String unitOfQuantity) {
		this.unitOfQuantity = unitOfQuantity;
	}

	public Double getCifUnit() {
		return cifUnit;
	}

	public void setCifUnit(Double cifUnit) {
		this.cifUnit = cifUnit;
	}

	public Double getCommercialQuantity() {
		return commercialQuantity;
	}

	public void setCommercialQuantity(Double commercialQuantity) {
		this.commercialQuantity = commercialQuantity;
	}

	public String getCommercialQuantityUnit() {
		return commercialQuantityUnit;
	}

	public void setCommercialQuantityUnit(String commercialQuantityUnit) {
		this.commercialQuantityUnit = commercialQuantityUnit;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public String getMerchandiseState() {
		return merchandiseState;
	}

	public void setMerchandiseState(String merchandiseState) {
		this.merchandiseState = merchandiseState;
	}

	public String getExporter() {
		return exporter;
	}

	public void setExporter(String exporter) {
		this.exporter = exporter;
	}

	public String getCustomsAgent() {
		return customsAgent;
	}

	public void setCustomsAgent(String customsAgent) {
		this.customsAgent = customsAgent;
	}

	public String getTransCorp() {
		return transCorp;
	}

	public void setTransCorp(String transCorp) {
		this.transCorp = transCorp;
	}

	public String getIncoterm() {
		return incoterm;
	}

	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
}
