package com.easyframework.core.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @Title: BaseDict.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:04:09
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:04:09
 */
@Entity
//@Inheritance(strategy=InheritanceType.JOINED) 
public class BaseDict {
	private static final int CODE_LENGTH = 20;
	//类型定义
	public static final int TYPE_UNDEFINE = 0;
	//在继子类中扩展TYPE_
	
	//数据库字段定义
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name = "id", unique = true, nullable = false, length = 36)
	private Long id;					//字典标识
	
	@Column(unique = true, nullable = false, length = CODE_LENGTH)
	private String code;				//字典项编码
	
	@Column(unique = true, nullable = false)
	private int type = TYPE_UNDEFINE;	//字典项分类
	
	@Column(unique = false, nullable = false, length = 200)
	private String name;				//字典项名称
	
	@Column(unique = true, nullable = true, length = 200)
	private String remark;				//字典项备注
		
	private boolean active = true;		//该字典项是否可用
	
	public BaseDict(){
		
	}
	
	public BaseDict(String name, int type){
		this.name = name;
		this.code = name.substring(CODE_LENGTH);
		this.type = type;
	}
	
	public BaseDict(String name, String code, int type){
		this.name = name;
		this.code = code;
		this.type = type;
	}
	
	//-------getter setter----------
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}
