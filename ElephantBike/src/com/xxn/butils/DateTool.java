package com.xxn.butils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xxn.entity.Bike;

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
	
	/**
	 * 获取年月日的date
	 * @param date
	 * @return
	 */
	public static Date getYMDDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * date类型转成 年月日的类型
	 * @param date
	 * @return
	 */
	public static String dateToStringYMD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	
	public static boolean compareDate(String value){
		value = value +" 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(date.getTime()-new Date().getTime() > 0)
			return true;
		else return false;
	}

	public static String calcUsedTime(long seconds) {
		int day = 0, hour = 0, mins = 0, sec = 0;
		
		day = (int) (seconds / (60 * 60 * 24));
		hour = (int) ((seconds - day * 60 * 60 * 24) / (60 * 60));
		mins = (int) ((seconds - day * 60 * 60 * 24 - hour * 60 * 60) / 60);
		sec = (int) ((seconds - day * 60 * 60 * 24 - hour * 60 * 60 - mins * 60));

		
		String d = "" + day, h = "" + hour, m = "" + mins, s = "" + sec;
		if (day < 10)
			d = "0" + day;
		if (hour < 10)
			h = "0" + hour;
		if (mins < 10)
			m = "0" + mins;
		if (sec < 10)
			s = "0" +sec;
		return d + ":" + h + ":" + m + ":" + s;
	}

	public static List<Integer> getT() {
		List<Integer> result = new ArrayList<>();
		int t = 1,t2=1;
		String dateStr = "2016-04-13 00:00:00";
//		String dateStr = getToday();
		Date date = stringToDate(dateStr);
		long millsec = new Date().getTime() - date.getTime();
		int mins = (int) (millsec / 1000) / (60);
//		System.out.println("mins" + mins);
		t = t + mins / 30;
//		System.out.println("t:" + t);
		if (t % 8 == 0)
			t = 8;
		else
			t = t % 8;
//		System.out.println("mod8:" + t);
		result.add(t);
		//----------错时验证-------
		
		if(mins % 30 >= 15)
		{
			t2 = t + 1;
			if(t2==8)t2 = 1;
		}
		if(mins % 30 < 15)
		{
			t2 = t - 1;
			if(t2==0)t2 = 8;
		}
		System.out.println("原生t:"+t+"--备用t:"+t2);
		result.add(t2);
		return result;
	}
	
	public static String getToday(){
		String res = "";
		String sql = "select time from t_time where id= 1";
		Connection connection =  JdbcUtils_DBCP.getConnection();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String m = resultSet.getString("time");
				res = m +" 00:00:00";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_DBCP.release(connection, preparedStatement, resultSet);
		}
		System.out.println(res);
		return res;
	}

}
