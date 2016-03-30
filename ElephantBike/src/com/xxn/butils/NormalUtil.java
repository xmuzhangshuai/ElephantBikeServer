package com.xxn.butils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

import com.xxn.constants.BikeConstants;

public class NormalUtil {

	public static boolean isStringLegal(String value) {
		if (null == value || value.isEmpty())
			return false;
		else
			return true;
	}

	/**
	 * 判断该字符串是否可转化为浮点数值
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isStringFloat(String value) {
		try {
			Float.valueOf(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断该字符串是否可转化为整形数值
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isStringInteger(String value) {
		try {
			Integer.valueOf(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 根据分钟计费
	 * 
	 * @param mins
	 * @param stage
	 * @return
	 */
	public static float countFee(int mins, String stage) {

		float fee = 0.0f;
		if(stage.equals("0"))fee=1.0f;
		if(mins <= 60){
			fee = fee + 0.06f*mins;
		}
		if(mins > 60){
			fee = fee + 0.06f*60 +(mins-60)*0.02f;
		}
		return fee;
		
//		Properties prop = new Properties();
//		float fee = 0.0f;
//		float[] stagefee = new float[5];
//		float leiji = 0.0f;
//		InputStream in = null;
//
//		if (stage == BikeConstants.STAGEONE) {
//			// 阶段1收费
//			in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream(
//					"stageone.properties");
//			try {
//				prop.load(in);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		if (stage == BikeConstants.STAGETWO) {
//			// 阶段2收费
//			in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream(
//					"stagetwo.properties");
//			try {
//				prop.load(in);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		if (stage == BikeConstants.STAGETHREE) {
//			// 阶段3收费
//			in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream(
//					"stagethree.properties");
//			try {
//				prop.load(in);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		// 根据分钟数计算费用
//		Iterator<String> it = prop.stringPropertyNames().iterator();
//		while (it.hasNext()) {
//			String key = it.next();
//			if (key.equals("1"))
//				stagefee[0] = Float.valueOf(prop.getProperty(key));
//			if (key.equals("10"))
//				stagefee[1] = Float.valueOf(prop.getProperty(key));
//			if (key.equals("60"))
//				stagefee[2] = Float.valueOf(prop.getProperty(key));
//			if (key.equals("1440"))
//				stagefee[3] = Float.valueOf(prop.getProperty(key));
//			if (key.equals("1441"))
//				stagefee[4] = Float.valueOf(prop.getProperty(key));
//		}
//		if (mins <= 1) {
//			leiji = stagefee[0];
//			fee = leiji;
//		}
//		if (1 < mins && mins <= 10) {
//			leiji = stagefee[0];
//			fee = leiji + (mins - 1) * stagefee[1];
//		}
//		if (10 < mins && mins <= 60) {
//			leiji = stagefee[0] + 9 * stagefee[1];
//			fee = leiji + (mins - 10) * stagefee[2];
//		}
//		if (60 < mins && mins <= 1440) {
//			leiji = stagefee[0] + 9 * stagefee[1] + 50 * stagefee[2];
//			fee = leiji + (mins - 60) * stagefee[3];
//		}
//		if (mins > 1440) {
//			leiji = stagefee[0] + 9 * stagefee[1] + 50 * stagefee[2]
//					+ (1440 - 60) * stagefee[3];
//			fee = leiji + (mins - 1440) * stagefee[4];
//		}
//		// 关闭文件流
//		try {
//			in.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//费用四舍五入 两位小数点
//		int scale = 2;// 设置位数
//		int roundingMode = 4;// 表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
//		BigDecimal bd = new BigDecimal((double) fee);
//		bd = bd.setScale(scale, roundingMode);
//		fee = bd.floatValue();
//		return fee;
	}

	public static String generateRandom() {
		Random random = new Random();
		int x = random.nextInt(899999);
		x = x + 100000;
		return String.valueOf(x);
	}
}
