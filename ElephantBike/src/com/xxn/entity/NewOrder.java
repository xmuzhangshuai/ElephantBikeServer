package com.xxn.entity;

public class NewOrder {
	private int id;
	private String date;
	private int count;
	private float totalfee;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public NewOrder(String date, int count, float totalfee) {
		super();
		this.date = date;
		this.count = count;
		this.totalfee = totalfee;
	}
}
