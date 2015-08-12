package com.njyb.gbdbas.util;
//存放常量--
public interface IConstantUtil {
	public final static String TEST="test";
	public final static String LUCENE_PATH_CHINA_EIGHT="e:/lucene/index";
	public final static String LUCENE_PATH_CHINA_TEN="e:/lucene/tenindex";
	public final static String LUCENE_SQL="select id,name,pwd,love from lc";
	//测试用户查看的数据条数
	public final static String TESTSEARCHNAME="TESTSEARCHNUM";
	//用户免费查看数据时间限制(八位)
	public final static String SEARCHEIGHTTIMENAME="SEARCHEIGHTTIME";
	//用户免费查看数据时间限制(十位)
	public final static String SEARCHTENTIMENAME="SEARCHTENTIME";
	//用户免费查看数据时间限制(美国)
	public final static String SEARCHUSATIMENAME="SEARCHUSATIME";
	//用户免费查看数据时间限制(俄罗斯)
	public final static String SEARCHRUSSIATIMENAME="SEARCHRUSSIATIME";
	//用户免费查看数据时间限制(乌克兰)
	public final static String SEARCHUKRAINETIMENAME="SEARCHUKRAINETIME";
	//用户免费查看数据时间限制(巴基斯坦)
	public final static String SEARCHPAKISTANTIMENAME="SEARCHRPAKISTANTIME";
	//用户免费查看数据时间限制(韩国)
	public final static String SEARCHKOREATIMENAME="SEARCHRKOREATIME";
	//用户免费查看数据时间限制(印度出口)
	public final static String SEARCH_INDIA_EXPORT_TIME_NAME = "SEARCHINDIAEXPORTTIME";
	//用户免费查看数据时间限制(印度进口)
	public final static String SEARCH_INDIA_IMPORT_TIME_NAME = "SEARCHINDIAIMPORTTIME";
	//用户免费查看数据时间限制(巴西)
	public final static String SEARCHBRAZILTIMENAME = "SEARCHBRAZILTIME";
	//用户免费查看数据时间限制(厄瓜多尔)
	public final static String SEARCHECUADORTIMENAME = "SEARCHECUADORTIME";
	//用户免费查看数据时间限制(委内瑞拉)
	public final static String SEARCHVENEZUELATIMENAME = "SEARCHVENEZUELATIME";
	//用户免费查看数据时间限制(越南)
	public final static String SEARCHVIETNAMTIMENAME = "SEARCHVIETNAMTIME";
	//用户免费查看数据时间限制(智利)
	public final static String SEARCHCHILETIMENAME = "SEARCHCHILETIME";
	//用户免费查看数据时间限制(洪都拉斯)
	public final static String SEARCHHONDURASTIMENAME = "SEARCHHONDURASTIME";
	//用户免费查看数据时间限制(西班牙)
	public final static String SEARCHSPAINTIMENAME = "SEARCHSPAINTIME";
	//用户免费查看数据时间限制(乌拉圭)
	public final static String SEARCHURUGUAYTIMENAME = "SEARCHURUGUAYTIME";
	//用户免费查看数据时间限制(巴拉圭)
	public final static String SEARCHPARAGUAYTIMENAME = "SEARCHPARAGUAYTIME";
	//用户免费查看数据时间限制(英国)
	public final static String SEARCHUKTIMENAME = "SEARCHUKTIME";
	//用户免费查看数据时间限制(墨西哥)
	public final static String SEARCHMEXICOTIMENAME = "SEARCHMEXICOTIME";
	//用户免费查看数据时间限制(秘鲁)
	public final static String SEARCHPERUTIMENAME = "SEARCHPERUTIME";
	//用户免费查看数据时间限制(哥斯达黎加)
	public final static String SEARCHCOSTARICATIMENAME = "SEARCHCOSTARICATIME";
	//用户免费查看数据时间限制(阿根廷)
	public final static String SEARCHARGENTINATIMENAME = "SEARCHARGENTINATIME";
	//用户免费查看数据时间限制(哥伦比亚进口)
	public final static String SEARCHCOLOMBIAIMPORTTIMENAME = "SEARCHCOLOMBIAIMPORTTIME";
	//用户免费查看数据时间限制(哥伦比亚出口)
	public final static String SEARCH_COLOMBIA_EXPORT_TIME_NAME = "SEARCHCOLOMBIAEXPORTTIME";
	//VIP限制下载的条数
	//public final static Integer VIPNUM =200;
	public final static String VIPNUMNAME="VIPNUM";
	//金卡用户限制下载的条数
	//public final static Integer VVIPNUM =300;
	public final static String VVIPNUMNAME="VVIPNUM";
	//限制excel导出数量
	public final static String EXCNUMNAME="EXCNUM";
	
