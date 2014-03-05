package com.easyframework.core.pageModel;

/**
 * @Title: PageParam.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:09:57
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:09:57
 */
public class PageParam {
	private static final String PARAM_SEPARATOR = "&";
	private static final String KEY_VALUE_SEPARATOR = "=";
//	private PageParamParseCB pageParamParseCB;
	private String params = "";
	
	public PageParam(){	
	}
	
	/*public PageParam(PageParamParseCB pageParamParseCB){
		this.pageParamParseCB = pageParamParseCB;
	}*/
	
	/**
	 * TODO 该方法有待考虑
	 * @return
	 */
	/*public Criteria parse2Criteria(Criteria criteria){
		if( params != null && !params.trim().equals("")){
			if(pageParamParseCB != null){
				String[] strs = params.split(PARAM_SEPARATOR);
				for(String str:strs){
					int index = str.indexOf(KEY_VALUE_SEPARATOR);
					String key = str.substring(0, index);
					String value = str.substring(index+1);
					pageParamParseCB.run(key, value, criteria);
				}
			}
		} 
		return criteria;
	}
*/
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
/*
	public PageParamParseCB getPageParamParseCB() {
		return pageParamParseCB;
	}

	public void setPageParamParseCB(PageParamParseCB pageParamParseCB) {
		this.pageParamParseCB = pageParamParseCB;
	}*/
}
