package com.njyb.gbdbas.util;

/**
 * 该类用于存放贸易情报模块的所有常量
 * @author 洪皓
 * 2015-04-02
 * @version 标准版
 */
public interface DataSearchConstantUtil {
	
	//搜索条件字段中文名
	public final static String CONDITIONZHNAME="_conditionzhname";
	//search English name
	public final static String CONDITIONENNAME="_conditionenname";
	//搜索条件字段id名
	public final static String CONDITIONFIELDID = "_conditionfieldid";
	//搜索条件字段中文名
	public final static String FIELDNAME="_fieldname";
	//Search condition English Name
	public final static String FIELDNAMEEN = "_fieldnameen";
	//搜索条件字段id名
	public final static String FIELDVALUE = "_fieldvalue";
	//搜索条件字段id名
	public final static String FIELDID = "_fieldid";
	//搜索条件字段id名
	public final static String KEYWORD = "_keyword";
	//需要添加翻译的字段id
	public final static String TRANS= "_trans";
	//需要添加文本框描述的字段id
	public final static String DESC= "_desc";
	//搜索条件中的日期格式
	public final static String DATEFORMATTER = "_dateFormatter";
	//搜索条件中的初始化日期
	public final static String DATEVALUE = "_dateValue";
	//查询列表中国家默认的排序字段
	public final static String DEFAULTSORTKEY = "_defaultsortkey";
	//搜索需要精度处理的字段
	public final static String SEARCH_FORMATPRECISION = "_search_formatPrecision";
	//报表需要精度处理的字段
	public final static String REPORT_FORMATPRECISION = "_report_formatPrecision";
	//海关编码文本框描述
	public final static String HSCODEDESC = "Click to add Hs Code";
	//提单号文本框描述
	public final static String BILLLADINGDESC = "Please enter Bill Number";
	//除了海关编码和提单号其他文本框描述
	public final static String OTHERDESC = "Please enter at least one word";
	//翻译的图片按钮路径
	public final static String TRANSBUTTON_PATH = "/gbdbas/static/img/datasearch/translate.png";
	//添加海关编码按钮路径
	public final static String ADD_HSCODE_PATH = "/gbdbas/static/img/datasearch/addHscode.png";
	//查询结果展示字段中文名
	public final static String RESULTDISPLAYZHNAME = "_resultdisplayzhname";
	//Search Result showing English
	public final static String RESULTDISPLAYENNAME = "_resultdisplayenname";
	//查询结果展示字段id名
	public final static String RESULTDISPLAYFIELD = "_resultdisplayfield";
	//查询结果隐藏字段中文名
	public final static String RESULTHIDEZHNAME="_resulthidezhname";
	//Search Result hide English
	public final static String RESULTHIDEENNAME="_resulthideenname";
	//查询结果隐藏字段id名
	public final static String RESULTHIDEFIELD="_resulthidefield";
	
