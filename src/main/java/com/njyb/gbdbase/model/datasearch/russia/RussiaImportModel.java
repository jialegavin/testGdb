package com.njyb.gbdbase.model.datasearch.russia;
import java.io.Serializable;

@SuppressWarnings("serial")
public class RussiaImportModel implements Serializable {

	/**
	 * 主键编号
	 */
	private Integer russiaImportId;

	/**
	 * 外贸统计帐号标记(STAT)
	 */
	private String accountSign;

	/**
	 * 报关单号(Declaration Number)
	 */
	private String declarationNo;

	/**
	 * 货物序号(Cargo Number on GTD)
	 */
	private String goodsNo;

	/**
	 * 
	 */
	private String g07;

	/**
	 * 海关记录内部编号(Customs Identity Code in Automated Information System)
	 */
	private String customsIdentityCode;

	/**
	 * 登记时间(Registration Date in Automated Information System)
	 */
	private String date;

	/**
	 * 海关记录序号(Declaration Serial Number)
	 */
	private String customsSerialNo;

	/**
	 * 贸易类型(Direction of Cargo Movement)
	 */
	private String tradeType;

	/**
	 * 海关模式(Code of Basic Customs Mode)
	 */
	private String basicCustomsMode;

	/**
	 * 申报的附加标志(Additional Sign of Declaration)
	 */
	private String declarationAdditionSign;

	/**
	 * 发货人SOATO编码(Shipper Main State Registration Number)
	 */
	private String exporterSoatoCode;

	/**
	 * 发货人纳税号(Shipper Taxpying Identification Number)
	 */
	private String exporterTaxId;

	/**
	 * 发货人（Foreign Shipper Name）
	 */
	private String exporter;

	/**
	 * 发货人地址(G023)
	 */
	private String exporterAddress;

	/**
	 * 
	 */
	private String g024b;

	/**
	 * 
	 */
	private String g05;

	/**
	 * 
	 */
	private Double g06;

	/**
	 * 收货人SOATO编码（Consignee Main State Registration Number）
	 */
	private String importerSoatoCode;

	/**
	 * 收货人纳税号(Consignee Taxpaying Identification Number)
	 */
	private String importerTaxId;

	/**
	 * 进口商(Russian Consignee Name)
	 */
	private String importer;

	/**
	 * 进口商地址(G083)
	 */
	private String importerAddress;

	/**
	 * 
	 */
	private String g084b;

	/**
	 * 付款人SOATO编号(Payee Main State Registration Number)
	 */
	private String payeeSoatoCode;

	/**
	 * 付款人纳税号(Payee Taxpaying Identification Number)
	 */
	private String payeeTaxId;

	/**
	 * 付款人(Russian Payee Name)
	 */
	private String payee;

	/**
	 * 银行地址(G093)
	 */
	private String bankAddress;

	/**
	 * 
	 */
	private String g094b;

	/**
	 * 申报人SOATO编号(Customs Applicant Main State Registration Number)
	 */
	private String appliantSoatoCode;

	/**
	 * 申报人纳税号(Customs Applicant Taxpying Identification Number)
	 */
	private String appliantTaxId;

	/**
	 * 申报人(Customs Applicant Name)
	 */
	private String appliant;

	/**
	 * 
	 */
	private String g143;

	/**
	 * 附加表序号(Additional Sheet Serial Number)
	 */
	private String additionalSheetNo;

	/**
	 * 贸易国代码(Trading Country Code)
	 */
	private String tradeCountryCode;

	/**
	 * 海关价值的货币类型(Digital Code of Customs Value Currency)
	 */
	private String customsCurrency;

	/**
	 * 
	 */
	private Double g12;

	/**
	 * 起运国(Departure Country)
	 */
	private String startCountry;

	/**
	 * 起运国代码(G15A)
	 */
	private String startCountryCode;

	/**
	 * 原产国(Origin Country)
	 */
	private String originCountry;

	/**
	 * 目的国代码(G17A)
	 */
	private String destCountryCode;

	/**
	 * 目的国(G17B)
	 */
	private String destCountry;

	/**
	 * 
	 */
	private String g18;

	/**
	 * 是否集装箱运输(Container Transportation Record)
	 */
	private String containerTranFlag;

	/**
	 * g21
	 */
	private String g21;

	/**
	 * 贸易术语,成交方式(Incoterms)
	 */
	private String incoterms;

	/**
	 * 交货地点(Cargo Delivery Point)
	 */
	private String placeDelivery;

	/**
	 * 合同结算货币（Currency Character Code of Contract Prices）
	 */
	private String contactCurrency;

