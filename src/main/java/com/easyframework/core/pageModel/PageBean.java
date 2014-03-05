package com.easyframework.core.pageModel;

import java.util.LinkedList;
import java.util.List;

/**
 * @Title: PageBean.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:09:50
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:09:50
 */
public class PageBean {
	private long total = 0;									// 总记录数
	private List rows = new LinkedList();	// 记录
	private List footer = new LinkedList();	//
	
	private int queryRows = 10;		//同步分页方式使用：每页显示记录的条数
	private int num = 1;			//同步分页方式使用：显示第num页的数据:从1开始（）
	private int[] pageBar = new int[0];//同步分页方式使用:分页条
	private int pageBarLength = 10; //同步分页方式使用:分页条长度
	private int totalPage = 0;		//同步分页方式使用:总页数
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int[] getPageBar() {
		if(pageBar == null){
			int _num = getNum();
			int _totalPage = getTotalPage();
			int _pageBarLength = getPageBarLength();
			if(_totalPage < _pageBarLength){
				pageBar = new int[_totalPage];
			}else{
				pageBar = new int[_pageBarLength];
			}
			/** 当前页尽量居中 */
			if( _num <= (_pageBarLength+1)/2){
				for(int i=0;i<pageBar.length;i++){
					pageBar[i] = i+1;
				}
			}else if( _num > (_pageBarLength+1)/2 && _num <= _totalPage - (_pageBarLength+1)/2){
				for(int i=0,j=_num-(_pageBarLength+1)/2;i<pageBar.length;i++){
					pageBar[i] = j;
					j++;
				}
			}else{//_num > _totalPage - (_pageBarLength+1)/2
				for(int i=pageBar.length-1,j=0;i>=0;i--){
					pageBar[i] = (int) (_totalPage - j);
					j++;
				}
			}
		}
		return pageBar;
	}
	public void setPageBar(int[] pageBar) {
		this.pageBar = pageBar;
	}
	public int getPageBarLength() {
		return pageBarLength;
	}
	public void setPageBarLength(int pageBarLength) {
		this.pageBarLength = pageBarLength;
	}
	public int getQueryRows() {
		return queryRows;
	}
	public void setQueryRows(int queryRows) {
		this.queryRows = queryRows;
	}
	
	public int getTotalPage() {
		Long _total = getTotal();
		int _rows = getQueryRows();
		return (int) ((_total + _rows -1) / _rows);
	}
}