	// 支付宝 支付 状态 
	// 支付成功
	public final static int SUCCESS_PAYMENT_STATE = 2;
	// 支付失败
	public final static int ERROR_PAYMENT_STATE = 3;
	//支付宝
	public final static String ALIPAY = "支付宝";
	//贝宝
	public final static String PAYPAL = "贝宝";
	
//	//未支付状态
//	public final static String PAYSTATUS = "未支付";
//	//已支付状态
//	public final static String SUCCESSPAYSTATUS = "已支付";
//	//未支付状态英文
//	public final static String PAYSTATUS_EN = "Unpaid";
//	//已支付状态英文
//	public final static String SUCCESSPAYSTATUS_EN = "Paid";
	//快速制定1个HSCODE的单价
	//public final static double MONEY = 500;
	  public final static String HSCODEMONEY = "MONEY";
	//快速定制1个国家的单价
	//public final static double COUNTRYMONEY = 10000;
	  public final static String COUNTRYMONEYNAME = "COUNTRYMONEY";
	//快速定制所有国家数量
	public final static int COUNTRIES = 22;
	//按次查看用户的收费价格
	public final static String COUNTMONEY="COUNTMONEY";
	//中国海关进口8位数据查询
	public final static String CHINA_EIGHT_SQL="select chinaeightid,customscode,productname,companyname,customsports,productplace,iexport,monthly,trade,productsalcountry,unitprice from china_eight limit 10000";
	//Excel设置每个工作区显示的数据条数
	public final static int SHEETSIZE = 60000;
	//PDF设置表格的最大宽度
	public final static int MAXWIDTH = 2200;
	public final static int FLEXWIDTH = 1000;
	//设置导出EXCEL类型
	public final static int EXCELTYPE =1;
	//设置导出PDF类型
	public final static int PDFTYPE = 2;
	//所有国家
	public final static String ALLDB_INDEX_PATH="alldb";
	//中国八位
	public final static String CHINA_EIGHT="china_eight";
	 
	  
	public final static String CHINA_EIGHT_COUNT_JCY = "select count(1) from china_eight ";
	//中国8位的主键编号
	public final static String CHINA_EIGHT_KEYID="ce_keyid";
	public final static String SEARCH_STATUS = "search_stauts";
	//查询中国八位的下拉列表的值
	public final static String CHINA_EIGHT_FIELD_SQL_JCY = "SELECT ID,FNAME,TYPE FROM EIGHT_FIELD_ESCAPE ";
	  
	 
	public final static String YEAR="year";
	//月
	public final static String MONTH="month";
	//季度
	public final static String QUAETER="quarter";
	//季度
	public final static String DAY="DAY";
	//进口
	public final static String IN="in";
	
	//出口
	public final static String OUT="out";
	
	/**
	 * 乌克兰sql路径
	 */
	public final static String UKRAINESQL="com/njyp/dbas_data/controller/report/wkl/wklsql.xml";
	//添加索引父目录
	public final static String COMMON_INDEXPATH=System.getProperties().getProperty("os.name").split(" ")[0].equals("Windows") ? "\\d:\\lucene\\" : "/data/luceneNew/";
	//索引路径
	public final static String DBRESOURCE_INDEXPATH="dbresource_indexpath";
	
	//FTP服务器连接url
	public final static String FTP_URL="";
	//FTP服务器登陆姓名
	public final static String FTP_USERNAME="";
	//FTP服务器登陆密码
	public final static String FTP_PASSWORD="";
	//FTP服务器登陆端口号
	public final static String FTP_PORT="";
	
	//同一个账号同时登陆的地方不超过3个
	public final static int LOGIN_TIMES=3;
	//用户同时登录的最大允许区间
	public final static int LOGIN_TIME_INTERVAL=60;

	//配置PDF下载路径    Linux和Windows都可用
	public static String LINUXPATH = System.getProperties().getProperty("os.name").split(" ")[0].equals("Windows") ? "\\WEB-INF\\img\\" : "WEB-INF/img/";

