package com.njyb.auth.util;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.njyb.auth.annotation.AuthorityMethodAnnoation;
/**
 * 初始化用户对应的角色所能访问的对应的权限信息
 * @author jiahp
 *
 */
@SuppressWarnings("all")
public class InitAuthorityByRoleUtil {
	/**
	 * 获取某个controller的包下面的所有的类
	 * @param packageName :包的名称
	 * @return
	 */
	private static String[] getClassByPackageName(String packageName) {
		String pr = packageName.replace(".", "/");
		String pp =InitAuthorityByRoleUtil.class.getClassLoader().getResource(pr).getPath();
		File file = new File(pp);
		String[] fs = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith(".class"))
					return true;
				return false;
			}
		});
		return fs;
	}
	
	
	/**
	 * 初始化系统的角色所访问的功能信息
	 * 
	 * @return
	 */
	public static Map<String, Set<String>> initAuthority(String pname) {
		try {
			Map<String, Set<String>> authList = new HashMap<String, Set<String>>();
			//获取包下面的所有类
			String[] ps = getClassByPackageName(pname);
			for (String p : ps) {
				String pc = pname + "."
						+ p.substring(0, p.lastIndexOf(".class"));
				//得到具体的class对象
				Class clz = Class.forName(pc);
				if (!clz.isAnnotationPresent(InitAuthorityByRoleUtil.class))
					continue;
				//获取每个类中的方法，以此确定哪些角色可以访问哪些方法
				Method[] ms = clz.getDeclaredMethods();
				//遍历method来判断每个method上面是否存在相应的AuthorityMethodAnnoation
				//如果存在就直接将这个方法存储到auths中，如果不存在就不存储 不存储就意味着该方法只能由超级管理员访问
				for (Method m : ms) {
					if (!m.isAnnotationPresent(AuthorityMethodAnnoation.class))
						continue;
					//如果存在就要获取这个Annotation
					AuthorityMethodAnnoation am = m.getAnnotation(AuthorityMethodAnnoation.class);
					String roles = am.role();
					//可能一个action可以被多个角色所访问，使用，进行分割
					String[] aRoles = roles.split(",");
					for (String role : aRoles) {
						Set<String> actions = authList.get(role);
						if (actions == null) {
							actions = new HashSet<String>();
							authList.put(role, actions);
						}
						actions.add(pc + "." + m.getName());
					}
				}
			}
			return authList;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
