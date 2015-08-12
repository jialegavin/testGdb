package com.njyb.auth.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * 用来标识某种角色访问特定的方法
 * role的值为visit_all表示这个这个方法可以被所有的合法登录用户访问
 * role的值为visit_vip表示这个方法可以被所有非按此用户的用户访问
 * role的值为visit_count表示这个方法可以被按此用户的登录用户访问
 * 如果某个方法中没有加这个注解就表示该方法只能被特定的用户访问
 * @author jiahp
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorityMethodAnnoation {
	public String role() default "visit_all";
}
