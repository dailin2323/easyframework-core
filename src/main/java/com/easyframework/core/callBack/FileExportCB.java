package com.easyframework.core.callBack;

import java.io.OutputStream;

/**
 * @Title: FileExportCB.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:05:27
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:05:27
 */
public interface FileExportCB extends CallBack {
	void run(OutputStream out);
}
