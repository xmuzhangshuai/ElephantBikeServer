package com.xxn.entity;

public class Question {
	private int id;
	private String phone;
	private String bikeid;
	private String type;
	private String voiceproof;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVoiceproof() {
		return voiceproof;
	}
	public void setVoiceproof(String voiceproof) {
		this.voiceproof = voiceproof;
	}
	public Question() {
		super();
	}
	public Question(String phone, String bikeid, String type,String voiceproof) {
		super();
		this.phone = phone;
		this.bikeid = bikeid;
		this.type = type;
		this.voiceproof = voiceproof;
	}
	public Question(int id, String phone, String bikeid, String type,
			String voiceproof) {
		super();
		this.id = id;
		this.phone = phone;
		this.bikeid = bikeid;
		this.type = type;
		this.voiceproof = voiceproof;
	}
}
