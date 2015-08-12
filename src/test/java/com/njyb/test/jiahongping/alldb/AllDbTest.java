package com.njyb.test.jiahongping.alldb;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.njyb.gbdbas.util.PageBeanUtil;
import com.njyb.gbdbase.model.datasearch.common.DataReportSumModel;
import com.njyb.gbdbase.model.datasearch.common.ReportCommonParamModel;
import com.njyb.gbdbase.model.datasearch.common.ReportPropertiesModel;
import com.njyb.gbdbase.model.datasearch.common.SearchCommonParamModel;
import com.njyb.gbdbase.service.common.engine.util.LuceneConstant;
import com.njyb.gbdbase.service.common.engines.IReportEngineService;
import com.njyb.gbdbase.service.common.engines.ISearchEngineService;
/**
 * 贾红平全库测试类
 * @author 贾红平
 *
 */
public class AllDbTest {
	/*报表汇总业务接口*/
	static IReportEngineService reportEngineService=null;
	/*数据检索业务接口*/
	static ISearchEngineService searchEngineService=null;
	/*保存报表配置信息的文件*/
	static ReportPropertiesModel propertiesModel=null;
	/*spring容器上下文对象*/
	static ApplicationContext context=null;
	/*spring配置的核心文件*/
	static String path="config\\core\\applicationContext.xml";
	/*测试查询条件对应的列名*/
	static String[]fields={"date", "hscode","goods_desc"};
	/*查询查询条件对应的值 PARA VEHIC*/
	static String[]values={"2014-01-01,2014-12-31","842199","football"};
	/*要汇总的报告类型*/
	static String reportType="jks";
	/*要检索的国家数组*/
	static String[]countrys={
//		LuceneConstant.ARGENTINA_IMPORT_STRING
//		,LuceneConstant.ARGENTINA_EXPORT_STRING,
//		LuceneConstant.BRAZIL_IMPORT_STRING,
		LuceneConstant.CHILE_IMPORT_STRING};
	/*接收汇总的结果*/
	static Map<String, List<DataReportSumModel>> resultList=null;
	/*接收查询返回的id集合*/
	static List<Integer>searchList=null;
	/*报表查询参数模型*/
	static ReportCommonParamModel reportModel=null;
	/*数据检索查询模型*/
	static SearchCommonParamModel searchModel=null;
	/*分页模型对象*/
	static PageBeanUtil page=new PageBeanUtil(1000);
	/*执行测试功能类型:汇总和检索 sumary:汇总 search:检索*/
	static String oper="sumary";
	public static void main(String[] args) {
		/*初始化上下文*/
		initContext();
		/*遍历国家检索结果*/
		for(String country:countrys){
			switch (country) {
			case LuceneConstant.ARGENTINA_IMPORT_STRING:
				testAngentinaImport("全库测试:阿根廷进口报表");
				break;
			case LuceneConstant.ARGENTINA_EXPORT_STRING:
				testAngentinaExport("全库测试:阿根廷出口报表");
				break;
			case LuceneConstant.BRAZIL_IMPORT_STRING:
				testBrazil("全库测试:巴西");
				break;
			case LuceneConstant.CHILE_IMPORT_STRING:
				testChileImport("全库测试:智利进口报表");
				break;
			case LuceneConstant.CHILE_EXPORT_STRING:
				testChileExport("全库测试:智利出口报表");
				break;
			case LuceneConstant.COLOM_IMPORT_STRING:
				testGlbyImport("全库测试:哥伦比亚进口进口报表");
				break;
			case LuceneConstant.COLOM_EXPORT_STRING:
				testGlbyExport("全库测试:哥伦比亚出口报表");
				break;
			case LuceneConstant.COSTARICA_IMPORT_STRING:
				testGsdnjImport("全库测试:哥斯达尼加进口报表");
				break;
			case LuceneConstant.COSTARICA_EXPORT_STRING:
				testGsdnjExport("全库测试:哥斯达尼加出口报表");
				break;
			case LuceneConstant.ECUADOR_IMPORT_STRING:
				testEcuadorImport("全库测试:厄瓜多尔进口报表");
				break;
			case LuceneConstant.ECUADOR_EXPORT_STRING:
				testEcuadorExport("全库测试:厄瓜多尔出口报表");
				break;
			case LuceneConstant.GUATEMALA_IMPORT_STRING:
				testWdmlImport("全库测试:危地马拉进口报表");
				break;
			case LuceneConstant.GUATEMALA_EXPORT_STRING:
				testWdmlExport("全库测试:危地马拉出口报表");
				break;
			case LuceneConstant.HONDURAS_IMPORT_STRING:
				testHdlsImport("全库测试:洪都拉斯进口报表");
				break;
			case LuceneConstant.HONDURAS_EXPORT_STRING:
				testHdlsExport("全库测试洪都拉斯出口报表");
				break;
			case LuceneConstant.INDIA_IMPORT_STRING:
				testYdImport("全库测试:印度进口报表");
				break;
			case LuceneConstant.KOREA_STRING:
				testKorea("全库测试:韩国进口报表");
				break;
			case LuceneConstant.MEXICON_IMPORT_STRING:
				testMexicon("全库测试:墨西哥进口报表");
				break;
			case LuceneConstant.NICARAGUA_IMPORT_STRING:
				testNjlgImport("全库测试:尼加拉瓜进口报表");
				break;
			case LuceneConstant.NICARAGUA_EXPORT_STRING:
				testNjlgExport("全库测试:尼加拉瓜出口报表");
				break;
			case LuceneConstant.PAKISTAN_IMPORT_STRING:
				testParksitan("全库测试:巴基斯坦进口报表");
				break;
			case LuceneConstant.PANAMA_IMPORT_STRING:
				testBlmImport("全库测试:巴拿马进口报表");
				break;
				
			case LuceneConstant.PANAMA_EXPORT_STRING:
				testBlmExport("全库测试:巴拿马出口报表");
				break;
			case LuceneConstant.PARAGUAY_IMPORT_STRING:
				testParaguayImport("全库测试:巴拉圭进口报表");
				break;
			case LuceneConstant.PARAGUAY_EXPORT_STRING:
				testParaguayExport("全库测试:巴拉圭出口报表");
				break;
			case LuceneConstant.PERU_IMPORT_STRING:
				testPeruImport("全库测试:秘鲁进口报表");
				break;
			case LuceneConstant.PERU_EXPORT_STRING:
				testPeruExport("全库测试:秘鲁出口报表");
				break;
			case LuceneConstant.RUSSIAN_IMPORT_STRING:
				testRussian("全库测试:俄罗斯进口报表");
				break;
			case LuceneConstant.SALVATORE_IMPROT_STRING:
				testSewdImport("全库测试:萨尔瓦多进口报表");
				break;
			case LuceneConstant.SALVATORE_EXPORT_STRING:
				testSewdExport("全库测试:萨尔瓦多出口报表");
				break;
			case LuceneConstant.UK_IMPORT_STRING:
				testUk("全库测试:英国进口报表");
				break;
			case LuceneConstant.UKRAINE_IMPORT_STRING:
				testWkl("全库测试:乌克兰进口报表");
				break;
			case LuceneConstant.URUGUAY_IMPORT_STRING:
				testUruguayImport("全库测试:乌拉圭进口报表");
				break;
			case LuceneConstant.URUGUAY_EXPORT_STRING:
				testUruguayExport("全库测试:乌拉圭出口报表");
				break;
			case LuceneConstant.USA_IMPORT_STRING:
				testUsa("全库测试:美国进口报表");
				break;
			case LuceneConstant.VENEZUELAID_IMPORT_STRING:
				testVnrlImport("全库测试:委内瑞拉进口报表");
				break;
			case LuceneConstant.VENEZUELAID_EXPORT_STRING:
				testVnrlExport("全库测试:委内瑞拉出口报表");
				break;
			case LuceneConstant.VIETNAM_IMPORT_STRING:
				testYnImport("全库测试:越南进口报表");
				break;
			case LuceneConstant.VIETNAM_EXPORT_STRING:
				testYnExport("全库测试:越南出口报表");
				break;
			case LuceneConstant.CHINA_EIGHT_STRING:
				testChinaEight("全库测试:中国报表");
				break;
			default:
				break;
			}
		}
		System.exit(0);
	}
	//初始化spring容器上下文
	public static void initContext(){
		context=new ClassPathXmlApplicationContext(path);
		reportEngineService=context.getBean(IReportEngineService.class);
		searchEngineService=context.getBean(ISearchEngineService.class);
		propertiesModel=context.getBean(ReportPropertiesModel.class);
	}
	//阿根廷进口
	public static void testAngentinaImport(String str){
		search(str, LuceneConstant.ARGENTINA_IMPORT_STRING);
		
	}
	
