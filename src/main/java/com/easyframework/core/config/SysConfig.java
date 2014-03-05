package com.easyframework.core.config;

import java.math.BigDecimal;
import java.util.Date;

import com.easyframework.core.pageModel.PageQueryParam;
import com.easyframework.core.util.DateUtil;

/**
 * @Title: SysConfig.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:06:04
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:06:04
 */
public class SysConfig {
	
	
	private static PageQueryParam defaultPageQueryParam = new PageQueryParam();
	private static int pageSize = 5;

	static {
		defaultPageQueryParam.setNum(1);
		defaultPageQueryParam.setRows(10);
		
		
		
	}
	
	
	public static PageQueryParam getDefaultPageQueryParam(){
		return defaultPageQueryParam;
	}
	
	public static String formatDate(Date date){
		return DateUtil.formateToYYMDHMS(date);
	}
	
	public static BigDecimal adaptBigDecimal(BigDecimal bigDecimal){
		BigDecimal b = null;
	//	b.setScale(2, BigDecimal.ROUND_DOWN);
	//	b.divide(divisor, scale, roundingMode)
		return null;
	}
	

	public static int getPageSize() {
		
		return pageSize;
	}
	
	
}