	/**
	 * 
	 */
	private String g221c;

	/**
	 * 
	 */
	private Double g222;

	/**
	 * 货币兑换日期(Date of Application Currency)
	 */
	private String currencyAppliedDate;

	/**
	 * g25
	 */
	private String g25;

	/**
	 * g26
	 */
	private String g26;

	/**
	 * 装卸地址(Place of Loading or Unloading)
	 */
	private String addressLoad;

	/**
	 * 仓库收货人(Warehouse Consignee)
	 */
	private String warehouseConsignee;

	/**
	 * 免费仓库证书号（Licenses Number of Free Warehouse）
	 */
	private String freeWarehouseNo;

	/**
	 * 证书类型（Type of Document for License or Certificate）
	 */
	private String certificateType;

	/**
	 * 证书发放日期(Issuance Date of  Certificates or Licenses)
	 */
	private String certificateDate;

	/**
	 * 仓储的海关编码或仓库所有者(Customs Authority Code or Warehouse Owner for Temporary Storage)
	 */
	private String tempStorageInfo;

	/**
	 * 俄罗斯企业名称（All-Russian Nomenclature of Business and Organization）
	 */
	private String businessName;

	/**
	 * 银行简称(Abbreviation of Bank Name)
	 */
	private String bankShort;

	/**
	 * 
	 */
	private String g29;

	/**
	 * 信息类型(Type of Information)
	 */
	private String infoType;

	/**
	 * 文档类型(Type of Document)
	 */
	private String documentType;

	/**
	 * 证书编号(Number of Certificates)
	 */
	private String certificateNo;

	/**
	 * 铁路车站或仓库收货人的名称(Name of Railway Stations or Warehouse Consignee)
	 */
	private String stationWarehousr;

	/**
	 * 仓库收货人的海关编码(Customs Authority Code of Warehouse Consignee)
	 */
	private String warehousrCode;

	/**
	 * 报关员(Customs Declarant)
	 */
	private String declarant;

	/**
	 * 申报单位电话(Phone Number)
	 */
	private String phone;

	/**
	 * 报关员岗位(Post of Customs Declarant)
	 */
	private String declarantPost;

	/**
	 * 通关代码(Code of Customs Clearance Completion)
	 */
	private String clearanceCode;

	/**
	 * 货物通关时间(Cargo Release Date)
	 */
	private String cargoReleaseDate;

	/**
	 * 检验员ID号(Personal  Inspector  ID Number)
	 */
	private String inspectorId;

	/**
	 * 产品描述(Detailed Cargo Description)
	 */
	private String goodsDesc;

	/**
	 * 生产商(Producer Name)
	 */
	private String producer;

	/**
	 * 商标（Trade Mark）
	 */
	private String brand;

	/**
	 * 生产国名称(Origin Country Name)
	 */
	private String produceCountry;

	/**
	 * 包装数量(Number of Packages)
	 */
	private Double packages;

	/**
	 * 包装类型和商标(Type of Cago Packages & Goods Marks)
	 */
	private String packageType;

	/**
	 * 货盘数目（Quantity of Pallets）
	 */
	private String quantityPallet;

	/**
	 * 货盘信息(Information on Pallets)
	 */
	private String palletInfo;

	/**
	 * 集装箱数量(Quantity of Container)
	 */
	private Double quantity;

	/**
	 * 数量2(Quantity of Cargo in Secondary Unit of Measure)
	 */
	private Double quantitySecond;

	/**
	 * 单位2(Name of Secondary Unit of Measure)
	 */
	private String measureSecond;

	/**
	 * 数量3(Quantity of  Goods on  Third Unit of Measure)
	 */
	private Double quantityThird;

	/**
	 * 单位3(Name of  Third Units of Measure)
	 */
	private String measureThird;

	/**
	 * 单位3编码(Code of Third Units of Measure)
	 */
	private String codeMeasureThird;

	/**
	 * 数量4(Quantity of  Goods on  Fourth Unit of Measure)
	 */
	private Double quantityFourth;

	/**
	 * 单位4(Name of  Fourth Units of Measure)
	 */
	private String measureFourth;

	/**
	 * 单位4编码(Code of  Fourth Units of Measure)
	 */
	private String codeMeasureFourth;

	/**
	 * 海关编码(Nomenclature)
	 */
	private String hscode;

	/**
	 * 原产国代码(Origin Country Code)
	 */
	private String originCountryCode;

	/**
	 * 毛重kg(Gross Weight Kilos)
	 */
	private Double grossWeight;