	//金卡用户对应权限id
	public final static int GOLD_USER_ROLEID = 4;
	//vip用户对应权限id
	public final static int VIP_USER_ROLEID = 3;
	//10000代表数据库中sys_country_right表中的userid拥有最大权限
	public final static int MAX_RIGHT_ID = 10000;
	
	//项目免费试用的用户名
	public final static String FREE_TRIAL = "trade";
	
	//登录模块各种状态码1:表示用户成功登录2:表示密码不正确3:表示用户不存在
	//4:表示用户验证码不正确5:同一个账号同时登陆的地方不超过3个6:该用户占时被锁定
	//7:表示未激活8:表示账号被禁用 9:表示用户的账号有效期到期
	public final static String LOGIN_SUCCESS = "1";
	public final static String PASSWD_INCORRENT = "2";
	public final static String USER_NOTEXIST = "3";
	public final static String VERIFICATION_INCORRENT = "4";
	public final static String ELSEWHERE_LOGIN_TIMES = "5";
	public final static String USER_LOCKED = "6";
	public final static String INACTIVE = "7";
	public final static String USER_DISABLE = "8";
	public final static String USER_TimeOut = "9";
	//密码输入错误次数超过5次 
	public final static int PASSWD_ENTER_TIMES = 5;
	//当锁定后超过15分钟方可登录
	public final static int LOCKED_TIME = 15;
	//管理员用户
	public final static String ADMIN_USER = "管理员用户";
	//试用用户
	public final static String TRIAL_USER = "试用用户";
	
	//web.xml中加载properties文件的param-name
	public final static String FILTERFIELD = "filterfield";
	//查看详情的properties文件
	public final static String VIEWDETAIL = "viewdetail";
	//动态替换网页信息的properties文件及参数
	public final static String WEBINFO = "webinfo";
	public final static String WEBINFO_INFOBASE = "infobase";
	public final static String WEBINFO_TRADE_EASY = "trade_easy";
	
	//导出excel及pdf中需要的信息
	public final static String EXPORTINFO = "exportInfo";
	//支付宝参数配置文件
	public final static String ALIPAYFIELD = "alipayParamConfig";
	
	public final static int DEFUATSONS=0;
	//用户激活状态
	public final static boolean USERACTIVE=false; 
	//用户非激活状态
	public final static boolean USERUNACTIVE=false; 
	//初始化开头子账户的权限
	public final static boolean OPENSERVICE=false; 
	//新增用户默认初始状态
	public static final String DEFAULTDESC="试用用户";
	//初始化账户的锁定状态
	public final static boolean LOCKED=false; 
	//初始化登录次数
	public final static int LOGINTIME=0; 
	//初始化用户可用状态
	public final static boolean ISDISABLE=false;
	//初始IP是否拦截
	public final static int ISCHECK=0;
	//email重复
	public final static String ISEMAILREPEAT="ER";
	//loginName重复
	public final static String ISLOGINNAMEREPEAT="LR";
	//允许开通子帐号的层数（默认只有开通三层）
	public static final int SONFLOOR=2;
	//是否删除用户以及其子用户
	public static final boolean ISDELETESONS=true;
	//初始Execl下载条数
	public static final int DEFAULTEXELNUM=0;
	//初始化总下载条数
	public static final int DEFAULTTOATLNUM=0;
	//初始化pdf下载条数
	public static final int DEFAULTPDF=0;
	//默认订单名称
	public static final String ORDER_NAME="海关数据信息";
	//订单初始化状态
	public static final String ORDER_STATUS0="0";
	//订单提交状态
	public static final String ORDER_STATUS1="1";
	//订单已支付
	public static final String ORDER_STATUS2="2";
	
	/**
	 * 全库
	 */
	//我的竞争对手
	public static final String COMPETITOR = "我的竞争对手";
	//我的客户
	public static final String CUSTOMER = "我的客户";
	
	/**
	 * 授权方式
	 */
	//海关编码
	public static final String AUTH_HSCODE = "auth_hscode";
	//产品描述
	public static final String AUTH_DESC = "auth_desc";
	//国家
	public static final String AUTH_COUNTRY = "auth_country";
	//按次
	public static final String AUTH_COUNT = "auth_count";
	//各个国家更新时间配置文件名
	public static final String COUNTRYUPDATETIME = "countryUpdateTime";
}
