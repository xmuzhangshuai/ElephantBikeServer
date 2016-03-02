package com.xxn.entity;

public class Question {
	private int id;
	private String phone;
	private String bikeid;
	private String type;
	private String description;
	private String bikeaddr;
	private String imgproof;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBikeaddr() {
		return bikeaddr;
	}
	public void setBikeaddr(String bikeaddr) {
		this.bikeaddr = bikeaddr;
	}
	public String getImgproof() {
		return imgproof;
	}
	public void setImgproof(String imgproof) {
		this.imgproof = imgproof;
	}
	public Question(String phone, String bikeid, String type,
			String description, String bikeaddr, String imgproof) {
		super();
		this.phone = phone;
		this.bikeid = bikeid;
		this.type = type;
		this.description = description;
		this.bikeaddr = bikeaddr;
		this.imgproof = imgproof;
	}
	
}
