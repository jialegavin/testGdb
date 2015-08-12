package com.njyb.gbdbas.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

/**
 * 
 * 字符串处理工具类
 * 
 * @author honghao
 * @version [1.0, 2013-08-07]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class StringUtil
{
    /**
     * toString方法,避免空指针
     * 
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String toString(Object obj)
    {
        if (null == obj)
        {
            return "";
        }
        // 如果是数组则分开拼接
        if (obj instanceof Object[])
        {
            return toString((Object[])obj, ",");
        }
        return obj.toString();
    }
    
    /**
     * 将strMessages中的字符串用symbol拼接成String
     * 
     * @param strMessages 信息字符串数组
     * @return
     */
    public static String toString(Object[] strMessages, String symbol)
    {
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < strMessages.length; i++)
        {
            Object obj = strMessages[i];
            if (obj instanceof Object[])
            {
                strBuf.append("{").append(toString((Object[])obj, ",")).append("}");
            }
            else
            {
                strBuf.append(obj);
            }
            if (i < strMessages.length - 1)
            {
                strBuf.append(symbol);
            }
        }
        return strBuf.toString();
    }
    
    /**
     * 字符串转小写,避免空指针
     * 
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String toLowerCase(String str)
    {
        if (null == str)
        {
            return str;
        }
        return str.toLowerCase(Locale.getDefault());
    }
    
    /**
     * 字符串转小写,避免空指针
     * 
     * @param obj
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String toLowerCase(Object obj)
    {
        return toLowerCase(toString(obj));
    }
    
    /**
     * 字符串转大写,避免空指针
     * 
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String toUpperCase(String str)
    {
        if (null == str)
        {
            return str;
        }
        return str.toUpperCase(Locale.getDefault());
    }
    
    /**
     * 字符串转大写,避免空指针
     * 
     * @param obj
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String toUpperCase(Object obj)
    {
        return toUpperCase(toString(obj));
    }
    
    /**
     * 字符串替换. 不使用正则表达式
     * 
     * @param str String 原字符串
     * @param subString String 需要被替换的子串
     * @param replacement String 替换成的子串
     * @return String 替换完成的字符串
     */
    public static String replaceAll(String str, String subString, String replacement)
    {
        if (null == str || null == subString || null == replacement || "".equals(subString))
        {
            return str;
        }
        StringBuffer sb = new StringBuffer();
        String temp = str;
        int index;
        while (-1 != (index = temp.indexOf(subString)))
        {
            sb.append(temp.subSequence(0, index));
            sb.append(replacement);
            temp = temp.substring(index + subString.length());
        }
        sb.append(temp);
        return sb.toString();
    }
    
    /**
     * 判断是否为空字符串
     * 
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(String str)
    {
        if (null == str || 0 == str.trim().length())
        {
            return true;
        }
        return false;
    }
    
    /**
     * 判断内容是否为空（N/A或空串或NUll）
     * @param str
     * @return
     */
    public static boolean isNone(String str)
    {
        if (null == str || 0 == str.trim().length()||"N/A".equalsIgnoreCase(str))
        {
            return true;
        }
        return false;
    }
    /**
     * 对象数组toString
     * 
     * @param objs Object[] 对象数组
     * @return String 对象数组的字符串表达
     */
    public static String arrayToString(Object[] objs)
    {
        if (null != objs)
        {
            StringBuffer objsBuffer = new StringBuffer();
            objsBuffer.append("{");
            for (int i = 0; i < objs.length; i++)
            {
                objsBuffer.append(objs[i]);
                if (i < objs.length - 1)
                {
                    objsBuffer.append(",");
                }
            }
            objsBuffer.append("}");
            return objsBuffer.toString();
        }
        return "{null}";
    }
    
    /**
     * 判断字符串是否在某字符串数组中,可选择是否区分大小写.
     * 
     * @param str 字符串
     * @param array 字符串数组
     * @param secern 是否区分大小写,true:区分;false:不区分
     * @return
     */
    public static boolean isStringInStringArray(String str, String[] array, boolean secern)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (secern && array[i].equals(str))
            {
                return true;
            }
            else if (array[i].equalsIgnoreCase(str))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 请使用HtmlTools中的同名方法,将Java中字符串转换成JS变量值 其实是将转义字符输出,并去掉字符串中回车换行
     * 
     * @param str String
     * @return String
     */
    // public static String toJsValue(String str)
    // {
    // if (null == str || "".equals(str))
    // {
    // return str;
    // }
    // String hstr = str;
    // // 转义斜线
    // hstr = hstr.replaceAll("\\x5C", "\\\\\\\\");
    //
    // // 转义回车
    // hstr = hstr.replaceAll("\r", "\\\\\\r");
    // hstr = hstr.replaceAll("[\n]", "\\\\\\n");
    //
    // // 转义双引号
    // // hstr = hstr.replaceAll("\"", "\\\\\"");
    // hstr = hstr.replaceAll("\\x22", "\\\\\\x22");
    //
    // // 转义单引号
    // hstr = hstr.replaceAll("\'", "\\\\\'");
    // // begin add by zhangyi 20090224
    // // 页面输入<script>a</script>会有问题，所以增加转换
    // hstr = hstr.replaceAll("\\x3C", "\\\\\\<");
    // hstr = hstr.replaceAll("\\x3E", "\\\\\\>");
    // // end add by zhangyi 20090224
    // return hstr;
    // }
    /**
     * 将Java中字符串转换成ASCII值
     * 
     * @param str String
     * @return String
     */
    public static String toASCIIValue(String str)
    {
        if (null == str || "".equals(str))
        {
            return str;
        }
        String hstr = str;
        // 转义斜线
        hstr = hstr.replaceAll("\\x5C", "\\\\\\x5C");
        
        // 转义空格
        // hstr = hstr.replaceAll("\\x32", "\\\\\\x32");
        // 转义回车
        hstr = hstr.replaceAll("\\x13", "\\\\\\x13");
        hstr = hstr.replaceAll("[\\x10]", "\\\\\\x10");
        
        // 转义双引号
        hstr = hstr.replaceAll("\\x22", "\\\\\\x22");
        
        // 转义单引号
        hstr = hstr.replaceAll("\\x27", "\\\\\\x27");
        
        return hstr;
    }
    
    /**
     * ascii码大于126的字符长度按2位计算
     * 
     * @param value
     * @return
     */
    public static int getDisplayLen(String value)
    {
        int i = 0;
        int len = 0;
        if (value == null)
        {
            return 0;
        }
        len = 0;
        for (i = 0; i < value.length(); i++)
        {
            if (value.charAt(i) > 126)
            {
                len += 2;
            }
            else
            {
                len++;
            }
        }
        return len;
    }
    
    /**
     * ascii码大于126的字符长度按3位计算
     * 
     * @param value
     * @return
     */
    public static int getStrLen(String value)
    {
        int i = 0;
        int len = 0;
        if (value == null)
        {
            return 0;
        }
        len = 0;
        for (i = 0; i < value.length(); i++)
        {
            if (value.charAt(i) > 126)
            {
                len += 3;
            }
            else
            {
                len++;
            }
        }
        return len;
    }
    
    /**
     * 取给定字符长度,ascii码大于255的字符占3位
     * 
     * @param value
     * @param length
     * @return
     */
    public static String getUncodeSubString(String value, int length)
    {
        if (value == null)
        {
            return "";
        }
        
        int len = getStrLen(value);
        
        if (len > length)
        {
            int count = 0;
            char c = 0;
            for (int i = 0; i < value.length(); i++)
            {
                
                c = value.charAt(i);
                if (c > 255)
                {
                    count += 3;
                }
                else
                {
                    count++;
                }
                
                if (count + 5 >= length)
                {
                    return value.substring(0, i) + "...";
                }
            }
            
        }
        
        return value;
    }
    
    /**
     * 将url(http://10.10.10.10:8080/url.jsp)解析为分成四部分:协议(http),IP或域名(10.10.10.10),端口(8080),路径(/url.jsp). String[0]为协议头;
     * String[1]为IP或域名; String[2]为端口; String[3]为路径.
     * 
     * @param url
     * @return String[] urlParts
     */
    public static String[] splitUrlForArray(String url)
    {
        Map<String, String> urlMap = splitUrlForMap(url);
        if (null == urlMap)
        {
            return new String[] {};
        }
        return new String[] {urlMap.get("protocol"), urlMap.get("server"), urlMap.get("port"), urlMap.get("file")};
    }
    
    /**
     * 以Map形式存储url的各组成部分,protocol=协议;body=服务主体;server=域名或ip地址;port=端口;file=路径.
     * 如:url为http://10.10.10.10:8080/url.jsp,则protocol
     * ="http";body="10.10.10.10:8080";server="10.10.10.10";port="8080";file="/url.jsp".
     * 
     * @param url
     * @return Map
     */
    
    public static Map<String, String> splitUrlForMap(String url)
    {
        if (null == url)
        {
            return null;
        }
        url = url.trim();
        if (-1 == url.indexOf("://"))
        {
            throw new IllegalArgumentException("invalid Url! Url:[" + url + "]");
        }
        Map<String, String> urlMap = new HashMap<String, String>();
        urlMap.put("protocol", url.substring(0, url.indexOf("://")));
        String body = url.substring(url.indexOf("://") + 3, url.length());
        String file = "";
        String port = "";
        if (-1 != body.indexOf("/"))
        {
            file = body.substring(body.indexOf("/"), body.length());
            body = body.substring(0, body.indexOf("/"));
        }
        String[] bodys = body.split("[:]");
        if (bodys.length > 1)
        {
            port = bodys[1];
        }
        urlMap.put("body", body);
        urlMap.put("server", bodys[0]);
        urlMap.put("port", port);
        urlMap.put("file", file);
        return urlMap;
    }
    
    /**
     * 清空为null的字符串
     * @param str
     * @return str
     */
    public static String getKillNull(String str){
    	if (Strings.isNullOrEmpty(str)) {
    		if ("null".equals(str)) {
    			str = "";
    		}
    	}
    	return str;
    }
    
    public static String toJsValue(String str)
    {
        if (null == str || "".equals(str))
        {
            return str;
        }
        String hstr = str;
        // 转义斜线
        hstr = hstr.replaceAll("\\x5C", "\\\\\\\\");
        
        // 转义回车
        hstr = hstr.replaceAll("\r", "\\\\\\r");
        hstr = hstr.replaceAll("[\n]", "\\\\\\n");
        
        // 转义双引号
        // hstr = hstr.replaceAll("\"", "\\\\\"");
        hstr = hstr.replaceAll("\\x22", "\\\\\\x22");
        
        // 转义单引号
        hstr = hstr.replaceAll("\'", "\\\\\'");
        // begin add by zhangyi 20090224
        // 页面输入<script>a</script>会有问题，所以增加转换
        hstr = hstr.replaceAll("\\x3C", "\\\\\\<");
        hstr = hstr.replaceAll("\\x3E", "\\\\\\>");
        // end add by zhangyi 20090224
        return hstr;
    }
    
    //处理明细中对数据库中数据进行处理，和索引中一致
    public static String getProcessedStr(String str)
    {
    	if(str==null){
    		str = "";
    	}
	    String regEx = "[`~!^&*()+=|{}':;',\\[\\].<>/?~！\"￥%……&*（）——+|{}【】'；：”“’。，、？\r\t\n]";
	    Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		str = m.replaceAll(" ");
		str = str.replaceAll(" +", " ");
	    return str.trim();
    }
    
  /**
   * 处理特殊字符与清除多余空格
   * @param str
   * @return
   */
  public static String getProcessResult(String str){
	  if(isEmpty(str)||"N/A".equals(str)){
		  return "N/A";
	  }
	  str = getProcessedStr(str);
	  return str.trim();
  }
  
  public static String handleNull(String str){
	  return str == null?"N/A":str;
  }
  
  
  /**
   * 处理double数字，截取两位小数
   * @param doubleData
   * @return
   */
  public static double getDataFormat(double doubleData){
	  BigDecimal bg = new BigDecimal(doubleData);
      double resultData = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	  return resultData;
  }
  
  	/**
	 * 字符串分组转换为数组,处理特殊字符{,}
	 * @param strParam
	 * @return
	 */
	public static String[] getSplitStr(String strParam) {
		strParam = strParam.replaceAll("&nbsp;", "");
		String[] countrys = null;
		if (!Strings.isNullOrEmpty(strParam)) {
			if (strParam.indexOf("、") > 0) {
				strParam = strParam.substring(0, strParam.lastIndexOf("、"));
				countrys = strParam.split("、");
			} else if (strParam.indexOf(",") > 0) {
				if (strParam.subSequence(strParam.length() -1, strParam.length()).equals(",")) {
					strParam = strParam.substring(0, strParam.lastIndexOf(","));
				}
				countrys = strParam.split(",");
			} else {
				countrys = new String[] { strParam };
			}
		}
		return countrys;
	}
   
  
  public static void main(String[] args) {
	  String s = "argentina_import_conditionfield";
	System.out.println(s.toUpperCase());
}
}
