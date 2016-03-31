package com.xxn.entity;

public class Activity {
	
	private int id;
	private String imageurl;
	private String linkurl;
	private int type;
	private int isunder;
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getLinkurl() {
		return linkurl;
	}
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getIsunder() {
		return isunder;
	}
	public void setIsunder(int isunder) {
		this.isunder = isunder;
	}
	public Activity(String imageurl, String linkurl, int isunder) {
		super();
		this.imageurl = imageurl;
		this.linkurl = linkurl;
		this.isunder = isunder;
	}
	public Activity(String imageurl, String linkurl,int type, int isunder) {
		super();
		this.imageurl = imageurl;
		this.linkurl = linkurl;
		this.type = type;
		this.isunder = isunder;
	}
}