	/**
	 * 费用支付选项(Fees Payment Preferences)
	 */
	private String feePay;

	/**
	 * 海关程序代码(Customs Procedure Code)
	 */
	private String customsProcedureCode;

	/**
	 * 净重(Net Weight Kilos)
	 */
	private Double netWeight;

	/**
	 * 配额
	 */
	private String quota;

	/**
	 * 计量单位附加码(Additional Code of UOM)
	 */
	private String uomCode;

	/**
	 * 货物成本(Price of Cargo)
	 */
	private Double cost;

	/**
	 * 海关是否调整货物价值(Customs Value Adjustments)
	 */
	private String adjustFlag;

	/**
	 * 货物估计:卢布(Cargo Customs Value in Roubles)
	 */
	private Double rubValue;

	/**
	 * 海关价值计量方法(Number Method for Determining the Customs Value)
	 */
	private String customsMethod;

	/**
	 * 货物估价:美元(Cargo Statistics Value in US Dollars)
	 */
	private Double cifValue;

	public Integer getRussiaImportId() {
		return russiaImportId;
	}

	public void setRussiaImportId(Integer russiaImportId) {
		this.russiaImportId = russiaImportId;
	}

	public String getAccountSign() {
		return accountSign;
	}

	public void setAccountSign(String accountSign) {
		this.accountSign = accountSign;
	}

	public String getDeclarationNo() {
		return declarationNo;
	}

