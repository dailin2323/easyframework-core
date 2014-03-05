package com.easyframework.core.easyUI;

import java.util.List;

/**
 * @Title: DataGrid.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:06:55
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:06:55
 */
public class DataGrid {
	private Long total;		// 总记录数
	private List rows;		// 每行记录
	private List footer;	// 
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public List getFooter() {
		return footer;
	}
	public void setFooter(List footer) {
		this.footer = footer;
	}

}
