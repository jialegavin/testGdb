package com.njyb.gbdbase.model.datasearch.common;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
/**
 * 存放项目中的一些数据
 * @author honghao
 *
 */
@Component
public class MapModel implements Serializable{
	public  static ConcurrentHashMap  map = new ConcurrentHashMap ();

	public static ConcurrentHashMap getMap() {
		return map;
	}

	public static void setMap(ConcurrentHashMap map) {
		MapModel.map = map;
	}
}
