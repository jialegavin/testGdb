package com.njyb.gbdbas.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.njyb.gbdbase.model.admincenter.MenuModel;



/**
 * 对菜单xml的解析并且生产java对象
 * @author jcy
 *
 */
public class ResolveXMLUtil {
	private static Element root = null;

	public ResolveXMLUtil(String path){
		SAXReader reader = new SAXReader();
		try {
			String newPath = ChangeOperSystemPath.getMenuPath();  //first try to get file as tomcat
			Document doc = reader.read(new File(newPath));
			root = doc.getRootElement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}
	
	private Element getElement(String roleName){
		Element userroles = root.element("userroles");
		List<Element> roles = userroles.elements();
		for(Element role : roles){
			String type = role.attributeValue("name");
			if(type.equals(roleName)){
				return role;
			}
		}
		return null;
	}
	
	private List<MenuModel> findMenuModel(String roleName){
		List<MenuModel> list = new ArrayList<MenuModel>();
		Element userRole = this.getElement(roleName);
		List<Element> menus = root.element("menus").elements();
		List<Element> menuitem = userRole.elements();
		for(Element e : menuitem){
			String name = e.attributeValue("ref");
			for(Element e1 : menus){
				String type = e1.attributeValue("id");
				if(name.equals(type)){
					MenuModel menuMode = new MenuModel();
					menuMode.setName(e1.attributeValue("name"));
					List<Element> lists = e1.elements();
					for(Element e2 : lists){
						menuMode.setMenus(e2.attributeValue("name"), e2.attributeValue("url"));
					}
					list.add(menuMode);
				}
			}
		}
		return list;
	}
	
	public String getJsonString(String roleName){
		List<MenuModel> list = this.findMenuModel(roleName);
		String menusDate ="({'menus':[";
		for(int i=0;i<list.size();i++){
			menusDate = menusDate + "{ 'menuname':'" +list.get(i).getName()
				+ "','icon':'icon-sys',	'menus': [ ";
			for(String key : list.get(i).getMenus().keySet()){
				menusDate = menusDate + "{'menuname':'"+key+"','icon':'icon-nav','url':'"+list.get(i).getMenus().get(key)+"'},";
			}
			menusDate = menusDate.substring(0,menusDate.lastIndexOf(","));
			menusDate = menusDate + "]},";
		}
		menusDate = menusDate.substring(0,menusDate.lastIndexOf(","));
		menusDate = menusDate + "]})";
		return menusDate;
	}
}
