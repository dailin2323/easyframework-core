package com.easyframework.core.thread.producerConsumer;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import com.easyframework.core.exception.CallBackExcepiton;


/**
 * 生产者消费者线程模型
 * @Title: ProducerConsumer.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-1-14 上午10:49:49
 * @最后修改人：邹凯明
 * @最后修改时间：2014-1-14 上午10:49:49
 * @version V1.0
 * @copyright: 
 */
public class ProducerConsumerModel<T> {
	/** 生产者和消费者锁 */
	
	/** 生产者和消费者的数据容器  */
	private Vector dataList = new Vector();
	/** 生产者回调函数  */
	private ProducerCB producerCB = null;
	/*** 消费者回调函数 */
	private ConsumerCB consumerCB = null;
	/** 线程停止运行标识 */
	private boolean flag = true;
	
	/** 数据缓存最大大小  */
	private int cacheMaxSize = 5000;
	/** 数据缓存最小大小 */
	private int cacheMinSize = 1000;
	/** 当数据为空时，睡眠时间 */
	private int dataEmptySleepTime = 300;
}
	/*public ProducerConsumerModel(ProducerCB<T> producerCB, ConsumerCB<T> consumerCB){
		if(producerCB == null || consumerCB == null){
			throw new CallBackExcepiton();
		}else{
			this.producerCB = producerCB;
			this.consumerCB = consumerCB;
		}
		Semaphore s = null;
		List l = null;
		
		//Thread.yield();
		Object o = null;
		

	}
	
	public void start(){
		//ProducerThread producer = new ProducerThread(dataList);
		ConsumerThread consumer = new ConsumerThread(dataList);
		
		producer.start();
		consumer.start();
	}
	
	public void stop(){
		this.flag = false;
	}
	
	class ProducerThread extends Thread {
		
		private byte[] lock = new byte[0];
		private Producer producer = new Producer();
		
		public ProducerThread(){
			//this.producer = new Producer(dataList);
		}
		
		public void run() {
			while(flag){
				synchronized(lock){
					//System.out.println("进入生产者");
					*//** 缓存已经满 ，读者进程休眠  *//*
					producerCB.run(producer);
					if(producer.size() > cacheMaxSize){
						//数据搬运到消费者中
						lock.notify();
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if( producer.size() == 0){
						
						Thread.sleep(dataEmptySleepTime);
						
					} else {
						producerCB.run(producer);
						lock.notify();
					}
				}
			}
		}
	}
		
	class ConsumerThread extends Thread {
		private Consumer consumer = null;
	
		public ConsumerThread(Vector dataList){
			this.consumer = new Consumer(dataList);
		}

		public void run() {
			while(flag){
				synchronized(lock){
					//System.out.println("进入消费者");
					if(consumer.size() > consumerCBLimit){
						consumerCB.run(consumer);
					}else if(consumer.size() > 0){
						consumerCB.run(consumer);
						lock.notify();
					}else {  // == 0
						lock.notify();
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} 
				}
			}
		}
	}//class ConsumerThread extends Thread 
}

*/


