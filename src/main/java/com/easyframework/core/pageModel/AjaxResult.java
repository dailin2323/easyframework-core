package com.easyframework.core.pageModel;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;

/**
 * 异步请求结果类
 * @Title: AjaxResult.java
 * @Description: 当实现复杂业务逻辑时，可以继承自该类，然后在子类中扩展STATUTS_定义
 * @author 邹凯明
 * @date 2014-3-5 上午1:09:29
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:09:29
 */
public class AjaxResult {
	public static final int STATUTS_UNDEFINE = 0;
	
	private boolean success = false;	//本次请求是否成功
	private int status = STATUTS_UNDEFINE;	//返回的状态码
	private String msg = "";			//返回的消息
	private Object data = "";			//返回的数据
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data, String dateFromat, String[] excludeFiled) {
		if(dateFromat == null || dateFromat.trim().equals("")){
			dateFromat = "yyyy-MM-dd HH:mm:ss";//DateFormatType.DEFAULT;
		}
		if(excludeFiled == null){
			this.data = JSON.toJSONStringWithDateFormat(data, dateFromat, null);
		} else {
			final Set<String> excludeFiledSet = new HashSet<String>();
			PropertyFilter propertyFilter = new PropertyFilter(){
				public boolean apply(Object source, String name, Object value) {
					if(excludeFiledSet.contains(name)){
						return false;
					}else{
						return true;
					}
			}};
			SerializeWriter serializeWriter = new SerializeWriter();
			JSONSerializer jSONSerializer = new JSONSerializer(serializeWriter);
			jSONSerializer.getPropertyFilters().add(propertyFilter);	
			jSONSerializer.setDateFormat(dateFromat);
			jSONSerializer.write(data);
			this.data = serializeWriter.toString();
		}
	}	
	
	public void setData(Object data, String dateFromat){
		this.setData(data, dateFromat, null);
	}
	
	public void setData(Object data){
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