	//阿根廷出口
	public static void testAngentinaExport(String str){
		search(str, LuceneConstant.ARGENTINA_EXPORT_STRING);
	}
	//巴西进口
	public static void testBrazil(String str){
		search(str, LuceneConstant.BRAZIL_IMPORT_STRING);
	}
	//智利进口
	public static void testChileImport(String str){
		search(str, LuceneConstant.CHILE_IMPORT_STRING);
	}
	//智利出口
	public static void testChileExport(String str){
		search(str, LuceneConstant.CHILE_EXPORT_STRING);
	}
	//哥伦比亚进口
	public static void testGlbyImport(String str){
		search(str, LuceneConstant.COLOM_IMPORT_STRING);
	}
	//哥伦比亚出口
	public static void testGlbyExport(String str){
		search(str, LuceneConstant.COLOM_EXPORT_STRING);
	}
	//哥斯达黎加进口
	public static void testGsdnjImport(String str){
		search(str, LuceneConstant.COSTARICA_IMPORT_STRING);
	}
	//哥斯达黎加出口
	public static void testGsdnjExport(String str){
		search(str, LuceneConstant.COSTARICA_EXPORT_STRING);
	}
	//厄瓜多尔进口
	public static void testEcuadorImport(String str){
		search(str, LuceneConstant.ECUADOR_IMPORT_STRING);
	}
	//厄瓜多尔出口
	public static void testEcuadorExport(String str){
		search(str, LuceneConstant.ECUADOR_EXPORT_STRING);
	}
	//危地拉马进口
	public static void testWdmlImport(String str){
		search(str, LuceneConstant.GUATEMALA_IMPORT_STRING);
	}
	//危地拉马出口
	public static void testWdmlExport(String str){
		search(str, LuceneConstant.GUATEMALA_EXPORT_STRING);
	}
	//洪都拉斯进口
	public static void testHdlsImport(String str){
		search(str, LuceneConstant.HONDURAS_IMPORT_STRING);
	}
	//洪都拉斯出口
	public static void testHdlsExport(String str){
		search(str, LuceneConstant.HONDURAS_EXPORT_STRING);
	}
	//印度进口
	public static void testYdImport(String str){
		search(str, LuceneConstant.INDIA_IMPORT_STRING);
	}
	//棒子国
	public static void testKorea(String str){
		search(str, LuceneConstant.KOREA_STRING);
	}
	//墨西哥进口
	public static void testMexicon(String str){
		search(str, LuceneConstant.MEXICON_IMPORT_STRING);
	}
	//尼加拉瓜进口
	public static void testNjlgImport(String str){
		search(str, LuceneConstant.NICARAGUA_IMPORT_STRING);
	}
	//尼加拉瓜出口
	public static void testNjlgExport(String str){
		search(str, LuceneConstant.NICARAGUA_EXPORT_STRING);
	}
	//巴基斯坦
	public static void testParksitan(String str){
		search(str, LuceneConstant.PAKISTAN_IMPORT_STRING);
	}
	//巴拿马进口
	public static void testBlmImport(String str){
		search(str, LuceneConstant.PANAMA_IMPORT_STRING);
	}
	//巴拿马出口
	public static void testBlmExport(String str){
		search(str, LuceneConstant.PANAMA_EXPORT_STRING);
	} 
	//巴拉圭进口
	public static void testParaguayImport(String str){
		search(str, LuceneConstant.PARAGUAY_IMPORT_STRING);
	}
	//巴拉圭出口
	public static void testParaguayExport(String str){
		search(str, LuceneConstant.PARAGUAY_EXPORT_STRING);
	}
	//秘鲁进口
	public static void testPeruImport(String str){
		search(str, LuceneConstant.PERU_IMPORT_STRING);
	}
	//秘鲁出口
	public static void testPeruExport(String str){
		search(str, LuceneConstant.PERU_EXPORT_STRING);
	}
	//俄罗斯进口
	public static void testRussian(String str){
		search(str, LuceneConstant.RUSSIAN_IMPORT_STRING);
	}
	//萨尔瓦多进口
	public static void testSewdImport(String str){
		search(str, LuceneConstant.SALVATORE_IMPROT_STRING);
	}
	//萨尔瓦多出口
	public static void testSewdExport(String str){
		search(str, LuceneConstant.SALVATORE_EXPORT_STRING);
	}
	//英国
	public static void testUk(String str){
		search(str, LuceneConstant.UK_IMPORT_STRING);
	}
	//乌克兰进口
	public static void testWkl(String str){
		search(str, LuceneConstant.UKRAINE_IMPORT_STRING);
	}
	//乌拉圭进口
	public static void testUruguayImport(String str){
		search(str, LuceneConstant.URUGUAY_IMPORT_STRING);
	}
	//乌拉圭出口
	public static void testUruguayExport(String str){
		search(str, LuceneConstant.URUGUAY_EXPORT_STRING);
	}
	//美国
	public static void testUsa(String str){
		search(str, LuceneConstant.USA_IMPORT_STRING);
	}
	//委内瑞拉进口
	public static void testVnrlImport(String str){
		search(str, LuceneConstant.VENEZUELAID_IMPORT_STRING);
	}
	//委内瑞拉出口
	public static void testVnrlExport(String str){
		search(str, LuceneConstant.VENEZUELAID_EXPORT_STRING);
	}
	//越南进口
	public static void testYnImport(String str){
		search(str, LuceneConstant.VENEZUELAID_IMPORT_STRING);
	}
	//越南出口
	public static void testYnExport(String str){
		search(str, LuceneConstant.VIETNAM_EXPORT_STRING);
	}
	//中国八位
	public static void testChinaEight(String str){
		search(str, LuceneConstant.CHINA_EIGHT_STRING);
	}
	//执行具体查询操作
	private static void search(String str, String country) {
		System.out.println(str);
		if(oper.equals("search")){
			searchModel=new SearchCommonParamModel(fields, values, country, "search", null, page);
			searchList=searchEngineService.getListKey(searchModel);
			if (searchList!=null) {
				System.out.println(country+"查询结果:"+searchList.size()+"条");				
			}
			else{
				System.out.println(country+"没有查询到结果集");
			}
		}
		if (oper.equals("sumary")) {
			reportModel=new ReportCommonParamModel(fields, values, country, "report", null, reportType);
			resultList=reportEngineService.builderMapList(reportModel, true);
			if(resultList!=null){
				for(Object o:resultList.keySet()){
					List<DataReportSumModel>list=resultList.get(o);
					if (list!=null) {
						for(DataReportSumModel data:list){
							try {
								String name=propertiesModel.getReportFieldMap().get(reportType).toString();
								String value=BeanUtils.getProperty(data, name);
								System.out.println(value+":"+data.getTradeMoney()+":"+data.getTradeQuantity()+":"+data.getTradeWeight()+":"+data.getTradePackage()+":"+data.getTradeSize());
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					else{
						System.out.println(country+":"+"对不起根据您输入的条件查询不到结果集!");
					}
				}
			}
			
		}
	}
}
