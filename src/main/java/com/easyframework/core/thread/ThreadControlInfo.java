package com.easyframework.core.thread;

/**
 * @Title: ThreadControlInfo.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:11:22
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:11:22
 */
public class ThreadControlInfo {
	public enum  ThreadControlType{RUN,NOTIFY,NOTIFYALL,WAIT,SLEEP};
	
	public ThreadControlType type = ThreadControlType.RUN;
	public long sleepTime = 300;
	
	
}
