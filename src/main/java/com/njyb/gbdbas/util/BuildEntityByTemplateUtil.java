package com.njyb.gbdbas.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import com.njyb.gbdbas.util.JdbcResultUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 生成模版工具类
 * 
 * @author 贾红平
 * 
 */
public class BuildEntityByTemplateUtil {
	/**
	 * 
	 * @param sql--查询字段里面的sql语句
	 * @param savePath--保存文件路径（String savePath = "//src//main//java//com//njyp//dbas_data//bean//search//";）
	 * @param packageName--包的名称(com.njyp.trada_data.bean.search)
	 * @param clsName--JAVABEAN的名称(ChinaEight)
	 * @throws IOException--输入输出异常
	 * @throws TemplateException--模板异常
	 * @throws Exception--根异常
	 */
	public static void buildBeanBySql(String sql,String savePath,String packageName,String clsName) throws IOException, TemplateException,Exception {
		
		Map<String, String> mp = new HashMap<String, String>();
		//获取集合对象
		mp=new JdbcResultUtil().getMapModleBySql(sql);
		
		String srcPath = new File("").getAbsolutePath().replace("\\", "/");
		//设置加载配置实例对象
		//加载模版的位置
		//加载的默认转换器
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(BuildEntityByTemplateUtil.class,"/temp");
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		//获取加载的具体的模版
		Template temp = cfg.getTemplate("model.ftl");		 
		Map<String, Object> root = new HashMap<String, Object>();
		//String t_name="Lc";
		//存储模型对象的名称
		root.put("class", clsName);
		//模型对象的数据
		root.put("testMap",mp);
		//模型对象的包
		root.put("package", packageName);
		//创建对应的目录
		createFileDirs(srcPath + "/" + savePath);
		String realPath = clsName+".java";
		//模型写入内存
		Writer out = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(srcPath + "/" + savePath + realPath),"UTF-8"));
		temp.process(root, out);
		out.flush();
		out.close();
		
	}
	
	/**
	 * 判断创建模型对性的目录是否存在
	 * @param url
	 */
	public static void createFileDirs(String url) {
		File file = new File(url);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	public static void main(String[] args)throws Exception 
	{
		//buildBeanBySql("SELECT * FROM data_alldb limit 1 ", "//src//com//njyp//dbas_data//bean//search//", "com.njyp.trade_data.bean.search", "MarketAnalysisReportModel");
	}
}
