package com.easyframework.core.callBack;

import org.hibernate.Criteria;

/**
 * @Title: PageParamParseCB.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:05:34
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:05:34
 */
public abstract class PageParamParseCB implements CallBack {
	//public void run(){}
	public abstract void run(String key, String value, Criteria criteria);
}
