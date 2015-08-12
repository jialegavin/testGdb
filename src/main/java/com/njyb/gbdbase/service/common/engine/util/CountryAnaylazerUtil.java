package com.njyb.gbdbase.service.common.engine.util;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.analysis.pt.PortugueseAnalyzer;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

import com.njyb.common.anayler.lucene.IKAnalyzer;
import com.njyb.gbdbase.model.admincenter.AuthorityFieldModel;

/**
 * 获取不同国家所使用的分词器
 * @author 贾红平
 *
 */
public class CountryAnaylazerUtil {
	public static Analyzer getAnalyzerByCountry(String countryName) {
		// 中国八位+中国十位--中文分词器
		if (countryName.equals(LuceneConstant.CHINA_EIGHT_STRING)
			||countryName.equals(LuceneConstant.DATA_RESOUCE)) {
			
			return new IKAnalyzer();
			
		} 
		// 危地马拉+尼加拉瓜+萨尔瓦多+韩国+美国+墨西哥+哥斯达黎加+巴基斯坦+买家资源库+巴拿马+玻利维亚--标准分词器
		if (countryName.equals(LuceneConstant.USA_IMPORT_STRING)
				||countryName.equals(LuceneConstant.MEXICON_IMPORT_STRING)
				||countryName.equals(LuceneConstant.COSTARICA_IMPORT_STRING)
				||countryName.equals(LuceneConstant.COSTARICA_EXPORT_STRING)
				||countryName.equals(LuceneConstant.PAKISTAN_IMPORT_STRING)
				||countryName.equals(LuceneConstant.UK_IMPORT_STRING)
				||countryName.equals(LuceneConstant.INDIA_IMPORT_STRING)
				||countryName.equals(LuceneConstant.INDIA_EXPORT_STRING)
				||countryName.equals(LuceneConstant.KOREA_STRING)
				||countryName.equals(LuceneConstant.PANAMA_EXPORT_STRING)
				||countryName.equals(LuceneConstant.PANAMA_IMPORT_STRING)
				||countryName.equals(LuceneConstant.GUATEMALA_IMPORT_STRING)
				||countryName.equals(LuceneConstant.GUATEMALA_EXPORT_STRING)
				||countryName.equals(LuceneConstant.NICARAGUA_IMPORT_STRING)
				||countryName.equals(LuceneConstant.NICARAGUA_EXPORT_STRING)
				||countryName.equals(LuceneConstant.SALVATORE_IMPROT_STRING)
				||countryName.equals(LuceneConstant.SALVATORE_EXPORT_STRING)
				||countryName.equals(LuceneConstant.BOLIVIA_IMPORT_STRING)
				)
		{
			return new StandardAnalyzer(Version.LUCENE_48, StandardAnalyzer.STOP_WORDS_SET.EMPTY_SET);
		}
		//俄罗斯+乌克兰--俄语分词器
		if (countryName.equals(LuceneConstant.RUSSIAN_IMPORT_STRING)
				||countryName.equals(LuceneConstant.RUSSIAN_EXPORT_STRING)
				||countryName.equals(LuceneConstant.UKRAINE_IMPORT_STRING)) {
			return new RussianAnalyzer(Version.LUCENE_48,RussianAnalyzer.getDefaultStopSet().EMPTY_SET);
		}
		//葡萄牙+巴西--葡萄牙分词器
		if (countryName.equals(LuceneConstant.BRAZIL_IMPORT_STRING)) {
			return new PortugueseAnalyzer(Version.LUCENE_48,PortugueseAnalyzer.getDefaultStopSet().EMPTY_SET);
			 
		}
		if(countryName.equals(LuceneConstant.VIETNAM_IMPORT_STRING)
				||countryName.equals(LuceneConstant.VIETNAM_EXPORT_STRING)){
			return new StandardAnalyzer(Version.LUCENE_48);
		}
		//西班牙+委内瑞拉+秘鲁+厄瓜多尔+巴拉圭+乌拉圭+阿根廷+智利+洪都拉斯+哥伦比亚+越南--西班牙分词器
		if (countryName.equals(LuceneConstant.VENEZUELAID_IMPORT_STRING)
				||countryName.equals(LuceneConstant.VENEZUELAID_EXPORT_STRING)
				||countryName.equals(LuceneConstant.PERU_IMPORT_STRING)
				||countryName.equals(LuceneConstant.PERU_EXPORT_STRING)
				||countryName.equals(LuceneConstant.ECUADOR_IMPORT_STRING)
				||countryName.equals(LuceneConstant.ECUADOR_EXPORT_STRING)
				||countryName.equals(LuceneConstant.PARAGUAY_IMPORT_STRING)
				||countryName.equals(LuceneConstant.PARAGUAY_EXPORT_STRING)
				||countryName.equals(LuceneConstant.URUGUAY_IMPORT_STRING)
				||countryName.equals(LuceneConstant.URUGUAY_EXPORT_STRING)
				||countryName.equals(LuceneConstant.ARGENTINA_IMPORT_STRING)
				||countryName.equals(LuceneConstant.ARGENTINA_EXPORT_STRING)
				||countryName.equals(LuceneConstant.CHILE_IMPORT_STRING)
				||countryName.equals(LuceneConstant.CHILE_EXPORT_STRING)
				||countryName.equals(LuceneConstant.HONDURAS_IMPORT_STRING)
				||countryName.equals(LuceneConstant.HONDURAS_EXPORT_STRING)
				||countryName.equals(LuceneConstant.COLOM_EXPORT_STRING)
				||countryName.equals(LuceneConstant.COLOM_IMPORT_STRING)) {
			return new SpanishAnalyzer(Version.LUCENE_48,SpanishAnalyzer.getDefaultStopSet().EMPTY_SET);
		}
		else{
			return null;
		}
	}
	
	
	public static File[] getIndexFileByTime(String commonIndexPath,String startTime,String endTime,boolean falg){
		String datestr = null;
		//同步锁
		synchronized (CountryAnaylazerUtil.class) {
			if(AuthorityFieldModel.getAuthorityFieldMap().get("first")!=null){
				if(!AuthorityFieldModel.getAuthorityFieldMap().get("first").toString().equals("1")){
					datestr = (String) AuthorityFieldModel.getAuthorityFieldMap().get("datestr");
				}
			}else{
				datestr = (String) AuthorityFieldModel.getAuthorityFieldMap().get("datestr");
			}
		}
		List<File>fs=new java.util.ArrayList<File>();
		File file=null;
		String commonyear;
		String s_m;
		String e_m;
		String[]months=getActualMonthsBySplit(startTime, endTime);
	    System.out.println(months.toString());
		for(String month:months){
			commonyear=month.substring(0,4);//2012
			s_m=month.substring(5,7);//01
			if (Integer.parseInt(s_m)<10) {
				file=new File(commonIndexPath+"/"+commonyear+"/"+"0"+Integer.parseInt(s_m));//d:/lucene/2012/01
				//判断月份是否存在
				if(isFileExist(file)){
					//排除月份
					excludeMonth(datestr, file, month, fs);
				}
			}
			else{
				file=new File(commonIndexPath+"/"+commonyear+"/"+Integer.parseInt(s_m));
				//判断月份是否存在
				if(isFileExist(file)){
					//排除月份
					excludeMonth(datestr, file, month, fs);
				}
			}
		}
		File[]sf=fs.toArray(new File[fs.size()]);
 		if (sf!=null && sf.length>0) {
			return sf;
		}
		return null;
	}
	
