package com.xxn.entity;

public class Wallet {
	private int id;
	private String phone;
	private float balance;
	private int usedcount;
	private float fee;
	private String fee_time;
	private String remark;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public int getUsedcount() {
		return usedcount;
	}
	public void setUsedcount(int usedcount) {
		this.usedcount = usedcount;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public String getFee_time() {
		return fee_time;
	}
	public void setFee_time(String fee_time) {
		this.fee_time = fee_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Wallet(String phone, float balance, int usedcount) {
		super();
		this.phone = phone;
		this.balance = balance;
		this.usedcount = usedcount;
	}
	public Wallet(String phone, float fee, String fee_time) {
		super();
		this.phone = phone;
		this.fee = fee;
		this.fee_time = fee_time;
	}
	public Wallet(String phone) {
		super();
		this.phone = phone;
	}
	
}
