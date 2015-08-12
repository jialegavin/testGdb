package com.njyb.gbdbase.model.datasearch.pakistan;
import java.io.Serializable;

@SuppressWarnings("serial")
public class PakistanImportModel implements Serializable {

	/**
	 * 主键id
	 */
	private Integer pakistanImportId;

	/**
	 * 抵达港，进口港(PORT)
	 */
	private String endPort;

	/**
	 * 进口舱单号
	 */
	private String manifestNo;

	/**
	 * 产品索引号
	 */
	private Integer indexNo;

	/**
	 * 货物类型
	 */
	private String type;

	/**
	 * 舱单日期(IGMDAT:MANIFEST_DATE)
	 */
	private String date;

	/**
	 * 船名(VSLFLT)
	 */
	private String vesselName;

	/**
	 * 船次(VOYAGE)
	 */
	private String voyage;

	/**
	 * 航线
	 */
	private String sline;

	/**
	 * 船代(ship_agency)
	 */
	private String shipAgency;

	/**
	 * 产品描述(ITMDSC)
	 */
	private String goodsDesc;

	/**
	 * 毛重(GRSWT)
	 */
	private Double grossWeight;

	/**
	 * 包装数(NOPACK)
	 */
	private Double packages;

	/**
	 * 包装单位(UOPCOD)
	 */
	private String packageUnit;

	/**
	 * 20英尺货柜
	 */
	private String c20;

	/**
	 * 40英尺货柜
	 */
	private String c40;

	/**
	 * 45英尺货柜
	 */
	private String c45;

	/**
	 * 其它英尺货柜
	 */
	private String c99;

	/**
	 * 货柜总数(CNTRNR)
	 */
	private String containers;

	/**
	 * 运费(CSGFRT)
	 */
	private String freight;

	/**
	 * 提单号(BLNO)
	 */
	private String blNumber;

	/**
	 * 提单日期(BLDATE)
	 */
	private String bldate;

	/**
	 * 进口商(IMPNAM)
	 */
	private String importer;

	/**
	 * 进口商地址(IMPADD)
	 */
	private String importerAddr;

	/**
	 * 发货人(CNSGNM)
	 */
	private String exporter;

	/**
	 * 起运港(PRTSHP)
	 */
	private String startPort;

	/**
	 * 国家(出口国：COUNTRY)
	 */
	private String originCountry;

	/**
	 * 地区
	 */
	private String region;

	private Double num = 1.0;

	public Integer getPakistanImportId() {
		return pakistanImportId;
	}

	public void setPakistanImportId(Integer pakistanImportId) {
		this.pakistanImportId = pakistanImportId;
	}

	public String getEndPort() {
		return endPort;
	}

	public void setEndPort(String endPort) {
		this.endPort = endPort;
	}

	public String getManifestNo() {
		return manifestNo;
	}

	public void setManifestNo(String manifestNo) {
		this.manifestNo = manifestNo;
	}

	public Integer getIndexNo() {
		return indexNo;
	}

	public void setIndexNo(Integer indexNo) {
		this.indexNo = indexNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVesselName() {
		return vesselName;
	}

	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}

	public String getVoyage() {
		return voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	public String getSline() {
		return sline;
	}

	public void setSline(String sline) {
		this.sline = sline;
	}

	public String getShipAgency() {
		return shipAgency;
	}

	public void setShipAgency(String shipAgency) {
		this.shipAgency = shipAgency;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getPackages() {
		return packages;
	}

	public void setPackages(Double packages) {
		this.packages = packages;
	}

	public String getPackageUnit() {
		return packageUnit;
	}

	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}

	public String getC20() {
		return c20;
	}

	public void setC20(String c20) {
		this.c20 = c20;
	}

	public String getC40() {
		return c40;
	}

	public void setC40(String c40) {
		this.c40 = c40;
	}

	public String getC45() {
		return c45;
	}

	public void setC45(String c45) {
		this.c45 = c45;
	}

	public String getC99() {
		return c99;
	}

	public void setC99(String c99) {
		this.c99 = c99;
	}

	public String getContainers() {
		return containers;
	}

	public void setContainers(String containers) {
		this.containers = containers;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getBlNumber() {
		return blNumber;
	}

	public void setBlNumber(String blNumber) {
		this.blNumber = blNumber;
	}

	public String getBldate() {
		return bldate;
	}

	public void setBldate(String bldate) {
		this.bldate = bldate;
	}

	public String getImporter() {
		return importer;
	}

	public void setImporter(String importer) {
		this.importer = importer;
	}

	public String getImporterAddr() {
		return importerAddr;
	}

	public void setImporterAddr(String importerAddr) {
		this.importerAddr = importerAddr;
	}

	public String getExporter() {
		return exporter;
	}

	public void setExporter(String exporter) {
		this.exporter = exporter;
	}

	public String getStartPort() {
		return startPort;
	}

	public void setStartPort(String startPort) {
		this.startPort = startPort;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}
}
