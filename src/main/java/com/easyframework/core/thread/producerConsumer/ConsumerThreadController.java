package com.easyframework.core.thread.producerConsumer;

import com.easyframework.core.thread.ThreadControlInfo;
import com.easyframework.core.thread.ThreadControlInfo.ThreadControlType;


public class ConsumerThreadController {
	private ThreadControlInfo threadControlInfo = new ThreadControlInfo();
	
	public void doRun(){
		this.threadControlInfo.type = ThreadControlType.RUN;
	}
	
	/*public void doSleep(long millisecond){
		this.threadControlInfo.type = ThreadControlType.SLEEP;
		this.threadControlInfo.sleepTime = millisecond;
	}*/
	
	public void doWait(){
		this.threadControlInfo.type = ThreadControlType.WAIT;
	}
	
	public ThreadControlInfo getThreadStatus(){
		return this.threadControlInfo;
	}
}
