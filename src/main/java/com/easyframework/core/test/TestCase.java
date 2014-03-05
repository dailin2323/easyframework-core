package com.easyframework.core.test;

/**
 * @Title: TestCase.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:11:06
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:11:06
 */
public class TestCase {
	private Object[] source;
	private int index = 0;
	private TestCaseCB testCaseCB;

	public TestCase(Object[] source, TestCaseCB testCaseCB) {
		this.source = source;
		this.testCaseCB = testCaseCB;
	}

	/**
	 * @Description: TODO
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	public boolean hasNext() {
		if (this.source == null) {
			return false;
		} else if (this.index < this.source.length) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Description: TODO
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	public Object next() {
		if (this.testCaseCB != null) {
			this.testCaseCB.run(index, this.source[this.index]);
		} else {
			System.out.println("index=" + index + "value=" + this.source[this.index]);
		}
		return this.source[this.index++];
	}
}
