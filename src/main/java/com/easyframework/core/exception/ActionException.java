package com.easyframework.core.exception;

/**
 * @Title: ActionException.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:08:02
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:08:02
 */
public class ActionException extends BaseException{
	private static final long serialVersionUID = -25753078724267215L;

	public ActionException() {
		super();
	}

	public ActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActionException(String message) {
		super(message);
	}

	public ActionException(Throwable cause) {
		super(cause);
	}
	
}