	public void setDeclarationNo(String declarationNo) {
		this.declarationNo = declarationNo;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getG07() {
		return g07;
	}

	public void setG07(String g07) {
		this.g07 = g07;
	}

	public String getCustomsIdentityCode() {
		return customsIdentityCode;
	}

	public void setCustomsIdentityCode(String customsIdentityCode) {
		this.customsIdentityCode = customsIdentityCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCustomsSerialNo() {
		return customsSerialNo;
	}

	public void setCustomsSerialNo(String customsSerialNo) {
		this.customsSerialNo = customsSerialNo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getBasicCustomsMode() {
		return basicCustomsMode;
	}

	public void setBasicCustomsMode(String basicCustomsMode) {
		this.basicCustomsMode = basicCustomsMode;
	}

	public String getDeclarationAdditionSign() {
		return declarationAdditionSign;
	}

	public void setDeclarationAdditionSign(String declarationAdditionSign) {
		this.declarationAdditionSign = declarationAdditionSign;
	}

	public String getExporterSoatoCode() {
		return exporterSoatoCode;
	}

	public void setExporterSoatoCode(String exporterSoatoCode) {
		this.exporterSoatoCode = exporterSoatoCode;
	}

	public String getExporterTaxId() {
		return exporterTaxId;
	}

	public void setExporterTaxId(String exporterTaxId) {
		this.exporterTaxId = exporterTaxId;
	}

	public String getExporter() {
		return exporter;
	}

	public void setExporter(String exporter) {
		this.exporter = exporter;
	}

	public String getExporterAddress() {
		return exporterAddress;
	}

	public void setExporterAddress(String exporterAddress) {
		this.exporterAddress = exporterAddress;
	}

	public String getG024b() {
		return g024b;
	}

	public void setG024b(String g024b) {
		this.g024b = g024b;
	}

	public String getG05() {
		return g05;
	}

	public void setG05(String g05) {
		this.g05 = g05;
	}

	public Double getG06() {
		return g06;
	}

	public void setG06(Double g06) {
		this.g06 = g06;
	}

	public String getImporterSoatoCode() {
		return importerSoatoCode;
	}

	public void setImporterSoatoCode(String importerSoatoCode) {
		this.importerSoatoCode = importerSoatoCode;
	}

	public String getImporterTaxId() {
		return importerTaxId;
	}

	public void setImporterTaxId(String importerTaxId) {
		this.importerTaxId = importerTaxId;
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

	public String getG084b() {
		return g084b;
	}

	public void setG084b(String g084b) {
		this.g084b = g084b;
	}

	public String getPayeeSoatoCode() {
		return payeeSoatoCode;
	}

	public void setPayeeSoatoCode(String payeeSoatoCode) {
		this.payeeSoatoCode = payeeSoatoCode;
	}

	public String getPayeeTaxId() {
		return payeeTaxId;
	}

	public void setPayeeTaxId(String payeeTaxId) {
		this.payeeTaxId = payeeTaxId;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getG094b() {
		return g094b;
	}

	public void setG094b(String g094b) {
		this.g094b = g094b;
	}

	public String getAppliantSoatoCode() {
		return appliantSoatoCode;
	}

	public void setAppliantSoatoCode(String appliantSoatoCode) {
		this.appliantSoatoCode = appliantSoatoCode;
	}

	public String getAppliantTaxId() {
		return appliantTaxId;
	}

	public void setAppliantTaxId(String appliantTaxId) {
		this.appliantTaxId = appliantTaxId;
	}

	public String getAppliant() {
		return appliant;
	}

	public void setAppliant(String appliant) {
		this.appliant = appliant;
	}

	public String getG143() {
		return g143;
	}

	public void setG143(String g143) {
		this.g143 = g143;
	}

	public String getAdditionalSheetNo() {
		return additionalSheetNo;
	}

	public void setAdditionalSheetNo(String additionalSheetNo) {
		this.additionalSheetNo = additionalSheetNo;
	}

	public String getTradeCountryCode() {
		return tradeCountryCode;
	}

	public void setTradeCountryCode(String tradeCountryCode) {
		this.tradeCountryCode = tradeCountryCode;
	}

	public String getCustomsCurrency() {
		return customsCurrency;
	}

	public void setCustomsCurrency(String customsCurrency) {
		this.customsCurrency = customsCurrency;
	}

	public Double getG12() {
		return g12;
	}

	public void setG12(Double g12) {
		this.g12 = g12;
	}

	public String getStartCountry() {
		return startCountry;
	}

	public void setStartCountry(String startCountry) {
		this.startCountry = startCountry;
	}

	public String getStartCountryCode() {
		return startCountryCode;
	}

	public void setStartCountryCode(String startCountryCode) {
		this.startCountryCode = startCountryCode;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getDestCountryCode() {
		return destCountryCode;
	}

	public void setDestCountryCode(String destCountryCode) {
		this.destCountryCode = destCountryCode;
	}

	public String getDestCountry() {
		return destCountry;
	}

	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}

	public String getG18() {
		return g18;
	}

	public void setG18(String g18) {
		this.g18 = g18;
	}

	public String getContainerTranFlag() {
		return containerTranFlag;
	}

	public void setContainerTranFlag(String containerTranFlag) {
		this.containerTranFlag = containerTranFlag;
	}

	public String getG21() {
		return g21;
	}

	public void setG21(String g21) {
		this.g21 = g21;
	}

	public String getIncoterms() {
		return incoterms;
	}

	public void setIncoterms(String incoterms) {
		this.incoterms = incoterms;
	}

	public String getPlaceDelivery() {
		return placeDelivery;
	}

	public void setPlaceDelivery(String placeDelivery) {
		this.placeDelivery = placeDelivery;
	}

	public String getContactCurrency() {
		return contactCurrency;
	}

	public void setContactCurrency(String contactCurrency) {
		this.contactCurrency = contactCurrency;
	}

	public String getG221c() {
		return g221c;
	}

	public void setG221c(String g221c) {
		this.g221c = g221c;
	}

	public Double getG222() {
		return g222;
	}

	public void setG222(Double g222) {
		this.g222 = g222;
	}

	public String getCurrencyAppliedDate() {
		return currencyAppliedDate;
	}

	public void setCurrencyAppliedDate(String currencyAppliedDate) {
		this.currencyAppliedDate = currencyAppliedDate;
	}

	public String getG25() {
		return g25;
	}

	public void setG25(String g25) {
		this.g25 = g25;
	}

	public String getG26() {
		return g26;
	}

	public void setG26(String g26) {
		this.g26 = g26;
	}

	public String getAddressLoad() {
		return addressLoad;
	}

	public void setAddressLoad(String addressLoad) {
		this.addressLoad = addressLoad;
	}

	public String getWarehouseConsignee() {
		return warehouseConsignee;
	}

	public void setWarehouseConsignee(String warehouseConsignee) {
		this.warehouseConsignee = warehouseConsignee;
	}

	public String getFreeWarehouseNo() {
		return freeWarehouseNo;
	}

	public void setFreeWarehouseNo(String freeWarehouseNo) {
		this.freeWarehouseNo = freeWarehouseNo;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateDate() {
		return certificateDate;
	}

	public void setCertificateDate(String certificateDate) {
		this.certificateDate = certificateDate;
	}

	public String getTempStorageInfo() {
		return tempStorageInfo;
	}

	public void setTempStorageInfo(String tempStorageInfo) {
		this.tempStorageInfo = tempStorageInfo;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBankShort() {
		return bankShort;
	}

	public void setBankShort(String bankShort) {
		this.bankShort = bankShort;
	}

	public String getG29() {
		return g29;
	}

	public void setG29(String g29) {
		this.g29 = g29;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getStationWarehousr() {
		return stationWarehousr;
	}

	public void setStationWarehousr(String stationWarehousr) {
		this.stationWarehousr = stationWarehousr;
	}

	public String getWarehousrCode() {
		return warehousrCode;
	}

	public void setWarehousrCode(String warehousrCode) {
		this.warehousrCode = warehousrCode;
	}

	public String getDeclarant() {
		return declarant;
	}

	public void setDeclarant(String declarant) {
		this.declarant = declarant;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDeclarantPost() {
		return declarantPost;
	}

	public void setDeclarantPost(String declarantPost) {
		this.declarantPost = declarantPost;
	}

	public String getClearanceCode() {
		return clearanceCode;
	}

	public void setClearanceCode(String clearanceCode) {
		this.clearanceCode = clearanceCode;
	}

	public String getCargoReleaseDate() {
		return cargoReleaseDate;
	}

	public void setCargoReleaseDate(String cargoReleaseDate) {
		this.cargoReleaseDate = cargoReleaseDate;
	}

	public String getInspectorId() {
		return inspectorId;
	}

	public void setInspectorId(String inspectorId) {
		this.inspectorId = inspectorId;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProduceCountry() {
		return produceCountry;
	}

	public void setProduceCountry(String produceCountry) {
		this.produceCountry = produceCountry;
	}

	public Double getPackages() {
		return packages;
	}

	public void setPackages(Double packages) {
		this.packages = packages;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getQuantityPallet() {
		return quantityPallet;
	}

	public void setQuantityPallet(String quantityPallet) {
		this.quantityPallet = quantityPallet;
	}

	public String getPalletInfo() {
		return palletInfo;
	}

	public void setPalletInfo(String palletInfo) {
		this.palletInfo = palletInfo;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getQuantitySecond() {
		return quantitySecond;
	}

	public void setQuantitySecond(Double quantitySecond) {
		this.quantitySecond = quantitySecond;
	}

	public String getMeasureSecond() {
		return measureSecond;
	}

	public void setMeasureSecond(String measureSecond) {
		this.measureSecond = measureSecond;
	}

	public Double getQuantityThird() {
		return quantityThird;
	}

	public void setQuantityThird(Double quantityThird) {
		this.quantityThird = quantityThird;
	}

	public String getMeasureThird() {
		return measureThird;
	}

	public void setMeasureThird(String measureThird) {
		this.measureThird = measureThird;
	}

	public String getCodeMeasureThird() {
		return codeMeasureThird;
	}

	public void setCodeMeasureThird(String codeMeasureThird) {
		this.codeMeasureThird = codeMeasureThird;
	}

	public Double getQuantityFourth() {
		return quantityFourth;
	}

	public void setQuantityFourth(Double quantityFourth) {
		this.quantityFourth = quantityFourth;
	}

	public String getMeasureFourth() {
		return measureFourth;
	}

	public void setMeasureFourth(String measureFourth) {
		this.measureFourth = measureFourth;
	}

	public String getCodeMeasureFourth() {
		return codeMeasureFourth;
	}

	public void setCodeMeasureFourth(String codeMeasureFourth) {
		this.codeMeasureFourth = codeMeasureFourth;
	}

	public String getHscode() {
		return hscode;
	}

	public void setHscode(String hscode) {
		this.hscode = hscode;
	}

	public String getOriginCountryCode() {
		return originCountryCode;
	}

	public void setOriginCountryCode(String originCountryCode) {
		this.originCountryCode = originCountryCode;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getFeePay() {
		return feePay;
	}

	public void setFeePay(String feePay) {
		this.feePay = feePay;
	}

	public String getCustomsProcedureCode() {
		return customsProcedureCode;
	}

	public void setCustomsProcedureCode(String customsProcedureCode) {
		this.customsProcedureCode = customsProcedureCode;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getUomCode() {
		return uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getAdjustFlag() {
		return adjustFlag;
	}

	public void setAdjustFlag(String adjustFlag) {
		this.adjustFlag = adjustFlag;
	}

	public Double getRubValue() {
		return rubValue;
	}

	public void setRubValue(Double rubValue) {
		this.rubValue = rubValue;
	}

	public String getCustomsMethod() {
		return customsMethod;
	}

	public void setCustomsMethod(String customsMethod) {
		this.customsMethod = customsMethod;
	}

	public Double getCifValue() {
		return cifValue;
	}

	public void setCifValue(Double cifValue) {
		this.cifValue = cifValue;
	}
	
}


