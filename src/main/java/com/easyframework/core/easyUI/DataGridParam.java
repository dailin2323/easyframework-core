package com.easyframework.core.easyUI;

import com.easyframework.core.pageModel.PageParam;

/**
 * @Title: DataGridParam.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:07:06
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:07:06
 */
public class DataGridParam extends PageParam {
	
	private static final int DEFAULT_ROWS = 10;	//缺省每页显现多少行记录
	private static final int DEFAULT_NUM = 1;	//缺省显示第几页记录
	
	private int rows = DEFAULT_ROWS;			//每页显示的记录数为rows行
	private int num = DEFAULT_NUM;				//显示第num页记录
	
	private static final String ORDER_ACS = "acs";
	private static final String ORDER_DESC = "desc";
	
	private String order = ORDER_ACS;
	private String sort = "";
	
	public DataGridParam(){
	}
	
	/*public DataGridParam(PageParamParseCB pageParamParseCB){
		setPageParamParseCB(pageParamParseCB);
	}*/
	
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
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

	//TODO 该方法有待考虑
	/*public Criteria parse2Criteria(Criteria criteria){
		criteria = super.parse2Criteria(criteria);
		if(order.equals(ORDER_ACS)){
			criteria.addOrder(Order.asc(sort));
		}else{
			criteria.addOrder(Order.desc(sort));
		}
		return criteria;
	}*/

}
