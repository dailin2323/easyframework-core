package com.easyframework.core.exception;

/**
 * @Title: DaoException.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:08:26
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:08:26
 */
public class DaoException extends BaseException{
	private static final long serialVersionUID = 4768736439996729600L;

	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
	
	
}
