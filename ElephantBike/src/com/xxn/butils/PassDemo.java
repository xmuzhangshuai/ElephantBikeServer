package com.xxn.butils;

public class PassDemo {

	public static String getUnlockPass(int m, int z) {

		if (m > 24)
			if (m % 24 != 0)
				m = m % 24;
			else
				m = 24;
		String f = (2 * m - 1) * (m + z) * 2 + 999 + "";
		int length = f.length();
		f = "0" + f.charAt(length - 2) + f.charAt(length - 3)
				+ f.charAt(length - 1) + f.charAt(length - 4);
		return f;
	}

	public static String getReturnPass(int n, int z, int t) {
		if (n > 24){
			if (n % 24 != 0)n = n % 24;
			else n = 24;
		}
		if (t > 8){
			if (t % 8 != 0) t = t % 8;
			else t = 8;
		}
		t = t + 6;
			
		int g = (2 * n - 1) * (n + z) + 999;
		int h = t * (t + z) + 98;
		String _g = g + "";
		int _g_length = _g.length();
		String _h = h + "";
		int _h_length = _h.length();
		System.out.println("n"+n+"--t"+t+"--g"+g+"--h"+h);
		String res = g % 6 + ""+_h.charAt(_h_length - 2)+ _g.charAt(_g_length - 3) + _h.charAt(_h_length - 1)+ _g.charAt(_g_length - 1) + "";
		System.out.println(res);
		return res;
	}

}
