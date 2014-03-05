package com.easyframework.core.exception;

/**
 * @Title: ServiceException.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:08:33
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:08:33
 */
public class ServiceException extends BaseException{
	private static final long serialVersionUID = 5188896188335873006L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
