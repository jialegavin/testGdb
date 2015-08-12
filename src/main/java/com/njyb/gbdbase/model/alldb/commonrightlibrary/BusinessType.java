package com.njyb.gbdbase.model.alldb.commonrightlibrary;

/**
 * 报告类型/钻取类型枚举类
 * @author WangBo
 * 2015年4月13日
 * BusinessType.java
 */
public enum BusinessType {

	/**
	 * 新增进口商
	 */
	ADDIMPORTER,

	/**
	 * 进口商排名10
	 */
	IMPORTER_ORDER10,
	/**
	 * 进口商排名20
	 */
	IMPORTER_ORDER20,
	/**
	 * 进口商排名30
	 */
	IMPORTER_ORDER30,
	/**
	 * 流失进口商
	 */
	REDURCEIMPORTER,
	/**
	 * 指定进口商
	 */
	APPOINTIMPORTER,
	/**
	 * 进口商交易趋势
	 */
	IMPORTERTREND,
	/**
	 * 进口商汇总
	 */
	IMPORTERSUMMARY,

	/**
	 * 进口商排名
	 */
	IMPORTER_ORDER,

	/**
	 * 出口商排名
	 */
	EXPORTER_ORDER,

	/**
	 * 新增出口商
	 */
	ADDEXPORTER,
	/**
	 * 出口商排名10
	 */
	EXPORTER_ORDER10,
	/**
	 * 出口商排名20
	 */
	EXPORTER_ORDER20,
	/**
	 * 出口商排名30
	 */
	EXPORTER_ORDER30,
	/**
	 * 流失出口商
	 */
	REDURCEEXPORTER,
	/**
	 * 指定出口商
	 */
	APPOINTEXPORTER,
	/**
	 * 出口商交易趋势
	 */
	EXPORTERTREND,
	/**
	 * 出口商汇总
	 */
	EXPORTERSUMMARY,

	/**
	 * 海关编码汇总
	 */
	HSCODESUMMARY,

	/**
	 * 明细
	 */
	DETAIL,

	/**
	 * 原产国汇总
	 */
	NATIVE,
	/**
	 * 产品汇总报告
	 */
	GOODSSUM,
	/**
	 * 买卖方趋势
	 */
	BUYANDSALETREND,
	/**
	 * 买卖方交易汇总
	 */
	BUYANDSALESUMMARY,
	/**
	 * 单价趋势
	 */
	UNITPRICETREND,
	/**
	 * cif单价趋势
	 */
	CIFUNITPRICE,
	/**
	 * fob单价趋势
	 */
	FOBUNITPRICE,
	/**
	 * 海关交易汇总报告
	 */
	CUSTOMERPORT,
	/**
	 * 交易趋势
	 */
	TRANSACTIONTREND,

	/**
	 * 同环比汇总
	 */
	SAMERING,

	/**
	 * 起运港汇总报告
	 */
	PORTOFSHIPMENT,

	/**
	 * 抵达港汇总报告
	 */
	PORTOFARRIVAL,

	/**
	 * 进口商钻取下面有哪些产品
	 */
	IMPORTERDRILLGOOD,
	/**
	 * 出口商钻取产品
	 */
	EXPORTERDRILLGOOD,
	/**
	 * 原产国钻取出口商
	 */
	NATIVEDRILLGOOD,
	/**
	 * 产品描述钻取进口商
	 */
	GOODDRILLPAY,
	/**
	 * 进口商钻取原产国
	 */
	PAYDRILLNATIVE,
	/**
	 * 毛重钻取产品
	 */
	WEIGHTDRILLGOOD,
	/**
	 * 目的港
	 */
	DESTINATIONSUM
}
