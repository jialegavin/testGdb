package com.njyb.gbdbase.model.admincenter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 对应menu菜单的实体类
 * @author jcy
 *
 */
public class MenuModel implements Serializable{
	//菜单的名字fff
	private String name;
	//菜单里面的功能（或者是对应的权限）
	private Map<String, String> menus = new HashMap<String, String>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getMenus() {
		return menus;
	}
	public void setMenus(Map<String, String> menus) {
		this.menus = menus;
	}
	public void setMenus(String key,String value) {
		this.menus.put(key, value);
	}
	
	public MenuModel(String name, Map<String, String> menus) {
		this.name = name;
		this.menus = menus;
	}
	public MenuModel() {
	}
	
	
}
