package com.xxn.butils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Lee date 2016-02
 */
public class DateTool {
	/**
	 * 时间字符串转成Date
	 * 
	 * @param value
	 * @return
	 */
	public static Date stringToDate(String value) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 时间date转成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String date2String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}

	public static String calcUsedTime(long seconds) {
		int day = 0, hour = 0, mins = 0, sec = 0;
		
		day = (int) (seconds / (60 * 60 * 24));
		hour = (int) ((seconds - day * 60 * 60 * 24) / (60 * 60));
		mins = (int) ((seconds - day * 60 * 60 * 24 - hour * 60 * 60) / 60);
		sec = (int) ((seconds - day * 60 * 60 * 24 - hour * 60 * 60 - mins * 60));
		return day+":"+hour+":"+mins+":"+sec;
	}
}
