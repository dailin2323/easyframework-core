package com.easyframework.core.util;

/**
 * @Title: ByteUtil.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:11:51
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:11:51
 */
public class ByteUtil {
	/** 扩展byte[]*/
	public static boolean test(byte[] num, int index){
		if(index >= num.length*8){
			return false;
		}else{
			int row = index / (num.length);
			int col = index % (num.length);
			return test(num[row], col);
		}
	}
	
	public static byte[] set(byte[] num, int index){
		if(index >= num.length){
			return num;
		}
		int row = index / (num.length);
		int col = index % (num.length);
		set(num[row], col);
		return num;
	}
	public static byte[] set(byte[] num){
	//	byte[] re = new byte[num.length];
		for(int i=0;i<num.length;i++){
			num[i] = set(num[i]);
		}
		return num;
	}
	
	public static byte[] reset(byte[] num, int index){
		if(index >= num.length){
			return num;
		}
		int row = index / (num.length);
		int col = index % (num.length);
		reset(num[row], col);
		return num;
	}
	
	public static byte[] reset(byte[] num){
		for(int i=0;i<num.length;i++){
			num[i] = 0;
		}
		return num;
	}
	
	public static byte[] reverse(byte[] num, int index){
		if(index >= num.length){
			return num;
		}
		int row = index / (num.length);
		int col = index % (num.length);
		reverse(num[row], col);
		return num;
	}
	
	public static byte[] reverse(byte[] num){
		for(int i=0;i<num.length;i++){
			num[i] = reverse(num[i]); 
		}
		return num;
	}
	
	public static boolean[] toArray(byte[] num){
		boolean[] re = new boolean[num.length*8];
		for(int i=0;i<num.length;i++){
			for(int j=0;j<8;j++){
				re[i*8+j] = test(num[i], j);
			}
		}
		return re;
	}
	
	/** 扩展 String */
	/** 注意这里的length是位长度而非，字节长度 */
	/*public static String getResetStr(int length){
		 int size = (length + 8-1)/8;
		 byte [] bytes = new byte[size];
		 for(int i=0;i<bytes.length;i++){
			 bytes[i] = reset(bytes[i]);
		 }
		 return new String(bytes);
	}
	
	public static String getSetStr(int length){
		 int size = (length + 8-1)/8;
		 byte [] bytes = new byte[size];
		 for(int i=0;i<bytes.length;i++){
			 bytes[i] = set(bytes[i]);
		 }
		 return new String(bytes);
	}
	
	public static boolean test(String num, int index){
		byte[] bytes = num.getBytes();
		return test(bytes, index);
	}
	
	public static String set(String num, int index){
		byte[] bytes = num.getBytes();
		bytes = set(bytes, index);
		return new String(bytes);
	}
	
	public static String set(String num){
		byte[] bytes = num.getBytes();
		bytes = set(bytes);
		return new String(bytes);
	}
	
	public static String reset(String num, int index){
		byte [] bytes = num.getBytes();
		bytes = reset(bytes, index);
		return new String(bytes);
	}
	
	public static String reset(String num){
		byte [] bytes = num.getBytes();
		bytes = reset(bytes);
		return new String(bytes);
	}
	
	public static String reverse(String num, int index){
		byte[] bytes = num.getBytes();
		bytes = reset(bytes, index);
		return new String(bytes);
	}
	
	public static String reverse(String num){
		byte[] bytes = num.getBytes();
		bytes = reverse(bytes);
		return new String(bytes);
	}
	
	public static boolean[] toArray(String num){
		byte[] bytes = num.getBytes();
		boolean [] re = new boolean[bytes.length*8];
		for(int i=0;i<bytes.length;i++){
			for(int j=0;j<8;j++){
				re[i*8+j] = test(bytes[i], j);
			}
		}
		return re;
	}*/
	
	/** test */
	public static boolean test(byte num, int index){
		byte d = (byte) createNumAtIndex(index);
		byte r = (byte) (num & d);
		return r != 0 ? true : false;
	}
	
	public static boolean test(short num, int index){
		short d = (short) createNumAtIndex(index);
		short r = (short) (num & d);
		return r != 0 ? true : false;
	}
	
	public static boolean test(char num, int index){
		char d = (char) createNumAtIndex(index);
		char r = (char) (num & d);
		return r != 0 ? true : false;
	}
	
	public static boolean test(int num, int index){
		int d = (int) createNumAtIndex(index);
		int r = (int) (num & d);
		return r != 0 ? true : false;
	}
	
	public static boolean test(long num, int index){
		long d = (long) createNumAtIndex(index);
		long r = (long) (num & d);
		return r != 0 ? true : false;
	}
	
