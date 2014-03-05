package com.easyframework.core.exception;

/**
 * @Title: CallBackExcepiton.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:08:17
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:08:17
 */
public class CallBackExcepiton extends BaseException{
	private static final long serialVersionUID = 6563180195309086571L;

	public CallBackExcepiton() {
		super();
	}

	public CallBackExcepiton(String message, Throwable cause) {
		super(message, cause);
	}

	public CallBackExcepiton(String message) {
		super(message);
	}

	public CallBackExcepiton(Throwable cause) {
		super(cause);
	}
	
}
