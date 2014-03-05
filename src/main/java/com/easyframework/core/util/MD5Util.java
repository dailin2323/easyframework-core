package com.easyframework.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Title: MD5Util.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-1-8 上午9:47:18
 * @最后修改人：邹凯明
 * @最后修改时间：2014-1-8 上午9:47:18
 * @version V1.0
 * @copyright: 
 */
public class MD5Util {

	public static void main(String[] args) {
		String s = "邹凯明";
		System.out.println(md5(s));
	}

	/**
	 * md5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] byteDigest = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++) {
				i = byteDigest[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

}
