package com.njyb.test.zhanghuacai;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.njyb.gbdbase.dao.datasearch.country.IAllCountrySelectFieldDao;
import com.njyb.gbdbase.model.datasearch.allCountrySelectField.AllCountrySelectFieldModel;
import com.njyb.gbdbase.model.datasearch.common.SqlModel;
public class TestCountryField {
	//定义需要参数的值
	static ApplicationContext context=null;
	String path="config\\core\\applicationContext.xml";;
	IAllCountrySelectFieldDao dao=null;
	
	@Test
	public void testListCountryField(){
		SqlModel model = new SqlModel();
		String name = "原产国";
//		String name = "产销国";
		String country = "玻利维亚进口";
		String n = "bolivia_import";
		String sql = "select distinct fieldvalue from help_country_select_field where fieldname ='"+name+"' and country = '"+country+"'" ;
		model.setSql(sql);
		List<AllCountrySelectFieldModel> list = dao.queryAllCountrySelectField(model);
		StringBuffer sbValue = new StringBuffer();	
		for (AllCountrySelectFieldModel allCountrySelectFieldModel : list) {
			System.out.println(allCountrySelectFieldModel);
			if(allCountrySelectFieldModel != null && !"".equals(allCountrySelectFieldModel))
			{
				String value = allCountrySelectFieldModel.getFieldValue().replaceAll("&", " ");
				System.out.println(value);
				sbValue.append(value + "~");
			}
		}
//		String n = "nicaragua_export_origin";
		setValueField(n, sbValue.toString());
//		util.setValue("fieldName", sbName.toString());
	}
//	
	@Test
	public void a(){
		String a = "aaaa&sdfd";
		System.out.println(a.replaceAll("&", " "));
	}
	
	/**
	 * 设置属性的值 根据指定属性的名称
	 * @param key
	 * @param value
	 */
	public static void setValueField(String key, String value) {
		
		File file = new File("E:\\allCountrySelectField.properties");
		Properties p = null;
		try {
			InputStream in = new BufferedInputStream (new FileInputStream(file));
			p  =   new  Properties(); 
		   try {
			 p.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		p.setProperty(key, value);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File("E:\\allCountrySelectField.properties"));
			p.store(fos, key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	@Before
	public void initContext(){
		System.out.println("启动类前..");
		context=new ClassPathXmlApplicationContext(path);
		dao=context.getBean(IAllCountrySelectFieldDao.class);
		
	}
	@After
	public void destoryContext(){
		System.out.println("结束后....spring is great!");
	}
}
