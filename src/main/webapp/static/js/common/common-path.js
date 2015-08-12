/**
 * **********************************************个人中心 & 权库 & 收藏夹 配置JS文件  对象池 配置
 * 勿动!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * ********************************************************************************
 * @author WangBo
 */
/**
 * js获取项目根路径，如： http://localhost:8083/uimcardprj
 */
function getRootPath() {
	// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}

/**
 * 全局对象
 */
var param = {};

/**
 * 国家配置
 */
var reportArray;

/**
 * 不用过滤的国家名称
 */
var reportName;

/**
 * 中英文报告类型
 */
var reportChinaTypeArray;

/**
 * 权库 客户信息 和 竞争对手 的 标示符 1 : 竞争对手 2 : 客户信息
 */
var flagPage;

/**
 * 用于JS传值到另一个JS
 * 
 * @param params
 * @returns {___anonymous_param}
 */
function setParam(params) {
	param = params;
}

/**
 * 获取对象
 * 
 * @returns {___anonymous_param}
 */
function getParam() {
	return param;
}

/**
 * ajax 封装方法
 */
function ajaxLoadJava(url, data, type, dataType) {
	$.ajax({
		url : url,
		data : data,
		type : type,
		dataType : dataType,
		success : returnMethod
	});
}

/**
 * 封装的列表查询请求{查询}
 * 
 * @param id :
 *            jquery Id
 * @param url :
 *            url
 * @param queryParams :
 *            查询条件
 * @param callBack :
 *            回调方法
 */
function easyUiAjaxLoad(id, url, queryParams, callBack) {
	id.datagrid({
		url : url,
		queryParams : queryParams,
		pageNumber : 1,
		onLoadSuccess : callBack,
		autoRowHeight:false
	});
}

/**
 * 封装的列表删除请求{删除}
 * 
 * @param id :
 *            jquery Id
 * @param url :
 *            url
 * @param queryParams :
 *            查询条件
 * @param loadMethod :
 *            回调方法
 */
function easyUiAjaxDel(id, url, queryParams, loadMethod) {
	id.datagrid({
		url : url,
		queryParams : queryParams,
		pageNumber : 1,
		pagination : true,
		onLoadSuccess : loadMethod
	});
}

//$(function() {
//	ajaxLoadJava(
//			getRootPath() + "/setSession/setName",
//			{
//				userId : 888,
//				country : "argentina_import",
//				allDBList : '[{"columName":"SHANGHAI","countNum":246,"country":"巴基斯坦","customscity":"暂无","date":"20120518","endport":"kpt","exporter":"YIWU JUXIAN IMPORT E   XPORT CO  LTD CHINA","goodsdescription":"CANVASPIPE FOOTBALL SKATINGSHOES BAG","hscode":"提单号:Z230211964","id":167132,"iexport":"暂无","importer":"EMAAN TRADING OFFICE","orgincountry":"N/A","quantity":176287,"sortKey":"","startport":"SHANGHAI","totalprice":0,"unitprice":0,"weight":2927521.41},{"columName":"VANCOUVER, BC","countNum":8,"country":"美国进口","customscity":"暂无","date":"2012-08-26","endport":"TACOMA, WA","exporter":"SASKCAN PULSE TRADING INC","goodsdescription":"CANADIAN FOOTBALL RED LENTILS ROBIN TYPE","hscode":"提单号:OOLU2525992990","id":7183129,"iexport":"暂无","importer":"M S FRIENDS ASSOCIATE","orgincountry":"CANADA","quantity":99680,"sortKey":"","startport":"VANCOUVER, BC","totalprice":0,"unitprice":0,"weight":2501911},{"columName":"nodetailinformation","countNum":168,"country":"美国进口","customscity":"暂无","date":"2012-12-03","endport":"Kaohsiung         ","exporter":"TAIWAN SPORTS TRADING LTD","goodsdescription":"FOOTBALL BELT-GREEN","hscode":"提单号:EGLV003202158025","id":11124129,"iexport":"暂无","importer":"CHAMPION SPORTS","orgincountry":"nodetailinformation","quantity":87813,"sortKey":"","startport":"nodetailinformation","totalprice":0,"unitprice":0,"weight":1379912},{"columName":"YANTIAN","countNum":204,"country":"美国进口","customscity":"暂无","date":"2012-07-30","endport":"SEATTLE, WA","exporter":"nodetailinformation","goodsdescription":"FOOTBALL HELMET COMPONENT THIS SHIPMENT CONTAINS NO WOOD PACKING MATERIALS","hscode":"提单号:APLU077234393","id":6163856,"iexport":"暂无","importer":"nodetailinformation","orgincountry":"CHINA","quantity":131781,"sortKey":"","startport":"YANTIAN","totalprice":0,"unitprice":0,"weight":935022},{"columName":"XIAMEN","countNum":34,"country":"美国进口","customscity":"暂无","date":"2012-09-02","endport":"TACOMA, WA","exporter":"nodetailinformation","goodsdescription":"FOOTBALL","hscode":"提单号:EXDO63E1015979","id":7379660,"iexport":"暂无","importer":"nodetailinformation","orgincountry":"CHINA","quantity":30528,"sortKey":"","startport":"XIAMEN","totalprice":0,"unitprice":0,"weight":386286},{"columName":"HONG KONG","countNum":63,"country":"智利","customscity":"VALPARAISO","date":"20120425","endport":"VALPARAISO","exporter":"暂无","goodsdescription":"PARTES PARA FILTRO DE AIRE MOT OR","hscode":"84219900","id":968390,"iexport":"暂无","importer":"IMPORT Y DIST ALAMEDA S A","orgincountry":"TAILANDIA","quantity":45582.92,"sortKey":"","startport":"HONG KONG","totalprice":1829.74,"unitprice":0,"weight":322523},{"columName":"BUSAN CY  PUSAN","countNum":37,"country":"智利","customscity":"SAN ANTONIO","date":"20120412","endport":"SAN ANTONIO","exporter":"暂无","goodsdescription":"DE MOTOR DE ENCEND  POR CHISPA","hscode":"84219900","id":845977,"iexport":"暂无","importer":"GENERAL MOTORS CHILE S A","orgincountry":"ORIG  O DEST  NO PREC","quantity":5134.04,"sortKey":"","startport":"BUSAN CY  PUSAN","totalprice":168717.67,"unitprice":0,"weight":291898.88},{"columName":"BRASIL OTROS PUERTOS","countNum":93,"country":"智利","customscity":"LOS ANDES","date":"20120424","endport":"CHILE OTROS PUERTOS","exporter":"暂无","goodsdescription":"ECHERA","hscode":"84219900","id":954168,"iexport":"暂无","importer":"WATT S S A","orgincountry":"BRASIL","quantity":17144.96,"sortKey":"","startport":"BRASIL OTROS PUERTOS","totalprice":296402.15,"unitprice":0,"weight":285547.1},{"columName":"NING BO","countNum":52,"country":"美国进口","customscity":"暂无","date":"2012-06-18","endport":"LOS ANGELES, CA","exporter":"EVER GLORY ENTERPRISE COMPANY LIMIT","goodsdescription":"WOODENKIDSGARDENTOOL ASST FOAM FOOTBALL BASKETBALL DELUXE","hscode":"提单号:OOLU3074600310","id":4575646,"iexport":"暂无","importer":"NINETY NINE CENT ONLY STORES","orgincountry":"CHINA","quantity":29224,"sortKey":"","startport":"NING BO","totalprice":0,"unitprice":0,"weight":284030},{"columName":"NANJING","countNum":24,"country":"美国进口","customscity":"暂无","date":"2012-06-25","endport":"SEATTLE, WA","exporter":"nodetailinformation","goodsdescription":"762 CARTONS BASKETBALL FOOTBALL SOCCERBALL HS CODE950662","hscode":"提单号:PNEP12550613806","id":4807151,"iexport":"暂无","importer":"nodetailinformation","orgincountry":"CHINA","quantity":16413,"sortKey":"","startport":"NANJING","totalprice":0,"unitprice":0,"weight":280669},{"columName":"SAO PAULO","countNum":38,"country":"智利","customscity":"METROPOLITANA","date":"20120412","endport":"AEROP  A M  BENITEZ","exporter":"暂无","goodsdescription":"LAS DEMAS SEPARADOR AGUA DIESE L PARA AUTOMOVILES","hscode":"84219900","id":834160,"iexport":"暂无","importer":"EMASA  EQUIPOS Y MAQUINAR S A","orgincountry":"BRASIL","quantity":22991.6,"sortKey":"","startport":"SAO PAULO","totalprice":214614.79,"unitprice":0,"weight":227734.12},{"columName":"KAOHSIUNG","countNum":34,"country":"美国进口","customscity":"暂无","date":"2012-05-21","endport":"LOS ANGELES, CA","exporter":"GRANDE INTERNATIONAL ENTERPRISE COR","goodsdescription":"AMERICAN FOOTBALL SHOULDER PAD ACCESSORIES","hscode":"提单号:PIFWSGN30001578","id":3749968,"iexport":"暂无","importer":"KRANOS CORPORATION","orgincountry":"CHINA TAIWAN","quantity":19455,"sortKey":"","startport":"KAOHSIUNG","totalprice":0,"unitprice":0,"weight":171869},{"columName":"BUSAN","countNum":8,"country":"美国进口","customscity":"暂无","date":"2012-07-06","endport":"HOUSTON, TX","exporter":"SPORTS ROYAL","goodsdescription":"PROTECTIVE FOOTBALL SHOULDER PADS","hscode":"提单号:MIQOMNL3119995","id":5226645,"iexport":"暂无","importer":"AMPAC ENTERPRISE INC","orgincountry":"SOUTH KOREA","quantity":5779,"sortKey":"","startport":"BUSAN","totalprice":0,"unitprice":0,"weight":168383},{"columName":"AMBERES","countNum":34,"country":"智利","customscity":"VALPARAISO","date":"20120410","endport":"VALPARAISO","exporter":"暂无","goodsdescription":"PARTE DE FILTRO PARA USO EN MO TOR ENCENDIDO POR COMPRESION D E VEHICULO AUTOMOVIL DE LA PDA  8702","hscode":"84219900","id":811584,"iexport":"暂无","importer":"VOLVO C V Y C E SOUTH CONE LTD","orgincountry":"BELGICA","quantity":3282.03,"sortKey":"","startport":"AMBERES","totalprice":85942.07,"unitprice":0,"weight":149018.16},{"columName":"MIAMI","countNum":234,"country":"智利","customscity":"METROPOLITANA","date":"20120412","endport":"AEROP  A M  BENITEZ","exporter":"暂无","goodsdescription":"PARA FILTRO DE MOTOR DIESEL","hscode":"84219900","id":845640,"iexport":"暂无","importer":"KOMATSU CHILE SA","orgincountry":"ORIG  O DEST  NO PREC","quantity":12158.68,"sortKey":"","startport":"MIAMI","totalprice":529204.55,"unitprice":0,"weight":139933.73},{"columName":"FRANCIA OTROS PUERTOS","countNum":33,"country":"智利","customscity":"SAN ANTONIO","date":"20120424","endport":"SAN ANTONIO","exporter":"暂无","goodsdescription":"PARA FILTRO DE COMBUSTIBLE","hscode":"84219900","id":959347,"iexport":"暂无","importer":"COMERCIAL KAUFMANN S A","orgincountry":"ALEMANIA","quantity":5843.9,"sortKey":"","startport":"FRANCIA OTROS PUERTOS","totalprice":115179.03,"unitprice":0,"weight":137142.41},{"columName":"QINGDAO","countNum":19,"country":"美国进口","customscity":"暂无","date":"2012-05-16","endport":"LOS ANGELES, CA","exporter":"YANTAI NE APPAREL CO LTD","goodsdescription":"SPORTS FOOTBALL GLOVES PO#2L221Z01 L22100 SUPER CHARGE","hscode":"提单号:DMALTAO111697","id":3564827,"iexport":"暂无","importer":"SPORTS LICENSED DIVISION","orgincountry":"CHINA","quantity":23103,"sortKey":"","startport":"QINGDAO","totalprice":0,"unitprice":0,"weight":120695},{"columName":"BALBOA","countNum":20,"country":"智利","customscity":"SAN ANTONIO","date":"20120430","endport":"SAN ANTONIO","exporter":"暂无","goodsdescription":"PARTE DE FILTRO COLECTOR DE PO LVO","hscode":"84219900","id":1030679,"iexport":"暂无","importer":"HORMIGONES SOBARZO LTDA","orgincountry":"ESTADOS UNIDOS","quantity":6733.19,"sortKey":"","startport":"BALBOA","totalprice":202724.87,"unitprice":0,"weight":111605.6},{"columName":"COLON","countNum":9,"country":"美国进口","customscity":"暂无","date":"2012-08-28","endport":"LOS ANGELES, CA","exporter":"SHIPCO TRANSPORT INC LAX","goodsdescription":"FOOTBALL EQUIPMENT 1 X40HC SLAC 10 PALLETS FOOTBALL EQUIPMENT HS CODE 9506997000","hscode":"提单号:EGLV430210225499","id":7197925,"iexport":"暂无","importer":"SHIPCO TRANSPORT JAPAN LTD","orgincountry":"PANAMA","quantity":1984.21,"sortKey":"","startport":"COLON","totalprice":23685.6,"unitprice":0,"weight":100225.35},{"columName":"EE UU OTROS PUERTOS","countNum":122,"country":"智利","customscity":"METROPOLITANA","date":"20120427","endport":"AEROP  A M  BENITEZ","exporter":"暂无","goodsdescription":"DEUSO EN FILTRO DE UNIDAD DE P ERFORACION","hscode":"84219900","id":1010770,"iexport":"暂无","importer":"INFORMACION NO DISPONIBLE","orgincountry":"ESTADOS UNIDOS","quantity":5623.67,"sortKey":"","startport":"EE UU OTROS PUERTOS","totalprice":279308.6,"unitprice":0,"weight":99663.35},{"columName":"INGLATERRA OTROS PUERTOS","countNum":15,"country":"智利","customscity":"METROPOLITANA","date":"20120423","endport":"AEROP  A M  BENITEZ","exporter":"暂无","goodsdescription":"PARA FILTROSDE AIRE","hscode":"84219900","id":951093,"iexport":"暂无","importer":"DISTRIBUIDORA PERKINS CHILENA","orgincountry":"ORIG  O DEST  NO PREC","quantity":5071.08,"sortKey":"","startport":"INGLATERRA OTROS PUERTOS","totalprice":96162.12,"unitprice":0,"weight":95124.12},{"columName":"EVERGLADES","countNum":31,"country":"智利","customscity":"VALPARAISO","date":"20120308","endport":"VALPARAISO","exporter":"暂无","goodsdescription":"PARA FILTRO DE ACEITE","hscode":"84219900","id":785809,"iexport":"暂无","importer":"FINNING CHILE S A","orgincountry":"ORIG  O DEST  NO PREC","quantity":3524.38,"sortKey":"","startport":"EVERGLADES","totalprice":85743.37,"unitprice":0,"weight":91136.3},{"columName":"HAMBURGO","countNum":46,"country":"智利","customscity":"METROPOLITANA","date":"20120418","endport":"AEROP  A M  BENITEZ","exporter":"暂无","goodsdescription":"PARA FILTRO DE MOTOR DIESEL LO S DEMAS","hscode":"84219900","id":895657,"iexport":"暂无","importer":"MAURICIO HOCHSCHILD ING  Y SE","orgincountry":"ALEMANIA","quantity":7337.05,"sortKey":"","startport":"HAMBURGO","totalprice":483725.7,"unitprice":0,"weight":82222.22},{"columName":"TAICHUNG","countNum":8,"country":"美国进口","customscity":"暂无","date":"2012-04-16","endport":"SAN PEDRO, CA","exporter":"nodetailinformation","goodsdescription":"SPORTING GOODS BASEBALL BELT ADULT SPORTING GOODS FOOTBALL FANNY BAG","hscode":"提单号:OOLU2522094760","id":2420203,"iexport":"暂无","importer":"nodetailinformation","orgincountry":"CHINA TAIWAN","quantity":8067,"sortKey":"","startport":"TAICHUNG","totalprice":0,"unitprice":0,"weight":71277},{"columName":"VUNG TAU","countNum":14,"country":"美国进口","customscity":"暂无","date":"2012-05-28","endport":"SAN PEDRO, CA","exporter":"GRANDE INTERNATIONAL ENTERPRISE COR","goodsdescription":"AMERICAN FOOTBALL SHOULDER PAD ACCESSORIES","hscode":"提单号:PIFWSGN30001597","id":3945396,"iexport":"暂无","importer":"KRANOS CORPORATION","orgincountry":"VIETNAM","quantity":6117,"sortKey":"","startport":"VUNG TAU","totalprice":0,"unitprice":0,"weight":68467},{"columName":"MANZANILLO","countNum":13,"country":"智利","customscity":"VALPARAISO","date":"20120307","endport":"VALPARAISO","exporter":"暂无","goodsdescription":"PARTES PARA FILTRO DE AIRE USO EN VEHICULO PDA 87 03","hscode":"84219900","id":778872,"iexport":"暂无","importer":"HONDA MOTOR DE CHILE S A","orgincountry":"ESTADOS UNIDOS","quantity":982.27,"sortKey":"","startport":"MANZANILLO","totalprice":18281.36,"unitprice":0,"weight":58990.77},{"columName":"CRISTOBAL","countNum":2,"country":"智利","customscity":"ANTOFAGASTA","date":"20120425","endport":"PUERTO ANGAMOS","exporter":"暂无","goodsdescription":"PARTE EXCLUSIVAPARA APARATO DE FILTRAR O DEPURAR GASES","hscode":"84219900","id":971541,"iexport":"暂无","importer":"COMPLE  INDUSTRIAL MOLINOR S A","orgincountry":"CANADA","quantity":36443.55,"sortKey":"","startport":"CRISTOBAL","totalprice":681163.26,"unitprice":0,"weight":53943.97},{"columName":"CEBU","countNum":6,"country":"美国进口","customscity":"暂无","date":"2012-06-03","endport":"SAVANNAH, GA","exporter":"SPORTS ROYAL INC","goodsdescription":"PROTECTIVE FOOTBALL SHOULDER PADS ACCESSORIES","hscode":"提单号:COSU6074415010","id":4190363,"iexport":"暂无","importer":"AMPAC ENTERPRISES INC","orgincountry":"PHILIPPINES","quantity":6863,"sortKey":"","startport":"CEBU","totalprice":0,"unitprice":0,"weight":51865},{"columName":"BANGKOK","countNum":15,"country":"美国进口","customscity":"暂无","date":"2012-09-12","endport":"LOS ANGELES, CA","exporter":"nodetailinformation","goodsdescription":"FOOTBALL AND BASKETBALL","hscode":"提单号:UASI7779662726","id":7636938,"iexport":"暂无","importer":"nodetailinformation","orgincountry":"THAILAND","quantity":2714,"sortKey":"","startport":"BANGKOK","totalprice":0,"unitprice":0,"weight":48895},{"columName":"ESPA A OTROS PUERTOS","countNum":16,"country":"智利","customscity":"METROPOLITANA","date":"20120410","endport":"AEROP  A M  BENITEZ","exporter":"暂无","goodsdescription":"N/A","hscode":"84219900","id":819651,"iexport":"暂无","importer":"SIGDOPACK S A","orgincountry":"ALEMANIA","quantity":1684.43,"sortKey":"","startport":"ESPA A OTROS PUERTOS","totalprice":25389.18,"unitprice":0,"weight":48290.45},{"columName":"LAEM CHABANG","countNum":7,"country":"美国进口","customscity":"暂无","date":"2012-08-15","endport":"LOS ANGELES, CA","exporter":"nodetailinformation","goodsdescription":"BASKETBALL AMERICAN FOOTBALL AS PER INV NO 0725 2012","hscode":"提单号:MQNATCC1207195","id":6683684,"iexport":"暂无","importer":"nodetailinformation","orgincountry":"THAILAND","quantity":2281,"sortKey":"","startport":"LAEM CHABANG","totalprice":0,"unitprice":0,"weight":46526},{"columName":"COLOMBIA OTROS PUERTOS","countNum":8,"country":"智利","customscity":"VALPARAISO","date":"20120412","endport":"VALPARAISO","exporter":"暂无","goodsdescription":"PARA FILTRO","hscode":"84219900","id":842085,"iexport":"暂无","importer":"COSMOPLAS S A","orgincountry":"ITALIA","quantity":623.19,"sortKey":"","startport":"COLOMBIA OTROS PUERTOS","totalprice":15386.27,"unitprice":0,"weight":42572.08},{"columName":"TOKYO","countNum":6,"country":"美国进口","customscity":"暂无","date":"2012-08-19","endport":"TACOMA, WA","exporter":"GRANDE INTERNATIONAL ENTERPRISE COR","goodsdescription":"AMERICAN FOOTBALL SHOULDER PAD ACCESSORIES","hscode":"提单号:PIFWSGN12070095","id":6810481,"iexport":"暂无","importer":"KRANOS CORPORATION","orgincountry":"JAPAN","quantity":2965,"sortKey":"","startport":"TOKYO","totalprice":0,"unitprice":0,"weight":35180},{"columName":"WUHU","countNum":8,"country":"美国进口","customscity":"暂无","date":"2012-08-08","endport":"OAKLAND, CA","exporter":"nodetailinformation","goodsdescription":"2PK FOOTBALL BEAD NECKLACE PLASTIC TOYS 2PK SQUISHY","hscode":"提单号:COSU6073960182","id":6440516,"iexport":"暂无","importer":"nodetailinformation","orgincountry":"CHINA","quantity":5271,"sortKey":"","startport":"WUHU","totalprice":0,"unitprice":0,"weight":29244},{"columName":"CALLAO","countNum":7,"country":"智利","customscity":"SAN ANTONIO","date":"20120309","endport":"SAN ANTONIO","exporter":"暂无","goodsdescription":"LOS DEMAS","hscode":"84219900","id":808479,"iexport":"暂无","importer":"EURO IMEXPORT LTDA","orgincountry":"ALEMANIA","quantity":5556.23,"sortKey":"","startport":"CALLAO","totalprice":79832.81,"unitprice":0,"weight":26920.3},{"columName":"CHIWAN","countNum":4,"country":"美国进口","customscity":"暂无","date":"2012-09-20","endport":"SAN PEDRO, CA","exporter":"NOVA SPORTS INC","goodsdescription":"AMERICAN FOOTBALL SHOULDER PAD ACCESSORIES","hscode":"提单号:PIFWCAN12080246","id":7921928,"iexport":"暂无","importer":"KRANOS CORPORATION","orgincountry":"CHINA","quantity":2094,"sortKey":"","startport":"CHIWAN","totalprice":0,"unitprice":0,"weight":23829},{"columName":"PUERTO PLATA","countNum":3,"country":"美国进口","customscity":"暂无","date":"2012-06-25","endport":"MIAMI, FL","exporter":"ATLANTIC MANUFACTURING SA","goodsdescription":"BXS CONT FOOTBALL JERSEY","hscode":"提单号:SMLU3109896A","id":4862236,"iexport":"暂无","importer":"HB ATHLETICS INC","orgincountry":"DOMINICAN REPUBLIC","quantity":771,"sortKey":"","startport":"PUERTO PLATA","totalprice":0,"unitprice":0,"weight":23001},{"columName":"ST. LOUIS, MO","countNum":1,"country":"美国进口","customscity":"暂无","date":"2012-11-27","endport":"Yantian","exporter":"CORTINA GLOBAL CORPORATION","goodsdescription":"SPORTING GOODS FOOTBALL HELMET FACEMASK","hscode":"提单号:COSU6064678991","id":9943001,"iexport":"暂无","importer":"RAWLINGS SPORTING GOODS COMPANY IN","orgincountry":"China","quantity":762,"sortKey":"","startport":"ST. LOUIS, MO","totalprice":0,"unitprice":0,"weight":22642},{"columName":"TAIBEI","countNum":2,"country":"美国进口","customscity":"暂无","date":"2012-05-29","endport":"LOS ANGELES, CA","exporter":"SPACE LINK TRANSPORTATION CO LTD","goodsdescription":"FOOTBALL KICKOFF TEE SPORTING GOODS POLY PRO WEB BELT COACHS BOARD WIT PEN FOOTBALL KICKOFF TEE FOOTBALL PADS","hscode":"提单号:EGLV003200792843","id":4033196,"iexport":"暂无","importer":"THE CAMELOT COMPANY","orgincountry":"CHINA TAIWAN","quantity":1614,"sortKey":"","startport":"TAIBEI","totalprice":0,"unitprice":0,"weight":22534},{"columName":"JEBEL ALI","countNum":1,"country":"美国进口","customscity":"暂无","date":"2012-01-31","endport":"NEWARK, NJ","exporter":"PULSE INDIA FOODSTUFF LLC","goodsdescription":"CHANA DAL RED MASOOR DALL MASOOR FOOTBALL","hscode":"提单号:PGSM1588471509","id":9001269,"iexport":"暂无","importer":"S K VEGETAVLES CORP","orgincountry":"UNITED ARAB EM","quantity":900,"sortKey":"","startport":"JEBEL ALI","totalprice":0,"unitprice":0,"weight":22500},{"columName":"ADANA","countNum":1,"country":"美国进口","customscity":"暂无","date":"2012-07-20","endport":"ELIZABETH, NJ","exporter":"NARIN BYS DIS TICARET A S","goodsdescription":"RED LENTILS FOOTBALL","hscode":"提单号:DFDSMERO1200401","id":6298565,"iexport":"暂无","importer":"BEST FOODS INC","orgincountry":"TURKEY","quantity":860,"sortKey":"","startport":"ADANA","totalprice":0,"unitprice":0,"weight":21543},{"columName":"KIIRUN","countNum":2,"country":"美国进口","customscity":"暂无","date":"2012-04-25","endport":"LOS ANGELES, CA","exporter":"nodetailinformation","goodsdescription":"A SPORTING GOODS JACK CORBETT RUBBER BASE VISOR FOOTBALL FACEGUARD","hscode":"提单号:YMLUW202058453","id":2788117,"iexport":"暂无","importer":"nodetailinformation","orgincountry":"CHINA TAIWAN","quantity":1192,"sortKey":"","startport":"KIIRUN","totalprice":0,"unitprice":0,"weight":20768},{"columName":"YOKOHAMA","countNum":5,"country":"智利","customscity":"SAN ANTONIO","date":"20120420","endport":"SAN ANTONIO","exporter":"暂无","goodsdescription":"","hscode":"84219900","id":925350,"iexport":"暂无","importer":"GENERAL MOTORS CHILE S A","orgincountry":"JAPON","quantity":690.13,"sortKey":"","startport":"YOKOHAMA","totalprice":36466.28,"unitprice":0,"weight":20383},{"columName":"PORT QASIM","countNum":5,"country":"美国进口","customscity":"暂无","date":"2012-07-17","endport":"ELIZABETH, NJ","exporter":"nodetailinformation","goodsdescription":"60 CARTONS FOOTBALL CARY BAGS","hscode":"提单号:WPAKWLP21584KL","id":5591919,"iexport":"暂无","importer":"nodetailinformation","orgincountry":"PAKISTAN","quantity":1859,"sortKey":"","startport":"PORT QASIM","totalprice":0,"unitprice":0,"weight":19765},{"columName":"BALTIMORE","countNum":15,"country":"智利","customscity":"SAN ANTONIO","date":"20120412","endport":"SAN ANTONIO","exporter":"暂无","goodsdescription":"PARA USO EN FILTRO DE GASES","hscode":"84219900","id":842060,"iexport":"暂无","importer":"3M CHILE S A","orgincountry":"ESTADOS UNIDOS","quantity":349.14,"sortKey":"","startport":"BALTIMORE","totalprice":16888.29,"unitprice":0,"weight":18884.18},{"columName":"BUR SAID","countNum":2,"country":"美国进口","customscity":"暂无","date":"2012-03-12","endport":"NEWARK, NJ","exporter":"HI-TEX LTD","goodsdescription":"CARTONS OF SEAMLESS FOOTBALL JERSEY PACKED ON 203 CARTONS","hscode":"提单号:MAEU556384896","id":1323696,"iexport":"暂无","importer":"SPORTS LICENSED DIVISION OF THE","orgincountry":"EGYPT","quantity":10329,"sortKey":"","startport":"BUR SAID","totalprice":0,"unitprice":0,"weight":18760},{"columName":"SHEKOU","countNum":3,"country":"美国进口","customscity":"暂无","date":"2012-09-17","endport":"ELIZABETH, NJ","exporter":"COHESION FREIGHT HK LIMITED","goodsdescription":"GLASS DISPLAY CASE FOOTBALL GLASS DISPLAY CASE","hscode":"提单号:OOLU2017542106","id":7750944,"iexport":"暂无","importer":"COHESION FREIGHT USA INC D B A C","orgincountry":"CHINA","quantity":2715,"sortKey":"","startport":"SHEKOU","totalprice":0,"unitprice":0,"weight":18693},{"columName":"SANTOS","countNum":6,"country":"智利","customscity":"SAN ANTONIO","date":"20120412","endport":"SAN ANTONIO","exporter":"暂无","goodsdescription":"PARTE DE FILTRO   LOS DEMAS","hscode":"84219900","id":843353,"iexport":"暂无","importer":"CURIFOR S A","orgincountry":"BRASIL","quantity":6662.88,"sortKey":"","startport":"SANTOS","totalprice":195509.54,"unitprice":0,"weight":17662.36},{"columName":"AMSTERDAM","countNum":30,"country":"智利","customscity":"METROPOLITANA","date":"20120410","endport":"AEROP  A M  BENITEZ","exporter":"暂无","goodsdescription":"PARTE PARA APARATO FILTRANTE D E MEMBRANAUSO INDUSTRIAL","hscode":"84219900","id":813325,"iexport":"暂无","importer":"ALTAIR S A","orgincountry":"ESTADOS UNIDOS","quantity":1831.11,"sortKey":"","startport":"AMSTERDAM","totalprice":150621.43,"unitprice":0,"weight":15792.86},{"columName":"SINGAPORE","countNum":6,"country":"美国进口","customscity":"暂无","date":"2012-05-09","endport":"LOS ANGELES, CA","exporter":"WORKWEAR LANKA PVT LTD","goodsdescription":"FOOTBALL GLOVES","hscode":"提单号:PIFWCMB12030145","id":3263747,"iexport":"暂无","importer":"CUTTERS GLOVES","orgincountry":"SINGAPORE","quantity":1677,"sortKey":"","startport":"SINGAPORE","totalprice":0,"unitprice":0,"weight":14450}]'
//			}, "post", "json");
//});

function returnMethod() {
};

/**
 * 非空验证<br>
 * 并赋值
 * 
 * @param value
 * @returns {Boolean}
 */
function checkIsNotNull(value) {
	if (value) {
		return value.trim();
	}
	return "";
}

/**
 * 获取所有的国家
 * 
 * @param reportType :
 */
function getAllCountryByJs(reportType) {
	var result = "";
	for (x in reportArray) {
		if (reportType == x) {
			result = reportArray[x];
		} else {
			result = reportArray.ALL;
		}
	}
	return result;
}

/**
 * 预加载{初始化} 勿动!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
$(function() {
	/**
	 * 权库使用的报告类型
	 */
	reportChinaTypeArray = {
		'起运港分析报告' : 'PORTOFSHIPMENT',
		'抵达港分析报告' : 'PORTOFARRIVAL',
		'全球采购商汇总分析报告' : 'IMPORTERSUMMARY',
		'全球供应商汇总分析报告' : 'EXPORTERSUMMARY',
		'原产国汇总分析报告' : 'NATIVE',
		'供货趋势分析报告' : 'TRANSACTIONTREND',
		'供货采购商分析报告' : 'IMPORTERSUMMARY',
		'采购趋势分析报告' : 'TRANSACTIONTREND',
		'供应商渠道分析报告' : 'EXPORTERSUMMARY',
		'采购产品分析报告' : 'GOODSSUM'
	};

	/**
	 * 报告类型对应的国家 配置
	 */
	reportArray = {
		/**
		 * 起运港
		 */
			// 屏蔽国家    '美国进口'
		'PORTOFSHIPMENT' : [ '美国进口', '巴拿马进口', '智利进口', '智利出口','秘鲁进口', '巴拿马出口', '巴基斯坦进口',
				'印度进口', '尼加拉瓜进口', '尼加拉瓜出口', '萨尔瓦多进口', '萨尔瓦多出口', '危地马拉进口',
				'危地马拉出口' ],
			
		/**
		 * 抵达港
		 */
				// 屏蔽国家 '美国进口',  
		'PORTOFARRIVAL' : [ '美国进口', '智利进口', '智利出口','秘鲁出口', '巴基斯坦进口', '越南进口', '尼加拉瓜进口',
				'尼加拉瓜出口', '萨尔瓦多进口', '萨尔瓦多出口', '危地马拉进口', '危地马拉出口' ],
				
		/**
		 * 采购商
		 */
				//  屏蔽国家  '美国进口','阿根廷进口', '俄罗斯进口' , '俄罗斯出口' , 
		'IMPORTERSUMMARY' : [ '美国进口','阿根廷进口', '俄罗斯进口' , '俄罗斯出口' , '乌拉圭进口', '乌拉圭出口', '巴拉圭进口', '巴拉圭出口',
				'秘鲁进口', '厄瓜多尔出口', '智利进口', '哥伦比亚进口', '哥伦比亚出口', '巴西进口', 
				'委内瑞拉进口',  '乌克兰进口', '英国进口', '韩国', '巴基斯坦进口', '越南进口',
				'越南出口', '印度进口', '尼加拉瓜进口', '尼加拉瓜出口', '萨尔瓦多进口', '萨尔瓦多出口',
				'危地马拉进口', '危地马拉出口' ],
		
		/**
		 * 供应商
		 */
				// 屏蔽国家  '美国进口', '俄罗斯', 
		'EXPORTERSUMMARY' : ['美国进口', '俄罗斯',  '巴拉圭进口', '巴拉圭出口', '秘鲁出口', '哥伦比亚进口', '哥伦比亚出口','智利进口','智利出口',
				'委内瑞拉出口', '巴拿马出口', '乌克兰进口', '巴基斯坦进口', '越南进口',
				'越南出口', '尼加拉瓜进口', '尼加拉瓜出口', '萨尔瓦多进口', '萨尔瓦多出口', '危地马拉进口',
				'危地马拉出口'],
				
		/**
		 * 原产国
		 */
		'NATIVE' : [ '阿根廷进口', '俄罗斯进口' , '俄罗斯出口','乌拉圭进口', '乌拉圭出口', '巴拉圭进口', '巴拉圭出口', '秘鲁进口', '智利出口',
				'厄瓜多尔出口', '智利进口', '哥伦比亚进口', '哥伦比亚出口', '巴西进口', '委内瑞拉进口', '秘鲁出口',
				'墨西哥进口', '洪都拉斯进口', '哥斯达尼加', '俄罗斯', '越南进口', '越南出口', '印度进口',
				'尼加拉瓜进口', '尼加拉瓜出口', '萨尔瓦多进口', '萨尔瓦多出口', '危地马拉进口', '危地马拉出口'],
		
		/**
		 * 所有国家				'俄罗斯',
		 */
				// 屏蔽国家  '阿根廷进口', '俄罗斯进口' , '俄罗斯出口' , '美国进口', '中国', 
		'ALL' : ['美国进口','巴基斯坦进口', '韩国',  '印度进口', '乌克兰进口', '英国进口', '俄罗斯进口' ,'俄罗斯出口',
					'墨西哥进口', '哥斯达黎加进口', '哥斯达黎加进口', '洪都拉斯进口', '委内瑞拉进口', '委内瑞拉出口',
					'哥伦比亚进口', '哥伦比亚出口', '厄瓜多尔出口', '秘鲁进口', '秘鲁出口', '巴西进口', '巴拉圭进口',
					'巴拉圭出口', '智利进口', '智利出口', '乌拉圭进口', '乌拉圭出口','阿根廷出口','阿根廷进口'],
				
		/**
		 * 下载全库
		 */
		'ALLDBDOWN' : ['中国','巴基斯坦进口', '韩国',  '印度进口', '乌克兰进口', '英国进口', '俄罗斯进口' ,'俄罗斯出口',
						'墨西哥进口', '哥斯达黎加进口', '哥斯达黎加进口', '洪都拉斯进口', '委内瑞拉进口', '委内瑞拉出口',
						'哥伦比亚进口', '哥伦比亚出口', '厄瓜多尔出口', '秘鲁进口', '秘鲁出口', '巴西进口', '巴拉圭进口',
						'巴拉圭出口', '智利进口', '智利出口', '乌拉圭进口', '乌拉圭出口','阿根廷出口','阿根廷进口'],
						
		/**
		 * 卖家资源库
		 */				
		'BUYER' : ['阿根廷进口','英国进口','巴西进口','玻利维亚进口','智利进口', '中国','哥伦比亚进口', '哥伦比亚出口',
		           '哥斯达黎加进口', '厄瓜多尔出口','厄瓜多尔进口','危地马拉出口','危地马拉进口','印度进口','韩国',
		           '尼加拉瓜出口','尼加拉瓜进口','巴基斯坦进口','巴拿马进口','巴拉圭出口','巴拉圭进口','秘鲁进口','俄罗斯出口',
		           '俄罗斯进口','萨尔瓦多出口','萨尔瓦多进口','乌克兰出口','乌克兰进口','乌拉圭进口','美国进口','委内瑞拉进口','越南出口','越南进口']
	}
	
	/**
	 * 不用过滤的国家
	 */
	//屏蔽国家 : '中国',
	noFilterArray = [  '韩国' ]
	
	noFilterArray = []
	
	/**
	 * 权库  市场分析  同环比用到的key
	 */
	WithArray = ['date','money','weight','quantity','count'];
	WithEnArray = ['日期','金额','重量','数量','次数'];
});

