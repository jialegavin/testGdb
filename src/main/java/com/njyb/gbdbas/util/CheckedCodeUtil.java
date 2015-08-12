package com.njyb.gbdbas.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 * 生产验证码的工具类
 * @author jcy
 *
 */
public class CheckedCodeUtil {
	
	private static final long serialVersionUID = 1L;
	private Random random = new Random();
	private int width;
	private int height;
	private int flag;// 验证码分类标志：0、数字，1、字母，2、汉字，3、数字+字母，4、数字+字母+汉字
	private int length;// 验证码字数
	private String fontName;
	private int fontStyle;
	private int fontSize;
	private int rotate;

	public CheckedCodeUtil(){
		init();
	}
	//初始化数据
	private void init(){
		width = 120;
		height = 30;
		length = 4;
		flag = 0;
		fontName = "隶体";
		fontStyle = 5;
		fontSize = 20;
		rotate = 0;
	}
	/**
	 * 生成随即颜色
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public Color getRandomColor(int i, int j) {
		Random random = new Random();
		if (i > 255)
			i = 200;
		if (j > 255)
			j = 255;
		int r = i + random.nextInt(j - i);
		int g = i + random.nextInt(j - i);
		int b = i + random.nextInt(j - i);
		return new Color(r, g, b);
	}

	public String drawRandomString(int length, Graphics2D g, int flag) {
		StringBuffer sb = new StringBuffer();
		String temp = "";
		AffineTransform atf = new AffineTransform();
		if (flag == 0) {
			for (int i = 0; i < length; i++) {
				temp = drawRandomDigital();
				sb.append(temp);
				if(rotate==1){
					atf.rotate(-(random.nextInt(5)*3.14/180), i * (width / length) + 5,
							(height + fontSize) / 2);
					g.setTransform(atf);
				}
				g.drawString(temp, i * (width / length) + 5,
						(height + fontSize) / 2);
			}
		} else if (flag == 1) {
			for (int i = 0; i < length; i++) {
				temp = drawRandomLetter();
				sb.append(temp);
				if(rotate==1){
					atf.rotate(-(random.nextInt(5)*3.14/180), i * (width / length) + 5,
							(height + fontSize) / 2);
					g.setTransform(atf);
				}
				g.drawString(temp, i * (width / length) + 5,
						(height + fontSize) / 2);
			}
		} else if (flag == 2) {
			for (int i = 0; i < length; i++) {
				temp = drawRandomChineseChar();
				sb.append(temp);
				if(rotate==1){
					atf.rotate(-(random.nextInt(5)*3.14/180), i * (width / length) + 5,
							(height + fontSize) / 2);
					g.setTransform(atf);
				}
				g.drawString(temp, i * (width / length) + 5,
						(height + fontSize) / 2);
			}
		} else if (flag == 3) {
			for (int i = 0; i < length; i++) {
				switch (random.nextInt(2)) {
				case 0:
					temp = drawRandomDigital();
					break;
				case 1:
					temp = drawRandomLetter();
					break;

				}
				sb.append(temp);
				if(rotate==1){
					atf.rotate(-(random.nextInt(5)*3.14/180), i * (width / length) + 5,
							(height + fontSize) / 2);
					g.setTransform(atf);
				}
				g.drawString(temp, i * (width / length) + 5,
						(height + fontSize) / 2);
			}
		} else {
			for (int i = 0; i < length; i++) {
				switch (random.nextInt(3)) {
				case 0:
					temp = drawRandomDigital();
					break;
				case 1:
					temp = drawRandomLetter();
					break;
				case 2:
					temp = drawRandomChineseChar();
					break;

				}
				sb.append(temp);
				if(rotate==1){
					atf.rotate(-(random.nextInt(5)*3.14/180), i * (width / length) + 5,
							(height + fontSize) / 2);
					g.setTransform(atf);
				}
				g.drawString(temp, i * (width / length) + 5,
						(height + fontSize) / 2);
			}
		}
		g.dispose();
		return sb.toString();
	}

	public String drawRandomDigital() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(random.nextInt(10)));
		return sb.toString();
	}

	public String drawRandomLetter() {
		StringBuffer sb = new StringBuffer();
		int tempInt = 0;
		switch (random.nextInt(2)) {
		case 0:
			tempInt = random.nextInt(26) + 65;
			break;
		case 1:
			tempInt = random.nextInt(26) + 97;
			break;

		}
		sb.append(String.valueOf((char) tempInt));
		return sb.toString();
	}

	// 汉字"啊"在汉字16区01位码位置，内码：0xb0,0xa1。汉字符集在94*94区中间，
	// 前15区是符号,常用汉字区码在16-87中间，位码01-94
	public String drawRandomChineseChar() {
		StringBuffer sb = new StringBuffer();
		String temp = "";
		 String[] rBase = {"0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" ,   
                 "8" , "9" , "a" , "b" , "c" , "d" , "e" , "f" };  
         int r1 = random.nextInt(5)+11 ;     //生成第1位的区码  
        String strR1 = rBase[r1] ;      //生成11～15的随机数  
        int r2 ;        //生成第2位的区码  
        if(r1 == 15)  
           r2 = random.nextInt(7) ;    //生成0～7的随机数  
         else  
            r2 = random.nextInt(16) ;   //生成0～16的随机数  
         String strR2 = rBase[r2] ;  
        int r3 = random.nextInt(6) + 10 ;   //生成第1位的位码  
        String strR3 = rBase[r3] ;  
         int r4 ;        //生成第2位的位码  
        if(r3 == 10)  
           r4 = random.nextInt(15) + 1;    //生成1～16的随机数  
         else if(r3 == 15)  
            r4 = random.nextInt(15) ;       //生成0～15的随机数  
       else  
             r4 = random.nextInt(16) ;       //生成0～16的随机数  
        String strR4 = rBase[r4] ;  
         //将生成的机内码转换成数字  
       byte[] bytes = new byte[2]   ;        
         String strR12 = strR1 + strR2 ;     //将生成的区码保存到字节数组的第1个元素中  
         int tempLow = Integer.parseInt(strR12, 16) ;  
        bytes[0] = (byte)tempLow;  
         String strR34 = strR3 + strR4 ;     //将生成的区码保存到字节数组的第2个元素中  
        int tempHigh = Integer.parseInt(strR34, 16) ;  
         bytes[1] = (byte)tempHigh;  
        temp = new String(bytes);           //根据字节数组生成汉字    
        sb.append(temp);
		return sb.toString();
	}
	
}
