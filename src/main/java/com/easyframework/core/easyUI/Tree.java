package com.easyframework.core.easyUI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Title: Tree.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:07:17
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:07:17
 */
public class Tree {
	public static final String NODE_STATE_OPEN = "open"; 
	public static final String NODE_STATE_CLOSED = "closed";
	
	//--------------基本属性---------------
	private long id;
	private String text;
	private String state = NODE_STATE_CLOSED;
	private boolean checked = false;
	private Map<String, Object> attributes = new HashMap<String, Object>();
	private List<Tree> children = new LinkedList<Tree>(); 
	
	//--------------扩展属性-----------------------
	private String url;
	private String iconCls;
	
	//--------------getter setter---------------
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	public String getIconCls() {
		return (String) this.getAttributes().get("iconCls");
		//return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.getAttributes().put("iconCls", iconCls);
		//this.iconCls = iconCls;
	}
	public String getUrl() {
		return (String) this.getAttributes().get("url");
		//return url;
	}
	public void setUrl(String url) {
		this.getAttributes().put("url", url);
		//this.url = url;
	}
	
}
