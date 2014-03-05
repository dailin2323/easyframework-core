package com.easyframework.core.config;

import java.util.ResourceBundle;

/**
 * @Title: Config.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:05:44
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:05:44
 */
public class Config {
	
	private static final ResourceBundle config = ResourceBundle.getBundle("config");  
	
	public static String get(String key){
		return config.getString(key);
	}
}
