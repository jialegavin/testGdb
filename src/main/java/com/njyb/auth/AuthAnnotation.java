package com.njyb.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author 贾红平
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthAnnotation {
	public String value () default "";
}