	//报表汇总查看详情中文名称
	public final static String DETAIL_FIELDZHNAME = "_detail_fieldzhname";
	//Report details English Name
	public final static String DETAIL_FILEDENNAME = "_detail_fieldenname";
	//报表汇总查看详情属性字段
	public final static String DETAIL_FIELDID = "_detail_fieldid";
	//需要鼠标移动上去显示对应的title的列的id名
	public final static String FORMATTITLE="_formatTitle";
	//需要鼠标移动上去显示对应的百度搜索的列的id名
	public final static String FORMATSEARCH="_formatSearch";
	//查看详情对应的字段id名
	public final static String VIEWDETAIL="viewdetail";
	//查看详情对应的字段中文名
	public final static String VIEWDETAIL_ZH="选择字段";
	//目前所有的国家名
	//阿根廷进口
	public final static String ARGENTINA_IMPORT="argentina_import";
	//阿根廷出口
	public final static String ARGENTINA_EXPORT="argentina_export";
	//巴西进口
	public final static String BRAZIL_IMPORT ="brazil_import";
	//智利出口
	public final static String CHILE_EXPORT="chile_export";
	//智利进口
	public final static String CHILE_IMPORT="chile_import";
	//中国八位
	public final static String CHINA_EIGHT ="china";
	//哥伦比亚出口
	public final static String COLOMBIA_EXPORT="colom_export";
	//哥伦比亚进口
	public final static String COLOMBIA_IMPORT="colom_import";
	//哥斯达黎加出口
	public final static String COSTARICA_EXPORT="const_export";
	//哥斯达黎加进口
	public final static String COSTARICA_IMPORT="const_import";
	//厄瓜多尔出口
	public final static String ECUADOR_EXPORT="ecuador_export";
	//厄瓜多尔进口
	public final static String ECUADOR_IMPORT="ecuador_import";
	//危地马拉出口
	public final static String GUATEMALA_EXPORT="wdml_export";
	//危地马拉进口
	public final static String GUATEMALA_IMPORT="wdml_import";
	//洪都拉斯进口
	public final static String HONDURAS_IMPORT="honduras_import";
	//印度进口
	public final static String INDIA_IMPORT="india_import";
	//韩国
	public final static String KOREA="korea";
	//墨西哥进口
	public final static String MEXICO_IMPORT="mexicon_import";
	//尼加拉瓜出口
	public final static String NICARAGUA_EXPORT="njlg_export";
	//尼加拉瓜进口
	public final static String NICARAGUA_IMPORT="njlg_import";
	//巴基斯坦进口
	public final static String PAKISTAN_IMPORT="pakistan_import";
	//巴拿马出口
	public final static String PANAMA_EXPORT="panama_export";
	//巴拿马进口
	public final static String PANAMA_IMPORT="panama_import";
	//秘鲁出口
	public final static String PERU_EXPORT="peru_export";
	//秘鲁出口
	public final static String PERU_IMPORT="peru_import";
	//俄罗斯出口
	public final static String RUSSIA_EXPORT="russian_export";
	//俄罗斯进口
	public final static String RUSSIA_IMPORT="russian_import";
	//萨尔瓦多出口
	public final static String SALVATORE_EXPORT="sewd_export";
	//萨尔瓦多进口
	public final static String SALVATORE_IMPORT="sewd_import";
	//英国进口
	public final static String UK_IMPORT="uk_import";
	//乌克兰进口
	public final static String UKRAINE_IMPORT="wkl_import";
	//乌拉圭出口
	public final static String URUGUAY_EXPORT="uruguay_export";
	//乌拉圭进口
	public final static String URUGUAY_IMPORT="uruguay_import";
	//美国进口
	public final static String USA_IMPORT="usa_import";
	//委内瑞拉出口
	public final static String VENEZUELA_EXPORT="vnrl_export";
	//委内瑞拉进口
	public final static String VENEZUELA_IMPORT="vnrl_import";
	//越南出口
	public final static String VIETNAM_EXPORT="yn_export";
	//越南进口
	public final static String VIETNAM_IMPORT="yn_import";
	//巴拉圭出口
	public final static String PARAGUAY_EXPORT="paraguay_export";
	//巴拉圭进口
	public final static String PARAGUAY_IMPORT="paraguay_import";
	//玻利维亚进口
	public final static String BOLIVIA_IMPORT="bolivia_import";
	//所有国家实体类中的字段名
	//海关编码
	public final static String HSCODE_MODEL="hscode";
	//产品描述
	public final static String GOOD_DESC_MODEL="goodsDesc";
	//cif价格
	public final static String CIF_VALUE_MODEL="cifValue";
	//毛重
	public final static String G_WEIGHT_MODEL="grossWeight";
	//日期
	public final static String DATE_MODEL="date";
	//fob价格
	public final static String FOB_VALUE_MODEL="fobValue";
	//数量
	public final static String QUANTITY_MODEL="quantity";
	//进口商
	public final static String IMPORTER_MODEL="importer";
	//原产国
	public final static String ORIGIN_COUNTRY_MODEL="originCountry";
	//出口商
	public final static String EXPORTER_MODEL="exporter";
	//起运港
	public final static String START_PORT_MODEL="startPort";
	//抵达港
	public final static String END_PORT_MODEL="endPort";
	//目的国
	public final static String DEST_COUNTRY_MODEL="destCountry";
	//净重
	public final static String N_WEIGHT_MODEL="netWeight";
	//提单号
	public final static String BL_NUMBER_MODEL="blNumber";
	//包装数
	public final static String PACKAGES_MODEL="packages";
	//通知人
	public final static String NOTIFIER_MODEL="notifier";
	//贸易类型
	public final static String TRADE_TYPE_MODEL="tradeType";
	//贸易类型
	public final static String CUSTOMS_MODEL = "customs";
	//所有国家查询条件的lucene中的字段名
	//海关编码
	public final static String HSCODE_LUCENE="hscode";
	//产品描述
	public final static String GOOD_DESC_LUCENE="goods_desc";
	//cif价格
	public final static String CIF_VALUE_LUCENE="cif_value";
	//毛重
	public final static String G_WEIGHT_LUCENE="g_weight";
	//日期
	public final static String DATE_LUCENE="date";
	//fob价格
	public final static String FOB_VALUE_LUCENE="fob_value";
	//数量
	public final static String QUANTITY_LUCENE="quantity";
	//进口商
	public final static String IMPORTER_LUCENE="importer";
	//原产国
	public final static String ORIGIN_COUNTRY_LUCENE="origin_country";
	//出口商
	public final static String EXPORTER_LUCENE="exporter";
	//起运港
	public final static String START_PORT_LUCENE="start_port";
	//抵达港
	public final static String END_PORT_LUCENE="end_port";
	//目的国
	public final static String DEST_COUNTRY_LUCENE="dest_country";
	//净重
	public final static String N_WEIGHT_LUCENE="n_weight";
	//提单号
	public final static String BL_NUMBER_LUCENE="bl_number";
	//包装数
	public final static String PACKAGES_LUCENE="packages";
	//通知人
	public final static String NOTIFIER_LUCENE="notifier";
	//贸易类型
	public final static String TRADE_TYPE_LUCENE="trade_type";
	//贸易类型
	public final static String CUSTOMS_LUCENE = "customs";
	//制造商
	public final static String MANUFACTURE = "manufacture";
	//导出pdf模板的项目路径
	public final static String PDF_TEMPALATEPATH ="/view/datasearch/pdfTemplate/pdfTemplate.html";
	//pdf文件名
	public final static String TEMPALTE_NAME = "view_detail";
	//降序
	public final static String ORDER_DESC = "desc";
	//升序
	public final static String ORDER_ASC = "asc";
	//按次用户 成功标志
	public final static String COUNT_USER="1";
	//按次用户 次数用完
	public final static String COUNT_USER_OVER="5";
	//当前用户是正式用户，满足查询权限
	public final static String CONDITION_TRUE_USER="2";
	//当前用户是正式用户，但是不具备查询的权限
	public final static String CONDITION_FALSE_USER="3";
	//未获取该用户
	public final static String NO_USER="4";
	//产品趋势分析报表
	public final static String _CP_TRADE="_cp_trade";
	//国别分析报表
	public final static String _CP_COUNTRY="_cp_country";
	//港口分析报表
	public final static String _CP_PORT="_cp_port";
	//产品趋势分析报表
	public final static String CP_TRADE = "cp_trade" ;
	//国别分析报表
	public final static String CP_COUNTRY="cp_country";
	//港口分析报表
	public final static String CP_PORT="cp_port";
	//搜索结果
	//public final static String SEARCH_RESULT="搜索结果";
	//Search Result
	public final static String SEARCH_RESULT="Search Result";
	//产品趋势分析报表
	//public final static String PRODUCT_TREAD_REPORT="交易趋势报表";
	//Trade Tendency Analysis
	public final static String PRODUCT_TREAD_REPORT="Trend Analysis";
	//港口分析报表
	//public final static String PORT_REPORT="港口分析报表";
	//Port Analysis
	public final static String PORT_REPORT="Port Analysis";
	//国别分析报表
	//public final static String COUNTRY_REPORT="国别分析报表";
	//Country Analysis
	public final static String COUNTRY_REPORT="Country Analysis";
	
