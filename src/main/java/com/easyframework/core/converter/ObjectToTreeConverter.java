package com.easyframework.core.converter;

import com.easyframework.core.easyUI.Tree;

/**
 * @Title: ObjectToTreeConverter.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:06:39
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:06:39
 */
public interface ObjectToTreeConverter extends Converter {
	Tree convert(Object src);
	
}
