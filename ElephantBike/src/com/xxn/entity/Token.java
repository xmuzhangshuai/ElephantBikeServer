package com.xxn.entity;

public class Token {
	private int id;
	private String phone;
	private String token;
	private String logintime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public Token(String phone, String token, String logintime) {
		super();
		this.phone = phone;
		this.token = token;
		this.logintime = logintime;
	}
}