	//搜索
	public final static String SEARCH="search";
	//报表
	public final static String REPORT="report";
	//报表模块计算比率的字段
	public final static String PERCENTAGE_SORT_FIELD = "_percentage_sort_field";
	//报表模块比率显示字段
	public final static String PERCENTFIELD = "_percentfield";
	//报表模块计算比率的字段
	public final static String THB_SORT_FIELD = "_thb_sort_field";
	//导出报表模块计算比率的字段
	public final static String PERCENTAGE = "_percentage";
	//导出报表模块排序的字段
	public final static String SORT_FIELD = "_sort_field";
	//导出报表模块统计字段
	public final static String STATISTICS = "_statistics";
	//导出报表汇总钻取字段
	public final static String DRILLREPORTTYPE = "_drillReportType";
	//导出报表汇总钻取字段中文名称
	public final static String DRILLCOLUMNNAME = "_drillColumnName";
	//混搭图字段id配置文件key值
	public final static String MIXCHART_FIELDID = "_mixchart_fieldid";
	//混搭图字段中文名配置文件key值
	public final static String MIXCHART_FIELDZHNAME = "_mixchart_fieldzhname";
	// mix chart english filedname
	public final static String MIXCHART_FIELDENNAME = "_mixchart_fieldenname";
	//饼图配置文件key值
	public final static String PIECHART_FIELDID = "_piechart_fieldid";
	//月度环比月度同比数据表格字段id配置文件key值
	public final static String MOM_MYOY_FIELDID = "_mom_myoy_fieldid";
	//月度环比月度同比数据表格字段中文名配置文件key值
	public final static String MOM_MYOY_FIELDZHNAME = "_mom_myoy_fieldzhname";
	
