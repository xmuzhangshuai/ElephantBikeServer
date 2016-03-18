package com.xxn.butils;

public class LockTool {

	public String getUnlockPass(int m, int z) {

		String f = (2 * m - 1) * (m + z) * 2 + 999 + "";
		f = "0"+f.charAt(2)+f.charAt(1)+f.charAt(3)+f.charAt(0);
		System.out.println(f);
		return f;
	}
	public void loop(){
		for(int i = 1;i<25;i++){
			getUnlockPass(i, 99);
		}
	}
}
