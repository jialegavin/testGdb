package com.njyb.auth.service.flyight.security;
import java.util.ArrayList;
import java.util.Collection;


import com.njyb.auth.service.flyight.Flyweight;
import com.njyb.auth.service.flyight.factory.FlyweightFactory;
import com.njyb.auth.service.flyight.impl.UnsharedConcreteFlyweight;
/**
 * 安全管理，实现成单例
 */
public class SecurityMgr {
	private static SecurityMgr securityMgr = new SecurityMgr();
	private SecurityMgr(){		
	}
	public static SecurityMgr getInstance(){
		return securityMgr;
	}
	/**
	 * 判断某用户对某个安全实体是否拥有某权限
	 * @param user 被检测权限的用户 
	 * @param securityEntity 安全实体
	 * @param permit 权限
	 * @return true表示拥有相应权限，false表示没有相应权限
	 */
	public boolean hasPermit(String user,String securityEntity,String permit){
		Collection<Flyweight> col = this.queryByUser(user);
		if(col==null || col.size()==0){
			System.out.println(user+"没有登录或是没有被分配任何权限");
			return false;
		}
		for(Flyweight fm : col){
			if(fm.match(securityEntity, permit)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 从数据库中获取某人所拥有的权限
	 * @param user 需要获取所拥有的权限的人员
	 * @return 某人所拥有的权限
	 */
	private Collection<Flyweight> queryByUser(String user){
		return null;
	}	
}

