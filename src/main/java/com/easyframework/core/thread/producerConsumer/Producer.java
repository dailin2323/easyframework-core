package com.easyframework.core.thread.producerConsumer;

import java.util.Vector;

/**
 * 生产者对象
 * @Title: Producer.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-1-14 下午2:24:35
 * @最后修改人：邹凯明
 * @最后修改时间：2014-1-14 下午2:24:35
 * @version V1.0
 * @copyright: 
 */
public class Producer<T> {
	
private Vector<T> list = new Vector<T>();
	
	public Producer(Vector<T> list){
		this.list = list;
	}
	
	public void push(T t){
		list.add(t);	//添加到末尾
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
