package com.njyb.gbdbase.model.admincenter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * 权限字段model
 * @author 章华才
 *
 */
public class AuthorityFieldModel implements Serializable{

	private static Map authorityFieldMap = null;

	public static Map getAuthorityFieldMap() {
		return authorityFieldMap;
	}

	public static void setAuthorityFieldMap(Map map) {
		authorityFieldMap = map;
	}
	
}
