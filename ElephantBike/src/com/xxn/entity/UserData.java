package com.xxn.entity;

public class UserData {
	private String tel;
	private int today;
	private int history;
	private float todaymoney;
	private float historymoney;
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public float getTodaymoney() {
		return todaymoney;
	}
	public void setTodaymoney(float todaymoney) {
		this.todaymoney = todaymoney;
	}
	public float getHistorymoney() {
		return historymoney;
	}
	public void setHistorymoney(float historymoney) {
		this.historymoney = historymoney;
	}
	public UserData(String tel, int today, int history, float todaymoney,
			float historymoney) {
		super();
		this.tel = tel;
		this.today = today;
		this.history = history;
		this.todaymoney = todaymoney;
		this.historymoney = historymoney;
	}
}