	/**
	 * 排除月份
	 * @param datestr
	 * @param file
	 * @param month
	 * @param fs
	 */
	public static void excludeMonth(String datestr,File file,String month,List<File> fs){
		//排除月份
		if(datestr!=null && !"".equals(datestr)){
			if(datestr.contains(month)){
				//month:2012-01
				fs.add(file);
			}
	    }
		else{
	    	fs.add(file);
	    }
	}
	
	
	
	/**
	 * 判断索引是否存在
	 * @param file
	 * @return
	 */
	public static boolean isFileExist(File file){
		  if(!file.exists()){
			  System.err.println("对不起,不存在这个文件夹!:"+file.getAbsolutePath());
			  return false;
		  }
		  return true;
	}
	
	/**
	 * 根据用户输入的查询时间去寻找索引文件对应的目录
	 * @param commonIndexPath
	 * @param startTime
	 * @param endTime
	 * @param falg
	 * @return
	 */
	/*public static File[] getIndexFileByTime(String commonIndexPath,String startTime,String endTime,boolean falg){
		List<File>fs=new java.util.ArrayList<File>();
		File file=null;
		 
		String commonyear;
		
		String s_m;
		String e_m;
	 
		//同一年
		if (falg) {
			commonyear=startTime.substring(0,4);
			s_m=startTime.substring(5,7);
			e_m=endTime.substring(5,7);
			for (int i = Integer.parseInt(s_m); i <= Integer.parseInt(e_m); i++) {
				if (i<10) {
					file=new File(commonIndexPath+"/"+commonyear+"/"+"0"+i);
				}
				else{
					file=new File(commonIndexPath+"/"+commonyear+"/"+i);

				}
				if (!file.exists()) {
					System.err.println("对不起,不存在这个文件夹!:"+file.getAbsolutePath());
					
				}
				else{
					fs.add(file);
				}
				
			}
			File[]sf=fs.toArray(new File[fs.size()]);
	 		if (sf!=null && sf.length>0) {
				return sf;
			}

		}
		//非同一年
		if (!falg) {
			List<File>s=new java.util.ArrayList<File>();
			List<File>e=new java.util.ArrayList<File>();
			String s_year=startTime.substring(0,4);
			String s_mm=startTime.substring(5,7);
			for (int i = Integer.parseInt(s_mm); i <= 12; i++) {
 				if (i<10) {
					file=new File(commonIndexPath+"/"+s_year+"/"+"0"+i);
					
				}
				else{
					file=new File(commonIndexPath+"/"+s_year+"/"+i);

				}
				if (!file.exists()) {
					System.err.println("对不起,不存在这个文件夹!:"+file.getAbsolutePath());
				}
				else{
					s.add(file);
				}
			}
			
			
			String e_year=endTime.substring(0,4);
			String e_mm=endTime.substring(5,7);
			
			for (int i =1; i <= Integer.parseInt(e_mm); i++) {
				if (i<10) {
					file=new File(commonIndexPath+"/"+e_year+"/"+"0"+i);
					
				}
				else{
					file=new File(commonIndexPath+"/"+e_year+"/"+i);

				}
 				if (!file.exists()) {
					System.err.println("对不起,不存在这个文件夹!:"+file.getAbsolutePath());
				}
				else{
					e.add(file);
				}
				
			}
			fs.addAll(s);
			fs.addAll(e);
			File[]sf=fs.toArray(new File[fs.size()]);
	 		if (sf!=null && sf.length>0) {
				return sf;
			}
			
		}
		
		return null;
	}*/
	
	
	
	
	/**
	 * 算出两段时间内相差的月份
	 * @param start 起始时间
	 * @param end   结束时间
	 * @return
	 */
	public static String[] getActualMonthsBySplit(String start, String end) {
		String splitSign = "-";
		String regex = "\\d{4}" + splitSign + "(([0][1-9])|([1][012]))";
		if (!start.matches(regex) || !end.matches(regex)){
			List<String> list = new ArrayList<String>();
			if (start.compareTo(end) > 0) {
				String temp = start;
				start = end;
				end = temp;
			}
			String temp = start;
			while (temp.compareTo(start) >= 0 && temp.compareTo(end) <= 0) {
				list.add(temp);
				String[] arr = temp.split(splitSign);
				int year = Integer.valueOf(arr[0]);
				int month = Integer.valueOf(arr[1]) + 1;
				if (month > 12) {
					month = 1;
					year++;
				}
				if (month < 10) {
					temp = year + splitSign + "0" + month+splitSign;
				} else {
					temp = year + splitSign + month+splitSign;
				}
			}
			int size = list.size();
			String[] result = new String[size];
			for (int i = 0; i < size; i++) {
				result[i] = list.get(i);
			}
			return result;
		}else{
			
		
			List<String> list = new ArrayList<String>();
			if (start.compareTo(end) > 0) {
				String temp = start;
				start = end;
				end = temp;
			}
			String temp = start;
			while (temp.compareTo(start) >= 0 && temp.compareTo(end) <= 0) {
				list.add(temp);
				String[] arr = temp.split(splitSign);
				int year = Integer.valueOf(arr[0]);
				int month = Integer.valueOf(arr[1]) + 1;
				if (month > 12) {
					month = 1;
					year++;
				}
				if (month < 10) {
					temp = year + splitSign + "0" + month;
				} else {
					temp = year + splitSign + month;
				}
			}
			int size = list.size();
			String[] result = new String[size];
			for (int i = 0; i < size; i++) {
				result[i] = list.get(i);
			}
			return result;
		}
	}
}
