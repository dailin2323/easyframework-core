package com.easyframework.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Title: DateUtil.java
 * @Description: TODO
 * @author 邹凯明
 * @date 2014-3-5 上午1:12:54
 * @最后修改人：邹凯明
 * @最后修改时间：2014-3-5 上午1:12:54
 */
public class DateUtil {

	public static Date getDate(int year,int month, int day, int hour, int minute, int second){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static int getYear(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	
	public static int getMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}
	
	public static int getDayOfMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getDayOfWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public static int getDayOfYear(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_YEAR);
	}
	
	public static int getHour(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MINUTE);
	}
	
	public static int getSecond(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.SECOND);
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getCurTime(){
		return new Date();
	}
	
	public static int getMaxDayOfMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	
	public static int getMaxDayOfMonth(int year, int month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取本月最大天数
	 * @return
	 */
	public static int getMaxDayOfCurMonth(){
		Calendar cal = Calendar.getInstance();
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取相对于今天的第day天的开始时间
	 * 
	 * @param day
	 * @return
	 */
	public static Date getBeginTimeForCurDay(int day) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_YEAR, day);
		return cal.getTime();
	}

	/**
	 * 获取相对于今天的第day天的结束时间
	 * 
	 * @param day
	 * @return
	 */
	public static Date getEndTimeForCurDay(int day) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);  
		cal.add(Calendar.DAY_OF_YEAR, day);
		return cal.getTime();
	}

	/**
	 * 获取相对于本周的第week周的结束时间
	 * 
	 * @param day
	 * @return
	 */
	public static Date getBeginTimeForCurWeek(int week) {
		Calendar cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);  
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.WEEK_OF_YEAR, week);
		return cal.getTime();
	}

	public static Date getEndTimeForCurWeek(int week) {
		Calendar cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);  
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.WEEK_OF_YEAR, week);
		return cal.getTime();
	}

	/**
	 * 
	 * @param week
	 * @return
	 */
	public static Date getBeginTimeForCurMonth(int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	
		cal.add(Calendar.MONTH, month);
		
		
		return cal.getTime();
	}

	public static Date getEndTimeForCurMonth(int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	public static Date getBeginTimeForCurYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.getActualMinimum(Calendar.MONTH));					//1月
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));	//1日
		cal.set(Calendar.HOUR_OF_DAY, 0);	//0时
		cal.set(Calendar.MINUTE, 0);		//0分
		cal.set(Calendar.SECOND, 0);		//0秒
		cal.set(Calendar.MILLISECOND, 0);	//0毫秒
		cal.add(Calendar.YEAR, year);
		return cal.getTime();
	}

	public static Date getEndTimeForCurYear(int year) {
	
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));				//12月
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));//31日
		cal.set(Calendar.HOUR_OF_DAY, 23);		//23时
		cal.set(Calendar.MINUTE, 59);			//59分
		cal.set(Calendar.SECOND, 59);			//59秒
		cal.set(Calendar.MILLISECOND, 999);		//999毫秒		
		cal.add(Calendar.YEAR, year);		
		return cal.getTime();
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param week
	 * @param day
	 * @return
	 */
/*	public static Date getBeginTime(Integer year, Integer month, Integer week,
			Integer day) {

		return null;
	}
*/
	/**
	 * 
	 * @param year
	 * @param month
	 * @param week
	 * @param day
	 * @return
	 */
/*	public static Date getEndTime(Integer year, Integer month, Integer week,
			Integer day) {
		return null;
	}
*/

	public static String formate(Date date, String formate) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		if (date != null) {
			str = sdf.format(date);
		}
		return str;
	}

	public static String formateToYYMD(Date date) {
		return formate(date, "yyyy-MM-dd");
	}
	
	public static String formateToYMD(Date date) {
		return formate(date, "yy-MM-dd");
	}

	public static String formateToHMS(Date date) {
		return formate(date, "HH:mm:ss");
	}
	
	public static String formateToHM(Date date) {
		return formate(date, "HH:mm");
	}
	
	public static String formateToYYMDHMS(Date date) {
		return formate(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String formateToYMDHMS(Date date){
		return formate(date, "yy-MM-dd HH:mm:ss");
	}
	
	public static String formateToMDHMS(Date date){
		return formate(date, "MM-dd HH:mm:ss");
	}
	
	public static String formateToMDHM(Date date){
		return formate(date, "MM-dd HH:mm");
	}
	
	public static String getDefaultFormateDate(Date date){
		return formateToYYMDHMS(date);
	}

}
