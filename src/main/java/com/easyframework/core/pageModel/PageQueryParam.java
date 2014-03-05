package com.easyframework.core.pageModel;

/**
 * @Title: PageQueryParam.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:10:07
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:10:07
 */
public class PageQueryParam extends PageParam {
	
	private static final int DEFAULT_ROWS = 10;	//缺省每页显现多少行记录
	private static final int DEFAULT_NUM = 1;	//缺省显示第几页记录
	private static final int DEFAULT_PAGE_BAR_LENGTH = 10;  //缺省分页条长度
	
	private int rows = DEFAULT_ROWS;			//每页显示的记录数为rows行
	private int num = DEFAULT_NUM;				//显示第num页记录
	
	private int pageBarLength = DEFAULT_PAGE_BAR_LENGTH; 	//同步分页方式下使用:分页条长度
	
	public int getRows() {
		return rows;
	}
	
	public void setRows(int rows) {
		if(rows <= 0){
			this.rows = DEFAULT_ROWS;
		}else{
			this.rows = rows;
		}
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		if(num <= 0){
			this.num = DEFAULT_NUM;
		}else{
			this.num = num;
		}
	}

	public int getPageBarLength() {
		return pageBarLength;
	}

	public void setPageBarLength(int pageBarLength) {
		if(pageBarLength <= 0){
			pageBarLength = DEFAULT_PAGE_BAR_LENGTH;
		}
		this.pageBarLength = pageBarLength;
	}
	
}
