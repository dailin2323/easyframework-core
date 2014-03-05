package com.easyframework.core.thread.producerConsumer;

import java.util.Vector;

/**
 * 
 * @Title: Consumer.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 下午4:13:52
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 下午4:13:52
 */
public class Consumer<T> {
	private Vector<T> list = new Vector<T>();
	
	public Consumer(Vector<T> list){
		this.list = list;
	}
	
	private long need = 0;
	
	
	public long getNeed(){
		return this.need;
	}
	
	public T pop(){
		if(list.size() == 0){
			return null;
		}else{
			T first = list.firstElement();
			list.remove(0);
			return first;
		}
	}
	
	public int size(){
		return list.size();
	}
	
	public T getFirst(){
		return list.firstElement();
	}
	
	public T getLast(){
		return list.lastElement();
	}
	
	public T get(int index){
		return list.get(index);
	}
}
