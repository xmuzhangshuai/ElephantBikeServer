package com.xxn.entity;

public class Bike {
	private int id;
	private String bikeid;
	private int state;
	private String college;
	private String usedtime;
	private int m;
	private int n;
	private String lastpass;
	public String getBikeid() {
		return bikeid;
	}
	public void setBikeid(String bikeid) {
		this.bikeid = bikeid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getUsedtime() {
		return usedtime;
	}
	public void setUsedtime(String usedtime) {
		this.usedtime = usedtime;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public String getLastpass() {
		return lastpass;
	}
	public void setLastpass(String lastpass) {
		this.lastpass = lastpass;
	}
	public Bike(String bikeid, int state, String college, String usedtime) {
		super();
		this.bikeid = bikeid;
		this.state = state;
		this.college = college;
		this.usedtime = usedtime;
	}
	public Bike(String bikeid, String lasspass, int m, int n) {
		super();
		this.bikeid = bikeid;
		this.lastpass = lasspass;
		this.m = m;
		this.n = n;
	}
	
}
