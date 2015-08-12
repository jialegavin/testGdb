package com.njyb.test.jiahongping;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class MapTest {
	 private static final BiMap<Integer,String> countryMap;
	 
	 static {
	   countryMap =HashBiMap.create(); 
	   countryMap.put(1, "aa");
	   countryMap.put(2, "bb");
	   countryMap.put(3, "cc");
	 }
	
	 public static int getElementNumber(String elementName) {
	   return countryMap.inverse().get(elementName);
	 }
	
	 public static String getElementName(int elementNumber) {
	   return countryMap.get(elementNumber);
	 }
}