	public final static String MOM_MYOY_FIELDENNAME = "_mom_myoy_fieldenname";

	//月度环比月度同比需要精度处理的字段
	public final static String MOM_MYOY_FORMATPRECISION = "_mom_myoy_formatPrecision";
	//年度同比数据表格字段id配置文件key值
	public final static String YOY_FIELDID = "_yoy_fieldid";
	//年度同比数据表格字段中文名配置文件key值
	public final static String YOY_FIELDZHNAME = "_yoy_fieldzhname";

	public final static String YOY_FIELDENNAME = "_yoy_fieldenname";
	//年度同比需要精度处理的字段
	public final static String YOY_FORMATPRECISION = "_yoy_formatPrecision";
	//月度环比混搭图字段id配置文件key值
	public final static String MOM_MIXCHART_FIELDID = "_mom_mixchart_fieldid";
	//月度环比混搭图字段中文名配置文件key值
	public final static String MOM_MIXCHART_FIELDZHNAME = "_mom_mixchart_fieldzhname";
	
	public final static String MOM_MIXCHART_FIELDENNAME = "_mom_mixchart_fieldenname";
	//月度同比混搭图字段id配置文件key值
	public final static String MYOY_MIXCHART_FIELDID = "_myoy_mixchart_fieldid";
	//月度同比混搭图字段中文名配置文件key值
	public final static String MYOY_MIXCHART_FIELDZHNAME = "_myoy_mixchart_fieldzhname";
	
	public final static String MYOY_MIXCHART_FIELDENNAME = "_myoy_mixchart_fieldenname";
	//年度同比混搭图字段id配置文件key值
	public final static String YOY_MIXCHART_FIELDID = "_yoy_mixchart_fieldid";
	//年度同比混搭图字段中文名配置文件key值
	public final static String YOY_MIXCHART_FIELDZHNAME = "_yoy_mixchart_fieldzhname";
	public final static String YOY_MIXCHART_FIELDENNAME = "_yoy_mixchart_fieldenname";

