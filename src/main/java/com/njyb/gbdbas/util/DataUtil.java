package com.njyb.gbdbas.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//处理时间类
public class DataUtil {
	private static String dateFormat[] = { "yyyy-MM-dd HH:mm:ss",
			"yyyy/MM/dd HH:mm:ss", "yyyy年MM月dd日HH时mm分ss秒", "yyyy-MM-dd",
			"yyyy/MM/dd", "yy-MM-dd", "yy/MM/dd", "yyyy年MM月dd日", "HH:mm:ss",
			"yyyyMMddHHmmss", "yyyyMMdd", "yyyy.MM.dd", "yy.MM.dd",
			"yyyyMMddHHmmssSSS", "MM/dd/yyyy", "MM/yyyy", "yyyyMM", "yyyy-MM" };

	public static Timestamp convUtilCalendarToSqlTimestamp(Calendar date) {
		if (date == null)
			return null;
		else
			return new Timestamp(date.getTimeInMillis());
	}

	public static Calendar convSqlTimestampToUtilCalendar(Timestamp date) {
		if (date == null) {
			return null;
		} else {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(date.getTime());
			return gc;
		}
	}

	public static long getDate(String date) {

		String a = date.substring(3);
		String b = date.substring(0, 2);
		String c = a + b;
		try {
			long d = Long.valueOf(c);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	public static long getDateByOperator(String date) {
		try {
			long d = Long.valueOf(date.replaceAll("-", ""));
			return d;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	public static String getDateStr() {
		Date date = new Date();
		String timer = date.getYear() + 1900 + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() + "  " + date.getHours() + ":"
				+ date.getMinutes() + ":" + date.getSeconds();
		return timer;
	}

	/**
	 * 将字符串日期转化成Calendar对象
	 * 
	 * @param dateStr
	 *            其格式为 "yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static Calendar parseDate(String dateStr) {
		if (dateStr == null || dateStr.trim().length() == 0) {
			return null;
		} else {
			Date result = parseDate(dateStr, 0);
			Calendar cal = Calendar.getInstance();
			cal.setTime(result);
			return cal;
		}
	}

	/**
	 * 将Calendar对象转化成字符串日期
	 * 
	 * @param date
	 * @return 其格式为 "yyyy-MM-dd HH:mm:ss"
	 */
	public static String toDateTimeStr(Calendar date) {
		if (date == null)
			return null;
		else
			return (new SimpleDateFormat(dateFormat[0])).format(date.getTime());
	}

	/**
	 * 
	 * @param date
	 * @return 其格式为 "yyyy-MM-dd"
	 */
	public static String toDateStr(Calendar date) {
		if (date == null)
			return null;
		else
			return (new SimpleDateFormat(dateFormat[3])).format(date.getTime());
	}

	public static int calendarMinus(Calendar d1, Calendar d2) {
		if (d1 == null || d2 == null) {
			return 0;
		} else {
			d1.set(11, 0);
			d1.set(12, 0);
			d1.set(13, 0);
			d2.set(11, 0);
			d2.set(12, 0);
			d2.set(13, 0);
			long t1 = d1.getTimeInMillis();
			long t2 = d2.getTimeInMillis();
			long daylong = 0x5265c00L;
			t1 -= t1 % daylong;
			t2 -= t2 % daylong;
			long t = t1 - t2;
			int value = (int) (t / daylong);
			return value;
		}
	}

	/**
	 * 将String类型数据转化成Date数据类型
	 * 
	 * @param dateStr
	 * @param index
	 *            其中index表示dateStr类型格式:<br>
	 *            0: "yyyy-MM-dd HH:mm:ss"<br>
	 *            1: "yyyy/MM/dd HH:mm:ss"<br>
	 *            2: "yyyy年MM月dd日HH时mm分ss秒"<br>
	 *            3: "yyyy-MM-dd"<br>
	 *            4: "yyyy/MM/dd"<br>
	 *            5: "yy-MM-dd"<br>
	 *            6: "yy/MM/dd"<br>
	 *            7: "yyyy年MM月dd日"<br>
	 *            8: "HH:mm:ss"<br>
	 *            9: "yyyyMMddHHmmss"<br>
	 *            10: "yyyyMMdd"<br>
	 *            11: "yyyy.MM.dd"<br>
	 *            12: "yy.MM.dd"<br>
	 *            14: mm/dd/yyyy<br>
	 *            15: mm/yyyy<br>
	 * @return
	 */
	public static Date parseDate(String dateStr, int index) {
		DateFormat df = null;
		String s = dateFormat[index];
		df = new SimpleDateFormat(s);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 处理俄罗斯时间格式 排序季度
	 * 
	 * @param s
	 * @return
	 */
	public static String getDateValueByChinese(String s) {
		// 处理日
		String ds = s.substring(0, s.indexOf("-")).replace(" ", "");
		if (ds.length() == 1) {
			ds = "0" + ds;
		}
		// 处理月
		String ms = s.substring(s.indexOf("-") + 1, s.lastIndexOf("-"));
		if (ms.length() == 3) {
			ms = "0" + ms.replace("月", "");
		} else {
			ms = ms.replace("月", "");
		}

		// 处理年
		String ys = s.substring(0, s.lastIndexOf("-") + 3);

		String yss = ys.substring(ys.lastIndexOf("-") + 1);
		if (Integer.parseInt(yss.replace(" ", "")) < 10) {
			yss = "200" + yss;
		} else {
			yss = "20" + yss;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(yss);
		sb.append("-");
		sb.append(ms.replace(" ", ""));
		sb.append("-");
		sb.append(ds);
		return sb.toString();
	}

	public static Date parseDate(String dateStr, String format) {
		DateFormat df = null;
		df = new SimpleDateFormat(format);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将Date类型数据转化成String数据类型
	 * 
	 * @param date
	 * @param index
	 *            其中index表示Date类型格式:<br>
	 *            0: "yyyy-MM-dd HH:mm:ss"<br>
	 *            1: "yyyy/MM/dd HH:mm:ss"<br>
	 *            2: "yyyy年MM月dd日HH时mm分ss秒"<br>
	 *            3: "yyyy-MM-dd"<br>
	 *            4: "yyyy/MM/dd"<br>
	 *            5: "yy-MM-dd"<br>
	 *            6: "yy/MM/dd"<br>
	 *            7: "yyyy年MM月dd日"<br>
	 *            8: "HH:mm:ss"<br>
	 *            9: "yyyyMMddHHmmss"<br>
	 *            10: "yyyyMMdd"<br>
	 *            11: "yyyy.MM.dd"<br>
	 *            12: "yy.MM.dd"<br>
	 *            13: "yyyyMMddHHmmssSSS"<br>
	 *            14: mm/dd/yyyy<br>
	 * @return
	 */
	public static String parseDate(Date date, int index) {
		if (null == date)
			return "";
		DateFormat df = null;
		df = new SimpleDateFormat(dateFormat[index]);
		return df.format(date);
	}

	public static String parseDate(Date date, String format) {
		if (null == date)
			return "";
		DateFormat df = null;
		df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 得到系统时间戳
	 * 
	 * @return 其格式为 "yyyyMMddHHmmssSSS"
	 */
	public static String getFullStrDateTime() {
		return parseDate(new Date(), "yyyyMMddHHmmssSSS");
	}

	/**
	 * 得到系统时间戳
	 * 
	 * @return 其格式为 "yyyy-MM-dd HH:mm:ss"
	 */
	public static String getSysDateTime() {
		Calendar cal = Calendar.getInstance();
		return toDateTimeStr(cal);
	}

	/**
	 * 得到数小时之后的时间
	 * 
	 * @param old
	 * @param hour
	 *            0 到 24
	 * @return
	 */
	public static Date getLastDate(Date old, int hour) {
		if (old == null) {
			return null;
		}
		return new Date(old.getTime() + hour * 3600l * 1000l);
	}

	/**
	 * 得到数小时\分钟之后的时间
	 * 
	 * @param old
	 * @param hour
	 *            0 到 24
	 * @param minute
	 *            0 到 60
	 * @return
	 */
	public static Date getLastDate(Date old, int hour, int minute) {
		if (old == null) {
			return null;
		}
		return new Date(old.getTime() + hour * 3600l * 1000l + minute * 60l
				* 1000l);
	}

	/**
	 * 得到数小时之前的时间
	 * 
	 * @param old
	 * @param hour
	 *            0 到 24
	 * @return
	 */
	public static Date getPreDate(Date old, int hour) {
		if (old == null) {
			return null;
		}
		return new Date(old.getTime() - hour * 3600l * 1000l);
	}

	/**
	 * 得到数小时\分钟之前的时间
	 * 
	 * @param old
	 * @param hour
	 *            0 到 24
	 * @param minute
	 *            0 到 60
	 * @return
	 */
	public static Date getPreDate(Date old, int hour, int minute) {
		if (old == null) {
			return null;
		}
		return new Date(old.getTime() - hour * 3600l * 1000l - minute * 60l
				* 1000l);
	}

	/**
	 * 判断日期先后
	 * 
	 * @param datef
	 *            第一个日期
	 * @param dates
	 *            第二个日期
	 * @return true datebefore 在dates之后,包括当天
	 * @return false datebefore 在dates之前
	 * @author lqy
	 */
	public static boolean Compare(Date datebefore, Date dates) {
		if (datebefore.before(dates)) {
			return false;
		}
		return true;
	}

	/**
	 * 判断日期是合法
	 * 
	 * @param strDate
	 *            要检查的字符串
	 * @return 如果合法，返回true。反之返回false
	 * 
	 *         <pre>
	 * 检查格式
	 * yyyy-MM-dd
	 * 
	 */
	public static boolean CheckDate(String strDate) {
		// ***********************
		if (strDate == null) {
			return false;
		}
		// ************************
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		dateformat.setLenient(false); // 不可以模糊检查
		try {
			dateformat.parse(strDate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            需要格式化的日期 no null
	 * @return 格式化格式 yyyy-MM-dd
	 */
	public static String FormatDate(Date date) {

		return FormatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 更加自定义格式格式化日期
	 * 
	 * @param date
	 * @param dataFormat
	 * @return
	 */
	public static String FormatDate(Date date, String dataFormat) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(dataFormat).format(date);
	}

	/**
	 * 格式化成中文日期
	 * 
	 * @param strDate
	 *            格式为 "yyyy-MM-dd"
	 * 
	 * @return String 中文日期 格式为 "yyyy年MM月dd日"
	 */
	public static String toChinaDate(String strDate) {
		if (strDate == null || strDate.equals("")) {
			return "";
		}
		return parseDate(parseDate(strDate, 3), 7);
	}

	/**
	 * 将一个java.util.Date类型的变量格式化成yyyyMMddHHmmss的字符串
	 * 
	 * @param date
	 * @return String 格式化成yyyyMMddHHmmss的字符串
	 */
	public static String getDateTimeStr(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(dateFormat[9]).format(date);
	}

	/**
	 * 将日期格式从 String 转到 java.sql.Timestamp 格式
	 * 
	 * @param strDate
	 *            其格式为 "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return Timestamp
	 */
	public static java.sql.Timestamp convStrToTimestamp(String strDate) {
		// assert strDate != null;
		return new java.sql.Timestamp((parseDate(strDate)).getTimeInMillis());
	}

	/**
	 * 将日期格式从 String 转到 java.sql.Timestamp 格式
	 * 
	 * @param strDate
	 *            其格式为 "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return Timestamp
	 */
	public static java.sql.Timestamp getTimestamp(String strDate) {
		if (strDate == null || strDate.trim().length() == 0) {
			return null;
		} else {
			return convStrToTimestamp(strDate);
		}
	}

	/**
	 * 取得系统的当前日期,并以"yyyy-MM-dd"的String类型返回
	 * 
	 * @return String
	 */
	public static String getStrCurrentDate() {
		return toDateStr(Calendar.getInstance());
	}

	/**
	 * 取得系统的当前日期,并以java.util.Date类型返回
	 * 
	 * @deprecated 该方法将在2.1.0时删除，请使用getSysDate()方法代替
	 * @return Date
	 */
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 取得系统的当前日期,并以"2001年01月01日"的String类型返回
	 * 
	 * 
	 * @return String
	 */
	public static String getStrCurrentChinaDate() {
		return toChinaDate(getStrCurrentDate());
	}

	/**
	 * 格式化成中文日期
	 * 
	 * @param sDate
	 *            其格式 "yyyy-MM-dd"
	 * 
	 * @return 中文日期（2001年1月1日）
	 * 
	 */
	public static String toChinaDate(Date sDate) {
		if (sDate == null) {
			return "";
		} else {
			return toChinaDate(FormatDate(sDate));
		}
	}

	/**
	 * 格式化成中文日期
	 * 
	 * @param sDate
	 * @return String 中文日期（2001年01月01日01时）
	 */
	public static String toChinaDate_hh(Date sDate) {
		if (sDate == null) {
			return "";
		}
		return new SimpleDateFormat("yyyy年MM月dd日HH时").format(sDate);
	}

	/**
	 * 将java.util.Date转换为java.sql.Timestamp
	 * 
	 * @param objDate
	 * @return Timestamp
	 */
	public static java.sql.Timestamp convDateToTimestamp(Date objDate) {
		if (objDate == null) {
			return null;
		} else {
			Calendar objCal = Calendar.getInstance();
			objCal.setTime(objDate);
			return new java.sql.Timestamp(objCal.getTimeInMillis());
		}
	}

	/**
	 * 得到当前日期15天后的日期
	 * 
	 * @return 其格式为 yyyy年MM月dd日
	 */
	public static String getStrAfter15Date() {
		Calendar cal = Calendar.getInstance();
		long longDate = cal.getTimeInMillis() + (15l * 3600l * 24l * 1000l);
		cal.setTimeInMillis(longDate);
		return toChinaDate(toDateStr(cal));
	}

	/**
	 * 得到指定日期指定天后的日期
	 * 
	 * @author liuqy
	 * @return 其格式为 yyyy年MM月dd日
	 */
	public static Date getAfterDate(Date date, int days) {
		if (date == null) {
			return null;
		}
		long longDate = date.getTime() + (days * 3600l * 24l * 1000l);
		return new Date(longDate);
	}

	public static String getSimpleDate(String date, int format) {
		// 使用正则表达式进行参数校验：是{数字}或者{-}
		if (date == null || date.equals("")) {
			// throw new IllegalArgumentException("日期参数不能为空！");
			return "";
		}
		// 如果时间含小时和秒的，去掉
		if (date.length() > 10) {
			date = date.substring(0, 10);

		}
		if (date.length() != 1 && date.length() != 2 && date.length() != 4
				&& date.length() != 10) {
			throw new IllegalArgumentException("日期参数长度不对，参数值为：" + date);
		}

		// 如果为2位的，去掉虚0
		if (date.length() == 2 || date.length() == 1) {
			if (format == 2) {
				date = getUpperCaseChinese(date);
			} else if (format == 1) {
				date = format(date);
			} else {
				date = String.valueOf(Integer.parseInt(date));
			}

			return date;
		}

		// 如果是4位的
		if (date.length() == 4) {
			if (format == 0) {
				date = String.valueOf(Integer.parseInt(date));
			} else {
				date = getUpperCaseChinese(date);
			}
			return date;
		}

		// 如果是10位日期型的
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);

		// 决定使用哪一种输出的显示

		if (format == 0) {
			year = String.valueOf(Integer.parseInt(year));
			month = String.valueOf(Integer.parseInt(month));
			day = String.valueOf(Integer.parseInt(day));

			date = year.concat("年").concat(month).concat("月").concat(day)
					.concat("日");
		} else if (format == 2) {
			date = getUpperCaseChinese(year).concat("年")
					.concat(getUpperCaseChinese(month)).concat("月")
					.concat(getUpperCaseChinese(day)).concat("日");
		} else {
			year = String.valueOf(Integer.parseInt(year));
			month = String.valueOf(Integer.parseInt(month));
			day = String.valueOf(Integer.parseInt(day));

			date = getUpperCaseChinese(year).concat("年").concat(format(month))
					.concat("月").concat(format(day)).concat("日");
		}

		return date;
	}

	/**
	 * 将日期转化为汉字大写 有如下三中格式 2002-07-09 -> 二○○二年七月九日 2002-12-29 -> 二○○二年一二月二九日 2002
	 * -> 二○○二 11 -> 一一 29 -> 二九
	 * 
	 * @param date
	 *            其格式为 "yyyy-MM-dd"
	 * @return
	 */
	public static String getUpperCaseChinese(String date) {

		String[] min = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		String[] max = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "○" };
		StringBuffer result = new StringBuffer();

		String aChar = "";
		for (int i = 0; i < date.length(); i++) {
			aChar = date.substring(i, i + 1);
			int index = 0;
			for (index = 0; index < 10; index++) {
				if (aChar.equals(min[index])) {
					break;
				}
			}
			if (index == 10) {
				result.append(aChar);
			} else {
				result.append(max[index]);
			}
		}

		return result.toString();
	}

	/**
	 * 调用会计部分转化大写金额的做法 0 -> ○ 9 -> 9 10 -> 十 15 -> 一十五 20 -> 二十 32 -> 三十二
	 * 
	 * @param s
	 * @return
	 */
	static String format(String s) {
		if (s.length() > 2 || s.length() < 0)
			throw new IllegalArgumentException("参数长度不对！");

		int i = Integer.parseInt(s);
		s = getUpperCaseChinese(String.valueOf(i));
		if (i == 10) {
			s = "十";
		}
		if (i != 0 && i != 10 && i % 10 == 0) {
			s = s.substring(0, 1).concat("十");
		}

		if (i > 10 && i < 20) {
			s = "十".concat(s.substring(1, 2));
		}
		if (i % 10 != 0 && i > 20) {
			s = s.substring(0, 1).concat("十").concat(s.substring(1, 2));
		}
		return s;
	}

	/**
	 * 判断日期
	 * 
	 * @param date
	 *            需要判断的日期 no null
	 * @return 为空时候显示空的年月日，不为空显示原来的数据 用于打印判断。
	 */
	public static String checkdate(String date) {
		if (date == null || date.equalsIgnoreCase("")) {
			date = "年    月    日";
		}
		return date;
	}

	/**
	 * 去掉格式为“yyyyMMddHH”形式的日期和时间的虚0，格式化为形如“2005年1月1日1时”的格式 张爱萍 修改功能调优问题增加
	 * 2005-12-14
	 * 
	 * @param date
	 * @return
	 */
	public static String getSimpleTime(String date) {
		if (date == null || date.equals("")) {
			return "";
		}
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6, 8);
		String hour = date.substring(8, 10);

		year = String.valueOf(Integer.parseInt(year));
		month = String.valueOf(Integer.parseInt(month));
		day = String.valueOf(Integer.parseInt(day));
		hour = String.valueOf(Integer.parseInt(hour));
		date = year.concat("年").concat(month).concat("月").concat(day)
				.concat("日").concat(hour).concat("时");
		return date;
	}

	/**
	 * 日期加减
	 * 
	 * @author zhubin
	 * @date 2013/12/5
	 * @param type
	 *            year(年 ) month(月) quarter(季度)
	 * @param value
	 *            1加一年 -1 减一年 1加一个月 -1减一个月 3加一个季度 -3减一个季度
	 * @return
	 */
	public static String getChangeDate(String type, String date, int value,
			int index) throws Exception {
		String[] strs = new String[] { "yyyy-MM-dd", "MM/yyyy", "yyyyMM" };
		SimpleDateFormat formate = new SimpleDateFormat(strs[index]);
		Date fdate = formate.parse(date);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fdate);
		if (null != type && type.equals(IConstantUtil.YEAR)) {
			calendar.add(1, value);
		} else if (null != type && type.equals(IConstantUtil.MONTH)) {
			calendar.add(2, value);
		} else if (null != type && type.equals(IConstantUtil.QUAETER)) {
			calendar.add(2, value);
		}
		String str = formate.format(calendar.getTime());
		return str;
	}

	/**
	 * 日期加减
	 * 
	 * @author honghao
	 * @date 2013/12/5
	 * @param type
	 *            year(年 ) month(月) quarter(季度)
	 * @param value
	 *            1加一年 -1 减一年 1加一个月 -1减一个月 3加一个季度 -3减一个季度
	 * @return
	 */
	public static String getChangeDate(String type, String date, int value)
			throws Exception {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		// mm/yyyy
		Date fdate = formate.parse(date);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fdate);
		if (null != type && type.equals(IConstantUtil.YEAR)) {
			// 例如用户输入6月30号时，计算上月环比时需要取5月31号
			if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == fdate
					.getDate()) {
				calendar.add(1, value);
				calendar.set(Calendar.DAY_OF_MONTH,
						calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			} else {
				calendar.add(1, value);
			}
		} else if (null != type && type.equals(IConstantUtil.MONTH)) {
			// 例如用户输入6月30号时，计算上月环比时需要取5月31号
			if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == fdate
					.getDate()) {
				calendar.add(2, value);
				calendar.set(Calendar.DAY_OF_MONTH,
						calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			} else {
				calendar.add(2, value);
			}
		} else if (null != type && type.equals(IConstantUtil.QUAETER)) {
			calendar.add(2, value);
		} else if (null != type && type.equals(IConstantUtil.DAY)) {
			calendar.add(Calendar.DAY_OF_MONTH, value);
		}
		String str = formate.format(calendar.getTime());
		return str;
	}

	/**
	 * 动态的日期加减
	 * 
	 * @author zhubin
	 * @date 2013/12/5
	 * @param index
	 *            当前所需要增加或者减少的年份或者月份的所在索引（从1开始）
	 * @param value
	 *            1加一年 -1 减一年 1加一个月 -1减一个月 3加一个季度 -3减一个季度
	 * @param formateType
	 *            需要转换的时间格式
	 * @return
	 */
	public static String getChangeDate(int index, String date, int value,
			String formateType) throws Exception {

		SimpleDateFormat formate = new SimpleDateFormat(formateType);
		Date fdate = formate.parse(date);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fdate);
		calendar.add(index, value);
		String str = formate.format(calendar.getTime());
		return str;
	}

	/**
	 * 计算发送时间与系统时间的差
	 * 
	 * @param sendTime
	 *            : 结束时间
	 * @return int
	 * @throws ParseException 
	 */
	public static int countTime(Date sendTime) throws ParseException {
		long between = (new Date().getTime() - sendTime.getTime()) / 1000;// 除以1000是为了转换成秒
		long hour1 = between % (24 * 3600) / 3600;
		return (int)hour1;
	}
	
	public static void main(String[] args) {
		String s = "vietnam_import";
		System.out.println(s.toUpperCase());
	}
}
