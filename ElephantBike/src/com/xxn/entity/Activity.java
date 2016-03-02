package com.xxn.entity;

public class Activity {
	
	private int id;
	private String imageurl;
	private String linkurl;
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
}
