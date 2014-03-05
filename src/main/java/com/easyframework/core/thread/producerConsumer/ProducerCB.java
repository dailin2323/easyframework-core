package com.easyframework.core.thread.producerConsumer;

import com.easyframework.core.callBack.CallBack;

public interface ProducerCB<T> extends CallBack {
	void run(Producer<T> producer);
}
