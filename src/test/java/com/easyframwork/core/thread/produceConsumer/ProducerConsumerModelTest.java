package com.easyframwork.core.thread.produceConsumer;

import org.junit.Test;

public class ProducerConsumerModelTest {
	
	@Test
	public void test(){
		
		/*ProducerConsumerModel pmd = new ProducerConsumerModel<Object>(new ProducerCB(){
			@Override
			public void run(Producer producer) {
				// 读取数据
				//threadController.doSleep(1000);
				//System.out.println("ok1");
				//threadController.doNotifyConsumer();
				
			}}, 
			new ConsumerCB(){
			@Override
			public void run(Consumer consumer) {
				System.out.println("22222222");
				//threadController.doRun();
			
			}} 
		);
		pmd.start();*/
		
	/*	MyThread my = new MyThread();
		my.run();*/
	}
}

class MyThread extends Thread{
	
	public void run(){
		while(true){
			synchronized (this) {
				System.out.println("step 1");
				System.out.println("step 2");
				
				Thread.yield();
				
				System.out.println("step 3");
				System.out.println("step 4");
			}
		}
	}
}
