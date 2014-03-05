package com.easyframework.core.exception;

/**
 * @Title: BaseException.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:08:11
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:08:11
 */
public class BaseException extends RuntimeException{
	private static final long serialVersionUID = 828976064126144450L;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
}
