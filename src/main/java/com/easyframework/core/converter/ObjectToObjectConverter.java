package com.easyframework.core.converter;

/**
 * @Title: ObjectToObjectConverter.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:06:33
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:06:33
 */
public interface ObjectToObjectConverter extends Converter {
	Object convert(Object src);
	
}
