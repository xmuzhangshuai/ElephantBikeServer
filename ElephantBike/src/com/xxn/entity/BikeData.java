package com.xxn.entity;

public class BikeData {
	private String bid;
	private int today;
	private int history;
	private float money;
	private String orderid;
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public int getToday() {
		return today;
	}
	public void setToday(int today) {
		this.today = today;
	}
	public int getHistory() {
		return history;
	}
	public void setHistory(int history) {
		this.history = history;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	public BikeData() {
		super();
	}
	public BikeData(String bid, int today, int history,
			float money, String orderid) {
		super();
		this.bid = bid;
		this.today = today;
		this.history = history;
		this.money = money;
		this.orderid = orderid;
	}
}
