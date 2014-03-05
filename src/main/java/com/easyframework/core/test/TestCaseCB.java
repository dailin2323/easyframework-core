package com.easyframework.core.test;

import com.easyframework.core.callBack.CallBack;

/**
 * @Title: TestCaseCB.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:11:15
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:11:15
 */
public abstract class TestCaseCB implements CallBack {
	public void run() {
	};

	public abstract void run(int index, Object value);
}
