package com.njyb.test.jiahongping.cache;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.njyb.gbdbas.cache.CreateEncache;

public class TestCache {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		@SuppressWarnings("static-access")
		Cache  cache=CreateEncache.getEacheInstance().getCache("testCache");
		Element element=cache.get("test");
		List<String>ls=null;
		if (element!=null) {
			ls=((List<String>) element.getObjectValue());
		}
		else{
			ls=add();
			Element e=new Element("test", ls);
			cache.put(e);
		}
		for(String str:ls){
			System.out.println(str);
		}
	}
	public static List<String> add(){
		List<String>ls=new LinkedList<String>();
		System.out.println("从数据库中加载数据");
		for (int i = 0; i <5; i++) {
			ls.add("test"+i);
		}
		return ls;
	}
}
