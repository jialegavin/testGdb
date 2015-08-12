package com.njyb.auth.service.authoritycontrol.component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.njyb.gbdbas.util.DataUtil;
/**
 * 处理权限工具类
 * @author 章华才
 *
 */
public class AuthorityDateUtil {

	/**
	 * 统一日期
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String singleDate(String startDate,String endDate){
		return null;
	}
	
	
	/**
	 * 使用日期加3个月的国外数据
	 * @param startDate 账号有效期起始日期
	 * @return
	 */
	public static String dateAddThree(String startDate,String counrty){
		startDate =  DataUtil.parseDate(DataUtil.parseDate(startDate,0),3);
		//判断年-月  /年-月-日
		if(validate(startDate)){
			String [] date = startDate.split("-");
			if(Integer.parseInt(date[1])+3 <= 12){
				date[1] = String.valueOf(Integer.parseInt(date[1])+3);
			}else{
				if(Integer.parseInt(date[1])+3 > 12){
					date[1] = String.valueOf((Integer.parseInt(date[1])+3)-12);
				}
				date[0] = String.valueOf((Integer.parseInt(date[0]) + 1));
			}
			if(date[1].length()<2){
				return date[0] + "-" + "0"+date[1] + "-" + date[2];
			}else{
				return date[0] + "-" + date[1] + "-" + date[2];
			}
		}else{
			String [] date = startDate.split("-");
			if(Integer.parseInt(date[1])+3 <= 12){
				date[1] = String.valueOf(Integer.parseInt(date[1])+3);
			}else{
				if(Integer.parseInt(date[1])+3 > 12){
					date[1] = String.valueOf((Integer.parseInt(date[1])+3)-12);
				}
				date[0] = String.valueOf((Integer.parseInt(date[0]) + 1));
			}
			if(date[1].length()<2){
				return date[0] + "-" + "0"+date[1];
			}else{
				return date[0] + "-" + date[1];
			}
		}
		
	}
	
	
	//测试用的
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String s = format.format(new Date());
		System.out.println(s);
	}
	
	/**
	 * 判断截止日期是否大于现在日期
	 * @param startDate 开始日期
	 * @param endDate   截止日期
	 * @return
	 */
	public static boolean isDateEquals(String startDate,String endDate,String country){
		//年月日
		if(validate(fromatDate(endDate,country))){
			if(DataUtil.parseDate(endDate,3).getTime() > DataUtil.parseDate(startDate,3).getTime() ){
				return true;
			}
		}
		//年-月
		else{
			if( DataUtil.parseDate(fromatDate(endDate,country),17).getTime() > DataUtil.parseDate(fromatDate(startDate,country),17).getTime()){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 判断截止日期是否大于现在日期
	 * @param startDate 开始日期
	 * @param endDate   截止日期
	 * @return
	 */
	public static boolean isDateEquals(String endDate,String country){
		//年月日
		if(validate(fromatDate(DataUtil.parseDate(DataUtil.parseDate(endDate,5),3),country))){
			if(DataUtil.parseDate(DataUtil.parseDate(new Date(),3),3).getTime() >  DataUtil.parseDate(endDate,3).getTime()){
				return true;
			}
		}
		//年月
		else{
			if(DataUtil.parseDate(DataUtil.parseDate(new Date(),17),17).getTime() >  DataUtil.parseDate(fromatDate(endDate,country),17).getTime()){
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * 判断日期大小
	 * @param startDate 开始日期
	 * @param endDate   截止日期
	 * @return
	 */
	public static boolean isDateSize(String startDate,String endDate,String country){
		//年月日
		if(validate(fromatDate(startDate,country)) && validate(fromatDate(endDate,country))){
			if(DataUtil.parseDate(startDate,3).getTime() >  DataUtil.parseDate(endDate,3).getTime()){
				return true;
			}
		}
		//年月
		else{
			if(DataUtil.parseDate(fromatDate(startDate,country),17).getTime() >  DataUtil.parseDate(fromatDate(endDate,country),17).getTime()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断日期是否相同
	 * @param startDate 页面输入的时间
	 * @param endDate	页面输入的时间
	 * @param startTime	数据库对应的时间
	 * @param endTime	数据库对应的时间
	 * @return
	 */
	public static boolean chinaDateEquals(String startDate,String endDate,String startTime,String endTime,String historyStartDate,String historyEndDate,String country){
		boolean istrue = false;
		String b3 = startTime;
		String e3 = endTime;
		
		String b2 = historyStartDate;
		String e2 = historyEndDate;
		
		String b1 = AuthorityDateUtil.fromatDate(startDate, null);
		String e1 = AuthorityDateUtil.fromatDate(endDate, null);
	   
		
		String time = b3 + "," + e3 + "," +b2 + "," + e2;
		if(time.contains(e1)){
			if(Integer.parseInt(e1.split("-")[1]) + 1 < 10){
				e1 = e1.split("-")[0] + "-" + "0" + String.valueOf(Integer.parseInt(e1.split("-")[1]) + 1);
			}else if((Integer.parseInt(e1.split("-")[1]) + 1) > 12 ){
				e1 = String.valueOf(Integer.parseInt(e1.split("-")[0])+1) + "-" + "0" + String.valueOf((Integer.parseInt(e1.split("-")[1]) + 1)-12);
			}else{
				e1 = e1.split("-")[0] + "-" + String.valueOf(Integer.parseInt(e1.split("-")[1]) + 1);
			}
			System.out.println(e1);
		}
		if(time.contains(b1)){
			if(Integer.parseInt(b1.split("-")[1]) + 1 < 10){
				b1 = b1.split("-")[0] + "-" + "0" + String.valueOf(Integer.parseInt(b1.split("-")[1]) + 1);
			}else if((Integer.parseInt(b1.split("-")[1]) + 1) > 12 ){
				b1 = String.valueOf(Integer.parseInt(b1.split("-")[0])+1) + "-" + "0" + String.valueOf((Integer.parseInt(b1.split("-")[1]) + 1)-12);
			}else{
				b1 = b1.split("-")[0] + "-" + String.valueOf(Integer.parseInt(b1.split("-")[1]) + 1);
			}
			System.out.println(b1);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
		
		try {
			if(sdf.parse(b2).after(sdf.parse(b1)) && sdf.parse(b2).before(sdf.parse(e1))){
//				System.out.println("第一时间段和第二时间段交叉");
				istrue = true;
			}else if(sdf.parse(e2).after(sdf.parse(b1)) && sdf.parse(e2).before(sdf.parse(e1))){
//				System.out.println("第一时间段和第二时间段交叉");
				istrue = true;
			}else if(sdf.parse(b3).after(sdf.parse(b1)) && sdf.parse(b3).before(sdf.parse(e1))){
//				System.out.println("第一时间段和第三时间段交叉");
				istrue = true;
			}else if(sdf.parse(e3).after(sdf.parse(b1)) && sdf.parse(e3).before(sdf.parse(e1))){
//				System.out.println("第一时间段和第三时间段交叉");
				istrue = true;
			}else if(sdf.parse(b3).after(sdf.parse(b2)) && sdf.parse(b3).before(sdf.parse(e2))){
//				System.out.println("第二时间段和第三时间段交叉");
				istrue = true;
			}else if(sdf.parse(e3).after(sdf.parse(b2)) && sdf.parse(e3).before(sdf.parse(e2))){
//				System.out.println("第二时间段和第三时间段交叉");
				istrue = true;
			}else if(sdf.parse(b1).before(sdf.parse(b2)) && sdf.parse(e1).after(sdf.parse(e2))){
//				System.out.println("第二时间段在第一时间段中间");
				istrue = true;
			}else if(sdf.parse(b2).before(sdf.parse(b1)) && sdf.parse(e2).after(sdf.parse(e1))){
//				System.out.println("第一时间段在第二时间段中间");
				istrue = true;
			}else if(sdf.parse(b1).before(sdf.parse(b3)) && sdf.parse(e1).after(sdf.parse(e3))){
//				System.out.println("第三时间段在第一时间段中间");
				istrue = true;
			}else if(sdf.parse(b3).before(sdf.parse(b1)) && sdf.parse(e3).after(sdf.parse(e1))){
//				System.out.println("第一时间段在第三时间段中间");
				istrue = true;
			}else if(sdf.parse(b2).before(sdf.parse(b3)) && sdf.parse(e2).after(sdf.parse(e3))){
//				System.out.println("第三时间段在第二时间段中间");
				istrue = true;
			}else if(sdf.parse(b3).before(sdf.parse(b2)) && sdf.parse(e3).after(sdf.parse(e2))){
//				System.out.println("第二时间段在第三时间段中间");
				istrue = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return istrue;
	}
	
	
	
	
	/**
	 * 判断日期是否相同
	 * @param startDate 页面输入的时间
	 * @param endDate	页面输入的时间
	 * @param startTime	数据库对应的时间
	 * @param endTime	数据库对应的时间
	 * @return
	 */
	public static boolean authorityCountryOutDate(String startDate,String endDate,String startTime,String endTime,String country){
		//年月日
		if(validate(fromatDate(startDate,country)) && validate(fromatDate(startTime,country))){
			
			if(DataUtil.parseDate(startDate,3).getTime() < DataUtil.parseDate(startTime,3).getTime()
					&& DataUtil.parseDate(endDate,3).getTime() >  DataUtil.parseDate(endTime,3).getTime())
			{
				return true;
			}else{
				return false;
			}
//			if(DataUtil.parseDate(startDate,3).getTime() < DataUtil.parseDate(endTime,3).getTime())
//			{
//				return false;
//			}else{
//				return true;
//			}
			
//			if(DataUtil.parseDate(startDate,3).getTime() >= DataUtil.parseDate(startTime,3).getTime()
//					&& DataUtil.parseDate(startDate,3).getTime() <= DataUtil.parseDate(endTime,3).getTime()
//					&& DataUtil.parseDate(endDate,3).getTime() >= DataUtil.parseDate(startTime,3).getTime()
//					&& DataUtil.parseDate(endDate,3).getTime() <= DataUtil.parseDate(endTime,3).getTime()){
//				return false;
//			}else{
//				return true;
//			}
		}
		//年月
		else{
			if((DataUtil.parseDate(fromatDate(startTime,country),17).getTime() <= DataUtil.parseDate(fromatDate(startDate,country),17).getTime() || 
					DataUtil.parseDate(fromatDate(startDate,country),17).getTime() < DataUtil.parseDate(fromatDate(endTime,country),17).getTime())
					&& DataUtil.parseDate(fromatDate(endTime,country),17).getTime() >= DataUtil.parseDate(fromatDate(startDate,country),17).getTime()
					&& (DataUtil.parseDate(fromatDate(endTime,country),17).getTime() <=  DataUtil.parseDate(fromatDate(endDate,country),17).getTime() 
					|| DataUtil.parseDate(fromatDate(endDate,country),17).getTime() <=  DataUtil.parseDate(fromatDate(endTime,country),17).getTime()))
			{
				return false;
			}else{
				return true;
			}
//			if(DataUtil.parseDate(fromatDate(startDate,country),17).getTime() < DataUtil.parseDate(fromatDate(endTime,country),17).getTime())
//			{
//				return false;
//			}else{
//				return true;
//			}
//			if(DataUtil.parseDate(fromatDate(startDate,country),17).getTime() >= DataUtil.parseDate(fromatDate(startTime,country),17).getTime()
//					&& DataUtil.parseDate(fromatDate(startDate,country),17).getTime() <= DataUtil.parseDate(fromatDate(endTime,country),17).getTime()
//					&& DataUtil.parseDate(fromatDate(endDate,country),17).getTime() >= DataUtil.parseDate(fromatDate(startTime,country),17).getTime()
//					&& DataUtil.parseDate(fromatDate(endDate,country),17).getTime() <= DataUtil.parseDate(fromatDate(endTime,country),17).getTime()){
//				return false;
//			}else{
//				return true;
//			}
		}
		
	}
	
	
	
	
	
	
	public static boolean DateEqualsOut(String startDate,String endDate,String startTime,String endTime,String country){
		//年月日
		if(validate(fromatDate(startDate,country)) && validate(fromatDate(endTime,country))){
			if((DataUtil.parseDate(startDate,3).getTime() <= DataUtil.parseDate(endTime,3).getTime()))
			{
				return true;
			}else{
				return false;
			}
		}
		//年月
		else{
			if((DataUtil.parseDate(fromatDate(startDate,country),17).getTime() <= DataUtil.parseDate(fromatDate(endTime,country),17).getTime()))
			{
				return true;
			}else{
				return false;
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean DateEqualsChina(String startDate,String endDate,String startTime,String endTime,String country){
		//年月日
		if(validate(fromatDate(startDate,country)) && validate(fromatDate(endTime,country))){
			if((DataUtil.parseDate(startDate,3).getTime() <= DataUtil.parseDate(endTime,3).getTime())
					&& (DataUtil.parseDate(endDate,3).getTime() >= DataUtil.parseDate(startTime,3).getTime()))
			{
				return true;
			}else{
				return false;
			}
		}
		//年月
		else{
			if((DataUtil.parseDate(fromatDate(startDate,country),17).getTime() <= DataUtil.parseDate(fromatDate(endTime,country),17).getTime())
					&& (DataUtil.parseDate(fromatDate(endDate,country),17).getTime() >= DataUtil.parseDate(fromatDate(startTime,country),17).getTime()))
			{
				return true;
			}else{
				return false;
			}
		}
		
	}
	
	public static String getDateStr(String date){
		String splitSign = "/";
		String regex = "(([0][1-9])|([1][012]))" +"/" + "\\d{4}";
		if (!date.matches(regex)){
			return date.split("-")[0]+"-"+date.split("-")[1];
		}else{
			return date.split("/")[1]+"-"+date.split("/")[0];
		}
		
	}
	
	public static boolean DateEqualsChina3(String startDate,String endDate,String startTime,String endTime,String nowStartDate,String nowEndDate,String country){
		//年月日
		if(validate(fromatDate(startDate,country)) && validate(fromatDate(endTime,country))){
			if((DataUtil.parseDate(startDate,3).getTime() <= DataUtil.parseDate(endTime,3).getTime())
					&& (DataUtil.parseDate(endDate,3).getTime() >= DataUtil.parseDate(startTime,3).getTime()))
			{
				return true;
			}else{
				if((DataUtil.parseDate(startDate,3).getTime() <= DataUtil.parseDate(nowEndDate,3).getTime())
						&& (DataUtil.parseDate(endDate,3).getTime() >= DataUtil.parseDate(nowStartDate,3).getTime())){
					return true;
				}
				return false;
			}
		}
		//年月
		else{
			if((DataUtil.parseDate(fromatDate(startDate,country),17).getTime() <= DataUtil.parseDate(fromatDate(endTime,country),17).getTime())
					&& (DataUtil.parseDate(fromatDate(endDate,country),17).getTime() >= DataUtil.parseDate(fromatDate(startTime,country),17).getTime()))
			{
				return true;
			}else{
				if((DataUtil.parseDate(fromatDate(startDate,country),17).getTime() <= DataUtil.parseDate(fromatDate(nowEndDate,country),17).getTime())
						&& (DataUtil.parseDate(fromatDate(endDate,country),17).getTime() >= DataUtil.parseDate(fromatDate(nowStartDate,country),17).getTime()))
				{
					return true;
				}
				return false;
			}
		}
		
	}
	
	/**
	 * 针对中国
	 * @param startDate
	 * @param endDate
	 * @param startTime
	 * @param endTime
	 * @param country
	 * @return
	 */
	public static boolean DateChina(String startDate,String endDate,String startTime,String endTime,String country){
		//年月日
		if(validate(fromatDate(startDate,country)) && validate(fromatDate(startTime,country))){
			if((DataUtil.parseDate(startDate,3).getTime() <= DataUtil.parseDate(startTime,3).getTime()
					&& (DataUtil.parseDate(endDate,3).getTime() >= DataUtil.parseDate(endTime,3).getTime()
					  || (DataUtil.parseDate(endDate,3).getTime() <= DataUtil.parseDate(endTime,3).getTime() && 
							  DataUtil.parseDate(endDate,3).getTime() >= DataUtil.parseDate(startTime,3).getTime())))
					)
			{
				return true;
			}else{
				return false;
			}
		}
		//年月
		else{
			if((DataUtil.parseDate(fromatDate(startDate,country),17).getTime() <= DataUtil.parseDate(fromatDate(startTime,country),17).getTime()
					&& (DataUtil.parseDate(fromatDate(endDate,country),17).getTime() >= DataUtil.parseDate(fromatDate(endTime,country),17).getTime()
					|| (DataUtil.parseDate(fromatDate(endDate,country),17).getTime() <= DataUtil.parseDate(fromatDate(endTime,country),17).getTime()
							&& DataUtil.parseDate(fromatDate(endDate,country),17).getTime() >= DataUtil.parseDate(fromatDate(endTime,country),17).getTime())))
					)
			{
				return true;
			}else{
				return false;
			}
		}
		
	}
	
	/**
	 * 针对中国
	 * @param startDate
	 * @param endDate
	 * @param startTime
	 * @param endTime
	 * @param country
	 * @return
	 */
	public static boolean DateChina2(String startDate,String endDate,String startTime,String endTime,String country){
		//年月日
		if(validate(fromatDate(startDate,country)) && validate(fromatDate(startTime,country))){
			if( (DataUtil.parseDate(startDate,3).getTime() >= DataUtil.parseDate(startTime,3).getTime()
							&& DataUtil.parseDate(endDate,3).getTime() >= DataUtil.parseDate(endTime,3).getTime()))
			{
				return true;
			}else{
				return false;
			}
		}
		//年月
		else{
			if( DataUtil.parseDate(fromatDate(startDate,country),17).getTime() >= DataUtil.parseDate(fromatDate(startTime,country),17).getTime()
					&& DataUtil.parseDate(fromatDate(endDate,country),17).getTime() >= DataUtil.parseDate(fromatDate(endTime,country),17).getTime()
					)
			{
				return true;
			}else{
				return false;
			}
		}
		
	}
	
	
	/**
	 * 验证日期
	 * @param date
	 * @return
	 */
	public static boolean validate(String date){
		String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			return false;
		}
		return true;
	}
	
	/**
	 * 验证日期
	 * @param date
	 * @return
	 */
	public static boolean validate2(String date){
		String eL = "[0-9]{2}/[0-9]{4}";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			return false;
		}
		return true;
	}
	
	/**
	 * 针对对比和贸易
	 * @param country
	 * @param date
	 * @return
	 */
	public static String fromatDate(String date,String country){
		// 月/年
		if(validate2(date)){
			String [] time = date.split("/");
			date = time[1] +"-"+ time[0];
		}
		return date;
	}
	
}
