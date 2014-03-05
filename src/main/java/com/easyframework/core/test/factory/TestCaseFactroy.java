package com.easyframework.core.test.factory;

/**
 * @Title: Adress.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2013-12-18 下午10:42:07
 * @最后修改人：邹凯明
 * @最后修改时间：2013-12-18 下午10:42:07
 * @version V1.0
 * @copyright: 
 */
public class TestCaseFactroy {
	private static TestCaseFactroy instantance = null;

	private TestCaseFactroy() {
	}

	@SuppressWarnings("unused")
	public static TestCaseFactroy getInstance() {
		if (instantance == null) {
			synchronized (TestCaseFactroy.class) {
				instantance = new TestCaseFactroy();
			}
		}
		return instantance;
	}

	
}
