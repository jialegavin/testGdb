package com.njyb.auth.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 标识哪些controller需要使用对应的权限验证
 * @author jiahp
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorityAnnoation {
	/**
	 * 如果value的值是login_admin 那么就代表该controller只能由管理员访问
	 * 如果value的值是login_user 那么就代表该controller里面部分的功能当前的用户是可以访问的
	 * @return
	 */
	public String value() default "login_user";
}
