package com.easyframework.core.util;

/**
 * @Title: StringUtil.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:13:47
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:13:47
 */
public class StringUtil {
	
	/** 将第Index位设置为 */
	public static String replace(String str, int index ,char ch){
		char[] chs = str.toCharArray();
		chs[index] = ch;
		return new String(chs);
	}
	
	public static String getRepeatStr(String str, int count){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<count;i++){
			sb.append(str);
		}
		return sb.toString();
	}
	
	public static String getRepeatStr(char ch, int count){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<count;i++){
			sb.append(ch);
		}
		return sb.toString();
	}
	
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean anyEmpty(String... strs) {
		for (String str : strs) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	public static boolean allEmpty(String... strs) {
		for (String str : strs) {
			if (!isEmpty(str)) {
				return false;
			}
		}
		return true;
	}
}