	//需要展示混搭图的报告类型
	public final static String MIXCHART_REPORTTYPE = "_mixchart_reportType";
	//需要展示饼图的报告类型
	public final static String PIECHART_REPORTTYPE = "_piechart_reportType";
	//该国家所有的报告类型
	public final static String REPORT_TYPE = "_reportType";
	//该国家深度挖取所有的报告类型
	public final static String DEPTHDIGGING_REPORTTYPE = "_depthDigging_reportType";
	//该国家深度挖取需要混搭图展示的报告类型
	public final static String DEPTHDIGGING_MIXCHART_REPORTTYPE = "_depthDigging_mixchart_reportType";
	//该国家深度挖取需要饼图展示的报告类型
	public final static String DEPTHDIGGING_PIECHART_REPORTTYPE = "_depthDigging_piechart_reportType";
	//该国家选择对比需要混搭图展示的报告类型
	public final static String DATACOMPARE_MIXCHART_REPORTTYPE = "_datacompare_mixchart_reportType";
	//该国家选择对比需要饼图展示的报告类型
	public final static String DATACOMPARE_PIECHART_REPORTTYPE = "_datacompare_piechart_reportType";
	//该国家同环比需要混搭图展示的报告类型
	public final static String THB_MIXCHART_REPORTTYPE = "_thb_mixchart_reportType";
	//该国家同环比需要饼图展示的报告类型
	public final static String THB_PIECHART_REPORTTYPE = "_thb_piechart_reportType";
	//公司名称  WangBo add this 336 line
	public final static String COMPANYNAME = "company_name";
	//公司名称  WangBo add this 336 line
	public final static String PRODUCTPLACE = "product_place";
	//以下为所有报告类型汇总
	//海关编码
	public final static String HSCODE = "hsCode";
	//产品描述
	public final static String GOODSDESC = "goodsDesc";
	//产品趋势
	public final static String QS = "qs";
	//进口商
	public final static String JKS = "jks";
	//出口商
	public final static String CKS = "cks";
	//起运港
	public final static String QYG = "qyg";
	//抵达港
	public final static String DDG = "ddg";
	//原产国
	public final static String YCG = "ycg";
	//目的国
	public final static String MDG = "mdg";
	//通知人
	public final static String TZR = "tzr";
	//制造商
	public final static String ZZS = "zzs";
	//海关城市
	public final static String HGCS = "hgcs";
	//中国进口商
	public final static String ZG_JKS = "zg_jks";
	//中国港口
	public final static String ZG_PORT = "zg_port";
	//中国收发货地
	public final static String ZG_SFHD = "zg_sfhd";
	//以下为所有报告类型的中文名
	//海关编码
	public final static String HSCODE_ZH = "海关编码报告";
	//Hs Code
	public final static String HSCODE_EN = "Hs Code Report ";
	//产品描述
	public final static String GOODSDESC_ZH = "产品描述报告";
	//roduct Description
	public final static String GOODSDESC_EN = "Product Description ";
	//产品趋势
	public final static String QS_ZH = "产品趋势";
	//Product Tendency
	public final static String QS_EN = "Product Tendency ";
	//十大进口商
	public final static String JKS_TEN_ZH = "十大进口商报告";
	//Tenth Largest Importer Report
	public final static String JKS_TEN_EN = "Tenth Largest Importer Report ";
	//进口商
	public final static String JKS_ZH = "进口商报告";
	//Importer 
	public final static String JKS_EN = "Importer Report ";
	//十大出口商
	public final static String CKS_TEN_ZH = "十大出口商报告";
	//Tenth Largest Exporter Report
	public final static String CKS_TEN_EN = "Tenth Largest Exporter Report ";
	//出口商
	public final static String CKS_ZH = "出口商报告";
	//Exporter
	public final static String CKS_EN = "Exporter Report ";
	//起运港
	public final static String QYG_ZH = "起运港报告";
	//Loading Port
	public final static String QYG_EN = "Loading Port Report ";
	//抵达港
	public final static String DDG_ZH = "抵达港报告";
	// End Port
	public final static String DDG_EN = "End Port Report ";
	//原产国
	public final static String YCG_ZH = "原产国报告";
	//Original Country
	public final static String YCG_EN = "Original Report ";
	//目的国
	public final static String MDG_ZH = "目的国报告";
	//Destination Country
	public final static String MDG_EN = "Destination Country Report ";
	//通知人
	public final static String TZR_ZH = "通知人报告";
	//Notifier
	public final static String TZR_EN = "Notifier ";
	//制造商
	public final static String ZZS_ZH = "制造商报告";
	//Manufacturer
	public final static String ZZS_EN = "Manufacturer ";
	//海关城市
	public final static String HGCS_ZH = "海关城市报告";
	//Custom Cities
	public final static String HGCS_EN = "Custom Cities Report ";
	//中国进口商
	public final static String ZG_JKS_ZH = "十大采购商/供应商";
	//Chinese Importer
	public final static String ZG_JKS_EN = "Importer/Exporter ";
	//中国港口
	public final static String ZG_PORT_ZH = "港口报告";
	//Port
	public final static String ZG_PORT_EN = "Port Report ";
	//中国收发货地
	public final static String ZG_SFHD_ZH = "收发货地报告";
	//Ship/Rec Location
	public final static String ZG_SFHD_EN = "Ship/Rec Location Report ";
	//报表汇总模块
	public final static int DATA_SUMMARY = 1;
	//深度挖取模块
	public final static int DEPTH_DIGGING = 2;
	//选择对比模块
	public final static int DATA_COMPARE = 3;
	//月度环比分析模块
	public final static int MOM_REPORT= 4;
	//月度同比分析模块
	public final static int MYOY_REPORT= 5;
	//年度同比分析模块
	public final static int YOY_REPORT = 6;
	//懒加载
	public final static int LAZY_LOAD = 1;
	//全部加载
	public final static int ALL_LOAD = 2;
	//点击查看更多
	public final static String LOAD_MORE = "View More";
	//同环比
	public final static String THB_TITLE = "同环比分析报告";
	public final static String THB = "同环比";
	//各国同环比显示类型
	public final static String THB_SHOWTYPE = "_thb_showtype";
	//各国同环比显示类型中文名
	public final static String THB_SHOWTYPE_ZHNAME = "_thb_showtype_zhname";
	//年度同比
	public final static String YOY = "yoy";
	//月度同比
	public final static String MYOY = "myoy";
	//月度环比
	public final static String MOM = "mom";
	//年度同比
	public final static String YOY_ZH = "年度同比";
	//YOY
	public final static String YOY_EN = "YOY "; 
	//月度同比
	public final static String  MYOY_ZH= "月度同比";
	//MYOY
	public final static String MYOY_EN = "MYOY ";
	//月度环比
	public final static String MOM_ZH = "月度环比";
	//MOM
	public final static String MOM_EN = "MOM ";
	//多海关编码字段中文名
	public final static String MUTI_HSCODE_FIELDZHNAME = "_muti_hscode_fieldzhname";
	//多海关编码字段id
	public final static String MUTI_HSCODE_FIELDID = "_muti_hscode_fieldid";
	
}

