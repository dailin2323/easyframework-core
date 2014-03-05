package com.easyframework.core.converter;

import java.util.Map;

import com.easyframework.core.easyUI.Tree;

/**
 * @Title: MapToTreeConverter.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:06:27
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:06:27
 */
public interface MapToTreeConverter extends Converter {
	Tree convert(Map<String, Object> src);
	
}
