package com.njyb.gbdbase.service.common.engine.util;
/**
 * 需要特殊处理的国家
 * @author 贾红平
 *
 */
public enum ParamEnumUtil {
	//1 进口商可以为空 可以为物流
	imp_other,
	//2 进口商可以为空 但是不可以为物流
	imp_not_wl,
	//3 进口商可以是物流 但是不可以是空值
	imp_not_null,
	//4 进口商既不能为空也不能为物流
	imp_not_wul_null,
	//代表进口商文本框输入框有填写值
	yes,
	//代表进口商文本输入框没有填写任何值
	no,
	//代表值类型为空
	//买家资源库  中国八位  美国 英国  除此之外其它国家
	data_resource,china,usa,uk,other,
	//创建字段查询query
	field,
	//创建多条件查询query
	condition,
	//产品检索
	search,
	//报表检索
	report,
	//汇总明细
	detail,
	//全库检索
	alldb,
	//买家资源库
	buyer,
	//报表关联分析
	drill,
	//进出口贸易类型
	trade_type,
	//报关口岸
	start_port,
	//代表要过滤的字符串
	date,hscode,number,
	//产品交易趋势分析
	cp_trade,
	//产品国别分析
	cp_country,
	//产品港口分析
	cp_port,
	//产品原产地/关区分析
	cp_yp,
	//综合分析
	cp_zhfx,
	//进口商
	jks,
	//出口商
	cks,
	//企业
	zg_jks,
	//关区
	zg_port,
	//收发货地
	zg_sfhd,
	//起运港
	qyg,
	//抵达港
	ddg,
	//原产国
	ycg,
	//目的国
	mdg,
	//通知人
	tzr,
	//制造商
	zzs,
	//交易趋势
	qs, 
	//海关城市
	hgcs,
	//使用线程
	use_thread,
	//不适用线程
	nouse_thread,
	jkscompare_first,//赋值importer
	jkscompare_second,//赋值importer
	ckscompare_first,//赋值exporter
	ckscompare_second,//赋值exporter
	companycompare_first,//赋值企业名称(company_name)
	companycompare_second,//赋值企业名称(company_name)
	
	mom,//月度环比
	myoy,//月度同比
	yoy,//年度同比
	mqoq,//季度环比
	same,//条件和值完全一致
	containhscode,//条件不一致 但是两次查询中包含海关编码
	nocontainhscode,//条件不一致 同时两次查询中也不包含海关编码
	init,//各种情况导致需要重新检索数据
	multi_hscode,//多海关编码
	goodsDesc,//产品描述汇总
	hsCode,//海关编码
	;
	/**
	 * 返回枚举对象
	 * @param param
	 * @return
	 */
	public static ParamEnumUtil getEnum(String param) {
		return valueOf(param);
	}
}
