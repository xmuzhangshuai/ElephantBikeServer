package com.xxn.entity;

public class Question {
	private int id;
	private String phone;
	private String bikeid;
	private String type;
	private String voiceproof;
	private int issolved;
	private String createtime;
	private String needfrozen;
	private String dealtime;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIssolved() {
		return issolved;
	}
	public void setIssolved(int issolved) {
		this.issolved = issolved;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getNeedfrozen() {
		return needfrozen;
	}
	public void setNeedfrozen(String needfrozen) {
		this.needfrozen = needfrozen;
	}
	public String getDealtime() {
		return dealtime;
	}
	public void setDealtime(String dealtime) {
		this.dealtime = dealtime;
	}
	public Question() {
		super();
	}
	public Question(String phone, String bikeid, String type,
			String voiceproof, int issolved, String createtime,
			String needfrozen) {
		super();
		this.phone = phone;
		this.bikeid = bikeid;
		this.type = type;
		this.voiceproof = voiceproof;
		this.issolved = issolved;
		this.createtime = createtime;
		this.needfrozen = needfrozen;
	}
	public Question(int id, String phone, String bikeid, String type,
			String voiceproof, int issolved, String createtime,
			String needfrozen,String dealtime) {
		super();
		this.id = id;
		this.phone = phone;
		this.bikeid = bikeid;
		this.type = type;
		this.voiceproof = voiceproof;
		this.issolved = issolved;
		this.createtime = createtime;
		this.needfrozen = needfrozen;
		this.dealtime = dealtime;
	}
}
