package com.easyframework.core.converter;

import java.util.Map;

/**
 * @Title: MapToObjectConverter.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:06:20
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:06:20
 */
public interface MapToObjectConverter extends Converter {
	Object convert(Map<String, Object> src);
}
