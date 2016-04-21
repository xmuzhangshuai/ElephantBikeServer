package com.xxn.butils;

import com.xxn.entity.Bike;
import com.xxn.iservice.IBikeService;
import com.xxn.service.BikeService;

public class PassWordTool {
	
	static IBikeService iBikeService = new BikeService();
	
	
	public static String getUnlockPass(String bikeid){
		Bike bike = new Bike(bikeid,"", 0, 0);
		Bike mn = iBikeService.getBikeMN(bike);
		if(mn!= null){
			int m = mn.getM();
			int length = bikeid.length();
			String z = bikeid.substring(length-2, length);
//			System.out.println("后三位"+z);
			String unlockpass = PassDemo.getUnlockPass(m, Integer.parseInt(z));
//			System.out.println(unlockpass);
			return unlockpass;
		}else{
			System.out.println("查找不到该编号的单车");
			return "";
		}
	}
	
//	public static String getReturnPass(String bikeid){
//		Bike bike = new Bike(bikeid,"", 0, 0);
//		int t = DateTool.getT();
//		Bike resBike = iBikeService.getBikeMN(bike);
//		if(resBike!=null){
//			int n = resBike.getN();
//			int length = bikeid.length();
//			String z = bikeid.substring(length-2, length);
//			String serverpass = PassDemo.getReturnPass(n, Integer.parseInt(z), t);
//			return serverpass;
//		}
//		else{
//			System.out.println("查找不到该编号的单车");
//			return "";
//		}
//	}
	
}
