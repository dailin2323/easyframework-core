package com.easyframework.core.easyUI;

/**
 * @Title: TreeParam.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:07:51
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:07:51
 */
public class TreeParam {
	public enum TreeDepth{LEVEL_1, LEVE_2, LEVE_3, LEVE_4, AUTO, USER_DEFINE};
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