/**
 * 根据中文报告类型获取英文报告类型
 */
function getCountryByChinaTitle(titleName) {
	var result = "";
	for (x in reportChinaTypeArray) {
		if (titleName == x) {
			result = reportChinaTypeArray[x];
		}
	}
	return result;
}

/**
 * 根据报告类型获取国家数组
 * 
 * @param reportName :
 *            报告类型
 */
function getCountryByReoportName(reportName) {
	for (x in reportArray) {
		if (x == reportName) {
			return reportArray[x];
		}
	}
	return reportArray.ALL;
}

/**
 * 验证日期格式
 * 
 * @author WangBo
 */
function verifyDate(d) {
	var datePattern = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
	return datePattern.test(d);
}

/**
 * 获取进出口的标示符
 * 
 * @param value
 * @returns {String}
 */
function getFlexTypeByValue(value) {
	var result = "";
	if ("进口" == value) {
		result = "I";
	} else if ("出口" == value) {
		result = "E";
	}
	return result;
}

/**
 * 正则表达式 验证方法
 * 
 * @param reg
 * @param value
 * @returns true: 验证失败 , false : 成功
 */
function checkObjIsRegular(reg, value) {
	if (reg.test(value)) {
		return true;
	}
	return false;
}

/**
 * 处理格式为 : key && value
 * 
 * @param value
 */
function slpltStrByValue(value) {
	var array = value.split(";");
	var key = "";
	var value = "";
	for (var i = 0; i < array.length; i++) {
		var valueArray = array[i].split(":");
		if (checkIsNotNull(valueArray[0])) {
			key += valueArray[0] + ";";
		}
		if (checkIsNotNull(valueArray[1])) {
			value += valueArray[1] + ";";
		}
	}
	var params = {
		queryKey : key,
		queryValue : value
	};
	return params;
}

//-----------------------------------------对权库 我的客户  && 我的竞争  添加 && 修改  按钮进行处理---------------
/**
 * 隐藏添加和修改页面的标签
 * @param flag : true : 修改 , false : 添加
 */
function updateJsp(flag){
	if (flag) {
		$("#titleSpan").val("修改我的竞争对手");
		$("#sureSpan").hide();
		$("#updateSpan").show();
	} else {
		$("#titleSpan").val("添加我的竞争对手");
		$("#sureSpan").show();
		$("#updateSpan").hide();
	}
}