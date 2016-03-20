package com.xxn.entity;

public class Order {
	
	private int id;
	private String orderid;
	private String phone;
	private String bikeid;
	private String starttime;
	private String finishtime;
	private String usedtime;
	private float cost;
	private String paymode;
	private String finishlocation;
	private String college;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBikeid() {
		return bikeid;
	}
	public void setBikeid(String bikeid) {
		this.bikeid = bikeid;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getFinishtime() {
		return finishtime;
	}
	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}
	public String getUsedtime() {
		return usedtime;
	}
	public void setUsedtime(String usedtime) {
		this.usedtime = usedtime;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getPaymode() {
		return paymode;
	}
	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
	public String getFinishlocation() {
		return finishlocation;
	}
	public void setFinishlocation(String finishlocation) {
		this.finishlocation = finishlocation;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public Order(String phone, String bikeid, String starttime) {
		super();
		this.phone = phone;
		this.bikeid = bikeid;
		this.starttime = starttime;
	}
	public Order(String phone, String bikeid, String finishtime,
			String usedtime, float cost) {
		super();
		this.phone = phone;
		this.bikeid = bikeid;
		this.finishtime = finishtime;
		this.usedtime = usedtime;
		this.cost = cost;
	}
	
	
}
