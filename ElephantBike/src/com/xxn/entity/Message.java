package com.xxn.entity;

public class Message {
	private int id;
	private String phone;
	private String createtime;
	private String content;
	private int state;
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
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Message(int id, String phone, String createtime, String content,
			int state) {
		super();
		this.id = id;
		this.phone = phone;
		this.createtime = createtime;
		this.content = content;
		this.state = state;
	}
	public Message(String phone, String createtime, String content, int state) {
		super();
		this.phone = phone;
		this.createtime = createtime;
		this.content = content;
		this.state = state;
	}
	
}
