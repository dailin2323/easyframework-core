package com.easyframework.core.thread.producerConsumer;

import com.easyframework.core.callBack.CallBack;

public interface ConsumerCB<T> extends CallBack {
	void run(Consumer<T> consumer);
}
