package com.njyb.auth.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import net.sf.json.JSONObject;

import com.google.gson.JsonObject;
import com.njyb.gbdbas.util.DataUtil;
import com.njyb.gbdbase.model.admincenter.AuthorityModel;
import com.njyb.gbdbase.model.admincenter.UserModel;

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
			if(DataUtil.parseDate(startDate,3).getTime() < DataUtil.parseDate(endDate,3).getTime()){
				return true;
			}
		}
		//年-月
		else{
			if(DataUtil.parseDate(fromatDate(startDate,country),17).getTime() < DataUtil.parseDate(fromatDate(endDate,country),17).getTime()){
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
		
		if(DataUtil.parseDate(startDate,3).getTime() >  DataUtil.parseDate(endDate,3).getTime()){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断日期是否相同
	 * @param startDate
	 * @param endDate
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean DateEquals(String startDate,String endDate,String startTime,String endTime,String country){
		//年月日
		if(validate(fromatDate(startDate,country)) && validate(fromatDate(startTime,country))){
			if(DataUtil.parseDate(startDate,3).getTime() >= DataUtil.parseDate(startTime,3).getTime()
					&& DataUtil.parseDate(startDate,3).getTime() <= DataUtil.parseDate(endTime,3).getTime()
					&& DataUtil.parseDate(endDate,3).getTime() >= DataUtil.parseDate(startTime,3).getTime()
					&& DataUtil.parseDate(endDate,3).getTime() <= DataUtil.parseDate(endTime,3).getTime()){
				return false;
			}else{
				return true;
			}
		}
		//年月
		else{
			if(DataUtil.parseDate(fromatDate(startDate,country),17).getTime() >= DataUtil.parseDate(fromatDate(startTime,country),17).getTime()
					&& DataUtil.parseDate(fromatDate(startDate,country),17).getTime() <= DataUtil.parseDate(fromatDate(endTime,country),17).getTime()
					&& DataUtil.parseDate(fromatDate(endDate,country),17).getTime() >= DataUtil.parseDate(fromatDate(startTime,country),17).getTime()
					&& DataUtil.parseDate(fromatDate(endDate,country),17).getTime() <= DataUtil.parseDate(fromatDate(endTime,country),17).getTime()){
				return false;
			}else{
				return true;
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
