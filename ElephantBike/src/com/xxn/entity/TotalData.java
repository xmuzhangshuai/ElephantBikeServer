package com.xxn.entity;

public class TotalData {
	private String date;
	private int count;
	private float totalfee;
	private int totaluser;
	private float averusercount;
	private float averuserfee;
	private int newuser;
	private int newvip;
	private int totalbike;
	private float averbikecount;
	private float averbikefee;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getTotalfee() {
		return totalfee;
	}
	public void setTotalfee(float totalfee) {
		this.totalfee = totalfee;
	}
	public int getTotaluser() {
		return totaluser;
	}
	public void setTotaluser(int totaluser) {
		this.totaluser = totaluser;
	}
	public float getAverusercount() {
		return averusercount;
	}
	public void setAverusercount(float averusercount) {
		this.averusercount = averusercount;
	}
	public float getAveruserfee() {
		return averuserfee;
	}
	public void setAveruserfee(float averuserfee) {
		this.averuserfee = averuserfee;
	}
	public int getNewuser() {
		return newuser;
	}
	public void setNewuser(int newuser) {
		this.newuser = newuser;
	}
	public int getNewvip() {
		return newvip;
	}
	public void setNewvip(int newvip) {
		this.newvip = newvip;
	}
	public int getTotalbike() {
		return totalbike;
	}
	public void setTotalbike(int totalbike) {
		this.totalbike = totalbike;
	}
	public float getAverbikecount() {
		return averbikecount;
	}
	public void setAverbikecount(float averbikecount) {
		this.averbikecount = averbikecount;
	}
	public float getAverbikefee() {
		return averbikefee;
	}
	public void setAverbikefee(float averbikefee) {
		this.averbikefee = averbikefee;
	}
	public TotalData(String date, int count, float totalfee,
			int totaluser, float averusercount, float averuserfee, int newuser,
			int newvip, int totalbike, float averbikecount, float averbikefee) {
		super();
		this.date = date;
		this.count = count;
		this.totalfee = totalfee;
		this.totaluser = totaluser;
		this.averusercount = averusercount;
		this.averuserfee = averuserfee;
		this.newuser = newuser;
		this.newvip = newvip;
		this.totalbike = totalbike;
		this.averbikecount = averbikecount;
		this.averbikefee = averbikefee;
	}
	
	
	
}