	/** 将指定位设置为1 */
	public static byte set(byte num, int index){
		//byte 8 位
		byte d = (byte) createNumAtIndex(index);
		return (byte) (num | d);
	}
	
	public static byte set(byte num){
		byte d = ~0;
		return d;
	}
	
	public static short set(short num, int index){
		//short 16位
		short d = (short) createNumAtIndex(index);
		return (short) (num | d);
	}
	
	public static short set(short num){
		short d = ~0;
		return d;
	}
	
	public static char set(char num, int index){
		//char 16位
		char d = (char) createNumAtIndex(index);
		return (char) (num | d);
	}
	
	public static char set(char num){
		char d = (char) ~0;
		return d;
	}
	
	public static int set(int num, int index){
		//int 32 位
		int d = (int) createNumAtIndex(index);
		return (int) (num | d);
	}
	
	public static int set(int num){
		int d = ~0;
		return d;
	}
	
	public static long set(long num, int index){
		//long 64 位
		long d = (long) createNumAtIndex(index);
		return (long) (num | d);
	}
	
	public static long set(long num){
		long d = ~0L;
		return d;
	}
	
	/** 将指定位设置为0 */
	public static byte reset(byte num, int index){
		byte d = (byte) createNumAtIndex(index);
		return (byte) (num & (byte)(~d));
	}
	
	public static byte reset(byte num){
		return 0;
	}

	public static short reset(short num, int index){
		short d = (short) createNumAtIndex(index);
		return (short) (num & (short)(~d));
	}
	
	public static short reset(short num){
		return 0;
	}
	
	public static char reset(char num, int index){
		char d = (char) createNumAtIndex(index);
		return (char) (num & (char)(~d));
	}
	
	public static char reset(char num){
		return 0;
	}
	
	public static int reset(int num, int index){
		int d = (int) createNumAtIndex(index);
		return (int) (num & (int)(~d));
	}
	
	public static int reset(int num){
		return 0;
	}
	
	public static long reset(long num, int index){
		long d = (long) createNumAtIndex(index);
		return (long) (num & (long)(~d));
	}
	
	public static long reset(long num){
		return 0;
	}
	
	//TODO 提高性能
	//----reverse---
	public static byte reverse(byte num, int index){
		if(test(num, index)){
			reset(num, index);
		}else{
			set(num, index);
		}
		return num;
	}
	
	public static byte reverse(byte num){
		return (byte) ~num;
	}
	
	public static short reverse(short num, int index){
		if(test(num, index)){
			reset(num, index);
		}else{
			set(num, index);
		}
		return num;
	}
	
	public static short reverse(short num){
		return (short) ~num;
	}
	
	public static char reverse(char num, int index){
		if(test(num, index)){
			reset(num, index);
		}else{
			set(num, index);
		}
		return num;
	}
	
	
	public static char reverse(char num){
		return (char) ~num;
	}
	
	public static int reverse(int num, int index){
		if(test(num, index)){
			reset(num, index);
		}else{
			set(num, index);
		}
		return num;
	}
	
	
	public static int reverse(int num){
		return (int) ~num;
	}
	
	public static long reverse(long num, int index){
		if(test(num, index)){
			reset(num, index);
		}else{
			set(num, index);
		}
		return num;
	}
	
	public static long reverse(long num){
		return (long) ~num;
	}
	
	//---------toarray-----------------
	public static boolean [] toArray(byte num){
		boolean[] d = new boolean[8];
		for(int i=0;i<8;i++){
			if(test(num, i)){
				d[i] = true;
			}else{
				d[i] = false;
			}
		}
		return d;
	}
	
	public static boolean [] toArray(short num){
		boolean[] d = new boolean[16];
		for(int i=0;i<16;i++){
			if(test(num, i)){
				d[i] = true;
			}else{
				d[i] = false;
			}
		}
		return d;
	}
	
	/**TODO
	 * 提高性能
	 * @param num
	 * @return
	 */
	public static boolean [] toArray(char num){
		boolean[] d = new boolean[16];
		for(int i=0;i<16;i++){
			if(test(num, i)){
				d[i] = true;
			}else{
				d[i] = false;
			}
		}
		return d;
	}
	
	public static boolean [] toArray(int num){
		boolean[] d = new boolean[32];
		for(int i=0;i<32;i++){
			if(test(num, i)){
				d[i] = true;
			}else{
				d[i] = false;
			}
		}
		return d;
	}
	
	public static boolean [] toArray(long num){
		boolean[] d = new boolean[64];
		for(int i=0;i<64;i++){
			if(test(num, i)){
				d[i] = true;
			}else{
				d[i] = false;
			}
		}
		return d;
	}
	
//	public static String toString(){}
	
	private static long createNumAtIndex(int index){
		long n = 1L;
		return n << index;
	}
	
}
